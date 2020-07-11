package com.p2p.www;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.p2p.www.common.Crawling;

//스프링의 테스트 컨텍스트 프레임워크의 JUnit 확장기능 지정
@RunWith(SpringJUnit4ClassRunner.class) 

@PropertySource("classpath:db.properties")
//테스트 컨텍스트가 자동으로 만들어줄 애플리케이션 컨텍스트의위치 지정
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"})

public class TestClass {
	@Autowired
	Crawling cr;
	
	@Test
	public void crawlingTest() throws IOException {
		String url = "https://movie.naver.com/movie/sdb/browsing/bmovie.nhn?year=2020&page=1";
		String tagName = ".directory_list>li>a";
		ArrayList<String> linkList = new ArrayList<String>();
		ArrayList<HashMap<String, String>> contents = new ArrayList<HashMap<String, String>>();
		linkList = cr.getLink(url, tagName);
		contents = cr.getContents(linkList);

		System.out.println(contents.size());

		for (int i = 0; i < contents.size(); i++) {
			System.out.println("***********************OBJECT*****************************");
			System.out.println(contents.get(i).toString());
			System.out.println("***********************END*****************************");
		}
	}
}
