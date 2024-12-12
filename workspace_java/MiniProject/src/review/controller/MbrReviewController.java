package review.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import comment.domain.CommentDTO;
import comment.model.CommentDAO;
import comment.model.CommentDAO_imple;
import job_posts.domain.JobpostDTO;
import user.domain.CompanyDTO;
import user.domain.MemberDTO;
import review.domain.ReviewDTO;
import review.model.*;


public class MbrReviewController {
	
	// field
	MbrReviewDAO mrdao = new MbrReviewDAO_imple();
	CommentDAO commentdao = new CommentDAO_imple();

	Scanner sc = new Scanner(System.in);
	
	// method
	// 개인회원 ->  기업후기작성메뉴 
	public void Company_review_memu(Scanner sc, CompanyDTO company, MemberDTO member) {

		
		String menuNo = "";
		
		do {
			System.out.println("            >> 후기관리메뉴 <<");
			System.out.println("-".repeat(40));
			System.out.println("1.작성	2.조회	3.수정	4.삭제	5.뒤로가기");
			System.out.println("-".repeat(40));
			System.out.print("▶ 메뉴번호선택 : ");
			menuNo = sc.nextLine();

				switch (menuNo) {
					case "1":
						reviewWrite(sc, member);	  // 후기작성
						break;
					case "2":
						reviewSearchMenu(sc, company, member);  // 기업후기조회메뉴 1.일반조회 2.상세조회 3.후기높은회사순위로조회 4.돌아가기
						break;
					case "3":
						reviewUpdate(mrdao, member, sc);     // 후기수정하기
						break;
					case "4":	
						reviewDelete(member, sc);		  //후기삭제하기
						break;
					case "5":	// 뒤로가기 
						return;
					default:
						System.out.println(">>> 메뉴에 없는 번호입니다. 다시 선택하세요 !! <<<");
						break;
				}	
		} while(true);	

	} // end of public void Company_review_memu(ReviewDTO review, Scanner sc)


	// 후기작성하기 
	private void reviewWrite(Scanner sc, MemberDTO member) {

		// 지원했던 회사들 보여줌 
		StringBuilder sb = new StringBuilder();
		List<JobpostDTO> applyList = mrdao.viewBoardList(member);

		
		String review_sc = "";
		if(applyList.size() > 0) {
			
			System.out.println("\n== "+ member.getUser_name() +" 님이 지원헀었던 기업목록 ==");
			System.out.println("-".repeat(30));
			System.out.println("회사번호\t회사명");
			System.out.println("-".repeat(30));
			
			for(int i=0; i<applyList.size(); i++) {
				sb.append(applyList.get(i).CompanyListInfo()+"\n"); // 지원했던 회사리스트 

			}
			System.out.println(sb.toString());
			
			
			int review_score = 0;			// 평점  
			String review_contents = "";	// 리뷰내용 
			
			
			do {
				String com_no = "";
				do {
					try {
						System.out.print("▶ 후기 작성할 회사번호를 입력하세요 : ");
						com_no = sc.nextLine();	// 회사번호  
						Integer.parseInt(com_no); 
						
						break;
					} catch(NumberFormatException e) {
						System.out.println(">> [경고] 글번호는 정수만 입력 가능합니다 ! 다시 입력하세요 << \n");
					}
				} while(true);
				
				
				int result = mrdao.searchPostno(member, com_no);
				
				if(result == 1) {
				// 평점입력         
					do {
						System.out.print("▶ 평점 (1 ~ 5) : ");
						review_sc = sc.nextLine();
						try {
							if(review_sc.isEmpty()) {
								System.out.println(">> [경고] 평점은 필수로 입력하셔야 합니다 ! << \n");
							} else {
								review_score = Integer.parseInt(review_sc);
								if( (review_score >= 1) && (review_score <= 5)) {
									break;
								} else {
									System.out.println(">> [경고] 평점은 1점부터 5점까지 가능합니다 ! << \n");
								}
							}
						} catch(NumberFormatException e) {
							System.out.println(">> [경고] 평점은 정수로만 입력하셔야 합니다 !! << \n");
						}
					} while(true);
					
					
					// 후기입력    
					do {
						System.out.print("▶ 후기 (200자 이내로 입력하세요!) : ");
						review_contents = sc.nextLine();
						
						if(review_contents.isBlank()) {
							System.out.println(">> [경고] 후기내용은 필수로 입력하셔야 합니다 ! << \n");
						} else if(review_contents.length() > 200) {
							System.out.println(">> [경고] 후기내용은 최대 200글자 이내로 입력해야 합니다 ! << \n");
						} else { 
							break;
						}
					} while(true);
		
					// 입력 확인하기    
					do {
						System.out.print("\n정말로 작성하겠습니까? [Y/N] => ");
						String yn = sc.nextLine();
						
						if("y".equalsIgnoreCase(yn)) {
							
							int comno = Integer.parseInt(com_no); 

							Map<String, String> paraMap = new HashMap<>(); 

							paraMap.put("review_sc", review_sc);
							paraMap.put("review_contents", review_contents);


							int write = mrdao.reviewWrite(paraMap, member, comno);
							
							if(write ==1) {
								System.out.println(">> 후기작성이 완료되었습니다 ! << \n ");
								break;
							}
							else {
								System.out.println(">> 후기작성을 실패했습니다... << \n");
							}
						} else if("n".equalsIgnoreCase(yn)) {
							System.out.println(">> 후기작성이 취소되었습니다 ! << \n");
							break;
						} else {
							System.out.println(">> [경고] Y 또는 N 만 입력하세요. << ");
						}
					} while(true);
					break;
				} else {
					System.out.println(">> 존재하지 않는 회사번호 입니다 ! 다시 입력하세요... << \n ");
				}
				
			} while(true);
		} else { // 지원한 회사 없음 
			System.out.println(">> 지원한 회사가 없습니다 .. 기업 후기 작성이 불가능 합니다 ! << \n ");
		}
	
	}
	
	// 2. 메뉴 
	////////////////////////////////////////////////////////////////////////////////////////	



	// 후기 조회하기 ( 새로운 메뉴 생성 )
	private void reviewSearchMenu(Scanner sc, CompanyDTO company, MemberDTO member) {
		
		String menuNo = "";
		do {
			System.out.println("\n>> 기업후기조회메뉴 <<");
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("1.회사별후기전체조회  2.후기내용상세조회  3.평점조건조회  4.내가작성한후기조회  5.뒤로가기");
			System.out.println("--------------------------------------------------------------------------------");
			System.out.print("▶ 메뉴번호선택 : ");
			menuNo = sc.nextLine();
			
				switch (menuNo) {
					case "1":	// 후기일반조회(전체조회)
						searchReviewList(member,  sc);
						break;
					case "2":	// 후기상세조회   
						detailSearchReview(member, sc, mrdao);
						break;
					case "3":	// 후기높은회사부터조회    
						rankSearchReview();    
						break;
					case "4":	// 내가작성한후기조회  
						myReviewSearch(member, mrdao);    
						break;
					case "5":	// 돌아가기   
						return;
					default:
						System.out.println(">>> 메뉴에 없는 번호입니다. 다시 선택하세요 !! <<<");
						break;
				}	
		} while(true);	

	}
	
	// 단순 회사목록 보여주는 메소드 
	private List<CompanyDTO> viewCompanyList() {

		StringBuilder sb = new StringBuilder();
		List<String> comList = mrdao.viewCompanyList();
		
		System.out.println("\n>> 기업 List <<");
		System.out.println("-".repeat(25));
		System.out.println("기업번호	기업명	후기수");
		System.out.println("-".repeat(25));
		
		for(int i=0; i<comList.size(); i++) {
			sb.append(comList.get(i)+"\n");
		}
		System.out.println(sb.toString());

		return null;
	}
	
	
	// 1. 후기전체조회 (회사별)
	private void searchReviewList(MemberDTO member,  Scanner sc) {

		StringBuilder sb = new StringBuilder();
		String com_no = "";
		
		viewCompanyList();  // (회사번호 + 회사이름) 
		
		do {
			do {
				try {
					System.out.print("▶ 후기 열람할 회사번호를 입력하세요 ! :  ");
					com_no = sc.nextLine();	
					Integer.parseInt(com_no); 
					
					break;
				} catch(NumberFormatException e) {
					System.out.println(">> [경고] 글번호는 정수만 입력 가능합니다 ! 다시 입력하세요 << \n");
				}
			} while(true);
			
				if(com_no.isEmpty()) {
					System.out.println(">> [경고] 회사번호는 필수로 입력하셔야 합니다 ! <<\n");
					continue;
				} else {	// 값을 입력하긴했음 
					List<ReviewDTO> AllreviewList = mrdao.AllreviewList(com_no);
					boolean is_exist = mrdao.compareNo(com_no);
					
					if(is_exist) {
						
						if( AllreviewList.size() > 0 ) {
							
							System.out.println(">> 작성된 후기 리스트 <<");
							System.out.println("-".repeat(100));
							System.out.println("후기번호\t기업명\t조회수\t평점\t작성일\t\t 후기내용");
							System.out.println("-".repeat(100));
							
								for(int i=0; i<AllreviewList.size(); i++) {
									sb.append(AllreviewList.get(i).reviewInfo()+"\n");
								}
								System.out.println(sb.toString());

						} else {
							System.out.println(">> 작성되어진 후기가 없습니다 ㅜ ㅜ << \n");
						}
					} else {
						System.out.println(">> " + com_no + " 는(은) 존재하지 않는 회사번호 입니다 ㅜㅜ 다시 입력하세요 ! << \n");
						continue;
					}
				}
			break;
		} while(true);

	}
	

	// 2.후기상세조회  (1개만 조회) 
	private void detailSearchReview(MemberDTO member, Scanner sc, MbrReviewDAO mrdao) {		

		do {
			String review_no = "";
			do { 
				try {
					System.out.print("▶ 자세하게 볼 후기 번호를 입력하세요! : ");
					review_no = sc.nextLine();
					Integer.parseInt(review_no);
					
					break; 
				} catch(NumberFormatException e) {
					System.out.println(">> [경고] 후기번호는 정수만 입력하세요 ! << \n");
				}
			} while(true);

			Map<String, String> paraMap = new HashMap<>();
			paraMap.put("review_no", review_no);
			paraMap.put("login_userid", member.getUser_id());
			
			ReviewDTO review = mrdao.detailViewReview(paraMap);	// 후기상세보기  
			
			if(review != null) {
				String name = review.getMember().getUser_name();
				String user_name = name.substring(0,1) + "*"+name.substring(2);

				System.out.println("-".repeat(30));
				System.out.println("[후기번호] " + review.getReview_no() + "\n"
						+ "[작성자명] " + user_name + "\n"
						+ "[후기내용] " + review.getReview_contents() + "\n"
						+ "[평점] " + review.getReview_score() + "\n"
						+ "[작성일] " + review.getReview_regidate() + "\n"
						+ "[조회수] " + review.getViewcount() );
				System.out.println("-".repeat(30)+"\n");
				
				///////////////////////////////////////////////////////////////////////////////////////
				// 댓글 보여줌 ... 
				System.out.println("-".repeat(30) + " [기업답변] " + "-".repeat(30));
				
				List <CommentDTO> commentList = commentdao.commentList(review_no);
				// 리뷰 번호를 넘겨 일치하는 리뷰 번호의 댓글목록을 가져옴 ... 
				
				if(commentList.size() > 0) {
				// 원글에 대한 댓글이 존재하는 경우
				StringBuilder sb = new StringBuilder();
				
				System.out.println("기업명\t작성일\t\t답변내용");
				System.out.println("-".repeat(70));
				
				for(CommentDTO commentdto : commentList) {
				sb.append(commentdto.getCompany().getCom_name() + "\t"
				+ commentdto.getComment_regidate() + "\t"
				+ commentdto.getComment_contents()+"\n");
				
				} // end of for
				
				System.out.println(sb.toString());
				}
				else {
				// 원글에 대한 댓글이 존재하지 않는 경우
				System.out.println(">> 댓글 내용 없음 << \n");
				}

				break;
			} 
			else {
				System.out.println(">> " + review_no + " 은(는) 존재하지 않는 후기번호 입니다 다시 입력하세요! << \n");
			}
		} while(true);
	}

	// 3.순위별 후기조회 (1.원하는평점이상만조회 2.평점높은순(내림차순) 3.평점낮은순(오름차순) 
	private void rankSearchReview() {
		
		StringBuilder sb = new StringBuilder();
	
		List<String> avgList = mrdao.avgCom();	// 회사별 평점평균 한행으로 출력
		
		System.out.println("\n" + "=".repeat(5)+ " <회사별평점평균> " + "=".repeat(5));
		for(int i=0; i<avgList.size(); i++) {
			sb.append(avgList.get(i));
		}
		System.out.println(sb.toString());
		
		rankSearchMenu(sc); // 다음메뉴 출력 
	}

	
	// 3. 평점높은회사순위별조회메뉴 
	private void rankSearchMenu(Scanner sc) {
		
		String menuNo = "";
		do {
			System.out.println(">> 평점조건조회 <<");
			System.out.println("--------------------------------------------------------------");
			System.out.println("1.원하는평점후기조회  2.평점높은순으로조회  3.평점낮은순으로조회  4.뒤로가기");
			System.out.println("--------------------------------------------------------------");
			System.out.print("▶ 메뉴번호선택 : ");
			menuNo = sc.nextLine();
			
				switch (menuNo) {
					case "1":	// 원하는평점이상의회사조회 
						hopeSearch();
						break;
					case "2":	// 평점높은순으로전체조회 (내림차순)
						descSearch();
						break;
					case "3":	// 후기높은회사부터조회  (오름차순) 
						ascSearch();  
						break;
					case "4":	// 돌아가기   
						return;
					default:
						System.out.println(">> 메뉴에 없는 번호입니다. 다시 선택하세요 !! << \n");
						break;
				}	
		} while(true);	

	}
	
	// 1.희망하는 평점이상의 리뷰조회   
	private void hopeSearch() {
		
		StringBuilder sb = new StringBuilder();

		do {
			System.out.print("\n▶ 몇점이상의 평점 후기를 조회하시겠습니까? (1 ~ 5점 입력가능) : ");
			String reviewno = sc.nextLine();	// 몇점이상인가 

			
			if(reviewno.isEmpty()) {
				System.out.println(">> [경고] 필수로 입력하셔야 합니다 ! <<\n");
				continue;
			} else {
				try {
					if(Integer.parseInt(reviewno) >= 1 && Integer.parseInt(reviewno) <= 5) {	// 1점이상 5점이하   
						List<String> hopeList = mrdao.hopeList(reviewno);	// 원하는 조건의 후기조회 
						
						if(hopeList.size() > 0) {
							sb = new StringBuilder();
							
							System.out.println(">> "+ reviewno + "점 이상 후기 리스트 <<");
							System.out.println("-".repeat(100));
							System.out.println("순위\t후기번호\t회사명\t조회수\t평점\t작성일\t\t후기내용");
							System.out.println("-".repeat(100));
							
							for(int i=0; i<hopeList.size(); i++) {
								sb.append(hopeList.get(i));
							}
							
							System.out.println(sb.toString());
						
							break;
						} else {
							System.out.println(">> 조건에 일치하는 등록된 후기가 없습니다... << \n");
						}
				} else {
					System.out.println(">> 1점 부터 5점 사이의 평점만 조회 가능합니다 ! 다시 입력하세요... <<");
				} 
				} catch(NumberFormatException e ) {
					System.out.println(">> 정수만 입력 가능합니다 ! 다시 입력하세요... <<");
				}
			} 
		} while(true);
	}

	
	// 후기 내림차순 조회 (높은순부터) 
	private void descSearch() {
		StringBuilder sb = new StringBuilder();
		
		List<String> descrankList = mrdao.descRankList();	// 랭크리스트   
		
		if(descrankList.size() > 0) {
			
			sb = new StringBuilder();
			
			System.out.println("\n>> 높은 평점 순 후기 리스트 <<");
			System.out.println("-".repeat(100));
			System.out.println("순위\t후기번호\t회사명\t조회수\t평점\t작성일\t\t후기내용");
			System.out.println("-".repeat(100));
			
			for(int i=0; i<descrankList.size(); i++) {
				sb.append(descrankList.get(i));
			}
			
			System.out.println(sb.toString());
			
		} else {
			System.out.println(">> 후기가 없습니다... <<");
		}
		
	}
	
	// 후기 오름차순 조회 (낮은순부터) 
		private void ascSearch() {
			StringBuilder sb = new StringBuilder();
			
			List<String> ascrankList = mrdao.ascRankList();	// 랭크리스트   
			
			if(ascrankList.size() > 0) {
				
				sb = new StringBuilder();
				
				System.out.println(">> 낮은 평점순 후기 리스트 <<");
				System.out.println("-".repeat(100));
				System.out.println("순위\t후기번호\t회사명\t조회수\t평점\t작성일\t\t후기내용");
				System.out.println("-".repeat(100));
				
				for(int i=0; i<ascrankList.size(); i++) {
					sb.append(ascrankList.get(i));
				}
				
				System.out.println(sb.toString());
				
			} else {
				System.out.println(">> 후기가 없습니다... <<");
			}
			
		}
	
	// 4.내가작성한후기조회  
	private void myReviewSearch(MemberDTO member, MbrReviewDAO mrdao) {
		
		
		StringBuilder sb = new StringBuilder();
		List<ReviewDTO> MyreviewList = mrdao.MyreviewList(member);
	
		if( MyreviewList.size() > 0 ) {
			
			System.out.println("\n"+"-".repeat(20) + ">> " + member.getUser_name() + " 작성된 후기 리스트 <<" + "-".repeat(20));
			System.out.println("후기번호\t기업명\t조회수\t평점\t작성일\t\t 후기내용");
			System.out.println("-".repeat(65));
			
				for(int i=0; i<MyreviewList.size(); i++) {
					sb.append(MyreviewList.get(i).reviewInfo()+"\n");
				}
				 System.out.println(sb.toString());
		} else  {
			System.out.println(">> " + member.getUser_name() + " 님은 작성한 후기가 없습니다 ㅜ ㅜ << ");
		}

	}
	
	
	////////////////////////////////////////////////////////////////////////////////////////

	// 3.후기 수정하기 
	private void reviewUpdate(MbrReviewDAO mrdao, MemberDTO member, Scanner sc) {
		
		StringBuilder sb = new StringBuilder();
		List<ReviewDTO> MyreviewList = mrdao.MyreviewList(member);
	
		if( MyreviewList.size() > 0 ) {
			
			System.out.println("\n"+"-".repeat(21) + ">> 수정가능한 후기 리스트 <<" + "-".repeat(21));
			System.out.println("후기번호\t기업명\t조회수\t평점\t작성일\t\t 후기내용");
			System.out.println("-".repeat(65));
			
			for(int i=0; i<MyreviewList.size(); i++) {
				sb.append(MyreviewList.get(i).reviewInfo()+"\n");
			}
			System.out.println(sb.toString());
				
			System.out.println("\n >>> 글수정하기 <<<");
			
			outer :
			do {			
				String review_no = "";
				String review_score = "";
				do {
					
					try {
						System.out.print("▶ 수정할 글번호 : ");
						review_no = sc.nextLine();
						Integer.parseInt(review_no);

						break;
					} catch(NumberFormatException e) {
						System.out.println(">> [경고] 글번호는 정수만 입력 가능합니다 ! << \n");
					}
				} while(true);
				
				Map<String, String> paraMap = new HashMap<>();
				paraMap.put("review_no", review_no);
				
				// 수정할 후기 보여주기
				ReviewDTO review = null;
				review = mrdao.ViewReview(paraMap);
					
				if(review != null) {

						if(member.getUser_no() != review.getMember().getUser_no() )  {
							// 삭제할 글번호가 다른 사용자가 쓴 글인 경우라면 
				            System.out.println(">> [경고] 다른 사용자의 글은 수정 불가합니다 << \n");
						} else {
								// 수정할 글번호가 자신이 쓴 글인 경우라면

							System.out.println("[후기번호] " + review.getReview_no() + "\n"
									 + "[작성자명] " + review.getMember().getUser_name() + "\n"
									 + "[후기내용] " + review.getReview_contents() + "\n"
									 + "[평점] " + review.getReview_score() + "\n"
									 + "[작성일] " + review.getReview_regidate() );

							do {
								System.out.print("▶ 비밀번호 확인 : ");
								String boardpasswd = sc.nextLine();
								
								if(! boardpasswd.equals(member.getUser_passwd()) ) {
									// 글암호가 일치하지 않는 경우
									System.out.println(">> [경고] 올바른 암호가 아닙니다... 다시 입력하세요! << \n");
									continue;
								}
								
								else {
								   // 글암호가 일치하는 경우
						               
					               System.out.println("--------------------------------------"); 
					               System.out.println("[수정전 평점] " + review.getReview_score());
					               System.out.println("[수정전 후기내용] " + review.getReview_contents() );
					               System.out.println("--------------------------------------"); 
					               do {
						               System.out.print("▶ 평점[1~5 입력 , 변경하지 않으려면 엔터] : "); 
						               review_score = sc.nextLine();
									   try {
										if(review_score.isBlank()) {	// 엔터시 원본입력 
							            	   review_score = Integer.toString(review.getReview_score());
							            	   break;
							               } else {
												int review_sc= Integer.parseInt(review_score);
												if( (review_sc >= 1) && (review_sc <= 5)) {
													break;
												} else {
													System.out.println("[경고] 평점은 1점부터 5점까지 가능합니다 ! \n");
												}
							               }
										} catch(NumberFormatException e) {
											System.out.println(">> [경고] 평점은 정수로만 입력하셔야 합니다 !! << \n");
										}
					               } while(true);
					               
					               //-- end of if --------------------------------------
					               
					               System.out.print("▶ 후기내용 [최대 200글자, 변경하지 않으려면 엔터] : ");
					               String review_contents = sc.nextLine();
					               if(review_contents.isBlank()) {
					            	   review_contents = review.getReview_contents();
					               }//-- end of if --------------------------------------
					               
					               if(review_score.length() > 5 || review_contents.length() > 200 ) {
					            	   System.out.println(">> [경고] 평점은 1부터 5 사이만 입력 가능하고, 후기내용은 200자 이내로 작성 가능합니다! << \n");
					               }
					               else {
					            	   String yn = "";
					            	   do {
					            		   System.out.print("▶ 정말로 글수정 하시겠습니까?[Y/N] : ");
						            	   yn = sc.nextLine();
						            	   
						            	   if("y".equalsIgnoreCase(yn)) {
						            		   
						            		   Map<String, String> paraMap2 = new HashMap<>();
						            		   paraMap2.put("review_score", review_score);
						            		   paraMap2.put("review_contents", review_contents);
						            		   paraMap2.put("review_no", review_no);
				
						            		   int n = mrdao.updateReview(paraMap2);	// 글수정하기
						            		   
						            		   if(n==1) {
						            			   System.out.println(">> 후기 수정 성공 !! << \n");
						            			   break outer; 
						            		   } else {
						            			   System.out.println(">> sql 구문 오류 발생으로 인해 글수정이 실패되었습니다. << \n");
						            		   }
						            		
						            		   
						            	   } else if("n".equalsIgnoreCase(yn)) {
						                        System.out.println(">> 후기 수정을 취소하셨습니다. << \n");
						                        break outer;
						                   } else {
						                        System.out.println(">> [경고] Y 또는 N 만 입력하세요!! << \n");
						                   }
					            	   } while("y".equalsIgnoreCase(yn) || "n".equalsIgnoreCase(yn));
					           
					               } //-- end of if --------------------------------------
				  
								}//-- end of else if --------------------------------------
						}while(true);
						} 
				} else {
					System.out.println(">> " + review_no + " 은(는) 존재하지 않는 후기번호 입니다. 다시 입력하세요! << \n");
				}

		} while(true);
		} else  {
			System.out.println(">> " + member.getUser_name() + " 님은 작성한 후기가 없습니다 ㅜ ㅜ << \n");
		}
	} // private void reviewUpdate(MemberDTO member, CompanyDTO company, Scanner sc)


	
	// 후기삭제
	private void reviewDelete(MemberDTO member, Scanner sc) {
		
		StringBuilder sb = new StringBuilder();
		List<ReviewDTO> MyreviewList = mrdao.MyreviewList(member);
	
		if( MyreviewList.size() > 0 ) {
			
			System.out.println("\n"+"-".repeat(21) + ">> 삭제가능한 후기 리스트 <<" + "-".repeat(21));
			System.out.println("후기번호\t기업명\t조회수\t평점\t작성일\t\t 후기내용");
			System.out.println("-".repeat(65));
			
			for(int i=0; i<MyreviewList.size(); i++) {
				sb.append(MyreviewList.get(i).reviewInfo()+"\n");
			}
			System.out.println(sb.toString());
				
			System.out.println("\n >>> 글삭제하기 <<<");
			
			
			outer :
			do {
				String review_no = "";
				do {
					
					try {
						System.out.print("▶ 삭제할 글번호 : ");
						review_no = sc.nextLine();
						Integer.parseInt(review_no);
						
						break;
					} catch(NumberFormatException e) {
						System.out.println(">> [경고] 글번호는 정수만 입력 가능합니다 ! << \n");
					}
				} while(true);
				
				Map<String, String> paraMap = new HashMap<>();
				paraMap.put("review_no", review_no);
				
				// 삭제할 후기 보여주기
				ReviewDTO review = null;
				review = mrdao.ViewReview(paraMap);
					
				if(review != null) {
						
						if(member.getUser_no() != review.getMember().getUser_no() )  {
							// 삭제할 글번호가 다른 사용자가 쓴 글인 경우라면 
				            System.out.println(">> [경고] 다른 사용자의 글은 삭제 불가합니다 << \n");
						}  else {
							
							System.out.println("[후기번호] " + review.getReview_no() + "\n"
									 + "[작성자명] " + review.getMember().getUser_name() + "\n"
									 + "[후기내용] " + review.getReview_contents() + "\n"
									 + "[평점] " + review.getReview_score() + "\n"
									 + "[작성일] " + review.getReview_regidate() );
					
									// 수정할 글번호가 자신이 쓴 글인 경우라면
								do {
									System.out.print("▷ 비밀번호 확인 : ");
									String boardpasswd = sc.nextLine();
									
									if(! boardpasswd.equals(member.getUser_passwd()) ) {
										// 글암호가 일치하지 않는 경우
										System.out.println(">> [경고] 올바른 암호가 아닙니다... 다시 입력하세요! << \n");
										continue;
									}
									
									else {
									   // 글암호가 일치하는 경우
							               
						               System.out.println("--------------------------------------"); 
						               System.out.println("[평점] " + review.getReview_score());
						               System.out.println("[후기내용] " + review.getReview_contents() );
						               System.out.println("--------------------------------------"); 
	
					            	   String yn = "";
					            	   do {
					            		   System.out.print("▶ 정말로 글삭제 하시겠습니까?[Y/N] : ");
						            	   yn = sc.nextLine();
						            	   
						            	   if("y".equalsIgnoreCase(yn)) {
						            		   
						            		   int n = mrdao.deleteBoard(review_no); //  글삭제하기 ( status 0 으로 변경 )
	
						            		   if(n==1) {
						            			   System.out.println(">> 후기삭제 성공 !! << \n");
						            			   break outer;
						            		   } else {
						            			   System.out.println(">> sql 구문 오류 발생으로 인해 글삭제가 실패되었습니다. << \n");
						            		   }
	
						            	   } else if("n".equalsIgnoreCase(yn)) {
						                        System.out.println(">> 후기 삭제를 취소하셨습니다. << \n");
						                        break outer;
						                   } else {
						                        System.out.println(">> [경고] Y 또는 N 만 입력하세요!! << \n");
						                   }
					            	   } while("y".equalsIgnoreCase(yn) || "n".equalsIgnoreCase(yn));
						           
						              
					  
									}//-- end of else if --------------------------------------
							} while(true);
							} // end of else if 
					} else {
						System.out.println(">> " + review_no + " 은(는) 존재하지 않는 후기번호 입니다 다시 입력하세요! <<\n ");
					}
		} while(true);
		} else  {
			System.out.println(">> " + member.getUser_name() + " 님은 작성한 후기가 없습니다 ㅜ ㅜ << \n");
		}
	}

} // end of public class MbrReviewController
