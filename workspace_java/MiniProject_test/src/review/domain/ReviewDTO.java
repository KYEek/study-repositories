package review.domain;

import user.domain.CompanyDTO;
import user.domain.MemberDTO;

public class ReviewDTO { 

	private int review_no;			// 후기번호
	private int review_score;		// 평점
	private String review_contents;	// 평가내용
	private String review_regidate;	// 작성일자
	private CompanyDTO company;  // 
	private MemberDTO member;  //
	
	
	public CompanyDTO getCompany() {
		return company;
	}
	public void setCompany(CompanyDTO company) {
		this.company = company;
	}
	public MemberDTO getMember() {
		return member;
	}
	public void setMember(MemberDTO member) {
		this.member = member;
	}
	public int getReview_no() {
		return review_no;
	}
	public void setReview_no(int review_no) {
		this.review_no = review_no;
	}
	public int getReview_score() {
		return review_score;
	}
	public void setReview_score(int review_score) {
		this.review_score = review_score;
	}
	public String getReview_contents() {
		return review_contents;
	}
	public void setReview_contents(String review_contents) {
		this.review_contents = review_contents;
	}
	public String getReview_regidate() {
		return review_regidate;
	}
	public void setReview_regidate(String review_regidate) {
		this.review_regidate = review_regidate;
	}
	
	public String reviewInfo() {	// 회사이름   후기내용   평점   후기등록일   
		return review_no + "\t" + company.getCom_name() + "\t" + review_contents + "\t" + review_score + "\t" + review_regidate;
	}
	
	public String rankInfo() {	// 후기번호	기업명	후기내용		평점		작성일   
		return review_no + "\t" + company.getCom_name() + "\t" + review_contents + "\t" + review_score + "\t" + review_regidate;
	}
	
	
	
}
