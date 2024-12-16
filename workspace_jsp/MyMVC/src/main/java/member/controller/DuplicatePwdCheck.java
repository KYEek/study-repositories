package member.controller;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import member.model.*;

public class DuplicatePwdCheck extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse respone) throws Exception {
		// TODO Auto-generated method stub
		String method = request.getMethod(); // "GET" 또는 "POST" 
		MemberDAO mdao = new MemberDAO_imple();
		
		if("POST".equalsIgnoreCase(method)) {
			String new_pwd = request.getParameter("new_pwd");
			String userid = request.getParameter("userid");
		
			Map<String, String> paraMap =  new HashMap<>();
			
			paraMap.put("new_pwd", new_pwd);
			paraMap.put("userid", userid);
			
			boolean isExists = mdao.duplicatePwdCheck(paraMap);
			
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("isExists", isExists);
			
			String json = jsonObj.toString();
			
			request.setAttribute("json", json);
		
		}
		

	}

}
