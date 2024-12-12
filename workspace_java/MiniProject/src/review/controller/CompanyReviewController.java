package review.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import user.domain.CompanyDTO;
import review.domain.ReviewDTO;
import review.model.CompanyReviewDAO_imple;
import review.model.CompanyReviewDAO;
import comment.domain.CommentDTO;
import comment.model.*;


public class CompanyReviewController {

	// field
	CompanyReviewDAO codao = new CompanyReviewDAO_imple();
	CommentDAO commentdao = new CommentDAO_imple();
	Scanner sc = new Scanner(System.in);
	
	// method
	// 기업회원 후기
	public void Company_review_search_memu(Scanner sc, CompanyDTO company , ReviewDTO review, CommentDTO comment) {

		
		String menuNo = "";
		
		do {
			System.out.println(">> 후기조회 <<");
			System.out.println("-".repeat(100));
			System.out.println("1.후기전체조회	2.후기상세조회	 3.회원후기에답변작성하기	4.답변수정하기	5.답변삭제하기	6.돌아가기  ");
			System.out.println("-".repeat(100));
			System.out.print("> 메뉴번호선택 : ");
			menuNo = sc.nextLine();

				switch (menuNo) {
					case "1":
						searchReview(sc, company, review);	// 우리회사후기조회 
						break;
					case "2":
						detailsearchReview(sc, company, review);  // 우리회사후기 상세하게 보기 
						break;
					case "3":
						addComments(sc, company, review, comment);  // 회원후기에 답변달기  
						break;
					case "4":
						updateComments(sc, company, review);  // 답변수정하기   
						break;
					case "5":
					 	deleteComments(sc, company, review);  // 답변삭제하기
						break;	
					case "6":	// 뒤로가기
						return;
					default:
						System.out.println(">> 메뉴에 없는 번호입니다. 다시 선택하세요 !! <<\n");
				}	
		} while(true);	

	} // end of public void Company_review_memu(ReviewDTO review, Scanner sc)




	


	// 우리회사후기조회 
	private void searchReview(Scanner sc, CompanyDTO company, ReviewDTO review) {
		

		StringBuilder sb = new StringBuilder();
		List<String> AllreviewList = codao.companyreviewList(company);
		
		if(AllreviewList.size() > 0) {
			System.out.println("\n>> " + company.getCom_name() + " 기업 후기 List <<");
			System.out.println("-".repeat(60));
			System.out.println("후기번호\t조회수\t작성일자	\t작성자명\t후기내용");
			System.out.println("-".repeat(60));
			
			for(int i=0; i<AllreviewList.size(); i++) {
				sb.append(AllreviewList.get(i)+"\n");
			}
			System.out.println(sb.toString());
		}
		else {
			System.out.println(">> " + company.getCom_name() + " 기업은 작성된 후기가 없습니다... << \n");
		}

		
	}
	
	// 우리회사후기상세조회 (댓글까지 보여진다)
	private void detailsearchReview(Scanner sc, CompanyDTO company, ReviewDTO review) {
		
		StringBuilder sb = new StringBuilder();
		List<String> AllreviewList = codao.companyreviewList(company);
		
		if(AllreviewList.size() > 0) {
			System.out.println("\n>> " + company.getCom_name() + " 기업 후기 List <<");
			System.out.println("-".repeat(60));
			System.out.println("후기번호\t조회수\t작성일자	\t작성자명\t후기내용");
			System.out.println("-".repeat(60));
			
			for(int i=0; i<AllreviewList.size(); i++) {
				sb.append(AllreviewList.get(i)+"\n");
			}
			System.out.println(sb.toString());
			
			do {
				
				String review_no = "";
				do {
					try {
						System.out.print("▶ 자세하게 볼 후기 번호를 입력하세요 ! :  ");
						review_no = sc.nextLine();
						Integer.parseInt(review_no);
						
						break;
					} catch (NumberFormatException e) {
						System.out.println(">> [경고] 후기번호는 정수만 입력하세요 ! <<\n");
					}
				} while(true);
				

				boolean is_exist = codao.compareNo(review_no, company);	// 우리회사리뷰인지판별   
				
				// 우리 회사 리뷰가 맞다면 ... 
				if(is_exist) {
					review = codao.detailViewReview(review_no);
					String name = review.getMember().getUser_name();
					String user_name = name.substring(0,1) + "*"+name.substring(2);

					System.out.println("-".repeat(30));
					System.out.println("[후기번호] " + review.getReview_no() + "\n"
									 + "[작성자명] " + user_name + "\n"
									 + "[후기내용] " + review.getReview_contents() + "\n"
								     + "[평점] " + review.getReview_score() + "\n"
									 + "[작성일] " + review.getReview_regidate() + "\n"
									 + "[조회수] " + review.getViewcount() );
					System.out.println("-".repeat(30) + "\n");
					
					///////////////////////////////////////////////////////////////////////////////////////
					// 댓글 보여줌 ... 
					System.out.println("-".repeat(30) + " [답변] " + "-".repeat(30));
					
					List <CommentDTO> commentList = commentdao.commentList(review_no);
					// 리뷰 번호를 넘겨 일치하는 리뷰 번호의 댓글목록을 가져옴 ... 
					
					if(commentList.size() > 0) {
						// 원글에 대한 댓글이 존재하는 경우
						sb = new StringBuilder();
						
						System.out.println("기업명\t작성일\t\t답변내용");
						System.out.println("-".repeat(67));
						
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
					} else {
						System.out.println(review_no + " 은(는) 존재하지 않는 후기번호 입니다 다시 입력하세요! \n");
					}

			} while(true);
			
			
		}
		else {
			System.out.println(">> " + company.getCom_name() + " 기업은 작성된 후기가 없습니다... << \n");
		}

		
		
	}
	
	
	// 회원후기에 답변달기 
	private void addComments(Scanner sc, CompanyDTO company, ReviewDTO review, CommentDTO comment) {
		
		int result = 0;
		
		System.out.println("\n"+"=".repeat(20) + " 답변작성이 가능한 후기목록 " + "=".repeat(20));
		StringBuilder sb = new StringBuilder();
		List<String> AllreviewList = codao.companyreviewList(company);
		
		if(AllreviewList.size() > 0) {
			System.out.println("\n>> " + company.getCom_name() + " 기업 후기 List <<");
			System.out.println("-".repeat(60));
			System.out.println("후기번호\t조회수\t작성일자	\t작성자명\t후기내용");
			System.out.println("-".repeat(60));
			
			for(int i=0; i<AllreviewList.size(); i++) {
				sb.append(AllreviewList.get(i)+"\n");
			}
			System.out.println(sb.toString());
			
			System.out.println(">>> 답변작성 <<<");
			
			String comname = company.getCom_name();
			System.out.println("1.기업명 : " + comname);
			
			int fk_review_no = 0;	// 답변작성할 후기번호   
			String contents = "";	// 답변내용  
			do {
				System.out.print("2. 답변 작성할 후기번호 : " );
				String review_no = sc.nextLine();	// 정수인지 판별
				boolean is_exist = codao.compareNo(review_no, company);	// 우리회사 번호가 맞나요 
				
				try {
					fk_review_no = Integer.parseInt(review_no);
					
					if(fk_review_no < 1) {	// 1 이하의 번호를 입력한 경우   
						System.out.println(">> [경고] 원글의 글번호는 1 이상인 정수로만 입력하셔야 합니다 !! << ");
					}
					else {
						if(is_exist) {
							break;
						}
						else {
							System.out.println("존재하지 않는 후기번호 입니다 다시 입력하세요 ! ");
						}
					}
				} catch(NumberFormatException e) {
					
				}
			} while(true);
			
			
			do {
				System.out.print("3.댓글 내용 : " );
				contents = sc.nextLine();
		
				if( contents.isBlank() ) {	// 그냥 엔터 또는 공백만으로 입력한 경우 
					System.out.println(">> [경고] 댓글내용은 필수로 입력하셔야 합니다 ! <<\n");
				} else if( contents.length() > 100) {
					System.out.println(">> [경고] 댓글내용은 최대 100글자 이내로 입력하셔야 합니다 ! <<\n");
				}
				else {
					break;
				}
			} while(true);
			
			
			do {
				System.out.print("▷ 정말로 댓글쓰기를 하시겠습니까? [Y/N] : ");
				String yn = sc.nextLine();
				
				if("y".equalsIgnoreCase(yn)) {

					
					Map<String, String> paraMap = new HashMap<>();
					paraMap.put("comno",Integer.toString(company.getCom_no()));
					paraMap.put("comname", comname);	// 회사이름 
					paraMap.put("fk_reviewno",Integer.toString(fk_review_no));	// 답변번호 
					paraMap.put("contents", contents);	// 답변내용 
					
					
					boolean existComment = commentdao.existComment(fk_review_no);
					
					if(existComment) {
						System.out.println("이미 답변이 존재합니다... 후기에 대한 기업답변은 하나만 작성 가능합니다 ! \n");
						break;
					} else {
						result = commentdao.writeComment(paraMap);
						// 		1 또는 -1
						
						if(result == 1) {
							System.out.println(">> 답변이 작성되었습니다 ! << \n");
							break;
						} else {
							System.out.println(">> 오류가 발생하였습니다 ㅜㅜ 답변작성 실패... << \n");
							break;
						}
					}
				} else if ("n".equalsIgnoreCase(yn)) {
					System.out.println(">> 답변 작성이 취소되었습니다 ... << \n");
					break;
				} else {
					System.out.println(">> [경고] Y 또는 N 만 입력하세요! << \n");
				}
			} while(true);
			
		}
		else {
			System.out.println(">> " + company.getCom_name() + " 기업은 작성된 후기가 없습니다... << \n");
		}


		
		
	} // end of private void addComments
	
	
	
	// 답변수정하기   
	private void updateComments(Scanner sc, CompanyDTO company, ReviewDTO review) {
		
		StringBuilder sb = new StringBuilder();
		List<String> MycommentList = commentdao.MycommentList(company);	// 내가 작성했었던 답변 리스트 
		
		String com_no = Integer.toString(company.getCom_no());
		boolean is_exist_comment = commentdao.existMyComment(com_no);	// 우리회사리뷰인지판별   

		if(is_exist_comment) {	// 난 작성한 답변이 있는가 ?
			
			// 답변존재 
			System.out.println("\n"+"-".repeat(21) + ">> 수정가능한 답변 리스트 <<" + "-".repeat(21));
			System.out.println("리뷰번호\t작성일자\t\t답변번호\t답변내용");
			System.out.println("-".repeat(65));
			
			for(int i=0; i<MycommentList.size(); i++) {
				sb.append(MycommentList.get(i)+"\n");
			}
			System.out.println(sb.toString());
				
			System.out.println("\n >>> 답변수정하기 <<<");

			outer :
			do {
				String review_no = "";
				do {
					try {
						System.out.print("▶ 답변 수정할 리뷰번호 : " );
						review_no = sc.nextLine();	// 정수인지 판별
						Integer.parseInt(review_no); 
						
						break;
					} catch(NumberFormatException e) {
						System.out.println(">> [경고] 글번호는 정수만 입력 가능합니다 ! 다시 입력하세요 << \n");
					}
				} while(true);
				
				boolean is_exist = codao.compareNo(review_no, company);	// 우리회사 번호가 맞나요 
				
				if(is_exist) {	// 우리회사번호맞다면 
				
				   CommentDTO comment = null;
				   comment  = commentdao.ViewComment(review_no);
				   
				   if(comment != null) {					   
					  
					   System.out.println("--------------------------------------"); 
					   System.out.println("[리뷰번호] " + review_no);
					   System.out.println("[수정전 답변내용] " + comment.getComment_contents());
					   System.out.println("--------------------------------------"); 
				    
		               
		               System.out.print("▶ 답변내용 [최대 200글자, 변경하지 않으려면 엔터] : ");
		               String comment_contents = sc.nextLine();
		               
		               if(comment_contents.isBlank()) {
		            	   comment_contents = comment.getComment_contents();
		               }//-- end of if --------------------------------------
		               
		               if(comment_contents.length() > 200 ) {
		            	   System.out.println(">> 답변내용은 200자 이내로 작성 가능합니다! << \n");
		               }
		               else {
		            	   String yn = "";
		            	   do {
		            		   System.out.print("▶ 정말로 글수정 하시겠습니까?[Y/N] : ");
			            	   yn = sc.nextLine();
			            	   
			            	   if("y".equalsIgnoreCase(yn)) {
			            		   
			            		   Map<String, String> paraMap = new HashMap<>();
			            		   paraMap.put("comment_contents", comment_contents);
			            		   paraMap.put("review_no", review_no);

			            		   int n = commentdao.updateComment(paraMap);	// 답변수정하기
			            		   
			            		   if(n==1) {
			            			   System.out.println(">> 답변 수정 성공 !! << \n");
			            			   break outer; 
			            		   } else {
			            			   System.out.println(">> sql 구문 오류 발생으로 인해 글수정이 실패되었습니다. << \n");
			            		   }
			            		
			            		   
			            	   } else if("n".equalsIgnoreCase(yn)) {
			                        System.out.println(">> 답변 수정을 취소하셨습니다. << \n");
			                        break outer;
			                   } else {
			                        System.out.println(">> [경고] Y 또는 N 만 입력하세요!! << \n");
			                   }
		            	   } while("y".equalsIgnoreCase(yn) || "n".equalsIgnoreCase(yn));
		           
			               } //-- end of if --------------------------------------
		   
				   } else {		// status = 0 인 후기 번호를 입력시 
					   System.out.println(">> " + review_no + " 은(는) 존재하지 않는 후기번호입니다. 다시 입력하세요 ! <<n");
				   }
				  
			} else {	// 다른 기업의 후기 번호를 입력시 
					System.out.println(">> " + review_no + " 은(는) 존재하지 않는 후기번호 입니다. 다시 입력하세요! << \n");
				} 		
		} while(true);
		} else {
			System.out.println(">> " + company.getCom_name() + " 기업은 작성한 답변이 없습니다 ㅜ ㅜ << \n ");
		}
	

	}
		
	// 답변 삭제하기 
	private void deleteComments(Scanner sc, CompanyDTO company, ReviewDTO review) {
		
		StringBuilder sb = new StringBuilder();
		List<String> MycommentList = commentdao.MycommentList(company);	// 내가 작성했었던 답변 리스트 
		
		String com_no = Integer.toString(company.getCom_no());
		boolean is_exist_comment = commentdao.existMyComment(com_no);	// 우리회사리뷰인지판별   

		if(is_exist_comment) {	// 난 작성한 답변이 있는가 ?
			
			// 답변존재 
			System.out.println("\n"+"-".repeat(21) + ">> 삭제가능한 답변 리스트 <<" + "-".repeat(21));
			System.out.println("리뷰번호\t작성일자\t\t답변번호\t답변내용");
			System.out.println("-".repeat(65));
			
			for(int i=0; i<MycommentList.size(); i++) {
				sb.append(MycommentList.get(i)+"\n");
			}
			System.out.println(sb.toString());
				
			System.out.println("\n >>> 답변수정하기 <<<");
			
				
			String review_no = "";
			outer:
			do {
			// 삭제할 리뷰번호 입력 (정수인지 판별) 
				do {
					try {
						System.out.print("▶ 답변 삭제할 리뷰번호 : " );
						review_no = sc.nextLine();	// 정수인지 판별
						Integer.parseInt(review_no); 
						
						break;
					} catch(NumberFormatException e) {
						System.out.println(">> [경고] 글번호는 정수만 입력 가능합니다 ! 다시 입력하세요 << \n");
					}
				} while(true);
				
				boolean is_exist = codao.compareNo(review_no, company);	// 우리회사 번호가 맞나요 
				
				if(is_exist) {	// 우리회사번호맞다면 
				
				   CommentDTO comment = null;
				   comment  = commentdao.ViewComment(review_no);
				   
				   if(comment != null) {					   
					  
					   System.out.println("--------------------------------------"); 
					   System.out.println("[리뷰번호] " + review_no);
					   System.out.println("[답변내용] " + comment.getComment_contents());
					   System.out.println("--------------------------------------"); 
				    
	
	            	   String yn = "";
	            
	            	   do {
	            		   System.out.print("▶ 정말로 답변을 삭제 하시겠습니까?[Y/N] : ");
		            	   yn = sc.nextLine();
		            	   
		            	   if("y".equalsIgnoreCase(yn)) {
		            		   
		            		   int n = commentdao.deleteComment(review_no);	// status 0으로 수정 
	
		            		   if(n==1) {
		            			   System.out.println(">> 답변 삭제 성공 !! << \n");
		            			   break outer; 
		            		   } else {
		            			   System.out.println(">> sql 구문 오류 발생으로 인해 글삭제가 실패되었습니다. << \n");
		            		   }
		       
		            	   } else if("n".equalsIgnoreCase(yn)) {
		                        System.out.println(">> 답변 삭제를 취소하셨습니다. << \n");
		                        break outer;
		                   } else {
		                        System.out.println(">> [경고] Y 또는 N 만 입력하세요!! << \n");
		                        
		                   }
	            	   } while("y".equalsIgnoreCase(yn) || "n".equalsIgnoreCase(yn));
	
					   } else {		// status = 0 인 후기 번호를 입력시 
						   System.out.println(">> " + review_no + " 은(는) 존재하지 않는 후기번호입니다. 다시 입력하세요 ! <<n");
					   }
					  
				} else {	// 다른 기업의 후기 번호를 입력시 
						System.out.println(">> " + review_no + " 은(는) 존재하지 않는 후기번호 입니다. 다시 입력하세요! << \n");
				} 	
			} while(true);
		} else {
			System.out.println(">> " + company.getCom_name() + " 기업은 작성한 답변이 없습니다 ㅜ ㅜ << \n ");
		}
	}

}
