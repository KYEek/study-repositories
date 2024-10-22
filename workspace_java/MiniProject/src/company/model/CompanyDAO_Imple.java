package company.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.ProjectDBConnection;
import user.domain.CompanyDTO;

public class CompanyDAO_Imple implements CompanyDAO {

	
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
	
	
	
	
	
	
	//회사 회원가입
	@Override
	public int registeCompany(CompanyDTO company) {
		
		
		String sql = " insert into tbl_companies(COM_NO, COM_ID, COM_PASSWD, COM_NAME, COM_INTRO, COM_EMAIL, COM_PRESIDENT, COM_REVENUE, COM_TEL, COM_ADDRESS, FK_JOB_TCODE) "
				+ "values(COM_SEQ.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		
		//결과 성공 여부 반환을 위한 변수
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, company.getCom_id());
			pstmt.setString(2, company.getCom_passwd());
			pstmt.setString(3, company.getCom_name());
			pstmt.setString(4, company.getCom_intro());
			pstmt.setString(5, company.getCom_email());
			pstmt.setString(6, company.getCom_president());
			pstmt.setInt(7, company.getCom_revenue());
			pstmt.setString(8, company.getCom_tel());
			pstmt.setString(9, company.getCom_address());
			pstmt.setInt(10, company.getFk_job_tcode());
			
			
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
