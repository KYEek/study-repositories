<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String ctxPath = request.getContextPath();
//    /MyMVC
%>

<%-- Required meta tags --%>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<%-- Bootstrap CSS --%>
<link rel="stylesheet" type="text/css"
	href="<%=ctxPath%>/bootstrap-4.6.2-dist/css/bootstrap.min.css">

<%-- Font Awesome 6 Icons --%>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.1/css/all.min.css">

<%-- Optional JavaScript --%>
<script type="text/javascript" src="<%=ctxPath%>/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript"
	src="<%=ctxPath%>/bootstrap-4.6.2-dist/js/bootstrap.bundle.min.js"></script>


<script type="text/javascript">
	$(document).ready(function() {
		const method = "${requestScope.method}";
		
		if(method == "GET") {
			$("#div_findResult").hide();
		}
		if(method == "POST") {
			$("input:text[name='name']").val("${requestScope.name}");
			$("input:text[name='email']").val("${requestScope.email}");
		}
		$("button.btn-success").click(function(){
			alert("입력");
			goFind();
		});// end of click
		$("input:text[name='email']").on("keyup", function(e){
			if(e.keyCode == 13) {
				goFind();
			}
		}); // end of keyup
	});//end of document ready
	
	
	function goFind(){
		if(name=="") {
			alert("성명을 입력하세요!!");
			return;
		}
		
		const email = $("input:text[name='email']").val();
	     
		   // const regExp_email = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;  
		   // 또는
		      const regExp_email = new RegExp(/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i);  
		     // 이메일 정규표현식 객체 생성 
		         
		      const bool = regExp_email.test(email);
		 
		      if(!bool) {
		         // 이메일이 정규표현식에 위배된 경우
		         alert("e메일을 올바르게 입력하세요!!");
		       return; // 종료
		      }    
		      
		      const frm = document.idFindFrm;
		      frm.action = "<%=ctxPath%>/login/idFind.up";
		      frm.method = "post";
		      frm.submit();
			
	}
	//아이디 찾기에 있는거 초기화 시키기
	function func_form_reset_empty(){
		document.querySelector("form[name='idFindFrm']").reset();
		$("div#div_findResult").empty();
	}// end of from reset
	
	// == 아이디 찾기에서 close 버튼을 클릭하면 iframe 의 form 태그에 입력된 값을 지우기 == //
	$("button.idFindClose").click(function(){
		const iframe_idFind = document.getElementById("iframe_idFind"); 
        // 대상 아이프레임을 선택한다.
        <%-- 선택자를 잡을때 jQuery를 사용한 ${} 으로 잡으면 안되고, 순수한 자바스크립트를 사용하여 선택자를 잡아야 한다. --%> 
        <%-- .jsp 파일속에 주석문을 만들때 ${} 을 넣고자 한다라면 반드시 JSP 주석문으로 해야 하지, 스크립트 주석문으로 해주면 ${} 때문에 오류가 발생한다. --%>
        const iframe_window = iframe_idFind.contentWindow;
        // iframe 요소에 접근하는 contentWindow 와 contentDocument 의 차이점은 아래와 같다.
        // contentWindow 와 contentDocument 둘 모두 iframe 하위 요소에 접근 할 수 있는 방법이다.
        // contentWindow 는 iframe의 window(전체)을 의미하는 것이다.
        // 참고로, contentWindow.document 은 contentDocument 와 같은 것이다.
        // contentWindow 가 contentDocument 의 상위 요소이다.
        iframe_window.func_form_reset_empty()	;
     // func_form_reset_empty() 함수는 idFind.jsp 파일에 정의해 둠.
	})//end of idFindCLose click
	
</script>

<form name="idFindFrm">

	<ul style="list-style-type: none;">
		<li style="margin: 25px 0"><label
			style="display: inline-block; width: 90px;">성명</label> <input
			type="text" name="name" size="25" autocomplete="off" /></li>
		<li style="margin: 25px 0"><label
			style="display: inline-block; width: 90px;">이메일</label> <input
			type="text" name="email" size="25" autocomplete="off" /></li>
	</ul>

	<div class="my-3 text-center">
		<button type="button" class="btn btn-success">찾기</button>
	</div>

</form>

<div class="my-3 text-center" id="div_findResult">
	ID : <span style="color: red; font-size: 16pt; font-weight: bold;">${requestScope.userid }</span>
</div>