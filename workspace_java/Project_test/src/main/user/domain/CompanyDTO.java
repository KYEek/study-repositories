package main.user.domain;

/*
 	TBL_COMPANIES 구조
	 이름            널?       유형             
	------------- -------- -------------- 
	COM_NO        NOT NULL NUMBER         
	COM_ID        NOT NULL VARCHAR2(20)   
	COM_PASSWD    NOT NULL VARCHAR2(20)   
	COM_NAME      NOT NULL NVARCHAR2(20)  
	COM_INTRO     NOT NULL NVARCHAR2(100) 
	COM_EMAIL     NOT NULL VARCHAR2(30)   
	COM_PRESIDENT NOT NULL NVARCHAR2(20)  
	COM_REVENUE   NOT NULL NUMBER         
	COM_TEL                NVARCHAR2(15)  
	COM_ADDRESS   NOT NULL NVARCHAR2(100) 
	FK_JOB_TCODE  NOT NULL NUMBER         
	COM_REGI      NOT NULL DATE           
	COM_STATUS    NOT NULL NUMBER        
 */


public class CompanyDTO {

	
	
	
	
	//field
	private int com_no;    			//기업번호
	private String com_id;			//기업회원아이디
	private String com_passwd;		//기업회원비밀번호
	private String com_name;		//기업명
	private String com_intro;		//기업소개
	private String com_email;		//기업이메일
	private String com_president;	//대표이사명
	private long com_revenue;    	//매출
	private String com_tel;			//전화번호
	private String com_address;		//소재지
	private int fk_job_tcode;		//업종코드
	private String com_regi;        //가입일자 
	private int com_status;			//회원상태(가입여부) 0 과 1만
	private String job_type;		//업종명
	
	
	
	
	//method
	//게터 세터 메서드
	public int getCom_no() {
		return com_no;
	}
	
	
	public boolean setCom_no(int com_no) {
		this.com_no = com_no;
		return true;
	}
	
	
	public String getCom_id() {
		return com_id;
	}
	public boolean setCom_id(String com_id) {
		this.com_id = com_id;
		return true;
	}
	
	
	public String getCom_passwd() {
		return com_passwd;
	}
	public boolean setCom_passwd(String com_passwd) {
		this.com_passwd = com_passwd;
		return true;
	}
	
	
	public String getCom_name() {
		return com_name;
	}
	public boolean setCom_name(String com_name) {
		this.com_name = com_name;
		return true;
	}
	
	
	public String getCom_intro() {
		return com_intro;
	}
	public boolean setCom_intro(String com_intro) {
		this.com_intro = com_intro;
		return true;
	}
	
	
	public String getCom_email() {
		return com_email;
	}
	public boolean setCom_email(String com_email) {
		this.com_email = com_email;
		return true;
	}
	
	
	public String getCom_president() {
		return com_president;
	}
	public boolean setCom_president(String com_president) {
		this.com_president = com_president;
		return true;
	}
	
	
	public long getCom_revenue() {
		return com_revenue;
	}
	public boolean setCom_revenue(long com_revenue) {
		this.com_revenue = com_revenue;
		return true;
	}
	
	
	public String getCom_tel() {
		return com_tel;
	}
	public boolean setCom_tel(String com_tel) {
		this.com_tel = com_tel;
		return true;
	}
	
	
	public String getCom_address() {
		return com_address;
	}
	public boolean setCom_address(String com_address) {
		this.com_address = com_address;
		return true;
	}
	
	
	public int getFk_job_tcode() {
		return fk_job_tcode;
	}
	public boolean setFk_job_tcode(int fk_job_tcode) {
		this.fk_job_tcode = fk_job_tcode;
		return true;
	}
	
	
	public String getCom_regi() {
		return com_regi;
	}
	public boolean setCom_regi(String com_regi) {
		this.com_regi = com_regi;
		return true;
	}
	
	
	public int getCom_status() {
		return com_status;
	}
	public boolean setCom_status(int com_status) {
		this.com_status = com_status;
		return true;
	}
	
	
	public String getJob_type() {
		return job_type;
	}
	public boolean setJob_type(String job_type) {
		this.job_type = job_type;
		return true;
	}
	
	
}
