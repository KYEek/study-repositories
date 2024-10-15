package my.day06.a.multifor;

public class Main_multifor_1 {

	public static void main(String[] args) {

		// 단일 for 문으로 아래와 같이 나오도록 한다

		/*
		 * abcdefg hijklmn opqrstu vwxyz
		 */

		// 이중 for 문으로 아래와 같이 나오도록 한다

		

		/*
		 * abcdefg hijklmn opqrstu vwxyz
		 */

		char ch = 'a';
		for (int i = 0; i < 'u' - 'a' + 1; i++) {
			String str = ((i + 1) % 7 == 0) ? "\n" : "";
			System.out.print(ch++ + str);
		} // ----------------end of for

		System.out.println("\n=====================================\n");
		ch = 'a';
		for (int i = 0; i < 3; i++) { // 바깥 for문은 행렬의 행
			for (int a = 0; a < 7; a++) { // 안쪽 for문은 행렬의 열
				System.out.print(ch++);
			}
			System.out.print("\n");
		} // ------------end of for

		System.out.println("\n================================\n");

		/*
		 * [0,0][0,1][0,2] [1,0][1,1][1,2] [2,0][2,1][2,2] [3,0][3,1][3,2]
		 */

		// ==단일 for문을 사용해 출력

		/*
		 * [0,0][0,1][0,2] [1,0][1,1][1,2] [2,0][2,1][2,2] [3,0][3,1][3,2]
		 */

		// ==이중 for문을 사용해 출력

		int m = 0, n = 0;
		for (int i = 0; i < 12; i++) {

			if (i > 0 && i % 3 == 0)
				m++;

			String str = ((i + 1) % 3 == 0) ? "\n" : "";
			System.out.print("[" + m + "," + (n++) % 3 + "]" + str);
		}

		System.out.println("\n================================\n");

		for (int i = 0; i < 4; i++) {
			for (int a = 0; a < 3; a++) {
				System.out.print("[" + i + "," + a + "]");
			}
			System.out.print("\n");
		}

		System.out.println("\n================================\n");

		/*
		 * [0,0][0,1][0,2] [1,0][1,1][1,2] [3,0][3,1][3,2]
		 */

		for (int i = 0; i < 4; i++) {
			if (i == 2)
				continue;
			for (int a = 0; a < 3; a++) {
				System.out.print("[" + i + "," + a + "]");
			}
			System.out.print("\n");
		}

		System.out.println("\n================================\n");

		/*
		 * 		 스킵 
		 * [0,0][0,1][0,2]
		 * [1,0][1,1][1,2]
		 * [2,0][2,1][2,2]
		 * [3,0][3,1][3,2]
		 */

		for (int i = 0; i < 4; i++) {
			for (int a = 0; a < 3; a++) {
				if (a == 1)
					continue;
				System.out.print("[" + i + "," + a + "]");
			}
			System.out.print("\n");
		}
		
		
		/*
			        			   스킵 
			501호   502호   503호   504호   505호
			401호   402호   403호   404호   405호  ==> 스킵
			301호   302호   303호   304호   305호
			201호   202호   203호   204호   205호
			101호   102호   103호   104호   105호 
		*/
		

		System.out.println("\n================================\n");
		
		for(int i =5; i>0; i--) {
			if (i==4)
				continue;
			for (int a=1; a<=5; a++) {
				if(a==4)
					continue;
				System.out.print(((i*100)+a)+"호\t");
			}
			System.out.println();
		}
		
		
		System.out.println("\n>>프로그램 종료 <<");
	}// --------------------end of main

}
