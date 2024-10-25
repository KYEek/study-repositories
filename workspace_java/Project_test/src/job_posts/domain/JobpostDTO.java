package job_posts.domain;

import applys.domain.ApplysDTO;
import user.domain.CompanyDTO;
import user.domain.MemberDTO;
import resume.domain.ResumeDTO;

public class JobpostDTO {
	
	
/*
// tbl_job_posts 구조 
	 이름            널?       유형             
------------- -------- -------------- 
JOB_POSTNO    NOT NULL NUMBER         
POST_TITLE    NOT NULL VARCHAR2(100)  
POST_CONTENTS NOT NULL NVARCHAR2(200) 
WAGE                   NUMBER         
END_DATE      NOT NULL DATE           
FK_COM_NO     NOT NULL NUMBER         
FK_WORK_TCODE NOT NULL NUMBER         
FK_JOB_TCODE  NOT NULL NUMBER   

*/

	// === field === //
	
	private int 	job_postno;		// 채용공고번호
	private int 	wage;		    // 연봉
	private String 	post_title;		// 공고제목 
	private String 	post_contents; 	// 공고내용
	private String 	end_date;		// 마감일
	private int 	fk_work_TCODE; 	// 고용형태코드
	private int 	fk_job_TCODE;   // 업종코드
	
	private CompanyDTO company;	// 기업회원코드참조
	private ResumeDTO resume;
	private MemberDTO member;
	private ApplysDTO apply;
 	
	
	// === method === //
	

	public ApplysDTO getApply() {
		return apply;
	}

	public void setApply(ApplysDTO apply) {
		this.apply = apply;
	}

	public CompanyDTO getCompany() {
		return company;
	}

	public void setCompany(CompanyDTO company) {
		this.company = company;
	}

	public ResumeDTO getResume() {
		return resume;
	}

	public void setResume(ResumeDTO resume) {
		this.resume = resume;
	}

	public MemberDTO getMember() {
		return member;
	}

	public void setMember(MemberDTO member) {
		this.member = member;
	}

	public int getJob_postno() {
		return job_postno;

	}
	
	// 채용공고번호
	public void setJob_postno(int job_postno) {
		this.job_postno = job_postno;
	}
	
	// 연봉
	public int getWage() {
		return wage;
	}
	

	public void setWage(int wage) {
		this.wage = wage;
	}
	
	// 공고제목 
	public String getPost_title() {
		return post_title;
	}
	

	public void setPost_title(String post_title) {
		this.post_title = post_title;
	}
	
	
	// 공고내용
	public String getPost_contents() {
		return post_contents;
	}
	
	public void setPost_contents(String post_contents) {
		this.post_contents = post_contents;
	}
	
	// 마감일
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	
	// 고용형태코드
	public int getFk_work_TCODE() {
		return fk_work_TCODE;
	}
	public void setFk_work_TCODE(int fk_work_TCODE) {
		this.fk_work_TCODE = fk_work_TCODE;
	}
	
	
	// 업종코드
	public int getFk_job_TCODE() {
		return fk_job_TCODE;
	}
	
	public void setFk_job_TCODE(int fk_job_TCODE) {
		this.fk_job_TCODE = fk_job_TCODE;
	}  
	
	
	public String CompanyListInfo() {
		// 공고번호		회사명		공고제목		지원날짜   
		return job_postno + "\t" + company.getCom_no() +"\t" + company.getCom_name()+ "\t" + post_title + "\t\t" + apply.getApplys_date() ; 
	}

	
}

