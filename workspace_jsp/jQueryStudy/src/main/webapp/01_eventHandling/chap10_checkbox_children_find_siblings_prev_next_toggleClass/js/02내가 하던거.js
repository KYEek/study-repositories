/**
 *
 */

$(document).ready(function () {
  //   $("#firstDiv span.button > label").click(function () {
  //     alert("label clicked");
  //   });

  $("#firstDiv")
    .find("span")
    .click(function (e) {
      //   alert("label clicked");
      /*   
       선택자.toggleClass("클래스명1");
         ==> 이것은 선택자에 "클래스명1" 이 이미 적용되어 있으면 선택자에 "클래스명1" 을 제거해주고, 
             만약에 선택자에 "클래스명1" 이 적용되어 있지 않으면 선택자에 "클래스명1" 을 추가해주는 것.
             
         한마디로 addClass("클래스명1") 와 removeClass("클래스명1") 를 합친것 이라고 보면 된다.     
     */
      $(e.target).toggleClass("changeCSSname");
    }); // end of click()-------------------

  // 체크박스 전체선택 전체해제 시작

  /*   
   $("div#firstDiv input:checkbox[id='checkall']").click ( (e) => {
        alert("확인용1");
   });

   $("div#firstDiv input#checkall]").click ( (e) => {
        alert("확인용2");
   });

   $("div#firstDiv").find("input#checkall").click ( (e) => {
        alert("확인용3");
   });
 */

  $("input#checkall").click(function (e) {
    // console.log($(e.target).prop("checked"));
    $("span.buttons > input:checkbox").prop(
      "checked",
      $(e.target).prop("checked")
    );
    if ($(e.target).prop("checked")) {
      $("span.buttons > label").addClass("changeCSSname");
    } else {
      $("span.buttons > label").removeClass("changeCSSname");
    }
  }); // end of click()-------------------

  // let b_all_checked = true;
  // $("div#firstDiv input:checkbox[name ='person']").click(function (e) {
  //   const bool = $(e.target).prop("checked");
  //   if (bool) {
  //     // 선택자.prev() 는 선택자의 바로 앞의 형제요소(형제태그)를 가리키는 것이다.
  //     $(e.target).prev().addClass("changeCSSname");
  //   } else {
  //     $(e.target).prev().removeClass("changeCSSname");

  //     $("input#checkall").prop("checked", false);
  //     $("label[for='checkall']").removeClass("changeCSSname");
  //     b_all_checked = true;
  //     const person_button = $("input[name='person']");
  //     person_button.each(function (index, item) {
  //       const b_checked = $(item).prop("checked");
  //       console.log("b_checked: ", b_checked);
  //       if (!b_checked) {
  //         b_all_checked = false;
  //         return false;
  //       }
  //     });
  //   }
  //   console.log("b_all_checked: ", b_all_checked);
  //   $("input#checkall").prop("checked", b_all_checked);
  // });
  //

  $("div#firstDiv input:checkbox[name ='person']").click(function (e) {
    const bool = $(e.target).prop("checked");
    if (bool) {
      // 선택자.prev() 는 선택자의 바로 앞의 형제요소(형제태그)를 가리키는 것이다.
      $(e.target).prev().addClass("changeCSSname");
    } else {
      let b_all_checked = true;
      $(e.target).prev().removeClass("changeCSSname");

      $("input#checkall").prop("checked", false);
      $("label[for='checkall']").removeClass("changeCSSname");
      const person_button = $("input[name='person'] :checked");
      if (person_button.length == 0) {
        b_all_checked = false;
      } else if (person_button.length == $("input[name='person']").length) {
        b_all_checked = true;
        $("label[for='checkall']").addClass("changeCSSname");
      }

      console.log("b_all_checked: ", b_all_checked);
      $("input#checkall").prop("checked", b_all_checked);
    }
  });
});
