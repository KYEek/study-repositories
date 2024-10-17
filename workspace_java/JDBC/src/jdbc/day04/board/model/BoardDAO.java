package jdbc.day04.board.model;

import java.util.List;

import jdbc.day04.board.domain.BoardDTO;

public interface BoardDAO {
	
	// 게시판 글쓰기
	int write(BoardDTO bdto);
	// === Transaction 처리 ===
	//    (tbl_board 테이블에 insert 가 성공되어지면 tbl_member 테이블의 point 컬럼에 10씩 증가 update 를 할 것이다.
	//     그런데 insert 또는 update 가 하나라도 실패하면 모두 rollback 할 것이고,
	//     insert 와 update 가 모두 성공해야만 commit 할 것이다.)

	// 글 목록 보기
	List<BoardDTO> boardList();

}
