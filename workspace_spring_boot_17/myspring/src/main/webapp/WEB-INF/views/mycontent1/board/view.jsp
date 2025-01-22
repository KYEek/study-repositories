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

	})//end of ready---------------------------------------
	
	// === #42. 이전글제목, 다음글제목 보기 === //
	function goView(seq) {
		location.href=`<%= ctxPath%>/board/list?seq=\${seq}`;
	}
</script>

<div style="display: flex;">
  <div style="margin: auto; padding-left: 3%;">

    <h2 style="margin-bottom: 30px;">글내용보기</h2>
    <c:if test="${not empty requestScope.boardvo}">
    	<table class="table table-bordered table-dark" style="width: 1024px; word-wrap: break-word; table-layout: fixed;">
    		<tr>
    			<th style="width:15%">글번호</th>
    			<td>${requestScope.boardvo.seq}</td>
    		</tr>
    		<tr>
    			<th>성명</th>
    			<td>${requestScope.boardvo.name}</td>
    		</tr>
    		<tr>
    			<th>성명</th>
    			<td>${requestScope.boardvo.subject}</td>
    		</tr>
    		<tr>
    			<th>내용</th>
    			<td><p style="word-break: break-all;">${requestScope.boardvo.content}</p></td>
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
    
	<!--이전글제목, 다음글 제목 보기 시작 -->
	<c:if test="${not empty requestScope.boardvo}">
		<div>이전글&nbsp;제목&nbsp;&nbsp;<span class="move" onclick="goView('${requestScope.boardvo.previousseq}')">${requestScope.boardvo.previoussubject}</span></div>
		<div>다음글&nbsp;제목&nbsp;&nbsp;<span class="move" onclick="goView('${requestScope.boardvo.nextseq}')">${requestScope.boardvo.nextsubject}</span></div>
	</c:if>
	<!--이전글제목, 다음글 제목 보기 끝 -->
   </div>
</div>
<jsp:include page="../../footer/footer1.jsp" />