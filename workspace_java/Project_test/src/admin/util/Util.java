package admin.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
	
	// === 시작일 유효성검사 메소드 === //
	public static boolean check_start_date(String event_start) {
		Pattern p = Pattern.compile("^[0-9]{8}$");

		Matcher m = p.matcher(event_start);

		if (!m.matches()){
			return false;
		}
		
		// 달력에 존재하지 않는 날
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");		// 날짜 표기 형식을 연월일로 지정 ==> 19970127

		sdf.setLenient(false);			// 날짜 검사할 때 필수..

		try {
			sdf.parse(event_start);			// 문자열을 날짜포맷으로 변환 ==> 19970127
		} catch (ParseException e) {
			return false;
		}
		return true;					// 모두 통과 시 true 반환
	}// end of check_start_date(String event_start) ----------------------------
	
	// === 종료일 유효성검사 메소드 === //
	public static int check_end_date(String event_start, String event_end) {
		
		Pattern p = Pattern.compile("^[0-9]{8}$");

		Matcher m = p.matcher(event_end);
		
		// 입력 형식에 맞지 않을 경우
		if (!m.matches()){
			return -1;
		}
		
		int start = Integer.parseInt(event_start);
		int end   = Integer.parseInt(event_end);
		
		// 시작일이 끝나는 날보다 뒤일 경우
		if (start > end) {
			return 0;
		}
		
		// 달력에 존재하지 않는 날
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");		// 날짜 표기 형식을 연월일로 지정 ==> 19970127

		sdf.setLenient(false);			// 날짜 검사할 때 필수..

		try {
			sdf.parse(event_start);			// 문자열을 날짜포맷으로 변환 ==> 19970127
		} catch (ParseException e) {
			return -1;
		}
		
		if (start == end) {
			return 1;
		}
		
		return 1;					// 모두 통과 시 1 반환
	}// end of check_start_date(String event_start) ----------------------------
	
	
	// == 숫자 맞추기 게임 메소드 == //
	public static void target_number() {
		
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int npc_num = random.nextInt(30 -1 + 1) + 1;
		int pc_num 	= 0;
		int count 	= 0;
		do {
			try {
				System.out.println("==== >>> 숫자맞추기 게임 <<< ====");
				System.out.println(">> 종료하시려면 엔터를 입력하세요 <<");
				System.out.println("\n>> 1 부터 30 사이의 숫자 중 하나를 고르세요.");
				System.out.print(">> 숫자입력: ");
				String input = sc.nextLine();

				if (input.isEmpty()) {
					System.out.println(">> 게임종료.. <<");
					return;
				}
				
				pc_num = Integer.parseInt(input);
				if (!(1<=pc_num && pc_num <= 30)) {
					System.out.println("1~30 사이의 숫자만 입력하세요!!");
					continue;
				}// 범위밖의 숫자를 입력했을 경우 -------------------
				
				if (npc_num != pc_num) {		// 정답이 아닐 때
					count++;
					if (npc_num > pc_num) {
						System.out.println("\n>> 땡!! [힌트] "+ pc_num +"보다 큽니다.\n");
					} else {
						System.out.println("\n>> 땡!! [힌트] "+ pc_num +"보다 작습니다.\n");
					}
				}
				else {		// 정답일 때
					count++;
					System.out.println("\n정답! " + count +"번 만에 맞췄습니다.");
					
					// 횟수별로 맞췄을 때
					if (count <= 5) {
						System.out.println("포인트 30 지급!!");
						// 쿼리문 실행 필요
						break;
					}
					else if (5<= count && count <= 7) {
						System.out.println("포인트 10 지급!!");
						// 쿼리문 실행 필요
						break;
					}
					else if (7<= count && count <= 10) {
						System.out.println("포인트 0 지급!!");
						// 쿼리문 실행 필요
						break;
					}
					else{
						System.out.println("10회 이상으로 포인트 10차감..");
						// 쿼리문 실행 필요
						break;
					}
					
				}// end of if() ----------------------
				
			} catch(NumberFormatException e) {
				System.out.println(">> [경고] 숫자만 입력하세요!! <<");
			}
		} while (true);
		sc.close();
	}// end of public static void target_number() ------------------------------
	
	
	// == 가위바위보 게임 메소드 == //
	public static void rock_scissors_paper() {
		
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int choice 		= 0;
		int win_conut 	= 0;
		
		for (int i=0; i<3; i++) {
			try {
				System.out.println("\n==== >>> 가위바위보 게임 <<< ====");
				System.out.println(">> 종료하시려면 엔터를 입력하세요 <<");
				System.out.println("\n============= 선택 ==============\n"+
								   "\t1.가위\t2.바위\t3.보\n"+
								   "================================");
	
				System.out.print(" >> 선택하세요 => ");
				String input = sc.nextLine();
				
				if (input.isEmpty()) {
					System.out.println(">> 게임 종료.. <<");
					return;
				}
				
				choice = Integer.parseInt(input);
														
				if (!(1 <= choice && choice <= 3)) {
					System.out.println("[경고] 사용할 수 없는 값입니다 !!\n");
					i--;
					continue;
				}// end of if()----------------------
				
				int pc_num = random.nextInt(3 - 1 + 1) + 1;
				
				// 사용자가 이긴 경우
				if ((choice == 1 && pc_num == 3) || 
					(choice == 2 && pc_num == 1) ||
					(choice == 3 && pc_num == 2)) {
					win_conut++;
					System.out.println("승리! 승리횟수: " + win_conut);
				}
				// 사용자가 진 경우
				else if ((choice == 3 && pc_num == 1) || 
						 (choice == 1 && pc_num == 2) ||
						 (choice == 2 && pc_num == 3)) {

					System.out.println("패배ㅠ 승리횟수: " + win_conut);
				}
				// 비긴 경우
				else
					System.out.println("무승부. 승리횟수: " + win_conut);
				// end of if()----------------------

			}catch (NumberFormatException e) {
				System.out.print("[경고] 숫자로만 입력하세요!!\n");
			}// end of try~catch()---------------------
			
		}// end of for() --------------------
		
		if (win_conut >= 2) {
			// 쿼리실행해야
			System.out.println("\n>> 최종결과: "+ win_conut +"승, 포인트 20 지급!!");
		}
		else if (win_conut == 1) {
			// 쿼리실행해야
			System.out.println("\n>> 최종결과: "+ win_conut +"승, 포인트 0 지급");
		}
		else {
			// 쿼리실행해야
			System.out.println("\n>> 최종결과: "+ win_conut +"승, 포인트 10 차감..ㅠ");
		}	
		sc.close();
	}// end of public static void target_number() ------------------------------
	
	
	
	public static boolean check_jubun(String jubun) {
		Pattern p = Pattern.compile("^[0-9]{6}[1-4]$");

		Matcher m = p.matcher(jubun);

		if (!m.matches()){
			return false;
		}

		// 꽝 : 달력에 존재하지 않는 날
		String str = ("1".equals(jubun.substring(6))||"2".equals(jubun.substring(6)))?"19":"20";	// 입력한 주민번호의 마지막 숫자가 1 or 2 인 경우 "19", 아니면 "20"
		jubun = str + jubun.substring(0,6);		// 입력 받아온 주번의 처음부터 5번째 문자열까지 잘라 str과 결합 ==> "19"+"970127" ==> "19970127"

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");		// 날짜 표기 형식을 연월일로 지정 ==> 19970127

		sdf.setLenient(false);			// 날짜 검사할 때 필수..

		try {
			sdf.parse(jubun);			// 문자열을 날짜포맷으로 변환 ==> 19970127
		} catch (ParseException e) {
			return false;
		}

		return true;					// 모두 통과 시 true 반환
	}// end of check_jubun(String jubun) ----------------------------
	
	
	
	
	
	
	
	
}
