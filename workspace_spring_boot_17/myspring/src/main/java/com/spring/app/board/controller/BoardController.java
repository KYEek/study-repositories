package com.spring.app.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.app.board.domain.BoardVO;
import com.spring.app.board.service.BoardService;
import com.spring.app.member.domain.MemberVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// ==== #22. 컨트롤러 선언 ====
@Controller
@RequestMapping(value="/board/*")
public class BoardController {

	@Autowired
	private BoardService service;
	
	
	// === #26. 게시판 글쓰기 폼페이지 요청 === //
	@GetMapping("add")
	public ModelAndView requiredLogin_add(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
		
		mav.setViewName("mycontent1/board/add");
		
		return mav;
	}
	
	
	// === #28. 게시판 글쓰기 완료 요청 === //
	@PostMapping("add")
	public ModelAndView add(ModelAndView mav, BoardVO boardvo) {
		/*
	       form 태그의 name 명과  BoardVO 의 필드명이 같다라면 
	       request.getParameter("form 태그의 name명"); 을 사용하지 않더라도
	           자동적으로 BoardVO boardvo 에 set 되어진다.
	   */
		
		int n = service.add(boardvo); // << 파일 첨부가 없는 글쓰기
		
		if(n==1) {
			mav.setViewName("redirect:/board/list");
			//	/board/list 페이지로 리다이렉트해라
		}
		else {
			mav.setViewName("mycontent1/board/error/add_error");
		//  /WEB-INF/views/mycontent1/board/error/add_error.jsp 파일을 생성한다.
		}
		
		return mav;
	}
	
	
	// === #32. 글목록 보기 페이지 요청 ===
	@GetMapping("list")
	public ModelAndView list(ModelAndView mav) {
		List<BoardVO> boardList = null;
		
		//=== 페이징 처리를 안한 검색어가 없는 전체 글목록 보여주기 ===//
		boardList = service.boardListNoSearch();
		mav.addObject("boardList", boardList);
		
		mav.setViewName("mycontent1/board/list");
	//  /WEB-INF/views/mycontent1/board/list.jsp 파일을 생성한다.
		
		return mav;
	}
	
	// === #36. 글1개를 보여주는 페이지 요청 ===///
	@GetMapping("view")
	public ModelAndView view(ModelAndView mav, @RequestParam String seq, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		MemberVO loginuser = (MemberVO)session.getAttribute("loginuser");
		System.out.println("실행됨");
		//테스트용
		String login_userid = null;
		if(loginuser != null) {
			login_userid = loginuser.getUserid();
			//login_userid는 로그인 되어진 사용자의 userid이다.
		}
		try {
			Integer.parseInt(seq);
			
			Map<String,String> paraMap = new HashMap<>();
			
			paraMap.put("seq", seq);
			paraMap.put("login_userid", login_userid);
			
			BoardVO boardvo = null;
			
			boardvo = service.getView(paraMap);
			
			mav.addObject("boardvo", boardvo);
			mav.setViewName("mycontent1/board/view");
		} catch(NumberFormatException e) {
			mav.setViewName("redirect:/board/list");
		}
		
				
		return mav;
	}
	
	
	
}
