<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
// 컨텍스트 패스명(context path name)을 알아오고자 한다.
String ctxPath = request.getContextPath();
// ctxPath ==> /JSPServletBegin
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="<%=ctxPath%>/bootstrap-4.6.2-dist/css/bootstrap.min.css"
	type="text/css" />

<!-- 사용자 CSS -->
<link rel="stylesheet"
	href="<%=ctxPath%>/chap05_css_js_images/css/personRegister.css"
	type="text/css" />
<!-- Optional JavaScript -->
<script src="<%=ctxPath%>/js/jquery-3.7.1.min.js" type="text/javascript"></script>
<script src="<%=ctxPath%>/js/bootstrap.bundle.min.js"
	type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){
	// 성명 입력해주기
	$("input:text[name='name']").val("${requestScope.psdto.name}");
	// 학력 입력해주기
	$("select[name='school']").val("${requestScope.psdto.school}");
	$("input:radio[id='${requestScope.psdto.color}']").prop('checked', true);
	console.log("${requestScope.psdto.strFood}");
	
	
	if("없음" != "${requestScope.psdto.strFood}") {
		

		const arr_food = "${requestScope.psdto.strFood}".split(",");
		
		
		arr_food.forEach((food)=>
		$("input:checkbox[value="+food+"]").prop('checked', true));
		
		
	}
	
	
	
	});
</script>

<title>개인성향 데이터 수정하기</title>
</head>
<body>
	<div class="container border border-info mt-5 p-4">
		<p class="h3 mb-5">${requestScope.psdto.name}님성향정보 수정하기</p>
		<%-- 먼저 div 엘리먼트에 .form-group 으로 <input>, <textarea> 및 <select> 태그를 감싼다.
            .form-control 클래스가 있는 모든 <input>, <textarea> 및 <select> 요소의 너비는 100% 입니다. 
      --%>
		<form name="registerFrm" action="<%=ctxPath%>/PersonUpdate_2.do"
			method="post">

			<input type="hidden" name="seq" value="${requestScope.psdto.seq}" />
			<div class="row">
				<label class="col-md-4" for="name">성명</label>
				<div class="col-md-8 form-group">
					<input type="text" class="form-control" name="name" id="name"
						autofocus autocomplete="off" />
				</div>
			</div>
			<div class="row">
				<label class="col-md-4">학력</label>
				<div class="col-md-8 form-group">
					<select class="form-control" name="school">
						<option value="">선택하세요</option>
						<option>고졸</option>
						<option>초대졸</option>
						<option>대졸</option>
						<option>대학원졸</option>
					</select>
				</div>
			</div>
			<div class="row">
				<label class="col-md-4">좋아하는 색상</label>
				<div class="col-md-8">
					<div class="row form-group">
						<div class="col-md-2">
							<label for="red">빨강</label> <input type="radio" name="color"
								id="red" value="red" />
						</div>

						<div class="col-md-2 offset-md-1">
							<label for="blue">파랑</label> <input type="radio" name="color"
								id="blue" value="blue" />
						</div>

						<div class="col-md-2 offset-md-1">
							<label for="green">초록</label> <input type="radio" name="color"
								id="green" value="green" />
						</div>

						<div class="col-md-2 offset-md-1">
							<label for="yellow">노랑</label> <input type="radio" name="color"
								id="yellow" value="yellow" />
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<label class="col-md-4">좋아하는 음식(다중선택)</label>
				<div class="col-md-8">
					<div class="row form-group">
						<div class="col-md-2">
							<label for="food_1">짜장면</label> <input type="checkbox"
								name="food" id="food_1" value="짜장면" />
						</div>

						<div class="col-md-2">
							<label for="food_2">짬뽕</label> <input type="checkbox" name="food"
								id="food_2" value="짬뽕" />
						</div>

						<div class="col-md-2">
							<label for="food_3">탕수육</label> <input type="checkbox"
								name="food" id="food_3" value="탕수육" />
						</div>

						<div class="col-md-2">
							<label for="food_4">양장피</label> <input type="checkbox"
								name="food" id="food_4" value="양장피" />
						</div>

						<div class="col-md-2">
							<label for="food_5">팔보채</label> <input type="checkbox"
								name="food" id="food_5" value="팔보채" />
						</div>
					</div>
				</div>
			</div>
			<div class="row mt-4">
				<div class="col-md-3 offset-md-1 form-group">
					<input type="submit" class="btn btn-success form-control"
						value="수정완료" />
				</div>
				<div class="col-md-3 offset-md-1 form-group">
					<input type="reset" class="btn btn-danger form-control"
						value="수정취소" />
				</div>
				<div class="col-md-3 offset-md-1 form-group">
					<a href="PersonSelect.do" class="btn btn-info">개인성향 모든정보 보기</a>
				</div>
			</div>
		</form>
	</div>

</body>
</html>