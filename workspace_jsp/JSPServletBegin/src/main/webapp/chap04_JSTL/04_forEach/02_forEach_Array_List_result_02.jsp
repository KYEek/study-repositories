<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%-- ===JSTL(Java Standard Tag Library) 사용하기--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%String ctxPath = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>친구명단 출력하기</title>
   <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%=ctxPath%>/bootstrap-4.6.2-dist/css/bootstrap.min.css.map" type="text/css">
    
</head>
<body>
<div class="container">
	<ol>
		<c:if test="${empty reqiestScope.arr_friend_name}">
			<div>
				<span style="color: red;">나는 친구가 업성요</span>
			</div>

		</c:if>

		<c:if test="${not empty reqiestScope.arr_friend_name}">
			<c:forEach var="friend_name" items="${requestScope.arr_friend_name}">
				<%-- items="${ }" 에 들어오는 것은 배열 또는 List 이다. --%>0
				<%-- 반복의 회수는 배열길이 또는 List 의 size 만큼 반복된다. --%>
				<li style="color: blue;">${friend_name}}</li>
			</c:forEach>
		</c:if>
	</ol>
	<hr style="border: solid 1px red;">
	
	<div style="border: solid 1px blue; width: 60%; martin: 0 auto;">
		<table class="table">
		<c:if test="${empty requestScope.person_List }">
			<tr>
				<td colspan="5" style="text-align:center;">데이터가 없습니다.</td>
			</tr>
		</c:if>
		
		<c:if test="${not empty requestScope.person_List }">
		<thead>
				<tr>
					<th>번호</th>
					<th>성명</th>
					<th>학력</th>
					<th>색상</th>
					<th>선호음식</th>
				</tr>
			</thead>
			<tbody>
			
				<c:forEach var="person_dto" items="${requestScope.person_List}" varStatus="status">
					<tr>
						<td>${status.count}</td>
						<td>${person_dto.name}</td>
						<td>${person_dto.school}</td>
						<td align="center"><span style="display: inline-block; width: 20px; height: 20px; background-color:${person_dto.color}; border-radius: 50%;"></span></td>
						<td>${person_dto.strFood}</td>
					</tr>
				</c:forEach>
				
				
			</tbody>
		</c:if>
			
		</table>
		<table class="table table-dark">
		<c:if test="${empty requestScope.woman_List }">
			<tr>
				<td colspan="5" style="text-align:center;">데이터가 없습니다.</td>
			</tr>
		</c:if>
		
		<c:if test="${not empty requestScope.person_List }">
		<thead>
				<tr>
					<th>번호</th>
					<th>성명</th>
					<th>학력</th>
					<th>색상</th>
					<th>선호음식</th>
				</tr>
			</thead>
			<tbody>
			
				
				<c:forEach var="map" items="${requestScope.woman_List}" varStatus="status">
					<tr>
						<td>${status.count}</td>
						<td>${map.name}</td>
						<td>${map.school}</td>
						<td>${map.gender }</td>
						<td>${map.address }</td>
					</tr>
				</c:forEach>
				
			</tbody>
		</c:if>
			
		</table>
	</div>
	</div>
</body>
</html>