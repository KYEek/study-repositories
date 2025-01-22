package com.spring.app.board.service;

import java.util.List;
import java.util.Map;

import com.spring.app.board.domain.BoardVO;

public interface BoardService {

	//파일첨부가 없는 글쓰기
	int add(BoardVO boardvo);

	//페이징 처리를 안한 검색어가 없는 전체 글목록 보여주기
	List<BoardVO> boardListNoSearch();

	//글 조회수 증가와 함께 글 1개를 조회를 해 오는것
	BoardVO getView(Map<String, String> paraMap);

}
