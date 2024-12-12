$(document).ready(function () {
  $("td#error").hide();

  //[충전결제하기]에 마우스를 올리거나 마우스를 빼면
  $("td#purchase").hover(
    function (e) {
      $(e.target).addClass("purchase");
    },
    function (e) {
      $(e.target).removeClass("purchase");
    }
  );

  //////////////////////////////////////////////
  $("input:radio[name='coinmoney']").bind("click", (e) => {
    const index = $("input:radio[name='coinmoney']").index($(e.target));
    // console.log("~~확인용 index",index);
    $("td>span").removeClass("stylePoint");
    $("td>span").eq(index).addClass("stylePoint");
    // $("td>span").eq(index); ==> $("td>span")중에 index 번째의 요소인 엘리먼트를 선택자로 보는 것이다.
    //                             $("td>span")은 마치 배열로 보면 된다. $("td>span").eq(index) 은 배열중에서 특정 요소를 끄집어 오는 것으로 보면 된다. 예를 들면 arr[i] 와 비슷한 뜻이다.
  }); //end of $("input:radio[name='coinmoney']").bind("click", e=>{});----------------
});

//충전결제하기를 클릭하면
function goCoinPayment(ctxPath, userid) {
  const checked_cnt = $("input:radio[name='coinmoney']:checked").length;
  if (checked_cnt == 0) {
    //결제금액을 선택하지 않았을 경우
    $("td#error").show();
    return;
  } else {
    //결제하러 들어간다.
    //충전금액
    const coinmoney = $("input:radio[name='coinmoney']:checked").val();
    // alert(`${coinmoney}원 결제하러 갑니다...`);
    /* === 팝업창에서 부모창 함수 호출 방법 3가지 ===
          1-1. 일반적인 방법
         opener.location.href = "javascript:부모창스크립트 함수명();";
                        
         1-2. 일반적인 방법
         window.opener.부모창스크립트 함수명();

         2. jQuery를 이용한 방법
         $(opener.location).attr("href", "javascript:부모창스크립트 함수명();");
      */
    opener.location.href = `javascript:goCoinPurchaseEnd("${ctxPath}", "${coinmoney}", "${userid}")`;
    self.close(); //  자신의 팝업창을 닫는 것이다
  }
} // end of function goCoinPayment(ctxPath, userid)-----------------
