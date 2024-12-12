/**
 * 
 */

$(document).ready(function() {

	$("tbody > tr").click((e) => {
		const seq = $(e.target).parent().find("span").text();
		console.log("확인용 seq=> ", seq);

		// !!!! 암기 !!!! //
		// 자바스크립트에서 URL 페이지 이동은 location.href="이동하고자 하는 URL주소";이다
		//		location.href="personDetail.do?seq="+ seq;
		//POST 방식으로 하려면 아래와 같이 하면 된다.
		const frm = document.seqFrm;
		frm.seq.value = seq;
		frm.action = "personDetail.do";
		frm.method = "post";
		frm.submit();

	});// end of $("tbody > tr").click((e)------------------------------

});//end of ready----------------------------------------------------

function deletePerson(personNum, name) {
	if (confirm("정말로 회원번호 " + personNum + "인 "+name+"님을 삭제하겠습니까?")) {
		const frm = document.seqFrm;
		frm.seq.value = personNum;
		console.log(frm.seq.value);
		frm.action = "PersonDelete.do";
		frm.method = "post";
		frm.submit();
	}
	else {
		alert("삭제 취소");
		return;
	}

}


function updatePerson(personNum, name) {
		const frm = document.seqFrm;
		frm.seq.value = personNum;
		console.log(frm.seq.value);
		frm.action = "PersonUpdate.do";
		frm.method = "post";
		frm.submit();

}