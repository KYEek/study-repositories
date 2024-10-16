package jdbc.day03;

import java.util.Map;

//DAO(Database Access Object)란?
//Database 에 연결하여 DB와 관련된 업무(SQL)를 실행시켜주는 객체이다.

public interface MemberDAO {

	//회원가입(insert) 메소드
	int memberRegister(MemberDTO member);
	
	
	//로그인 처리(Select) 메소드
	MemberDTO login(Map<String, String> paraMap);
	
	//회원탈퇴(update) 메소드
	int memberDelete(int userseq);
	
	
}
