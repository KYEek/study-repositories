/**
 *
 */
$(document).ready(function () {
  const arr_btn = [
    { seatName: "A1", x: 0, y: 0, reservation_status: 0 },
    { seatName: "A2", x: 1, y: 0, reservation_status: 0 },
    { seatName: "A3", x: 2, y: 0, reservation_status: 0 },
    { seatName: "A4", x: 3, y: 0, reservation_status: 0 },
    { seatName: "A5", x: 4, y: 0, reservation_status: 0 },
    { seatName: "A6", x: 5, y: 0, reservation_status: 0 },
    { seatName: "A7", x: 6, y: 0, reservation_status: 0 },
    { seatName: "A8", x: 7, y: 0, reservation_status: 0 },
    { seatName: "A9", x: 8, y: 0, reservation_status: 0 },
    { seatName: "A10", x: 9, y: 0, reservation_status: 0 },
    { seatName: "B1", x: 0, y: 1, reservation_status: 0 },
    { seatName: "B2", x: 1, y: 1, reservation_status: 0 },
    { seatName: "B3", x: 2, y: 1, reservation_status: 0 },
    { seatName: "B4", x: 3, y: 1, reservation_status: 1 },
    { seatName: "B5", x: 4, y: 1, reservation_status: 1 },
    { seatName: "B6", x: 5, y: 1, reservation_status: 0 },
    { seatName: "B7", x: 6, y: 1, reservation_status: 0 },
    { seatName: "B8", x: 7, y: 1, reservation_status: 0 },
    { seatName: "B9", x: 8, y: 1, reservation_status: 0 },
    { seatName: "B10", x: 9, y: 1, reservation_status: 0 },
    { seatName: "C1", x: 0, y: 2, reservation_status: 0 },
    { seatName: "C2", x: 1, y: 2, reservation_status: 0 },
    { seatName: "C3", x: 2, y: 2, reservation_status: 0 },
    { seatName: "C4", x: 3, y: 2, reservation_status: 0 },
    { seatName: "C5", x: 4, y: 2, reservation_status: 0 },
    { seatName: "C6", x: 5, y: 2, reservation_status: 0 },
    { seatName: "C7", x: 6, y: 2, reservation_status: 0 },
    { seatName: "C8", x: 7, y: 2, reservation_status: 0 },
    { seatName: "C9", x: 8, y: 2, reservation_status: 0 },
    { seatName: "C10", x: 9, y: 2, reservation_status: 0 },
  ];

  $("div#container > div#age_cnt_div > button:reset").prop("disabled", true);
  $("div#container > div#reservation_div").hide();
  $("div#container > div#sum_pay").hide();
  let cnt = 0;
  let age = "";
  let sum_pay = 0;
  $(document).on(
    "click",
    "div#container > div#age_cnt_div > button#seat_btn",
    function () {
      age = $("div#age_cnt_div > select");
      cnt = $("div#age_cnt_div > input").val();
      if (age.val() == "" || cnt == 0) {
        alert("연령대와 예매수를 지정하세요.");
      } else {
        $("div#container > div#reservation_div").show();
      }
    }
  ); //end of $(document).on("click", "div#container > div#age_cnt_div > button#seat_btn", function(){})

  let html = `<div>예매하실 좌석을 클릭하세요</div>`;
  $("div#reservation_div > div#titleDiv").html(html);
  html = `<div>스크린</div>`;
  $("div#reservation_div > div#screenDiv").html(html);
  html = ``;
  arr_btn.forEach((item, index) => {
    if (`${item.reservation_status}` == 0) {
      html += `<button type = 'button'> ${item.seatName}</button>`;
    } else {
      html +=
        "<button type = 'button' style ='background-color:red; color:white; font-size : 11pt'>예매불가</button>";
    }

    if ((index + 1) % 10 == 5) {
      html += `<span></span>`;
    }
    if ((index + 1) % 10 == 0) {
      html += `<br>`;
    }
  }); //end of arr_btn.forEach((item, index) => {})
  $("div#reservation_div > div#btnsDiv").html(html);

  $(document).on("click", "div#reservation_div > div#btnsDiv > button", (e) => {
    const idx = $("div#reservation_div > div#btnsDiv > button").index(e.target);
    // 자바스크립트에서 숫자를 문자형태로 변환은 ==> 숫자.toString()
    // alert(arr_btn[idx].x.toString() + arr_btn[idx].y.toString());

    // 자바스크립트에서 숫자를 알파벳으로 변환은 ==> String.fromCharCode(숫자);
    // alert(String.fromCharCode(65));  ==> A

    // 자바스크립트에서 알파벳을 숫자로 변환은  ==> "A".charCodeAt(0);
    // alert("A".charCodeAt(0));  ==> 65

    const reservation_status = arr_btn[idx].reservation_status;
    if (reservation_status == 0 && cnt > 0) {
      alert(`앞으로 좌석 예매를 ${cnt}번 가능합니다.`);

      const seatName =
        String.fromCharCode(arr_btn[idx].y + 65) + (arr_btn[idx].x + 1);
      if (confirm(`좌석을 ${age.val()}예매하시겠습니까?`)) {
        alert(`좌석 ${seatName}을 예매하셨습니다.`);
        arr_btn[idx].reservation_status = 1;

        $(e.target).html(`${age.val()}`).css({
          "background-color": "red",
          color: "white",
          "font-size": "11pt",
        });

        switch (age.val()) {
          case "성인":
            sum_pay += 15000;
            break;

          case "청소년":
            sum_pay += 12000;
            break;
            cnt--;
          case "어린이":
            sum_pay += 10000;
            break;
        }

        cnt--;

        if (cnt == 0) {
          if (confirm("예매를 마치시겠습니까?")) {
            $("div#container > div#age_cnt_div >  select").val("");
            $("div#container > div#age_cnt_div > input").val("");
            $("div#container > div#reservation_div").hide();

            $("div#container > div#sum_pay").html(
              "계속해서 예매가 진행중입니다."
            );
            $("div#container > div#sum_pay").show();
          } else {
            alert("예매가 종료되었습니다..");
            $("div#container > div#age_cnt_div >  select").val("");
            $("div#container > div#age_cnt_div > input").val("0");
            $("button#seat_btn").prop("disabled", false);
            $("button:reset").prop("disabled", false);
          }
        }
      }
    } else if (reservation_status == 1 && cnt > 0) {
      alert("이미 예약된 좌석입니다.");
    } else {
      alert(
        "예매가 종료되었으므로 처음부터 다시하려면 초기화 버튼을 클릭하세요!!"
      );
    }
  }); //end of $(document).on("click", "div#reservation_div > div#btnsDiv > button", (e) => {})
}); // end of $(document).ready(function(){})-----------------
