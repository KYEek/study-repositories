/**
 *
 */

$(document).ready(function () {
  $("div.answerCheck").hide();

  let radio_button = $("input[type = radio]");
  let checked_btn = -1;

  $("#btnOK").click(function () {
    $.each(radio_button, function (index, value) {
      if (value.checked) {
        checked_btn = index;
      }
    });

    if (checked_btn == -1) {
      alert("선택하세요");
      return;
    }

    if (checked_btn == 2) {
      $("div.no").hide();
      $("div.ok").show();
    } else {
      $("div.no").show();
      $("div.ok").hide();
    }
  });
  $("#btnRestart").click(function () {
    $("div.answerCheck").hide();
    $.each(radio_button, function (index, value) {
      value.checked = false;
    });
    checked_btn = -1;
  });
});
