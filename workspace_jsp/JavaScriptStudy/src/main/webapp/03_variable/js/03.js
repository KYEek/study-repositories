/**
 *
 */

function func_1() {
  var num1 = 10;
  var num2 = 20;

  {
    var num1 = 30;
  }

  var sum = num1 + num2;
  //   50 =  30  +  20
  document.getElementById("span1").innerHTML = sum;
}

function func_2() {
  const num1 = 10;
  const num2 = 20;

  {
    const num1 = 30;
    // { } block 속에 선언된 const num1 은 { } 내에서만 사용가능한 것이며, { } 을 벗어나는 순간 자동적으로 소멸 되어진다.
    // 그러므로 { } 내에서는 새로 선언이 가능하다.!!
    // const num1 = 50; // Error
    const sum = num1 + num2;
    // 50 = 30 + 20
    // { } block 속에 선언된 const sum 은 { } 내에서만 사용가능한 것임.
    // { } 을 벗어나는 순간 const sum 은 자동적으로 소멸 되어진다.

    // sum = num1 + num2
    // const sum에 대한 값을 변경하려고 하면 Error 가 발생한다.
    document.getElementById("span2-1").innerHTML = sum;
  }
  //   const sum = num1 + num2;
  //   50 =  30  +  20
  const sum = num1 + num2;
  document.getElementById("span2-2").innerHTML = sum;
}

function func_3() {
  let num1 = 10;
  let num2 = 20;

  {
    let num1 = 30;
    // { } block 속에 선언된 let num1 은 { } 내에서만 사용가능한 것이며, { } 을 벗어나는 순간 자동적으로 소멸 되어진다.
    // 그러므로 { } 내에서는 새로 선언이 가능하다.!!
    // const num1 = 50; // Error
    let sum = num1 + num2;
    // 50 = 30 + 20
    // { } block 속에 선언된 let sum 은 { } 내에서만 사용가능한 것임.
    // { } 을 벗어나는 순간 let sum 은 자동적으로 소멸 되어진다.

    // sum = num1 + num2
    // let sum에 대한 값을 변경하려고 하면 Error 가 발생한다.
    document.getElementById("span2-1").innerHTML = sum;
  }
  //   let sum = num1 + num2;
  //   50 =  30  +  20
  let sum = num1 + num2;
  document.getElementById("span2-2").innerHTML = sum;
}

function func_4() {
  const num1 = 10;

  let num2 = 20;

  //   num1 += num2; // num1 = num1 + num2;
  //num1은 const로 선언되어 있기 때문에 값을 변경하려고 하면 Error가 발생한다.
  num2 += num1; // num2 = num2 + num1;
  // num2는 let으로 선언되어 있기 때문에 값을 변경할 수 있다.
}
