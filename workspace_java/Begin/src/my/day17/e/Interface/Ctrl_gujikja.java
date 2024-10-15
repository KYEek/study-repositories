package my.day17.e.Interface;

import java.util.Scanner;

public interface Ctrl_gujikja {
	
	// 구직자 회원가입
	// 구직자(Gujikja) 신규 회원가입시
	// Gujikja 클래스의 field 의 요구사항에 모두 맞으면
	// CommonMember[] cmber_arr 에 저장시켜주는 메소드 생성하기
	void register(Scanner sc, CommonMember[] cmber_arr);
	
	// === CommonMEmber[] cmbr_arr 에 구직자가 존재하는지 확인하는 용도에요
	boolean is_Find_gujikja(CommonMember[] cmber_arr);
	
	// 구직자 모두보기
	void view_all_info(CommonMember[] cmber_arr);
	
	// 검색하기
	void search_menu(Scanner sc, CommonMember[] cmber_arr);
	
	// == 구직자 전용 메뉴 메소드 생성하기 == //
	public void gu_menu(Scanner sc, Gujikja_imple login_gu, CommonMember[] cmber_arr, Recruit_imple[] rc_arr, Recruit_Apply[] rcApply_arr);
}
	
