package jdbc.day04.board.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import jdbc.day04.board.domain.BoardDTO;
import jdbc.day04.member.domain.CommentDTO;
import jdbc.day04.member.domain.MemberDTO;
import jdbc.day04.board.model.*;

public class BoardController {
	
	
	
	//field
	BoardDAO bdao = new BoardDAO_imple();
	
	
	
	//method
	//			게시판 메뉴	를 보여주는 메소드		//
	public void menu_Board(MemberDTO member, Scanner sc) {
		//———————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
		boolean isExit= false;
		do {
			String add_menu = "admin".equals(member.getUserid())?"7.최근1주일간 일자별 게시글 작성건수 \n8.이번달 일자별 게시글 작성건수   9.나가기\n":"7.나가기 \n";
			
			System.out.println("\n--------------게시판 메뉴 [" + member.getName() + "님 로그인중..]------------------\n"
							+ "1.글목록보기   2.글내용보기   3.글쓰기   4.댓글쓰기 \n"
							+ "5.글수정하기   6.글삭제하기   " + add_menu);
			
			System.out.print("🤚메뉴번호 선택 : ");
			String s_menuNo = sc.nextLine();
			
			switch (s_menuNo) {
			case "1":	//글목록보기
				boardList();
				break;
			case "2":	//글내용보기
				viewContents(member.getUserid(), sc);
				break;
			case "3":	//글쓰기
				int n = write(member, sc);
				if(n==1)
					System.out.println(">> 글쓰기 성공!! <<");
				else if(n==0)
					System.out.println(">> 글쓰기 취소!! <<");
				else if(n== -1)
					System.out.println(">> 글쓰기 실패!! <<");
				
				break;
			case "4":	//댓글쓰기
				n = writeConmment(member, sc);
				
				if(n==1)
					System.out.println(">> 글쓰기 성공!! <<");
				else if(n==0)
					System.out.println(">> 글쓰기 취소!! <<");
				else if(n== -1)
					System.out.println(">> 글쓰기 실패!! <<");
				
				break;
			case "5":	//글수정하기
				updateBoard(member.getUserid(), sc);
				break;
			case "6":	//글삭제하기
				deleteBoard(member.getUserid(), sc);
				break;
			case "7":	//관리자(최근1주일간 일자별 게시글 작성건수) 유저(나가기)
				if(!"admin".equals(member.getUserid())) 
					isExit = true;
				else {
					statics_by_week();
					
				}
				break;
			case "8":	//관리자 일 때 이번달 일자별 게시글 작성건수
				if("admin".equals(member.getUserid())) {
					statics_by_currentMonth();
					break;
				}
			case "9":	//admin 일때 나가기
				if("admin".equals(member.getUserid())) {
					isExit = true;
					break;
				}
			default:
				System.out.println(">> 메뉴에 없는 번호 입니다. <<");
				
				break;
			}// end of switch --------------------------------------
		}while (!isExit);
		//———————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
	}

	
	

	
	
	
	
	
	
	
	
	
	
	




	








	



























	





























































































































































































































































































































































































	// *** 글쓰기를 해주는 메소드 *** //
	// === Transaction 처리 ===
	//    (tbl_board 테이블에 insert 가 성공되어지면 tbl_member 테이블의 point 컬럼에 10씩 증가 update 를 할 것이다.
	//     그런데 insert 또는 update 가 하나라도 실패하면 모두 rollback 할 것이고,
	//     insert 와 update 가 모두 성공해야만 commit 할 것이다.)
	private int write(MemberDTO member, Scanner sc) {
		
		int result = 0;
	
		System.out.println("⌂⌂⌂⌂⌂글쓰기⌂⌂⌂⌂⌂");
		
		System.out.println("1. 작성자명 : " + member.getName());
		
		System.out.print("2. 글제목 [최대 100글자] : " );
		String subject = sc.nextLine();
		
		System.out.print("3. 글내용 [최대 200글자] : " );
		String contents = sc.nextLine();
		
		System.out.print("4. 글암호 [최대 20글자] : " );
		String boardpasswd = sc.nextLine();
		
		
		BoardDTO bdto = new BoardDTO();
		bdto.setFk_userid(member.getUserid());
		bdto.setSubject(subject);
		bdto.setContents(contents);
		bdto.setBoardpasswd(boardpasswd);
		
		do {
			System.out.print(" 정말로 글쓰기를 하시겠습니까?[Y/N] => ");
			String yn = sc.nextLine();
			
			if("y".equalsIgnoreCase(yn)) {
				int subject_length = subject.length();
				int contents_length = contents.length();
				int boardpasswd_length = boardpasswd.length();
				
				if((1<=subject_length && subject_length <=100) && (1<=contents_length && contents_length <=200) && (1<=boardpasswd_length && boardpasswd_length <=20))
				{
					result = bdao.write(bdto);
				}
				else {
					System.out.println(">> 입력한 데이터가 너무 크므로 입력이 불가합니다. <<");
				}
				
				break;
			}
			else if("n".equalsIgnoreCase(yn)) {
				break;
			}
			else {
				System.out.println("Y 또는 N 만 입력하세요!! 喝喝喝");
			}
		} while (true);
		return result;
	}
	
	
	//		글목록보기 해주는 메소드		//
	private void boardList() {
		
		List<BoardDTO> boardList = bdao.boardList();
		
		if(boardList.size() > 0) {
			//게시글이 존재 하는 경우
			StringBuilder sb = new StringBuilder();
			
			sb.append("\n" + "-".repeat(30) + " [게시글 목록] " + "-".repeat(35) + "\n");
			sb.append("글번호\t글제목\t\t작성자\t작성일자\t\t\t\t조회수\n");
			sb.append("-".repeat(75) +"\n");
			
			for(int i = 0; i<boardList.size(); i++) {
				sb.append(boardList.get(i).boardInfo() + "\n");
						//boardList.get(i) 는 BoardDTO 이다
			}//end of for-----------------
			
			System.out.println(sb);
		}
		else {
			//게시글이 존재 하지 않는 경우
			System.out.println("글 목록이 없어ㅡㅡ");
		}
		
		
	}//end of private void boardList() -----------------------------------
	
	
	
	//			글 내용보기 해주는 메소드			//
	// == 현재 로그인 사용자가 자신이 쓴 글을 볼때는 조회수 증가가 없지만
	//    다른 사용자가 쓴 글을 볼때는 조회수를 1증가 해주어야 한다.
	private void viewContents(String login_userid, Scanner sc) {
		
		
		System.out.println("\n>>> 글내용 보기 <<< ");
		
		System.out.print("🤚 글번호 : ");
		String boardNo = sc.nextLine();
		
		Map<String, String> paraMap = new HashMap<>();
		paraMap.put("login_userid", login_userid);
		paraMap.put("boardNo", boardNo);
		
		BoardDTO bdto = bdao.viewContents(paraMap);
		
		if(bdto != null) {
			// 존재하는 글번호를 입력한 경우
			System.out.println("[글제목] " + bdto.getSubject() + "\n"
							+ "[글내용] " + bdto.getContents() + "\n"
							+ "[작성자] " + bdto.getMbrdto().getName() + "\n"
							+ "[조회수] " + bdto.getViewcount());
		}
		else {
			// 존재하지 않는 글번호 또는 글번호를 숫자가 아닌 문자로 입력한 경우
			System.out.println(">> 글번호 " + boardNo + "은(는) 글 목록에 존재하지 않습니다. << \n");
		}
		
		//여기까지는 뭔글
		//————————————————————————————————————————————
		
		System.out.println("[댓글]\n"+"-".repeat(50));
		List<CommentDTO> commentList = bdao.commentList(boardNo);
		// 원글에 대한 댓글을 가져오는 것(특정 게시글 글번호에 대한 tbl_comment 테이블과 tbl_member 테이블을 JOIN 해서 보여준다.)
		
		if(commentList.size() > 0) {
			//원글에 대한 댓그링 존재하는 경우
			StringBuilder sb = new StringBuilder();
			
			sb.append("댓글내용\t\t작성자명\t작성일자\n");
			sb.append("-".repeat(50)+"\n");
			
			for(CommentDTO cmtdto : commentList) {
				sb.append(cmtdto.getContents()+"\t\t"+cmtdto.getMember().getName()+"\t"+ cmtdto.getWriteday() +"\n");
			}
			System.out.println(sb);
		}
		else {
			//원글에 대한 댓글이 존재하지 않는 경우
			System.out.println("댓글 내용 없음😐 \n");
			
		}
		
	}//end of viewContents————————————————————————————————
	
	
	//			댓글쓰기 해주는 메소드			//
	private int writeConmment(MemberDTO member, Scanner sc) {
		
		int result = 0;
		
		System.out.println("\n>>> 댓글쓰기 <<<");
		
		System.out.println("1. 작성자명 : " + member.getName());
		
		System.out.print("2. 원글의 글번호 : ");
		String s_fk_boardno = sc.nextLine();	// "돌똘이"와 같은 문자가 들어오면 안된다.!!
		
		String contents = null;
		int fk_boardno = 0;
		
		
		do {
		//————————————————————————————————————————————————————————
			try {
				fk_boardno = Integer.parseInt(s_fk_boardno);
				
				if(fk_boardno < 1) 
					System.out.println("!!!🤚경고 원글의 글 번호는 1 이상인 정수로만 입력하셔야 해요!!!");
				else
					break;
				
			} catch (NumberFormatException e) {
				System.out.println("!!!🤚경고 원글의 글 번호는 정수로만 입력하셔야 해요!!!");
			}
		//————————————————————————————————————————————————————————
		} while (true);
		
		
		do {
		//————————————————————————————————————————————————————————
			System.out.print("3. 댓글 내용 : ");
			contents = sc.nextLine();
			
			/*
		        댓글의 내용을 입력할 때 그냥 엔터
		        또는 공백만으로 입력하거나 
		        또는 tbl_comment 테이블의 contents 컬럼의 크기(최대 100글자)보다 더 많은 글자를 입력하는 경우 
			 */
			if(contents.isBlank()) {	//그냥 엔터 또는 공백만으로 입력한 경우
				System.out.println("!!!🤚경고 댓글 내용은 필수로 입력하셔야 해요!!!\n");
			}
			else if (contents.length() > 100) {
				System.out.println("!!!🤚경고 댓글 내용은 최대 100글자 이내로 입력하셔야 해요!!!\n");
			}
			else {
				break;
			}
		//————————————————————————————————————————————————————————
		} while (true);
		
		do {
		//————————————————————————————————————————————————————————
			System.out.print("🤔정말로 댓글 쓰기를 하시겠습니까? [Y / N]\n");
			String yn = sc.nextLine();
			
			if("y".equalsIgnoreCase(yn)) {
				CommentDTO cmtdto = new CommentDTO();
				cmtdto.setFk_boardno(fk_boardno);
				cmtdto.setFk_userid(member.getUserid());
				cmtdto.setContents(contents);
				
				
				result = bdao.writeComment(cmtdto);
				// 		 1 또는 -1(실패, 외래키 무결성)
				break;
			}
			else if("n".equalsIgnoreCase(yn)) {
				break;
			}
			else {
				
			}
		//————————————————————————————————————————————————————————
		} while(true);
		return result;
	}
	
	//			글 수정 해주는 메소드			//
	private void updateBoard(String login_userid, Scanner sc) {

		System.out.println("\n >>> 글 수정하기 <<<");

		System.out.print("!!수정할 글 번호 : ");
		String boardno = sc.nextLine();

		BoardDTO bdto = bdao.viewContents(boardno);

		if (bdto == null) {
			// 수정할 글번호가 글목록에 존재하지 않는 경우
			System.out.println(">> 글번호 " + boardno + "은 글목록에 존재하지 않습니다. << \n");
		} else {
			// 수정할 글번호가 글목록에 존재하는 경우
			if (!login_userid.equals(bdto.getFk_userid())) {
				// 수정할 글번호가 다른 사용자가 쓴 글인 경우라면
				System.out.println("[경고] 다른 사용자의 글은 수정 불가합니다.!! \n");
			} else {
				// 수정할 글번호가 내가 쓴 글인 경우라면
				System.out.print("!!글암호 : ");
				String boardpasswd = sc.nextLine();
				if (!boardpasswd.equals(bdto.getBoardpasswd())) {
					// 글암호가 일치하지 않는 경우
					System.out.println("[경고] 입력하신 글암호가 작성시 입력한 글암호와 일치하지 않으므로 수정 불가합니다.!! \n");
				} else {
					// 글암호가 일치하는 경우

					System.out.println("--------------------------------------");
					System.out.println("[수정전 글제목] " + bdto.getSubject());
					System.out.println("[수정전 글내용] " + bdto.getContents());
					System.out.println("--------------------------------------");

					System.out.print("▷ 글제목[최대 100글자, 변경하지 않으려면 그냥 엔터] : ");
					String subject = sc.nextLine();
					if (subject.isBlank()) {
						subject = bdto.getSubject();
					}

					System.out.print("▷ 글내용[최대 200글자, 변경하지 않으려면 그냥 엔터] : ");
					String contents = sc.nextLine();
					if (contents.isBlank()) {
						contents = bdto.getContents();
					}
					if (subject.length() > 100 || contents.length() > 200) {
						System.out.println("[경고] 글제목은 최대 100글자 이며, 글내용은 최대 200글자 이내이어야 합니다. \n");
					} else {
						String yn = "";
						do {
							// ————————————————————————————————————————————————
							System.out.print("▷ 정말로 글수정 하시겠습니까?[Y/N] : ");
							yn = sc.nextLine();

							if ("y".equalsIgnoreCase(yn)) {
								Map<String, String> paraMap = new HashMap<>();

								paraMap.put("boardno", boardno);
								paraMap.put("subject", subject);
								paraMap.put("contents", contents);
								int n = bdao.updateBoard(paraMap);

								if (n == 1) {
									System.out.println(" >> 글 수정 성공!!<< \n");
								} else {
									System.out.println(">>SQL 구문 오류 발생으로 인해 글 수정이 실패되었습니다...😭");
								}

							} else if ("n".equalsIgnoreCase(yn)) {
								System.out.println(">> 글 수정을 취소하셨습니다. << \n");
							} else {
								System.out.println(">> [경고] Y 또는 N 만 입력하세요!!");
							}
							// ————————————————————————————————————————————————
						} while (!("y".equalsIgnoreCase(yn) || "n".equalsIgnoreCase(yn)));
					}
				}
			}

		}

	}// end of function-----------------------------------

	//	글 삭제해주는 메소드			///
	private void deleteBoard(String login_userid, Scanner sc) {
		System.out.println("\n >>> 글 수정하기 <<<");

		System.out.print("!!삭제할 글 번호 : ");
		String boardno = sc.nextLine();

		BoardDTO bdto = bdao.viewContents(boardno);

		if (bdto == null) {
			// 삭제할 글번호가 글목록에 존재하지 않는 경우
			System.out.println(">> 글번호 " + boardno + "은 글목록에 존재하지 않습니다. << \n");
		} else {
			// 삭제할 글번호가 글목록에 존재하는 경우
			if (!login_userid.equals(bdto.getFk_userid())) {
				// 삭제할 글번호가 다른 사용자가 쓴 글인 경우라면
				System.out.println("[경고] 다른 사용자의 글은 삭제 불가합니다.!! \n");
			} else {
				// 삭제할 글번호가 내가 쓴 글인 경우라면
				System.out.print("!! 글암호 : ");
				String boardpasswd = sc.nextLine();
				if (!boardpasswd.equals(bdto.getBoardpasswd())) {
					// 글암호가 일치하지 않는 경우
					System.out.println("[경고] 입력하신 글암호가 작성시 입력한 글암호와 일치하지 않으므로 삭제 불가합니다.!! \n");
				} else {
					// 글암호가 일치하는 경우

					System.out.println("--------------------------------------");
					System.out.println("[삭제전 글제목] " + bdto.getSubject());
					System.out.println("[삭제전 글내용] " + bdto.getContents());
					System.out.println("--------------------------------------");

					String yn = "";
					do {
						// ————————————————————————————————————————————————
						System.out.print("▷ 정말로 글수정 하시겠습니까?[Y/N] : ");
						yn = sc.nextLine();

						if ("y".equalsIgnoreCase(yn)) {
							
							int n = bdao.deleteBoard(boardno);

							if (n == 1) {
								System.out.println(" >> 글 삭제 성공!!<< \n");
							} else {
								System.out.println(">>SQL 구문 오류 발생으로 인해 글 삭제가 실패되었습니다...😭");
							}

						} else if ("n".equalsIgnoreCase(yn)) {
							System.out.println(">> 글 삭제를 취소하셨습니다. << \n");
						} else {
							System.out.println(">> [경고] Y 또는 N 만 입력하세요!!");
						}
						// ————————————————————————————————————————————————
					} while (!("y".equalsIgnoreCase(yn) || "n".equalsIgnoreCase(yn)));
					
					
				}
			}

		}
	
	}
	
	//	최근 1주일간 일자별 게시글 작성 건수 		//
	private void statics_by_week() {
		
		System.out.println("\n"+"-".repeat(30)+" [최근 1주일간 일자별 게시글 작성건수] "+"-".repeat(30));
		//만약 오늘이 2024-10-21 이라면
		//------------------------------ [최근 1주일간 일자별 게시글 작성건수 ] ------------------------------ 
		//전체	2024-10-15	2024-10-16	2024-10-17	2024-10-18	2024-10-19	2024-10-20	2024-10-21
		StringBuilder sb = new StringBuilder();
		
		sb.append("전체\t");
		
		for(int i = 0; i<7; i++) {
			sb.append(addDay(i-6) +"  ");		//	-6 -5 -4 -3 -2 -1 0 오늘 부터 6일전을 구하기 위해서 (i-6)을 해줘요 
		}//end of for-------------------
		
		sb.append("\n"+ "-".repeat(91));
		
		System.out.println(sb.toString());// 여기까진 타이틀을 만들기 위한 거에요
		
		
		
		// 4번부터 여기에요
		// 최근 1주일내에 작성된 게시글만 DB에서 가져온 결과물
		Map<String, Integer> resultMap = bdao.statics_by_week();	//select을 해주는데 DTO를 만드는 것이 아니라 Map을 통해서 셀렉트해온 값을 저장해요 ** 기억해야 합니다 **
		//Map은 1개 행으로 보면 된다 🤚필수 암기랍니다.
		
		
		//7번부터 여기에요
		String result = resultMap.get("total") + "\t" +
		resultMap.get("previous6") + "\t" +
		resultMap.get("previous5") + "\t" +
		resultMap.get("previous4") + "\t" +
		resultMap.get("previous3") + "\t" +
		resultMap.get("previous2") + "\t" +
		resultMap.get("previous1") + "\t" +
		resultMap.get("today");
		
	System.out.println(result);
		
	}//end of statics_by_week







	//		현재일로 부터 일수만큼 더하거나 빼주어서 날짜를 리턴시켜주는 메소드		//
	private String addDay(int n) {
		
		Calendar currentDate = Calendar.getInstance();
		//현재 시간을 얻기 위해서 만들었어요 Begin 의 day 11을 참조하면 돼요
		
		currentDate.add(Calendar.DATE, n);
		// currentDate.add(Calendar.DATE, 1);
	    // ==> currentDate(현재날짜) 에서 두번째 파라미터에 입력해준 숫자(그 단위는 첫번째 파라미터인 것이다. 지금은 Calendar.DATE 이므로 날짜수이다) 만큼 더한다. 
	    // ==> 위의 결과는 currentDate 값은 1일 더한 값으로 변한다. 
		
		SimpleDateFormat sdfmt = new SimpleDateFormat("yyyy-MM-dd");
		
		return sdfmt.format(currentDate.getTime());	//캘린더 타입을 Date 타입으로 바꾸고 포맷에 맞추어 리턴해줘요
		
	}// end of addDay(int n)-----------------------------
	
	
	

	//		이번달 일자별 게시글 작성건수 			//
	private void statics_by_currentMonth() {

			/*
			  아래와 같이 출력할 거에요
			  
			  >>> [2024년 10월 일자별 게시글 작성건수] <<<
			  --------------------
			  작성일자			작성건수
			  --------------------
			  2024-10-17	 3
			  2024-10-21	 2
			  전체			 5
			  
			  
			  만약 글이 없으면 이렇게 나올거에요
			  >>> [2024년 10월 일자별 게시글 작성건수] <<<
			  게시된 글이 없습니다.
			 
			 */
		
		//11번은 여기부터
		Calendar currentDate = Calendar.getInstance();
		//현재 시간을 얻기 위해서 만들었어요 Begin 의 day 11을 참조하면 돼요
		
		SimpleDateFormat sdfmt = new SimpleDateFormat("yyyy년 MM월");
		
		String currentMonth = sdfmt.format(currentDate.getTime()); //현재 년도와 월을 저장해요
		
		System.out.println("\n>>> [" + currentMonth + " 일자별 게시글 작성건수] <<<");
		
		List<Map<String, String>> mapList = bdao.statics_by_currentMonth(); //map이 여러개 들어오기 위해서 list로 map을 만들었어요
		
		
	}
	//end of statics_by_currentMonth() ----------------------------
























































































































	
	
	
	
	
}
