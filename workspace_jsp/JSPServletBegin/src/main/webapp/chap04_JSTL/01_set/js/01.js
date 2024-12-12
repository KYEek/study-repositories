/**
 * 
 */

function goSubmit() {
	
	const regExp = /^[0-9]{1,5}$/;
	const frm = document.myFrm;
	const num1 = frm.firstNum.value.trim();
	const num2 = frm.secondNum.value.trim();
	
	if(!(regExp.test(num1) && regExp.test(num2))) {
		alert("숫자로만 입력하세요!!");
		frm.firstNum.value = "";
		frm.secondNum.value = "";
		frm.firstNum.focus();
		return;
	}
	
	frm.action = "numtiply_result_02.jsp";
	//frm.method = "get";  //method 를 명기하지 않으면 기본은 "get"이다
	frm.submit();
}
