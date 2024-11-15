/**
 *
 */

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

function openDaumPOST() {
  new daum.Postcode({
    oncomplete: function (data) {
      // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

      // 각 주소의 노출 규칙에 따라 주소를 조합한다.
      // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
      let addr = ""; // 주소 변수
      let extraAddr = ""; // 참고항목 변수

      //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
      if (data.userSelectedType === "R") {
        // 사용자가 도로명 주소를 선택했을 경우
        addr = data.roadAddress;
      } else {
        // 사용자가 지번 주소를 선택했을 경우(J)
        addr = data.jibunAddress;
      }

      // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
      if (data.userSelectedType === "R") {
        // 법정동명이 있을 경우 추가한다. (법정리는 제외)
        // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
        if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
          extraAddr += data.bname;
        }
        // 건물명이 있고, 공동주택일 경우 추가한다.
        if (data.buildingName !== "" && data.apartment === "Y") {
          extraAddr +=
            extraAddr !== "" ? ", " + data.buildingName : data.buildingName;
        }
        // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
        if (extraAddr !== "") {
          extraAddr = " (" + extraAddr + ")";
        }
        // 조합된 참고항목을 해당 필드에 넣는다.
        document.getElementById("extraAddress").value = extraAddr;
      } else {
        document.getElementById("extraAddress").value = "";
      }

      // 우편번호와 주소 정보를 해당 필드에 넣는다.
      document.getElementById("postcode").value = data.zonecode;
      document.getElementById("address").value = addr;
      // 커서를 상세주소 필드로 이동한다.
      document.getElementById("detailAddress").focus();
    },
  }).open();
} //end of openDaumPOST---------------------
