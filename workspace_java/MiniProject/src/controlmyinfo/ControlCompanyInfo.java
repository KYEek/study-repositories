package controlmyinfo;


import user.domain.CompanyDTO;
import user.domain.MemberDTO;

public interface ControlCompanyInfo {
	
	
	//일반회원 정보 수정
	int updateinfo(MemberDTO member);

	
	//기업 회원 정보 수정
	int updateinfo(CompanyDTO company);


	
	
	//멤버 비번 변경
	int update_passwd(MemberDTO member, String passwd);

	
	//기업 비번 변경
	int update_passwd(CompanyDTO company, String passwd);

	
	//멤버 회원탈퇴 메서드
	int delete_account(MemberDTO member);

	
	//기업 회원탈퇴 메서드
	int delete_account(CompanyDTO company);

	
	

}
