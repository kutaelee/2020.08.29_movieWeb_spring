package com.p2p.www.navi;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class NaviDao {
	@Autowired
	private SqlSession sqlsession;
	
	public List<HashMap<String, Object>> naviList() {
		return sqlsession.selectList("navi.list");
	}

}
