/**
 *
 */
window.onload = function () {
  const tbl = document.querySelector("table#tbl");

  //   tbl.onchange = function () {
  //     // alert("변경이 발생했습니다.");
  //   };
  //또는
  //   tbl.addEventListener("change", function () {
  //     alert("변경이 발생했습니다.");
  //   });
  //   tbl.onkeydown = function () {
  //     alert("키보드 이벤트가 발생했습니다.");
  //   };
  // 또는
  //   tbl.addEventListener("keydown", function () {
  //     alert("키보드 이벤트가 발생했습니다.");
  //   });
  //   tbl.onkeyup = function () {
  //     alert("키보드 이벤트가 발생했습니다.");
  //   };
  // 또는
  //   tbl.addEventListener("keyup", function () {
  //     alert("키보드 이벤트가 발생했습니다.");
  //   });
  //e.target은 이벤트가 발생한 객체를 가리킨다. 이거 암기
  tbl.addEventListener("change", function (e) {
    // alert(e.target.value);
    /*
           노드.parentNode.parentNode - 부모노드 의 부모노드
           노드.parentNode            - 부모노드
           노드.childNodes            - 모든자식노드들
           노드.firstChild            - 첫째자식노드
           노드.lastChild             - 막내자식노드
           노드.nextSibling           - 나의바로밑동생노드(형제노드)
           노드.previousSibling       - 나의바로위형노드(형제노드)
           
           !! 조심할 사항은 태그사이에 띄어쓰기를 하거나 태그를 줄 바꿈을 해버리면
             띄어쓰기나 줄바꿈을 텍스트 노드(text node)로 취급해버리므로 
              원하는 노드의 값을 가지고 오지 않고 undefined 가 나온다.  
                    그래서 이거를 잘 쓰지 않는다
           이렇게 undefined 가 나오지 않도록 하기 위해서 우리는 노드에 id 값을 주어서 처리하도록 한다. 
        */
    //  e.target 이 input 태그를 가리키는 것이다. apple   strawberry    melon
    // alert(e.target.parentNode.innerText);

    let price = e.target.parentNode.innerText;
    price = Number(price.split(",").join(""));

    // alert(price);
    // alert(typeof price);

    let su = Number(e.target.value);
    price *= su; // price = price * su; // price는 개수*단가인 금액이 된다.
    // alert(`가격 : ${price.toLocaleString("en")}`);
    // 사과는 0, 딸기도 0, 참외는 undefined 나옴.
    // alert(e.target.parentNode.nextSibling.innerHTML);

    const id = e.target.id;
    //alert(document.querySelector(`td#${id}`).innerHTML);
    document.querySelector(`td#${id}`).innerHTML = price.toLocaleString("en");

    // document.querySelector("td#sum").innerHTML = sum();

    const td_list = document.querySelectorAll(
      "table#tbl > tbody > tr > td:last-child"
    );

    // console.log(
    //   document.querySelectorAll("table#tbl > tbody > tr > td:last-child")
    // );

    console.log(td_list);
    const arr = Array.from(td_list);

    const arr_td = Array.from(td_list, (elmt) => {
      elmt.innerHTML.split(",").join("");
    });
    console.log(arr_td);

    let sum = 0;
    for (let price of arr_td) {
      sum += Number(price);
    }
    console.log(sum);

    document.querySelector("table#tbl > tfoot > tr > td:last-child").innerHTML =
      sum.toLocaleString("en");
  });
  document.getElementById;
  // tbl.addEventListener("keyup", function (e) {
  // alert("수량은 키보드로는 안되며, 마우스로만 조작하세요");
  //   e.target.value = "0"; //e.target은 input 태그를 가리킨다.
  // });

  tbl.addEventListener("keyup", function (e) {
    func_calculate(e.target);
  });
};

function func_calculate(target) {
  // alert(`확인용 :  ${target.value}`);
  const su = Number(target.value);
  if (su < 0 || su > 10) {
    document.querySelector("div#display_error > span").style.display = "inline";
    target.value = "0";
    // document.querySelector(`td#${target.id}`).innerHTML = "0";

    document.querySelector("div#display_error > span").style.display = "";
  }

  // else {}
}
