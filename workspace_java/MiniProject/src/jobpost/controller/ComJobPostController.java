package jobpost.controller;

import java.util.List;
import java.util.Scanner;

import jobpost.service.*;
import user.domain.CompanyDTO;
import jobpost.member.domain.JobPostDTO;

public class ComJobPostController {
	
	JobPostDAO jobPostDAO = new JobPostDAO_imple(); 
	JobPostController jpctrl = new JobPostController();

	public void jobpostcdmenu(Scanner sc, CompanyDTO company) { 
		 do { 
	            System.out.println("\n>> ---- 공고 관리 ---- <<");
	            System.out.println("--------------------------------------------");
	            System.out.println( " 1.공고 등록   2.공고 조회   3. 공고 수정 \n"
	            		           + "4.공고 삭제   5. 돌아가기" );
	            		
	            System.out.println("--------------------------------------------");
	            System.out.print("▶ 메뉴 번호를 입력하세요: ");

	            String choice = null;
	            choice = sc.nextLine();

	            switch (choice) {
	               
	            case "1":    // 공고 등록
	                  insertjobpost(sc, company);
	                   break;
	                
	            case "2": // 공고 조회
	                  applyshowjobPostMenu(sc, company); // 공고 지원 메뉴
	                   break;
	                
	            case "3": // 공고 수정 
	                   viewjobpost(sc, company);
	                   break;
	                   
	            case "4": // 공고 삭제
	            	   deleteJobpost(sc, company);
	            	   break;
	            	
	            case "5": // 돌아가기 
	            	
	            	    return;  // 공고 관리 메뉴 종료
	                
	            default:
	                 System.out.println("\n [경고]:잘못된 선택입니다! 다시 시도하세요.\n");
	                 break;
	            }
	        } while(true); 
	}

   

   




	// 기업이 공고 등록하는 메소드
	private void insertjobpost(Scanner sc, CompanyDTO company) {
		JobPostDTO jp = new JobPostDTO();

		System.out.println("▶ 공고 제목 입력: ");
		jp.setPost_title(sc.nextLine());
		
		System.out.println("▶ 공고 내용 입력: ");
		jp.setPost_contents(sc.nextLine());
	  while (true){
			try {
				System.out.println("▶ 연봉 입력 (만원 단위):	 ");
				jp.setWage(Integer.parseInt(sc.nextLine()));   
				break;
 
			    } catch (NumberFormatException e) {
				System.out.println("[경고]정수를 입력하세요!");
			   }
	      }
		System.out.println("▶ 마감일 입력(YYYY-MM-DD): ");
		jp.setEnd_date(sc.nextLine());
		
		outer:
		while (true) {
			String inputnumber = "";
			
            System.out.println("1번은 정규직 2번은 비정규직");
			System.out.print("▶ 고용형태 입력: ");
			
			inputnumber = sc.nextLine();
			switch (inputnumber) {
			case "1":
			case "2":
				jp.setFk_work_TCODE(Integer.parseInt(inputnumber));
				break outer;

			default:
				System.out.println("[경고]: 1 또는 2만 입력하세요!!");
				break;
			}		
		}

		String yn;
	        do {
	            System.out.print("\n정말로 공고를 등록하시겠습니까? [Y/N]: ");
	            yn = sc.nextLine(); 

	            if (!"y".equalsIgnoreCase(yn) && !"n".equalsIgnoreCase(yn)) {
	                System.out.println("[경고!!!]: Y/N만 입력하세요!!");
	            }
	        } while (!"y".equalsIgnoreCase(yn) && !"n".equalsIgnoreCase(yn)); // 유효하지 않은 입력일 경우 반복

	        if ("y".equalsIgnoreCase(yn)) {
	            // 공고 지원 로직 호출
	            boolean isApplied = jobPostDAO.insertjobpost(jp,company);
	            
	            if (isApplied) {
	                System.out.println("공고가 성공적으로 등록되었습니다.");
	            } else {
	                System.out.println("공고 등록이 실패했습니다. ");
	            }
	        
	        }
		
	}
	
	
	
	// 공고 조회 메소드
	private void applyshowjobPostMenu(Scanner sc, CompanyDTO company) {
	    System.out.println("\n>> ---- 등록된 공고 목록 ---- <<");
	    System.out.println("--------------------------------------------");

	    // 기업 ID 또는 회사 번호를 사용하여 등록된 공고를 가져옵니다.
	    int companyNo = company.getCom_no(); // CompanyDTO에서 회사 번호를 가져옵니다.
	    
	    // 등록된 공고 목록을 가져오는 DAO 메소드를 호출합니다.
	    List<JobPostDTO> jobPostList = jobPostDAO.getJobPostsByCompany(companyNo);
	    
	    // 공고가 없을 경우 처리
	    if (jobPostList.isEmpty()) {
	        System.out.println("등록된 공고가 없습니다.");
	    } else {
	        // 등록된 공고가 있을 경우 목록 출력
	        System.out.printf("%-10s %-20s %-20s %-10s %-15s\n", "공고 번호", "공고 제목", "연봉", "고용 형태", "마감일");
	        System.out.println("--------------------------------------------");
	        
	        for (JobPostDTO jobPost : jobPostList) {
	            System.out.printf("%-10d %-20s %-20d %-10s %-15s\n",
	                jobPost.getJob_postno(),
	                jobPost.getPost_title(),
	                jobPost.getWage(),
	                jobPost.getFk_work_TCODE() == 1 ? "정규직" : "비정규직",
	                jobPost.getEnd_date());
	        }
	    }
	    // 사용자에게 상세 조회 여부 물어보기
        System.out.print("상세 조회를 하시겠습니까? [Y/N]: ");
        

      

        String continueChoice = sc.nextLine();
        if (continueChoice.equalsIgnoreCase("Y")) {
            jpctrl.showJobPostDetailMenu(sc);  // 상세 조회 메소드 호출
        }
	    System.out.println();
	}

	
	
	
	
	

    // 공고 수정
	private void viewjobpost(Scanner sc, CompanyDTO company) {
		
		JobPostDTO jp = new JobPostDTO();
		System.out.println("▶ 수정할 공고 번호를 입력하세요: ");
		String input = null;
		int  jobPostNo= 0;
		while (true){
			try {
				System.out.println("▶ 수정할 공고 번호:	 ");
			    jobPostNo = Integer.parseInt(sc.nextLine());  
				break;
 
			    } catch (NumberFormatException e) {
				System.out.println("[경고]정수를 입력하세요!");
			   }
	      }
		
		JobPostDTO jobPost = jobPostDAO.getJobPostDetail(jobPostNo);
		System.out.println("수정하지 않으려면 엔터를 누르십시오.");
		 
		System.out.println("▶ 공고 제목 수정: ");
		input = sc.nextLine();
		jp.setPost_title(input);
		if(input.isBlank())
		{
			jp.setPost_title(jobPost.getPost_title());
		}
		System.out.println("▶ 공고 내용 수정: ");
		input = sc.nextLine();
		jp.setPost_contents(input);
		if (input.isBlank())
		{
			jp.setPost_contents(jobPost.getPost_contents());
		}
	  while (true){
			try {
				String inputnumber = "";
				System.out.println("▶ 연봉 수정:	 ");
				
				inputnumber = sc.nextLine();
				
				if(inputnumber.isEmpty()) {
					jp.setWage(jobPost.getWage());
					break;
				}
				jp.setWage(Integer.parseInt(inputnumber));   
				break;
 
			    } catch (NumberFormatException e) {
				System.out.println("[경고]정수를 입력하세요!");
			   }
	  }
		System.out.println("▶ 마감일 수정(YYYY-MM-DD): ");
		input = sc.nextLine();
		jp.setEnd_date(input);
		if(input.isBlank())
		{
			jp.setEnd_date(jobPost.getEnd_date());
		}
		outer:
		while (true) {
			String inputnumber = "";
			
            System.out.println("1번은 정규직 2번은 비정규직");
			System.out.print("▶ 고용형태 수정: ");
			
			inputnumber = sc.nextLine();
			if(inputnumber.isEmpty()) {
				jp.setFk_work_TCODE(jobPost.getFk_work_TCODE());
				break;
			}
			
			switch (inputnumber) {
			case "1":
			case "2":
				jp.setFk_work_TCODE(Integer.parseInt(inputnumber));
				break outer;

			default:
				System.out.println("[경고]: 1 또는 2만 입력하세요!!");
				break;
			}

		}
		
		String yn;
	        do {
	            System.out.print("\n정말로 공고를 수정하시겠습니까? [Y/N]: ");
	            yn = sc.nextLine(); 

	            if (!"y".equalsIgnoreCase(yn) && !"n".equalsIgnoreCase(yn)) {
	                System.out.println("[경고!!!]: Y/N만 입력하세요!!");
	            }
	        } while (!"y".equalsIgnoreCase(yn) && !"n".equalsIgnoreCase(yn)); // 유효하지 않은 입력일 경우 반복

	        if ("y".equalsIgnoreCase(yn)) {
	            // 공고 지원 로직 호출
	            boolean isApplied = jobPostDAO.updatejobpost(jp,jobPostNo);
	            
	            if (isApplied) { // isApplied 도 가능
	                System.out.println("공고가 성공적으로 수정되었습니다.");
	            } else {
	                System.out.println("공고 수정이 실패했습니다.");
	            }
	        
	        }

		}

		// 기업 공고 삭제
		private void deleteJobpost(Scanner sc, CompanyDTO company) {
			// 지원할 공고 번호 번호 입력
	        System.out.print("▶ 삭제할 공고 번호 입력: ");
	        String job_postno = sc.nextLine(); // String으로 읽어들입니다.

	        JobPostDAO jobPostDAO = new JobPostDAO_imple(); // DAO 인스턴스 생성
	        
	        
	        // 지원 여부 확인
	        String yn;
	        do {
	            System.out.print("\n정말로 공고를 삭제하시겠습니까? [Y/N]: ");
	            yn = sc.nextLine(); 

	            if (!"y".equalsIgnoreCase(yn) && !"n".equalsIgnoreCase(yn)) {
	                System.out.println("[경고!!!]: Y/N만 입력하세요!!");
	            }
	        } while (!"y".equalsIgnoreCase(yn) && !"n".equalsIgnoreCase(yn)); // 유효하지 않은 입력일 경우 반복

	        if ("y".equalsIgnoreCase(yn)) {
	            // 공고 삭제 로직 호출
	            boolean isApplied = jobPostDAO.deletejobpostmenu(Integer.parseInt(job_postno), company.getCom_no());
	            
	            if (isApplied) {
	                System.out.println("공고 지원이 성공적으로 삭제되었습니다.");
	            } else {
	                System.out.println("공고 지원 삭제가 실패했습니다.");
	            }
	        
	        }
		}
		
		
		
		
		
	
}
	

