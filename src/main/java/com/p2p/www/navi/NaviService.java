package com.p2p.www.navi;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NaviService {
	@Autowired
	NaviDao nd;

	public List<HashMap<String, Object>> naviList() {
		
		return nd.naviList();
	}

}
