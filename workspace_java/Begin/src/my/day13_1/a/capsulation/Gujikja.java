package my.day13_1.a.capsulation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import my.util.MyUtil;

/*
		   ==== *** 객체지향 프로그래밍(Object Oriented Programming)이란? *** ==== 
	
		   ※ 객체지향(중심)프로그래밍(OOP) 이란?
		      -- field(= attribute, property, 속성) 와 method(= operation, function, 기능, 행위) 로 구성된 클래스를 통해 객체(= instance, 인스턴스)를 만들고, 
		         데이터처리와 같은 상호작용을 만들어진 객체를 가지고 프로그래밍화 하는것을 OOP 라고 부른다.
	
		    예를 들어, 
		    쇼핑몰 프로그램에 있어서 어떤 회원이 어떤 제품을 주문하는것을 프로그래밍 하려고 한다. 
		    주문이라는 데이터 처리를 위해서는 어떤 회원이 어떤 제품을 언제 몇 개를 주문했다라는 정보를 담아야 한다. 
		    이것을 위해서 "주문" 이라는 클래스를 만드는데 이 "주문" 클래스의 field 로 "회원" 클래스와 "제품" 클래스가 들어가게 된다. 
		    또한 "주문" 클래스의 method 로 주문결과를 화면에 보여주는 기능이 들어가야 할 것이다. 
		    이와같이 "회원" 클래스, "제품" 클래스, "주문" 클래스를 통해 객체를 만들고 객체들간의 상호작용을 논리적으로 프로그래밍 하는것을 OOP라고 부른다.
	
	
		    ※ OOP 의 4가지 특징(!!!! 필수암기 !!!!)
		    --> 1. 상속성(Inheritance) : 클래스의 재사용과 소스 코드의 중복제거를 목적으로 하는 기술 
		    --> 2. 추상화(Abstraction) : 프로그램이 필요로 하는 실제 데이터들을 모델링하는 기술 
		    --> 3. 캡슐화(EnCapsulation == 은닉화) : 객체지향의 가장 중요한 데이터 보호 기술   
		    --> 4. 다형성(Polymorphism) : 상속을 이용하여 여러 클래스 타입을 하나의 클래스 타입으로 다루는 기술
		 
		 
		    구인구직 어플리케이션 프로그래밍 제작시 
		    필요한 요소(부품)가 무엇이 있는지 생각해보자.
		    아주 다양하고, 많은 요소들이 필요할 것이다.
	
		    예를 들어,
		    구직자, 구인회사, 채용.... 등등등 많이 있다.
		    이러한 요소(부품)들이 바로 객체인데 이 객체를 만드는 설계도면이 클래스이다.
	
		    위와(구인구직 어플리케이션 프로그래밍 제작) 같이 현실세계에 존재하는
		    다양한 객체들을 각각 하나하나 추상화 시켜서 클래스로 옮기는 것을
		    객체지향 프로그래밍(OOP)이라고 한다.
	
		    여기서 추상화(abstraction)란?
		    --> 일반적인 의미의 추상화란 어떤 물체(object)에서 주된 특징을 부각시켜 표현하고, 
		        나머지 부분은 과감하게 생략하는 것을 말한다.
		        추상화의 반대는 아주 시시콜콜한것(세세한 것)까지 전부 몽땅 다 묘사하는 것을 말하는데 이것을 정밀화 라고 말한다.
		 
		    객체지향 프로그래밍(OOP)에서 사용되는 추상화도 이와 비슷하다.
		    어떤 하나의 물체를 대표하는 속성(명사, 멤버변수)과 기능(동사, 메소드)을 추출해내는 것을 
		    객체지향 프로그래밍(OOP)에서 "추상화" 라고 한다. 
	
		    >>> 예를 들어, 구인구직 어플리케이션 프로그래밍 제작시
		        필요한 부품(요소)중 하나인 "구직자" 에 대해서 알아보자.
		        "구직자" 가 가지고 있는 속성(명사, 멤버변수)과 행동양식(기능, 동사, 메소드)을 뽑아보자.
	
		    1). 속성(property, attribute, field)은 멤버변수가 되고,
		    2). 행동양식(기능)은 메소드가 되는 것이다.
	
		    Gujikja(클래스)
		    |
		    |-- 속성(property, attribute, field) : 아이디, 암호, 성명, 연락처, 이메일, 주소, 학력, 희망구직직종, 희망연봉.....  
		    |-- 행동양식(기능, 메소드) : 구직자로 접속(로그인)할수 있는 기능, 구직자정보를 조회해주는 기능, 구직자정보를 변경해주는 기능, ....... 
	
		*/   

public class Gujikja {
	
		/*
			    ---------------------------------------------------------------------------------------------------------------------------
			   접근제한자(접근지정자, accessmodifier)   자기자신클래스내부      동일패키지에있는다른클래스      다른패키지에있는하위(자식)클래스       그외의영역  
			   --------------------------------------------------------------------------------------------------------------------------- 
			   public                                    O                    O                         O                        O  
			   protected                                 O                    O                         O                        X
			   없음(default)                              O                    O                         X                        X
			   private                                   O                    X                         X                        X
		 */   
	
	
	//field 생성
	//field의 캡슐화 (Encapsulation) == 은닉화
	
	private String userid;			// 아이디
	private String passwd;			// 비밀번호
	private String name;			// 성명
	private String jubun;			// 주민번호인데 앞자리 6자리에 + 성별을 나타내는 1자리까지만 입력한다.
							// 예: "9506201"  "9607202"   "0006203"  "0007204"  "1106203"
	private String register_day;	// 가입일자
	
	static int count;		// Gujikja 객체(인스턴스)의 개수를 알아오려는 용도
	
	//기본생성자
	Gujikja() {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		register_day = sdf.format(now);
		// "2024-08-29 11:45:50"
	}
	
	
	//method 생성
	
	// 캡슐화(EnCapsulation == 은닉화) 되어진 field 를 메소드를 통해 접근하도록 만들기 //
	// == getter, setter 하기
	
	public void setUserid (String userid) {

		if (userid == null || userid.isBlank()) {
			System.out.println("경고!!╰（‵□′）╯ 아이디는 공백이 아닌 글자로 입력하셔야 합니다.\n");
		} else {
			// userid 는 첫글자는 반드시 영문(대.소문자)자로 시작해야 하고 그나머지 글자는
			// 영문자 또는 숫자로만 이루어져야 한다.!!
			// 그리고 userid 의 길이는 최소 3글자, 최대 10글자로 제한한다.

			// >> 정규표현식(Regular Expression)이란? <<
			// ==> 특정한 규칙을 가진 문자열의 집합을 표현하기 위해 쓰이는 형식언어
			
			// == 1 . 정규표현식(Regular Expression) 패턴을 작성한다. ==//
			Pattern p = Pattern.compile("^[A-Za-z][A-Za-z0-9]{2,9}$");
			// ^은 시작을 의미한다.
			// $는 끝을 의미한다.
			// []는 글자 1개가 들어오는 것을 의미한다.
			// [A-Za-z]는 글자 1개가 들어오는데 영문자만 가능하다.
			// [A-Za-z0-9]는 글자 1개가 들어오는데 영문자 또는 숫자만 가능하다.
			// [A-Za-z0-9]{2,9}은 [A-Za-z0-9] 이 최소 2번 반복, 최대 9번 반복해야 한다.
			
			// == 2. 문자열이 주어진 정규식 패턴과 일치하는지 판별하는 객체를 생성한다. == //
			Matcher m = p.matcher(userid);
			
			// == 3.판별하도록 한다. ==//
			if(m.matches()) {

				this.userid = userid;
			}
			else {
				System.out.println("[경고] 아이디는 첫글자는 영문이어야 하고 나머지는 영문 또는 숫자로만 이루어진 글자길이는 3~10글자만 가능합니다.\n");
			}
			
		}
	}
	
	public String getUserid() {
		return userid;
	}
	
	
	public String getName() {
		return name;
	}


	public String getJubun() {
		return jubun;
	}


	/*
	public void setPasswd (String passwd) {
		
	
	}
	*/
	public void setPasswd(String passwd) {
		// 비밀번호는 영대문자, 영소문자, 숫자, 특수문자가 혼합된 최소 8글자 이상 최대 15글자 이하이어야 한다.
		
		if (MyUtil.isCheckPasswd(passwd)) {
			this.passwd = passwd;
		} else {
			System.out.println("[경고] 비밀번호는 영대문자, 영소문자, 숫자, 특수문자가 혼합된 최소 8글자 이상 최대 15글자 이하이어야 합니다.\n");
		}
	}
	public String getPasswd() {
		return passwd;
	}

	/*		원래 하던 방식-----------------------------------------------------------
	
	public void setName(String name) {
		// 성명은 공백이 없는 한글로만 이루어져야 하며 최소 2글자 이상 최대 6글자로만 되어져야 한다.
		if (name != null) {
			char[] ch_arr = name.toCharArray();

			boolean isOnly_hangul = true;
			for (char ch : ch_arr) {
				if (!('가' <= ch && ch <= '힣')) {
					isOnly_hangul = false;
					break;
				}
			} // end of for-----------------

			if (2 <= ch_arr.length && ch_arr.length <= 6 && isOnly_hangul) {
				this.name = name;
			} else {
				System.out.println("[경고] 성명은 공백이 없는 한글로만 이루어져야 하며 최소 2글자 이상 최대 6글자로만 되어져야 합니다.\n");
			}
		}
		
		
		
	}

		-------------------------------------------------------------------------*/

	// 성명을 정규표현식으로 검사해 보겠다
	public void setName(String name) {
		// 성명은 공백이 없는 한글로만 이루어져야 하며 최소 2글자 이상 최대 6글자로만 되어져야 한다.
		if (name == null || name.isBlank()) {
			System.out.println("경고!!╰（‵□′）╯ 아이디는 공백이 아닌 글자로 입력하셔야 합니다.\n");
		} else {
			
			// == 1 . 정규표현식(Regular Expression) 패턴을 작성한다. ==//
			Pattern p = Pattern.compile("^[가-힣]{2,6}$");
			
			// == 2. 문자열이 주어진 정규식 패턴과 일치하는지 판별하는 객체를 생성한다. == //
			Matcher m = p.matcher(name);
			
			// == 3.판별하도록 한다. ==//
			if(m.matches()) {

				this.name = name;
			}
			else {
				System.out.println("[경고](ノ｀Д)ノ 성명은 공백없이 한글로만 2글자 이상 6글자 이하이어야 합니다.\n");
			}
			
		}
	}
	
	
	public String getRegister_day() {
		return register_day;
	}


	public void setJubun(String jubun) {
		if(MyUtil.isCheckJubun(jubun)) {
			this.jubun = jubun;
		}
		else {
			System.out.println("[경고](ノ｀Д)ノ 올바른 주민번호를 입력하세요.\\n");
		}
	}


	//==== 구직자의 정보를 한줄로 리턴해주는 메소드 생성하기 =====
	String getInfo() {
		//eomjh  qWe******	엄정화	1986-10-20 여	37		2024-08-29 15:20:09
		StringBuilder sb = new StringBuilder();
		
		sb.append(userid+"\t");
		sb.append(passwd.subSequence(0, 3)+"*".repeat(passwd.length()-3)+"\t");
		sb.append(name+"\t");
		/*
		if("1".equals(jubun.substring(6))||"2".equals(jubun.substring(6)) ) {
			
		}
		*/
		//또는
		String str;
		if(jubun.endsWith("1")||jubun.endsWith("2")) {
			str ="19";
		}
		else {
			str = "20";
		}
		sb.append(str + jubun.substring(0,2) + "-" + jubun.substring(2,4) + "-" + jubun.substring(4,6) + "\t");
		
		sb.append(gender()+"\t");
		sb.append(age()+ "\t");
		sb.append(register_day);
		
		return sb.toString();
	}


	


	int age() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String str;
		int current_year = Integer.parseInt(sdf.format(new Date()));
		int add = 0;	//add 생일이 지났으면 0 안지나면 -1을 하기 위한 것
		if(jubun.endsWith("1")||jubun.endsWith("2")) {
			str ="19";
		}
		else {
			str = "20";
		}
		
		int birth_year = Integer.parseInt(str + jubun.substring(0,2));
		
		//생일이 지났는지 판단하기 위한 부분　東京フラッシュ
		try {
			sdf = new SimpleDateFormat("yyyyMMdd");
			Date current_year_birthday = sdf.parse(current_year + jubun.substring(2,6));
			
			add = (current_year_birthday.after(new Date()))?-1:0;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//-----------------------誕生日判断終わり--------------
		return current_year - birth_year + add;
	}


	String gender() {
		String result;
		
		if(jubun.endsWith("1")||jubun.endsWith("3")) {
			result = "남";
		}
		else {
			result = "여";
		}
		return result;
	}
}
