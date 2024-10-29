package controlmyinfo;

import java.util.Scanner;

import main.Main;
import user.domain.*;

public class Myinfo_Controller {
	
	//임플 변수 생성
	Control_Info ctlinfo = new Control_Info_Imple();
	

	// 기업 정보 수정 메뉴
	public void info_menu(CompanyDTO company, Scanner sc) {
		
		String menuNum = null;		//메뉴 번호를 입력 받기 위한 변수
		
		System.out.print("비밀번호 입력 : ");
		
		//만약 비밀번호가 맞지 않는 다면
		if(!company.getCom_passwd().equals(sc.nextLine())) {
			System.out.println("비밀번호가 맞지 않습니다!!");
			return;		//되돌아가기
		}
		
		//비밀 번호가 맞는 다면 실행
		//무한 반복에 4번 입력이면 함수 끝내기
		while(true) {
			
			
			//내정보 표시
			System.out.println("\n\n기업정보");
			System.out.println("———————————————————————————————————————————————");
			System.out.println("기업이름 : " + company.getCom_name());
			System.out.println("대표번호 : " + company.getCom_tel());
			System.out.println("주소 : " + company.getCom_address());
			System.out.println("매출액(천원) : " + company.getCom_revenue());
			System.out.println("대표명 : " + company.getCom_president());
			
			
			
			//로그인 메뉴 표시
			System.out.println("\n\n" + "—".repeat(25) + "내 정보 관리" + "—".repeat(25));
			System.out.println("1.내정보 수정  2.비밀번호 수정  3.회원 탈퇴  4.돌아가기");
			System.out.print("😁메뉴 번호 입력 : ");
			menuNum = sc.nextLine();

			// 메뉴 번호 스위치문
			switch (menuNum) {
			case "1": // 내 정보 수정
				update_myinfo(company, sc);
				break;

			case "2": // 비밀번호 수정
				update_mypasswd(company, sc);
				break;

			case "3": // 회원 탈퇴
				boolean is_delete = leave_account(company, sc);		//만약 회원탈퇴 여부를 판단하기 위한 변수
				if(is_delete) {			//만약 회원탈퇴가 성공했다면
					company = null;		//로그인 된 company 값을 초기화 해주고
					Main.check_delete = false;
					return;
				}
				
				break;	//회원탈퇴를 하지 않으면 계속 반복

			case "4": // 돌아가기
				
				return;// 함수를 끝내고 전 메뉴로 돌아가기

			default: //
				System.out.println("올바른 값을 입력하세요!!");
				break;
				
			}//end of switch ---------------------------------
		}//end of while---------------------------------------------
		
	}//end of info_menu----------------------------------------------------------------------------------------------------------------------------------
	
	
	
	
	

	





	













	// 개인 회원 정보 수정
	public void info_menu(MemberDTO member, Scanner sc) {

		String menuNum = null; // 메뉴 번호를 입력 받기 위한 변수

		System.out.print("비밀번호 입력 : ");
		
		// 만약 비밀번호가 맞지 않는 다면
		if (!member.getUser_passwd().equals(sc.nextLine())) {
			System.out.println("비밀번호가 맞지 않습니다!!");
			return; // 되돌아가기
		}

		
		
		
		// 비밀 번호가 맞는 다면 실행
		// 무한 반복에 4번 입력이면 함수 끝내기
		while (true) {
			
			String jobcode = "";
			
			switch (member.getFk_job_tcode()) {
			case 1:
				jobcode = "IT";
				break;
			case 2:
				jobcode = "제조";
				break;
			case 3:
				jobcode = "서비스";
				break;
			case 4:
				jobcode = "경영";
				break;

			default:
				jobcode = "교육";
				break;
			}
			
			//내정보 표시
			System.out.println("\n\n개인정보");
			System.out.println("———————————————————————————————————————————————");
			System.out.println("이름 : " + member.getUser_name());
			System.out.println("연락처 : " + member.getUser_tel());
			System.out.println("주소 : " + member.getUser_address());
			System.out.println("희망업종 : " + jobcode);
			System.out.println("포인트 : " + member.getUser_point());
			//TODO 희망업종 이름으로 표시되게 하기
			
			
			// 로그인 메뉴 표시
			System.out.println("\n\n" + "—".repeat(17) + "내 정보 관리" + "—".repeat(17));
			System.out.println("1.내정보 수정  2.비밀번호 수정  3.회원 탈퇴  4.돌아가기");
			System.out.print("😁메뉴 번호 입력 : ");
			menuNum = sc.nextLine();

			// 메뉴 번호 스위치문
			switch (menuNum) {
			case "1": // 내 정보 수정
				update_myinfo(member, sc);
				break;

			case "2": // 비밀번호 수정
				update_mypasswd(member, sc);
				break;

			case "3": // 회원 탈퇴
				boolean is_delete = leave_account(member, sc);		//만약 회원탈퇴 여부를 판단하기 위한 변수
				if(is_delete) {			//만약 회원탈퇴가 성공했다면
					member = null;		//로그인 된 member 값을 초기화 해주고
					Main.check_delete = false;
					return;	//메서드 종료
				}
				
				break;	//회원탈퇴를 하지 않으면 계속 반복

			case "4": // 돌아가기
				
				return;// 함수를 끝내고 전 메뉴로 돌아가기

			default: //
				System.out.println("올바른 값을 입력하세요!!");
				break;

			}// end of switch ---------------------------------
		} // end of while---------------------------------------------

	}//end of info_menu----------------------------------------------------------------------------------------------------------------------------------



	




	
	
	





	
	//개인 회원 수정
	private void update_myinfo(MemberDTO member, Scanner sc) {
		
		boolean is_success = false;
		
		MemberDTO temp_member = new MemberDTO();

		System.out.println("\n\n변경할 정보 입력 (변경 하지 않으려면 엔터를 입력 하세요)");
		System.out.println("———————————————————————————————————————————————");

		// 실행 후 조건에 맞으면 진행
		// 이름 변경
		do {
			System.out.print("이름 : ");

			String input = sc.nextLine();		//입력 받기			
			if(input.isEmpty()) {				//값이 비어있으면 기존값 넣기
				temp_member.setUser_name(member.getUser_name());
				break;
			}
			
			is_success = temp_member.setUser_name(input);
		} while (!is_success);//------------------------------------------ end of while

		// 연락처 변경
		do {
			System.out.print("연락처 : ");
			
			String input = sc.nextLine();		//입력 받기			
			if(input.isEmpty()) {				//값이 비어있으면 기존값 넣기
				temp_member.setUser_tel(member.getUser_tel());
				break;
			}
			
			is_success = temp_member.setUser_tel(input);
		} while (!is_success);//------------------------------------------ end of while

		// 주소 변경
		do {
			System.out.print("주소 : ");
			
			String input = sc.nextLine();		//입력 받기			
			if(input.isEmpty()) {				//값이 비어있으면 기존값 넣기
				temp_member.setUser_address(member.getUser_address());
				break;
			}
			
			is_success = temp_member.setUser_address(input);
		} while (!is_success);//------------------------------------------ end of while
		
		

		// 업종 변경
		System.out.println("업종 종류");
		System.out.println("-------------------------------------------");
		System.out.println("1. IT    2. 제조   3. 서비스   4. 경영   5. 교육");
		do {
			System.out.print("업종코드 입력 : ");
			
			
			String input = sc.nextLine();		//입력 받기			
			if(input.isEmpty()) {				//값이 비어있으면 기존값 넣기
				temp_member.setFk_job_tcode(member.getFk_job_tcode());
				break;
			}
	
			try {
				int inputnum = Integer.parseInt(input);
				if(inputnum < 1 || inputnum > 5) {
					System.out.println("범위 안에 있는 값에서 선택 하세요");
				}
				else {
					is_success = temp_member.setFk_job_tcode(inputnum);
				}
			} catch (NumberFormatException e) {
				
				System.out.println("숫자만 입력하세요");
			}//-------------------end of try catch
			
			
			
			
		} while(!is_success);//------------------------------------------ end of while
		
		int n = 0 ;
		
		// y 또는 n 이 들어오기 전 까지 반복
		while (true) {
			// 수정전 확인 질문
			System.out.print("수정 하시겠습니까?[Y/N] : ");
			String yn = sc.nextLine();
			System.out.println(temp_member.getUser_name());
			// y면 sql실행, n이면 취소 후 메소드 끝내기, 잘못 넣으면
			if ("y".equalsIgnoreCase(yn)) {
				member.setUser_name(temp_member.getUser_name());
				member.setUser_tel(temp_member.getUser_tel());
				member.setUser_address(temp_member.getUser_address());
				member.setFk_job_tcode(temp_member.getFk_job_tcode());
				n = ctlinfo.updateinfo(member);
				break;
			}
			else if ("n".equalsIgnoreCase(yn)) {
				System.out.println("취소했습니다.");
				return;
			} else
				System.out.println("올바른 값을 입력하세요.");
		}
		
		
		if(n == 1) {
			System.out.println("수정 성공했습니다.🤗");
			member = temp_member;				//수정 성공시 바뀐 정보로 대체
		}
		else {
			System.out.println("수정 실패했습니다.😭");
		}
		
	}//end of method---------------------------------------------------------------------------------------------------------------------
	
	
	
	
	
	



	//기업 회원 수정
	private void update_myinfo(CompanyDTO company, Scanner sc) {

		boolean is_success = false;
		
		CompanyDTO temp_company = new CompanyDTO();
		
		System.out.println("\n\n변경할 정보 입력 (변경 하지 않으려면 엔터를 입력 하세요)");
		System.out.println("———————————————————————————————————————————————");
		
		// 실행 후 조건에 맞으면 진행
		// 기업이름 변경
		do {
			System.out.print("기업이름 : ");
			
			String input = sc.nextLine();		//입력 받기			
			if(input.isEmpty()) {				//값이 비어있으면 그냥 넘어가기
				temp_company.setCom_name(company.getCom_name());
				break;
			}
			
			is_success = temp_company.setCom_name(input);		//값이 있으면 값을 입력
		} while (!is_success);// ------------------------------------------ end of while

		// 대표번호 변경
		do {
			System.out.print("대표번호 : ");
			
			String input = sc.nextLine();		//입력 받기		
			if(input.isEmpty()) {				//값이 비어있으면 그냥 넘어가기
				temp_company.setCom_tel(company.getCom_tel());
				break;
			}
			
			is_success = temp_company.setCom_tel(input);		//값이 있으면 값을 입력
		} while (!is_success);// ------------------------------------------ end of while

		// 주소 변경
		do {
			System.out.print("주소 : ");
			String input = sc.nextLine();			
			if(input.isEmpty()) {				//값이 비어있으면 그냥 넘어가기
				temp_company.setCom_address(company.getCom_address());
				break;
			}
			is_success = temp_company.setCom_address(input);
		} while (!is_success);// ------------------------------------------ end of while
		
		//매출액 변경
		do {
			System.out.print("매출액 : ");
			String input = sc.nextLine();			
			if(input.isEmpty()) {				//값이 비어있으면 그냥 넘어가기
				temp_company.setCom_revenue(company.getCom_revenue());
				break;
			}
			is_success = temp_company.setCom_revenue(Long.parseLong(input));		//값이 있으면 값을 입력
		} while (!is_success);// ------------------------------------------ end of while
		
		//대표자명 변경
		do {
			System.out.print("대표자명 : ");
			String input = sc.nextLine();			
			if(input.isEmpty()) {				//값이 비어있으면 그냥 넘어가기
				temp_company.setCom_president(company.getCom_president());
				break;
			}
			is_success = temp_company.setCom_president(input);		//값이 있으면 값을 입력
		} while (!is_success);// ------------------------------------------ end of while
		
		

		// 업종 변경
		System.out.println("업종 종류");
		System.out.println("-------------------------------------------");
		System.out.println("1. IT    2. 제조   3. 서비스   4. 경영   5. 교육");
		do {
			System.out.print("업종코드 입력 : ");
			
			
			String input = sc.nextLine();		//입력 받기			
			if(input.isEmpty()) {				//값이 비어있으면 그냥 넘어가기
				temp_company.setFk_job_tcode(company.getFk_job_tcode());
				break;
			}
	
			try {
				int inputnum = Integer.parseInt(input);
				if(inputnum < 1 || inputnum > 5) {
					System.out.println("범위 안에 있는 값에서 선택 하세요");
				}
				else {
					is_success = temp_company.setFk_job_tcode(inputnum);
				}
			} catch (NumberFormatException e) {
				
				System.out.println("숫자만 입력하세요");
			}//-------------------end of try catch
			
			
			
			
		} while(!is_success);//------------------------------------------ end of while

		int n = 0;
		// y 또는 n 이 들어오기 전 까지 반복
		while (true) {
			// 수정전 확인 질문
			System.out.print("수정 하시겠습니까?[Y/N] : ");
			String yn = sc.nextLine();

			// y면 sql실행, n이면 취소 후 메소드 끝내기, 잘못 넣으면
			if ("y".equalsIgnoreCase(yn)) {
				company.setCom_name(temp_company.getCom_name());
				company.setCom_tel(temp_company.getCom_tel());
				company.setCom_address(temp_company.getCom_address());
				company.setCom_revenue(temp_company.getCom_revenue());
				company.setCom_president(temp_company.getCom_president());
				company.setFk_job_tcode(temp_company.getFk_job_tcode());
				n = ctlinfo.updateinfo(company);
				break;
			}
			else if ("n".equalsIgnoreCase(yn)) {
				System.out.println("취소했습니다.");
				return;
			} else
				System.out.println("올바른 값을 입력하세요.");
		}
		
		if(n == 1) {
			System.out.println("수정 성공했습니다.🤗");
			company = temp_company;
		}
		else {
			System.out.println("수정 실패했습니다.😭");
		}
		
	}//end of method---------------------------------------------------------------------------------------------------------------------------
	
	

	
	
	
	
	
	
	
	
	
	
	
	



	// 개인 비밀번호 변경
	private void update_mypasswd(MemberDTO member, Scanner sc) {
		
		MemberDTO check_passwd = new MemberDTO();		//비밀번호 무결성 검사를 위한 변수
		
		String passwd="";	// 비밀번호 입력을 위한 변수
		do {
			System.out.print("바꿀 비밀번호를 입력하십시오 : ");
			passwd = sc.nextLine();
		} while(!check_passwd.setUser_passwd(passwd));		//set에서 무결성 검사를 합니다.
		int n = 0;
		// y 또는 n 이 들어오기 전 까지 반복
		while (true) {
			// 수정전 확인 질문
			System.out.print("정말로 수정 하시겠습니까?[Y/N] : ");
			String yn = sc.nextLine();

			// y면 sql실행, n이면 취소 후 메소드 끝내기, 잘못 넣으면
			if ("y".equalsIgnoreCase(yn)) {
				n = ctlinfo.update_passwd(member, passwd);
				break;
			}
			else if ("n".equalsIgnoreCase(yn)) {
				System.out.println("취소했습니다.");
				return;
			} else
				System.out.println("올바른 값을 입력하세요.");
		}
		
		if(n == 1) {
			System.out.println("수정 성공했습니다.🤗");
		}
		else {
			System.out.println("수정 실패했습니다.😭");
		}
		
		
		
		
	}//end of method---------------------------------------------------------------------------------------------------------------------------

	
	
	
	
	
	
	
	
	// 기업 비밀번호 변경
	private void update_mypasswd(CompanyDTO company, Scanner sc) {
		
		CompanyDTO check_passwd = new CompanyDTO();		//비밀번호 무결성 검사를 위한 변수
		
		String passwd="";	// 비밀번호 입력을 위한 변수
		do {
			System.out.print("바꿀 비밀번호를 입력하십시오 : ");
			passwd = sc.nextLine();
		} while(!check_passwd.setCom_passwd(passwd));		//set에서 무결성 검사를 합니다.
		
		int n = 0;
		// y 또는 n 이 들어오기 전 까지 반복
		while (true) {
			// 수정전 확인 질문
			System.out.print("정말로 수정 하시겠습니까?[Y/N] : ");
			String yn = sc.nextLine();

			// y면 sql실행, n이면 취소 후 메소드 끝내기, 잘못 넣으면
			if ("y".equalsIgnoreCase(yn)) {
				n = ctlinfo.update_passwd(company, passwd);
				break;
			}
			else if ("n".equalsIgnoreCase(yn)) {
				System.out.println("취소했습니다.");
				return;
			} else
				System.out.println("올바른 값을 입력하세요.");
		}
		
		if(n == 1) {
			System.out.println("수정 성공했습니다.🤗");
		}
		else {
			System.out.println("수정 실패했습니다.😭");
		}
		
		
		
	}//end of method---------------------------------------------------------------------------------------------------------------------------
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	// 개인 회원 탈퇴
	private boolean leave_account(MemberDTO member, Scanner sc) {
		
		
		int n = 0;
		// y 또는 n 이 들어오기 전 까지 반복
		while (true) {
			// 수정전 확인 질문

			System.out.print("정말 회원 탈퇴 하시겠습니까?[Y/N] : ");
			String yn = sc.nextLine();

			// y면 sql실행, n이면 취소 후 메소드 끝내기, 잘못 넣으면
			if ("y".equalsIgnoreCase(yn)) {
				n = ctlinfo.delete_account(member);
				break;
			}
			else if ("n".equalsIgnoreCase(yn)) {
				System.out.println("취소했습니다.");
				return false;
			} else
				System.out.println("올바른 값을 입력하세요.");
		}
		
		if(n == 1) {
			System.out.println("회원 탈퇴를 했습니다. 다시 오길 바래요🤗");
			return true;
		}
		else {
			System.out.println("탈퇴 실패했습니다.😭");
			return false;
		}
		
		
	}//end of method---------------------------------------------------------------------------------------------------------------------------
		
	
	// 기업 회원 탈퇴
	private boolean leave_account(CompanyDTO company, Scanner sc) {

		
		
		int n = 0;
		// y 또는 n 이 들어오기 전 까지 반복
		while (true) {
			// 수정전 확인 질문

			System.out.print("정말 회원 탈퇴 하시겠습니까?[Y/N] : ");
			String yn = sc.nextLine();

			// y면 sql실행, n이면 취소 후 메소드 끝내기, 잘못 넣으면
			if ("y".equalsIgnoreCase(yn)) {
				n = ctlinfo.delete_account(company);
				break;
			}
			else if ("n".equalsIgnoreCase(yn)) {
				System.out.println("취소했습니다.");
				return false;
			} else
				System.out.println("올바른 값을 입력하세요.");
		}
		
		if(n == 1) {
			System.out.println("회원 탈퇴를 했습니다. 다시 오길 바래요🤗");
			return true;
		}
		else {
			System.out.println("탈퇴 실패했습니다.😭");
			return false;
		}
		
	}//end of method---------------------------------------------------------------------------------------------------------------------------

}
