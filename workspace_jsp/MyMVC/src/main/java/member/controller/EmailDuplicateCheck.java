package member.controller;

import org.json.JSONObject;

import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import member.model.MemberDAO;
import member.model.MemberDAO_imple;

public class EmailDuplicateCheck extends AbstractController {

	private MemberDAO mdao = new MemberDAO_imple();

	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse respone) throws Exception {
		String method = request.getMethod();
		
		System.out.println("자바실행");
		if("POST".equalsIgnoreCase(method)) {
			System.out.println("sql실행");
			String email = request.getParameter("email");
			
			boolean isExists = mdao.emailDuplicatedCheck(email);
			
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("isExists",isExists);//{"isExist" : true} 또는 {"isExists":false} 으로 만들어준다
			
			String json = jsonObj.toString();
//			System.out.println(">>>확인용 json => " + json);
			
			request.setAttribute("json", json);
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/jsonview.jsp");
		}
	
	}

}
