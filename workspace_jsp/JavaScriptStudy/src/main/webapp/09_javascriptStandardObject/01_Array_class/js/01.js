/**
 *
 */
window.onload = function () {
  // === 1. 자바스크립트에서 배열은 아래와 같이 나타냅니다. === //
  // const arr_fruit1 = []; // 배열도 객체이다.
  // 또는
  const arr_fruit1 = new Array(); // 배열도 객체이다.

  let len = arr_fruit1.push("사과"); // 배열명.push() : 배열의 마지막에 새로운 요소를 추가한 후, 변경된 배열의 길이를 리턴시켜줌.
  len = arr_fruit1.push("딸기"); // 배열명.push() : 배열의 마지막에 새로운 요소를 추가한 후, 변경된 배열의 길이를 리턴시켜줌.
  arr_fruit1.push("메론"); // 배열명.push() : 배열의 마지막에 새로운 요소를 추가한 후, 변경된 배열의 길이를 리턴시켜줌.
  arr_fruit1.push("참외"); // 배열명.push() : 배열의 마지막에 새로운 요소를 추가한 후, 변경된 배열의 길이를 리턴시켜줌.
  len = arr_fruit1.push("수박"); // 배열명.push() : 배열의 마지막에 새로운 요소를 추가한 후, 변경된 배열의 길이를 리턴시켜줌.

  console.log("1. 배열 arr_fruit1 의 길이 => ", arr_fruit1.length);
  // 1. 배열 arr_fruit1 의 길이 =>  5
  console.log("2. 변수 len 의 값 => ", len);
  // 2. 변수 len 의 값 =>  5

  // ==== !!!! 자바스크립트에서 사용되어지는 for 문 !!!! ==== //
  let html_1 = `<ol>`;
  for (let i = 0; i < arr_fruit1.length; i++) {
    html_1 += `<li>${arr_fruit1[i]}</li>`;
  } // end of for
  html_1 += `</ol>`;

  document.querySelector("div#fruitDisplay1").innerHTML = html_1;

  ////////////////////////////////////////////////////////////
  let html_2 = `<ol>`;
  for (let i = 0; i < arr_fruit1.length; i++) {
    html_2 += `<li>${arr_fruit1[i]}</li>`;
  } // end of for
  html_2 += `</ol>`;

  document.querySelector("div#fruitDisplay2").innerHTML = html_2;

  ////////////////////////////////////////////////////////////

  let html_3 = `<ol>`;
  for (let item of arr_fruit1) {
    // [참고] for ... of 는 배열값을 가져올때 사용하는 것이고,  for ... in 은 객체의 속성목록을 가져올때 사용하는 것이다.
    // 배열도 객체인데 배열의 속성은 바로 인덱스번호 이다. 그래서 of 대신에 in 을 사용하면 배열요소의 인덱스번호가 나온다.
    html_3 += `<li>${item}</li>`;
  } // end of for
  html_3 += `</ol>`;

  document.querySelector("div#fruitDisplay3").innerHTML = html_3;

  ////////////////////////////////////////////////////////////

  //  배열명.forEach(function(item, index, array){});
  /*  item  - 현재 처리 중인 배열의 각 요소  
         index - (optional) 처리 중인 요소의 인덱스 
         array - (optional) forEach()가 적용되고 있는 배열 */

  let html_4 = `<ol>`;
  arr_fruit1.forEach(function (item, index, array) {
    html_4 += `<li>${item}</li>`;
  });
  html_4 += `</ol>`;

  document.querySelector("div#fruitDisplay4").innerHTML = html_4;

  ////////////////////////////////////////////////////////////

  let html_5 = `<ol>`;
  arr_fruit1.forEach(function (item, index, array) {
    html_5 += `<li>${array[index]}</li>`;
  });
  html_5 += `</ol>`;

  document.querySelector("div#fruitDisplay5").innerHTML = html_5;

  ////////////////////////////////////////////////////////////

  let html_6 = `<ol>`;
  arr_fruit1.forEach((item) => {
    html_6 += `<li>${item}</li>`;
  });
  html_6 += `</ol>`;

  document.querySelector("div#fruitDisplay6").innerHTML = html_6;

  ////////////////////////////////////////////////////////////

  let html_7 = `<ol>`;
  arr_fruit1.forEach((item) => {
    // 파라미터가 1개 밖에 없으면 ()를 생략할 수 있다.
    html_7 += `<li>${item}</li>`;
  });
  html_7 += `</ol>`;

  document.querySelector("div#fruitDisplay7").innerHTML = html_7;

  ////////////////////////////////////////////////////////////

  let html_8 = `<ol>`;
  arr_fruit1.forEach((item) => (html_8 += `<li>${item}</li>`));
  // 처리해야할 내용이 1개밖에 없으면 { } 를 생략할 수 있다.

  html_8 += `</ol>`;

  document.querySelector("div#fruitDisplay8").innerHTML = html_8;

  ////////////////////////////////////////////////////////////

  // === 2. 자바스크립트에서 배열은 아래와 같이 나타냅니다. === //
  const arr_fruit = ["사과", "딸기", "메론", "참외", "수박"]; // 배열도 객체이다.

  let html = `<ol>`;
  arr_fruit.forEach((item) => (html += `<li>${item}</li>`));
  // 처리해야할 내용이 1개밖에 없으면 { } 를 생략할 수 있다.

  html += `</ol>`;

  const fruitDisplay = document.querySelector("div#fruitDisplay");
  fruitDisplay.innerHTML = html;

  ////////////////////////////////////////////////////////////

  // ======== 과일을 맨 마지막에 추가하기[push()] 시작 ======== //
  document.querySelector("button[id = 'btn_last_add']").onclick = function () {
    const addVal = document.querySelector("input[id= 'addVal']").value;

    if (addVal.trim() == "") {
      // "문자열".trim()은 문자열의 좌,우의 공백이 있으면 좌,우 공백을 모두 제거해주는 것이다.
      alert("경고: 다른 과일명을 입력하세요");
      document.querySelector('input[id="addVal"]').value = "";
      document.querySelector('input[id="addVal"]').focus();
      return; // 종료
    }
    arr_fruit.push(addVal.trim()); // 배열명.push() : 배열의 마지막에 새로운 요소를 추가한 후, 변경된 배열의 길이를 리턴시켜줌.
    //    console.log(arr_fruit); // (6) ['사과', '딸기', '메론', '참외', '수박', '바나나']

    func_view_1(arr_fruit, fruitDisplay);
    document.querySelector('input[id="addVal"]').value = "";
    document.querySelector('input[id="addVal"]').focus();
  };

  // === Function Expression(함수 표현식) === //
  const func_view_1 = function (arr, displayid) {
    let html = `<ol>`;
    for (let item of arr) {
      html += `<li>${item}</li>`;
    } // end of for
    html += `</ol>`;

    displayid.innerHTML = html;
  };

  // ======== 과일을 맨 마지막에 추가하기[push()] 끝 ======== //

  // ======== 과일을 맨 마지막에 추가하기[unshift()] 시작 ======== //
  document.querySelector("button[id = 'btn_last_add']").onclick = function () {
    const addVal = document.querySelector("input[id= 'addVal']").value;

    if (addVal.trim() == "") {
      // "문자열".trim()은 문자열의 좌,우의 공백이 있으면 좌,우 공백을 모두 제거해주는 것이다.
      alert("경고: 다른 과일명을 입력하세요");
      document.querySelector('input[id="addVal"]').value = "";
      document.querySelector('input[id="addVal"]').focus();
      return; // 종료
    }
    arr_fruit.unshift(addVal.trim()); // 배열명.push() : 배열의 마지막에 새로운 요소를 추가한 후, 변경된 배열의 길이를 리턴시켜줌.
    //    console.log(arr_fruit); // (6) ['사과', '딸기', '메론', '참외', '수박', '바나나']

    func_view_1(arr_fruit, fruitDisplay);
    document.querySelector('input[id="addVal"]').value = "";
    document.querySelector('input[id="addVal"]').focus();
  };

  // === Function Expression(함수 표현식) === //
  //   const func_view_1 = function (arr, displayid) {
  //     let html = `<ol>`;
  //     for (let item of arr) {
  //       html += `<li>${item}</li>`;
  //     } // end of for
  //     html += `</ol>`;

  //     displayid.innerHTML = html;
  //   };

  // ======== 과일을 맨 마지막에 추가하기[push()] 끝 ======== //
  // ====== 과일을 배열의 특정 위치에 추가하기[splice()] 시작 ====== //
  document.querySelector("button[id = 'bn_speciatl_add']").onclick =
    function () {
      // 배열명.splice() : 배열의 특정 위치에 배열 요소를 추가하거나 삭제하는데 사용한다.
      //                  삭제할 경우 리턴값은 삭제한 배열 요소이다. 삭제한 요소가 없으면 빈 배열( [] )을 반환한다.
      /*
       // 배열명.splice(start, 0, element);  // 배열의 특정 위치에 배열 요소를 추가하는 경우 
                      start   - 수정할 배열 요소의 인덱스
                        0       - 요소를 추가할 경우
                        element - 배열에 추가될 요소
              
         // 배열명.splice(start, deleteCount); // 배열의 특정 위치의 배열 요소를 삭제하는 경우    
                         start   - 수정할 배열 요소의 인덱스
                         deleteCount - 삭제할 요소 개수
       */
      //["사과", "딸기", "메론", "참외", "수박"];
      console.log(arr_fruit);
      arr_fruit.splice(2, 0, "밤", "감", "대추");
      // 배열 arr_fruit의 인덱스 번호 2
      func_view_1(arr_fruit, fruitDisplay);
      document.querySelector('input[id="addVal"]').value = "";
      document.querySelector('input[id="addVal"]').focus();
    };

  // ====== 과일을 배열의 특정 위치에 추가하기[splice()] 끝 ====== //
  // ====== 배열의 맨 마지막 요소 삭제하기[pop()] 시작 ====== //

  document.querySelector("button[id = 'btn_last_del']").onclick = function () {
    const deleted_log = arr_fruit.pop(); // 배열명.pop() 은 배열의 마지막 요소를 제거한 후, 제거한 요소를 리턴시켜줌.
    console.log("deleted_item    => ", deleted_log);
    if (deleted_log != undefined) {
      alert(`배열 arr_fruit 에서${deleted_log}을(를) 삭제하였습니다. `);
    } else {
      alert(`더이상 삭제할 과일이 없습니다..`);
    }
    func_view_1(arr_fruit, fruitDisplay);
  };
  // ====== 배열의 맨 마지막 요소 삭제하기[pop()] 끝 ====== //

  // ====== 배열의 맨 처음 요소 삭제하기[shift()] 시작 ====== //

  document.querySelector("button[id = 'btn_first_del']").onclick = function () {
    const deleted_log = arr_fruit.shift(); // 배열명.shift() 은 배열의 마지막 요소를 제거한 후, 제거한 요소를 리턴시켜줌.
    console.log("deleted_item    => ", deleted_log);
    if (deleted_log != undefined) {
      alert(`배열 arr_fruit 에서${deleted_log}을(를) 삭제하였습니다. `);
    } else {
      alert(`더이상 삭제할 과일이 없습니다..`);
    }
    func_view_1(arr_fruit, fruitDisplay);
  };
  // ====== 배열의 맨 처음 요소 삭제하기[shift()] 끝 ====== //

  // ====== 배열의 특정 위치에 있는 요소 삭제하기[splice()] 시작 ====== //
  document.querySelector("button[id = 'btn_special_del']").onclick =
    function () {
      // 배열명.splice() : 배열의 특정 위치에 배열 요소를 추가하거나 삭제하는데 사용한다.
      //                  삭제할 경우 리턴값은 삭제한 배열 요소이다. 삭제한 요소가 없으면 빈 배열( [] )을 반환한다.
      /*
       // 배열명.splice(start, 0, element);  // 배열의 특정 위치에 배열 요소를 추가하는 경우 
                      start   - 수정할 배열 요소의 인덱스
                        0       - 요소를 추가할 경우
                        element - 배열에 추가될 요소
              
         // 배열명.splice(start, deleteCount); // 배열의 특정 위치의 배열 요소를 삭제하는 경우    
                         start   - 수정할 배열 요소의 인덱스
                         deleteCount - 삭제할 요소 개수
       */
      //["사과", "딸기", "메론", "참외", "수박"];
      console.log(arr_fruit);
      const deleted_item = arr_fruit.splice(1, 3);
      // 배열 arr_fruit의 인덱스 번호 1부터 3개를 삭제한다.
      console.log(arr_fruit);

      let func_msg = function () {
        let msg = "";

        if (deleted_item.length == 0) {
          msg = "삭제할 과일이 없습니다.";
          return msg;
        }
        for (let i = 0; i < deleted_item.length; i++) {
          if (i < deleted_item.length - 1) {
            msg += deleted_item[i] + ",";
          } else {
            msg += deleted_item[i];
          }
        }

        msg += " 을(를) 삭제하였습니다.";
        return msg;
      };
      alert(func_msg());
      func_view_1(arr_fruit, fruitDisplay);
      document.querySelector('input[id="addVal"]').value = "";
      document.querySelector('input[id="addVal"]').focus();
    };

  // ====== 배열의 특정 위치에 있는 요소 삭제하기[splice()] 끝 ====== //

  // ====== 배열의 요소를 역순으로 만들기 (배열명.reverse()) 시작 ====== //

  document.querySelector("button[id = 'btn_sort']").onclick = function () {
    arr_fruit.sort(); // 배열의 요소를 오름차순으로 정렬한다.
    func_view_1(arr_fruit, fruitDisplay);
  };
  // ====== 배열의 요소를 역순으로 만들기 (배열명.reverse()) 끝 ====== //

  // ====== 배열의 요소를 역순으로 만들기 (배열명.reverse()) 시작 ====== //

  document.querySelector("button[id = 'btn_sort_reverse']").onclick =
    function () {
      arr_fruit.reverse(); // 배열의 요소를 오름차순으로 정렬한다.
      func_view_1(arr_fruit, fruitDisplay);
    };
  // ====== 배열의 요소를 역순으로 만들기 (배열명.reverse()) 끝 ====== //
  //////////////////////////////////////////////////////////////////////////////

  // ===== 숫자배열 ===== //
  const num_arr = [10, 1, 50, 11, 109, 1004, 20];

  //이거는 필수 암기
  // === 배열명.join("구분문자"); === //
  /*  배열명.join("구분문자"); 는 구분문자를 사용해서 배열을 하나의 문자열로 만들어 반환해주는 것이다.
          만약에 "구분문자"를 지정해주지 않으면 즉, 배열명.join(); 으로 하면 구분문자는 자동적으로 콤마(,)로 되어진다. */
  let str_num = num_arr.join(" / ");
  console.log("str_num = ", str_num); // 10 /1 /50 /11 /109 /1004 /20
  console.log("typeof str_num = ", typeof str_num);

  str_num = num_arr.join(" , ");
  console.log("str_num = ", str_num); // 10 /1 /50 /11 /109 /1004 /20
  console.log("typeof str_num = ", typeof str_num);

  document.querySelector("div#number_display").innerHTML = str_num;

  // ==== 숫자로 되어진 배열의 요소를 오름차순 정렬하기(틀린것) 시작 ==== //
  document.querySelector("button#btn_asc_sort_number_fail").onclick =
    function () {
      document.querySelector("div#number_sort_display").innerHTML = num_arr
        .sort()
        .join(" , ");
      //배열명.sort()는 배열의 요소를 문자열로 변환한 후, 유니코드의 코드 포인트 순서로 정렬한다.
      // 현재 배열의 요소의 타입이 숫자이므로 배열명.sort()를 해버리면 숫자를 문자열로 변환시켜 버리므로
      // ["10","1","50","11","109","1004","20"] 으로 되어진 후 오름차순 정렬시키므로
      // ["1","10","1004","109","11","20","50"] 으로 된다.
      // 그런다음에 하나의 문자열로 합쳐서 보여주므로
      // 1 , 10 , 1004 , 109 , 11 , 20 , 50 으로 보여지게 된다.
    };

  // ==== 숫자로 되어진 배열의 요소를 오름차순 정렬하기(틀린것) 끝 ==== //

  // ==== 숫자로 되어진 배열의 요소를 오름차순 정렬하기(옳은 것) 시작 ==== //
  document.querySelector("button#btn_asc_sort_number_success").onclick =
    function () {
      document.querySelector("div#number_sort_display").innerHTML = num_arr
        .sort(function (a, b) {
          return a - b;
        })
        .join(" , ");
      // 배열요소가 숫자인 경우 문자열이 아닌 숫자로 오름차순 정렬하고자 하고자 한다라면 sort() 괄호 속에
      // function(a,b){return a-b;} 을 넣어줌으로써 해결해준다.
      // return a-b; 의 결과값이 음수 이라면  a 가 낮은 인덱스로 정렬된다.
      // return a-b; 의 결과값이  0 이라면  a 와 b 의 순서는 바뀌지 않는다.
      // return a-b; 의 결과값이 양수 이라면  b 가 낮은 인덱스로 정렬된다.
    };

  // ==== 숫자로 되어진 배열의 요소를 오름차순 정렬하기(옳은 것) 끝 ==== //

  // ==== 숫자로 되어진 배열의 요소를 내림차순 정렬하기(옳은 것) 시작 ==== //
  document.querySelector("button#btn_asc_sort_number_success").onclick =
    function () {
      document.querySelector("div#number_sort_display").innerHTML = num_arr
        .sort(function (a, b) {
          return b - a;
        })
        .join(" , ");
      // 배열요소가 숫자인 경우 문자열이 아닌 숫자로 내림차순 정렬하고자 하고자 한다라면 sort() 괄호 속에
      // function(a,b){return b-a;} 을 넣어줌으로써 해결해준다.
      // return a-b; 의 결과값이 음수 이라면  a 가 낮은 인덱스로 정렬된다.
      // return a-b; 의 결과값이  0 이라면  a 와 b 의 순서는 바뀌지 않는다.
      // return a-b; 의 결과값이 양수 이라면  b 가 낮은 인덱스로 정렬된다.
    };

  // ==== 숫자로 되어진 배열의 요소를 내림차순 정렬하기(옳은 것) 끝 ==== //

  // ==== 이름 오름차순 정렬하기 시작 ==== //
  document.querySelector("button#btn_name_sort_asc").onclick = function () {
    const s_irum = document.querySelector("div#irum").innerText;
    console.log("s_irum = ", s_irum);
    // 배열요소가 숫자인 경우 문자열이 아닌 숫자로 내림차순 정렬하고자 하고자 한다라면 sort() 괄호 속에
    // function(a,b){return b-a;} 을 넣어줌으로써 해결해준다.
    // return a-b; 의 결과값이 음수 이라면  a 가 낮은 인덱스로 정렬된다.
    // return a-b; 의 결과값이  0 이라면  a 와 b 의 순서는 바뀌지 않는다.
    // return a-b; 의 결과값이 양수 이라면  b 가 낮은 인덱스로 정렬된다.

    document.querySelector("div#irum_sort_result").innerHTML = s_irum
      .split(",")
      .sort()
      .join(" , ");
  };

  // ==== 이름 오름차순 정렬하기 끝 ==== //

  // ==== 이름 내림차순 정렬하기 시작 ==== //
  document.querySelector("button#btn_name_sort_asc").onclick = function () {
    const s_irum = document.querySelector("div#irum").innerText;
    console.log("s_irum = ", s_irum);
    // 배열요소가 숫자인 경우 문자열이 아닌 숫자로 내림차순 정렬하고자 하고자 한다라면 sort() 괄호 속에
    // function(a,b){return b-a;} 을 넣어줌으로써 해결해준다.
    // return a-b; 의 결과값이 음수 이라면  a 가 낮은 인덱스로 정렬된다.
    // return a-b; 의 결과값이  0 이라면  a 와 b 의 순서는 바뀌지 않는다.
    // return a-b; 의 결과값이 양수 이라면  b 가 낮은 인덱스로 정렬된다.

    document.querySelector("div#irum_sort_result").innerHTML = s_irum
      .split(",")
      .reverse()
      .join(" , ");
  };

  document.querySelector("button#btn_food_list_1").onclick = function () {
    const food_list = document.querySelector("div#food").textContent;
    let result = "<ol>";
    for (let item of food_list.split(",")) {
      result += `<li>${item}</li>`;
    }
    result += "</ol>";

    document.querySelector("div#food_list").innerHTML = result;
  };

  document.querySelector("button#btn_food_clear").onclick = function () {
    document.querySelector("div#food_list").innerHTML = "";
  };

  // ==== 이름 내림차순 정렬하기 끝 ==== //

  document.querySelector("button#btn_food_list_3").onclick = function () {
    const food_list = document.querySelector("div#food").textContent;

    console.log(food_list.split(","));
    console.log(food_list.split(",").slice(1, 6)); // 1번째 인덱스부터 6번째 인덱스까지 가져온다.
    // 배열명.slice() 메서드는 인수를 통해 지정한 만큼의 요소를 잘라낸 후 배열로 반환해준다.
    // 잘라내어진 원본 배열은 잘라낸 만큼 줄어드는 것이 아니라 그대로 이다.
    // 이때 첫 번째 인자는 잘라낼 위치의 인덱스 숫자를 지정하며, 두 번째 인자의 인덱스 숫자의 바로 앞 요소까지를 잘라낼 요소로 지정한다.
    let result = "<ol>";
    for (let item of food_list.split(",").slice(1, 6)) {
      result += `<li>${item}</li>`;
    }
    result += "</ol>";

    document.querySelector("div#food_list").innerHTML = result;
  };

  document.querySelector("button#btn_food_list_4").onclick = function () {
    const food_list = document.querySelector("div#food").textContent;

    console.log(food_list.split(","));
    console.log(food_list.split(",").slice(1)); // 1번째 인덱스부터 6번째 인덱스까지 가져온다.

    let result = "<ol>";
    for (let item of food_list.split(",").slice(1)) {
      result += `<li>${item}</li>`;
    }
    result += "</ol>";

    document.querySelector("div#food_list").innerHTML = result;
  };

  // === [중요] 자바스크립트에서 배열은 반드시 동일한 데이터 타입만 들어오는 것이 아니라 서로 다른 데이터 타입을 가지는 데이터도 들어올 수 있다.!!! === //
  const data_arr = [
    1234,
    "java",
    100,
    true,
    1234,
    "html",
    "java",
    "java",
    { userid: "leess", passwd: "qwer1234", name: "이순신" },
  ];

  for (let item of data_arr) {
    console.log(item);
  } // end of for-------------------

  /*
            1234
            java
            100
            true
            1234
            html
            java
            java
            {userid: 'leess', passwd: 'qwer1234', name: '이순신'}
        */

  console.log("\n=========================================\n");

  const person = data_arr[data_arr.length - 1];
  // {userid: 'leess', passwd: 'qwer1234', name: '이순신'}

  // for(.. of ..) 는 배열값을 가져올때 사용하는 것이고,
  // for(.. in ..) 는 객체의 속성목록을 가져올때 사용하는 것이다.
  for (let property_name in person) {
    console.log(property_name);
  } // end of for-------------------
  console.log("\n=========================================\n");

  // !!! [중요] !!!
  // 객체의 속성에 해당하는 값을 읽어오는 방법에 있어서 속성명이 변수로 되어져 있을 경우에는 .표기법이 아닌 대괄호 표기법을 사용해야 한다.
  // 즉, 속성명이 변수로 되어져 있을 경우 속성명에 해당하는 값을 읽어오기 위해서는 객체.속성명 이 아닌 객체[속성명] 으로 해야만 한다.

  for (let property_name in person) {
    //console.log(person.property_name);
    console.log(person[property_name]);
  } // end of for-------------------
  //console.log(person.property_name); 이거를 쓰면 결과값이 undefined 가 나온다.
  // undefined
  // undefined
  // undefined

  console.log("\n=========================================\n");
  const person_arr = [
    { photo: "iyou.jpg", userid: "iyou", passwd: "qwer1234", name: "아이유" },
    { photo: "kimth.jpg", userid: "kimth", passwd: "qwer1234", name: "김태희" },
    {
      photo: "parkby.jpg",
      userid: "parkby",
      passwd: "qwer1234",
      name: "박보영",
    },
  ];
  //여기서 {}는 하나의 객체를 의미한다. 자바에서 DTO를 만들때 사용하는 것과 같다.

  let person_html = ``;
  for (let item of person_arr) {
    //console.log(item);
    person_html += `<div style ='margin-right: 5%;'><ul style='list-style-type:none;'>`;
    for (let property_name in item) {
      //console.log(item[property_name]);

      /*
               iyou.jpg
               iyou
               qwer1234
               아이유
               ------------
               kimth.jpg
               kimth
               qwer1234
               김태희
               ------------
               parkby.jpg
               parkby
               qwer1234
               박보영
            */
      let title;

      switch (property_name) {
        case "photo":
          title = "";
          break;
        case "userid":
          title = "아이디";
          break;
        case "passwd":
          title = "비밀번호";
          break;
        case "name":
          title = "이름";
          break;
        default:
          title = "기타";
          break;
      }
      if (property_name === "photo") {
        person_html += `<li><img width = '119px', height = '149px', src = "images/${item[property_name]}"/></li>`;
      } else {
        person_html += `<li><label class = 'title'>${title}</label> : ${item[property_name]}</li>`;
      }
    }

    person_html += `</ul></div>`;
  }

  document.querySelector("div#person_arr").innerHTML = person_html;
};
