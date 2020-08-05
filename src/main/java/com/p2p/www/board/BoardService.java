package com.p2p.www.board;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

@Service
public class BoardService {
	@Autowired
	BoardDao bd;

	public List<HashMap<String, Object>> regDateFormat(List<HashMap<String, Object>> list) {
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

		for (int i = 0; i < list.size(); i++) {

			list.get(i).put("REG_DATE", format.format(list.get(i).get("REG_DATE")));
		}

		return list;
	}
}
