$(document).ready(function () {
  $("div#div1 > img#fish1").click((e) => {
    $(e.target)
      .animate({ left: 600, top: 400 }, 3000)
      .fadeTo(2500, 0.1, func_myimh);
    //func_myimh는 콜백함수로서 fadeTo가 끝나면 실행된다.
  });
}); //end of ready-------------------------------------

function func_myimh() {
  //   alert("이미지를 클릭하셨습니다.");
  $("div#div1").html("<img src='images/kimthLarge.jpg'>");
}
