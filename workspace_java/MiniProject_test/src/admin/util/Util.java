package admin.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
	
	// === 시작일 유효성검사 메소드 === //
	public static boolean check_start_date(String event_start) {
		Pattern p = Pattern.compile("^[0-9]{8}$");

		Matcher m = p.matcher(event_start);

		if (!m.matches()){
			return false;
		}
		
		// 달력에 존재하지 않는 날
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");		// 날짜 표기 형식을 연월일로 지정 ==> 19970127

		sdf.setLenient(false);			// 날짜 검사할 때 필수..

		try {
			sdf.parse(event_start);			// 문자열을 날짜포맷으로 변환 ==> 19970127
		} catch (ParseException e) {
			return false;
		}
		return true;					// 모두 통과 시 true 반환
	}// end of check_start_date(String event_start) ----------------------------
	
	// === 종료일 유효성검사 메소드 === //
	public static int check_end_date(String event_start, String event_end) {
		
		Pattern p = Pattern.compile("^[0-9]{8}$");

		Matcher m = p.matcher(event_end);
		
		// 입력 형식에 맞지 않을 경우
		if (!m.matches()){
			return -1;
		}
		
		int start = Integer.parseInt(event_start);
		int end   = Integer.parseInt(event_end);
		
		// 시작일이 끝나는 날보다 뒤일 경우
		if (start > end) {
			return 0;
		}
		
		// 달력에 존재하지 않는 날
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");		// 날짜 표기 형식을 연월일로 지정 ==> 19970127

		sdf.setLenient(false);			// 날짜 검사할 때 필수..

		try {
			sdf.parse(event_start);			// 문자열을 날짜포맷으로 변환 ==> 19970127
		} catch (ParseException e) {
			return -1;
		}
		
		if (start == end) {
			return 1;
		}
		
		return 1;					// 모두 통과 시 1 반환
	}// end of check_start_date(String event_start) ----------------------------
	
}
