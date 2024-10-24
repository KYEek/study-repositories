package main;

import java.util.Scanner;

import company.model.*;
import member.model.*;
import user.domain.CompanyDTO;
import user.domain.MemberDTO;

public class FindIDController {

	
	private int user_ststus = 0; // 회원이 기업 회원인지 아닌지 확인 하기 위한 메뉴, 다른 클래스에서는 딱히 필요하지 않기 때문에 private로 함
								 // 1번은 개인, 2번은 기업
	MemberDAO mdao = new MemberDAO_Imple();
	CompanyDAO cdao = new CompanyDAO_Imple();
	
	MemberDTO member = null;
	CompanyDTO company = null;
	
	boolean check_input = false;	//입력이 정상인지 아닌지 판단하기 위해 만듬
	
	
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
				
				findid:
				while (!is_exit) {

					// 시작메뉴 프린트 아웃
					System.out.println("\n" + "—".repeat(17) + "아이디 찾기" + "—".repeat(17));
					System.out.println("1. 아이디 찾기  2.비밀번호 변경  3.돌아가기 ");
					System.out.print("😁메뉴 번호 입력 : ");
					menuNum = sc.nextLine();
					
					String text = null;		//기업과 일반 회원의 출력이 조금 다르게 나오게 하기 위한 변수
					String name;			//이름 입력을 위한 변수
					String email;			//이메일 입력을 위한 변수
					String ID;				//ID 입력을 위한 변수
					
					switch (menuNum) {

					case "1": // 아이디 찾기
						if (user_ststus == 1)
							member = new MemberDTO();	//새로운 멤버를 만들어줌
						else
							company = new CompanyDTO();	//새로운 회사를 만들어줌

						
						text = user_ststus == 1 ? "이름" : "기업명";
						
						do {
							System.out.print(text + " 입력 : ");
							name = sc.nextLine();
							
							if (user_ststus == 1)		//일반 멤버
								check_input = member.setUser_name(name);
							else
								check_input = company.setCom_name(name); // 기업 회원
							
						} while (!check_input);// end of while --------------------------------------------
						
						//이메일 입력
						do {
							System.out.print("이메일 입력 : ");
							email = sc.nextLine();
							
							if (user_ststus == 1)		//일반 멤버
								check_input = member.setUser_email(email);
							else
								check_input = company.setCom_email(email); // 기업 회원
							
						} while (!check_input);// end of while --------------------------------------------
						
						
						
						if (user_ststus == 1) {				//유저가 일반 회원이면
							String myID = mdao.find_ID(member);
							
							if(myID == null) {
								System.out.println("찾으시는 아이디가 없습니다.");
							}
							else {
								System.out.print("당신의 아이디는 : " + myID + "입니다.");
							}//end of if-----------------------------------------------
							
						} else {							//유저가 기업 회원이면
							String myID = cdao.find_ID(company);
							
							if(myID == null) {
								System.out.println("찾으시는 아이디가 없습니다.");
							}
							else {
								System.out.print("당신의 아이디는 : " + myID + "입니다.");
							}//end of if-----------------------------------------------
							
						}//end of if-----------------------------------------------

						
						break;
					case "2": // 비밀번호 변경
						if (user_ststus == 1)
							member = new MemberDTO();	//새로운 멤버를 만들어줌
						else
							company = new CompanyDTO();	//새로운 회사를 만들어줌
						
						
						
						System.out.println("비밀번호를 재 설정 하겠습니다.");
						
						
						
						text = user_ststus == 1 ? "이름" : "기업명";
						
						
						//아이디 입력
						do {
							System.out.print("아이디를 입력 : ");
							ID = sc.nextLine();
							
							if (user_ststus == 1)		//일반 멤버
								check_input = member.setUser_id(ID);
							else
								check_input = company.setCom_id(ID); // 기업 회원
							
						} while (!check_input);// end of while --------------------------------------------
						
						
						
						boolean check_id = true;
						//아이디 비교 부분
						if (user_ststus == 1)		//일반 멤버
							check_id = mdao.compareID(member);
						else
							check_id = cdao.compareID(company); // 기업 회원
						
						if(!check_id) {		//아이디가 없다면 돌아가기
							System.out.println("아이디가 없습니다.");
							continue findid;
						}
						
						// 이름 입력
						do {
							System.out.print(text + " 입력 : ");
							name = sc.nextLine();
							
							if (user_ststus == 1)		//일반 멤버
								check_input = member.setUser_name(name);
							else
								check_input = company.setCom_name(name); // 기업 회원
							
						} while (!check_input);// end of while --------------------------------------------
						
						//이메일 입력
						do {
							System.out.print("이메일 입력 : ");
							email = sc.nextLine();
							
							if (user_ststus == 1)		//일반 멤버
								check_input = member.setUser_email(email);
							else
								check_input = company.setCom_email(email); // 기업 회원
							
						} while (!check_input);// end of while --------------------------------------------
						
						String passwd = null;
						
						do {
							System.out.print("새로운 비밀번호를 입력 하세요 : ");
							passwd = sc.nextLine();
							
							if (user_ststus == 1)		//일반 멤버
								check_input = member.setUser_passwd(passwd);
							else
								check_input = company.setCom_passwd(passwd); // 기업 회원
							
						} while (!check_input);
						
						int result = 0;
						//비밀번호 설정
						if (user_ststus == 1) {
							
							result = mdao.reset_passwd(member);
						} else {
							
							result = cdao.reset_passwd(company);
						}
						
						if (result == 1) 
							System.out.println("비밀번호 초기화 완료되었습니다.");
						else
							System.out.println("입력한 값이 정확하지 않습니다.....");
						
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
