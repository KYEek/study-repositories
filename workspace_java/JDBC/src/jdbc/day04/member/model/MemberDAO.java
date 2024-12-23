package jdbc.day04.member.model;

import java.util.List;
import java.util.Map;

import jdbc.day04.member.domain.MemberDTO;


public interface MemberDAO {

	
	//회원가입(insert) 메소드
	int memberRegister(MemberDTO member);
	
	
	// 로그인 처리(Select) 메소드
	MemberDTO login(Map<String, String> paraMap);

	
	// 모든 회원을 조회해주(Select)는 메소드
	List<MemberDTO> showALLMember(String sortChoice);
	
	
}
