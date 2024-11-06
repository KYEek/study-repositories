/**
 *
 */

/*
   window.onload = function() {} 은 웹페이지 문서가 모두 로드되어진 다음에 자동적으로 실행해주는 것이다. 
*/

window.onload = function () {
  //   const val_1 = document.getElementById("val_1").innerHTML;
  //   console.log("~~~ 확인용 val_1 : ", val_1);
  //~~~ 확인용 val_1 :  <span style="color: red;">1234</span>

  //   const val_1 = document.getElementById("val_1").innerText;
  //   console.log("~~~ 확인용 val_1 : ", val_1);
  //~~~ 확인용 val_1 :  1234

  const val_1 = document.getElementById("val_1").innerText;
  console.log("~~~ 확인용 val_1 : ", val_1);
  //~~~ 확인용 val_1 :  1234

  console.log("변수 val_1의 타입 : ", typeof val_1);
  //변수 val_1의 타입 :  string

  //   document.getElementById("type_1").innerHTML =
  //     "<span style='color:blue;'>" + typeof val_1 + "</span>";

  //   document.getElementById(
  //     "type_1"
  //   ).innerHTML = `<span style='color:red;'>${typeof val_1} </span>`;

  //   document.getElementById("type_1").innerText =
  // "<span style='color:blue;'>" + typeof val_1 + "</span>";

  document.getElementById("type_1").innerText = typeof val_1;

  /////////////////////////////////////////////////////////////////

  const val_2 = document.getElementById("val_2").innerText;
  console.log("~~~ 확인용 val_2 : ", val_2);

  console.log("~~~ 확인용 val_2 : ", parseInt(val_2));

  document.getElementById(
    "type_2"
  ).innerHTML = `<span style='color:blue;'>${typeof parseInt(val_2)}</span>`;

  ////////////////////////////////////////////////////////////////////////////

  const val_3 = document.getElementById("val_3").innerText;
  console.log("~~~ 확인용 val_3 : ", val_3);
  console.log("~~~ 확인용 val_3 : ", parseFloat(val_3));
  document.getElementById(
    "type_3"
  ).innerHTML = `<span style='color:green;'>${typeof parseInt(val_3)}</span>`;

  ////////////////////////////////////////////////////////////////////////////

  const val_4 = document.getElementById("val_4").innerText;
  console.log("~~~ 확인용 val_4 : ", val_4);
  console.log("~~~ 확인용 val_4 : ", Number(val_4));
  document.getElementById(
    "type_4"
  ).innerHTML = `<span style='color:purple;'>${typeof Number(val_4)}</span>`;

  /////////////////////////////////////////////////////////////////////////
  const val_5 = document.getElementById("val_5").innerText;
  console.log("~~~ 확인용 val_5 : ", val_5);
  console.log("~~~ 확인용 val_5 : ", Number(val_5));
  document.getElementById(
    "type_5"
  ).innerHTML = `<span style='color:gray;'>${typeof Number(val_5)}</span>`;

  /////////////////////////////////////////////////////////////////////////

  const val_6 = document.getElementById("val_6").innerText;
  document.getElementById(
    "type_6"
  ).innerHTML = `<span style='color:gray;'>${typeof val_6}</span>`;

  const num1 = document.getElementById("val_7").innerText;
  const num2 = document.getElementById("val_8").innerText;
  const sum = Number(num1) + Number(num2);

  document.getElementById("sum").innerText = sum;

  const sum_type = typeof document.getElementById("sum").innerText;

  console.log("~~~ 확인용 sum_type : ", sum_type);
  document.getElementById("type_8").innerText = typeof sum_type;

  // document.getElementById("btn_10") 을 "이벤트 소스"라고 한다.
  //onclick 은 "이벤트"라고 한다.
  // function() {} 는 "이벤트 핸들러"라고 한다.

  document.getElementById("btn_10").ondblclick = function () {
    //     alert("Hello, World!");
    let val_9 = document.getElementById("val_9").value;
    let val_10 = document.getElementById("val_10").value;

    document.getElementById("type_9").innerHTML = `${val_9} => ${typeof val_9}`;
    document.getElementById("type_10").innerHTML =
      val_10 + " => " + typeof val_10;

    if (val_9 == val_10) {
      document.getElementById(
        "result_11"
      ).innerHTML = `val_9 와 val10은 데이터 타입 및 데이터 값이 같습니다.`;
    }

    val_10 = number(val_10);
    if (val_9 === val_10) {
      document.getElementById(
        "result_12"
      ).innerHTML = `val_9 와 val10은 데이터 타입 및 데이터 값이 같습니다.`;
    } else if (val_9 == val_10) {
      document.getElementById(
        "result_12"
      ).innerHTML = `val_9 와 val10은 데이터 값이 같습니다.`;
    } else {
      document.getElementById(
        "result_12"
      ).innerHTML = `val_9 와 val10은 데이터 값이 같지 않습니다.`;
    }
  };

  // == 와 === 의 차이점
  // == 는 값만 비교한다.
  // === 는 값과 타입을 모두 비교한다.

  //   let a = 10;
  //   let b = "10";
  //   if (a == b) {
  //     alert("a와 b는 같다.");
  //   }
  //   if (a === b) {
  //     alert("a데이터 타입과 b 데이터 타입은 같고 또한, a값과 b값도 같다.");
  //   }

  /////////////////////////////////////////////////////////////////////////

  let val_13;
  document.getElementById("type_13").innerHTML = typeof val_13;

  // ============!!!! 자바스크립트에서 사용되어지는 객체 사용법 -1 !!!!================//
  //   const person1 = new Object(); //자바스크립트에서 말하는 빈 객체이다.
  //또는
  const person1 = {}; //자바스크립트에서 말하는 빈 객체이다.
  person1.userid = "leess"; //객체에 새 속성을 추가하는 방법은 .표기법이 있다. 즉, 객체명.속성명 = 값; 으로 한다.
  person1.pwd = "qwer1234";
  person1["mobile phone"] = "010-1234-5678"; // 객체에 새 속성을 추가하는 방법은 대괄호 표기법이 있다. 즉, 객체명["속성명"] = 값; 으로 한다.
  person1["name"] = "이순신";
  person1["age"] = 25;

  document.getElementById("type_14").innerHTML = typeof person1;
  //object
  document.getElementById("val_15").innerHTML = person1.userid;
  document.getElementById("type_15").innerHTML = typeof person1.userid;

  document.getElementById("val_16").innerHTML = person1.pwd;
  document.getElementById("type_16").innerHTML = typeof person1.pwd;

  document.getElementById("val_17").innerHTML = person1["mobile phone"];
  document.getElementById("type_17").innerHTML = typeof person1["mobile phone"];

  document.getElementById("val_18").innerHTML = person1.name;
  document.getElementById("type_18").innerHTML = typeof person1.name;

  document.getElementById("val_19").innerHTML = person1.age;
  document.getElementById("type_19").innerHTML = typeof person1.age;

  // ============!!!! 자바스크립트에서 사용되어지는 객체 사용법 -2 !!!!================//
  // {속성명1 : 값, 속성명2 : 값, 속성명3 : 값, 속성명4 : 값} 이 자바스크립트에서 말하는 객체이다.
  const person2 = {
    userid: "eomjh",
    pwd: "abcd1234",
    "mobile phone": "010-3456-7890",
    name: "엄정화",
    age: 27,
  };

  document.getElementById("type_20").innerHTML = typeof person2;

  document.getElementById("val_21").innerHTML = person2.userid;
  document.getElementById("type_21").innerHTML = typeof person2.userid;

  document.getElementById("val_22").innerHTML = person2.pwd;
  document.getElementById("type_22").innerHTML = typeof person2.pwd;

  document.getElementById("val_23").innerHTML = person2["mobile phone"];
  document.getElementById("type_23").innerHTML = typeof person2["mobile phone"];

  document.getElementById("val_24").innerHTML = person2.name;
  document.getElementById("type_24").innerHTML = typeof person2.name;

  document.getElementById("val_25").innerHTML = person2.age;
  document.getElementById("type_25").innerHTML = typeof person2.age;

  /////////////////////////////////////////////////////////////////////////
  //==== !!!! 자바스크립트에서는 함수도 변수에 넣을 수 있다.!!!!====//
  //자바스크립트에서 함수를 선언하는 방법1 : 함수 표현식(function expression) 방법

  const func_sum = function (num1, num2) {
    if (isNaN(num1)) {
      alert("첫번째 입력값은 숫자이어야 합니다.");
      return;
    }

    if (isNaN(num2)) {
      alert("두번째 입력값은 숫자이어야 합니다.");
      return;
    }
    return Number(num1) + Number(num2);
  };

  //함수 호출 방법
  const val_26 = document.getElementById("val_26").innerText;
  const val_27 = document.getElementById("val_27").innerText;

  document.getElementById("result_27").innerHTML = func_sum(val_26, val_27);

  console.log(typeof func_sum);

  document.getElementById("type_27").innerHTML = typeof func_sum;

  const val_28 = document.getElementById("val_28").innerText;
  const val_29 = document.getElementById("val_29").innerText;

  document.getElementById("result_29").innerHTML = func_minus(val_28, val_29);

  // === "화살표 함수" 란?
  // ES6(ECMAScript 6)에서 처음으로 소개된 것으로써 화살표(=>)를 사용하여 함수를 선언하는 방법이다.
  // 다른말로 람다 표현식(lambda expression)이라고 부른다.
  // 이것은 function 과 return 을 생략하기 위해서 개발한 것이다.
  const func_multiply_1 = (num1, num2) => {
    if (isNaN(num1)) {
      alert("첫번째 입력값은 숫자이어야 합니다.");
      return;
    }

    if (isNaN(num2)) {
      alert("두번째 입력값은 숫자이어야 합니다.");
      return;
    }
    // 앞뒤에 백틱 `` 을 쓰고 백틱 속에 ${} 을 쓰고 {}속에 변수를 넣어준다.
    return `${Number(num1) * Number(num2)}`;
  };

  const func_multiply_2 = (num1, num2) => {
    return `${Number(num1) * Number(num2)}`;
  };
  // 함수의 내용물이 return 값만 있을 경우에는 {} 와 return 을 생략할 수 있다.
  const func_multiply_3 = (num1, num2) => `${Number(num1) * Number(num2)}`;

  //매개변수가 하나일 경우에는 ()를 생략할 수 있다.
  const func_multiply_4 = (num) => `${Number(num) * 5}`;
  // 매개변수가 없을 경우에는 ()를 사용해야 하며, return 이 있을 경우 {} 를 사용해야 한다.
  const func_multiply_5 = () => {
    return 200;
  };

  const n1 = Number(document.getElementById("val_30").innerText);
  const n2 = Number(document.getElementById("val_31").innerText);

  document.getElementById("result_32").innerHTML = func_multiply_1(n1, n2);
  document.getElementById("result_33").innerHTML = func_multiply_2(n1, n2);
  document.getElementById("result_34").innerHTML = func_multiply_3(n1, n2);
  document.getElementById("result_35").innerHTML = n1 * func_multiply_4(6);
  document.getElementById("result_36").innerHTML = func_multiply_5() * 3;

  document.getElementById("type_37").innerHTML = typeof func_multiply_4;

  /////////////////////////////////////////////////////////////////////////
  const val_32 = document.getElementById("val_32").innerText;
  const num_val_32 = Number(document.getElementById("val_32").innerText);

  document.getElementById("result_37").innerHTML = val_32 == num_val_32;
  document.getElementById("result_38").innerHTML = val_32 === num_val_32;
  console.log(typeof (val_32 === num_val_32));
  document.getElementById("type_38").innerHTML = typeof (val_32 === num_val_32);
}; //end window.onload--------------------------------------------------------

//자바스크립트에서 함수를 선언하는 방법2 : 함수 선언식(function declaration) 방법
function func_minus(num1, num2) {
  if (isNaN(num1)) {
    alert("첫번째 입력값은 숫자이어야 합니다.");
    return;
  }

  if (isNaN(num2)) {
    alert("두번째 입력값은 숫자이어야 합니다.");
    return;
  }
  return Number(num1) - Number(num2);
}
