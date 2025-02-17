package chap02;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/07_get_post_Method.jsp")
public class Get_Post_Method_07 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());

//		HttpServletRequest 객체는 전성되어온 데이터를 처리해주는 용도로 쓰인다
		String name = request.getParameter("name");
		String school = request.getParameter("school");

		String color = request.getParameter("color");
		String[] arr_food = request.getParameterValues("food");

		if (name == null) {
			name = "없음";
		}
		if (color == null) {
			color = "없음";
		}

		// **** 콘솔에 출력하여 확인하기 시작 ****//
		System.out.println("name =>" + name);
		System.out.println("school =>" + school);
		System.out.println("color =>" + color);

		String like_foodes = "좋아하는 음식이 없습니다";
		if (arr_food != null) {

			for (int i = 0; i < arr_food.length; i++) {
				System.out.println("arr_food[" + i + "]=> " + arr_food[i]);
			}

			like_foodes = String.join(",", arr_food);
		}
		System.out.println("food =>" + like_foodes);
		// **** 콘솔에 출력하여 확인하기 끝 ****//

		// **** 웹브라우저에 출려갛기 시작 ****//
		// HttpServletResponse 객체는 전송되어져온 데이터를 조작해서 결과물을 나타내고자 할 때 쓰인다.
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out = response.getWriter();
		// out 은 웹브라우저에 기술하는 대상체라고 생각하자.

		String like_color = "좋아하는 색이 없습니다";
		if(!"없음".equals(color)) {
			like_color = "<span style ='color:"+color+";'>";
		}
		
		switch (color) {
		case "red":
			color = "빨간색";
			break;
		case "blue":
			color = "파랑색";
			break;
		case "green":
			color = "초록색";
			break;
		case "yellow":
			color = "노란색";
			break;
		}
		if(!"없음".equals(color)) {
			like_color += color + "</span>을 좋아합니다.";
		}
//		like_color = !"없음".equals(color) ? color + "을 좋아합니다" : "좋아하는 색이 없습니다.";
		like_foodes = arr_food != null ? String.join(",", arr_food) + "입니다" : "없습니다";

		// *** 클라이언트(form 태그가 있는 .jsp 파일)에서 넘어온 method 방식이 GET 인지 POST 인지 알아오기 *** //
		String method = request.getMethod(); //GET 또는 POST
		out.println("<html>" + "<head><title>개인성향 테스트 결과화면07</title></head>" + "<body>" + "<h2>개인성향 테스트 결과 04("+method+")</h2>"
				+ "<span style='color:orange; font-weight:bold;'>" + name + "</span>님의 개인성향은 <br><br>" + "학력은 " + school
				+ "이며 " + like_color + "<br><br>" + "좋아하는 음식은 " + like_foodes + "<br><br>" + "</body>" + "</html>");

		// **** 웹브라우저에 출려갛기 끝 ****//
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
