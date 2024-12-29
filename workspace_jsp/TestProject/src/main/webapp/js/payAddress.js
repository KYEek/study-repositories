document.addEventListener("DOMContentLoaded", function () {
  const closeBtn = document.querySelector("#address_list_close > span"); //주소목록 x버튼
  const addressList = document.querySelector("#address_list_container"); //주소목록
  const address_info_and_select = document.querySelector(
    "#address_info_and_select"
  ); //지정된 주소가 표시되는 div
  const address_list_main = document.querySelector("#address_list_main"); //주소목록이 표시되는 div

  //주소목록을 가져와서 출력해주는 함수
  fetch("json/address.json")
    .then((Response) => Response.json())
    .then((data) => {
      //주소를 넣어줄 html
      let address_html = "<ul>";
      for (const item of data) {
        // console.log(item[key]);
        address_html += `<li>
              <div class="address_list_item" data-addr_no ="${item["pk_addr_no"]}">
                <div class="address_list_item_text">${item["member_name"]}</div>
                <div class="address_list_item_text">${item["addr_address"]}</div>
                <div class="address_list_item_text">${item["addr_detail"]}</div>
                <div class="address_list_item_text">${item["addr_post_no"]}</div>
                <div class="address_list_item_text">${item["member_mobile"]}</div>
              </div>
            </li>`;
        if (item["addr_isdefault"] == "1") {
          sessionStorage.setItem("selected_address_no", item["pk_addr_no"]);
          address_info_and_select.innerHTML = ` <div>${item["addr_address"]}${item["addr_extraaddr"]}</div>
                    <a href="#">변경</a>`;
        }
      }

      address_html += "</ul>";
      address_list_main.innerHTML = address_html;

      const address_list_item = document.querySelectorAll(".address_list_item"); //주소목록의 각각의 주소
      console.log(address_list_item);

      address_list_item.forEach((item) => {
        console.log(item);
        item.addEventListener("click", (e) => {
          if (e.target.getAttribute("class") != "address_list_item") {
            console.log(e.target.parentElement.getAttribute("data-addr_no"));
            sessionStorage.setItem(
              "selected_address_no",
              e.target.parentElement.getAttribute("data-addr_no")
            );
          } else {
            console.log(e.target.getAttribute("data-addr_no"));
            sessionStorage.setItem(
              "selected_address_no",
              e.target.getAttribute("data-addr_no")
            );
          }
          // e.target.getAttibute("data-addr_no");
          // sessionStorage.setItem("selected_address_no", item["pk_addr_no"]);
        });
      });
    }); //end of fetch
  //이미지가 출력되는 div
  const delivery_product_list = document.querySelector(
    "div#delivery_product_list"
  );
  const basket_list_str = sessionStorage.getItem("basket_item_arry");
  const basket_list = JSON.parse(basket_list_str);
  console.log(basket_list);
  //주소목록 x버튼 클릭이벤트
  closeBtn.addEventListener("click", (e) => {
    // 사이드 바 가리가가
    addressList.style.display = "none";
  });

  //주소 클릭 이벤트
  address_info_and_select.addEventListener("click", (e) => {
    //사이드바 표시
    addressList.style.display = "block";
  });
  const basket_item_arry = JSON.parse(
    sessionStorage.getItem("basket_item_arry")
  );
});
