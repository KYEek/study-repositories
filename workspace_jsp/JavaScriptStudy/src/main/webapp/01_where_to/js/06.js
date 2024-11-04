/**
 *
 */

// 한줄주석
/*
      여러줄 주석
     */

// window.onload는 브라우저에 페이지 로딩이 끝나면 실행되는 이벤트입니다.
//자동적으로 호출되어야할 명령은 function(){}안에 작성합니다.
window.onload = function () {
  document.getElementById("h1").innerHTML =
    "<span style='color: red; font-weight: bold;'>h1태그입니다</span>";
};

//자바스크립트에서 문자열을 사용할 때는 큰따옴표나 작은따옴표를 사용합니다.
document.getElementById("h1").innerHTML =
  "<span style='color: red; font-weight: bold;'>h1태그입니다</span>";

function myFunction() {
  document.getElementById("h1").innerHTML =
    "<span style='color: green; font-weight: bold;'>h1태그입니다</span>";
  document.getElementById("demo").innerHTML =
    "p태그의 <span style='color: red; font-weight: bold;'>내용이 변경</span> 되었습니다.";
}
