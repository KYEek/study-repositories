package member.model;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import member.domain.MemberVO;
import util.security.AES256;
import util.security.SecretMyKey;
import util.security.Sha256;

public class MemberDAO_imple implements MemberDAO {
	private DataSource ds;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private AES256 aes;
	// 생성자
	public MemberDAO_imple() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/myoracle");
			aes =new AES256(SecretMyKey.KEY);
			// SecretMyKey.KEY 은 우리가 만든 암호화/복호화 키이다.

		} catch (NamingException e) {
			e.printStackTrace();
		} catch ( UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	// 사용한 자원을 반납하는 close() 메소드 생성하기
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
	}// end of private void close()-------------------------------------

	@Override
	public int registerMember(MemberVO member) throws SQLException {
		int result = 0;

		try {
			conn = ds.getConnection();
			String sql = " insert into tbl_member(userid, pwd, name, email, mobile, postcode, address, detailaddress, extraaddress, gender, birthday) "
					+ " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, member.getUserid());
			pstmt.setString(2, Sha256.encrypt(member.getPwd()));// 암호를 SHA256 알고리즘으로 단방향 암호화 시킨다.
			pstmt.setString(3, member.getName());
			pstmt.setString(4, aes.encrypt(member.getEmail()));// 이메일을 AES256 알고리즘으로 양방향 암호화 시킨다.
			pstmt.setString(5, aes.encrypt(member.getMobile()));// 휴대폰을 AES256 알고리즘으로 양방향 암호화 시킨다.
			pstmt.setString(6, member.getPostcode());
			pstmt.setString(7, member.getAddress());
			pstmt.setString(8, member.getDetailaddress());
			pstmt.setString(9, member.getExtraaddress());
			pstmt.setString(10, member.getGender());
			pstmt.setString(11, member.getBirthday());
			
			pstmt.executeUpdate();
		} catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch(GeneralSecurityException e) {
			e.printStackTrace();
		} catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
		return 0;
	}

	@Override
	public boolean idDuplicatedCheck(String userid) throws SQLException {
		
		boolean isExists = false;
		
		try {
			conn = ds.getConnection();
			
			String sql = " select userid "
					+ " from tbl_member "
					+ " where userid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			
			rs = pstmt.executeQuery();
			isExists = rs.next();
		}
		
		finally {
			close();
		}
		return isExists;
	}

}
