package my.day17.e.Interface;

import java.util.Scanner;

public interface Ctrl_company {

	// 구인회사 회원가입
	// 구인회사(Company) 신규 회원가입시
	// Company 클래스의 field 의 요구사항에 모두 맞으면
	// CommonMember[] cmber_arr 에 저장시켜주는 메소드 생성하기
	public void register(Scanner sc, CommonMember[] cmber_arr);
	
	public void cp_menu(Scanner sc, Company_imple login_cp, CommonMember[] cmber_arr, Recruit_imple[] rc_arr, Recruit_Apply[] rcApply_arr); //구인회사 전용 메뉴
	
}
