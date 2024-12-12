package jobpost.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import common.ProjectDBConnection;
import user.domain.CompanyDTO;
import user.domain.MemberDTO;
import jobpost.member.domain.JobPostDTO;


public class JobPostDAO_imple implements JobPostDAO {
  
	 Connection conn = ProjectDBConnection.getConn();
     PreparedStatement pstmt;
     ResultSet rs; 
     
    
	

   // 전체 공고 조회 메소드
    @Override
    public List<JobPostDTO> getAllJobPosts() {
        List<JobPostDTO> jobPosts = new ArrayList<>();
        String sql = " SELECT jp.*, c.com_name " +
                     " FROM tbl_job_posts jp " +
                     " JOIN tbl_companies c ON jp.fk_com_no = c.com_no "
                     + " where jp.post_status = 1 "; // sql selecet 문 자바에 가져오기
        
        try {
        	
        	pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery(); 

            while (rs.next()) {
                JobPostDTO jobPost = new JobPostDTO();
                jobPost.setWage(rs.getInt("wage"));
                jobPost.setJob_postno(rs.getInt("job_postno"));
                jobPost.setPost_title(rs.getString("post_title"));
                jobPost.setCom_name(rs.getString("com_name"));
                jobPost.setPost_contents(rs.getString("post_contents"));
                jobPost.setWrite_date(rs.getString("write_date"));
                jobPost.setEnd_date(rs.getString("end_date"));
                jobPost.setView_count(rs.getInt("view_count"));
                jobPost.setFk_com_no(rs.getInt("fk_com_no"));              
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
	                   + " WHERE jp.job_postno = ? and post_status = 1 "; 

	        // PreparedStatement 객체 생성
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, jobPostNo); // ?에 값 바인딩

	        // 쿼리 실행
	        rs = pstmt.executeQuery();

	        // 결과 처리
	        if (rs.next()) {
	            jobPost = new JobPostDTO();
	            // 결과에서 데이터 추출
	            jobPost.setJob_postno(rs.getInt("job_postno"));
	            jobPost.setCom_name(rs.getString("com_name"));
	            jobPost.setJob_type(rs.getString("job_type"));
	            jobPost.setWage(rs.getInt("wage"));
	            jobPost.setWrite_date(rs.getString("write_date"));
	            jobPost.setEnd_date(rs.getString("end_date"));
	            jobPost.setView_count(rs.getInt("view_count"));
	            jobPost.setPost_contents(rs.getString("post_contents"));
	            jobPost.setFk_work_TCODE(rs.getInt("fk_work_tcode"));
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


    // 공고 조건 검색 메소드
	@Override
	public List<JobPostDTO> searchJobPosts(String jobPostNo, String companyName, String wage, String jobType) {
	    // 조건에 맞는 공고 목록을 담을 리스트 초기화
	    List<JobPostDTO> filteredPosts = new ArrayList<>();
	    // 모든 입력란이 비어있으면 경고 메시지 출력
	    if ((jobPostNo == null || jobPostNo.isEmpty()) &&
	        (companyName == null || companyName.isEmpty()) &&
	        (wage == null || wage.isEmpty()) &&
	        (jobType == null || jobType.isEmpty())) {
	        System.out.println("[경고]: 검색할 조건을 적어도 한개는 입력해야 합니다.");
	        return Collections.emptyList(); // 빈 리스트 반환
	    }
	    


	    // 공고 조건 검색을 위한 SQL 쿼리 문자열 생성
	    String sql = "SELECT jp.job_postno, c.com_name, jp.wage, jt.job_type, jp.write_date AS start_date, jp.end_date, jp.view_count " +
	                 "FROM tbl_job_posts jp " +   // 공고 정보를 담은 테이블과
	                 "JOIN tbl_companies c ON jp.fk_com_no = c.com_no " +   // 회사 테이블을 회사 ID(fk_com_no)를 통해 조인
	                 "JOIN tbl_job_types jt ON jp.fk_job_tcode = jt.job_code " + // 직종명 테이블을 직종 코드(fk_job_tcode)로 조인
	                 "WHERE post_status =1 ";   // 기본 조건 설정 (후속 조건을 추가하기 위함)

	    // SQL 조건을 사용자가 입력한 값에 따라 추가
	    if (jobPostNo != null && !jobPostNo.isEmpty()) sql += " AND job_postno = ? ";   // 공고 번호 조건
	    if (companyName != null && !companyName.isEmpty()) sql += " AND com_name LIKE ? ";   // 회사 이름 조건
	    if (wage != null && !wage.isEmpty()) sql += " AND wage >= ? ";   // 연봉 조건
	    if (jobType != null && !jobType.isEmpty()) sql += " AND job_type LIKE ? ";   // 직종명 조건

	    try {
	        // 데이터베이스 연결 생성
	        Connection conn = ProjectDBConnection.getConn();
	        
	        // 조건에 따라 SQL을 준비하여 PreparedStatement 생성
	        PreparedStatement pstmt = conn.prepareStatement(sql);

	        // SQL에 설정할 파라미터 인덱스 초기화
	        int paramIndex = 1;
	        // SQL에 설정할 파라미터 인덱스에 따라 값 설정
	        if (jobPostNo != null && !jobPostNo.isEmpty()) {
	            pstmt.setInt(paramIndex++, Integer.parseInt(jobPostNo)); // 공고번호 파라미터 설정
	        }
	        if (companyName != null && !companyName.isEmpty()) {
	            pstmt.setString(paramIndex++, "%" + companyName + "%"); // 회사 이름 파라미터 설정
	        }
	        if (wage != null && !wage.isEmpty()) {
	            pstmt.setInt(paramIndex++, Integer.parseInt(wage)); // 연봉 파라미터 설정
	        }
	        if (jobType != null && !jobType.isEmpty()) {
	            pstmt.setString(paramIndex++, "%" + jobType + "%"); // 직종명 파라미터 설정
	        }


	        // SQL 실행 및 결과 가져오기
	        ResultSet rs = pstmt.executeQuery();
	        
	        // 결과 집합을 반복하면서 각 공고 정보를 JobPostDTO 객체로 변환
	        while (rs.next()) {
	            JobPostDTO post = new JobPostDTO();
	            post.setJob_postno(rs.getInt("job_postno"));  // 공고번호 설정
	            post.setCom_name(rs.getString("com_name"));   // 회사 이름 설정
	            post.setWage(rs.getInt("wage"));              // 연봉 설정
	            post.setJob_type(rs.getString("job_type"));   // 직종명 설정
	            post.setWrite_date(rs.getString("start_date")); // 공고 시작일 설정
	            post.setEnd_date(rs.getString("end_date"));   // 공고 종료일 설정
	            post.setView_count(rs.getInt("view_count"));  // 조회수 설정

	            filteredPosts.add(post);  // 리스트에 공고 객체 추가
	        }

	        
	    } catch (SQLException e) {
	        // SQL 예외 발생 시 스택 트레이스 출력
	        e.printStackTrace();
	    }

	    // 조건에 맞는 공고 목록 반환
	    return filteredPosts;
	}

	/*
	
	// 입력 검증 메서드 구현
    @Override
    public boolean isValidJobPostNo(String jobPostNo) {
        return jobPostNo != null && !jobPostNo.isEmpty() && jobPostNo.matches("\\d+");
    }

    @Override
    public boolean isValidCompanyName(String companyName) {
        return companyName != null && !companyName.isEmpty();
    }

    @Override
    public boolean isValidWage(String wage) {
        return wage != null && !wage.isEmpty() && wage.matches("\\d+");
    }

    @Override
    public boolean isValidJobType(String jobType) {
        return jobType != null && !jobType.isEmpty();
    }

   */

	    // 나의 이력서를 조회하는 메소드
		@Override
		public List<JobPostDTO> showAllResumeList(MemberDTO member) {
		        List<JobPostDTO> jobpostList = new ArrayList();
			
			try {
		        // 데이터베이스 연결
		        conn = ProjectDBConnection.getConn(); // 연결 객체 가져오기
		        String sql = " select resume_no, res_title, res_intro, res_career, res_hwage "
		        		   + " from tbl_resumes where resume_status = 1 and fk_user_no = ? ";

		        // PreparedStatement 객체 생성
		        pstmt = conn.prepareStatement(sql);
		        pstmt.setInt(1, member.getUser_no());
		        rs = pstmt.executeQuery();
		        
		        
		    

		        // 결과 처리
		        while (rs.next()) {
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
	








		
		// 내가 지원한 공고 조회 메소드
		@Override
		public List<JobPostDTO> ViewAppliedJobPosts(int userNo) {
		    List<JobPostDTO> appliedPosts = new ArrayList<>();
		    String sql = " select B.com_no, B.com_name, A.job_type, A.WAGE, A.write_date, A.end_date, A.APPLY_NO, A.JOB_POSTNO "
		    		   + " from "
		    		   + " (select C.JOB_POSTNO, D.APPLY_NO, fk_com_no, job_type, write_date, end_date, C.WAGE "
		    		   + " from tbl_job_posts C "
		    		   + "     JOIN (SELECT APPLY_NO, FK_JOB_POSTNO "
		    		   + "           FROM TBL_APPLYS "
		    		   + "           WHERE fk_resume_no in (select RESUME_NO "
		    		   + "           from tbl_resumes "
		    		   + "           where fk_user_no = ? )) D "
		    		   + "     on D.FK_JOB_POSTNO  = C.JOB_POSTNO "
		    		   + "     JOIN tbl_job_types F "
		    		   + "     on C.fk_job_tcode = F.job_code where C.post_status = 1) A "
		    		   + "     JOIN tbl_companies B "
		    		   + "     on B.com_no = A.FK_com_no "; // 사용자 번호를 매개변수로 사용

		    try {
		        pstmt = conn.prepareStatement(sql);
		        pstmt.setInt(1, userNo);
		        rs = pstmt.executeQuery();
		        
		        while (rs.next()) {
		            JobPostDTO post = new JobPostDTO();
		            post.setJob_postno(rs.getInt("JOB_POSTNO"));
		            post.setApply_no(rs.getInt("APPLY_NO"));
		            post.setFk_com_no(rs.getInt("com_no"));
		            post.setCom_name(rs.getString("com_name"));
		            post.setJob_type(rs.getString("job_type"));
		            post.setWage(rs.getInt("wage"));
		            post.setWrite_date(rs.getString("write_date"));
		            post.setEnd_date(rs.getString("end_date"));
		            
		            appliedPosts.add(post);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        try {
		            if (rs != null) rs.close();
		            if (pstmt != null) pstmt.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		    
		    return appliedPosts;
		}

		
		
		
//		
//		@Override
//	    public boolean isAlreadyApplied(int jobPostNo, int resumeNo) {
//	        String sql = "SELECT COUNT(*) FROM TBL_APPLYS WHERE FK_JOB_POSTNO = ? AND FK_RESUME_NO = ?";
//	        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
//	            pstmt.setInt(1, jobPostNo);
//	            pstmt.setInt(2, resumeNo);
//	            ResultSet rs = pstmt.executeQuery();
//	            if (rs.next()) {
//	                return rs.getInt(1) > 0; // 이미 지원한 경우 true 반환
//	            }
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        }
//	        return false; // 지원하지 않은 경우 false 반환
//	    }
//		
	
		
 

		
		
		// 공고지원 
		@Override
		public boolean applyjobpostmenu(int jobPostno, int resumeNo) {
			String sql = " INSERT INTO TBL_APPLYS ( apply_no, FK_JOB_POSTNO, FK_RESUME_NO) "
					   + " VALUES (apply_seq.nextval, ?, ?) ";


			// 자동 커밋 비활성화
			try {
				conn.setAutoCommit(false); // 자동 커밋 비활성화

				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, jobPostno); // 공고 번호 입력
				pstmt.setInt(2, resumeNo); // 이력서 번호 입력

				int rowsAffected = pstmt.executeUpdate(); // 데이터 삽입 실행

				// 커밋
				conn.commit(); // 데이터 삽입 후 커밋

				return rowsAffected > 0; // 삽입된 행이 있을 경우 true 반환
			} catch (SQLException e) {
				
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				} // 오류 발생 시 롤백
				
				if(e.getErrorCode() == 1400 )
					System.out.println("[경고!]:없는 공고 번호입니다. 다시 입력하세요!");
				
				return false; // 오류 발생 시 false 반환
			}

			finally {

				try {
					conn.setAutoCommit(true);
				} catch (SQLException e) {
					e.printStackTrace();
				} // 자동 커밋 활성화 (기본값으로 복구)

			}
		}//end of method-------------------------------------

		

		
		
		// 공고 지원 여부 확인 메소드
		@Override
		public boolean isAlreadyApplied(int jobPostNo, int resumeNo) {
		    String sql = "SELECT * FROM TBL_APPLYS WHERE FK_JOB_POSTNO = ? AND FK_RESUME_NO = ?";
		    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
		        pstmt.setInt(1, jobPostNo);
		        pstmt.setInt(2, resumeNo);
		        ResultSet rs = pstmt.executeQuery();
		        if (rs.next()) {
		            return true; // 이미 지원한 경우 true 반환
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return false; // 지원하지 않은 경우 false 반환
		}
         
		
		
		
		
		
		
		
		
		
		// 공고 지원 삭제 메소드 
		@Override
		public boolean canclejobpostmenu(int int1, int user_no) {
			String sql = "  delete "
					   + "     from tbl_applys "
					   + "     where fk_job_postno in (SELECT FK_JOB_POSTNO "
					   + "                             FROM TBL_APPLYS "
					   + "                             WHERE fk_resume_no in (select RESUME_NO "
					   + "                             from tbl_resumes "
					   + "                             where fk_user_no = ?)) and apply_no = ? ";


			// 자동 커밋 비활성화
			try {
				conn.setAutoCommit(false); // 자동 커밋 비활성화

				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, user_no);
				pstmt.setInt(2, int1);
				
				int rowsAffected = pstmt.executeUpdate(); // 데이터 삽입 실행
   
				// 커밋
				conn.commit(); // 데이터 삽입 후 커밋

				return rowsAffected > 0; // 삽입된 행이 있을 경우 true 반환
			} catch (SQLException e) {
				
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				} // 오류 발생 시 롤백
				
				if(e.getErrorCode() == 1400 )
					System.out.println("[경고!]:없는 공고 번호입니다. 다시 입력하세요!");
				
				return false; // 오류 발생 시 false 반환
			}

			finally {

				try {
					conn.setAutoCommit(true);
				} catch (SQLException e) {
					e.printStackTrace();
				} // 자동 커밋 활성화 (기본값으로 복구)

			}
		}

		
	

		
		// 공고 등록 메소드
		@Override
		public boolean insertjobpost(JobPostDTO jp, CompanyDTO company) {
		     
			String sql = " INSERT INTO TBL_JOB_POSTS (  job_postno, "
						+ "                             post_title, "
						+ "                             post_contents, "
						+ "                             wage, "
						+ "                             end_date, "
						+ "                             fk_com_no, "
						+ "                             fk_work_tcode, "
						+ "                             fk_job_tcode) "
						+ "  values (job_postseq.nextval, ?, ?, ?, to_date(?, 'yyyy-mm-dd'), ?, ?, ? ) "; 
	

			// 자동 커밋 비활성화
			try {
				conn.setAutoCommit(false); // 자동 커밋 비활성화

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, jp.getPost_title()); // 공고 번호 입력
				pstmt.setString(2, jp.getPost_contents()); // 이력서 번호 입력
				pstmt.setInt(3, jp.getWage());
				pstmt.setString(4, jp.getEnd_date());
				pstmt.setInt(5, company.getCom_no());
				pstmt.setInt(6, jp.getFk_work_TCODE());
				pstmt.setInt(7, company.getFk_job_tcode());

				int rowsAffected = pstmt.executeUpdate(); // 데이터 삽입 실행

				// 커밋
				conn.commit(); // 데이터 삽입 후 커밋

				return rowsAffected > 0; // 삽입된 행이 있을 경우 true 반환
			} catch (SQLException e) {
				
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				} // 오류 발생 시 롤백
				
				
				return false; // 오류 발생 시 false 반환
			}

			finally {

				try {
					conn.setAutoCommit(true);
				} catch (SQLException e) {
					e.printStackTrace();
				} // 자동 커밋 활성화 (기본값으로 복구)

			}
		
			
			
		}
		// 기업이 공고 조회하는 메소드 
		@Override
		public List<JobPostDTO> getJobPostsByCompany(int companyId) {
		    List<JobPostDTO> jobPosts = new ArrayList<>();
		    String sql = " SELECT job_postno, post_title, post_contents, wage, end_date, write_date " +
		                 " FROM tbl_job_posts WHERE fk_com_no = ? and post_status = 1 "; 

		    try {
		         pstmt = conn.prepareStatement(sql);   
		         pstmt.setInt(1, companyId);
		         ResultSet rs = pstmt.executeQuery();
		        
		        while (rs.next()) {
		            JobPostDTO jobPost = new JobPostDTO();
		            jobPost.setJob_postno(rs.getInt("job_postno"));
		            jobPost.setPost_title(rs.getString("post_title"));
		            jobPost.setPost_contents(rs.getString("post_contents"));
		            jobPost.setWage(rs.getInt("wage"));
		            jobPost.setEnd_date(rs.getString("end_date"));
		            jobPost.setWrite_date(rs.getString("write_date"));
		            jobPosts.add(jobPost);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    
		    return jobPosts;
		}

		@Override
		public boolean updatejobpost(JobPostDTO jp, int jobPostNo) {
			String sql = " update TBL_JOB_POSTS "
					   + " set post_title = ? , post_contents =  ? , wage = ?, end_date = to_date(?, 'yyyy-mm-dd') , fk_work_tcode = ? "
					   + " where job_postno = ? "; 


		// 자동 커밋 비활성화
		try {
			conn.setAutoCommit(false); // 자동 커밋 비활성화

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, jp.getPost_title()); // 공고 번호 입력
			pstmt.setString(2, jp.getPost_contents()); // 이력서 번호 입력
			pstmt.setInt(3, jp.getWage());
			pstmt.setString(4, jp.getEnd_date().substring(0, 10));
			pstmt.setInt(5, jp.getFk_work_TCODE());
			pstmt.setInt(6,  jobPostNo); // 내가 수정할 공고 번호 입력한다. 
			

			int rowsAffected = pstmt.executeUpdate(); // 데이터 삽입 실행

			// 커밋
			conn.commit(); // 데이터 삽입 후 커밋

			return rowsAffected > 0; // 삽입된 행이 있을 경우 true 반환
		} catch (SQLException e) {

			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			} // 오류 발생 시 롤백
			
			
			return false; // 오류 발생 시 false 반환
		}

		finally {

			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			} // 자동 커밋 활성화 (기본값으로 복구)

		}
	
	
		}

		
		// 기업 공고 삭제 메소드 
		@Override
		public boolean deletejobpostmenu(int int1, int com_no) {
			String sql = " update tbl_JOB_POSTS set post_status = 0 "
					   + " where job_postno = ? and fk_com_no = ? ";


			// 자동 커밋 비활성화
			try {
				conn.setAutoCommit(false); // 자동 커밋 비활성화

				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, int1);
				pstmt.setInt(2, com_no);
				
				int rowsAffected = pstmt.executeUpdate(); // 데이터 삽입 실행

				// 커밋
				conn.commit(); // 데이터 삽입 후 커밋

				return rowsAffected > 0; // 삽입된 행이 있을 경우 true 반환
			} catch (SQLException e) {
				
				try {
					conn.rollback();

					e.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				} // 오류 발생 시 롤백
				
				if(e.getErrorCode() == 1400 )
					System.out.println("[경고!]:없는 공고 번호입니다. 다시 입력하세요!");
				
				return false; // 오류 발생 시 false 반환
			}

			finally {

				try {
					conn.setAutoCommit(true);
				} catch (SQLException e) {
					e.printStackTrace();
				} // 자동 커밋 활성화 (기본값으로 복구)

			}
		}



		

	

		
}		
		
		

		
	

	
		
		
		/*
		@Override
		// 특정 공고에 대한 지원 이력서를 조회하는 메소드
		public List<JobPostDTO> applyjobpostmenu(int jobPostno, int resumeNo) {
		    List<JobPostDTO> appliedPosts = new ArrayList<>();
		    
		    String sql = " SELECT * FROM TBL_APPLIED_JOBPOSTS "
		    		   + " WHERE JOB_POSTNO = ? AND RESUME_NO = ? ";
		    
		    try {
		        pstmt = conn.prepareStatement(sql);
		        pstmt.setInt(1, jobPostno); // 공고 번호 바인딩
		        pstmt.setInt(2, resumeNo); // 이력서 번호 바인딩
		        rs = pstmt.executeQuery();
		        
		        while (rs.next()) {
		            JobPostDTO post = new JobPostDTO();
		            // 필요한 필드에 대한 값을 설정
		            post.setJop_postno(rs.getInt("JOB_POSTNO"));
		            post.setCom_name(rs.getString("COM_NAME"));
		            post.setJob_type(rs.getString("JOB_TYPE"));
		            post.setWage(rs.getInt("WAGE"));
		            post.setWrite_date(rs.getString("WRITE_DATE"));
		            post.setEnd_date(rs.getString("END_DATE"));
		            
		            appliedPosts.add(post);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        try {
		            if (rs != null) rs.close();
		            if (pstmt != null) pstmt.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }

		    return appliedPosts;
		}

		*/

		
		
		
		
		
		














    

