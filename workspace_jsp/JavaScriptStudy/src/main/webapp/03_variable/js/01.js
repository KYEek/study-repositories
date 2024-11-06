/**
 *
 */

window.onload = function () {
  // 문서가 로딩이 끝난 다음에 자동적으로 수행해야할 일들은 여기서 기술해준다.
  document.getElementById("num1").focus();
  // focus() : 해당 요소에 포커스(커서)를 준다.

  //   자바스크립스테에서 선언되어지는 변수의 타입은 입력되어지는 데이터 값에 의해서 결정된다.
  var a = 10;
  console.log("변수 a의 타입 : ", typeof a);
  //변수 a의 타입 : number

  console.log("변수 a의 값 : ", a);
  //변수 a의 값 : 10

  var a = 10.1234;
  console.log("변수 a의 타입 : ", typeof a);
  //변수 a의 타입 : number

  console.log("변수 a의 값 : ", a);
  //변수 a의 값 : 10.1234

  var a = "안녕하세요";

  console.log("변수 a의 타입 : ", typeof a);
  //변수 a의 타입 : string

  console.log("변수 a의 값 : ", a);
  //변수 a의 값 : 안녕하세요

  var a = "즐거운 하루 되세요";

  console.log("변수 a의 타입 : ", typeof a);
  //변수 a의 타입 : string

  console.log("변수 a의 값 : ", a);
  //변수 a의 값 : 즐거운 하루 되세요

  var a = true;

  console.log("변수 a의 타입 : ", typeof a);
  //변수 a의 타입 : boolean

  console.log("변수 a의 값 : ", a);
  //변수 a의 값 : true
};

function func_sum_error() {
  // 웹에서 입력되어지는 모든 데이터는 문자열로 인식된다.
  var num1 = document.getElementById("num1").value;
  var num2 = document.getElementById("num2").value;

  var sum = num1 + num2;
  // 자바스크립트에서 + 는 모두 number 타입일 때만 더하기 연산을 수행한다.
  // 자바스크립트에서 + 는 문자열이 포함되어 있을 경우에는 문자열을 연결하는 연산을 수행한다.
  console.log("확인용 sum 타입 : ", typeof sum); //typeof : 변수의 타입을 확인하는 함수

  document.getElementById("sum").innerHTML =
    "<span style='color:red; font-weight:bold;'>" + sum + "</span>";

  //   document.getElementById("sum").innerHTML = (
  //     <span style="color:red; font-weight:bold;"> + sum+ </span>
  //   );
} //end of func_sum_error--------------------------------------

function func_sum_correct() {
  // 웹에서 입력되어지는 모든 데이터는 문자열로 인식된다.
  var num1 = document.getElementById("num1").value;
  var num2 = document.getElementById("num2").value;

  var sum = num1 + num2;

  // console.log("확인용 num1 타입 : ", typeof num1);
  // //확인용 num1 타입 : string
  // //   console.log("확인용 num1 타입 : ", isNaN(num1)); //isNaN : 숫자인지 아닌지 확인하는 함수, Not a Number의 약자
  // console.log("확인용 num1 타입 : ", typeof Number(num1));
  // //확인용 num1 타입 : number
  // console.log("확인용 num1 타입 : ", isNaN(num1));

  // console.log("확인용 num1 타입 : ", isNaN(Number(num1)));
  // //isNaN : 타입이 아니라 값이 숫자인지 아닌지 확인하는 함수, 숫자로 변환할 수 없는 문자열이면 true, 숫자로 변환할 수 있는 문자열이면 false

  // console.log("확인용 num2 타입 : ", Number(num2)); //Number : 문자열을 숫자로 변환하는 함수

  if (isNaN(num1) || isNaN(num2)) {
    alert("입력하시는 값은 두개 모두 숫자로만 입력하셔야 합니다.");
  }

  document.getElementById("sum").innerHTML =
    "<span style='color:red; font-weight:bold;'>" + sum + "</span>";

  //   document.getElementById("sum").innerHTML = (
  //     <span style="color:red; font-weight:bold;"> + sum+ </span>
  //   );
} //end of func_sum_error--------------------------------------

function my_lear() {
  document.getElementById("number3").innerHTML = "";
  document.getElementById("number4").innerHTML = "";
  document.getElementById("sum2").innerHTML = "";
}

function minus_correct() {
  var num3 = document.getElementById("number3").value;
  var num4 = document.getElementById("number4").value;

  if (isNaN(num3) || isNaN(num4)) {
    alert("입력하시는 값은 두개 모두 숫자로만 입력하셔야 합니다.");
    my_clear();
    return; //munus_함수의 종료
  }
  // var는 이미 선언되어진 것을 재선언 할 수 있따
  var result = Number(num3) - Number(num4);
  console.log("확인용 result 타입 : ", result);
  document.getElementById("sum2").innerHTML =
    "<span style ='color:green; font-weight:bold; font-size:20pt;'>" +
    result +
    "</span>";
} //end of minus_correct--------------------------------------
