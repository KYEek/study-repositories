package chap03;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/registerPerson.do")
public class RegisterPerson extends HttpServlet {

	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
//		String method = request.getMethod(); //GET 또는 POST가 나온다.
//		
//		if("POST".equals(method)) {
//			System.out.println("~~Post 방식으로 들어오심을 환영합니다.");	
//		}
//		else {
//			System.out.println("!!Get 방식으로 들어오시면 안돼요!!.");	
//		}
		String name=request.getParameter("name");
		String school=request.getParameter("school");
		String color=request.getParameter("color");
		String[] arr_food = request.getParameterValues("food");
		
		System.out.println(name);
		System.out.println(school);
		System.out.println(color);
		System.out.println(String.join(",", arr_food));
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
}
