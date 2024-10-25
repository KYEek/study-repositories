package search.controller;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import review.controller.CompanyReviewController;
import review.controller.MbrReviewController;
import review.domain.ReviewDTO;
import search.model.*;
import user.domain.CompanyDTO;
import user.domain.MemberDTO;

public class SearchController {

	// field
	SearchDAO sdao = new SearchDAO_imple();
	CompanyReviewController ComReviewCtrl = new CompanyReviewController();
	MbrReviewController mbrReviewCtrl = new MbrReviewController();
	
	// == 구직자를 조회하는 메뉴 메소드 == //
	public void search_gujikja(Scanner sc) {
		
		do {
			System.out.println("\n=============== >>> 구직자 조회 <<< ===============");
			System.out.println("1.전체조회   2.상세조회   3.조건검색   4.돌아가기");
			System.out.println("=".repeat(50));
			System.out.print(">> 메뉴번호입력: ");
			String menu_no = sc.nextLine();
			
			switch (menu_no) {
				case "1":		// 전체조회
					view_all_gujikja();
					break;
				case "2":		// 상세조회
					view_detail_gujikja(sc);
					break;
				case "3":		// 조건검색
					Conditional_search(sc);
					break;
				case "4":		// 돌아가기
					return;
		
				default:
					System.out.println(">> [경고] 메뉴에 없는 번호입니다. <<");
					break;
			}
		} while (true);
	}// end of public void search_gujikja(Scanner sc) ---------------------------



	// == 구직자의 구인회사조회 메뉴 메소드 == //
	public void search_company(Scanner sc, CompanyDTO company, MemberDTO member) {
		
		do {
			System.out.println("\n=============== >>> 구인회사 조회 <<< ===============");
			System.out.println("1.전체조회   2.상세조회   3.기업후기   4.돌아가기");
			System.out.println("=".repeat(50));
			System.out.print(">> 메뉴번호입력: ");
			String menu_no = sc.nextLine();
			
			switch (menu_no) {
				case "1":		// 전체조회
					view_all_company();
					break;
				case "2":		// 상세조회
					view_detail_company(sc);
					break;
				case "3":		// 기업후기메뉴
					mbrReviewCtrl.Company_review_memu(sc, company, member);
					break;
				case "4":		// 돌아가기
					return;
		
				default:
					System.out.println(">> [경고] 메뉴에 없는 번호입니다. <<");
					break;
			}
		} while (true);
	}// end of public void search_gujikja(Scanner sc) ---------------------------
	
	
	/* 구인회사 검색 메소드 시작 */
	// == 전체 구인회사를 보여주는 메소드 == //
	private void view_all_company() {
		List<Map<String, String>> company_list = sdao.view_all_company();
		
		if (company_list.size() > 0) {
			
			StringBuilder sb = new StringBuilder();
			
			//기업번호 기업명 대표명 기업소개 업종
			System.out.println("\n----------------------- >>> 전체 구인회사 조회 <<< -----------------------");
			System.out.println("-".repeat(56));
			System.out.println("기업번호\t기업명\t대표명\t기업소개\t업종");
			System.out.println("-".repeat(56));
			
			for (int i=0; i<company_list.size(); i++) {
				sb.append(company_list.get(i).get("com_no") 		+ "\t" +
						  company_list.get(i).get("com_name") 		+ "\t" +
						  company_list.get(i).get("com_president") 	+ "\t" +
						  company_list.get(i).get("job_type") 		+ "\n"
						 );
			}
			System.out.println(sb);
			
		} else {
			System.out.println(">> 가입된 구인회사가 없습니다. <<");
		}
		
	}// end of private void view_all_company() ------------------------
	
	
	// == 구인회사의 상세정보를 보여주는 메소드 == //
	private void view_detail_company(Scanner sc) {
		
		String select_comno;
		do {
			System.out.println(">> 뒤로가시려면 엔터를 입력하세요 !! <<\n");
			System.out.print(">> 상세정보를 볼 기업번호입력: ");
			select_comno = sc.nextLine();
			
			if (select_comno.isEmpty()) {
				return;
			}
			
			try {
				Integer.parseInt(select_comno);
				break;
			} catch (NumberFormatException e) {
				System.out.println(">> [경고] 회원번호는 숫자만 입력 가능합니다!! <<");
			}
		}while (true);
		// end of do~while(회원번호입력) -------------------------------------------
		
		Map<String, String> company_detail = sdao.view_detail_company(select_comno);	// 회원번호에 해당하는 정보를 가져옴
		
		if (company_detail != null) {
			StringBuilder sb = new StringBuilder();
			
			DecimalFormat df = new DecimalFormat("#,###");		// 3자리마다 콤마를 넣어주겠다는 의미 개꿀템~~
			
			sb.append("\n>> 기업번호 "	+ company_detail.get("com_no") 			+"번 님의 정보\n");
			sb.append(">> 기업명: "		+ company_detail.get("com_name") 		+"\n");
			sb.append(">> 대표명: "		+ company_detail.get("com_president")	+"\n");
			sb.append(">> 기업소개: "		+ company_detail.get("com_intro")		+"\n");
			sb.append(">> 이메일: "		+ company_detail.get("com_email")		+"\n");
			sb.append(">> 주소: "		+ company_detail.get("com_address")		+"\n");
			sb.append(">> 매출: "		+ df.format(Long.parseLong(company_detail.get("com_revenue")+"000")) +" 원\n");
			sb.append(">> 업종: "		+ company_detail.get("job_type")		+"\n");
			
			System.out.println(sb);
		}
		else {
			System.out.println(">> 기업회원번호 "+ select_comno +"번에 해당하는 정보는 없습니다. <<");
		}
		
	}// end of private void view_detail_company(Scanner sc) --------------------------
	/* 구인회사 검색 메소드 끝 */
	
	
	/* 구직자 검색 메소드 시작 */
	// == 모든 구직자를 보여주는 메소드 == //
	private void view_all_gujikja() {
		
		List<Map<String, String>> gujikja_list = sdao.view_all_gujikja();
		
		if (gujikja_list.size() > 0) {
			
			StringBuilder sb = new StringBuilder();
			
			System.out.println("\n-------------------------- >>> 전체 구직자 조회 <<< --------------------------");
			System.out.println("-".repeat(76));
			System.out.println("회원번호\t성명\t나이\t성별\t이메일\t학력\t전공\t경력");
			System.out.println("-".repeat(76));
			
			for (int i=0; i<gujikja_list.size(); i++) {
				sb.append(gujikja_list.get(i).get("user_no") + "\t" +
						  gujikja_list.get(i).get("user_name") + "\t" +
						  gujikja_list.get(i).get("age") + "\t" +
						  gujikja_list.get(i).get("gender") + "\t" +
						  gujikja_list.get(i).get("user_email") + "\t" +
						  gujikja_list.get(i).get("edu_degree") + "\t" +
						  gujikja_list.get(i).get("major_name") + "\t" +
						  gujikja_list.get(i).get("res_career") + "년\n"
						 );
			}
			System.out.println(sb);
			
		} else {
			System.out.println(">> 가입된 구직자가 없습니다. <<");
		}
		
	}// end of private void view_all_gujikja() ----------------------------------
	
	// == 구직자의 상세정보를 보여주는 메소드 == //
	private void view_detail_gujikja(Scanner sc) {
		
		// 이름, 나이, 성별, 이메일, 학력, 전공, 경력, 희망연봉, 자기소개
		String select_userno;
		do {
			System.out.println(">> 뒤로가시려면 엔터를 입력하세요 !! <<\n");
			System.out.print(">> 상세정보를 볼 회원번호입력: ");
			select_userno = sc.nextLine();
			
			if (select_userno.isEmpty()) {
				return;
			}
			
			try {
				Integer.parseInt(select_userno);
				break;
			} catch (NumberFormatException e) {
				System.out.println(">> [경고] 회원번호는 숫자만 입력 가능합니다!! <<");
			}
		}while (true);
		// end of do~while(회원번호입력) -------------------------------------------
		
		Map<String, String> gujikja_detail = sdao.view_detail_gujikja(select_userno);	// 회원번호에 해당하는 정보를 가져옴
		
		if (gujikja_detail != null) {
			StringBuilder sb = new StringBuilder();
			
			DecimalFormat df = new DecimalFormat("#,###");		// 3자리마다 콤마를 넣어주겠다는 의미 개꿀템~~
			
			sb.append("\n>> 회원번호 "	+ gujikja_detail.get("user_no") +"번 님의 정보\n");
			sb.append(">> 이름: "	+ gujikja_detail.get("user_name") 	+"\n");
			sb.append(">> 나이: "	+ gujikja_detail.get("age")			+"\n");
			sb.append(">> 성별: "	+ gujikja_detail.get("gender")		+"\n");
			sb.append(">> 이메일: "	+ gujikja_detail.get("user_email")	+"\n");
			sb.append(">> 학력: "	+ gujikja_detail.get("edu_degree")	+"\n");
			sb.append(">> 전공: "	+ gujikja_detail.get("major_name")	+"\n");
			sb.append(">> 경력: "	+ gujikja_detail.get("res_career")	+"\n");
			sb.append(">> 희망연봉: "	+ df.format(Integer.parseInt(gujikja_detail.get("res_hwage"))) +"만원\n");
			sb.append(">> 자기소개: "	+ gujikja_detail.get("res_intro"));
			
			System.out.println(sb);
		}
		else {
			System.out.println(">> 회원번호 "+ select_userno +"번에 해당하는 구직자는 없습니다. <<");
		}
		
	}// end of private void view_detail_gujikja(Scanner sc) -------------------------------------
	/* 구직자 검색 메소드 끝 */
	
	
	
	// 구직자 조회에서 case 3(조건검색) //
	// == 조건 검색을 해주는 메소드 == //
	private void Conditional_search(Scanner sc) {
		
		String menu_no = "";
		
		do {
			System.out.println("\n>> 조건검색 메뉴 <<");
			System.out.println("1.학력검색 2.전공검색 3.연령대검색 4.성별검색 5.돌아가기");
			System.out.print("▶ 메뉴 번호 선택: ");
			menu_no = sc.nextLine();
			
			switch (menu_no) {
				case "1":	// 학력검색
					degree_search(sc);
					break;
	
				case "2":	// 전공검색
					major_search(sc);
					break;
					
				case "3":	// 연령대검색
					age_line_search(sc);
					break;
					
				case "4":	// 성별검색
					gender_search(sc);
					break;

				case "5":	// 돌아가기
					return;
					
				default:
					System.out.println(">> [경고] 메뉴에 없는 번호입니다!! <<");
					break;
			}// end of switch(menu_no) ----------------------------
		} while(true);
		// end of do~while(true) -------------------------
		
		
	}// end of private void Conditional_search(Scanner sc) --------------------------




	// == 학력검색을 해주는 메소드 == //
	private void degree_search(Scanner sc) {
		
		String edu_code = "";
		
		System.out.println("\n >> 학력검색 시작! <<");
		System.out.println("1.고졸이상 2.초대졸이상 3.대졸이상 4.석박사이상 5.돌아가기");
		System.out.print("▶ 학력 번호 선택: ");
		edu_code = sc.nextLine();
		
		switch (edu_code) {
			case "1":	// 고졸이상
				break;
	
			case "2":	// 초대졸이상				
				break;
				
			case "3":	// 대졸이상	
				break;
				
			case "4":	// 석졸이상
				break;
				
			case "5":	// 돌아가기
				return;
				
			default:
				System.out.println(">> [경고] 존재하지 않는 학력입니다!! <<");
				break;
		}
		
		List<Map<String, String>> gujikja_list = sdao.degree_search(edu_code);
		
		if (gujikja_list.size() > 0) {
			
			StringBuilder sb = new StringBuilder();
			
			System.out.println("\n>> 학력검색 결과 <<");
			System.out.println("-".repeat(76));
			System.out.println("성명\t나이\t성별\t이메일\t학력\t전공\t경력");
			System.out.println("-".repeat(76));
			
			for (int i=0; i<gujikja_list.size(); i++) {
				sb.append(gujikja_list.get(i).get("user_name") + "\t" +
						  gujikja_list.get(i).get("age") + "\t" +
						  gujikja_list.get(i).get("gender") + "\t" +
						  gujikja_list.get(i).get("user_email") + "\t" +
						  gujikja_list.get(i).get("edu_degree") + "\t" +
						  gujikja_list.get(i).get("major_name") + "\t" +
						  gujikja_list.get(i).get("res_career") + "년\n"
						 );
			}
			System.out.println(sb);
			
		} else {
			System.out.println(">> 학력검색 결과가 없습니다. <<");
		}

		
	}// end of private void degree_search(Scanner sc) ---------------
	
	
	
	// == 전공검색을 해주는 메소드 == //
	private void major_search(Scanner sc) {
		
		String major_code = "";
		
		System.out.println("\n >> 전공검색 시작! <<");
		System.out.println("1.컴퓨터공학 2.경영학 3.교육학 4.돌아가기");
		System.out.print("▶ 전공 번호 선택: ");
		major_code = sc.nextLine();
		
		switch (major_code) {
			case "1":	// 컴퓨터공학
				break;
	
			case "2":	// 겅영학				
				break;
				
			case "3":	// 교육학	
				break;
				
			case "4":	// 돌아가기
				return;
				
			default:
				System.out.println(">> [경고] 존재하지 않는 전공입니다!! <<");
				break;
		}
		
		List<Map<String, String>> gujikja_list = sdao.major_search(major_code);
		
		if (gujikja_list.size() > 0) {
			
			StringBuilder sb = new StringBuilder();
			
			System.out.println("\n>> 학력검색 결과 <<");
			System.out.println("-".repeat(76));
			System.out.println("성명\t나이\t성별\t이메일\t학력\t전공\t경력");
			System.out.println("-".repeat(76));
			
			for (int i=0; i<gujikja_list.size(); i++) {
				sb.append(gujikja_list.get(i).get("user_name") + "\t" +
						  gujikja_list.get(i).get("age") + "\t" +
						  gujikja_list.get(i).get("gender") + "\t" +
						  gujikja_list.get(i).get("user_email") + "\t" +
						  gujikja_list.get(i).get("edu_degree") + "\t" +
						  gujikja_list.get(i).get("major_name") + "\t" +
						  gujikja_list.get(i).get("res_career") + "년\n"
						 );
			}
			System.out.println(sb);
			
		} else {
			System.out.println(">> 전공검색 결과가 없습니다. <<");
		}
		
	}// end of private void major_search(Scanner sc) ----------

	

	// == 연령대를 검색해주는 메소드 == //
	private void age_line_search(Scanner sc) {
		
		String age_line = "";
		
		System.out.println("\n >> 연령대검색 시작! <<");
		System.out.println(">> 이전 메뉴로 돌아가려면 엔터를 입력하세요!! <<");
		System.out.print("▶ 검색할 연령대 : ");
		age_line = sc.nextLine();
		
		switch (age_line) {
			case "10":	// 10대
				break;
	
			case "20":	// 20대				
				break;
				
			case "30":	// 30대	
				break;
				
			case "40":	// 40대
				break;

			case "50":	// 50대
				break;
				
			case "60":	// 60대
				break;
				
			case "70":	// 70대
				break;
				
			case "":
				return;
				
			default:
				System.out.println(">> [경고] 올바른 연령대를 입력하세요!! <<");
				break;
		}
		
		List<Map<String, String>> gujikja_list = sdao.age_line_search(age_line);
		
		if (gujikja_list.size() > 0) {
			
			StringBuilder sb = new StringBuilder();
			
			System.out.println("\n>> 연령대검색 결과 <<");
			System.out.println("-".repeat(76));
			System.out.println("성명\t나이\t성별\t이메일\t학력\t전공\t경력");
			System.out.println("-".repeat(76));
			
			for (int i=0; i<gujikja_list.size(); i++) {
				sb.append(gujikja_list.get(i).get("user_name") + "\t" +
						  gujikja_list.get(i).get("age") + "\t" +
						  gujikja_list.get(i).get("gender") + "\t" +
						  gujikja_list.get(i).get("user_email") + "\t" +
						  gujikja_list.get(i).get("edu_degree") + "\t" +
						  gujikja_list.get(i).get("major_name") + "\t" +
						  gujikja_list.get(i).get("res_career") + "년\n"
						 );
			}
			System.out.println(sb);
			
		} else {
			System.out.println(">> 연령대검색 결과가 없습니다. <<");
		}
		
	}// end of private void age_line_search(Scanner sc) ------------------
	
	

	// == 성별 검색을 해주는 메소드 == //
	private void gender_search(Scanner sc) {
		
		String gender_no = "";
		String gender = "";
		
		System.out.println("\n >> 성별검색 시작! <<");
		System.out.println("1.남자 2.여자 3.돌아가기");
		System.out.print("▶ 연령대 번호 선택: ");
		gender_no = sc.nextLine();
		
		switch (gender_no) {
			case "1":	// 남자
				gender = "남자";
				break;
	
			case "2":	// 여자			
				gender = "여자";
				break;
				
			case "3":	// 돌아가기	
				return;
							
			default:
				System.out.println(">> [경고] 올바른 성별을 선택하세요!! <<");
				break;
		}
		
		List<Map<String, String>> gujikja_list = sdao.gender_search(gender);
		
		if (gujikja_list.size() > 0) {
			
			StringBuilder sb = new StringBuilder();
			
			System.out.println("\n>> 학력검색 결과 <<");
			System.out.println("-".repeat(76));
			System.out.println("성명\t나이\t성별\t이메일\t학력\t전공\t경력");
			System.out.println("-".repeat(76));
			
			for (int i=0; i<gujikja_list.size(); i++) {
				sb.append(gujikja_list.get(i).get("user_name") + "\t" +
						  gujikja_list.get(i).get("age") + "\t" +
						  gujikja_list.get(i).get("gender") + "\t" +
						  gujikja_list.get(i).get("user_email") + "\t" +
						  gujikja_list.get(i).get("edu_degree") + "\t" +
						  gujikja_list.get(i).get("major_name") + "\t" +
						  gujikja_list.get(i).get("res_career") + "년\n"
						 );
			}
			System.out.println(sb);
			
		} else {
			System.out.println(">> 성별검색 결과가 없습니다. <<");
		}
		
	}// end of private void gender_search(Scanner sc) ------
	
}
