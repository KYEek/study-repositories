package search.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.ProjectDBConnection;

public class SearchDAO_imple implements SearchDAO {

	//filed
	private Connection conn = ProjectDBConnection.getConn();
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	//자원 회수 메서드
	private void close() {
		try {
			if (rs != null) {rs.close(); rs = null;}
			if (pstmt != null) {pstmt.close(); pstmt = null;}
		} catch (SQLException e) {e.printStackTrace();}
	}
	
	
	// 모든구직자조회(select)
	@Override
	public List<Map<String, String>> view_all_gujikja() {
		List<Map<String, String>> map_list = new ArrayList<>(); 
		
		try {
			String sql 	= " SELECT F.user_no, F.user_name, F.age, F.gender, F.user_email, E.edu_degree, E.major_name, E.res_career "
						+ "   FROM "
						+ " ( "
						+ "    SELECT * "
						+ "      FROM "
						+ "    ( "
						+ "        SELECT * "
						+ "          FROM "
						+ "        ( "
						+ "            SELECT ORG_R.* "
						+ "              FROM "
						+ "            ( "
						+ "                SELECT fk_user_no, max(resume_no) AS recent_resume "
						+ "                  FROM tbl_resumes "
						+ "                 WHERE resume_status = 1 "
						+ "                GROUP BY fk_user_no "
						+ "            ) MAX_R "
						+ "            JOIN  "
						+ "            ( "
						+ "                SELECT * "
						+ "                  FROM tbl_resumes "
						+ "            ) ORG_R "
						+ "                ON MAX_R.recent_resume = ORG_R.resume_no "
						+ "        ) A JOIN tbl_education B "
						+ "        ON A.fk_edu_code = B.edu_code "
						+ "    ) C JOIN tbl_major D "
						+ "    ON C.fk_major_code = D.major_code "
						+ " ) E JOIN  "
						+ " ( "
						+ "    SELECT user_no, user_name, func_age(user_jubun) AS age, func_gender(user_jubun) AS gender, user_email "
						+ "      FROM tbl_users WHERE user_status = 1 "
						+ " ) F "
						+ " ON E.fk_user_no = F.user_no ";
		
			pstmt = conn.prepareStatement(sql);		// 쿼리문 세팅
			rs = pstmt.executeQuery();				// 쿼리문 실행
			
			// 쿼리결과 세팅
			while (rs.next()) {
				Map<String, String> map = new HashMap<>();
				
				map.put("user_no", rs.getString("user_no"));
				map.put("user_name", rs.getString("user_name"));
				map.put("age", rs.getString("age"));
				map.put("gender", rs.getString("gender"));
				map.put("user_email", rs.getString("user_email"));
				map.put("edu_degree", rs.getString("edu_degree"));
				map.put("major_name", rs.getString("major_name"));
				map.put("res_career", rs.getString("res_career"));
				
				map_list.add(map);
			}// end of while (rs.next()) ----------------------
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return map_list;
	}// end of public List<Map<String, String>> view_all_gujikja() ---------------------------


	// 구직자 상세조회(select)
	@Override
	public Map<String, String> view_detail_gujikja(String select_userno) {
		Map<String, String> map = null;
		
		try {
			String sql 	= " SELECT F.user_no, E.res_title, F.user_name, F.age, F.gender, F.user_email, E.edu_degree, E.major_name, E.res_career, E.res_hwage, E.res_intro "
						+ "  FROM "
						+ " ( "
						+ "    SELECT * "
						+ "      FROM "
						+ "    ( "
						+ "        SELECT * "
						+ "          FROM "
						+ "        ( "
						+ "            SELECT ORG_R.* "
						+ "              FROM "
						+ "            ( "
						+ "                SELECT fk_user_no, max(resume_no) AS recent_resume "
						+ "                  FROM tbl_resumes "
						+ "                 WHERE resume_status = 1 "
						+ "                GROUP BY fk_user_no "
						+ "            ) MAX_R "
						+ "            JOIN  "
						+ "            ( "
						+ "                SELECT * "
						+ "                  FROM tbl_resumes "
						+ "            ) ORG_R "
						+ "                ON MAX_R.recent_resume = ORG_R.resume_no "
						+ "        ) A JOIN tbl_education B "
						+ "        ON A.fk_edu_code = B.edu_code "
						+ "    ) C JOIN tbl_major D "
						+ "    ON C.fk_major_code = D.major_code "
						+ " ) E JOIN  "
						+ " ( "
						+ "    SELECT user_no, user_name, func_age(user_jubun) AS age, func_gender(user_jubun) AS gender, user_email "
						+ "      FROM tbl_users "
						+ "		WHERE user_no = ? AND user_status = 1 "
						+ " ) F "
						+ " ON E.fk_user_no = F.user_no ";
		
			pstmt = conn.prepareStatement(sql);		// 쿼리문 세팅
			pstmt.setString(1, select_userno);
			rs = pstmt.executeQuery();				// 쿼리문 실행
			
			// 쿼리결과 세팅
			if (rs.next()) {
				map = new HashMap<>();
				
				map.put("user_no", rs.getString("user_no"));
				map.put("res_title", rs.getString("res_title"));
				map.put("user_name", rs.getString("user_name"));
				map.put("age", rs.getString("age"));
				map.put("gender", rs.getString("gender"));
				map.put("user_email", rs.getString("user_email"));
				map.put("edu_degree", rs.getString("edu_degree"));
				map.put("major_name", rs.getString("major_name"));
				map.put("res_career", rs.getString("res_career"));
				map.put("res_hwage", rs.getString("res_hwage"));
				map.put("res_intro", rs.getString("res_intro"));
				
			}// end of while (rs.next()) ----------------------
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return map;
	}// end of public Map<String, String> view_detail_gujikja() ---------------------------


	// 모든구인회사조회(select)
	@Override
	public List<Map<String, String>> view_all_company() {
		List<Map<String, String>> map_list = new ArrayList<>(); 
		
		try {
			String sql 	= " SELECT A.com_no, A.com_name, A.com_president, B.job_type "
						+ "  FROM tbl_companies A JOIN tbl_job_types B "
						+ "    ON A.fk_job_tcode = B.job_code "
						+ " WHERE com_status = 1 ";
		
			pstmt = conn.prepareStatement(sql);		// 쿼리문 세팅
			rs = pstmt.executeQuery();				// 쿼리문 실행
			
			// 쿼리결과 세팅
			while (rs.next()) {
				Map<String, String> map = new HashMap<>();
				
				map.put("com_no", rs.getString("com_no"));
				map.put("com_name", rs.getString("com_name"));
				map.put("com_president", rs.getString("com_president"));
				map.put("job_type", rs.getString("job_type"));
				
				map_list.add(map);
			}// end of while (rs.next()) ----------------------
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return map_list;
	}// end of public List<Map<String, String>> view_all_company()

	// 구인회사 상세조회(select)
	@Override
	public Map<String, String> view_detail_company(String select_comno) {
	Map<String, String> map = null;
		
		try {
			String sql 	= " SELECT A.com_no, A.com_name, A.com_president, A.com_intro, A.com_email, A.com_address, A.com_revenue , B.job_type "
						+ "  FROM tbl_companies A JOIN tbl_job_types B "
						+ "    ON A.fk_job_tcode = B.job_code "
						+ " WHERE com_no = ? AND com_status = 1 ";
			
			pstmt = conn.prepareStatement(sql);		// 쿼리문 세팅
			pstmt.setString(1, select_comno);
			rs = pstmt.executeQuery();				// 쿼리문 실행
			
			// 쿼리결과 세팅
			if (rs.next()) {
				map = new HashMap<>();
				
				map.put("com_no", rs.getString("com_no"));
				map.put("com_name", rs.getString("com_name"));
				map.put("com_president", rs.getString("com_president"));
				map.put("com_intro", rs.getString("com_intro"));
				map.put("com_email", rs.getString("com_email"));
				map.put("com_address", rs.getString("com_address"));
				map.put("com_revenue", rs.getString("com_revenue"));
				map.put("job_type", rs.getString("job_type"));
				
			}// end of if (rs.next()) ----------------------
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return map;
	}// end of public Map<String, String> view_detail_company(String select_comno) --------------------


	
	// == 학력검색을 해주는 메소드 == //
	@Override
	public List<Map<String, String>> degree_search(String edu_code) {
		
		// 이름 나이 성별 이메일 학력 전공 경력
		
		List<Map<String, String>> map_list = new ArrayList<>(); 
		
		try {
			String sql 	= " SELECT F.user_no, E.res_title, F.user_name, F.age, F.gender, F.user_email, E.edu_degree, E.major_name, E.res_career, E.res_hwage, E.res_intro "
					+ " FROM "
					+ " ( "
					+ " SELECT * "
					+ " FROM "
					+ " ( "
					+ " SELECT * "
					+ " FROM "
					+ " ( "
					+ " SELECT ORG_R.* "
					+ " FROM "
					+ " ( "
					+ " SELECT fk_user_no, max(resume_no) AS recent_resume "
					+ " FROM tbl_resumes "
					+ " WHERE resume_status = 1 "
					+ " GROUP BY fk_user_no "
					+ " ) MAX_R "
					+ " JOIN  "
					+ " ( "
					+ " SELECT * "
					+ " FROM tbl_resumes WHERE resume_status = 1 and fk_edu_code >= ? "
					+ " ) ORG_R "
					+ " ON MAX_R.recent_resume = ORG_R.resume_no "
					+ " ) A JOIN tbl_education B "
					+ " ON A.fk_edu_code = B.edu_code "
					+ " ) C JOIN tbl_major D "
					+ " ON C.fk_major_code = D.major_code "
					+ " ) E JOIN  "
					+ " ( "
					+ " SELECT user_no, user_name, func_age(user_jubun) AS age, func_gender(user_jubun) AS gender, user_email "
					+ " FROM tbl_users "
					+ " WHERE user_status = 1 "
					+ " ) F "
					+ " ON E.fk_user_no = F.user_no ";
		
			pstmt = conn.prepareStatement(sql);		// 쿼리문 세팅
			pstmt.setString(1, edu_code);
			rs = pstmt.executeQuery();				// 쿼리문 실행
			
			// 쿼리결과 세팅
			while (rs.next()) {
				Map<String, String> map = new HashMap<>();
				// 이름 나이 성별 이메일 학력 전공 경력
				map.put("user_name", rs.getString("user_name"));
				map.put("age", rs.getString("age"));
				map.put("gender", rs.getString("gender"));
				map.put("user_email", rs.getString("user_email"));
				map.put("edu_degree", rs.getString("edu_degree"));
				map.put("major_name", rs.getString("major_name"));
				map.put("res_career", rs.getString("res_career"));
				
				map_list.add(map);
			}// end of while (rs.next()) ----------------------
			
		} catch (SQLException e) { 
			if(e.getErrorCode() == 1722) {
				System.out.println(">> [경고] 숫자만 입력 가능합니다!! <<");
			}
			else {
				e.printStackTrace();
			}	
		} finally {
			close();
		}
		
		
		return map_list;
	}

	
	
	// == 구직자 전공을 검색해주는 메소드 == //
	@Override
	public List<Map<String, String>> major_search(String major_code) {
		
		// 이름 나이 성별 이메일 학력 전공 경력
		
		List<Map<String, String>> map_list = new ArrayList<>(); 
		
		try {
			String sql 	= " SELECT F.user_no, E.res_title, F.user_name, F.age, F.gender, F.user_email, E.edu_degree, E.major_name, E.res_career, E.res_hwage, E.res_intro "
					+ " FROM "
					+ " ( "
					+ " SELECT * "
					+ " FROM "
					+ " ( "
					+ " SELECT * "
					+ " FROM "
					+ " ( "
					+ " SELECT ORG_R.* "
					+ " FROM "
					+ " ( "
					+ " SELECT fk_user_no, max(resume_no) AS recent_resume "
					+ " FROM tbl_resumes "
					+ " WHERE resume_status = 1 "
					+ " GROUP BY fk_user_no "
					+ " ) MAX_R "
					+ " JOIN  "
					+ " ( "
					+ " SELECT * "
					+ " FROM tbl_resumes WHERE resume_status = 1 and fk_major_code = ? "
					+ " ) ORG_R "
					+ " ON MAX_R.recent_resume = ORG_R.resume_no "
					+ " ) A JOIN tbl_education B "
					+ " ON A.fk_edu_code = B.edu_code "
					+ " ) C JOIN tbl_major D "
					+ " ON C.fk_major_code = D.major_code "
					+ " ) E JOIN  "
					+ " ( "
					+ " SELECT user_no, user_name, func_age(user_jubun) AS age, func_gender(user_jubun) AS gender, user_email "
					+ " FROM tbl_users "
					+ " WHERE user_status = 1 "
					+ " ) F "
					+ " ON E.fk_user_no = F.user_no ";
		
			pstmt = conn.prepareStatement(sql);		// 쿼리문 세팅
			pstmt.setString(1, major_code);
			rs = pstmt.executeQuery();				// 쿼리문 실행
			
			// 쿼리결과 세팅
			while (rs.next()) {
				Map<String, String> map = new HashMap<>();
				// 이름 나이 성별 이메일 학력 전공 경력
				map.put("user_name", rs.getString("user_name"));
				map.put("age", rs.getString("age"));
				map.put("gender", rs.getString("gender"));
				map.put("user_email", rs.getString("user_email"));
				map.put("edu_degree", rs.getString("edu_degree"));
				map.put("major_name", rs.getString("major_name"));
				map.put("res_career", rs.getString("res_career"));
				
				map_list.add(map);
			}// end of while (rs.next()) ----------------------
			
		} catch (SQLException e) {
			if(e.getErrorCode() == 1722) {
				System.out.println(">> [경고] 숫자만 입력 가능합니다!! <<");
			}
			else {
				e.printStackTrace();
			}	
		} finally {
			close();
		}
		
		
		return map_list;
		
	}// end of public List<Map<String, String>> major_search(String major_code)------------

	
	
	// == 구직자 연령대 검색을 해주는 메소드 == //
	@Override
	public List<Map<String, String>> age_line_search(String age_line) {
		
		// 이름 나이 성별 이메일 학력 전공 경력
		
		List<Map<String, String>> map_list = new ArrayList<>(); 
		
		try {
			String sql 	= " SELECT F.user_no, E.res_title, F.user_name, F.age, F.gender, F.user_email, E.edu_degree, E.major_name, E.res_career, E.res_hwage, E.res_intro "
					+ " FROM "
					+ " ( "
					+ " SELECT * "
					+ " FROM "
					+ " ( "
					+ " SELECT * "
					+ " FROM "
					+ " ( "
					+ " SELECT ORG_R.* "
					+ " FROM "
					+ " ( "
					+ " SELECT fk_user_no, max(resume_no) AS recent_resume "
					+ " FROM tbl_resumes "
					+ " WHERE resume_status = 1 "
					+ " GROUP BY fk_user_no "
					+ " ) MAX_R "
					+ " JOIN  "
					+ " ( "
					+ " SELECT * "
					+ " FROM tbl_resumes WHERE resume_status = 1  "
					+ " ) ORG_R "
					+ " ON MAX_R.recent_resume = ORG_R.resume_no "
					+ " ) A JOIN tbl_education B "
					+ " ON A.fk_edu_code = B.edu_code "
					+ " ) C JOIN tbl_major D "
					+ " ON C.fk_major_code = D.major_code "
					+ " ) E JOIN  "
					+ " ( "
					+ " SELECT user_no, user_name, func_age(user_jubun) AS age, trunc(func_age(user_jubun), -1) AS age_line, func_gender(user_jubun) AS gender, user_email "
					+ " FROM tbl_users "
					+ " WHERE user_status = 1 "
					+ " ) F "
					+ " ON E.fk_user_no = F.user_no"
					+ " WHERE age_line = ? ";
		
			pstmt = conn.prepareStatement(sql);		// 쿼리문 세팅
			pstmt.setString(1, age_line);
			rs = pstmt.executeQuery();				// 쿼리문 실행
			
			// 쿼리결과 세팅
			while (rs.next()) {
				Map<String, String> map = new HashMap<>();
				// 이름 나이 성별 이메일 학력 전공 경력
				map.put("user_name", rs.getString("user_name"));
				map.put("age", rs.getString("age"));
				map.put("gender", rs.getString("gender"));
				map.put("user_email", rs.getString("user_email"));
				map.put("edu_degree", rs.getString("edu_degree"));
				map.put("major_name", rs.getString("major_name"));
				map.put("res_career", rs.getString("res_career"));
				
				map_list.add(map);
			}// end of while (rs.next()) ----------------------
			
		} catch (SQLException e) {
			if(e.getErrorCode() == 1722) {
				System.out.println(">> [경고] 숫자만 입력 가능합니다!! <<");
			}
			else {
				e.printStackTrace();
			}	
		} finally {
			close();
		}
		
		return map_list;
		
	}// end of public List<Map<String, String>> age_line_search(String age_line)


	
	
	// == 성별검색을 해주는 메소드 == //
	@Override
	public List<Map<String, String>> gender_search(String gender) {
		
		// 이름 나이 성별 이메일 학력 전공 경력
		
		List<Map<String, String>> map_list = new ArrayList<>(); 
		
		try {
			String sql 	= " SELECT F.user_no, E.res_title, F.user_name, F.age, F.gender, F.user_email, E.edu_degree, E.major_name, E.res_career, E.res_hwage, E.res_intro "
					+ " FROM "
					+ " ( "
					+ " SELECT * "
					+ " FROM "
					+ " ( "
					+ " SELECT * "
					+ " FROM "
					+ " ( "
					+ " SELECT ORG_R.* "
					+ " FROM "
					+ " ( "
					+ " SELECT fk_user_no, max(resume_no) AS recent_resume "
					+ " FROM tbl_resumes "
					+ " WHERE resume_status = 1 "
					+ " GROUP BY fk_user_no "
					+ " ) MAX_R "
					+ " JOIN  "
					+ " ( "
					+ " SELECT * "
					+ " FROM tbl_resumes WHERE resume_status = 1  "
					+ " ) ORG_R "
					+ " ON MAX_R.recent_resume = ORG_R.resume_no "
					+ " ) A JOIN tbl_education B "
					+ " ON A.fk_edu_code = B.edu_code "
					+ " ) C JOIN tbl_major D "
					+ " ON C.fk_major_code = D.major_code "
					+ " ) E JOIN  "
					+ " ( "
					+ " SELECT user_no, user_name, func_age(user_jubun) AS age, trunc(func_age(user_jubun), -1) AS age_line, func_gender(user_jubun) AS gender, user_email "
					+ " FROM tbl_users "
					+ " WHERE user_status = 1 "
					+ " ) F "
					+ " ON E.fk_user_no = F.user_no"
					+ " WHERE gender = ? ";
		
			pstmt = conn.prepareStatement(sql);		// 쿼리문 세팅
			pstmt.setString(1, gender);
			rs = pstmt.executeQuery();				// 쿼리문 실행
			
			// 쿼리결과 세팅
			while (rs.next()) {
				Map<String, String> map = new HashMap<>();
				// 이름 나이 성별 이메일 학력 전공 경력
				map.put("user_name", rs.getString("user_name"));
				map.put("age", rs.getString("age"));
				map.put("gender", rs.getString("gender"));
				map.put("user_email", rs.getString("user_email"));
				map.put("edu_degree", rs.getString("edu_degree"));
				map.put("major_name", rs.getString("major_name"));
				map.put("res_career", rs.getString("res_career"));
				
				map_list.add(map);
			}// end of while (rs.next()) ----------------------
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return map_list;
		
	}// public List<Map<String, String>> gender_search(String gender_no) ------------

	
	
	
}
