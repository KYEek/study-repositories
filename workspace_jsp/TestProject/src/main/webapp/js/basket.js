/* <div>
            <div class="basket_img">
              <a><img src="images/basket/00155055500-p.jpg" /></a>
            </div>
            <div class="basket_product_info">
              <div class="basket_product_info_header">
                <div><a class="product_link">스웨이드 레더 점퍼</a></div>
                <div>⨉</div>
              </div>
              <div class="basket_pruduct_price">27,000₩</div>
              <div class="basket_pruduct_size_category">
                <span class="basket_product_size">XL (KR 105-110) </span>|<span
                  class="basket_product_category"
                  >샌드</span
                >
              </div>
              <div class="basket_product_count_container">
                <button class="minus_count">+</button>
                <div class="basket_product_count">
                  <span class="pruduct_count_num">1</span>
                </div>
                <button class="plus_count">-</button>
              </div>
            </div>
          </div>
 */
//장바구니 목록을 가져오는 함수
async function getBasketList() {
  try {
    const response = await fetch("json/basketList.json", {
      method: "get",
      headers: { contentType: "application/json" },
    });
    const basketList = await response.json();
    //각 json객체를 순환

    let html = "";
    let PK_BASKET_NO = "";
    let FK_MEMBER_NO = "";
    let FK_PRODUCT_DETAIL_NO = "";
    let BASKET_QUANTITY = "";
    let PRODUCT_NO = "";
    let PRODUCT_SIZE = "";
    let PRODUCT_INVENTORY = "";
    let PRODUCT_NAME = "";
    let PRODUCT_PRICE = "";
    let COLOR_NAME = "";
    let PRODUCT_IMAGE_PATH = "";
    let PRODUCT_IMAGE_NAME = "";

    basketList.forEach((basket) => {
      //   console.log(basket);
      //   json 객체의 값을 추출하기 위한 for문
      for (key in basket) {
        // console.log(basket[key]);
        switch (key) {
          case "PK_BASKET_NO":
            PK_BASKET_NO = basket[key];
            break;
          case "FK_MEMBER_NO":
            FK_MEMBER_NO = basket[key];
            break;
          case "FK_PRODUCT_DETAIL_NO":
            FK_PRODUCT_DETAIL_NO = basket[key];
            break;
          case "BASKET_QUANTITY":
            BASKET_QUANTITY = basket[key];
            break;
          case "PRODUCT_NO":
            PRODUCT_NO = basket[key];
            break;
          case "PRODUCT_SIZE":
            PRODUCT_SIZE = basket[key];
            break;
          case "PRODUCT_INVENTORY":
            PRODUCT_INVENTORY = basket[key];
            break;
          case "PRODUCT_NAME":
            PRODUCT_NAME = basket[key];
            break;
          case "PRODUCT_PRICE":
            PRODUCT_PRICE = basket[key];
            break;
          case "COLOR_NAME":
            COLOR_NAME = basket[key];
            break;
          case "PRODUCT_IMAGE_PATH":
            PRODUCT_IMAGE_PATH = basket[key];
            break;
          case "PRODUCT_IMAGE_NAME":
            PRODUCT_IMAGE_NAME = basket[key];
            break;
        }
      } // end of for-------------------------------
      html += `<div>
            <div class="basket_img">
              <a><img src="${PRODUCT_IMAGE_PATH}" /></a>
            </div>
            <div class="basket_product_info">
              <div class="basket_product_info_header">
                <div><a class="product_link">${PRODUCT_NAME}</a></div>
                <div>⨉</div>
              </div>
              <div class="basket_pruduct_price">${PRODUCT_PRICE}₩</div>
              <div class="basket_pruduct_size_category">
                <span class="basket_product_size">${PRODUCT_SIZE}</span>&nbsp;|&nbsp;<span
                  class="basket_product_category"
                  >${COLOR_NAME}</span
                >
              </div>
              <div class="basket_product_count_container">
                <button class="minus_count">+</button>
                <div class="basket_product_count">
                  <span class="pruduct_count_num">${BASKET_QUANTITY}</span>
                </div>
                <button class="plus_count">-</button>
              </div>
            </div>
          </div>`;
    }); // end of forEach-------------------------------
    return html;
  } catch {
    console.log("error");
  }
}

document.addEventListener("DOMContentLoaded", function () {
  const basket_list = document.querySelector("div#basket_list");
  getBasketList().then((html) => {
    basket_list.innerHTML = html;
  });
});
