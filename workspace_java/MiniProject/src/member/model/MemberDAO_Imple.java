package member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.ProjectDBConnection;
import user.domain.MemberDTO;

public class MemberDAO_Imple implements MemberDAO {

	private Connection conn = ProjectDBConnection.getConn();
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
	
	//자원 회수 메서드
	private void close() {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (pstmt != null) {
				pstmt.close();
				pstmt = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	//기업 회원가입 메서드
	@Override
	public int registeMember(MemberDTO member) {
		
		String sql = " insert into tbl_users(USER_NO, USER_ID, USER_PASSWD, USER_NAME, USER_JUBUN, USER_EMAIL, USER_TEL, USER_ADDRESS, FK_JOB_TCODE)\r\n"
				+ "values(USER_SEQ.nextval, 'kangdw', 'qwer1234$', '강동원', '93002281', 'gangdw@naver.com', '010-2234-5567', '서울시 마포구 창전동', 1); ";
		
		//결과 성공 여부 반환을 위한 변수
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getUser_id());
			pstmt.setString(2, member.getUser_passwd());
			pstmt.setString(3, member.getUser_name());
			pstmt.setString(4, member.getUser_jubun());
			pstmt.setString(5, member.getUser_email());
			pstmt.setString(6, member.getUser_tel());
			pstmt.setString(7, member.getUser_address());
			pstmt.setInt(8, member.getFk_job_tcode());
			
			
			// sql 실행
			result = pstmt.executeUpdate();
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return result;
	}//end of method ----------------------------------------------------

	
	
	
}
