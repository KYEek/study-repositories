package review.controller;

import java.util.List;
import java.util.Scanner;

import user.domain.CompanyDTO;
import review.domain.ReviewDTO;
import review.model.CompanyReviewDAO_imple;
import review.model.CompanyReviewDAO;


public class CompanyReviewController {

	// field
	CompanyReviewDAO codao = new CompanyReviewDAO_imple();
	Scanner sc = new Scanner(System.in);
	
	// method
	// 기업회원 후기
	public void Company_review_search_memu(Scanner sc, CompanyDTO company , ReviewDTO review) {

		
		String menuNo = "";
		
		do {
			System.out.println(">> 후기조회 <<");
			System.out.println("-".repeat(40));
			System.out.println("1.후기전체조회	2.후기상세조회	3.뒤로가기");
			System.out.println("-".repeat(40));
			System.out.print("> 메뉴번호선택 : ");
			menuNo = sc.nextLine();

				switch (menuNo) {
					case "1":
						searchReview(sc, company, review);	// 우리회사후기조회 
						break;
					case "2":
						detailsearchReview(sc, company, review);  // 우리회사후기 상세하게 보기 
						break;
					case "3":	// 뒤로가기
						return;
					default:
						System.out.println(">>> 메뉴에 없는 번호입니다. 다시 선택하세요 !! <<<");
				}	
		} while(true);	

	} // end of public void Company_review_memu(ReviewDTO review, Scanner sc)

	
	
	
	// 우리회사후기조회 
	private void searchReview(Scanner sc, CompanyDTO company, ReviewDTO review) {
		
		
		System.out.println("우리회사후기조회");
		
		StringBuilder sb = new StringBuilder();
		List<String> AllreviewList = codao.companyreviewList(company);
		
		System.out.println("\n >> " + company.getCom_name() + " 기업 List <<");
		System.out.println("-".repeat(60));
		System.out.println("후기번호	후기내용   	조회수   작성일자	    작성자명");
		System.out.println("-".repeat(60));
		
		for(int i=0; i<AllreviewList.size(); i++) {
			sb.append(AllreviewList.get(i)+"\n");
		}
		System.out.println(sb.toString());
		
	}
	
	// 우리회사후기상세조회 
	private void detailsearchReview(Scanner sc, CompanyDTO company, ReviewDTO review) {

		searchReview(sc, company, review);
		
		do {
			
			String review_no = "";
			do {
				try {
					System.out.println("▶ 자세하게 볼 후기 번호를 입력하세요 ! :  ");
					review_no = sc.nextLine();
					Integer.parseInt(review_no);
					
					break;
				} catch (NumberFormatException e) {
					System.out.println(">> [경고] 후기번호는 정수만 입력하세요 ! <<");
				}
			} while(true);
			

			boolean is_exist = codao.compareNo(review_no, company);
			
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
				break;
				} else {
					System.out.println(review_no + " 은(는) 존재하지 않는 후기번호 입니다 다시 입력하세요! \n");
				}

		} while(true);
		
	}

	
	
	
}
