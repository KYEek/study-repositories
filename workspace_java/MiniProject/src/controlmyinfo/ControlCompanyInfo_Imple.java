package controlmyinfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.ProjectDBConnection;
import user.domain.CompanyDTO;
import user.domain.MemberDTO;

public class ControlCompanyInfo_Imple implements ControlCompanyInfo {

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
	}//end of method-----------------------------------------------------------------------------------
	
	
	//일반회원 정보 수정
	@Override
	public int updateinfo(MemberDTO member) {
		
		
		String sql = " update tbl_users "
				+ " set USER_NAME = ?, USER_TEL = ?, USER_ADDRESS = ?, FK_JOB_TCODE = ? where user_no = ? ";
		
		//결과 성공 여부 반환을 위한 변수
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getUser_name());
			pstmt.setString(2, member.getUser_tel());
			pstmt.setString(3, member.getUser_address());
			pstmt.setInt(4, member.getFk_job_tcode());
			pstmt.setInt(5, member.getUser_no());
			
			
			// sql 실행
			result = pstmt.executeUpdate();
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return result;
	}// end of method ----------------------------------------------------------------------------

	
	//기업회원 정보 수정
	@Override
	public int updateinfo(CompanyDTO company) {
		
		
		String sql = " update tbl_companies "
				+ " set com_name = ?, com_TEL = ?, com_ADDRESS = ?,com_president = ? ,com_revenue = ?, FK_JOB_TCODE = ? where com_no = ? ";
		
		//결과 성공 여부 반환을 위한 변수
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, company.getCom_name());
			pstmt.setString(2, company.getCom_tel());
			pstmt.setString(3, company.getCom_address());
			pstmt.setString(4, company.getCom_president());
			pstmt.setLong(5, company.getCom_revenue());
			pstmt.setInt(6, company.getFk_job_tcode());
			pstmt.setInt(7, company.getCom_no());
			
			
			// sql 실행
			result = pstmt.executeUpdate();
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return result;
	}//end of method-------------------------------------------------------------------

	// 일반 유저 비번 변경
	@Override
	public int update_passwd(MemberDTO member, String passwd) {
		
		String sql = " update tbl_users set user_passwd = ? where user_no = ? ";
		
		//결과 성공 여부 반환을 위한 변수
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, passwd);
			pstmt.setInt(2, member.getUser_no());
			
			
			// sql 실행
			result = pstmt.executeUpdate();
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return result;
	}//end of method--------------------------------------------------------------------

	// 기업 비번 변경
	@Override
	public int update_passwd(CompanyDTO company, String passwd) {
		
		
		String sql = " update TBL_COMPANIES set com_passwd = ? where com_no = ? ";

		// 결과 성공 여부 반환을 위한 변수
		int result = 0;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, passwd);
			pstmt.setInt(2, company.getCom_no());

			// sql 실행
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return result;
	}//end of method ----------------------------------------------------------------------------


	
	
	//일반회원 계정탈퇴
	@Override
	public int delete_account(MemberDTO member) {
		
		
		String sql = " update tbl_users set user_status = 0 where user_no = ? ";

		// 결과 성공 여부 반환을 위한 변수
		int result = 0;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member.getUser_no());

			// sql 실행
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return result;
		
	}


	
	//기업회원 계정탈퇴
	@Override
	public int delete_account(CompanyDTO company) {
		
		String sql = " update tbl_companies set com_status = 0 where com_no = ? ";

		// 결과 성공 여부 반환을 위한 변수
		int result = 0;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, company.getCom_no());

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
