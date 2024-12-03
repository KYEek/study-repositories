package chap05.oracle.controller;

import java.io.IOException;

import chap05.oracle.domain.PersonDTO_02;
import chap05.oracle.model.PersonDAO_03;
import chap05.oracle.model.PersonDAO_imple_04;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/PersonUpdate_2.do")
public class PersonUpdate_2 extends HttpServlet {

	private static final long serialVersionUID = 1L;
	PersonDTO_02 psdto = new PersonDTO_02();
	PersonDAO_03 dao = new PersonDAO_imple_04();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String path = "";
		
		String method = request.getMethod();
		if("GET".equalsIgnoreCase(method)) {
			response.sendRedirect(request.getContextPath()+"/PersonDetail.do");
		}
		else {
			
		}
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
}
