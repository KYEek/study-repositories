/**
 *
 */

window.onload = function () {
  let html = `<option>출생년도</option>`;
  const now = new Date();
  for (let i = 1950; i <= now.getFullYear(); i++) {
    html += "<option>" + i + "</option>";
  }
  document.querySelector("select#birthYear").innerHTML = html;

  html = `<option>출생월</option>`;
  for (let i = 1; i <= 12; i++) {
    if (i < 10) {
      html += "<option>0" + i + "</option>";
    } else {
      html += "<option>" + i + "</option>";
    }
  }
  document.querySelector("select#birthMonth").innerHTML = html;

  html = `<option>출생일</option>`;

  for (let i = 1; i <= 31; i++) {
    if (i < 10) {
      html += "<option>0" + i + "</option>";
    } else {
      html += "<option>" + i + "</option>";
    }
  }

  document.querySelector("select#birthDate").innerHTML = html;
  // 숫자로 된 날짜가 실제 달력에 존재하는 날짜인지 검사하도록 하는 함수
  /*
   yyyy-mm-dd형식에 맞춰서 유효한 날짜인지 체크해주는 코드입니다.
   윤달까지 고려하여 유효한 날짜인지 체크해주는 코드입니다.
*/

  // 만나이를 구해주기 시작

  const birthSelect_list = document.querySelectorAll("select.birthSelect");
  for (let birth of birthSelect_list) {
    // onchange 으로 해야지 onChange 하면 작동을 하지 않는다.!!!
    birth.onchange = function () {
      if (isNaN(birth.value)) {
        if (birth.value === "출생년도") {
          alert("태어나신 년도를 선택하세요");
        } else {
          alert("태어나신 월을 선택하세요");
        }
        document.querySelector("span#age").innerText = "";
      } else {
        //숫자년도 및 숫자월을 선택한 경우
        const birthday =
          document.querySelector("select#birthYear").value +
          "-" +
          document.querySelector("select#birthMonth").value +
          "-" +
          document.querySelector("select#birthDate").value;
        console.log(birthday);
        if (
          !isNaN(document.querySelector("select#birthYear").value) &&
          !isNaN(document.querySelector("select#birthMonth").value) &&
          !isNaN(document.querySelector("select#birthDate").value) &&
          !checkValidDate(birthday)
        ) {
          alert("달력상에 존재하지 않는 생년월일 입니다.");
          document.querySelector("span#age").innerHTML = "";
        }

        if (checkValidDate(birthday)) {
          //생년월일이 달력에 존재하는 날짜이라면
          document.querySelector("span#age").innerText = go_age(birthday);
        }
      }
    };
  }
  // 만나이를 구해주기 끝

  //전송(button) 클릭시 시작
  document.querySelector("input[type='button']").onclick = function () {
    // document.querySelector("button[id='btn_submit_ok']").onclick = function () { 이거는 타입이 button 이어서 정상적으로 작동한다
    // document.querySelector("input[id='btn_submit_fail']").onclick = function () { 이거는 타입이 정해지지 않아서 submit으로 인식한다.
    //from 태그 안에서 button 타입을 설정해주지 않으면 submit으로 인식한다.

    const frm = document.register_form; //form 태그 객체를 참조
    // 1. 사용자 ID는 5글자 이상 20글자 이하 이어야 하고, 첫글자는 영문자 이어야 하고 그 나머지는 영문자 또는 숫자로 이루어져야 한다.
    //    올바른예 : superman , Batman007 , s9man
    //    틀린예  :  007superman, Lees, Batman007$

    //    정규표현식 객체 만들기
    // 정규표현식 객체는 항상 / 로 시작해서 / 로 끝나고 ; 을 붙여주면 된다.
    const regExp_userid = /^[A-Za-z][A-Za-z0-9]{4,19}$/;

    //위에서 생성한 정규표현식 객체를 이용하여 사용자 ID가 올바른지 검사
    const bool_userid = regExp_userid.test(frm.userid.value);

    // frm.userid.value 값이 regExp_userid 패턴에 맞으면 true,
    // frm.userid.value 값이 regExp_userid 패턴에 틀리면 false

    if (!bool_userid) {
      document.querySelector("span#err_userid").innerText =
        "아이디는 5글자 이상 20글자 이하 이어야 하고, 첫글자는 영문자 나머지는 소문자 또는 숫자 이어야 합니다.";
      frm.userid = "";
      frm.userid.focus();
      return; // 종료
    } else {
      document.querySelector("span#err_userid").innerText = "";
    }

    if (2 == 4 - 1) {
      alert("전송버튼 클릭");
      frm.submit(); // 유효성 검사를 통과하면 submit을 한다.
    } else {
      alert("유효성 검사 문제 발생");
      return false; //submit을 하지 않는다
    }
  };

  //전송(button) 클릭시 끝

  //전송(submit) 클릭시 시작
  const frm = document.register_form; //form 태그 객체를 참조
  // onsubmit 은 버튼 인풋 상관없이 type="submit" 이면 작동한다.
  frm.onsubmit = function () {
    alert("summit 클릭");
  };
  //전송(submit ) 클릭시 끝
};

function go_birthDate() {}

//생년월일을 입력 받으면 '만나이'를 구해주는 함수
function go_age(birthday) {
  const d = new Date(birthday); //문자열(yyyy-mm-dd)을 Date객체로 변환
  //날짜형식의 문자열은 yyyy-mm-dd 또는 yyyy/mm/dd 또는 yyyy.mm.dd 형식이어야 한다.

  const birth_year = d.getFullYear(); //생일년도(number 타입이다)
  console.log(birth_year);

  const now = new Date(); //현재 날짜를 구한다.
  const now_year = now.getFullYear(); //현재년도를 구한다.

  //올해 생일이 현재일자 보다 작은 경우, 즉 오늘이 생일이거나, 생일이 지난 경우
  if (d.setFullYear(now_year) <= now) {
    return now_year - birth_year;
  }
  //올해 생일이 현재일자보다 뒤에 있는 경우, 즉 생일이 아직 안 지난 경우
  else {
    return now_year - birth_year - 1;
  }
}

function checkValidDate(value) {
  var result = true;
  try {
    var date = value.split("-");
    var y = parseInt(date[0], 10),
      m = parseInt(date[1], 10),
      d = parseInt(date[2], 10);
    //    parseInt(숫자값, 진법);
    //    val = parseInt(1010, 2);     2진수 1010을 10진수로 변경.
    //  val = parseInt(24, 8);       8진수 24을 10진수로 변경.
    //  val = parseInt("1e", 16);  16진수 1e을 10진수로 변경하시오.

    var dateRegex =
      /^(?=\d)(?:(?:31(?!.(?:0?[2469]|11))|(?:30|29)(?!.0?2)|29(?=.0?2.(?:(?:(?:1[6-9]|[2-9]\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00)))(?:\x20|$))|(?:2[0-8]|1\d|0?[1-9]))([-.\/])(?:1[012]|0?[1-9])\1(?:1[6-9]|[2-9]\d)?\d\d(?:(?=\x20\d)\x20|$))?(((0?[1-9]|1[012])(:[0-5]\d){0,2}(\x20[AP]M))|([01]\d|2[0-3])(:[0-5]\d){1,2})?$/;
    result = dateRegex.test(d + "-" + m + "-" + y);
  } catch (err) {
    result = false;
  }
  return result;
} // end of function checkValidDate(value)--------------------------------

//생년월일에서 '출생일'에 해당하는 select 태그의 값을 변경했을 때 호출되는 함수
function go_birthDate(select) {
  select.onchange = function () {
    if (isNaN(select.value)) {
      alert("태어나신 일을 선택하세요");
      document.querySelector("span#age").innerText = "";
    } else {
      //숫자년도 및 숫자월을 선택한 경우
      const birthday =
        document.querySelector("select#birthYear").value +
        "-" +
        document.querySelector("select#birthMonth").value +
        "-" +
        document.querySelector("select#birthDate").value;
      console.log(birthday);
      if (
        !isNaN(document.querySelector("select#birthYear").value) &&
        !isNaN(document.querySelector("select#birthMonth").value) &&
        !isNaN(document.querySelector("select#birthDate").value) &&
        !checkValidDate(birthday)
      ) {
        alert("달력상에 존재하지 않는 생년월일 입니다.");
        document.querySelector("span#age").innerHTML = "";
      }

      if (checkValidDate(birthday)) {
        //생년월일이 달력에 존재하는 날짜이라면
        document.querySelector("span#age").innerText = go_age(birthday);
      }
    }
  };
} // end of function go_birthDate(select)--------------------------------
