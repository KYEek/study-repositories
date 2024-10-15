package my.day07.a.While;



public class Main_while {

	public static void main(String[] args) {
		

		 /*
		    === while 문 형식 ===
		    
		    변수의 초기화;
		       
		    while(조건식) {
		       조건식이 참(true)이라면 반복해서 실행할 명령문을 실행하고,
		       조건식이 거짓(false)이라면 while의 { } 이부분을 빠져나간다. 
		       
		       반복해서 실행할 명령문;
		       증감식;
		    }
		  */
	
		 int cnt = 5, loop = 0;
		 while(loop < cnt) {
			 System.out.println((loop+1) + ".안녕 자바~~");
			 loop++;
			 
			 
		 }

			/*
			 * 	1.안녕 자바~~
			 * 	2.안녕 자바~~
			 * 	3.안녕 자바~~
			 * 	4.안녕 자바~~
			 * 	5.안녕 자바~~
			 */
		 
		 
		 System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		 cnt = 5; loop = 0;
		 while(loop++ < cnt) {
			 System.out.println(loop + ".Hello Java~~");
		 }
		 
		 
		 
		 
		 
		 System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		 cnt = 5; loop = 0;
		 while(loop < cnt) {
			 System.out.println(++loop + ".Hi Eclipse~~");
		 }
		 
		 
		 
		 
		 
		 System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		 loop = 0;
		 while(true) {
			 System.out.println(++loop +". Hi 이클립스");
			 if(loop == 5)
				 break;
		 }
		 
		 
		 System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		 loop = 0;
		 while(!(++loop > 5)) {
			 System.out.println(loop + ".안녕 오라클");
		 }	// end of while --------------------
		 

		 
		 
		 
		 
		 System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		 
		 loop = 0;
		 while(true) {
			 if(++loop > 10)	//탈출조건
				 break;
			 if(loop%2 == 0)
				 continue; // 아래로 내려가지 않고 while() 의 괄호() 속의 조건식으로 이동한다.
			 System.out.println(loop+ ".Hi Oracle~~");
			 
		 }
		 

		 
		 
		 
		 System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		 System.out.println("\n===5단===");
		 
		 loop = 0;
		 while (++loop < 10) {
			 System.out.println("5*"+loop +"=" + (5*loop));
		 }
		 
		 
		 
		 
		 System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		 System.out.println("\n===6단===");
		 
		 loop = 0;
		 while (true) {
			 if(++loop > 9)
				 break;
			 System.out.println("6*"+loop +"=" + (6*loop));
		 }
		 
		 
		 
		 
		 System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		 System.out.println("\n===7단===");
		 
		 loop = 0;
		 while (!(++loop > 9)) {	//while문의 탈출조건을 쓴다
			 System.out.println("7*"+loop +"=" + (7*loop));
		 }
		 

		 System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		 
		 System.out.printf("%35s\n", "=== 구구단 ===");
		 
		 //9행  8열
		 //jul dan
		 int jul = 0, dan=1;
		 
		 while(!(++jul > 9)) {
			 while(!(++dan > 9)) {
				 String add = (dan < 9)? "\t":"\n";
				 System.out.print(dan + "*" +jul + "=" + (dan*jul) + add);
			 }// end of while---------------

			 //위의 while 문 빠져나올 때 dan의 값은 10 이다.
			 //그러므로 아래와 같이 dan의 값을 1로 초기화 해주어야 한다.
			 dan = 1;
			 
		 }//----------end of while
		 
		 
		 System.out.println("\n>>> 프로그램 종료 <<<\n");

	}		
	
}
