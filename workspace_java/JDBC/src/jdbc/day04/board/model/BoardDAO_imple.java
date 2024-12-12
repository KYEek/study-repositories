package jdbc.day04.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jdbc.day04.board.dbconnection.MyDBConnection;
import jdbc.day04.board.domain.BoardDTO;
import jdbc.day04.member.domain.CommentDTO;
import jdbc.day04.member.domain.MemberDTO;

public class BoardDAO_imple implements BoardDAO {
	
	
	
	
	// field, attribute, property
	private Connection conn = MyDBConnection.getConn();
	private PreparedStatement pstmt;
	private ResultSet rs;

	
	
	// method, operation, 기능
	
	// 자원반납을 해주는 메소드 //
	private void close() {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (pstmt != null) {
				pstmt.close();
				pstmt = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	//		게시판에 글을 쓰는 메소드		//
	// === Transaction 처리 ===
	//    (tbl_board 테이블에 insert 가 성공되어지면 tbl_member 테이블의 point 컬럼에 10씩 증가 update 를 할 것이다.
	//     그런데 insert 또는 update 가 하나라도 실패하면 모두 rollback 할 것이고,
	//     insert 와 update 가 모두 성공해야만 commit 할 것이다.)
	
	// 게시판 글쓰기 Transaction 처리하여 성공되어지면 1을 리턴시켜 줄 것이고,
	// 장애(오류)가 발생되어 실패하면 -1 리턴시켜 줄 것이다
	@Override
	public int write(BoardDTO bdto) {
		
		int result = 0;

		try {

			// Transaction 처리를 위해서 수동 commit 으로 전환 시킨다.
			conn.setAutoCommit(false);
			
			String sql = " insert into tbl_board(boardno, fk_userid, subject, contents, boardpasswd) "
					+ " values(seq_board.nextval, ?, ?, ? ,?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bdto.getFk_userid());
			pstmt.setString(2, bdto.getSubject());
			pstmt.setString(3, bdto.getContents());
			pstmt.setString(4, bdto.getBoardpasswd());

			int n1 = pstmt.executeUpdate();

			if( n1 == 1) {	// tbl_board 테이블에 insert가 성공되었다라면
				sql = " update tbl_member set point = point + 10 "
					+ " where userid = ? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, bdto.getFk_userid());
				
				int n2 = pstmt.executeUpdate();
				
				if(n2 == 1) {
					conn.commit();
					result = 1;
				}
				
			}
			
		}catch (SQLException e) {
			if(e.getErrorCode()==2290) {
				System.out.println(">> 아이디 " +bdto.getFk_userid()+ "님의 포인트는 30을 초과할 수 없기 때문에 오류발생 하였습니다. <<");
			}
			else {
				e.printStackTrace();
			}
			try {
				conn.rollback();
				result = -1;
			}
			catch (SQLException e1) {	}
			
		} finally {

			try {
				conn.setAutoCommit(true);
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			close();
			
		}

		return result;
	}//end of write ---------------------------

	//			글 목록 보기			//
	@Override
	public List<BoardDTO> boardList() {
		
		List<BoardDTO> boardList = new ArrayList<>();

		try {
			
			String sql = " select boardno, case when cmtcnt is null then subject else subject|| ' [' ||cmtcnt || ']' end subject "
						+ "            , name, writeday, viewcount "
						+ "    from "
						+ "    (select boardno "
						+ "    , case when length(subject) > 15 then substr(subject, 1, 13)||'..' else subject end subject "
						+ "    , name, to_char(writeday, 'yyyy-mm-dd hh24:mi:ss') writeday, viewcount "
						+ "    from tbl_board B join tbl_member M "
						+ "    on B.fk_userid = M.userid) V1 "
						+ "    left join "
						+ "    (select fk_boardno, count(*) cmtcnt "
						+ "    from tbl_comment "
						+ "    group by fk_boardno) V2 "
						+ "    on V1.boardno = V2.fk_boardno "
						+ "    order by boardno desc ";
			

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			//int cnt = 0;
			while (rs.next()) {
				
				BoardDTO board = new BoardDTO();
				
				board.setBoardno(rs.getInt("boardno"));
				board.setSubject(rs.getString("subject"));
				MemberDTO member = new MemberDTO();
				
				member.setName(rs.getString("name"));
				board.setMbrdto(member);
				
				board.setWriteday(rs.getString("writeday"));
				board.setViewcount(rs.getInt("viewcount"));
				
				boardList.add(board);
			}

		}  catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return boardList;
	}


	// 글 1개 내용 보기
	// == 현재 로그인 사용자가 자신이 쓴 글을 볼때는 조회수 증가가 없지만
	//    다른 사용자가 쓴 글을 볼때는 조회수를 1증가 해주어야 한다.
	@Override
	public BoardDTO viewContents(Map<String, String> paraMap) {
		
		BoardDTO bdto = null;
		
		try {
			
			String sql = " select subject, contents, name, viewcount, fk_userid "
					+ " from  "
					+ " ( "
					+ "     select subject, contents, viewcount, fk_userid "
					+ "     from  tbl_board "
					+ "     where boardno = ? "
					+ " ) B join tbl_member M "
					+ " on B.fk_userid = M.userid ";
			

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMap.get("boardNo"));
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bdto = new BoardDTO();
				
				bdto.setSubject(rs.getString("subject"));
				bdto.setContents(rs.getString("contents"));
				
				MemberDTO mbrdto = new MemberDTO();
				mbrdto.setName(rs.getString("name"));
				bdto.setMbrdto(mbrdto);
				bdto.setViewcount(rs.getInt("viewcount"));
				
				//————————————————————————————————
				
				
				//로그인한 사용자가 다른 사용자가 쓴 글을 조회할 경우에만 글 조회수 1증가 시켜야 한다.
				if(!(paraMap.get("login_userid").equals(rs.getString("fk_userid")))) {
					sql = " update tbl_board set viewcount = viewcount + 1 "
							+ " where boardno = ? ";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, paraMap.get("boardNo"));
					
					pstmt.executeUpdate();
					
					bdto.setViewcount(bdto.getViewcount() + 1);
					
				}//end of if————————————————————————————————
				
			}

		}  catch (SQLException e) {
			if(e.getErrorCode()==1722) {
				System.out.println(">> [경고] 글번호는 정수만 가능합니다. << \n");
			}
			else {
				e.printStackTrace();
			}
			
			
		} finally {
			close();
		}
		
		
		
		return bdto;
	}// end of viewContents————————————————————————————————————————


	
	
	//			댓글 쓰기			//
	// === Transaction 처리 ===
	//    (tbl_comment 테이블에 insert 가 성공되어지면 tbl_member 테이블의 point 컬럼에 10씩 증가 update 를 할 것이다.
	//     그런데 insert 또는 update 가 하나라도 실패하면 모두 rollback 할 것이고,
	//     insert 와 update 가 모두 성공해야만 commit 할 것이다.)
	
	// 댓글쓰기 Transaction 처리하여 성공되어지면 1을 리턴시켜 줄 것이고,
	// 장애(오류)가 발생되어 실패하면 -1 리턴시켜 줄 것이다
	@Override
	public int writeComment(CommentDTO cmtdto) {

		int result = 0;

		try {
			// Transaction 처리를 위해서 수동 commit 으로 전환 시킨다.
			conn.setAutoCommit(false);
			
			String sql = " insert into tbl_comment(commentno, fk_boardno, fk_userid, contents) "
					+ " values(seq_comment.nextval, ?, ?, ?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cmtdto.getFk_boardno());
			pstmt.setString(2, cmtdto.getFk_userid());
			pstmt.setString(3, cmtdto.getContents());

			int n1 = pstmt.executeUpdate();

			if( n1 == 1) {	// tbl_board 테이블에 insert가 성공되었다라면
				sql = " update tbl_member set point = point + 5 "
					+ " where userid = ? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, cmtdto.getFk_userid());
				
				int n2 = pstmt.executeUpdate();
				
				if(n2 == 1) {
					conn.commit();
					result = 1;
				}
				
			}
			
		} catch (SQLException e) {
			if(e.getErrorCode()==2290) {
				System.out.println(">> 아이디 " +cmtdto.getFk_userid()+ "님의 포인트는 30을 초과할 수 없기 때문에 오류발생 하였습니다. <<");
				try {
					conn.rollback();
					result = -1;
				} catch (SQLException e1) {	}
			}
			else if(e.getErrorCode() == 2291) {
				/*
	              오류 보고 -
	              ORA-02291: 무결성 제약조건(JDBC_USER.FK_TBL_COMMENT_FK_BOARDNO)이 위배되었습니다- 부모 키가 없습니다
	            */
				System.out.println(">> 입력하신 원글번호 " +cmtdto.getFk_boardno()+ "은(는) 게시글에 존재하지 않습니다 <<\n");
				result = -1;
			}
			else {
				e.printStackTrace();
			}
			
		} finally {

			try {
				conn.setAutoCommit(true);
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			close();
			
		}

		return result;	
		
	}

	//원글에 대한 댓글을 가져오는 것(특정 게시글 글번호에 대한 tbl_comment 테이블과 tbl_member 테이블을 JOIN 해서 보여준다.)
	@Override
	public List<CommentDTO> commentList(String boardno) {
		List<CommentDTO> commentList = new ArrayList<>();

		try {

			String sql = " select C.contents, M.name, to_char(C.writeday, 'yyyy-mm-dd hh24:mi:ss') writeday "
					+ "    from "
					+ "    ( "
					+ "    select * "
					+ "    from tbl_comment  "
					+ "    where fk_boardno = ? "
					+ "    ) C join tbl_member M "
					+ "    on C.fk_userid = M.userid "
					+ "    order by commentno desc ";
			

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardno);
			rs = pstmt.executeQuery();
			//int cnt = 0;
			while (rs.next()) {
				
				CommentDTO cmtdto = new CommentDTO();
				
				cmtdto.setContents(rs.getString("contents"));
				MemberDTO member = new MemberDTO();
				
				member.setName(rs.getString("name"));
				cmtdto.setMember(member);
				
				cmtdto.setWriteday(rs.getString("writeday"));
				
				commentList.add(cmtdto);
			}

		}  catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return commentList;
	}
			
	// 조회수 증가는 없고 단순히 글 내용만 보여주기
	@Override
	public BoardDTO viewContents(String boardno) {
		
		BoardDTO bdto = null;
		
		try {

			String sql = " select subject, contents, fk_userid, BOARDPASSWD "
					+ " from tbl_board "
					+ " where boardno = ? ";
			

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardno);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bdto = new BoardDTO();
				
				bdto.setSubject(rs.getString("subject"));
				bdto.setContents(rs.getString("contents"));
				bdto.setFk_userid(rs.getString("fk_userid"));
				bdto.setBoardpasswd(rs.getString("BOARDPASSWD"));	//글암호
				
				//————————————————————————————————
				
				
			}

		} catch (SQLException e) {
			if(e.getErrorCode()==1722) {
				System.out.println(">> [경고] 글번호는 정수만 가능합니다. << \n");
			}
			else {
				e.printStackTrace();
			}
			
			
		} finally {
			close();
		}
		
		
		
		return bdto;
	}// end of viewContents————————————————————————————————————————

	
	//			글 수정 메소드				//
	@Override
	public int updateBoard(Map<String, String> paraMap) {
		int result = 0;

		try {

			// Transaction 처리를 위해서 수동 commit 으로 전환 시킨다.
			conn.setAutoCommit(false);
			
			String sql = " update tbl_board set subject = ?, contents = ? "
						+ " where boardno = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMap.get("subject"));
			pstmt.setString(2, paraMap.get("contents"));
			pstmt.setString(3, paraMap.get("boardno"));

			result = pstmt.executeUpdate();

			
		} catch (SQLException e) {
			if(e.getErrorCode() == 1722) {
				System.out.println("글 번호는 정수로만 입력하세요!!😡😡😡 \n");
			}
			else {
				e.printStackTrace();
			}
		} finally {

			try {
				conn.setAutoCommit(true);
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			close();
			
		}

		return result;	
	}


	@Override
	public int deleteBoard(String boardno) {
		int result = 0;

		try {

			// Transaction 처리를 위해서 수동 commit 으로 전환 시킨다.
			conn.setAutoCommit(false);
			
			String sql = " delete from tbl_board "
						+ " where boardno = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardno);

			result = pstmt.executeUpdate();

			
		}  catch (SQLException e) {
			if(e.getErrorCode() == 1722) {
				System.out.println("글 번호는 정수로만 입력하세요!!😡😡😡 \n");
			}
			else {
				e.printStackTrace();
			}
		} finally {

			try {
				conn.setAutoCommit(true);
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			close();
			
		}

		return result;	
	}// end of deleteBoard ------------------------------

	
	
	
	
	
	// 최근 1주일내에 작성된 게시글만 DB에서 가져온 결과물
	@Override
	public Map<String, Integer> statics_by_week() {
		
		Map<String, Integer> resultMap = new HashMap<>();
		
		
		
		try {

			String sql = " select count(*) as total "
					+ "            ,sum(decode(to_date(to_char(sysdate,'yyyymmdd'),'yyyymmdd') - to_date(to_char(writeday,'yyyymmdd'),'yyyymmdd'), 6, 1,0)) as previous6 "
					+ "            ,sum(decode(to_date(to_char(sysdate,'yyyymmdd'),'yyyymmdd') - to_date(to_char(writeday,'yyyymmdd'),'yyyymmdd'), 5, 1,0)) as previous5 "
					+ "            ,sum(decode(to_date(to_char(sysdate,'yyyymmdd'),'yyyymmdd') - to_date(to_char(writeday,'yyyymmdd'),'yyyymmdd'), 4, 1,0)) as previous4 "
					+ "            ,sum(decode(to_date(to_char(sysdate,'yyyymmdd'),'yyyymmdd') - to_date(to_char(writeday,'yyyymmdd'),'yyyymmdd'), 3, 1,0)) as previous3 "
					+ "            ,sum(decode(to_date(to_char(sysdate,'yyyymmdd'),'yyyymmdd') - to_date(to_char(writeday,'yyyymmdd'),'yyyymmdd'), 2, 1,0)) as previous2 "
					+ "            ,sum(decode(to_date(to_char(sysdate,'yyyymmdd'),'yyyymmdd') - to_date(to_char(writeday,'yyyymmdd'),'yyyymmdd'), 1, 1,0)) as previous1 "
					+ "            ,sum(decode(to_date(to_char(sysdate,'yyyymmdd'),'yyyymmdd') - to_date(to_char(writeday,'yyyymmdd'),'yyyymmdd'), 0, 1,0)) as today "
					+ "    from tbl_board "
					+ "    where to_date(to_char(sysdate,'yyyymmdd'),'yyyymmdd') - to_date(to_char(writeday,'yyyymmdd'),'yyyymmdd') < 7 ";
			

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			rs.next();	//결과가 0이든 뭐든 나오기 때문에 if절 필요없어요
			
			resultMap.put("total", rs.getInt("total"));
			resultMap.put("previous6", rs.getInt("previous6"));
			resultMap.put("previous5", rs.getInt("previous5"));
			resultMap.put("previous4", rs.getInt("previous4"));
			resultMap.put("previous3", rs.getInt("previous3"));
			resultMap.put("previous2", rs.getInt("previous2"));
			resultMap.put("previous1", rs.getInt("previous1"));
			resultMap.put("today", rs.getInt("today"));
			//맵에 select해온 결과들을 저장해요
				

		}  catch (SQLException e) {
				e.printStackTrace();
		} finally {
			close();
		}
		
		return resultMap;		//맵을 반환해줘요
	}//end of public Map<String, Integer> statics_by_week()-----------------------------


	
	
	// 		이번달 일자별 게시글 작성건수		//
	@Override
	public List<Map<String, String>> statics_by_currentMonth() {

		List<Map<String, String>> mapList = new ArrayList<>();

		try {

			String sql = " select decode(grouping(to_char(writeday, 'yyyy-mm-dd')), 0, to_char(writeday, 'yyyy-mm-dd'), '전체') as writeday "
					+ "            , count(*) as cnt "
					+ "    from tbl_board "
					+ "    where to_char(writeday, 'yyyymm') = to_char(sysdate, 'yyyymm') "
					+ "    group by rollup(to_char(writeday, 'yyyy-mm-dd')) ";
			

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()) {	//결과가 없거나 복수개 일 수 있으니깐 while이에요
				Map<String, String> map = new HashMap<> (); //list에 넣을 Map을 만들어 줘요 테이블의 한 행을 만들어 준다고 생각하면 돼요
				
				map.put("writeday", rs.getString("writeday"));
				//map.put("cnt", String.valueOf(rs.getInt("cnt")));	//rs.getInt가 int 타입이여서 valueOf로 형변환 해줘요
				//또는
				map.put("cnt",rs.getString("cnt"));	//그냥 getString으로 String 타입으로 불러와도 상관없어요
				
				mapList.add(map);	//list에 map을 담아줘요
				
			}// end of while----------------------------
			
			if(mapList.size() > 0) {		//리스트에 값이 있는지 없는지를 비교해요
				StringBuilder sb = new StringBuilder();
				sb.append("-".repeat(25)+"\n");
				sb.append("작성일자 \t  작성건수 \n");
				sb.append("-".repeat(25)+"\n");
				
				for(Map<String, String> map : mapList) {
					sb.append(map.get("writeday") + "\t  " + map.get("cnt") +"\n");
				}//end of for--------------------------------
				
				System.out.println(sb.toString());
				
			}
			else {
				System.out.println("게시된 글이 없습니다");
			}
				

		} catch (SQLException e) {
				e.printStackTrace();
		} finally {
			close();
		}
		
		return mapList;		//리스트를 반환해줘요
		
	}// end of statics_by_currentMonth--------------------------
	
			
			
			
			
			
			
			
			
			
}
