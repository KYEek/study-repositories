package my.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class MyUtil {
	// == 현재시각을 출력해주는 static 메소드를 생성한다. == //
	public static String current_time() {
		Date now = new Date();
		SimpleDateFormat sdfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//System.out.println("== 현재시각 : " + sdfmt.format(now));
		return sdfmt.format(now);
	}
	
	
	public static String certification_key() {
		String result = "";
		
		Random rndom = new Random();

		
		for(int i= 0; i<3; i++) {
			result += (char)(rndom.nextInt('z'-'a' + 1) + 'a');
		}
		for(int i= 0; i<4; i++) {
			result += rndom.nextInt(9-0+1);
		}
		for(int i= 0; i<3; i++) {
			result += (char)(rndom.nextInt('Z'-'A' + 1) + 'A');
		}
		return result;
	}

	// 소문자3개 와 숫자4개 와 대문자 3개 로 이루어진 랜덤한 인증키 만들기
	// 예:zac5359ARQ

	public static String spaceDelete_for(String str) {
		String result = "";

		if (str != null) {

			for (int i = 0; i < str.length(); i++) {
				char ch = str.charAt(i);

				if (!(ch == ' ')) {
					result += ch;
				}
			}
		}
		return result;
	}

	public static String spaceDelete_while(String str) {
		String result = "";
		int i = 0;
		while (!"".equals(str) && str != null && i < str.length()) {
			char ch = str.charAt(i);

			if (!(ch == ' ')) {
				result += ch;
			}
			i++;
		}
		return result;
	}

	public static String spaceDelete_dowhile(String str) {
		
		String result = "";
		
		if (!"".equals(str) && str != null) {

			int i = 0;
			do {
				char ch = str.charAt(i);

				if (!(ch == ' ')) {
					result += ch;
				}
				i++;
			} while (i < str.length());
		}
		
		return result;
	}
	
	
	// == 비밀번호가 규칙에 맞는지 틀리는지 알려주는 static 메소드 생성하기 == //
	   /*
	      비밀번호 규칙은 비밀번호의 길이는 8글자 이상 15글자 이하이어야 하고,
	      또한 비밀번호는 영문대문자, 영문소문자, 숫자, 특수기호가 혼합되어야만 한다.
	      우리는 규칙에 맞으면 true 를 리턴해주고, 규칙에 틀리면 false 를 리턴하도록 만든다.  
	   */
	
	public static boolean isCheckPasswd (String pwd) { 
		
		//passwd ==> Ab3$
		if(pwd == null)
			return false;
		
		//예를 들어서
		//입력받은 pwd 가 Ab3$ 이라면
		//입력받은 pwd 가 Ab3$fsafawewfwga$%$sdADF 이라면
		if(pwd.length()<8 || pwd.length()>15)
			return false;
		
		//이제 부터는 입력받은 pwd의 글자수가 8글자 이상 15글자 이하인 경우이다
		// 예를들어서
		// 입력받은 pwd 가 C5d#하하호호 이라면 C5d#하하s@! 이라면
		// 또는
		// 입력받은 pwd 가 C5dawxab 이라면 c5dawxab# 이라면
		// 입력받은 pwd 가 C5dawxab@ 이라면 c5dawxab#T 이라면
		
		boolean find_upper = false, find_lawer= false, find_number = false, find_special = false;
		
		
		for (int i = 0; i<pwd.length(); i++) {
			char ch = pwd.charAt(i);
			
			if('가'<= ch && ch <= '힣' ) 
				return false;
			else if(Character.isUpperCase(ch))
				find_upper = true;
			else if(Character.isLowerCase(ch))
				find_lawer = true;
			else if(Character.isDigit(ch))
				find_number = true;
			else
				find_special = true;
			
		}
		
		return find_lawer && find_number && find_special && find_upper;
		
		
	}


	public static long delete_charactor(String money) {
		// money "$2,000,000"
		// money "2,000,000$"
		// money "$2,000,000$"
		
		//money = "2,000,000"
		do {
			int dollar_index = money.indexOf("$");
			
			if(dollar_index == -1)
				break;
			money = money.substring(0, dollar_index) + money.substring(dollar_index+1);
			
		} while (true);
		
		//System.out.println(money);
		
		do {
			int comma_index = money.indexOf(",");
			
			if(comma_index == -1)
				break;
			money = money.substring(0, comma_index) + money.substring(comma_index+1);
			
		} while (true);
		return Long.parseLong(money);
	}

	// === 숫자에 3자리 마다 ,를 추가해서 문자열로 리턴시켜주는 메소드 생성하기 === //
	public static String append_comma(long num) {
		
		// >> 숫자를 문자열로 변경시켜주는 메소드 <<
		/*
		Integer.toString(12345); // ==>"12345"
		Long.toString(1234567890123456789L); //==>"1234567890123456789"
		
		String.valueOf(12345); //==> "12345"
		String.valueOf(1234567890123456789L); ////==>"1234567890123456789"
		*/
		String temp = String.valueOf(num);
		// num 이 2500000 이라면 temp 는 "2500000"
	      // num 이 892500000 이라면 temp 는 "892500000"
		
		char[] origin_arr = temp.toCharArray();
		
		/*
		 	----------------
		 	|2|5|0|0|0|0|0|	==> 길이 7
		 	----------------
		 	
		 	-------------------
		 	|8|9|2|5|0|0|0|0|0|	==> 길이 9
		 	-------------------
		 */
		int comma_cnt = (origin_arr.length%3 == 0)?origin_arr.length/3-1:origin_arr.length/3;
		char[] result_arr = new char[origin_arr.length + comma_cnt];
		
		for (int i = origin_arr.length - 1, count = 1, j = result_arr.length -1; i >= 0; i--,j-- , count++ ) {
			if(count%4 != 0) {
				result_arr[j] = origin_arr[i];
			}
			else {
				result_arr[j] = ',';
				i++;
			}
			
			//|'2'|','|'5'|'0'|'0'|','|'0'|'0'|'0'|
			
			
		}// end of for-----------------------------------
		/*
		 	------------------------------------------------
		 	' '|' '|' '|' '|' '|' '|' '|' '|' '|' '|' '|' '|	==> 길이 7
		 	------------------------------------------------
		 	
		 	-------------------------------------
		 	|'2'|','|'5'|'0'|'0'|','|'0'|'0'|'0'|	==> 길이 7
		 	-------------------------------------
		 	
		 	---------------------------------------------
		 	|'8'|'9'|'2'|','|'5'|'0'|'0'|','|'0'|'0'|'0'|	==> 길이 9
		 	---------------------------------------------
		 */
		
		return String.valueOf(result_arr);
	}

	// === 주민번호 7자리를 입력받아서 올바른 주민번호 인지 검사해주는 메소드 생성 === ///
	public static boolean isCheckJubun(String jubun) {
		
		
		//숫자로만 이루어지지 않았으므로
		try {
			Integer.parseInt(jubun);
		} catch (java.lang.NumberFormatException e) {
			return false;
		}
		// 입력받는 주민번호 길이는 오로지 7글자가 아니므로
		if(jubun.length()!= 7)
			return false;
		
		// 주민번호 마지막 글자는 1 또는 2 또는 3또는 4만 가능하므로
		switch (jubun.substring(6)) {
		case "1":
		case "2":
		case "3":
		case "4":
			break;
		default:
			return false;
		}
		 
		// 달력에 존재하지 않는 날짜이므로
		String str = ("1".equals(jubun.substring(6))||"2".equals(jubun.substring(6)))?"19":"20";
		jubun = str + jubun.substring(0,6);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		
		sdf.setLenient(false);
		// false 로 해주어야만 입력한 값을 날짜 타입으로 변경할때 날짜로 되지 못하는 값일 경우 오류가 발생한다.
		// 날짜로 파싱될 때 허술하게 하지 말고 엄격하게 하라고 설정해주는 것이라고 생각하면 된다.
		
		// === 문자열을 날짜 형태로 변환하기 === //
		try {
			sdf.parse(jubun);
		} catch (java.text.ParseException e) {
			return false;
		}		
		
		
		return true;
	}
	// === 주민번호 7자리를 입력받아서 만 나이를 구해주는 메소드 생성 === ///
	public static int age(String jubun) {
		if(!isCheckJubun(jubun)) {
			System.out.println(jubun + " 은 올바른 주민번호가 아닙니다.");
			return -1;
		}
		int add =0;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		
		
		int current_year = Integer.parseInt(sdf.format(new Date())); 
		
		String str = ("1".equals(jubun.substring(6)) || "2".equals(jubun.substring(6)))?"19":"20";
		
		
		int birth_year = Integer.parseInt(str + jubun.subSequence(0, 2));
		
		//String 을 Date 타입으로 만들기
		try {
		sdf = new SimpleDateFormat("yyyyMMdd");
		Date current_year_birthday = sdf.parse(current_year + jubun.substring(2,6));
		
		add = (current_year_birthday.after(new Date()))?-1:0;
		}
		catch(java.text.ParseException e) {
			
		}
		
		// 현재년도 - 태어난년도 + 0 (올해생일이 현재일과 같거나 이전인 경우)
		// 현재년도 - 태어난년도 + -1 (올해생일이 현재일 보다 이후인 경우)  
		return current_year - birth_year + add;
	}

	public static int start_pension(String birthday) {
		
		//내가 1996년생이라면 ? 년에 만 65세
		//내가 2000년생이라면 ? 년에 만 65세
		// 그러므로 ? = 65 + 1996 or 2000
		String str = ("1".equals(birthday.substring(6))||"2".equals(birthday.substring(6)))? "19":"20";
		
		if(!isCheckJubun(birthday)) {
			System.out.println(birthday + " 은 올바른 주민번호가 아닙니다.");
			return -1;
		}
		
		
		
		return 65+Integer.parseInt(str +birthday.substring(0, 2));
	}

	public static String today() {
		Calendar cal = Calendar.getInstance();
		String result = "";
		String strMonth = (cal.get(Calendar.MONTH)+1 < 10) ? "0" + (cal.get(Calendar.MONTH)+1)
				: String.valueOf(cal.get(Calendar.MONTH)+1);
		String str_day = (cal.get(Calendar.DAY_OF_MONTH) < 10) ? "0" + cal.get(Calendar.DAY_OF_MONTH)
				: String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
		String hour = (cal.get(Calendar.HOUR_OF_DAY) < 10) ? "0" + cal.get(Calendar.HOUR_OF_DAY)
				: String.valueOf(cal.get(Calendar.HOUR_OF_DAY));
		String min = (cal.get(Calendar.MINUTE) < 10) ? "0" + cal.get(Calendar.MINUTE)
				: String.valueOf(cal.get(Calendar.MINUTE));
		String scd = (cal.get(Calendar.SECOND) < 10) ? "0" + cal.get(Calendar.SECOND)
				: String.valueOf(cal.get(Calendar.SECOND));
		String dayname = "";
		switch (cal.get(Calendar.DAY_OF_WEEK)) {
		case 1:
			dayname = "일요일";
			break;
		case 2:
			dayname = "월요일";
			break;
		case 3:
			dayname = "화요일";
			break;
		case 4:
			dayname = "수요일";
			break;
		case 5:
			dayname = "목요일";
			break;
		case 6:
			dayname = "금요일";
			break;
		case 7:
			dayname = "토요일";
			break;
		}

		
		result += cal.get(Calendar.YEAR) + "-" + strMonth + "-" + str_day + " " + hour + ":"
				+ min + ":" + scd + ":" + dayname;
		return result;
	}


	public static String today2() {
		
		Calendar current_now = Calendar.getInstance();//현재시간
		SimpleDateFormat sdfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String dayname="";
		switch (current_now.get(Calendar.DAY_OF_WEEK)) {
		case 1:
			dayname = "일";
			break;
		case 2:
			dayname = "월";
			break;
		case 3:
			dayname = "화";
			break;
		case 4:
			dayname = "수";
			break;
		case 5:
			dayname = "목";
			break;
		case 6:
			dayname = "금";
			break;
		case 7:
			dayname = "토";
			break;
		}
		
		return sdfmt.format(current_now.getTime()) + " " + dayname;
	}
}
