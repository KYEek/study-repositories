<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>select 연습</title>

<style type="text/css">
	table, th, td {
		border: solid 1px gray;
		border-collapse: collapse;
	}
</style>

</head>
<body>

	<h3>오라클 서버에 있는 데이터 조회(begin/select_one/{no}.action)</h3>
	<table>
		<tr>
			<th>번호</th>
			<th>입력번호</th>
			<th>성명</th>
			<th>작성일자</th>
		</tr>
		
		   <tr>
		      <td>1</td>
		      <td>${requestScope.bvo.no}</td>
		      <td>${requestScope.bvo.name}</td>
		      <td>${requestScope.bvo.writeday}</td>
		   </tr>
    </table>
    
	<button onclick="location.href='/begin/test/select_all_2.action'">돌아가기</button>
</body>
</html>