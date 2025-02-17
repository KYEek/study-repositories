package admin.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import admin.domain.EventDTO;
import admin.model.*;
import admin.util.Util;

public class AdminController {
	
	// field
	AdminDAO admindao = new AdminDAO_Imple();
	
	// == 관리자 메인메뉴 메소드 == //
	public void admin_menu(Scanner sc) {
		
		do {
			System.out.println("\n----- >>> 관리자메뉴 [관리자 님이 로그인중] <<< -----");
			System.out.println("1.공고관리   2.회원관리   3.미니게임관리   4.로그아웃");
			System.out.println("-".repeat(48));
			System.out.print(">> 메뉴번호입력: ");
			
			String menu_no = sc.nextLine();
			
			
			switch (menu_no) {
				case "1":		// 공고관리
					manage_apply(sc);
					break;
				case "2":		// 회원관리
					manage_member(sc);

					break;
				case "3":		// 이벤트관리
					manage_event(sc);
					break;
					
				case "4":		// 로그아웃
					return;
				default:
					System.out.println(">> [경고] 메뉴에 없는 번호입니다. <<");
					break;
			}
		} while(true);
		
	}// end of public void admin_menu(Scanner sc) ---------------- 


	// == 공고관리 메뉴 메소드 == //
	private void manage_apply(Scanner sc) {
		do {
			System.out.println("\n===================== >>> 공고관리메뉴 <<< =====================");
			System.out.println("1.월간공고작성통계   2.공고조회수랭킹   3.지원건수랭킹   4.돌아가기");
			System.out.println("=".repeat(62));
			System.out.print(">> 메뉴번호입력: ");
			
			String menu_no = sc.nextLine();
			
			
			switch (menu_no) {
					case "1":		// 월간공고작성통계
						monthly_post_statistics();
						break;
						
					case "2":		// 공고조회수랭킹
						post_ranking();
						break;
						
					case "3":		// 지원건수랭킹
						apply_ranking();
						break;
						
					case "4":		// 돌아가기
						return;
						
					default:
						System.out.println(">> [경고] 메뉴에 없는 번호입니다. <<");
						break;
			}
		} while(true);
	}// end of private void manage_apply(Scanner sc) ------------------------

	
	// == 회원관리 메뉴 메소드 == //
	private void manage_member(Scanner sc) {
		
		do {
			System.out.println("\n===================== >>> 회원관리메뉴 <<< =====================");
			System.out.println("1.월간가입자수통계   2.월간이력서작성건수   3.월간후기작성건수   4.미니게임통계   5.돌아가기");
			System.out.println("=".repeat(62));
			System.out.print(">> 메뉴번호입력: ");
			
			String menu_no = sc.nextLine();
			
			
			switch (menu_no) {
					case "1":		// 월간가입자수통계
						monthly_sign_up_statistics();
						break;
						
					case "2":		// 월간이력서작성건수
						monthly_resumes_statistics();
						break;
						
					case "3":		// 월간후기작성건수
						monthly_reviews_statistics();
						break;

					case "4":		// 미니게임통계
						event_statistics_menu(sc);
						break;
						
					case "5":		// 돌아가기
						return;
						
					default:
						System.out.println(">> [경고] 메뉴에 없는 번호입니다. <<");
						break;
			}
		} while(true);
		
	}// end of private void manage_member(Scanner sc) -----------------------------------


	// == 이벤트관리 메뉴 메소드 == //
	private void manage_event(Scanner sc) {
		do {
			System.out.println("\n================= >>> 미니게임관리메뉴 <<< =================");
			System.out.println("\t1.미니게임등록   2.미니게임수정   3.미니게임삭제\n"
							 + "\t4.진행중미니게임   5.지난미니게임   6.돌아가기");
			System.out.println("=".repeat(56));
			System.out.print(">> 메뉴번호입력: ");
			String menu_no = sc.nextLine();
			
			switch (menu_no) {
				case "1":		// 미니게임등록
					register_event(sc);
					break;
					
				case "2":		// 미니게임수정
					update_event(sc);
					break;
					
				case "3":		// 미니게임삭제
					delete_event(sc);
					break;
					
				case "4":		// 진행중미니게임
					view_during_event();
					break;
					
				case "5":		// 지난미니게임
					view_ended_event();
					break;
					
				case "6":		// 돌아가기
					return;

				default:
					System.out.println(">> [경고] 메뉴에 없는 번호입니다. <<");
					break;
				}
		} while(true);
	}// end of private void manage_event(Scanner sc) ----------------------------------


	// == 이벤트 통계 메뉴 메소드 == //
	private void event_statistics_menu(Scanner sc) {
		do {
			System.out.println("\n========== >>> 미니게임통계메뉴 <<< ==========");
			System.out.println("1.회원포인트랭킹   2.게임별이용횟수   3.돌아가기");
			System.out.println("=".repeat(43));
			System.out.print(">> 메뉴번호입력: ");

			String menu_no = sc.nextLine();


			switch (menu_no) {
				case "1":		// 회원포인트랭킹
					user_point_ranking(sc);
					break;

				case "2":		// 게임별이용횟수
					play_game_statistics();
					break;

				case "3":		// 돌아가기
					return;

				default:
					System.out.println(">> [경고] 메뉴에 없는 번호입니다. <<");
					break;
			}
		} while(true);
	}// end of private void event_statistics_menu(Scanner sc)


	// == 회원 보유포인트 랭킹을 보여주는 메소드 == //
	private void user_point_ranking(Scanner sc) {
		String menu_no;
		do {
			System.out.println(">> 뒤로 가시려면 엔터를 입력하세요. <<");
			System.out.print(">> 회원구분[1:일반회원 / 2:기업회원]: ");
			menu_no = sc.nextLine();
			switch (menu_no) {
				case "1":
				case "2":
					break;
				case "":
					return;
				default:
					System.out.println(">> [경고] 선택지에 없는 번호입니다. <<");
					break;
			}
		} while (!("1".equals(menu_no) || "2".equals(menu_no)));

		if ("1".equals(menu_no)) {		// 일반회원 선택 시

			List<Map<String, String>> gujikja_ranking = admindao.have_point_ranking(1);

			if (gujikja_ranking.size() > 0){
				StringBuilder sb = new StringBuilder();
				sb.append(">> 일반회원랭킹 <<" 	+ "\n");
				sb.append("-".repeat(62) + "\n");
				sb.append("회원번호\t성명\t\t나이\t성별\t보유포인트\t랭킹\n");
				sb.append("-".repeat(62) + "\n");

				for(int i=0; i<gujikja_ranking.size(); i++){
					sb.append(gujikja_ranking.get(i).get("user_no") 	+ "\t\t" +
							  gujikja_ranking.get(i).get("user_name") 	+ "\t" +
							  gujikja_ranking.get(i).get("age") 		+ "\t" +
							  gujikja_ranking.get(i).get("gender") 		+ "\t\t" +
							  gujikja_ranking.get(i).get("user_point")	+ "\t\t" +
							  gujikja_ranking.get(i).get("rank") 		+ "위\n");
				}// end of for() ---------------------------
				System.out.println(sb);
			}else {
				System.out.println(">> 가입된 회원이 없습니다.");
			}

		}
		else {		// 기업회원 선택 시

			List<Map<String, String>> company_ranking = admindao.have_point_ranking(2);

			if (company_ranking.size() > 0){
				StringBuilder sb = new StringBuilder();

				sb.append(">> 기업회원랭킹 <<\n");
				sb.append("-".repeat(55)+ "\n");
				sb.append("회원번호\t회사명\t대표자명\t보유포인트\t랭킹\n");
				sb.append("-".repeat(55)+ "\n");

				for(int i=0; i<company_ranking.size(); i++){
					sb.append(company_ranking.get(i).get("com_no") 			+ "\t" +
							  company_ranking.get(i).get("com_name") 		+ "\t\t" +
							  company_ranking.get(i).get("com_president") 	+ "\t" +
							  company_ranking.get(i).get("com_point") 		+ "\t" +
							  company_ranking.get(i).get("rank") 			+ "위\n");
				}// end of for() ---------------------------

				System.out.println(sb);
			}else {
				System.out.println(">> 가입된 회원이 없습니다.");
			}
		}

	}// end of private void user_point_ranking(Scanner sc) ------------------------------


	/* 공고관리 메뉴 관련 메소드 시작 */
	// == 월간공고작성통계를 보여주는 메소드 == //
	private void monthly_post_statistics() {
		
		// title
		System.out.println("\n------------ >>> 월별 공고 작성 건수 <<< ------------");
		System.out.println("-".repeat(50));
		System.out.println("3개월전   2개월전   1개월전   이번달   총합");		
		System.out.println("-".repeat(50));
		
		Map<String, Integer> monthly_posts = admindao.monthly_post_statistics();		// 월별 공고개수
		
	
		if (monthly_posts != null) {	// 정보가 있으면
			StringBuilder sb = new StringBuilder();
			
			// 퍼센티지 구하기(소수점 첫 째 자리까지 표기) -> 둘째 자리에서 반올림
			double pct_before_3months = Math.round((float)monthly_posts.get("before_3months") / (float)monthly_posts.get("total") * 1000) / 1000.0 * 100;
			double pct_before_2months = Math.round((float)monthly_posts.get("before_2months") / (float)monthly_posts.get("total") * 1000) / 1000.0 * 100;
			double pct_before_1months = Math.round((float)monthly_posts.get("before_1months") / (float)monthly_posts.get("total") * 1000) / 1000.0 * 100;
			double pct_this_month = Math.round((float)monthly_posts.get("this_month") / (float)monthly_posts.get("total") * 1000) / 1000.0 * 100;
			
			sb.append(monthly_posts.get("before_3months") + "건("+ pct_before_3months +"%)\t");
			sb.append(monthly_posts.get("before_2months") + "건("+ pct_before_2months +"%)\t");
			sb.append(monthly_posts.get("before_1months") + "건("+ pct_before_1months +"%)\t");
			sb.append(monthly_posts.get("this_month") + "건("+ pct_this_month +"%)\t");
			sb.append(monthly_posts.get("total")+"건(100%)");
			
			System.out.println(sb);
		}
		else {
			System.out.println(">> 등록된 공고가 없습니다 <<");
		}
		
	}// end of private void monthly_post_statistics() ------------------------------------
	
	
	// == 작성된 공고의 조회수랭킹을 보여주는 메소드 == //
	private void post_ranking() {
		
		// title
		System.out.println("\n----------- >>> 공고 조회수 랭킹 <<< -----------");
		System.out.println("-".repeat(46));
		System.out.println("공고번호   제목   내용   마감일   조회수   랭킹");
		System.out.println("-".repeat(46));
		
		List<Map<String, String>> monthly_posts = admindao.post_ranking();		// 월별 공고개수
		
		if (monthly_posts.size() > 0) {	// 정보가 있으면
			StringBuilder sb = new StringBuilder();
			
			for (int i=0; i<monthly_posts.size(); i++) {
				sb.append(monthly_posts.get(i).get("job_postno") +"\t");
				sb.append(monthly_posts.get(i).get("post_title") +"\t");
				sb.append(monthly_posts.get(i).get("post_contents") +"\t");
				sb.append(monthly_posts.get(i).get("end_date") +"\t");
				sb.append(monthly_posts.get(i).get("view_count")+"\t");
				sb.append(monthly_posts.get(i).get("rank")+"위\n");
			}
			System.out.println(sb);
		}
		else {
			System.out.println(">> 등록된 공고가 없습니다 <<");
		}
		
	}// end of private void post_ranking() -----------------------------------------

	
	// == 공고지원 건수 랭킹을 보여주는 메소드 == //
	private void apply_ranking() {
		
		System.out.println("\n---------- >>> 공고 지원자수 랭킹 <<< ----------");
		System.out.println("-".repeat(46));
		System.out.println("공고번호   제목   내용   마감일   지원자수   랭킹");
		System.out.println("-".repeat(46));
		
		List<Map<String, String>> apply_ranking_list = admindao.apply_ranking();		// 월별 지원자수
	
		if (apply_ranking_list.size() > 0) {	// 정보가 있으면
			StringBuilder sb = new StringBuilder();
			
			for (int i=0; i<apply_ranking_list.size(); i++) {
				sb.append(apply_ranking_list.get(i).get("fk_job_postno") +"\t");
				sb.append(apply_ranking_list.get(i).get("post_title") +"\t");
				sb.append(apply_ranking_list.get(i).get("post_contents") +"\t");
				sb.append(apply_ranking_list.get(i).get("end_date") +"\t");
				sb.append(apply_ranking_list.get(i).get("apply_count")+"\t");
				sb.append(apply_ranking_list.get(i).get("rank")+"위\n");
			}
			System.out.println(sb);
		}
		else {
			System.out.println(">> 등록된 공고가 없습니다 <<");
		}
		
	}// end of private void apply_ranking() -------------------------------
	/* 공고관리 메뉴 관련 메소드 끝 */
	
	
	
	
	/* 회원관리 메뉴 관련 메소드 시작 */
	// == 월간 가입자수 통계를 보여주는 메소드 == //
	private void monthly_sign_up_statistics() {
		System.out.println("\n------------- >>> 월별 회원가입 건수 <<< -------------");
		System.out.println("-".repeat(50));
		System.out.println("3개월전   2개월전   1개월전   이번달   총합");		
		System.out.println("-".repeat(50));
		
		Map<String, Integer> monthly_sign_up = admindao.monthly_sign_up_statistics();		// 월별 회원가입 인원수
		
		
		if (monthly_sign_up != null) {	// 정보가 있으면
			StringBuilder sb = new StringBuilder();
			
			// 퍼센티지 구하기
			float pct_before_3months = (float) (Math.round((float)monthly_sign_up.get("before_3months") / (float)monthly_sign_up.get("total") * 1000) / 1000.0 * 100);
			float pct_before_2months = (float) (Math.round((float)monthly_sign_up.get("before_2months") / (float)monthly_sign_up.get("total") * 1000) / 1000.0 * 100);
			float pct_before_1months = (float) (Math.round((float)monthly_sign_up.get("before_1months") / (float)monthly_sign_up.get("total") * 1000) / 1000.0 * 100);
			float pct_thismonth = (float) (Math.round((float)monthly_sign_up.get("this_month") / (float)monthly_sign_up.get("total") * 1000) / 1000.0 * 100);
			
			sb.append(monthly_sign_up.get("before_3months") + "건("+ pct_before_3months +"%)\t");
			sb.append(monthly_sign_up.get("before_2months") + "건("+ pct_before_2months +"%)\t");
			sb.append(monthly_sign_up.get("before_1months") + "건("+ pct_before_1months +"%)\t");
			sb.append(monthly_sign_up.get("this_month") + "건("+ pct_thismonth +"%)\t");
			sb.append(monthly_sign_up.get("total")+"건(100%)");
			
			System.out.println(sb);
		}
		else {
			System.out.println(">> 가입한 회원이 없습니다. <<");
		}
		
	}// end of private void monthly_register_statistics() ------------------------------

	
	// == 월간 이력서 작성건수 통계를 보여주는 메소드 == // 
	private void monthly_resumes_statistics() {
		
		System.out.println("\n----------- >>> 월별 이력서 작성 건수 <<< -----------");
		System.out.println("-".repeat(50));
		System.out.println("3개월전   2개월전   1개월전   이번달   총합");		
		System.out.println("-".repeat(50));
		
		Map<String, Integer> monthly_resume_write = admindao.monthly_resumes_statistics();		// 월별 이력서 작성 개수
		
		
		if (monthly_resume_write != null) {	// 정보가 있으면
			StringBuilder sb = new StringBuilder();
			
			// 퍼센티지 구하기
			float pct_before_3months = (float) (Math.round((float)monthly_resume_write.get("before_3months") / (float)monthly_resume_write.get("total") * 1000) / 1000.0 * 100);
			float pct_before_2months = (float) (Math.round((float)monthly_resume_write.get("before_2months") / (float)monthly_resume_write.get("total") * 1000) / 1000.0 * 100);
			float pct_before_1months = (float) (Math.round((float)monthly_resume_write.get("before_1months") / (float)monthly_resume_write.get("total") * 1000) / 1000.0 * 100);
			float pct_thismonth = (float) (Math.round((float)monthly_resume_write.get("this_month") / (float)monthly_resume_write.get("total") * 1000) / 1000.0 * 100);
			
			sb.append(monthly_resume_write.get("before_3months") + "건("+ pct_before_3months +"%)\t");
			sb.append(monthly_resume_write.get("before_2months") + "건("+ pct_before_2months +"%)\t");
			sb.append(monthly_resume_write.get("before_1months") + "건("+ pct_before_1months +"%)\t");
			sb.append(monthly_resume_write.get("this_month") + "건("+ pct_thismonth +"%)\t");
			sb.append(monthly_resume_write.get("total")+"건(100%)");
			
			System.out.println(sb);
		}
		else {
			System.out.println(">> 가입한 회원이 없습니다. <<");
		}
		
		
	}// end of private void monthly_resumes_statistics() --------------------------------------


	// == 월간 후기 작성 건수를 보여주는 메소드 == //
	private void monthly_reviews_statistics() {
		System.out.println("\n----------- >>> 월별 후기 작성 건수 <<< -----------");
		System.out.println("-".repeat(50));
		System.out.println("3개월전   2개월전   1개월전   이번달   총합");
		System.out.println("-".repeat(50));

		Map<String, Integer> monthly_reviews_write = admindao.monthly_reviews_statistics();		// 월별 이력서 작성 개수


		if (monthly_reviews_write != null) {	// 정보가 있으면
			StringBuilder sb = new StringBuilder();

			// 퍼센티지 구하기
			float pct_before_3months = (float) (Math.round((float)monthly_reviews_write.get("before_3months") / (float)monthly_reviews_write.get("total") * 1000) / 1000.0 * 100);
			float pct_before_2months = (float) (Math.round((float)monthly_reviews_write.get("before_2months") / (float)monthly_reviews_write.get("total") * 1000) / 1000.0 * 100);
			float pct_before_1months = (float) (Math.round((float)monthly_reviews_write.get("before_1months") / (float)monthly_reviews_write.get("total") * 1000) / 1000.0 * 100);
			float pct_thismonth = (float) (Math.round((float)monthly_reviews_write.get("this_month") / (float)monthly_reviews_write.get("total") * 1000) / 1000.0 * 100);

			sb.append(monthly_reviews_write.get("before_3months") + "건("+ pct_before_3months +"%)\t");
			sb.append(monthly_reviews_write.get("before_2months") + "건("+ pct_before_2months +"%)\t");
			sb.append(monthly_reviews_write.get("before_1months") + "건("+ pct_before_1months +"%)\t");
			sb.append(monthly_reviews_write.get("this_month") + "건("+ pct_thismonth +"%)\t");
			sb.append(monthly_reviews_write.get("total")+"건(100%)");

			System.out.println(sb);
		}
		else {
			System.out.println(">> 작성된 후기가 없습니다. <<");
		}
	}// end of private void monthly_reviews_statistics() --------------------
	/* 회원관리 메뉴 관련 메소드 끝 */
	
	
	/* 미니게임관리 메뉴 관련 메소드 시작 */
	// == 새로운 미니게임를 등록하는 메소드 == //
	private void register_event(Scanner sc) {
		
		System.out.println("\n==== >> 미니게임 등록 << ====");
		String event_name, event_start, event_end, event_contents, yn;
		
		do {
			System.out.print(">> 게임명(50글자 이내): ");
			event_name = sc.nextLine();
			
			if (event_name.length() > 50) {
				System.out.println(">> [경고] 게임명은 50글자 이내로만 작성할 수 있습니다. <<");
			}
			else if (event_name.isBlank()) {
				System.out.println(">> [경고] 게임명은 필수 입력사항 입니다. <<");
			}
			else {
				break;
			}
		} while(true);
		// end of do~while(이벤트명) -----------------------------
		
		do {
			System.out.print(">> 시작일(미입력시 등록일:) ex)20241023: ");
			event_start = sc.nextLine();
			
			if(event_start.isEmpty()) {
				Calendar currentDate = Calendar.getInstance(); 
				SimpleDateFormat sdfmt = new SimpleDateFormat("yyyyMMdd");
	
				event_start = sdfmt.format(currentDate.getTime());		// 미입력 시 기본값
				break;
			}
			else if (!Util.check_start_date(event_start)) {
				System.out.println(">> [경고] 올바른 날짜를 입력하세요. <<");
			}
			else {
				break;
			}
		} while(true);
		// end of do~while(시작일) -----------------------------
		
		do {
			System.out.print(">> 종료일 ex)20241023: ");
			event_end = sc.nextLine();
			
			if (Util.check_end_date(event_start, event_end) == -1) {
				System.out.println(">> [경고] 올바른 날짜를 입력하세요. <<");
			}
			else if (Util.check_end_date(event_start, event_end) == 0) {
				System.out.println(">> [경고] 종료일은 시작일보다 이후여야 합니다. <<");
			}
			else {
				break;
			}
		} while(true);
		// end of do~while(종료일) -----------------------------
		
		do {
			System.out.print(">> 게임내용: ");
			event_contents = sc.nextLine();
			
			if (event_contents.length() > 100) {
				System.out.println(">> [경고] 게임내용은 100글자 이내로만 작성할 수 있습니다. <<");
			}
			else if (event_contents.isBlank()) {
				System.out.println(">> [경고] 게임내용은 필수 입력사항 입니다. <<");
			}
			else {
				break;
			}
		} while(true);
		// end of do~while(종료일) -----------------------------
		
		do {
			System.out.print(">> 등록 하시겠습니까? [Y/N]: ");
			yn = sc.nextLine();
			
			if ("y".equalsIgnoreCase(yn)) {
				break;
			}
			else if ("n".equalsIgnoreCase(yn)) {
				System.out.println(">> 게임 등록 취소.. <<");
				return;
			}
			else {
				System.out.println(">> Y 또는 N 만 입력하세요!! <<");
			}
		} while(true);
		// end of do~while(등록여부확인) -----------------------------
		
		EventDTO edto = new EventDTO();
		
		edto.setEvent_name(event_name);
		edto.setEvent_start(event_start);
		edto.setEvent_end(event_end);
		edto.setEvent_contents(event_contents);
		
		int result = admindao.register_event(edto);
		
		if (result == 1) {
			System.out.println(">> 게임 등록 성공!! <<");
		}
		
	}// end of private void register_event(Scanner sc) ------------------------------
	
	
	// == 작성된 미니게임을 수정하는 메소드 == //
	private void update_event(Scanner sc) {
		
		System.out.println("\n==== >> 미니게임 수정 << ====");
		String event_name, event_start, event_end, event_contents, yn;
		
		System.out.print(">> 수정할 게임번호: ");
		String update_no = sc.nextLine();
		
		EventDTO detail_event = admindao.view_event_detail(update_no);
		
		if (detail_event != null) {
			System.out.println("\n==== >> 수정전 게임정보 << ====");
			System.out.println(">> 게임명: "   + detail_event.getEvent_name());
			System.out.println(">> 시작일: "	   + detail_event.getEvent_start());
			System.out.println(">> 종료일: "    + detail_event.getEvent_end());
			System.out.println(">> 게임내용: " + detail_event.getEvent_contents());
			
			
			System.out.println("\n==== >> 수정하지 않으려면 엔터를 입력하세요!! << ====");
			do {
				System.out.print(">> 수정할 게임명(50글자 이내): ");
				event_name = sc.nextLine();
				
				if(event_name.isEmpty()) {			// 엔터만 입력했을 때
					event_name = detail_event.getEvent_name();
//					System.out.println(event_name);
				}
				
				if (event_name.length() > 50) {
					System.out.println(">> [경고] 게임명은 50글자 이내로만 작성할 수 있습니다. <<");
				}
				else if (event_name.isBlank()) {	// 공백 입력 시
					System.out.println(">> [경고] 게임명은 필수 입력사항 입니다. <<");
				}
				else {
					break;
				}
			} while(true);
			// end of do~while(이벤트명) -----------------------------
			
			do {
				System.out.print(">> 시작일(미입력시 등록일:) ex)20241023: ");
				event_start = sc.nextLine();
				
				if(event_start.isEmpty()) {			// 엔터만 입력했을 때
					// 2024-10-10
					event_start = detail_event.getEvent_start().substring(0, 4) + detail_event.getEvent_start().substring(5, 7) + detail_event.getEvent_start().substring(8);
//					System.out.println(event_start);
					break;
				}
				else if (!Util.check_start_date(event_start)) {
					System.out.println(">> [경고] 올바른 날짜를 입력하세요. <<");
				}
				else {
					break;
				}
			} while(true);
			// end of do~while(시작일) -----------------------------
			
			do {
				System.out.print(">> 종료일 ex)20241023: ");
				event_end = sc.nextLine();
				
				if (event_end.isEmpty()) {			// 엔터만 입력했을 때
					event_end = detail_event.getEvent_end().substring(0, 4) + detail_event.getEvent_end().substring(5, 7) + detail_event.getEvent_end().substring(8);
//					System.out.println(event_end);
					break;
				}
				
				if (Util.check_end_date(event_start, event_end) == -1) {
					System.out.println(">> [경고] 올바른 날짜를 입력하세요. <<");
				}
				else if (Util.check_end_date(event_start, event_end) == 0) {
					System.out.println(">> [경고] 종료일은 시작일보다 이후여야 합니다. <<");
				}
				else {
					break;
				}
			} while(true);
			// end of do~while(종료일) -----------------------------
			
			do {
				System.out.print(">> 수정할 게임내용: ");
				event_contents = sc.nextLine();
				
				if (event_contents.isEmpty()) {		// 엔터만 입력했을 때
					event_contents = detail_event.getEvent_contents();
//					System.out.println(event_contents);
				}
				
				if (event_contents.length() > 100) {
					System.out.println(">> [경고] 게임내용은 100글자 이내로만 작성할 수 있습니다. <<");
				}
				else if (event_contents.isBlank()) {
					System.out.println(">> [경고] 게임내용은 필수 입력사항 입니다. <<");
				}
				else {
					break;
				}
			} while(true);
			// end of do~while(종료일) -----------------------------
			
			do {
				System.out.print(">> 수정 하시겠습니까? [Y/N]: ");
				yn = sc.nextLine();
				
				if ("y".equalsIgnoreCase(yn)) {
					break;
				}
				else if ("n".equalsIgnoreCase(yn)) {
					System.out.println(">> 게임 수정 취소.. <<");
					return;
				}
				else {
					System.out.println(">> Y 또는 N 만 입력하세요!! <<");
				}
			} while(true);
			// end of do~while(수정여부확인) -----------------------------
			
			EventDTO edto = new EventDTO();
			
			edto.setEvent_name(event_name);
			edto.setEvent_start(event_start);
			edto.setEvent_end(event_end);
			edto.setEvent_contents(event_contents);
			
			int result = admindao.update_event(edto, update_no);
			
			if (result == 1) {
				System.out.println(">> 미니게임 수정 성공!! <<");
				
				System.out.println("\n==== >> 수정후 미니게임정보 << ====");
				System.out.println(">> 게임명: "   + edto.getEvent_name());
				System.out.println(">> 시작일: "	   + edto.getEvent_start());
				System.out.println(">> 종료일: "    + edto.getEvent_end());
				System.out.println(">> 게임내용: " + edto.getEvent_contents());
				
			}
		}
		else {
			System.out.println(">> 게임번호 "+ update_no +"번 게임은 존재하지 않습니다.");
		}
		
	}// end of private void update_event(Scanner sc) -----------------------------

	
	// == 미니게임을 삭제하는 메소드 == //
	private void delete_event(Scanner sc) {
		System.out.println("\n==== >> 미니게임 삭제 << ====");
		String yn;
		
		System.out.print(">> 삭제할 미니게임번호: ");
		String delete_no = sc.nextLine();
		
		EventDTO detail_event = admindao.view_event_detail(delete_no);
		
		if (detail_event != null) {
			System.out.println("\n==== >> 삭제전 미니게임정보 << ====");
			System.out.println(">> 게임명: "   + detail_event.getEvent_name());
			System.out.println(">> 시작일: "	   + detail_event.getEvent_start());
			System.out.println(">> 종료일: "    + detail_event.getEvent_end());
			System.out.println(">> 게임내용: " + detail_event.getEvent_contents());
			
			do {
				System.out.print(">> 삭제 하시겠습니까? [Y/N]: ");
				yn = sc.nextLine();
				
				if ("y".equalsIgnoreCase(yn)) {
					break;
				}
				else if ("n".equalsIgnoreCase(yn)) {
					System.out.println(">> 미니게임 삭제 취소.. <<");
					return;
				}
				else {
					System.out.println(">> Y 또는 N 만 입력하세요!! <<");
				}
			} while(true);
			// end of do~while(삭제확인) -----------------------------
			
			int n = admindao.delete_event(delete_no);
			
			if (n == 1) {
				System.out.println(">> 미니게임 삭제 성공!! <<");
			}
		}
		else {
			System.out.println(">> 미니게임번호 "+ delete_no +"번 게임은 존재하지 않습니다.");
		}
	}// end of private void delete_event(Scanner sc) -------------------------------


	// == 진행중인 미니게임을 보여주는 메소드 == //
	private void view_during_event() {
		
		List<EventDTO> during_event = admindao.show_during_event();
		
		if (during_event.size() > 0) {
			StringBuilder sb = new StringBuilder();
			
			sb.append("-".repeat(50) +"\n");
			sb.append("게임번호\t게임명\t게임소개\t종료일\t남은기간\n");
			sb.append("-".repeat(50) +"\n");
			
			for (int i=0; i<during_event.size(); i++) {
				sb.append(during_event.get(i).getEvent_no() 		+ "\t"	+
						  during_event.get(i).getEvent_name() 		+ "\t"	+
						  during_event.get(i).getEvent_contents() 	+ "\t"	+
						  during_event.get(i).getEvent_end() 		+ "\t"	+
						  during_event.get(i).getDuring_days() 		+ "일\n"
						 ) ;
				
			}// end of for() --------------------------
			
			System.out.println(sb);
		}
		else {
			System.out.println(">> 진행중인 미니게임이 없습니다. <<");
		}
	}// end of private void view_during_event() ----------------------------------

	
	// == 지난미니게임을 보여주는 메소드 == //
	private void view_ended_event() {
		List<EventDTO> ended_event = admindao.view_ended_event();
		
		if (ended_event.size() > 0) {
			StringBuilder sb = new StringBuilder();
			
			sb.append("-".repeat(50) +"\n");
			sb.append("게임번호\t게임명\t시작일\t종료일\t진행기간\n");
			sb.append("-".repeat(50) +"\n");
			
			for (int i=0; i<ended_event.size(); i++) {
				sb.append(ended_event.get(i).getEvent_no() 		+ "\t"	+
						  ended_event.get(i).getEvent_name() 	+ "\t"	+
						  ended_event.get(i).getEvent_start() 	+ "\t"	+
						  ended_event.get(i).getEvent_end() 	+ "\t"	+
						  ended_event.get(i).getDuring_days() 	+ "일\n"	
						 ) ;
				
			}// end of for() --------------------------
			
			System.out.println(sb);
		}
		else {
			System.out.println(">> 마감된 미니게임이 없습니다. <<");
		}
		
	}// end of private void view_ended_event() -------------------------------


	// == 미니게임별 이용횟수 통계를 보여주는 메소드 == //
	private void play_game_statistics() {

		List<Map<String, String>> play_event = admindao.play_game_statistics();

		if (play_event.size() > 0) {
			StringBuilder sb = new StringBuilder();

			sb.append("-".repeat(50) +"\n");
			sb.append("게임번호\t게임명\t게임소개\t참여횟수\t순위\n");
			sb.append("-".repeat(50) +"\n");

			for (int i=0; i<play_event.size(); i++) {
				sb.append(play_event.get(i).get("event_no") 		+ "\t"	+
						  play_event.get(i).get("event_name") 		+ "\t"	+
						  play_event.get(i).get("event_contents") 	+ "\t"	+
						  play_event.get(i).get("count") 			+ "번\t" +
						  play_event.get(i).get("rank") 			+ "위\n"
				) ;

			}// end of for() --------------------------

			System.out.println(sb);
		}
		else {
			System.out.println(">> 진행중인 미니게임이 없습니다. <<");
		}

	}// end of private void play_game_statistics() -----------------------------




}
