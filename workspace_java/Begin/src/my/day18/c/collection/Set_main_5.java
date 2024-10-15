package my.day18.c.collection;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/*
	== HashSet == 
	1. 출력시 저장된 순서가 유지되지 않는다.
	2. 중복된 데이터를 저장할 수 없다.
	   그러므로 Collection 내의 중복된 요소들을 저장하지 않고자 할때 많이 사용된다.
	          
	== LinkedHashSet ==
	1. 출력시 저장된 순서가 유지된다.
	2. 중복된 데이터를 저장할 수 없다.
	   그러므로 Collection 내의 중복된 요소들을 저장하지 않고자 할때 많이 사용된다.        
*/

public class Set_main_5 {

	public static void main(String[] args) {
	
		System.out.println("\n === HashSet === \n");
		
		// Member 클래스의 객체만을 저장할 수 있는 HashSet 객체 mbr_set 을 생성한다. 
		Set<Member> mbr_set = new HashSet<>();
		
		// Member 클래스의 객체를 mbr_set 에 저장한다.
		mbr_set.add(new Member("youjs","Qwer1234$","유재석","7209101"));
		mbr_set.add(new Member("eom","Qwer1234$","엄정화","6808152"));
		mbr_set.add(new Member("kanghd","Qwer1234$","강호동","7006151"));
		mbr_set.add(new Member("leess","Qwer1234$","이순신","0010203"));
		mbr_set.add(new Member("kimth","Qwer1234$","김태희","0105064"));
		mbr_set.add(new Member("kimth","Qwer1234$","김태희","0105064"));
		
		Member mem1 = new Member("kwonjy","Qwer1234$","권지용","0303203");
		Member mem2 = mem1;
		mem2.setName("지드래곤");
		
		mbr_set.add(mem1); 
		mbr_set.add(mem2); // 중복된 데이터를 저장할 수 없다.!!!
		
		// mbr_set 에 저장되어진 모든 Member 객체들의 정보를 출력해본다.
		Iterator<Member> itr = mbr_set.iterator();
		
		while(itr.hasNext()) {
			// itr.hasNext() 은 
			// itr 에서 현재 itr가 가지고 있는 여러 Member 데이터중 
			// 하나를 끄집어내서 존재하는지를 알아보는 것이다.
			// 존재하면 true, 존재하지 않으면 false.
			
			Member mbr = itr.next();
			// 실제로 Member를 itr 에서 끄집어낸다.
			// 그러면 끄집어 낸 Member는 더이상 itr 에는 남아있지 않게 된다.
			
			System.out.println(mbr);
		}// end of while---------------------------
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~\n");
		
		
		// mbr_set 에 저장되어진 Member 객체들 중에서 id가 "kimth" 인 객체만 정보를 출력해본다.
		String id = "kimth";
		itr = mbr_set.iterator();
		boolean isExist = false; 
		while(itr.hasNext()) {
			Member mbr = itr.next();
			if(id.equals(mbr.getId())) {
				isExist = true;
				System.out.println(mbr);
			}
		}// end of while---------------------------
		
		if(!isExist) {
			System.out.println(">> 아이디가 "+id+" 인 회원은 없습니다.<<");
		}
		
		
		// mbr_set 에 저장되어진 Member 객체들 중에서 id 값이 "kimth" 인 객체를 삭제하세요 
		itr = mbr_set.iterator();
		
		while(itr.hasNext()) {
			Member mbr = itr.next();
			if("kimth".equals(mbr.getId())) {
				itr.remove();
				// mbr_set 에 저장된 요소(Element)를 삭제하려면 
				// Iterator를 이용한 itr.remove(); 을 사용하여 삭제한다.
			}
		}// end of while---------------------------
		
		System.out.println(">>> kimth 을 삭제한 후 모든 객체 정보 출력하기 <<<");
		
		itr = mbr_set.iterator();
		
		while(itr.hasNext()) {
			Member mbr = itr.next();
			System.out.println(mbr);
		}// end of while---------------------------
		
		
		////////////////////////////////////////////////////////
		
		System.out.println("\n === LinkedHashSet === \n");
		
		// Member 클래스의 객체만을 저장할 수 있는 LinkedHashSet 객체 mbr_set2 을 생성한다. 
		Set<Member> mbr_set2 = new LinkedHashSet<>();
		
		// Member 클래스의 객체를 mbr_set2 에 저장한다.
		mbr_set2.add(new Member("youjs","Qwer1234$","유재석","7209101"));
		mbr_set2.add(new Member("eom","Qwer1234$","엄정화","6808152"));
		mbr_set2.add(new Member("kanghd","Qwer1234$","강호동","7006151"));
		mbr_set2.add(new Member("leess","Qwer1234$","이순신","0010203"));
		mbr_set2.add(new Member("kimth","Qwer1234$","김태희","0105064"));
		mbr_set2.add(new Member("kimth","Qwer1234$","김태희","0105064"));
		
		Member mem3 = new Member("kwonjy","Qwer1234$","권지용","0303203");
		Member mem4 = mem3;
		mem4.setName("지드래곤");
		
		mbr_set.add(mem3); 
		mbr_set.add(mem4); // 중복된 데이터를 저장할 수 없다.!!!
		
		// mbr_set2 에 저장되어진 모든 Member 객체들의 정보를 출력해본다.
		Iterator<Member> itr2 = mbr_set2.iterator();
		
		while(itr2.hasNext()) {
			// itr2.hasNext() 은 
			// itr2 에서 현재 itr2가 가지고 있는 여러 Member 데이터중 
			// 하나를 끄집어내서 존재하는지를 알아보는 것이다.
			// 존재하면 true, 존재하지 않으면 false.
			
			Member mbr = itr2.next();
			// 실제로 Member를 itr2 에서 끄집어낸다.
			// 그러면 끄집어 낸 Member는 더이상 itr2 에는 남아있지 않게 된다.
			
			System.out.println(mbr);
		}// end of while---------------------------
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~\n");
		
		
		// mbr_set2 에 저장되어진 Member 객체들 중에서 id가 "kimth" 인 객체만 정보를 출력해본다.
		String id2 = "kimth";
		itr2 = mbr_set2.iterator();
		boolean isExist2 = false; 
		while(itr2.hasNext()) {
			Member mbr = itr2.next();
			if(id2.equals(mbr.getId())) {
				isExist2 = true;
				System.out.println(mbr);
			}
		}// end of while---------------------------
		
		if(!isExist2) {
			System.out.println(">> 아이디가 "+id2+" 인 회원은 없습니다.<<");
		}
		
		
		// mbr_set2 에 저장되어진 Member 객체들 중에서 id2 값이 "kimth" 인 객체를 삭제하세요 
		itr2 = mbr_set2.iterator();
		
		while(itr2.hasNext()) {
			Member mbr = itr2.next();
			if("kimth".equals(mbr.getId())) {
				itr2.remove();
				// mbr_set2 에 저장된 요소(Element)를 삭제하려면 
				// Iterator를 이용한 itr2.remove(); 을 사용하여 삭제한다.
			}
		}// end of while---------------------------
		
		System.out.println(">>> kimth 을 삭제한 후 모든 객체 정보 출력하기 <<<");
		
		itr2 = mbr_set2.iterator();
		
		while(itr2.hasNext()) {
			Member mbr = itr2.next();
			System.out.println(mbr);
		}// end of while---------------------------		

	}// end of main()---------------------------------

}
