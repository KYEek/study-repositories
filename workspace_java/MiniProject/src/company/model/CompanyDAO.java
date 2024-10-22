package company.model;

import user.domain.CompanyDTO;

public interface CompanyDAO {

	
	//회사 회원가입
	public int registeCompany(CompanyDTO company);
	
}
