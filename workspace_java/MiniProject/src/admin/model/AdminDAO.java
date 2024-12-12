package admin.model;

import java.util.List;
import java.util.Map;

import admin.domain.EventDTO;

public interface AdminDAO {
	
	//관리자 로그인 
	boolean login(Map<String, String> login);

	// 월간공고작성통계(select)
	Map<String, Integer> monthly_post_statistics();

	// 공고조회수랭킹(select)
	List<Map<String, String>> post_ranking();

	// 지원자수랭킹(select)
	List<Map<String, String>> apply_ranking();

	// 월간회원가입인원통계(select)
	Map<String, Integer> monthly_sign_up_statistics();

	// 월간이력서작성건수통계(select)
	Map<String, Integer> monthly_resumes_statistics();
	
	// 이벤트등록(insert)
	int register_event(EventDTO edto);
	
	// 이벤트상세보기(select)
	EventDTO view_event_detail(String update_no);

	// 이벤트수정(update)
	int update_event(EventDTO edto, String update_no);

	// 이벤트삭제(update)
	int delete_event(String delete_no);
	
	// 진행중이벤트조회(select)
	List<EventDTO> show_during_event();

	// 마감된이벤트조회(select)
	List<EventDTO> view_ended_event();

	// 구직자보유포인트랭킹조회 (select)
	List<Map<String, String>> have_point_ranking(int i);

	// 게임별이용횟수조회 (select)
	List<Map<String, String>> play_game_statistics();

	// 월간후기작성건수통계 (select)
	Map<String, Integer> monthly_reviews_statistics();
}
