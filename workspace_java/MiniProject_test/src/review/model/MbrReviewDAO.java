package review.model;

import java.util.List;
import java.util.Map;

import applys.domain.ApplysDTO;
import job_posts.domain.JobpostDTO;
import user.domain.CompanyDTO;
import user.domain.MemberDTO;
import resume.domain.ResumeDTO;
import review.domain.ReviewDTO;

public interface MbrReviewDAO {

	// 후기작성하기 
	int reviewWrite(MemberDTO member, int comNo, ReviewDTO review);

	// 지원한 회사목록보기
	List<JobpostDTO> viewBoardList(MemberDTO member);

	// 후기전체조회   
	List<ReviewDTO> AllreviewList(int com_no);

	// 내가작성한후기조회  
	List<ReviewDTO> MyreviewList(MemberDTO member);
	
	// 후기상세조회   
	ReviewDTO detailViewReview(Map<String, String> paraMap);

	// 리뷰수정학기   
	int updateReview(Map<String, String> paraMap2);

	// 회사번호찾기   
	int searchComNo(int com_no);

	// 순위높은 평점조회 
	List<String> rankList();

	// 글삭제하기   
	int deleteBoard(String deleteno);

	

	
	
	
	

	
	
}
