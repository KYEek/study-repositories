<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import= "chap03.MemberDTO" %>
<% 
	// === MemberDTO 객체 생성하기 === //
	MemberDTO mbr1 = new MemberDTO();
	mbr1.setIrum("이순신");
	mbr1.setJubun("9811271234567");
	
	String name1 = mbr1.getName();
	String jubun1 = mbr1.getJubun();
	String gender1 = mbr1.getGender();
	///////////////////////////////////
	// === MemberDTO 객체 생성하기 === //
	MemberDTO mbr2 = new MemberDTO("엄정화", "9611272234567");
	mbr2.setIrum("이순신");
	mbr2.setJubun("9811271234567");
	
	String name2 = mbr2.getName();
	String jubun2 = mbr2.getJubun();
	String gender2 = mbr2.getGender();
%>
<!DOCTYPE html>        
<html>
<head>
<meta charset="UTF-8">
<title>JSP 표준액션 중 useBean 에 대해서 알아봅니다.</title>
</head>
<body>
	<div>
		<h2>스크립틀릿을 사용하여 MemberDTO 클래스의 객체정보 불러오기</h2>
		<ul>
			<li>성명 : <%= name1%></li>
			<li>주민번호 : <%= jubun1%></li>
			<li>성별 : <%= gender1%></li>
		</ul>
		<br>
		<ul>
			<li>성명 : <%= name2%></li>
			<li>주민번호 : <%= jubun2%></li>
			<li>성별 : <%= gender2%></li>
		</ul>
	</div>
	<hr style="border:solid 1px red;">
	<div>
		<h2>JSP 표준액션 중 useBean을 사용하여 MemberDTO 클래스의 객체정보 불러오기</h2>
		<jsp:useBean id="mbr3" class = "chap03.MemberDTO"></jsp:useBean>
		
		<%--
			위으 것은 아래의 뜼이다.
			chap03.MemberDTO mbr3 = new chap03.MemberDTO(); // 기본생성자
			즉, chap03.MemberDTO 클래스의 기본생성자로 mbr3 이라는 객체를 생성하는 것이다.
			그래서 JSP 표준액션 중 useBean을 사용하려면 반드시 기본생성자가 존재해야 한다.!!! 
		 --%>
		 
		 <jsp:setProperty property="irum" name="mbr3" value="김태희"/>
		 <%--
		 	위의 것은 아래의 뜻이다.
		 	mbr3.setIrum();
		  --%>
		  <jsp:setProperty property="jubun" name="mbr3" value="8911272234567"/>
		  <ul>
			<li>성명 : <jsp:getProperty property="name" name="mbr3"/> </li>
			<%--
				위의 것은 아래의 뜻이다.
				mbr3.getSeongmyung()이거와 같다.
			 --%>
			<li>주민번호 : <jsp:getProperty property="jubun" name="mbr3"/> </li>
			<%--
				위의 것은 아래의 뜻이다.
				mbr3.getJubun()이거와 같다.
			 --%>
			<li>성별 : <jsp:getProperty property="gender" name="mbr3"/></li>
			<%--
				위의 것은 아래의 뜻이다.
				mbr3.getGender()이거와 같다.
			 --%>
		</ul>
	</div>
</body>
</html>