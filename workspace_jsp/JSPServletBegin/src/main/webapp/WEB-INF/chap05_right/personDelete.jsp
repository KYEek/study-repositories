<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원삭제 성공시 메시지 보여주기</title>
<script type="text/javascript">
window.onload =function() {
	alert('회원번호 ${requestScope.psdto.seq}인 ${requestScope.psdto.name}님을 "삭제성공"했습니다');
	location.href='personSelect.do';	
}
</script>
</head>
<body>

</body>
</html>