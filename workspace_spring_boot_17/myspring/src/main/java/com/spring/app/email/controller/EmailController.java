package com.spring.app.email.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.app.common.FileManager;

import jakarta.servlet.http.HttpSession;

// === #205. 다중 파일첨부가 있는 복수 사용자에게 이메일 보내기 ===
@Controller
@RequestMapping("/email/*")  
public class EmailController {

	// === #207. 빈으로 등록되어진 GoogleMail 클래스를 DI 하기 ===
	@Autowired   // Type 에 따라 알아서 Bean 을 주입해준다. 
	private GoogleMail mail;
	
	// === 파일업로드 및 파일다운로드를 해주는 FileManager 클래스 의존객체 주입하기(DI : Dependency Injection) === 
	@Autowired   // Type 에 따라 알아서 Bean 을 주입해준다.
	private FileManager fileManager;
	
	
	@GetMapping("emailWrite")
	public String emailWrite() {
		
		return "mycontent1/email/emailWrite";
	    //  /WEB-INF/views/mycontent1/email/emailWrite.jsp 페이지를 만들어야 한다.
	}
	
	
	@PostMapping("emailWrite")
	@ResponseBody
	public String emailWrite(MultipartHttpServletRequest mtp_request) {

		String recipient = mtp_request.getParameter("recipient");
		String subject = mtp_request.getParameter("subject");
		String content = mtp_request.getParameter("content");
		
		List<MultipartFile> fileList = mtp_request.getFiles("file_arr"); // "file_arr" 은 /myspring/src/main/webapp/WEB-INF/views/mycontent1/email/emailWrite.jsp 페이지의 317 라인에 보여지는 formData.append("file_arr", item); 의 값이다.           
		// !! 주의 !! 복수개의 파일은 mtp_request.getFile 이 아니라 mtp_request.getFiles 이다.!!
		// MultipartFile interface는 Spring에서 업로드된 파일을 다룰 때 사용되는 인터페이스로 파일의 이름과 실제 데이터, 파일 크기 등을 구할 수 있다.
		
		
		/*
		   >>>> 첨부파일이 업로드 되어질 특정 경로(폴더)지정해주기
		        우리는 WAS 의 webapp/resources/email_attach_file 라는 폴더로 지정해준다.
		*/
		// WAS 의 webapp 의 절대경로를 알아와야 한다.
		HttpSession session = mtp_request.getSession();
		String root = session.getServletContext().getRealPath("/");
		String path = root + "resources"+File.separator+"email_attach_file";
		// path 가 첨부파일들을 저장할 WAS(톰캣)의 폴더가 된다.
		
	 // System.out.println("~~~~ 확인용 path => " + path);
	    // ~~~~ 확인용 path => C:\NCS\workspace_spring_boot_17\myspring\src\main\webapp\resources\email_attach_file 
		
		File dir = new File(path);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		// >>>> 첨부파일을 위의 path 경로에 올리기 <<<< //
		String[] arr_attachFilename = null; // 첨부파일명들을 기록하기 위한 용도  
				
		if(fileList != null && fileList.size() > 0) {
			arr_attachFilename = new String[fileList.size()];
			
			for(int i=0; i<fileList.size(); i++) {
				MultipartFile mtfile = fileList.get(i);
			//	System.out.println("파일명 : " + mtfile.getOriginalFilename() + " / 파일크기 : " + mtfile.getSize());
			/*	
				파일명 : berkelekle심플라운드01.jpg     / 파일크기 : 71317
				파일명 : Electrolux냉장고_사용설명서.pdf  / 파일크기 : 791567
				파일명 : 쉐보레전면.jpg                 / 파일크기 : 131110
			*/	
			
				try {	
				// === MultipartFile 을 File 로 변환하여 탐색기 저장폴더에 저장하기 시작 === //
					File attachFile = new File(path+File.separator+mtfile.getOriginalFilename());
					mtfile.transferTo(attachFile); // !!!!! 이것이 파일을 업로드해주는 것이다. !!!!!! 
					/*
					   form 태그로 부터 전송받은 MultipartFile mtfile 파일을 지정된 대상 파일(attachFile)로 전송한다.
                       만약에 대상 파일(attachFile)이 이미 존재하는 경우 먼저 삭제된다.
					*/
					// 탐색기에서 C:\NCS\workspace_spring_boot_17\myspring\src\main\webapp\resources\email_attach_file 폴더에 가보면
					// 첨부한 파일이 생성되어져 있음을 확인한다.
				// === MultipartFile 을 File 로 변환하여 탐색기 저장폴더에 저장하기 끝 === //	
					
					arr_attachFilename[i] = mtfile.getOriginalFilename(); // 첨부파일명들을 기록한다. 
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}// end of for-------------------------------
			
		}// end of if(fileList != null && fileList.size() > 0)----------
		
		
	//	System.out.println("recipient : " + recipient);
		// recipient : hanmailrg@naver.com;tjdudgkr0959@naver.com
		
	//	System.out.println("subject : " + subject);
		// subject : 첨부파일이 있는 메일보내기 연습
		
	//	System.out.println("content : " + content);
		// content : <p>첨부파일이 있는 메일보내기 연습 입니다.</p>

		JSONObject jsonObj = new JSONObject();
		
		String[] arr_recipient = recipient.split("\\;");
		
		for(String recipient_email : arr_recipient) {
			
			Map<String, Object> paraMap = new HashMap<>();
			paraMap.put("recipient", recipient_email);
			paraMap.put("subject", subject);
			paraMap.put("content", content);
			
			if(fileList != null && fileList.size() > 0) {
				paraMap.put("path", path); // path 는 첨부파일들이 저장된 WAS(톰캣)의 폴더의 경로명이다. 
				paraMap.put("arr_attachFilename", arr_attachFilename); // arr_attachFilename 은 첨부파일명들이 저장된 배열이다.
			}
			
			try {
				mail.sendmail_withFile(paraMap);
				
				jsonObj.put("result", 1);
				
			} catch (Exception e) {
				e.printStackTrace();
				
				jsonObj.put("result", 0);
				break;
			}
			
		}// end of for--------------------------
		
		// 메일 전송 후 업로드한 첨부파일 지우기
		if(arr_attachFilename != null) {
			for(String attachFilename : arr_attachFilename) {
				try {
					fileManager.doFileDelete(attachFilename, path);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}// end of for------------------------
		}
		
		System.out.println(jsonObj.toString());  // "{"result":1}"
		
		return jsonObj.toString(); // "{"result":1}"
	}
	
	
	@GetMapping("emailWrite/done")
	public String emailWrite_done() {
		
		return "mycontent1/email/emailWrite_done";
	}
	
	
	
	
	
}
