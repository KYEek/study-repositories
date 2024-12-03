package test.controller;

import java.io.IOException;

import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Test2Controller extends AbstractController{
	public Test2Controller() {
		System.out.println("222 확인용 Test2Controller 클래스 생성자 호출함");
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		super.setRedirect(true);
		super.setViewPage(request.getContextPath()+"/test1.up");
		
	}
}
