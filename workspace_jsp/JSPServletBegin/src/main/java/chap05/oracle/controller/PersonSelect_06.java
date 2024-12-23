package chap05.oracle.controller;

import java.io.IOException;
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

@WebServlet("/PersonSelect.do")
public class PersonSelect_06 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private PersonDAO_03 dao = new PersonDAO_imple_04();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// tbl_person_interest 테이블에 저장되어진 행(데이터)을 읽어다가(select) 웹페이지에 보여줘야 한다.
		String pathname = "";
		try {
			List<PersonDTO_02> personList = dao.selectAll();
			request.setAttribute("personList", personList);
			pathname = "/WEB-INF/chap05_right/personSelectAll.jsp";

		} catch (java.sql.SQLException e) {
			e.printStackTrace();
			pathname = "/WEB-INF/chap05_right/error.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(pathname);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
