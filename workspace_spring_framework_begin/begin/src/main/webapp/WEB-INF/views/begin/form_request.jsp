<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% String ctxPath = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>form 연습</title>

<script type="text/javascript" src="<%= ctxPath %>/resources/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("form[name='testFrm']").submit(function(){
			const no_val = $("input[name='no']").val();
			const name_val = $("input:text[name='name']").val();
			const reg_exp = /^[0-9]+$/;
			if(!reg_exp.text(no_val)) {
				alert("숫자로만 입력하세요");
				return false;//submit(전송)을 하지마랄는 뜻이다.
			}
			
			if(name_val.trim().length == 0) {
				alert("성명을 입력하세요");
				return false;//submit(전송)을 하지마랄는 뜻이다.
			}
			alert("전송성공");
		});
	});
</script>

</head>
<body>
<div>/test/form_request.action 페이지</div>
    <br>
    <form name ="testFrm" action="<%= ctxPath%>/test/form_request.action"  method="post">
	     번호 : <input type="text" name="no" /><br>
	     성명 : <input type="text" name="name" /><br>
       <input type="submit" value="확인"/>
       <input type="reset"  value="취소" /> 
    </form>
</body>
</html>