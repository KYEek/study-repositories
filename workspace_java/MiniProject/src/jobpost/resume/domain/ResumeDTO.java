package jobpost.resume.domain;

import user.domain.MemberDTO;

public class ResumeDTO {// resumeDTO 는 tbl_resumes 테이블에 해당.
	
	// field, attribute, property, 속성 (DB 의 column)
	private int resume_no;			// 이력서 번호
	private String res_title;		// 이력서 제목
	private String res_intro;		// 자기소개
	private int res_career;			// 연차
	private int res_hwage;			// 희망연봉
	private String res_regiday;		// 작성일
	private int fk_user_no;			// 회원번호
	private int fk_edu_code;		// 학력코드
	private int fk_major_code;		// 전공코드
	private int fk_work_tcode;		// 고용형태코드
	
	
	///// select 용 field
	private MemberDTO mbdto;		// !!! JOIN 해서 SELECT 하는 용도, 자식(board) 테이블에 부모(member) 테이블을 객체를 가져옴 !!!
	private int age;
	private String gender;
	private String  edu_degree;
	private String major_name;
	private String work_type;
	
	// method, operation, 기능


	// 이력서 번호
	public int getResume_no() {
		return resume_no;
	}
	public void setResume_no(int resume_no) {
		this.resume_no = resume_no;
	}
	
	
	// 이력서 제목
	public String getRes_title() {
		return res_title;
	}
	public void setRes_title(String res_title) {
		this.res_title = res_title;
	}
	
	
	// 자기소개
	public String getRes_intro() {
		return res_intro;
	}
	public void setRes_intro(String res_intro) {
		this.res_intro = res_intro;
	}
	
	
	// 연차
	public int getRes_career() {
		return res_career;
	}
	public void setRes_career(int res_career) {
		this.res_career = res_career;
	}
	
	
	// 희망연봉
	public int getRes_hwage() {
		return res_hwage;
	}
	public void setRes_hwage(int res_hwage) {
		this.res_hwage = res_hwage;
	}
	
	
	// 작성일
	public String getRes_regiday() {
		return res_regiday;
	}
	public void setRes_regiday(String res_regiday) {
		this.res_regiday = res_regiday;
	}
	
	
	// 회원번호
	public int getFk_user_no() {
		return fk_user_no;
	}
	public void setFk_user_no(int fk_user_no) {
		this.fk_user_no = fk_user_no;
	}
	
	
	// 학력코드
	public int getFk_edu_code() {
		return fk_edu_code;
	}
	public void setFk_edu_code(int fk_edu_code) {
		this.fk_edu_code = fk_edu_code;
	}
	
	
	// 전공코드
	public int getFk_major_code() {
		return fk_major_code;
	}
	public void setFk_major_code(int fk_major_code) {
		this.fk_major_code = fk_major_code;
	}
	
	
	// 고용형태코드
	public int getFk_work_tcode() {
		return fk_work_tcode;
	}
	public void setFk_work_tcode(int fk_work_tcode) {
		this.fk_work_tcode = fk_work_tcode;
	}
	
	
	// 멤버불러오기
	public MemberDTO getMbdto() {
		return mbdto;
	}
	public void setMbdto(MemberDTO mbdto) {
		this.mbdto = mbdto;
	}
	
	
	// 나이
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
		
	
	// 성별
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	// 학력
	public String getEdu_degree() {
		return edu_degree;
	}
	public void setEdu_degree(String edu_degree) {
		this.edu_degree = edu_degree;
	}
	
	
	// 전공
	public String getMajor_name() {
		return major_name;
	}
	public void setMajor_name(String major_name) {
		this.major_name = major_name;
	}
	
	
	// 고용형태
	public String getWork_type() {
		return work_type;
	}
	public void setWork_type(String work_type) {
		this.work_type = work_type;
	}
	
	/////////////////////////
	public String resumeInfo() {
		// 이력서번호  작성자명  나이  제목  작성일자
		return resume_no +"\t"+ mbdto.getUser_name() +"\t"+ age +"\t"+ res_title +"\t\t"+ res_regiday+"\n";
	}// end of public String boardInfo() ----------------------------
	
	
}
