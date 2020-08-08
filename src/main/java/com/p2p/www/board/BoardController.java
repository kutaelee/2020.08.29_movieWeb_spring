package com.p2p.www.board;

import java.util.HashMap;
import java.util.List;

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
		int pageNum = 1;
		int count = bd.getBoardCount(request.getParameter("link"));
		int lastPage = (int) Math.ceil(count / 10d);
		try {
			if (!ObjectUtils.isEmpty(request.getParameter("pageNum"))) {
				pageNum = Integer.parseInt(request.getParameter("pageNum"));
				if (pageNum > lastPage) {
					pageNum = lastPage;
				} else if (pageNum < 0) {
					pageNum = 1;
				}

			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		List<HashMap<String, Object>> list = bd.boardList((pageNum - 1) * 10);
		list = bs.regDateFormat(list);

		return list;
	}

	@GetMapping("/getBoardCount")
	public int getBoardCount(WebRequest request) {

		return bd.getBoardCount(request.getParameter("link"));
	}
}
