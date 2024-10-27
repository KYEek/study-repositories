package review.model;

import java.util.List;
import java.util.Map;


import job_posts.domain.JobpostDTO;
import user.domain.MemberDTO;
import review.domain.ReviewDTO;

public interface MbrReviewDAO {

	// 후기작성하기 
	int reviewWrite(Map<String, String> paraMap, MemberDTO member , int comno);

	// 지원한 회사목록보기
	List<JobpostDTO> viewBoardList(MemberDTO member);

	// 후기전체조회   
	List<ReviewDTO> AllreviewList(String com_no);

	// 내가작성한후기조회  
	List<ReviewDTO> MyreviewList(MemberDTO member);
	
	// 후기상세조회 - 조회수증가   
	ReviewDTO detailViewReview(Map<String, String> paraMap);
	
	// 후기상세조회 - 조회수증가 안됨 
	ReviewDTO ViewReview(Map<String, String> paraMap);

	// 리뷰수정학기   
	int updateReview(Map<String, String> paraMap2);

	// 회사번호찾기   
	int searchComNo(String com_no);
	
	// 회사존재유무
	boolean compareNo(String comno);
	
	// 공고번호찾기
	int searchPostno(MemberDTO member, String com_no);

	// 순위높은 평점조회 
	List<String> descRankList();
	
	// 순위낮은 평점조회 
	List<String> ascRankList();

	// 글삭제하기   
	int deleteBoard(String deleteno);

	// 단순 회사 목록들 보여주기 
	List<String> viewCompanyList();

	// 회사별평점평균
	List<String> avgCom();
	
	// 평점조건검색 
	List<String> hopeList(String reviewno);

	
	


	

	
	
	
	

	
	
}
