/**
 *
 */

function myFunction() {
  window.alert("확인 버튼을 클릭하셨군요!");

  //일반적으로 window는 생략한다. \n은 줄바꿈을 의미한다.
  alert("좋은하루 \n행복한 하루 되세요!!");
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
