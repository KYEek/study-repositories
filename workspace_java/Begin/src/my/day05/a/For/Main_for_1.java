package my.day05.a.For;

/*
 **** 반복문(loop) ==> for 문 ****
  
    ※ for 문의 형식
   
    for(초기화; 조건식; 증감식) {
          반복해서 실행할 문장;
    }  
    
    ▣ 순서
    1) 초기화;
    2) 조건식; (조건식이 참(true)이라면 반복해서 실행할 문장;을 실행하고서 } 을 못빠져나간다.         
                              조건식이 거짓(false)이라면 반복해서 실행할 문장;을 실행하지 않고
               } 을 빠져나간다.)
    3) 증감식
    4) 조건식; (조건식이 참(true)이라면 반복해서 실행할 문장;을 실행하고서 } 을 못빠져나간다.         
                              조건식이 거짓(false)이라면 반복해서 실행할 문장;을 실행하지 않고
               } 을 빠져나간다.) 
    5) 증감식
    6) 조건식; (조건식이 참(true)이라면 반복해서 실행할 문장;을 실행하고서 } 을 못빠져나간다.         
                              조건식이 거짓(false)이라면 반복해서 실행할 문장;을 실행하지 않고
               } 을 빠져나간다.)                                                  
    
*/



public class Main_for_1 {

	public static void main(String[] args) {
		
		for(int i=0; i < 10; i++) {
	          System.out.println((i+1) + ". 안녕 자바~~");
	    }  
		
		
		/*
		 * 1. 안녕자바~
		 * 2. 안녕자바~
		 * 3. 안녕자바~
		 * 4. 안녕자바~
		 * 5. 안녕자바~
		 * 6. 안녕자바~
		 * 7. 안녕자바~
		 * 8. 안녕자바~
		 * 9. 안녕자바~
		 * 10. 안녕자바~
		 */
		
		
		
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		for(int i=0; i < 10; i+=1) {	//i+=1; 은 i= i+1; 와 같다.
	          System.out.println((i+1) + ". 안녕 Java~~");
	    }  
		
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		for(int i=0; i<10; i++) {
			System.out.println(++i + ".안녕 이클립스~~");
		}
		
		
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		for (int i = 0; i < 10; i+=2) {
			System.out.println((i+1) + ".안녕 Eclipse~~");
		}
		
		
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		for(int i=0, j=0; i<10; i++, j+=2) {
			System.out.println((j+1) + ". 안녕 오라클~");
		}
		

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		for(int i =10; i>0; i--) {
			System.out.println(i + ". 안녕 Oracle");
		}
		
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		for(int i =10; i>0; i-=1) {
			System.out.printf("%2d. 안녕 스프링%n", i);
		}
		
		
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		for(int i =10; i>0; i--) {
			if(i%2 !=0 )	//i%2 은 i 를 2로 나누었을때의 나머지를 나타내는 것이다.
				System.out.println(i + ". 안녕 Spring~~");
		}
		
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		for(int i =10; i>0; i-=2) {
				System.out.println((i-1)+ ". 안녕하십니까?~~");
		}
		
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		for(int i =10; i>0; i--) {
				System.out.println(--i+ ". Hello?~~");
		}
		

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		for(int i=0, j=9; i<5; i++, j-=2) {
			System.out.println(j + ". 헬로~~");
		}
		
		int i = 0;
		System.out.println("============================");
		for(; i<5; i++) {
			System.out.println((i+1) + ".길동");
		}
		
		System.out.println("길동을 "+ i +"번 반복했습니다.");
		

	}// end of main(String[] args)=================================

}
