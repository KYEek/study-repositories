package my.day07.c.random;

import java.util.Random;
import java.util.Scanner;

public class Main_binggo_3 {

	/*
	   PC 가 1 부터 50 까지 중 랜덤한 숫자를 낸다.
	   그러면 우리가 그 숫자를 맞추는 게임인데 몇 번만에 맞추는지가 관건인 게임이다.
	   
	   예> 47
	   
	   >> 숫자가 얼마일까요? 49엔터
	   == 틀렸습니다. [힌트] 49보다 적습니다.
	   
	   >> 숫자가 얼마일까요? 20엔터
	   == 틀렸습니다. [힌트] 20보다 큽니다.
	   
	   >> 숫자가 얼마일까요? 30엔터
	   == 틀렸습니다. [힌트] 30보다 큽니다.
	   
	   >> 숫자가 얼마일까요? 47엔터
	   == 빙고~~^^
	   == 4번에 만에 맞추었습니다.
	*/
	
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		
		int pc_num = random.nextInt((50-1+1) + 1);
		int cnt = 0;
		
		do {
			System.out.print(">> 숫자가 얼마일까요? ");
			int input = Integer.parseInt(sc.nextLine());
			cnt++;
			if(pc_num == input) {
				System.out.println("== 빙고~~^^");
				System.out.println(cnt +"번 만에 맞추었습니다.\n");
				break;
			}
			else if(input < pc_num) {
				System.out.print("== 틀렸습니다.\t");
				System.out.println("[힌트]" + input + "보다 큽니다.\n");
			}
			else {
				System.out.print("== 틀렸습니다.\t");
				System.out.println("[힌트]" + input + "보다 작습니다.\n");
			}
			
			
		} while (true);
		
		sc.close();
		

	}

}
