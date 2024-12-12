package my.day03.c.scanner;

import java.util.Scanner;

public class Main_scanner_4 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("첫번째 정수 입력 : ");
		String inputStr = null;
		
		
		try {
			inputStr = sc.nextLine();
			int no1 = Integer.parseInt(inputStr);
			
			System.out.print("두번째 정수 입력 : ");
			inputStr = sc.nextLine();
			int no2 = Integer.parseInt(inputStr);
			System.out.println(no1 + " + " + no2 + " = " + (no1 + no2));
		}
		catch(java.lang.NumberFormatException e) {
			System.out.println(">> [경고] " + inputStr + "는 정수가 아닙니다. 모두 정수를 입력해 주세요!!");
		}
		
		
		
		sc.close();
		
		

	}

}
