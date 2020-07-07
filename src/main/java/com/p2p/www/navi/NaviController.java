package com.p2p.www.navi;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NaviController {
	@Autowired
	NaviService ns;
	
	@GetMapping("/naviList")
	public List<HashMap<String,Object>> naviList() {
		
		return ns.naviList();
	}
}
