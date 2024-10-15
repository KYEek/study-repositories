package my.day05.b.For_quiz;

import java.util.Scanner;

// ▣ 첫번째 정수 : 1
// ▣ 두번째 정수 : 10

// >> 결과 <<
// 1 부터 10 까지의 홀수의 합 : 25
// 1 부터 10 까지의 짝수의 합 : 30

//   -----------------------------------

// ▣ 첫번째 정수 : 2
// ▣ 두번째 정수 : 10

// >> 결과 <<
// 2 부터 10 까지의 홀수의 합 : 24
// 2 부터 10 까지의 짝수의 합 : 30


// ▣ 첫번째 정수 : 2.3453
// [경고] 올바른 정수를 입력하세요!!

// ▣ 첫번째 정수 : 1
// ▣ 두번째 정수 : 똘똘이
// [경고] 올바른 정수를 입력하세요!!

// ▣ 첫번째 정수 : 1
// ▣ 두번째 정수 : 10
// >> 결과 <<
// 1 부터 10 까지의 홀수의 합 : 25
// 1 부터 10 까지의 짝수의 합 : 30

public class Main_quiz_3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input_str = null;
		int num1 =0, num2 = 0, cnt = 0;
		int odd = 0, even = 0;
		
		for(;;) {
			
			try {
				if(cnt ==0) {
					System.out.print("▣ 첫번째 정수 : ");
					input_str = sc.nextLine();
					num1 = Integer.parseInt(input_str);
					cnt++;
				}
				else {
					System.out.print("▣ 두번째 정수 : ");
					input_str = sc.nextLine();
					num2 = Integer.parseInt(input_str);
					if(num1>num2) {
						System.out.println("두번째 숫자가 더 큽니다.");
						continue;
					}
					cnt++;
				}
			}
			
			catch (java.lang.NumberFormatException e) {
				System.out.println("[경고] 올바른 정수를 입력하세요!!");
			}
			
			
			
			if(cnt == 2) {
				sc.close();
				break;
			}
		}//-----------------------무한 반복 포문 끝-------------------
		
		for(int i = num1; i <= num2; i++) {
			if(i%2 == 0)
				even += i;
			else
				odd += i;
		}
		
		System.out.println(num1 + " 부터 " + num2 + " 까지의 홀수의 합 : " + odd);
		System.out.println(num1 + " 부터 " + num2 + " 까지의 짝수의 합 : " + even);
		

	}

}
