package admin.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.ProjectDBConnection;
import admin.domain.EventDTO;

public class AdminDAO_Imple implements AdminDAO {

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
	
	// 관리자 로그인 
	@Override
	public boolean login(Map<String, String> login) {
		
		//관리자는 관리자 정보를 받아올 필요가 없고 로그인 성공과 실패만 필요해서 DTO를 만들지 않았다
		boolean result = false;
		
		String sql = " select admin_id, admin_passwd, admin_name from TBL_admin "
				+ "where admin_ID = ? and admin_passwd = ? ";
		
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, login.get("userid"));
			pstmt.setString(2, login.get("passwd"));
			rs = pstmt.executeQuery();
			
			// 있는지 검사
			if (rs.next()) {
				result = true;	//로그인 성공
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}// end of public boolean login(Map<String, String> login) --------------------


	// 월간공고작성통계(select)
	@Override
	public Map<String, Integer> monthly_post_statistics() {
		
		Map<String, Integer> map = null;
					
		try {
			String sql 	= " SELECT COUNT(*) AS total "
						+ "		 , SUM(CASE WHEN TO_DATE(TO_CHAR(ADD_MONTHS(SYSDATE, -3), 'yyyymm'), 'yyyymm') - TO_DATE(TO_CHAR(write_date, 'yyyymm'),'yyyymm') = 0 THEN 1 ELSE 0 END) AS before_3months "
						+ "		 , SUM(CASE WHEN TO_DATE(TO_CHAR(ADD_MONTHS(SYSDATE, -2), 'yyyymm'), 'yyyymm') - TO_DATE(TO_CHAR(write_date, 'yyyymm'),'yyyymm') = 0 THEN 1 ELSE 0 END) AS before_2months "
						+ "		 , SUM(CASE WHEN TO_DATE(TO_CHAR(ADD_MONTHS(SYSDATE, -1), 'yyyymm'), 'yyyymm') - TO_DATE(TO_CHAR(write_date, 'yyyymm'),'yyyymm') = 0 THEN 1 ELSE 0 END) AS before_1months "
						+ "		 , SUM(CASE WHEN TO_DATE(TO_CHAR(sysdate, 'yyyymm'), 'yyyymm') - TO_DATE(TO_CHAR(write_date, 'yyyymm'),'yyyymm') = 0 THEN 1 ELSE 0 END) AS this_month "
						+ "   FROM tbl_job_posts "
						+ "  WHERE TO_CHAR(write_date, 'yyyy-mm') BETWEEN TO_CHAR((ADD_MONTHS(write_date, -3)), 'yyyy-mm') AND TO_CHAR(SYSDATE, 'yyyy-mm') ";
		
			pstmt = conn.prepareStatement(sql);		// 쿼리문 세팅
			rs = pstmt.executeQuery();				// 쿼리 실행
			
			// 쿼리결과 세팅
			if (rs.next()) {
				map = new HashMap<>();
				
				map.put("total", rs.getInt("total"));
				map.put("before_3months", rs.getInt("before_3months"));
				map.put("before_2months", rs.getInt("before_2months"));
				map.put("before_1months", rs.getInt("before_1months"));
				map.put("this_month", rs.getInt("this_month"));
				
			}// end of while (rs.next()) ----------------------
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return map;
	}// end of public Map<String, Integer> monthly_post_statistics() ----------------------

	
	// 공고조회수랭킹(select)
	@Override
	public List<Map<String, String>> post_ranking() {
		List<Map<String, String>> mapList = new ArrayList<>();
		
		try {
			String sql 	= " SELECT job_postno "
						+ "		 , CASE WHEN LENGTH(post_title) > 10 THEN SUBSTR(post_title, 1,7) || '...' ELSE post_title END AS post_title "
						+ "		 , CASE WHEN LENGTH(post_contents) > 10 THEN SUBSTR(post_contents, 1,7) || '...' ELSE post_contents END AS post_contents "
						+ "		 , TO_CHAR(end_date, 'yyyy-mm-dd') AS end_date, view_count, rank() over(order by view_count desc) AS rank "
						+ "   FROM tbl_job_posts "
						+ "  WHERE TO_CHAR(end_date, 'yyyymmdd') - TO_CHAR(sysdate, 'yyyymmdd') > 0 ";
		
			pstmt = conn.prepareStatement(sql);		// 쿼리문 세팅
			rs = pstmt.executeQuery();				// 쿼리문 실행
			
			// 쿼리결과 세팅
			while (rs.next()) {
				Map<String, String> map = new HashMap<>();
				
				map.put("job_postno", rs.getString("job_postno"));
				map.put("post_title", rs.getString("post_title"));
				map.put("post_contents", rs.getString("post_contents"));
				map.put("end_date", rs.getString("end_date"));
				map.put("view_count", rs.getString("view_count"));
				map.put("rank", rs.getString("rank"));
				
				mapList.add(map);
			}// end of while (rs.next()) ----------------------
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return mapList;
	}// end of public List<Map<String, String>> post_ranking() ----------------------------

	
	// 지원자수랭킹(select)
	@Override
	public List<Map<String, String>> apply_ranking() {
		List<Map<String, String>> mapList = new ArrayList<>();
		
		try {
			String sql 	= " SELECT C.fk_job_postno, C.post_title, C.post_contents, TO_CHAR(C.end_date, 'yyyy-mm-dd') AS end_date, D.apply_count, rank() over(order by D.apply_count desc) AS rank "
						+ "	  FROM ( "
						+ "		 SELECT A.fK_job_postno, B.post_title, B.post_contents, B.end_date "
						+ "		   FROM tbl_applys A JOIN tbl_job_posts B "
						+ "			 ON A.fk_job_postno = B.job_postno "
						+ "	) C JOIN ( "
						+ "		 SELECT fk_job_postno, COUNT(*) AS apply_count "
						+ "		   FROM tbl_applys "
						+ "		 GROUP BY fk_job_postno ) D "
						+ "     ON C.fk_job_postno = D.fk_job_postno "
						+ "  UNION "
						+ " SELECT C.fk_job_postno, C.post_title, C.post_contents, TO_CHAR(C.end_date, 'yyyy-mm-dd') AS end_date, D.apply_count, rank() over(order by D.apply_count desc) AS rank "
						+ "	  FROM ( "
						+ "		 SELECT A.fK_job_postno, B.post_title, B.post_contents, B.end_date "
						+ "		   FROM tbl_applys A JOIN tbl_job_posts B "
						+ "			 ON A.fk_job_postno = B.job_postno "
						+ "	) C JOIN ( "
						+ "		 SELECT fk_job_postno, COUNT(*) AS apply_count "
						+ "		   FROM tbl_applys "
						+ "		 GROUP BY fk_job_postno ) D "
						+ "     ON C.fk_job_postno = D.fk_job_postno ";
		
			pstmt = conn.prepareStatement(sql);		// 쿼리문 세팅
			rs = pstmt.executeQuery();				// 쿼리문 실행	
			// 쿼리결과 세팅
			while (rs.next()) {
				Map<String, String> map = new HashMap<>();
				
				map.put("fk_job_postno", rs.getString("fk_job_postno"));
				map.put("post_title", rs.getString("post_title"));
				map.put("post_contents", rs.getString("post_contents"));
				map.put("end_date", rs.getString("end_date"));
				map.put("apply_count", rs.getString("apply_count"));
				map.put("rank", rs.getString("rank"));
				
				mapList.add(map);
			}// end of while (rs.next()) ----------------------
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return mapList;
	}// end of public List<Map<String, String>> apply_ranking() ------------------------

	
	// 월간회원가입인원통계(select)
	@Override
	public Map<String, Integer> monthly_sign_up_statistics() {
		Map<String, Integer> map = null;
		
		try {
			String sql 	= " SELECT COUNT(*) AS total "
						+ "		 , SUM(CASE WHEN TO_DATE(TO_CHAR(ADD_MONTHS(SYSDATE, -3), 'yyyymm'), 'yyyymm') - TO_DATE(TO_CHAR(user_regi, 'yyyymm'),'yyyymm') = 0 THEN 1 ELSE 0 END) AS before_3months "
						+ "		 , SUM(CASE WHEN TO_DATE(TO_CHAR(ADD_MONTHS(SYSDATE, -2), 'yyyymm'), 'yyyymm') - TO_DATE(TO_CHAR(user_regi, 'yyyymm'),'yyyymm') = 0 THEN 1 ELSE 0 END) AS before_2months "
						+ "		 , SUM(CASE WHEN TO_DATE(TO_CHAR(ADD_MONTHS(SYSDATE, -1), 'yyyymm'), 'yyyymm') - TO_DATE(TO_CHAR(user_regi, 'yyyymm'),'yyyymm') = 0 THEN 1 ELSE 0 END) AS before_1months "
						+ "		 , SUM(CASE WHEN TO_DATE(TO_CHAR(sysdate, 'yyyymm'), 'yyyymm') - TO_DATE(TO_CHAR(user_regi, 'yyyymm'),'yyyymm') = 0 THEN 1 ELSE 0 END) AS this_month "
						+ "   FROM tbl_users "
						+ "  WHERE TO_CHAR(user_regi, 'yyyy-mm') BETWEEN TO_CHAR((ADD_MONTHS(user_regi, -3)), 'yyyy-mm') AND TO_CHAR(SYSDATE, 'yyyy-mm') ";
		
			pstmt = conn.prepareStatement(sql);		// 쿼리문 세팅
			rs = pstmt.executeQuery();				// 쿼리 실행
			
			// 쿼리결과 세팅
			if (rs.next()) {
				map = new HashMap<>();
				
				map.put("total", rs.getInt("total"));
				map.put("before_3months", rs.getInt("before_3months"));
				map.put("before_2months", rs.getInt("before_2months"));
				map.put("before_1months", rs.getInt("before_1months"));
				map.put("this_month", rs.getInt("this_month"));
				
			}// end of while (rs.next()) ----------------------
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return map;
	}// end of public Map<String, Integer> monthly_sign_up_statistics() ------------------------------------

	
	// 월간이력서작성건수통계(select)
	@Override
	public Map<String, Integer> monthly_resumes_statistics() {
		Map<String, Integer> map = null;
		
		try {
			String sql 	= " SELECT COUNT(*) AS total "
						+ "		 , SUM(CASE WHEN TO_DATE(TO_CHAR(ADD_MONTHS(SYSDATE, -3), 'yyyymm'), 'yyyymm') - TO_DATE(TO_CHAR(res_regiday, 'yyyymm'),'yyyymm') = 0 THEN 1 ELSE 0 END) AS before_3months "
						+ "		 , SUM(CASE WHEN TO_DATE(TO_CHAR(ADD_MONTHS(SYSDATE, -2), 'yyyymm'), 'yyyymm') - TO_DATE(TO_CHAR(res_regiday, 'yyyymm'),'yyyymm') = 0 THEN 1 ELSE 0 END) AS before_2months "
						+ "		 , SUM(CASE WHEN TO_DATE(TO_CHAR(ADD_MONTHS(SYSDATE, -1), 'yyyymm'), 'yyyymm') - TO_DATE(TO_CHAR(res_regiday, 'yyyymm'),'yyyymm') = 0 THEN 1 ELSE 0 END) AS before_1months "
						+ "		 , SUM(CASE WHEN TO_DATE(TO_CHAR(sysdate, 'yyyymm'), 'yyyymm') - TO_DATE(TO_CHAR(res_regiday, 'yyyymm'),'yyyymm') = 0 THEN 1 ELSE 0 END) AS this_month "
						+ "   FROM tbl_resumes "
						+ "  WHERE TO_CHAR(res_regiday, 'yyyy-mm') BETWEEN TO_CHAR((ADD_MONTHS(res_regiday, -3)), 'yyyy-mm') AND TO_CHAR(SYSDATE, 'yyyy-mm') ";
		
			pstmt = conn.prepareStatement(sql);		// 쿼리문 세팅
			rs = pstmt.executeQuery();				// 쿼리 실행
			
			// 쿼리결과 세팅
			if (rs.next()) {
				map = new HashMap<>();
				
				map.put("total", rs.getInt("total"));
				map.put("before_3months", rs.getInt("before_3months"));
				map.put("before_2months", rs.getInt("before_2months"));
				map.put("before_1months", rs.getInt("before_1months"));
				map.put("this_month", rs.getInt("this_month"));
				
			}// end of while (rs.next()) ----------------------
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return map;
	}// end of public Map<String, Integer> monthly_resumes_statistics() ------------------------------

	
	// 이벤트등록(insert)
	@Override
	public int register_event(EventDTO edto) {
		int result = 0;
		
		try {
			String sql 	= " INSERT INTO tbl_events (event_no, event_name, event_start, event_end, event_contents) "
						+ "	VALUES (event_seq.nextval, ?, ?, ?, ?) ";
		
			pstmt = conn.prepareStatement(sql);		// 쿼리문 세팅
			pstmt.setString(1, edto.getEvent_name());
			pstmt.setString(2, edto.getEvent_start());
			pstmt.setString(3, edto.getEvent_end());
			pstmt.setString(4, edto.getEvent_contents());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return result;
	}// end of public int register_event(EventDTO edto) -----------------------------
	
	
	// 이벤트상세보기(select)
	@Override
	public EventDTO view_event_detail(String update_no) {
		EventDTO edto = null;
		
		try {
			String sql 	= " SELECT event_no, event_name, TO_CHAR(event_start, 'yyyy-mm-dd') AS event_start, TO_CHAR(event_end, 'yyyy-mm-dd') AS event_end, event_contents "
						+ "	  FROM tbl_events "
						+ "  WHERE event_no = ? ";
		
			pstmt = conn.prepareStatement(sql);		// 쿼리문 세팅
			pstmt.setString(1, update_no);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				edto = new EventDTO();
				
				edto.setEvent_name(rs.getString("event_name"));
				edto.setEvent_start(rs.getString("event_start"));
				edto.setEvent_end(rs.getString("event_end"));
				edto.setEvent_contents(rs.getString("event_contents"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return edto;
	}// end of public EventDTO view_event_detail(String update_no) ---------------------------

	// 이벤트수정(update)
	@Override
	public int update_event(EventDTO edto, String update_no) {
		int result = 0;
		
		try {
			String sql 	= " UPDATE tbl_events "
						+ " SET event_name = ?, event_start = TO_DATE(?), event_end = TO_DATE(?), event_contents = ? "
						+ "	WHERE event_no = ? ";
		
			pstmt = conn.prepareStatement(sql);		// 쿼리문 세팅
			pstmt.setString(1, edto.getEvent_name());
			pstmt.setString(2, edto.getEvent_start());
			pstmt.setString(3, edto.getEvent_end());
			pstmt.setString(4, edto.getEvent_contents());
			pstmt.setString(5, update_no);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			if (e.getErrorCode() == 1407) {
				System.out.println(">> 데이터 미입력으로 정보 변경 없음. <<");
			}
			else { e.printStackTrace(); }
		} finally {
			close();
		}
		
		return result;
	}// end of public int update_event(EventDTO edto, String update_no) --------------------------------
		
}
