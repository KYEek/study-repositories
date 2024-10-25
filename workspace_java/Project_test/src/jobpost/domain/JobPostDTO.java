package member.domain;

public class JobPostDTO {
	
	
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
	

	private int 	wage;		    // 연봉
	private int     jop_postno;     // 공고번호
	private String 	post_title;		// 공고제목 
	private String 	post_contents; 	// 공고내용
	private String  write_date;     // 작성일자
	private String 	end_date;		// 마감일
	private int 	view_count;     // 조회수
	private int 	fk_com_no;		// 기업회원번호
	private int 	fk_work_TCODE; 	// 고용형태코드
	private int 	fk_job_TCODE;   // 업종코드 
    private String  com_name;       // join된 회사명
    private String  job_type;       // 직종명
 	private int resume_no;			// 이력서 번호
    private String res_title;    	// 이력서 제목
    private String res_intro;		// 자기소개
    private int res_career;			// 연차
    private int res_hwage;			// 희망연봉

    
    
   // 게터 세터 메소드이다 (유효성 검사 할때 사용한다.)	
	
	
	

	public int getResume_no() {
		return resume_no;
	}
	public void setResume_no(int resume_no) {
		this.resume_no = resume_no;
	}
	public String getRes_title() {
		return res_title;
	}
	public void setRes_title(String res_title) {
		this.res_title = res_title;
	}
	public String getRes_intro() {
		return res_intro;
	}
	public void setRes_intro(String res_intro) {
		this.res_intro = res_intro;
	}
	public int getRes_career() {
		return res_career;
	}
	public void setRes_career(int res_career) {
		this.res_career = res_career;
	}
	public int getRes_hwage() {
		return res_hwage;
	}
	public void setRes_hwage(int res_hwage) {
		this.res_hwage = res_hwage;
	}
	public int getWage() {
		return wage;
	}
	public void setWage(int wage) {
		this.wage = wage;
	}
	public int getJop_postno() {
		return jop_postno;
	}
	public void setJop_postno(int jop_postno) {
		this.jop_postno = jop_postno;
	}
	public String getPost_title() {
		return post_title;
	}
	public void setPost_title(String post_title) {
		this.post_title = post_title;
	}
	public String getPost_contents() {
		return post_contents;
	}
	public void setPost_contents(String post_contents) {
		this.post_contents = post_contents;
	}
	public String getWrite_date() {
		return write_date;
	}
	public void setWrite_date(String write_date) {
		this.write_date = write_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public int getFk_com_no() {
		return fk_com_no;
	}
	public void setFk_com_no(int fk_com_no) {
		this.fk_com_no = fk_com_no;
	}
	public int getFk_work_TCODE() {
		return fk_work_TCODE;
	}
	public void setFk_work_TCODE(int fk_work_TCODE) {
		this.fk_work_TCODE = fk_work_TCODE;
	}
	public int getFk_job_TCODE() {
		return fk_job_TCODE;
	}
	public void setFk_job_TCODE(int fk_job_TCODE) {
		this.fk_job_TCODE = fk_job_TCODE;
	}

	public int getView_count() {
		return view_count;
	}
	public void setView_count(int view_count) {
		this.view_count = view_count;
	}

	public String getCom_name() {
		return com_name;
	}
	public void setCom_name(String com_name) {
		this.com_name = com_name;
}
	public String getJob_type() {
		return job_type;
	}
	public void setJob_type(String job_type) {
		this.job_type = job_type;
	}
	
}