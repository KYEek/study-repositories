package my.day16.e.polymorphism;

public class Main2 {

	// >>> 다형성(Polymorphism) <<<
	// ==> 상속을 이용하여 여러 클래스 타입을 하나의 클래스 타입으로 다루는 기술.
	// 자식클래스로 생성되어진 객체를 부모 클래스 타입으로 받을 수 있다는 것이 다형성(Polymorphism)이다.!!
	
	public static void main(String[] args) {
		
		// Animal an = new Animal(); ==> 오류!!
				// 미완성 클래스(== 추상 클래스 == abstract class)를 사용하여 객체(인스턴스)를 생성할 수 없다.!!

				Animal[] ani_arr = new Animal[15];
				// 미완성 클래스는 스스로 객체생성(인스턴스화)은 불가하지만
				// 일반적인 자식클래스로 생성된 객체를 저장하는 용도로 사용이 가능하다.!!!
				
				Dog dog1 = new Dog();

				dog1.setName("뽀삐");
				dog1.setBirth_year(2022);
				dog1.setWeight(3);

				Dog dog2 = new Dog();

				dog2.setName("뽀삐");
				dog2.setBirth_year(2022);
				dog2.setWeight(3);

				Dog dog3 = new Dog();

				dog3.setName("뽀삐");
				dog3.setBirth_year(2022);
				dog3.setWeight(3);
				
				ani_arr[0] = dog1;
				ani_arr[1] = dog2;
				ani_arr[2] = dog3;
				
				Cat cat1 = new Cat();
				cat1.setName("톰");
				cat1.setBirth_year(2021);
				cat1.setColor("검정");
				Cat cat2 = new Cat();
				cat2.setName("톰");
				cat2.setBirth_year(2021);
				cat2.setColor("검정");
				Cat cat3 = new Cat();
				cat3.setName("톰");
				cat3.setBirth_year(2021);
				cat3.setColor("검정");
				
				ani_arr[3] = cat1;
				ani_arr[4] = cat2;
				ani_arr[5] = cat3;
				
				Duck duck1 = new Duck();
				duck1.setName("도널드");
				duck1.setBirth_year(2023);
				duck1.setPrice(5000);
				
				Duck duck2 = new Duck();
				duck2.setName("도널드");
				duck2.setBirth_year(2023);
				duck2.setPrice(5000);
				
				Duck duck3 = new Duck();
				duck3.setName("도널드");
				duck3.setBirth_year(2023);
				duck3.setPrice(5000);
				
				
				ani_arr[6] = duck1;
				ani_arr[7] = duck2;
				ani_arr[8] = duck3;
				
				// 액션을 보기 위한 for
				for(int i = 0; i < ani_arr.length; i++) {
					if(ani_arr[i] != null) {
						ani_arr[i].action();
					}
				}// end of for---------------------------------------------
			}

}
