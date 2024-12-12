$(document).ready(function () {
  const arr_data = [
    {
      city: "런던",
      content: "런던 (영어: London)은 잉글랜드와 영국의 수도이다.",
      photo: "london.jpg",
    },
    {
      city: "파리",
      content: "파리(Paris)는 프랑스의 수도이다.",
      photo: "paris.jpg",
    },
    { city: "앙카라", content: "터키의 수도이다.", photo: "ankara.jpg" },
  ];

  let html = `<p> 탭 메뉴를 선택해 보세요</p>
      <div id ="tab">`;

  arr_data.forEach((item) => {
    html += `<button type ='button'>${item.city}</button>`;
  });

  html += "</div>";

  arr_data.forEach((item) => {
    html += `<div class='content'>
        <h3>${item.city}</h3>
        <p>${item.content}</p>
        <img src='images/${item.photo}' width='512px' height = '288px'/>
        </div>`;
  });

  $("div#container").html(html);
  // 클릭한 탭(버튼)에 대한 내용을 보여주기 위한 이벤트 처리

  $(document).on("click", "div#tab > button", function (e) {
    const index = $("div#tab > button").index($(e.target));
    // console.log(index);

    $("div#container > div.content").css({ display: "none" });
    $("div#container > div.content").eq(index).css({ display: "block" });
    $("div#tab > button").removeClass("active");
    $(e.target).addClass("active");
  }); // end of click event--------------------------------

  $("div#tab > button").eq(0).trigger("click");
}); // end of ready--------------------------------
