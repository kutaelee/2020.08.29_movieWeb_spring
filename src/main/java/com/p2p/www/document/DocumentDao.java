package com.p2p.www.document;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class DocumentDao {
	@Autowired
	private SqlSession sqlsession;
	
	public HashMap<String, Object> getDocument(String seq) {
		return sqlsession.selectOne("document.getDocument",seq);
	}

}
