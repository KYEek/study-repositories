package my.day12.a.abstration;

import java.util.Scanner;

import my.util.MyUtil;

public class Ctrl_gujikja {
	// == 메인 메뉴를 보여주는 메소드 생성하기 == //
	void main_menu() {
		System.out.println("\n === 메인메뉴 ===\n"
						+ " 1. 구직자 회원가입	2.구직자 모두보기	3. 검색하기	4.프로그램종료\n");
		System.out.print("（￣︶￣）↗　메뉴번호 선택 ┗|｀O′|┛ ");
	}// end of void main_menu()	-------------------------------------
	
	
	// 구직자 회원가입
	// 구직자(Gujikja) 신규 회원가입시
	// Gujikja 클래스의 field 의 요구사항에 모두 맞으면
	// Gujikja[] gu_arr 에 저장시켜주는 메소드 생성하기
	void register(Scanner sc, Gujikja[] gu_arr) {
		
		// 지금까지 생성된 구직자 회원수가 gu_arr.length(==>정원) 보다 적을 경우에만 신규회원을 받아들인다.

		String userid;
		String passwd;
		String name;
		String jubun;
		
		
		if (Gujikja.count < gu_arr.length) {
			
			boolean isUse_userid;
			do {
				
				isUse_userid = true;
				System.out.print("1. 아이디 : ");
				userid = sc.nextLine();// "eomjh" "leess" "chaew" 현재 사용중인 아이디 이므로 입력불가!!
											 // "" 또는 "                      " 공백으로만 입력 불가!!
				// == 가입된 회원들에 대해 중복아이디 검사하기 ==//
				if(userid.isBlank()) {
					System.out.println("아이디는 필수입력 사항입니다.╰（‵□′）╯\n");
					isUse_userid = false;
				}
				else {
					//각 배열을 돌면서 중복 검사
					for(int i = 0; i<Gujikja.count; i++) {
						if(userid.equals(gu_arr[i].userid)) {
							System.out.println("이미 사용중인 아이디 입니다.＞﹏＜");
							isUse_userid = false;
							break;
							
						}
					}//---------------중복 검사 for문----------------
					
				}
				
			} while (!isUse_userid);
			// end of do~while -----------------------
			
			
			//비밀번호는 필수 입력사항이면서 비밀번호 조건에 맞을때 까지 반복해야 한다.
			boolean isUse_passwd;
			do {
				
				isUse_passwd = true;
				System.out.print("2. 비밀번호 : ");
				passwd = sc.nextLine();// "Qw12$"  "Qwer1234sdfsdfdsfsfsdf$"  "qwer1234$"  "qWer1234$"
											
				if(!MyUtil.isCheckPasswd(passwd)) {
					System.out.println("[경고](ノ｀Д)ノ 비밀번호는 8글자 이상 15글자 이하의 대,소문자,숫자,특수기호가 혼합되어야만 합니다.\n");
					isUse_passwd = false;
				}
				
			} while (!isUse_passwd);
			// end of do~while -----------------------
			
			// 성명은 필수 입력사항이므로 그냥 엔터나 공백만으로 된 것이 아닐때 까지 반복해야 한다.
	        // 성명은 2글자 이상 6글자 이하의 한글로만 되어져야 한다.
			boolean isUse_name;
			do {
				
				isUse_name = true;
				System.out.print("3. 이름 : ");
				name = sc.nextLine(); // ""  "      "  "강 감 찬"  "강"  "김수한무거북이와두루미"  "강KamC"
											   // "강감찬"
						
				if(name.isBlank()) {
					isUse_name = false;
				}
				else {
					char[] ch_arr = name.toCharArray();
					if(2 <= ch_arr.length && ch_arr.length <= 6) {
						
						for(int i = 0; i<ch_arr.length;i++) {
							if(!('가' <= ch_arr[i] && ch_arr[i] <= '힣')) {
								isUse_name = false;
								break;
							}
						}
						
					}
					else {
						isUse_name = false;
					}
				}
				
				if(!isUse_name) {
					System.out.println("[경고](ノ｀Д)ノ 성명은 공백없이 한글로만 2글자 이상 6글자 이하이어야 합니다.\n");
				}
			} while (!isUse_name);
			// end of do~while -----------------------
			
			boolean isUse_jubun;
			do {
				
				isUse_jubun = true;
				System.out.print("4. 주민번호 : ");
				jubun = sc.nextLine();// "9610021" "9610022"  정상
							                  // "0010023" "0010024"  정상
							                  // "9604311" "9604312"  "0004313"  "0004314" 비정상
							                  // "9610025" "0010026"  비정상
											
				if(!MyUtil.isCheckJubun(jubun)) {
					System.out.println("[경고](ノ｀Д)ノ 올바른 주민번호를 입력하세요.\n");
					isUse_jubun = false;
				}
				
			} while (!isUse_jubun);
			// end of do~while -----------------------
			
			Gujikja gu = new Gujikja();
			gu.userid = userid;
			gu.passwd = passwd;
			gu.name = name;
			gu.jubun = jubun;
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
							&& input_gender.equals(gu_arr[i].gender()) ); {

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

}
