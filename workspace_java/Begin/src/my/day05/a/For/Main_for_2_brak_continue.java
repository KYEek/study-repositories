package my.day05.a.For;

public class Main_for_2_brak_continue {

	public static void main(String[] args) {
		
		System.out.println(">>> 1. break <<<");
		
		for(int i = 0; i < 10; i++) {
			if(i==5)
				break;		//반복문에서 break;를 만나면 가장 가장 가까운 반복문을 벗어나는 것이다
			System.out.println(i+1);
		}//------------------------
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		int cnt = 0;
		
		for(;;) {
			System.out.println(++ cnt + "번째 반복");
			if(cnt == 5)
				break;
		}//-------------------------
		
		System.out.println("\n>>> 2. continue <<<");
		
		for (int i = 0; i<10; i++) {
			if((i+1)%2 == 0)		//출력하고자 하는 값이 짝수라면
				continue;			//반복문에 continue; 를 만나면 실행 순서가 밑으로 내려가지 않고 가장 가까운 반복문의 증감식으로 이동시킨다.
			System.out.print((i+1) + " ");
			// i ==> 0 (i+1) ==> 1
			// i ==> 1 (i+1) ==> 2 continue; i++
			// i ==> 2 (i+1) ==> 3
			// i ==> 3 (i+1) ==> 4 continue; i++
			// i ==> 4 (i+1) ==> 5
			// i ==> 5 (i+1) ==> 6 continue; i++
			// i ==> 6 (i+1) ==> 7
			// i ==> 7 (i+1) ==> 8 continue; i++
			// i ==> 8 (i+1) ==> 9
			// i ==> 9 (i+1) ==> 10 continue; i++
			// i ==> 10 i<10; == 거짓
		} // ---------------------------
		

		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		for(int i=1; i<=10; i++) {
			System.out.print(i);
			if(i<10)
				System.out.print(",");
		}
		
		System.out.println("");

		for(int i=1; i<=9; i+=2) {
			System.out.print(i);
			if(i<8)
				System.out.print(",");
		}
		
		
		
		System.out.println("\n>> 프로그램 종료 <<");
		

	}// end of main(String[] args) ============================

}
