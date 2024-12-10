package common.controller;

import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class IndexController extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse respone) throws Exception {
		
		super.setRedirect(false);
		super.setViewPage("/WEB-INF/index.jsp");

	}

}