package company.model;

import java.util.Map;

import user.domain.CompanyDTO;

public interface CompanyDAO {

	
	//회사 회원가입
	public int registeCompany(CompanyDTO company);
	
	
	//로그인 메서드
	public CompanyDTO login(Map<String, String> login);
	
}
