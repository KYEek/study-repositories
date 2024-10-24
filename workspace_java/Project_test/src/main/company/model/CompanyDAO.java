package main.company.model;

import java.util.Map;

import main.user.domain.CompanyDTO;

public interface CompanyDAO {

	
	//회사 회원가입
	public int registeCompany(CompanyDTO company);
	
	
	//로그인 메서드
	public CompanyDTO login(Map<String, String> login);

	//아이디 찾기 메서드
	public String find_ID(CompanyDTO company);

	//입력받은 아이디가 같은지 비교
	public boolean compareID(CompanyDTO company);

	//비밀번호 초기화
	public int reset_passwd(CompanyDTO company);
	
}
