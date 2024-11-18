package test;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int count = Integer.parseInt(sc.nextLine()) ;
		int[] num_arr = new int[count];
		
		for(int i = 0; i < num_arr.length; i++) {
			String str = sc.nextLine();
			String[] str_arr = str.split(" ");
			if(str_arr.length == 2) {
				num_arr[i] = Integer.parseInt(str_arr[0]) + Integer.parseInt(str_arr[1]);
			}
		}
		
		for(int i = 0; i < num_arr.length; i++) {
			sb.append("Case #"+ (i+1) + ": " + num_arr[i] + "\n");
		}
		System.out.println(sb.toString());
	}

}
