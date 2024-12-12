package my.day17.b.Final;

public class Parent_1 {
	// field
	String id;
	String name;
	
	
	//field 에 final 을 붙이면 더 이상 새로운 값으로 할당할 수 없다.
	final double PI = 3.141592;	//상수 변수
	
	//method
	void greeting() {
		System.out.println("~~~ 안녕하세요 ~~~");
	}
	

	// 메소드에 final을 붙이면 자식 클래스에서 메소드 오버라이딩(재정의)을 할 수 없게 된다.
	final void rule() {
		System.out.println("~~~ 정직하게 삽시다 ~~~");
	}
	
}
