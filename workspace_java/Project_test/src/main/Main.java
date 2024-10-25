package main;

import java.util.Scanner;

import common.ProjectDBConnection;
import login.controller.LoginController;
import registe.controller.RegisteController;

public class Main {


	public static boolean check_delete = true;		//회원 가입시 메인 메뉴로 탈출하기 위한 변수
	
	public static void main(String[] args) {
		
		//입력받기 위한 스캐너
		Scanner sc = new Scanner(System.in);
		RegisteController registeCtl = new RegisteController();
		LoginController loginCtl = new LoginController();
		FindIDController findID = new FindIDController();

		
		
		String menuNum = null;//메뉴 번호 입력 받기 위한 변수
		
		boolean is_exit = false;	//반복문 탈출을 위한 변수 true 면 탈출 false 면 남아있기
		while(!is_exit) {
			
			Main.check_delete = true;		// 메인 메뉴로 돌아오면 메뉴들을 반복 하기 위해서 true로 설정
			
			//시작메뉴 프린트 아웃
			System.out.println("\n"+"—".repeat(17) + "시작메뉴" +"—".repeat(17));
			System.out.println("1. 회원가입  2.로그인  3.아이디찾기  4.프로그램 종료");
			System.out.print("😁메뉴 번호 입력 : ");
			menuNum = sc.nextLine();
			switch (menuNum) {
			
			
			case "1":		//회원가입 메뉴
				registeCtl.registe_menu(sc);
				break;
			case "2":		//로그인 메뉴
				loginCtl.login_menu(sc);
				break;
			case "3":		//아이디 찾기 메뉴
				findID.findID_menu(sc);
				break;
			case "4":		//프로그램 종료
				is_exit = true;
				break;
	
			default:		//
				System.out.println("올바른 값을 입력하세요!!");
				break;
				
			}//end of switch ---------------------------------
		}//end of while---------------------------------------------
		
		System.out.println("프로그램이 종료되었 습니다😁");
		ProjectDBConnection.closeConnection();
		
		return;	 // 회원 탈퇴 후 메인 메뉴로 왔을 때 4번으로 프로그램 종료 시 기존의 내정보 표시 메뉴에서 머무는 문제를 해결 하기 위해
				 //	return 으로 확실하게 종료
		
		

	}

}
