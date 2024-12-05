package member.model;

import java.sql.SQLException;

import member.domain.MemberVO;

public interface MemberDAO {
	//회원가입을 해주는 메소드(tbl_member 테이블에 insert)
	int registerMember(MemberVO member) throws SQLException;
//ID 중복검사
	boolean idDuplicatedCheck(String userid) throws SQLException;
}
