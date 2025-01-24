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
	   
   });// end of $(document).ready(function(){})------------------
   
   
   // Function Declaration
   function goView(seq) {
	   
	   location.href=`<%= ctxPath%>/board/view?seq=\${seq}`; 
	   
   }// end of goView(seq)----------------------
   

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
		              <td align="center">${status.count}</td>
		              <td align="center">${boardvo.seq}</td>
		              <td>
		                  <c:if test="${fn:length(boardvo.subject) < 30}">
		                     <span class="subject" onclick="goView('${boardvo.seq}')">${boardvo.subject}</span>
		                  </c:if>
		                  <c:if test="${fn:length(boardvo.subject) >= 30}">
		                     <span class="subject" onclick="goView('${boardvo.seq}')">${fn:substring(boardvo.subject, 0, 28)}..</span>
		                  </c:if>
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
    
  </div>
 </div>   

<jsp:include page="../../footer/footer1.jsp" />













    
    