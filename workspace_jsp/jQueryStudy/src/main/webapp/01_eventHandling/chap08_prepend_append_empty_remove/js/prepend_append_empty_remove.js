/**
 *
 */

$(document).ready(function () {
  const lovecountrysong1 =
    "동해물과 백두산이 마르고 닳도록 하느님이 보우하사 <span style='color: red; font-weight: bold;'>우리나라</span> 만세";
  const lovecountrysong2 =
    "무궁화 삼천리 화려강산 대한사람 대한으로 <span style='color: blue; font-weight: bold;'>길이보전</span> 하세";
  let cnt = 0;
  $("#divBtn > span.add").click(function () {
    cnt++;
    console.log(cnt);
    $("div.dvi1").prepend(`<p>${cnt},${lovecountrysong1}</p>`); // prepend() 메서드는 선택한 요소의 내부 앞쪽에 새 요소를 추가합니다.
    $("div.dvi2").append(`<p>${cnt},${lovecountrysong2}</p>`);
  });

  $("#divBtn > span.empty").click(function () {
    console.log(cnt);
    $("div#div1").empty(); // empty() 메서드는 선택한 요소의 자식 요소를 모두 제거합니다.
    $("div#div2").empty();
  });

  $("#divBtn > span.remove").click(function () {
    console.log(cnt);
  });
});
