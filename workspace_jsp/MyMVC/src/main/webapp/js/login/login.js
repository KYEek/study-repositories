/**
 *
 */
$(document).ready(function () {
  $("button#btnSubmit").click(() => {
    goLogin();
  }); //로그인 시도
  $("input#loginPwd").on("keyup", (e) => {
    if (e.keyCode == 13) {
      //암호 입력 후 엔터키 누르면 로그인 시도
      goLogin();
    }
  });
});

//Function Declaration

// 로그인 처리 함수
function goLogin() {
  //   alert("로그인 시도");

  if ($("input#loginUserid").val().trim() == "") {
    alert("아이디를 입력하세요");
    $("input#loginUserid").val("").focus();
    return;
  }
  if ($("input#loginPwd").val().trim() == "") {
    alert("암호를 입력하세요");
    $("input#loginPwd").val("").focus();
    return;
  }
  219;
  const frm = document.loginFrm;
  frm.submit();
} // end of goLogin-----------------------------------

function goLogOut(ctx_Path) {
  // 로그아웃을 처리해주는 페이지로 이동이얌
  location.href = `${ctx_Path}/login/logout.up`;
}
