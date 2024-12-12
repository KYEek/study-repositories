package chap05.oracle.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import chap05.oracle.domain.PersonDTO_02;
import chap05.oracle.model.PersonDAO_03;
import chap05.oracle.model.PersonDAO_imple_04;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/personDetail.do")
public class PersonDetail_07 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private PersonDAO_03 dao = new PersonDAO_imple_04();
	String pathname = "";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//		-----이건 일반적인 get 방식일 경우-----
//		String seq = request.getParameter("seq");
//		try {
//			
//			Integer.parseInt(seq);
//			PersonDTO_02 psdto = dao.selectOne(seq);
//			if (psdto != null) {
//				request.setAttribute("psdto", psdto);
//				pathname = "/WEB-INF/chap05_right/personDetail.jsp";
//			}
//			else {
//				// 암기 !!! 자바에서 URL페이지 이동 시키기 !!!
//				response.sendRedirect(request.getContextPath()+"/PersonSelect.do");
//				return; //종료
//			}
//		} catch(NumberFormatException e) {
//			response.sendRedirect(request.getContextPath()+"/PersonSelect.do");
//			return;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			pathname = "/WEB-INF/chap05_right/error.jsp";
//		} 
//		RequestDispatcher dispatcher = request.getRequestDispatcher(pathname);
//		dispatcher.forward(request, response);
		//=== 오로지 POST 방식만 사용이 되도록 하겠다. ===
		String method = request.getMethod();
		if("POST".equalsIgnoreCase(method)) {
			//POST방식으로 접근한 경우
			String seq = request.getParameter("seq");
			String pathname = "";
			try {
				PersonDTO_02 psdto = dao.selectOne(seq);
				request.setAttribute("psdto", psdto);
				pathname = "/WEB-INF/chap05_right/personDetail.jsp";
			} catch (SQLException e) {
				e.printStackTrace();
				pathname = "/WEB-INF/chap05_right/error.jsp";
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher(pathname);
			dispatcher.forward(request, response);
		}
		else {
			//GET방식으로 접근한 경우
			response.sendRedirect(request.getContextPath()+"/PersonSelect.do");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
