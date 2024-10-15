package my.day08.b.object_array;

public class Main_test {

	public static void main(String[] args) {
		
		String str1 = "      ";
		String str2 = "";
		String str3 = "a";
		
		System.out.println(str1.isEmpty());
		System.out.println(str2.isEmpty());
		System.out.println(str3.isEmpty());

		System.out.println("-------------------------");
		
		System.out.println(str1.isBlank());
		System.out.println(str2.isBlank());
		System.out.println(str3.isBlank());
		

		System.out.println("-------------------------");
		

		String str4 = "aaa";
		String str5 = "    b b b    ";
		String str6 = "ccc";
		
		System.out.println(str4 + str5 + str6);
		System.out.println(str4 + str5.trim() + str6);
		

		System.out.println("-------------------------");
		
		if("".equals(str1)) {
			System.out.println("str1 은 \"\" 입니다.");
		}
		if("".equals(str1.trim())) {
			System.out.println("str1.trim()은 \"\" 입니다.");
		}
		
		System.out.println(str1.trim().isEmpty());	//true
		System.out.println(str2.trim().isEmpty());
		

		System.out.println(str1.isBlank());
		System.out.println(str2.isBlank());
		/*
			isEmpty()는 JDK 6 부터 사용가능한 메소드로서
	        문자열의 길이를 체크하여, 문자열의 길이가 0인 경우에만 true를 리턴
	        그러므로 빈 공백만 들어있는 문자열은 false를 리턴.
	
	        isBlank()는 JDK 11 부터 사용가능한 메소드로서
	        문자열이 비어 있거나, 빈 공백만으로 되어 있는 경우에 true를 리턴
	        그러므로 "      " 도 true를 리턴.
		 */

	}

}
