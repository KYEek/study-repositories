package jdbc.day04.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jdbc.day04.board.dbconnection.MyDBConnection;
import jdbc.day04.member.domain.MemberDTO;

public class MemberDAO_imple implements MemberDAO {

	
		//field, attribute, property
		private Connection conn = MyDBConnection.getConn();
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
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	
	
	@Override
	public int memberRegister(MemberDTO member) {
		int result = 0;

		try {
			
			String sql = " insert into tbl_member(userseq, userid, passwd, name, mobile) "
					+ " values (userseq.nextval, ?, ?, ?, ?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getUserid());
			pstmt.setString(2, member.getPasswd());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getMobile());

			result = pstmt.executeUpdate();

		}  catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return result;
	}// end of memberRegister----------------------------
	
	
	

	@Override
	public MemberDTO login(Map<String, String> paraMap) {
		MemberDTO member = null;

		try {
			
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
			
		
		}  catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		
		return member;
	}//end of MemberDTO------------------------------

	
	
	@Override
	public List<MemberDTO> showALLMember(String sortChoice) {
		List<MemberDTO> memberList = null;

		try {
			
			String sql = " select userseq, userid, name, mobile, point, to_char(registerday, 'yyyy-mm-dd hh24:mi:ss') as registerday, status "
					+ " from tbl_member "
					+ " where user != 'admin' ";
			
			switch (sortChoice) {
			case "":
			case "1":	//회원명의 오름차순
				sql += " order by name asc ";

				break;
			case "2":	//회원명의 내림차순
				sql += " order by name desc ";
				
				break;
			case "3":	//가입일자의 오름차순
				sql += " order by registerday asc ";
				
				break;
			case "4":	//가입일자의 내림차순
				sql += " order by registerday desc ";
				
				break;
			}

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int cnt = 0;
			while (rs.next()) {
				cnt++;
				if (cnt == 1)
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

		}  catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return memberList;
	}//List<MemberDTO> showALLMember

}
