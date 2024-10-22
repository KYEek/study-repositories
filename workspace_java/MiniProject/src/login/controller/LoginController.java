package login.controller;

import java.util.Scanner;

import user.domain.MemberDTO;

public class LoginController {

	
	MemberDTO member = new MemberDTO();	//멤버 변수
	int user_condition = 0;	//현재 로그인한 유저가 누구인지 확인을 위한 것 1은 일반, 2는 기업, 3은 관리자
	
	// 로그인 메뉴 표시를 위한 메소드
	public void login_menu(Scanner sc) {
		System.out.println("\n\n"+"—".repeat(17) + "로그인" +"—".repeat(17));
		System.out.println("1.개인회원 로그인  2.기업회원 로그인  3.관리자 회원 로그인  4.돌아가기");
		
		String menuNum = null;
		boolean is_exit = false;	//반복문 탈출을 위한 변수 true 면 탈출 false 면 남아있기
		while(!is_exit) {
			System.out.print("😁메뉴 번호 입력 : ");
			menuNum = sc.nextLine();
			
			
			//메뉴 번호 스위치문
			switch (menuNum) {
			case "1":		//개인 회원 회원가입
				loginMember(sc);
				
				break;
				
			case "2":		//기업회원 회원가입
				loginCompany(sc);
				
				break;
				
			case "3": 		// 관리자 로그인
				loginAdmin(sc);

			case "4": 		// 돌아가기

				is_exit = true;// 전 메뉴로 돌아가기

			default: //
				System.out.println("올바른 값을 입력하세요!!");
				break;
				
			}//end of switch ---------------------------------
		}//end of while---------------------------------------------
		
		
		System.out.println("\n\n"+"—".repeat(17) + "메뉴[일반회원 "member.getUser_name()" 님이 로그인중]" +"—".repeat(17));
		System.out.println("1.이력서 관리 2.채용 공고  3.기업정보 조회  4.내정보 관리  5.로그아웃");
		
		
		is_exit = false;	//반복문 탈출을 위한 변수 true 면 탈출 false 면 남아있기
		while(!is_exit) {
			System.out.print("😁메뉴 번호 입력 : ");
			menuNum = sc.nextLine();
			
			
			//메뉴 번호 스위치문
			switch (menuNum) {
			case "1":		//개인 회원 회원가입
				
				
				break;
				
			case "2":		//기업회원 회원가입
				
				
				break;
				
			case "3": // 돌아가기
				
				
				
			case "4": // 돌아가기
				

			case "5": // 돌아가기

				is_exit = true;// 전 메뉴로 돌아가기

			default: //
				System.out.println("올바른 값을 입력하세요!!");
				break;
				
			}//end of switch ---------------------------------
		}//end of while---------------------------------------------
		
		
		
	}

	
	
	
	
	
	
	
	
	
	//admin 로그인 페이지
	private void loginAdmin(Scanner sc) {
		
	}//end of loginAdmin-------------------------------------

	
	//기업 로그인 페이지
	private void loginCompany(Scanner sc) {
		
	}//end of loginCompany----------------------------------

	
	//멤버 로그인 페이지
	private void loginMember(Scanner sc) {
		
	}//end of loginMember----------------------------------

}
