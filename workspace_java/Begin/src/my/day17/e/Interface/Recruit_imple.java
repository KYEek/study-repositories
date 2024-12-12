package my.day17.e.Interface;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Recruit_imple implements Recruit {
	
	//field
	private int recruit_no;//채용공고 순번(1001부터 시작해서 매번 1씩 증가)
	private Company_imple cp;//회사
	private String subject;//채용제목
	private String work_type;//채용분야(근무형태)
	private int cnt;//채용인원수
	private int yearpay;//연봉
	private String register_day;//채용공고 등록일자
	private String finish_day;//채용공고 마감일자
	
	static int count; //Recruit 객체(인스턴스)의 개수를 알아오려는 용도

	//기본생성사
	public Recruit_imple() {
		recruit_no = count + 1001;
		
		//현재시각
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		
		register_day = sdf.format(now);
	}



	
	//method


	public int getRecruit_no() {
		return recruit_no;
	}
	
	public Company_imple getCp() {
		return cp;
	}

	public void setCp(Company_imple cp) {
		if(cp!=null) {
			this.cp = cp;	
		}
		else {
			System.out.println("[경고] 채용하려면 반드시 회사정보를 입력하세요!!\n");
		}
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		if(subject != null && !subject.isBlank()) {
			this.subject = subject;
		}
		else {
			System.out.println("[경고] 채용제목을 필수로 입력해야 합니다.!!\n");
		}
	}

	public String getWork_type() {
		return work_type;
	}

	public void setWork_type(String work_type) {
		if(work_type != null && !work_type.isBlank()) {
	         this.work_type = work_type;   
	      }
	      else {
	         System.out.println("[경고] 채용분야(근무형태)를 필수로 입력해야 합니다.!!\n");
	      }
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		if(cnt > 0) {
			this.cnt = cnt;
		}
		else {
			System.out.println("[경고] 채용인원수는 1명 이상이어야 합니다.!!\n");
		}
	}

	public int getYearpay() {
		return yearpay;
	}

	public void setYearpay(int yearpay) {
		if(yearpay > 0) {
			this.yearpay = yearpay;
		}
		else {
			System.out.println("연봉은 1이상이어야 합니다.!!");
		}
	}

	public String getFinish_day() {
		return finish_day;
	}

	public void setFinish_day(String finish_day) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		sdf.setLenient(false);
		// false 로 해주어야만 입력한 값을 날짜 타입으로 변경할때 날짜로 되지 못하는 값일 경우 오류가 발생한다.
		// 날짜로 파싱될 때 허술하게 하지 말고 엄격하게 하라고 설정해주는 것이라고 생각하면 된다.
		
		
		try {
			// 채용공고 등록일자 문자열값을 날짜 타입으로 변환
			Date date_register_day = sdf.parse(register_day);
			// 채용공고 등록일자 문자열값을 날짜 타입으로 변환
			Date date_finish_day = sdf.parse(finish_day);
			
			if(date_finish_day.after(date_register_day)) {
				this.finish_day = finish_day;
			}
			else {
				System.out.println("[경고] 채용마감일자는 현재일자 보다 이후인 날짜이어야 합니다.\n");
			}
			

			
			
		} catch (ParseException e) {
			System.out.println("[경고] 채용마감일자는 달력에 존재하는 날짜이어야 합니다.\n");
		}
		
	}


	public String getRegister_day() {
		return register_day;
	}



	// == 채용정보를 알려주는 메소드
	public String recruit_info() {
		StringBuilder sb = new StringBuilder();

		sb.append("=".repeat(50) + "\n");
		sb.append("1. 채용번호 : " + recruit_no + "\n");
		sb.append("2. 채용제목 : " + subject + "\n");
		sb.append("3. 채용분야 : " + work_type + "\n");
		sb.append("4. 채용인원 : " + cnt + "\n");
		sb.append("5. 연봉 : " + new DecimalFormat("#,###").format(yearpay) + "만원\n");
		sb.append("6. 등록일자 : " + register_day.substring(0, 4) + "-" + register_day.substring(4, 6) + "-"
				+ register_day.substring(6) + "\n");
		sb.append("7. 채용마감일자 : " + finish_day.substring(0, 4) + "-" + finish_day.substring(4, 6) + "-"
				+ finish_day.substring(6) + "\n");
		sb.append("=".repeat(50));
		return sb.toString();
	}

	
}
