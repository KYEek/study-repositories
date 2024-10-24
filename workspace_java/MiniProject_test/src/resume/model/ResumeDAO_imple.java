package resume.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.ProjectDBConnection;
import user.domain.MemberDTO;
import resume.domain.ResumeDTO;



public class ResumeDAO_imple implements ResumeDAO {
	
	// field, attribute, property, 속	
	private Connection conn = ProjectDBConnection.getConn();
	private PreparedStatement pstmt;
	private ResultSet rs;

	
	
	// method, operation, 기능
	// === 자원 반납을 해주는 메소드 === //
		private void close() {
			try {
				if (rs 	  != null) 	{rs.close(); 	rs 	  = null;}
				if (pstmt != null) 	{pstmt.close(); pstmt = null;}
			} catch (SQLException e) {
				e.printStackTrace();
			}// end of try~catch (SQLException e) ---------------
		}// end of private void close() -----------------
		
		
	// == 이력서를 등록하는 메소드 == //
	@Override
	public int register_resume(ResumeDTO resume) {
		int result = 0;
		
		try {
			String sql	= " insert into tbl_resumes(resume_no, res_title, res_intro, res_career, res_hwage, "
					+ "fk_user_no, fk_edu_code, fk_major_code, fk_work_tcode) "
					+ "values(resume_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?) ";
			
			pstmt = conn.prepareStatement(sql);		// sql 문을 담음
			
			// 위치홀더에 값 설정
			pstmt.setString(1, resume.getRes_title());
			pstmt.setString(2, resume.getRes_intro());
			pstmt.setInt(3, resume.getRes_career());
			pstmt.setInt(4, resume.getRes_hwage());
			pstmt.setInt(5, resume.getFk_user_no());
			pstmt.setInt(6, resume.getFk_edu_code());
			pstmt.setInt(7, resume.getFk_major_code());
			pstmt.setInt(8, resume.getFk_work_tcode());
			
			
			// 쿼리문 실행 -> 결과 result 에 대입 (성공한 행의 개수)
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}// end of try~catch (SQLException e) ----------------------
		
		
		return result;
	}// end of public int register_resume(ResumeDTO resume) --------------

	
	
	
	// == 내 이력서를 '전체' 조회해주는 메소드 == //
	@Override
	public List<ResumeDTO> view_all_resume(MemberDTO member) {
		
		List<ResumeDTO> resumeList = new ArrayList<>();
		
		try {
			String sql	= " select R.resume_no, U.user_name, func_age(user_jubun) AS age, R.res_title, R.res_regiday "
					+ " from tbl_resumes R JOIN tbl_users U "
					+ " ON R.fk_user_no = U.user_no "
					+ " WHERE R.fk_user_no = ? and resume_status = 1 ";
			
			pstmt = conn.prepareStatement(sql);		// sql 문을 담음
			
			pstmt.setInt(1, member.getUser_no());
			
			
			
			// 쿼리문 실행
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ResumeDTO resume = new ResumeDTO();
			
				resume.setResume_no(rs.getInt("resume_no"));			// 이력서 번호
				
				member.setUser_name(rs.getString("user_name"));			// 작성자명 세팅
				resume.setMbdto(member);		// resume 에 있는 Mbdtd 에 member 객체를 세팅
				
				resume.setAge(rs.getInt("age"));						// 나이
				resume.setRes_title(rs.getString("res_title")); 		// 이력서 제목	
				resume.setRes_regiday(rs.getString("res_regiday"));	 	// 이력서 작성일자
				
				resumeList.add(resume);
				
			}// end of while (rs.next()) ------------------- 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}// end of try~catch (SQLException e) ----------------------
		
		return resumeList;
		
	}// end of public List<ResumeDTO> view_all_resume(MemberDTO member)

	
	
	
	// == 내 이력서를 '상세' 조회해주는 메소드 == //
	@Override
	public ResumeDTO view_detail_resume(MemberDTO member, String resume_no) {
		
		ResumeDTO resume_detail = null;
				
		try {
			String sql	= " select res_title, user_name, age, gender, user_email, edu_degree, major_name, res_career, work_type, res_hwage, res_intro "
					+ " FROM "
					+ "    ( "
					+ "    select res_title, user_name, age, gender, user_email, edu_degree, major_name, res_career, fk_work_tcode, res_hwage, res_intro "
					+ "    FROM "
					+ "    ( "
					+ "        select res_title, user_name, age, gender, user_email, edu_degree, fk_major_code, res_career, fk_work_tcode, res_hwage, res_intro "
					+ "        FROM "
					+ "        ( "
					+ "            select res_title, user_name, func_age(user_jubun) AS age, func_gender(user_jubun) AS gender, user_email, fk_edu_code, fk_major_code, res_career, fk_work_tcode, res_hwage, res_intro "
					+ "            FROM tbl_resumes R JOIN tbl_users U "
					+ "            ON R.fk_user_no = U.user_no "
					+ "            WHERE R.fk_user_no = ? and resume_no = ? and resume_status = 1 "
					+ "        )R JOIN tbl_education E "
					+ "        ON R.fk_edu_code = edu_code "
					+ "    )A JOIN tbl_major M\r\n"
					+ "    ON A.fk_major_code = M.major_code "
					+ "    )B JOIN tbl_work_types W "
					+ " ON B.fk_work_tcode = W.work_tcode ";
			
			pstmt = conn.prepareStatement(sql);		// sql 문을 담음
			
			pstmt.setInt(1, member.getUser_no());
			pstmt.setString(2, resume_no);
			
			rs = pstmt.executeQuery();
			
			
			if(rs.next()) {
				resume_detail = new ResumeDTO();
				resume_detail.setFk_user_no(member.getUser_no());
				
				resume_detail.setRes_title(rs.getString("res_title"));
	
				member.setUser_name(rs.getString("user_name"));			// 작성자명 세팅
				member.setUser_email(rs.getString("user_email"));			// 작성자명 세팅
				resume_detail.setMbdto(member);		// resume 에 있는 Mbdtd 에 member 객체를 세팅
				
				
				resume_detail.setAge(rs.getInt("age"));
				resume_detail.setGender(rs.getString("gender"));
				resume_detail.setEdu_degree(rs.getString("edu_degree"));
				resume_detail.setMajor_name(rs.getString("major_name"));
				resume_detail.setRes_career(rs.getInt("res_career"));
				resume_detail.setWork_type(rs.getString("work_type"));
				resume_detail.setRes_hwage(rs.getInt("res_hwage"));
				resume_detail.setRes_intro(rs.getString("res_intro"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}// end of try~catch (SQLException e) ----------------------
		
		return resume_detail;
	}// end of public ResumeDTO view_detail_resume(MemberDTO member) ----------

	
	
	// == 내 이력서를 수정해주는 메소드 == //
	@Override
	public int update_resume(Map<String, String> paraMap, String resume_no) {
		
		int result = 0;
		
		try {
			
			String sql = " update tbl_resumes set res_title = ?, fk_edu_code = ?, fk_major_code = ?, "
					+ " res_career = ?, fk_work_tcode = ?, res_hwage = ?, res_intro = ? "
					+ " where resume_no = ? and resume_status = 1 ";
			
			pstmt = conn.prepareStatement(sql);		// sql 문을 담음
			
			pstmt.setString(1, paraMap.get("res_title"));
			pstmt.setString(2, paraMap.get("fk_edu_code"));
			pstmt.setString(3, paraMap.get("fk_major_code"));
			pstmt.setString(4, paraMap.get("res_career"));
			pstmt.setString(5, paraMap.get("fk_work_tcode"));
			pstmt.setString(6, paraMap.get("res_hwage"));
			pstmt.setString(7, paraMap.get("res_intro"));
			pstmt.setString(8, resume_no);
			
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			if(e.getErrorCode() == 1407) {
				System.out.println(">> 데이터 미입력으로 정보 변경 없음 <<\n");
			}
			else {
				e.printStackTrace();
			}	
		} finally {
			close();
		}// end of try~catch (SQLException e) ----------------------
			
		return result;
	}// end of public int update_resume(Map<String, String> paraMap)------------

	
	
	// == 내 이력서를 삭제해주는 메소드 == //
	@Override
	public int delete_resume(String resume_no) {
		
		int delete_resume = 0;
		
		try {
			
			String sql = " update tbl_resumes set resume_status = 0 "
					+ " where resume_no = ? and resume_status = 1 ";
			
			pstmt = conn.prepareStatement(sql);		// sql 문을 담음
			
			pstmt.setString(1, resume_no);
			
			delete_resume = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}// end of try~catch (SQLException e) ----------------------
		
		
		return delete_resume;
		
	}// end of public int delete_resume(String resume_no) -----------------


	
	
	
	
	
	
}
