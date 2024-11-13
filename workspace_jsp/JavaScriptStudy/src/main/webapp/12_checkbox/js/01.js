/**
 *
 */

// == 체크박스 여러개중 라디오 처럼 1개만 선택되도록 만들기 ==
function onlyOneCheck(obj) {
  const checkbox_list = document.querySelectorAll("input[name='product_old']");
  for (let checkbox of checkbox_list) {
    if (checkbox !== obj) {
      // checkbox != obj 은 체크박스에 체크를 하지 않은 나머지 모든 체크박스를 말한다.
      checkbox.checked = false;
    }
  }
} // end of onlyOneCheck() -----------------------------

// == 체크박스 전체 선택/해제 ==
function func_allCheck(checked) {
  checkbox_list = document.querySelectorAll("input[name='product_usa']");

  for (let checkbox of checkbox_list) {
    checkbox.checked = checked;
  }
}
function func_usaCheck(bool) {
  if (bool == false) {
    // 미국산 체크박스 6개중 클릭한 체크박스가 체크가 해제 되어진 상태로 넘어온 경우
    document.querySelector("#allChoice").checked = true;
  } else;
  {
    checkbox_list = document.querySelectorAll("input[name='product_usa']");
    let is_all_checked = true;
    for (let checkbox of checkbox_list) {
      if (checkbox.checked == false) {
        // 미국산 체크박스 6개를 반복할때, 해당 체크박스가 체크가 해제 되어진 경우라면
        is_all_checked = false;
        break;
      }
    }
    document.querySelector("input[id='allChoice']").checked = is_all_checked;
  }
}
