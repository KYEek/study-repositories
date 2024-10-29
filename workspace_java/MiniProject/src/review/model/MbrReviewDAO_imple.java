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

	
	
	// 단순 존재하는 회사 목록 
	@Override
	public List<String> viewCompanyList() {
		
		List<String> viewCompanyList = new ArrayList<>();


		try {
			String sql = " select B.com_no, com_name, nvl( to_char(count), '작성된 후기 없음') as reviewcount "
						+ " from "
						+ " ( "
						+ " select fk_com_no, count(*) as count "
						+ " from tbl_reviews "
						+ " group by fk_com_no "
						+ " ) A right join tbl_companies B "
						+ " on A.fk_com_no = B.com_no "
						+ " order by com_no ";
 
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String result = rs.getInt("com_no") + "\t" + rs.getString("com_name") + "\t" +rs.getString("reviewcount"); 
				
				viewCompanyList.add(result);
			}
		} catch (SQLException e) {
				e.printStackTrace();
		} finally {
				close();
		}
		
		return viewCompanyList;
			
	}
	
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
						+ " select  fk_work_tcode, fk_job_postno, fk_user_no "
						+ " from tbl_applys A join tbl_resumes B "
						+ " on A.fk_resume_no = B.resume_no "
						+ " ) "
						+ " , B as "
						+ " ( "
						+ " select com_id, com_no, com_name, fk_work_tcode "
						+ " from tbl_companies A join tbl_job_posts B "
						+ " on A.com_no = B.fk_com_no "
						+ " ) "
						+ " select distinct com_no, com_name "
						+ " from A join B "
						+ " on A.fk_work_tcode = B.fk_work_tcode "
						+ " where fk_user_no = ? "
						+ " order by com_no ";
	 
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member.getUser_no());
			rs = pstmt.executeQuery();
			
			while(rs.next()) { 
				
				jobpost = new JobpostDTO();
				company = new CompanyDTO();
				
				company.setCom_name(rs.getString("com_name"));	// 회사이름
				company.setCom_no(rs.getInt("com_no"));
				jobpost.setCompany(company);

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
	public int reviewWrite(Map<String, String> paraMap, MemberDTO member, int comno) {
		
		int result = 0;
		
		try {
			
			String sql = " insert into tbl_reviews(review_no, review_score, review_contents, review_regidate, fk_user_no, fk_com_no) "
					   + " values(review_seq.nextval, ?, ?, sysdate, ?, ?) " ;
		
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMap.get("review_sc"));
			pstmt.setString(2, paraMap.get("review_contents"));
			pstmt.setInt(3, member.getUser_no());
			pstmt.setInt(4, comno);

			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return result;
	}


	// 리뷰 전체조회    
	@Override
	public List<ReviewDTO> AllreviewList(String com_no) {
		
		List<ReviewDTO> AllreviewList = new ArrayList<>();
		CompanyDTO company = new CompanyDTO();
		try {
			String sql = " select review_no, com_name, viewcount "
					   + " , case when length(review_contents) > 13 then substr(review_contents, 1,10) || '...' "
					   + " else review_contents end as review_contents "
					   + " , review_score, "
					   + " to_char(review_regidate, 'yyyy-mm-dd') as review_regidate "
					   + " from tbl_reviews A join tbl_companies B "
					   + " on A.fk_com_no = B.com_no "
					   + " where com_no = ? "
					   + " order by review_regidate desc ";
 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, com_no);	// 회사번호    
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				ReviewDTO review = new ReviewDTO();
	
				review.setReview_no(rs.getInt("review_no"));
				review.setViewcount(rs.getInt("viewcount"));
				company.setCom_name(rs.getString("com_name"));
				review.setCompany(company);
				
				review.setReview_contents(rs.getString("review_contents"));
				review.setReview_score(rs.getInt("review_score"));
				review.setReview_regidate(rs.getString("review_regidate"));
				
				
				AllreviewList.add(review);
			}
			
		} catch (SQLException e) {
			if(e.getErrorCode() == 1722) {
				System.out.println(">> [경고] 글번호는 정수만 입력하세요 ! ");
			} else {
				e.printStackTrace();
			}
		
		} finally {
				close();
		}
		
	
		return AllreviewList;
	}


	
	// 내가작성한후기리스트    
	@Override
	public List<ReviewDTO> MyreviewList(MemberDTO member) {
		
		List<ReviewDTO> myreviewList = new ArrayList<>();
		
		
		try {
			String sql =  " select review_no, com_name"
					    + " , case when length(review_contents) > 13 then substr(review_contents, 1,10) || '...' else review_contents end as review_contents "
					    + " , viewcount, review_score , to_char(review_regidate, 'yyyy-mm-dd') as review_regidate "
						+ " from "
						+ " (  "
						+ " select viewcount, review_status, user_id, review_no, review_contents, review_score, review_regidate, fk_com_no "
						+ " from tbl_reviews A join tbl_users B  "
						+ " on A.fk_user_no = B.user_no "
						+ " ) A join tbl_companies B "
						+ " on A.fk_com_no = B.com_no "
						+ " where A.user_id = ? and review_status = 1"
						+ " order by review_regidate desc, review_no " ; 
 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getUser_id());	// 내이름으로입력    
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				ReviewDTO review = new ReviewDTO();
				CompanyDTO company = new CompanyDTO(); // 0
				
				review.setReview_no(rs.getInt("review_no"));
				company.setCom_name(rs.getString("com_name"));
				review.setCompany(company);
				
				review.setViewcount(rs.getInt("viewcount"));
				review.setReview_contents(rs.getString("review_contents"));
				review.setReview_score(rs.getInt("review_score"));
				review.setReview_regidate(rs.getString("review_regidate"));
				
				myreviewList.add(review);
	
			}
			
		} catch (SQLException e) {
			if(e.getErrorCode() == 1722) {
				System.out.println(">> [경고] 글번호는 정수만 입력하세요 ! ");
			} else {
				e.printStackTrace();
			}
		
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
			
			String sql =  " select user_id, review_no "
						+ " , user_name "
						+ " , user_no, review_contents, review_score, viewcount "
						+ " , review_regidate "
						+ " from tbl_reviews A join tbl_users B "
						+ " on A.fk_user_no = B.user_no "
						+ " where review_no = ? and review_status = 1 "; 
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMap.get("review_no"));
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				review = new ReviewDTO();
				review.setReview_no(rs.getInt("review_no"));
				
				MemberDTO member = new MemberDTO();
				member.setUser_name(rs.getString("user_name"));
				member.setUser_no(rs.getInt("user_no"));
				member.setUser_id(rs.getString("user_id"));
				review.setMember(member);
				
				review.setReview_contents(rs.getString("review_contents"));
				review.setReview_score(rs.getInt("review_score"));
				review.setReview_regidate(rs.getString("review_regidate"));
				review.setViewcount(rs.getInt("viewcount"));
				
				
				// 로그인한 사용자가 다른 사용자가 쓴 글을 조회할 경우에만 글조회수 1 증가 시켜야 한다.
				if(! paraMap.get("login_userid").equals(rs.getString("user_id")) ) {
					
					sql = " update tbl_reviews set viewcount = viewcount + 1 "
						+ " where review_no = ? ";
					
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, paraMap.get("review_no"));
					
					pstmt.executeUpdate();
					
					review.setViewcount(review.getViewcount() + 1);
				}
				
			}
			
		} catch (SQLException e) {
			if(e.getErrorCode() == 1722) {
				System.out.println(">> [경고] 글번호는 정수만 입력하세요 ! ");
			} else {
				e.printStackTrace();
			}
		
		} finally {
				close();
		}
		
		return review;
		
	}

	// 수정할 후기 보여주기 
	@Override
	public ReviewDTO ViewReview(Map<String, String> paraMap) {
		ReviewDTO review = null;
		
		try {
			String sql =  " select user_id, review_no "
						+ " , user_name "
						+ " , user_no, review_contents, review_score, viewcount "
						+ " , review_regidate "
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
				member.setUser_id(rs.getString("user_id"));
				review.setMember(member);
				
				review.setReview_contents(rs.getString("review_contents"));
				review.setReview_score(rs.getInt("review_score"));
				review.setReview_regidate(rs.getString("review_regidate"));
				review.setViewcount(rs.getInt("viewcount"));
				
			}
			
		} catch (SQLException e) {
			if(e.getErrorCode() == 1722) {
				System.out.println(">> [경고] 글번호는 정수만 입력하세요 ! ");
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
	public int searchComNo(String com_no) {
		
		int result = 0;
		
		try {
			String sql =  " select review_no"
						+ " from tbl_reviews A join tbl_companies B "
						+ " on A.fk_com_no = B.com_no "
						+ " where fk_com_no = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, com_no);
			
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


	
	// 원하는평점조건검색  
	@Override
	public List<String> hopeList(String reviewno) {

		List<String> hopeList = new ArrayList<>();

		try {
			String sql =  " select  dense_rank() over(order by review_score desc) as rank "
						+ " , review_no, com_name, viewcount " 
						+ " , case when length(review_contents) > 13 then substr(review_contents, 1,10) || '...' else review_contents end as review_contents "
						+ " , review_score "
						+ " , to_char(review_regidate, 'yyyy-mm-dd') as review_regidate "
						+ " from "
						+ " ( "
						+ " select review_no, viewcount, com_name, review_contents, review_score, review_regidate, fk_user_no "
						+ " from tbl_reviews A join tbl_companies B "
						+ " on A.fk_com_no = B.com_no "
						+ " ) A join tbl_users B "
						+ " on A.fk_user_no = B.user_no "
						+ " where review_score >= ? "
						+ " order by rank, com_name, viewcount desc " ; 
						
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reviewno);
			
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				

				String result = rs.getInt("rank") + "\t" +  rs.getInt("review_no") + "\t" 
						      + rs.getString("com_name") + "\t" + rs.getInt("viewcount") + "회"+ "\t" + rs.getInt("review_score") + "점"
						      + "\t" + rs.getString("review_regidate") + "\t" + rs.getString("review_contents") +"\n";
				hopeList.add(result);
			}
		
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				close();
		}
		
		return hopeList;
	}



	// 평점순위별 후기 조회 (내림차순)
	@Override
	public List<String> descRankList() {
		
		List<String> rankList = new ArrayList<>();
 
		try {
			String sql =  " select  dense_rank() over(order by review_score desc) as rank "
						+ "       , review_no, com_name, viewcount "
						+ "		  , case when length(review_contents) > 13 then substr(review_contents, 1,10) || '...' else review_contents end as review_contents  "
						+ "       , review_score "
						+ "		  , to_char(review_regidate, 'yyyy-mm-dd') as review_regidate  "
						+ " from "
						+ " ( "
						+ " select review_no, viewcount, com_name, review_contents, review_score, review_regidate, fk_user_no "
						+ " from tbl_reviews A join tbl_companies B "
						+ " on A.fk_com_no = B.com_no " 
						+ " ) A join tbl_users B "
						+ " on A.fk_user_no = B.user_no "
						+ " order by rank, com_name, viewcount desc " ; 
	 
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			
			while(rs.next()) {

				String result = rs.getInt("rank") + "\t" +  rs.getInt("review_no") + "\t" 
						      + rs.getString("com_name") + "\t" + rs.getInt("viewcount") + "회"+ "\t" + rs.getInt("review_score") + "점"
						      + "\t" + rs.getString("review_regidate") + "\t" + rs.getString("review_contents") +"\n";
				rankList.add(result);
			}
			
		} catch (SQLException e) {
				e.printStackTrace();
		} finally {
				close();
		}

		return rankList;
	}

	// 평점순위별 후기 조회 (오름차순)
	@Override
	public List<String> ascRankList() {
		List<String> rankList = new ArrayList<>();
		 
		try {
			String sql =  " select  dense_rank() over(order by review_score asc) as rank "
						+ "       , review_no, com_name, viewcount "
						+ "		  , case when length(review_contents) > 13 then substr(review_contents, 1,10) || '...' else review_contents end as review_contents  "
						+ "       , review_score "
						+ "		  , to_char(review_regidate, 'yyyy-mm-dd') as review_regidate  "
						+ " from "
						+ " ( "
						+ " select review_no, viewcount, com_name, review_contents, review_score, review_regidate, fk_user_no "
						+ " from tbl_reviews A join tbl_companies B "
						+ " on A.fk_com_no = B.com_no " 
						+ " ) A join tbl_users B "
						+ " on A.fk_user_no = B.user_no "
						+ " order by rank, com_name, viewcount desc" ; 
	 
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			
			while(rs.next()) {

				String result = rs.getInt("rank") + "\t" +  rs.getInt("review_no") + "\t" 
						      + rs.getString("com_name") + "\t" + rs.getInt("viewcount") + "회"+ "\t" + rs.getInt("review_score") + "점"
						      + "\t" + rs.getString("review_regidate") + "\t" + rs.getString("review_contents") +"\n";
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
			String sql = " update tbl_reviews set review_status = 0 where review_no = ?  " ;
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, deleteno);
			
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


	// 공고번호찾기
	@Override
	public int searchPostno(MemberDTO member, String com_no) {
		int result = 0;
		
		try {
			String sql =  " with  "
						+ " A as "
						+ " ( "
						+ " select  fk_work_tcode,fk_user_no, applys_date "
						+ " from tbl_applys A join tbl_resumes B "
						+ " on A.fk_resume_no = B.resume_no "
						+ " ) "
						+ " , B as "
						+ " ( "
						+ " select com_no, com_id, com_name, post_title, fk_work_tcode, job_postno "
						+ " from tbl_companies A join tbl_job_posts B "
						+ " on A.com_no = B.fk_com_no "
						+ " ) "
						+ " select job_postno, com_no ,com_name, post_title, applys_date "
						+ " from A join B "
						+ " on A.fk_work_tcode = B.fk_work_tcode "
						+ " where fk_user_no = ? and com_no = ? " ; 
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member.getUser_no());
			pstmt.setString(2, com_no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = 1;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				close();
		}
		
		return result;
	}


	// 회사 존재유무
	@Override
	public boolean compareNo(String comno) {
		
		   String sql = " select com_no from tbl_companies where com_no = ? ";

		      boolean result = false; // 아이디가 있는지 없는지 비교

		      try {

		         pstmt = conn.prepareStatement(sql);
		         pstmt.setString(1, comno); // 입력받은 아이디 설정
		         rs = pstmt.executeQuery();

		         // 있는지 검사
		         if (rs.next()) {
		            result = true;
		         }

		      } catch (SQLException e) {
					if(e.getErrorCode() == 1722) {
						
					} else {
						e.printStackTrace();
					}
				
				} finally {
						close();
				}

		      return result;

	}



	// 회사별평점평균 
	@Override
	public List<String> avgCom() {
		
		List<String> avgList = new ArrayList<>();
		
		String result = "";
		try {
			
			String sql =  " select  rank() over(order by avg(review_score) desc) rank "
						+ "      , com_name\r\n"
						+ "      , trunc(avg(review_score),1) as review_score "
						+ " from tbl_reviews A join tbl_companies B\r\n"
						+ " on A.fk_com_no = B.com_no "
						+ " group by com_name "
						+ " order by review_score desc " ; 
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				result = rs.getString("rank") + "위\t" + rs.getString("com_name")+" : " + rs.getDouble("review_score") + "\n" ; 
				
				avgList.add(result);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				close();
		}

		return avgList;
	}



}
	
