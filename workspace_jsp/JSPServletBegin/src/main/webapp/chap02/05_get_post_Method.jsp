<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
//컨텍스트 패스명(context path name)을 알아오고자 한다.
String ctxPath = request.getContextPath();

System.out.println("ctxPath => " + ctxPath);
// ctxPath => /JSPServletBegin <== WAS(톰캣서버) path 설정의 기본값임
// ctxPath => /gclass          <== WAS(톰캣서버) path 설정을 gclass로 변경한 경우임
// ctxPath =>                  <== WAS(톰캣서버) path 설정을 /또는 아무것도 없는 것으로 변경한 경우임
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GET/POST방식으로 데이터 전송하기</title>
<link rel="stylesheet" type="text/css" href="css/04.css" />
</head>
<body>
	<div id="container">
		<form action="<%= ctxPath%>/05_get_post_Method.do" method="get">
			<fieldset>
				<legend>개인성향 테스트 05(GET/POST method)</legend>
				<ul>
					<li><label for="name">성명</label> <input type="text"
						name="name" id="name" placeholder="성명입력" required /></li>
					<li><label>학력</label> <select name="school">
							<option>고졸</option>
							<option>초대졸</option>
							<option>대졸</option>
							<option>대학원졸</option>
					</select></li>
					<li><label>좋아하는 색상</label>
						<div>
							<label for="red">빨강</label> <input type="radio" name="color"
								id="red" value="red" /> <label for="blue">파랑</label> <input
								type="radio" name="color" id="blue" value="blue" /> <label
								for="green">초록</label> <input type="radio" name="color"
								id="green" value="green" /> <label for="yellow">노랑</label> <input
								type="radio" name="color" id="yellow" value="yellow" />
						</div></li>
					<li><label>좋아하는 음식(다중선택)</label>
						<div>
							<label for="food1">짜장면</label> <input type="checkbox" name="food"
								id="food1" value="짜장면" /> <label for="food2">짬뽕</label> <input
								type="checkbox" name="food" id="food2" value="짬뽕" /> <label
								for="food3">탕수육</label> <input type="checkbox" name="food"
								id="food3" value="탕수육" /> <label for="food4">양장피</label> <input
								type="checkbox" name="food" id="food4" value="양장피" /> <label
								for="food5">팔보채</label> <input type="checkbox" name="food"
								id="food5" value="팔보채" />
						</div></li>
					<li><input type="submit" value="전송" /> <input type="reset"
						value="취소" /></li>
				</ul>
			</fieldset>
		</form>
	</div>
</body>
</html>