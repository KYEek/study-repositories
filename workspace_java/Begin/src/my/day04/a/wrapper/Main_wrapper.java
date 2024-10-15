package my.day04.a.wrapper;

import java.util.Scanner;

/*
	=== *** wrapper 클래스 *** ===
	
	------------------------------------------------
	기본자료형(원시형, primitive)     wrapper 클래스
	------------------------------------------------
	byte                  			Byte 
	short                 			Short
	int                   			Integer  
	long                  			Long
	char                  			Character
	float                 			Float
	double                			Double
	boolean               			Boolean 
	------------------------------------------------
	
	기본자료형(원시형)은 데이터 저장 및 4칙연산 에서만 사용하는 것이고, 
	wrapper 클래스는  데이터 저장 및 4칙연산 뿐만아니라 
	아주 다양한 기능의 메소드가 제공되므로 다방면으로 사용되어진다. 
*/

public class Main_wrapper {

	public static void main(String[] args) {

		char ch = 'a';
		System.out.println("ch => " + ch);  // ch =>  a
		System.out.println(ch+1);           // 97+1   98
		System.out.println((char)(ch-32));  // 97-32  65  (char)65    A
		
		Character chr = Character.valueOf('a');
		System.out.println("chr => " + chr);  // chr =>  a
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		System.out.println((char)('a'-32));   // A
		System.out.println(Character.toUpperCase('a'));  // A
		System.out.println(Character.toLowerCase('A'));  // a
		System.out.println(Character.toUpperCase(97));   // 65
		System.out.println(Character.toLowerCase(65));   // 97
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		char ch2 = 'c';
		
		if('A' <= ch2 && ch2 <= 'Z') {  // 대문자인지 알아본다.
			System.out.println(ch2+" 는 대문자 입니다.");
		}
		
		else if('a' <= ch2 && ch2 <= 'z') { // 소문자인지 알아본다.
			System.out.println(ch2+" 는 소문자 입니다.");
		}
		
		else if('0' <= ch2 && ch2 <= '9') { // 숫자인지 알아본다.
			System.out.println(ch2+" 는 숫자 입니다.");
		}
		
		else {
			System.out.println(ch2+" 는 특수문자 입니다.");
		}
		
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~\n");
				
		if(Character.isUpperCase(ch2)) {  // 대문자인지 알아본다.
			System.out.println(ch2+" 는 대문자 입니다.");
		}
		
		else if(Character.isLowerCase(ch2)) { // 소문자인지 알아본다.
			System.out.println(ch2+" 는 소문자 입니다.");
		}
		
		else if(Character.isDigit(ch2)) { // 숫자인지 알아본다.
			System.out.println(ch2+" 는 숫자 입니다.");
		}
		
		else {
			System.out.println(ch2+" 는 특수문자 입니다.");
		}

		
		System.out.println("\n==========================\n");
		
		String str = "Baby";
		//     index  0123
		
		char ch1=  str.charAt(0);  // 'B'
		System.out.println(ch1);   // B
		
		ch1=  str.charAt(3);       // 'y'
		System.out.println(ch1);   // y
		
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print(">> 한글을 포함한 문장을 입력하세요 => ");
		String str_input = sc.nextLine();
		// I am a boy           ==> 첫글자 I 은(는) 대문자 입니다.
		// superman             ==> 첫글자 s 은(는) 소문자 입니다.
		// 8080 tomcat          ==> 첫글자 8 은(는) 숫자 입니다.
		// 안녕하세요 goodmoring  ==> 첫글자 안 은(는) 한글 입니다. 
		//  좋은하루 되세요        ==> 첫글자  은(는) 공백 입니다.
		// @gmail.com           ==> 첫글자 @ 은(는) 기타문자 입니다.
		
		char c = str_input.charAt(0);
		
		if(Character.isUpperCase(c)) {  // 대문자 인지 알아본다.
			System.out.println("첫글자 " + c + "은(는) 대문자 입니다.");
		}
		
		else if(Character.isLowerCase(c)) { // 소문자 인지 알아본다.
			System.out.println("첫글자 " + c + "은(는) 소문자 입니다.");
		}
		
		else if(Character.isDigit(c)) {  // 숫자 인지 알아본다.
			System.out.println("첫글자 " + c + "은(는) 숫자 입니다.");
		}
		
		else if('가' <= c && c <= '힣') { // 한글 인지 알아본다. 
			System.out.println("첫글자 " + c + "은(는) 한글 입니다.");
		}
		
		else if(c == ' ') { // 공백 인지 알아본다. 
			System.out.println("첫글자 " + c + "은(는) 공백 입니다.");
		}
		
		else {
			System.out.println("첫글자 " + c + "은(는) 기타문자 입니다.");
		}
		
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		System.out.println("=== 오토박싱(auto boxing), 오토언박싱(auto unboxing) 에 대해서 알아봅니다. ===");
		
		// Boxing(박싱, 포장을 하는것) 이란 ?
		// ==> 기본자료형(boolean, byte, short, int, long, char, float, double)으로 되어진 변수를 
		//     객체타입인 Wrapper 클래스(Boolean, Byte, Short, Integer, Long, Character, Float, Double)	
		//     타입의 객체로 만들어주는 것을 말한다.
		
		int a1 = 10;
		Integer a2 = Integer.valueOf(a1);   // Boxing(박싱)
		System.out.println("a2 => " + a2);  // a2 => 10
		
		int b1 = 10;
		Integer b2 = b1;   // Auto Boxing(박싱)
		System.out.println("b2 => " + b2);  // b2 => 10
		
		
		// UnBoxing(언박싱, 포장을 푸는것) 이란?	
		// ==> Wrapper 클래스(Boolean, Byte, Short, Integer, Long, Character, Float, Double)로 
		//     되어져 있는 객체를 기본자료형(boolean, byte, short, int, long, char, float, double)으로 
		//     만들어주는 것을 말한다.
		Integer a3 = Integer.valueOf(20);
		int a4 = a3.intValue(); // UnBoxing(언박싱)
		System.out.println("a4 => " + a4); // a4 => 20
		
		int a5 = Integer.valueOf(20);  // Auto UnBoxing(언박싱)
		System.out.println("a5 => " + a5); // a5 => 20
		
		sc.close();
	}

}





