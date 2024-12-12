package my.day16.e.polymorphism;

	/*
		---------------------------------------------------------------------------------------------------------------------------
		접근제한자(접근지정자, accessmodifier)   자기자신클래스내부      동일패키지에있는다른클래스      다른패키지에있는하위(자식)클래스       그외의영역  
		--------------------------------------------------------------------------------------------------------------------------- 
		public                                    O                    O                         O                        O  
		protected                                 O                    O                         O                        X
		없음(default)                              O                    O                         X                        X
		private                                   O                    X                         X                        X
	*/   


//>>> 미완성 클래스(== 추상 클래스  == abstract class) <<< 
public abstract class Animal {
	
	//Dog Cat, Duck 의 공통 field (추상화)
	private String name;
	private int birth_year;
	
	//Dog Cat, Duck 의 공통 method(기능) (추상화)

	protected String getName() {
		return name;
	}
	protected void setName(String name) {
		if(name != null && !name.isBlank())
			this.name = name;
	}
	protected int getBirth_year() {
		return birth_year;
	}
	protected void setBirth_year(int birth_year) {
		if(birth_year > 0)
			this.birth_year = birth_year;
	}
	
	// >> 미완성 메소드(== 추상 메소드, abstract method	) <<
	protected abstract void view_info();
	
	protected abstract void action();
	
	
}
