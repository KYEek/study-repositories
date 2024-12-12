/*
   웹브라우저에 HTML문서(documet)가 로드되고 나면 
   자동적으로 실행할 내용이 있으면 그 내용은 아래와 같이 기술해주면 된다.
*/
$(document).ready(function () {
  //$(function(){}); 와 같은 의미
  alert("안녕하세요");
  window.open(
    "./popup_contents.html",
    "my_popup",
    "left=100px, top=100px, width=400px, height = 350px"
  );

  $("span#test").html("안녕하세요<br>jQuery~~"); //$("span#test") 는 document.getElementById("test")와 같다.
  //.html()은 innerHTML과 같다.
  $("span.example").html("반갑습니다^^.<br>jQuery~~");
});
