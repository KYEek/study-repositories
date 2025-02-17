package jdbc.day03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// DAO(Database Access Object)란?
// Database 에 연결하여 DB와 관련된 업무(SQL)를 실행시켜주는 객체이다.

public class MemberDAO_imple implements MemberDAO {

	
	//field, attribute, property
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
	
	
	
	
	// method, operation, 기능
	
	//	자원반납을 해주는 메소드 //
	
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
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}





	//	회원가입 메서드 		//
	@Override
	public int memberRegister(MemberDTO member) {
		int result = 0;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "JDBC_USER", "gclass");

			String sql = " insert into tbl_member(userseq, userid, passwd, name, mobile) "
					+ " values (userseq.nextval, ?, ?, ?, ?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getUserid());
			pstmt.setString(2, member.getPasswd());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getMobile());

			result = pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			System.out.println(">>> ojdbc8.jar 파일이 없습니다. <<<");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return result;
	}//end of memberRegister----------------------------





	@Override
	public MemberDTO login(Map<String, String> paraMap) {
		
		
		MemberDTO member= null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "JDBC_USER", "gclass");
			
			String sql = " select userseq, userid, name, mobile, point, to_char(registerday, 'yyyy-mm-dd hh24:mi:ss') as registerday, status "
					+ " from tbl_member "
					+ " where status = 1 and userid = ? and passwd = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMap.get("userid"));
			pstmt.setString(2, paraMap.get("passwd"));
			rs =  pstmt.executeQuery();
			
			if(rs.next()) {
				
				member = new MemberDTO();
				
				member.setUserseq(rs.getInt("userseq"));
				member.setUserid(rs.getString("userid"));
				member.setName(rs.getString("name"));
				member.setMobile(rs.getString("mobile"));
				member.setPoint(rs.getInt("point"));
				member.setRegisterday(rs.getString("registerday"));
				member.setStatus(rs.getInt("status"));
			}
			
		
		} catch (ClassNotFoundException e) {
			System.out.println(">>> ojdbc8.jar 파일이 없습니다. <<<");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		
		return member;
	}//end of MemberDTO------------------------------




	// ==== 회원탈퇴(update) 메소드 ====
	@Override
	public int memberDelete(int userseq) {
		
		int result = 0;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "JDBC_USER", "gclass");

			String sql = " update tbl_member set status = 0 where userseq = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userseq);

			result = pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			System.out.println(">>> ojdbc8.jar 파일이 없습니다. <<<");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return result;
	}




	
	@Override
	public List<MemberDTO> showALLMember() {

		List<MemberDTO> memberList = null;
		


		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "JDBC_USER", "gclass");

			String sql = " select userseq, userid, name, mobile, point, to_char(registerday, 'yyyy-mm-dd hh24:mi:ss') as registerday, status "
					+ " from tbl_member "
					+ " where user != 'admin' "
					+ " order by userseq ";
			
			pstmt = conn.prepareStatement(sql);
			rs =  pstmt.executeQuery();
			int cnt = 0;
			while(rs.next()) {
				cnt++;
				if(cnt == 1)
					memberList = new ArrayList<>();
				MemberDTO member = new MemberDTO();
				
				member.setUserseq(rs.getInt("userseq"));
				member.setUserid(rs.getString("userid"));
				member.setName(rs.getString("name"));
				member.setMobile(rs.getString("mobile"));
				member.setPoint(rs.getInt("point"));
				member.setRegisterday(rs.getString("registerday"));
				member.setStatus(rs.getInt("status"));

				
				
				memberList.add(member);
			}
			
		
		} catch (ClassNotFoundException e) {
			System.out.println(">>> ojdbc8.jar 파일이 없습니다. <<<");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return memberList;
	}//List<MemberDTO> showALLMember





	@Override
	public int updateMyinfo(String name, String mobile, int userseq) {

		int result = 0;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "JDBC_USER", "gclass");

			String sql = " update tbl_member set name = ?, mobile = ? where userseq = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, mobile);
			pstmt.setInt(3, userseq);

			result = pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			System.out.println(">>> ojdbc8.jar 파일이 없습니다. <<<");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return result;
		
	}
	
	
	
	
	
	
	
	
	//		회원가입 메서드 		//
	/*
	public int memberRegister(MemberDTO member) {
		
		int result = 0;
		
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "JDBC_USER", "gclass");
		
		String sql = " insert into tbl_member(userseq, userid, passwd, name, mobile) "
					+" values (userseq.nextval, ?, ?, ?, ?) ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, member.getUserid());
		pstmt.setString(2, member.getPasswd());
		pstmt.setString(3, member.getName());
		pstmt.setString(4, member.getMobile());
		
		
		result = pstmt.executeUpdate();
		
		
		
		
		} catch (ClassNotFoundException e) {
			System.out.println(">>> ojdbc8.jar 파일이 없습니다. <<<");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return result;
		
	}//end of memberRegister----------------------------
*/
}
