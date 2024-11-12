/**
 *
 */

window.onload = function () {
  const btn_previous = document.querySelector("button#previous");
  const btn_next = document.querySelector("button#next");

  const images = document.querySelectorAll("div#images > img");

  images.forEach((elmt) => {
    elmt.style.display = "none";
  });
  //초기 설정으로 모든 이미지들은 안보이게 만든다
  images[0].style.display = "";

  let current_index = 0; // 현재 인덱스 번호
  let max_length = images.length - 1;
  // == 다음으로 이동하는 함수
  const func_next = function () {
    if (current_index < max_length) {
      //현재 인덱스 번호가 마지막(지금은 3)이 아닌경우
      //이전 버튼을 활성화 한다
      //   btn_previous.disabled = false;
      //또는
      btn_previous.removeAttribute("disabled");
      images.forEach((elmt) => {
        elmt.style.display = "none";
      });

      const img = images[++current_index];
      img.style.display = ""; //다음번 이미지만 보이게 만든다.
      // ==== 이미지에 애니메이션 효과 주기(transition) 시작 ==== //
      img.style.opacity = 0.3;
      let opc = img.style.opacity;

      let increase = 0.1;
      const func_img_opacity = setInterval(function () {
        increase += 0.01;
        img.style.opacity = `${Number(opc) + increase}`;
        console.log(img.style.opacity);
        if (Number(img.style.opacity) >= 1) {
          console.log("그만...");
          clearInterval(func_img_opacity);
        }
      }, 10);
      // ==== 이미지에 애니메이션 효과 주기(transition) 끝 ==== //

      document.querySelector("h2#msg").innerHTML = "";
    } else {
      btn_next.setAttribute("disabled", "true"); // 마지막 사진일 때 다음버튼을 비활성화 상태로 만든다.
      //  또는
      //  btn_next.disabled = true; // 마지막 사진일 때 다음버튼을 비활성화 상태로 만든다.
      document.querySelector("h2#msg").innerHTML = "마지막 사진입니다.";
    }
  };

  // == 이전으로 이동하는 함수
  const func_prev = function () {
    if (current_index > 0) {
      //현재 인덱스 번호가 마지막(지금은 3)이 아닌경우
      images.forEach((elmt) => {
        elmt.style.display = "none";
      });
      btn_next.removeAttribute("disabled");

      const img = images[--current_index];
      img.style.display = ""; //다음번 이미지만 보이게 만든다.
      // ==== 이미지에 애니메이션 효과 주기(transition) 시작 ==== //
      img.style.opacity = 0.3;
      let opc = img.style.opacity;

      let increase = 0.1;
      const func_img_opacity = setInterval(function () {
        increase += 0.01;
        img.style.opacity = `${Number(opc) + increase}`;
        console.log(img.style.opacity);
        if (Number(img.style.opacity) >= 1) {
          console.log("그만...");
          clearInterval(func_img_opacity);
        }
      }, 10);
      // ==== 이미지에 애니메이션 효과 주기(transition) 끝 ==== //
      document.querySelector("h2#msg").innerHTML = "";
    } else {
      btn_previous.setAttribute("disabled", "true"); // 처음 사진일 때 이전버튼을 비활성화 상태로 만든다.
      //  또는
      //  btn_next.disabled = true; // 마지막 사진일 때 다음버튼을 비활성화 상태로 만든다.
      document.querySelector("h2#msg").innerHTML = "처음 사진입니다.";
    }
  };
  btn_previous.setAttribute("disabled", "true"); // 처음 사진일 때 이전버튼을 비활성화 상태로 만든다.
  btn_next.addEventListener("click", func_next);
  btn_previous.addEventListener("click", func_prev);
};
