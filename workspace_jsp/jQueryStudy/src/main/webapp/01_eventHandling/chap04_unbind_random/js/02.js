/**
 *
 */
$(document).ready(function () {
  $("div#result").hide();

  $("span.buttons").bind("click", function (e) {
    if ($(e.target).is("span.lucky")) {
      // == 자바스크립트에서 난수 발생시키기 ==
      /*
         Math.random(); 은 
         0 이상 1 미만의 랜덤한 실수가 발생되어진다.
         0 <= Math.random() < 1 
         
         == 1 부터 5까지 의 난수를 발생시키기 ==
            공식 :  Math.floor( Math.random()*(max-min+1) ) + min; 
          
          Math.random() 이 0 이 나왔다라면
          Math.floor( 0*(5-1+1) ) + 1;
          Math.floor( 0 ) + 1; ==> 0+1 ==> 1
          
          Math.random() 이 0.4 이 나왔다라면
          Math.floor( 0.4*(5-1+1) ) + 1;
          Math.floor( 2.0 ) + 1; ==> 2+1 ==> 3
          
          Math.random() 이 0.9 이 나왔다라면
          Math.floor( 0.9*(5-1+1) ) + 1;
          Math.floor( 4.5 ) + 1; ==> 4+1 ==> 5
      */
      const n_random = Math.floor(Math.random() * (5 - 1 + 1)) + 1;

      const s_user_choice = $(e.target).text();

      // if(Number(s_user_choice) === n_random){
      //또는
      if (s_user_choice === String(n_random)) {
      }
      //$(e.target)은 실제로 이벤트가 발생한 엘리먼트를 가리킨다.
      // 엘리먼트와 엘리먼트가 같은지 다른지 비교할때 is 를 사용한다.
      // 선택자1.is(선택자2) 은 선택자1과 선택자2가 동일한 엘리먼트를 가리키는 것이라면 true 이고,
      // 선택자1.is(선택자2) 은 선택자1과 선택자2가 동일한 엘리먼트를 가리키는 것이 아니라면 false 이다.

      $("div#result").html(
        `<span style ="color : blue;">당첨!! 축하합니다.</span>`
      );
    }
    $("div#result").show();

    $("div#firstdiv").empty();
    //선택자 $("div.firstdiv")에 대해서 empty()를 사용하여 자식 엘리먼트를 모두 제거한다.
    // $("span.buttons").unbind("click");
    //선택자 $("span.buttons") 에 대해서 click이벤트가 발생하지 않도록 unbind()를 사용한다.
  }); // end of $("span.buttons").bind("click", function(e) { ... });-------------------
}); // end of $(document).ready(function(){ ... });-------------------------------------
