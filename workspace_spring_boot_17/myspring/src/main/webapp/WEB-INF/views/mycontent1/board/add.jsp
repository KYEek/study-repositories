<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
   String ctxPath = request.getContextPath();
    //     /myspring
%>

<jsp:include page="../../header/header1.jsp" /> 

<style type="text/css">

</style>

<script type="text/javascript">
	$(document).ready(function() {
		<%-- === 스마트 에디터 구현 시작 === --%>
		//전역변수
	    var obj = [];
	    
	    //스마트에디터 프레임생성
	    nhn.husky.EZCreator.createInIFrame({
	        oAppRef: obj,
	        elPlaceHolder: "content",
	        sSkinURI: "<%= ctxPath%>/smarteditor/SmartEditor2Skin.html",
	        htParams : {
	            // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
	            bUseToolbar : true,            
	            // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
	            bUseVerticalResizer : true,    
	            // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
	            bUseModeChanger : true,
	        }
	    });
		
	    //글쓰기 버튼
	    $("button#btnWrite").click(function(){
	    	<%-- === 스마트 에디터 구현 시작 === --%>
	         // id가 content인 textarea에 에디터에서 대입
	         obj.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
	         <%-- === 스마트 에디터 구현 끝 === --%>
	         
	         // === 글제목 유효성 검사 ===
        	 const subject = $("input:password[name='subject']").val();
	         if(subject == "") {
				alert("글제목을 입력하세요😡");
				$("input:password[name='subject']").val("");
				return;
	         }
	         
	         
	         //글내용 유효성 검사(스마트 에디터를 사용할 경우)
	         let content_val = $("textarea[name='content']").val().trim();
	         
	         //alert(content_val); 공백 확인용
	         //<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;</p>라고 나온다
	         
	         //공백(&nbsp;)을 ""으로 변환
	         content_val = content_val.replace(/&nbsp;/gi, ""); //공백을 ""로 변환
	         /*    
	               대상문자열.replace(/찾을 문자열/gi, "변경할 문자열");
	           ==> 여기서 꼭 알아야 될 점은 나누기(/)표시안에 넣는 찾을 문자열의 따옴표는 없어야 한다는 점입니다. 
	                       그리고 뒤의 gi는 다음을 의미합니다.
	        
	           g : 전체 모든 문자열을 변경 global
	           i : 영문 대소문자를 무시, 모두 일치하는 패턴 검색 ignore
        	*/
	         //alert(content_val);
	         //<p>      </p>
	         content_val = content_val.substring(content_val.indexOf("<p>")+3);
	         
	         
	         
	         content_val = content_val.substring(0, content_val.indexOf("</p>"));
	         
	         //alert(content_val);
	         
	         if(content_val.trim().length ==0) {
	        	 alert("글 내용을 입력하세요!!😡");
		         return; //종료
	         }
	         
	         // === 글암호 유효성 검사 ===
        	 const pw = $("input:password[name='pw']").val();
	         if(pw == "") {
				alert("글암호를 입력하세요😡");
				return;
	         }
	         
	         //폼을 전송
	         const frm = document.addFrm;
	         frm.method = "POST";
	         frm.action = "<%= ctxPath%>/board/add";
	         frm.submit();
	    });
	    
	    
	    
	    <%-- === 스마트 에디터 구현 끝 === --%>
	});// end of $(document).ready(function(){})-----------
</script>

<div style="display:flex;">
	<div style="margin: auto; padding-left: 3%;">
	<form name="addFrm">
		<table style="width: 1024px" class="table table-bordered">
	         <tr>
	            <th style="width: 15%; background-color: #DDDDDD;">성명</th>
	            <td>
	            	<input type="hidden" name="fk_userid" value="${sessionScope.loginuser.userid}"/>
	            	<input type="text" name="name" value="${sessionScope.loginuser.name}" readonly/>
	            </td>
	        </tr>
	        <tr>
	        	<th style="width: 15%; background-color: #DDDDDD;">제목</th>
	        	<td>
	        		<input type="text" name="subject" size="100" maxlength="200" />
	        	</td>
	        </tr>
	        <tr>
            <th style="width: 15%; background-color: #DDDDDD;">내용</th> 
            <td>
                <textarea style="width: 100%; height: 612px;" name="content" id="content"></textarea>
            </td>
         </tr>
         <tr>
            <th style="width: 15%; background-color: #DDDDDD;">글암호</th> 
            <td>
                <input type="password" name="pw" maxlength="20" />
            </td>
         </tr>
        </table>
        <div style="margin: 20px;">
            <button type="button" class="btn btn-secondary btn-sm mr-3" id="btnWrite">글쓰기</button>
            <button type="button" class="btn btn-secondary btn-sm" onclick="javascript:history.back()">취소</button>  
        </div>
	</form>
	</div>
</div>

<jsp:include page="../../footer/footer1.jsp" />