package my.day03.c.scanner;

import java.util.Scanner;

public class Main_Scanner_1 {

	public static void main(String[] args) {
		
		//System.in 은 입력장치(키보드)를 말한다.
		//System.out 은 출력장치(모니터)를 말한다.
		
		Scanner sc = new Scanner(System.in);
		//Scanner sc는 키보드에서 입력된 문자열을 읽어들이는 객체이다.
		
		System.out.print("1. 문장을 입력하세요 => ");
		String inputStr = sc.nextLine();
		
		/*
		 * 	sc.nextLine(); 은 키보드로 부터 입력받은 문장을 읽어들이는 것인데,
		 * 	엔터(종결신호)까지 모두 읽어들인 후 스캐너 버퍼에 아무것도 남기지 않는다
		 */
		
		System.out.println("입력한 문장 => " + inputStr);
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		System.out.print("2. 정수를 입력하세요 => ");
		
		//byte inputNum1 = sc.nextByte();
		//short inputNum2 = sc.nextShort();
		int inputNum3 = sc.nextInt();
		//long inputNum4 = sc.nextLong();
		
		/*
		 * sc.nextInt(); 는 정수(int)를 읽어들이는 것인데
		 * 종결자가 공백 또는 엔터입니다.
		 * 종결자(공백 또는 엔터) 앞까지의 입력해준 정수를 읽어온다
		 * sc.nextInt(); 를 사용하면 스캐너(sc)버퍼에는 종결자(공백 또는 엔터)가 삭제되는 것이 아니라 그래도 남아 있게 된다
		 */
		
		sc.nextLine();	// 버퍼를 비워줌
		
		System.out.println("입력한 정수 => " + inputNum3);
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		

		System.out.print("3. 실수를 입력하세요 => ");
		
		//float inputFlt = sc.nextFloat();
		double inputDbl = sc.nextDouble();
		/*
		 * sc.nextDouble(); 는 실수(double)를 읽어들이는 것인데
		 * 종결자가 공백 또는 엔터입니다.
		 * 종결자(공백 또는 엔터) 앞까지의 입력해준 실수를 읽어온다
		 * sc.nextDouble(); 을 사용하면 스캐너(sc)버퍼에는 종결자(공백 또는 엔터)가 삭제되는 것이 아니라 그래도 남아 있게 된다
		 */
		
		
		sc.nextLine();	// 스캐너 버퍼에 남아있는 것을 비우는(제거하는) 효과를 가진다
		
		System.out.println("입력한 실수 => " + inputDbl);
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		System.out.print("4. 첫번째 단어를 입력하세요 => ");
		String input_word1 = sc.next();
		/*
		 * sc.next(); 는 단어를 읽어들이는 것인데
		 * 종결자가 공백 또는 엔터입니다.
		 * 종결자(공백 또는 엔터) 앞까지의 입력해준 실수를 읽어온다
		 * sc.next(); 을 사용하면 스캐너(sc)버퍼에는 종결자(공백 또는 엔터)가 삭제되는 것이 아니라 그래도 남아 있게 된다
		 */
		
		
		sc.nextLine();
		
		System.out.println("입력한 단어 => " + input_word1);
		
		System.out.print("5. 두번째 단어를 입력하세요 => ");
		String input_word2 = sc.next();
		sc.nextLine();
		System.out.println("입력한 단어 => " + input_word2);
		
		sc.close();
	}

}
