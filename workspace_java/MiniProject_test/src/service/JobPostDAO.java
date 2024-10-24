package service;

import java.util.List;
import user.domain.JobPostDTO;

public interface JobPostDAO {
    void addJobPost(JobPostDTO jobPost);
  
    
    List<JobPostDTO> getAllJobPosts(); 

  
    JobPostDTO getJobPostDetail(int jobPostNo);



	List<JobPostDTO> searchJobPosts(String companyNo, String companyName, String salary, String jobPeriod);


    List<JobPostDTO> getAppliedJobPosts(int userNo);





	

}
