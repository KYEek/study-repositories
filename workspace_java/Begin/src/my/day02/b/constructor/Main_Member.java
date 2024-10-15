package my.day02.b.constructor;

public class Main_Member {

	public static void main(String[] args) {
		
		Member hongkd_mbr = new Member();
		hongkd_mbr.userid = "hongkd";
		hongkd_mbr.passwd = "qwer1234";
		hongkd_mbr.name = "홍길동";
		hongkd_mbr.age =30;
		hongkd_mbr.point = 100;
		
		hongkd_mbr.info_print();
		
		
		////////////////////////////////////////////
		
		Member eomjh_mbr = new Member("eomjh", "abcd", "엄정화", 27, 200);
		eomjh_mbr.info_print();
		
		
		////////////////////////////////////////////
		Member leess_mbr = new Member("leess", "qwer1234$", "이순신", "less@gmail.com", 29, 300);
		leess_mbr.info_print();
		
		////////////////////////////////////////////
		// === 퀴즈 === //
		
		leess_mbr.update("leess", "wxyz", "순신이", "sunsin@naver.com", 30, 500);
		
	}

}
