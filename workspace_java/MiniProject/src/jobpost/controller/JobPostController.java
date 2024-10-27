package jobpost.controller;
import jobpost.service.JobPostDAO; 
import jobpost.service.JobPostDAO_imple; 
import java.util.List;
import java.util.Scanner;
import jobpost.member.domain.JobPostDTO;
import user.domain.*;

public class JobPostController {
	
       private JobPostDAO jobPostService = new JobPostDAO_imple();
  
    // 사용자 메뉴 선택을 처리하는 메소드
//    public void menuJobPost(MemberDTO member, Scanner scanner) {
//        do {
//            System.out.println(">> ---- 메뉴 [일반회원 " + member.getUser_name() + " 로그인 중..] ---- <<");
//            System.out.println("-----------------------------------------------------");
//            System.out.println("1. 이력서 관리  2. 채용 공고  3. 기업정보 조회");
//            System.out.println("4. 내 정보 관리  5. 로그아웃");
//            System.out.println("-----------------------------------------------------");
//            System.out.print("▶ 메뉴 번호를 입력하세요: ");
//
//            String choice = scanner.nextLine();
//            scanner.nextLine();  // 다음 줄을 읽기 위해 추가
//
//            switch (choice) {
//                case "1":
//                    // 이력서 관리 
//                    break;
//               
//                case "2":
//                    JobPostMenu(scanner);
//                    break;
//                
//                case "3":
//                    // 기업정보 조회
//                    break;
//                
//                case "4":
//                    // 내 정보 
//                    break;
//                
//                case "5":
//                    System.out.println(">> ---- 로그아웃 되었습니다!! ---- <<");
//                    return;  // 메소드 종료 (로그아웃)
//                
//                default:
//                    System.out.println("\n [경고]:잘못된 선택입니다! 다시 시도하세요.\n");
//            }
//        } while(true);
//    }

   
    
    
    // 채용 공고 관련 메뉴를 처리하는 메소드
    public void JobPostMenu(MemberDTO member, Scanner scanner) {
       do { 
            System.out.println("\n>> ---- 채용 공고 ---- <<");
            System.out.println("--------------------------------------------");
            System.out.println("1. 공고 조회    2. 공고 지원    3. 돌아가기");
            System.out.println("--------------------------------------------");
            System.out.print("▶ 메뉴 번호를 입력하세요: ");

            String choice = scanner.nextLine();

            switch (choice) {
               
            case "1":
                   showJobPostMenu(scanner, member);  // 공고 조회 메뉴
                   break;
                
            case "2": // 공고 지원 로직
                  applyshowjobPostMenu(member); // 공고 지원 메뉴
                   break;
                
            case "3":
            
                   return;  // 채용 공고 메뉴 종료
                
            default:
                 System.out.println("\n [경고]:잘못된 선택입니다! 다시 시도하세요.\n");
                 break;
            }
        } while(true); 
    }
   
    


    public void showJobPostMenu(Scanner scanner, MemberDTO member) {
         
         String menuChoice;

        do {
            System.out.println("\n>> ----  공고 조회 ---- <<");
            System.out.println("-----------------------------------------------------------------");
            System.out.println("  1. 공고리스트(상세)조회  2. 내가 지원한 공고 조회");
            System.out.println("  3. 돌아가기");
            System.out.println("-----------------------------------------------------------------");

            System.out.print("▶ 메뉴 번호를 입력하세요: ");
            menuChoice = null;
            menuChoice = scanner.nextLine();

            switch (menuChoice) {
            case "1": // 공고 상세 조회
                 // 전체 공고 리스트 출력
                displayAllJobPosts();

                // 사용자에게 상세 조회 여부 물어보기
                System.out.print("상세 조회를 하시겠습니까? [Y/N]: ");
                

              

                String continueChoice = scanner.nextLine();
                if (continueChoice.equalsIgnoreCase("Y")) {
                    showJobPostDetailMenu(scanner);  // 상세 조회 메소드 호출
                }
                break;

//                case "2": // 조건검색조회
//                   showConditionSearchMenu(scanner);
//                   break;

				case "2":
					// 내가 지원한 공고 조회
					displayAppliedJobPosts(scanner, member);
					break;

				case "3":
					// 돌아가기

					return;

				default:
					System.out.println("\n [경고]:잘못된 선택입니다! 다시 시도하세요.\n");
					break;
            }
        } while(true);  // 메뉴 번호가 4가 아닐 경우 반복
    }
   


   

   
private void displayAllJobPosts() {
    List<JobPostDTO> jobPosts = jobPostService.getAllJobPosts();  // 모든 공고를 조회

    // 공고가 없는 경우 처리
    if (jobPosts.isEmpty()) {
        System.out.println("등록된 공고가 없습니다.");
    } else {
        // StringBuilder를 사용하여 출력 내용을 구성
        StringBuilder sb = new StringBuilder();
        sb.append("\n" + "-".repeat(50) + " [공고 리스트] " + "-".repeat(50) + "\n");
        sb.append("공고번호\t\t기업명\t\t\t연봉\t\t\t공고기간\t\t조회수\n");
        sb.append("-".repeat(113) +"\n");
       

        for (JobPostDTO jobPost : jobPosts) {
            // 각 항목을 정렬하여 추가
            sb.append(String.format("%-10d %-20s %-10d %-25s ~ %-25s %-15d%n",
                      jobPost.getJop_postno(), 
                      jobPost.getCom_name(), 
                      jobPost.getWage(), 
                      jobPost.getWrite_date(), 
                      jobPost.getEnd_date(),
                      jobPost.getView_count()));
        }

        // StringBuilder의 내용을 출력
        System.out.println(sb.toString());
    }
}







//    상세 공고 번호 입력시 상세 공고를 볼 수 있는 메소드
private void displayJobPostDetail(JobPostDTO jobPost) {
	if (jobPost != null) {
		System.out.printf("%-10s %-10s %-30s%n", "기업명", "직종명", "공고 제목");
		System.out.println("-------------------------------------------");
		System.out.printf("%-10s %-10s %-30s%n", jobPost.getCom_name() != null ? jobPost.getCom_name() : "N/A", // 기업명
	    
	    jobPost.getJob_type() != null ? jobPost.getJob_type() : "N/A", // 직종명
	    jobPost.getPost_title() != null ? jobPost.getPost_title() : "N/A"); // 공고 제목
		
		System.out.println("---------------------------------------------");
		System.out.println("내용: " + (jobPost.getPost_contents() != null ? jobPost.getPost_contents() : "내용 없음")); // 공고내용
																													
	
	} else {
		System.out.println("해당 공고를 찾을 수 없습니다.");
	}
}


 
// 상세 공고 조회를 위한 메소드 추가
public void showJobPostDetailMenu(Scanner scanner) {
    String jobPostNo = null;
    do {
    	 try {
    		 System.out.print("▶ 상세 조회 할 공고 번호를 입력하세요: ");
    		 jobPostNo = scanner.nextLine();
    		 Integer.parseInt(jobPostNo);
    		 break;
    	 } catch (NumberFormatException e) {
    		 System.out.println("[경고]: 정수만 입력하세요! ");
    	 }
    }while(true);

    JobPostDTO jobPost = jobPostService.getJobPostDetail(Integer.parseInt(jobPostNo));
    displayJobPostDetail(jobPost); // 상세 정보 출력
    


  }
    



// 조건 검색 메뉴를 보여주는 메소드

private void showConditionSearchMenu(Scanner scanner) {


	
    System.out.println(">>  조건 검색 입력란  <<");
    System.out.println("-------------------------------------------------");   
    
    System.out.print("▶ 공고번호: "); 
    String jop_postno = scanner.nextLine();

    System.out.print("▶ 기업명: "); 
    String com_name = scanner.nextLine();

    System.out.print("▶ 연봉: "); 
    String wage = scanner.nextLine();

    System.out.print("▶ 직종명: "); 
    String job_type = scanner.nextLine();

    System.out.println("-------------------------------------------------");
   
    // 조건 검색을 실행하는 로직 추가
   // List<JobPostDTO> filteredPosts = jobPostService.searchJobPosts(jop_postno, com_name, wage, job_type);
   // displayFilteredJobPosts(filteredPosts);

    System.out.println("▶  (뒤로가려면 엔터를 누르십시오) ");
    scanner.nextLine();  // 뒤로 가기를 위해 엔터 입력 대기
}










// 필터링된 공고 리스트를 출력하는 메소드
private void displayFilteredJobPosts(List<JobPostDTO> jobPosts) {
    if (jobPosts.isEmpty()) {
        System.out.println("조건에 맞는 공고가 없습니다.");
    } else {
        // 공고 리스트 출력 
        StringBuilder sb = new StringBuilder();
        sb.append("\n" + "-".repeat(50) + " [검색 결과] " + "-".repeat(50) + "\n");
        sb.append("공고번호\t\t기업명\t\t\t연봉\t\t\t공고기간\t\t조회수\n");
        sb.append("-".repeat(113) +"\n");

        for (JobPostDTO jobPost : jobPosts) {
            sb.append(String.format("%-10d %-20s %-10d %-25s ~ %-25s %-15d%n",
                      jobPost.getJop_postno(), 
                      jobPost.getCom_name(),  
                      jobPost.getWage(), 
                      jobPost.getWrite_date(), 
                      jobPost.getEnd_date(),
                      jobPost.getView_count()));
        }
        System.out.println(sb.toString());
    }
}

    
    
    
    
//내가 지원한 공고 조회를 위한 메소드 추가
private void displayAppliedJobPosts(Scanner scanner, MemberDTO member) {
    System.out.println(">>  내가 지원한 공고 조회 <<");
    System.out.println("-------------------------------------------------");
    System.out.println("공고지원번호 공고번호 기업번호   기업명   직종     연봉     공고기간");
    System.out.println("-------------------------------------------------");

    // userNo 값을 로그인 정보에서 가져오는 부분
    int userNo = member.getUser_no(); // 로그인한 사용자 정보를 가져오는 메소드

    List<JobPostDTO> appliedPosts = jobPostService.ViewAppliedJobPosts(userNo);

    if (appliedPosts.isEmpty()) {
        System.out.println("지원한 공고가 없습니다.");
    } else {
        for (JobPostDTO post : appliedPosts) {
            System.out.printf("%-10d %-10d %-10d %-20s %-10s %-10d %-20s%n",
            		post.getApply_no(),
            		post.getJop_postno(),
                    post.getFk_com_no(),
                    post.getCom_name(),
                    post.getJob_type(),
                    post.getWage(),
                    post.getWrite_date() + " ~ " + post.getEnd_date());
        }
    }

}





//공고 지원 메뉴
private void applyshowjobPostMenu(MemberDTO member) {
 Scanner scanner = new Scanner(System.in);
 String applymenuChoice;

 do {
     System.out.println(">> ----  채용 지원 메뉴 ---- <<");
     System.out.println("-----------------------------------------------------------------");
     System.out.println(" 1.공고 지원    2.공고 지원 취소     3.돌아가기");
     System.out.println("-----------------------------------------------------------------");

     System.out.print("▶ 메뉴 번호를 입력하세요: ");
     applymenuChoice = scanner.nextLine();

     switch (applymenuChoice) {
         case "1": // 공고 지원 
        	 
        	 applyJobPostMenu(scanner, member);
       
             break;

         case "2": // 공고 지원 취소
                cancle_appled_joppost(scanner ,member);
             break;

         case "3": // 돌아가기
             return;

         default:
             System.out.println("\n [경고]:잘못된 선택입니다! 다시 시도하세요.\n");
     }
   } while (true); // 메뉴 번호가 3이 아닐 경우 반복
}






// 전체 이력서 리스트 조회 메소드 
    private void showAllResumeList(MemberDTO member) {
        List<JobPostDTO> jobPosts = jobPostService.showAllResumeList(member);  // 모든 공고를 조회

        // 공고가 없는 경우 처리
        if (jobPosts.size() == 0) {
            System.out.println("등록된 이력서가 없습니다.");
        } else {
            // StringBuilder를 사용하여 출력 내용을 구성
            StringBuilder sb = new StringBuilder();
            sb.append("\n" + "-".repeat(50) + " [나의 이력서 리스트] " + "-".repeat(50) + "\n");
            sb.append(String.format("%-15s %-30s %-30s %-10s %-15s%n", "이력서 번호", "이력서 제목", "자기소개", "연차", "희망연봉"));
            sb.append("-".repeat(113) + "\n");

            for (JobPostDTO jobPost : jobPosts) {
                // 각 항목을 정렬하여 추가
                sb.append(String.format("%-15d %-30s %-30s %-10d %-15d%n", 
                    jobPost.getResume_no(), 
                    jobPost.getRes_title(), 
                    jobPost.getRes_intro(), 
                    jobPost.getRes_career(), 
                    jobPost.getRes_hwage()));
            }

            // StringBuilder의 내용을 출력
            System.out.println(sb.toString());
        }
    }


    private void applyJobPostMenu(Scanner scanner, MemberDTO member) {
        showAllResumeList(member); // 이력서 목록 보여주기
    	String job_postno = null;
    	String resume_no = null;
		// 지원할 채용공고 번호 입력
		while (true) {
			System.out.print("▶ 지원할 채용공고 번호 입력: ");
		     job_postno = scanner.nextLine(); // String으로 읽어들입니다.
			try {
				Integer.parseInt(job_postno);
				break;
			} catch (NumberFormatException e) {
				System.out.println("[경고]: 숫자만 입력하십시오.");
			}
		}
		// 제출할 이력서 번호 입력
		while (true) {
			System.out.print("▶ 제출할 이력서 번호 입력: ");
			 resume_no = scanner.nextLine();
			try {
				Integer.parseInt(resume_no);
				break;
			} catch (NumberFormatException e) {
				System.out.println("[경고]: 숫자만 입력하십시오.");
			}
		}
        JobPostDAO jobPostDAO = new JobPostDAO_imple(); // DAO 인스턴스 생성
        
        // 지원한 공고에는 이력서 지원이 불가 출력
        if (jobPostDAO.isAlreadyApplied(Integer.parseInt(job_postno), Integer.parseInt(resume_no))) {
            System.out.println("이미 지원한 공고에는 이력서 지원이 불가합니다.");
            return; // 메소드 종료
        }

        // 지원 여부 확인
        String yn;
        do {
            System.out.print("\n정말로 공고에 지원하시겠습니까? [Y/N]: ");
            yn = scanner.nextLine(); 

            if (!"y".equalsIgnoreCase(yn) && !"n".equalsIgnoreCase(yn)) {
                System.out.println("[경고!!!]: Y/N만 입력하세요!!");
            }
        } while (!"y".equalsIgnoreCase(yn) && !"n".equalsIgnoreCase(yn)); // 유효하지 않은 입력일 경우 반복

        if ("y".equalsIgnoreCase(yn)) {
            // 공고 지원 로직 호출
            boolean isApplied = jobPostDAO.applyjobpostmenu(Integer.parseInt(job_postno), Integer.parseInt(resume_no));
            
            if (isApplied) {
                System.out.println("지원이 성공적으로 완료되었습니다.");
            } else {
                System.out.println("지원이 실패했습니다.");
            }
        } else {
            System.out.println("공고 지원이 취소되었습니다.\n");
        }
    }


    private void cancle_appled_joppost(Scanner scanner, MemberDTO member) {
        // scanner.nextLine(); 
        //showAllResumeList(); // 이력서 목록 보여주기

        // 지원할 공고 번호 번호 입력
        System.out.print("▶ 지원 취소할 공고 지원 번호 입력: ");
        String job_postno = scanner.nextLine(); // String으로 읽어들입니다.

        JobPostDAO jobPostDAO = new JobPostDAO_imple(); // DAO 인스턴스 생성
        
        
        // 지원 여부 확인
        String yn;
        do {
            System.out.print("\n정말로 공고지원을 취소 하시겠습니까?[Y/N]: ");
            yn = scanner.nextLine(); 

            if (!"y".equalsIgnoreCase(yn) && !"n".equalsIgnoreCase(yn)) {
                System.out.println("[경고!!!]: Y/N만 입력하세요!!");
            }
        } while (!"y".equalsIgnoreCase(yn) && !"n".equalsIgnoreCase(yn)); // 유효하지 않은 입력일 경우 반복

        if ("y".equalsIgnoreCase(yn)) {
            // 공고 지원 로직 호출
            boolean isApplied = jobPostDAO.canclejobpostmenu(Integer.parseInt(job_postno), member.getUser_no());
            
            if (isApplied) {
                System.out.println("공고 지원이 성공적으로 삭제되었습니다.");
            } else {
                System.out.println("공고 지원 삭제가 실패했습니다.");
            }
        
        }
    }

    


/*
 // 공고 지원 메뉴
    private void applyjobpostmenu(Scanner scanner) {
        scanner.nextLine(); 
        showAllResumeList();

        // 지원할 채용공고 번호 입력
   
        System.out.print("▶ 지원할 채용공고 번호 입력: ");
        String job_postno = scanner.nextLine(); // String으로 읽어들입니다.

        // 제출할 이력서 번호 입력
        System.out.print("▶ 제출할 이력서 번호 입력: ");   
        int resume_no = scanner.nextInt(); 
        scanner.nextLine(); 
        
        // 지원한 공고에는 이력서 지원이 불가 출력
        JobPostDAO jobPostDAO = new JobPostDAO_imple(); // DAO 인스턴스 생성
        if (jobPostDAO.isAlreadyApplied(Integer.parseInt(job_postno), resume_no)) {
            System.out.println("이미 지원한 공고에는 이력서 지원이 불가합니다.");
            return; // 메소드 종료
        }


        String yn;
        do {
            // 지원 여부 확인
            System.out.print("\n정말로 공고에 지원하시겠습니까? [Y/N]: ");
            yn = scanner.nextLine(); 

            if (!"y".equalsIgnoreCase(yn) && !"n".equalsIgnoreCase(yn)) {
                System.out.println("[경고!!!]: Y/N만 입력하세요!!]");
            }
           } while (!"y".equalsIgnoreCase(yn) && !"n".equalsIgnoreCase(yn)); // 유효하지 않은 입력일 경우 반복

        if ("y".equalsIgnoreCase(yn)) {
            // 공고 지원 로직 호출
            System.out.println("공고 지원에 성공했습니다.\n");
        } else {
            System.out.println("공고 지원이 취소되었습니다.\n");
        }
       
    }


  */  

}




 

    
    
    
/*

    //공고 지원 철회
	private void cancle_appled_joppost(Scanner scanner) {
		  scanner.nextLine(); 
	      showAllResumeList(); // 임시로 전체리스트로 둠 나중에 각 계정에 지원할 리스트로 변경할것!!
	      
	      System.out.println("▶ 공고 지원을 취소할 이력서 번호: ");
	     
	      System.out.println(" 정말로 공고에 지원을 철회하시겠습니까? [Y/N]: ");
	      String yn = scanner.nextLine(); 
	      
	      if ("y".equalsIgnoreCase(yn)) {
	     	 System.out.println(" 공고 지원이 철회 되었습니다. \n");
	      
	       } else if 
	      
	         ("n".equalsIgnoreCase(yn)) {
	         System.out.println(" 공고 지원 철회가 취소 되었습니다. \n");
	      
	       } 
	        
	         else {
	         System.out.println(" 이력서 번호가 존재하지 않습니다. \n");
	       }
	      	 
		
	}

}
    
 */
    
    
    
    
    
    
  