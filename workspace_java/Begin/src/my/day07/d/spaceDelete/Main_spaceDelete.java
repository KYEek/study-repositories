package my.day07.d.spaceDelete;

import my.util.MyUtil;

public class Main_spaceDelete {

	public static void main(String[] args) {
		
		String str = null;//  korea   seou l 쌍용 강북 교육센터  
		System.out.println("시작"+MyUtil.spaceDelete_for(str)+"끝");
		//시작koreaseoul쌍용강북교육센터끝

		System.out.println("시작"+MyUtil.spaceDelete_while(str)+"끝");
		//시작koreaseoul쌍용강북교육센터끝

		System.out.println("시작"+MyUtil.spaceDelete_dowhile(str)+"끝");
		//시작koreaseoul쌍용강북교육센터끝

	}

}
