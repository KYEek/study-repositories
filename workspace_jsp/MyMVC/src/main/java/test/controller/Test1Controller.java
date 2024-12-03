package test.controller;

import java.io.IOException;

import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Test1Controller extends AbstractController{
	public Test1Controller() {
		System.out.println("111 확인용 Test1Controller 클래스 생성자 호출함");
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setAttribute("name", "연규영");
		
		///////////////////////////////////////////
		super.setRedirect(false);
		super.setViewPage("/WEB-INF/test/test1.jsp");
		
	}
}