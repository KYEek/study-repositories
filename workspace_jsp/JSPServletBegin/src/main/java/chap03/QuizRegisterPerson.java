package chap03;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/quiz_registerPerson.do")
public class QuizRegisterPerson extends HttpServlet {

	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getMethod(); //GET 또는 POST가 나온다.
		
		if("POST".equals(method)) {
			String name=request.getParameter("name");
			String school=request.getParameter("school");
			String color=request.getParameter("color");
			String[] arr_food = request.getParameterValues("food");
			
			//request의 역할은 크게 보면 2가지가 있는데
			// 그 첫번째가 form 태그에서 보내온 데이터 값을 받아오는 용도로 쓰이고,
//			System.out.println(name);
//			System.out.println(school);
//			System.out.println(color);
//			System.out.println(String.join(",", arr_food));
			
			//그 두번째가 form 태그에서 보내온 데이터 값을 저장하는 저장소 용도로 쓰인다.
			//request.setAttribute("키", 데이터값);
			request.setAttribute("name", name);
			request.setAttribute("school", school);
			request.setAttribute("color", color);
			request.setAttribute("arr_food", arr_food);
			
			////////////////////////////////////
			
			String name2=request.getParameter("name");
			String school2=request.getParameter("school");
			String color2=request.getParameter("color");
			
			request.setAttribute("name2", name2);
			request.setAttribute("school2", school2);
			request.setAttribute("color2", color2);
			
//			System.out.println(String.join(",", arr_food));
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/chap04_JSTL/quiz/03_view_03.jsp");
			dispatcher.forward(request, response);
			
		}
		else {
			//GET 방식으로 들어온 경우
			System.out.println("접근금지!!");	
			RequestDispatcher dispatcher = request.getRequestDispatcher("/chap04_JSTL/quiz/03_forbidden_02.jsp");
			dispatcher.forward(request, response);
		}
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
