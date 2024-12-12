package my.day07.b.dowhile;

import java.util.Scanner;

public class Main_factorial_2 {

    // === 입사문제 === //
    /*
       ▣ 알고 싶은 팩토리얼 수 입력 => 5엔터
       >> 결과 : 5! = 120 
       >> 또 할래?[Y/N] => y
       
       5! ==> 5*4*3*2*1
       7! ==> 7*6*5*4*3*2*1
       
       ▣ 알고 싶은 팩토리얼 수 입력 => 0엔터
       >> [경고] 자연수만 입력하세요!!
       
       ▣ 알고 싶은 팩토리얼 수 입력 => -5엔터
       >> [경고] 자연수만 입력하세요!!
       
       ▣ 알고 싶은 팩토리얼 수 입력 => 1.25엔터
       >> [경고] 정수만 입력하세요!!
       
       ▣ 알고 싶은 팩토리얼 수 입력 => 똘똘이엔터
       >> [경고] 정수만 입력하세요!!
       
       ▣ 알고 싶은 팩토리얼 수 입력 => 4엔터
       >> 결과 : 4! = 24
       >> 또 할래?[Y/N] => s엔터
       >> [경고] Y 또는 N 만 입력하세요!!
       >> 또 할래?[Y/N] => n엔터
       
       >>> 프로그램 종료 <<<
	*/
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		outer:
		do {
			try {

				System.out.print("▣ 알고 싶은 팩토리얼 수 입력 => ");
				int num = Integer.parseInt(sc.nextLine());
				if(num <= 1) {
					System.out.println(">> [경고] 자연수만 입력하세요!!");
					continue;
					//continue; 를 만나면 아래로 떨어지지 않고 do~while(조건식); 의 조건식으로 들어간다.
				}
				//	=== 팩토리얼 구하기 시작 ===
				int factorial = 1;
				for(int i = num; i>0; i--) {
					factorial *= i;	//factorial = factorial * i와 같다
				}
				System.out.println(num + "! =" + factorial);
				//	=== 팩토리얼 구하기 끝 ===
				do {
					System.out.print(">> 또 할래?[Y/N] => ");
					String yn = sc.nextLine();
					
					if("y".equalsIgnoreCase(yn)) {
						break;
					}
					else if("n".equalsIgnoreCase(yn)) {
						sc.close();
						break outer;	//팩토리얼 구하는 프로그램을 끝내야 한다
					}
					else
						System.out.println(">> [경고] Y 또는 N 만 입력하세요!!");
				} while (true);
				//----------------end of do~while

			}
			catch (java.lang.NumberFormatException e) {
				System.out.println(">> [경고] 자연수만 입력하세요!!");
			}

		} while (true);

		System.out.println("\n>>>프로그램 종료<<<");

	}//end of main-----------------

}
