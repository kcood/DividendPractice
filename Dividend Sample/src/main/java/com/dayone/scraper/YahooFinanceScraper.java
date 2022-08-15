package com.dayone.scraper;

import com.dayone.model.Company;
import com.dayone.model.Dividend;
import com.dayone.model.ScrapedResult;
import com.dayone.model.constants.Month;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class YahooFinanceScraper implements Scraper {

    private static final String STATISTICS_URL = "https://finance.yahoo.com/quote/%s/history?period1=%d&period2=%d&interval=1mo";
    private static final String SUMMARY_URL = "https://finance.yahoo.com/quote/%s?p=%s";

<<<<<<< HEAD
    private static final long START_TIME = 86400; //60초 * 60분 * 24시간 (1일)


    @Override
    public ScrapedResult scrap(Company company){
        var scrapResult = new ScrapedResult();
        scrapResult.setCompany(company);
        try {
            long now = System.currentTimeMillis()/1000; //현재시간

            String url = String.format(STATISTICS_URL, company.getTicker(), START_TIME, now);

=======
    private static final long START_TIME = 86400;   // 60 * 60 * 24

    @Override
    public ScrapedResult scrap(Company company) {
        var scrapResult = new ScrapedResult();
        scrapResult.setCompany(company);

        try {
            long now = System.currentTimeMillis() / 1000;

            String url = String.format(STATISTICS_URL, company.getTicker(), START_TIME, now);
>>>>>>> c283280 (Initial commit)
            Connection connection = Jsoup.connect(url);
            Document document = connection.get();

            Elements parsingDivs = document.getElementsByAttributeValue("data-test", "historical-prices");
<<<<<<< HEAD
            Element tableEle = parsingDivs.get(0); //테이블 전체

            Element tbody = tableEle.children().get(1); //thead = 0, tbody = 1, tfoot=2
=======
            Element tableEle = parsingDivs.get(0);  // table 전체

            Element tbody = tableEle.children().get(1);
>>>>>>> c283280 (Initial commit)

            List<Dividend> dividends = new ArrayList<>();
            for (Element e : tbody.children()) {
                String txt = e.text();
<<<<<<< HEAD
                if (!txt.endsWith("Dividend")){
=======
                if (!txt.endsWith("Dividend")) {
>>>>>>> c283280 (Initial commit)
                    continue;
                }

                String[] splits = txt.split(" ");
                int month = Month.strToNumber(splits[0]);
                int day = Integer.valueOf(splits[1].replace(",", ""));
                int year = Integer.valueOf(splits[2]);
<<<<<<< HEAD
                String divined = splits[3];
=======
                String dividend = splits[3];
>>>>>>> c283280 (Initial commit)

                if (month < 0) {
                    throw new RuntimeException("Unexpected Month enum value -> " + splits[0]);
                }

<<<<<<< HEAD
                dividends.add(new Dividend(LocalDateTime.of(year, month, day, 0, 0), divined));
//                System.out.println(year + "/" + month + "/" + day + " -> " + divined);
            }
            scrapResult.setDividends(dividends);


        } catch (IOException e) {
            e.printStackTrace();
        }
=======
                Dividend d = null;  // not implemented yet
                dividends.add(d);

            }
            scrapResult.setDividends(dividends);

        } catch (IOException e) {
            // TODO error handling
            e.printStackTrace();
        }

>>>>>>> c283280 (Initial commit)
        return scrapResult;
    }

    @Override
    public Company scrapCompanyByTicker(String ticker) {
        String url = String.format(SUMMARY_URL, ticker, ticker);

        try {
            Document document = Jsoup.connect(url).get();
            Element titleEle = document.getElementsByTag("h1").get(0);
<<<<<<< HEAD

            String title = titleEle.text().split(" - ")[1].trim(); //야후 파이낸스가 회사명 구분할때의 특성때문에, 깔끔하게 고치기위해

            return new Company(ticker, title);


=======
            String title = titleEle.text().split(" - ")[1].trim();

            return new Company(ticker, title);
>>>>>>> c283280 (Initial commit)
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
