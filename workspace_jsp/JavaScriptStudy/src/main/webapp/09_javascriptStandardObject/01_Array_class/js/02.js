/**
 *
 */

// === 배열의 메소드인 map()메소드는 반복문을 돌며 배열 안의 요소들을 1대1로 짝지어 새로운 배열을 만들어 주는 것이다. === //
window.onload = function () {
  const point_arr = [100, 85, 93, 64, 76, 50];

  const bool_arr = point_arr.map(function (item, index, array) {
    // index, array 는 생략가능하다.
    // item 은 point_arr 배열의 요소이고,
    // index 는 point_arr 배열의 인덱스이고,
    // array 는 point_arr 배열의 자기자신을 말한다.

    return item >= 70;
  });

  console.log("point_arr => ", point_arr);
  console.log("bool_arr => ", bool_arr);
  // 결과값은 [true, true, true, false, true, false] 이다.

  const bool_arr_1 = point_arr.map((item) => {
    return item >= 70;
  });
  console.log("bool_arr_1 => ", bool_arr_1);
  const bool_arr_2 = point_arr.map((item) => item >= 70);
  console.log("bool_arr_2 => ", bool_arr_2);

  const arr_student = [
    { hakbun: "02001", name: "이순신", kor: 90, eng: 80, math: 70 },
    { hakbun: "02002", name: "엄정화", kor: 100, eng: 90, math: 90 },
    { hakbun: "02003", name: "강감찬", kor: 86, eng: 67, math: 56 },
    { hakbun: "02004", name: "차은우", kor: 100, eng: 70, math: 95 },
    { hakbun: "02006", name: "이혜리", kor: 50, eng: 40, math: 30 },
  ];
  const arr_total = arr_student.map(function (item, index, array) {
    return item.kor + item.eng + item.math;
  });
  console.log("arr_total => ", arr_total);
  //[240, 280, 209, 265, 120]

  const arr_total_1 = arr_student.map(
    (item) => item.kor + item.eng + item.math
  );
  console.log("arr_total => ", arr_total);
  //[240, 280, 209, 265, 120]

  const arr_avg = arr_total.map((item) => (item / 3).toFixed(1));
  console.log("arr_avg => ", arr_avg);
  //[240, 280, 209, 265, 120]

  const arr_grade = arr_avg.map((item) => {
    const n_item = Number(item);
    let grade;
    if (n_item >= 90) {
      return (grade = "A");
    } else if (n_item >= 80) {
      return (grade = "B");
    } else if (n_item >= 70) {
      return (grade = "C");
    } else if (n_item >= 60) {
      return (grade = "D");
    } else {
      return (grade = "F");
    }
  });

  console.log("arr_grade => ", arr_grade);

  let html = `<table>
<tr>
<th>학번</th>
<th>성명</th>
<th>국어</th>
<th>영어</th>
<th>수학</th>
<th>총점</th>
<th>평균</th>
<th>등급</th>
</tr>`;

  arr_student.forEach((item, index, array) => {
    html += `<tr> 
  <td>${item.hakbun} </td>
  <td>${item.name} </td>
  <td>${item.kor} </td>
  <td>${item.eng} </td>
  <td>${item.math} </td>
  <td>${arr_total[index]} </td>
  <td>${arr_avg[index]} </td>
  <td>${arr_grade[index]} </td>
   </tr>`;
  });

  html += "</table>";
  document.querySelector("div#view").innerHTML = html;

  document.querySelector("button#btn_pass").onclick = () => {
    const tr_list = document.querySelectorAll("table#tbl > tbody > tr");

    let html = `<table>
    <thead>
    <tr>
    <th width= "50%">합격자명</th>
    <th>점수</th>
    </tr>
    </tead>
    <tbody>`;

    tr_list.forEach((elmt, index, array) => {
      if (
        index < tr_list.length - 1 &&
        Number(elmt.children[1].innerText) >= 80
      ) {
        html += `<tr>
        <td>${elmt.children[0].innerText}</td>
        <td>${elmt.children[1].innerText}</td>
        </tr>`;
      }
    });

    html += `</tbody> </table>`;
    document.querySelector("div#view_pass").innerHTML = html;
  };

  document.querySelector("button#btn_clear").onclick = function () {
    document.querySelector("div#view_pass").innerHTML = "";
  };
};
