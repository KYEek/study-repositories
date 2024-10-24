package resume.model;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import main.user.domain.MemberDTO;
import resume.domain.ResumeDTO;

public interface ResumeDAO {
	
	// == 이력서를 등록하는 메소드 == //
	int register_resume(ResumeDTO resume);

	
	// == 이력서를 '전체' 조회해주는 메소드 == //
	List<ResumeDTO> view_all_resume(MemberDTO member);
	
	
	// == 내 이력서를 '상세' 조회해주는 메소드 == //
	ResumeDTO view_detail_resume(MemberDTO member, String resume_no);

	
	// == 내 이력서를 수정해주는 메소드 == //
	int update_resume(Map<String, String> paraMap, String resume_no);

	
	// == 내 이력서를 삭제해주는 메소드 == //
	int delete_resume(String resume_no);
	
	
	
	
	
	
}
