package my.day13_16.b.inheritance;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Recruit_Apply {
	
	//field
	private Recruit rc;	// 채용 공고
	private Gujikja gu;	// 구직자
	private String apply_motive;	// 지원 동기
	private String register_day;
	
	static int count; //RecruitApply 객체의 개수를 알아오려는 용도
	
	
	//기본생성자
	public Recruit_Apply() {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		register_day = sdf.format(now);
		
	}
	//method


	public Recruit getRc() {
		
		return rc;
	}


	public void setRc(Recruit rc) {
		if(rc != null) {
	         this.rc = rc;
	      }
	      else {
	         System.out.println("[경고] 채용에 응모하려면 반드시 채용정보를 입력하세요!!\n"); 
	      }
	}


	public Gujikja getGu() {
		return gu;
	}


	public void setGu(Gujikja gu) {
		if(gu != null) {
	         this.gu = gu;
	      }
	      else {
	         System.out.println("[경고] 채용에 응모하려면 반드시 구직자정보를 입력하세요!!\n");
	      }
	}


	public String getApply_motive() {
		return apply_motive;
	}


	public void setApply_motive(String apply_motive) {
		if(apply_motive != null && !apply_motive.isBlank()) {
	         this.apply_motive = apply_motive;   
	      }
	      else {
	         System.out.println("[경고] 입사지원동기는 필수로 입력해야 합니다.!!\n");
	      }
	}


	public String getRegister_day() {
		return register_day;
	}
	
	
}
