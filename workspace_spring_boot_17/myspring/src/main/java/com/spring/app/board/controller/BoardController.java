package com.spring.app.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.app.board.domain.BoardVO;
import com.spring.app.board.domain.CommentVO;
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
		//  /WEB-INF/views/mycontent1/board/add.jsp 페이지를 만들어야 한다.
		
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
		
		int n = service.add(boardvo);  // <== 파일첨부가 없는 글쓰기 
		
		if(n==1) {
			mav.setViewName("redirect:/board/list");
			//   /board/list 페이지로 redirect(페이지이동)해라는 말이다.
		}
		else {
			mav.setViewName("mycontent1/board/error/add_error");
		    //  /WEB-INF/views/mycontent1/board/error/add_error.jsp 파일을 생성한다. 
		}
		
		return mav;
	}
	
		
	// === #32. 글목록 보기 페이지 요청 === //
	@GetMapping("list")
	public ModelAndView list(ModelAndView mav, HttpServletRequest request) {
		
		List<BoardVO> boardList = null;
		
		////////////////////////////////////////////////////////
		// === #44. 글조회수(readCount)증가 (DML문 update)는
		//          반드시 목록보기에 와서 해당 글제목을 클릭했을 경우에만 증가되고,
		//          웹브라우저에서 새로고침(F5)을 했을 경우에는 증가가 되지 않도록 해야 한다.
		//          이것을 하기 위해서는 session 을 사용하여 처리하면 된다.
		HttpSession session = request.getSession();
		session.setAttribute("readCountPermission", "yes");
		/*
			session 에  "readCountPermission" 키값으로 저장된 value값은 "yes" 이다.
			session 에  "readCountPermission" 키값에 해당하는 value값 "yes"를 얻으려면 
			반드시 웹브라우저에서 주소창에 "/board/list" 이라고 입력해야만 얻어올 수 있다. 
		*/
		////////////////////////////////////////////////////////
		
		// === 페이징 처리를 안한 검색어가 없는 전체 글목록 보여주기 === //
		boardList = service.boardListNoSearch();
		
		mav.addObject("boardList", boardList);
		
		mav.setViewName("mycontent1/board/list");
		//  /WEB-INF/views/mycontent1/board/list.jsp 파일을 생성한다.
		
		return mav;
	}
	
		
	// === #36. 글1개를 보여주는 페이지 요청 === //
	@GetMapping("view")
	public ModelAndView view(ModelAndView mav, 
			                 @RequestParam String seq,
			                 HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		MemberVO loginuser = (MemberVO) session.getAttribute("loginuser");
		
		String login_userid = null;
		if(loginuser != null) {
			login_userid = loginuser.getUserid();
			// login_userid 는 로그인 되어진 사용자의 userid 이다. 
		}
		
		try {
			Integer.parseInt(seq);
		
			Map<String, String> paraMap = new HashMap<>();
			paraMap.put("seq", seq);
			paraMap.put("login_userid", login_userid);
			
			
			// === #43. !!! 중요 !!! 
	        //     글1개를 보여주는 페이지 요청은 select 와 함께 
			//     DML문(지금은 글조회수 증가인 update문)이 포함되어져 있다.
			//     이럴경우 웹브라우저에서 페이지 새로고침(F5)을 했을때 DML문이 실행되어
			//     매번 글조회수 증가가 발생한다.
			//     그래서 우리는 웹브라우저에서 페이지 새로고침(F5)을 했을때는
			//     단순히 select만 해주고 DML문(지금은 글조회수 증가인 update문)은 
			//     실행하지 않도록 해주어야 한다. !!! === //
			
			// 위의 글목록보기 #44. 에서 session.setAttribute("readCountPermission", "yes"); 해두었다.
			BoardVO boardvo = null;
			
			if("yes".equals( (String)session.getAttribute("readCountPermission") )) {
			// 글목록보기인 /list 페이지를 클릭한 다음에 특정글을 조회해온 경우이다.
				
				boardvo = service.getView(paraMap);
				// 글 조회수 증가와 함께 글 1개를 조회를 해오는 것
			//	System.out.println("~~ 확인용 글내용 : " + boardvo.getContent());
				
				session.removeAttribute("readCountPermission");
				// 중요함!! session 에 저장된 readCountPermission 을 삭제한다. 
			}
			
			else {
				// 글목록에서 특정 글제목을 클릭하여 본 상태에서
			    // 웹브라우저에서 새로고침(F5)을 클릭한 경우이다.
			//	System.out.println("글목록에서 특정 글제목을 클릭하여 본 상태에서 웹브라우저에서 새로고침(F5)을 클릭한 경우"); 
				
				boardvo = service.getView_no_increase_readCount(paraMap);
				// 글 조회수 증가는 없고 단순히 글 1개만 조회를 해오는 것
				
				if(boardvo == null) {
					mav.setViewName("redirect:/board/list");
					return mav;
				}
			
				// 또는 redirect 해주기 
			/*	
				mav.setViewName("redirect:/board/list");
				return mav;
			*/	
			}
						
			mav.addObject("boardvo", boardvo);
			mav.setViewName("mycontent1/board/view");
			
			//  /WEB-INF/views/mycontent1/board/view.jsp 파일을 생성한다. 
			
		} catch (NumberFormatException e) {
			mav.setViewName("redirect:/board/list");
		}
		
		return mav;
	}
	
	
	// === #46. 글을 수정하는 페이지 요청 === //
	@GetMapping("edit/{seq}")
	public ModelAndView requiredLogin_edit(HttpServletRequest request
			                             , HttpServletResponse response
			                             , ModelAndView mav
			                             , @PathVariable String seq) {
		
		try {
			Long.parseLong(seq);
			
			// 글 수정해야 할 글 1개 내용가져오기
			Map<String, String> paraMap = new HashMap<>();
			paraMap.put("seq", seq);
			
			BoardVO boardvo = service.getView_no_increase_readCount(paraMap);
			// 글 조회수 증가는 없고 단순히 글 1개만 조회를 해오는 것
			
			if(boardvo == null) {
				mav.setViewName("redirect:/board/list");
			}
			
			else {
				HttpSession session = request.getSession();
				MemberVO loginuser = (MemberVO) session.getAttribute("loginuser");
				
				if( loginuser.getUserid().equals(boardvo.getFk_userid()) ) {
					// 자신의 글을 수정할 경우
					// 가져온 1개글을 글수정할 폼이 있는 view 단으로 보내준다.
					
					mav.addObject("boardvo", boardvo);
					mav.setViewName("mycontent1/board/edit");
				}
				else {
					// 자신의 글이 아닌 다른 사람의 글을 수정할 경우
					
					mav.addObject("message", "다른 사용자의 글은 수정이 불가합니다");
					mav.addObject("loc", "javascript:history.back()");
					
					mav.setViewName("msg");
				}
			}
			
			return mav;
			
		} catch (NumberFormatException e) {
			mav.setViewName("redirect:/board/list");
			return mav;
		}
		
	}
	
	
	// === #47. 글을 수정하는 페이지 완료하기 === //
	@PostMapping("edit")
	public ModelAndView edit(ModelAndView mav
                           , BoardVO boardvo
                           , HttpServletRequest request) {
		
		int n = service.edit(boardvo);
		
		if(n==1) {
			mav.addObject("message", "글 수정 성공!!");
			mav.addObject("loc", request.getContextPath()+"/board/view?seq="+boardvo.getSeq());
			mav.setViewName("msg");
		}
		
		return mav;
	}
	
	
	// === #51. 글을 삭제하는 페이지 요청 === //
	@GetMapping("del/{seq}")
	public ModelAndView requiredLogin_del(HttpServletRequest request
			                             , HttpServletResponse response
			                             , ModelAndView mav
			                             , @PathVariable String seq) {
		
		try {
			Long.parseLong(seq);
			
			// 삭제해야 할 글 1개 내용가져오기
			Map<String, String> paraMap = new HashMap<>();
			paraMap.put("seq", seq);
			
			BoardVO boardvo = service.getView_no_increase_readCount(paraMap);
			// 글 조회수 증가는 없고 단순히 글 1개만 조회를 해오는 것
			
			if(boardvo == null) {
				mav.setViewName("redirect:/board/list");
			}
			
			else {
				HttpSession session = request.getSession();
				MemberVO loginuser = (MemberVO) session.getAttribute("loginuser");
				
				if( loginuser.getUserid().equals(boardvo.getFk_userid()) ) {
					// 자신의 글을 삭제할 경우
					// 가져온 1개글을 글삭제할 폼이 있는 view 단으로 보내준다.
					
					mav.addObject("boardvo", boardvo);
					mav.setViewName("mycontent1/board/del");
				}
				else {
					// 자신의 글이 아닌 다른 사람의 글을 삭제할 경우
					
					mav.addObject("message", "다른 사용자의 글은 삭제가 불가합니다");
					mav.addObject("loc", "javascript:history.back()");
					
					mav.setViewName("msg");
				}
			}
			
			return mav;
			
		} catch (NumberFormatException e) {
			mav.setViewName("redirect:/board/list");
			return mav;
		}
		
	}	
	
	
	// === #52. 글을 삭제하는 페이지 완료하기 === //
	@PostMapping("del")
	public ModelAndView del(ModelAndView mav,
			                @RequestParam String seq,
			                HttpServletRequest request) {
		
		int n = service.del(seq);
		
		if(n==1) {
			mav.addObject("message", "글 삭제 성공!!");
		    mav.addObject("loc", request.getContextPath()+"/board/list");
		    mav.setViewName("msg");
		}
		
		return mav;
	}
	
	
	// === #59. 댓글쓰기(Ajax 로 처리) ===
	@PostMapping("addComment")
	@ResponseBody
	public Map<String, Object> addComment(CommentVO commentvo){
		//댓글쓰기의 첨부파일이 없는 경우
		int n = 0;
		try {
			n = service.addComment(commentvo);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		// 댓글쓰기(insert) 및 원게시물(tbl_board 테이블)에 댓글의 개수 증가(update 1씩 증가)하기 
        // 이어서 회원의 포인트를 50점을 증가하도록 한다. (tbl_member 테이블에 point 컬럼의 값을 50 증가하도록 update 한다.)
		Map<String, Object> map = new HashMap<>();
		map.put("name", commentvo.getName());
		map.put("n", n);
		//{"name":"연규영", "n":1}
		return map;
	}
	
	
	// ===#63. 원게시물에 딸린 댓글들을 조회해 오기(Ajax로 처리)===//
	@GetMapping("readComment")
	@ResponseBody
	public List<CommentVO> readComment(@RequestParam String parentSeq) {
		List<CommentVO> commentList = service.getCommentList(parentSeq);
		return commentList;
		// [{"seq":"3","fk_userid":"dltnstls89","name":"연규영", "contnet":"4번쨰글","regDate":"2025-01-24 11:09:05", "parentSeq":null, "status":null}]
	}
	
	// === #68. 댓글 수정(Ajax 로 처리) === //
	@PutMapping("updateComment")
	@ResponseBody
	public Map<String, Integer> updateComment(@RequestParam Map<String,String> paraMap){
		
		int n = service.updateComment(paraMap);
		
		Map<String,Integer> map = new HashMap<>();
		map.put("n", n);
		
		return map;
	}
	
	@DeleteMapping("deleteComment")
	@ResponseBody
	public Map<String, Integer> deleteComment(@RequestParam Map<String, String> paraMap) {
		System.out.println("실행");
		int n = 0;
		try {
			n = service.deleteComment(paraMap);
		}
		catch(Throwable e) {
			e.printStackTrace();
		}
		Map<String, Integer> map = new HashMap<>();
		map.put("n", n);

		return map;
	}
	
	
	
}
