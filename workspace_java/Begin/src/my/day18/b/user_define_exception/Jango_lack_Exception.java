package my.day18.b.user_define_exception;

		//== 사용자(개발자)가 정의하는 예외(익셉션)절 클래스 만들기 == //
		/*
		1. Exception 클래스를 상속받아야 한다.
		2. 생성자내에서 예외메시지(오류메시지)를 등록해주면 끝난다. 
		*/

public class Jango_lack_Exception extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//기본생성자
	public Jango_lack_Exception() {
		super(">> 잔고량이 주문량 보다 적으므로 주문이 불가합니다.");
	}
	
	
	//파라미터가 있는 생성자
	public Jango_lack_Exception(String err_msg) {
		super(err_msg);
	}

}
