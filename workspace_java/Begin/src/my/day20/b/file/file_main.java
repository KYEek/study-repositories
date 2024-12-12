package my.day20.b.file;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class file_main {
	
	/*
	    >>> File 클래스 <<<
	    자바에서 File 클래스의 객체라 함은 파일 및 폴더(디렉토리)를 다 포함한다. 
	 */
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.print("탐색기에 존재하는 파일명을 입력하세요 : ");

		String file_name = sc.nextLine();
		// C:/NCS/iotestdata/쉐보레전면.jpg
		
		File file_1 = new File(file_name);
		
		System.out.println("파일명만 : " + file_1.getName());
		// file_1.getName();은 파일명만 알려주는 것이다.
		
		long file_size = file_1.length();
		System.out.println("파일크기 : " + file_size + "Byte");
		
		String absolutePath = file_1.getAbsolutePath();
		System.out.println("파일이 저장된 경로명을 포함한 파일명1 : " + absolutePath);
		
		String path = file_1.getPath();
		System.out.println("파일이 저장된 경로명을 포함한 파일명2 : " + path);
		
		// C:/NCS/iotestdata/쉐보레전면.jpg 파일이 저장된 경로명만 출력하세요!! 
		String path_name = path.substring(0,path.indexOf(file_1.getName())-1);
		System.out.println("경로명만 : "+ path_name);
		
		System.out.println("———————————————————————————————————————————");
		System.out.println(" 디렉토리 (폴더) 생성하기");
		File dir = new File("C:/NCS/iotestdata/MyDir");
		
		if(!dir.exists()) { 
			// 해당 디렉토리(폴더)가 없으면
			boolean bool = dir.mkdir();	//해당 디렉토리(폴더)를 생성해라.
			String result = bool?"디렉토리(폴더) 생성 성공!!":"디렉토리(폴더) 생성 실패ㅜㅜ"; 
			System.out.println("C:/NCS/iotestdata/MyDir " + result);
		}
		
		//File dir 이 디렉토리(폴더)인지 알아보기
		if(dir.isDirectory()) {
			System.out.println("C:/NCS/iotestdata/MyDir 은 디렉토리(폴더) 입니다.");
		}
		

		File file_2 = new File("C:/NCS/iotestdata/MyDir/테스트1.txt");
		
		if(!file_2.exists()) {
			// 해당 파일이 존재하지 않으면
			try {
				boolean bool = file_2.createNewFile();	//파일 생성하기
				
				if(bool) {
					//해당파일이 정상적으로 생성되었다면
					System.out.println("테스트1.txt 의 절대경로 : " + file_2.getAbsolutePath());
				}
				
			} catch (IOException e) {
				e.printStackTrace();
				
			} 
		}
		
		// File file_2 이 파일인지 알아보기!
		
		if(file_2.isFile()) {
			System.out.println("C:/NCS/iotestdata/MyDir/테스트1.txt 은 파일입니다.");
		}
		
		System.out.println("———————————————————————————————————————————");

		System.out.println(" 파일을 삭제해 보자!!");
		
		boolean is_delete_ok = file_2.delete();
		String result = is_delete_ok?"C:/NCS/iotestdata/MyDir/테스트1.txt 파일삭제 성공!!":"C:/NCS/iotestdata/MyDir/테스트1.txt 파일삭제 실패ㅜㅜ";
		
		System.out.println(result);

		System.out.println("———————————————————————————————————————————");

		System.out.println(" 텅빈 디렉토리를 삭제해 보자!!");
		
		if (dir.isDirectory()) { // dir은 C:/NCS/iotestdata/MyDir 폴더이다.
			is_delete_ok = dir.delete();
		}
		result = is_delete_ok?"C:/NCS/iotestdata/MyDir 폴더삭제 성공!!":"C:/NCS/iotestdata/MyDir 폴더삭제 실패ㅜㅜ";
		
		System.out.println(result);
		System.out.println("======================================");

		System.out.println(">>> 내용물이 들어있는 디렉토리(폴더) 삭제하기 실패한 예제 <<<");
		// 먼저 아래의 실습을 하려면 탐색기에서 C:/NCS/iotestdata 밑에 images 라는 폴더를 생성하고
		// images 폴더 속에 파일을 몇 개 올려둔다.
		
		File images_dir = new File("C:/NCS/iotestdata/images");
		if (images_dir.isDirectory()) { // images_dir은 C:/NCS/iotestdata/images 폴더이다.
			is_delete_ok = images_dir.delete();
		}
		result = is_delete_ok?"C:/NCS/iotestdata/images 폴더삭제 성공!!":"C:/NCS/iotestdata/images 폴더삭제 실패ㅜㅜ";
		
		System.out.println(result);
		
		System.out.println("======================================");

		System.out.println(">>> 내용물이 들어있는 디렉토리(폴더) 삭제하기 성공한 예제 <<<");
		// 먼저 아래의 실습을 하려면 탐색기에서 C:/NCS/iotestdata 밑에 images 라는 폴더를 생성하고
		// images 폴더 속에 파일을 몇 개 올려둔다.
		
		// 1. 내용물이 들어있는 디렉토리(폴더)내에 존재하는 내용물을 파악한다.
		File[] file_arr = images_dir.listFiles();
		
		if(file_arr != null) {
			for(int i = 0; i<file_arr.length; i++) {
				if(file_arr[i].isFile()) {
					System.out.println(file_arr[i].getAbsolutePath());
				}
			}// end of for------------------------------------------------
			
			//C:/NCS/iotestdata/images 폴더내의 파일들을 모두 삭제한다.
			for(int i = 0; i<file_arr.length; i++) {
				if(file_arr[i].isFile()) {
					file_arr[i].delete();
				}
			}// end of for------------------------------------------------
			
		}
		
		if (images_dir.isDirectory()) { // images_dir은 C:/NCS/iotestdata/images 폴더이다.
			is_delete_ok = images_dir.delete();
		}
		result = is_delete_ok?"C:/NCS/iotestdata/images 폴더삭제 성공!!":"C:/NCS/iotestdata/images 폴더삭제 실패ㅜㅜ";
		
		System.out.println(result);
		
		

		sc.close();
	}

}
