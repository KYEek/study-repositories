package my.day16.e.polymorphism;

import java.text.DecimalFormat;

public class Duck extends Animal {

	// Duck 만 가지는 field 를 정의 (추상화)
	private int price;

	// Duck 만 가지는 method 를 정의 (추상화)
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		if (price > 0) {
			this.price = price;
		}
	}
	
	@Override
	protected void view_info() {

		System.out.println("===== 오리 정보 ====="
				+ "\n1. 성명 : " + super.getName()
				+ "\n2. 생일 : " + super.getBirth_year()
				+ "\n3. 가격 : " + new DecimalFormat("#,###").format(price) + "원\n");
	}

	@Override
	protected void action() {
		System.out.println("[오리 "+super.getName() +"는 물가에서 헤엄을 칩니다.^^]");
	}
	
	public void action_duck() {
		System.out.println("[오리 "+super.getName() +"는 물가에서 헤엄을 칩니다.^^]");
	}

}
