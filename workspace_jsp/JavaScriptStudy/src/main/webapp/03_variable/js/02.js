/**
 *
 */

function func_sum_correct() {
  // 웹에서 입력되어지는 모든 데이터는 문자열로 인식된다.
  let num1 = document.getElementById("num1").value;
  let num2 = document.getElementById("num2").value;

  let sum = num1 + num2;

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

  document.getElementById("num3").focus();
}

function func_minus2() {
  let num3 = document.getElementById("num3").value;

  console.log("num 3 : ", num3);
  let num4 = document.getElementById("num4").value;

  if (isNaN(num3) || isNaN(num4)) {
    alert("입력하시는 값은 두개 모두 숫자로만 입력하셔야 합니다.");
    my_clear();
    return; //munus_함수의 종료
  }
  // var는 이미 선언되어진 것을 재선언 할 수 있따
  let result = Number(num3) - Number(num4);
  console.log("확인용 result 타입 : ", result);
  document.getElementById("minus").innerHTML =
    "<span style ='color:green; font-weight:bold; font-size:20pt;'>" +
    result +
    "</span>";
} //end of minus_correct--------------------------------------
