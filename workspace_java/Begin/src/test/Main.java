package test;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		try {
		int count = Integer.parseInt(sc.nextLine());
		if(count >0 && count <= 100) {

			for (int i = 0; i < count; i++) {
				for(int a = count-1; a > i; a--) {
					sb.append(" ");
				}
				for(int b = 0; b <= i; b++)
				{
					sb.append("*");
				}
				sb.append("\n");
			}

			System.out.println(sb.toString());
		}
		}
		catch (NumberFormatException e) {
			System.out.println("1에서 100 사이의 숫자를 입력하세요");
		}
	}

}
