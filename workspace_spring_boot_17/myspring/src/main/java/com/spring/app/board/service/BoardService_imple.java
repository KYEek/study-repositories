package com.spring.app.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.app.board.domain.BoardVO;
import com.spring.app.board.domain.CommentVO;
import com.spring.app.board.model.BoardDAO;

// ==== #23. 서비스 선언 ====
//트랜잭션 처리를 담당하는 곳, 업무를 처리하는 곳, 비지니스(Business)단
@Service
public class BoardService_imple implements BoardService {

	@Autowired
	private BoardDAO dao;

	
	// === #29. 파일첨부가 없는 글쓰기 ===
	@Override
	public int add(BoardVO boardvo) {
		
		int n = dao.add(boardvo); 
		return n;
	}


	// === #33. 페이징 처리를 안한 검색어가 없는 전체 글목록 보여주기 ===
	@Override
	public List<BoardVO> boardListNoSearch() {
		List<BoardVO> boardList = dao.boardListNoSearch();
		return boardList;
	}


	// === #37. 글 조회수 증가와 함께 글 1개를 조회를 해오는 것 ===
	@Override
	public BoardVO getView(Map<String, String> paraMap) {
		
		BoardVO boardvo = dao.getView(paraMap); // 글 1개 조회하기
		
		String login_userid = paraMap.get("login_userid");
		// paraMap.get("login_userid") 은 로그인을 한 상태이라면 로그인한 사용자의 userid 이고,
		// 로그인을 하지 않은 상태이라면  paraMap.get("login_userid") 은 null 이다.
		
		if(login_userid != null &&
				boardvo != null &&
		  !login_userid.equals(boardvo.getFk_userid() )) {
		  // 글조회수 증가는 로그인을 한 상태에서 다른 사람의 글을 읽을때만 증가하도록 한다.
			
		  int n = dao.increase_readCount(paraMap.get("seq")); // 글조회수 1증가 하기 
		
		  if(n==1) {
			  boardvo.setReadCount( String.valueOf(Integer.parseInt(boardvo.getReadCount()) + 1) ); 
		  }
		}
		
		return boardvo;
	}


	// === #45. 글 조회수 증가는 없고 단순히 글 1개만 조회를 해오는 것 ===
	@Override
	public BoardVO getView_no_increase_readCount(Map<String, String> paraMap) {
		BoardVO boardvo = dao.getView(paraMap); // 글 1개 조회하기
		return boardvo;
	}


	// === #48. 1개글 수정하기 === //
	@Override
	public int edit(BoardVO boardvo) {
		int n = dao.edit(boardvo);
		return n;
	}


	// === #53. 1개글 삭제하기 === //
	@Override
	public int del(String seq) {
		int n = dao.del(seq);
		return n;
	}

	// 댓글쓰기(Transaction 처리) === // 
    // tbl_comment 테이블에 insert 된 다음에 
    // tbl_board 테이블에 commentCount 컬럼이 1증가(update) 하도록 요청한다.
    // 이어서 회원의 포인트를 50점을 증가하도록 한다.
    // 즉, 2개이상의 DML 처리를 해야하므로 Transaction 처리를 해야 한다. (여기서는 3개의 DML 처리가 일어남)
    // >>>>> 트랜잭션처리를 해야할 메소드에 @Transactional 어노테이션을 설정하면 된다. 
    // rollbackFor={Throwable.class} 은 롤백을 해야할 범위를 말하는데 Throwable.class 은 error 및 exception 을 포함한 최상위 루트이다. 즉, 해당 메소드 실행시 발생하는 모든 error 및 exception 에 대해서 롤백을 하겠다는 말이다. //
	
	@Override
	@Transactional(value="transactionManager_mymvc_user", propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor= {Throwable.class})
	public int addComment(CommentVO commentvo) {

		int n1=0, n2 = 0, result = 0;
		
		n1 = dao.addComment(commentvo); // 댓글쓰기(tbl_comment 테이블에 insert)
		System.out.println("~~~~~확인용 n1 : " + n1);
		
		if(n1 == 1) {
			n2 = dao.updateCommentCount(commentvo.getParentSeq()); // tbl_board 테이블에 commentCount 컬럼이 1증가(update)
			System.out.println("~~~~~확인용 n2 : " + n2);
		}
		
		if(n2 == 1) {
			Map<String, String> paraMap = new HashMap<>();
			paraMap.put("userid", commentvo.getFk_userid());
			paraMap.put("point", "50");
			result = dao.updateMemberPoint(paraMap);// tbl_member 테이블의 point 컬럼의 값을 50점을 증가(update)
		}
		return result;
	}

	//원 게시물에 딸린 댓글들을 조횧해오기
	@Override
	public List<CommentVO> getCommentList(String parentSeq) {
		List<CommentVO> commentList = dao.getCommentList(parentSeq);
		return commentList;
	}


	// 댓글 수정(Ajax로 처리)
	@Override
	public int updateComment(Map<String, String> paraMap) {
		
		int n = dao.updateComment(paraMap.get("seq"));
		
		return n;
	}


	@Override
	@Transactional(value="transactionManager_mymvc_user", propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor= {Throwable.class})
	public int deleteComment(Map<String, String> paraMap) {
		int n = dao.deleteComment(paraMap);
		
		int m = 0;
		
		if(n==1) {
			// 댓글삭제시 tbl_board 테이블에 commentCount 컬럼이 1감소(update)
			m = dao.updateCommentCount_decrease(paraMap.get("parentSeq"));
			//System.out.println("~~~~확인용 m : " + m);
		}
		return m;
	}
	
}





