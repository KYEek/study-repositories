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
import com.spring.app.common.FileManager;

// ==== #23. 서비스 선언 ====
// 트랜잭션 처리를 담당하는 곳, 업무를 처리하는 곳, 비지니스(Business)단
@Service
public class BoardService_imple implements BoardService {

	@Autowired   // Type 에 따라 알아서 Bean 을 주입해준다.
	private BoardDAO dao;
	
	
	// === #169. 첨부파일 및 사진이미지 파일을 삭제하기 위한 것 === // 
	@Autowired   // Type 에 따라 알아서 Bean 을 주입해준다.
	private FileManager fileManager;

	
	// === #29. 파일첨부가 없는 글쓰기 ===
	@Override
	public int add(BoardVO boardvo) {
		
		// === #137. 글쓰기가 원글쓰기인지 아니면 답변글쓰기인지를 구분하여 
 		//           tbl_board 테이블에 insert 를 해주어야 한다.
 		//           원글쓰기 이라면 tbl_board 테이블의 groupno 컬럼의 값은 
 		//           groupno 컬럼의 최대값(max)+1 로 해서 insert 해야하고,
 		//           답변글쓰기 이라면 넘겨받은 값(boardvo)을 그대로 insert 해주어야 한다. 
		
		// 원글쓰기인지, 답변글쓰기인지 구분하기 시작 === //
		if("".equals(boardvo.getFk_seq())) {
			// 원글쓰기인 경우
			// groupno 컬럼의 값은 groupno 컬럼의 최대값(max)+1 로 해야 한다.
			int groupno = dao.getGroupnoMax()+1;
			boardvo.setGroupno(String.valueOf(groupno));
		}
		// === 원글쓰기인지, 답변글쓰기인지 구분하기 끝 === //
		
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


	// === #53. 1개글 삭제하기 (첨부파일 및 사진이미지가 없는 경우의 글삭제) === //
/*	
	@Override
	public int del(String seq) {
		int n = dao.del(seq);
		return n;
	}
*/
	
	// === #164. 1개글 삭제할 때 먼저 사진이미지파일명 및 첨부파일명을 알아오기 위한 것 === //
	@Override
	public Map<String, String> getView_delete(String seq) {
		Map<String, String> boardmap = dao.getView_delete(seq);
		return boardmap;
	}
	
	// === #168. 첨부파일 및 사진이미지가 있는 경우의 글삭제 === // 
	//      먼저, 위의 #53 을 주석처리 한 이후에 아래처럼 한다.
	@Override
	public int del(Map<String, String> paraMap) {
		
		int n = dao.del(paraMap.get("seq")); // 테이블에서 행삭제하기
		
		// === 첨부파일 및 사진이미지 파일 삭제하기 시작 === // 
		if(n==1) {
			String filepath = paraMap.get("filepath");  // 삭제해야할 첨부파일이 저장된 경로
			String filename = paraMap.get("filename");  // 삭제해야할 첨부파일명 
			
			if(filename != null && !"".equals(filename)) {
				try {
					fileManager.doFileDelete(filename, filepath);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			//////////////////////////////////////////////////////
			
			// 글내용에 사진이미지가 들어가 있는 경우라면 사진이미지 파일도 삭제해야 한다.
			String photofilename = paraMap.get("photofilename");
			
			if(photofilename != null) {
				
				String photo_upload_path = paraMap.get("photo_upload_path");
				
				if(photofilename.contains("/")) {
					// 사진이미지가 2개 이상 존재하는 경우
					
				   String[] arr_photofilename =	photofilename.split("[/]");
				   
				   for(int i=0; i<arr_photofilename.length; i++) {
						try {
							fileManager.doFileDelete(arr_photofilename[i], photo_upload_path); 
						} catch (Exception e) {
							e.printStackTrace();
						}   
				   }// end of for----------------
					
				}
				else {
					// 사진이미지가 1개만 존재하는 경우
					try {
						fileManager.doFileDelete(photofilename, photo_upload_path); 
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
			
		}
		// === 첨부파일 및 사진이미지 파일 삭제하기 끝 === // 
		
		return n;
	}


	// === #60. 댓글쓰기(Transaction 처리) === // 
 	// tbl_comment 테이블에 insert 된 다음에 
 	// tbl_board 테이블에 commentCount 컬럼이 1증가(update) 하도록 요청한다.
 	// 이어서 회원의 포인트를 50점을 증가하도록 한다.
 	// 즉, 2개이상의 DML 처리를 해야하므로 Transaction 처리를 해야 한다. (여기서는 3개의 DML 처리가 일어남)
 	// >>>>> 트랜잭션처리를 해야할 메소드에 @Transactional 어노테이션을 설정하면 된다. 
 	// rollbackFor={Throwable.class} 은 롤백을 해야할 범위를 말하는데 Throwable.class 은 error 및 exception 을 포함한 최상위 루트이다. 즉, 해당 메소드 실행시 발생하는 모든 error 및 exception 에 대해서 롤백을 하겠다는 말이다.
	@Override
	@Transactional(value="transactionManager_mymvc_user", propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor= {Throwable.class})
	public int addComment(CommentVO commentvo) {
		
		int n1=0, n2=0, result=0;
		
		n1 = dao.addComment(commentvo); // 댓글쓰기(tbl_comment 테이블에 insert)
	 // System.out.println("~~~~ 확인용 n1 : " + n1);
		
		if(n1 == 1) {
			n2 = dao.updateCommentCount(commentvo.getParentSeq());  // tbl_board 테이블에 commentCount 컬럼이 1증가(update)
		//	System.out.println("~~~~ 확인용 n2 : " + n2);
		}
		
		if(n2 == 1) {
			Map<String, String> paraMap = new HashMap<>();
			paraMap.put("userid", commentvo.getFk_userid());
			paraMap.put("point", "50");
			
			result = dao.updateMemberPoint(paraMap);  // tbl_member 테이블의 point 컬럼의 값을 50점을 증가(update)
		//	System.out.println("~~~~ 확인용 result : " + result);
		}
		
		return result;
	}


	// === #64. 원게시물에 딸린 댓글들을 조회해오기 === //
	@Override
	public List<CommentVO> getCommentList(String parentSeq) {
		List<CommentVO> commentList = dao.getCommentList(parentSeq);
		return commentList;
	}


	// === #69. 댓글 수정(Ajax 로 처리) === //
	@Override
	public int updateComment(Map<String, String> paraMap) {
		int n = dao.updateComment(paraMap);
		return n;
	}


	// === #73. 댓글 삭제(Ajax 로 처리) === //
	@Override
	@Transactional(value="transactionManager_mymvc_user", propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor= {Throwable.class}) 
	public int deleteComment(Map<String, String> paraMap) {
		
		int n = dao.deleteComment(paraMap.get("seq"));
		
		int m = 0 ;
		
		if(n==1) {
			// 댓글삭제시 tbl_board 테이블에 commentCount 컬럼이 1감소(update)
		   m = dao.updateCommentCount_decrease(paraMap.get("parentSeq"));
		// System.out.println("~~~~ 확인용 m : " + m);
		   // ~~~~ 확인용 m : 1
		}
		
		return n*m;
	}


	// === #79. CommonAop 클래스에서 사용하는 것으로 특정 회원에게 특정 점수만큼 포인트를 증가하기 위한 것 === // 
	@Override
	public void pointPlus(Map<String, String> paraMap) {
		dao.pointPlus(paraMap);
	}


	// === #84. 페이징 처리를 안한 검색어가 있는 전체 글목록 보여주기 === //
	@Override
	public List<BoardVO> boardListSearch(Map<String, String> paraMap) {
		List<BoardVO> boardList = dao.boardListSearch(paraMap);
		return boardList;
	}


	// === #90. 검색어 입력시 자동글 완성하기 4 === //
	@Override
	public List<String> wordSearchShow(Map<String, String> paraMap) {
		List<String> wordList = dao.wordSearchShow(paraMap);
		return wordList;
	}


	// === #96. 총 게시물 건수(totalCount) 구하기 --> 검색이 있을 때와 검색이 없을때 로 나뉜다. === //
	@Override
	public int getTotalCount(Map<String, String> paraMap) {
		int totalCount = dao.getTotalCount(paraMap);
		return totalCount;
	}


	// === #99. 글목록 가져오기(페이징 처리 했으며, 검색어가 있는 것 또는 검색어가 없는 것 모두 포함한 것이다.) === // 
	@Override
	public List<BoardVO> boardListSearch_withPaging(Map<String, String> paraMap) {
		List<BoardVO> boardList = dao.boardListSearch_withPaging(paraMap);
		return boardList;
	}


	// === #119. 원게시물에 딸린 댓글내용들을 페이징 처리하기 ===
	@Override
	public List<CommentVO> getCommentList_Paging(Map<String, String> paraMap) {
		List<CommentVO> commentList = dao.getCommentList_Paging(paraMap); 
		return commentList;
	}


	// === #122. 페이징 처리시 보여주는 순번을 나타내기 위한 것 ===
	@Override
	public int getCommentTotalCount(String parentSeq) {
		int totalCount = dao.getCommentTotalCount(parentSeq);
		return totalCount;
	}
	
	
	// === #153. 파일첨부가 있는 글쓰기 ===
	@Override
	public int add_withFile(BoardVO boardvo) {
		
		// 글쓰기가 원글쓰기인지 아니면 답변글쓰기인지를 구분하여 
 		// tbl_board 테이블에 insert 를 해주어야 한다.
 		// 원글쓰기 이라면 tbl_board 테이블의 groupno 컬럼의 값은 
 		// groupno 컬럼의 최대값(max)+1 로 해서 insert 해야하고,
 		// 답변글쓰기 이라면 넘겨받은 값(boardvo)을 그대로 insert 해주어야 한다. 
		
		// 원글쓰기인지, 답변글쓰기인지 구분하기 시작 === //
		if("".equals(boardvo.getFk_seq())) {
			// 원글쓰기인 경우
			// groupno 컬럼의 값은 groupno 컬럼의 최대값(max)+1 로 해야 한다.
			int groupno = dao.getGroupnoMax()+1;
			boardvo.setGroupno(String.valueOf(groupno));
		}
		// === 원글쓰기인지, 답변글쓰기인지 구분하기 끝 === //
		
		int n = dao.add_withFile(boardvo);  // 첨부파일이 있는 경우
		return n;
	}
	
	
}





