/**
 *
 */

$(document).ready(function () {
  const arr_btn = [
    { color: "red", hangul: "빨강" },
    { color: "orange", hangul: "주황" },
    { color: "yellow", hangul: "노랑" },
    { color: "green", hangul: "초록" },
    { color: "blue", hangul: "파랑" },
    { color: "navy", hangul: "남색" },
    { color: "purple", hangul: "보라" },
  ];
  // 버튼을 생성
  html = ``;
  for (let arr_color of arr_btn) {
    html += `<button>${arr_color["color"]}</button>`;
  }
  $("#color_buttons").html(html);

  //버튼을 클릭시 해당 버튼의 색상을 적용
  const buttons = $("button");
  //   console.log(buttons);// 7개의 버튼이 출력됨
  $.each(buttons, function (index, item) {
    item.onclick = () => {
      //   console.log("영어 : ", arr_btn[index].color); // 영어 색상명 출력
      //   console.log("한글 : ", arr_btn[index].hangul);// 한글 색상명 출력
      $("#square").css({ "background-color": arr_btn[index].color });
      $("#color-text").css({ color: arr_btn[index].color });
      $("div#color-text").text(arr_btn[index].hangul);
    };
  });
}); // end of $(document).ready(function(){})-----------------
