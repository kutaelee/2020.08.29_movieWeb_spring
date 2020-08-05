package com.p2p.www.document;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
public class DocumentController {
	@Autowired
	DocumentService ds;
	@Autowired
	DocumentDao dd;
	
	@GetMapping("/document")
	public HashMap<String,Object> getDocument(WebRequest request) {
		HashMap<String,Object> map=dd.getDocument(request.getParameter("seq"));
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		map.put("REG_DATE",format.format(map.get("REG_DATE")));
		return map;
	}
}
