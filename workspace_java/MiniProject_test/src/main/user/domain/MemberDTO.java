package main.user.domain;


	/*
	
		TBL_USERS 구조
		이름           널?       유형             
		------------ -------- -------------- 
		USER_NO      NOT NULL NUMBER         
		USER_ID      NOT NULL VARCHAR2(20)   
		USER_PASSWD  NOT NULL VARCHAR2(20)   
		USER_NAME    NOT NULL NVARCHAR2(20)  
		USER_JUBUN   NOT NULL VARCHAR2(20)   
		UESR_EMAIL   NOT NULL VARCHAR2(30)   
		USER_TEL              NVARCHAR2(15)  
		USER_ADDRESS          NVARCHAR2(100) 
		USER_REGI    NOT NULL DATE           
		USER_STATUS  NOT NULL NUMBER         
		FK_JOB_TCODE          NUMBER      
	
	*/

public class MemberDTO {
	
	
	
	//field
	private int user_no;		//회원번호
	private String user_id;		//회원아이디
	private String user_passwd; //회원비밀번호
	private String user_name;	//회원이름
	private String user_jubun;	//생년월일
	private String user_email;	//이메일
	private String user_tel;	//연락처
	private String user_address;//주소
	private String user_regi;	//가입일
	private int user_status;	//회원상태(가입여부)
	private int fk_job_tcode;   //업종코드
	private String job_type;	//업종명
	
	
	
	//method
	//게터 세터 메서드
	//set을 해줄 때 유효성 검사를 해준다
	
	
	public int getUser_no() {
		return user_no;
	}
	
	public void setUser_no(int user_no) {
		
		this.user_no = user_no;
	}
	
	
	
	public String getUser_id() {
		return user_id;
	}
	public boolean setUser_id(String user_id) {
		
//		
//		if(user_no < 5 && user_no > 20) {
//			System.out.println("아이디는 5글자 이상 20글자 이하여야 합니다.");
			this.user_id = user_id;
//			return false;
//		}
//		else {
			return true;
//		}
		
	}//end of setUser_id---------------------------------------------
	
	
	
	
	
	
	
	public String getUser_passwd() {
		return user_passwd;
	}

	public boolean setUser_passwd(String user_passwd) {
//		// user_passwd ==> Ab3$
//		boolean correct_pw = true;
//		if (user_passwd == null)
//			correct_pw = false;
//
//		// 예를 들어서
//		// 입력받은 user_passwd 가 Ab3$ 이라면
//		// 입력받은 user_passwd 가 Ab3$fsafawewfwga$%$sdADF 이라면
//		if (user_passwd.length() < 8 || user_passwd.length() > 20)
//			correct_pw = false;
//
//		// 이제 부터는 입력받은 user_passwd의 글자수가 8글자 이상 15글자 이하인 경우이다
//		// 예를들어서
//		// 입력받은 user_passwd 가 C5d#하하호호 이라면 C5d#하하s@! 이라면
//		// 또는
//		// 입력받은 user_passwd 가 C5dawxab 이라면 c5dawxab# 이라면
//		// 입력받은 user_passwd 가 C5dawxab@ 이라면 c5dawxab#T 이라면
//		// find_korean 은 한글이 발견 되면 false가 되어서 실패한다 기본은 없는 상태이기 때문에 true.
//		boolean find_upper = false, find_lawer = false, find_number = false, find_special = false, find_korean = true;
//
//		for (int i = 0; i < user_passwd.length(); i++) {
//			char ch = user_passwd.charAt(i);
//
//			if ('가' <= ch && ch <= '힣')
//				find_korean = false;
//			else if (Character.isUpperCase(ch))
//				find_upper = true;
//			else if (Character.isLowerCase(ch))
//				find_lawer = true;
//			else if (Character.isDigit(ch))
//				find_number = true;
//			else
//				find_special = true;
//
//		}
//
//		if (find_lawer && find_number && find_special && find_upper && find_korean) { // 모든 조건을 충족 한다면
			this.user_passwd = user_passwd;
			return true;
//			correct_pw = true;
//		} else {
//			System.out.println("비밀번호는 8글자 이상 20글자 이하의 영어 대소문자, 숫자 기호를 포함해야 합니다.");
//			correct_pw = false;
//		}
//		return correct_pw;

	}// end of setUser_passwd---------------------------------------------
	
	
	
	
	
	
	
	public String getUser_name() {
		return user_name;
	}
	public boolean setUser_name(String user_name) {
		//이름에 기호가 있는 지 검사하기 위한 변수
		boolean check_symbol = true;
		//이름에 기호를 검사하는 반복문
		for(char i:user_name.toCharArray()) {
			if(i >= '!' && i<= '/') {
				check_symbol = false;	//기호가 있으면 false
			}
		}
		
		if(user_name.length()< 2 || user_name.length() > 20)
		{
			System.out.println("이름은 2글자 이하 20글자 이상이어야 합니다.");
			return false;	//실패로 반환
		}
		else if(check_symbol == false) {
			System.out.println("기호가 포함이 되면 안됩니다..");
			return false;	//실패로 반환
		}
		else {
			this.user_name = user_name;
			return true;	//이름을 저장하고 참을 반환
		}
	}//end of setUser_name ---------------------------------------------------
	
	
	
	
	
	
	
	public String getUser_jubun() {
		return user_jubun;
	}

	public boolean setUser_jubun(String user_jubun) {
//		
//		//숫자만 입력했는지 검사 하는 try catch
//		try {
//			Integer.parseInt(user_jubun);
//		} catch (NumberFormatException e){
//			System.out.println("숫자만 입력하십시오");
//			return false;		//숫자가 아니면 false 반환 
//		}
//		
//		if (user_jubun.length() != 7) {			//주번이 7자가 아니면 
//			System.out.println("주민번호는 7자만 입력하십시오");
//			return false;
//			
//		} 
//		else if(user_jubun.charAt(7) < 1 && user_jubun.charAt(7) > 4) {		//주번이 7자리가  1,2,3,4 가 아니면 
//			System.out.println("주민번호 7번째 자리를 올바로 입력하세요");
//			return false;
//		}
//		else {
			this.user_jubun = user_jubun;
			return true;
//		}

	}// end of user_jubun----------------------------------------------
	
	
	
	
	
	public String getUser_email() {
		return user_email;
	}
	public boolean setUser_email(String uesr_email) {
		//TODO 여기 부터 작성해야 함.
		
		
		this.user_email = uesr_email;
		return true;
	}
	
	
	public String getUser_tel() {
		return user_tel;
	}
	public boolean setUser_tel(String user_tel) {
		this.user_tel = user_tel;
		return true;
	}
	
	
	public String getUser_address() {
		return user_address;
	}
	public boolean setUser_address(String user_address) {
		this.user_address = user_address;
		return true;
	}
	
	
	public String getUser_regi() {
		return user_regi;
	}
	public void setUser_regi(String user_regi) {
		this.user_regi = user_regi;
	}
	
	
	public int getUser_status() {
		return user_status;
	}
	public void setUser_status(int user_status) {
		this.user_status = user_status;
	}
	
	
	public int getFk_job_tcode() {
		return fk_job_tcode;
	}
	public boolean setFk_job_tcode(int fk_job_tcode) {
		this.fk_job_tcode = fk_job_tcode;
		return true;
	}
	
	
	public String getJob_type() {
		return job_type;
	}
	public void setJob_type(String job_type) {
		this.job_type = job_type;
	}
	
	
	
}
