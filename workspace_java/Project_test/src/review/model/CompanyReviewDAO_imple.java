package review.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.ProjectDBConnection;
import user.domain.CompanyDTO;
import user.domain.MemberDTO;
import review.domain.ReviewDTO;

public class CompanyReviewDAO_imple implements CompanyReviewDAO {
	
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Connection conn = ProjectDBConnection.getConn();
	
	
	// === 자원반납을 해주는 메소드 ===
	private void close() {
		try {
			if(rs != null) 	  { rs.close();      rs = null; }
			if(pstmt != null) { pstmt.close(); pstmt = null; }
		} catch (SQLException e) { e.printStackTrace(); }
	} // end of private void close()	

	

	
	// 우리회사리뷰보기 
	@Override
	public List<String> companyreviewList(CompanyDTO company) {
		
		List<String> companyreviewList = new ArrayList<>();
		
		try {
			String sql =  " select review_no, review_contents"
					    + " , to_char(review_regidate, 'yyyy-mm-dd') as review_regidate "
					    + " , viewcount "
						+ " ,      substr(user_name, 1,1) || '**' as user_name "
						+ " from "
						+ " ( "
						+ " select fk_com_no, review_no, viewcount "
						+ " , case when length(review_contents) > 13 then substr(review_contents, 1,10) || '...' else review_contents end as review_contents  "
						+ " , review_regidate, fk_user_no "
						+ " from tbl_companies A join tbl_reviews B "
						+ " on A.com_no = B.fk_com_no "
						+ " ) A join tbl_users B "
						+ " on A.fk_user_no = B.user_no "
						+ " where fk_com_no = ? "
						+ " order by review_no ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, company.getCom_no());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				
				String result = rs.getInt("review_no") + "\t" + rs.getString("review_contents") + "\t" + rs.getInt("viewcount") + "회"
				+ "\t" + rs.getString("review_regidate") + "\t" + rs.getString("user_name") ;
					
				companyreviewList.add(result);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return companyreviewList;
		
	}



	// 후기상세조회 
	@Override
	public ReviewDTO detailViewReview(String review_no) {
		
		ReviewDTO review = null;
		
		try {
			
			String sql =  " select review_no, user_name, user_no, review_contents, review_score, review_regidate, viewcount "
						+ " from tbl_reviews A join tbl_users B "
						+ " on A.fk_user_no = B.user_no "
						+ " where review_no = ? "; 
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, review_no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				review = new ReviewDTO();
				review.setReview_no(rs.getInt("review_no"));
				
				MemberDTO member = new MemberDTO();
				member.setUser_name(rs.getString("user_name"));
				member.setUser_no(rs.getInt("user_no"));
				review.setMember(member);
				
				review.setReview_contents(rs.getString("review_contents"));
				review.setReview_score(rs.getInt("review_score"));
				review.setReview_regidate(rs.getString("review_regidate"));
				review.setViewcount(rs.getInt("viewcount"));
				

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	
		
		return review;
	}



	// 우리회사가맞는지판별 
	@Override
	public boolean compareNo(String review_no, CompanyDTO company) {
		
		String sql = " select review_no "
				+ " from " 
				+ " ( "
				+ " select fk_com_no, review_no, fk_user_no "
				+ " from tbl_companies A join tbl_reviews B "
				+ " on A.com_no = B.fk_com_no "
				+ " ) A join tbl_users B "
				+ " on A.fk_user_no = B.user_no "
				+ " where fk_com_no = ? and review_no = ? ";

	      boolean result = false; // 아이디가 있는지 없는지 비교

	      try {

	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, company.getCom_no());
	         pstmt.setString(2, review_no); // 입력받은 아이디 설정
	         rs = pstmt.executeQuery();

	         // 있는지 검사
	         if (rs.next()) {
	            result = true;
	         }

	      } catch (SQLException e) {
				if(e.getErrorCode() == 1722) {
					System.out.println(">> [경고] 후기번호는 정수만 입력하세요 ! <<");
				} else {
					e.printStackTrace();
				}
			
			} finally {
					close();
			}
	      return result;

	}
	

}
