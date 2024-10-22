package registe.controller;

import java.util.Scanner;

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
		System.out.println("\n\n"+"—".repeat(17) + "회원가입" +"—".repeat(17));
		System.out.println("1.개인회원 회원가입  2.기업회원 회원가입  3.돌아가기");
		
		String menuNum = null;
		boolean is_exit = false;	//반복문 탈출을 위한 변수 true 면 탈출 false 면 남아있기
		while(!is_exit) {
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
	
			default:		//
				System.out.println("올바른 값을 입력하세요!!");
				break;
				
			}//end of switch ---------------------------------
		}//end of while---------------------------------------------
		
		
		
		
	}//end of registe_menu -----------------------------------------

	
	
	
	// 회사 계정 생성 메서드
	private void registeCompany(Scanner sc) {
		
		
		//companyDTO 오브젝트를 생성
		CompanyDTO company = new CompanyDTO();
		
		
		
		//입력 받는 부분
		System.out.print("아이디 입력 : ");
		company.setCom_id(sc.nextLine());
		System.out.print("비밀번호 입력 : ");
		company.setCom_passwd(sc.nextLine());
		System.out.print("기업명 입력 : ");
		company.setCom_name(sc.nextLine());
		System.out.print("기업소개 입력 : ");
		company.setCom_intro(sc.nextLine());
		System.out.print("기업 이메일 입력 : ");
		company.setCom_email(sc.nextLine());
		System.out.print("대표자명 : ");
		company.setCom_president(sc.nextLine());
		System.out.print("매출 입력 : ");
		company.setCom_revenue(Integer.parseInt(sc.nextLine()));
		System.out.print("주소 입력 : ");
		company.setCom_address(sc.nextLine());
		System.out.print("업종코드 입력 : ");
		company.setFk_job_tcode(Integer.parseInt(sc.nextLine()));
		
		//n은 결과값이 어떻게 되었는지 확인 하기 위한 변수 -1이면 sql이 실행되지 않음 0이면 취소 1이면 성공
		int n = -1;
		
		//y/n 입력 장소
		System.out.print("입력 하시겠습니까?[Y/N]"); 
		String yn = sc.nextLine();
		
		if("y".equalsIgnoreCase(yn)) 
			n = cdao.registeCompany(company);
		else if("n".equalsIgnoreCase(yn))
			n = 0;
		else
			System.out.println("올바른 값을 입력하세요.");
		
		if(n == 1) {
			System.out.println("값이 정상적으로 입력이 되었습니다.");
		}
		else if(n == 0) {
			System.out.println("입력을 취소했습니다.");
		}
		else if(n==-1) {
			System.out.println("입력이 정상적으로 되지 않았습니다.");
		}
			
			
		
		
		
		
		
	}//end of method ------------------------------------------------------

	
	
	
	// 일반회원 계정 생성 메서드
	private void registeMember(Scanner sc) {

		MemberDTO member = new MemberDTO();
		
		// 입력 받는 부분
		System.out.print("아이디 입력 : ");
		member.setUser_id(sc.nextLine());
		System.out.print("비밀번호 입력 : ");
		member.setUser_passwd(sc.nextLine());
		System.out.print("이름 입력 : ");
		member.setUser_name(sc.nextLine());
		System.out.print("주민번호 앞 7자리 입력 : ");
		member.setUser_jubun(sc.nextLine());
		System.out.print("이메일 입력 : ");
		member.setUser_email(sc.nextLine());
		System.out.print("전화번호 입력 : ");
		member.setUser_tel(sc.nextLine());
		System.out.print("주소 입력 : ");
		member.setUser_address(sc.nextLine());
		System.out.println("\n 업종 종류");
		System.out.println("-----------------");
		System.out.println("업종 코드 \t 업종명\t");
		System.out.println("-----------------");
		System.out.print("업종코드 입력 : ");
		member.setFk_job_tcode(Integer.parseInt(sc.nextLine()));

		// n은 결과값이 어떻게 되었는지 확인 하기 위한 변수 -1이면 sql이 실행되지 않음 0이면 취소 1이면 성공
		int n = -1;

		// y/n 입력 장소
		System.out.print("입력 하시겠습니까?[Y/N]");
		String yn = sc.nextLine();

		if ("y".equalsIgnoreCase(yn))
			n = mdao.registeMember(member);
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
		
	}//end of method ------------------------------------------------------

	

	
	
	
	
}
