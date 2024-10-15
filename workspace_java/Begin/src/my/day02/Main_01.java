package my.day02;


import my.util.MyUtil;

// 멤버변수 ==> instance 변수와 static 변수를 합친것을 멤버변수라고 부른다.
// >>> 멤버변수와 local(지역)변수의 차이점에 대해서 알아본다.


public class Main_01 {
	
	//instance 변수
	String id; 		// String 은 문자열 타입
	String pw;
	String name;
	String email;
	int age;		//int 는 정수타입
	double height;  //double 은 실수타입
	char grade;		//char 는 문자형
	
/*
    멤버변수(instance 변수와 static 변수를 합친것)는 
    즉, instance 변수와 static 변수는 초기화를 하지 않더라도 자동적으로 초기화가 되어진다.
    정수형인 데이터타입(byte, short, int, long)은 자동적으로 0 으로 초기화가 되고, 
    실수형인 데이터타입(float, double)은 자동적으로 0.0 으로 초기화가 되고,
    문자형인 데이터타입(char)는 자동적으로 ' ' 으로 초기화 되고,
    String 을 포함한 클래스 타입은 자동적으로 null 로 초기화가 된다.
    
    ※ 초기화란? 변수에 어떤값을 부여하는 것을 초기화라고 부른다. 
*/
	
	
	// instance 메소드
	void info_print(int money, String per_address) {
		
		int hope_money = money; 
		// 지역변수(local variable)는 반드시 초기화(== 변수에 어떤 값을 부여하는것)를 꼭 해주어야 한다.!!!
		// 지역변수는 { } 내에서만 사용되는 것으로 { } 를 벗어나는 순간 지역변수는 자동적으로 메모리(RAM)에서 삭제가 된다. !!
		
		String address = per_address;
		
		System.out.println("=== 회원정보 1===\n"  
						+ "1. 아이디 : " + id + "\n"
						+ "2. 비밀번호 : " + pw + "\n"
						+ "3. 성명 : " + name + "\n"
						+ "4. 이메일 : " + email + "\n"
						+ "5. 나이 : " + age + "\n"
						+ "6. 신장 : " + height + "\n"
						+ "7. 등급 : " + grade + "\n"
						+ "8. 희망급여 : " + hope_money + "$\n"
						+ "9. 주소 : " + address + "\n"
						+ "10. 현재시각 : " + MyUtil.current_time() +"\n");
		
		/*
        문자열 과 문자열 사이의 + 는 문자열결합을 뜻하는 것이고,
        숫자 와 숫자 사이의 + 는 더하기(plus)를 뜻하는 것이고,
        문자열 과 숫자 사이의 + 는 문자열결합을 뜻하는 것이다.
     */
		
	}
	
	void info_view() {
			
			int hope_money = 9000; 
			// 지역변수(local variable)는 반드시 초기화(== 변수에 어떤 값을 부여하는것)를 꼭 해주어야 한다.!!!
			// 지역변수는 { } 내에서만 사용되는 것으로 { } 를 벗어나는 순간 지역변수는 자동적으로 메모리(RAM)에서 삭제가 된다. !!
			
			String address = "서울시 종로구";
			
			System.out.println("=== 회원정보 2===\n"  
							+ "1. 아이디 : " + id + "\n"
							+ "2. 비밀번호 : " + pw + "\n"
							+ "3. 성명 : " + name + "\n"
							+ "4. 이메일 : " + email + "\n"
							+ "5. 나이 : " + age + "\n"
							+ "6. 신장 : " + height + "\n"
							+ "7. 등급 : " + grade + "\n"
							+ "8. 희망급여 : " + hope_money + "$\n"
							+ "9. 주소 : " + address + "\n");
			
			/*
	        문자열 과 문자열 사이의 + 는 문자열결합을 뜻하는 것이고,
	        숫자 와 숫자 사이의 + 는 더하기(plus)를 뜻하는 것이고,
	        문자열 과 숫자 사이의 + 는 문자열결합을 뜻하는 것이다.
	     */
			
		}
	
	/*
	void info_input(String per_id, String per_pw, String per_name, String per_email, int per_age, double per_height, char per_grade) {
		id = per_id;
		pw = per_pw;
		name = per_name;
		email = per_email;
		age = per_age;
		height = per_height;
		grade = per_grade;
		
	}
	*/
	
	public static void main(String[] args) {
		Main_01 per1 = new Main_01();
		
		//per1.info_input("wasd", "qwer","규영", "qwer@naver.com", 15, 173, 'A');
		per1.id = "wasd";
		per1.pw = "qwer";
		per1.name = "연규영";
		
		
		
		per1.info_print(80000, "서울시 마포구 창전동");
		per1.info_view();
	}

}
