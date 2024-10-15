package my.day02.a.dataType;

public class Main_Sungjuk {

	public static void main(String[] args) {
		
	Sungjuk lss_sungjuk = new Sungjuk();
	lss_sungjuk.hakbun = "091234";
	lss_sungjuk.name = "이순신";
	lss_sungjuk.kor_point = 70;
	lss_sungjuk.eng = 96;
	lss_sungjuk.math = 100;
	
	lss_sungjuk.sungjuk_print();
	System.out.println("\n================\n");
	Sungjuk.char_print();
	
	

	System.out.println("\n################\n");
	Sungjuk.boolean_print();
	
	}
	
	

}
