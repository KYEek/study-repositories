package my.day09.a.twodimension.array;

public class Main_twodimension_array_1 {

	public static void main(String[] args) {
		
		
		// === 1차원 배열 === //
		int[] subject_arr = new int[5];  // 1행 5열
	   /*
	                 ----------------------
	       값 ==>     | 0 | 0 | 0 | 0 | 0 | 
	                 ----------------------
	       index ==>   0   1   2   3   4        
	   */
		subject_arr[0] = 100;
		subject_arr[1] = 90;
		subject_arr[2] = 95;
		subject_arr[3] = 80;
		subject_arr[4] = 88;
		
		// === 2차원 배열 === //
		int[][] point_arr = new int[4][3];
		
		/*
        값 ==> 
              --------------
              | 0 | 0 | 0 | 
              --------------
              | 0 | 0 | 0 |
              --------------
              | 0 | 0 | 0 |
              --------------
              | 0 | 0 | 0 |
              --------------
          
         
        index ==>
               -----------------------------
               | [0][0] | [0][1] | [0][2] |
               -----------------------------
               | [1][0] | [1][1] | [1][2] |
               -----------------------------
               | [2][0] | [2][1] | [2][2] |
               -----------------------------
               | [3][0] | [3][1] | [3][2] |
               -----------------------------
                 
		*/
		
		point_arr[0][0] = 10;
		point_arr[0][1] = 20;
		point_arr[0][2] = 30;
		
		point_arr[1][0] = 40;
		point_arr[1][1] = 50;
		point_arr[1][2] = 60;
		
		point_arr[2][0] = 70;
		point_arr[2][1] = 80;
		point_arr[2][2] = 90;
		
		point_arr[3][0] = 100;
		point_arr[3][1] = 100;
		point_arr[3][2] = 100;
		
		System.out.println("point_arr.length : " + point_arr.length);
		//point_arr.length : 4
		//2차원배열명.length 은 행의 길이가 나온다

		System.out.println("point_arr.length[0] : " + point_arr[0].length);
		//point_arr[0].length : 3
		//2차원배열명[행의인덱스].length 은 그 행에 존재하는 열의 길이(크기)가 나온다.
		
		System.out.println("point_arr.length[1] : " + point_arr[0].length);
		//point_arr[0].length : 3
		//2차원배열명[행의인덱스].length 은 그 행에 존재하는 열의 길이(크기)가 나온다.
		
		System.out.println("point_arr.length[2] : " + point_arr[0].length);
		//point_arr[0].length : 3
		//2차원배열명[행의인덱스].length 은 그 행에 존재하는 열의 길이(크기)가 나온다.
		System.out.println("point_arr.length[3] : " + point_arr[0].length);
		//point_arr[0].length : 3
		//2차원배열명[행의인덱스].length 은 그 행에 존재하는 열의 길이(크기)가 나온다.
		
		System.out.println("\n------------------------------------------\n");
		
		for(int i = 0; i< point_arr.length; i++) {
			for (int j = 0; j<point_arr[i].length; j++) {
				String add = (j<point_arr[i].length-1)?",":"\n";
				System.out.printf("%3d%s", point_arr[i][j], add);
			}//--------------내부 for문
		}//-----------------------------외부 for문
		
		System.out.println("\n===== 성적결과 =====\n");
		
							//국어 영어 수학
		int[][] jumsu_arr = { {90,80,70}	// 이순신
							, {80,85,76}	// 엄정화
							, {80,85,76}	// 홍길동
							, {85,70,90}	// 공유
							, {60,80,50}};	// 아이유
		int[] total_arr = new int[jumsu_arr.length];
		
		//각 학생별 총점을 저장시켜두는 곳이다.
		String[] result_arr = new String[jumsu_arr.length];
		// 각 학생별로 등수를 제외한 국어,영어,수학,총점,평균,학점까지 성적결과를 저장시켜두는 곳이다.
		
		System.out.println("----------------------------------------------------");
		System.out.println("국어\t영어\t수학\t총점\t평균\t학점\t등수");
		System.out.println("----------------------------------------------------");
		
		for(int i = 0; i<jumsu_arr.length; i++) {
			String result = "";
			int sum = 0;
			for(int j = 0; j<jumsu_arr[i].length; j++) {
				sum += jumsu_arr[i][j];
			}//---------------내부 for문
			double avg = Math.round((double)sum/jumsu_arr[i].length*10)/10.0;
			
			char grade = ' ';
			
			switch ((int)avg/10) {	//byte, short, int, char, String만 들어올 수 있다
			case 10:
			case 9:
				grade = 'A';
				break;
			case 8:
				grade = 'B';
				break;
			case 7:
				grade = 'C';
				break;
			case 6:
				grade = 'D';
				break;

			default:
				grade = 'F';
				break;
			}
			
			result += sum + "\t"  + avg + "\t"  +grade + "\t";
			//System.out.println(result);
			
			// 각 학생별로 국어,영어, 수학, 총점, 평균, 학점까지 결과만을 구해서 배열 result_arr 에 저장시켜 둔다.
			
			result_arr[i] = result;
			total_arr[i] = sum;		//각 학생별 총점을 구해서 배열 total_arr에 저장시켜 둔다.
			
		}//--------------------------------외부 for문
		
		// >>> 등수를 구해서 성적결과 출력하기 <<<
		
		for(int i = 0; i < jumsu_arr.length; i++) {
			int rank = 1;
			
			for (int j = 0; j < total_arr.length; j++) {
				if(i != j && total_arr[i] < total_arr[j]) {
					//total_arr[i] 이 자신의 총점
					//total_arr[j] 이 다른사람의 총점
					rank++;
				}
			}
			result_arr[i] += rank;
			//System.out.println(result_arr[i]+rank);
		}
		
		for(int i = 0; i<jumsu_arr.length; i++) {
			System.out.println(jumsu_arr[i][0] + "\t" + jumsu_arr[i][1] + "\t" + jumsu_arr[i][2]+ "\t" + result_arr[i]);
		}
		
		/*		
				----------------------------------------------------
				국어		영어		수학		총점		평균			학점		등수
				----------------------------------------------------
				90		80		70		240		80.0		B		4
				80		85		76		241		80.3		B		2
				80		85		76		241		80.3		B		2
				85		70		90		245		81.7		B		1
				60		80		50		190		63.3		D		5
				====================================================
				395		400		362	
				79.0	80.0	72.4
		 */

		int[] total_subject_arr = new int[jumsu_arr[0].length];		//과목 총점
		double[] avg_subject_arr = new double[jumsu_arr[0].length];	//과목 평균
		
		
		System.out.println("====================================================");
		for(int i = 0; i<total_subject_arr.length; i++) {
			for(int j = 0; j<jumsu_arr.length; j++) {
				total_subject_arr[i] += jumsu_arr[j][i];
			}
			/*   
	         total_subject_arr[0] += jumsu_arr[0][0];
	                                 jumsu_arr[1][0];
	                                 jumsu_arr[2][0];
	                                 jumsu_arr[3][0];
	                                 jumsu_arr[4][0];
	                                 
	                                 
	         total_subject_arr[1] += jumsu_arr[0][1];
	                                 jumsu_arr[1][1];
	                                 jumsu_arr[2][1];	
	                                 jumsu_arr[3][1];
	                                 jumsu_arr[4][1];
	                                 
	                                 
	         total_subject_arr[2] += jumsu_arr[0][2];
	                                 jumsu_arr[1][2];
	                                 jumsu_arr[2][2];
	                                 jumsu_arr[3][2];
	                                 jumsu_arr[4][2];                                 
	      */
			avg_subject_arr[i] =Math.round(total_subject_arr[i]/(double)jumsu_arr.length*10)/10.0 ;
		}
		for (int i = 0; i<total_subject_arr.length; i++) 
			System.out.print(total_subject_arr[i] + "\t");
		
		System.out.println();
		
		for (int i = 0; i<total_subject_arr.length; i++) 
			System.out.print(avg_subject_arr[i] + "\t");
		
	}// end of main(String[] args)-----------------------------------

}
