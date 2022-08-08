package com.example.divined;

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

		try {
			Connection connection = Jsoup.connect("https://finance.yahoo.com/quote/QQQ/history?period1=921024000&period2=1659830400&interval=1mo&filter=history&frequency=1mo&includeAdjustedClose=true");
			Document document = connection.get();

			Elements eles = document.getElementsByAttributeValue("data-test", "historical-prices");
			Element ele = eles.get(0);

			System.out.println(ele);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
