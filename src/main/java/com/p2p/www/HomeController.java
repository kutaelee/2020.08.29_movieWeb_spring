package com.p2p.www;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.request.WebRequest;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@GetMapping(value = "/")
	public String home() {
		return "index";
	}
	
	@GetMapping(value ="/page/{pageName}")
	public String page(@PathVariable String pageName,WebRequest request) {
		
		if(pageName.equals("movie")||pageName.equals("drama")) {
			return "board";
		}else {
			return pageName;
		}
	}
	@GetMapping(value ="/page/document/{seq}")
	public String page(@PathVariable String seq) {
		return "document";
	}
	
}
