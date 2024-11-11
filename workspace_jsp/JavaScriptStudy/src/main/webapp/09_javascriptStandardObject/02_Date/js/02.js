/**
 *
 */

window.onload = function () {
  const timerDiv = document.querySelector("div#timer");

  let is_stop = false;
  let time = 600;
  let time_continue = 0;

  //타이머 함수 만들기 시작

  const timer = function () {
    let minute;
    let second;

    minute = parseInt(time / 60);

    if (minute < 10) {
      minute = "0" + minute;
    }

    second = time % 60;
    if (second < 10) {
      second = "0" + second;
    }

    const html = `${minute}:${second}`;
    timerDiv.innerHTML = html;
    time_continue = time--;
  };
  //   setInterval(function () {
  //     timer();
  //   }, 1000);
  let interval_timer = setInterval(timer, 1000);

  const btnTimerClear = document.querySelector("button#btnTimerClear");

  btnTimerClear.addEventListener("click", () => {
    clearInterval(interval_timer); // interval_timer를 멈추는 함수
    // interval_timer 는 중단해야할 setInterval 함수를 가리키는 것이다.
    is_stop = true;
    /*
         만약에 setTimeout 으로 지정된 것이라면 clearTimeout(interval_timer); 으로 한다.
         interval_timer 는 중단해야할 setTimeout 함수를 가리키는 것이다. 
       */

    //타이머가 중지했던
  });

  //타이머 중지 끝

  //타이머 처음부터 시작
  const btnTimerRestart = document.querySelector(
    "div#timerclear > button#btnTimerRestart"
  );

  btnTimerRestart.addEventListener("click", () => {
    /*타이머를 다시 생성하려면 기존것을 중지시키고 다시 생성해야한다. */

    time = 600;
    clearInterval(interval_timer); // interval_timer를 멈추는 함수

    interval_timer = setInterval(timer, 1000);
  });

  //타이머 처음부터 시작의 끝

  //타이머 중지이후 부터 시작
  const btnTimerContinue = document.querySelector(
    "div#timerclear > button#btnTimerContinue"
  );

  btnTimerContinue.addEventListener("click", () => {
    /*타이머를 다시 생성하려면 기존것을 중지시키고 다시 생성해야한다. */

    if (is_stop) {
      time = time_continue;

      interval_timer = setInterval(timer, 1000);
      is_stop = false;
    } else {
      alert("타이머가 중지되지 않았습니다.");
    }
  });
};
