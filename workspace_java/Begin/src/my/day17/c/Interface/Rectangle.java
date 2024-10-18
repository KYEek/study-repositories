package my.day17.c.Interface;

public class Rectangle implements Figure {
// Rectangle 이라는 클래스는 Figure 라는 인터페이스를 implements(구현) 한다는 말이다.
	@Override
	public double area(double x, double y) {
		return x * y;
	}

	@Override
	public double circle_area(double r) {
		return 0;
	}

	@Override
	public double circle_area(double x, double y) {
		return 0;
	}

}
