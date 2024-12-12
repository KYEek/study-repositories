package my.day16.c.overloading;

public class Main {

	public static void main(String[] args) {
		
		Member mbr = new Member();
		
		mbr.id = "leess";
		mbr.name = "이순신";
		mbr.age = 26;
		mbr.height = 185.7;
		System.out.println(mbr.info());
		
		System.out.println(mbr.info("eomjh", "엄정화", 36, 165.8));
	}

}
