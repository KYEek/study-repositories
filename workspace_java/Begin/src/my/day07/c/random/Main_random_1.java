package my.day07.c.random;

import java.util.Random;

import my.util.MyUtil;

public class Main_random_1 {

	public static void main(String[] args) {
		
		// === 랜덤한 정수를 뽑아낸다. === //
		// 1. Math.random() 메서드를 사용해서 랜덤한 정수 얻어오기
		double random = Math.random();
		System.out.println("random => " + random);
		
		/*
			  java.lang.Math.random() 메소드는
	          0.0 이상 1.0 미만의 실수(double)값을 랜덤하게 나타내어주는 메소드이다. 
	          즉,  0.0 <= 임의의 난수(실수) < 1.0
	          
	          1 부터 10까지 중 랜덤한 정수를 얻어와 본다.
	          
	          랜덤한 정수 = (int)(Math.random()*구간범위) + 시작값;
	          
	          (int)(Math.random()*(10-1+1)) + 1 = (int)(0.00*(10)) + 1 = 0 + 1 = 1
	          
	          (int)(Math.random()*(10-1+1)) + 1 = (int)(0.6117768350198761*(10)) + 1 = 6 + 1 = 7
	          (int)(Math.random()*(10-1+1)) + 1 = (int)(0.1340784053527604*(10)) + 1 = 1 + 1 = 2
	          (int)(Math.random()*(10-1+1)) + 1 = (int)(0.7293160034716356*(10)) + 1 = 7 + 1 = 8
	          (int)(Math.random()*(10-1+1)) + 1 = (int)(0.5867334845103773*(10)) + 1 = 5 + 1 = 6
	          
	          (int)(Math.random()*(10-1+1)) + 1 = (int)(0.999*(10)) + 1 = 9 + 1 = 10
	          
	          -------------------------------------------------------------------------------------
	          
	          2부터 5까지 중 랜덤한 정수를 얻어와 본다.
	          
	          랜덤한 정수 = (int)(Math.random()*구간범위) + 시작값;
	          
	          (int)(Math.random()*(5-2+1)) + 2 = (int)(0.00*(4)) + 2 = 0 + 1 = 1
	          
	          (int)(Math.random()*(5-2+1)) + 2 = (int)(0.6117768350198761*(4)) + 2 = 2 + 2 = 4
	          (int)(Math.random()*(5-2+1)) + 2 = (int)(0.1340784053527604*(4)) + 2 = 0 + 2 = 2
	          (int)(Math.random()*(5-2+1)) + 2 = (int)(0.7293160034716356*(4)) + 2 = 2 + 2 = 4
	          (int)(Math.random()*(5-2+1)) + 2 = (int)(0.5867334845103773*(4)) + 2 = 2 + 2 = 4
	          
	          (int)(Math.random()*(5-2+1)) + 2 = (int)(0.999*(4)) + 2 = 3 + 2 = 5
	          
		 */
		
		//double rnd = Math.random();
		
		int n1 = (int)(Math.random() * (9-0+1));
		int n2 = (int)(Math.random()*(5-3+1)) + 3;;
		char ch1 = (char)((Math.random()*('z'-'a' + 1) + 'a'));
		char ch2 = (char)((Math.random()*('Z'-'A' + 1) + 'A'));
		
		System.out.println("0부터 9 까지 중 랜덤하게 발생한 값: " + n1);
		System.out.println("3부터 7 까지 중 랜덤하게 발생한 값: " + n2);
		System.out.println("a부터 z 까지 중 랜덤하게 발생한 값: " + ch1);
		System.out.println("A부터 Z 까지 중 랜덤하게 발생한 값: " + ch2);
		
		// 2. Random클래스를 사용해서 랜덤한 정수 얻어오기
		
		System.out.println("\n===== new Random() 을 사용한 것 ====");
		
		// === 랜덤한 정수를 뽑아낸다. === //
	      // 보안상 Math.random() 보다는 new Random(); 을 사용하는 것이 더 안전한다. 
		
		Random rndom = new Random();
		
		// 처음얼마부터 마지막얼마까지 랜덤한 정수
	      // ==> rndom.nextInt(마지막수 - 처음수 + 1) + 처음수;
		
		n1 = rndom.nextInt(9-0+1);
		n2 = rndom.nextInt(5-3+1) + 3;;
		ch1 = (char)(rndom.nextInt('z'-'a' + 1) + 'a');
		ch2 = (char)(rndom.nextInt('Z'-'A' + 1) + 'A');
		
		System.out.println("0부터 9 까지 중 랜덤하게 발생한 값: " + n1);
		System.out.println("3부터 7 까지 중 랜덤하게 발생한 값: " + n2);
		System.out.println("a부터 z 까지 중 랜덤하게 발생한 값: " + ch1);
		System.out.println("A부터 Z 까지 중 랜덤하게 발생한 값: " + ch2);
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		System.out.println("인증키 : " + MyUtil.certification_key());
		

	}

}
