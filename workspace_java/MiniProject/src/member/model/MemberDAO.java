package member.model;



import java.util.Map;

import user.domain.MemberDTO;

public interface MemberDAO {

	//일반 멤버 회원가입
	public int registeMember(MemberDTO member);

	
	//멤버 로그인 
	public MemberDTO login(Map<String, String> login);
	
	
	
}
