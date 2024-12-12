package my.day09.b.object_array;

public class Student {
	String student_num;
	String name;
	int kor;
	int eng;
	int math;
	int total_num;
	double avg;
	char degree;
	int rank;
	
	Student() {
		student_num = "";
		name = "";
		kor = 0;
		eng = 0;
		math = 0;
		total_num = 0;
		avg = 0.0;
		degree = ' ';
		rank = 1;
	}
	
	public static boolean checkScore(int num) {
		if(num <0 || num >100) 
			return false;
		else
			return true;
	}
	
	String view_info() {
		return student_num + "\t" + name + "\t" + kor + "\t"+ eng + "\t" + math + "\t" + total_num + "\t" + Math.round(avg*10)/10.0 + "\t" + degree + "\t" + rank;
	}
	
	void set_totalnum() {
		total_num = kor + eng + math;	
	}
	
	void set_avgnum() {
		avg = total_num/3.0;	
	}
	
	void setGrade () {
		switch ((int)avg/10) {	//byte, short, int, char, String만 들어올 수 있다
		case 10:
		case 9:
			degree = 'A';
			break;
		case 8:
			degree = 'B';
			break;
		case 7:
			degree = 'C';
			break;
		case 6:
			degree = 'D';
			break;

		default:
			degree = 'F';
			break;
		}
	}
}
