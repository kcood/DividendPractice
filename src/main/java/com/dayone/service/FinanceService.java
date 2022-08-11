package com.dayone.service;

import com.dayone.model.Company;
import com.dayone.model.Dividend;
import com.dayone.model.ScrapedResult;
import com.dayone.persist.CompanyRepository;
import com.dayone.persist.DividendRepository;
import com.dayone.persist.entity.CompanyEntity;
import com.dayone.persist.entity.DividendEntity;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
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

    //배당금 정보를 캐싱하기위한 고려사항
    //요청이 얼마나 자주 오는가? -> 특정데이터에 대한 요청 몰리는편. 유명한 회사는 자주
    //자주 변경되는 데이터인가? -> 배당금은 몇달에 한번.
    // -> 배당금 데이터는 캐시 처리가 더 효율적

    @Cacheable(key = "#companyName",value = "finance")
    public ScrapedResult getDividendByCompanyName(String companyName) {
        //1.회사명 기준으로 회사 정보 조회
        CompanyEntity company = this.companyRepository.findByName(companyName)
                                            .orElseThrow(() -> new RuntimeException("존재하지않는 회사명입니다"));


        //2. 조회된 회사 아이디로 배당금 정보 조회
        List<DividendEntity> dividendEntities = this.dividendRepository.findAllByCompanyId(company.getId());

        //3. 결과 조합 후 scrapedResult로 반환
        /*ScrapedResult 안에는 CompanyEntity & 리스트<DividendEntity>가 아니라
        * Company랑 리스트<Dividend>로 구성돼있음. 일반 모델 클래스로 매핑하는 작업 필요*/


        //List<Dividend> dividends = new ArrayList<>(); //결과를 담을 dividends 리스트 만들기

//        for (var entity : dividendEntities){
//            dividends.add(Dividend.builder()
//                                    .date(entity.getDate())
//                                    .dividend(entity.getDividend())
//                                    .build());
//        }

        //for문 말고 스트림으로도 가능. 취향에 맞게
        List<Dividend> dividends = dividendEntities.stream()
                                                    .map(e -> new Dividend(e.getDate(), e.getDividend()))
                                                    .collect(Collectors.toList());

        return new ScrapedResult(new Company(company.getTicker(), company.getName()), dividends);
    }
}
