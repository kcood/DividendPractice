package com.dayone.scheduler;

import com.dayone.model.Company;
import com.dayone.model.ScrapedResult;
import com.dayone.persist.CompanyRepository;
import com.dayone.persist.DividendRepository;
import com.dayone.persist.entity.CompanyEntity;
import com.dayone.persist.entity.DividendEntity;
import com.dayone.scraper.Scraper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class ScraperScheduler {

    private final CompanyRepository companyRepository;

    private final DividendRepository dividendRepository; //배당금 정보 저장 위해서

    private final Scraper yahooFinanceScraper;


    @Scheduled(cron = "0 0 0 * * *") //매일 정각
    public void yahooFinanceScheduling(){
//        log.info("scraping scheduler started");

        //저장된 회사 목록 조회
        List<CompanyEntity> companies = this.companyRepository.findAll();

        //회사 하나씩 순회하면서 배당금 정보를 스크래핑
        for (var company : companies){
            log.info("scraping scheduler started -> "+ company.getName());
            ScrapedResult scrapedResult = this.yahooFinanceScraper.scrap(Company.builder()/*scrap 메소드는 파라미터로 company 타입을 받음.
                                                                                            지금 여기 company는 company타입이 아니라 companEntity니까
                                                                                            company빌더로 company 인스턴스에 매핑시켜줘야함*/
                                                                            .name(company.getName())
                                                                            .ticker(company.getTicker())
                                                                            .build());

            //스크래핑한 배당금 정보 중 db에 없는 값은 저장
            scrapedResult.getDividends().stream()
                    //디비든 모델은 디비등 엔티티로 매핑
                    .map(e -> new DividendEntity(company.getId(), e))
                    //엘리먼트를 하나씩 디비든 레파지토리에 삽입
                    .forEach(e -> {
                        boolean exists = this.dividendRepository.existsByCompanyIdAndDate(e.getCompanyId(), e.getDate());//->db에 이미 존재하는지 여부
                        if (!exists){
                            this.dividendRepository.save(e); //존재하지 않으면 dividend엔티니 e를 저장
                        }
                    });

            //연속적으로 스크래핑 대상 사이트 서버에 요청을 날리지 않도록 일시정지
            try {
                Thread.sleep(3000); //3초간 스레드 정지
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        }
    }

}
