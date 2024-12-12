package my.day08.b.object_array;

import java.util.Scanner;

import my.util.MyUtil;

public class Main_member {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		
		Member[] arr_mbr = new Member[3];
		
		
		
		
		
		//java.lang.ArrayIndexOutOfBoundsException
		
		
		String str_menuno = "";
		int mbr_cnt = 0;		//회원 수
		
		
		/*
		Member mbr1 = new Member();
		mbr1.id = "leess";
		arr_mbr[0] = mbr1;
		
		Member mbr2 = new Member();
		mbr2.id = "eomjh";
		arr_mbr[1] = mbr2;
		
		mbr_cnt =2;
		*/
		
		
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~첫번째 반복문~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		
		do {
			System.out.println("\n================ >> 메뉴 << ==================\n"
							+  "1. 회원가입	2. 모든회원조회 	3.프로그램 종료\n"
							+  "=============================================\n");
			System.out.print("▣ 선택하세요 => ");
			
			str_menuno = MyUtil.spaceDelete_for(sc.nextLine());
			
			//==================================================================
			switch (str_menuno) {
			
/*
			Member mbr = new Member();
			arr_mbr[mbr_cnt].id = id;

			System.out.println(">> 회원가입 성공!!<<\n");
	*/		
			
			case "1":			//회원가입
				if(mbr_cnt < arr_mbr.length) {
					
					//------------------------------------------------------------------------		올바로 입력하기 전까지 반복
					
					boolean check = true;
					String id= null;
					String passwd = null;
					String name = null;
					
					
					check_id:
					while (check) {
						System.out.print("\n▣아이디 : ");
						id = sc.nextLine();
						
						
					/*
					jdk 11 이전방법
					if(id.trim().isEmpty()) {
						
					}
					*/
					
						
						
					//jdk 11 이후 방법
					if (id.isBlank()) {
						System.out.println(">>[경고] 아이디값을 공백이 아닌 다른 값으로 입력하세요!!\\n");
					}
					else {
						
						
						/*
						boolean isDuplicate_id = false;
						// === 입력한 아이디가 중복된 아이디인지 검사하기 첫번째 방법
						for (int i = 0; i < mbr_cnt; i++) {
							if(arr_mbr[i].id.equals(id)) {
								System.out.println(id + "는 이미 사용중인 id 이므로 새로운 id값을 넣으세요");
								isDuplicate_id = true;
								break;
								
							}
						}

						if(isDuplicate_id != true) {
							
							Member mbr = new Member();
							arr_mbr[mbr_cnt++] = mbr;

							System.out.println(">> 회원가입 성공!!<<\n");
							check = false;
							
						}
						*/
						
						// === 입력한 아이디가 중복된 아이디인지 검사하기 두번째 방법ㅣ
						for (int i = 0; i < mbr_cnt; i++) {
							if (arr_mbr[i].id.equals(id)) {
								System.out.println(id + "는 이미 사용중인 id 이므로 새로운 id값을 넣으세요");
								continue check_id;
							}
						}
						
						check = false;						
					}

				}
					
					////////////////////////////////////////// 체크 id while 끝
					do {
						System.out.print("\n▣비밀번호 : ");
						passwd = sc.nextLine();
						if(!MyUtil.isCheckPasswd(passwd)) {
							
							System.out.println("\">>[경고] 비밀번호는 8글자 이상 15글자 이하의 영문 대,소문자 및 숫자 및 특수문자가 혼합되어야만 합니다.\\n");
						}
						else
							break;
						
					} while (true);
					///////////////////////////////////////////체크 passwd 끝
					
					do {
						
						System.out.print("\n▣성명 : ");
						name = sc.nextLine();
						
						boolean isUseName = true;
						
						if (name.length() < 2 || 6 < name.length()) {
							isUseName = false;
						}
						else {		//입력한 성명의 길이기 2글자 이상 6글자 이하인 경우
							for(int i = 0; i<name.length(); i++) {
								if (!('가' <= name.charAt(i) && name.charAt(i)<='힣')) {
									isUseName = false;
									break;
								}
							}// end of for (한글이 맞는지 검사)--------------------------------
						}
						
						if(isUseName) {
							break;
						}
						else {
							System.out.println("\n>>[경고]성명은 공백이 없는 한글로만 2글자 이상 6글자 이하로 입력하세요!! \n");
						}
						
					} while (true);
					//////////////////////////////////체크 name 끝
					
					
					//성명은 공백이 아닌 한글로만 되어야 하고 글자길이는 2글자 이상 6글자 이하이어야 한다.
					
					
					Member mbr = new Member();
					mbr.id = id;
					mbr.passwd = passwd;
					mbr.name = name;
					
					arr_mbr[mbr_cnt++] = mbr;
					System.out.println(">> 회원가입 성공!!<<\\n");
					// -------------------------------------------------------------------------
					
					
			} else
				System.out.println(">> 정원마감되어 회원가입이 불가합니다!!!\n");
			break;

			//---------------------------1번 스위치 끝 -------------------------------
			
			
			case "2":			//모든회원조회
				if (mbr_cnt == 0)
					System.out.println(">> 가입된 회원이 없습니다. <<\n");
				else  {
					System.out.println("\n---------------------------------------\n"
									 + "아이디\t비밀번호\t\t성명 \n"
									 + "-----------------------------------------\n");
					for(int i=0; i<mbr_cnt; i++) {
						System.out.println(arr_mbr[i].view_info());
					}//-----------------end of for
				}
				break;
					
				
				
			case "3":
				break;
				
			default:		//메뉴에 존재하지 않는 값
				System.out.println("==[경고]  메뉴에 없는 것입니다.==");
				break;
				
				
				
				
				
			}
			//==============================================================================================
			
			
		} while (!("3".equals(str_menuno)));	//입력한 메뉴번호가 3번 이라면 반복문을 빠져나와야 한다.
		
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~첫번째 반복문~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

		sc.close();
		
		System.out.println("\n==============프로그램 종료=============\n" );
		//end of do~while----------------------

	}

}
