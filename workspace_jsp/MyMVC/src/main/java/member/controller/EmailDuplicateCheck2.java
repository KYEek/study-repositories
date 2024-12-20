package member.controller;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import member.model.MemberDAO;
import member.model.MemberDAO_imple;

public class EmailDuplicateCheck2 extends AbstractController {

	private MemberDAO mdao = new MemberDAO_imple();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse respone) throws Exception {
		
		String method = request.getMethod(); // "GET" 또는 "POST" 
		
		if("POST".equalsIgnoreCase(method)) {
		   
			String email = request.getParameter("email");
			String userid = request.getParameter("userid");
			
			Map<String, String> paraMap = new HashMap<>();
			paraMap.put("email", email);
			paraMap.put("userid", userid);
			
			boolean isExists = mdao.emailDuplicateCheck2(paraMap);
			// 자신이 변경하고자하는 이메일이 현재 사용중인지 아닌지 여부 알아오기
			
			if(!isExists) { // 자신이 변경하고자하는 이메일이 현재 사용중이 않는 새로운 이메일 이라면   
				isExists = mdao.emailDuplicateCheck(email);  // 다른 사용자가 사용중인 이메일인지 아닌지 알아와야 한다. 
			}
			
			JSONObject jsonObj = new JSONObject(); // {}
			jsonObj.put("isExists", isExists);     // {"isExists" : true}  또는   {"isExists" : false} 
			
			String json = jsonObj.toString(); // 문자열 형태인 "{"isExists":true}" 또는 "{"isExists":false}" 으로 만들어준다. 
		//	System.out.println(">>> 확인용 json => " + json);
		    // >>> 확인용 json => {"isExists":true}
			// >>> 확인용 json => {"isExists":false}
			
			request.setAttribute("json", json);
			
		//	super.setRedirect(false);
			super.setViewPage("/WEB-INF/jsonview.jsp");
		}	

	}

}
