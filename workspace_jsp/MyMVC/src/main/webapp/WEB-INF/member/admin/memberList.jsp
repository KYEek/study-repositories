<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../../header2.jsp" />

모든회원 회원조회
<script type="text/javascript">
	$(document).ready(function() {
		
		$("select[name='sizePerPage']").val("${requestScope.sizePerPage}");
		$("input:text[name='searchWord']").on("keydown", function (e){
			if(e.keyCode ==13) {}
			goSearch();
		});
		
	$("select[name='sizePerPage']").bind("change",function(){
		const frm = document.member_search_frm;
// 		frm.action = "memberList.up";
		frm.submit();
	};)
	});
	
	function goSearch() {
		const searchType = $("select[name='searchType']").val();
		
		if(searchType=="") {
			alert("검색대상을 선택하세요!!");
			return;
		}
		
		const frm = document.member_search_frm;
// 		frm.action = "memberList.up";
		frm.submit();

	}//end of function goSearch() {}--------------------
</script>

<div class="container" style="">
	<h2 class="text-center; mb-5">회원 전체 목록록</h2>
	<form name="member_search_frm">
		<select name="searchType">
			<option value="">검색대상</option>
			<option value="name">회원명</option>
			<option value="userid">아이디</option>
			<option value="email">이메일</option>
		</select> &nbsp; <input type="text" name="searchWord">
		<%--
             form 태그내에서 데이터를 전송해야 할 input 태그가 만약에 1개 밖에 없을 경우에는
             input 태그내에 값을 넣고나서 그냥 엔터를 해버리면 submit 되어져 버린다.
             그래서 유효성 검사를 거친후 submit 을 하고 싶어도 input 태그가 만약에 1개 밖에 없을 경우라면 
             유효성검사가 있더라도 유효성검사를 거치지 않고 바로 submit 되어진다. 
             이것을 막으려면 input 태그가 2개 이상 존재하면 된다.  
             그런데 실제 화면에 보여질 input 태그는 1개 이어야 한다.
             이럴 경우 아래와 같이 해주면 된다. 
             또한 form 태그에 action 이 명기되지 않았으면 현재보이는 URL 경로로 submit 되어진다.   
        --%>
        <input type="text" style="display: none;" /> <%-- 조심할 것은 type="hidden" 이 아니다. --%>
		<button type="button" class="btn btn-secondary" onclick="goSearch()">검색</button>

		<span style="font-size: 12pt; font-weight: bold;">페이지당
			회원명수&nbsp;-&nbsp;</span> <select name="sizePerPage">
			<option value="10">10명</option>
			<option value="5">5명</option>
			<option value="3">3명</option>
		</select>
	</form>
	<table class="table table-bordered" id="memberTbl">
		<thead>
			<tr>
				<th>번호</th>
				<th>아이디</th>
				<th>회원명</th>
				<th>이메일</th>
				<th>성별</th>
			</tr>
		</thead>

		<tbody>
			<c:if test="${not empty requestScope.memberList}">
			<c:forEach var="membervo" items="${requestScope.membeList}"></c:forEach>
				<c:forEach  
					varstatus="status">
					<tr>
						<td>${status.count}</td>
						<td>${membervo.user_id}</td>
						<td>${membervo.membername}</td>
						<td>${membervo.email}</td>
						<td>${membervo.gender}</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty requestScope.memberList}">
				<tr>
					<td colspan="5">데이터가 존재하지 않습니다.</td>
				</tr>
			</c:if>
		</tbody>
	</table>
</div>
<jsp:include page="../../footer2.jsp" />