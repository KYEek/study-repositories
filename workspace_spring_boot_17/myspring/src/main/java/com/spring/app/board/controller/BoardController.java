package com.spring.app.board.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.spring.app.board.domain.BoardVO;
import com.spring.app.board.domain.CommentVO;
import com.spring.app.board.service.BoardService;
import com.spring.app.common.MyUtil;
import com.spring.app.member.domain.MemberVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// ==== #22. 컨트롤러 선언 ====
@Controller
@RequestMapping(value="/board/*")
public class BoardController {

	@Autowired  // Type 에 따라 알아서 Bean 을 주입해준다.
	private BoardService service;
	
	
	// === #26. 게시판 글쓰기 폼페이지 요청 === //
	@GetMapping("add")
	public ModelAndView requiredLogin_add(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {  // <== Before Advice 를 사용하기         
 		mav.setViewName("mycontent1/board/add");
		//  /WEB-INF/views/mycontent1/board/add.jsp 페이지를 만들어야 한다.
		
		return mav;
	}
	
	
	// === #28. 게시판 글쓰기 완료 요청 === //
	@PostMapping("add")
//	public ModelAndView add(ModelAndView mav, BoardVO boardvo) {   // <== After Advice 를 사용하기 전 
	public ModelAndView pointPlus_add(Map<String, String> paraMap, ModelAndView mav, BoardVO boardvo) {   // <== After Advice 를 사용하기 	
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
		
		// === #77. After Advice 를 사용하기 ===
		//          글쓰기를 한 이후에는 회원의 포인트를 100점 증가
		paraMap.put("userid", boardvo.getFk_userid());
		paraMap.put("point", "100");
		
		return mav;
	}
	
		
	// === #32. 글목록 보기 페이지 요청 === //
	@GetMapping("list")
//	public ModelAndView list(ModelAndView mav, HttpServletRequest request) {
	public ModelAndView list(ModelAndView mav, HttpServletRequest request,
			                 @RequestParam(defaultValue = "") String searchType,
			                 @RequestParam(defaultValue = "") String searchWord,
			                 @RequestParam(defaultValue = "1") String currentShowPageNo) {  // <== 페이징 처리 
		
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
	//	boardList = service.boardListNoSearch();
		
		// === #83. 페이징 처리를 안한 검색어가 있는 전체 글목록 보여주기 === //
	/*
		String searchType = request.getParameter("searchType");
		String searchWord = request.getParameter("searchWord");
	*/	
	//	System.out.println("~~~ 확인용 searchType : " + searchType);
		/*
		   ~~~ 확인용 searchType : null
           ~~~ 확인용 searchType : subject
           ~~~ 확인용 searchType : content
           ~~~ 확인용 searchType : subject_content
           ~~~ 확인용 searchType : name
		*/
		
	//	System.out.println("~~~ 확인용 searchWord : " + searchWord);
		/*
		   ~~~ 확인용 searchWord : null
		   ~~~ 확인용 searchWord : 아침 
		   ~~~ 확인용 searchWord : 점심
		   ~~~ 확인용 searchWord : 저녁
		   ~~~ 확인용 searchWord : 엄정화
		   ~~~ 확인용 searchWord : 
		   ~~~ 확인용 searchWord :    
		   ~~~ 확인용 searchWord : "null"
		*/
	/*	
		if(searchType == null) {
			searchType = "";
		}
		
		if(searchWord == null) {
			searchWord = "";
		}
		
		if(searchWord != null) {
			searchWord = searchWord.trim();
			// "     아침   "    ==> "아침"
			// "     아침 식사  " ==> "아침 식사"
			// "           "    ==> ""
			
		}
		
		Map<String, String> paraMap = new HashMap<>();
		paraMap.put("searchType", searchType);
		paraMap.put("searchWord", searchWord);
		
		boardList = service.boardListSearch(paraMap);
	*/
		
		////////////////////////////////////////////////////////////
		
		// === #95. 페이징 처리를 한 검색어가 있는 전체 글목록 보여주기 === //
		
	    /*  페이징 처리를 통한 글목록 보여주기는 
	     
            예를 들어 3페이지의 내용을 보고자 한다라면 
            검색을 할 경우는 아래와 같이
            list?searchType=subject&searchWord=안녕&currentShowPageNo=3 와 같이 해주어야 한다. 
 
            또는
 
            검색이 없는 전체를 볼때는 아래와 같이 한다
            list
            
            또는 
            
            검색이 없는 3페이지만 볼때는 아래와 같이 한다.
            
            list?searchType=&searchWord=&currentShowPageNo=3 또는 
            list?searchType=subject&searchWord=&currentShowPageNo=3 또는
            list?searchType=name&searchWord=&currentShowPageNo=3 와 같이 해주어야 한다.
    */
		
	//	System.out.println("~~~ 확인용 currentShowPageNo : " + currentShowPageNo); 
		/*
		  ~~~ 확인용 currentShowPageNo : 1
		  ~~~ 확인용 str_currentShowPageNo : 3
		  ~~~ 확인용 str_currentShowPageNo : dsfdsfsdfㄴㄹㅇㄹㅇㄴㄹ
		  ~~~ 확인용 str_currentShowPageNo : -3412
		  ~~~ 확인용 str_currentShowPageNo : 0
		  ~~~ 확인용 str_currentShowPageNo : 32432
		  ~~~ 확인용 str_currentShowPageNo : 64532432432436534252345
		*/
		
		searchWord = searchWord.trim();
		// "     아침   "    ==> "아침"
		// "     아침 식사  " ==> "아침 식사"
		// "           "    ==> ""
		
		Map<String, String> paraMap = new HashMap<>();
		paraMap.put("searchType", searchType);
		paraMap.put("searchWord", searchWord);
		
		// 먼저, 총 게시물 건수(totalCount)를 구해와야 한다.
		// 총 게시물 건수(totalCount)는 검색조건이 있을 때와 검색조건이 없을때로 나뉘어진다.
		int totalCount = 0;          // 총 게시물 건수
		int sizePerPage = 10;        // 한 페이지당 보여줄 게시물 건수
		int totalPage = 0;           // 총 페이지수(웹브라우저상에서 보여줄 총 페이지 개수, 페이지바)
		
		int n_currentShowPageNo = 0; 
		
		// 총 게시물 건수 (totalCount)
		totalCount = service.getTotalCount(paraMap);
	//	System.out.println("~~~ 확인용 totalCount : " + totalCount);
		/*
		   ~~~ 확인용 totalCount : 211 
		   ~~~ 확인용 totalCount : 202
		   ~~~ 확인용 totalCount : 1
		   ~~~ 확인용 totalCount : 0
		*/
		
		// 만약에 총 게시물 건수(totalCount)가 124 개 이라면 총 페이지수(totalPage)는 13 페이지가 되어야 한다.
		// 만약에 총 게시물 건수(totalCount)가 120 개 이라면 총 페이지수(totalPage)는 12 페이지가 되어야 한다. 
		totalPage = (int) Math.ceil((double)totalCount/sizePerPage);
		//  (double)124/10 ==> 12.4 ==> Math.ceil(12.4) ==> 13.0 ==> 13 
	    //  (double)120/10 ==> 12.0 ==> Math.ceil(12.0) ==> 12.0 ==> 12 
		
		try {
			  n_currentShowPageNo = Integer.parseInt(currentShowPageNo);
		
			  if(n_currentShowPageNo < 1 || n_currentShowPageNo > totalPage) {
				// get 방식이므로 사용자가 currentShowPageNo 에 입력한 값이 0 또는 음수를 입력하여 장난친 경우 
				// get 방식이므로 사용자가 currentShowPageNo 에 입력한 값이 실제 데이터베이스에 존재하는 페이지수 보다 더 큰값을 입력하여 장난친 경우 
				 n_currentShowPageNo = 1;
			  }
			  
		} catch(NumberFormatException e) {
			// get 방식이므로 currentShowPageNo 에 입력한 값이 숫자가 아닌 문자를 입력하거나
			// int 범위를 초과한 경우
			n_currentShowPageNo = 1;
		}
		
		
		// **** 가져올 게시글의 범위를 구한다.(공식임!!!) **** 
		/*
		     currentShowPageNo      startRno     endRno
		    --------------------------------------------
		         1 page        ===>    1           10
		         2 page        ===>    11          20
		         3 page        ===>    21          30
		         4 page        ===>    31          40
		         ......                ...         ...
		 */
		 
		 int startRno = ((n_currentShowPageNo - 1) * sizePerPage) + 1; // 시작 행번호
		 int endRno = startRno + sizePerPage - 1; // 끝 행번호 
		 
		 paraMap.put("startRno", String.valueOf(startRno)); // Oracle 11g 와 호환되는 것으로 사용하기 위함 
		 paraMap.put("endRno", String.valueOf(endRno));     // Oracle 11g 와 호환되는 것으로 사용하기 위함
		 
		 paraMap.put("currentShowPageNo", currentShowPageNo); // Oracle 12c 이상으로 사용하기 위함
		 
		 boardList = service.boardListSearch_withPaging(paraMap);
		 // 글목록 가져오기(페이징 처리 했으며, 검색어가 있는 것 또는 검색어가 없는 것 모두 포함한 것이다.)
		
		 mav.addObject("boardList", boardList);
		
		 
		 // 검색시 검색조건 및 검색어 유지시키기 
		 if("subject".equals(searchType) || 
		    "content".equals(searchType) ||
		    "subject_content".equals(searchType) ||
		    "name".equals(searchType)) { 
		
			// === #107. 이전글제목, 다음글제목 보여줄때 검색이 있는지 여부를 넘겨주기 시작 === //
			 paraMap.put("searchType", searchType);
			 paraMap.put("searchWord", searchWord);
			// === 이전글제목, 다음글제목 보여줄때 검색이 있는지 여부를 넘겨주기 끝 === //
			 
			 mav.addObject("paraMap", paraMap);	
		 }
		
		 
		 // === #102. 페이지바 만들기 === //
		 int blockSize = 10;
		 // blockSize 는 1개 블럭(토막)당 보여지는 페이지번호의 개수이다.
		 /*
			             1  2  3  4  5  6  7  8  9 10 [다음][마지막]  -- 1개블럭
			[맨처음][이전]  11 12 13 14 15 16 17 18 19 20 [다음][마지막]  -- 1개블럭
			[맨처음][이전]  21 22 23
		 */
		 
		 int loop = 1;
		 /*
	    	loop는 1부터 증가하여 1개 블럭을 이루는 페이지번호의 개수[ 지금은 10개(== blockSize) ] 까지만 증가하는 용도이다.
	     */
		 
		 int pageNo = ((n_currentShowPageNo - 1)/blockSize) * blockSize + 1;
			// *** !! 공식이다. !! *** //
			
		/*
		    1  2  3  4  5  6  7  8  9  10  -- 첫번째 블럭의 페이지번호 시작값(pageNo)은 1 이다.
		    11 12 13 14 15 16 17 18 19 20  -- 두번째 블럭의 페이지번호 시작값(pageNo)은 11 이다.
		    21 22 23 24 25 26 27 28 29 30  -- 세번째 블럭의 페이지번호 시작값(pageNo)은 21 이다.
		    
		    currentShowPageNo         pageNo
		   ----------------------------------
		         1                      1 = ((1 - 1)/10) * 10 + 1
		         2                      1 = ((2 - 1)/10) * 10 + 1
		         3                      1 = ((3 - 1)/10) * 10 + 1
		         4                      1
		         5                      1
		         6                      1
		         7                      1 
		         8                      1
		         9                      1
		         10                     1 = ((10 - 1)/10) * 10 + 1
		        
		         11                    11 = ((11 - 1)/10) * 10 + 1
		         12                    11 = ((12 - 1)/10) * 10 + 1
		         13                    11 = ((13 - 1)/10) * 10 + 1
		         14                    11
		         15                    11
		         16                    11
		         17                    11
		         18                    11 
		         19                    11 
		         20                    11 = ((20 - 1)/10) * 10 + 1
		         
		         21                    21 = ((21 - 1)/10) * 10 + 1
		         22                    21 = ((22 - 1)/10) * 10 + 1
		         23                    21 = ((23 - 1)/10) * 10 + 1
		         ..                    ..
		         29                    21
		         30                    21 = ((30 - 1)/10) * 10 + 1
		*/
		 
		String pageBar = "<ul style='list-style:none;'>";
		String url = "list";
		
		// === [맨처음][이전] 만들기 === //
		pageBar += "<li style='display:inline-block; width:70px; font-size:12pt;'><a href='"+url+"?searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo=1'>[맨처음]</a></li>";
		
		if(pageNo != 1) {
			pageBar += "<li style='display:inline-block; width:50px; font-size:12pt;'><a href='"+url+"?searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+(pageNo-1)+"'>[이전]</a></li>"; 
		}
		
		
		while( !(loop > blockSize || pageNo > totalPage) ) {
			
			if(pageNo == Integer.parseInt(currentShowPageNo)) {
				pageBar += "<li style='display:inline-block; width:30px; font-size:12pt; border:solid 1px gray; color:red; padding:2px 4px;'>"+pageNo+"</li>"; 
			}
			else {
				pageBar += "<li style='display:inline-block; width:30px; font-size:12pt;'><a href='"+url+"?searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+pageNo+"'>"+pageNo+"</a></li>"; 
			}
			
			loop++;
			pageNo++;
		}// end of while-------------------------------
		
		
		// === [다음][마지막] 만들기 === //
		if(pageNo <= totalPage) {
			pageBar += "<li style='display:inline-block; width:50px; font-size:12pt;'><a href='"+url+"?searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+pageNo+"'>[다음]</a></li>"; 	
		}
		
		pageBar += "<li style='display:inline-block; width:70px; font-size:12pt;'><a href='"+url+"?searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+totalPage+"'>[마지막]</a></li>";
					
		pageBar += "</ul>";	
		
		mav.addObject("pageBar", pageBar);
		 
		
		///////////////////////////////////////////////////////////

		mav.addObject("totalCount", totalCount);   // 페이징 처리시 보여주는 순번을 나타내기 위한 것임.
		mav.addObject("currentShowPageNo", currentShowPageNo); // 페이징 처리시 보여주는 순번을 나타내기 위한 것임.
		mav.addObject("sizePerPage", sizePerPage); // 페이징 처리시 보여주는 순번을 나타내기 위한 것임.
		
        ///////////////////////////////////////////////////////////
        
		// === #104. 페이징 처리되어진 후 특정 글제목을 클릭하여 상세내용을 본 이후
		//           사용자가 "검색된결과목록보기" 버튼을 클릭했을때 돌아갈 페이지를 알려주기 위해
		//           현재 페이지 주소를 뷰단으로 넘겨준다.
		String currentURL = MyUtil.getCurrentURL(request);
	//	System.out.println("~~~ 확인용 currentURL : " + currentURL);
		/*
            ~~~ 확인용 currentURL : /board/list		
            ~~~ 확인용 currentURL : /board/list?searchType=&searchWord=&currentShowPageNo=5
            ~~~ 확인용 currentURL : /board/list?searchType=subject&searchWord=java
            ~~~ 확인용 currentURL : /board/list?searchType=subject&searchWord=입니다&currentShowPageNo=7
        */

		mav.addObject("goBackURL", currentURL);
		
		mav.setViewName("mycontent1/board/list");
		//  /WEB-INF/views/mycontent1/board/list.jsp 파일을 생성한다.
		
		return mav;
	}
	
		
	// === #36. 글1개를 보여주는 페이지 요청 === //
//	@GetMapping("view")
//	@PostMapping("view")
	@RequestMapping("view")  // === #106. 특정글을 조회한 후 "검색된결과목록보기" 버튼을 클릭했을 때 돌아갈 페이지를 만들기 위함. === 
	public ModelAndView view(ModelAndView mav, 
			                 HttpServletRequest request) {

		String seq = "";
		String goBackURL = "";
		String searchType = "";
		String searchWord = "";
		
		
		// === #115. view_2 에서 redirect 해온것을 처리해주기 시작 === //
		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
		// redirect 되어서 넘어온 데이터가 있는지 꺼내어 와본다.
		
		if(inputFlashMap != null) { // redirect 되어서 넘어온 데이터가 있다라면 
			
			@SuppressWarnings("unchecked") // 경고 표시를 하지 말라는 뜻이다. 
			Map<String, String> redirect_map = (Map<String, String>) inputFlashMap.get("redirect_map");
			// "redirect_map" 값은  /view_2 에서  redirectAttr.addFlashAttribute("키", 밸류값); 을 할때 준 "키" 이다. 
			// "키" 값을 주어서 redirect 되어서 넘어온 데이터를 꺼내어 온다. 
			// "키" 값을 주어서 redirect 되어서 넘어온 데이터의 값은 Map<String, String> 이므로 Map<String, String> 으로 casting 해준다. 
			
		//	System.out.println("~~~ 확인용 seq : " + redirect_map.get("seq"));
			// ~~~ 확인용 seq : 70
			// ~~~ 확인용 seq : 
			
			seq = redirect_map.get("seq");
			searchType = redirect_map.get("searchType");
						
			try {
				searchWord = URLDecoder.decode(redirect_map.get("searchWord"), "UTF-8"); // 한글데이터가 포함되어 있으면 반드시 한글로 복구해 주어야 한다.
				goBackURL = URLDecoder.decode(redirect_map.get("goBackURL"), "UTF-8"); // 한글데이터가 포함되어 있으면 반드시 한글로 복구해 주어야 한다. 
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}  
			
		//	System.out.println("~~~ 확인용 seq : " + seq);
		//	System.out.println("~~~ 확인용 searchType : " + searchType);
		//	System.out.println("~~~ 확인용 searchWord : " + searchWord);
		//	System.out.println("~~~ 확인용 goBackURL : " + goBackURL);
			/*
				~~~ 확인용 seq : 70
				~~~ 확인용 searchType : name
				~~~ 확인용 searchWord : 서영학
				~~~ 확인용 goBackURL : /board/list?searchType=name&searchWord=%EC%84%9C%EC%98%81%ED%95%99&currentShowPageNo=5
			*/
			// === view_2 에서 redirect 해온것을 처리해주기 끝 === //
		}
		
		else { // redirect 되어서 넘어온 데이터가 아닌 경우  
			
			seq = request.getParameter("seq");
			goBackURL = request.getParameter("goBackURL");
			searchType = request.getParameter("searchType");
			searchWord = request.getParameter("searchWord");
			
			if(searchType == null) {
				searchType = "";
			}
			
			if(searchWord == null) {
				searchWord = "";
			}
			
		}// end of if ~ else--------------------------
		
		
		HttpSession session = request.getSession();
		MemberVO loginuser = (MemberVO) session.getAttribute("loginuser");
		
		String login_userid = null;
		if(loginuser != null) {
			login_userid = loginuser.getUserid();
			// login_userid 는 로그인 되어진 사용자의 userid 이다. 
		}
		
		try {
			Integer.parseInt(seq);
		
		//	System.out.println("~~~ 확인용 goBackURL : " + goBackURL);
			// GET 방식일 때  ~~~ 확인용 goBackURL : /board/list?searchType=subject
			// POST 방식일 때 ~~~ 확인용 goBackURL : /board/list?searchType=subject&searchWord=%EC%9E%85%EB%8B%88%EB%8B%A4&currentShowPageNo=7 
			mav.addObject("goBackURL", goBackURL);
			
			
			Map<String, String> paraMap = new HashMap<>();
			paraMap.put("seq", seq);
			paraMap.put("login_userid", login_userid);
			
			// === #108. 이전글제목, 다음글제목 보여줄때 검색이 있는지 여부를 넘겨주기 시작 === //
			 paraMap.put("searchType", searchType);
			 paraMap.put("searchWord", searchWord);
			// === 이전글제목, 다음글제목 보여줄때 검색이 있는지 여부를 넘겨주기 끝 === //
			
			
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
			
			// === #112. 이전글제목 보기, 다음글제목 보기시 POST 방식으로 넘기기 위한것 === 
			mav.addObject("paraMap", paraMap);
			
			mav.setViewName("mycontent1/board/view");
			//  /WEB-INF/views/mycontent1/board/view.jsp 파일을 생성한다. 
			
		} catch (NumberFormatException e) {
			mav.setViewName("redirect:/board/list");
		}
		
		return mav;
	}
	
	
//	=== #114. 이전글제목보기, 다음글제목보기를 할때 글조회수 증가를 하기 위한 것이다.
	@PostMapping("view_2")
	public ModelAndView view_2(ModelAndView mav, 
					           @RequestParam(defaultValue = "") String seq,
					           @RequestParam(defaultValue = "") String goBackURL,
					           @RequestParam(defaultValue = "") String searchType,
					           @RequestParam(defaultValue = "") String searchWord,
					           HttpServletRequest request,
					           RedirectAttributes redirectArr) {
		
		try {
			searchWord = URLEncoder.encode(searchWord, "UTF-8");
			goBackURL = URLEncoder.encode(goBackURL, "UTF-8");
			
		//	System.out.println("~~~~ view_2 의 searchWord : " + searchWord);
			// ~~~~ view_2 의 searchWord : %EC%84%9C%EC%98%81%ED%95%99
			
		//	System.out.println("~~~~ view_2 의 searchWord : " + URLDecoder.decode(searchWord, "UTF-8")); // URL인코딩 되어진 한글을 원래 한글모양으로 되돌려주는 것임. 
			// ~~~~ view_2 의 searchWord : 서영학
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		
		HttpSession session = request.getSession();
		session.setAttribute("readCountPermission", "yes");

		// ==== redirect(GET방식임) 시 데이터를 넘길때 GET 방식이 아닌 POST 방식처럼 데이터를 넘기려면 RedirectAttributes 를 사용하면 된다. 시작 ==== // 
		Map<String, String> redirect_map = new HashMap<>();
		redirect_map.put("seq", seq);
		redirect_map.put("goBackURL", goBackURL);
		redirect_map.put("searchType", searchType);
		redirect_map.put("searchWord", searchWord);
		
		redirectArr.addFlashAttribute("redirect_map", redirect_map);
		// redirectAttr.addFlashAttribute("키", 밸류값); 으로 사용하는데 오로지 1개의 데이터만 담을 수 있으므로 여러개의 데이터를 담으려면 Map 을 사용해야 한다. 
		
		mav.setViewName("redirect:/board/view"); // 실제로 redirect:/board/view 은 POST 방식이 아닌 GET 방식이다.
		// ==== redirect(GET방식임) 시 데이터를 넘길때 GET 방식이 아닌 POST 방식처럼 데이터를 넘기려면 RedirectAttributes 를 사용하면 된다. 끝 ==== //
		
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
	
	
	// === #59. 댓글쓰기(Ajax 로 처리) === //
	@PostMapping("addComment")
	@ResponseBody
	public Map<String, Object> addComment(CommentVO commentvo) {  
		// 댓글쓰기에 첨부파일이 없는 경우
		
		int n = 0;
		
		try {
		     n = service.addComment(commentvo);
		    // 댓글쓰기(insert) 및 원게시물(tbl_board 테이블)에 댓글의 개수 증가(update 1씩 증가)하기 
		    // 이어서 회원의 포인트를 50점을 증가하도록 한다. (tbl_member 테이블에 point 컬럼의 값을 50 증가하도록 update 한다.)
		} catch(Throwable e) {
			e.printStackTrace();
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("name", commentvo.getName());
		map.put("n", n);
		
		return map;	
		// {"name":"서영학","n":1}
		// 또는
		// {"name":"서영학","n":0}
	}
	
	
	// === #63. 원게시물에 딸린 댓글들을 조회해오기 (Ajax 로 처리) === //
	@GetMapping("readComment")
	@ResponseBody
	public List<CommentVO> readComment(@RequestParam String parentSeq){
		
		List<CommentVO> commentList = service.getCommentList(parentSeq);
		
		return commentList;
		/*
		  [{"seq":"3","fk_userid":"seoyh","name":"서영학","content":"세번째 댓글쓰기 입니다","regDate":"2025-01-24 10:50:40","parentSeq":null,"status":null}
		  ,{"seq":"2","fk_userid":"seoyh","name":"서영학","content":"두번째 댓글쓰기 입니다","regDate":"2025-01-24 10:48:50","parentSeq":null,"status":null}
		  ,{"seq":"1","fk_userid":"seoyh","name":"서영학","content":"첫번째 댓글쓰기 입니다","regDate":"2025-01-24 10:46:23","parentSeq":null,"status":null}] 
		  
		  또는
		  
		  [] 
		*/
	}
	
	
	// === #68. 댓글 수정(Ajax 로 처리) === //
	@PutMapping("updateComment")
	@ResponseBody
	public Map<String, Integer> updateComment(@RequestParam Map<String, String> paraMap){
		
		int n = service.updateComment(paraMap);
		
		Map<String, Integer> map = new HashMap<>();
		map.put("n", n);
		
		return map;  // {"n":1}
	}
	
	
	// === #72. 댓글 삭제(Ajax 로 처리) === //
	@DeleteMapping("deleteComment")
	@ResponseBody
    public Map<String, Integer> deleteComment(@RequestParam Map<String, String> paraMap){
		
		int n=0;
				
		try {
		  n=service.deleteComment(paraMap);
		} catch(Throwable e) {
			e.printStackTrace();
		}
		
		Map<String, Integer> map = new HashMap<>();
		map.put("n", n);
		
		return map;  // {"n":1}
	}
	
	
	// === #89. 검색어 입력시 자동글 완성하기 3 === //
	@GetMapping("wordSearchShow")
	@ResponseBody
	public List<Map<String, String>> wordSearchShow(@RequestParam Map<String, String> paraMap) {
		
		List<String> wordList = service.wordSearchShow(paraMap); 
		
		List<Map<String, String>> mapList = new ArrayList<>();
		
		if(wordList != null) {
			for(String word : wordList) {
				Map<String, String> map = new HashMap<>();
				map.put("word", word);
				mapList.add(map);
			}// end of for-------------
		}
		
		return mapList;
	  /*
		 [{"word":"java가 쉽나요?"}
		 ,{"word":"JAVA공부를 하려고 해요. 도와주세요~~"}
		 ,{"word":"프로그래밍은 Java 를 해야 하나요?"}
		 ,{"word":"javascript 는 쉬운가요?"}
		 ,{"word":"프론트 엔드를 하려면 JavaScript 를 해야 하나요?"}
		 ,{"word":"질문있어요 jQuery 와 javaScript 는 관련이 있나요?"}] 
		 
		 또는 []
	  */
	}
	
	

	// === #118. 원게시물에 딸린 댓글들을 조회해오기 (Ajax 로 처리) === //
	@GetMapping("commentList")
	@ResponseBody
	public String commentList(@RequestParam(defaultValue = "") String parentSeq,
			                  @RequestParam(defaultValue = "1") String currentShowPageNo){
		
		int sizePerPage = 3; // 한 페이지당 3개의 댓글을 보여줄 것임.
		
		// **** 가져올 게시글의 범위를 구한다.(공식임!!!) **** 
		/*
		     currentShowPageNo      startRno     endRno
		    --------------------------------------------
		           1 page     ==>      1           3
		           2 page     ==>      4           6
		           3 page     ==>      7           9
		           4 page     ==>     10          12
		           ......             ...         ...
		*/
		int startRno = ((Integer.parseInt(currentShowPageNo) - 1) * sizePerPage) + 1; // 시작 행번호
		int endRno = startRno + sizePerPage - 1; // 끝 행번호 
		
		Map<String, String> paraMap = new HashMap<>();
		
		paraMap.put("parentSeq", parentSeq);
		
		paraMap.put("startRno", String.valueOf(startRno)); // Oracle 11g 와 호환되는 것으로 사용하기 위함 
		paraMap.put("endRno", String.valueOf(endRno));     // Oracle 11g 와 호환되는 것으로 사용하기 위함
		 
		paraMap.put("currentShowPageNo", currentShowPageNo);     // Oracle 12c 이상으로 사용하기 위함
		paraMap.put("sizePerPage", String.valueOf(sizePerPage)); // Oracle 12c 이상으로 사용하기 위함
		
		List<CommentVO> commentList = service.getCommentList_Paging(paraMap); 
		int totalCount = service.getCommentTotalCount(parentSeq); // 페이징 처리시 보여주는 순번을 나타내기 위한 것임.
		
		JSONArray jsonArr = new JSONArray();  //  []
		
		if(commentList != null) {
			for(CommentVO cmtvo : commentList) {
				JSONObject jsonObj = new JSONObject();           // {}
				jsonObj.put("seq", cmtvo.getSeq());              // {"seq":"3"}
				jsonObj.put("fk_userid", cmtvo.getFk_userid());  // {"seq":"3","fk_userid":"seoyh"}
				jsonObj.put("name", cmtvo.getName());            // {"seq":"3","fk_userid":"seoyh","name":"서영학"}
				jsonObj.put("content", cmtvo.getContent());      // {"seq":"3","fk_userid":"seoyh","name":"서영학","content":"세번째 댓글쓰기 입니다"}
				jsonObj.put("regDate", cmtvo.getRegDate());      // {"seq":"3","fk_userid":"seoyh","name":"서영학","content":"세번째 댓글쓰기 입니다","regDate":"2025-01-24 10:50:40"} 
				
				jsonObj.put("totalCount", totalCount); // 페이징 처리시 보여주는 순번을 나타내기 위한 것임.
				// {"seq":"3","fk_userid":"seoyh","name":"서영학","content":"세번째 댓글쓰기 입니다","regDate":"2025-01-24 10:50:40","totalCount":23}
				
				jsonObj.put("sizePerPage", sizePerPage); // 페이징 처리시 보여주는 순번을 나타내기 위한 것임. 
				// {"seq":"3","fk_userid":"seoyh","name":"서영학","content":"세번째 댓글쓰기 입니다","regDate":"2025-01-24 10:50:40","totalCount":23,"sizePerPage":3}
				
				jsonArr.put(jsonObj);
			}// end of for------------------------
			
		}// end of if(commentList != null)--------------------
		
	//	System.out.println(jsonArr.toString());
		/*
		  [{"name":"서영학","regDate":"2025-02-05 10:41:34","totalCount":23,"sizePerPage":3,"fk_userid":"seoyh","seq":"37","content":"댓글연습 23"}
		  ,{"name":"서영학","regDate":"2025-02-05 10:41:32","totalCount":23,"sizePerPage":3,"fk_userid":"seoyh","seq":"36","content":"댓글연습 22"}
		  ,{"name":"서영학","regDate":"2025-02-05 10:41:30","totalCount":23,"sizePerPage":3,"fk_userid":"seoyh","seq":"35","content":"댓글연습 21"}]
		 
		  또는
		  
		  [] 
		*/
		
		return jsonArr.toString();
		
	}	
	
	
	
	
	
	
	
	
}
