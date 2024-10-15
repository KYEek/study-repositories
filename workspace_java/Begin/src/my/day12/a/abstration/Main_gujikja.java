package my.day12.a.abstration;

import java.util.Scanner;

public class Main_gujikja {

	public static void main(String[] args) {
		Gujikja[] gu_arr = new Gujikja[5];
		
		///////////////////////////////////
		
		Gujikja gu1 = new Gujikja();
		gu1.userid = "eomjh";
		gu1.passwd = "qWer1234$";
		gu1.name = "엄정화";
		gu1.jubun = "8610202";
		gu_arr[Gujikja.count++] = gu1;
		
		Gujikja gu2 = new Gujikja();
		gu2.userid = "leess";
		gu2.passwd = "Abcd12345$";
		gu2.name = "이순신";
		gu2.jubun = "8601201";
		gu_arr[Gujikja.count++] = gu2;
		
		Gujikja gu3 = new Gujikja();
		gu3.userid = "chaew";
		gu3.passwd = "Wxyz1234$";
		gu3.name = "차은우";
		gu3.jubun = "0106203";
		gu_arr[Gujikja.count++] = gu3;
		///////////////////////////////////
		
		Scanner sc = new Scanner(System.in);
		Ctrl_gujikja ctrl = new Ctrl_gujikja();
		
		String str_menuno = "";
		
		do {
			ctrl.main_menu();
			str_menuno = sc.nextLine();
			
			switch (str_menuno) {
			case "1": // 구직자 회원가입
				ctrl.register(sc, gu_arr);
				break;

			case "2": // 구직자 모두보기
				ctrl.view_all_info(gu_arr);
				break;

			case "3": // 검색하기
				ctrl.search_menu(sc, gu_arr);
				break;
			case "4": // 프로그램 종료
				
				break;

			default:
				System.out.println("[경고] 메뉴에 없는 번호 입니다.");
				break;
			}//end of switch----------------------------------------
			
			
		} while (!("4".equals(str_menuno)));
		//end of do~while---------------------------------------
		
		
		sc.close();
		System.out.println("\n>>>프로그램 종료<<<");
		
	}//--------------------end of main(String[] args)---------------------------------

}
