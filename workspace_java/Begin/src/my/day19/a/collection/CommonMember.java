package my.day19.a.collection;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import my.util.MyUtil;


// >> 미완성 클래스 (== 추상 클래스, abstract class)라고 부름 <<
public abstract class CommonMember implements Common{
	// Gujikja 클래스와 Company 클래스에서 공통으로 사용되어지는 field(속성) 생성하기
	
	private String id;				// 아이디
	private String passwd;			// 비밀번호
	private String name;			// 성명 또는 회사명
	private String register_day;	// 가입일자
	
	
	// Gujikja 및 Company 클래스의 부모클래스인 CommonMember 클래스의 기본생성자
	public CommonMember() {
		//System.out.println("== 부모 클래스인 CommonMember 클래스의 기본생성자 호출==");
		
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		register_day = sdf.format(now);
	}
	
	
	// 캡슐화(EnCapsulation == 은닉화) 되어진 field 를 메소드를 통해 접근하도록 만들기 //
	// == getter, setter 하기 ==//
	
//	Gujikja() {
//		Date now = new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		
//		register_day = sdf.format(now);
//		// "2024-08-29 11:45:50"
//	}
	
	
	public String getId() {
		return id;
	}
	
	
	public void setId(String id) {
		if (id == null || id.isBlank()) {
			System.out.println("경고!!╰（‵□′）╯ 아이디는 공백이 아닌 글자로 입력하셔야 합니다.\n");
		} else {
			// userid 는 첫글자는 반드시 영문(대.소문자)자로 시작해야 하고 그나머지 글자는
			// 영문자 또는 숫자로만 이루어져야 한다.!!
			// 그리고 userid 의 길이는 최소 2글자, 최대 10글자로 제한한다.

			// >> 정규표현식(Regular Expression)이란? <<
			// ==> 특정한 규칙을 가진 문자열의 집합을 표현하기 위해 쓰이는 형식언어
			
			// == 1 . 정규표현식(Regular Expression) 패턴을 작성한다. ==//
			Pattern p = Pattern.compile("^[A-Za-z][A-Za-z0-9]{1,9}$");
			// ^은 시작을 의미한다.
			// $는 끝을 의미한다.
			// []는 글자 1개가 들어오는 것을 의미한다.
			// [A-Za-z]는 글자 1개가 들어오는데 영문자만 가능하다.
			// [A-Za-z0-9]는 글자 1개가 들어오는데 영문자 또는 숫자만 가능하다.
			// [A-Za-z0-9]{1,9}은 [A-Za-z0-9] 이 최소 1번 반복, 최대 9번 반복해야 한다.
			
			// == 2. 문자열이 주어진 정규식 패턴과 일치하는지 판별하는 객체를 생성한다. == //
			Matcher m = p.matcher(id);
			
			// == 3.판별하도록 한다. ==//
			if(m.matches()) {

				this.id = id;
			}
			else {
				System.out.println("[경고] 아이디는 첫글자는 영문이어야 하고 나머지는 영문 또는 숫자로만 이루어진 글자길이는 2~10글자만 가능합니다.\n");
			}
			
		}
	}
	
	
	public String getPasswd() {
		return passwd;
	}
	
	
	public void setPasswd(String passwd) {
		// 비밀번호는 영대문자, 영소문자, 숫자, 특수문자가 혼합된 최소 8글자 이상 최대 15글자 이하이어야 한다.
		
		if (MyUtil.isCheckPasswd(passwd)) {
			this.passwd = passwd;
		} else {
			System.out.println("[경고] 비밀번호는 영대문자, 영소문자, 숫자, 특수문자가 혼합된 최소 8글자 이상 최대 15글자 이하이어야 합니다.\n");
		}
	}
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	

	public String getRegister_day() {
		return register_day;
	}
	


	
	///////////////////////////////////////////////////////////////////
	
	
	
	
	
	
	
	
}
