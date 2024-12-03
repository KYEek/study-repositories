package test.controller;

import java.io.IOException;

import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Test4Controller extends AbstractController{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setAttribute("name", "아이유");
		request.setAttribute("img", request.getContextPath()+"/images/iyou.jpg");
		
		super.setRedirect(false);
		super.setViewPage("/WEB-INF/test/test2.jsp");
		
	}

}
