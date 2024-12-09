package member.model;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

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

	@Override
	public boolean emailDuplicatedCheck(String email) throws SQLException {
		boolean isExists = false;
				
		try {
			conn = ds.getConnection();
			
			String sql = " select userid "
					+ " from tbl_member "
					+ " where email = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aes.encrypt(email));
			
			rs = pstmt.executeQuery();
			isExists = rs.next();
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch(GeneralSecurityException e) {
			e.printStackTrace();
		} catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		finally {
			close();
		}
		return isExists;
	}

	@Override
	public MemberVO login(Map<String, String> paraMap) throws SQLException {
		MemberVO member = null;
		try {
			conn = ds.getConnection();
			String sql = " select userid, name, coin, point, pwdchangegap, "
					+ "        nvl(lastlogingap, trunc(months_between(sysdate,registerday))) as lastlogindate, "
					+ "        idle, email, mobile, postcode, address, detailaddress,extraaddress "
					+ " from  "
					+ " ( "
					+ "    select userid, name, coin, point,  "
					+ "        trunc(months_between(sysdate, lastpwdchangedate)) as pwdchangegap, "
					+ "        registerday, idle, email,mobile,postcode,address, detailaddress,extraaddress "
					+ "    from  tbl_member "
					+ "    where status = 1 and userid = ? and pwd = ? "
					+ " ) m "
					+ " cross join  "
					+ " ( "
					+ "    select trunc(months_between(sysdate,max(logindate))) as lastlogingap "
					+ "    from tbl_loginhistory "
					+ "    where fk_userid = ? "
					+ " ) h ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMap.get("userid"));
			pstmt.setString(2, Sha256.encrypt(paraMap.get("pwd")));
			pstmt.setString(3, paraMap.get("fk_userid"));
			
			pstmt.executeQuery();
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				member = new MemberVO();
				
				member.setUserid(rs.getString("userid"));
				member.setName(rs.getString("name"));
				member.setCoin(rs.getInt("coin"));
				member.setPoint(rs.getInt("point"));
				
				if (rs.getInt("lastlogindate") >= 12) {
					// 마지막으로 로그인 한 날짜시간이 현재시각으로 부터 1년이 지났으면 휴면으로 지정
					member.setIdle(1);
					System.out.println(rs.getInt("idle"));
					if(rs.getInt("idle")==0) {
						//=== tbl_member 테이블의 idle 컬럼의 값을 1로 변경하기 ===
						sql = " update tbl_member set idle = 1 "
								+ " where userid = ? ";
						pstmt = conn.prepareStatement(sql);
						pstmt. setString(1, paraMap.get("userid"));
						System.out.println("휴면처리");
						pstmt.executeUpdate();
					}

				}//end of if(rs.getInt)
				
				//휴면이 아닌 회원만 tbl_loginhistory(로그인기록) 테이블에 insert 하기 시작
				if(rs.getInt("lastlogindate") < 12) {
					sql = " insert into tbl_loginhistory (historyno, fk_userid, clientip) "
							+ " values(seq_historyno.nextval, ?, ?) ";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, paraMap.get("userid"));
					pstmt.setString(2, paraMap.get("clientip"));
					
					pstmt.executeUpdate();
					//휴면이 아닌 회원만 tbl_loginhistory(로그인기록) 테이블에 insert 하기 끝
					
					if(rs.getInt("pwdchangegap") >=3 ) {
						// 마지막으로 암호를 변경한 날짜가 현재시각으로 부터 3개월이 지났으면 true
						
						
						// 마지막으로 암호를 변경한 날짜가 현재시각으로 부터 3개월이 지나지 않았으면 false
						member.setRequirePwdChange(true); // 로그인시 암호를 변경해라는 alert 를 띄우도록 할때 사용한다.
						
						
					}
				}
				member.setEmail( aes.decrypt(rs.getString("email")) );
	            member.setMobile( aes.decrypt(rs.getString("mobile")) );
	            member.setPostcode( rs.getString("postcode") );
	            member.setAddress( rs.getString("address") );
	            member.setDetailaddress( rs.getString("detailaddress") );
	            member.setExtraaddress( rs.getString("extraaddress") );
			}
			
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch(GeneralSecurityException e) {
			e.printStackTrace();
		} catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
		return member;
	}

	@Override
	public String findUserid(Map<String, String> paraMap) throws SQLException {
		
		String userid = null;
		conn = ds.getConnection();
		
		
		
		try {
			String sql = " select userid "
					+ "from tbl_member "
					+ " where status =1 and name ? and email ? ";
			

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMap.get("name"));
			pstmt.setString(2, aes.encrypt(paraMap.get("email")) );
			
			if(rs.next()) {
				userid = rs.getString("userid");
			}
			
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch(GeneralSecurityException e) {
			e.printStackTrace();
		} catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		finally {
			close();
		}
		
		return userid;
	}

}
