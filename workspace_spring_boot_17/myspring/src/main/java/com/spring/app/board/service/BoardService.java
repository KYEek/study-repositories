package com.spring.app.board.service;

import java.util.List;
import java.util.Map;

import com.spring.app.board.domain.BoardVO;
import com.spring.app.board.domain.CommentVO;

public interface BoardService {

	// 파일첨부가 없는 글쓰기
	int add(BoardVO boardvo);

	// 페이징 처리를 안한 검색어가 없는 전체 글목록 보여주기
	List<BoardVO> boardListNoSearch();

	// 글 조회수 증가와 함께 글 1개를 조회를 해오는 것
	BoardVO getView(Map<String, String> paraMap);

	// 글 조회수 증가는 없고 단순히 글 1개만 조회를 해오는 것
	BoardVO getView_no_increase_readCount(Map<String, String> paraMap);

	// 1개글 수정하기
	int edit(BoardVO boardvo);

	// 1개글 삭제하기
	int del(String seq);

	// 댓글 쓰기(Transaction 처리)
	int addComment(CommentVO commentvo);

	//원 게시물에 딸린 댓글들을 조횧해오기
	List<CommentVO> getCommentList(String parentSeq);
	
	// 댓글 수정(Ajax로 처리)
	int updateComment(Map<String, String> paraMap);
	
	// 댓글 삭제(Ajax로 처리)
	int deleteComment(Map<String, String> paraMap);
	
	
	
	
	
	
	
	
	

}
