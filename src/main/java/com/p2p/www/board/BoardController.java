package com.p2p.www.board;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
public class BoardController {
	@Autowired
	BoardService bs;
	@Autowired
	BoardDao bd;

	@GetMapping("/boardList")
	public List<HashMap<String, Object>> boardList(WebRequest request) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("link", request.getParameter("link"));

		if (ObjectUtils.isEmpty(request.getParameter("keyword"))) {
			params.put("keyword", null);
		} else {
			params.put("keyword", request.getParameter("keyword"));
		}

		int pageNum = 1;
		int count = bd.getBoardCount(params);
		int lastPage = (int) Math.ceil(count / 10d);

		if (lastPage <= 0) {
			return null;
		}

		try {
			if (!ObjectUtils.isEmpty(request.getParameter("pageNum"))) {
				if (request.getParameter("pageNum").equals("index")) {
					Random random = new Random();
					random.setSeed(System.currentTimeMillis());
					pageNum = random.nextInt(lastPage);
				} else {
					pageNum = Integer.parseInt(request.getParameter("pageNum"));
				}

				if (pageNum > lastPage) {
					pageNum = lastPage;
				} else if (pageNum < 0) {
					pageNum = 1;
				}

			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		if (ObjectUtils.isEmpty(request.getParameter("keyword"))) {
			params.put("startNum", (count - ((pageNum - 1) * 10)) - 10);
			params.put("endNum", count - ((pageNum - 1) * 10));
		} else {
			params.put("startNum", (pageNum - 1) * 10);
		}

		List<HashMap<String, Object>> list = bd.boardList(params);
		list = bs.regDateFormat(list);

		return list;
	}

	@GetMapping("/getBoardCount")
	public int getBoardCount(WebRequest request) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("link", request.getParameter("link"));
		params.put("keyword", request.getParameter("keyword"));

		return bd.getBoardCount(params);
	}
}
