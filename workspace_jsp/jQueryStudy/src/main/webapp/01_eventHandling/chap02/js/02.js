/**
 *
 */
$(document).ready(function () {
  const man = "이순신, 서강준, 강감찬, 차은우, 정두환, 김수현";
  const woman = "한효주, 김연아, 김여원, 배수지, 김지원";

  $("span.man").bind("click", function () {
    $("div#result")
      .html(func_friend(man))
      .removeClass("text-color-woman")
      .addClass("text-color-man");
  });
  $("span.woman").bind("click", function () {
    $("div#result")
      .html(func_friend(woman))
      .removeClass("text-color-man")
      .addClass("text-color-woman");
  });
}); // end of $(document).ready-----------------------------------

function func_friend(names) {
  let html = `<ol>`;
  names.split(",").forEach((item) => {
    html += `<li>${item}</li>`;
  });
  html += `</ol>`;
  return html;
} // end of function func_friend-----------------------------------
