package my.day06.a.multifor;

/*
		== 숙제 1 == 
		*
		**
		***
		****
		*****
		      
		      
		== 숙제 2 == 
		    *
		   **
		  ***
		 ****
		***** 
		
		
		== 숙제 3 == 
		*****   
		****    
		***    
		**     
		*   
		
		      
		== 숙제 4 ==
		
		  *
		 ***
		***** 
		
		      
		== 숙제 5 ==
		      
		*****
		 ***
		  *
		
		          
		== 숙제 6 ==
		   
		  *
		 ***
		*****
		 ***   
		  *      
		
		
		== 입사문제 == 
		    
		*********1
		********2
		*******3
		******4
		*****5
		****6
		***7
		**8
		*9
*/

//1. 제출할 파일명 :  본인이름_Main_homework_5.java.txt (txt 파일로 제출함)
// 2. 메일주소 :  tjdudgkr0959@naver.com
// 3. 제출기한 :  8월23일(금) 18:00 까지 
// 4. 자바소스 :  for 문 사용하여 만들것.

public class Main_homework_5 {

	public static void main(String[] args) {

		String str = "";
		System.out.println("== 숙제 1 ==");
		for (int i = 1; i <= 5; i++) {
			str += "*";
			System.out.println(str);
		} // ------------숙제1 -----------------------

		str = ""; // str 초기화

		System.out.println("\n\n== 숙제 2 ==");
		for (int i = 1; i <= 5; i++) {
			str += "*";
			System.out.printf("%5s\n", str);
		} // ------------숙제2 -----------------------

		System.out.println("\n\n== 숙제 3 ==");
		for (int i = 5; i >= 1; i--) {
			for (int a = 1; a <= i; a++) {
				System.out.print("*");
			}
			System.out.println();
		} // ------------숙제3 -----------------------

		int s = 2;

		System.out.println("\n\n== 숙제 4 ==");
		for (int i = 1; i <= 5; i += 2) {
			if (s > 0)
				System.out.printf("%" + s + "s", " ");
			str = "";
			
			for (int a = 1; a <= i; a++) {
				str+="*";
			}
			System.out.println(str);
			
			s--;
		} // ------------숙제4 -----------------------

		
		
		s = 0;

		System.out.println("\n\n== 숙제 5 ==");
		for (int i = 5; i >= 1; i -= 2) {
			if (s > 0)
				System.out.printf("%" + s + "s", " ");
			
			
			for (int a = 0; a < i; a++) {
				System.out.print("*");
			}
			
			
			s++;
			System.out.println();
		} // ------------숙제5 -----------------------
		
		
		s = 3;
		System.out.println("\n\n== 숙제 6 ==");
		for (int i = 1, j =3 ;;) {
			
			if(i<=5) {
				s--;
				if (s > 0)
					System.out.printf("%" + s + "s", " ");	//공백의 개수를 정해줌
				for (int a = 1; a <= i; a++) {
					System.out.print("*");
				}
				i += 2;
			}
			else {
				s++;
				if (s > 0)
					System.out.printf("%" + s + "s", " ");	//공백의 개수를 정해줌
				for (int a = 0; a < j; a++) {
					System.out.print("*");
				}
				j -= 2;
			}
			

			if (j < 0)
				break;
			
			
			System.out.println();
		} // ------------숙제6 -----------------------
		
		
		


		System.out.println("\n\n== 입사문제 ==");
		int b = 0;
		for (int i = 9; i >= 1; i--) {
			for (int a = 1; a <= i; a++) {
				System.out.print("*");
			}
			b++;
			System.out.println(b);
		} // ------------입사문제 -----------------------

	}// ---------- end of main------------

}
