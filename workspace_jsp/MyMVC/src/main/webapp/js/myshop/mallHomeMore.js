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
    success: function (json) {
      // console.log(json);
      let v_html = ``;
      if (start == "1" && json.length == 0) {
        //처음부터 데이터가 존재하지 않는 경우
        /* !!!! 주의 !!!!
                    if(start == "1" && json == null) 이 아님!!!
                    if(start == "1" && json.length) 로 해야 함!!!
                */

        v_html = `현재 상품 준비중 입니다...`;
        $("div#displayHIT").html(v_html);
      } else if (json.length > 0) {
        $.each(json, function (inde, item) {
          v_html += `<div class='col-md-6 col-lg-3'>
          <div class='card mb-3'>
          <div class='card-body' style='padding: 0; font-size: 11pt;'>
                                        <ul class='list-unstyled mt-3 pl-3'>
                                            <li><label class='prodInfo'>제품명</label>${
                                              item.pname
                                            }</li>
                                            <li><label class='prodInfo'>정가</label><span style='text-decoration: line-through; color: red;'>${item.price.toLocaleString(
                                              "en"
                                            )} 원</span></li>
                                            <li><label class='prodInfo'>판매가</label><span style='font-weight: bold; color: red;'>${item.saleprice.toLocaleString(
                                              "en"
                                            )} 원</span></li>
                                            <li><label class='prodInfo'>할인율</label><span style='font-weight: bold; color: blue;'>[${
                                              item.discountPercent
                                            }%]할인</span></li>
                                            <li><label class='prodInfo'>포인트</label><span style='font-weight: bold; color: orange;'>${item.point.toLocaleString(
                                              "en"
                                            )} POINT</span></li>
                                            <li class='text-center'><a href='/MyMVC/shop/prodView.up?pnum=${
                                              item.pnum
                                            }' class='btn btn-sm btn-outline-dark stretched-link' role='button'>자세히보기</a></li>
                                            ${
                                              "" /* 카드 내부의 링크에 .stretched-link 클래스를 추가하면 전체 카드를 클릭할 수 있고 호버링할 수 있습니다(카드가 링크 역할을 함). */
                                            }  
                                        </ul>
                                    </div>
          <img src='/MyMVC/images/${
            item.pimage1
          }' class='card-img-top' style='width: 100%' />
          </div>
          </div>`;
        }); //end of each ------------------------------

        $("div#displayHIT").append(v_html);
        // !!!!! 중요 !!!!!
        // 더보기... 버튼의 value 속성에 값을 지정하기
        $("button#btnMoreHIT").val(Number(start) + lenHIT);
        // 더보기... 버튼의 value 값이  "9" 로 변경된다.
        // span#countHIT 에 지금까지 출력된 상품의 개수를 누적해서 기록한다.
        $("span#countHIT").text() + json.length;

        displayHIT("1");

        // HIT 상품 게시물을 더보기 위하여 "더보기..." 버튼 클릭액션 이벤트 등록하기
        $("button#btnMoreHIT").click(function () {
          displayHIT($(this).val());
          // displayHIT("9");   첫번째로 더보기를 클릭한 경우
        });
      }
    },
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
