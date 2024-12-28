document.addEventListener("DOMContentLoaded", function () {
  const closeBtn = document.querySelector("#address_list_close > span");
  const addressList = document.querySelector("#address_list_container");
  const address_info_and_select = document.querySelector(
    "#address_info_and_select"
  );
  console.log(address_info_and_select);
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
});
