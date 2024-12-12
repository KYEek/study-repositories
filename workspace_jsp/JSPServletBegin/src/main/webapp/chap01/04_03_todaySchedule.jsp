<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘의 스케줄</title>
<link rel="stylesheet" type="text/css" href="css/04.css" />
</head>
<body>
	<h3>오늘의 메뉴</h3>
	<ol>
		<li>기상</li>
		<li>식사</li>
		<li>PC방</li>
		<li>취침</li>
	</ol>
	<p id = "today">
	현재시각 : <%@include file="04_01_todayTime.jsp"%>
	</p>
</body>
</html>