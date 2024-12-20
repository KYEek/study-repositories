package member.controller;

import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.domain.MemberVO;
import member.model.*;

public class MemberOneDetail extends AbstractController {

	private MemberDAO mdao = new MemberDAO_imple();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse respone) throws Exception {
		
		// ==== 관리자(admin)로 로그인 했을때만 조회가 가능하도록 한다. ==== //
		HttpSession session = request.getSession();
		
		MemberVO loginuser = (MemberVO) session.getAttribute("loginuser"); 
		
		if(loginuser != null && "admin".equals(loginuser.getUserid())) {
			// 관리자(admin)로 로그인 했을 경우 
			
			String method = request.getMethod();
			
			if("POST".equalsIgnoreCase(method)) {
				// POST 방식일때
				
				String userid = request.getParameter("userid");
				
				MemberVO mvo = mdao.selectOneMember(userid);
				
				request.setAttribute("mvo", mvo);
				
				super.setRedirect(false);
				super.setViewPage("/WEB-INF/member/admin/memberOneDetail.jsp");
			}
			
		}
		else {
			// 로그인을 안하거나 또는 관리자(admin)가 아닌 사용자로 로그인 했을 경우
			
		}
		

	}

}
