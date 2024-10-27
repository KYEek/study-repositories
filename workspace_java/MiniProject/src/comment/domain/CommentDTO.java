package comment.domain;

import user.domain.CompanyDTO;

public class CommentDTO {

	private int comment_no;  
	private int fk_review_no;
	private int fk_user_no; 
	private String comment_contents;
	private String comment_regidate;
	private int comment_status;
	private CompanyDTO company;
	
	
	public CompanyDTO getCompany() {
		return company;
	}
	public void setCompany(CompanyDTO company) {
		this.company = company;
	}


	
	public int getComment_no() {
		return comment_no;
	}
	public void setComment_no(int comment_no) {
		this.comment_no = comment_no;
	}
	public int getFk_review_no() {
		return fk_review_no;
	}
	public void setFk_review_no(int fk_review_no) {
		this.fk_review_no = fk_review_no;
	}
	public int getFk_user_no() {
		return fk_user_no;
	}
	public void setFk_user_no(int fk_user_no) {
		this.fk_user_no = fk_user_no;
	}
	public String getComment_contents() {
		return comment_contents;
	}
	public void setComment_contents(String comment_contents) {
		this.comment_contents = comment_contents;
	}
	public String getComment_regidate() {
		return comment_regidate;
	}
	public void setComment_regidate(String comment_regidate) {
		this.comment_regidate = comment_regidate;
	}
	public int getComment_status() {
		return comment_status;
	}
	public void setComment_status(int comment_status) {
		this.comment_status = comment_status;
	}
	
	
	
	
	
	
}
