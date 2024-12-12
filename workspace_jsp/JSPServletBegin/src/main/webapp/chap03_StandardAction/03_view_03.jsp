<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
	<h3>스클립틀릿을 사용한 것</h3>
	<div>
		<ol>
			<li>성명 : <%= name%></li>
			<li>학력 : <%= school %></li>
			<li>색상 : <span style="display: inline-block; width:20px; height :20px; border-radius: 50%; background-color:<%= color %>;"></span></li>
			<li>음식 : <% for(int i = 0; i<arr_food.length; i++) {%>
				<img src="<%= ctxPath%>/chap03_StandardAction/images/<%= arr_food[i]%>" width ="76.5px" height="57px"/>
				<%}%></li>
		</ol>
	</div>
	<hr style="border:solid 1px blue;">
	<h3>EL(Express Language)을 사용한 것</h3>
	<div>
		<ol>
			<li>성명 : ${requestScope.name2}</li>
			<li>학력 : ${requestScope.school2}</li>
			<li>색상 : <span style="display: inline-block; width:20px; height :20px; border-radius: 50%; background-color:${requestScope.color2};"></span></li>
			<li>음식 : <% for(int i = 0; i<arr_food.length; i++) {%>
				<img src="<%= ctxPath%>/chap03_StandardAction/images/<%= arr_food[i]%>" width ="76.5px" height="57px"/>
				<%}%></li>
		</ol>
	</div>
</body>
</html>