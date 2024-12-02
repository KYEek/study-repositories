<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
	href="<%=ctxPath%>/chap05_css_js_images/css/PersonDetail.css"
	type="text/css" />
<!-- Optional JavaScript -->
<script src="<%=ctxPath%>/js/jquery-3.7.1.min.js" type="text/javascript"></script>
<script
	src="<%=ctxPath%>/bootstrap-4.6.2-dist/js/bootstrap.bundle.min.js"
	type="text/javascript"></script>
<script src="<%=ctxPath%>/chap05_css_js_images/js/personSelectAll.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(
			function() {
				$("span#colorbox").css('background-color',
						'${requestScope.psdto.color}');
				// 		$("span#colorbox").css('background-color':'${requestScope.psdto.color}');
			});//end of $(document).ready(function()--------------------------------
</script>

<title>개별 회원의 성향</title>
</head>
<body class="py-3">
	<div class="container">
		<div class="card">
			<div class="card-header">
				<%-- .card-header 에는 카드 머리말이 들어갑니다. --%>
				<p class="h2 text-center">
					<span class="text-primary">${requestScope.psdto.name}</span>&nbsp;<small>님의
						개인성향 결과</small>
				</p>
			</div>
			<div class="card-body">
				<%-- .card-body 에는 카드 내용이 들어갑니다. --%>
				<div
					class="row border border-top-0 border-left-0 border-right-0 mx-4 my-3">
					<div class="col-md-3 py-2">회원번호</div>
					<div class="col-md-9 py-2">
						<span class="h5">${requestScope.psdto.seq}</span>
					</div>
				</div>

				<div
					class="row border border-top-0 border-left-0 border-right-0 mx-4 my-3">
					<div class="col-md-3 py-2">성명</div>
					<div class="col-md-9 py-2">
						<span class="h5">${requestScope.psdto.name}</span>
					</div>
				</div>

				<div
					class="row border border-top-0 border-left-0 border-right-0 mx-4 my-3">
					<div class="col-md-3 py-2">학력</div>
					<div class="col-md-9 py-2">
						<span class="h5">${requestScope.psdto.school}</span>
					</div>
				</div>
				<div
					class="row border border-top-0 border-left-0 border-right-0 mx-4 my-3">
					<div class="col-md-3 py-2">색상</div>
					<div class="col-md-9 py-2">
						<c:choose>
							<c:when test="${requestScope.psdto.color eq 'red' }">
								<span class="h5">빨강</span>
							</c:when>
							<c:when test="${requestScope.psdto.color eq 'blue' }">
								<span class="h5">파랑</span>
							</c:when>
							<c:when test="${requestScope.psdto.color eq 'green' }">
								<span class="h5">초록</span>
							</c:when>
							<c:when test="${requestScope.psdto.color eq 'yellow' }">
								<span class="h5">노랑</span>
							</c:when>
							<c:otherwise></c:otherwise>
						</c:choose>
						<span id="colorbox" class="rounded-circle"></span>
					</div>

				</div>
				<div
					class="row border border-top-0 border-left-0 border-right-0 mx-4 my-3">
					<div class="col-md-3 py-2">음식</div>
					<div class="col-md-9 py-2">
						<span class="h5">${requestScope.psdto.strFood}</span>
					</div>
				</div>
				<div
					class="row border border-top-0 border-left-0 border-right-0 mx-4 my-3">
					<div class="col-md-3 py-2">이미지 파일명</div>
					<div class="col-md-9 py-2">
						<span class="h5">${requestScope.psdto.strFood_imgFileName}</span>
					</div>
				</div>
				<div
					class="row border border-top-0 border-left-0 border-right-0 mx-4 my-3">
					<div class="col-md-3 py-2">음식사진</div>
					<div class="col-md-9 py-2">
						<c:if test="${not empty requestScope.psdto.strFood_imgFileName}">
							<c:forTokens var="imgFileName"
								items="${requestScope.psdto.strFood_imgFileName}" delims=",">
								<img
									src="<%=ctxPath%>/chap03_StandardAction/images/${imgFileName}"
									width="130" class="img-fluid rounded mr-1" />
							</c:forTokens>
						</c:if>
						<c:if test="${empty requestScope.psdto.strFood_imgFileName}">
							<span class="h5">좋아하는 음식이 없습니다.</span>
						</c:if>
					</div>
				</div>
			</div>
			<div class="card-footer pt-4">
				<%-- .card-footer 에는 카드 꼬리말이 들어갑니다. --%>
				<div class="row">
					<div class="col-md-3">
					<p class="text-center">
						<a href="PersonSelect.do" class="btn btn-info">목록보기1</a>
					</p>
				</div>
				<div class="col-md-3">
					<p class="text-center">
						<button type="button" class="btn btn-info"
							onclick="javascript:location.href='PersonSelect.do'">목록보기2</button>
					</p>
				</div>
				<div class="col-md-3">
					<p class="text-center">
						<button type="button" class="btn btn-danger"
							onclick = "deletePerson('${requestScope.psdto.seq}', '${requestScope.psdto.name}')">삭제하기</button>
							<!--deletePerson('superman') 홑따옴표 꼭 넣어라
							"/personDelete.do
							POST 가능
							confirm ==> 정말로 회원번호 2인 이순신님을 삭제하시겠습니까?
							delete from tbl_person_intrest where
							  -->
					</p>
				</div>
				<div class="col-md-3">
					<p class="text-center">
						<button type="button" class="btn btn-primary"
							onclick= "updatePerson('${requestScope.psdto.seq}', '${requestScope.psdto.name}')">내정보 수정하기</button>
					</p>
				</div>
			</div>
		</div>
	</div>
	<form name="seqFrm">
		<input type="hidden" name="seq"/>
	</form>
</body>
</html>