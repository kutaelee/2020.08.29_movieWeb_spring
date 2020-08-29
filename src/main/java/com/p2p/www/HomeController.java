package com.p2p.www;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.request.WebRequest;

import com.p2p.www.board.BoardDao;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	BoardDao bd;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@GetMapping(value = "/error")
	public String error() {
		return "error";
	}

	@GetMapping(value = "/")
	public String home() {
		return "index";
	}

	@GetMapping(value = "/page/{pageName}")
	public String page(@PathVariable String pageName, WebRequest request) {

		if (pageName.equals("movie") || pageName.equals("drama")) {
			return "board";
		} else {
			return "error";
		}
	}

	@GetMapping(value = "/page/document/{seq}")
	public String page(@PathVariable String seq) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("link", "/page/movie");
		int count = bd.getBoardCount(params);
		if (Integer.parseInt(seq) > count) {
			return "error";
		} else {
			return "document";
		}
	}

}
