package com.dayone.service;

import com.dayone.model.Company;
import com.dayone.model.ScrapedResult;
import com.dayone.persist.CompanyRepository;
import com.dayone.persist.DividendRepository;
import com.dayone.persist.entity.CompanyEntity;
import com.dayone.persist.entity.DividendEntity;
import com.dayone.scraper.Scraper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CompanyService {

    private final Scraper yahooFinanceScraper;

    private final CompanyRepository companyRepository;
    private final DividendRepository dividendRepository;

    public Company save (String ticker){
        boolean exists = this.companyRepository.existsByTicker(ticker);
        if (exists){
            throw new RuntimeException("already existing ticker -> " + ticker);
        }
        return /*==========*/this.storeCompanyAndDividend(ticker);
    }

    public Page<CompanyEntity> getAllCompany(Pageable pageable){
        return this.companyRepository.findAll(pageable);
    }

    private Company storeCompanyAndDividend(String ticker){
        //ticker를 기준으로 회사 스크래핑
        Company company = this.yahooFinanceScraper.scrapCompanyByTicker(ticker);
        if (ObjectUtils.isEmpty(company)){
            throw new RuntimeException("failed to scrap ticker -> " + ticker);
        }

        //해당회사가 존재하면 배당금 정보를 스크래핑
        ScrapedResult scrapedResult = this.yahooFinanceScraper.scrap(company);

        //스크래핑 결과
        CompanyEntity companyEntity = this.companyRepository.save(new CompanyEntity(company));
        List<DividendEntity> dividendEntities = scrapedResult.getDividendEntities().stream()
                                                            .map(e/*컬렉션의 아이템 하나하나. 여기선 Dividend의 아이템*/ -> new DividendEntity(companyEntity.getId(), e))
                                                            .collect(Collectors.toList());
        this.dividendRepository.saveAll(dividendEntities);
        return company;
    }
}