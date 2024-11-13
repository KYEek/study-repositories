/**
 *
 */

const arr_quizData = [
  {
    question: "문제1. 대한민국의 수도는?",
    answers: {
      1: "부산",
      2: "수원",
      3: "서울",
      4: "인천",
    },
    correct: 3,
  },
  {
    question: "문제2. 1+1은?",
    answers: {
      1: "1",
      2: "2",
      3: "3",
      4: "4",
    },
    correct: 2,
  },
  {
    question: "문제3. 미국의 수도는?",
    answers: {
      1: "뉴욕",
      2: "파리",
      3: "로스엔젤러스",
      4: "워싱턴",
      5: "런던",
    },
    correct: 4,
  },
  {
    question:
      "문제4. 사진속의 인물의 이름은?<div><img src='images/iyou.jpg'/></div>",
    answers: {
      1: "엄정화",
      2: "아이유",
      3: "김태희",
      4: "박보영",
    },
    correct: 2,
  },
  {
    question:
      "문제5. 사진속의 인물의 이름은?<div><img src='images/parkby.jpg'/></div>",
    answers: {
      1: "엄정화",
      2: "아이유",
      3: "김태희",
      4: "박보영",
    },
    correct: 4,
  },
];

window.onload = function () {
  const btnSubmit = document.querySelector("button#btnSubmit"); // "제출하기" 버튼

  const timerDiv = document.querySelector("div#timer"); // 타이머를 보여줄 장소

  let time = 600; // 타이머 시간을 10분으로 지정함.

  // ===== 타이머 함수 만들기 시작 ===== //
  const timer = function () {
    if (time < 0) {
      alert("시험시간 종료!!\n자동으로 제출됩니다.");

      clearInterval(interval_timer); // 타이머 삭제하기
      // interval_timer 는 중단해야할 setInterval 함수를 가리키는 것이다.

      //  btnSubmit.disabled = true;   // "제출하기" 버튼 비활성화
      //  또는
      btnSubmit.setAttribute("disabled", "true"); // "제출하기" 버튼 비활성화

      //  [참고]  btnSubmit.disabled = false;   // "제출하기" 버튼 활성화

      check(); // 채점하는 함수 호출
    } else {
      let minute = "";
      let second = "";

      minute = parseInt(time / 60); // 소수부는 없애 버리고 정수부만 가져오는 것이다.
      if (minute < 10) {
        minute = "0" + minute;
      }

      second = time % 60; // time 을 60 으로 나누었을때의 나머지
      if (second < 10) {
        second = "0" + second;
      }

      timerDiv.innerHTML = `${minute}:${second}`;

      time--;
    }
  };
  timer();
  // ===== 타이머 함수 만들기 끝 ===== //

  const interval_timer = setInterval(timer, 1000); // 1초 마다 주기적으로 타이머 함수가 호출되도록 지정함.

  // ===== 퀴즈문항을 html 로 만들기 시작 ===== //

  const btn_previous = document.querySelector("button#previous");
  const btn_next = document.querySelector("button#next");

  const quiz_arr = document.querySelectorAll("main > div");

  let current_index = 0; // 현재 인덱스 번호
  let max_length = quiz_arr.length - 1;

  quiz_arr.forEach((elmt) => {
    elmt.style.display = "none";
  });

  quiz_arr[0].style.display = "";

  arr_quizData.forEach((item, index) => {
    const quizDiv = document.querySelector(`div#quiz_${index}`); // 퀴즈문항을 보여줄 장소

    let html = ``;
    html += `<p id="q${index}">${item.question}</p>`;

    html += `<ol>`;

    for (let property_name in item.answers) {
      // 어떤 객체의 속성(키)들을 모두 불러올때는 for문에서 of 가 아니라 in 을 사용한다.
      html += `<li><label for="${index}${property_name}">${item.answers[property_name]}</label>&nbsp;<input id="${index}${property_name}" type="radio" name="question${index}" value="${property_name}" /></li>`;
      // 객체명.속성명 은 속성명에는 변수가 사용될 수 없다.
      // 객체명[속성명] 은 속성명에 변수가 사용될 수 있다.
      // ${item.answers[property_name]} 는 "부산" 과 같은 것을 말하는 것이다.

      // 라디오는 반드시 name 값이 동일해야 한다.
      // value 값은 item.answers 의 속성명인 1 2 3 4 로 되어진다.
    } // end of for----------------------------

    html += `</ol>`;
    html += `<div class='ox' id="ox${index}"></div>`; // 퀴즈문항에 대해 정답인지 틀린것인지 보여줄 장소
    console.log(item);
    console.log(html);
    quizDiv.innerHTML = html;
  }); // end of arr_quizData.forEach()--------------------------
  // ===== 퀴즈문항을 html 로 만들기 끝 ===== //

  const func_next = function () {
    if (current_index < max_length) {
      //현재 인덱스 번호가 마지막(지금은 3)이 아닌경우
      //이전 버튼을 활성화 한다
      //   btn_previous.disabled = false;
      //또는
      btn_previous.removeAttribute("disabled");
      quiz_arr.forEach((elmt) => {
        elmt.style.display = "none";
      });

      const quiz = quiz_arr[++current_index];
      quiz.style.display = ""; //다음번 이미지만 보이게 만든다.

      document.querySelector("h2#msg").innerHTML = "";
    } else {
      btn_next.setAttribute("disabled", "true"); // 마지막 사진일 때 다음버튼을 비활성화 상태로 만든다.
      document.querySelector("h2#msg").innerHTML = "마지막 사진입니다.";
    }
  };

  // == 이전으로 이동하는 함수
  const func_prev = function () {
    if (current_index > 0) {
      //현재 인덱스 번호가 마지막(지금은 3)이 아닌경우
      quiz_arr.forEach((elmt) => {
        elmt.style.display = "none";
      });
      btn_next.removeAttribute("disabled");

      const quiz = quiz_arr[--current_index];
      quiz.style.display = ""; //다음번 이미지만 보이게 만든다.
      document.querySelector("h2#msg").innerHTML = "";
    } else {
      btn_previous.setAttribute("disabled", "true"); // 처음 사진일 때 이전버튼을 비활성화 상태로 만든다.
      document.querySelector("h2#msg").innerHTML = "처음 사진입니다.";
    }
  };

  btn_previous.setAttribute("disabled", "true"); // 처음 사진일 때 이전버튼을 비활성화 상태로 만든다.
  btn_next.addEventListener("click", func_next);
  btn_previous.addEventListener("click", func_prev);

  // ==== "제출하기" 버튼 클릭시 이벤트 처리하기 시작 ==== //
  const handleSubmit = function () {
    if (check() == true) {
      // 채점하는 함수 호출

      alert("제출이 완료되었습니다.");

      clearInterval(interval_timer); // 타이머 삭제하기
      // interval_timer 는 중단해야할 setInterval 함수를 가리키는 것이다.

      timerDiv.innerHTML = `00:00`;

      //  btnSubmit.disabled = true;   // "제출하기" 버튼 비활성화
      //  또는
      btnSubmit.setAttribute("disabled", "true"); // "제출하기" 버튼 비활성화

      //  [참고]  btnSubmit.disabled = false;   // "제출하기" 버튼 활성화
    }
  };

  // btnSubmit 은 "제출하기" 버튼이다. 이것은 맨 위에서 만들었다.
  btnSubmit.addEventListener("click", handleSubmit);
  // ==== "제출하기" 버튼  클릭시 이벤트 처리하기 끝 ==== //

  ////////////////////////////////////////////////////////////

  // ===== 채점하는 함수 만들기 시작 ====== //
  const check = function () {
    if (time >= 0) {
      // 시험시간이 남아있을때만 사용자가 "제출하기" 버튼을 클릭할 수 있다.
      let choice_cnt = 0; // 답안선택 누적용

      arr_quizData.forEach((item, index) => {
        // === 해당 문제의 라디오 개수가 몇개인지 알아오기 === //
        let radio_length = document.querySelectorAll(
          `input[name="question${index}"]`
        ).length;

        //  console.log(`${index+1}번 문제의 라디오 개수 : ${radio_length}`);
        /*
               1번 문제의 라디오 개수 : 4
               2번 문제의 라디오 개수 : 4
               3번 문제의 라디오 개수 : 5
               4번 문제의 라디오 개수 : 4
            */

        for (let i = 0; i < radio_length; i++) {
          if (
            document.querySelectorAll(`input[name="question${index}"]`)[i]
              .checked
          ) {
            choice_cnt++;
            break;
          }
        } // end of for---------------------
      }); // end of arr_quizData.forEach()--------------------------

      if (choice_cnt != arr_quizData.length) {
        alert("답안을 선택하지 않은 문제가 있습니다.");
        return false; // false 를 리턴시켜 주면서 함수의 종료한다.
      }
    }
    ///////////////////////////////////////////////////////////

    // ========== 진짜로 채점하러 가기 시작 =========== //
    let answer_cnt = 0; // 정답개수 누적용

    arr_quizData.forEach((item, index) => {
      // console.log(`${index+1}번 문제 정답 : ${item.correct}`);
      /*
              1번 문제 정답 : 3
              2번 문제 정답 : 2
              3번 문제 정답 : 4
              4번 문제 정답 : 2
           */

      // 퀴즈문항 뒤에 정답번호 공개하기
      const question = document.querySelector(`p#q${index}`).innerHTML; // 정답을 보여주기전 문제를 읽어와서 확인해본다.
      // console.log(question);
      /*
              문제1. 대한민국의 수도는?
              문제2. 1+1은?
              문제3. 미국의 수도는?
              문제4. 사진속의 인물의 이름은?
           */
      console.log("실행됨");
      document.querySelector(`p#q${index}`).innerHTML =
        question +
        `&nbsp;<span style='color:red; font-weight:bold;'>${item.correct}</span>`;
      // 문제에다가 뒤에 정답을 붙여서 보여준다.

      //   let user_answer = document.querySelector(
      //     `input[name="question${index}"]:checked`
      //   ).value;
      // :checked 은 라디오중에 선택되어진 라디오를 말한다.

      const checked_radio_elmt = document.querySelector(
        `input[name="question${index}"]:checked`
      );
      let user_answer;
      if (checked_radio_elmt == null) {
        user_anser = "-1";
      } else {
        user_answer = checked_radio_elmt.value;
      }

      if (user_answer == item.correct) {
        answer_cnt++;
        document.querySelector(
          `div#ox${index}`
        ).innerHTML = `<span style='color:blue;'>정답</span>`;
      } else {
        console.log(document.querySelector(`div#ox${index}`));
        document.querySelector(
          `div#ox${index}`
        ).innerHTML = `<span style='color:red;'>틀림</span>`;
      }
    }); // end of arr_quizData.forEach()--------------------------

    // document.querySelector(
    //   "div#score"
    // ).innerHTML = `<span style='font-weight:bold;'>정답개수 : ${answer_cnt}</span>`;

    return true; // true 를 리턴시켜 주면서 함수의 종료한다.
    // =========== 진짜로 채점하러 가기 끝 ============ //
  };
  // ===== 채점하는 함수 만들기 끝 ====== //
};
