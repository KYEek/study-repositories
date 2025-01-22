package com.spring.app.board.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.spring.app.board.domain.BoardVO;

// ==== #24. Repository(DAO) 선언 ====
@Repository
public class BoardDAO_imple implements BoardDAO {

	@Autowired
	@Qualifier("sqlsession")
	private SqlSessionTemplate sqlsession;

	@Override
	public int add(BoardVO boardvo) {
		int n = sqlsession.insert("board.add",boardvo);
		return n;
	}

	@Override
	public List<BoardVO> boardListNoSearch() {
		List<BoardVO> boardList = sqlsession.selectList("board.boardListNoSearch");
		return boardList;
	}

	// #38. 글 조회수 증가와 함께 글 1개를 조회를 해 오는것
	@Override
	public BoardVO getView(Map<String, String> paraMap) {
		BoardVO boardvo = sqlsession.selectOne("board.getView", paraMap);
		return boardvo;
	}
	
	// #40. 글 조회수 1증가하기
	@Override
	public int increase_readCount(String seq) {
		int n = sqlsession.update("board.increase_readCount", seq);
		return n;
	}
	
	
}
