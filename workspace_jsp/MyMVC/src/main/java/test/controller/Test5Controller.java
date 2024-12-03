package test.controller;

import java.io.IOException;

import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Test5Controller extends AbstractController{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		super.setRedirect(true);
		super.setViewPage(request.getContextPath()+"/test/test4.up");
	}

}
