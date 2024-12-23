package my.day18.b.user_define_exception;

public class Product_imple implements Product {
	
//public class Product_imple extends Object implements Product { 와 같은 것이다
// 모든 클래스의 최상위 부모 클래스는 Object 이며, 클래스 생성시 extends 를 하지 않으면 자동적으로 extends Object 가 생략된 것이다. 
	
	// field
	private String prod_name; // 제품명 ("새우깡", "감자깡", "양파링")
	private int jango; // 잔고 (100 , 50 , 150)

	//생성자
	public Product_imple() {	
		
	}
	public Product_imple(String prod_name, int jango) {
		this.prod_name = prod_name;
		this.jango = jango;
		
	}
	
	// method
	public String getProd_name() {
		return prod_name;
	}

	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}

	public int getJango() {
		return jango;
	}

	public void setJango(int jango) {
		this.jango = jango;
	}

	//제품의 정보를 보여주기
	@Override
	public String toString() {
		
		
		
		return "1. 제품명 : " + prod_name
				+"\n2. 잔고량 : " + jango + "개\n";
	}
	
	//주문받기 1
	@Override
	public void order(int jumun_su) throws Jango_lack_Exception {
		// order(int jumun_su) 메소드는 파라미터로 들어오는 jumun_su 의 값에 따라서 
		// 사용자(개발자)가 만든 Jango_lack_Exception 익셉션을 유발 할 수 있다는 말이다. 
		
		if(jango < jumun_su) {
			throw new Jango_lack_Exception();
		}
		else {

			System.out.println(prod_name + " 제품을 " + jumun_su + "개를 주문하셨습니다.");
			jango -= jumun_su;

			System.out.println(toString());
		}
	}
	
	// 주문받기 2
	@Override
	public void jumun(int jumun_su) throws Jango_lack_Exception {
		// order(int jumun_su) 메소드는 파라미터로 들어오는 jumun_su 의 값에 따라서 
		// 사용자(개발자)가 만든 Jango_lack_Exception 익셉션을 유발 할 수 있다는 말이다. 
		
		if(jango < jumun_su) {
			throw new Jango_lack_Exception(prod_name + " 잔고량이 "+jango+ "개 인데 주문량이 "+jumun_su+"개 라서 잔고부족으로 주문이 불가합니다.");
		}
		else {

			System.out.println(prod_name + " 제품을 " + jumun_su + "개를 주문하셨습니다.");
			jango -= jumun_su;

			System.out.println(toString());
		}
	}

}
