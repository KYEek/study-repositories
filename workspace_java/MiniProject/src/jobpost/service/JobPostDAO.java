package jobpost.service;

import java.util.List;

import user.domain.CompanyDTO;
import user.domain.MemberDTO;
import jobpost.member.domain.*;


public interface JobPostDAO {
  
    
    // 본인이 같은 곳에 공고 지원한 곳인지 확인하는 메소드
    boolean isAlreadyApplied(int jobPostNo, int resumeNo);
    

    // 모든 공고를 조회하는 메소드
    List<JobPostDTO> getAllJobPosts();  

    // 특정 공고의 상세 정보를 조회하는 메소드
    JobPostDTO getJobPostDetail(int jobPostNo);

    // 조건에 따라 공고를 검색하는 메소드
    List<JobPostDTO> searchJobPosts(String companyNo, String companyName, String salary, String jobPeriod);
  /*  
    // 입력 검증 메서드 추가
    boolean isValidJobPostNo(String jobPostNo);
    boolean isValidCompanyName(String companyName);
    boolean isValidWage(String wage);
    boolean isValidJobType(String jobType);
   */
    
    // 모든 이력서를 조회하는 메소드
    List<JobPostDTO> showAllResumeList(MemberDTO member);
    
    // 공고 지원 메뉴
    boolean applyjobpostmenu(int jobPostno, int resumeNo);
  
    // 사용자가 지원한 공고를 조회하는 메소드
    List<JobPostDTO> ViewAppliedJobPosts(int userNo);
    
    // 공고 지원 취소 메소드
	boolean canclejobpostmenu(int int1, int user_no);

	
	// 공고 등록 메소드
	boolean insertjobpost(JobPostDTO jp, CompanyDTO company);
	
	// 기업 공고 조회 메소드
	public List<JobPostDTO> getJobPostsByCompany(int companyId);

	// 기업 공고 수정 메소드
	boolean updatejobpost(JobPostDTO jp, int jobPostNo);

	// 기업 공고 삭제 메소드
	boolean deletejobpostmenu(int int1, int com_no);
	
	

	 	
	
	
	
   
	
}
