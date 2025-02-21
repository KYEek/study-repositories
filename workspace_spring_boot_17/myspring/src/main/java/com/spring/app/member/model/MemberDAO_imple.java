package com.spring.app.member.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.spring.app.member.domain.MemberVO;

// === #13. Repository(DAO) 선언 ===
@Repository
public class MemberDAO_imple implements MemberDAO {

	@Autowired
	@Qualifier("sqlsession")
	private SqlSessionTemplate sqlsession;

	
	// === #17. 로그인 처리하기 === //
	@Override
	public MemberVO getLoginMember(Map<String, String> paraMap) {
		
		MemberVO loginuser = sqlsession.selectOne("member.getLoginMember", paraMap);
		return loginuser;
	}

	// tbl_loginhistory 테이블에 insert 해주기
	@Override
	public void insert_tbl_loginhistory(Map<String, String> paraMap) {
		
		sqlsession.insert("member.insert_tbl_loginhistory", paraMap);
	}

	// tbl_member 테이블의 idle 컬럼의 값을 1로 변경하기 
	@Override
	public void updateIdle(String userid) {
		
		sqlsession.update("member.updateIdle", userid);
	}

	
	// 인사관리 페이지에 접속한 이후에, 인사관리 페이지에 접속한 페이지URL, 사용자ID, 접속IP주소, 접속시간을 기록으로 DB에 tbl_empManger_accessTime 테이블에 insert 하도록 한다. 
	@Override
	public void insert_accessTime(Map<String, String> paraMap) {
		
		sqlsession.insert("member.insert_accessTime", paraMap);
	}

	
	// 인사관리 페이지별 사용자별 접속통계 가져오기 
	@Override
	public List<Map<String, String>> pageurlUsername() {
		
		List<Map<String, String>> pageurlUsernameList = sqlsession.selectList("member.pageurlUsername"); 
		return pageurlUsernameList;
	}
	
	
	
}
