package jdbc.day03;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MemberCtrl {

	// field, arrtibute, property, 속성
	MemberDAO mdao = new MemberDAO_imple();
	
	
	//method, operation, 기능
	//		시작 메뉴를 보여주는 메소드(기능)			//
	public void menu_Start(Scanner sc) {
		
		
		
		
		
		String s_Choice = "";
		boolean isSuccess_Login = false;
		MemberDTO member = null;
		
		
		
		do {
			// 로그인 하기전
			if(isSuccess_Login == false) {
				do {
					System.out.println("\n⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙시작메뉴⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙ \n"
									+ "1. 회원가입  2. 로그인  3. 프로그램 종료 \n"
									+ "⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙⁙");
					
					System.out.print("메뉴번호 선택 : ");
					s_Choice = sc.nextLine();
		
					switch (s_Choice) {
					case "1": // 회원가입
						memberRegister(sc);
						break;
					case "2": // 로그인
						member = login(sc);
						if(member != null)
							isSuccess_Login = true;
						break;
					case "3": // 프로그램 종료
		
						return;	//menu_Start(Scanner sc) 이 메서드를 종료함
					default:
						System.out.println("😣 메뉴에 없는 번호 입니다. 다시 선택하세요!!");
						break;
					}
				} while (!("2".equals(s_Choice) && isSuccess_Login == true));		
			}// end of if-------------------------------
			
			
			
			// 로그인 한 후
			if(isSuccess_Login = true) {
				String add_menu = "admin".equals(member.getUserid()) ? "4. 모든회원조회" : "4.내정보 수정하기";
				//String bar = "admin".equals(member.getUserid()) ?"⁙".repeat(66):"⁙".repeat(48);
				
				System.out.println("\n⁙⁙⁙⁙⁙시작메뉴 [" +member.getName() +"]님 로그인 중...⁙⁙⁙⁙⁙⁙ \n"
						+ "1. 로그아웃  2. 회원탈퇴  3. 나의정보보기  " + add_menu + " \n"
						+ "⁙".repeat(66));
		
				System.out.print("메뉴번호 선택 : ");
				s_Choice = sc.nextLine();
				
				
				switch (s_Choice) {
				case "1":	//로그아웃
					member = null;
					isSuccess_Login = false;
					System.out.println("로그아웃 되었습니다 O(∩_∩)O");
					break;
				case "2":	//회원탈퇴
					String yn = null;
					
					
					do {
						System.out.print(" /_ \\ 정말로 탈퇴 하시겠습니까? [Y/N] : ");
						yn = sc.nextLine();
						if("y".equalsIgnoreCase(yn)) {
							int n = mdao.memberDelete(member.getUserseq());
							if(n == 1) {
								member = null;
								isSuccess_Login = false;
								System.out.println("회원탈퇴가 성공되었습니다 (￣ ‘i ￣;)\n");
							}
						}
						else if("n".equalsIgnoreCase(yn)) {
							System.out.println("회원탈퇴를 취소하셨습니다.\n");
						}
						
						else {
							System.out.println("Y 또는 N만 입력하세😡.\n");
						}
						
						
					} while(!("y".equalsIgnoreCase(yn) || "n".equalsIgnoreCase(yn)));
					//----------end of while----------------------
					
					break;
				case "3":	//나의정보조회
					
					System.out.println(member.toString());
					
					
					break;
				case "4":	//admin 으로 로그인 시 모든 회원 조회, 일반회원으로 로그인 시 메뉴에 없는 번호로 표시
					if("admin".equals(member.getUserid())) {
						
						showALLMember();
					}
					else {
						//수정 전 나의 정보
						updateMyinfo(member, sc);
					}
					break;
				default:
					System.out.println(">>> 메뉴에 없는 번호 입니다. 다시 선택하세요!! <<<");
					break;
				}
			}//end of if---------------------------------------------
			
		} while (true);
		//end of while---------------------------------------------
		
	}
	
	




	private void updateMyinfo(MemberDTO member, Scanner sc) {
		// TODO Auto-generated method stub
		System.out.println(member.toString());
		System.out.println("== [주의사항] 변경하지 않으려면 그냥 엔터하세요!!");
		System.out.print("- 성명 : ");
		String name = sc.nextLine();
		System.out.print("- 연락처 : ");
		String mobile = sc.nextLine();
		
		System.out.println("\n\n");
		if (name.isEmpty()) {
			name = member.getName();
		}
		if (mobile.isEmpty()) {
			mobile = member.getMobile();
		}
		
		int n = mdao.updateMyinfo(name, mobile, member.getUserseq()); 
		member.setName(name);
		member.setMobile(mobile);
		if(n == 1)
		{
			System.out.println("\n수정 완료!! (((o(*ﾟ▽ﾟ*)o)))\n");
			// 수정 후 나의 정보
			System.out.println(member.toString());
		}
		
	}






	//		회원가입을 해주는 메소드 		//
	private void memberRegister(Scanner sc) {
		
		System.out.println("\n 😊 회원가입 😊");
		System.out.print("1. 아이디 : ");
		String userid = sc.nextLine();
		
		System.out.print("2. 비밀번호 : ");
		String passwd = sc.nextLine();
		
		System.out.print("3. 회원명 : ");
		String name = sc.nextLine();
		
		System.out.print("4. 연락처(휴대폰) : ");
		String mobile = sc.nextLine();
		
		MemberDTO member = new MemberDTO();
		member.setUserid(userid);
		member.setPasswd(passwd);
		member.setName(name);
		member.setMobile(mobile);
		
		int n = mdao.memberRegister(member);

		if(n==1) {
			System.out.println("\n회원가입을 축하드립니다. (((o(*ﾟ▽ﾟ*)o)))\n");
		}
		else {
			System.out.println("\n😭회원가입이 실패되었습니다.");
		}
		
		
	}//------------------------end of memberRegister()


	//	로그인 시도하기		//
	private MemberDTO login(Scanner sc) {
	
		System.out.println("\n —————————로그인—————————");
		
		System.out.print("🫵 아이디 : ");
		String userid = sc.nextLine();
		System.out.print("🫵 비밀번호 : ");
		String passwd = sc.nextLine();
		
		Map<String, String> paraMap = new HashMap<>();
		paraMap.put("userid", userid);
		paraMap.put("passwd", passwd);
		
		MemberDTO member = mdao.login(paraMap);
		
		if(member != null ) {
			System.out.println("로그인 성공👍");
		}
		else {
			System.out.println("로그인 실패😭");
		}
		
		
		
		return member;
	}
	
	
	
	//			모든 회원을 조회해주는 메소드			//
	private void showALLMember() {
		
		List<MemberDTO> memberList = mdao.showALLMember();
		
		if(memberList != null) {
			
			StringBuilder sb = new StringBuilder();
			
			sb.append("-".repeat(50) + "\n");
			sb.append("회원번호  아이디  회원명  연락처  포인트  가입일자  가입상태\n");
			sb.append("-".repeat(50) + "\n");
			
			for (int i = 0; i<memberList.size(); i++) {
				
				String status = (memberList.get(i).getStatus() == 1)? "가입중": "탈퇴";
				
				sb.append(memberList.get(i).getUserseq()+ "  ");
				sb.append(memberList.get(i).getUserid()+ "  ");
				sb.append(memberList.get(i).getName()+ "  ");
				sb.append(memberList.get(i).getMobile()+ "  ");
				sb.append(memberList.get(i).getPoint()+ "  ");
				sb.append(memberList.get(i).getRegisterday().substring(0, 10)+ "  ");
				sb.append(status + "\n");
			}// end of for --------------------------------------
			
			
			//System.out.println(sb.toString());
			//또는
			System.out.println(sb);
		}
		else  {
			System.out.println("가입된 회원이 1명도 없습니다.😭😭");
		}
		
	} // end of showALLMember------------------------------------
	
	
	



}


