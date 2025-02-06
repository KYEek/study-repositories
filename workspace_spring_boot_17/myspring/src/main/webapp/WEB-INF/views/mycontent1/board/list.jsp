<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
	String ctxPath = request.getContextPath();
    //     /myspring
%>

<jsp:include page="../../header/header1.jsp" /> 

<style type="text/css">
    
    th {background-color: #ddd}
    
    .subjectStyle {font-weight: bold;
                   color: navy;
                   cursor: pointer; }
                   
    a {text-decoration: none !important;} /* 페이지바의 a 태그에 밑줄 없애기 */
    
</style>

<script type="text/javascript">

   $(document).ready(function(){
	   
	   $("span.subject").hover(function(e){
		   $(e.target).addClass("subjectStyle");
	   }, function(e){
		   $(e.target).removeClass("subjectStyle");
	   });
	   
	   
	   $("input:text[name='searchWord']").bind("keyup", function(e){
		   if(e.keyCode == 13){ // 엔터를 했을 경우
			   goSearch();
		   }
	   });
	   
	   // 검색시 검색조건 및 검색어 값 유지시키기
	   if(${not empty requestScope.paraMap}) {
		   $("select[name='searchType']").val("${requestScope.paraMap.searchType}");
		   $("input[name='searchWord']").val("${requestScope.paraMap.searchWord}");
	   }
	   
	   
	   <%-- === #88. 검색어 입력시 자동글 완성하기 2 === --%>
	   $("div#displayList").hide();
	   
	   $("input[name='searchWord']").keyup(function(){
		  
		   const wordLength = $(this).val().trim().length;
		   // 검색어에서 공백을 제거한 길이를 알아온다.
		   
		   if(wordLength == 0) {
			   $("div#displayList").hide();
			   // 검색어가 공백이거나 검색어 입력후 백스페이스키를 눌러서 검색어를 모두 지우면 검색된 내용이 안 나오도록 해야 한다. 
		   }
		   
		   else {
			   
			   if( $("select[name='searchType']").val() == "subject" || 
			       $("select[name='searchType']").val() == "name") {
				
				   $.ajax({
					   url:"<%= ctxPath%>/board/wordSearchShow",
					   type:"get",
					   data:{"searchType":$("select[name='searchType']").val()
						    ,"searchWord":$("input[name='searchWord']").val()},
					   dataType:"json",
					   success:function(json){
						// console.log(JSON.stringify(json));
						   /*
						       [{"word":"java가 쉽나요?"}
						       ,{"word":"JAVA공부를 하려고 해요. 도와주세요~~"}
						       ,{"word":"프로그래밍은 Java 를 해야 하나요?"}
						       ,{"word":"javascript 는 쉬운가요?"}
						       ,{"word":"프론트 엔드를 하려면 JavaScript 를 해야 하나요?"}
						       ,{"word":"질문있어요 jQuery 와 javaScript 는 관련이 있나요?"}]
						      
						   또는 []
						   */
						   
						   <%-- === #93. 검색어 입력시 자동글 완성하기 7 === --%>
						   if(json.length > 0){
							   // 검색된 데이터가 있는 경우임.
							   
							   let v_html = ``;
							   
							   $.each(json, function(index, item){
								   const word = item.word;
								   /* java가 쉽나요?
									  JAVA공부를 하려고 해요. 도와주세요~~
									  프로그래밍은 Java 를 해야 하나요?
									  javascript 는 쉬운가요?
									  프론트 엔드를 하려면 JavaScript 를 해야 하나요?
									  질문있어요 jQuery 와 javaScript 는 관련이 있나요?		  
								   */ 
								   
							   // word.toLowerCase() 은 word 를 모두 소문자로 변경하는 것이다.
							   /*     java가 쉽나요?
								      java공부를 하려고 해요. 도와주세요~~
								      프로그래밍은 java 를 해야 하나요?
								      javascript 는 쉬운가요?
								      프론트 엔드를 하려면 javascript 를 해야 하나요?
								      질문있어요 jquery 와 javascript 는 관련이 있나요?		  
							   */ 
								   
							       const idx = word.toLowerCase().indexOf($("input[name='searchWord']").val().toLowerCase());
								   // 만약에 검색어가 JavA 이라면
								   
							   /*     java가 쉽나요?   은 idx 가 0 이다.                  
								      java공부를 하려고 해요. 도와주세요~~  은 idx 가 0 이다.
								      프로그래밍은 java 를 해야 하나요?     은 idx 가 7 이다.
								      javascript 는 쉬운가요?   은 idx 가 0 이다.
								      프론트 엔드를 하려면 javascript 를 해야 하나요?  은 idx 가 12 이다.
								      질문있어요 jquery 와 javascript 는 관련이 있나요?	은 idx 가 15 이다.	  
							   */ 
							   
							       const len = $("input[name='searchWord']").val().length; 
								   // 검색어(JavA)의 길이 len 은 4 가 된다. 
							   /*	   
							       console.log("~~~~ 시작 ~~~~");
								   console.log(word.substring(0, idx));       // 검색어(JavA) 앞까지의 글자      ==> 프로그래밍은 
								   console.log(word.substring(idx, idx+len)); // 검색어(JavA) 글자             ==> Java
								   console.log(word.substring(idx+len));      // 검색어(JavA) 뒤부터 끝까지 글자  ==>  를 해야 하나요?  
							       console.log("~~~~ 끝 ~~~~");
							   */
							       
							       const result = word.substring(0, idx) + "<span style='color:purple;'>"+word.substring(idx, idx+len)+"</span>" + word.substring(idx+len); 
							       
								   v_html += `<span style='cursor:pointer;' class='result'>\${result}</span><br>`;
								   
							   }); // end of $.each(json, function(index, item){})-----------
							   
							   const input_width = $("input[name='searchWord']").css("width"); // 검색어 input 태그 width 값 알아오기  
							   
							   $("div#displayList").css({"width":input_width}); // 검색결과 div 의 width 크기를 검색어 입력 input 태그의 width 와 일치시키기 
							   
							   $("div#displayList").html(v_html).show();
						   }
					   },
					   error: function(request, status, error){
						   alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
					   }    
				   });
				   
			   }
		   }
	   });// end of $("input[name='searchWord']").keyup(function(){})--------
	   
	   
	   <%-- === #94. 검색어 입력시 자동글 완성하기 8 === --%>
	   $(document).on("click", "span.result", function(e){
		   const word = $(e.target).text();
		   $("input[name='searchWord']").val(word); // 텍스트박스에 검색된 결과의 문자열을 입력해준다.
		   $("div#displayList").hide();
		   goSearch();
	   });
	   
   });// end of $(document).ready(function(){})-------------------------------
   
   
   // Function Declaration
   function goView(seq) {
	   
	<%--  
	   location.href=`<%= ctxPath%>/board/view?seq=\${seq}`; 
	--%>
	 
	   const goBackURL = "${requestScope.goBackURL}";
	// console.log(goBackURL);
	   //   /board/list?searchType=subject&searchWord=입니다&currentShowPageNo=7
	
	<%--		   
	   아래처럼 get 방식으로 보내면 안된다. 왜냐하면 get 방식에서 &는 데이터의 구분자로 사용되기 때문이다.!!!! 
	   그래서 BoardController 의 @GetMapping("view") 에 가서 @RequestParam String goBackURL 에 저장된 값을 꺼내보면 
	   /board/list?searchType=subject&searchWord=입니다&currentShowPageNo=7 와 같이 안나오고
	   /board/list?searchType=subject 만 나온다.
	   그래서 보내줄 데이터에 & 가 들어가 있는 경우라면 get 방식이 아닌 post 방식으로 전달해주어야 한다. 		   
	--%>   
    <%--
	   location.href=`<%= ctxPath%>/board/view?seq=\${seq}&goBackURL=\${goBackURL}`;
	--%>   
	
	<%-- 그러므로 &를 데이터의 구분자로 사용하지 않고 글자 그대로 인식하도록 하기 위해 post 방식으로 보내야 한다.
	     아래의 본문에 #105 에 표기된 form 태그를 먼저 만든다. --%>
	   const frm = document.goViewFrm;
	   frm.seq.value = seq;
	   frm.goBackURL.value = goBackURL;
	   
	   if(${not empty requestScope.paraMap}) { // 검색조건이 있을 경우 
		   frm.searchType.value = "${requestScope.paraMap.searchType}"; 
		   frm.searchWord.value = "${requestScope.paraMap.searchWord}";   
	   } 
	   
	   frm.method = "post";
	   frm.action = "<%= ctxPath%>/board/view";
	   frm.submit();
	   
   }// end of goView(seq)----------------------
   
   
   function goSearch() {
	   const frm = document.searchFrm;
  <%-- 
       frm.method = "get";
	   frm.action = "<%= ctxPath%>/board/list";
  --%>
	   frm.submit();
   }// end of function goSearch()-----------------
   

</script>

<div style="display: flex;">
  <div style="margin: auto; padding-left: 3%;">

	<h2 style="margin-bottom: 30px;">글목록</h2>
	
	<table style="width: 1024px" class="table table-bordered">
		<thead>
		    <tr>
		    	<th style="width: 70px;  text-align: center;">순번</th>
		    	<th style="width: 70px;  text-align: center;">글번호</th>
				<th style="width: 300px; text-align: center;">제목</th>
				<th style="width: 70px;  text-align: center;">성명</th>
				<th style="width: 150px; text-align: center;">날짜</th>
				<th style="width: 60px;  text-align: center;">조회수</th>
		    </tr>
		</thead>
		
		<tbody>
		    <c:if test="${not empty requestScope.boardList}">
		       <c:forEach var="boardvo" items="${requestScope.boardList}" varStatus="status">
		          <tr>
		              <td align="center">
		                  ${ (requestScope.totalCount) - (requestScope.currentShowPageNo - 1) * (requestScope.sizePerPage) - (status.index) }         
		              <%-- >>> 페이징 처리시 보여주는 순번 공식 <<<
						   데이터개수 - (페이지번호 - 1) * 1페이지당보여줄개수 - 인덱스번호 => 순번 
						
						   <예제>
						   데이터개수 : 12
						   1페이지당보여줄개수 : 5
						
						   ==> 1 페이지       
						   12 - (1-1) * 5 - 0  => 12
						   12 - (1-1) * 5 - 1  => 11
						   12 - (1-1) * 5 - 2  => 10
						   12 - (1-1) * 5 - 3  =>  9
						   12 - (1-1) * 5 - 4  =>  8
						
						   ==> 2 페이지
						   12 - (2-1) * 5 - 0  =>  7
						   12 - (2-1) * 5 - 1  =>  6
						   12 - (2-1) * 5 - 2  =>  5
						   12 - (2-1) * 5 - 3  =>  4
						   12 - (2-1) * 5 - 4  =>  3
						
						   ==> 3 페이지
						   12 - (3-1) * 5 - 0  =>  2
						   12 - (3-1) * 5 - 1  =>  1 
					  --%>
		              </td>
		              <td align="center">${boardvo.seq}</td>
		              <td>
		                  <%-- === 댓글쓰기 및 답변형 및 파일첨부가 있는 게시판 시작 === --%>
		                     <%-- 첨부파일이 없는 경우 시작 --%>
			                  
			                  <%-- 댓글이 있는 경우 시작 --%>
				                  <c:if test="${boardvo.commentCount > 0}">
					                  <c:if test="${fn:length(boardvo.subject) < 30}">
					                     <span class="subject" onclick="goView('${boardvo.seq}')">${boardvo.subject}<span style="vertical-align: super;">[<span style="color: red; font-style: italic; font-size: 9pt; font-weight: bold;">${boardvo.commentCount}</span>]</span></span>
					                  </c:if>
					                  <c:if test="${fn:length(boardvo.subject) >= 30}">
					                     <span class="subject" onclick="goView('${boardvo.seq}')">${fn:substring(boardvo.subject, 0, 28)}..<span style="vertical-align: super;">[<span style="color: red; font-style: italic; font-size: 9pt; font-weight: bold;">${boardvo.commentCount}</span>]</span></span>
					                  </c:if>
				                  </c:if>
			                  <%-- 댓글이 있는 경우 끝 --%>
			                  
			                  <%-- 댓글이 없는 경우 시작 --%>
			                  	  <c:if test="${boardvo.commentCount == 0}">
					                  <c:if test="${fn:length(boardvo.subject) < 30}">
					                     <span class="subject" onclick="goView('${boardvo.seq}')">${boardvo.subject}</span>
					                  </c:if>
					                  <c:if test="${fn:length(boardvo.subject) >= 30}">
					                     <span class="subject" onclick="goView('${boardvo.seq}')">${fn:substring(boardvo.subject, 0, 28)}..</span>
					                  </c:if>
				                  </c:if>
			                  <%-- 댓글이 없는 경우 끝 --%>
			                  
		                     <%-- 첨부파일이 없는 경우 끝 --%>
		                  <%-- === 댓글쓰기 및 답변형 및 파일첨부가 있는 게시판 끝 === --%>   
		              </td>
		              <td align="center">${boardvo.name}</td>
		              <td align="center">${boardvo.regDate}</td>
		              <td align="center">${boardvo.readCount}</td>
		          </tr>
		       </c:forEach>
		    </c:if>
		    
		    <c:if test="${empty requestScope.boardList}">
		       <tr>
		          <td colspan="6">데이터가 없습니다.</td>
		       </tr>
		    </c:if>
		</tbody>
    </table>
    
    
    <%-- === #103. 페이지바 보여주기 === --%>
    <div align="center" style="border: solid 0px gray; width: 80%; margin: 30px auto;">
    	${requestScope.pageBar}
    </div>
    
    <%-- === #82. 글검색 폼 추가하기 : 글제목, 글내용, 글제목+글내용, 글쓴이로 검색을 하도록 한다. --%>
    <form name="searchFrm" style="margin-top: 20px;">
		<select name="searchType" style="height: 26px;">
			<option value="subject">글제목</option>
			<option value="content">글내용</option>
			<option value="subject_content">글제목+글내용</option>
			<option value="name">글쓴이</option>
		</select>
		<input type="text" name="searchWord" size="50" autocomplete="off" /> 
		<input type="text" style="display: none;"/> <%-- form 태그내에 input 태그가 오로지 1개 뿐일경우에는 엔터를 했을 경우 검색이 되어지므로 이것을 방지하고자 만든것이다. --%>  
		<button type="button" class="btn btn-secondary btn-sm" onclick="goSearch()">검색</button> 
	</form>	
    
    <%-- === #87. 검색어 입력시 자동글 완성하기 1 === --%>
    <div id="displayList" style="border:solid 1px gray; border-top:0px; height:100px; margin-left:13.2%; margin-top:-1px; margin-bottom:30px; overflow:auto;">
	</div>
    
  </div>
 </div>   

<%-- === #105. 페이징 처리되어진 후 특정 글제목을 클릭하여 상세내용을 본 이후
	           사용자가 "검색된결과목록보기" 버튼을 클릭했을때 돌아갈 페이지를 알려주기 위해
	           현재 페이지 주소를 뷰단으로 넘겨준다. --%>
<form name="goViewFrm">
   <input type="hidden" name="seq" />
   <input type="hidden" name="goBackURL" />
   <input type="hidden" name="searchType" />
   <input type="hidden" name="searchWord" />
</form>	           
	           

<jsp:include page="../../footer/footer1.jsp" />













    
    