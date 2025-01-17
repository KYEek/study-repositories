<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/begin/test/select_all_crud.action</title>

<style type="text/css">
	
	table {
	    width: 50%;
	}
	
	table, th, td {
		border: solid 1px gray;
		border-collapse: collapse;
	}
	
	th, td {
		height: 30px;
		text-align: center;
	}
	
	a { text-decoration: none;
	    color: blue;
	}
	
	a:hover{ color: red;}
</style>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
  $(document).ready(function(){
	
	  const func_all_view = function(){
		  
		  $.ajax({
			  url:`${pageContext.request.contextPath}/test/select_all_view.action`,
			  type:"get",
			  dataType:"json",
			  success:function(json){
			   // console.log(JSON.stringify(json));
				  /*
				    [{"NO":"6005","WRITEDAY":"2025-01-17 12:28:41","NAME":"육천오"}
				    ,{"NO":"6004","WRITEDAY":"2025-01-17 11:35:25","NAME":"육천사"}
				    ,{"NO":"6003","WRITEDAY":"2025-01-17 11:32:03","NAME":"육천삼"}
				    ,{"NO":"6002","WRITEDAY":"2025-01-17 11:12:06","NAME":"육천이"}
				    ,{"NO":"6001","WRITEDAY":"2025-01-17 10:48:12","NAME":"육천일"}
				    ,{"NO":"5009","WRITEDAY":"2025-01-17 10:43:38","NAME":"이진호"}
				    ,{"NO":"5008","WRITEDAY":"2025-01-17 10:43:30","NAME":"강이훈"}
				    ,{"NO":"5007","WRITEDAY":"2025-01-17 09:40:12","NAME":"오천칠"}
				    ,{"NO":"5006","WRITEDAY":"2025-01-16 14:42:12","NAME":"오천육"}
				    ,{"NO":"5005","WRITEDAY":"2025-01-16 14:30:42","NAME":"오천오"}
				    ,{"NO":"5004","WRITEDAY":"2025-01-15 00:00:00","NAME":"오천사"}
				    ,{"NO":"5003","WRITEDAY":"2025-01-16 12:19:29","NAME":"오천삼"}
				    ,{"NO":"5002","WRITEDAY":"2025-01-16 12:09:07","NAME":"오천이"}
				    ,{"NO":"5001","WRITEDAY":"2025-01-16 11:46:45","NAME":"오천일"}
				    ,{"NO":"103","WRITEDAY":"2025-01-15 17:20:54","NAME":"변우석"}
				    ,{"NO":"102","WRITEDAY":"2025-01-15 17:20:54","NAME":"김태희"}
				    ,{"NO":"101","WRITEDAY":"2025-01-15 16:49:51","NAME":"이순신"}] 
				  */
				  
				  let v_html = `<table>
									<tr>
										<th>번호</th>
										<th>입력번호</th>
										<th>성명</th>
										<th>작성일자</th>
										<th>조회</th>
										<th>삭제</th>
										<th>수정</th>
									</tr>`;
									
				   $.each(json, function(index, item){
					   v_html += `<tr>
					                 <td>\${index+1}</td>
					                 <td>\${item.NO}</td>
					                 <td>\${item.NAME}</td>
					                 <td>\${item.WRITEDAY}</td>
					                 <td><button type="button" class="read"   value="\${item.NO}">조회</button></td>
					                 <td><button type="button" class="delete" value="\${item.NO}">삭제</button></td>
					                 <td><button type="button" class="update" value="\${item.NO}">수정</button></td>
					              </tr>`;
				   }); 					
				
				   v_html += `</table>`;
				   
				   $("div#all_view").html(v_html);
			  },
			  error: function(request, status, error){
			     alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			  }
		  });
		  
	  };// end of const func_all_view = function(){}----------------------

	  
	  func_all_view();
	  
	  
	  // create 폼
	  $(document).on("click", "button#btn_create", function(e){
		  
		  const div_elmt = $(e.target).parent();
		  
		  div_elmt.html(`<form>
		                                  번호 : <input type="text" name="no" /><br> 
		                                  성명 : <input type="text" name="name" /><br>
		                   <input type="button" id="btn_create_ok" value="생성완료" />
		                   <input type="reset" value="취소" />
		                 </form>`);
	  });
	  
	  
	  // create
	  $(document).on("click", "input#btn_create_ok", function(e){
		  
		  const no = $("input:text[name='no']").val();
		  const name = $("input:text[name='name']").val();
		  
		  const reg_exp = /^[0-9]+$/;
			if( !reg_exp.test(no) ) {
				alert("숫자로만 입력하세요");
				return;
			}
			
			if(name.trim().length == 0) {
				alert("성명을 입력하세요");
				return;
			}
			
			const param = {"no":no, "name":name};
			
			$.ajax({
				url:`${pageContext.request.contextPath}/test/crud/create_update.action`,
				type:"post",
				data:JSON.stringify(param), 
			    // 전송하는 내용물의 타입은 json 형태를 띈 문자열임. 
			    // data 는 JSON.stringify(param)으로 했으므로 JSON 모양을 띄는 문자열(string)이다.
				
			    contentType:"application/json; charset=utf-8",  
			    /* 위에서 전송하는 내용물의 타입이 json 형태를 띈 문자열 이므로  "application/json; charset=utf-8" 을 꼭 해주어야 한다.
			             만약에 contentType 을 명기하지 않으면 "application/x-www-form-urlencoded" 이다. */
			    
				dataType:"json",
				success:function(json){
					console.log(JSON.stringify(json));
					// {"n":1}
					
					if(json.n == 1) {
						alert(`회원생성이 성공했습니다.`);
						func_all_view();
						$("div#div_create").html(`<button type="button" id="btn_create" style="width: 4%; height: 30px;">생성</button>`); 
					}
				},
				error: function(request, status, error){
				   alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
				}
			});
		  
	  });
	  
	  
	  // read
	  $(document).on("click", "button.read", function(e){
		  const no = $(e.target).val();
	  //  alert(`${pageContext.request.contextPath}/test/crud/\${no}.action`);
		  //   /begin/test/crud/7002.action
		  //   /begin/test/crud/7001.action
		  
		  $.ajax({
			  url:`${pageContext.request.contextPath}/test/crud/\${no}.action`,
			  type:"get",
			  dataType:"json",
			  success:function(json){
				  console.log(JSON.stringify(json));
				  // {"NO":"6005","WRITEDAY":"2025-01-17 12:28:41","NAME":"육천오"}
				  
				  let v_html = `<ol>
				                   <li>입력번호 : \${json.NO}</li>
				                   <li>성명 : \${json.NAME}</li>
				                   <li>작성일자 : \${json.WRITEDAY}</li>
				                </ol>`;
				                
				  $("div#one_view").html(v_html);              
			  },
			  error: function(request, status, error){
			     alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			  }
		  });
		  
	  });
	  
	
  });// end of $(document).ready(function(){})----------------------------
    
</script>
	
</head>
<body>

    <h2>REST-API 연습</h2>
	<h3>오라클 서버에 있는 데이터 조회</h3> 
	<div id="all_view"></div>
	<div style="margin: 2% 0;" id="div_create">
		<button type="button" id="btn_create" style="width: 4%; height: 30px;">생성</button>
	</div>
	<div style="margin: 2% 0;" id="div_update"></div>
	<div id="one_view"></div>
    
</body>
</html>