package my.day19.a.collection;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public interface Ctrl_company {

	// 구인회사 회원가입
	// 구인회사(Company) 신규 회원가입시
	// Company 클래스의 field 의 요구사항에 모두 맞으면
	// CommonMember[] cmber_arr 에 저장시켜주는 메소드 생성하기
	public void register(Scanner sc, List<Company_imple> cp_list, Map<String, Company_imple> cp_map);
	
	public void cp_menu(Scanner sc, Company_imple login_cp, List<Gujikja_imple> gu_list, List<Recruit_imple> rc_list, List<Recruit_Apply> rcapply_list); //구인회사 전용 메뉴
	

	Company_imple login(Scanner sc, Map<String, Company_imple> cp_map);
}
