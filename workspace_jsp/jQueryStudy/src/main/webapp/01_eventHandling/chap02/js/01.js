/**
 *
 */
$(function () {
  $("span.bold").bind("click", function () {
    //bind는 addEventListener와 같다.
    $("div#result").html(
      "<span id ='result_bold'> " +
        $(this).text() +
        "버튼을 클릭하셨군요</span>"
    ); //$(thid)는 이벤트가 발생한 객체를 의미한다.
    //$(this).text()는 이벤트가 발생한 객체의 텍스트를 의미한다.
  });
  $("span.italic").bind("click", function () {
    $("div#result").html(
      "<span id ='result_italic'> " +
        $(this).text() +
        "버튼을 클릭하셨군요</span>"
    );
  });
  $("span.clear").bind("click", function () {
    $("div#result").html("");
  });
});
