/**
 *
 */

$(document).ready(function () {
  $("div.ok").html("<img src = 'images/ok.png'>");
  $("div.no").html("<img src = 'images/no.png'>");
  $("div.answerCheck").hide(); //div.answerCheck를 숨긴다.
  $("input#answer").focus();

  //"정답확인" 버튼을 클릭했을 경우
  $("button#btnOK").bind("click", function () {
    const user_answer = $("input#answer").val().trim();

    if (user_answer == "") {
      if (confirm("정답을 미기입 하셨는데 정말로 진행하시겠습니까?")) {
        $("div.no").show();
      } else {
        $("input#answer").val("").focus();
      }
    } else {
      if (user_answer == 23) {
        $("div.no").hide();
        $("div.ok").show();
      } else {
        $("div.ok").hide();
        $("div.no").show();
      }
      3;
    }
  });

  $("button#btnRestart").bind("click", function () {
    $("input#answer").val("").focus();
    $("div.answerCheck").hide();
  });
});
