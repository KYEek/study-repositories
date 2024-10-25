package service;

import java.util.List;
import member.domain.JobPostDTO;
import member.domain.ResumeDTO;

public interface JobPostDAO {
    void addJobPost(JobPostDTO jobPost);
  
    
    List<JobPostDTO> getAllJobPosts(); 

  
    JobPostDTO getJobPostDetail(int jobPostNo);



	List<JobPostDTO> searchJobPosts(String companyNo, String companyName, String salary, String jobPeriod);


    List<JobPostDTO> getAppliedJobPosts(int userNo);


    // 모든 이력서 조회 메소드
	List<JobPostDTO> showAllResumeList();

	

}
