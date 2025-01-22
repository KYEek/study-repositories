<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String ctxPath = request.getContextPath();
    //     /begin
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSR-303 을 사용 및 @Valid 어노테이션 연습</title>

</head>
<body>

    <div>/test/form_beginvo_jsr303 페이지</div>
    <br>
	<form name="testFrm" action="<%= ctxPath%>/test/form_beginvo_jsr303" method="post"> 
		번호 : <input type="text" name="no" /><br>
		성명 : <input type="text" name="name" /><br>
	    <input type="submit" value="확인" />
	    <input type="reset"  value="취소" /> 
	</form>

</body>
</html>




