package my.day04.e.sungjuk.Switch;

import java.util.Scanner;

public class Sungjuk {
   // === field ===
   
   String hakbun, name;  // "091234_예시"
   byte kor, eng, math;  // byte => -128 ~ 127
   short age;            // short => -32768 ~ 32767, 20~50 로 제한한다.
   String str_input = null;
   String subject = null;
   int status = 0;
   Scanner sc = new Scanner(System.in);
   
   
   
   // === method ===
   boolean check_jumsu(byte jumsu) {
      
      if(0 <= jumsu && jumsu <=100) {
         return true; // return을 만나면 해당 메소드가 종료된다.
      } 
      else {
         System.out.println("[경고] 입력하시는 점수는 0~100 범위여야만 합니다.\n");
         return false;
      }
      
   } // end of boolean check_jumsu(byte age)------------------
   
   boolean check_age(short age) {
      
      if(20 <= age && age <= 50) {
         return true; // return을 만나면 해당 메소드가 종료된다.
      } 
      else {
         System.out.println("[경고] 입력하시는 나이는 20~50 범위여야만 합니다.\n");
         return false;
      }
      
   } // end of boolean check_jumsu(short age)------------------
   
   
   // 프린트 실행 sungjuk_print 함수
   void sungjuk_print(String name2) {
	   System.out.println("====" + name2 + "님의 성적결과=====");
	   System.out.print("1. 학번 >> ");
	      hakbun = sc.nextLine(); // "091234"로 넣을 수 있다.
	      
	      System.out.print("2. 성명 >> ");
	      name = sc.nextLine();
	      String present = "";
	      
	      
	      try {
	         
	         // >> 유효성 검사하기(올바른 데이터인지, 틀린 데이터인지 검사하는 것) <<
	         status = 1;
	         System.out.print("3. 국어점수 >> ");
	          str_input = sc.nextLine();
	         byte kor = Byte.parseByte(str_input);
	         
	         if(!check_jumsu(kor)) {
	            sc.close();
	            return; // return을 만나면 해당 메소드가 종료된다.
	         }

	         else {
	        	 this.kor = kor;
	        	 
	         }
	         
	         status = 2;
	         System.out.print("4. 영어점수 >> ");
	         str_input = sc.nextLine();
	         byte eng = Byte.parseByte(str_input);
	         
	         if(!check_jumsu(eng)) {
	            sc.close();
	            return; // return을 만나면 해당 메소드가 종료된다.
	         }

	         else {
	        	 this.eng = eng;
	        	 
	         }
	         
	         status = 3;
	         System.out.print("5. 수학점수 >> ");
	         str_input = sc.nextLine();
	         byte math = Byte.parseByte(str_input);
	         
	         if(!check_jumsu(math)) {
	            sc.close();
	            return; // return을 만나면 해당 메소드가 종료된다.
	         }
	         else {
	        	 this.math = math;
	        	 
	         }
	         
	         double avg = (this.kor + this.eng + this.math)/3.0;
	         char hakjum = ' ';
	         	         	         
	         System.out.println("6. 평균(반올림이 없는것) : " + avg);
	         System.out.println("7. 평균(소수부 첫쨰자리까지 반올림) : " + Math.round(avg*10)/10.0);
	         System.out.println("8. 평균(소수부 둘째자리까지 반올림) : " + Math.round(avg*100)/100.0);
	         
	        /* 
	         if(avg >= 90) {
	        	 hakjum = 'A';
	         }
	         
	         else if (avg >= 80) {
	        	 hakjum = 'B';
	         }
	         
	         else if (avg >= 70) {
	        	 hakjum = 'C';
	         }
	         else if (avg >= 60) {
	        	 hakjum = 'D';
	         }
	         else {
	        	 hakjum = 'F';
	         }
	         */
	         switch ((int)avg/10) {
			case 10:
			case 9:		//값이 10 또는 9 라면
				hakjum = 'A';
				break;
			case 8:		//값이 8 이라면
				hakjum = 'B';
				break;
			case 7:		//값이 7 이라면
				hakjum = 'C';
				break;
			case 6:		//값이 6 이라면
				hakjum = 'D';
				break;
			default:	//값이 6 미만이라면 이라면
				hakjum = 'F';
				break;
			}

	         System.out.println("9. 학점 : " + hakjum);
	         
	         
	         status = 4;
	         System.out.print("10. 나이 >> ");
	         str_input = sc.nextLine();
	         short age = Short.parseShort(str_input);
	         
	         if(!check_age(age)) {
	            sc.close();
	            return;
	         }
	         else {
	        	 this.age = age;
	        	 
	         }
	         
	         switch (hakjum) {
			case 'A':
				present += "놀이공원이용권, ";
			case 'B':
				present += "치킨, ";
			case 'C':
				present += "피자, ";
			case 'D':
				present += "아이스크림";
			break;	//break; 를 만나야만 switch case 문을 빠져나간다.

			default:
				present = "꿀밤3대";
				break;
			}
	         
	         System.out.println("11. 선물 : " + present);
	         

	         
	         
	      } catch(NumberFormatException e) {
	         //System.out.println(">> 입력하신 " + subject + " "+ str_input + "는 올바른 데이터가 아닙니다.");
	    	 if (status == 1) {
	             subject = "국어";
	    	 }
	    	 else if(status == 2) {
	             subject = "영어";
	    		 
	    	 }
	    	 else if(status == 3) {
	             subject = "수학";
	    		 
	    	 }
	    	 else if(status == 4) {
	    		 subject = "나이";
	    	 }

			 System.out.println(">> 입력하신 " + subject + " "+ str_input + "는 올바른 데이터가 아닙니다.");
	      }
   }
}
