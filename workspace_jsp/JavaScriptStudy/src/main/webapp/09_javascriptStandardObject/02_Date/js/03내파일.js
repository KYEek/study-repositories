/**
 *
 */

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

      // 채점하는 함수 호출
      check();
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
  // ===== 타이머 함수 만들기 끝 ===== //

  timer();

  // const interval_timer = setInterval(function(){ timer(); }, 1000); // 1초 마다 주기적으로 타이머 함수가 호출되도록 지정함.
  // 또는
  const interval_timer = setInterval(timer, 1000); // 1초 마다 주기적으로 타이머 함수가 호출되도록 지정함.

  //////////////////////////////////////////////////////////////////////

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
  ];

  const quizDiv = document.querySelector("div#quiz_display"); // 퀴즈문항을 보여줄 장소

  // ===== 퀴즈문항을 html 로 만들기 시작 ===== //
  let html = ``;

  arr_quizData.forEach((item, index) => {
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
  }); // end of arr_quizData.forEach()--------------------------

  quizDiv.innerHTML = html;
  // ===== 퀴즈문항을 html 로 만들기 끝 ===== //

  // ===== "제출하기" 버튼을 클릭시 이벤트 처리하기 시작 ===== //
  const handleSubmit = function () {
    if (check() == true) {
      alert("제출하기 버튼을 클릭하셨습니다.");
      clearInterval(interval_timer); // 타이머 삭제하기
      // interval_timer 는 중단해야할 setInterval 함수를 가리키는 것이다.

      timerDiv.innerHTML = "00:00"; // 타이머를 00:00 으로 만들기
      //  btnSubmit.disabled = true;   // "제출하기" 버튼 비활성화
      //  또는
      btnSubmit.setAttribute("disabled", "true"); // "제출하기" 버튼 비활성화
    }
    //  [참고]  btnSubmit.disabled = false;   // "제출하기" 버튼 활성화
    //////////////////////////////////////////////
    // 채점하는 함수 만들기 시작
    const check = function () {
      let cnt_check = 0; // 체크한 문제의 갯수를 저장할 변수
      let answer_cnt = 0;

      arr_quizData.forEach((item, index, array) => {
        // console.log(`${index + 1}번 문제 정답은 ${item.correct}번 입니다.`);

        //퀴즈 문항 뒤에 정답번호 공개하기
        const question = document.querySelector(`p#q${index}`).innerHTML; //정답을 보여주기전 문제를 읽어와서 확인해 본다.
        // console.log(question);
        //문제에 다가 정답번호를 추가한다.
        document.querySelector(`p#q${index}`).innerHTML =
          question +
          `&nbsp;<span style ='color:red; font-weight:bold;'>${item.correct}</span>`;

        // == 해당 문제의 라디오 개수가 몇개인지 알아오기 시작 == //

        let radio_length = document.querySelectorAll(
          `input[name="question${index}"]`
        ).length;

        //라디오의 선택 유무 검사용
        let isCheckAnswer = false;
        for (let i = 0; i < radio_length; i++) {
          if (
            document.querySelectorAll(`input[name="question${index}"]`)[i]
              .checked
          ) {
            break;
          }
        }
        // console.log(`${index + 1}번 문제 답을 선택하셨나요? ${isCheckAnswer}`);
      }); //end of arr_quizData.forEach--------------------------

      if (cnt_check != arr_quizData.length) {
        alert("선택하지 않은 답이 있어요 제출할레요?");
        return false; //함수를 끝내라
      }

      //진짜로 채점하러 가기 시작
      arr_quizData.forEach((item, index, array) => {
        // console.log(`${index + 1}번 문제 정답은 ${item.correct}번 입니다.`);

        //퀴즈 문항 뒤에 정답번호 공개하기
        const question = document.querySelector(`p#q${index}`).innerHTML; //정답을 보여주기전 문제를 읽어와서 확인해 본다.
        // console.log(question);
        //문제에 다가 정답번호를 추가한다.
        document.querySelector(`p#q${index}`).innerHTML =
          question +
          `&nbsp;<span style ='color:red; font-weight:bold;'>${item.correct}</span>`;

        // == 해당 문제의 라디오 개수가 몇개인지 알아오기 시작 == //
        //
        let user_answer = document.querySelector(
          `input[name"question${index}"]:checked`
        );
        // :checked 은 라디오중에 선택되어진 라디오를 말한다.
        if (user_answer == item.correct) {
          answer_cnt++;
          document.querySelector(
            `div#ox${index}`
          ).innerHTML = `<span style ='color:blue;'>정답</span>`;
        } else {
          `<span style ='color:blue;'>정답</span>`;
        }
      }); //end of arr_quizData.forEach--------------------------
      document.querySelector(
        "div#score"
      ).innerHTML = `<span style = 'font-weight:bold'>정답개수 : ${answer_cnt}</span>`;
      //진짜로 채점하러 가기 끝
    };
    return true;
    //채점하는 함수 만들기 끝
  };
  //btnSubmit은 "제출하기" 버튼이다. 이것은 맨 위에서 만들었다.
  btnSubmit.addEventListener("click", handleSubmit);
  // ===== "제출하기" 버튼을 클릭시 이벤트 처리하기 끝 ===== //
}; // end of window.onload = function(){}-------------------------

// window.onload = function () {
//   const btnSbubit = document.querySelector("button#btnSubmit");

//   const timerDiv = document.querySelector("div#timer"); //타이머를 보여줌

//   let time = 600; //타이머 시간을 10분으로 지정

//   //타이머 함수 만들기 시작
//   const timer = function () {
//     if (time < 0) {
//       alert("시험시간이 종료되었습니다.\n 자동으로 제출됩니다.");
//       // 타이머 삭제하기
//       clearInterval(interval_timer);
//       // "제출하기" 버튼을 비활성화
//       //   btnSbubit.disabled = ture;
//       //또는
//       btnSbubit.setAttribute("disabled", "true"); //버튼을 비활성화

//       //[참고] btnSubmit.disabled = false; //버튼을 활성화
//       //채점하는 함수
//     } else {
//       let minute = "";
//       let cecond = "";

//       parseInt(time / 60); //소수점을 제거하기 위해 parseInt를 사용
//       if (minute < 10) {
//         minute = "0" + minute;
//       }
//       second = time % 60; //time을 60으로 나눈 나머지를 구함

//       if (second < 10) {
//         second = "0" + second;
//       }

//       timerDiv.innerHTML = `${minute}:${second}`;

//       time--;
//     }
//   };
//   //타이머 함수 만들기 끝

//   timer();

//   //   const interval_timer = setInterval(function () {
//   //     timer();
//   //   }, 1000); // 1초 마다 주기적으로 타이머 함수가 호출되도록 지정함.
//   // 또는
//   const interval_timer = setInterval(timer, 1000);
//   //////////////////////////////////////////////
//   const arr_quizData = [
//     {
//       question: "문제1. 대한민국의 수도는?",
//       answers: { 1: "부산", 2: "수원", 3: "서울", 4: "인천" },
//       correct: 3,
//     },
//     {
//       question: "문제2. 1+1은?",
//       answers: {
//         1: "1",
//         2: "2",
//         3: "3",
//         4: "4",
//       },
//       correct: 2,
//     },
//     {
//       question: "문제3. 미국의 수도는?",
//       answers: {
//         1: "뉴욕",
//         2: "파리",
//         3: "로스엔젤러스",
//         4: "워싱턴",
//         5: "런던",
//       },
//       correct: 4,
//     },
//     {
//       question:
//         "문제4. 사진속의 인물의 이름은?<div><img src='images/iyou.jpg'/></div>",
//       answers: {
//         1: "엄정화",
//         2: "아이유",
//         3: "김태희",
//         4: "박보영",
//       },
//       correct: 2,
//     },
//   ]; //[{}, {}] //NOSQL MongDB

//   const quizDiv = document.querySelector("div#quiz_display"); // 퀴즈 문항을 보여줄 자리

//   // 퀴즈 문항을 html로 만들기 시작
//   let html = ``;

//   arr_quizData.forEach((item, index) => {
//     html += `<p id='q${index}'>${item.question}</p>`;
//     for (let property_name in item.answers) {
//       html += `<ol>`;
//       // 어떤 객체의 속성(키)들을 모두 불러올때는 for문에서 of 가 아니라 in 을 사용한다.
//       html += `<li><label for ="${index}${property_name}">${tem.answers[property_name]}&nbsp</label><input id = "${index}${property_name}" type = "radio" name ="question${index}" value="${property_name}"></li>`;
//       // 객체명.속성명 은 속성명에는 변수가 사용될 수 없다.
//       // 객체명[속성명] 은 속성명에 변수가 사용될 수 있다.
//       // ${item.answers[property_name]} 는 "부산" 과 같은 것을 말하는 것이다.

//       // 라디오는 반드시 name 값이 동일해야 한다.
//       // value 값은 item.answers 의 속성명인 1 2 3 4 로 되어진다.
//     }
//     html += `</ol>`;
//     html += `<div class ='ox' id =''></div>`; // 퀴즈문항이 정답을 제출 했을때 정답인지 틀린것인지 보여줄 장소
//   }); //end of forEach--------------------------------------------

//   quizDiv.innerHTML = html;
//   // 퀴즈 문항을 html로 만들기 끝
// }; //end of window.onload--------------------------------------------
