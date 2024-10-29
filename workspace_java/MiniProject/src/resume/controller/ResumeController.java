package resume.controller;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import user.domain.MemberDTO;
import resume.domain.*;
import resume.model.*;

public class ResumeController {
	
	// field	
	ResumeDAO redao = new ResumeDAO_imple();


	// method
	public void resume_menu(Scanner sc, MemberDTO member) {
		
		
		String Choice = "";
		
		do {
			///////////////////////////////////////////////////////////
			System.out.println(">> 이력서 관리 메뉴 <<");
			System.out.println("1.등록  2.조회  3.수정  4.삭제  5.돌아가기");
			System.out.print("▶ 메뉴 번호 선택: ");
			Choice = sc.nextLine();
			
			switch (Choice) {
				case "1":	// 등록(insert)
					register_resume(sc, member);
					
					break;
	
				case "2":	// 조회(select)
					view_resume(sc, member);
					break;
					
				case "3":	// 수정(update)
					update_resume(sc, member);
					break;
					
				case "4":	// 삭제(delete)
					delete_resume(sc, member);
					break;
					
				case "5":	// 돌아가기
					
					break;
				
				default:
					System.out.println(">> 메뉴에 없는 번호입니다. 다시 선택하세요!! <<");
					break;
			
			}// end of switch (Choice) --------------
			///////////////////////////////////////////////////////////
		} while(!("5".equals(Choice)));
		// end of do~while(!("5".equals(Choice)));---------------------
		
	} // end of public void resume_menu(Scanner sc) ---------------------



	// == 이력서를 등록하는 메소드 == //
	private void register_resume(Scanner sc, MemberDTO member) {
		
		ResumeDTO resume = new ResumeDTO();
		String res_title = "";
		
		
		do {
			System.out.print("▶ 제목: ");		// 제목조건 - 100글자 이내, not null
			res_title = sc.nextLine();
			if(res_title.length() > 100) {
				System.out.println(">> [경고] 제목은 100글자 이내로 작성해야합니다!! << \n");
			}
			else if (res_title.isEmpty()) {
				System.out.println(">> [경고] 제목은 필수입력사항입니다!! << \n");
			}
			else {
				break;
			}
		} while(true);
		
		
		String res_intro = "";
		do {
			System.out.print("▶ 자기소개 : ");		// 자기소개조건 - 200글자 이내, not null
			res_intro = sc.nextLine();
			if(res_intro.length() > 200) {
				System.out.println(">> [경고] 자기소개는 200글자 이내로 작성해야합니다!! << \n");
			}
			else if (res_intro.isEmpty()) {
				System.out.println(">> [경고] 자기소개는 필수입력사항입니다!! << \n");
			}
			else {
				
				break;
			}
		} while(true);
		
		
		int res_career = 0;
		do {
			System.out.print("▶ 경력[년 단위](없으면 엔터를 입력하세요.): ");	// 경력조건 - 숫자만 가능, 없으면 0(엔터입력)
			String input_res_career = sc.nextLine();
			if(!input_res_career.isEmpty()) {
				try {
					res_career = Integer.parseInt(input_res_career);
					break;
				} catch(NumberFormatException e) {
					System.out.println(">> [경고] 경력은 숫자로만 입력해야 합니다!! << \n");
				}
			}
			else {
				break;
			}
		} while(true);
		
		
		int res_hwage = 0;
		do {
			System.out.print("▶ 희망연봉[만원 단위] : ");	// 희망연봉조건 - 숫자만 가능, 없으면 0(엔터입력) -> 회사 내규에 따름
			String input_res_hwage = sc.nextLine();
			if(!input_res_hwage.isEmpty()) {
				try {
					input_res_hwage = String.join("", input_res_hwage.split("[ ,]")); 
					res_hwage = Integer.parseInt(input_res_hwage);
					break;
				} catch (NumberFormatException e) {
					System.out.println(">> [경고] 희망연봉은 숫자로만 입력해야 합니다!! << \n");
				}
			}
			else {
				break;
			}
		} while(true);
		
		
		// 변수 초기화
		int fk_edu_code = 0;
		boolean exit = false;
		
		do {
			System.out.print("▶ 학력(1.고졸 2.초대졸 3.대졸 4.석박사) : ");		// 학력조건 - 숫자만 입력가능, not null
			String input_fk_edu_code = sc.nextLine();
			
			switch (input_fk_edu_code) {
				case "1":	// 고졸 
					fk_edu_code = Integer.parseInt(input_fk_edu_code);
					exit = true;
					break;
					
				case "2":	// 초대졸
					fk_edu_code = Integer.parseInt(input_fk_edu_code);
					exit = true;
					break;
					
				case "3":	// 대졸
					fk_edu_code = Integer.parseInt(input_fk_edu_code);
					exit = true;
					break;
					
				case "4":	// 석박사
					fk_edu_code = Integer.parseInt(input_fk_edu_code);
					exit = true;
					break;
					
				default:
					System.out.println(">> [경고] 학력은 [1, 2, 3, 4] 중에 입력하세요!! << \n");
					break;
					
			}// end of switch(Choice) --------------
			
		} while(!exit);
		// end of do~while(!exit)--------------------------
		
		
		// 변수 초기화
		exit = false;
		int fk_major_code = 0;
		
		do {
			System.out.print("▶ 전공(1.컴퓨터공학 2.경영학 3.교육학) : ");	// 전공조건 - 숫자만 입력가능, not null
			String input_fk_major_code = sc.nextLine();		
			
			switch (input_fk_major_code) {
			case "1":	// 컴퓨터공학과
				fk_major_code = Integer.parseInt(input_fk_major_code);
				exit = true;
				break;
				
			case "2":	// 경영학
				fk_major_code = Integer.parseInt(input_fk_major_code);
				exit = true;
				break;
				
			case "3":	// 교육학
				fk_major_code = Integer.parseInt(input_fk_major_code);
				exit = true;
				break;
				
			default:
				System.out.println(">> [경고] 전공은 [1,2,3] 중에 입력하세요!! << \n");
				break;
			}// end of switch (Choice) -----------------
			
		} while(!exit);
		// end of do~while(!exit)------------------------
		
		
		// 변수 초기화
		exit = false;
		int fk_work_tcode = 0;
		
		do {
			System.out.print("▶ 고용형태(1.정규직 2.비정규직) : ");	// 고용형태조건 - 숫자만 입력가능, not null
			String input_fk_work_tcode = sc.nextLine();
			
			switch (input_fk_work_tcode) {
			case "1":
				fk_work_tcode = Integer.parseInt(input_fk_work_tcode);
				exit = true;
				break;
				
			case "2":
				fk_work_tcode = Integer.parseInt(input_fk_work_tcode);
				exit = true;
				break;
				
			default:
				System.out.println(">> [경고] 고용형태는 [1, 2] 중에 입력하세요!! << \n");
				break;
			}// end of switch(Choice) ------------------
		} while(!exit);
		// end of do~while(!exit)--------------------
		
		
		// 변수 초기화
		exit = false;
		do {
			String yn = "";
			System.out.print("정말로 이력서를 등록하시겠습니까? [Y/N] : ");
			yn = sc.nextLine();
			
			if("y".equalsIgnoreCase(yn)) {
				resume.setRes_title(res_title);				// 제목
				resume.setRes_intro(res_intro);				// 자기소개
				resume.setRes_career(res_career);			// 경력
				resume.setRes_hwage(res_hwage);				// 희망연봉
				resume.setFk_user_no(member.getUser_no());	// 회원번호
				resume.setFk_edu_code(fk_edu_code);			// 학력
				resume.setFk_major_code(fk_major_code);		// 전공
				resume.setFk_work_tcode(fk_work_tcode);		// 고용형태
				
				int n = redao.register_resume(resume);
				
				if (n == 1) {
					System.out.println("\n>>> 이력서 등록 !!  <<< \n");
					exit = true;
				}
				else {
					System.out.println("\n>>> 이력서 등록 실패 !! <<<");
					exit = true;
				}
			}
			else if ("n".equalsIgnoreCase(yn)) {
				System.out.println("\n>> 이력서 등록 취소 !! << \n");
				exit = true;
			}
			else {
				System.out.println(">> [경고] Y 또는 N 만 입력하세요!! <<");
			}
		} while(!exit);
		// end of do~while(!exit);		
		
	}// end of private void register_resume() ---------------------


	
	
	// == 내 이력서를 조회해주는 메소드 == //
	private void view_resume(Scanner sc, MemberDTO member) {
		
		System.out.println("\n>> 이력서 조회 <<< ");
		System.out.println("1.전체조회 2.상세조회 3.돌아가기");
		System.out.print("▶ 메뉴 번호 선택 : ");
		String Choice = sc.nextLine();
		
		switch (Choice) {
			case "1":	// 전체조회
				view_all_resume(member);
				break;
				
			case "2":	// 상세조회
				view_detail_resume(member, sc);
				break;
				
			case "3":	// 돌아가기
				
				break;
				
			default:
				System.out.println(">> 메뉴에 없는 번호입니다. 다시 선택하세요!! <<");
				break;
		}	// end of switch(Choice) -----------------------------
			
		
	}// end of private void view_resume(Scanner sc) ---------------- 




	// == 내 이력서를 '전체' 조회해주는 메소드 == //
	private void view_all_resume(MemberDTO member) {
		
		List<ResumeDTO> resumeList = redao.view_all_resume(member);
		
		StringBuilder sb = new StringBuilder();
		
		if(resumeList.size() > 0) {		// 작성한 이력서가 존재할 때
		
			sb.append("-".repeat(55) + "\n");
			sb.append("이력서번호  작성자명  나이  	제목  			작성일자 \n");
			sb.append("-".repeat(55) + "\n");
			
			for (int i=0; i<resumeList.size(); i++) {
				sb.append(resumeList.get(i).resumeInfo() + "\n");		// boardList.get(i) = BoardDTO 
			}// end of for() --------------------			
			System.out.println(sb.toString());
		}
		else {		// 작성한 이력서가 없을 때
			System.out.println(">> 작성된 이력서가 존재하지 않습니다. <<\n");
		}	
	}// end of private void view_all_resume() ------------------
	
	
	
	// == 내 이력서를 '상세' 조회해주는 메소드 == //
	private void view_detail_resume(MemberDTO member, Scanner sc) {
		
		view_all_resume(member);
		
		String resume_no = "";
		
		do {
			System.out.print("▶ 조회할 이력서 번호 : ");
			resume_no = sc.nextLine();
			
			try {
				Integer.parseInt(resume_no);
				break;
			} catch(NumberFormatException e) {
				System.out.println(">> [경고] 이력서번호는 숫자만 입력 가능합니다!! <<");
			} 
			
		} while(true);
	
		ResumeDTO resume_detail = redao.view_detail_resume(member, resume_no);
		
		DecimalFormat df = new DecimalFormat("#,###");  // 숫자를 3자리 마다 콤마(,)를 찍어주는 것이다.
		
			if( resume_detail != null && member.getUser_no() == resume_detail.getFk_user_no()) {
				System.out.println("▶ 제목 : " + resume_detail.getRes_title() + "\n"
								+  "▶ 이름 : " + resume_detail.getMbdto().getUser_name() + "\n"
								+  "▶ 나이 : " + resume_detail.getAge() + "\n"
								+  "▶ 성별 : " + resume_detail.getGender() + "\n"
								+  "▶ 이메일 : " + resume_detail.getMbdto().getUser_email() + "\n"
								+  "▶ 학력 : " + resume_detail.getEdu_degree() + "\n"
								+  "▶ 전공 : " + resume_detail.getMajor_name() + "\n"
								+  "▶ 경력 : " + resume_detail.getRes_career() + "년 \n"
								+  "▶ 고용형태 : " + resume_detail.getWork_type() + "\n"
								+  "▶ 희망연봉 : " + df.format(resume_detail.getRes_hwage()) + "만원 \n"
								+  "▶ 자기소개 : " + resume_detail.getRes_intro() + "\n");
			}
			else {
				System.out.println(">> 이력서번호 "+ resume_no +"는(은) 존재하지 않습니다. <<\n");
			}
		
	}// end of private void view_detail_resume() ---------------
	
	
	
	
	// == 내 이력서를 수정해주는 메소드 == //
	private void update_resume(Scanner sc, MemberDTO member) {
		// 이력서 수정 - 수정하지 않으려면 엔터입력
		// -> 기존에 작성해둔 이력서에서 가져오기
		view_all_resume(member);
		
		System.out.print("▶ 수정할 이력서 번호 : ");
		String resume_no = sc.nextLine();
		
		
		ResumeDTO my_resume = redao.view_detail_resume(member, resume_no);
		
		if(my_resume == null) {
			System.out.println(">> [경고] 올바른 이력서번호를 입력하세요 <<");
		}
		else {
			
			DecimalFormat df = new DecimalFormat("#,###");  // 숫자를 3자리 마다 콤마(,)를 찍어주는 것이다.
			System.out.println("=== 수정 전 이력서 ===");
			System.out.println("▶ 제목 : " + my_resume.getRes_title() + "\n"
							+  "▶ 이름 : " + my_resume.getMbdto().getUser_name() + "\n"
							+  "▶ 나이 : " + my_resume.getAge() + "\n"
							+  "▶ 성별 : " + my_resume.getGender() + "\n"
							+  "▶ 이메일 : " + my_resume.getMbdto().getUser_email() + "\n"
							+  "▶ 학력 : " + my_resume.getEdu_degree() + "\n"
							+  "▶ 전공 : " + my_resume.getMajor_name() + "\n"
							+  "▶ 경력 : " + my_resume.getRes_career() + "년 \n"
							+  "▶ 고용형태 : " + my_resume.getWork_type() + "\n"
							+  "▶ 희망연봉 : " + df.format(my_resume.getRes_hwage()) + "만원 \n"
							+  "▶ 자기소개 : " + my_resume.getRes_intro() + "\n");
			
			System.out.println(">> 수정하지 않으려면 엔터를 입력하세요!! <<");
			
			String res_title = "";
			do {
				System.out.print("▶ 수정할 제목 :");
				res_title = sc.nextLine();
				
				if(res_title.length() > 100) {
					System.out.println(">> [경고] 제목은 100글자 이내로 작성해야합니다!! << \n");
				}
				else if (res_title.isEmpty()) {
					res_title = my_resume.getRes_title();
					break;
				}
				else {
					break;
				}
			} while(true);
			
			
			int fk_edu_code = 0;
			boolean exit = false;
			do {
				System.out.print("▶ 수정할 학력(1.고졸 2.초대졸 3.대졸 4.석박사) : ");		// 학력조건 - 숫자만 입력가능, not null
				String input_fk_edu_code = sc.nextLine();
				
				switch (input_fk_edu_code) {
					case "1":	// 고졸 
						fk_edu_code = Integer.parseInt(input_fk_edu_code);
						exit = true;
						break;
						
					case "2":	// 초대졸
						fk_edu_code = Integer.parseInt(input_fk_edu_code);
						exit = true;
						break;
						
					case "3":	// 대졸
						fk_edu_code = Integer.parseInt(input_fk_edu_code);
						exit = true;
						break;
						
					case "4":	// 석박사
						fk_edu_code = Integer.parseInt(input_fk_edu_code);
						exit = true;
						break;
					
					case "":
						fk_edu_code = my_resume.getFk_edu_code();
						exit = true;
						break;
						
					default:
						System.out.println(">> [경고] 학력은 [1, 2, 3, 4] 중에 입력하세요!! << \n");
						break;
						
				}// end of switch(Choice) --------------
				
			} while(!exit);
			// end of do~while(!exit)--------------------------
			
			// 변수 초기화
			exit = false;
			int fk_major_code = 0;
			
			do {
				System.out.print("▶ 수정할 전공(1.컴퓨터공학 2.경영학 3.교육학) : ");	// 전공조건 - 숫자만 입력가능, not null
				String input_fk_major_code = sc.nextLine();		
				
				switch (input_fk_major_code) {
				case "1":	// 컴퓨터공학과
					fk_major_code = Integer.parseInt(input_fk_major_code);
					exit = true;
					break;
					
				case "2":	// 경영학
					fk_major_code = Integer.parseInt(input_fk_major_code);
					exit = true;
					break;
					
				case "3":	// 교육학
					fk_major_code = Integer.parseInt(input_fk_major_code);
					exit = true;
					break;
				
				case "":
					fk_major_code = my_resume.getFk_major_code();
					exit = true;
					break;
					
				default:
					System.out.println(">> [경고] 전공은 [1,2,3] 중에 입력하세요!! << \n");
					break;
				}// end of switch (Choice) -----------------
				
			} while(!exit);
			// end of do~while(!exit)------------------------
			
			
			
			int res_career = 0;
			do {
				System.out.print("▶ 수정할 경력[년 단위](없으면 엔터를 입력하세요) : ");	// 경력조건 - 숫자만 가능, 없으면 0(엔터입력)
				String input_res_career = sc.nextLine();
				if(!input_res_career.isEmpty()) {
					try {
						res_career = Integer.parseInt(input_res_career);
						break;
					} catch(NumberFormatException e) {
						System.out.println(">> [경고] 경력은 숫자로만 입력해야 합니다!! << \n");
					}
				}
				else {
					res_career = my_resume.getRes_career();
					break;
				}
			} while(true);
			
			
			// 변수 초기화
			exit = false;
			int fk_work_tcode = 0;
			
			do {
				System.out.print("▶ 수정할 고용형태(1.정규직 2.비정규직) : ");	// 고용형태조건 - 숫자만 입력가능, not null
				String input_fk_work_tcode = sc.nextLine();
				
				switch (input_fk_work_tcode) {
				case "1":
					fk_work_tcode = Integer.parseInt(input_fk_work_tcode);
					exit = true;
					break;
					
				case "2":
					fk_work_tcode = Integer.parseInt(input_fk_work_tcode);
					exit = true;
					break;
				
				case "":
					fk_work_tcode = my_resume.getFk_work_tcode();
					exit = true;
					break;
				default:
					System.out.println(">> [경고] 고용형태는 [1, 2] 중에 입력하세요!! << \n");
					break;
				}// end of switch(Choice) ------------------
			} while(!exit);
			// end of do~while(!exit)--------------------
			
			
			int res_hwage = 0;
			do {
				System.out.print("▶ 수정할 희망연봉[만원 단위] : ");	// 희망연봉조건 - 숫자만 가능, 없으면 0(엔터입력) -> 회사 내규에 따름
				String input_res_hwage = sc.nextLine();
				if(!input_res_hwage.isEmpty()) {
					try {
						input_res_hwage = String.join("", input_res_hwage.split("[ ,]")); 
						res_hwage = Integer.parseInt(input_res_hwage);
						break;
					} catch (NumberFormatException e) {
						System.out.println(">> [경고] 희망연봉은 숫자로만 입력해야 합니다!! << \n");
					}
				}
				else {
					res_hwage = my_resume.getRes_hwage();
					break;
				}
			} while(true);
			
			
			
			String res_intro = "";
			do {
				System.out.print("▶ 수정할 자기소개 : ");		// 자기소개조건 - 200글자 이내, not null
				res_intro = sc.nextLine();
				if(res_intro.length() > 200) {
					System.out.println(">> [경고] 자기소개는 200글자 이내로 작성해야합니다!! << \n");
				}
				else if (res_intro.isEmpty()) {
					res_intro = my_resume.getRes_intro();
					break;
				}
				else {
					break;
				}
			} while(true);
			
			String yn = "";
			
			do {
				System.out.print(">> 정말로 수정하시겠습니까? [Y/N] :");
				yn = sc.nextLine();
				
				if("y".equalsIgnoreCase(yn)) {
					break;
				}
				else if ("n".equalsIgnoreCase(yn)) {
					System.out.println(">> 수정을 취소했습니다!! <<");
					return;
				}
				else {
					System.out.println(">> [경고] Y 또는 N 중에 입력하세요 !! <<");
				}
			}while(true);
			
			Map<String, String> paramap = new HashMap<>();
			
			paramap.put("res_title", res_title);
    		paramap.put("fk_edu_code", String.valueOf(fk_edu_code));
    		paramap.put("fk_major_code", String.valueOf(fk_major_code));
    		paramap.put("res_career", String.valueOf(res_career));
    		paramap.put("fk_work_tcode", String.valueOf(fk_work_tcode));
    		paramap.put("res_hwage", String.valueOf(res_hwage));
    		paramap.put("res_intro", res_intro);

    		int n = redao.update_resume(paramap, resume_no);
			
    		if (n == 1) {
    			System.out.println(">> 이력서 수정 성공!! << \n");
    		}
 	
		}
			
	}// end of private void update_resume(MemberDTO member, Scanner sc) ----------




	// == 내 이력서를 삭제해주는 메소드 == //
	private void delete_resume(Scanner sc, MemberDTO member) {
		
		view_all_resume(member);
		
		String resume_no = "";
		
		do {
			do {
				System.out.print("▶ 삭제할 이력서 번호 : ");
				resume_no = sc.nextLine();
				
				try {
					Integer.parseInt(resume_no);
					break;
				} catch(NumberFormatException e) {
					System.out.println(">> [경고] 이력서번호는 숫자만 입력 가능합니다!! <<");
				} 
				
			} while(true);
			// end of do~while(true);
			
			
			ResumeDTO resume_detail = redao.view_detail_resume(member, resume_no);
			
			DecimalFormat df = new DecimalFormat("#,###");  // 숫자를 3자리 마다 콤마(,)를 찍어주는 것이다.
			
			if( resume_detail != null && member.getUser_no() == resume_detail.getFk_user_no()) {
				System.out.println("▶ 제목 : " + resume_detail.getRes_title() + "\n"
								+  "▶ 이름 : " + resume_detail.getMbdto().getUser_name() + "\n"
								+  "▶ 나이 : " + resume_detail.getAge() + "\n"
								+  "▶ 성별 : " + resume_detail.getGender() + "\n"
								+  "▶ 이메일 : " + resume_detail.getMbdto().getUser_email() + "\n"
								+  "▶ 학력 : " + resume_detail.getEdu_degree() + "\n"
								+  "▶ 전공 : " + resume_detail.getMajor_name() + "\n"
								+  "▶ 경력 : " + resume_detail.getRes_career() + "년 \n"
								+  "▶ 고용형태 : " + resume_detail.getWork_type() + "\n"
								+  "▶ 희망연봉 : " + df.format(resume_detail.getRes_hwage()) + "만원 \n"
								+  "▶ 자기소개 : " + resume_detail.getRes_intro() + "\n");
				
				String yn = "";
				
				do {
					System.out.print(">> 정말로 삭제하시겠습니까? [Y/N] << : ");
					yn = sc.nextLine();
					
					if("y".equalsIgnoreCase(yn)) {
						
						int n = redao.delete_resume(resume_no);
						
						if(n==1) {
							System.out.println(">> " + resume_no + "번 이력서 삭제 성공!! << \n");
							return;
						}
						else {
							System.out.println(">> " + resume_no + "번 이력서 삭제 실패!! << \n");
							return;
						}
						
					}
					else if("n".equalsIgnoreCase(yn)) {
		                System.out.println(">> 이력서 삭제를 취소하셨습니다. << \n");
		                return;
		            }
		            else {
		                System.out.println(">> [경고] Y 또는 N 만 입력하세요!!");
		            }
				
				} while(!("y".equalsIgnoreCase(yn) || "n".equalsIgnoreCase(yn)));
			
			}
			// end of if( resume_detail != null && member.getUser_no() == resume_detail.getFk_user_no())
			
			else {
				System.out.println(">> 이력서번호 "+ resume_no +"는(은) 존재하지 않습니다. <<\n");
			}
		
		} while(true);
		// end of do~while(true) -------------
		
	}// end of private void delete_resume(Scanner sc, MemberDTO member) -------

	
	
	
}
