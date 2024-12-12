package my.day18.c.collection;

import my.util.MyUtil;

public class Member {

	private String id;
	private String passwd;
	private String name;
	private String jubun;
	
	public Member() {}
	
	public Member(String id, String passwd, String name, String jubun) {
		this.id = id;
		this.passwd = passwd;
		this.name = name;
		this.jubun = jubun;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJubun() {
		return jubun;
	}

	public void setJubun(String jubun) {
		this.jubun = jubun;
	}
	
	
	public String gender() {
		
		if(jubun == null) {
			return "";
		}
		
		else {
			if("1".equals(jubun.substring(6)) || 
			   "3".equals(jubun.substring(6)) ) {
				return "남";
			}
			else {
				return "여";
			}
		}
		
	}// end of public String gender()--------------------------------
		
	
	// 메소드의 오버라이딩(재정의) //
	@Override
	public String toString() {
	    
		return "=== "+name+"님의 회원정보 ===\n"
	    	 + "1.아이디 : "+id+"\n"
	    	 + "2.비밀번호 : "+passwd+"\n"
	    	 + "3.성명 : "+name+"\n"
	    	 + "4.주민번호 : "+jubun+"\n"
	    	 + "5.성별 : "+gender()+"\n"
	    	 + "6.만나이 : "+MyUtil.age(jubun)+"세\n";
		
	}
	
	
}
