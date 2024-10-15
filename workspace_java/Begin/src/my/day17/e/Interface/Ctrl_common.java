package my.day17.e.Interface;

import java.util.Scanner;

public interface Ctrl_common {

	// == 시작화면(메인 메뉴)를 보여주는 메소드 생성하기 == //
	void main_menu();

	//구직자 및 구인회사 로그인 메소드
	CommonMember login(Scanner sc, CommonMember[] cmber_arr, int type);
	
}
