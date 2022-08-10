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
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;

@SpringBootApplication
@EnableScheduling
public class DivinedApplication {

	public static void main(String[] args) {
		SpringApplication.run(DivinedApplication.class, args);

	}
}
