package test;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int amount = 0;
		int a = 0, b = 0;
		int count = 0, sum  = 0;
		
		Scanner sc = new Scanner(System.in);
		
		amount = Integer.parseInt(sc.nextLine());
		count = Integer.parseInt(sc.nextLine());
		
		for (int i= 0; i < count; i ++) {
			a = sc.nextInt();
			b = sc.nextInt();
			
			sum += (a * b);
		}
		if (sum == amount)
			System.out.println("Yes");
		else
			System.out.println("No");
		
	}

}
