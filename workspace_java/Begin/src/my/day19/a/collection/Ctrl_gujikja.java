package my.day19.a.collection;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public interface Ctrl_gujikja {
	
	// 구직자 회원가입
	// 구직자(Gujikja) 신규 회원가입시
	// Gujikja 클래스의 field 의 요구사항에 모두 맞으면
	// CommonMember[] cmber_arr 에 저장시켜주는 메소드 생성하기
	void register(Scanner sc,List<Gujikja_imple> gu_list,  Map<String, Gujikja_imple> gu_map);
	
	
	// 구직자 모두보기
	void view_all_info(List<Gujikja_imple> gu_list);
	
	// == 구직자 전용 메뉴 메소드 생성하기 == //
	public void gu_menu(Scanner sc, Gujikja_imple login_gu, List<Company_imple> cp_list, List<Recruit_imple> rc_list, List<Recruit_Apply> rcapply_list);
	
	// 검색하기
	void search_menu(Scanner sc, List<Gujikja_imple> gu_list);
	
	
	

	Gujikja_imple login(Scanner sc, Map<String, Gujikja_imple> gu_map);
}
	
