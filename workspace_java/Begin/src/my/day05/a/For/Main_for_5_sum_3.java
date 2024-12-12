package my.day05.a.For;

import java.util.Scanner;

/*
 	== 5개의 홀수를 입력하세요 ==
 	>> 1번째 홀수 입력 : 1
 	>> 2번째 홀수 입력 : 3
 	>> 3번째 홀수 입력 : 5
 	>> 4번째 홀수 입력 : 15
 	>> 5번째 홀수 입력 : 3
 	== 입력한 5개 홀수의 합계는 27 입니다.
 	
 	== 5개의 홀수를 입력하세요 ==
 	>> 1번째 홀수 입력 : 10
 	>> 1번째 홀수 입력 : 11
 	>>[경고] 홀수만 입력하세요!!
 	>> 2번째 홀수 입력 : 똘똘이
 	[경고] 똘똘이는 올바른 홀수가 아닙니다. 올바른 홀수를 입력하세요!!
 	>> 2번째 홀수 입력 : 21
 	>> 3번째 홀수 입력 : 3242532534534524324234252543
 	[경고] 똘똘이는 올바른 정수가 아닙니다. 올바른 정수를 입력하세요!!
 	>> 3번째 홀수 입력 : 5
 	>> 4번째 홀수 입력 : 15
 	>> 5번째 홀수 입력 : 31
 	== 11+21+5+15+31= 80 입니다.
 */
public class Main_for_5_sum_3 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		System.out.println("== 5개의 정수를 입력하세요 ==");
		int sum = 0;	//숫자 누적
		int cnt = 0;	//올바른 정수를 입력했을시 카운트용
		String str_input ="";
		String str = "";
		
		
		for(;;) {
			try {
				System.out.print(">> "+ (cnt+1) + "번째 홀수 입력 : ");
				str_input = sc.nextLine();
				int num = Integer.parseInt(str_input);

				if(num%2 == 0) {
					System.out.println("[경고] 홀수만 입력하세요");
					continue;
				}
				
				cnt++;
				sum += num;
				if(cnt < 5)
					str = str + str_input + "+";
				else
					str = str + str_input + "=";
				
				if(cnt == 5)
				{
					sc.close();
					break;
				}
			}
			catch(java.lang.NumberFormatException e) {
				System.out.println("[경고] "+ str_input +"는 올바른 정수가 아닙니다. 올바른 정수를 입력하세요!!");
			}
		}// end of for-----------------------------------
		//System.out.println("=== 입력한 5개 정수의 합계는 "+ sum + "입니다.");
		System.out.println(str + sum + "입니다.");
		System.out.println("\n>> 프로그램 종료<<");
		
	}//end of main(String[] args) ========================

}
