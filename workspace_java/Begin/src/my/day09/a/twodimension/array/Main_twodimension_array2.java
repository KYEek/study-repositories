package my.day09.a.twodimension.array;

public class Main_twodimension_array2 {

	public static void main(String[] args) {
		
		int[][] num_arr = new int[4][];	//4행 null 열
		/*
		
		// 열의 크기를 성정하지 않았으므로 NullPointerException
		num_arr[0][0] = 10;
		num_arr[0][1] = 20;
		num_arr[0][2] = 30;
		
		num_arr[1][0] = 40;
		num_arr[1][1] = 50;
		num_arr[1][2] = 60;
		*/
		
		num_arr[0] = new int[3];
		num_arr[1] = new int[2];
		num_arr[2] = new int[4];
		num_arr[3] = new int[3];
		
		num_arr[0][0] = 10;
		num_arr[0][1] = 20;
		num_arr[0][2] = 30;
		
		num_arr[1][0] = 40;
		num_arr[1][1] = 50;
		//num_arr[1][2] = 60;	//ArrayIndexOutOfBoundsException 발생
		
		num_arr[2][0] = 70;
		num_arr[2][2] = 90;
		
		for(int i = 0; i<num_arr.length; i++) {				//행
			for (int j = 0; j<num_arr[i].length; j++) {		//열
				String add = (j<num_arr[i].length-1)?",":"\n";
				System.out.printf("%2d%s", num_arr[i][j], add);
			}
		}
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		int[][] num_arr2 = { {10,20,30,}
							,{40,50}
							,{70,0,90,0}
							,{0,0,0}
							};
		
		for(int i = 0; i<num_arr2.length; i++) {				//행
			for (int j = 0; j<num_arr2[i].length; j++) {		//열
				String add = (j<num_arr2[i].length-1)?",":"\n";
				System.out.printf("%2d%s", num_arr2[i][j], add);
			}
		}

	}//------------------end of main--------------------------------

}
