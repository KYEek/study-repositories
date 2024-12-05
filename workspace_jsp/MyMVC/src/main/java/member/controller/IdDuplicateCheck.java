package member.controller;

import org.json.JSONObject;

import common.controller.AbstractController;
import member.model.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class IdDuplicateCheck extends AbstractController {
	private MemberDAO mdao = new MemberDAO_imple();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String method = request.getMethod(); // "GET" 또는 "POST"
		
		
		if ("POST".equalsIgnoreCase(method)) {
			String userid = request.getParameter("userid");

			boolean isExists = mdao.idDuplicatedCheck(userid);
			
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("isExists",isExists);//{"isExist" : true} 또는 {"isExists":false} 으로 만들어준다
			
			String json = jsonObj.toString();
			System.out.println(">>>확인용 json => " + json);
			
			request.setAttribute("json", json);
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/jsonview.jsp");
			

		}
	}

}
