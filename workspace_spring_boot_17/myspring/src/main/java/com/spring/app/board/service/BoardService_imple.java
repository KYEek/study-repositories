package com.spring.app.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.app.board.domain.BoardVO;
import com.spring.app.board.model.BoardDAO;

// ==== #23. 서비스 선언 ====
@Service
public class BoardService_imple implements BoardService {

	@Autowired
	private BoardDAO dao;

	@Override
	public int add(BoardVO boardvo) {
		
		int n = dao.add(boardvo);
		return n;
	}

	//#33. 페이징 처리를 안한 검색어가 없는 전체 글목록 보여주기
	@Override
	public List<BoardVO> boardListNoSearch() {
		
		List<BoardVO> boardList = dao.boardListNoSearch();
		return boardList;
	}

	//#37. 글 조회수 증가와 함께 글 1개를 조회를 해 오는것
	@Override
	public BoardVO getView(Map<String, String> paraMap) {
		
		BoardVO boardvo = dao.getView(paraMap);	//글 1개 조회하기
		
		String login_userid = paraMap.get("login_userid");
		// paraMap.get("login_userid") 은 로그인을 한 상태이라면 로그인한 사용자의 userid 이고,
        // 로그인을 하지 않은 상태이라면  paraMap.get("login_userid") 은 null 이다.
		
		if(login_userid != null && boardvo != null && !login_userid.equals(boardvo.getFk_userid())) {
			// 글조회수 증가는 로그인을 한 상태에서 다른 사람의 글을 읽을때만 증가하도록 한다.
			
			int n = dao.increase_readCount(paraMap.get("seq")); //글조회수 1증가하기
			if(n==1) {
				boardvo.setReadCount(String.valueOf(Integer.parseInt(boardvo.getReadCount()) + 1));
			}
		}
		
		return boardvo;
	}
	
}
