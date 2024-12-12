package my.day16.e.polymorphism;

public class Cat extends Animal {

	// Cat 만 가지는 field 를 정의 (추상화)
	private String color;

	// Cat 만 가지는 method 를 정의 (추상화)
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
	      if(color != null && !color.isBlank()) {
	         this.color = color;
	      }
	}
	
	
	@Override
	protected void view_info() {
		System.out.println("===== 고양이 정보 ====="
				+ "\n1. 성명 : " + super.getName()
				+ "\n2. 생일 : " + super.getBirth_year()
				+ "\n3. 색깔 : " + color + "색\n");

	}

	@Override
	protected void action() {
		System.out.println("[고양이 "+super.getName() +"은 점프를 합니다.!!!]");
	}
	
	public void action_cat() {
		System.out.println("[고양이 "+super.getName() +"은 점프를 합니다.!!!]");
	}

}
