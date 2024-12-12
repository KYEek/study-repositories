package comment.model;

import java.util.List;
import java.util.Map;

import comment.domain.CommentDTO;
import review.domain.ReviewDTO;
import user.domain.CompanyDTO;

public interface CommentDAO {

	// 후기에 답변작성하기 (기업만 가능하다)
	int writeComment(Map<String, String> paraMap);

	// 단순 답변조회 
	List<CommentDTO> commentList(String review_no);

	// 답변이 있는지 조회
	boolean existComment(int fk_review_no);

	// 내가작성했었던 답변목록들 
	List<String> MycommentList(CompanyDTO company);

	// 수정답변 보여주기 
	CommentDTO ViewComment(String review_no);

	// 수정하기 
	int updateComment(Map<String, String> paraMap);

	// 내가 답변을 작성했었는지 
	boolean existMyComment(String com_no);

	// 답변삭제하기 
	int deleteComment(String review_no);



}
