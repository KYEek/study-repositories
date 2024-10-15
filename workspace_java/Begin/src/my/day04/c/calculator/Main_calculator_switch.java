package my.day04.c.calculator;

import java.util.Scanner;

/*
 * ▣ 첫번째 정수 입력 => 10 	1000000000000000	똘똘이
 * ▣ 두번째 정수 입력 => 4	5000000000000000	헤헤헤
 * ▣ 사칙연산을 선택하세요(+ - * /) : + - * / # 우하하 7
 * 10 + 4 =14
 * 10 - 4 = 6
 * 10 * 4 = 40
 * 10 / 4 = 2.5
 * 10 / 0 
 * 
 * 
 */

public class Main_calculator_switch {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		String str_input = null;
		
		
		//---------------------실행 부분 ----------------------------
		
		try {
			System.out.print("▣ 첫번째 정수 입력 => ");
			str_input = sc.nextLine();
			int num1 = Integer.parseInt(str_input);	// 문자열을 정수로 형변환 시킨다.
			// 10
			// 100000000000
			// 똘똘이


			// -----------------------------------------
			
			System.out.print("▣ 두번째 정수 입력 => ");
			str_input = sc.nextLine();
			int num2 = Integer.parseInt(str_input);
			
			// -----------------------------------------
			
			System.out.print("▣ 사칙연산을 선택하세요(+ - * /) : ");
			
			String operator = sc.nextLine();
			
			//String str = null;
			//System.out.println(str.equals("하하하"));
			//System.out.println("하하하".equals("str"));
			
			double calc_result = 0;
			String result = "";
			
			/*
			if("+".equals(operator)) {		//변수.equals 는 문자열이 같은지 비교한다 (문자열은 == 사용 안됨), 널포인트익셉션때문에 상수가 앞에 와야 한다
				calc_result = num1 + num2;
			}
			else if ("-".equals(operator)) {
				calc_result = num1 - num2;
			}
			else if("*".equals(operator)) {
				calc_result = num1 * num2;
			}
			else if("/".equals(operator)) {
				calc_result = (double)num1 / num2;
			}
			else {
				//사칙연산 선택시 + - * / 을 제외한 다른 것을 입력한 경우
				System.out.println("[경고] 사칙연산 선택은 + - * / 만 입력하세요");
				sc.close();
				return;
			}
			
			*/
			switch (operator) {	// switch (변수) 에서 변수에 들어올 수 있는 타입은 byte, short, int, char, String만 가능하다!!
			case "+":
				calc_result = num1 + num2;
				break;
			case "-":
				calc_result = num1 - num2;
				break;
			case "*":
				calc_result = num1 * num2;
				break;
			case "/":
				calc_result = (double)num1 / num2;
				break;

			default:
				//사칙연산 선택시 + - * / 을 제외한 다른 것을 입력한 경우
				System.out.println("[경고] 사칙연산 선택은 + - * / 만 입력하세요");
				sc.close();
				return;
			}
			
			switch (operator) {
			case "/":
				if(num1 % num2 !=0) 
					result = num1 +" " + operator + " " + num2 + " = " + calc_result;
				else 
					result = num1 +" " + operator + " " + num2 + " = " + (int)calc_result;
				break;

			default:
				result = num1 +" " + operator + " " + num2 + " = " + (int)calc_result;
				break;
			}
			
			
			/*
			if("/".equals(operator) && num1 % num2 !=0) {	// / 이면서 나머지가 있는 경우
				result = num1 +" " + operator + " " + num2 + " = " + calc_result;
			}
			else {
				result = num1 +" " + operator + " " + num2 + " = " + (int)calc_result;
			}
			*/
			
			System.out.println(result);
			
		}
		catch(java.lang.NumberFormatException e) {
			System.out.println(">>>" + str_input + "은 올바른 데이터가 아닙니다. 올바른 정수를 입력하세요 <<<");
		}
		catch(java.lang.ArithmeticException e) {
			System.out.println(">>> 0 으로 나눌 수 없습니다.");
		}
		sc.close();
		

	} //==================end of main(String[] args)

}
