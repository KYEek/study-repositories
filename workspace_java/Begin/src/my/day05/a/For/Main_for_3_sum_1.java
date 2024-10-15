package my.day05.a.For;

public class Main_for_3_sum_1 {

	public static void main(String[] args) {
		
		int sum = 0;	//sum은 누적의 합을 저장시키는 변수
		
		 for (int i = 1; i <=10; i++) {
			 sum += i; // sum = sum + i 와 같은 뜻이다
		 }
		 
		 System.out.println("1부터 10 까지의 누적의 합계 : " + sum);
		
		// sum = 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 + 10
		// 1부터 10 까지의 누적의 합계 : 55
		 
		 sum = 0;
		 String str="";
		 for (int i = 1; i<=10; i++) {

			 sum += i;
			 
			 if (i<10)
				 str += i+"+";
			 else
				 str += i;
			 //System.out.println(str);
		 }//end of for--------------
		 System.out.println(str + "=" + sum);
		// 1+2+3+4+5+6+7+8+9+10 = 55
		 
		 System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		 
		 sum = 0;
		 str="";
		 for (int i = 1; i<=10; i++) {
			 String temp = (i<10) ? i+"+" : i+"=";
			 str += temp;
			 sum += i;
			 			
		 }//end of for--------------
		 System.out.println(str + sum);
		// 1+2+3+4+5+6+7+8+9+10 = 55
	}

}
