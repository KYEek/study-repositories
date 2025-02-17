<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%-- ===JSTL(Java Standard Tag Library) 사용하기--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전송되어져 온 값을 if를 사용하여 비교한 결과물 출력하기</title>
</head>
<body>
	<c:if test="${param.first eq param.second}">
	${param.first} 와 ${param.second} 은 <span style="color:blue;">같습니다.</span>
	</c:if>
	<c:if test="${param.first ne param.second}">
	${param.first} 와 ${param.second} 은 <span style="color:red;">같지 않습니다.</span>
	</c:if>
	
	<hr style="border: solid 1px blue; margin:20px 0;">
	<c:if test="${param.first == param.second}">
	${param.first} 와 ${param.second} 은 <span style="color:blue;">같습니다.</span>
	</c:if>
	<c:if test="${param.first != param.second}">
	${param.first} 와 ${param.second} 은 <span style="color:red;">같지 않습니다.</span>
	</c:if>
	<hr style="border: solid 1px red; margin:20px 0;">
	<c:if test="${empty param.third }">
	세번째 입력란은 <span style="background-color:black; color:yellow;">입력하지 않으셨습니다.</span>
	</c:if>
	<c:if test="${not empty param.third }">
	세번째 입력란은 <span style="color:green;">입력하셨습니다.</span>
	</c:if>
	<c:if test="${! empty param.third }">
	세번째 입력란은 <span style="color:blue;">입력하셨습니다.</span>
	</c:if>
</body>
</html>