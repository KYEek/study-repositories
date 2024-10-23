package main;

import java.util.Scanner;

public class test {

	public static void main(String[] args) {

		
		Scanner sc = new Scanner(System.in);
		
		String user_id = sc.nextLine();
		
		
		if(user_id.length() < 5 && user_id.length() > 20) {
			System.out.println("아이디는 5글자 이상 20글자 이하여야 합니다.");
		}
		
		boolean check_alphabet = false;		//영어가 있는지 파악하는 변수
		for(char i:user_id.toCharArray()) {
			if(i >= '!' && i<= '/') {
				System.out.println("기호가 있으면 안됩니다.");
			}
			
			if(i >= '가' && i <= '힣') {
				System.out.println("한글이 있으면 안됩니다.");
			}
			
			if((i >= 'a' && i <='z') || (i >= 'A' && i <= 'Z')) {
				check_alphabet = true;		//5글자 이내고 20글자 이하의 한글과 기호가 없는 글자 중에 영어가 있으면 ok
			}
			else {
				System.out.println("숫자만 입력은 안됩니다.");
				check_alphabet = false;	//숫자만 있으면 실패
			}
		}//end of for-----------------------------
		
		//아이디에 알파벳이 있으면 true 리턴, 숫자만 있으면 false 리턴
		if(check_alphabet) {
			System.out.println("성공");
			
		}
		else {
			System.out.println("숫자만 입력은 안됩니다.");
		}
		
		
	}

}
