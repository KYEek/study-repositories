package login.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import game.controller.GameController;
import jobpost.controller.ComJobPostController;
import jobpost.controller.JobPostController;
import user.domain.CompanyDTO;
import user.domain.MemberDTO;
import company.model.*;
import controlmyinfo.*;
import main.Main;
import member.model.*;
import resume.controller.ResumeController;
import review.controller.CompanyReviewController;
import review.domain.ReviewDTO;
import search.controller.SearchController;
import admin.controller.AdminController;
import admin.model.*;
import comment.domain.CommentDTO;


public class LoginController {

	
	MemberDTO member = null;	//멤버 변수
	CompanyDTO company = null; //컴퍼니 변수
	CompanyDAO cdao = new CompanyDAO_Imple();	//컴퍼니 dao
	MemberDAO mdao = new MemberDAO_Imple();		//멤버 dao
	AdminDAO admin = new AdminDAO_Imple();		//admin dao
	int user_condition = 0;	//현재 로그인한 유저가 누구인지 확인을 위한 것 1은 일반, 2는 기업, 3은 관리자
	int menu_select = 0; //메뉴가 어디가 어디에 표시되게 할지 선택하는 변수
	AdminController adctl = new AdminController();
	SearchController searchCtl = new SearchController();
	ResumeController resumeCtl = new ResumeController();
	CompanyReviewController ComReviewCtrl = new CompanyReviewController();
	ReviewDTO review = new ReviewDTO();
	CommentDTO comment = new CommentDTO();
	JobPostController jpctrl = new JobPostController();
	ComJobPostController comjpctrl = new ComJobPostController();
	
	//임시로 내 정부 관리 메뉴를 위해서 만듬
	Myinfo_Controller info_control = new Myinfo_Controller();
	Control_Info ctlcpinfo = new Control_Info_Imple();
	//---------------------------------
	
	
	// 로그인 메뉴 표시를 위한 메소드
	public void login_menu(Scanner sc) {
		
		
		menu_select = 0;	//회원 탈퇴 후 메인 메뉴 돌아온 후 다시 처음 메뉴를 보여주기 위해서
		
		String menuNum = null;		//메뉴 번호를 입력 받기 위한 변수
		boolean is_exit = false;	//반복문 탈출을 위한 변수 true 면 탈출 false 면 남아있기
		
		while (true) {
			
			is_exit = false;		// 반복문이 다시 돌아 왔을 때 안의 반복 문을 실행 하기 위해서
			
			// 메뉴 변수가 0번일 경우
			if(menu_select == 0) {
				while(!is_exit) {
					
					
					//로그인 메뉴 표시
					System.out.println("\n\n"+"—".repeat(17) + "로그인" +"—".repeat(17));
					System.out.println("1.개인회원 로그인  2.기업회원 로그인  3.관리자 회원 로그인  4.돌아가기");
					System.out.print("😁메뉴 번호 입력 : ");
					menuNum = sc.nextLine();
					
					
					//메뉴 번호 스위치문
					switch (menuNum) {
					case "1":		//개인 회원 로그인
						loginMember(sc);
						is_exit = true;	//올바른 값이 입력이 되면 
						break;
						
					case "2":		//기업회원 로그인
						loginCompany(sc);
						is_exit = true;
						break;
						
					case "3": 		// 관리자 로그인
						loginAdmin(sc);
						is_exit = true;
						break;
		
					case "4": 		// 돌아가기
		
						return;// 함수를 끝내고 전 메뉴로 돌아가기
		
					default: //
						System.out.println("올바른 값을 입력하세요!!");
						break;
						
					}//end of switch ---------------------------------
				}//end of while---------------------------------------------
			}//enf of if-------------------------------------------------------
			
			
			else if(menu_select == 1) {			// 메뉴 번호가 1번일 시
				
				
				is_exit = false;				//반복문 탈출을 위한 변수 true 면 탈출 false 면 남아있기
				while(!is_exit) {
					
					if(Main.check_delete == false) {
						return;		//만약 방급 회원 탈퇴를 했다면 메서드 종료
					}
					
					
					if (user_condition == 1) {	//유저 상태 변수로 일반(1), 기업(2), 관리자(3) 별로 보여줌
						// 일반 회원 메뉴 화면
						System.out.println("\n\n"+"—".repeat(17) + "메뉴 [일반회원 "+ member.getUser_name()+" 님이 로그인중]" +"—".repeat(17));
						System.out.println("1.이력서 관리 2.채용 공고  3.기업정보 조회  4.내정보 관리  5.미니게임  6.로그아웃");
					}
					else if(user_condition ==2) {
						// 기업 회원 메뉴 화면
						System.out.println("\n\n"+"—".repeat(17) + "메뉴 [기업회원 "+ company.getCom_name()+" 님이 로그인중]" +"—".repeat(17));
						System.out.println("1.공고 관리 2.구직자 조회  3.후기 조회  4.내정보 관리  5.미니게임  6.로그아웃");
					}
					else {
						adctl.admin_menu(sc);
						menu_select = 0;
						break;
					}
					
					
					System.out.print("😁메뉴 번호 입력 : ");
					menuNum = sc.nextLine();
					
					
					//메뉴 번호 스위치문
					switch (menuNum) {
					case "1":		//1번 메뉴 선택시
						if(user_condition == 1) { 			//일반 회원일 시
							resumeCtl.resume_menu(sc, member);
						}
						else if(user_condition == 2) {		//기업 회원일 시
							comjpctrl.jobpostcdmenu(sc, company);
						}
						
						
						break;
						
					case "2": // 2번 메뉴 선택시
						if (user_condition == 1) { // 일반 회원일 시
							jpctrl.JobPostMenu(member, sc);
						} else if (user_condition == 2) { // 기업 회원일 시
							searchCtl.search_gujikja(sc);
						}

						break;
						
					case "3": // 3번 메뉴 선택시
						if (user_condition == 1) { // 일반 회원일 시
							searchCtl.search_company(sc, company, member);
						} else if (user_condition == 2) { // 기업 회원일 시
							ComReviewCtrl.Company_review_search_memu(sc, company, review, comment);
						} 
						break;
						
						
					case "4": // 4번 메뉴 선택시
						if (user_condition == 1) { // 일반 회원일 시
							info_control.info_menu(member ,sc);
						} else if (user_condition == 2) { // 기업 회원일 시
							info_control.info_menu(company ,sc);
						} 
						break;

					case "5": // 5번 메뉴 선택시
						GameController gamectrl = new GameController();
						gamectrl.game_menu(member, company, sc);
						break;

					case "6": 		//로그아웃
						
						
						menu_select = 0;	//반복문 탈출 후 첫번째 메뉴를 표시해주기 위해서 설정
						
						// 로그 아웃시 각 유저에 대한 정보 초기화
						member = null;
						company = null;
						
						is_exit = true;// 반복문 탈출
						break;
		
					default: //잘못된 값 입력시
						System.out.println("올바른 값을 입력하세요!!");
						break;
						
					}//end of switch ---------------------------------
				}//end of while---------------------------------------------
			}//end of if-------------------------------------------------------
		
		}//end of while(전체 와일문) -----------------------------------------------
		
	}

	
	
	
	
	
	
	
	
	
	
	
//로그인 메서드 시작—————————————————————————————————————————————————————————————————————————————————
	
	
	
	//admin 로그인 페이지
	private boolean loginAdmin(Scanner sc) {
		
		
		
		Map<String, String> login = new HashMap<String, String>(); // 입력 받을 로그인과 비번 저장 변수

		System.out.print("아이디 입력 : ");
		login.put("userid", sc.nextLine()); // 아이디 입력
		System.out.print("비밀번호 입력 : ");
		login.put("passwd", sc.nextLine()); // 비번 입력

		boolean result = admin.login(login); // 성공되면 ture 실패면 null
		//성공이면 true 실패면 false
		
		if(result) {	//true(로그인 성공)면
			System.out.println("로그인 성공했습니다.");
			menu_select = 1;
			user_condition = 3;
		}
		else {			//false(로그인 실패)면
			System.out.println("로그인을 실패하였습니다");
		}
		
		return result;
		
		
		
	}//end of loginAdmin-------------------------------------

	
	//기업 로그인 페이지
	private boolean loginCompany(Scanner sc) {
		

		Map<String, String> login = new HashMap<String, String>();	//입력 받을 로그인과 비번 저장 변수
		
		System.out.print("아이디 입력 : ");
		login.put("userid", sc.nextLine()); 	//아이디 입력
		System.out.print("비밀번호 입력 : ");
		login.put("passwd", sc.nextLine());		//비번 입력
		
		company = cdao.login(login);			//성공되면 company에 변수가 있고 없으면 null
		
		
		if(company == null) {		//로그인 실패 시
			System.out.println("로그인을 실패하였습니다");
			return false;
		}
		else {						//로그인 성공 시
			System.out.println("로그인 성공했습니다.");
			menu_select = 1;
			user_condition = 2;
			return true;
		}
		
	}//end of loginCompany----------------------------------

	
	//멤버 로그인 페이지
	private boolean loginMember(Scanner sc) {
		
		Map<String, String> login = new HashMap<String, String>();	//입력 받을 로그인과 비번 저장 변수
		
		System.out.print("아이디 입력 : ");
		login.put("userid", sc.nextLine()); 	//아이디 입력
		System.out.print("비밀번호 입력 : ");
		login.put("passwd", sc.nextLine());		//비번 입력
		
		member = mdao.login(login);			//성공되면 company에 변수가 있고 없으면 null
		
		
		if(member == null) {		//로그인 실패 시
			System.out.println("로그인을 실패하였습니다");
			return false;
		}
		else {						//로그인 성공 시
			System.out.println("로그인 성공했습니다.");
			menu_select = 1;
			user_condition = 1;	
			return true;
		}
	}//end of loginMember----------------------------------
	
	
//로그인 메서드 끝—————————————————————————————————————————————————————————————————————————————————

}
