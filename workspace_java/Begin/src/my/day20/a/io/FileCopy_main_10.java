package my.day20.a.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.Scanner;

/*
		※ Data Source (File, 키보드, 원격 컴퓨터)
		: 데이터의 근원
		
		※ Data Destination (파일, 모니터, 프린터, 메모리)
		: 데이터가 최종적으로 도착하는 곳
		
		Data Sourceㅇ======>ㅇ 프로그램 ㅇ======>ㅇ Data Destination
		             입력스트림          출력스트림
		           InputStream       OutputStream          
		
		  
		  === 파일로 부터 입력받은 것을 파일에 출력(기록)하는 예제 ===
		              
		  1. 데이터소스  : 특정 파일로 부터 읽어들임  (노드스트림: FileInputStream) 
		  2. 데이터목적지 : 결과물을 특정 파일에 출력함 (노드스트림: FileOutputStream)
	*/

public class FileCopy_main_10 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		System.out.print(">> 복사할 원본파일명(절대경로)을 입력 => ");
		String src_fileName = sc.nextLine();
		
		System.out.print(">> 목적 파일명(절대경로)을 입력 => ");
		String target_fileName = sc.nextLine();
		
		System.out.println("소스파일 : " + src_fileName);
		System.out.println("목적파일 : " + target_fileName);
		
		byte[] data_arr = new byte[10240]; // 10KB
		int input_length = 0;
		// input_length 변수의 용도는 빨대(System.in, 키보드)에서 흡입한 실제 크기를 나타내는 용도임. 
		int cnt = 0;
		int sharp_cnt = 0;
//		int totalByte = 0; //Byte 수 누적용
		
		try {
			
			//소스 File 객체 생성하기
			File src_file = new File(src_fileName);
			// String 타입인 src_fileName(파일경로명/파일명)을 실제 File 클래스의 객체로 만든다.
			
			long src_file_size = src_file.length();//파일의 크기를 알려준다.
			System.out.println(">> 원본 파일("+src_file+") 크기 : "+ new DecimalFormat("#,###").format(src_file_size)+"byte");
			
			long max_size = 1024*1024*10; //10mb;
			if(src_file_size > max_size) {
				// 원본 파일의 크기가 10MB 초과한 경우
				System.out.println(">> 원본 파일의 크기가 10mb 초과했으므로 복사할 수 없습니다. <<");
				sc.close();
				return;
			}
			
			FileInputStream fist = new FileInputStream(src_file);
			FileOutputStream fost = new FileOutputStream(target_fileName);
			while ((input_length = fist.read(data_arr))!=-1) {
				
				fost.write(data_arr, 0, input_length);
				
//				totalByte += input_length;
				if(sharp_cnt == 40) {
					System.out.print("#\n");

					sharp_cnt = 0;
					cnt++;
				}
				else {
					System.out.print("#");
					sharp_cnt++;
					cnt++;
				}
			}
			// end of while
			fist.close();
			fost.close();
			
		} catch (java.io.FileNotFoundException e) {
			System.out.println(src_fileName + "파일은 없습니다");
			System.out.println(target_fileName + "파일은 없습니다");
		}
		catch (java.io.IOException e) {
			e.printStackTrace();
		}
		
//		System.out.println(target_fileName + "에 쓰기 완료!! " + totalByte + "Byte 씀");
		System.out.println("\n반복회수 : " +cnt + "번 반복함.");
		System.out.println("\n복사완료.");
		sc.close();
		
	}//

}


	/*
	 
			>> 복사할 원본파일명(절대경로)을 입력 => C:\NCS\iotestdata\openjdk-17.0.0.1+2_windows-x64_bin.zip
			>> 목적 파일명(절대경로)을 입력 => C:\NCS\iotestdata\openjdk.zip
			소스파일 : C:\NCS\iotestdata\openjdk-17.0.0.1+2_windows-x64_bin.zip
			목적파일 : C:\NCS\iotestdata\openjdk.zip
			>> 원본 파일(C:\NCS\iotestdata\openjdk-17.0.0.1+2_windows-x64_bin.zip) 크기 : 186223043byte
			>> 원본 파일의 크기가 10mb 초과했으므로 복사할 수 없습니다. <<
	
	
	 */


