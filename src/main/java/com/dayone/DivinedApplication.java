package com.dayone;

import com.dayone.model.Company;
import com.dayone.scraper.Scraper;
import com.dayone.scraper.YahooFinanceScraper;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class DivinedApplication {

	public static void main(String[] args) {
//		SpringApplication.run(DivinedApplication.class, args);

		Scraper scraper = new YahooFinanceScraper(); //NaverFinanceScrapper
		//var result = scraper.scrap(Company.builder().ticker("O").build());
		var result = scraper.scrapCompanyByTicker("MMM");
		System.out.println(result);
	}
}
