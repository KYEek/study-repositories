package game.model;

import common.ProjectDBConnection;
import user.domain.CompanyDTO;
import user.domain.MemberDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class GameDAO_imple implements GameDAO{

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

    // 미니게임결과에 따라 포인트지급(update)
    @Override
    public int game_point(MemberDTO member, CompanyDTO company, String eventNo, int point) {

        int result = 0;

        try {
            ///////////////////////////////////////////////
            // == 포인트 지급 시작 == //
            conn.setAutoCommit(false);      // 수동 커밋

            String sql;
            if (member != null) {           // 일반회원이 플레이시
                sql     = " UPDATE tbl_users SET user_point = user_point + ? "
                        + "	WHERE user_no = ? ";

                pstmt = conn.prepareStatement(sql);        // 쿼리문 세팅
                pstmt.setInt(1, point);
                pstmt.setInt(2, member.getUser_no());
//                System.out.println("포인트확인: " + point);
//                System.out.println("회원번호확인: " + member.getUser_no());


            }
            else {                          // 기업회원이 플레이시
                sql     = " UPDATE tbl_companies SET com_point = com_point + ? "
                        + "	WHERE com_no = ? ";

                pstmt = conn.prepareStatement(sql);        // 쿼리문 세팅
                pstmt.setInt(1, point);
                pstmt.setInt(2, company.getCom_no());

            }
            int i = pstmt.executeUpdate();
            // == 포인트 지급 끝 == //
            ///////////////////////////////////////////////
            // == 게임정보 저장 시작 == //
            if (i == 1){
                if (member != null) {       // 일반회원이 플레이시
                    sql     = " INSERT INTO tbl_event_play (event_pno, user_no, fk_event_no) "
                            + "	VALUES (event_play_seq.nextval, ?, ?) ";

                    pstmt = conn.prepareStatement(sql);        // 쿼리문 세팅
                    pstmt.setInt(1, member.getUser_no());
                    pstmt.setString(2, eventNo);


                }
                else {                      // 기업회원이 플레이시
                    sql     = " INSERT INTO tbl_event_play (event_pno, com_no, fk_event_no) "
                            + "	VALUES (event_play_seq.nextval, ?, ?) ";

                    pstmt = conn.prepareStatement(sql);        // 쿼리문 세팅
                    pstmt.setInt(1, company.getCom_no());
                    pstmt.setString(2, eventNo);
                }

                int n = pstmt.executeUpdate();

                if (n == 1){
                    conn.commit();
                    result = 1;
                }

            }// end of if (
            // == 게임정보 저장 끝 == //
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        } finally {
            try {
                conn.setAutoCommit(true);	// Auto Commit 으로 복원시킨다.
            } catch (SQLException e2) {	e2.printStackTrace(); }
            close();
        }

        return result;

    }// end of public void rock_scissors_paper(MemberDTO member) -------------------------

    // 게임 정보를 가져옴(select)
    @Override
    public Map<String, String> game_info(int i) {
        Map<String, String> map = null;

        try {
            String sql 	= " SELECT * FROM tbl_events "
                        + "  WHERE event_no = ? AND event_status = 1";

            pstmt = conn.prepareStatement(sql);		// 쿼리문 세팅
            pstmt.setInt(1, i);

            rs = pstmt.executeQuery();				// 쿼리 실행

            // 쿼리결과 세팅
            if (rs.next()) {
                map = new HashMap<>();

                map.put("event_no", rs.getString("event_no"));
                map.put("event_name", rs.getString("event_name"));
                map.put("event_contents", rs.getString("event_contents"));

            }// end of while (rs.next()) ----------------------

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }


        return map;
    }// end of public Map<String, String> game_info()

    // 랜덤한 문제와 답을 가져옴 (select)
    @Override
    public Map<String, String> get_quiz(int quizNo) {
        Map<String, String> map = null;

        try {
            String sql 	= " SELECT * FROM tbl_four_words "
                        + "  WHERE word_no = ? ";

            pstmt = conn.prepareStatement(sql);		// 쿼리문 세팅
            pstmt.setInt(1, quizNo);

            rs = pstmt.executeQuery();				// 쿼리 실행

            // 쿼리결과 세팅
            if (rs.next()) {
                map = new HashMap<>();

                map.put("word_no", rs.getString("word_no"));
                map.put("word_desc", rs.getString("word_desc"));
                map.put("word_answer", rs.getString("word_answer"));

            }// end of while (rs.next()) ----------------------

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return map;
    }// end of public Map<String, String> get_quiz(int quizNo) ---------------------------

    // 문제 개수를 가져옴 (select)
    @Override
    public int get_quiz_count() {
        int result = 0;

        try {
            String sql 	= " SELECT COUNT(*) AS count FROM tbl_four_words ";

            pstmt = conn.prepareStatement(sql);		// 쿼리문 세팅
            rs = pstmt.executeQuery();				// 쿼리 실행

            // 쿼리결과 세팅
            if (rs.next()) {

                result = rs.getInt("count");

            }// end of while (rs.next()) ----------------------

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return result;
    }// end of public int get_quiz_count() -----------------------------
}
