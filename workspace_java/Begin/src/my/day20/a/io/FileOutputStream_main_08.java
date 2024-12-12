package my.day20.a.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/*
	※ Data Source (File, 키보드, 원격 컴퓨터)
	: 데이터의 근원
	
	※ Data Destination (파일, 모니터, 프린터, 메모리)
	: 데이터가 최종적으로 도착하는 곳
	
	Data Sourceㅇ======>ㅇ 프로그램 ㅇ======>ㅇ Data Destination
	           입력스트림           출력스트림
	          InputStream        OutputStream          
	
	  
	=== 키보드로 부터 입력받은 것을 파일(C:\NCS\iotestdata\result.txt)에 기록(출력)하기로 한다. ===
	
	1. 데이터 소스  : 키보드로 부터 입력받음    [노드스트림 : System.in]
	2. 데이터 목적지 : 결과를 특정 파일에 출력함 [노드스트림 : FileOutputStream] 
	*/


public class FileOutputStream_main_08 {

	public static void main(String[] args) {
		System.out.println(">> 내용을 입력하세요 [입력하신 내용은 C:\\NCS\\iotestdata\\result.txt 파일에 저장됨.]");

		String fileName = "C:/NCS/iotestdata/result.txt";
		byte[] data_arr = new byte[10];
		// data_arr 변수의 용도는 빨대(System.in, 키보드)에서 흡입할 때 단위크기를 10byte로 하는 것이다.
		
		int input_length = 0;
		// input_length 변수의 용도는 빨대(System.in, 키보드)에서 흡입한 실제 크기를 나타내는 용도임. 
		//int input = 0;
		int cnt = 0;
		int totalByte = 0; //Byte 수 누적용
		
		try {
			boolean append = false;
			FileOutputStream fost = new FileOutputStream(fileName, append);
			
			/*
            만약에 탐색기에서 
             C:/NCS/iotestdata/result.txt 파일이 없다라면
             파일을 자동으로 생성해준다.
             단, 탐색기에서 C:/NCS/iotestdata 폴더는 존재해야 한다.
         
             두번째 파라미터인 append 가 true 인 경우는
             첫번째 파라미터인 해당파일에 이미 내용물이 적혀 있는 경우일때
             기존내용물은 그대로 두고 기존내용뒤에 새로운 내용을 덧붙여 추가하겠다는 말이다. 
             
             두번째 파라미터인 append 가 false 인 경우는
             첫번째 파라미터인 해당파일에 이미 내용물이 적혀 있는 경우일때
             기존내용물은 싹 지우고 새로운 내용을 새롭게 처음부터 쓰겠다는 말이다.
           
             그런데 만약에 두번째 파라미터를 생략하면    
             즉, FileOutputStream fost = new FileOutputStream(filename); 이라면
             자동적으로 false 로 인식한다. 즉, filename 의 기존내용물은 싹 지우고 새로운 내용을 새롭게 처음부터 쓰겠다는 말이다.
         */
			while((input_length = System.in.read(data_arr)) != -1) {
				
				fost.write(input_length);
				fost.flush();
				
				totalByte += input_length;
				cnt++;
				
			}// end of while --------------------------------
			
			fost.close();
		} catch (FileNotFoundException e) {
			System.out.println(fileName + " 파일이 없습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(fileName + "에 쓰기 완료" + totalByte + "Byte 사용함");
		System.out.println("반복회수 : " + cnt + "번 반복함.");
		
	}// end of main-----------------------------------------------------

}


		/*
			>>> 명령 프롬프트에서 실행한 결과
			C:\NCS\workspace_java\Begin\bin>java my.day20.a.io.FileOutputStream_main_07
			>> 내용을 입력하세요 [입력하신 내용은 C:\NCS\iotestdata\result.txt 파일에 저장됨.]
			superman 안녕하세요?
			Bye Bye
			^Z
			C:/NCS/iotestdata/result.txt에 쓰기 완료31Byte 사용함
			반복회수 : 31번 반복함.
		*/