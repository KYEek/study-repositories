package my.day05.b.For_quiz;

//for 문을 사용하여 알파벳 소문자를 아래처럼 출력하세요. 26개
// a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z

//13
public class Main_quiz_1 {

	public static void main(String[] args) {
		
		/* 내 대답
		char a = 'a';
		for (int i = 0; i <26; i++) {
			
			if(a < 'm') {
				System.out.print(a);
				System.out.print(",");
				a +=1;
			}
			else if(a =='m' || a == 'z') {
				System.out.print(a);
				System.out.println("");
				a +=1;
			}
			else if(a < 'z') {
				System.out.print(a);
				System.out.print(",");
				a +=1;
			}
			else if(i<='z'){
				System.out.print(a);		
				System.out.print(",");	
				a +=1;
			}
		}
		System.out.println("");
		a='a';
		for (int i = 0; i < 26; i++) {

			if (i < 25) {
				System.out.print(a);
				System.out.print(",");
				a += 1;
			}
			else {
				System.out.print(a);
			}
		}
		*/
		
		for (int i= 0; i<'z'- 'a'+1; i++) {
			String str = (i<'z'-'a')?",":"";
			System.out.print((char)('a'+i)+str);
		}
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		String result = "";
		
		for (int i= 0; i<'z'- 'a'+1; i++) {
			char ch = (char)('a' + i);
			
			String add = (i<'z' - 'a')? ",": "";
			result += ch + add;
		}

		System.out.print(result);
		
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

		result = "";

		for (int i = 0; i < 'z' - 'a' + 1; i++) {
			char ch = (char) ('a' + i);
			String add = (ch=='m' || ch=='z')? "\n": ",";
			
			result += ch + add;
		}

		System.out.print(result);

	}

}
