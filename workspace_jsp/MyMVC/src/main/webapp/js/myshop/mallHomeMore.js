$(document).ready(function () {
  // HIT상품 게시물을 더보기 위하여 "더보기..." 버튼 클릭액션에 대한 초기값 호출하기
  // 즉, 맨처음에는 "더보기..." 버튼을 클릭하지 않더라도 클릭한 것 처럼 8개의 HIT상품을 게시해주어야 한다는 말이다.

  displayHIT("1");
}); //end of DOMContentLoaded--------------------------

//function declation

let lenHIT = 8;
// HIT 상품 "더보기..." 버튼을 클릭할때 보여줄 상품의 개수(단위)크기

function displayHIT(start) {
  // start가  1 이라면   1~ 8  까지 상품 8개를 보여준다.
  // start가  9 이라면   9~ 16  까지 상품 8개를 보여준다.
  //...
  $.ajax({
    url: "mallDisplayJSON.up",
    // type: "get",
    data: { sname: "HIT", start: start, len: lenHIT },
    dataType: "json",
    success: function (json) {},
    error: function (request, status, error) {
      alert(
        "code: " +
          request.status +
          "\n" +
          "message: " +
          request.responseText +
          "\n" +
          "error: " +
          error
      );
    },
  });
} //end of displayHIT-------------------------
