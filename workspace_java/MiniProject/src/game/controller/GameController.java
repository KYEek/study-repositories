package game.controller;

import admin.domain.EventDTO;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import admin.model.*;
import game.model.GameDAO;
import game.model.GameDAO_imple;
import user.domain.CompanyDTO;
import user.domain.MemberDTO;

public class GameController {

    // field
    AdminDAO admindao = new AdminDAO_Imple();
    GameDAO gamedao = new GameDAO_imple();

    // method
    // == 미니게임 메인화면 메소드 == //
    public void game_menu(MemberDTO member, CompanyDTO company, Scanner sc) {

        do {
            System.out.println("\n---------- >>> 미니게임메뉴 <<< ----------");
            System.out.println("1.게임보기   2.게임하기   3.뒤로가기");
            System.out.println("-".repeat(40));
            System.out.print(">> 메뉴번호입력: ");

            String menu_no = sc.nextLine();

            switch (menu_no) {
                case "1":		// 게임보기
                    view_game();
                    break;

                case "2":		// 게임하기
                    play_game(member, company, sc);
                    break;

                case "3":		// 뒤로가기
                    return;

                default:
                    System.out.println(">> [경고] 메뉴에 없는 번호입니다. <<");
                    break;
            }
        } while(true);

    }// end of public void game_menu(Scanner sc) ----------------------------------


    // == 현재 진행중인 미니게임을 보여주는 메소드 == //
    private void view_game() {
        List<EventDTO> during_event = admindao.show_during_event();

        if (during_event.size() > 0) {
            StringBuilder sb = new StringBuilder();

            sb.append("\n>> 현재 진행중인 미니게임 목록 <<\n");
            sb.append("-".repeat(50) +"\n");
            sb.append("게임번호\t게임명\t게임소개\t종료일\t남은기간\n");
            sb.append("-".repeat(50) +"\n");

            for (int i=0; i<during_event.size(); i++) {
                sb.append(during_event.get(i).getEvent_no() 		+ "\t"	+
                        during_event.get(i).getEvent_name() 		+ "\t"	+
                        during_event.get(i).getEvent_contents() 	+ "\t"	+
                        during_event.get(i).getEvent_end() 		    + "\t"	+
                        during_event.get(i).getDuring_days() 		+ "일\n"
                ) ;

            }// end of for() --------------------------

            System.out.println(sb);
        }
        else {
            System.out.println(">> 진행중인 미니게임이 없습니다. <<");
        }
    }// end of private void view_game() -------------------------------


    // == 미니게임을 진행하는 메소드 == //
    private void play_game(MemberDTO member, CompanyDTO company, Scanner sc) {

        do {
            System.out.println("\n-------------- >>> 미니게임목록 <<< --------------");
            System.out.println("1.가위바위보   2.숫자 맞추기   3.사자성어 맞추기   4.뒤로가기");
            System.out.println("-".repeat(48));
            System.out.print(">> 메뉴번호입력: ");

            String menu_no = sc.nextLine();

            switch (menu_no) {
                case "1":        // 가위바위보
                    rock_scissors_paper(member, company, sc);
                    break;

                case "2":        // 숫자 맞추기
                    target_number(member, company, sc);
                    break;

                case "3":        // 사자성어 맞추기
                    four_words_quiz(member, company, sc);
                    break;

                case "4":        // 뒤로가기
                    return;
                default:
                    System.out.println(">> [경고] 메뉴에 없는 번호입니다. <<");
                    break;
            }
        } while(true);
    }// end of private void play_game(Scanner sc)


    // == 가위바위보 게임 메소드 == //
    private void rock_scissors_paper(MemberDTO member, CompanyDTO company, Scanner sc) {

        // 게임정보를 가져옴
        Map<String, String> game = gamedao.game_info(2);

        Random random = new Random();
        int choice 		= 0;
        int win_conut 	= 0;
        int point       = 0;

        System.out.println("\n==== >>> "+ game.get("event_name") +" 게임 <<< ====\n");
        System.out.println("총 3번 진행하며 승리횟수에 따라 포인트가 지급 혹은 차감됩니다.");
        System.out.println(">> 종료하시려면 엔터를 입력하세요 <<");
        for (int i=0; i<3; i++) {
            try {

                System.out.println("\n============= 선택 ==============\n"+
                                   "\t1.가위\t2.바위\t3.보\n"+
                                   "================================");

                System.out.print(" >> 선택하세요 => ");
                String input = sc.nextLine();

                if (input.isEmpty()) {
                    System.out.println(">> 게임 종료.. <<");
                    return;
                }

                choice = Integer.parseInt(input);

                if (!(1 <= choice && choice <= 3)) {
                    System.out.println("[경고] 사용할 수 없는 값입니다 !!\n");
                    i--;
                    continue;
                }// end of if()----------------------

                int pc_num = random.nextInt(3 - 1 + 1) + 1;

                // 사용자가 이긴 경우
                if ((choice == 1 && pc_num == 3) ||
                        (choice == 2 && pc_num == 1) ||
                        (choice == 3 && pc_num == 2)) {
                    win_conut++;
                    System.out.println("승리! 승리횟수: " + win_conut);
                }
                // 사용자가 진 경우
                else if ((choice == 3 && pc_num == 1) ||
                        (choice == 1 && pc_num == 2) ||
                        (choice == 2 && pc_num == 3)) {

                    System.out.println("패배ㅠ 승리횟수: " + win_conut);
                }
                // 비긴 경우
                else
                    System.out.println("무승부. 승리횟수: " + win_conut);
                // end of if()----------------------

            }catch (NumberFormatException e) {
                System.out.print("[경고] 숫자로만 입력하세요!!\n");
            }// end of try~catch()---------------------

        }// end of for() --------------------

        if (win_conut >= 2) {
            // 쿼리실행해야
            point = 20;
            int n = gamedao.game_point(member, company, game.get("event_no"), point);
            if (n == 1 ) {
                System.out.println("\n>> 최종결과: " + win_conut + "승, 포인트 20 지급!!");
            }
        }
        else if (win_conut == 1) {
            // 쿼리실행해야
            System.out.println("\n>> 최종결과: "+ win_conut +"승, 포인트 0 지급");
        }
        else {
            // 쿼리실행해야
            point = -10;
            int n = gamedao.game_point(member, company, game.get("event_no"), point);
            if (n == 1) {
                System.out.println("\n>> 최종결과: " + win_conut + "승, 포인트 10 차감..ㅠ");
            }
        }

    }// end of private void rock_scissors_paper(MemberDTO member, Scanner sc) ---------------------------------


    private void target_number(MemberDTO member, CompanyDTO company, Scanner sc) {

        // 게임정보를 가져옴
        Map<String, String> game = gamedao.game_info(7);

        Random random = new Random();
        int npc_num = random.nextInt(30 -1 + 1) + 1;
        int pc_num 	= 0;
        int count 	= 0;
        int point   = 0;
        System.out.println("\n==== >>> "+ game.get("event_name") +" 게임 <<< ====\n");
        System.out.println("시도 횟수에 따라 포인트 지급 혹은 차감됩니다.");
        System.out.println(">> 종료하시려면 엔터를 입력하세요 <<");
        do {
            try {
                System.out.println("\n>> 1 부터 30 사이의 숫자 중 하나를 고르세요.");
                System.out.print(">> 숫자입력: ");
                String input = sc.nextLine();

                if (input.isEmpty()) {
                    System.out.println(">> 게임종료.. <<");
                    return;
                }

                pc_num = Integer.parseInt(input);
                if (!(1<=pc_num && pc_num <= 30)) {
                    System.out.println("1~30 사이의 숫자만 입력하세요!!");
                    continue;
                }// 범위밖의 숫자를 입력했을 경우 -------------------

                if (npc_num != pc_num) {		// 정답이 아닐 때
                    count++;
                    if (npc_num > pc_num) {
                        System.out.println("\n>> 땡!! [힌트] "+ pc_num +"보다 큽니다.");
                    } else {
                        System.out.println("\n>> 땡!! [힌트] "+ pc_num +"보다 작습니다.");
                    }
                }
                else {		// 정답일 때
                    count++;
                    System.out.println("\n정답! " + count +"번 만에 맞췄습니다.");

                    // 횟수별로 맞췄을 때
                    if (count <= 5) {
                        point = 30;
                        int n = gamedao.game_point(member, company, game.get("event_no"), point);
                        if (n == 1) {
                            System.out.println("포인트 30 지급!!");
                        }
                        break;
                    }
                    else if (5<= count && count <= 7) {
                        point = 10;
                        int n = gamedao.game_point(member, company, game.get("event_no"), point);
                        if (n == 1) {
                            System.out.println("포인트 10 지급!!");
                        }
                        break;
                    }
                    else if (7<= count && count <= 10) {
                        System.out.println("포인트 0 지급!!");
                        break;
                    }
                    else{
                        point = -10;
                        int n = gamedao.game_point(member, company, game.get("event_no"), point);
                        if (n == 1) {
                            System.out.println("10회 이상으로 포인트 10차감..");
                        }
                        break;
                    }

                }// end of if() ----------------------

            } catch(NumberFormatException e) {
                System.out.println(">> [경고] 숫자만 입력하세요!! <<");
            }
        } while (true);
    }// end of public static void target_number() ------------------------------


    // == 사자성어 맞추기 게임 메소드 == //
    private void four_words_quiz(MemberDTO member, CompanyDTO company, Scanner sc) {
        // 게임정보를 가져옴
        Map<String, String> game = gamedao.game_info(9);
        int quiz_count = gamedao.get_quiz_count();
        Random random = new Random();

        // 값 세팅
        int answer_count = 0;
        int point        = 0;
        int before_quiz  = 0;

        System.out.println("\n==== >>> "+ game.get("event_name") +" 게임 <<< ====\n");
        System.out.println("총 5문제가 출제되며 정답수에 따라 포인트 지급 혹은 차감됩니다.");
        System.out.println(">> 종료하시려면 엔터를 입력하세요 <<");

        for (int i=0; i<5; i++) {
            int quiz_no = random.nextInt(quiz_count -1 + 1) + 1;     // 랜덤한 문제 뽑기

            if (quiz_no == before_quiz){    // 이전 출제문제와 같으면
                i--;
                continue;
            }

            Map<String, String> quiz = gamedao.get_quiz(quiz_no);   // 문제와 정답 정보를 담아옴

            System.out.println("\n================== 문제 ===================\n"+
                                quiz.get("word_desc") + "\n" +
                                "==========================================");

            System.out.print(" >> 답안 => ");
            String answer = sc.nextLine();

            if (answer.isEmpty()) {
                System.out.println(">> 게임 종료.. <<");
                return;
            }

            if (answer.equals(quiz.get("word_answer"))) {
                System.out.println("\n>> *** 정답입니다~! *** <<");
                answer_count++;
                point += 10;
                System.out.println("맞춘문제수: " + answer_count);
            }
            else {
                System.out.println("\n>> ### 오답입니다~ㅠ ### <<");
                System.out.println("맞춘문제수: " + answer_count);
            }

            before_quiz = quiz_no;      // 해당 문제의 번호를 담음
        }// end of for() --------------------

        if (answer_count == 0) {
            System.out.println(">> 한 문제도 맞추지 못했어요ㅠㅠ 공부 후 다시도전하세요!! <<");
            point = -10;
            int n = gamedao.game_point(member, company, game.get("event_no"), point);
            if (n == 1) {
                System.out.println(">> 포인트 10차감.. <<");
            }
        }
        else {
            int n = gamedao.game_point(member, company, game.get("event_no"), point);
            if (n == 1) {
                System.out.println(">> 최종 정답개수: " + answer_count + "개, 포인트 " + point + " 획득! <<");
            }
        }
    }// end of private void four_words_quiz(MemberDTO member, CompanyDTO company, Scanner sc) -------------------------


}
