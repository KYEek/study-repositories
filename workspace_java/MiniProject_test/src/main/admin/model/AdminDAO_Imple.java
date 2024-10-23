package main.admin.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import common.ProjectDBConnection;

public class AdminDAO_Imple implements AdminDAO {

	
	//filed
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
	
	
	
	//관리자 로그인 
	@Override
	public boolean login(Map<String, String> login) {
		
		
		//관리자는 관리자 정보를 받아올 필요가 없고 로그인 성공과 실패만 필요해서 DTO를 만들지 않았다
		boolean result = false;
		
		
		
		String sql = " select admin_id, admin_passwd, admin_name from TBL_admin "
				+ "where admin_ID = ? and admin_passwd = ? ";
		

		
		
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, login.get("userid"));
			pstmt.setString(2, login.get("passwd"));
			rs = pstmt.executeQuery();
			
			
			// 있는지 검사
			if (rs.next()) {
				result = true;	//로그인 성공
			}
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		
		
		return result;
	}
	
}
