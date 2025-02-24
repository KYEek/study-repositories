package registe.controller;

import java.util.Scanner;

import admin.util.Util;
import company.model.CompanyDAO;
import company.model.CompanyDAO_Imple;
import user.domain.CompanyDTO;
import user.domain.MemberDTO;
import member.model.MemberDAO;
import member.model.MemberDAO_Imple;



public class RegisteController {
	
	//field
	CompanyDAO cdao = new CompanyDAO_Imple();
	MemberDAO mdao = new MemberDAO_Imple();
	
	
	
	
	//회원가입 메뉴 표시를 위한 메소드
	public void registe_menu(Scanner sc) {
		
		
		String menuNum = null;
		boolean is_exit = false;	//반복문 탈출을 위한 변수 true 면 탈출 false 면 남아있기
		while(!is_exit) {
			System.out.println("\n\n"+"—".repeat(17) + "회원가입" +"—".repeat(17));
			System.out.println("1.개인회원 회원가입  2.기업회원 회원가입  3.돌아가기");
			System.out.print("😁메뉴 번호 입력 : ");
			menuNum = sc.nextLine();
			
			
			//메뉴 번호 스위치문
			switch (menuNum) {
			case "1":		//개인 회원 회원가입
				registeMember(sc);
				
				break;
				
			case "2":		//기업회원 회원가입
				registeCompany(sc);
				
				break;
				
			case "3":		//돌아가기
				
				
				is_exit = true;// 전 메뉴로 돌아가기
				break;
	
			default:		//
				System.out.println("올바른 값을 입력하세요!!");
				break;
				
			}//end of switch ---------------------------------
		}//end of while---------------------------------------------
		
		
		
		
	}//end of registe_menu -----------------------------------------

	
	
	
	// 회사 계정 생성 메서드
	private void registeCompany(Scanner sc) {
		
		
		// companyDTO 오브젝트를 생성
		CompanyDTO company = new CompanyDTO();

		// 입력 받는 부분

		System.out.println(" ".repeat(20) +"회원가입을 시작 하겠습니다");
		System.out.println("—".repeat(40));
		boolean check_current = false; // 입력값이 맞는지 체크
		do {
			
			
			System.out.print("아이디 입력 : ");
			String inputID = sc.nextLine();
			try {
				if(Integer.parseInt(inputID) < 0) {
					System.out.println("아이디는 5글자 이상 20글자 이하여야 합니다.");
					continue;
				}
			} catch(NumberFormatException e) {
				System.out.println("아이디는 5글자 이상 20글자 이하여야 합니다.");
				continue;
			}

			if (cdao.compareID(company)) {				//중복된 아이디 검사
				System.out.println("존재하는 아이디 입니다.");
				continue;
			}
			check_current = company.setCom_id(inputID);
		} while (!check_current);
		do {
			System.out.println("————————————————————————————————————————————————————");
			System.out.println("비밀번호는 8글자 이상 20글자 이하의 영어, 숫자 기호를 포함해야 합니다");
			System.out.print("비밀번호 입력 : ");
			check_current = company.setCom_passwd(sc.nextLine());
		} while (!check_current);
		do {
			System.out.print("기업명 입력 : ");
			check_current = company.setCom_name(sc.nextLine());
		} while (!check_current);
		do {
			System.out.print("기업소개 입력 : ");
			check_current = company.setCom_intro(sc.nextLine());
		} while (!check_current);
		do {
			System.out.print("기업 연락처 입력(기호를 포함하세요) : ");
			check_current = company.setCom_tel(sc.nextLine());
		} while (!check_current);
		do {
			System.out.print("기업 이메일 입력 : ");
			check_current = company.setCom_email(sc.nextLine());
		} while (!check_current);
		do {
			System.out.print("대표자명 : ");
			check_current = company.setCom_president(sc.nextLine());
		} while (!check_current);
		do {
			System.out.print("매출 입력(천원단위) : ");
			try {
				check_current = company.setCom_revenue(Long.parseLong(sc.nextLine()));
			} catch (NumberFormatException e) {
				System.out.println("숫자를 입력하세요.");
				check_current = false;
			}
		} while (!check_current);
		do {
			System.out.print("주소 입력 : ");
			check_current = company.setCom_address(sc.nextLine());
		} while (!check_current);
		System.out.println("업종 종류\t");
		System.out.println("———————————————————————————————————————————");
		System.out.println("1. IT    2. 제조   3. 서비스   4. 경영   5. 교육");
		do {
			System.out.print("업종코드 입력 : ");
			
			
			String input = sc.nextLine();		//입력 받기			
			if(input.isEmpty()) {				//값이 비어있으면 그냥 넘어가기
				break;
			}
	
			try {
				int inputnum = Integer.parseInt(input);
				if(inputnum < 1 || inputnum > 5) {
					System.out.println("범위 안에 있는 값에서 선택 하세요");
				}
				else {
					check_current = company.setFk_job_tcode(inputnum);
				}
			} catch (NumberFormatException e) {
				
				System.out.println("숫자만 입력하세요");
			}//-------------------end of try catch
			
			
			
			
		} while(!check_current);//------------------------------------------ end of while

		// n은 결과값이 어떻게 되었는지 확인 하기 위한 변수 -1이면 sql이 실행되지 않음 0이면 취소 1이면 성공
		int n = -1;

		// y/n 입력 장소
		System.out.print("입력 하시겠습니까?[Y/N] : ");
		String yn = sc.nextLine();

		if ("y".equalsIgnoreCase(yn))
			n = cdao.registeCompany(company);
		else if ("n".equalsIgnoreCase(yn))
			n = 0;
		else
			System.out.println("올바른 값을 입력하세요.");

		if (n == 1) {
			System.out.println("값이 정상적으로 입력이 되었습니다.");
		} else if (n == 0) {
			System.out.println("입력을 취소했습니다.");
		} else if (n == -1) {
			System.out.println("입력이 정상적으로 되지 않았습니다.");
		}

	}// end of method ------------------------------------------------------
	
	
	
	// 일반회원 계정 생성 메서드
	private void registeMember(Scanner sc) {

		MemberDTO member = new MemberDTO();
		
		System.out.println(" ".repeat(20) +"회원가입을 시작 하겠습니다");
		System.out.println("—".repeat(40));
		
		// 입력 받는 부분
		boolean check_currect = false;		//입력값이 맞는지 체크
		while(!check_currect) {
			
			
			System.out.print("아이디 입력 : ");
			String inputID = sc.nextLine();
			try {
				if(Integer.parseInt(inputID) < 0) {
					System.out.println("아이디는 5글자 이상 20글자 이하여야 합니다.");
					continue;
				}
			} catch(NumberFormatException e) {
				System.out.println("아이디는 5글자 이상 20글자 이하여야 합니다.");
				continue;
			}
			check_currect = member.setUser_id(inputID);
		}
		check_currect = false;		//성공여부 초기화
		while(!check_currect) {
			System.out.println("————————————————————————————————————————————————————");
			System.out.println("비밀번호는 8글자 이상 20글자 이하의 영어, 숫자 기호를 포함해야 합니다");
			System.out.print("비밀번호 입력 : ");
			check_currect = member.setUser_passwd(sc.nextLine());
		}
		check_currect = false;		//성공여부 초기화
		while(!check_currect) {
			System.out.print("이름 입력 : ");
			check_currect = member.setUser_name(sc.nextLine());
		}
		check_currect = false;		//성공여부 초기화	
		while(!check_currect) {
			System.out.print("주민번호 앞 7자리 입력 : ");
			String jubun = sc.nextLine();
			check_currect = Util.check_jubun(jubun);			//주민번호 체크 후 참일경우 값을 입력
			if(check_currect) {
				check_currect = member.setUser_jubun(jubun);
			}
			else {
				System.out.println("올바른 날자를 입력하세요");
			}
		}
		check_currect = false;		//성공여부 초기화
		while(!check_currect) {
			System.out.print("이메일 입력 : ");
			check_currect = member.setUser_email(sc.nextLine());
		}
		check_currect = false;		//성공여부 초기화
		while(!check_currect) {
			System.out.print("전화번호 입력 (-기호를 포함하세요): ");
			check_currect = member.setUser_tel(sc.nextLine());
		}
		check_currect = false;		//성공여부 초기화
		while(!check_currect) {
			System.out.print("주소 입력 : ");
			check_currect = member.setUser_address(sc.nextLine());
		}
		check_currect = false;		//성공여부 초기화
		System.out.println("업종 종류\t");
		System.out.println("———————————————————————————————————————————");
		System.out.println("1. IT    2. 제조   3. 서비스   4. 경영   5. 교육");
		do {
			System.out.print("업종코드 입력 : ");

			String input = sc.nextLine(); // 입력 받기
			if (input.isEmpty()) { // 값이 비어있으면 그냥 넘어가기
				break;
			}

			try {
				int inputnum = Integer.parseInt(input);
				if (inputnum < 1 || inputnum > 5) {
					System.out.println("범위 안에 있는 값에서 선택 하세요");
				} else {
					check_currect = member.setFk_job_tcode(inputnum);
				}
			} catch (NumberFormatException e) {

				System.out.println("숫자만 입력하세요");
			} // -------------------end of try catch
		} while (!check_currect);
		// n은 결과값이 어떻게 되었는지 확인 하기 위한 변수 -1이면 sql이 실행되지 않음 0이면 취소 1이면 성공
		int n = -1;

		// y 또는 n 이 들어오기 전 까지 반복
		while (true) {
			// y/n 입력 장소
			System.out.print("입력 하시겠습니까?[Y/N] : ");
			String yn = sc.nextLine();
	
			if ("y".equalsIgnoreCase(yn)) {
				n = mdao.registeMember(member);
				break;
			}
			else if ("n".equalsIgnoreCase(yn)) {
				n = 0;
				break;
			}
			else
				System.out.println("올바른 값을 입력하세요.");
		}

		if (n == 1) {
			System.out.println("값이 정상적으로 입력이 되었습니다.");
		} else if (n == 0) {
			System.out.println("입력을 취소했습니다.");
		} else if (n == -1) {
			System.out.println("입력이 정상적으로 되지 않았습니다.");
		}
		
	}//end of method ------------------------------------------------------

	

	
	
	
	
}
