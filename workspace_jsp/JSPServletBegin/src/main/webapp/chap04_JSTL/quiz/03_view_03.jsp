<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String ctxPath = request.getContextPath();
String name = (String)request.getAttribute("name");
String school = (String)request.getAttribute("school");
String color = (String)request.getAttribute("color");
String[] arr_food = (String[] )request.getAttribute("arr_food");


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인셩향 테스트 결과</title>
</head>
<body>
	<h2>개인성향 테스트 결과</h2>
	
	<hr style="border:solid 1px blue;">
	<h3>EL(Express Language) JSTL(Java Standard Tag Library)을 사용한 것</h3>
	<div>
		<ol>
			<li>성명 : ${requestScope.name2}</li>
			<li>학력 : ${requestScope.school2}</li>
			<li>색상 : <span style="display: inline-block; width:20px; height :20px; border-radius: 50%; background-color:${requestScope.color2};"></span></li>
			<li>음식 : 
			<c:forEach var="food_img" items="${requestScope.arr_food}">
				<img src="<%=request.getContextPath()%>/chap03_StandardAction/images/${food_img}"  width ="76.5px" height="57px"/>
			</c:forEach>
			</li>
		</ol>
	</div>
</body>
</html>