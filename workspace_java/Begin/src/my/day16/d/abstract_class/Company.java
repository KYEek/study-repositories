package my.day16.d.abstract_class;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Company extends CommonMember {
	//  Company 클래스는 CommonMember 클래스에 생성되어진 field 및 method 및 기본생성자를 상속 받아온다. 
	//  CommonMember 클래스는 Company 클래스의 부모클래스 가 되어지고,
	//  Company 클래스는 CommonMember 클래스의 자식클래스 가 되어진다.
	
	/*
	public Company() {
		//super(); //super(); 은 Company 클래스의 부모 클래스인 CommonMember 클래스의 기본 생성자이다.
		System.out.println(">> Company 생성자 호출");
		super(); // 부모클래스의 생성자는 반드시 자식클래스의 기본생성자 내에서 맨 처음에 나와야 한다.
	}
	*/
	
	
	//field 생성
	//field 의 캡슐화(EnCapsulation == 은닉화)
	
	private String business_number;		//사업자등록번호
	private String job_type;			//회사직종타입(제조업, 서비스업, IT, ...)
	private long seed_money;			//자본금
	
	static int count;		// Company 객체(인스턴스)의 개수를 알아오려는 용도
	//개본생성자 생략됨!!

	public String getBusiness_number() {
		
		return business_number;
	}

	public void setBusiness_number(String business_number) {
		// 사업자등록번호는 오로지 숫자로만 들어와야 하며
		// 첫글자는 0 이 들어올 수 없다.
		// 또한 사업자 등록번호의 길이는 10글자가 되어야 한다.

		// == 1 . 정규표현식(Regular Expression) 패턴을 작성한다. ==//
		Pattern p = Pattern.compile("^[1-9][0-9]{9}$");
		// ^은 시작을 의미한다.
		// $는 끝을 의미한다.
		// []는 글자 1개가 들어오는 것을 의미한다.
		// [A-Za-z]는 글자 1개가 들어오는데 영문자만 가능하다.
		// [A-Za-z0-9]는 글자 1개가 들어오는데 영문자 또는 숫자만 가능하다.
		// [A-Za-z0-9]{2,9}은 [A-Za-z0-9] 9번 반복해야 한다.
		// [1-9] 는 글자 1개가 들어오는데 1 부터 9 만 가능하다.
		// [0-9]{9} 는 글자 1개가 들어오는데 숫자인 0 부터 9만 가능하다. 이러한 숫자의 개수가 9번 들어와야 한다.

		// == 2. 문자열이 주어진 정규식 패턴과 일치하는지 판별하는 객체를 생성한다. == //
		Matcher m = p.matcher(business_number);

		// == 3.판별하도록 한다. ==//
		if (m.matches()) {

			this.business_number = business_number;
		}
		else {
			System.out.println("(ノ｀Д)ノ사업자등록번호는 숫자로 10자리만 가능합니다. 첫글자는 0이 안돼요!!\n");
		}
	}
	
	

	public String getJob_type() {
		return job_type;
	}

	
	
	public void setJob_type(String job_type) {
		if(job_type == null || job_type.isBlank()) {
			System.out.println("[경고] 회사직종군은 공백이 아닌 글자로 입력하셔야 합니다.\n");
		}
		else {
			this.job_type = job_type;
		}
	}

	
	
	public long getSeed_money() {
		return seed_money;
	}

	
	
	public void setSeed_money(long seed_money) {
		
		if(seed_money <= 0) {
			System.out.println("ㄟ( ▔, ▔ )ㄏ자본금은 0 보다 커야 합니다.\n");
		}
		else {
			this.seed_money = seed_money;
		}
		
	}
	
	 public void title_company() {
		System.out.println("-".repeat(70) + "\n"
						 + " 회사명	업종	사업자등록번호	자본금\n"
					  	 + "-".repeat(70) +"\n");
	}// end of void title()-------------------------------


	 
	@Override
	public String getInfo() {
		
		DecimalFormat df = new DecimalFormat("#,###");
		// 숫자를 3자리 마다 콤마(,)를 찍어주는 것이다.
		
		//		회사명				업종					사업자등록번호				자본金
		return super.getName() + "\t" + job_type  + "\t" + business_number + "\t" + df.format(seed_money)+"원";
	}// end of public String getInfo------------------------------------

	public String view_info() {
		DecimalFormat df = new DecimalFormat("#,###");
		StringBuilder sb = new StringBuilder();

		sb.append(super.getId() + "\t");
		sb.append(super.getPasswd() + "\t");
		sb.append(super.getName() + "\t");
		sb.append(super.getRegister_day().substring(0, 10) + "\t");
		sb.append(getBusiness_number() + "\t");
		sb.append(getJob_type() + "\t");
		sb.append(df.format(getSeed_money()) +"원\n");
		
		
		
		return sb.toString();
	}

	@Override
	public void setName(String name) {
		// 성명은 공백이 없는 한글또는형문으로만 이루어져야 하며 최소 2글자 이상 최대 6글자로만 되어져야 한다.
		if (name == null || name.isBlank()) {
			System.out.println("경고!!╰（‵□′）╯ 회사명은 공백이 아닌 글자로 입력하셔야 합니다.\n");
		} else {

			// == 1 . 정규표현식(Regular Expression) 패턴을 작성한다. ==//
			Pattern p = Pattern.compile("^[가-힣A-Za-z]{2,6}$");

			// == 2. 문자열이 주어진 정규식 패턴과 일치하는지 판별하는 객체를 생성한다. == //
			Matcher m = p.matcher(name);

			// == 3.판별하도록 한다. ==//
			if (m.matches()) {

				super.setName2(name);
			} else {
				System.out.println("[경고](ノ｀Д)ノ 회사명은 공백없이 한글또는 영문으로만 2글자 이상 6글자 이하이어야 합니다.\n");
			}

		}

	}
	
	
}
