package my.day05.b.For_quiz;

import java.util.Scanner;

/*
 * 
 * >> 글자를 입력하세요 : Abz3쌍a0# $ T
 * 총글자수 : 12
 * 대문자 : A,T 대문자 개수 : 2
 * 소문자 : b,z,a 소문자 개수 : 3
 * 숫자 3,0: 숫자 개수 : 2
 * 한글 : 쌍 한글:개수 : 1
 * 공백 개수 : 2
 * 특수문자 : # $ 특수문자 개수 : 2
 * 
 */
public class Main_quiz_2 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print(">> 글자를 입력하세요 : ");
		String str_input = sc.nextLine();
		int length = str_input.length();
		int upper_cnt = 0, lower_cnt = 0, digit_cnt = 0, hangul_cnt = 0, space_cnt = 0, special_cnt = 0;
		String upper_str = "", lower_str = "", digit_str = "", hangul_str = "", special_str = "";
		
		
		
		
		for(int i=0; i<length; i++) {
			char ch = str_input.charAt(i);
			
			if(Character.isUpperCase(ch)) {//대문자 인지 검사한다
				upper_cnt++;
				if(!upper_str.equals(""))
					upper_str += ",";
				upper_str += ch;
			}
			else if(Character.isLowerCase(ch)) {	//소문자인지 검사
				lower_cnt++;
				if(!lower_str.equals(""))
					lower_str += ",";
				lower_str += ch;
			}
			else if(Character.isDigit(ch)) {	//숫자인지 검사
				digit_cnt++;
				if(!digit_str.equals(""))
					digit_str += ",";
				digit_str += ch;
			}
			else if('가' <= ch && ch <= '힣') {	//한글인지 검사
				hangul_cnt++;
				if(!hangul_str.equals(""))
					hangul_str += ",";
				hangul_str += ch;
			}
			else if(ch == ' ') {
				space_cnt++;
			}
			else {
				special_cnt++; 
				if(!special_str.equals(""))
					special_str += ",";
				special_str += ch;
			}
		}
		
		System.out.println("総文字数 : " + length);
		System.out.println("大文字の個数: " + upper_cnt +"\t大文字 : " + upper_str);
		System.out.println("小文字の個数: " + lower_cnt +"\t小文字 : " + lower_str);
		System.out.println("숫자 개수: " + digit_cnt +"\t숫자 : " + digit_str);
		System.out.println("한글 개수: " + hangul_cnt+"\t한글 : " + hangul_str);
		System.out.println("공백 개수 : " + space_cnt);
		System.out.println("특수문자 개수: " +special_cnt + "\t특수문자 : " + special_str);
		
		sc.close();

	}// end of main(String[] args)==========================

}
