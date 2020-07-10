package com.p2p.www.common;

import java.io.IOException;
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
			url.append(path).replace(url.length() - 1, url.length(), Integer.toString(i));
			Document doc = Jsoup.connect(url.toString()).get();

			Elements els = doc.select(tagName.toString());

			for (Element el : els) {
				crawList.add(el.absUrl("href"));
			}

			if (!doc.select(".pagenavigation>table>tbody>tr>td:last-child").text().equals("다음"))
				break;
			url.delete(0, url.length());
			i++;
		}

		return crawList;
	}

	public ArrayList<HashMap<String, String>> getContents(ArrayList<String> linkList) throws IOException {
		ArrayList<HashMap<String, String>> contents = new ArrayList<HashMap<String, String>>();
		StringBuilder sb = new StringBuilder();
		Document doc = new Document("");
		Elements els = new Elements();

		for (String url : linkList) {
			HashMap<String, String> content = new HashMap<String, String>();
			doc = Jsoup.connect(url.toString()).get();
			content.put("title", doc.select(".mv_info>h3>a:first-child").first().text());

			// poster
			els = doc.select(".poster>img");
			if (!els.isEmpty()) {
				content.put("poster", els.toString());
			} else {
				content.put("poster", "이미지 준비 중 입니다.");
			}

			// story_area
			els = doc.select(".story_area>p");
			sb.append("<h4>줄거리</h4>");
			if (!els.isEmpty()) {
				sb.append(els.toString());
			} else {
				sb.append("<p>줄거리가 없습니다.</p>");
			}

			// people
			els = doc.select(".people>ul>li");
			sb.append("<h4>배우/제작진</h4>");
			if (!els.isEmpty()) {
				sb.append(els.toString());
			} else {
				sb.append("<p>배우/제작진 정보가 없습니다.</p>");
			}

			content.put("info", sb.toString());
			contents.add(content);

			sb.delete(0, sb.length());

		}
		return contents;
	}

}
