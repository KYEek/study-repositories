<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import= "java.util.Date"%>
<%-- 필요한 클래스를 import 하려면 <%@ page %>  page directive(페이지 지시어)를 사용하여 import 를 한다. --%>
<%
	// *** 현재 시각을 알아오기 *** //
	Date now = new Date(); // 현재시각
		 String currentTime = String.format("%tF %tp %tT %tA %tZ", now, now, now, now, now);
	 /*
	    <참고>
	      날짜 타입 format         설명
	     --------------------------------------------------
	      %tF              날짜를 yyyy-mm-dd 형식으로 포맷해주는 것
	      %tT              날짜의 시각을 HH:MM:SS 형식으로 포맷해주는 것  
	      %tp              오전, 오후를 출력
	      %tA              요일명 출력
	      %tY              4자리 년도만 출력
	      %tB              월의 이름(January, February ...)출력
	      %tm              월의 01,02,03 ~~ 10,11,12 로 출력
	      %td              일수를 1~31 로 출력
	      %te              %td와 동일함
	      %tk              시간을 0~23 으로 출력
	      %tl              시간을 1~12 으로 출력
	      %tM              분을 00~59 으로 출력
	      %tS              초를 00~59 으로 출력
	      %tZ 			  타임존을 출력(한국은 KST로 나옴).
		 */
		 
		 
%>
<%-- HTML 태그가 없어도 맨위의 page 지시어 contentType="text/html; charset=UTF-8" 때문에 JSP 이다. 즉, 웹페이지로 출력되어진다. --%>
<%= currentTime%>
