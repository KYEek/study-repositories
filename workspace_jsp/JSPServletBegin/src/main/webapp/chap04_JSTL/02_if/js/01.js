/**
 * 
 */

$(document).ready(function() {
//	$("form[name=myFrm").submit(function(){});
//	또는
//	$("form[name=myFrm").submit(()=>{});
	$("form[name=myFrm").submit(() => {
		const firstVal = $("input:text[name='first']").val().trim();
		const secondVal = $("input:text[name='second']").val().trim();
		if (firstVal == "" || secondVal == "") {
			alert("문자를 입력하세요!!");
			return false; //sumbit을 하지 않는다.
		}
	});//end of submit-------------------------------
	
});