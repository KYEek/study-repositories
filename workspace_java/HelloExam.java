// 1줄 주석문

/*

    여러줄
    주석문

*/


/**
    문서화 주석
    ==> 어떤 클래스나 어떤 메소드 생성 시 javadoc 를 이용해서  API 문서를 만들 때 사용한다.
*/




// 어떤 프로그램 (예: 쇼핑몰 프로그램)을 생성하려면 먼저 설계도면이 있어야 하는데 
// 자바(java)라는 프로그램 개발 언어를 사용하여 만드는 설계도면을 "클래스"라고 부른다.

// === *** CLASS(클래스)의 구조 *** ===
/*
    1. 패키지 선언문
    ==> 패키지란 클래스가 저장되어진 디렉토리 경로라고 보면 된다.

    package 패키지명; ==> 이때 패키지명은 반드시 소문자로 시작해야 한다. 숫자 X

    예> packge my.day01;
    
    2. import 문
    
      예> import 패키지명.클래스명;
          
            import java.lang.System;
            import java.lang.StringBuilder;

            import java.lang.*; // * 의 뜻은 모든것을 의미한다. 
 
            import java.util.Date;

     기본적으로 import java.lang.*; 이 생략되어져 있다.

      3. 클래스 선언문

      4. 컴파일(comphile) 하기 
         ==> 고급언어를 저급언어로 바꾸어주는 과정을 컴파일(comphile) 이라고 부른다.
         고급언어란? 사람(개발자)이 알아 볼 수 있는 언어 
         저급언어란? 기계(컴퓨터)가 알아들을 수 있는 언어

         기계(컴퓨터)는 전기의 힘으로 작동된다.
         전기는 +, - 로 이루어져 있다.
         경우의 수가 2가지로 되어진 것 ==> 이진수(Binary digit)
       
         A 를 나타내고자 하는데 ASCII 코드에 의해 10진수 65 라고 약속 해두었으므로 2진수 0100 0001 로 읽혀진다.

ASCII(아스키)는 American Standard Code for Information Interchange의 약자로써, ANSI(미국표준협회)에서 만든 표준 코드 체계다.

         0, 1 (이진수 Binary digit, bit)

         컴파일러 ==> C:\SW\jdk-17.0.0.1\bin\javac.exe

         C:\NCS\workspace_java>javac HelloExam.java

         5. 실행하기 ==> C:\SW\jdk-17.0.0.1\bin\javac.exe        
             C:\NCS\workspace_java>java HelloExam
             Hello World!
             안녕하세요?

*/


// import java.lang.System;
// 기본적으로 import java.lang.*; 이 생략되어져 있다.
// import java.lang.*;

import java.util.Date;

public class HelloExam // 클래스명의 첫글자는 대문자로 해야 한다.
                                // 또한 저장 시 파일명은 클래스명과 동일하게 해야 하며, 확장자는 .java 이다.
{ // class body (클래스 본체)는 { 로 시작해서 } 로 끝난다.


   public static void main(String[] args) {
        System.out.println("Hello World!"); // System.out 은 모니터 (화면)라고 보면 된다.

        System.out.println("안녕하세요?");

        System.out.println("오늘은 개강 첫 날입니다.");

        Date now = new Date();
        System.out.println("현재시각 : " + now);

   }
}








