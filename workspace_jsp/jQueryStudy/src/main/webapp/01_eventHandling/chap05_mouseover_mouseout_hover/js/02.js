/**
 *
 */

$(document).ready(function () {
  const arr_person = [
    {
      name: "김태희",
      filename: "kimth.jpg",
      address: "서울 강동구",
      email: "kimth@gmail.com",
    },
    {
      name: "아이유",
      filename: "iyou.jpg",
      address: "서울 강서구",
      email: "iyou@gmail.com",
    },
    {
      name: "박보영",
      filename: "parkby.jpg",
      address: "서울 강남구",
      email: "parkby@gmail.com",
    },
  ];
  let html = ``;
  arr_person.forEach((item, index) => {
    html += `<span class ='buttons' id = '${index}'>${item.name}</span>`;
  });

  $("div#firstdiv").html(html);
  //   $("div#container > div#firstdiv > span.buttons").bind(
  //     "mousever",
  //     function () {
  //       alert("헤헤헤");
  //     }
  //   );
  //또는
  //   $("div#container > div#firstdiv > span.buttons").mouseover(function () {
  //     alert("헤헤헤");
  //   });

  $("div#container > div#firstdiv > span.buttons").each(function (i, elmt) {
    html = "";

    $("div#seconddiv").show();
    $(elmt).mouseover(function (e) {
      const filename = arr_person[$(elmt).attr("id")].filename;
      html = `<img src = 'images/${filename}'>`;
      $("div#face").html(html);
      html = `<ol><li><span>이름</span> : ${
        arr_person[$(elmt).attr("id")].name
      }</li>
      <li><span>주소</span> : ${arr_person[$(elmt).attr("id")].address}</li>
      <li><span>이메일</span> : ${
        arr_person[$(elmt).attr("id")].email
      }</li></ol>`;
      $("div#comment").html(html);

      $(elmt).css({
        "background-color": "green",
        color: "white",
      });
    });
    $("div#container > div#firstdiv > span.buttons").mouseout(function (e) {
      $(elmt).css({
        "background-color": "",
        color: "",
      });
      $("div#seconddiv").hide();
    });
  });

  // $("div#container > div#firstdiv > span.buttons").mouseover(function (e) {
  // console.log($(e.target).text());
  // console.log($(e.target).attr("class"));
  // console.log($(e.target).attr("id"));
  /*
         ===== 선택자의 class 명 알아오기 =====
              선택자.attr('class')  또는  선택자.prop('class')  
         
         ===== 선택자의 id 명 알아오기 =====
              선택자.attr('id')  또는  선택자.prop('id')
                  
         ===== 선택자의 name 명 알아오기 =====   
             선택자.attr('name')  또는  선택자.prop('name')
                     
         >>>> .prop() 와 .attr() 의 차이 <<<<            
         .prop() ==> form 태그내에 사용되어지는 엘리먼트의 disabled, selected, checked 의 속성값 확인 또는 변경하는 경우에 사용함. 
         .attr() ==> 그 나머지 엘리먼트의 속성값 확인 또는 변경하는 경우에 사용함.
     */
  // });
});
