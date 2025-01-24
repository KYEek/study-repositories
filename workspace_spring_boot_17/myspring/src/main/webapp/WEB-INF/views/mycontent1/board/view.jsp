<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
	String ctxPath = request.getContextPath();
    //     /myspring
%>

<jsp:include page="../../header/header1.jsp" />

<style type="text/css">
	
	span.move  {cursor: pointer; color: navy;}
	.moveColor {color: #660029; font-weight: bold; background-color: #ffffe6;}

    td.comment {text-align: center;}

    a {text-decoration: none !important;}
</style>

<script type="text/javascript">

  $(document).ready(function(){
	  
	  goReadComment(); // 페이징 처리 안한 댓글 읽어오기
	  
	  $("span.move").hover(function(e){
       							$(e.target).addClass("moveColor");
            				}, 
            				function(e){
                				$(e.target).removeClass("moveColor");  
					});
	  
	  $("input:text[name='content']").bind("keydown", function(e){
		  if(e.keyCode == 13) { // 엔터
			  goAddWrite();
		  }
	  });
	  
	  let origin_comment_content = "";
	  
	  //댓글 수정
	  $(document).on('click', 'button.btnUpdateComment', function(e){
		const $btn = $(e.target);
		
		if($btn.text()== "수정") {
			//alert($btn.parent().parent().children("td:nth-child(2)").text());
			const $content = $btn.parent().parent().children("td:nth-child(2)");
			origin_comment_content = $content.text();
			$content.html(`<input id='comment_update' type='text' value='\${origin_comment_content}' size='40'/>`);
			$btn.text("완료").removeClass("btn-secondary").addClass("btn-info");
			$btn.next().text("취소").removeClass("btn-secondary").addClass("btn-danger");
			
			$(document).on("keyup", "", function(e){
				if(e.keyCode == 13) {
// 					alert("엔터");
// 					alert($btn.text()));
					$btn.click();
				}
			});
		}
		else if($btn.text()=="완료") {
// 			alert("댓글수정완료");
// 			alert($btn.val()); 수정해야할 댓글 시퀀스 번호
			//수정후 댓글 내용
			//alert($btn.parent().parent().children("td:nth-child(2)").children("input").val());
			const content = $btn.parent().parent().children("td:nth-child(2)").children("input").val();
			
			$.ajax({
				url:"${pageContext.request.contextPath}/board/updateComment",
				type:"put",
				data:{"seq":$btn.val(),
					"content":content},
				dataType:"json",
				success:function(json) {

					console.log(JSON.stringify(json));
					console.log(json.n);
					if(json.n == 1) {
						goReadComment(); //페이징 처리 안한 댓글 읽어오기
					}
					
					$btn.text("수정").removeCLass("btn-info").addClass("btn-secondary");
					$btn.next().text("삭제").removeCLass("btn-danger").addClass("btn-secondary");
				},
				error: function(request, status, error){
				      alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
				  }
			});
			
		}
	  });//end of $(document).on('click', 'button.btnUpdateComment', function(e)
			  
	// ==== 댓글수정취소 / 댓글삭제 ==== //
	$(document).on('click', 'button.btnDeleteComment', function(e) {
		const $btn = $(e.target);
		
		if($btn.text() == "취소") {
// 			alert("댓글 수정 취소");

// 			alert($btn.parent().parent().children("td:nth-child(2)").html());
			const $content = $btn.parent().parent().children("td:nth-child(2)");
			
			$content.html(`\${origin_comment_content}`);

			console.log($btn.prev());
			$btn.text("삭제").removeClass("btn-danger").addClass("btn-secondary");
			$btn.prev().text("수정").removeClass("btn-info").addClass("btn-secondary");
		}
		else if($btn.text() == "삭제") {
// 			alert("댓글 삭제");
			alert($btn.val());
			if(confirm("정말로 삭제하시겠습니까?")) {
				$.ajax({
					url:"${pageContext.request.contextPath}/board/deleteComment",
					type:"put",
					data:{"seq":$btn.val(),
						"parentSeq":"${requestScope.boardvo.seq}"},
					dataType:"json",
					success:function(json) {

						console.log(JSON.stringify(json));
						
						
						if(json.n == 1) {
							goReadComment(); //페이징 처리 안한 댓글 읽어오기
						}
						
						$btn.text("수정").removeCLass("btn-info").addClass("btn-secondary");
						$btn.next().text("삭제").removeCLass("btn-danger").addClass("btn-secondary");
					},
					error: function(request, status, error){
					      alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
					  }
				});
			}			

		}
	});// end of $(document).on('click', 'button.btnDeleteComment', function(e)
	  
  });// end of $(document).ready(function(){})-----------
  
  
  // Function Declaration
  
  // === #42. 이전글제목, 다음글제목 보기 === //
  function goView(seq) {
	  
	  location.href=`<%= ctxPath%>/board/view?seq=\${seq}`;
	  
  }// end of function goView(seq){}-------------------------
  
  
  // === 댓글쓰기 === //
  function goAddWrite() {
	  
	  const comment_content = $("input:text[name='content']").val().trim();
	  if(comment_content == ""){
		 alert("댓글 내용을 입력하세요!!");
		 return; // 종료
	  }
	  
	  // 첨부파일이 없는 댓글쓰기인 경우
	  goAddWrite_noAttach();
	  
  }// end of function goAddWrite()-----------------
  
  
  // 첨부파일이 없는 댓글쓰기
  function goAddWrite_noAttach() {
	  
	  <%--
         // 보내야할 데이터를 선정하는 또 다른 방법
	     // jQuery에서 사용하는 것으로써,
	     // form태그의 선택자.serialize(); 을 해주면 form 태그내의 모든 값들을 name값을 키값으로 만들어서 보내준다. 
	     const queryString = $("form[name='addWriteFrm']").serialize();
      --%>
      
      const queryString = $("form[name='addWriteFrm']").serialize();
  //  alert(queryString);
      /*
         fk_userid=seoyh&name=서영학&content=댓글쓰기내용입니다&parentSeq=3
      */
      
      $.ajax({
    	  url:"<%= ctxPath%>/board/addComment",
    	/*  
    	  data:{"fk_userid":$("input:hidden[name='fk_userid']").val()
    		  , "name":$("input:text[name='name']").val()
    		  , "content":$("input:text[name='content']").val()
    		  , "parentSeq":$("input:hidden[name='parentSeq']").val()},
    	*/
    	// 또는
    	  data:queryString,
    	  
    	  type:"post",
    	  dataType:"json",
    	  success:function(json){
    		  console.log(JSON.stringify(json));
    		  // {"name":"서영학","n":1}
    		  // 또는 
    		  // {"name":"서영학","n":0}
    		  
    		  if(json.n == 0){
    			  alert(json.name + "님의 포인트는 300점을 초과할 수 없으므로 댓글쓰기가 불가합니다.");
    		  }
    		  else {
    		      goReadComment(); // 페이징 처리 안한 댓글 읽어오기
    			                   // 페이징 처리 한 댓글 읽어오기
    		  }
    		  
    		  $("input:text[name='content']").val("");
    	  },
    	  error: function(request, status, error){
		      alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
		  }
      });
	  
  }// end of goAddWrite_noAttach()----------------------------
  
  
  // 페이징 처리 안한 댓글 읽어오기
  function goReadComment() {
	  
	  $.ajax({
		  url:"<%= ctxPath%>/board/readComment",
		  data:{"parentSeq":"${requestScope.boardvo.seq}"},
		  dataType:"json",
		  success:function(json){
			  console.log(JSON.stringify(json));
			  /*
			     [""]
			  */
			  let v_html = ``;
	           if(json.length > 0) {
	              $.each(json, function(index, item){
	                 v_html += `<tr>
	                             <td>\${index+1}</td>
	                             <td>\${item.content}</td>
	                             <td class='comment'>\${item.name}</td>
	                             <td class='comment'>\${item.regDate}</td>`;
	                 
	                           if(${sessionScope.loginuser != null} &&
	                              "${sessionScope.loginuser.userid}" == item.fk_userid) {
	                           
	                             v_html += `<td class='comment'>
	                                              <button type='button' class='btn btn-secondary btn-sm btnUpdateComment' value="\${item.seq}">수정</button>&nbsp;<button type='button' class='btn btn-secondary btn-sm btnDeleteComment' value="\${item.seq}">삭제</button> 
	                                           </td>`;   
	                           }
	                           else {
	                             v_html += `<td>&nbsp;</td>`;
	                           }           
	                 v_html += `</tr>`;
	              });
	           }
	           
	           else {
	              v_html = `<tr>
	                          <td colspan='5'>댓글이 없습니다</td> 
	                        </tr>`;
	           }
			  $("tbody#commentDisplay").html(v_html);
		  },
		  error: function(request, status, error){
		      alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
		  }
	  });
	  
  }// end of function goReadComment()------------------------- 
  

</script>

<div style="display: flex;">
  <div style="margin: auto; padding-left: 3%;">

	 <h2 style="margin-bottom: 30px;">글내용보기</h2>
	 
	 <c:if test="${not empty requestScope.boardvo}">
	 	<table class="table table-bordered table-dark" style="width: 1024px; word-wrap: break-word; table-layout: fixed;">
	 	   <tr>
	 	      <th style="width: 15%">글번호</th>
	 	      <td>${requestScope.boardvo.seq}</td>
	 	   </tr>
	 	   
	 	   <tr>
	 	      <th>성명</th>
	 	      <td>${requestScope.boardvo.name}</td>
	 	   </tr>
	 	   
	 	   <tr>
	 	      <th>제목</th>
	 	      <td>${requestScope.boardvo.subject}</td>
	 	   </tr>
	 	   
	 	   <tr>
	 	      <th>내용</th>
	 	      <td>
	 	         <p style="word-break: break-all;">
	 	            ${requestScope.boardvo.content}
	 	         <%-- 
				    style="word-break: break-all; 은 공백없는 긴영문일 경우 width 크기를 뚫고 나오는 것을 막는 것임. 
				        그런데 style="word-break: break-all; 나 style="word-wrap: break-word; 은
				        테이블태그의 <td>태그에는 안되고 <p> 나 <div> 태그안에서 적용되어지므로 <td>태그에서 적용하려면
				    <table>태그속에 style="word-wrap: break-word; table-layout: fixed;" 을 주면 된다.
				 --%>
	 	         </p>   
	 	      </td>
	 	   </tr>
	 	   
	 	   <tr>
	 	      <th>조회수</th>
	 	      <td>${requestScope.boardvo.readCount}</td>
	 	   </tr>
	 	   
	 	   <tr>
	 	      <th>날짜</th>
	 	      <td>${requestScope.boardvo.regDate}</td>
	 	   </tr>
	 	   
	 	</table>
	 </c:if> 
	 
	 <c:if test="${empty requestScope.boardvo}">
	    <div style="padding: 20px 0; font-size: 16pt; color: red;" >존재하지 않습니다</div> 
	 </c:if>
	 
	 <div class="mt-5">
	 
		 <%-- ==== 이전글제목, 다음글제목 보기 시작 ==== --%>	
		 <c:if test="${not empty requestScope.boardvo}">
			 <div style="margin-bottom: 1%;">이전글&nbsp;제목&nbsp;&nbsp;<span class="move" onclick="goView('${requestScope.boardvo.previousseq}')">${requestScope.boardvo.previoussubject}</span></div> 
			 <div style="margin-bottom: 1%;">다음글&nbsp;제목&nbsp;&nbsp;<span class="move" onclick="goView('${requestScope.boardvo.nextseq}')">${requestScope.boardvo.nextsubject}</span></div>
		 </c:if>
		 <%-- ==== 이전글제목, 다음글제목 보기 끝 ==== --%> 
	 
		 <br>
		 
		 <button type="button" class="btn btn-secondary btn-sm mr-3" onclick="javascript:location.href='<%= ctxPath%>/board/list'">전체목록보기</button>
		 
		 <c:if test="${not empty sessionScope.loginuser && sessionScope.loginuser.userid == requestScope.boardvo.fk_userid}">
		    <button type="button" class="btn btn-secondary btn-sm mr-3" onclick="javascript:location.href='<%= ctxPath%>/board/edit/${requestScope.boardvo.seq}'">글수정하기</button>
		    <button type="button" class="btn btn-secondary btn-sm mr-3" onclick="javascript:location.href='<%= ctxPath%>/board/del/${requestScope.boardvo.seq}'">글삭제하기</button>
		 </c:if>
		 
		 
		 <%-- === #58. 댓글쓰기 폼 추가 === --%>
		 <c:if test="${not empty sessionScope.loginuser}">
		    <h3 style="margin-top: 50px;">댓글쓰기</h3>
		    
		    <form name="addWriteFrm" id="addWriteFrm" style="margin-top: 20px;">
		       <table class="table" style="width: 1024px">
				   <tr style="height: 30px;">
				      <th width="10%">성명</th>
				      <td>
				         <input type="hidden" name="fk_userid" value="${sessionScope.loginuser.userid}" readonly /> 
				         <input type="text" name="name" value="${sessionScope.loginuser.name}" readonly />
				      </td>
				   </tr>
				   
				   <tr style="height: 30px;">
				      <th>댓글내용</th>
				      <td>
				         <input type="text" name="content" size="100" maxlength="1000" /> 
				         
				         <%-- 댓글에 달리는 원게시물 글번호(즉, 댓글의 부모글 글번호) --%>
				         <input type="hidden" name="parentSeq" value="${requestScope.boardvo.seq}" readonly />
				      </td>
				   </tr>
				   
				   <tr>
				      <th colspan="2">
				      	<button type="button" class="btn btn-success btn-sm mr-3" onclick="goAddWrite()">댓글쓰기 확인</button>
				      	<button type="reset" class="btn btn-success btn-sm">댓글쓰기 취소</button>
				      </th>
				   </tr>
				      
			   </table>	      
		    </form>
		 </c:if>
		 
		 <%-- === #67. 댓글 내용 보여주기 === --%>
       <h3 style="margin-top: 50px;">댓글내용</h3>
	      <table class="table" style="width: 1024px; margin-top: 2%; margin-bottom: 3%;">
	         <thead>
	            <tr>
	              <th style="width: 6%">순번</th>
	              <th style="text-align: center;">내용</th>
	              
	              <%-- === 댓글쓰기에 첨부파일이 있는 경우 시작 === --%>
	              
	              
	              <%-- === 댓글쓰기에 첨부파일이 있는 경우 끝 === --%>
	              
	              <th style="width: 8%; text-align: center;">작성자</th>
	              <th style="width: 12%; text-align: center;">작성일자</th>
	              <th style="width: 12%; text-align: center;">수정/삭제</th>
	            </tr>
	         </thead>
	         <tbody id="commentDisplay"></tbody>
	      </table>
	 
	 </div>
	 
  </div>
</div>  	 

<jsp:include page="../../footer/footer1.jsp" />





    