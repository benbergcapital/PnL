package com.ben.main;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class GoogleScrape {
	
	public String getLast(String Ticker) {
		try {
				return scrape_value(Ticker);
			} catch (IOException e) {
				return "--error--";
			}
			
		
		
		

	}
	public String getLastChange() {
		try {
			return scrape_change();
		} catch (IOException e) {
			return "--error--";
		}

	}
	private String scrape_value(String Ticker) throws IOException {
		Document doc_curr = Jsoup.connect("https://www.google.co.uk/finance?q="+Ticker).get();

		Element content_curr = doc_curr.getElementById("price-panel");
		Elements links_curr = content_curr.getElementsByClass("pr");
		
		
	
		for (Element link : links_curr) {
			System.out.println(link.text());
			return link.text();
			
		}
		
		
		return "0";
	}

	private String scrape_change() throws IOException {
		int m = 0;
		Document doc_curr = Jsoup.connect("https://www.google.co.uk/finance?q=GOOG").get();
		Element content_curr = doc_curr.getElementById("price-panel");
		Elements links_curr_change = content_curr.getElementsByClass("chr");
		
		for (Element link : links_curr_change) {
			System.out.println(link.text());

		//	if (m == 3)
			//	 if (link.text().startsWith("-"))
				//	 return "<font color=\"red\">"+link.text()+"</font>";						 
			//	 else   return "<font color=\"green\">"+link.text()+"</font>";	
				

			m++;
		}
		
		
		
		
		return "0";
	}

	
}


