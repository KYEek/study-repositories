/**
 *
 */

const arr_person = [
  {
    name: "김태희",
    filename: "kimth.jpg",
    large_file: "kimthLarge.jpg",
    address: "서울 강동구",
    email: "kimth@gmail.com",
  },
  {
    name: "아이유",
    filename: "iyou.jpg",
    large_file: "iyouLarge.jpg",
    address: "서울 강서구",
    email: "iyou@gmail.com",
  },
  {
    name: "박보영",
    filename: "parkby.jpg",
    large_file: "parkbyLarge.jpg",
    address: "서울 강남구",
    email: "parkby@gmail.com",
  },
];

$(document).ready(function () {
  // 프로그램 시작

  //버튼 만드는 부분
  let html = ``;
  arr_person.forEach((person, index) => {
    html += `<button type = "button" id = "${index}" class ="name_button"> ${person["name"]}</button> `;
  });
  $("#button").html(html);

  //버튼 만드는 부분 끝
  //————————————————————————————————————————————————————————————————
  //마우스 오버시 이미지 변경

  const $button = $("button.name_button"); //버튼 객체를 생성
  //   console.log($button);
  $button.hover(
    //마우스 호버 이벤트
    (e) => {
      //마우스 오버시
      //   console.log("mouse over");
      //   console.log($(e.target).index());인덱스 출력

      $("#main").show();
      html = `<img class ="small_photo" src = "images/${
        arr_person[$(e.target).index()].filename
      }"/>`;
      $("div#main").html(html);
      $(`button#${$(e.target).index()}`).addClass("mouseover_button");
      $(`button#${$(e.target).index()}`).text("클릭하세요");
    },
    (e) => {
      //마우스 아웃시
      //   console.log("mouse out");

      $(`button#${$(e.target).index()}`).removeClass("mouseover_button");
      $("#main").hide();
      $(`button#${$(e.target).index()}`).css("border-radius", "");
      $(`button#${$(e.target).index()}`).text(
        arr_person[$(e.target).index()]["name"]
      );
    }
  );
  //마우스 오버시 이미지 변경 끝
  //————————————————————————————————————————————————————————————————
  //클릭시 이미지 변경
  $(document).on("click", $button, function (e) {
    $(`button#${$(e.target).index()}`).css("border-radius", "0"); //버튼 모서리를 각지게
    html = `<div id = 'content_div' >`; //div태그 생성
    html += `<img class= "large_image" src = "images/${
      arr_person[$(e.target).index()].large_file
    }"/>`; //클릭시 큰 이미지 출력
    html += "<div id='content_list'>";
    html += `<ul class="content_view" >`;
    html += `<li>성명 : ${arr_person[$(e.target).index()].name}</li>`;
    html += `<li>주소 : ${arr_person[$(e.target).index()].address}</li>`;
    html += `<li>e메일 : ${arr_person[$(e.target).index()].email}</li>`;
    html += `</ul>`;
    html += "</div>";
    html += `</div>`; //div태그 닫기

    $("#main").html(html); //main에 html출력
  });
  //클릭시 이미지 변경 끝
}); //end of ready()---------------------------------
