package com.spring.app.member.model;

import java.util.List;
import java.util.Map;

import com.spring.app.member.domain.MemberVO;

public interface MemberDAO {

	// 로그인 처리하기
	MemberVO getLoginMember(Map<String, String> paraMap);

	// tbl_loginhistory 테이블에 insert 해주기
	void insert_tbl_loginhistory(Map<String, String> paraMap);

	// tbl_member 테이블의 idle 컬럼의 값을 1로 변경하기 
	void updateIdle(String string);

	// 인사관리 페이지에 접속한 이후에, 인사관리 페이지에 접속한 페이지URL, 사용자ID, 접속IP주소, 접속시간을 기록으로 DB에 tbl_empManger_accessTime 테이블에 insert 하도록 한다. 
	void insert_accessTime(Map<String, String> paraMap);

	// 인사관리 페이지별 사용자별 접속통계 가져오기 
	List<Map<String, String>> pageurlUsername();

}
