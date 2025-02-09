package chap04;

public class PersonDTO {
	private String name;
	private String school;
	private String color;
	private String[] food;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String[] getFood() {
		return food;
	}
	public void setFood(String[] food) {
		this.food = food;
	}
	public String getStrFood() {
		if(food != null) {
			return String.join(",", food);
		}
		else {
			return "";
		}
	}
}
