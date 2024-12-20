package member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

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
				+ "values(USER_SEQ.nextval, ?, ?, ?, ?, ?, ?, ?, ?) ";
		
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




	//멤버 로그인 메소드
	@Override
	public MemberDTO login(Map<String, String> login) {
		
		
		String sql = " select user_no, user_id, user_passwd, user_name, user_jubun, user_email, user_tel, user_address, user_regi, user_status, fk_job_tcode, job_code, job_type, user_point "
				+ " from TBL_USERS join TBL_JOB_TYPES on FK_JOB_TCODE = JOB_CODE "
				+ " where user_id = ? and user_passwd = ? and user_status = 1 ";
		
		//반환 하기 위한 member 변수 생성이고 기본값은 null 값이다 실행이 안되면 null을 반환한다.
		MemberDTO member = null;
		
		
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, login.get("userid"));
			pstmt.setString(2, login.get("passwd"));
			rs = pstmt.executeQuery();
			
			
			// 있는지 검사
			if (rs.next()) {
				
				//있으면 null인 member 변수에 MemberDTO 객채를 생성해줌
				member = new MemberDTO();
				
				member.setUser_no(rs.getInt(1));	//그냥 컬럼 순서대로 함
				member.setUser_id(rs.getString(2));
				member.setUser_passwd(rs.getString(3));
				member.setUser_name(rs.getString(4));
				member.setUser_jubun(rs.getString(5));
				member.setUser_email(rs.getString(6));
				member.setUser_tel(rs.getString(7));
				member.setUser_address(rs.getString(8));
				member.setUser_regi(rs.getString(9));
				member.setUser_status(rs.getInt(10));
				member.setFk_job_tcode(rs.getInt(11));
				member.setJob_type(rs.getString(12));
				member.setUser_point(rs.getInt("user_point"));
				
			}
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return member;
		
	}//end of method ----------------------------------------------------





	//아이디 찾기 메서드
	@Override
	public String find_ID(MemberDTO member) {
		
		
		String sql = " select user_id from tbl_users where user_name = ? and user_email = ? and user_status = 1 ";


		String result = null; // 결과를 저장하는 문자열, 실패시 널을 반환

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getUser_name()); // 입력받은 이름 설정
			pstmt.setString(2, member.getUser_email()); // 입력받은 이메일 설정
			rs = pstmt.executeQuery();

			// 있는지 검사
			if (rs.next()) {

				result = rs.getString(1); // 결과값 저장

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return result;

	}//end of find_ID----------------------------------------------------------




	
	
	//아이디 비교 메서드
	@Override
	public boolean compareID(MemberDTO member) {
		
		
		String sql = " select * from tbl_users where user_id = ? and user_status = 1";

		
		boolean result = false; // 아이디가 있는지 없는지 비교

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getUser_id()); // 입력받은 아이디 설정
			rs = pstmt.executeQuery();

			// 있는지 검사
			if (rs.next()) {

				result = true;

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return result;
		
		
	}




	//비밀번호 초기화
	@Override
	public int reset_passwd(MemberDTO member) {
		
		String sql = " update tbl_users set user_passwd = ? where user_id =? and user_name = ? and user_email = ? and user_status = 1";
		
		//결과 성공 여부 반환을 위한 변수
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getUser_passwd());
			pstmt.setString(2, member.getUser_id());
			pstmt.setString(3, member.getUser_name());
			pstmt.setString(4, member.getUser_email());
			
			
			// sql 실행
			result = pstmt.executeUpdate();
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return result;
		
	}

	
	
	
}
