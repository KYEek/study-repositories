package my.day17.e.Interface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class Ctrl_company_imple implements Ctrl_company {
	// 구인회사 회원가입
	// 구인회사(Company) 신규 회원가입시
	// Company 클래스의 field 의 요구사항에 모두 맞으면
	// CommonMember[] cmber_arr 에 저장시켜주는 메소드 생성하기
	@Override
	public void register(Scanner sc, CommonMember[] cmber_arr) {
		
		// 지금까지 생성된 구직자 회원수가 gu_arr.length(==>정원) 보다 적을 경우에만 신규회원을 받아들인다.

		
		
		// 아이디는 필수 입력사항이면서 아이디 조건에 맞을때 까지 반복해야 한다.
		if (CommonMember.count < cmber_arr.length) {

			Company_imple cp = new Company_imple();

			String id;
			input_id:
			do {
				System.out.print("1. 아이디 : ");
				id = sc.nextLine();
				for (int i = 0; i < CommonMember.count; i++) {

					if (cmber_arr[i].getType() == 2) {
						if (id.equals(cmber_arr[i].getId())) {
							System.out.println("이미 사용중인 아이디 입니다.＞﹏＜");
							continue input_id;

						}
					}
				} // ---------------중복 검사 for문----------------
				cp.setId(id);
				
				
			} while (!(cp.getId()!=null));
			
			
		
			
			
			//비밀번호는 필수 입력사항이면서 비밀번호 조건에 맞을때 까지 반복해야 한다.
			do {
				String passwd;
				System.out.print("2. 비밀번호 : ");
				passwd = sc.nextLine();// "Qw12$"  "Qwer1234sdfsdfdsfsfsdf$"  "qwer1234$"  "qWer1234$"	
				cp.setPasswd(passwd);
			} while (cp.getPasswd()==null);
			// end of do~while -----------------------
			
			
			
			// 회사명은 필수 입력사항이므로 그냥 엔터나 공백만으로 된 것이 아닐때 까지 반복해야 한다.
	        // 회사명은 2글자 이상 6글자 이하의 한글로만 되어져야 한다.
			do {
				String name;
				System.out.print("3. 회사명 : ");
				name = sc.nextLine();// "Qw12$"  "Qwer1234sdfsdfdsfsfsdf$"  "qwer1234$"  "qWer1234$"	
				cp.setName(name);
			} while (cp.getName()==null);
			// end of do~while -----------------------
			
			
			//사업자등록번호
			do {
				String number;
				System.out.print("4. 사업자번호 : ");
				number = sc.nextLine();// "Qw12$"  "Qwer1234sdfsdfdsfsfsdf$"  "qwer1234$"  "qWer1234$"	
				cp.setBusiness_number(number);;
			} while (cp.getBusiness_number()==null);
			// end of do~while -----------------------
			//회사직종타입은 필수 입력사항이면서 조건에 맞을때 까지 반복해야 한다.
			do {
				String str;
				System.out.print("5. 업종 : ");
				str = sc.nextLine();// "Qw12$"  "Qwer1234sdfsdfdsfsfsdf$"  "qwer1234$"  "qWer1234$"	
				cp.setJob_type(str);
			} while (cp.getJob_type()==null);
			// end of do~while -----------------------
			
			do {
				long money;
				
				try{
					System.out.print("6. 자본금 : ");
					money =  Long.parseLong(sc.nextLine());// "Qw12$"  "Qwer1234sdfsdfdsfsfsdf$"  "qwer1234$"  "qWer1234$"	
					cp.setSeed_money(money);
				}
				catch(java.lang.NumberFormatException e) {
					System.out.println("자본금은 숫자로만 입력해라!!!.(╯‵□′)╯︵┻━┻");
				}
			} while (cp.getSeed_money()<=0);
			// end of do~while -----------------------
			
			cmber_arr[CommonMember.count++] = cp;
			
			System.out.println("(((o(*ﾟ▽ﾟ*)o)))구인회사 회원가입 성공!!!");
		}
		else {	// 지금까지 생성된 구인회사 회원수가 cp_arr.length(==>정원) 와 같거나 큰 경우에만 신규회원을 받아들이면 안된다......
			System.out.println("╰（‵□′）╯ 정원 "+ cmber_arr.length + "개가 꽉차서 구직자 회원가입이 불가합니다!!!! <<\n");
		}
		
	}

//	public Company_imple login(Scanner sc, CommonMember[] cmber_arr) {
//		System.out.print(":D구인회사 ID : ");
//		String id = sc.nextLine();
//		
//		System.out.print(":D비밀번호 : ");
//		String passwd = sc.nextLine();
//		
//		for(int i = 0; i<Company_imple.count; i++) {
//			if(id.equals(cmber_arr[i].getId()) && passwd.equals(cmber_arr[i].getPasswd())) {
//				return (Company_imple)cmber_arr[i];
//			}
//		}
//		return null;
//	}
	
	@Override
	public void cp_menu(Scanner sc, Company_imple login_cp, CommonMember[] cmber_arr, Recruit_imple[] rc_arr, Recruit_Apply[] rcApply_arr) { //구인회사 전용 메뉴
		
		String str_menuno;
		do {
			System.out.println("\n=== 구직회사 전용 메뉴(" + login_cp.getName() + "기업 로그인중)===\n"
							+ "1.우리회사 정보 보기	2.우리회사 정보 수정	3.모든 구직자 조회	4.구직자 검색\n"
							+ "5.채용공고 입력하기	6.우리회사 채용공고 조회	7.우리회사 채용공고 지원자 조회	8.로그아웃");
			System.out.print(";-) 메뉴번호 선택 :");
			str_menuno = sc.nextLine();
			switch (str_menuno) {
			case "1": // 우리회사 정보 보기
				view_myInfo(login_cp);
				break;//우리회사 정보 수정
			case "2": // 내정보 수정
				update_myInfosc(sc, login_cp);
				break;
			case "3": // 모든 구직자 조회
				view_all_gujikja_info(cmber_arr);
				break;
			case "4": // 구직자 검색
				search_gujikja(sc, cmber_arr);
				break;
			case "5": // 채용공고 입력하기
				register_recruit(sc, login_cp, rc_arr);
				break;
			case "6": // 우리회사 채용공고 조회
				view_recruit_mycompany(login_cp, rc_arr);
				break;
			case "7": // 우리회사 채용공고 지원자 조회
				view_gujikja_my_recruit(login_cp, rcApply_arr);
				break;
			case "8": // 로그아웃
				
				break;
			default:
				System.out.println(">> 경고! 선택하신 번호는 메뉴에 없습니다ㅡㅡ.<<");
				break;
			}// end of switch
		} while (!("8".equals(str_menuno)));
		// end of do-while------------------------------------------
		System.out.println(">> 로그아웃 되었습니다. <<");
		
	}//end of cp_menu-------------------------------------------------------

	




	private void view_myInfo(Company_imple login_cp) {
/*
        
        >>> 삼성 기업의 정보 <<<
        ----------------------------------------------------------------------------------
          아이디     비밀번호      회사명   가입일자      사업자등록번호  직종타입   자본금
        ----------------------------------------------------------------------------------  
          samsung  Abcd1234$   삼성     2024-02-02  8123456789   제조업    8,000,000,000원
   */

		System.out.println(">>> " +login_cp.getName()+ "기업의 정보 <<<");
		System.out.println("-".repeat(100));
		System.out.println("아이디	비밀번호		회사명		가입일자	사업자등록번호		직종타입			자본금");
		System.out.println("-".repeat(100));
		System.out.println(login_cp.view_info());
		
		
	}
	// 우리회사 정보 수정
	private void update_myInfosc(Scanner sc, Company_imple login_cp) {
		
		view_myInfo(login_cp);
		System.out.println("\n>> [주의사항] 변경하지 않고 예전의 데이터값을 그대로 사용하시려면 그냥 엔터하세요!!\n");
		//-----------------------------비밀 번호 섹터------------------------------------------------------------------
		boolean exit = false;
		do {
			System.out.print("1. 비밀번호 : ");
			String new_passwd = sc.nextLine();// 기존회사명인 삼성 을 삼성전자 로 변경하려고 한다.
								            // 기존회사명인 삼성 을 삼성 으로 변경하려고 하려면 기존회사명과 동일하므로 변경이 불가합니다. 
								            // 기존회사명인 삼성 을 변경하기 싫다. 
								            // 기존회사명인 삼성 을 삼sung 으로 변경하고자 할때는 회사명정책에 맞지 않으므로 안된다.!!
			
			if (!new_passwd.equals("")) {// 입력한 비밀번호가 엔터가 아닐 경우

				if (login_cp.getPasswd().equals(new_passwd)) {// 입력한 비밀번호가 기존 비밀번호와 같을 경우
					System.out.println(">> 기존암호와 동일하므로 변경이 안돼요o(TヘTo)\n");
				} else {
					login_cp.setPasswd(new_passwd);
					if (login_cp.getPasswd().equals(new_passwd)) { // 비밀번호 정책에 맞는지 확인 >이건 맞는 경우
						exit = true;
					}
				}

			}
			else {// 입력한 비밀번호가 엔터일 경우
				exit = true;
			}
		} while (!exit);
		//-------end of do while---------------------------------------------
		
		//-----------------------------비밀 번호 섹터------------------------------------------------------------------
		
		//-----------------------------이름 섹터------------------------------------------------------------------
		exit = false;
		do {
			System.out.print("2. 성명 : ");
			String new_name = sc.nextLine();// 기존성명인 엄정화 를 엄화정 으로 변경하려고 한다.
									        // 기존성명인 엄정화 를 엄정화로 변경하려고 하려면 기존성명과 동일하므로 변경이 불가합니다. 
									        // 기존성명인 엄정화 을 변경하기 싫다. 
									        // 기존성명인 엄정화를 엄JungHwa 로 변경하고자 할때는 성명정책에 맞지 않으므로 안된다.!!
			if (!new_name.equals("")) {// 입력한 성명이 엔터가 아닐 경우

				if (login_cp.getName().equals(new_name)) {// 입력한 성명이 기존 성명과 같을 경우
					System.out.println(">> 기존 회사과 동일하므로 변경이 안돼요o(TヘTo)\n");
				} else {
					login_cp.setName(new_name);
					if (login_cp.getName().equals(new_name)) { // 성명 정책에 맞는지 확인 >이건 맞는 경우
						exit = true;
					}
				}

			} else {// 입력한 비밀번호가 엔터일 경우
				exit = true;
			}
		} while (!exit);

		//-----------------------------이름 섹터------------------------------------------------------------------
		
		//-----------------------------사업자 번호 섹터------------------------------------------------------------------
		exit = false;
		do {
			System.out.print("3. 사업자번호 : ");
			String new_business_number = sc.nextLine();
			
			
			if (!new_business_number.equals("")) {// 입력한 사업자 번호가 엔터가 아닐 경우

				if (login_cp.getBusiness_number().equals(new_business_number)) {// 입력한 사업자 번호가 기존 주민번호와 같을 경우
					System.out.println(">> 기존 사업자번호와 동일하므로 변경이 안돼요o(TヘTo)\n");
				} else {
					login_cp.setBusiness_number(new_business_number);
					if (login_cp.getBusiness_number().equals(new_business_number)) { // 성명 정책에 맞는지 확인 >이건 맞는 경우
						exit = true;
					}
				}

			} else {// 입력한 사업자 번호가 엔터일 경우
				exit = true;
			}
		} while (!exit);
		//-----------------------------사업자 번호 섹터------------------------------------------------------------------
		
		//-----------------------------업종 섹터------------------------------------------------------------------
		exit = false;
		do {
			System.out.print("4. 업종 : ");
			String new_job_type = sc.nextLine();

			if (!new_job_type.equals("")) {// 입력한 업종이 엔터가 아닐 경우

				if (login_cp.getJob_type().equals(new_job_type)) {// 입력한 업종이 기존 업종과 같을 경우
					System.out.println(">> 기존 업종과 동일하므로 변경이 안돼요o(TヘTo)\n");
				}
				else {
					login_cp.setJob_type(new_job_type);
					if (login_cp.getJob_type().equals(new_job_type)) { // 성명 정책에 맞는지 확인 >이건 맞는 경우
						exit = true;
					}
				}

			} else {// 입력한 주민번호가 엔터일 경우
				exit = true;
			}
		} while (!exit);
		// -----------------------------업종 섹터------------------------------------------------------------------
		
		//-----------------------------자본금 섹터------------------------------------------------------------------
				exit = false;
				do {
					System.out.print("5. 자본금 : ");
					String new_seed_money = sc.nextLine();

					if (!new_seed_money.equals(""))
					{
						try {
							
							long seed_money = Long.parseLong(String.join("", new_seed_money.split("[, ]")));

							if (login_cp.getSeed_money() == seed_money) {// 입력한 가격이 기존 가격과 같을경우
								System.out.println(">> 기존 가격과 동일하므로 변경이 안돼요o(TヘTo)\n");
							}
							else {
								login_cp.setSeed_money(seed_money);
								if (login_cp.getSeed_money() == seed_money) { // 올바르게 가격이 변경되었는지 확인 >이건 맞는 경우
									exit = true;
								}
							}

						}
						catch(java.lang.NumberFormatException e) {
							System.out.println("올바른 금액을 입력하세요!!!(╬▔皿▔)╯");
						}
					}
					
					
					else {// 입력한 주민번호가 엔터일 경우
						exit = true;
					}
					
					
					
					
				} while (!exit);
				// -----------------------------업종 섹터------------------------------------------------------------------
	}// end of update_myInfosc-----------------------------------------

	private void view_all_gujikja_info(CommonMember[] cmber_arr) {// 모든 구직자 조회

		if(Gujikja_imple.count == 0) {
			System.out.println(">>구직자로 가입된 회원이 아무도 없습니다.");
		}
		else {
			title();
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i<Gujikja_imple.count; i++) {
				if (cmber_arr[i].getType() == 1)
					sb.append(cmber_arr[i].getInfo() + "\n");
			} // end of for ---------------------------------------
			
			System.out.println(sb.toString());
		}
		
		
	}//end of view_all_gujikja_info---------------------------------------------------------------

	private void title() {
		System.out.println("-".repeat(70) + "\n"
						+ " 아이디	비밀번호	성명	생년월일	성별	현재나이	가입일자\n"
						+ "-".repeat(70));
		
	}



	private void search_gujikja(Scanner sc, CommonMember[] cmber_arr) {
		String menuno;
		do {
			System.out.println(">>> 구직자 검색메뉴 <<<\n"
	                		+ "1. 성별검색    2.연령대검색    3.구직자 연령대 및 성별검색     4.구인회사 메뉴로 돌아가기"); 
			System.out.print("▷ 검색메뉴번호 입력 : ");
			menuno = sc.nextLine();
			
			switch (menuno) {
			case "1":	//성별 검색
				search_gender_gujikja(sc, cmber_arr);
				break;

			case "2":	//연령대 검색
				search_age_gujikja(sc, cmber_arr);
				break;
			case "3":	//구직자 연령대 및 성별 검색
				search_ageline_gender_gujikja(sc, cmber_arr);
				break;
			case "4":	//구인회사 메뉴로 돌아가기
				break;
			default:
				System.out.println(">> 경고! 선택하신 번호는 구직자 검색 메뉴에 없습니다ㅡㅡ.<<");
				break;
			}
		} while (!"4".equals(menuno));
	}// end of search_gujikja

	

	

	// 구직자 성별 검색
	private void search_gender_gujikja(Scanner sc, CommonMember[] cmber_arr) {
		
		String input_gender;
		
		if(CommonMember.count == 0) {	//구직자가 없는 경우
			System.out.println(">> 구직자로 가입된 회원이 아무도 없습니다.<<");
		}
		else {
			boolean isUse_input_gender = false;
			do {

				System.out.print(":D검색하고자 하는 성별[남/여] =>");
				input_gender = sc.nextLine().trim();
				// "남" "여" "   남" "여   " "    남   " "    여   " --> 정상
	            // ""  "      " "강아지" --> 비정상
				
				switch (input_gender) {
				case "남":
				case "여":
					isUse_input_gender = true;
					break;
				default:
					System.out.println("[경고] \"남\" 또는 \"여\" 만 입력하세욧!!!\n");
					break;
				}//  ------------- end of switch----------------------
			} while (!isUse_input_gender);
			// end of do~while--------------------------
			
			//입력받은  성별에 해당하는 구직자 찾기
			StringBuilder sb = new StringBuilder();
			boolean isFind = false;
			for (int i = 0; i<CommonMember.count; i++) {
				if(cmber_arr[i].getType() ==1) {

					if(((Gujikja_imple)cmber_arr[i]).gender().equals(input_gender)) {
						sb.append(cmber_arr[i].getInfo() + "\n");
						isFind = true;
					}
				}
			}// end of for -------------------------------------
			if(isFind) {

				title();
				System.out.println(sb.toString());
			}
			else {
				System.out.println("[검색경과 성별 "+ input_gender+"자 구직자가 없습니다. ");
			}
		}
		
	}// end of search_gender_gujikja
	
	// 연령대 검색
	private void search_age_gujikja(Scanner sc, CommonMember[] cmber_arr) {
		if(CommonMember.count == 0) {
			System.out.println("구직자로 가입된 회원이 아무도 없습니다.<<");
		}
		else {
			boolean isCheck = false;
			String input_str;
			do {
				System.out.print(":D검색하고자 하는 연령대(0~80대까지) : ");
				input_str = sc.nextLine();
				switch (String.join("", input_str.split("[ ]"))) {
				case "0":
				case "10":
				case "20":
				case "30":
				case "40":
				case "50":
				case "60":
				case "70":
				case "80":
					isCheck = true;
					break;
				default:
					System.out.println("올바른 값을 입력하세요!! ex)10, 20, 30");
					break;
				}
			} while (!isCheck);
			// do~while문 종료 ----------------------------------(입력값 체크)
			
			
			
			StringBuilder sb = new StringBuilder();
			boolean isExist = false;
			
			int age = Integer.parseInt(String.join("", input_str.split("[ ]")));
			for(int i = 0; i< CommonMember.count; i++) {
				if (cmber_arr[i].getType() == 1) {

					if (((((Gujikja_imple)cmber_arr[i]).age()) / 10 * 10) == age) {
						isExist = true;
						sb.append(cmber_arr[i].getInfo());
					}
				}
			}// end of for문 ------------------------ (정보 쌓기)---------
			
			if(isExist) {
				title();
				System.out.println(sb.toString()+"\n");
			}
			else {
				System.out.println("검색결과 연령대 "+ input_str+"대의 구직자가 없습니다. ");
			}
			
		}
		
	}// end of search_age_gujikja--------------------------------
	
	// 구직자 연령대 및 성별 검색
	private void search_ageline_gender_gujikja(Scanner sc, CommonMember[] cmber_arr) {

		if(CommonMember.count == 0) {
			System.out.println("구직자로 가입된 회원이 아무도 없습니다.<<");
		return;
		}
		
		boolean isCheck = false;
		String input_age, input_gender;
		do {
			System.out.print(":D검색하고자 하는 연령대(0~80대까지) : ");
			input_age = sc.nextLine();
			switch (String.join("", input_age.split("[ ]"))) {
			case "0":
			case "10":
			case "20":
			case "30":
			case "40":
			case "50":
			case "60":
			case "70":
			case "80":
				isCheck = true;
				break;
			default:
				System.out.println("올바른 값을 입력하세요!! ex)10, 20, 30");
				break;
			}
		} while (!isCheck);
		// do~while문 종료 ----------------------------------(입력값 체크)

		boolean isUse_input_gender = false;
		do {

			System.out.print(":D검색하고자 하는 성별[남/여] =>");
			input_gender = sc.nextLine().trim();
			// "남" "여" "   남" "여   " "    남   " "    여   " --> 정상
            // ""  "      " "강아지" --> 비정상
			
			switch (input_gender) {
			case "남":
			case "여":
				isUse_input_gender = true;
				break;
			default:
				System.out.println("[경고] \"남\" 또는 \"여\" 만 입력하세욧!!!\n");
				break;
			}// ------------- end of switch----------------------
		} while (!isUse_input_gender);
		// end of do~while--------------------------

		StringBuilder sb = new StringBuilder();
		boolean isExist = false;

		int age = Integer.parseInt(String.join("", input_age.split("[ ]")));
		for (int i = 0; i < CommonMember.count; i++) {
			if(cmber_arr[i].getType() == 1) {
				if (((((Gujikja_imple)cmber_arr[i]).age() / 10 * 10) == age) && (((Gujikja_imple)cmber_arr[i]).gender().equals(input_gender.trim()))) {
					isExist = true;
					sb.append(cmber_arr[i].getInfo());
				}
			}
		} // end of for문 ------------------------ (정보 쌓기)---------

		if (isExist) {
			title();
			System.out.println(sb.toString() + "\n");
		}
		else {
			System.out.println("검색결과 연령대 " + input_age + "대 " + input_gender + "자 구직자가 없습니다. ");
		}
	}
	
	// 채용공고 입력하기
	private void register_recruit(Scanner sc, Company_imple login_cp, Recruit_imple[] rc_arr) {
		
		System.out.println("==== " +login_cp.getName() + " 채용공고 등록 ====");
		
		Recruit_imple rc = new Recruit_imple();
		
		do {
			System.out.print("1.채용제목 : ");
			rc.setSubject(sc.nextLine());
			
		} while (rc.getSubject() == null);
		
		do {
			System.out.print("2.채용분야[예: 정규사무직/계약직] : ");
			rc.setWork_type(sc.nextLine());
			
		} while (rc.getWork_type() == null);
		
		do {
			System.out.print("3.채용인원 : ");
			
			try {
				rc.setCnt(Integer.parseInt(sc.nextLine()));
			}
			catch(java.lang.NumberFormatException e) {
				System.out.println("갈! 정수로만 입력하세요!!");
			}
			
			
		} while (!(rc.getCnt() > 0));
		
		do {
			System.out.print("4.연봉[단위 만원] : ");
			
			try {
				rc.setYearpay((Integer.parseInt(String.join("", sc.nextLine().split("[, ]"))))*10000);
				
			}
			catch(java.lang.NumberFormatException e) {
				System.out.println("갈! 정수로만 입력하세요!!");
			}
			
			
		} while (!(rc.getYearpay() > 0));
		
		do {
			System.out.print("5.채용마감일자[예: 20240910] : ");
			rc.setFinish_day(sc.nextLine());
			
		} while (rc.getFinish_day() == null);

		rc.setCp(login_cp);
		rc_arr[Recruit_imple.count++] = rc;
		System.out.println("채용공고 등록 성공!!");
		
		
	}// end of register_recruit -----------------------------------------------------------------

	
	// 우리회사 채용공고(채용 마감 일자가 진행중인 것만) 조회
	private void view_recruit_mycompany(Company_imple login_cp, Recruit_imple[] rc_arr) {
		
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		boolean is_existence =false;
		for(int i = 0; i<Recruit_imple.count; i++) {
			if(rc_arr[i].getCp().getId().equals(login_cp.getId())) {
				
				try {
					Date finish_day = sdf.parse(rc_arr[i].getFinish_day());

					if(finish_day.after(now)) {
						is_existence = true;
						System.out.println(rc_arr[i].recruit_info());
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			}

		if(!is_existence==true) {
			System.out.println("현재 채용 일정은 없네요");
		}
	}// end of view_recruit_mycompany ------------------------------------------------------------
	
	private void view_gujikja_my_recruit(Company_imple login_cp, Recruit_Apply[] rcApply_arr) {
		
		StringBuilder sb = new StringBuilder();
		boolean is_Exist = false;
		for(int i = 0; i<Recruit_Apply.count; i++) {
			if(login_cp.getId().equals(rcApply_arr[i].getRc().getCp().getId())) {
				sb.append(rcApply_arr[i].getRc().getRecruit_no() + "\t");
				sb.append(rcApply_arr[i].getRc().getSubject() + "\t\t\t\t");
				sb.append(rcApply_arr[i].getGu().getName() + "\t");
				sb.append(rcApply_arr[i].getGu().gender() + "\t");
				sb.append(rcApply_arr[i].getGu().age() + "\t");
				sb.append(rcApply_arr[i].getApply_motive() + "\n");
				is_Exist = true;
			}
		}// end of for----------------------------------
		
		if(is_Exist) {
			System.out.println("=".repeat(100));
			System.out.println("채용공고번호	채용제목				지원자영	성별	나이	지원동기");
			System.out.println(sb.toString());
			System.out.println("=".repeat(100));
			
		}
		else {
			System.out.println("지원자가 없습니다. ㅜㅜ┗( T﹏T )┛");
		}
		
	}//----------end of view_gujikja_my_recruyt----------------------------------
	
}
