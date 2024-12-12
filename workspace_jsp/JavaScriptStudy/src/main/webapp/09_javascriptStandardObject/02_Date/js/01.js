/**
 *
 */

window.onload = function () {
  /*
       자바스크립트 new Date() 란?
      ==> new Date() 는 new Date() 가 실행되는 시점에 날짜 및 시간 데이터를 갖고 오는 것이다.
           출력하게 되면 요일명 월 일 년도 시:분:초 GMT기준시간을 표시해준다.
       
        자바스크립트 get날짜함수란? 
      ==> getFullYear / getMonth / getDate 등 get날짜함수는 날짜 데이터에서 필요한 형식만 갖고 오는 함수이다.
          
         종류               리턴값
         -----------------------------------
         getFullYear()      YYYY
           getMonth()         MM     자바스크립트의 월은 0부터 시작하기 때문에 1을 더해야 정상적인 월이 된다.
           getDate()          DD
           getDay()           0 ~ 6 (일 ~ 토)
           getHours()         HH
           getMinutes()       MM
           getSeconds()       SS
           getMilliseconds()  mmm
      
       자바스크립트 set날짜함수란? 
      ==> setFullYear / setMonth / setDate 등 set날짜함수는 날짜 데이터의 원하는 형식의 값으로 대입해주는 함수이다.
          종류 : setFullYear(), setMonth(), setDate(), 
                 setHours(), setMinutes(), setSeconde(), setMilliseconds()
    */
  //   //함수 표현식
  //   const func_currentDate = function () {
  //     return "함수 표현식";
  //   };

  //   document.querySelector("td#current_date_1").innerHTML = func_currentDate();
  //   //함수 표현식
  //   const func_timebomb_1 = function () {
  //     alert("5초 뒤에 터지는 시한폭탄!!");
  //   };

  //   //   setTimeout(func_timebomb_1, 5000);
  //   function func_timebomb_2() {
  //     alert("8초 뒤에 터지는 시한폭탄!!");

  //     const body = document.querySelector("body");
  //     body.style.backgroundColor = "yellow";
  //   } // end of func_timebomb_2

  //   setTimeout(func_timebomb_2, 8000);

  function func_currentDate() {
    const now = new Date();
    console.log(now);

    console.log(now.toLocaleString());

    const year = now.getFullYear(); //현재년도 2024
    let month = now.getMonth() + 1; //11월 0부터 시작하기 때문에 1을 더해줘야 한다.
    let date = now.getDate();

    let hours = now.getHours();
    let minutes = now.getMinutes();
    let second = now.getSeconds();
    if (month < 10) {
      month = "0" + month;
    }
    if (date < 10) {
      date = "0" + date;
    }
    if (hours < 10) {
      hours = "0" + hours;
    }
    if (minutes < 10) {
      minutes = "0" + minutes;
    }
    if (second < 10) {
      second = "0" + second;
    }

    const day = now.getDay(); //요일 0 ~ 6 (일 ~ 토)
    let dayName;
    switch (day) {
      case 0:
        dayName = "일요일";
        break;
      case 1:
        dayName = "월요일";
        break;
      case 2:
        dayName = "화요일";
        break;
      case 3:
        dayName = "수요일";
        break;
      case 4:
        dayName = "목요일";
        break;
      case 5:
        dayName = "금요일";
        break;
      case 6:
        dayName = "토요일";
        break;
    }
    return `${year}- ${month}- ${date} ${hours}:${minutes}:${second} ${dayName}`;
  }

  const func_clock_loop = function () {
    document.querySelector("td#current_date_1").innerHTML = func_currentDate();
    setTimeout(func_clock_loop, 1000);
  };

  func_clock_loop(); //함수 실행하기

  const func_clock = function () {
    document.querySelector("td#current_date_2").innerHTML = func_currentDate();
  };
  //   func_clock();
  //   setInterval(function () {
  //     func_clock;
  //   }, 1000); //1초마다 반복실행
  //또는
  setInterval(() => func_clock(), 1000);
};
