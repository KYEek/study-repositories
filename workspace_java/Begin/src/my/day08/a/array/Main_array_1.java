package my.day08.a.array;

public class Main_array_1 {

	public static void main(String[] args) {
		/*
        === 배열(array)이란 ? ===
          동일한 데이터타입을 가지는 여러개의 데이터를 저장할 수 있는 데이터 타입을 말한다. 
          그리고 배열 또한 객체라는 것을 꼭 기억하도록 하자!!!  
     */
		
		
		//1. == 배열의 선언 ==
		
		//int[] arr_subject;
		//또는
		int arr_subject[];
		
		//2. == 선언되어진 배열을 메모리에 크기 할당을 해준다.
		arr_subject = new int[7];
		/*
		 -----------------------------
         | 0 | 1 | 2 | 3 | 4 | 5 | 6 |
         -----------------------------
		 위의 숫자는 배열의 인덱스(index)를 가리키는 번호로써 0부터 시작하여 매번 1씩 증가한다.
		 배열의 인덱스(index)를 "배열의 방번호" 라고 흔히 부른다.
		 
		 배열에 저장된 인덱스 데이터를 표현할때는 아래와 같이 배열명[인덱스번호]로 나타낸다.
		 
		 arr_subject[0]
		 arr_subject[1]
		 arr_subject[2]
		 arr_subject[3]
		 arr_subject[4]
		 arr_subject[5]
		 arr_subject[6]
		 
		 arr_subject[7] ==> 존재하지 않으므로 오류!!
		 
		 배열로 선언된 변수에는 자동적으로 초기값이 들어간다.
		 정수는 0, 실수는 0.0, char는 ' ', String 을 포함한 객체는 null 이 들어간다.
		 */
		
		
		
		System.out.println("배열 arr_subject 의 길이(크기) : " + arr_subject.length);

		System.out.println("\n==============================\n");
		
		
		for (int i = 0; i < 7; i++) {
			int val = arr_subject[i];
			System.out.println("arr_subject[" + i +"] => " + val);
		}
		
		System.out.println("\n==============================\n");
		//3. == 선언되어진 배열에 데이터값을 넣어주기 ==
		
		arr_subject[0] = 100;	//국어점수
		arr_subject[1] = 90;	//영어점수
		arr_subject[2] = 95;	//수학
		arr_subject[3] = 70;	//과학점수
		arr_subject[4] = 98;	//사회점수
		arr_subject[5] = 100;	//음악점수
		arr_subject[6] = 90;	//미술점수
		
		
		for (int i = 0; i < 7; i++) {
			int val = arr_subject[i];
			System.out.println("arr_subject[" + i +"] => " + val);
		}
		

		System.out.println("\n==============================\n");
		int sum = 0;
		for (int i = 0; i < arr_subject.length; i++) {
			sum += arr_subject[i];;
		}
		
		
		System.out.println("총점 : " + sum);
			
		double avg = (double)sum/arr_subject.length;

		avg = Math.round(avg*10)/10.0;
		System.out.println("평균 : " + avg);
		
		///////////////////////////////////
		System.out.println("\n==============================\n");
		
		/*
		 * 	위의 1. == 배열의 선언과 ==
		 * 		2. == 선언되어진 배열을 메모리에 크기 할당을 해준다. == 을
		 *  각각 따로 하지 않고 아래와 같이 동시에 할 수 있다.
		 */
		
		
		int arr_subject_2[] = new int[7];
		
		arr_subject_2[0] = 100;	//국어점수
		arr_subject_2[1] = 90;	//영어점수
		arr_subject_2[2] = 95;	//수학
		arr_subject_2[3] = 70;	//과학점수
		arr_subject_2[4] = 98;	//사회점수
		arr_subject_2[5] = 100;	//음악점수
		arr_subject_2[6] = 90;	//미술점수
		
		for (int i = 0; i < arr_subject_2.length; i++) {
			int val = arr_subject_2[i];
			System.out.println("arr_subject_2[" + i +"] => " + val);
		}
		
		System.out.println("\n==============================\n");
		sum = 0;
		for (int i = 0; i < arr_subject_2.length; i++) {
			sum += arr_subject_2[i];;
		}
		
		
		System.out.println("총점 : " + sum);
		
		avg = (double)sum/arr_subject_2.length;

		avg = Math.round(avg*10)/10.0;
		System.out.println("평균 : " + avg);
		
		/////////////////////////////////////////////////////
		
		/*
		 * 	위의 1. == 배열의 선언과 ==
		 * 		2. == 선언되어진 배열을 메모리에 크기 할당을 해준다. == 과
		 * 		3. == 선언되어진 배열에 데이터값을 넣어주기 == 를
		 *  각각 따로 하지 않고 아래와 같이 동시에 할 수 있다.
		 *  
		 */
		
		System.out.println("\n==========================\n");
		
		int arr_subject_3[] = new int[] {100,90,95,70,98,100,90};
		
		
		for (int i = 0; i < arr_subject_3.length; i++) {
			int val = arr_subject_3[i];
			System.out.println("arr_subject_3[" + i +"] => " + val);
		}
		
		System.out.println("\n==============================\n");
		sum = 0;
		for (int i = 0; i < arr_subject_3.length; i++) {
			sum += arr_subject_3[i];;
		}
		
		
		System.out.println("총점 : " + sum);
		
		avg = (double)sum/arr_subject_3.length;

		avg = Math.round(avg*10)/10.0;
		System.out.println("평균 : " + avg);
		
			/////////////////////////////////////////////////////
		
			/*
			* 	위의 1. == 배열의 선언과 ==
			* 		2. == 선언되어진 배열을 메모리에 크기 할당을 해준다. == 과
			* 		3. == 선언되어진 배열에 데이터값을 넣어주기 == 를
			*  각각 따로 하지 않고 아래와 같이 동시에 할 수 있다.
			*  new int[]은 생략 가능하다.!!
			*/
		
		System.out.println("\n==========================\n");
		
		int[ ] arr_subject_4 = { 100, 90, 95, 70, 98, 100, 90 };
		
		for (int i = 0; i < arr_subject_4.length; i++) {
			int val = arr_subject_4[i];
			System.out.println("arr_subject_4[" + i + "] => " + val);
		}
		
		System.out.println("\n==============================\n");
		sum = 0;
		for (int i = 0; i < arr_subject_4.length; i++) {
			sum += arr_subject_4[i];
			;
		}
		
		System.out.println("총점 : " + sum);
		
		avg = (double) sum / arr_subject_4.length;
		
		avg = Math.round(avg * 10) / 10.0;
		System.out.println("평균 : " + avg);
				
		
		
	}//-------------end of main()

}
