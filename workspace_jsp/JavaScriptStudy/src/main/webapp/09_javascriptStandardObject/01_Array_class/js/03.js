/**
 *
 */

window.onload = function () {
  // === Array.from() === //
  // ES6에 새로 도입된 메소드로서 유사 배열 객체나 반복 가능한 객체를 실제 배열로 변환시켜주는 것이다.

  const li_list = document.querySelectorAll(
    "div#div_fruit > ul:first-child > li"
  );

  console.log(li_list);

  // **** Array.from(유사배열객체) **** //
  const li_arr = Array.from(li_list); //유사배열객체인 li_list를 배열로 변환시킨다.
  console.log(li_arr);
  const fruitname_arr = li_arr.map((elmt) => elmt.innerText);

  console.log(fruitname_arr);
  document.querySelector("div#fruit_hangul").innerTHML =
    fruitname_arr.join(", ");

  // *** Array.from(유사배열객체 , map()메소드와 비슷한 1:1 매핑 시켜주는 함수정의) *** //
  const li_list2 = document.querySelectorAll(
    "div#div_fruit > ul:last-child > li"
  );
  const fruitname2_arr = Array.from(li_list2, function (elmt, index, array) {
    return elmt.innerText;
  });
  //elmt는 배열요소인 li_list2의 요소들을 가리킨다.
  console.log(fruitname2_arr);

  document.querySelector("div#fruit_english").innerHTML =
    fruitname2_arr.join(", ");

  const fruitname3_arr = Array.from(li_list2, (elmt) => elmt.innerText);
  //elmt는 배열요소인 li_list2의 요소들을 가리킨다.
  console.log(fruitname2_arr);

  document.querySelector("div#fruit_english2").innerHTML =
    fruitname3_arr.join(", ");

  // ==== td 태그에 나열된 점수를 가져와서 합계 및 평균 구하기 시작 ==== //
  const td_list = document.querySelectorAll(
    "table#tbl > tbody > tr > td:last-child"
  );
  console.log(td_list);

  const point_arr = Array.from(td_list, (item) => item.innerText);
  console.log(point_arr);

  let sum = 0;
  for (let point of point_arr) {
    sum += Number(point);
  }
  console.log("sum => ", sum);

  let avg = sum / point_arr.length;
  console.log("avg => ", avg);

  document.querySelector(
    "table#tbl > tfoot > tr:first-child > td:last-child"
  ).innerHTML = sum;
  document.querySelector(
    "table#tbl > tfoot > tr:last-child > td:last-child"
  ).innerHTML = avg;

  ////////////////////////////////////////////////////////////////////
  const arr_member = [
    { userid: "leess", passwd: "qwer1234$", name: "이순신", age: 25 },
    { userid: "eomjh", passwd: "qwer1234$", name: "엄정화", age: 47 },
    { userid: "seokj", passwd: "qwer1234$", name: "서강준", age: 23 },
    { userid: "sunsin", passwd: "qwer1234$", name: "이순신", age: 38 },
    { userid: "youks", passwd: "qwer1234$", name: "유관순", age: 24 },
  ];
  html = `<table>
  <thead>
  <tr>
  <th>아이디</th>
    <th>비밀번호</th>
    <th>성명</th>
    <th>나이</th>
  </tr>
  </thead>
  <tbody>`;

  let cnt = 0;
  for (let member of arr_member) {
    // console.log(member);
    if (member["name"] == "이순신") {
      //   console.log(member);
      cnt++;
      html += `<tr>
          <td>${member["userid"]}</td>
          <td>${member["passwd"]}</td>
          <td>${member["name"]}</td>
          <td>${member["age"]}</td>
          </tr>`;
    }
  }

  if (cnt == 0) {
    html += `<tr>
        <td colspan="4">데이터가 존재하지 않습니다..</td>
        </tr>`;
  }
  document.querySelector("div#quiz").innerHTML = html;

  //여기는 선생님이 만드신 코드
  ////////////////////////////////////////////////////////////////

  const arr_result = [];

  arr_member.forEach((item) => {
    if (item.name == "이순신") {
      arr_result.push(item);
    }
  });

  html = `<table>
<thead>
<tr>
<th>아이디</th>
<th>암호</th>
<th>성명</th>
<th>나이</th>
</tr>
</thead>
<tbody>`;

  if (arr_result.length > 0) {
    arr_result.forEach((data) => {
      html += `<tr>
   <td>${data.userid}</td>
<td>${data.passwd}</td>
<td>${data.name}</td>
<td>${data.age}</td>
</tr>`;
    });
  } else {
    html += `<tr>
<td colspan='4'>데이터가 존재하지 않습니다</td>
</tr>`;
  }

  html += `</tbody>
</table>`;

  document.querySelector("div#quiz").innerHTML = html;

  //////////////////////////////////////////////////////////////

  html2 = `<table>
<thead>
<tr>
<th>아이디</th>
<th>암호</th>
<th>성명</th>
<th>나이</th>
</tr>
</thead>
<tbody>`;

  if (arr_result.length > 0) {
    arr_result.forEach((data) => {
      html2 += `<tr>`;

      for (let property_name in data) {
        html2 += `<td>${data[property_name]}</td>`;
      } // end of for---------------

      html2 += `</tr>`;
    });
  } else {
    html2 += `<tr>
   <td colspan='4'>데이터가 존재하지 않습니다</td>
</tr>`;
  }

  html2 += `</tbody>
</table>`;

  document.querySelector("div#quiz2").innerHTML = html2;
  /////////////////////////////////////////////////////////////

  // ---- **** 배열명.find() **** ----
  // ES6에서 새로 도입된 메소드로서 판별함수의 조건에 만족하는 배열요소가 있으면 첫번째 배열요소를 리턴 시켜주고 끝내고,
  // 판별함수의 조건에 만족하는 배열요소가 없으면 undefinded

  let search_member = arr_member.find(function (item, index, array) {
    // index, atrray는 생략가능하다
    if (item.name == "이순신") {
      return item;
    }
  });

  console.log(search_member);

  // === !!!! 암기  배열명.find() 은 고유한 값(primary key)으로 검색할 때 사용한다. !!! === //
  //그래서 첫번째 배열요소만 리턴시켜주기 때문에 중복된 값이 있을 경우에는 첫번째 배열요소만 리턴시켜준다.
  //중복된 값이 있을 경우에는 filter() 메소드를 사용한다.
  search_member = arr_member.find((item) => {
    if (item.userid == "eomjh") {
      return item;
    }
  });

  console.log(search_member);

  // ---- **** 배열명.filter() **** ----
  // 판별함수의 조건에 만족하는 배열요소가 있으면 모든 배열요소를 담은 배열로 리턴 시켜주고,
  // 판별함수의 조건에 만족하는 배열요소가 없으면 빈배열 [] 을 리턴시켜준다.

  let search_member_arr = arr_member.filter(function (item, index, array) {
    // index, atrray는 생략가능하다
    if (item.name.trim == "이순신") {
      return item;
    }
  });
  console.log(search_member_arr);

  search_member_arr = arr_member.filter((item) => item.name.trim() == "이순신");
  console.log(search_member_arr);

  // search_member_arr = arr_member.filter((item) => item.name.trim() == "연규영");
  // console.log(search_member_arr);

  let html3 = `<table>
<thead>
<tr>
<th>아이디</th>
<th>암호</th>
<th>성명</th>
<th>나이</th>
</tr>
</thead>
<tbody>`;

  if (arr_result.length > 0) {
    search_member_arr.forEach((data) => {
      html3 += `<tr>`;

      for (let property_name in data) {
        html3 += `<td>${data[property_name]}</td>`;
      } // end of for---------------

      html3 += `</tr>`;
    });
  } else {
    html3 += `<tr>
   <td colspan='4'>데이터가 존재하지 않습니다</td>
</tr>`;
  }

  html3 += `</tbody>
</table>`;

  document.querySelector("div#quiz3").innerHTML = html3;

  // ---- **** 배열명.findIndex() **** ----
  // ES6에서 새로 도입된 메소드로서 판별함수의 조건에 만족하는 배열요소가 있으면 첫번째 배열요소의 인덱스번호만 리턴 시켜주고 끝내고,
  // 판별함수의 조건에 만족하는 배열요소가 없으면 -1 이 나온다
  // ==> 배열 arr_member 에서 나이가 20대인 회원의 배열인덱스번호를 출력시켜보자. <== //
  // === !!!! 암기  배열명.findIndex() 은 고유한 값(primary key)으로 검색할 때 사용한다. !!! === //
  //  20    ==> 2     21 ==> 2         27 ==> 2        29 ==> 2
  //  20/10 ==> 2     21/10 ==> 2.1    27/10 ==> 2.7   29/10 ==> 2.9
  //  Math.floor(2)   ==> 2
  //  Math.floor(2.1) ==> 2
  //  Math.floor(2.7) ==> 2
  //  Math.floor(2.9) ==> 2
  //  parseInt("2.9") ==> 2

  let idx = arr_member.findIndex((item) => Math.floor(item.age / 10) == 3);
  console.log(idx);

  // == 배열 arr_member 에 저장된 회원들 중에서 userid 가 "seokj"인 회원의 정보를 아래와 같이 나타내세요 === //
  /*
  아이디 : seokj
  비밀번호 : qwer1234$
  이름 : 서강준
  나이 : 23
*/
  idx = arr_member.findIndex((item) => item.userid == "leess");

  if (idx != -1) {
    console.log(`아이디 : ${arr_member[idx].userid}`);
    console.log(`비밀번호 : ${arr_member[idx].passwd}`);
    console.log(`성명 : ${arr_member[idx].name}`);
    console.log(`나이 : ${arr_member[idx].age}`);
  } else {
    console.log("데이터가 존재하지 않습니다.");
  }
  // ---- **** 배열명.some() **** ----
  // 배열명에서 판별함수의 조건에 만족하는 배열요소가 하나라도 있으면 true 를 리턴 시켜주고 조건검사를 중지한다.
  // 배열명에서 판별함수의 조건에 만족하는 배열요소가 하나라도 없으면 false 를 리턴 시켜준다.

  arr_member.push({
    userid: "teen",
    passwd: "qwer1234$",
    name: "나어려",
    age: 12,
  });

  let bool = arr_member.some((item) => item.age < 20);
  console.log(bool);

  if (arr_member.some((item) => item.age < 20)) {
    console.log("20세 미만 회원이 존재합니다.");
  } else {
    console.log("20세 미만 회원이 존재하지 않습니다.");
  }

  // ---- **** 배열명.every() **** ----
  // 배열명에서 판별함수의 조건에 만족하는 배열요소가 하나라도 없으면 false 를 리턴 시켜주고 조건검사를 중지한다.
  // 배열명에서 판별함수의 조건에 만족하는 배열요소가 모두 존재해야만 true 를 리턴 시켜준다.

  bool = arr_member.every((item) => item.age >= 10);
  console.log(bool);
  // true

  bool = arr_member.every((item) => item.age >= 30);
  console.log(bool);
  // false

  if (arr_member.every((item) => item.age >= 10)) {
    console.log("모든 회원들의 나이는 10세 이상입니다.");
  } else {
    console.log("회원중에 나이가 10세 미만인 회원이 존재합니다.");
  }
  // 모든 회원들의 나이는 10세 이상입니다.
};
