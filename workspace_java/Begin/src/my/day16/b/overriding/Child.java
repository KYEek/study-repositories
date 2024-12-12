package my.day16.b.overriding;

public class Child extends Parent{
	
	
	
	@Override	//권장사항, 필수아님, @는 애노테이션(어노테이션)이라고 함
	public String info() {
		return "아이디 : " + super.id + "\n"
				+ "비밀번호 : " + super.passwd; 
	}
	// 메소드의 오버라이딩(overriding) == 메소드의 재정의
	// 메소드의 오버라이딩(overriding) 시 접근제한자는 부모클래스에서 정의해둔 메소드의 접근제한자와 같거나 또는 허용이 더 큰 것을 해야한다.
	// 또한 메소드의 오버라이딩(overriding) 시 껍데기(리턴타입 메소드명 파라미터)는 똑 같아야 한다.
	// 알맹이(내용물)는 새롭게 정의해서 만들면 된다.

}
