<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
// 컨텍스트 패스명(context path name)을 알아오고자 한다.
String ctxPath = request.getContextPath();
// ctxPath ==> /JSPServletBegin
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="<%=ctxPath%>/bootstrap-4.6.2-dist/css/bootstrap.min.css"
	type="text/css" />

<!-- 사용자 CSS -->
<link rel="stylesheet" href="" type="text/css" />
<!-- Optional JavaScript -->
<script src="<%=ctxPath%>/js/jquery-3.7.1.min.js" type="text/javascript"></script>
<script src="<%=ctxPath%>/js/bootstrap.bundle.min.js"
	type="text/javascript"></script>
<script src="" type="text/javascript"></script>
<title>개인성향 모든 정보 출력 페이지</title>
</head>
<body>
	<div id="div2">
		<div class="container">
			<h3 class="mb-4">개인성향 모든 정보 출력 페이지(JSTL을 사용하여 작성한 것)</h3>
			<c:if test="${not empty requestScope.personList}">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>성명</th>
							<th>학력</th>
							<th>색상</th>
							<th>음식</th>
							<th>등록일자</th>
							<th>변경일자</th>
						</tr>
					<tbody>
						<c:forEach var="psdto" items="${requestScope.personList}">
							<tr>

								<td>${psdto.name}</td>
								<td>${psdto.school}</td>
								<td>${psdto.color}</td>
								<td>${psdto.strFood}</td>
								<td>${psdto.registerday}</td>
								<td>${psdto.updateday}</td>
							</tr>
						</c:forEach>

					</tbody>
					</thead>
				</table>
			</c:if>
			<c:if test="${empty requestScope.personList}">
				<span class="text-danger">데이터가 존재하지 않습니다.</span>
			</c:if>
			<div class="text-center mt-5">
				<a href="PersonRegister.do" class="btn btn-info">개인성향 모든정보 보기</a>
			</div>
		</div>
	</div>
</body>
</html>