package main;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import company.model.*;
import member.model.*;

public class FindIDController {

	
	private int user_ststus = 0; // 회원이 기업 회원인지 아닌지 확인 하기 위한 메뉴, 다른 클래스에서는 딱히 필요하지 않기 때문에 private로 함
								 // 1번은 개인, 2번은 기업
	MemberDAO mdao = new MemberDAO_Imple();
	CompanyDAO cdao = new CompanyDAO_Imple();
	
	
	public void findID_menu(Scanner sc) {
		
		
		String menuNum = null;		//메뉴 번호를 선택하기 위한 변수
		boolean is_exit = false;	//탈출 조건을 위하 변수
		int menu_select = 1;		//메뉴의 위치 선택을 위한 변수
		
		while(true) {
			
			if(menu_select== 1) {		//메뉴 번호가 1번일 시
				while (!is_exit) {

					// 시작메뉴 프린트 아웃
					System.out.println("\n" + "—".repeat(17) + "아이디 찾기" + "—".repeat(17));
					System.out.println("1. 개인회원  2.기업회원  3.돌아가기 ");
					System.out.print("😁메뉴 번호 입력 : ");
					menuNum = sc.nextLine();
					switch (menuNum) {

					case "1": // 개인회원
						user_ststus = 1;	//개인 회원 아이디 찾기
						menu_select = 2;	//2번 메뉴로
						is_exit = true;		
						
						break;
					case "2": // 기업회원
						user_ststus = 2;	//기업 회원 아이디 찾기
						menu_select = 2;	//2번 메뉴로
						is_exit =true;
						break;
					case "3": // 돌아가기
						return;
					default: //잘못된 값
						System.out.println("올바른 값을 입력하세요!!");
						is_exit = false;
						break;

					}// end of switch ---------------------------------
				} // end of while---------------------------------------------
				
				is_exit = false;		//반복 을 초기화
			}//end of if----------------------------------------------------------
			
			
			
			
			
			else if(menu_select == 2) {		//메뉴 번호가 2번일 시
				
				while (!is_exit) {

					// 시작메뉴 프린트 아웃
					System.out.println("\n" + "—".repeat(17) + "아이디 찾기" + "—".repeat(17));
					System.out.println("1. 아이디 찾기  2.비밀번호 변경  3.돌아가기 ");
					System.out.print("😁메뉴 번호 입력 : ");
					menuNum = sc.nextLine();
					switch (menuNum) {

					case "1": // 아이디 찾기

						System.out.println("아이디를 찾겠습니다 ");
						
						String text  = user_ststus == 1? "이름":"기업명";		//기업과 일반 회원의 출력이 조금 다르게 나오게 하기 위한 변수
						
						String name = null;		//이름 입력 받기 위한 변수
						while(true) {
							System.out.print(text + " 입력 : ");
							name = sc.nextLine();
							try {
								Integer.parseInt(name);			//이름이 
								break;
							} catch (NumberFormatException e) {
								System.out.println("올바른 이름을 입력하세요");
							}
						}
						System.out.print("이메일 입력 : ");
						String email = sc.nextLine();
						
						Map<String, String> map = new HashMap<>();
						
						map.put("name", name);
						map.put("email", email);
						
						if (user_ststus == 1) {

							 
							
							//String myID = mdao.find_ID(map);
						} else {
							//String myID = cdao.find_ID(map);
						}

						
						break;
					case "2": // 비밀번호 변경
						if (user_ststus == 1) {
							//int result = mdao.reset.passwd();
						} else {
							//int result = mdao.reset_passwd();
						}
						
						break;
					case "3": // 돌아가기
						menu_select = 1;	//처음 메뉴로 돌아가게 한다.
						is_exit = true;
						break;
					default: //잘못된 값
						System.out.println("올바른 값을 입력하세요!!");
						is_exit = false;
						break;

					}// end of switch ---------------------------------
				} // end of while---------------------------------------------
				
			}//end of if----------------------------------------------------------
			
		}// end of while---------------------------------------------
		
		

	}//end of findID_menu-----------------------------------------------------------------

}
