package search.model;

import java.util.List;
import java.util.Map;

public interface SearchDAO {

	// 모든구직자조회(select)
	List<Map<String, String>> view_all_gujikja();
	
	// 구직자 상세조회(select)
	Map<String, String> view_detail_gujikja(String select_userno);

	// 모든구인회사조회(select)
	List<Map<String, String>> view_all_company();

	// 구인회사 상세조회(select)
	Map<String, String> view_detail_company(String select_comno);
	
	// 구직자 학력검색(select)
	List<Map<String, String>> degree_search(String edu_code);
	
	// 구직자 전공검색(select)
	List<Map<String, String>> major_search(String major_code);
	
	// 구직자 연령대검색(select)
	List<Map<String, String>> age_line_search(String age_line);
	
	// 구직자 성별검색(select)
	List<Map<String, String>> gender_search(String gender);
	
	
	
}
