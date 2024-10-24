package review.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import job_posts.domain.JobpostDTO;
import user.domain.CompanyDTO;
import user.domain.MemberDTO;
import resume.domain.ResumeDTO;
import review.domain.ReviewDTO;
import review.model.*;
import common.ProjectDBConnection;


public class MbrReviewController {
	
	// field
	MbrReviewDAO mrdao = new MbrReviewDAO_imple();
	Scanner sc = new Scanner(System.in);
	
	// method
	// 개인회원 ->  기업후기작성메뉴 
	public void Company_review_memu(Scanner sc, CompanyDTO company ,MemberDTO member, JobpostDTO jobpost) {

		
		String menuNo = "";
		
		do {
			System.out.println("            >> 기업후기메뉴 <<");
			System.out.println("-".repeat(40));
			System.out.println("1.작성	2.조회	3.수정	4.삭제	5.뒤로가기");
			System.out.println("-".repeat(40));
			System.out.print("> 메뉴번호선택 : ");
			menuNo = sc.nextLine();

				switch (menuNo) {
					case "1":
						//reviewWrite(sc, member, jobpost);	  // 후기작성
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
						ProjectDBConnection.closeConnection(); 
						return;
					default:
						System.out.println(">>> 메뉴에 없는 번호입니다. 다시 선택하세요 !! <<<");
						break;
				}	
		} while(true);	

	} // end of public void Company_review_memu(ReviewDTO review, Scanner sc)


	
	// 내가 지원했던 기업들 목록 보여주는 메소드
	private String viewBoardList(MemberDTO member) {
					
		String result = "";
		
		StringBuilder sb = new StringBuilder();
		List<JobpostDTO> applyList = mrdao.viewBoardList(member);
		
		if(applyList.size() > 0) {
			
			System.out.println("== "+ member.getUser_name() +" 님이 지원한 기업 List =="); 
			System.out.println("-".repeat(50));
			System.out.println("기업번호\t\t이름\t\t지원일");
			System.out.println("-".repeat(50));
			
			for(int i=0; i<applyList.size(); i++) {
				sb.append(applyList.get(i).CompanyListInfo()+"\n"); // 지원했던 회사리스트 
			}
			
			result = sb.toString();

		}
		return result;
		
	}
	
	// 후기작성하기 아직 미완성,,,,,,,입니다. 
//	private int reviewWrite(Scanner sc, MemberDTO member, JobpostDTO jobpost) {
//		
//		String viewBoardList = viewBoardList(member); // 내가 지원했던 회사 보여줌 
//		
//		int result = 0;
//		
//		if(! viewBoardList.isEmpty()) {	// 지원했던 회사가 존재할 시 
//			
//			if()	// 
//			System.out.println(viewBoardList);
//			
//			int review_score = 0;			// 평점  
//			String review_contents = "";	// 리뷰내용 
//			
//			System.out.print("▷ 후기 작성할 기업번호를 입력하세요 : ");
//			int comNo = sc.nextInt();	// 기업번호    
//			  
//			// 평점입력         
//			do {
//				System.out.print("평점 (1 ~ 5) : ");
//				String review_sc = sc.nextLine();
//				try {
//					review_score = Integer.parseInt(review_sc);
//					
//					if(! (review_score < 1) && (review_score >= 5)) {
//						System.out.println("\n[경고] 평점은 1점부터 5점까지 가능합니다 ! ");
//					} else {
//						break;
//					}
//				} catch(NumberFormatException e) {
//					System.out.println("\n >> [경고] 평점은 정수로만 입력하셔야 합니다 !! << ");
//				}
//			} while(true);
//			
//			
//			// 후기입력    
//			do {
//				System.out.print("후기 (200자 이내로 입력하세요!) : ");
//				review_contents = sc.nextLine();
//				
//				if(review_contents.isBlank()) {
//					System.out.println("\n [경고] 후기내용은 필수로 입력하셔야 합니다 ! ");
//				} else if(review_contents.length() > 200) {
//					System.out.println("\n [경고]후기내용은 최대 200글자 이내로 입력해야 합니다 !");
//				} else { 
//					break;
//				}
//			} while(true);
//
//			// 입력 확인하기    
//			do {
//				System.out.print("\n정말로 작성하겠습니까? [Y/N] => ");
//				String yn = sc.nextLine();
//				
//				if("y".equalsIgnoreCase(yn)) {
//
//					ReviewDTO review = new ReviewDTO();
//					review.setReview_score(review_score);
//					review.setReview_contents(review_contents);
//					
//					result = mrdao.reviewWrite(comNo, review);
//					
//					System.out.println("후기작성이 완료되었습니다 ! ");
//					break;
//				} else if("n".equalsIgnoreCase(yn)) {
//					System.out.println("후기작성이 취소되었습니다 ! \n");
//					break;
//				} else {
//					System.out.println("[경고] Y 또는 N 만 입력하세요.");
//				}
//			} while(true);
//
//		} else { // 지원한 회사 없음 
//			System.out.println("지원한 회사가 없습니다 ..\n ");
//		}
//		
//		return result;
//	}
//	
	// 2. 메뉴 
	////////////////////////////////////////////////////////////////////////////////////////	
	
	
	// 후기 조회하기 ( 새로운 메뉴 생성 )
	private void reviewSearchMenu(Scanner sc, CompanyDTO company, MemberDTO member) {
		
		String menuNo = "";
		do {
			System.out.println("\n>> 기업후기조회메뉴 <<");
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("1.일반조회  2.상세조회  3.평점 높은 회사 순위별 조회  4.내가작성한후기조회  5.뒤로가기");
			System.out.println("-------------------------------------------------------------------------");
			System.out.print("> 메뉴번호선택 : ");
			menuNo = sc.nextLine();
			
				switch (menuNo) {
					case "1":	// 후기일반조회(전체조회)
						searchReviewList(member, company, sc);
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
						ProjectDBConnection.closeConnection(); 
						return;
					default:
						System.out.println(">>> 메뉴에 없는 번호입니다. 다시 선택하세요 !! <<<");
						break;
				}	
		} while(true);	

	}
	
	
	
	// 1. 후기전체조회 (회사별)
	private void searchReviewList(MemberDTO member, CompanyDTO company, Scanner sc) {

		StringBuilder sb = new StringBuilder();

		System.out.println(">> 후기 열람할 회사번호를 입력하세요 ! << ");
		int com_no = sc.nextInt();
		
		List<ReviewDTO> AllreviewList = mrdao.AllreviewList(com_no);
		
		int result = mrdao.searchComNo(com_no);
		
		if(result == 1) {

			if( AllreviewList.size() > 0 ) {
				
				System.out.println(" >> 작성된 후기 리스트 <<");
				System.out.println("-".repeat(50));
				System.out.println("후기번호	기업명	후기내용		평점		작성일");
				System.out.println("-".repeat(50));
				
					for(int i=0; i<AllreviewList.size(); i++) {
						sb.append(AllreviewList.get(i).reviewInfo()+"\n");
					}
					System.out.println(sb.toString());
	
			} else  {
				System.out.println( company.getCom_name() +  " 기업은 작성되어진 후기가 없습니다 ㅜ ㅜ ");
			}
		}
		else {
			System.out.println( com_no + "존재하지 않는 회사번호 입니다 ㅜㅜ ");
		}
		
		
	}
	
	// 2.후기상세조회  (1개만 조회) 
	private void detailSearchReview(MemberDTO member, Scanner sc, MbrReviewDAO mrdao) {		
		
		System.out.println(">> 자세하게 볼 후기 번호를 입력하세요 ! << ");
		String review_no = sc.nextLine();
		
		Map<String, String> paraMap = new HashMap<>();
		paraMap.put("review_no", review_no);
		
		ReviewDTO review = mrdao.detailViewReview(paraMap);
		
		if(review != null) {
			
			System.out.println("[후기번호] " + review.getReview_no() + "\n"
					+ "[작성자명] " + review.getMember().getUser_name() + "\n"
					+ "[후기내용] " + review.getReview_contents() + "\n"
					+ "[평점] " + review.getReview_score() + "\n"
					+ "[작성일] " + review.getReview_regidate() );
		}
		else {
			System.out.println(review_no + " 은(는) 존재하지 않는 후기번호 입니다 ");
		}
		
	}

	// 3.순위별 후기조회 
	private void rankSearchReview() {
	
		List<String> rankList = mrdao.rankList();	// 랭크리스트   
		
		System.out.println(">> 순위별 후기 조회하기 ! <<");
		
		if(rankList.size() > 0) {
			StringBuilder sb = new StringBuilder();
			
			System.out.println(" >> 평점순 후기 리스트 <<");
			System.out.println("-".repeat(50));
			System.out.println("순위  후기번호 	 회사명	평점		작성일");
			System.out.println("-".repeat(50));
			
			for(int i=0; i<rankList.size(); i++) {
				sb.append(rankList.get(i));
			}
			
			System.out.println(sb.toString());
			
		} else {
			System.out.println("후기가 없습니다... ");
		}
		
	}

	// 4.내가작성한후기조회  
	private void myReviewSearch(MemberDTO member, MbrReviewDAO mrdao) {
		
		StringBuilder sb = new StringBuilder();
		List<ReviewDTO> MyreviewList = mrdao.MyreviewList(member);
	
		if( MyreviewList.size() > 0 ) {
			
			System.out.println(">> " + member.getUser_name() + " 작성된 후기 리스트 <<");
			System.out.println("-".repeat(50));
			System.out.println("후기번호	기업명	후기내용		평점		작성일");
			System.out.println("-".repeat(50));
			
				for(int i=0; i<MyreviewList.size(); i++) {
					sb.append(MyreviewList.get(i).reviewInfo()+"\n");
				}
				System.out.println(sb.toString());
				 
		} else  {
			System.out.println( member.getUser_name() + " 님은 작성한 후기가 없습니다 ㅜ ㅜ ");
		}
	}
	
	

	////////////////////////////////////////////////////////////////////////////////////////

	// 3.후기 수정하기 
	private void reviewUpdate(MbrReviewDAO mrdao, MemberDTO member, Scanner sc) {
		
		System.out.println("\n >>> 글수정하기 <<<");
				
		System.out.print("▷ 수정할 글번호 : ");
		String review_no = sc.nextLine();
		
		Map<String, String> paraMap = new HashMap<>();
		paraMap.put("review_no", review_no);
		
		// 삭제할 후기 보여주기
		ReviewDTO review = null;
		review = mrdao.detailViewReview(paraMap);
		
		if(review != null) {
			System.out.println("[후기번호] " + review.getReview_no() + "\n"
							 + "[작성자명] " + review.getMember().getUser_name() + "\n"
							 + "[후기내용] " + review.getReview_contents() + "\n"
							 + "[평점] " + review.getReview_score() + "\n"
							 + "[작성일] " + review.getReview_regidate() );
			
			if(member.getUser_no() != review.getMember().getUser_no() )  {
				// 삭제할 글번호가 다른 사용자가 쓴 글인 경우라면 
	            System.out.println(">> [경고] 다른 사용자의 글은 수정 불가합니다 << \n");
			} else {
					// 수정할 글번호가 자신이 쓴 글인 경우라면
					System.out.print("▷ 비밀번호 확인 : ");
					String boardpasswd = sc.nextLine();
					
					if(! boardpasswd.equals(member.getUser_passwd()) ) {
						// 글암호가 일치하지 않는 경우
						System.out.println("[경고] 입력하신 글암호가 작성시 입력한 글암호와 일치하지 않으므로 수정 불가합니다.!! \n");
					}
					else {
					   // 글암호가 일치하는 경우
			               
		               System.out.println("--------------------------------------"); 
		               System.out.println("[수정전 평점] " + review.getReview_score());
		               System.out.println("[수정전 후기내용] " + review.getReview_contents() );
		               System.out.println("--------------------------------------"); 
		               
		               System.out.print("▷ 평점[1~5 입력 , 변경하지 않으려면 그냥 엔터] : "); 
		               String review_score = sc.nextLine();
		               if(review_score.isBlank()) {
		            	   review_score = Integer.toString(review.getReview_score());
		               }
		               
		               System.out.print("▷ 후기내용 [최대 200글자, 변경하지 않으려면 그냥 엔터] : ");
		               String review_contents = sc.nextLine();
		               if(review_contents.isBlank()) {
		            	   review_contents = review.getReview_contents();
		               }
		               
		               if(review_score.length() > 5 || review_contents.length() > 200 ) {
		            	   System.out.println("[경고] 평점은 1부터 5 사이만 입력 가능하고, 후기내용은 200자 이내로 작성 가능합니! \n");
		               }
		               else {
		            	   String yn = "";
		            	   do {
		            		   System.out.print("▷ 정말로 글수정 하시겠습니까?[Y/N] : ");
			            	   yn = sc.nextLine();
			            	   
			            	   if("y".equalsIgnoreCase(yn)) {
			            		   
			            		   Map<String, String> paraMap2 = new HashMap<>();
			            		   paraMap2.put("review_score", review_score);
			            		   paraMap2.put("review_contents", review_contents);
			            		   paraMap2.put("review_no", review_no);
	
			            		   int n = mrdao.updateReview(paraMap2);	// 글수정하기
			            		   
			            		   if(n==1) {
			            			   System.out.println(">> 후기 수정 성공 !! << \n");
			            			   break;
			            		   } else {
			            			   System.out.println(">> sql 구문 오류 발생으로 인해 글수정이 실패되었습니다. << \n");
			            		   }
			            		
			            		   
			            	   } else if("n".equalsIgnoreCase(yn)) {
			                        System.out.println(">> 후기 수정을 취소하셨습니다. << \n");
			                   } else {
			                        System.out.println(">> [경고] Y 또는 N 만 입력하세요!!");
			                   }
		            	   } while("y".equalsIgnoreCase(yn) || "n".equalsIgnoreCase(yn));
		           
		               } 
	  
					}
				}
			} else {
				System.out.println(review_no + " 은(는) 존재하지 않는 후기번호 입니다 ");
			}
			
		
	} // private void reviewUpdate(MemberDTO member, CompanyDTO company, Scanner sc)


	
	// 후기 삭제하기 
	private void reviewDelete(MemberDTO member, Scanner sc) {
		
	System.out.println("\n >>> 글삭제하기 <<<");
		
		System.out.print("▷ 삭제할 글번호 : ");
		String review_no = sc.nextLine();
		
		Map<String, String> paraMap = new HashMap<>();
		paraMap.put("review_no", review_no);
		
		ReviewDTO review = null;
		review = mrdao.detailViewReview(paraMap);
		
		if(review != null) {
			System.out.println("[후기번호] " + review.getReview_no() + "\n"
							 + "[작성자명] " + review.getMember().getUser_name() + "\n"
							 + "[후기내용] " + review.getReview_contents() + "\n"
							 + "[평점] " + review.getReview_score() + "\n"
							 + "[작성일] " + review.getReview_regidate() );
			
			if(member.getUser_no() != review.getMember().getUser_no() )  {
				// 삭제할 글번호가 다른 사용자가 쓴 글인 경우라면 
	            System.out.println(">> [경고] 다른 사용자의 글은 수정 불가합니다 << \n");
			} else {
				// 삭제할 글번호가 자신이 쓴 글인 경우라면
				
					System.out.print("▷ 글암호 : ");
					String reviewpasswd = sc.nextLine();
					
					if(! reviewpasswd.equals(member.getUser_passwd())) {
						// 글암호가 일치하지 않는 경우
						System.out.println("[경고] 입력하신 글암호가 작성시 입력한 글암호와 일치하지 않으므로 삭제 불가합니다.!! \n");
				
					} else {
				   // 글암호가 일치하는 경우
		               
	               System.out.println("--------------------------------------"); 
	               System.out.println("[삭제할 후기번호] " + review.getReview_no() );
	               System.out.println("[삭제할 후기내용] " + review.getReview_contents() );
	               System.out.println("--------------------------------------"); 
	               
	             
            	   String yn = "";
            	   do {
            		   System.out.print("▷ 정말로 글삭제 하시겠습니까?[Y/N] : ");
	            	   yn = sc.nextLine();
	            	   
	            	   if("y".equalsIgnoreCase(yn)) {
	            		   
	            		   int n = mrdao.deleteBoard(review_no); //  글삭제하기 ( status 0 으로 변경 )

	            		   if(n==1) {
	            			   System.out.println(">> 후기삭제 성공 !! << \n");
	            			   break;
	            		   } else {
	            			   System.out.println(">> sql 구문 오류 발생으로 인해 글삭제가 실패되었습니다. << \n");
	            		   }

	            	   } else if("n".equalsIgnoreCase(yn)) {
	                        System.out.println(">> 후기 삭제를 취소하셨습니다. << \n");
	                   } else {
	                        System.out.println(">> [경고] Y 또는 N 만 입력하세요!!");
	                   }
            	   } while("y".equalsIgnoreCase(yn) || "n".equalsIgnoreCase(yn));

				}
			} // end of else 
			
			
		} 
		else {
			System.out.println(review_no + " 은(는) 존재하지 않는 후기번호 입니다 ");
		}	
		
		if(member.getUser_id() != review.getMember().getUser_id() ) {
				// 삭제할 글번호가 다른 사용자가 쓴 글인 경우라면 
	            System.out.println(">> [경고] 다른 사용자의 글은 수정 불가합니다.!! << \n"); 
		} 
	} 



} // end of public class MbrReviewController
