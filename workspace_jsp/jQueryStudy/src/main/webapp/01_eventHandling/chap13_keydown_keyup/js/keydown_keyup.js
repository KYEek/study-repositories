$(document).ready(function () {
  // Your code here
  $("input:text[id='text1']").keydown((e) => {
    $("input:text[id='text2']").val($(e.target).val());
  });
  $("input:text[id='text3']").keyup((e) => {
    $("input:text[id='text4']").val($(e.target).val());
  });
});
