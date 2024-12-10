<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/test/test4.up 페이지</title>
</head>
<body>
안녕하세요? ${requestScope.name}입니다.<br>
<img src="<%= request.getContextPath()%>/images/${requestScope.img}" />
</body>
</html>