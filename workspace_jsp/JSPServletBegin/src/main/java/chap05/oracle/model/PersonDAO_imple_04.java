package chap05.oracle.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import chap05.oracle.domain.PersonDTO_02;

public class PersonDAO_imple_04 implements PersonDAO_03 {

	private Connection conn = MyDBConnection_05.getConn();
	private PreparedStatement pstmt;
	private ResultSet rs;

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

		}
	}// end of private void close() ----------------------------------
		// 개인성향을 입력(insert)해주는 추상메소드(미완성메소드)

	@Override
	public int personRegister(PersonDTO_02 psdto) throws SQLException {

		int n = 0;
		try {
			String sql = "insert into tbl_person_interest (seq, name, school, color, food)"
					+ "values(person_seq.nextval, ?, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, psdto.getName());
			pstmt.setString(2, psdto.getSchool());
			pstmt.setString(3, psdto.getColor());

			if (psdto.getFood() != null) {
				pstmt.setString(4, String.join(",", psdto.getFood()));
			} else {
				pstmt.setString(4, null);
			}

			n = pstmt.executeUpdate();
		} finally {
			close();
		}

		return n;
	} // end of public int personRegister ----------------------------

	
	
	@Override
	public List<PersonDTO_02> selectAll() throws SQLException {
		
		List<PersonDTO_02> personList = new ArrayList<>();
		
		try {
			String sql = " select seq, name, school, color, food "
						+ "     , to_char(registerday, 'yyyy-mm-dd hh24:mi:ss') AS registerday "
						+ "     , to_char(updateday, 'yyyy-mm-dd hh24:mi:ss') AS updateday "
						+ " from tbl_person_interest "
						+ " order by seq ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PersonDTO_02 psdto = new PersonDTO_02();
				psdto.setSeq(rs.getInt("seq"));
				psdto.setName(rs.getString("name"));
				psdto.setSchool(rs.getString("school"));
				psdto.setColor(rs.getString("color"));
				
				String foodes = rs.getString("food");
				if(foodes != null) {
					psdto.setFood(foodes.split("\\,"));
				}
				else {
					psdto.setFood(null);
				}
				psdto.setRegisterday(rs.getString("registerday"));
				psdto.setUpdateday(rs.getString("updateday"));
				
				personList.add(psdto);
				
			}// end of while-----------------------
		} finally {
			
		}
		
		
		return personList;
	}

	//tbl_person_interest 테이블에 저장되어진 특정 1개행(데이터)만 읽어다가(select) 웹페이지에 보여줘야 한다.
	@Override
	public PersonDTO_02 selectOne(String seq) throws SQLException {
		
		PersonDTO_02 psdto =null;
		
		try {
			String sql = " select seq, name, school, color, food "
						+ "     , to_char(registerday, 'yyyy-mm-dd hh24:mi:ss') AS registerday "
						+  "      , NVL( to_char(updateday, 'yyyy-mm-dd hh24:mi:ss'), ' ') AS updateday "
						+ " from tbl_person_interest "
						+ " where seq = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, seq);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				psdto = new PersonDTO_02();
				psdto.setSeq(rs.getInt("seq"));
				psdto.setName(rs.getString("name"));
				psdto.setSchool(rs.getString("school"));
				psdto.setColor(rs.getString("color"));
				
				String foodes = rs.getString("food");
				if(foodes != null) {
					psdto.setFood(foodes.split("\\,"));
				}
				else {
					psdto.setFood(null);
				}
				psdto.setRegisterday(rs.getString("registerday"));
				psdto.setUpdateday(rs.getString("updateday"));
				
				
			}// end of while-----------------------
		} finally {
			close();
		}
		
		return psdto;
	}// end of public PersonDTO_02 selectOne(String seq) --------------------

	@Override
	public int personDelete(int seqNum) throws SQLException {
		
		int result =0;
		
		
		try {
			String sql = " delete from tbl_person_interest where seq = ?  ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seqNum);
			result = pstmt.executeUpdate();
			
			
			
			
		}
		finally {
			close();
		}
		
		return result;
	}

}
