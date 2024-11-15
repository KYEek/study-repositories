/**
 *
 */

const arr_product = [
  {
    productId: "led",
    productName: "led모니터",
    image: "monitor.jpg",
    price: 200000,
    option: [
      { optionId: "HDMI", optionName: "HDMI케이블", price: 1000 },
      { optionId: "3Dglasses", optionName: "3D안경", price: 2000 },
    ],
  },
  {
    productId: "camcorder",
    productName: "캠코더",
    image: "cam.jpg",
    price: 500000,
    option: [
      { optionId: "3Pedestal", optionName: "3각받침대", price: 10000 },
      { optionId: "limokon", optionName: "리모컨", price: 20000 },
      { optionId: "charger", optionName: "충전기", price: 30000 },
    ],
  },
];
window.onload = function () {
  let html = `<table id ='tbl'>
                    <thead>
                        <tr>
                            <th colspan='4'>제품선택</th>
                        </tr>
                        <tr>
                            <th width='22%'>제품사진</th>
                            <th width='30%'>제품정보</th>
                            <th width='33%'>부속품</th>
                            <th width='15%'>주문금액</th>
                        </tr>
                    </thead>
                    <tbody>`;
  arr_product.forEach(function (item, index, array) {
    html += `<tr>
        <td style = 'text-align:center;'><img src ='images/${item.image}'></td>
        <td>
            <ul>
                <li><label>제품명 : </label>${item.productName}</li>
                <li><label>가격 :</label><span id = 
                "${item.productId}"=>${item.price.toLocaleString(
      "en"
    )}</span></li>
                <li><label>주문수량 :</label><input type ='number'  max="20" min="0" id ="${
                  item.productId
                }"></li>
            </ul>
        </td>
        <td>`;
    item.option.forEach(function (data, i) {
      html += `<label for ="cb_${data.optionId}">${data.optionName}</label> <input id ="cb_${data.optionId}" type ='checkbox' name = "${item.productId}" value = ${data.price}>`;
    });
    html += `</td>
        <td id ="td_${item.productId}">0</td>
    </tr>`;
  }); // end of forEach---------------------
  html += `</tbody>
                <tfoot>
                    <tr>
                        <td colspan = '3'>주문총액</td>\
                        <td id ="final_price">0</td>
                    </tr>
                </tfoot>
  </table>`;

  document.querySelector("div#view").innerHTML = html;

  //이미지 클릭시 팝업창 띄우기
  const img_list = document.querySelectorAll(
    "div#container > div#view > table#tbl > tbody > tr > td:first-child > img"
  );

  for (let img of img_list) {
    img.onclick = () => {
      //   window.open(
      //     "popup_contents.html",
      //     "my_popup",
      //     "width=400, height=350, left=100px, top=100px"

      open_popup(img.src); // 팝업창을 띄우도록 만든 함수 호출
    };
  }
  //이미지 클릭시 팝업창 띄우기 끝
  //팝업창 띄우기
  //   window.open(
  //     "popup_contents.html",
  //     "my_popup",
  //     "width=400, height=350, left=100px, top=100px"
  //   );
  const open_popup = (src) => {
    const popup = window.open(
      "",
      "my_popup",
      "width=400, height=350, left=100px, top=100px"
    );
    popup.document.write(`<html>
        <head>
        <title>제품이미지 확대보기 </title>
        </head>
        <body align='center'>
        <img src = ${src}>
        <br>
        <br>
        <br>
        <button type='button' onclick = 'window.close()'>팝업창 닫기</button>
        </body>
                            </html>`);
  };

  // === 주문수량이 바뀌면 주문금액에 값을 넣어주기 그리고 부속품중 체크를 한 부속품에 대해서만 부속품 금액을 주문금액에 값을 넣어주기 시작 === //

  const input_number_list = document.querySelectorAll(
    "table#tbl input[type='number']"
  );

  //주문수량 이벤트 처리하기 시작//
  for (let input_number of input_number_list) {
    input_number.addEventListener("change", () => {
      //   console.log("확인용 수량 : ", input_number.value);
      const str_price = document
        .querySelector(`table#tbl span#${input_number.id}`)
        .innerText.split(",")
        .join("");

      //주문수량에 대한 금액 알아오기
      const su_price = Number(str_price) * Number(input_number.value);
      //   console.log("확인용 su_price : ", su_price);
      document.querySelector(`td#td_${input_number.id}`).innerText =
        su_price.toLocaleString("en");
      final_price();

      //0일때 체크박스 해제하기
      if (su_price == 0) {
        const checkbox_list = document.querySelectorAll(
          `input[name=${input_number.id}]`
        );
        console.log(`input${input_number.id}`);
        console.log(checkbox_list);
        checkbox_list.forEach(function (item, index) {
          item.checked = false;
        });
      }
      //0일때 체크박스 해제하기 끝
    });

    input_number.addEventListener("keyup", () => {
      alert("키보드 입력은 안됩니다.");
      input_number.value = 0;
    });
  }
  //주문수량 이벤트 처리하기 끝//

  //   //===부속품명에 마우스가 올라가면 부속품명의 배경색은 노랑색, 글자색은 빨간색으로 만들기 시작 ===
  //   const label_list = document.querySelectorAll(
  //     "#tbl > tbody > tr > td:nth-child(3) > label"
  //   );

  //   for (let label of label_list) {
  //     label.addEventListener("mouseover", function () {
  //       this.style.backgroundColor = "yellow";
  //       this.style.color = "red";
  //       this.cursor = "pointer";
  //     });

  //     label.addEventListener("mouseout", function () {
  //       this.style.backgroundColor = "";
  //       this.style.color = "";
  //       this.cursor = "";
  //     });
  //   }
  //===부속품명에 마우스가 올라가면 부속품명의 배경색은 노랑색, 글자색은 빨간색으로 만들기 시작 ===

  //부속품 체크박스 이벤트 처리하기 시작

  const checkbox_list = document.querySelectorAll(
    "table#tbl input[type='checkbox']"
  );

  checkbox_list.forEach(function (item, index) {
    item.addEventListener("click", function () {
      component_price = 0;
      price = document
        .querySelector(`td#td_${item.name}`)
        .innerText.split(",")
        .join("");
      if (price == "0") {
        alert("주문수량을 먼저 선택해주세요");
        item.checked = false;
        return false;
      }
      if (item.checked) {
        price = Number(price) + Number(item.value);
        document.querySelector(`td#td_${item.name}`).innerText =
          price.toLocaleString("en");
        final_price();
      } else {
        price = Number(price) - Number(item.value);
        document.querySelector(`td#td_${item.name}`).innerText =
          price.toLocaleString("en");
        final_price();
      }
    });
  });

  //부속품 체크박스 이벤트 처리하기 끝

  //주문총액 구하기 시작

  //   for (let product of arr_product) {
  //     document.querySelectorAll(`td#td_${product.productId}`)
  //     product.addEventListener("change", function () {
  //       let price = 0;
  //       const final_price = document.querySelector("td#final_price");
  //       for (let i = 0; i < arr_product.length; i++) {
  //         price += Number(
  //           document
  //             .querySelector(`td#td_${product.productId}`)
  //             .innerText.split(",")
  //             .join("")
  //         );
  //       }
  //     });
  //   }
  //   price += Number(
  //     document.querySelector(`td#td_${product.productId}`).split(",").join("")
  //   );

  //주문총액 구하기 끝
  //// === 주문수량이 바뀌면 주문금액에 값을 넣어주기 그리고 부속품중 체크를 한 부속품에 대해서만 부속품 금액을 주문금액에 값을 넣어주기 끝 === //
}; // end of window.onload---------------------

function final_price() {
  let price = 0;
  const final_price = document.querySelector("td#final_price");
  for (let product of arr_product) {
    price += Number(
      document
        .querySelector(`td#td_${product.productId}`)
        .innerText.split(",")
        .join("")
    );
  }
  final_price.innerText = price.toLocaleString("en");
}
