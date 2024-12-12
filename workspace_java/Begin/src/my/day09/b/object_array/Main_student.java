package my.day09.b.object_array;

import java.util.Scanner;

public class Main_student {

	/*
	 	=================== >> 메뉴 << ====================
	 	1. 학생점수입력	2.모든학생성적출력	3.특정학생성적출력	4. 종료
	 	==================================================
	 	▣ 메뉴번호선택 : 5
	 	>>[경고]메뉴에 없는 번호입니다.
	 	
	 	▣ 메뉴번호선택 : 1
	 	>> 학생점수입력 <<
	 	1)학번 : 001
	 	2)성명 : 이순신
	 	3)국어 : 90
	 	>> [경고] 점수는 0~100만 가능합니다.
	 	3)국어 : 90
	 	4)영어 : 100
	 	5)수학 : 80
	 	== 학생점수입력 완료 ==
	 	=================== >> 메뉴 << ====================
	 	1. 학생점수입력	2.모든학생성적출력	3.특정학생성적출력	4. 종료
	 	==================================================
	 	
	 	
	 	▣ 메뉴번호선택 : 1
	 	>> 정원(3명) 마감되어 더이상 학생점수입력이 불가합니다.
	 	
	 	
	 	=================== >> 메뉴 << ====================
	 	1. 학생점수입력	2.모든학생성적출력	3.특정학생성적출력	4. 종료
	 	==================================================
	 	▣ 메뉴번호선택 : 2
	 	
	 	====>> 모든학생성적출력 <<====
	 	--------------------------------------------
	 	학번	성명		국어	영어	수학	총점	평균		학점	등수
	 	--------------------------------------------
	 	001	이순신	90	100	80	270	90.0	A	2
	 	002	엄정화	100	100	100	300	100.0	A	1
	 	003	차은우	80	80	80	240	80.0	B	3
	 	--------------------------------------------
	 	=================== >> 메뉴 << ====================
	 	1. 학생점수입력	2.모든학생성적출력	3.특정학생성적출력	4. 종료
	 	==================================================
	 	▣ 메뉴번호선택 : 3
	 	>> 조회할 학번 : 004
	 	>> [경고] 학번 004는 존재하지 않습니다
	 	
	 	=================== >> 메뉴 << ====================
	 	1. 학생점수입력	2.모든학생성적출력	3.특정학생성적출력	4. 종료
	 	==================================================
	 	▣ 메뉴번호선택 : 3
	 	>> 조회할 학번 : 002
	 	
	 	--------------------------------------------
	 	학번	성명		국어	영어	수학	총점	평균		학점	등수
	 	--------------------------------------------
	 	002	엄정화	100	100	100	300	100.0	A	1
	 	--------------------------------------------
	 	
	 	=================== >> 메뉴 << ====================
	 	1. 학생점수입력	2.모든학생성적출력	3.특정학생성적출력	4. 종료
	 	==================================================
	 	▣ 메뉴번호선택 : 4
	 	
		
	 	>> 프로그램 종료!!! <<
	 */
	
	public static void print_menu() {
		System.out.println("===================== >> 메뉴 << ======================");
		System.out.println("1. 학생점수입력\t2.모든학생성적출력\t3.특정학생성적출력\t4.종료");
		System.out.println("=====================================================");
	}
	//-----------print_menu 메서드
	
	
	
	
	
	
	public static void main(String[] args) {
		Student[] students = new Student[3];
		
		Scanner sc = new Scanner(System.in);
		
		
		int menu_num = 0;
		int numberOfStuendts = 0;
				
		for (int i = 0; i<students.length; i++) {
			students[i] = new Student();
		}
	 	
		//-------------------------while 시작------------------------
		final_while:
		while(true) {
			
			print_menu();
			menu_num = Integer.parseInt(sc.nextLine());
			String input_str = "";
			
			switch (menu_num) {
			case 1:
				if(numberOfStuendts < 3) {
					System.out.println(">> 학생점수입력 <<");
					
					
					System.out.print("1)학번 : ");
					 input_str= sc.nextLine();
					 for (int i = 0; i < numberOfStuendts; i++) {
							if (students[i].student_num.equals(input_str)) {
								System.out.println(input_str +"는 이미 사용중인 학번 이므로 새로운 학번값을 넣으세요");
								continue final_while;
							}
						}
					 students[numberOfStuendts].student_num = input_str;					
					System.out.print("2)이름 : ");
					 input_str= sc.nextLine();
					 students[numberOfStuendts].name = input_str;
					
					
					while(true) {
						int kor = 0;
						System.out.print("3)국어 : ");
						kor = Integer.parseInt(sc.nextLine());
						if(Student.checkScore(kor)) {
							students[numberOfStuendts].kor = kor;
							break;
						}
						else {
							System.out.println(">> [경고] 점수는 0~100만 가능합니다.");
						}
					}//-----------------국어 while---------------------
					
					while(true) {
						int eng = 0;
						System.out.print("4)영어 : ");
						eng = Integer.parseInt(sc.nextLine());
						if(Student.checkScore(eng)) {
							students[numberOfStuendts].eng = eng;
							break;
						}
						else {
							System.out.println(">> [경고] 점수는 0~100만 가능합니다.");
						}
					}//-----------------영어 while---------------------
					
					while(true) {
						int math = 0;
						System.out.print("4)수학 : ");
						math = Integer.parseInt(sc.nextLine());
						if(Student.checkScore(math)) {
							students[numberOfStuendts].math = math;
							break;
						}
						else {
							System.out.println(">> [경고] 점수는 0~100만 가능합니다.");
						}
					}//-----------------수학 while---------------------
					students[numberOfStuendts].set_totalnum();
					students[numberOfStuendts].set_avgnum();
					students[numberOfStuendts].setGrade();
					
					
					
					System.out.println("== 학생점수입력 완료 ==\n\n");
					numberOfStuendts++;
				}
				else {
					System.out.println(">> 정원(3명) 마감되어 더이상 학생점수입력이 불가합니다.\n\n");
					continue final_while;
				}
				break;
				
				
			case 2:
				if (numberOfStuendts == 0)
					System.out.println(">> 학생이 없습니다. <<\n");
				else  {
					for(int i = 0; i < students.length; i++) {
						int rank = 1;
						
						for (int j = 0; j < students.length; j++) {
							if(i != j && students[i].total_num < students[j].total_num) {
								//total_arr[i] 이 자신의 총점
								//total_arr[j] 이 다른사람의 총점
								rank++;
							}
						}
						students[i].rank = rank;
						//System.out.println(result_arr[i]+rank);
					}
					System.out.println("\n=====================================================\n"
									 + "학번\t성명\t국어\t영어\t수학\t총점\t평균\t학점\t등수 \n"
									 + "=====================================================\n");
					for(int i=0; i<numberOfStuendts; i++) {
						System.out.println(students[i].view_info());
					}
					System.out.println("\n");//-----------------end of for
				}
				break;
				
				
			case 3:
				if (numberOfStuendts == 0) {
					
					System.out.println(">>입력된 학생이 아무도 업습니다.<< \n");
				}
				else {
					System.out.print("조회할 학번 : ");

					boolean check = true;

					String num = sc.nextLine();
					for (int i = 0; i < numberOfStuendts; i++) {
						if (students[i].student_num.equals(num)) {
							int my_total = students[i].total_num;
							int rank = 1;
							for (int j = 0; j < numberOfStuendts;j++) {
								if (i != j && my_total < students[j].total_num) {
									// total_arr[i] 이 자신의 총점
									// total_arr[j] 이 다른사람의 총점
									rank++;
								}
							}

								students[i].rank = rank;
								System.out.println("\n=====================================================\n"
										+ "학번\t성명\t국어\t영어\t수학\t총점\t평균\t학점\t등수 \n"
										+ "=====================================================\n");
								System.out.println(students[i].view_info() + "\n");
								check = false;
								break;
							}
						}
						if (check) {
							System.out.println("[경고] 학번 " + num + "는 존재하지 않습니다\n\n");
						}
					}
				break;
				
				
			case 4:
				sc.close();
				break final_while;
				
				
			default:
				System.out.println(">>[경고]메뉴에 없는 번호입니다.\n\n\n");
				break;
			}
		}//-----------------------------큰 while문 종료 ----------------------
		
		
		System.out.println(">> 프로그램 종료!!! <<");
		
	}//--------------------end of main-------------------------

	
	
//	public static void main(String[] args) {
//		 
//		Scanner sc = new Scanner(System.in);
//		String str_menu = "";
//		Student[] arr_student = new Student[3];
//		int cnt_student = 0;
//		
//		
//		do {
//			System.out.println("===================== >> 메뉴 << ======================");
//			System.out.println("1. 학생점수입력\t2.모든학생성적출력\t3.특정학생성적출력\t4.종료");
//			System.out.println("=====================================================");
//			
//			str_menu = sc.nextLine(); 
//			
//			int kor = 0, eng = 0, math = 0;
//			
//			switch (str_menu) {
//			case "1":
//				if (cnt_student < arr_student.length) {
//					System.out.println(">> 학생점수입력 <<");
//					System.out.println("1)학번 : ");
//					String hakbun = sc.nextLine();
//
//					System.out.println("2)이름 : ");
//					String name = sc.nextLine();
//
//					while (true) {
//						try {
//							System.out.println("3)국어 : ");
//							String str_kor = sc.nextLine();
//							kor = Integer.parseInt(str_kor);
//							if (!(0 <= kor && kor <= 100)) {
//								System.out.println(">>[경고] 점수는 0~100 만 가능합니다.");
//
//							} else
//								break;
//						} catch (java.lang.NumberFormatException e) {
//							System.out.println(">>[경고] 점수는 0~100 만 가능합니다.");
//						}
//
//					}
//					while (true) {
//						try {
//							System.out.println("4)영어 : ");
//							String str_eng = sc.nextLine();
//							eng = Integer.parseInt(str_eng);
//							if (!(0 <= eng && eng <= 100)) {
//								System.out.println(">>[경고] 점수는 0~100 만 가능합니다.");
//
//							} else
//								break;
//						} catch (java.lang.NumberFormatException e) {
//							System.out.println(">>[경고] 점수는 0~100 만 가능합니다.");
//						}
//
//					}
//					while (true) {
//						try {
//							System.out.println("5)수학 : ");
//							String str_math = sc.nextLine();
//							math = Integer.parseInt(str_math);
//							if (!(0 <= math && math <= 100)) {
//								System.out.println(">>[경고] 점수는 0~100 만 가능합니다.");
//
//							} else
//								break;
//						} catch (java.lang.NumberFormatException e) {
//							System.out.println(">>[경고] 점수는 0~100 만 가능합니다.");
//						}
//
//					}
//
//					Student st = new Student();
//					st.student_num = hakbun;
//					st.name = name;
//					st.kor = kor;
//					st.eng = eng;
//					st.math = math;
//					arr_student[cnt_student].set_totalnum();
//					arr_student[cnt_student].set_avgnum();
//					arr_student[cnt_student].setGrade();
//
//					arr_student[cnt_student++] = st;
//
//					System.out.println("==학생점수입력 완료 ==");
//				}
//				else {
//					System.out.println(">> 정원(3명) 마감되어 더이상 학생점수입력이 불가합니다.\n\n");
//				}
//
//				break;
//			case "2":
//				if (cnt_student == 0)
//					System.out.println(">> 학생이 없습니다. <<\n");
//				else  {
//					for(int i = 0; i < arr_student.length; i++) {
//						int rank = 1;
//						
//						for (int j = 0; j < arr_student.length; j++) {
//							if(i != j && arr_student[i].total_num < arr_student[j].total_num) {
//								//total_arr[i] 이 자신의 총점
//								//total_arr[j] 이 다른사람의 총점
//								rank++;
//							}
//						}
//						arr_student[i].rank = rank;
//						//System.out.println(result_arr[i]+rank);
//					}
//					System.out.println("\n=====================================================\n"
//									 + "학번\t성명\t국어\t영어\t수학\t총점\t평균\t학점\t등수 \n"
//									 + "=====================================================\n");
//					for(int i=0; i<cnt_student; i++) {
//						System.out.println(arr_student[i].view_info());
//					}
//					System.out.println("\n");//-----------------end of for
//				}
//				break;
//			case "3":
//
//				break;
//			case "4":
//
//				break;
//
//			default:
//				System.out.println(">>[경고]메뉴에 없는 번호입니다.\n\\n\\n");
//				break;
//			}
//
//		} while (!"4".equals(str_menu));
//
//		sc.close();
//		System.out.println(">> 프로그램 종료!!! <<");
//
//	}
}
