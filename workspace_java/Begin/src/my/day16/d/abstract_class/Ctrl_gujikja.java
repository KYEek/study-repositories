package my.day16.d.abstract_class;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class Ctrl_gujikja {
	// == 메인 메뉴를 보여주는 메소드 생성하기 == //
	
	
	
	// 구직자 회원가입
	// 구직자(Gujikja) 신규 회원가입시
	// Gujikja 클래스의 field 의 요구사항에 모두 맞으면
	// Gujikja[] gu_arr 에 저장시켜주는 메소드 생성하기
	void register(Scanner sc, Gujikja[] gu_arr) {
		
		// 지금까지 생성된 구직자 회원수가 gu_arr.length(==>정원) 보다 적을 경우에만 신규회원을 받아들인다.

//		String userid;
		String passwd;
		String name;
		String jubun;
		
		
		
		
		// 아이디는 필수 입력사항이면서 아이디 조건에 맞을때 까지 반복해야 한다.
		if (Gujikja.count < gu_arr.length) {

			Gujikja gu = new Gujikja();

			String userid;
			input_userid:
			do {
				System.out.print("1. 아이디 : ");
				userid = sc.nextLine();
				for (int i = 0; i < Gujikja.count; i++) {
					if (userid.equals(gu_arr[i].getId())) {
						System.out.println("이미 사용중인 아이디 입니다.＞﹏＜");
						continue input_userid;

					}
				} // ---------------중복 검사 for문----------------
				gu.setId(userid);	
				
				
			} while (!(gu.getId()!=null));
			
			
			
//			boolean isUse_userid;
//			do {
//				
//				isUse_userid = true;
//				System.out.print("1. 아이디 : ");
//				userid = sc.nextLine();// "eomjh" "leess" "chaew" 현재 사용중인 아이디 이므로 입력불가!!
//									   // "" 또는 "                      " 공백으로만 입력 불가!!
//				// == 가입된 회원들에 대해 중복아이디 검사하기 ==//
//				if(userid.isBlank()) {
//					System.out.println("아이디는 필수입력 사항입니다.╰（‵□′）╯\n");
//					isUse_userid = false;
//				}
//				else {
//					//각 배열을 돌면서 중복 검사
//					for(int i = 0; i<Gujikja.count; i++) {
//						if(userid.equals(gu_arr[i].getUserid())) {
//							System.out.println("이미 사용중인 아이디 입니다.＞﹏＜");
//							isUse_userid = false;
//							break;
//							
//						}
//					}//---------------중복 검사 for문----------------
//					
//				}
//				
//			} while (!isUse_userid);
//			// end of do~while -----------------------
			
			
			//비밀번호는 필수 입력사항이면서 비밀번호 조건에 맞을때 까지 반복해야 한다.
			do {
				
				System.out.print("2. 비밀번호 : ");
				passwd = sc.nextLine();// "Qw12$"  "Qwer1234sdfsdfdsfsfsdf$"  "qwer1234$"  "qWer1234$"	
				gu.setPasswd(passwd);
			} while (gu.getPasswd()==null);
			// end of do~while -----------------------
			
			
			
			// 성명은 필수 입력사항이므로 그냥 엔터나 공백만으로 된 것이 아닐때 까지 반복해야 한다.
	        // 성명은 2글자 이상 6글자 이하의 한글로만 되어져야 한다.
			do {
				System.out.print("3. 성명 : ");
				name = sc.nextLine();// "Qw12$"  "Qwer1234sdfsdfdsfsfsdf$"  "qwer1234$"  "qWer1234$"	
				gu.setName(name);
			} while (gu.getName()==null);
			// end of do~while -----------------------
			
			do {
				System.out.print("4. 주민번호 : ");
				jubun = sc.nextLine();// "Qw12$"  "Qwer1234sdfsdfdsfsfsdf$"  "qwer1234$"  "qWer1234$"	
				gu.setJubun(jubun);
			} while (gu.getJubun()==null);
			// end of do~while -----------------------
			
			
			// end of do~while -----------------------
			
			gu_arr[Gujikja.count++] = gu;
			
			System.out.println("(((o(*ﾟ▽ﾟ*)o)))구직자 회원가입 성공!!!");
		}
		else {	// 지금까지 생성된 구직자 회원수가 gu_arr.length(==>정원) 와 같거나 큰 경우에만 신규회원을 받아들이면 안된다......
			System.out.println("╰（‵□′）╯ 정원 "+ gu_arr.length + "명이 꽉차서 구직자 회원가입이 불가합니다!!!! <<\n");
		}
	}// end of void register(Scanner sc, Gujikja[] gu_arr) --------------------------------

	
	
	
	// 구직자 모두보기
	void view_all_info(Gujikja[] gu_arr) {
		/*
        -----------------------------------------------------------------------------
          아이디   비밀번호       성명      생년월일   성별   현재만나이   가입일자
        -----------------------------------------------------------------------------
        eomjh  qWe******	엄정화	1986-10-20 여	37		2024-08-29 15:20:09
        leess  ABC*******	이순신	1986-01-20 남	36		2024-08-29 15:20:09
        chawe  Wxy******	차은우	2001-06-20 남	24		2024-08-29 15:20:09
        -----------------------------------------------------------------------------
        */
		
		if(Gujikja.count == 0) {
			System.out.println(" .·´¯`(>▂<)´¯`·. 구직자로 가입된 회원이 아무도 없습니다");
		}
		else {
			title();
			StringBuilder sb = new StringBuilder();
			
			for(int i=0; i<Gujikja.count; i++) {
				sb.append(gu_arr[i].getInfo()+"\n");
			}//------------end of for---------------------
			System.out.println(sb.toString());
		}
	}// end of void view_all_info() --------------------------------
	
	//타이틀
	void title() {
		System.out.println("-".repeat(70) + "\n"
						 + " 아이디   비밀번호       성명      생년월일   성별   현재만나이   가입일자 \n"
					  	 + "-".repeat(70) +"\n");
	}// end of void title()-------------------------------
	
	
	
	
	//검색하기
	void search_menu(Scanner sc, Gujikja[] gu_arr) {
		String str_menuno;
		do {
			System.out.println("\n === 검색메뉴 ===\n"
							 + " 1.연령대 검색	2.성별	3. 연령대 및 성별	4.메인메뉴로 돌아가샤\n");
			System.out.print("( •̀ ω •́ )y　메뉴번호 선택 ");
			
			str_menuno = sc.nextLine();
			
			switch (str_menuno) {
			case "1":	// 연령대 검색
				search_ageLine(sc, gu_arr);
				break;
			case "2":	// 성별 검색
				search_gender(sc, gu_arr);
				break;
			case "3":	// 연령대 및 성별 검색
				search_ageLine_gender(sc, gu_arr);
				break;
			case "4":	// 메인메뉴로 돌아가기
				break;

			default:
				System.out.println("╰（‵□′）╯검색메뉴에 존재하는 번호만 입력하세요!!!");
				break;
			}
		} while (!"4".equals(str_menuno));
		// end of do~while~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		
	}// end of void search_menu() --------------------------------
	
	
	
	// 연령대 및 성별 검색
	
	void search_ageLine_gender(Scanner sc, Gujikja[] gu_arr) {
		if(Gujikja.count == 0) {
			System.out.println("(′д｀ )…彡…彡 구직자로 가입된 회원이 아무도 없습니다. \n");
		}
		else {
			String str_ageLine;
			boolean isUse_ageLine = false;
			do {
				System.out.print(";)검색하고자 하는 연령대[예:20] => ");
				str_ageLine = sc.nextLine();	// "0" "10" "20" "30" "40" "50" "60" "70" "80" --> 정상
								// "25" "강아지" "-20" --> 비정상
				switch (str_ageLine) {
				case "0":
				case "10":
				case "20":
				case "30":
				case "40":
				case "50":
				case "60":
				case "70":
				case "80":
					isUse_ageLine = true;
					break;

				default:
					System.out.println("(ﾟДﾟ*)ﾉ경고!! 올바른 연령대를 입력해!!!");
					break;
				}
			} while (!isUse_ageLine);
			// end of do_while--------------------------------------
			
			//== 입력받은 연령대에 해당하는 구직자 찾기 ==//
			boolean isFind_ageline = false;
			
			for(int i = 0; i <Gujikja.count; i++) {
				
				int ageLine = gu_arr[i].age()/10*10;
											//나이/10*10 ==> 연령대
											//37 ==> 30
											//36 ==> 30
											//23 ==> 20
					
				if(Integer.parseInt(str_ageLine) ==ageLine) {
					isFind_ageline = true;
					break;
				}
				
				
			}// end of for--------------------------------------------
			
			if(!isFind_ageline) {	// 검색하고자 하는 연령대에 해당하는 구직자가 존재하지 않는 경우
				System.out.println("(´･ω･`)?검색결과 연령대 " + str_ageLine + "대인 구직자는 없습니다.\n");
				return;
			}
			else { // 검색하고자 하는 연령대에 해당하는 구직자가 없는 경우

				// ===!! 입력받은 연령대 및 성별에 해당하는 구직자 찾기!!===//
				boolean isUse_gender = false;
				String input_gender;
				do {
					System.out.print("/_ \\ 검색하고자 하는 성별 [남/녀]");// "남" "여" " 남" "여 " " 남 " " 여 " --> 정상
																// "" " " "강아지" --> 비정상
					input_gender = sc.nextLine();
					input_gender = input_gender.trim();

					switch (input_gender) {
					case "남":
					case "여":
						isUse_gender = true;
						break;

					default:
						System.out.println("(ﾟДﾟ*)ﾉ경고!! \"남\" 또는 \"여\" 만 입력해!!!\n");
						break;
					}
				} while (!isUse_gender);
				
				boolean isFind_ageline_gender = false;

				StringBuilder sb = new StringBuilder();
				
				for(int i= 0; i<Gujikja.count; i++) {
					if(Integer.parseInt(str_ageLine) == gu_arr[i].age()/10*10
							&& input_gender.equals(gu_arr[i].gender()) ) {

								isFind_ageline_gender = true;
								sb.append(gu_arr[i].getInfo() +"\n");
							}
					
					
//					if( Integer.parseInt(str_ageLine) == gu_arr[i].age()/10*10 
//							&& input_gender.equals(gu_arr[i].gender()) ) {
//							
//							isFind_ageline_gender = true;
//							sb.append(gu_arr[i].getInfo() + "\n");
//						}
				}//---------------------- end of for---------------------------
				
				if(isFind_ageline_gender) {
					title();
					System.out.println(sb.toString());
				}
				else {
					System.out.println("(´･ω･`)?검색결과 연령대가 " + str_ageLine + "대인 " + input_gender + "자 구직자는 없습니다.\n");
				}
				
			}//end of if~else---------------------------------------------------------
		}
		
	}// end of search_ageLine_gender--------------------------------

	
	/*
	// 연령대 및 성별 검색하기
		void search_ageLine_gender(Scanner sc, Gujikja[] gu_arr) {
			
			if(Gujikja.count == 0) { // 구직자가 없는 경우 
				System.out.println(">> 구직자로 가입된 회원이 아무도 없습니다. <<\n");
			}

			else { // 구직자가 있는 경우
				
				boolean isUse_ageLine = false;
				String str_ageLine = "";
				
				do {
					System.out.print("▷ 검색하고자 하는 연령대[예: 20] => ");
					str_ageLine = sc.nextLine(); // "0" "10" "20" "30" "40" "50" "60" "70" "80" --> 정상
		                                         // "25" "강아지" "-20" --> 비정상
				
					switch (str_ageLine) {
						case "0":
						case "10":
						case "20":
						case "30":
						case "40":
						case "50":
						case "60":
						case "70":
						case "80":
							isUse_ageLine = true;
							break;
		
						default:
							System.out.println("[경고] 올바른 연령대를 입력하세요!!\n"); 
							break;
					}// end of switch (str_ageLine)---------------
					
				} while(!isUse_ageLine);
				// end of do~while--------------------------------
				
				
				// == 입력받은 연령대에 해당하는 구직자 찾기 == //
				boolean isFind_ageline = false;
				
				for(int i=0; i<Gujikja.count; i++) {
					
					int ageLine = gu_arr[i].age()/10*10;
								// 나이/10*10 ==> 연령대
								// 37/10*10 ==> 30
								// 36/10*10 ==> 30
								// 23/10*10 ==> 20 
					if(Integer.parseInt(str_ageLine) == ageLine) {
						isFind_ageline = true;
						break;
					}
					
				}// end of for-------------------------------
				
				if(!isFind_ageline) { // 검색하고자 하는 연령대에 해당하는 구직자가 존재하지 않는 경우 
					System.out.println("[검색결과 연령대 "+str_ageLine+"대인 구직자는 없습니다]\n");
					return; // 해당 메소드(지금은 search_ageLine_gender() ) 를 종료하는 것이다.
				}
				
				else { // 검색하고자 하는 연령대에 해당하는 구직자가 존재하는 경우 
					
					// === !! 입력받은 연령대 및 성별에 해당하는 구직자 찾기 !! === //
					boolean isUse_gender = false;
					String input_gender = "";
					do {
						System.out.print("▷ 검색하고자 하는 성별[남/여] => "); // "남" "여" "   남" "여   " "    남   " "    여   " --> 정상 
		                                                                 // ""  "      " "강아지" --> 비정상
						input_gender = sc.nextLine();
						input_gender = input_gender.trim();
						
						switch (input_gender) {
							case "남":
							case "여":
								isUse_gender = true;
								break;
			
							default:
								System.out.println("[경고] \"남\" 또는 \"여\" 만 입력하세요!!\n");
								break;
						}// end of switch (input_gender)-----------
					
					} while(!isUse_gender);
					
					
					StringBuilder sb = new StringBuilder();
					boolean isFind_ageline_gender = false;
					
					for(int i=0; i<Gujikja.count; i++) {
						
						if( Integer.parseInt(str_ageLine) == gu_arr[i].age()/10*10 
							&& input_gender.equals(gu_arr[i].gender()) ) {
							
							isFind_ageline_gender = true;
							sb.append(gu_arr[i].getInfo() + "\n");
						}
						
					}// end of for-------------------------
					
					if(isFind_ageline_gender) {
						title();
						System.out.println(sb.toString());
					}
					else {
						System.out.println("[검색결과 연령대가 "+str_ageLine+"대인 "+input_gender+"자 구직자는 없습니다]\n");
					}
					
				}			
				
			}// end of if~else--------------
			
		}// end of void search_ageLine_gender(Scanner sc, Gujikja[] gu_arr)---
	*/
	// 성별 검색
	void search_gender(Scanner sc, Gujikja[] gu_arr) {
		if(Gujikja.count == 0) {
			System.out.println("(′д｀ )…彡…彡 구직자로 가입된 회원이 아무도 없습니다. \n");
		}
		else {	//구직자가 있는 경우
			String input_gender;
			boolean isUse_gender = false;
			do {
				System.out.print(":)검색하고자 하는 성별[남/여] => ");
				input_gender = sc.nextLine();	// "남" "여" "   남" "여   " "    남   " "    여   " --> 정상
                								// ""  "      " "강아지" --> 비정상
				switch (input_gender.trim()) {
				case "남":
				case "여":
					isUse_gender = true;
					break;

				default:
					System.out.println("(ﾟДﾟ*)ﾉ경고!! \"남\" 또는 \"여\" 만 입력해!!!\n");
					break;
				}// end of switch---------------------------------------
			} while (!isUse_gender);
			// end of do_while--------------------------------------
			
			//== 입력받은 연령대에 해당하는 구직자 찾기 ==//
			StringBuilder sb = new StringBuilder();
			boolean isFind = false;
			
			for(int i = 0; i <Gujikja.count; i++) {
				
				if(input_gender.trim().equals(gu_arr[i].gender())) {

					isFind = true;
					sb.append(gu_arr[i].getInfo()+ "\n");
					
				}
				
				
			}// end of for--------------------------------------------
			
			if(isFind) {
				title();
				System.out.println(sb.toString());
			}
			else {
				
				System.out.println("(´･ω･`)?검색결과 성별 " + input_gender.trim() + "자 구직자는 없습니다.\n");
			}
			
		}
	}// end of search_gender--------------------------------------


	//연령대 검색
	void search_ageLine(Scanner sc, Gujikja[] gu_arr) {
		if(Gujikja.count == 0) {
			System.out.println("(′д｀ )…彡…彡 구직자로 가입된 회원이 아무도 없습니다. \n");
		}
		else { // 구직자가 있는 경우
			String str_ageLine;
			boolean isUse_ageLine = false;
			do {
				System.out.print(";)검색하고자 하는 연령대[예:20] => ");
				str_ageLine = sc.nextLine();	// "0" "10" "20" "30" "40" "50" "60" "70" "80" --> 정상
								// "25" "강아지" "-20" --> 비정상
				switch (str_ageLine) {
				case "0":
				case "10":
				case "20":
				case "30":
				case "40":
				case "50":
				case "60":
				case "70":
				case "80":
					isUse_ageLine = true;
					break;

				default:
					System.out.println("(ﾟДﾟ*)ﾉ경고!! 올바른 연령대를 입력해!!!");
					break;
				}
			} while (!isUse_ageLine);
			// end of do_while--------------------------------------
			
			//== 입력받은 연령대에 해당하는 구직자 찾기 ==//
			StringBuilder sb = new StringBuilder();
			boolean isFind = false;
			
			for(int i = 0; i <Gujikja.count; i++) {
				
				int ageLine = gu_arr[i].age()/10*10;
											//나이/10*10 ==> 연령대
											//37 ==> 30
											//36 ==> 30
											//23 ==> 20
					
				if(Integer.parseInt(str_ageLine) ==ageLine) {

					isFind = true;
					sb.append(gu_arr[i].getInfo()+ "\n");
					
				}
				
				
			}// end of for--------------------------------------------
			
			if(isFind) {
				title();
				System.out.println(sb.toString());
			}
			else {
				System.out.println("(´･ω･`)?검색결과 연령대 " + str_ageLine + "대인 구직자는 없습니다.\n");
			}
		}
	}// end of search_ageLine ------------------------------------




	public Gujikja login(Scanner sc, Gujikja[] gu_arr) {
		
		System.out.print(":D구직자 ID : ");
		String id = sc.nextLine();
		
		System.out.print(":D비밀번호 : ");
		String passwd = sc.nextLine();
		
		for(int i =0; i < Gujikja.count; i++) {
			if (id.equals(gu_arr[i].getId()) && passwd.equals(gu_arr[i].getPasswd())) {
				return gu_arr[i];
			}
			
		}//		end 	of		for문
		
		
		return null;
	}// end of public Gujikja login(Scanner sc, Gujikja[] gu_arr)



	// == 구직자 전용 메뉴 메소드 생성하기 == //
	public void gu_menu(Scanner sc, Gujikja login_gu, Company[] cp_arr, Recruit[] rc_arr, Recruit_Apply[] rcApply_arr) {
		String str_menuno;
		do {
			System.out.println("\n=== 구직자 전용 메뉴(" + login_gu.getName() + "님 로그인중)===\n"
							+ "1.내정보 보기	2.내정보 수정	3.모든 구인회사 조회	4.구인회사 검색\n"
							+ "5.모든 채용공고 조회	6. 채용공고 상세보기 7.채용응모하기	8.채용응모한것 조회	\n"
							+ "9.채용응모한것 수정하기	10.로그아웃");
			System.out.print(";-) 메뉴번호 선택 :");
			str_menuno = sc.nextLine();
			switch (str_menuno) {
			case "1": // 내정보 보기
				view_myInfo(login_gu);
				break;
			case "2": // 내정보 수정
				update_myInfosc(sc, login_gu);
				break;
			case "3": // 모든 구인회사 조회
				view_all_company_info(cp_arr);
				break;
			case "4": // 구인회사 검색
				search_company(sc, cp_arr);
				break;
			case "5": // 모든 채용공고 조회
				view_all_recruit_info(rc_arr);
				break;
			case "6": // 채용공고 상세보기
				view_detail_one_recruit_info(sc, rc_arr);
				break;
			case "7": // 채용응모하기
				input_recruitApply(sc, login_gu, rc_arr, rcApply_arr);
				break;
			case "8": // 채용응모한것 조회
				view_my_recruitApply(login_gu, rcApply_arr);
				break;
			case "9": // 채용응모한것 수정
				update_my_recruitApply(sc , login_gu, rcApply_arr);
				break;
			case "10": // 로그아웃
				
				break;
			default:
				System.out.println(">> 경고! 선택하신 번호는 메뉴에 없습니다ㅡㅡ.<<");
				break;
			}// end of switch
		} while (!("10".equals(str_menuno)));
		// end of do-while------------------------------------------
		System.out.println(">> 로그아웃 되었습니다. <<");
		
	}// end of gu_menu-----------------------------------------




	







	




	private void view_myInfo(Gujikja login_gu) {
		/*
		 	>>>엄정화님의 정보 <<<
		 	-----------------------------------------------------------------------
		 	아이디	비밀번호		성명		주민번호	성별		현재나이			가입일자
		 	-----------------------------------------------------------------------
		 	eomjh	qWer1234$ 	엄정화	8610202	여성		37		2024-09-02 10:05:10
		 
		 */

		System.out.println(">>> " + login_gu.getName()+"님의 정보 <<<");
		System.out.println("-".repeat(70));
		System.out.println("아이디	비밀번호		성명		주민번호	성별		현재나이			가입일자");
		System.out.println("-".repeat(70));
		System.out.println(login_gu.view_info());
		
	}

	// 내정보 수정
	private void update_myInfosc(Scanner sc, Gujikja login_gu) {
		view_myInfo(login_gu);
		System.out.println("\n>> [주의사항] 변경하지 않고 예전의 데이터값을 그대로 사용하시려면 그냥 엔터하세요!!\n");
		//-----------------------------비밀 번호 섹터------------------------------------------------------------------
		boolean exit = false;
		do {
			System.out.print("1. 비밀번호 : ");
			String new_passwd = sc.nextLine();// 기존비밀번호인 qWer1234$ 을 qWer0070$ 으로 변경하려고 한다.
											// 기존비밀번호인 qWer1234$ 을 qWer1234$ 으로 변경하려고 하려면 기존암호와 동일하므로 변경이 불가합니다.
											// 기존비밀번호인 qWer1234$ 을 변경하기 싫다.
											// 기존비밀번호인 qWer1234$ 을 abcd 로 변경하고자 할때는 비밀번호 정책에 맞지 않으므로 안된다.!!
			
			if (!new_passwd.equals("")) {// 입력한 비밀번호가 엔터가 아닐 경우

				if (login_gu.getPasswd().equals(new_passwd)) {// 입력한 비밀번호가 기존 비밀번호와 같을 경우
					System.out.println(">> 기존암호와 동일하므로 변경이 안돼요o(TヘTo)\n");
				} else {
					login_gu.setPasswd(new_passwd);
					if (login_gu.getPasswd().equals(new_passwd)) { // 비밀번호 정책에 맞는지 확인 >이건 맞는 경우
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

				if (login_gu.getName().equals(new_name)) {// 입력한 성명이 기존 성명과 같을 경우
					System.out.println(">> 기존이름과 동일하므로 변경이 안돼요o(TヘTo)\n");
				} else {
					login_gu.setName(new_name);
					if (login_gu.getName().equals(new_name)) { // 성명 정책에 맞는지 확인 >이건 맞는 경우
						exit = true;
					}
				}

			} else {// 입력한 비밀번호가 엔터일 경우
				exit = true;
			}
		} while (!exit);

		//-----------------------------이름 섹터------------------------------------------------------------------
		
		//-----------------------------주민번호 섹터------------------------------------------------------------------
		exit = false;
		do {
			System.out.print("3. 주민번호 : ");
			String new_jubun = sc.nextLine();
			
			
			if (!new_jubun.equals("")) {// 입력한 주민번호가 엔터가 아닐 경우

				if (login_gu.getJubun().equals(new_jubun)) {// 입력한 주민번호가 기존 주민번호와 같을 경우
					System.out.println(">> 기존 주민번호와 동일하므로 변경이 안돼요o(TヘTo)\n");
				} else {
					login_gu.setJubun(new_jubun);
					if (login_gu.getJubun().equals(new_jubun)) { // 성명 정책에 맞는지 확인 >이건 맞는 경우
						exit = true;
					}
				}

			} else {// 입력한 주민번호가 엔터일 경우
				exit = true;
			}
		} while (!exit);

		// -----------------------------주민번호 섹터------------------------------------------------------------------
	}//end of private void update_myInfosc
	
	// 모든 회사 정보 표시
	private void view_all_company_info(Company[] cp_arr) {
		if(Company.count == 0) {
			System.out.println(">> 구인회사로 등록된 회사가 한개도 없습니다.");
		}
		else {
			cp_arr[0].title_company();
			//회사명 업종 사업자등록번호 자본금
			
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < Company.count; i++) {
				sb.append(cp_arr[i].getInfo() + "\n");
			}
			
			System.out.println(sb.toString());
		}
		
	}// end of view_all_company_info--------------------------------------


	// 구인 회사 검색하기---------------------------------------------------------
	private void search_company(Scanner sc, Company[] cp_arr) {
		String menuno;
		do {
			System.out.println(">>> 구인회사 검색메뉴 <<<\n"
	                		+ "1. 업종검색    2.자본금검색    3.구직자메뉴로 돌아가기"); 
			System.out.print("▷ 검색메뉴번호 입력 : ");
			menuno = sc.nextLine();
			
			switch (menuno) {
			case "1":	//업종 검색
				search_jobtype_company(sc, cp_arr);
				break;

			case "2":	//자본금 검색
				search_seedmoney_company(sc, cp_arr);
				break;
			case "3":	//구직자메뉴로 돌아가기
				break;
			
			default:
				System.out.println(">> 경고! 선택하신 번호는 구직회사 검색 메뉴에 없습니다ㅡㅡ.<<");
				break;
			}
		} while (!"3".equals(menuno));
		
		
		
		
	}// end of search_company---------------------------------------------

	// 업종검색
	private void search_jobtype_company(Scanner sc, Company[] cp_arr) {
		String job_type_name;
		System.out.print("업종명 : ");
		job_type_name = sc.nextLine().toLowerCase();
		boolean is_check = false;
		
		StringBuilder  sb = new StringBuilder();
		
		for(int i =0; i<Company.count; i++) {
			if(cp_arr[i].getJob_type().toLowerCase().contains(String.join("",job_type_name.split("[ ]") ))) {
				sb.append(cp_arr[i].getInfo() + "\n");
				is_check = true;
			}
		}
		
		if(is_check) {
			cp_arr[0].title_company();
			System.out.println(sb.toString());	
		}
		else {
			System.out.println("검색하시려는 " + job_type_name + "에 해당하는 회사는 없습니다.\n");
		}
	}//end of search_jobtype_company -------------------------------------------------
	
	
	//자본금 검색
	private void search_seedmoney_company(Scanner sc, Company[] cp_arr) {
		System.out.print("자본금 : ");
		String search_seedmoney = sc.nextLine().toLowerCase();  // "5000000000"   <- ヾ(≧▽≦*)o
																// "5,000,000,000" <- ヾ(≧▽≦*)o
																// "   5000000000" <- ヾ(≧▽≦*)o
																// "50억" <-￣へ￣
																// "" <-￣へ￣
																// "                 " <-￣へ￣
		
		//System.out.println("확인용 : " + );

		try {
			long search_seed_money = Long.parseLong(String.join("", search_seedmoney.split("[, ]")));
			boolean is_check = false;

			StringBuilder sb = new StringBuilder();
			
			for (int i = 0; i < Company.count; i++) {
				if (cp_arr[i].getSeed_money() >= search_seed_money) {
					sb.append(cp_arr[i].getInfo() + "\n");
					is_check = true;
				}
			}
			DecimalFormat df = new DecimalFormat("#,###");
			if (is_check) {
				cp_arr[0].title_company();
				System.out.println(sb.toString());
			}
			else {
				System.out.println("검색하시려는 자본금이 " + df.format(search_seed_money) + "원 이상인 회사는 없습니다.\n");
			}
		}
		
		
		catch (java.lang.NumberFormatException e) {
			System.out.println("경고!!! 자본금은 정수로만 입력해!!(╬▔皿▔)╯");
		}
		
		
		
		
	}// end of search_seedmoney_company -----------------------------------------------


	// 모든 채용일정 보기
	private void view_all_recruit_info(Recruit[] rc_arr) {
		if(Recruit.count == 0) {
			System.out.println("모집하는 것이 없습니다 ㅜㅜㅜ 망했어요 .·´¯`(>▂<)´¯`·. ");
		}
		else {
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			StringBuilder sb = new StringBuilder();
			
			
			for (int i = 0; i< Recruit.count; i++) {
				try {
					Date finish_day = sdf.parse(rc_arr[i].getFinish_day());
					
					if(finish_day.after(now)) {
						sb.append(rc_arr[i].getRecruit_no() + "\t");
						sb.append(rc_arr[i].getCp().getName() + "\t");
						sb.append(rc_arr[i].getCp().getJob_type() + "\t");
						sb.append(new DecimalFormat("#,###").format(rc_arr[i].getCp().getSeed_money()) + "원\t");
						sb.append(rc_arr[i].getWork_type() + "\t");
						sb.append(rc_arr[i].getCnt() + "\t");
						sb.append(rc_arr[i].getRegister_day().substring(0, 4) + "-"
								+ rc_arr[i].getRegister_day().substring(4, 6) + "-"
								+ rc_arr[i].getRegister_day().substring(6) + "\t");
						sb.append(rc_arr[i].getFinish_day().substring(0, 4) + "-"
								+ rc_arr[i].getFinish_day().substring(4, 6) + "-"
								+ rc_arr[i].getFinish_day().substring(6) + "\n");
						
						
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
			}// 반복문 종료
			
			System.out.println("-".repeat(100));
			System.out.println("채용공고순번\t회사명\t회사직종타입\t자본금\t채용분야(근무형태)\t채용인원\t등록일자\t채용마감일자");
			System.out.println("-".repeat(100));
			System.out.println(sb.toString());
			System.out.println("채용공고가 "+Recruit.count+"건이 있습니다.");
		}//---------------------else 문 끝-------------------------------------------------------------
		
	}//end of view_all_recruit_info--------------------------------------------------

	
	// 채용응모하기
	private void input_recruitApply(Scanner sc, Gujikja login_gu, Recruit[] rc_arr, Recruit_Apply[] rcApply_arr) {
		
		if(rcApply_arr.length > Recruit_Apply.count) {
			
			
			Recruit_Apply rcapply = null;
			Recruit rc = null;
			// == 채용공고번호는 채용공고로 올라온 번호만 입력해야 한다. == //
			String input_recruit;							
			boolean isExist = false;
			int recruit_number;
			while(true) {
				try {
					System.out.print("너님이 지원하고 싶은 곳은 어디임 채용공고번호 입력하셈 : ");
					input_recruit = sc.nextLine();	//1001 <- ヾ(≧▽≦*)o
													//92345 <- ￣へ￣
					recruit_number = Integer.parseInt(input_recruit);	// 문자열을 숫자로 변환
	
					for(int i =0; i<Recruit.count; i++) {
						if(rc_arr[i].getRecruit_no() == recruit_number) {
							rc = rc_arr[i];
							isExist = true;
							break;
						}
					}
					// for문으로 같은 값이 있는지 검사
					break;
				}
				catch(java.lang.NumberFormatException e) {
					System.out.println("숫자를 넣으라고요...\n");
				}
			}
			
			//end of try-catch------------------------------
			
			
			if(!isExist) {
				System.out.println(">>너님이 넣은 " + input_recruit +"는 없는번호임￣へ￣\n");
				return; //메소드 끝
			}
			boolean is_already_apply =false;
			// == 채용공고번호는 채용공고로 올라온 번호이지만 이미 응모한 채용공고번호라면
			for(int i = 0; i<Recruit_Apply.count; i++) {
				if(rcApply_arr[i].getRc().getRecruit_no() == recruit_number) {
					if(rcApply_arr[i].getGu().getId().equals(login_gu.getId())) {
						is_already_apply = true;
						break;
					}
				}
			}//-------enf of for-----------------------------------------
			
			if(is_already_apply) {
				System.out.println("님 이거 전에 "+recruit_number + "번 응모했잖슴(￣_,￣ )... 중복떴음\n");
				return;
			}
			rcapply = new Recruit_Apply();
			do {
				System.out.print("지원동기가 뭐셈? ");
				rcapply.setApply_motive(sc.nextLine());
			} while (!(rcapply.getApply_motive() != null));
			
			
			
			rcapply.setRc(rc);
			rcapply.setGu(login_gu);
			rcApply_arr[Recruit_Apply.count] = rcapply; 
		}
		else {
			System.out.println("정원꽉찼어요.. 미안해요o(TヘTo)");
		}
	}// end of input_recruitApply
		// -------------------------------------------------------

	private void view_my_recruitApply(Gujikja login_gu, Recruit_Apply[] rcApply_arr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		boolean is_Exist = false;
		
		
		try {
			for (int i = 0; i < Recruit_Apply.count; i++) {

				if (rcApply_arr[i].getGu().getId().equals(login_gu.getId())
						&& sdf.parse(rcApply_arr[i].getRc().getFinish_day()).after(new Date()))	{// 오류
					
					
					System.out.println(rcApply_arr[i].getRc().recruit_info());
					System.out.println("지원 동기 :"+ rcApply_arr[i].getApply_motive());
					String register_day = rcApply_arr[i].getRegister_day();
					System.out.println("지원 일자 :"+ register_day.substring(0, 4) +"-" + register_day.substring(4,6) + "-" + register_day.substring(6) + "\n\n");
					is_Exist = true;
				}
			}// 포문 오와리------------------------------------

		} catch (java.text.ParseException e) {
			System.out.println("경고");
		}// try catch end------------------------------------------
		
		if(!is_Exist) {
			System.out.println("지원한게 없잔슴~~...");
		}
		
	}// end of view_my_recruitApply---------------------------------------------
	
	
	
	private void update_my_recruitApply(Scanner sc, Gujikja login_gu, Recruit_Apply[] rcApply_arr) {
		view_my_recruitApply(login_gu, rcApply_arr);
		System.out.print("수정하고싶은거 채용번호 적으셈 : ");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String str_recruit_no = sc.nextLine();
		Recruit_Apply rc_apply = null;
		
		try {
			for (int i = 0; i < Recruit_Apply.count; i++) {

				if (rcApply_arr[i].getGu().getId().equals(login_gu.getId())
						&& sdf.parse(rcApply_arr[i].getRc().getFinish_day()).after(new Date())
						&& str_recruit_no.equals(String.valueOf(rcApply_arr[i].getRc().getRecruit_no()))) {
					rc_apply = rcApply_arr[i];
					break;
					
				}
			}// 포문 오와리------------------------------------

		} catch (java.text.ParseException e) {
			System.out.println("경고");
		}// try catch end------------------------------------------
		
		if(rc_apply == null) {
			System.out.println(str_recruit_no +"는 없는번호에요...￣へ￣");
			return;
		}
		String apply_motive;
		do {

			System.out.print("뭐로 바꾸려고요 : ");
			apply_motive = sc.nextLine();
			rc_apply.setApply_motive(apply_motive);
		} while (!rc_apply.getApply_motive().equals(apply_motive));
		
		System.out.println("수정했어");
	}// end of update_my_recruitApply -------------------------------------
	
	
	private void view_detail_one_recruit_info(Scanner sc, Recruit[] rc_arr) {
		
		if(Recruit.count == 0) {
			System.out.println("모집하는 것이 없습니다 ㅜㅜㅜ 망했어요 .·´¯`(>▂<)´¯`·. ");
			return;
		}
		
		view_all_recruit_info(rc_arr);
		boolean is_Exist = false;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		System.out.print("어떤거 더 보시고 싶으셔? :");
		
		String input_number = sc.nextLine();

		try {

			for (int i = 0; i < Recruit.count; i++) {
				if (sdf.parse(rc_arr[i].getFinish_day()).after(new Date())
						&& input_number.equals(String.valueOf(rc_arr[i].getRecruit_no()))) {
					is_Exist= true;
					System.out.println(rc_arr[i].recruit_info()+"\n\n\n");
					break;
				}
			}
		} catch (java.text.ParseException e) {
			System.out.println("경고");
		}
		
		if(!is_Exist) {
			System.out.println(input_number +"는 없는번호에요...￣へ￣\n\n\n");
			return;
		}
		
	}// end of view_detail_one_recruit_info -----------------------------------
	
}
