package com.dayone.scheduler;

import com.dayone.model.Company;
import com.dayone.model.ScrapedResult;
import com.dayone.model.constants.CacheKey;
import com.dayone.persist.CompanyRepository;
import com.dayone.persist.DividendRepository;
import com.dayone.persist.entity.CompanyEntity;
import com.dayone.persist.entity.DividendEntity;
import com.dayone.scraper.Scraper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
@EnableCaching
public class ScraperScheduler {

    private final CompanyRepository companyRepository;
    private final DividendRepository dividendRepository; //배당금 정보 저장 위해서

    private final Scraper yahooFinanceScraper;


    @CacheEvict(value = CacheKey.KEY_FINANCE, allEntries = true) //레디스 캐시 키 중 finance에 해당하는 데이터는 모두 비우기
    // scheduler가 동작할때마다 캐시에 있는 데이터가 모두 비워지게됨. 배당금이 조회될때마다 다시 올라가게됨
    @Scheduled(cron = "${scheduler.scrap.yahoo}")
    public void yahooFinanceScheduling(){
        log.info("scraping scheduler started");

        //저장된 회사 목록 조회
        List<CompanyEntity> companies = this.companyRepository.findAll();

        //회사 하나씩 순회하면서 배당금 정보를 스크래핑
        for (var company : companies){
            log.info("scraping scheduler started -> "+ company.getName());
            ScrapedResult scrapedResult = this.yahooFinanceScraper.scrap(
                                                                    new Company(company.getTicker(), company.getName()));


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
