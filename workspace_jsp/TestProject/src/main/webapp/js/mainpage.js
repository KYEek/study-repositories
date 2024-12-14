/**
 *
 */

document.addEventListener("DOMContentLoaded", function () {
  const group_list = document.querySelectorAll(".group_list");

  //   테스트용;
  //   console.log(group_list);

  //스크롤 발생 시 실행할 이벤트
  group_list.forEach((element) => {
    element.addEventListener("scroll", (e) => {
      //이벤트 객체 안의 li를 찾는다
      const li = element.querySelectorAll("li");
      //   console.log(li);
      const li_index = Math.floor(element.scrollTop / 1000);
      //   console.log(li_index);
      // 스크롤 값의 첫번째 소수점 자리로 만든다
      const current_place =
        Number((element.scrollTop / 1000).toFixed(4)) + 0.05;
      console.log(current_place);
      const animation_position = current_place - li_index;
      const scaleNum = 1.73 - animation_position;
      if (animation_position > 0.7) {
        console.log("scaneNum : ", scaleNum);
        li[li_index].style.transform = `scale(${scaleNum})`;
      }
    });
  });
});
