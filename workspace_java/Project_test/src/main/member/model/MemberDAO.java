package main.member.model;



import java.util.Map;

import main.user.domain.MemberDTO;

public interface MemberDAO {

	//일반 멤버 회원가입
	public int registeMember(MemberDTO member);

	
	//멤버 로그인 
	public MemberDTO login(Map<String, String> login);

	//아이디 찾기
	public String find_ID(MemberDTO member);

	//입력 받은 아이디가 같은지 비교
	public boolean compareID(MemberDTO member);

	//비밀번호 초기화
	public int reset_passwd(MemberDTO member);

	
	
	
}
