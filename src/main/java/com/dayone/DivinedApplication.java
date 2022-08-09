package com.dayone;

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
		SpringApplication.run(DivinedApplication.class, args);

		try {
			Connection connection = Jsoup.connect("https://finance.yahoo.com/quote/QQQ/history?period1=921024000&period2=1659830400&interval=1mo&filter=history&frequency=1mo&includeAdjustedClose=true");
			Document document = connection.get();

			Elements eles = document.getElementsByAttributeValue("data-test", "historical-prices");
			Element ele = eles.get(0); //테이블 전체

			Element tbody = ele.children().get(1); //thead = 0, tbody = 1, tfoot=2
			for (Element e : tbody.children()) {
				String txt = e.text();
				if (!txt.endsWith("Dividend")){
					continue;
				}

				String[] splits = txt.split(" ");
				String month = splits[0];
				int day = Integer.valueOf(splits[1].replace(",", ""));
				int year = Integer.valueOf(splits[2]);
				String divined = splits[3];

				System.out.println(year + "/" + month + "/" + day + " -> " + divined);
			}



		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}