package com.dayone.service;

import com.dayone.model.Company;
import com.dayone.model.Dividend;
import com.dayone.model.ScrapedResult;
import com.dayone.persist.CompanyRepository;
import com.dayone.persist.DividendRepository;
import com.dayone.persist.entity.CompanyEntity;
import com.dayone.persist.entity.DividendEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FinanceService {

    private final CompanyRepository companyRepository;
    private final DividendRepository dividendRepository;

    public ScrapedResult getDividendByCompanyName(String companyName) {
        //1.회사명 기준으로 회사 정보 조회
        CompanyEntity company = this.companyRepository.findByName(companyName)
                                            .orElseThrow(() -> new RuntimeException("존재하지않는 회사명입니다"));


        //2. 조회된 회사 아이디로 배당금 정보 조회
        List<DividendEntity> dividendEntities = this.dividendRepository.findAllByCompanyId(company.getId());

        //3. 결과 조합 후 scrapedResult로 반환
        /*ScrapedResult 안에는 CompanyEntity & 리스트<DividendEntity>가 아니라
        * Company랑 리스트<Dividend>로 구성돼있음. 일반 모델 클래스로 매핑하는 작업 필요*/


        List<Dividend> dividends = new ArrayList<>(); //결과를 담을 dividends 리스트 만들기

        for (var entity : dividendEntities){
            dividends.add(Dividend.builder()
                                    .date(entity.getDate())
                                    .dividend(entity.getDividend())
                                    .build());
        }

        /*for문 말고 스트림으로도 가능. 취향에 맞게
        List<Dividend> dividends = dividendEntities.stream()
                                                    .map(e -> Dividend.builder()
                                                            .date(e.getDate())
                                                            .dividend(e.getDividend())
                                                            .build())
                                                    .collect(Collectors.toList()); */

        return new ScrapedResult(Company.builder() /*CompanyEntity를 Company 모델로 바꿔주기*/
                                        .ticker(company.getTicker())
                                        .name(company.getName())
                                        .build(),
                dividends);
    }
}