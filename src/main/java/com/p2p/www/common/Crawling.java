package com.p2p.www.common;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawling {
	public ArrayList<String> getLink(String path, String tagName) throws IOException {
		StringBuilder url = new StringBuilder();
		ArrayList<String> crawList = new ArrayList<String>();
		int i = 1;

		while (true) {
			url.append(path).replace(url.length() - Integer.toString(i).length(), url.length(), Integer.toString(i));
			System.out.println(url.toString());
			Document doc = Jsoup.connect(url.toString()).get();

			Elements els = doc.select(tagName.toString());

			for (Element el : els) {
				crawList.add(el.absUrl("href"));
			}
			System.out.println(crawList);
			if (!doc.select(".pagenavigation>table>tbody>tr>td:last-child").text().equals("다음"))
				break;
			url.delete(0, url.length());
			i++;
			if(i==2)
				break;
		}

		return crawList;
	}

	public ArrayList<HashMap<String, String>> getContents(ArrayList<String> linkList) throws IOException {
		ArrayList<HashMap<String, String>> contents = new ArrayList<HashMap<String, String>>();
		Document doc = new Document("");
		Elements els = new Elements();

		for (String url : linkList) {
			HashMap<String, String> content = new HashMap<String, String>();
			doc = Jsoup.connect(url.toString()).get();
			content.put("title", doc.select(".mv_info>h3>a:first-child").first().text());

			// poster
			els = doc.select(".poster>a>img:first-child");
			if (!els.isEmpty()) {
				content.put("poster", els.get(0).absUrl("src"));
			} else {
				content.put("poster", "null");
			}

			// story_area
			els = doc.select(".story_area>p");
			if (!els.isEmpty()) {
				content.put("content", els.toString());
			} else {
				content.put("content", "<p>줄거리가 없습니다.</p>");
			}

			// people
			els = doc.select(".people>ul>li");
			if (!els.isEmpty()) {
				content.put("people", els.toString());
			} else {
				content.put("people", "<p>배우/제작진 정보가 없습니다.</p>");
			}

			contents.add(content);


		}
		return contents;
	}

	public static void main(String args[]) throws IOException {
		
		  Crawling cl = new Crawling(); ArrayList<HashMap<String, String>> contents =
		  new ArrayList<HashMap<String, String>>(); contents=cl.getContents(cl.getLink(
		  "https://movie.naver.com/movie/sdb/browsing/bmovie.nhn?year=2000&page=1",
		  ".directory_list>li>a")); System.out.println(contents.get(0).get("title"));
		  System.out.println(contents.get(0).get("poster"));
		  System.out.println(contents.get(0).get("content"));
		  System.out.println(contents.get(0).get("people")); for(HashMap<String,String>
		  content:contents) {
		  
		  }
		 

	}

}
