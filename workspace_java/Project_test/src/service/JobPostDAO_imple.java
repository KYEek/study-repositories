package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.ProjectDBConnection;
import member.domain.JobPostDTO;


public class JobPostDAO_imple implements JobPostDAO {
  
	 Connection conn = ProjectDBConnection.getConn();
     PreparedStatement pstmt;
     ResultSet rs; 
     
    
	@Override
	public void addJobPost(JobPostDTO jobPost) {
		
	}

   // 전체 공고 조회 메소드
    @Override
    public List<JobPostDTO> getAllJobPosts() {
        List<JobPostDTO> jobPosts = new ArrayList<>();
        String sql = " SELECT jp.*, c.com_name " +
                     " FROM tbl_job_posts jp " +
                     " JOIN tbl_companies c ON jp.fk_com_no = c.com_no "; // sql selecet 문 자바에 가져오기
        
        try {
        	
        	pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery(); 

            while (rs.next()) {
                JobPostDTO jobPost = new JobPostDTO();
                jobPost.setWage(rs.getInt("wage"));
                jobPost.setJop_postno(rs.getInt("job_postno"));
                jobPost.setPost_title(rs.getString("post_title"));
                jobPost.setCom_name(rs.getString("com_name"));
                jobPost.setPost_contents(rs.getString("post_contents"));
                jobPost.setWrite_date(rs.getString("write_date"));
                jobPost.setEnd_date(rs.getString("end_date"));
                jobPost.setView_count(rs.getInt("view_count"));
                jobPost.setFk_com_no(rs.getInt("fk_com_no"));
                jobPost.setFk_work_TCODE(rs.getInt("fk_work_TCODE"));
                jobPost.setFk_job_TCODE(rs.getInt("fk_job_TCODE"));

                jobPosts.add(jobPost);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // 예외 발생 시 출력
        }finally {
	        
	        try {
	            if (rs != null) rs.close();
	            if (pstmt != null) pstmt.close();
	        } catch (SQLException e) {
	            e.printStackTrace(); // 자원을 사용했으면 반납해준다. 
	        }
	    }
        return jobPosts;
    }



	@Override
	// 상세 공고 조회 메소드
	public JobPostDTO getJobPostDetail(int jobPostNo) {
	    JobPostDTO jobPost = null;

	    try {
	        // 데이터베이스 연결
	        conn = ProjectDBConnection.getConn(); // 연결 객체 가져오기
	        String sql = " SELECT jp.*, c.com_name, j.job_type " 
	                   + " FROM TBL_JOB_POSTS jp " 
	        		   + " JOIN TBL_COMPANIES c ON jp.fk_com_no = c.com_no " 
	                   + " JOIN TBL_JOB_TYPES j ON jp.fk_job_tcode = j.job_code " 
	                   + " WHERE jp.job_postno = ? "; 

	        // PreparedStatement 객체 생성
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, jobPostNo); // ?에 값 바인딩

	        // 쿼리 실행
	        rs = pstmt.executeQuery();

	        // 결과 처리
	        if (rs.next()) {
	            jobPost = new JobPostDTO();
	            // 결과에서 데이터 추출
	            jobPost.setJop_postno(rs.getInt("job_postno"));
	            jobPost.setCom_name(rs.getString("com_name"));
	            jobPost.setJob_type(rs.getString("job_type"));
	            jobPost.setWage(rs.getInt("wage"));
	            jobPost.setWrite_date(rs.getString("write_date"));
	            jobPost.setEnd_date(rs.getString("end_date"));
	            jobPost.setView_count(rs.getInt("view_count"));
	            jobPost.setPost_contents(rs.getString("post_contents"));
	            jobPost.setPost_title(rs.getString("post_title"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); 
	    } finally {
	        
	        try {
	            if (rs != null) rs.close();
	            if (pstmt != null) pstmt.close();
	        } catch (SQLException e) {
	            e.printStackTrace(); // 자원을 사용하면 반납해준다......
	        }
	    }

	    return jobPost;
	}



	@Override
	// 조건 검색 조회 메소드
	  public List<JobPostDTO> searchJobPosts(String jop_postno, String com_name, String wage, String job_type) {

		List<JobPostDTO> jobPosts = new ArrayList<>();
        String sql = " SELECT jp.*, c.com_name, j.job_type "
                   + " FROM TBL_JOB_POSTS jp "
                   + " JOIN TBL_COMPANIES c ON jp.fk_com_no = c.com_no "
                   + " JOIN TBL_JOB_TYPES j ON jp.fk_job_tcode = j.job_tcode "
                   + " WHERE (1=1)";
       
        // 조건에 따라 SQL 문을 동적으로 구성
        if (!jop_postno.isEmpty()) {
            sql += " AND c.com_no = ?";
        }
        if (!com_name.isEmpty()) {
            sql += " AND c.com_name LIKE ?";
        }
        if (!wage.isEmpty()) {
            sql += " AND jp.wage = ?";
        }
        if (!job_type.isEmpty()) {
            sql += " AND jp.write_date >= ?"; // 시작일 기준으로 공고 기간 조건 추가
        }

        try {
        	
        	pstmt = conn.prepareStatement(sql);  

            int index = 1;

            if (!jop_postno.isEmpty()) {
                pstmt.setInt(index++, Integer.parseInt(jop_postno));
            }
            if (!com_name.isEmpty()) {
                pstmt.setString(index++, "%" + com_name + "%");
            }
            if (!wage.isEmpty()) {
                pstmt.setInt(index++, Integer.parseInt(wage));
            }
            if (!job_type.isEmpty()) {
                pstmt.setString(index++, job_type); // 날짜 형식에 맞춰 입력
            }

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                JobPostDTO jobPost = new JobPostDTO();
                
                // 여기에 jobPost의 필드 설정
                jobPosts.add(jobPost);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
	        
	        try {
	            if (rs != null) rs.close();
	            if (pstmt != null) pstmt.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

        return jobPosts;
    }


	    @Override
	    public List<JobPostDTO> getAppliedJobPosts(int userNo) {
	        List<JobPostDTO> appliedPosts = new ArrayList<>();
	
	        String sql = " SELECT * FROM TBL_APPLIED_JOBPOSTS WHERE USER_NO = ? ";
	        
	        try {
	        	 pstmt = conn.prepareStatement(sql);
	             pstmt.setInt(1, userNo);
	             rs = pstmt.executeQuery();
	            
	            while ( rs.next()) {
	                JobPostDTO post = new JobPostDTO();
	                post.setJop_postno(	rs.getInt("JOP_POSTNO"));
	                post.setCom_name(rs.getString("COM_NAME"));
	                post.setJob_type (rs.getString("JOB_TYPE"));
	                post.setWage(rs.getInt("WAGE"));
	                post.setWrite_date( rs.getString("WRITE_DATE"));
	                post.setEnd_date( rs.getString("END_DATE"));
	                
	                appliedPosts.add(post);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }finally {
		        
		        try {
		            if (rs != null) rs.close();
		            if (pstmt != null) pstmt.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
	        
	        return appliedPosts;
	    }

	    // 나의 이력서를 조회하는 메소드
		@Override
		public List<JobPostDTO> showAllResumeList() {
		        List<JobPostDTO> jobpostList = new ArrayList();
			
			try {
		        // 데이터베이스 연결
		        conn = ProjectDBConnection.getConn(); // 연결 객체 가져오기
		        String sql = " select resume_no, res_title, res_intro, res_career, res_hwage "
		        		   + " from tbl_resumes ";

		        // PreparedStatement 객체 생성
		        pstmt = conn.prepareStatement(sql);
		        rs = pstmt.executeQuery();
		        
		    

		        // 결과 처리
		        if (rs.next()) {
		        	JobPostDTO jobpost = new JobPostDTO();
		            // 결과에서 데이터 추출
		        	jobpost.setResume_no(rs.getInt("resume_no"));
		        	jobpost.setRes_title(rs.getString("res_title"));
		        	jobpost.setRes_intro(rs.getString("res_intro"));
		        	jobpost.setRes_career(rs.getInt("res_career"));
		        	jobpost.setRes_hwage(rs.getInt("res_hwage"));
		        	
		        	jobpostList.add(jobpost);
		    
		        }
		    } catch (SQLException e) {
		        e.printStackTrace(); 
		    } finally {
		        
		        try {
		            if (rs != null) rs.close();
		            if (pstmt != null) pstmt.close();
		        } catch (SQLException e) {
		            e.printStackTrace(); // 자원을 사용하면 반납해준다......
		        }
		    }

		    return jobpostList;
		}
}
	

 




















    

