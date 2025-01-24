package com.spring.app.board.model;

import java.util.List;
import java.util.Map;

import com.spring.app.board.domain.BoardVO;
import com.spring.app.board.domain.CommentVO;

public interface BoardDAO {

	// 파일첨부가 없는 글쓰기
	int add(BoardVO boardvo);

	// 페이징 처리를 안한 검색어가 없는 전체 글목록 보여주기 
	List<BoardVO> boardListNoSearch();

	// 글 1개 조회하기
	BoardVO getView(Map<String, String> paraMap);

	// 글조회수 1증가 하기
	int increase_readCount(String seq);

	// 글 1개 수정하기
	int edit(BoardVO boardvo);

	// 글 1개 삭제하기
	int del(String seq);

	////////////////////////////////////////////
	// 댓글쓰기(tbl_comment 테이블에 insert)
	int addComment(CommentVO commentvo);
	
	// tbl_board 테이블에 commentCount 컬럼이 1증가(update)
	int updateCommentCount(String parentSeq);
	
	// tbl_member 테이블의 point 컬럼의 값을 50점을 증가(update)
	int updateMemberPoint(Map<String,String> paraMap);

	//원 게시물에 딸린 댓글들을 조횧해오기
	List<CommentVO> getCommentList(String parentSeq);

	// 댓글 수정(Ajax로 처리)
	int updateComment(String seq);
	
	// 댓글 삭제(Ajax로 처리)
	int deleteComment(Map<String, String> paraMap);
	
	// 댓글삭제시 tbl_board 테이블에 commentCount 컬럼이 1감소(update)
	int updateCommentCount_decrease(String parentSeq);
	
	/////////////////////////////////////////////
	
	
	
	
	
	
	
	
	
	
	

}
