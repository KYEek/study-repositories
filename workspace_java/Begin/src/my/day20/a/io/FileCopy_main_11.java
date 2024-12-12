package my.day20.a.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DecimalFormat;

/*
		>>>>> BufferedInputStream 와 BufferedOutputStream <<<<<
      -- 1 byte 기반 스트림.
      -- 필터스트림(다른말로 보조스트림 이라고 부른다.)
      -- 단독으로 사용할 수 없고, 반드시 노드스트림에 장착되어 사용되는 것이다.
         마치 수영장에서 오리발처럼 보조기구로 사용한다.
         이것을 사용하면 오리발처럼 속도가 향상되므로 많이 사용한다.
              
      -- 읽어올 데이터를 매번 1 byte 마다 읽고,쓰고 한다라면 입.출력에 너무 많은 시간이 소요된다. 
         그래서 쓰는 작업없이 메모리 버퍼에 데이터를 한꺼번에 쭉~~ 읽기만 하여 모아두면
         그만큼 쓰는 작업이 없으므로 읽기 속도는 빨라질 것이다.
         그리고 나서 메모리 버퍼에 읽어서 모아두었던 내용을 한방에 쓰기를 하면 매번 1byte 씩 쓰는 것보다
         속도가 빨라진다.
              
       BufferedInputStream 와  BufferedOutputStream 의 기본 버퍼크기는 512 byte 이다.
       
       [예제]
       필터스트림(보조스트림)을 이용해서 파일로 부터 입력받고, 
       입력받은 그 내용을 파일에 출력해본다. 즉, 파일복사하기
          
       >>> 데이터소스 : 파일(FileInputStream --> 노드스트림)
                   + 필터스트림(보조스트림)으로 BufferedInputStream 을 사용함.
       
       >>> 데이터목적지 : 파일(FileOutputStream --> 노드스트림)
                     + 필터스트림(보조스트림)으로  BufferedOutputStream 을 사용함.
	*/

public class FileCopy_main_11 {

	public static void main(String[] args) {
		
		String src_fileName = null;
		String target_fileName= null;
		try {
			//입력 노드 스트림 ==> vkdlf(FileInputStream)
			src_fileName = "C:/NCS/iotestdata/planet_of_beauty.jpg";
			File src_file = new File(src_fileName);
			FileInputStream fist = new FileInputStream(src_file);
			
			//입력 노드 스트림에 보조(필터) 스트림(BufferedInputStream)을 장착한다.
			//BufferedInputStream bist = new BufferedInputStream(fist);	//bist의 버퍼크기는 512Byte가 된다.
			BufferedInputStream bist = new BufferedInputStream(fist, 1024*1024);	//bist의 버퍼크기는 1MByte 가 된다.
			// 입력노드스트림인 fist에 보조 (필터) 스트림을 장착함
			
			
			
			// 출력 노드 스트림 ==> 파일(FileOutputStream)
			target_fileName = "C:/NCS/iotestdata/beauty_of_space.jpg";
			File target_file = new File(target_fileName);
			FileOutputStream fost = new FileOutputStream(target_file);
			
			// 출력 노드 스트림에 보조(필터)스트림(BufferedOutputStream)을 장착한다.
			//BufferedOutputStream bost = new BufferedOutputStream(fost);	//bost 의 버퍼크기는 512byte 가 된다.
			BufferedOutputStream bost = new BufferedOutputStream(fost, 1024*1024);	//bost 의 버퍼크기는 1MByte 가 된다.
			//출력 노드 스트림인 fost 에 보조(필터)스트림을 장착함. 
			//bost 의 버퍼크기는 1MByte 가 된다.
			
			
			byte[] data_arr = new byte[1024*1024]; // 1MB
			int input_length = 0;
			// input_length 변수의 용도는 빨대(System.in, 키보드)에서 흡입한 실제 크기를 나타내는 용도임. 
			int cnt = 0;
			int sharp_cnt = 0;
	//		int totalByte = 0; //Byte 수 누적용
			
	
			long src_file_size = src_file.length();//파일의 크기를 알려준다.
			System.out.println(">> 원본 파일("+src_file+") 크기 : "+ new DecimalFormat("#,###").format(src_file_size)+"byte");
				
			long max_size = 1024*1024*10; //10mb;
			if(src_file_size > max_size) {
				// 원본 파일의 크기가 10MB 초과한 경우
				System.out.println(">> 원본 파일의 크기가 10mb 초과했으므로 복사할 수 없습니다. <<");
				bist.close();
				bost.close();
				fist.close();
				fost.close();
				return;
			}
			
			while ((input_length = bist.read(data_arr))!=-1) {
				
				bost.write(data_arr, 0, input_length);
				
//				totalByte += input_length;
				if(cnt<39 && sharp_cnt%2 != 0) {
					System.out.print("φ(゜▽゜*)♪");
					sharp_cnt++;
					cnt++;
				}
				if(cnt<39 && cnt%2 == 0) {
					System.out.print("(o゜▽゜)o☆");
					sharp_cnt++;
					cnt++;
				}
				else {
					System.out.print("φ(゜▽゜*)♪\n");
					sharp_cnt= 0;
					cnt++;
				}
			}

			System.out.println("\n반복회수 : " +cnt + "번 반복함.");
			System.out.println("\n복사완료.");
			// end of while
			bist.close();
			bost.close();
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


