package my.day10.a.string;

import java.text.DecimalFormat;

import my.util.MyUtil;

public class Main_String {

	public static void main(String[] args) {
		
		// === 1. "문자열".charAt(int index) ===
		// "안녕하세요".charAt(2) ==> '하'
		// index => 01234

		char ch = "안녕하세요".charAt(2);
		System.out.println("ch => " + ch);
		// ch => 하

		String str = "안녕하세요";
		// index => 01234
		String result = "";
		
		for(int i = str.length()-1; i>=0; i--) {
			result += str.charAt(i);
		}// end of for ------------------------------
		System.out.println(result);
		
		// === 2. "문자열".toCharArray() ===
		// "안녕하세요".toCharArray() ==> char 타입의 배열로 만들어준다.
		// ------------------------
		// |'안'|'녕'|'하'|'세'|'요'|
		// ------------------------
		// 0 1 2 3 4 <== index
		
		char[] ch_arr = str.toCharArray();
		result = "";
		for(int i = ch_arr.length-1; i>=0; i--) {
			result += ch_arr[i];
		}
		
		System.out.println(result);
		
		// === 3. "문자열".substring(int 시작인덱스, int 끝인덱스) ===
		// "안녕하세요".substring(1, 4);
		// ==> "안녕하세요" 에서 시작인덱스가 1인 글자("녕") 부터
		// "안녕하세요" 에서 끝인덱스 4인 글자("요") 앞(인덱스 3)에까지("세")만 뽑아온다.
		str = "안녕하세요".substring(1, 4);
		System.out.println(str);
		
		
		str = "안녕하세요 오늘도 행복하시고 좋은 하루 되세요~~^^";
		str = str.substring(2,str.length());

		System.out.println(str);
		
		// === 4. "문자열".substring(int 시작인덱스) ===
		// "안녕하세요".substring(2);
		// ==> "안녕하세요" 에서 시작인덱스가 2인 글자("하") 부터 끝까지 뽑아온다.
		str = "안녕하세요".substring(2);
		System.out.println(str);

		
		// === 5. "문자열".indexOf("찾을문자열") ====
		// "문자열" 에서 최초로 나오는 "찾을문자열"의 인덱스(int)를 알려준다.
		
		int index = "시작하라 안녕하세요 건강하세요".indexOf("하");
		
		System.out.println(index);
		index = "시작하라 안녕하세요 건강하세요".indexOf("하세요");

		System.out.println(index);
		
		index = "시작하라 안녕하세요 건강하세요".indexOf("A");

		System.out.println(index);//-1 찾고자 하는 문자열이 없으면 -1이 나온다
		
		// ==== [퀴즈] ==== //
		System.out.println("\n~~~~~~~~~~~~~~~~~~~\n");

	    String[] pathFileNameArr = {"C:/mydocument/resume/나의이력서.hwp",
	                                "D:/mymusic.mp3",
	                                "C:/photo/내얼굴.jpg"};
		
	    for(int i = 0; i < pathFileNameArr.length; i++) {
	    	System.out.println(pathFileNameArr[i]);
	    }//----------------end of for------------------------
		
		//substring
	    //indexOf();
	    System.out.println("\n=== 파일명만 뽑아온 결과물 ==");
	    for(int i = 0; i < pathFileNameArr.length; i++) {
	    	char[] n = pathFileNameArr[i].toCharArray();
	    	index = 0;
	    	for (int j = n.length-1; j>= 0; j--) {
	    		if(n[j] == '/') {
	    			index = j;
	    			break;
	    		}
	    	}
	    	System.out.println(pathFileNameArr[i].substring(index+1));
	    	
	    	//str = pathFileNameArr[i];
//	    	
//	    	while(true) {
//		    	index = str.indexOf("/");
//		    	if(index == -1)
//		    		break;
//		    	str = str.substring(index+1);
//	    	}
//	    	
//	    	System.out.println(str);

	    	
	    	// === 6. "문자열".lastIndexOf("찾을문자열") ====
			// "문자열" 에서 마지막으로 나오는 "찾을문자열"의 인덱스(int)를 알려준다.

	    	
	    }
	    index = "시작하라 안녕하세요 건강하세요".lastIndexOf("하");
		
		System.out.println(index);
		index = "시작하라 안녕하세요 건강하세요".lastIndexOf("하세요");

		System.out.println(index);
		
		index = "시작하라 안녕하세요 건강하세요".lastIndexOf("A");
		System.out.println(index);
		
		// -- 퀴즈 --
		System.out.println("\n~~~~~~~~~~~~~~~~~~~\n");
		/*
	    String[] pathFileNameArr = {"C:/mydocument/resume/나의이력서.hwp",
	                                "D:/mymusic.mp3",
	                                "C:/photo/내얼굴.jpg"};
		*/
		for(int i =0; i<pathFileNameArr.length; i++) {
			index = pathFileNameArr[i].lastIndexOf("/");
			System.out.println(pathFileNameArr[i].substring(index + 1));
			// pathFileNameArr[i].substring(idx+1) 은 마지막으로 나오는 "/" 의 인덱스 그 다음부터 끝까지 뽑아낸다.
		}
		
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		String money_1 = "$2,$000$,0$0$0";
		String money_2 = "$500,000";
		
		long sum_money = MyUtil.delete_charactor(money_1) + MyUtil.delete_charactor(money_2);
		
		//System.out.println(sum_money);
		//2500000
		
		System.out.println("$" + MyUtil.append_comma(sum_money));
		
		
		/*
		 * 			 5		5			글자길이 1글자(3의배수아님)	콤마의 개수는 1/3 ==> 0
		 * 	   		50		50			글자길이 2글자(3의배수아님)	콤마의 개수는 2/3 ==> 0
		 * 	   	   500		500			글자길이 3글자(3의배수임)	콤마의 개수는 3/3 ==> 1 - 1 ==> 0
		 * 	   500,000		500000		글자길이 6글자(3의배수임)	콤마의 개수는 6/3 ==> 2 - 1 ==> 1
		 * 	 2,500,000		2500000		글자길이 7글자(3의배수아님)	콤마의 개수는 7/3 ==> 2 - 1 ==> 2
		 * 892,500,000		892500000	글자길이 9글자(3의배수임)	콤마의 개수는 9/3 ==> 3 - 1 ==> 2
		 */
		
		//$2,500,000
		
		
		System.out.println("\n====================================\n");

		money_1 = "$2,$000$,0$0$0";
		money_2 = "$500,000";

		sum_money = MyUtil.delete_charactor(money_1) + MyUtil.delete_charactor(money_2);

		// System.out.println(sum_money);
		// 2500000
		// 숫자를 3자리 마다 콤마(,)를 찍어주는 것이다.
		DecimalFormat df = new DecimalFormat("#,###");
		System.out.println("$" + df.format(sum_money));
		
		// === 7. "문자열".split("구분자") ====
		// "문자열"을 "구분자"로 잘라서 String 타입의 배열로 돌려주는 것이다.
		System.out.println("\n 1.================================= \n");

		String food = "제육볶음,볶음밥,닭가슴살,함박스테이크,소고기덮밥";
		
		String[] food_arr = food.split(",");
		
		for (int i = 0; i < food_arr.length; i++) {
			System.out.println((i + 1) + "." + food_arr[i]);
		}// end of for--------------
		
		System.out.println("\n========================\n");
		
		for(String fd : food_arr) {
			System.out.println(fd);
		}
		
		
		System.out.println("\n 2.================================= \n");

		food = "제육볶음	볶음밥	닭가슴살	함박스테이크	소고기덮밥";
		
		food_arr = food.split("\t");
		
		for (int i = 0; i < food_arr.length; i++) {
			System.out.println((i + 1) + "." + food_arr[i]);
		}// end of for--------------
		
		
		System.out.println("\n 3.=== split 사용시 구분자로 . | / 등 특수문자를 사용하려고 하는 경우 === \n");
	       
	       food = "제육볶음.볶음밥.닭가슴살.함박스테이크.소고기덮밥";
	       
	       food_arr = food.split(".");
	       System.out.println("food_arr.length => " +food_arr.length);

			// food_arr.length => 0
			// ==> 0 이 나옴. 즉 . 단독만으로는 구분자로 인식을 못함.
			// split 사용시 구분자로 . | / 등 특수문자를 사용하려고 할 경우에는 구분자로 인식을 못할 경우가 많으므로
			// 구분자 앞에 \\ 를 붙이거나 구분자를 [] 로 씌워주면 된다.
			// 즉, \\구분자 이거나 [구분자] 이렇게 해야 한다.
		
	       
	       food_arr = food.split("\\.");
	       System.out.println("food_arr.length => " +food_arr.length);
	       
	       for (int i = 0; i < food_arr.length; i++) {
				System.out.println((i + 1) + "." + food_arr[i]);
			}// end of for--------------
	       // 또는
	       
	       food_arr = food.split("[.]");
	       System.out.println("food_arr.length => " +food_arr.length);
	       
	       for (int i = 0; i < food_arr.length; i++) {
				System.out.println((i + 1) + "." + food_arr[i]);
			}// end of for--------------
	       
	       
	       
	       System.out.println("\n 4.=== split 사용시 구분자로 . | / 등 특수문자를 사용하려고 하는 경우 === \n");

			food = "제육볶음,볶음밥.닭가슴살|함박스테이크,소고기덮밥	라면";

			food_arr = food.split("\\,|\\.|\\||\\\t");
			for (int i = 0; i < food_arr.length; i++) {
				System.out.println((i + 1) + "." + food_arr[i]);
			} // end of for--------------

			System.out.println("\n 5.=== split 사용시 구분자로 . | / 등 특수문자를 사용하려고 하는 경우 === \n");

			food = "제육볶음,볶음밥.닭가슴살|함박스테이크,소고기덮밥	라면";

			food_arr = food.split("[,.|\t]");
			for (int i = 0; i < food_arr.length; i++) {
				System.out.println((i + 1) + "." + food_arr[i]);

				System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

				// === [참고] \ 를 escape 문자라고 부른다. ===
				System.out.println("나의 이름은 \"이순신\" 입니다.");
				// 나의 이름은 "이순신" 입니다.

				System.out.println("C:\\NCS\\workspace_java");
				// C:\NCS\workspace_java
			}
			
			// === 8. String.join("합칠구분자", 문자열배열명) ====
			// 문자열배열을 "합칠구분자"로 합쳐서 String 타입으로 돌려주는 것이다.
			String[] name_arr = { "한석규", "두석규", "세석규", "네석규", "오석규" };
			String names = String.join("-", name_arr);
			System.out.println(names);
			
			String str_1 = "시작하세요 안녕하세요 건강하세요";
			str_1 = String.join("", str_1.split("하세요")); //{"시작", " 안녕", " 건강"}
			System.out.println(str_1);
		      
			
			
			String str_2 = "    행복    하세요 안녕하     세요 건강하세     요";
			str_2= String.join("", str_2.split("\\ "));
			System.out.println("시작" + str_2 + "끝");
			
			
			String bank_book_no = "103-23-523-009";
			System.out.println(String.join("", bank_book_no.split("\\-")));
			
			String money_3 = "$2,000,000";
			String money_4 = "$500,000";
			
			long sum_dollar = Long.parseLong(String.join("", money_3.split("[,$]"))) + Long.parseLong(String.join("", money_4.split("[,$]")));
			
			System.out.println("$" + df.format(sum_dollar));
			
			
			// === 9. "문자열".replaceAll("변경대상문자열", "새로이변경될문자열") ====
			//     "문자열" 에서 "변경대상문자열" 을 모두 "새로이변경될문자열" 로 교체해서 반환.
			
			names = names.replaceAll("석규", "SK");
			System.out.println(names);
			// === 10. "문자열".replaceFirst("변경대상문자열", "새로이변경될문자열") ====
			// "문자열" 에서 "변경대상문자열" 을 첫번째만 "새로이변경될문자열" 로 교체해서 반환.
			
			names = names.replaceFirst("SK", "석규");
			System.out.println(names);
			
			names = "한SK-두SK-세SK-네SK-오SK";
			//replaceFirst 를 사용하여 첫번쨰, 두번째, 세번째 "SK"를 "석규"로 변환하세요;
			for(int i = 0; i<3; i++) {
				names = names.replaceFirst("SK", "석규");
			}
			System.out.println(names);
			names = "한SK-두SK-세SK-네SK-오SK";
		    // 홀수번째 나오는 것만 "SK" 를 "석규"로 변환하세요.
			String[] names_str = names.split("\\-");
			for(int i = 0; i < names_str.length; i++) {
				if(i%2 == 0) {
					names_str[i] = names_str[i].replaceAll("SK", "석규");
				}
			}
			names = String.join("-", names_str);
			System.out.println(names);
			
			System.out.println("\n ===== 퀴즈 ===== \n");

			String[] contents = { "호호안녕하세요", "건강하세요", "행복하세요 또봐요", "즐겁고 건강한 하루되세요" };

			// "건강" 이라는 단어가 포함된것을 출력하세요.
			
			for(int i =0; i<contents.length; i++) {
				if(contents[i].indexOf("건강") != -1) {
					System.out.println(contents[i]);
				}
			}
			
			
			System.out.println("=================\n");
		       // "건강" 이라는 단어로 시작하는 것만 출력하세요.
			for(int i =0; i<contents.length; i++) {
				if(contents[i].indexOf("건강") == 0) {
					System.out.println(contents[i]);
				}
			}
			
			System.out.println("=================");

			String strr = "안녕하세요 javajava를 배우는 중입니다. Java is Programming Language!! JAVA를 배우는 중입니다.";
			// strr 에서 대.소문자를 구분치 않고 java 라는 글자가 몇번 나오는지 그 회수를 구하세요
			
			
			// strr 의 글자를 모두 소문자로 변경하겠습니다.
			char[] chr_arr = strr.toCharArray();
			for(int i = 0; i<chr_arr.length; i++) {
				if('A' <= chr_arr[i] && chr_arr[i] <= 'Z') {
					chr_arr[i] = (char)(chr_arr[i]+32);
				}
			}
			strr = String.valueOf(chr_arr);
			
			int cnt = (strr.indexOf("java") != -1)? strr.split("java").length-1:0;
			// strr.split("java") 은 {"안녕하세요 ","","를 배우는 중입니다. "," is programming language!! ","를 배우는 중입니다."}
			
			
			System.out.println("대소문자 구분치 않고 java의 개수 : " + cnt + "개");
			System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
			 
			
			// === 11. "문자열".startsWith("찾고자하는문자열") ===
			// "문자열" 에서 "찾고자하는문자열"이 맨첫번째에 나오면 true 를 반환.
			// "문자열" 에서 "찾고자하는문자열"이 맨첫번째에 나오지 않으면 false 를 반환.

			for (int i = 0; i < contents.length; i++) {
				if (contents[i].startsWith("건강")) {
					System.out.println(contents[i]);
				}
			}

			System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

			// === 12. "문자열".endsWith("찾고자하는문자열") ===
			// "문자열" 에서 "찾고자하는문자열"로 끝나면 true 를 반환.
			// "문자열" 에서 "찾고자하는문자열"로 끝나지 않으면 false 를 반환.
			
			//하세요라는 단어로 끝나는 것만 출력하세요
			for (int i = 0; i < contents.length; i++) {
				if (contents[i].endsWith("하세요")) {
					System.out.println(contents[i]);
				}
			}
			
			// === 13. "문자열".trim() ===
			// "문자열".trim() 은 "문자열"의 좌,우에 공백이 있으면 공백을 모두 제거하고서 반환.
			String insa = "          수고        많으셨습니다                ";

			System.out.println("하하하" + insa + "내일 뵐께요~~");
			// 하하하 수고 많으셨습니다 내일 뵐께요~~

			System.out.println("하하하" + insa.trim() + "내일 뵐께요~~");
			// 하하하수고 많으셨습니다내일 뵐께요~~

			// === 14. "문자열".isEmpty() ===
			// "문자열" 이 아무것도 없으면 true 를 반환해주고,
			// "문자열" 이 뭔가 있으면 false 를 반환해준다.
			String str1 = "", str2 = "abc", str3 = "        ";

			System.out.println(str1.isEmpty()); // true
			System.out.println(str2.isEmpty()); // false
			System.out.println(str3.isEmpty()); // false
			System.out.println(str3.trim().isEmpty()); // true
			// "".isEmpty();

			// === 15. "문자열".toUpperCase() ===
			// "문자열"에서 소문자가 있으면 모두 대문자로 변경해서 반환.
			String words = "My Name is Tom 입니다.";
			System.out.println(words.toUpperCase());
			// MY NAME IS TOM 입니다.

			// === 16. "문자열".toLowerCase() ===
			// "문자열"에서 대문자가 있으면 모두 소문자로 변경해서 반환.
			words = "My Name is Tom 입니다.";
			System.out.println(words.toLowerCase());
			// my name is tom 입니다.
			
			
			
			System.out.println("=================");

			String strr2 = "안녕하세요 javajava를 배우는 중입니다. Java is Programming Language!! JAVA를 배우는 중입니다.";
			// strr 에서 대.소문자를 구분치 않고 java 라는 글자가 몇번 나오는지 그 회수를 구하세요
			
			
			// strr 의 글자를 모두 소문자로 변경하겠습니다.
			strr2 = strr2.toLowerCase();
			strr2 = String.valueOf(chr_arr);
			
			//int cnt2 = (strr.indexOf("java") != -1)? strr.split("java").length-1:0;
			// strr.split("java") 은 {"안녕하세요 ","","를 배우는 중입니다. "," is programming language!! ","를 배우는 중입니다."}
			
			
			System.out.println("대소문자 구분치 않고 java의 개수 : " + cnt + "개");
			System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
			
			// === 17. "문자열".contains("찾을문자열") ===
			// "문자열" 에서 "찾을문자열" 을 포함하고 있으면 true 를 반환
			// "문자열" 에서 "찾을문자열" 을 포함하고 있지 않으면 false 를 반환
			System.out.println("안녕하세요 JaVa입니다.".contains("java")); // false
			System.out.println("안녕하세요 JaVa입니다.".toLowerCase().contains("java")); // true
			
			String[] contents2 = { "호호안녕하세요", "건강하세요", "행복하세요 또봐요", "즐겁고 건강한 하루되세요" };

			// "건강" 이라는 단어가 포함된것을 출력하세요.

			for (int i = 0; i < contents2.length; i++) {
				if (contents[i].contains("건강")) {
					System.out.println(contents2[i]);
				}
			}

			// === 18. "문자열".equals("비교대상문자열") ===
			// 대문자와 소문자를 구분하면서
			// "문자열" 과 "비교대상문자열" 의 값이 일치하면 true 를 반환.
			// "문자열" 과 "비교대상문자열" 의 값이 일치하지 않으면 false 를 반환.

			System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

			// === 19. "문자열".equalsIgnoreCase("비교대상문자열") ===
			// 대문자와 소문자를 구분하지 않으면서
			// "문자열" 과 "비교대상문자열" 의 값이 대,소문자와 관계없이 일치하면 true 를 반환.
			// "문자열" 과 "비교대상문자열" 의 값이 대,소문자와 관계없이 일치하지 않으면 false 를 반환.
			
			String[] strArr = {"korea", "kORea", "    kOReA   ","    kor ea   " ,  "seoul", " korea seoul", " KOREA seoul", "Korea 대한민국", "서울 kOrEA 만세", null};

			//strArr 에서 대소문자 구분치 않고 "korea" 라는 글자만 있은 것을 출력하세요
			for (String s : strArr) {
				//if (s.equalsIgnoreCase("korea"))
				//	System.out.println(s);
				
				//if ("korea".equalsIgnoreCase(s.trim()))
				//	System.out.println(s);
				
				//if (s != null && s.trim().equalsIgnoreCase("korea"))
				//	System.out.println(s);
				
				//if(s !=null)
				//System.out.println(s.join("", s.split(" ")));
				if(s != null && String.join("", s.split(" ")).equalsIgnoreCase("korea"))
					System.out.println(s);
			}
			
			
			System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
			//strArr 에서 대소문자 구분치 않고 "korea" 라는 글자가 들어있는 것을 출력하세요
			for (String s : strArr) {
				if(s != null && s.toLowerCase().contains("korea"))
					System.out.println(s);
			}
			
			System.out.println("\n-------------------------------------------\n");
			
			for (String s : strArr) {
				if(s != null && (String.join("", s.split(" ")).toLowerCase().indexOf("korea") !=-1))
					System.out.println(s);
			}
			
			
			System.out.println("\n-------------------------------------------\n");

			for (String s : strArr) {
				if (s != null && (String.join("", s.split(" ")).toLowerCase().indexOf("korea") != -1))
					System.out.println(s);
			}
			

			System.out.println("\n-------------------------------------------\n");
			
			//String search ="korea";
			//String search ="KOREA";
			//String search ="KoREa";
			//String search ="  Korea  ";
			//String search ="  KOREA  ";
			String search ="  kor   ea   ";
			//String search ="  KoR   EA";
			
			search = String.join("", search.split(" ")).toLowerCase();
			
			System.out.println(search);
			
			System.out.println("\n-------------------------------------------\n");

			for (String s : strArr) {
				if(s != null) {
					if(String.join("", s.split(" ")).toLowerCase().contains(search)) {
						System.out.println(s);
					}
				}
			}
			
			
			System.out.println("\n#######################################\n");
		     
		     // ==== JDK 11 부터 새로이 추가된 String 관련 메소드 (JDK 1.8 에서는 사용이 불가함) ==== //
		      
		     // isBlank: 문자열이 비어있거나 공백이면 true 반환
		     System.out.println("".isBlank()); // true
		     System.out.println("".isEmpty()); // true
		   
		     System.out.println("         ".isEmpty()); // false
		     System.out.println("         ".isBlank()); // true
		     System.out.println("         ".trim().isEmpty()); // true
		   
		     System.out.println(" 안녕 ".isBlank()); // false
		     System.out.println(" 안녕 ".trim().isEmpty()); // false
		   
		     System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

				// strip: 문자열 공백 제거
				// stripLeading: 문자열 앞의 공백을 제거
				// stripTrailing: 문자열 뒤의 공백을 제거
		     
		     System.out.println("시작" + "        ja  va       ".strip() +"끝");
		     // 시작ja  va끝
		         
		     System.out.println("시작" + "        ja  va       ".stripLeading() +"끝");
		     // 시작ja  va       끝
		         
		     System.out.println("시작" + "        ja  va       ".stripTrailing() +"끝");
		     // 시작        ja  va끝
		     
		     // reoeat : 문자열을 파라미터로 주어진 수 만큼 반복
		     System.out.println("\n" + "~".repeat(50) + "\n");
		     System.out.println("안녕 ".repeat(5));
		     
		     
		     System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		      
		     // lines: 줄 단위로 나뉘어 있는 문자를 배열로 반환
		     Object[] arr_obj = "id:leess\npwd:qwer1234$\n성명:이순신\n주소:경기도 군포시".lines().toArray();
		     for(Object obj : arr_obj) {
		        System.out.println((String)obj);
		     }// end of for------------------
		     /*
		      id:leess
		      pwd:qwer1234$
		      성명:이순신
		      주소:경기도 군포시 
		     */
		     
		      System.out.println("------------------");
		      
		      String[] arr_str = "id:leess\npwd:qwer1234$\n성명:이순신\n주소:경기도 군포시".split("\n");
		      for(String info : arr_str) {
		         System.out.println(info);
		      }// end of for------------------
		      /*
		       id:leess
		       pwd:qwer1234$
		       성명:이순신
		       주소:경기도 군포시 
		      */


		      System.out.println("------------------");
		      
		      // === JDK 17 부터 새로이 추가된 String 관련 내용들 === //
		      /* 텍스트 블록은 3개의 큰 따옴표로 정의되며, 아래와 같이 작성할 수 있다. 
		         이것은 기존에 JSON 문자열을 직접 생성할 때 이스케이프 처리(\) 로 가독성이 떨어지던 문제를 
		         텍스트 블록을 통해 개선하게 되었다. */
		      
		      
		      String msg1 = "안녕하세요\n" +
		    		  		"오늘은 수요일 입니다\n" +
		    		  		"점심 맛있게 드세요";
		      
				System.out.println(msg1);

				String msg2 = """
						안녕하세요2
						오늘은 수요일 입니다2
						점심 맛있게 드세요2
						""";

				System.out.println(msg2);

				String old_json = "{\n" + "  \"name\": \"이순신\",\n" + "  \"age\": 27,\n"
						+ "  \"address\": \"서울시 마포구 월드컵북로 21\"\n" + "}";

				System.out.println(old_json);
		     /*
		        {
		        "name": "이순신",
		        "age": 27,
		        "address": "서울시 마포구 월드컵북로 21"
		     }
		     */
				System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~\n");

				String new_json = """
						{
						  "name": "이순신",
						  "age": 27,
						  "address": "서울시 마포구 월드컵북로 21"
						}
						""";

				System.out.println(new_json);
		     /*
		        {
		        "name": "이순신",
		        "age": 27,
		        "address": "서울시 마포구 월드컵북로 21"
		     }
		     */
	
		      
			} // -- end of main(String[] args)----------------------------------------
}
