package my.day05.a.For;

import java.util.Scanner;

/*
>> 누적해야할 시작 숫자 => 1
>> 누적해야할 마지막 숫자 => 10
[실행결과] 1+2+3+4+5+6+7+8+9+10=55 

>> 누적해야할 시작 숫자 => 3
>> 누적해야할 마지막 숫자 => 5
[실행결과] 3+4+5=12

>> 누적해야할 시작 숫자 => 똘똘이
[경고] 올바른 정수만 입력하세요!!
>> 누적해야할 시작 숫자 => 3
>> 누적해야할 마지막 숫자 => 345234543643643543
[경고] 올바른 정수만 입력하세요!!
>> 누적해야할 마지막 숫자 => 6
[실행결과] 3+4+5+6=18
*/

public class Main_for_7_sum_5 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		String str_input = "";
		String str = "";
		int a = 0, b = 0;
		int sum = 0;
		byte cnt = 0;
		
		
		// a와 b를 받기 위한 for문
		for(;;) {
			
			try {
				
				
				// 입력숫자가 시작숫자인지 마지막 숫자인지 판독
				if (cnt == 0) {
					System.out.print("누적해야할 시작 숫자 => ");
					str_input = sc.nextLine();
					a = Integer.parseInt(str_input);
					cnt++;
				}
				else {
					System.out.print("누적해야할 마지막 숫자 => ");
					str_input = sc.nextLine();
					b = Integer.parseInt(str_input);
					if(a>=b) {
						System.out.println("[경고] 마지막 숫자는 첫번째 숫자보다 커야 합니다.");
						continue;
					}
					cnt++;
				}
				
				
				
				if(cnt==2) {
					sc.close();
					break;
				}
			}
				
			catch(java.lang.NumberFormatException e) {
				System.out.println("[경고] 올바른 정수만 입력하세요!!!");
			}
			
			
		}//---------------------for문 끝----------------------
		
		//sum 계산을 하는 구역
		for(int i=a;i<=b;i++) {
			sum += a;
			/*
			if (i<b)
				str += i + "+";
			else
				str += i + " = ";
				*/
			String temp = (i<b)?"+":"=";
			str += i + temp;
		}//------------------------for문 끝-------------------
		
		//결과 출력
		System.out.println("[실행결과] : " +str+sum);
		System.out.println("\n\n=========프로그램 종료==========");


	}

}
