package my.day17.b.Final;

//final 로 되어진 클래스는 다른 클래스로 상속해줄 수 없다는 말이다.
//즉, final 로 되어진 클래스는 다른 클래스에서 부모 클래스로 사용될 수 없다는 말이다.
public final class Parent_3 {
	// field
	String id;
	String name;
	
	
	//field 에 final 을 붙이면 더 이상 새로운 값으로 할당할 수 없다.
	final double PI = 3.141592;	//상수 변수
	
	// method
	void greeting() {
		System.out.println("~~~ 건강하세요 ~~~");
	}
	
}
