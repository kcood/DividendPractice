package com.dayone.service;

<<<<<<< HEAD
import com.dayone.model.Company;
import com.dayone.model.ScrapedResult;
=======
import com.dayone.exception.impl.NoCompanyException;
import com.dayone.model.Company;
import com.dayone.model.ScrapedResult;
import com.dayone.model.constants.CacheKey;
>>>>>>> c283280 (Initial commit)
import com.dayone.persist.CompanyRepository;
import com.dayone.persist.DividendRepository;
import com.dayone.persist.entity.CompanyEntity;
import com.dayone.persist.entity.DividendEntity;
import com.dayone.scraper.Scraper;
import lombok.AllArgsConstructor;
<<<<<<< HEAD
import org.apache.commons.collections4.Trie;
=======
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.Trie;
import org.apache.commons.collections4.trie.PatriciaTrie;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.cache.annotation.CacheEvict;
>>>>>>> c283280 (Initial commit)
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

<<<<<<< HEAD
=======
@Slf4j
>>>>>>> c283280 (Initial commit)
@Service
@AllArgsConstructor
public class CompanyService {

    private final Trie trie;
    private final Scraper yahooFinanceScraper;
<<<<<<< HEAD

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
=======
    private final CompanyRepository companyRepository;
    private final DividendRepository dividendRepository;

    public Company save(String ticker) {
        throw new NotYetImplementedException();
    }

    public Page<CompanyEntity> getAllCompany(Pageable pageable) {
        throw new NotYetImplementedException();
>>>>>>> c283280 (Initial commit)
    }

    private Company storeCompanyAndDividend(String ticker){

        //ticker??? ???????????? ?????? ????????????
        Company company = this.yahooFinanceScraper.scrapCompanyByTicker(ticker);
        if (ObjectUtils.isEmpty(company)){
            throw new RuntimeException("failed to scrap ticker -> " + ticker);
        }

        //??????????????? ???????????? ????????? ????????? ????????????
        ScrapedResult scrapedResult = this.yahooFinanceScraper.scrap(company);

        //???????????? ??????
        CompanyEntity companyEntity = this.companyRepository.save(new CompanyEntity(company));
        List<DividendEntity> dividendEntities = scrapedResult.getDividends().stream()
<<<<<<< HEAD
                                                            .map(e/*???????????? ????????? ????????????. ????????? Dividend??? ?????????*/ -> new DividendEntity(companyEntity.getId(), e))
                                                            .collect(Collectors.toList());
=======
                .map(e/*???????????? ????????? ????????????. ????????? Dividend??? ?????????*/ -> new DividendEntity(companyEntity.getId(), e))
                .collect(Collectors.toList());
>>>>>>> c283280 (Initial commit)
        this.dividendRepository.saveAll(dividendEntities);
        return company;
    }

<<<<<<< HEAD
    public List<String> getCompanyNamesByKeyword(String keyword){
        Pageable limit = PageRequest.of(0,10);

        Page<CompanyEntity> companyEntities = this.companyRepository.findByNameStartingWithIgnoreCase(keyword, limit);
        return companyEntities.stream()
                .map(e -> e.getName())
                .collect(Collectors.toList()); //companyEntities??? ?????? ????????? ??????
    }

    public void addAutocompleteKeyword(String keyword){
        this.trie.put(keyword,null);
    }

    public List<String> autocomplete(String keyword){
        return (List<String>) this.trie.prefixMap(keyword).keySet()
                .stream()
                //.limit(10) /*???????????? ????????????*/
                .collect(Collectors.toList());
    }

    public void deleteAutocompleteKeyword(String keyword){
        this.trie.remove(keyword);
    }
=======
    public List<String> getCompanyNamesByKeyword(String keyword) {
        throw new NotYetImplementedException();
    }

    public void addAutocompleteKeyword(String keyword) {
        this.trie.put(keyword, null);
    }

    public List<String> autocomplete(String keyword) {
        return (List<String>) this.trie.prefixMap(keyword).keySet()
                .stream()
                .collect(Collectors.toList());
    }

    public void deleteAutocompleteKeyword(String keyword) {
        this.trie.remove(keyword);
    }

    public String deleteCompany(String ticker){
        var company = this.companyRepository.findByTicker(ticker)
                .orElseThrow(() -> new RuntimeException("???????????? ?????? ???????????????"));

        this.dividendRepository.deleteAllByCompanyId(company.getId());
        this.companyRepository.delete(company);

        this.deleteAutocompleteKeyword(company.getName());
        return company.getName();
    }

>>>>>>> c283280 (Initial commit)
}
