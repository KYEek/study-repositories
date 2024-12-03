package test.controller;

import java.io.IOException;

import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Test3Controller extends AbstractController {
	public Test3Controller() {
		System.out.println("333 확인용 Test3Controller 클래스 생성자 호출함");
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setAttribute("name", "쌍용강북교육센터 Gclass");
		
		super.setRedirect(false);
		super.setViewPage("/WEB-INF/test/test1.jsp");
		
	}
}
