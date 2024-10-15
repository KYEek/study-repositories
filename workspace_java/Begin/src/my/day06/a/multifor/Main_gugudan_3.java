package my.day06.a.multifor;

		/*
			== 구구단 ==
			2*1=2    3*1=3    4*1=4    ....   9*1=9
			2*2=4    3*2=6    4*2=8    ....   9*2=18
			2*3=6    3*3=9    4*3=12   ....   9*3=27
			2*4=8    3*4=12   4*4=16   ....   9*4=36
			2*5=10   3*5=15   4*5=20   ....   9*5=45
			2*6=12   3*6=18   4*6=24   ....   9*6=54
			2*7=14   3*7=21   4*7=28   ....   9*7=63
			2*8=16   3*8=24   4*8=32   ....   9*8=72
			2*9=18   3*9=27   4*9=36   ....   9*9=81
		*/


public class Main_gugudan_3 {

	public static void main(String[] args) {
		
		System.out.printf("%s님의 나이는 %d세이고, 신장은 %fcm 입니다.", "이순신", 25, 178.9);
		System.out.println("");
		System.out.printf("%30s%s\n","== 구구단 ==","하하하");
		// 글자크기 30 만큼을 잡아준 후 오른쪽 정렬로 "== 구구단 ==" 을 채워준다.
		System.out.printf("%-30s%s\n","== 구구단 ==","하하하");
		// 글자크기 30 만큼을 잡아준 후 왼쪽 정렬로 "== 구구단 ==" 을 채워준다.
		System.out.printf("\n%30s\n","== 구구단 ==");
		// 글자크기 30 만큼을 잡아준 후 왼쪽 정렬로 "== 구구단 ==" 을 채워준다.
		
		for(int row = 1; row<=9; row++) {
			
			for (int col = 2; col<=9; col++) {
				System.out.printf(col + "*" + row + "=%-4d", col*row);
			}
			System.out.println("\n");
		}//--------------end of for

	}

}
