package com.spring.app.board.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.spring.app.board.domain.BoardVO;
import com.spring.app.board.domain.CommentVO;

// ==== #24. Repository(DAO) 선언 ====
@Repository
public class BoardDAO_imple implements BoardDAO {

	@Autowired
	@Qualifier("sqlsession")
	private SqlSessionTemplate sqlsession;

	
	// === #30. 파일첨부가 없는 글쓰기 ===
	@Override
	public int add(BoardVO boardvo) {
		int n = sqlsession.insert("board.add", boardvo);
		return n;
	}


	// === #34. 페이징 처리를 안한 검색어가 없는 전체 글목록 보여주기 ===
	@Override
	public List<BoardVO> boardListNoSearch() {
		List<BoardVO> boardList = sqlsession.selectList("board.boardListNoSearch");
		return boardList;
	}


	// === #38. 글 1개 조회하기 === 
	@Override
	public BoardVO getView(Map<String, String> paraMap) {
		BoardVO boardvo = sqlsession.selectOne("board.getView", paraMap);
		return boardvo;
	}

	// === #40. 글조회수 1증가 하기 ===
	@Override
	public int increase_readCount(String seq) {
		int n = sqlsession.update("board.increase_readCount", seq);
		return n;
	}
	
	// === #49. 1개글 수정하기 === //
	@Override
	public int edit(BoardVO boardvo) {
		int n = sqlsession.update("board.edit", boardvo);
		return n;
	}


	// === #54. 1개글 삭제하기 === //
	@Override
	public int del(String seq) {
		int n = sqlsession.delete("board.del", seq);
		return n;
	}

	// === #61.1 댓글쓰기(tbl_comment 테이블에 insert) === //
	@Override
	public int addComment(CommentVO commentvo) {
		int n = sqlsession.insert("board.addComment", commentvo);
		return n;
	}

	// === #61.2 tbl_board 테이블에 commentCount 컬럼이 1증가(update) === //
	@Override
	public int updateCommentCount(String parentSeq) {
		int n = sqlsession.insert("board.updateCommentCount", parentSeq);
		return n;
		
	}

	// === #61.3 tbl_member 테이블의 point 컬럼의 값을 50점을 증가(update) === //
	@Override
	public int updateMemberPoint(Map<String,String> paraMap) {
		int n = sqlsession.insert("board.updateMemberPoint", paraMap);
		return n;
	}


	//=== #65.원 게시물에 딸린 댓글들을 조횧해오기
	@Override
	public List<CommentVO> getCommentList(String parentSeq) {
		List<CommentVO> commentList = sqlsession.selectList("board.getCommentList", parentSeq);
		return commentList;
	}


	// 댓글 수정(Ajax로 처리)
	@Override
	public int updateComment(String seq) {
		int n = sqlsession.update("board.updateComment", seq);
		return n;
	}

	// #74.1 댓글 삭제(Ajax로 처리)
	@Override
	public int deleteComment(Map<String, String> paraMap) {
		int n = sqlsession.delete("board.deleteComment", paraMap);
		return n;
	}

	// #74.2 댓글삭제시 tbl_board 테이블에 commentCount 컬럼이 1감소(update)
	@Override
	public int updateCommentCount_decrease(String parentSeq) {
		int n = sqlsession.delete("board.updateCommentCount_decrease", parentSeq);
		return n;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
