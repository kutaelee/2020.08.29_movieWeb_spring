package com.p2p.www.board;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class BoardDao {
	@Autowired
	private SqlSession sqlsession;
	
	public List<HashMap<String, Object>> boardList(HashMap<String,Object> params) {
		return sqlsession.selectList("board.boardList",params);
	}

	public int getBoardCount(HashMap<String,Object> params) {
		return sqlsession.selectOne("board.getBoardCount",params);
	}

}
