package review.model;

import java.util.List;
import java.util.Map;

import comment.domain.CommentDTO;
import user.domain.CompanyDTO;
import review.domain.ReviewDTO;

public interface CompanyReviewDAO {

	// 우리회사후기목록보기  
	List<String> companyreviewList(CompanyDTO company);

	// 후기상세조회 
	ReviewDTO detailViewReview(String review_no);

	// 우리회사리뷰인지판단 
	boolean compareNo(String review_no, CompanyDTO company);

	


	
}
