/**
 *
 */

function myFunction() {
  console.log("확인 버튼을 클릭하셨군요!");
  // console.log는 크롬 웹브라우저에서 키보드 F12(개발자도구)를 누르면 콘솔창에서 확인할 수 있다.
  document.getElementById("name").innerHTML = "연규영";
}

function myClear() {
  const isClear = confirm("정말로 지우시겠습니까?");
  // 일반적으로 window는 생략한다.
  // isclrar 변수에는 true 또는 false가 들어가게 된다.
  if (isClear) {
    document.getElementById("name").innerHTML = "";
  } else {
    alert("지우기를 취소하셨습니다.");
  }
}
