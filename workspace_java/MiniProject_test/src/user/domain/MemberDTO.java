package user.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		
		
		if(user_id.length() < 5 || user_id.length() > 20) {			//아이디 글자 파악
			System.out.println("아이디는 5글자 이상 20글자 이하여야 합니다.");
			return false;
		}
		if(user_id.isBlank()) {
			System.out.println("공백은 안됩니다");						//공백 파악
			return false;
		}
		
		boolean check_alphabet = false;		//영어가 있는지 파악하는 변수
		for(char i:user_id.toCharArray()) {
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
			this.user_id = user_id;
			return true;
		}
		else {
			System.out.println("숫자만 입력은 안됩니다.");
			return false;	//숫자만 있으면 실패
		}
		
		
	}//end of setUser_id---------------------------------------------
	
	
	
	
	
	
	
	public String getUser_passwd() {
		return user_passwd;
	}

	public boolean setUser_passwd(String user_passwd) {
		
		boolean correct_pw = true;
		if (user_passwd.isBlank())	{	//공백은 안된다
			System.out.println("공백은 안됩니다");
			return false;
		}

		if (user_passwd.length() < 8 || user_passwd.length() > 20) {		//비밀번호의 길이를 설정
			System.out.println("길이는 8글자 이상 20글자 이하여야 합니다");
			return false;
		}

		char first_char = user_passwd.charAt(0); // 첫글자가 대문자인지 판별

		// 첫글자가 영어가 아니면
		if (!((first_char >= 'A' && first_char <= 'Z') || (first_char >= 'a' && first_char <= 'z'))) {
			System.out.println("첫 글자는 영어여야 합니다");
			return false;
		}

		//소문자, 숫자, 기호, 한글 체크하는 부분
		boolean find_lawer = false, find_number = false, find_special = false, find_korean = true;

		for (int i = 0; i < user_passwd.length(); i++) {
			char ch = user_passwd.charAt(i);

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
			this.user_passwd = user_passwd;
			correct_pw = true;
		} else {
			System.out.println("비밀번호는 8글자 이상 20글자 이하의 영어 대소문자, 숫자 기호를 포함해야 합니다.");
			correct_pw = false;
		}
		return correct_pw;

	}// end of setUser_passwd---------------------------------------------
	
	
	
	
	
	
	
	public String getUser_name() {
		return user_name;
	}
	public boolean setUser_name(String user_name) {
		//이름에 기호가 있는 지 검사하기 위한 변수
		boolean check_symbol = true;
		//이름에 기호를 검사하는 반복문
		for(char i:user_name.toCharArray()) {
			if(i >= '!' && i<= '/' || i >= ':' && i<= '@' || i >= '[' && i<= 96 ) {
				check_symbol = false;	//기호가 있으면 false
			}
			if(i >='0' && i<='9') {		//숫자가 있으면 false
				check_symbol = false;
			}
		}
		
		//길이 파악 부분
		if(user_name.length()< 2 || user_name.length() > 20)
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
			this.user_name = user_name;
			return true;	//이름을 저장하고 참을 반환
		}
	}//end of setUser_name ---------------------------------------------------
	
	
	
	
	
	
	
	public String getUser_jubun() {
		return user_jubun;
	}

	public boolean setUser_jubun(String user_jubun) {
		
		
		// 입력값이 공백일 시
		if (user_jubun.isBlank()) {
			System.out.println("공백은 안됩니다.");
		}
		
		
		//숫자만 입력했는지 검사 하는 try catch
		try {
			Integer.parseInt(user_jubun);
		} catch (NumberFormatException e){
			System.out.println("숫자만 입력하십시오");
			return false;		//숫자가 아니면 false 반환 
		}
		
		if (user_jubun.length() != 7) {			//주번이 7자가 아니면 
			System.out.println("주민번호는 7자만 입력하십시오");
			return false;
			
		} 
		else if(user_jubun.charAt(6) < '1' || user_jubun.charAt(6) > '4') {		//주번이 7자리가  1,2,3,4 가 아니면 
			System.out.println("주민번호 7번째 자리를 올바로 입력하세요");
			return false;
		}
		else {
			this.user_jubun = user_jubun;
			return true;
		}

	}// end of user_jubun----------------------------------------------
	
	
	
	
	
	public String getUser_email() {
		return user_email;
	}
	public boolean setUser_email(String uesr_email) {
		
		// 입력값이 공백일 시
		if (uesr_email.isBlank()) {
			System.out.println("공백은 안됩니다.");
		}
		
		//이메일 패턴을 정의 후 확인, @가 포함되고 도메인에 .이 포함되야 됨
		Pattern p = Pattern.compile("^[A-Za-z][A-Za-z0-9]{0,14}[@][A-Za-z0-9]{1,9}[.][A-Za-z0-9]{1,5}$");

		Matcher m = p.matcher(uesr_email);
		
		
		//규정에 맞는지 확인
		if(m.matches()) {
			this.user_email = uesr_email;
			return true;
		}
		else {
			System.out.println("올바른 이메일 주소를 입력하세요");
			return false;
		}
		
		
		
	}
	
	
	public String getUser_tel() {
		return user_tel;
	}
	public boolean setUser_tel(String user_tel) {

		// 입력값이 공백일 시
		if (user_tel.isBlank()) {
			System.out.println("공백은 안됩니다.");
		}
		
		//전화번호 형식은 ㅇㅇㅇ-ㅇㅇㅇㅇ-ㅇㅇㅇㅇ 형식이어야 한다.
		Pattern p = Pattern.compile("^[0-9]{3}[-][0-9]{4}[-][0-9]{4}$");

		Matcher m = p.matcher(user_tel);

		if (m.matches()) {
			this.user_tel = user_tel;
			return true;
		} else {
			System.out.println("올바른 전화번호를 입력하세요");
			return false;
		}
		
	}
	
	
	public String getUser_address() {
		return user_address;
	}
	public boolean setUser_address(String user_address) {
		
		// 입력값이 공백일 시
		if (user_address.isBlank()) {
			System.out.println("공백은 안됩니다.");
		}
		
		//주소 길이 파악
		if(user_address.length() > 100) {
			System.out.println("주소의 길이가 너무 깁니다");
			return false;
		}
		
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
	//잡타입은 그냥 가지고 오는 거니깐 그냥 내비둠
	public void setJob_type(String job_type) {
		this.job_type = job_type;
	}
	
	
	
}
