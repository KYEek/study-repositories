<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String ctxPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script>
windows.onload=function(){
	alert("회원가입에 감사드립니다");
	
	const frm = document.LoginFrm;
	frm.action = "<%=ctxPath%>/login/login.up";
	frm.method = "post";
	frm.submit();
}
</script>
<title>Insert title here</title>
</head>
<body>
	<form name="LoginFrm">
		<input type="hidden" name="userid" value="${requestScope.userid }" />
		<input type="hidden" name="pwd" value="${requestScope.pwd }" />
	</form>
</body>
</html>