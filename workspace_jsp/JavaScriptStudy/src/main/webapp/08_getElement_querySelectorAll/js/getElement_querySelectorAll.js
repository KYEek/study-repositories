/**
 *
 */
window.onload = function () {
  // === 1. 자바스크립트에서 태그를 가지고 선택자를 잡는 방법 === //
  /*
      document.getElementsByTagName("태그명"); 이다.
      
      document.getElementsByTagName("태그명"); 을 실행하면 NodeList 타입(배열과 거의 비슷함)으로 반환해준다. 즉, 쉽게 말하자면 객체(object)가 여러개인 형태로 반환해준다.        
   */

  const divList = document.getElementsByTagName("div"); // div 태그를 가지고 있는 모든 객체를 반환해준다.

  console.log("divList : ", divList); // HTMLCollection(3) [div, div, div]

  //alert("diviList의 길이 : " + divList.length); // diviList의 길이 : 3

  for (let i = 0; i < divList.length; i++) {
    divList[i].style.display = "inline-block";
    divList[i].style.border = "1px solid black";
    divList[i].style.width = "200px";
    divList[i].style.height = "150px";
    divList[i].style.margin = "20px";
    divList[i].style.backgroundColor = divList[i].innerText;
    // CSS 에서 background-color 와 같이 스네이크기법으로 했던 것은 자바스크립트에서는 backgroundColor 와 같이 카멜기법으로 바꾸어야만 한다.
    divList[i].style.color = "white";
  }

  const btnList = document.getElementsByName("button");

  for (let i = 0; i < btnList.length; i++) {
    btnList[i].style.width = "100px";
    btnList[i].style.height = "50px";
    btnList[i].margin = "20px";
  } // end of for------------------------------

  document.getElementsByTagName("button")[3].style.backgroundColor = "yellow";

  // === 2. 자바스크립트에서 클래스명을 가지고 선택자를 잡는 방법 === //
  /*
      document.getElementsByClassName("클래스명");
      
      document.getElementsByClassName("클래스명"); 을 실행하면 NodeList 타입(배열과 거의 비슷함)으로 반환해준다. 즉, 쉽게 말하자면 객체(object)가 여러개인 형태로 반환해준다.        
   */

  const btn_navy_list = document.getElementsByClassName("btn_navy");

  for (let i = 0; i < btn_navy_list.length; i++) {
    btn_navy_list[i].style.backgroundColor = "navy";
    btn_navy_list[i].style.color = "white";
  }

  ///////////////////////////////////////////////////////////////

  document.getElementsByTagName("ul")[0].style.listStyleType = "none";

  const label_list = document.getElementsByTagName("label");

  for (let i = 0; i < label_list.length; i++) {
    label_list[i].style.color = "red";
    label_list[i].style.fontWeight = "bold";
    label_list[i].style.fontSize = "16pt";
  }

  // === 3. 자바스크립트에서 name 속성을 가지고 선택자를 잡는 방법 === //
  /*
      document.getElementsByName("name값");
      
      document.getElementsByName("name값"); 을 실행하면 NodeList 타입(배열과 거의 비슷함)으로 반환해준다. 즉, 쉽게 말하자면 객체(object)가 여러개인 형태로 반환해준다.        
   */
  const checkbox_hobby_list = document.getElementsByName("hobby");
  for (let i = 0; i < checkbox_hobby_list.length; i++) {
    console.log(checkbox_hobby_list[i].value);
  }

  const span_hobby_list = document.getElementsByClassName("hobby_val");

  for (let i = 0; i < span_hobby_list.length; i++) {
    span_hobby_list[i].innerHTML = checkbox_hobby_list[i].value;
  }
  // === 4. 자바스크립트에서 id 값을 가지고 선택자를 잡는 방법 === //
  /*
      document.getElementById("id값");
      
      document.getElementById("id값"); 을 실행하면 리턴타입은 1개의 객체(object)이다.         
   */
  document.getElementById("btn_ok_1").onmousemove = () => {
    // document.getElementById("btn_ok_1").style.backgroundColor = "yellow";
    document.getElementById("btn_ok_1").style.backgroundColor = "red";
    //화살표 함수는 this를 사용할 수 없다.
  };
  document.getElementById("btn_ok_1").onmouseout = () => {
    // document.getElementById("btn_ok_1").style.backgroundColor = "yellow";
    document.getElementById("btn_ok_1").style.backgroundColor = ""; //css에서 주었던 원래의 배경색
    //화살표 함수는 this를 사용할 수 없다.
  };

  // === 5. 자바스크립트에서 CSS 선택자를 가지고 선택자를 잡는 방법 === //
  // document.querySelectorAll("CSS 선택자");
  // document.querySelectorAll("CSS 선택자"); 을 실행하면 NodeList 타입(배열과 거의 비슷함)으로 반환해준다. 즉, 쉽게 말하자면 객체(object)가 여러개인 형태로 반환해준다.

  const li_foodList = document.querySelectorAll(
    "body > section#food > ol:nth-child(2) > li"
  );

  //   for (let i = 0; i < li_foodList.length; i++) {
  //     li_foodList[i].style.color = "red";
  //   }

  const li_foodList2 = document.querySelectorAll(
    "section#food > ol:nth-child(2) > li"
  );
  for (let i = 0; i < li_foodList2.length; i++) {
    // switch (i) {
    //   case 0:
    li_foodList2[i].onmousemove = () => {
      alert(li_foodList2[i].innerText + " 음식에 마우스를 올리셨군요");
    };
    //     break;
    //   case 1:
    //     li_foodList2[i].onmousemove = () =>
    //       alert("짬뽕 음식에 마우스를 올리셨군요");
    //     break;
    //   case 2:
    //     li_foodList2[i].onmousemove = () =>
    //       alert("탕수육 음식에 마우스를 올리셨군요");
    //     break;
    //   case 3:
    //     li_foodList2[i].onmousemove = () =>
    //       alert("팔보채 음식에 마우스를 올리셨군요");
    //     break;
  }
  // 나중에 innerText를 활용하는 방법으로 바꿔보자

  // === 6. 자바스크립트에서 CSS 선택자를 가지고 선택자를 잡는 방법 === //
  // document.querySelector("CSS 선택자");
  // document.querySelector("CSS 선택자");  을 실행하면 리턴타입은 1개의 객체(object)이다.

  const img = document.querySelector("img#food_image");
  const img_food_arr = ["jjm.png", "jjbong.png", "tangsy.png", "palbc.png"];

  for (let i = 0; i < li_foodList2.length; i++) {
    li_foodList2[i].onmousemove = () => {
      img.src = "images/" + img_food_arr[i];
      img.style.display = "inline";
      li_foodList2[i].style.color = "white";
      li_foodList2[i].style.backgroundColor = "navy";
    };
    li_foodList2[i].onmouseout = () => {
      img.style.display = "none";
      li_foodList2[i].style.color = "";
      li_foodList2[i].style.backgroundColor = "";
    };
    img.width = "150"; //px을 안붙여도 된다.
    img.height = "110"; //기본적으로 px이다.
  } // end of fot

  // 체크박스확인1 버튼을 클릭하면 name 값이 hobby 인 체크박스에 체크가 되어진것만 value 을 나열해서 span태그의 id값이 result1 인 곳에 출력하세요...
  document.querySelectorAll("button")[6].onclick = function () {
    //document.querySelectorAll("button")[6]를 "이벤트 소스"라고 한다.
    //onclick = function(){} 이벤트 소스에 클릭 이벤트를 걸어준다.
    //function(){} 이벤트 핸들러라고 한다.
    // alert("체크박스확인1 버튼을 클릭하셨군요");

    //input[type=checkbox] 보다는 input[name=hobby]를 사용하는 것이 좋다.
    //const checkbox_list = document.querySelectorAll("input[type=checkbox]");
    const checkbox_list = document.querySelectorAll("input[name=hobby]");
    let result = "";
    let cnt = 0;
    for (let i = 0; i < checkbox_list.length; i++) {
      //필수암기
      // console.log("확인용 value : ", checkbox_list[i].value);
      //cheked 속성 필수 암기
      // console.log("확인용 체크유무 : ", checkbox_list[i].checked);

      if (checkbox_list[i].checked) {
        cnt++;
        let s_comma = cnt == 0 ? "" : ",";
        result += s_comma + checkbox_list[i].value;
      }
    } //end of for-----------------------------

    // console.log(result);
    document.querySelector("span#result1").innerHTML = result;
  };

  // 체크박스확인2 버튼을 클릭하면 name 값이 hobby 인 체크박스에 체크가 되어진것만 value 을 나열해서 span태그의 id값이 result1 인 곳에 출력하세요...
  document
    .querySelector("button#btn_ok_2")
    .addEventListener("click", function () {
      const checkbox_list = document.querySelectorAll("input[type=checkbox]");
      let result = "";
      let cnt = 0;
      for (let i = 0; i < checkbox_list.length; i++) {
        //필수암기
        // console.log("확인용 value : ", checkbox_list[i].value);
        //cheked 속성 필수 암기
        // console.log("확인용 체크유무 : ", checkbox_list[i].checked);

        if (checkbox_list[i].checked) {
          cnt++;
          let s_comma = cnt == 0 ? "" : ",";
          result += s_comma + checkbox_list[i].value;
        }
      } //end of for-----------------------------
    });
  //document.querySelectorAll("button")[6]를 "이벤트 소스"라고 한다.
  //onclick = function(){} 이벤트 소스에 클릭 이벤트를 걸어준다.
  //function(){} 이벤트 핸들러라고 한다.
  // alert("체크박스확인1 버튼을 클릭하셨군요");

  // console.log(result);
  document.querySelector("span#result1").innerHTML = result;
};

function func_clear() {
  const checkbox_list = document.querySelectorAll("input[type=checkbox]");

  for (let i = 0; i < checkbox_list.length; i++) {
    //체크박스의 체크를 해제
    checkbox_list[i].checked = false;
  }

  document.querySelector("span#result1").innerHTML = "";
  document.querySelector("span#result2").innerHTML = "";
}
