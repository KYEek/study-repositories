/**
 *
 */
window.onload = function () {
  const checkbox_list = document.getElementsByName("product_old");
  // 하나만 선택되도록 만들기
  for (let checkbox of checkbox_list) {
    checkbox.addEventListener("click", function () {
      for (checkbox2 of checkbox_list) {
        if (checkbox == checkbox2) {
          continue;
        } else {
          checkbox2.checked = false;
        }
      }
    });
  }

  //하나만 선택되로록 만들기 끝

  const allcheck = document.querySelector("input[id = 'allCheck']");
  const usa_check = document.getElementsByName("product_usa");
  //미국산 변수 선언

  //전부 체크하기 시작

  allcheck.addEventListener("click", function () {
    let checke = allcheck.checked;
    console.log(checke);
    for (let usa of usa_check) {
      usa.checked = checke;
    }
  });

  //전부 체크하기 끝

  //자동으로 체크박스 체크하기 시작
  for (let usa of usa_check) {
    usa.addEventListener("click", function () {
      let all_checked = true;
      for (let usa of usa_check) {
        if (usa.checked == false) {
          all_checked = false;
        }
      }
      console.log(all_checked);
      allcheck.checked = all_checked;
    });
  }
};
