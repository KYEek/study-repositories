package game.model;

import user.domain.CompanyDTO;
import user.domain.MemberDTO;

import java.util.Map;

public interface GameDAO {

    // 가위바위보결과에 따라 포인트지급(update)
    int game_point(MemberDTO member, CompanyDTO company, String eventNo, int point);

    // 게임 정보를 가져옴(select)
    Map<String, String> game_info(int i);

    // 랜덤한 문제와 답을 가져옴 (select)
    Map<String, String> get_quiz(int quizNo);

    // 문제 개수를 가져옴 (select)
    int get_quiz_count();
}
