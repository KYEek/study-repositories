package my.day04.e.sungjuk.Switch;

//import java.util.Scanner;

public class Main_sungjuk {

   public static void main(String[] args) {
      
      //Scanner sc = new Scanner(System.in);
      Sungjuk sj = new Sungjuk(); // 기본생성자는 생략되어 있다.
      //String str_input = null;
     // String subject = null;
      //int status = 0;
      
      
      /*
      System.out.print("1. 학번 >> ");
      sj.hakbun = sc.nextLine(); // "091234"로 넣을 수 있다.
      
      System.out.print("2. 성명 >> ");
      sj.name = sc.nextLine();
      
      
      try {
         
         // >> 유효성 검사하기(올바른 데이터인지, 틀린 데이터인지 검사하는 것) <<
         status = 1;
         System.out.print("3. 국어점수 >> ");
          str_input = sc.nextLine();
         byte kor = Byte.parseByte(str_input);
         
         if(!sj.check_jumsu(kor)) {
            sc.close();
            return; // return을 만나면 해당 메소드가 종료된다.
         }

         else {
        	 sj.kor = kor;
        	 
         }
         
         status = 2;
         System.out.print("4. 영어점수 >> ");
         str_input = sc.nextLine();
         byte eng = Byte.parseByte(str_input);
         
         if(!sj.check_jumsu(eng)) {
            sc.close();
            return; // return을 만나면 해당 메소드가 종료된다.
         }

         else {
        	 sj.eng = eng;
        	 
         }
         
         status = 3;
         System.out.print("5. 수학점수 >> ");
         str_input = sc.nextLine();
         byte math = Byte.parseByte(str_input);
         
         if(!sj.check_jumsu(math)) {
            sc.close();
            return; // return을 만나면 해당 메소드가 종료된다.
         }
         else {
        	 sj.math = math;
        	 
         }
         X
         
         
         status = 4;
         System.out.print("6. 나이 >> ");
         str_input = sc.nextLine();
         short age = Short.parseShort(str_input);
         
         if(!sj.check_age(age)) {
            sc.close();
            return;
         }
         else {
        	 sj.age = age;
        	 
         }
         
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
      */
	  
      sj.sungjuk_print("이순신");
      sj.sungjuk_print("연규영");
      sj.sungjuk_print("가나다");
	   

   } // end of main(String[] args)----------------

}

