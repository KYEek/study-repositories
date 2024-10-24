package company.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

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
			pstmt.setLong(7, company.getCom_revenue());
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





	//기업 회원 로그인 메서드
	@Override
	public CompanyDTO login(Map<String, String> login) {
		
		String sql = " select com_no, com_id, com_passwd, com_name, com_intro, com_email, com_president, com_revenue, com_tel, com_address, fk_job_tcode, com_regi, com_status, job_code, job_type  "
				+ "from TBL_companies join TBL_JOB_TYPES on FK_JOB_TCODE = JOB_CODE "
				+ "where COM_ID = ? and COM_PASSWD = ? and com_status = 1 ";
		
		
		//반환 하기 위한 company 변수 생성이고 기본값은 null 값이다 실행이 안되면 null을 반환한다.
		CompanyDTO company = null;
		
		
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, login.get("userid"));
			pstmt.setString(2, login.get("passwd"));
			rs = pstmt.executeQuery();
			
			
			// 있는지 검사
			if (rs.next()) {
				
				//있으면 null인 company 변수에 companyDTO 객채를 생성해줌
				company = new CompanyDTO();
				
				//값 입력
				company.setCom_no(rs.getInt("com_no"));
				company.setCom_id(rs.getString("com_id"));
				company.setCom_passwd(rs.getString("com_passwd"));
				company.setCom_name(rs.getString("com_name"));
				company.setCom_intro(rs.getString("com_intro"));
				company.setCom_email(rs.getString("com_email"));
				company.setCom_president(rs.getString("com_president"));
				company.setCom_revenue(rs.getLong("com_revenue"));
				company.setCom_tel(rs.getString("com_tel"));
				company.setCom_address(rs.getString("com_address"));
				company.setFk_job_tcode(rs.getInt("fk_job_tcode"));
				company.setCom_regi(rs.getString("com_regi"));
				company.setCom_status(rs.getInt("com_status"));
				company.setFk_job_tcode(rs.getInt("job_code"));
				company.setJob_type(rs.getString("job_type"));
			}
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return company;
	}





	//아이디 찾기 메서드
	@Override
	public String find_ID(CompanyDTO company ) {


		String sql = " select com_id from tbl_companies where com_name = ? and com_email = ? com_status = 1 ";


		String result = null; // 결과를 저장하는 문자열, 실패시 널을 반환

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, company.getCom_name()); // 입력받은 이름 설정
			pstmt.setString(2, company.getCom_email()); // 입력받은 이메일 설정
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
		
	}//end of find_ID-----------------------------------------------------------------------





	//아이디 비교 메서드
	@Override
	public boolean compareID(CompanyDTO company) {
		
		String sql = " select * from tbl_companies where com_id = ? and com_status = 1 ";

		
		boolean result = false; // 아이디가 있는지 없는지 비교

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, company.getCom_id()); // 입력받은 아이디 설정
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
	public int reset_passwd(CompanyDTO company) {

		String sql = " update tbl_companies set com_passwd = ? where com_id =? and com_name = ? and com_email = ? and com_status = 1 ";
		
		//결과 성공 여부 반환을 위한 변수
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, company.getCom_passwd());
			pstmt.setString(2, company.getCom_id());
			pstmt.setString(3, company.getCom_name());
			pstmt.setString(4, company.getCom_email());
			
			
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
