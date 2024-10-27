package comment.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import comment.domain.CommentDTO;
import common.ProjectDBConnection;
import review.domain.ReviewDTO;
import user.domain.CompanyDTO;

public class CommentDAO_imple implements CommentDAO {

	
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

	
	
	// 답변작성하기    
	@Override
	public int writeComment(Map<String, String> paraMap) {
		
		int result = 0;
		
		try {
			String sql = " insert  into tbl_comment (comment_no, fk_review_no, fk_com_no, comment_contents, comment_regidate, comment_status) "
					   + " values (seq_comment.nextval, ?, ?, ?, sysdate, 1) " ;
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, paraMap.get("fk_reviewno"));
			pstmt.setString(2, paraMap.get("comno"));
			pstmt.setString(3, paraMap.get("contents"));
			
			result = pstmt.executeUpdate();	 // sql 실행

			
		} catch (SQLException e) {
			
			if(e.getErrorCode() == 2291) {
		
				System.out.println(">> 입력하신 원글번호 " + paraMap.get("fk_review_no") + " 은(는) 게시글에 존재하지 않습니다. << \n");
				result = -1;
			}
			else {
				e.printStackTrace();
			}
			
		} finally {
			
			close();
		}
		return result;
		
	}


	// 답변조회하기 
	@Override
	public List<CommentDTO> commentList(String review_no) {

		List<CommentDTO> commentList = new ArrayList<>();
		
		try {
			
			String sql =  " select com_name, comment_contents"
					    + " ,to_char(comment_regidate, 'yyyy-mm-dd') as comment_regidate "
						+ " from tbl_comment A join tbl_companies B "
						+ " on A.fk_com_no = B.com_no "
						+ " where A.fk_review_no = ? ";
				
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, review_no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				CommentDTO commentdto = new CommentDTO();
				commentdto.setComment_contents(rs.getString("comment_contents"));
				commentdto.setComment_regidate(rs.getString("comment_regidate"));
				
				CompanyDTO company = new CompanyDTO();
				company.setCom_name(rs.getString("com_name"));

				commentdto.setCompany(company);

				commentList.add(commentdto);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return commentList;
	}


	// 답변유무판별   
	@Override
	public boolean existComment(int fk_review_no) {
		
		
		boolean result = false;
		
		try {
			String sql =  " select * "
						+ " from tbl_comment "
						+ " where fk_review_no = ? ";
		
		     result = false; // 아이디가 있는지 없는지 비교

	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, fk_review_no);
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


	// 내가 작성했었던 답변 목록들  
	@Override
	public List<String> MycommentList(CompanyDTO company) {
		
		List<String> commentList = new ArrayList<>();
		
		try {
			String sql =  " select comment_no, fk_review_no, comment_contents"
					    + " , to_char(comment_regidate, 'yyyy-mm-dd') as comment_regidate"
						+ " from tbl_comment "
						+  "where fk_com_no = ?";
					
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, company.getCom_no());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				String result = rs.getInt("comment_no") + "\t" + rs.getInt("fk_review_no") + "\t" 
				 + rs.getString("comment_regidate") + "\t" + rs.getString("comment_contents"); 
				
				commentList.add(result);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return commentList;
	}


	// 수정할답변보여줌 
	@Override
	public CommentDTO ViewComment(String review_no) {

		CommentDTO comment = null; 
		
		try {
			String sql =  " select fk_review_no, comment_contents, comment_regidate "
						+ " from tbl_comment "
						+ " where fk_review_no = ? and comment_status = 1 ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, review_no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				comment = new CommentDTO();

				comment.setFk_review_no(rs.getInt("fk_review_no"));
				comment.setComment_contents(rs.getString("comment_contents"));
				comment.setComment_regidate(rs.getString("comment_regidate"));
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
		
		return comment;

	}


	// 답변수정하기 
	@Override
	public int updateComment(Map<String, String> paraMap) {
		
		int result = 0;
		
		try {
			
			String sql =  " update tbl_comment set comment_contents = ? "
						+ " where fk_review_no = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMap.get("comment_contents"));
			pstmt.setString(2, paraMap.get("review_no"));

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return result;
	}


	// 기업이 답변을 작성한적이 있는가 ?
	@Override
	public boolean existMyComment(String com_no) {
		boolean result = false;
		
		try {
			String sql =  " select * "
						+ " from tbl_comment "
						+ " where fk_com_no = ? ";
			
		     result = false; // 아이디가 있는지 없는지 비교

	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, com_no);
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



	// 답변삭제하기 
	@Override
	public int deleteComment(String review_no) {
		
		int result = 0;
		
		try {
			String sql = " update tbl_comment set comment_status = 0 where fk_review_no = ?  " ;
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, review_no);
			
			result = pstmt.executeUpdate();	 // sql 실행
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	    return result;
	}






}
