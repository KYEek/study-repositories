package review.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import applys.domain.ApplysDTO;
import common.ProjectDBConnection;
import job_posts.domain.JobpostDTO;
import user.domain.CompanyDTO;
import user.domain.MemberDTO;
import resume.domain.ResumeDTO;
import review.domain.ReviewDTO;

public class MbrReviewDAO_imple implements MbrReviewDAO {


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

	
	
	// 지원한 회사목록보기
	// 지원했던 회사를 들고오는것
	@Override
	public List<JobpostDTO> viewBoardList(MemberDTO member) {

		ApplysDTO apply = null;
		JobpostDTO jobpost = null;
		CompanyDTO company = null;
		List<JobpostDTO> viewBoardList = new ArrayList<>();
	
		try {
			String sql =  " with "
						+ " A as "
						+ " (  "
						+ " select  fk_work_tcode, fk_job_postno, fk_user_no, applys_date "
						+ " from tbl_applys A join tbl_resumes B "
						+ " on A.fk_resume_no = B.resume_no "
						+ " )  "
						+ " , B as "
						+ " ( "
						+ " select com_id, com_name, post_title, fk_work_tcode, job_postno	 "
						+ " from tbl_companies A join tbl_job_posts B "
						+ " on A.com_no = B.fk_com_no "
						+ " ) "
						+ " select job_postno, com_name, post_title, applys_date "
						+ " from A join B "
						+ " on A.fk_work_tcode = B.fk_work_tcode "
						+ " where fk_user_no = ? ";
	 
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member.getUser_no());
			rs = pstmt.executeQuery();
			
			while(rs.next()) { 
				
				jobpost = new JobpostDTO();
				company = new CompanyDTO();
				apply = new ApplysDTO();
				
				jobpost.setJob_postno(rs.getInt("job_postno"));		// 공고번호
				jobpost.setPost_title(rs.getString("post_title"));
				
				company.setCom_name(rs.getString("com_name"));	// 회사이름
				jobpost.setCompany(company);
				
				apply.setApplys_date(rs.getString("applys_date"));	//  지원날짜 
				jobpost.setApply(apply);
	
				viewBoardList.add(jobpost);
			}
			
		} catch (SQLException e) {
				e.printStackTrace();
		} finally {
				close();
		}
		return viewBoardList;

	}
	
	

	// 후기작성 
	@Override
	public int reviewWrite(MemberDTO member, int comNo, ReviewDTO review) {
		
		int result = 0;
		
		try {
			
			String sql = " insert into tbl_reviews(review_no, review_score, review_contents, review_regidate, fk_user_no, fk_com_no) "
					   + " values(review_seq.nextval, ?, ?, sysdate, ?, ?) " ;
		
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, review.getReview_score());
			pstmt.setString(2, review.getReview_contents());
			pstmt.setInt(3, member.getUser_no());
			pstmt.setInt(4, comNo);
			
			result = pstmt.executeUpdate();	 // sql 실행
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return result;
	}


	// 리뷰 전체조회    
	@Override
	public List<ReviewDTO> AllreviewList(int com_no) {
		
		List<ReviewDTO> AllreviewList = new ArrayList<>();
		CompanyDTO company = new CompanyDTO();
		try {
			String sql = " select review_no, com_name, review_contents, review_score, review_regidate "
					   + " from tbl_reviews A join tbl_companies B "
					   + " on A.fk_com_no = B.com_no "
					   + " where com_no = ? "
					   + " order by review_regidate ";
 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, com_no);	// 회사명 입력     
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				ReviewDTO review = new ReviewDTO();
	
				review.setReview_no(rs.getInt("review_no"));
				
				company.setCom_name(rs.getString("com_name"));
				review.setCompany(company);
				
				review.setReview_contents(rs.getString("review_contents"));
				review.setReview_score(rs.getInt("review_score"));
				review.setReview_regidate(rs.getString("review_regidate"));
				
				
				AllreviewList.add(review);
			}
			
		} catch (SQLException e) {
				e.printStackTrace();
		} finally {
				close();
		}
		
	
		return AllreviewList;
	}


	
	// 내가작성한후기리스트    
	@Override
	public List<ReviewDTO> MyreviewList(MemberDTO member) {
		
		List<ReviewDTO> myreviewList = new ArrayList<>();
		CompanyDTO company = new CompanyDTO(); // 0
		
		try {
			String sql =  " select review_no, com_name, review_contents, review_score, review_regidate "
						+ " from "
						+ " ( "
						+ " select user_id, review_no, review_contents, review_score, review_regidate, fk_com_no "
						+ " from tbl_reviews A join tbl_users B "
						+ " on A.fk_user_no = B.user_no "
						+ " ) A join tbl_companies B "
						+ " on A.fk_com_no = B.com_no "
						+ " where A.user_id = ? " ; 
 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getUser_id());	// 내이름으로입력    
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				ReviewDTO review = new ReviewDTO();

				review.setReview_no(rs.getInt("review_no"));
				company.setCom_name(rs.getString("com_name"));
				review.setCompany(company);
				
				review.setReview_contents(rs.getString("review_contents"));
				review.setReview_score(rs.getInt("review_score"));
				review.setReview_regidate(rs.getString("review_regidate"));
				
				myreviewList.add(review);
			}
			
		} catch (SQLException e) {
				e.printStackTrace();
		} finally {
				close();
		}
	
		return myreviewList;
		
	}



	// 후기수정하기  
	@Override
	public int updateReview(Map<String, String> paraMap2) {
		
		int result = 0;
		
		try {
			
			String sql = " update tbl_reviews set review_score = ?, review_contents = ? "
					   + " where review_no = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMap2.get("review_score"));
			pstmt.setString(2, paraMap2.get("review_contents"));
			pstmt.setString(3, paraMap2.get("review_no"));
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			if(e.getErrorCode() == 1722) {
				System.out.println(">> [경고] 글번호는 정수만 입력하세요 ! ");
			} else {
				e.printStackTrace();
			}
		
		} finally {
				close();
		}
		return result;
	}



	// 후기상세조회   
	@Override
	public ReviewDTO detailViewReview(Map<String, String> paraMap) {
		
		ReviewDTO review = null;
		
		try {
			
			String sql =  " select review_no, user_name, user_no, review_contents, review_score, review_regidate "
						+ " from tbl_reviews A join tbl_users B "
						+ " on A.fk_user_no = B.user_no "
						+ " where review_no = ? "; 
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMap.get("review_no"));
			
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
			}
			
		} catch (SQLException e) {
			if(e.getErrorCode() == 1722) {
				System.out.println(">> [경고] 후기 번호는 정수만 입력하세요 ! ");
			} else {
				e.printStackTrace();
			}
		
		} finally {
				close();
		}
		
		return review;
		
	}


	// 회사번호찾기
	@Override
	public int searchComNo(int com_no) {
		
		int result = 0;
		
		try {
			String sql = " select review_no "
						+ " from tbl_reviews "
						+ " where fk_com_no = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, com_no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = 1;
			}
			
		} catch (SQLException e) {
			if(e.getErrorCode() == 1722) {
				System.out.println(">> [경고] 회사 번호는 정수만 입력하세요 ! ");
			} else {
				e.printStackTrace();
			}
		
		} finally {
				close();
		}
		
		return result;
	}


	// 평점순위별 후기 조회 
	@Override
	public List<String> rankList() {
		
		
		List<String> rankList = new ArrayList<>();
 
		
		try {
			String sql =  " select  dense_rank() over(order by review_score desc) as rank "
						+ "       , review_no, com_name, review_contents "
						+ "       , review_score, review_regidate "
						+ " from "
						+ " ( "
						+ " select review_no, com_name, review_contents, review_score, review_regidate, fk_user_no "
						+ " from tbl_reviews A join tbl_companies B "
						+ " on A.fk_com_no = B.com_no " 
						+ " ) A join tbl_users B "
						+ " on A.fk_user_no = B.user_no " ; 
	 
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				StringBuilder sb = new StringBuilder();
				
				String result = rs.getInt("rank") + "\t" + rs.getInt("review_no") + "\t" + rs.getString("com_name") + "\t" + rs.getString("review_contents") + "\t" +
								rs.getString("review_contents") + "\t" + rs.getInt("review_score") +  "\t" +rs.getString("review_regidate") + "\n";
				
				
				rankList.add(result);
			}
			
		} catch (SQLException e) {
				e.printStackTrace();
		} finally {
				close();
		}

		return rankList;
	}


	
	// 글삭제하기 (status 0으로 변경) 
	@Override
	public int deleteBoard(String deleteno) {
	
		int result = 0;
		
		try {
			String sql = " update tbl_reviews set review_status = ? where review_no = ?  " ;
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,deleteno);
			
			result = pstmt.executeUpdate();	 // sql 실행
			
		} catch (SQLException e) {
			if(e.getErrorCode() == 1722) {
				System.out.println(">> [경고] 글번호는 정수로만 입력하세요 ! << \n");
			}
			else {
				e.printStackTrace();
			}
			
		} finally {
			close();
		}
		
		return result;
	}







}
	
