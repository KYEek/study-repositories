package my.day18.c.collection;

import java.util.LinkedList;
import java.util.List;

	/*
		== LinkedList == 
		1. 출력시 저장된 순서대로 나온다.
		2. 중복된 데이터를 저장할 수 있다.
		3. 데이터를 읽어오는 속도는 LinkedList 가 ArrayList 보다 상대적으로 느리다.
		4. 순차적으로 데이터를 추가/삭제하는 경우에는 LinkedList 가  ArrayList 보다 상대적으로 느리다.
		5. 일반적인 데이터 추가/삭제는 데이터 중간 중간마다 발생하므로 이러한 경우에는 ArrayList 가 LinkedList 보다 상대적으로 빠르다.    
		6. 결과값은 ArrayList 를 사용하든지 LinkedList 를 사용하든지 동일한 결과값을 가진다.
		7. LinkedList 보다는 ArrayList 를 사용하도록 하자. 
		
		== LinkedList (저장소) ==   
		
		          유재석(자신의 메모리주소 1372)----(앞서존재하던객체의 메모리주소 1372)엄정화(자신의 메모리주소 3236)----(앞서존재하던객체의 메모리주소 3236)강호동(자신의 메모리주소 1034)----(앞서존재하던객체의 메모리주소 1034)이순신(자신의 메모리주소 2340)---(앞서존재하던객체의 메모리주소 2340)김태희(자신의 메모리주소 5236)   
		
		== 엄정화 를 삭제할 경우
		          유재석(자신의 메모리주소 1372)----(앞서존재하던객체의 메모리주소 1372)강호동(자신의 메모리주소 1034)----(앞서존재하던객체의 메모리주소 1034)이순신(자신의 메모리주소 2340)---(앞서존재하던객체의 메모리주소 2340)김태희(자신의 메모리주소 5236)
		    
		== 엄정화 를 특정 위치에 추가할 경우
		          엄정화(자신의 메모리주소 7876)를 유재석 다음에 추가하고자 한다.
		          유재석(자신의 메모리주소 1372)----(앞서존재하던객체의 메모리주소 1372)엄정화(자신의 메모리주소 7876)----(앞서존재하던객체의 메모리주소 7876)강호동(자신의 메모리주소 1034)----(앞서존재하던객체의 메모리주소 1034)이순신(자신의 메모리주소 2340)---(앞서존재하던객체의 메모리주소 2340)김태희(자신의 메모리주소 5236)
	*/

public class LinkedList_main_3 {

	public static void main(String[] args) {
		
		// Member 클래스의 객체만을 저장할 수 있는 LinkedList 객체 mbrList 를 생성한다.
		List<Member> mbrList = new LinkedList<>();
		
		
		// Member 클래스의 객체 7개를 생성하여 mbrList 에 저장한다.
		mbrList.add(new Member("youjs", "Qwer1234$", "유재석", "7209101"));
		mbrList.add(new Member("eom", "Qwer1234$", "엄정화", "6808152"));
		mbrList.add(new Member("kanghd", "Qwer1234$", "강호동", "7006151"));
		mbrList.add(new Member("leess", "Qwer1234$", "이순신", "0010203"));
		mbrList.add(new Member("kimth", "Qwer1234$", "김태희", "0105064"));
		mbrList.add(new Member("kangkc", "Qwer1234$", "강감찬", "9812301"));
		mbrList.add(new Member("kimss", "Qwer1234$", "김순신", "0203203"));

		
		//mbrList 에 저장된 회원들의 정보를 출력하도록 한다.
		for(int i = 0; i<mbrList.size(); i++) {
			System.out.println(mbrList.get(i));
		}// end of for-----------------------------------------
		
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~\n");
		
		for(Member mbr : mbrList) {
			System.out.println(mbr);
		}
		
		System.out.println("\n[퀴즈1] mbrList 에 저장되어진 모든 회원들중에 여자만 정보를 출력하세요 \n"); 
		boolean is_Exist = false;
		for(Member mbr : mbrList) {
			if(mbr.gender().equals("여")) {
				is_Exist = true;
				System.out.println(mbr);
			}
		}

		if(!is_Exist == true) {
			System.out.println("여자가 없습니다.");
		}
		
		
		
		
		System.out.println("\n[퀴즈2] mbrList 에 저장되어진 모든 회원들중에 성이 김씨인 회원만 정보를 출력하세요 \n");
		is_Exist= false;
		
//		첫번째 방법
//		for(Member mbr : mbrList) {
//			char[] name = mbr.getName().toCharArray();
//			if(name[0]== '김') {
//				is_Exist = true;
//				System.out.println(mbr);
//			}	
//		}
		
		
		for(Member mbr : mbrList) {
			if(mbr.getName().startsWith("김")) {
				is_Exist = true;
				System.out.println(mbr);
			}	
		}
		if(!is_Exist == true) {
			System.out.println("김씨가 없어요 말이돼?");
		}
		
		
		System.out.println("\n[퀴즈3] mbrList 에 저장되어진 모든 회원들중에 이름이 순신인 회원만 정보를 출력하세요 \n");
		is_Exist= false;
		for(Member mbr : mbrList) {
			if(mbr.getName().endsWith("순신")) {
				is_Exist = true;
				System.out.println(mbr);
			}	
		}
		if(!is_Exist == true) {
			System.out.println("순신이라는 이름은 없어요");
		}
		
		
		System.out.println("\n———————————————————————————————————————————\n");
		// *** LinkedList 타입인 mbrList 에 새로운 Member 객체 추가시
        //     특정 index(위치)에 들어가도록 할 수 있다. *** 
           
        System.out.println("\n ~~~ mbrList 에 새로운 Member 객체 추가하기 ~~~ \n");
        

		mbrList.add(new Member("seolh", "Qwer1234$", "설현", "9910122"));
		// index 값이 없으면 mbrList의 맨 뒤에 추가된다.
		System.out.println(mbrList.get(mbrList.size()-1) );
		
		System.out.println("\n——————————————————————————————————————————————\n");
		mbrList.add(3, new Member("seokj", "Qwer1234$", "서강준", "9910051"));
		//유재석(0)	엄정화(1)	강호동(2)	이순신(3) 으로 되어있었는데
		//유재석(0)	엄정화(1)	강호동(2)	서강준(3)	이순신(4) 으로 된다.
		
		for(Member mbr : mbrList) {
			System.out.println(mbr);
		}
		
		// **** LinkedList 타입인 mbrList 에 저장되어진 Member 객체 삭제하기 **** //
		System.out.println("\n——————————————————————————————————————————————\n");
        System.out.println("\n **** LinkedList 타입인 mbrList 에 저장되어진 Member 객체 삭제하기 **** \n");
		System.out.println(">> 삭제하기 전 mbrList.size() => " + mbrList.size());
		
		Member deleted_mbr = mbrList.remove(3);
		// mbrList.remove(삭제할 Member 객체의 인덱스번호);
		System.out.println(deleted_mbr);
		System.out.println(">> 삭제한 후 mbrList.size() => " + mbrList.size());

		System.out.println("\n——————————————————————————————————————————————\n");
		
		System.out.println("\n[퀴즈4] mbrList 에 저장되어진 모든 회원들중에 남자만 모두 삭제한 후 모든 회원의 정보를 출력하세요 \n");  
		
		
		// 방법 1
//		for (int i = 0; i < mbrList.size() ; i++) {
//			if (mbrList.get(i).gender().equals("남")) {
//				mbrList.remove(i);
//				i--;
//			}
//		}
//		for (Member mbr : mbrList) {
//			System.out.println(mbr);
//		}
		
		
		// 방법 2
		for (int i = mbrList.size()-1; i >= 0 ; i--) {
			if (mbrList.get(i).gender().equals("남")) {
				mbrList.remove(i);
			}
		}
		for (Member mbr : mbrList) {
			System.out.println(mbr);
		}
		
		System.out.println("현재 mbrList.size() = " + mbrList.size());
		
		
		System.out.println("\n === mbrList 에 저장된 모든 객체 삭제하기 === \n");
		mbrList.clear();
		System.out.println("모두 삭제한 후의 mbrList.size() = " + mbrList.size());
		
		// == 2. 중복된 데이터를 저장할 수 있다 == 
		mbrList.add(new Member("leemj", "Qwer1234$", "이미자", "9812301"));
		mbrList.add(new Member("leemj", "Qwer1234$", "이미자", "9812301"));
		
		Member mbr3 = new Member("sammj", "Qwer1234$", "삼미자", "8812302");
		Member mbr4 = mbr3;		//중복
		
		mbr4.setName("오미자");
		
		System.out.println(mbr3);
		System.out.println(mbr4);
		
		mbrList.add(mbr3);
		mbrList.add(mbr4);
		
		System.out.println("현재 mbrList.size() = " + mbrList.size());
		
	}

}
