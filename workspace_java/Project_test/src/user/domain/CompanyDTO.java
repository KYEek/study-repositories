package user.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		if(com_id.length() < 2 || com_id.length() > 20) {			//아이디 글자 파악
			System.out.println("아이디는 5글자 이상 20글자 이하여야 합니다.");
			return false;
		}
		if(com_id.isBlank()) {
			System.out.println("공백은 안됩니다");						//공백 파악
			return false;
		}
		
		boolean check_alphabet = false;		//영어가 있는지 파악하는 변수
		for(char i:com_id.toCharArray()) {
			if(i >= '!' && i<= '/') {
				System.out.println("기호가 있으면 안됩니다.");
				return false;	//기호가 있으면 false
			}
			
			if(i >= 10000) {
				System.out.println("한글이 있으면 안됩니다.");
				return false;	//한글이 있으면 실패
			}
			
			
			if(i >= 'a' && i <='z') {
				check_alphabet = true;
				break;//5글자 이내고 20글자 이하의 한글과 기호가 없는 글자 중에 영어가 있으면 ok
			}
			
			
		}//end of for-----------------------------
		
		//아이디에 알파벳이 있으면 true 리턴, 숫자만 있으면 false 리턴
		if(check_alphabet) {
			this.com_id = com_id;
			return true;
		}
		else {
			System.out.println("숫자만 입력은 안됩니다.");
			return false;	//숫자만 있으면 실패
		}
	}//end of setCom_id ----------------------------------------------
	
	
	public String getCom_passwd() {
		return com_passwd;
	}
	public boolean setCom_passwd(String com_passwd) {
		boolean correct_pw = true;
		if (com_passwd.isBlank())	{	//공백은 안된다
			System.out.println("공백은 안됩니다");
			return false;
		}

		if (com_passwd.length() < 8 || com_passwd.length() > 20) {		//비밀번호의 길이를 설정
			System.out.println("길이는 8글자 이상 20글자 이하여야 합니다");
			return false;
		}

		char first_char = com_passwd.charAt(0); // 첫글자가 대문자인지 판별

		// 첫글자가 영어가 아니면
		if (!((first_char >= 'A' && first_char <= 'Z') || (first_char >= 'a' && first_char <= 'z'))) {
			System.out.println("첫 글자는 영어여야 합니다");
			return false;
		}

		//소문자, 숫자, 기호, 한글 체크하는 부분
		boolean find_lawer = false, find_number = false, find_special = false, find_korean = true;

		for (int i = 0; i < com_passwd.length(); i++) {
			char ch = com_passwd.charAt(i);

			if (ch > 10000)
				find_korean = false;
			else if (Character.isLowerCase(ch))
				find_lawer = true;
			else if (Character.isDigit(ch))
				find_number = true;
			else
				find_special = true;

		}

		if (find_lawer && find_number && find_special && find_korean) { // 모든 조건을 충족 한다면
			this.com_passwd = com_passwd;
			correct_pw = true;
		} else {
			System.out.println("비밀번호는 8글자 이상 20글자 이하의 영어 대소문자, 숫자 기호를 포함해야 합니다.");
			correct_pw = false;
		}
		return correct_pw;
	}// end of setCom_passwd------------------------------------------------------
	
	
	public String getCom_name() {
		return com_name;
	}

	public boolean setCom_name(String com_name) {

		// 입력값이 공백일 시
		if (com_name.isBlank()) {
			System.out.println("공백은 안됩니다.");
		}

		// 길이 파악 부분
		if (com_name.length() < 2 || com_name.length() > 20) {
			System.out.println("이름은 2글자 이하 20글자 이상이어야 합니다.");
			return false; // 실패로 반환
		}

		this.com_name = com_name;
		return true;
	}//end of name----------------------------------------------------
	
	
	public String getCom_intro() {
		return com_intro;
	}
	public boolean setCom_intro(String com_intro) {

		// 입력값이 공백일 시
		if (com_intro.isBlank()) {
			System.out.println("공백은 안됩니다.");
		}
		
		//글 길이가 100을 넘는지 파악
		if (com_intro.length() > 100) {
			System.out.println("소개글은 100글자 이내이어야 합니다.");
			return false; // 실패로 반환
		}
		//성공
		this.com_intro = com_intro;
		return true;
	}//end of intro----------------------------------------------------
	
	
	public String getCom_email() {
		return com_email;
	}
	public boolean setCom_email(String com_email) {


		// 입력값이 공백일 시
		if (com_email.isBlank()) {
			System.out.println("공백은 안됩니다.");
		}
		
		
		// 이메일 패턴을 정의 후 확인, @가 포함되고 도메인에 .이 포함되야 됨
		Pattern p = Pattern.compile("^[A-Za-z][A-Za-z0-9]{0,14}[@][A-Za-z0-9]{1,9}[.][A-Za-z0-9]{1,5}$");

		Matcher m = p.matcher(com_email);

		// 규정에 맞는지 확인
		if (m.matches()) {
			this.com_email = com_email;
			return true;
		} else {
			System.out.println("올바른 이메일 주소를 입력하세요");
			return false;
		}
	}//end of email-------------------------------------------------
	
	
	public String getCom_president() {
		return com_president;
	}
	public boolean setCom_president(String com_president) {
		boolean check_symbol = true;
		
		//입력값이 공백일 시 
		if(com_president.isBlank()) {
			System.out.println("공백은 안됩니다.");
		}
		
		//이름에 기호를 검사하는 반복문
		for(char i:com_president.toCharArray()) {
			if(i >= '!' && i<= '/') {
				check_symbol = false;	//기호가 있으면 false
			}
			if(i >='0' && i<='9') {		//숫자가 있으면 false
				check_symbol = false;
			}
		}
		
		//길이 파악 부분
		if(com_president.length()< 2 || com_president.length() > 20)
		{
			System.out.println("이름은 2글자 이하 20글자 이상이어야 합니다.");
			return false;	//실패로 반환
		}
		
		//기호가 포함여부로 실행
		if(!check_symbol) {
			System.out.println("숫자나 기호가 포함이 되면 안됩니다..");
			return false;	//실패로 반환
		}
		else {
			this.com_president = com_president;
			return true;	//이름을 저장하고 참을 반환
		}
	}// end of president---------------------------------------------------
	
	
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

		// 입력값이 공백일 시
		if (com_tel.isBlank()) {
			System.out.println("공백은 안됩니다.");
		}
		
		// 전화번호 형식은 ㅇㅇㅇ-ㅇㅇㅇㅇ-ㅇㅇㅇㅇ 형식이어야 한다.
		Pattern p = Pattern.compile("^[0-9]{3}[-][0-9]{4}[-][0-9]{4}$");		//휴대폰 번호
		Pattern p2 = Pattern.compile("^[0-9]{2,3}[-|)][0-9]{3,4}[-][0-9]{3,4}$");	//지역 전화번호
		Matcher m = p.matcher(com_tel);
		Matcher m2 = p2.matcher(com_tel);
		if (m.matches() || m2.matches()) {		//지역번호거나 휴대폰 번호면 실행
			this.com_tel = com_tel;
			return true;
		} else {
			System.out.println("올바른 전화번호를 입력하세요");
			return false;
		}
		
	}//end of Com_tel----------------------------------------------
	
	
	public String getCom_address() {
		return com_address;
	}

	public boolean setCom_address(String com_address) {
		// 입력값이 공백일 시
		if (com_address.isBlank()) {
			System.out.println("공백은 안됩니다.");
		}

		// 주소 길이 파악
		if (com_address.length() > 100) {
			System.out.println("주소의 길이가 너무 깁니다");
			return false;
		}

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
