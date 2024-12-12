/**
 *
 */

$(document).ready(function () {
  //== submit 에 대한 이벤트 처리=== //
  $("form[name='registerFrm']").submit(() => {
    // --- 유효성 검사가 필요하다 --- //
    //1. 성명은 필수입력 검사
    const name_lehgth = $("input:text[name='name']").val().trim().length;
    if (name_lehgth == 0) {
      alert("성명을 입력하세요!!");
      $("input:text[name='name']").val("").focus();
      return false;
    }
    //2. 학력도 필수입력 검사
    const school_val = $("select[name='school']").val();
    if (school_val == "선택하세요") {
      alert("학력을 선택하세요!!");
      $("select[name='school']").focus();
      return false;
    }
    //3. 색상도 필수입력 검사
    const color_checked_length = $("input:radio[name='color']:checked").length;
    if (color_checked_length == 0) {
      alert("색상을 선택하세요!!");
      return false;
    }
    //4. 음식도 필수입력 검사
    const food_checked_length = $("input:checkbox[name='food']:checked").length;
    if (food_checked_length == 0) {
      alert("선호하는 음식을 최소한 1개 이상 선택하세요!!");
      return false;
    }
  }); //end of submit ----------------------------
});
