package test.controller;

import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Test4Controller extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse respone) throws Exception {
		
		request.setAttribute("name", "아이유");
		request.setAttribute("img", "iyou.jpg");
		
		///////////////////////////////////////////
		super.setRedirect(false);
		super.setViewPage("/WEB-INF/test/test4.jsp");
	}

}
