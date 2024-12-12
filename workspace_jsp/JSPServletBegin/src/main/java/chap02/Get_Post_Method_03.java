package chap02;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/*
=== Servlet 이란? 웹서비스 기능을 해주는 자바 클래스를 말한다. ===

*** Servlet 이 되기 위한 조건은 3가지 규칙을 따라야 한다. ***

1. 서블릿(Servlet)은 반드시 
javax.servlet.http.HttpServlet 클래스를 부모 클래스로 상속을 받아와야 한다.
(Tomcat 9 까지)

jakarta.servlet.http.HttpServlet 클래스를 부모 클래스로 상속을 받아와야 한다.
(Tomcat 10 이후 부터)



2. 웹클라이언트의 요청방식이 GET 방식으로 요청을 해오면
doGet() 메소드로 응답을 해주도록 코딩을 해야하고,
웹클라이언트의 요청방식이 POST 방식으로 요청을 해오면
doPost() 메소드로 응답을 해주도록 코딩을 해주어야만 한다.
그러므로  반드시  doGet() 메소드와  doPost() 메소드를 
Overriding(재정의)를 해주어야만 한다.

doGet() 메소드와 doPost() 메소드의 
첫번째 파라미터는 HttpServletRequest 타입이고,
두번째 파라미터는 HttpServletResponse 타입이다.


3. 만약에  서블릿(Servlet)에서 결과물을 웹브라우저상에 출력하고자 한다라면 
      doGet() 메소드와 doPost() 메소드 모두 
      서블릿(Servlet)의 두번째 파라미터인 HttpServletResponse response 를 
      사용하여 출력해준다.

*/

public class Get_Post_Method_03 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{

		System.out.println("~~~ get 방식으로 요청을 보내어서 doGet 메소드가 응대를 하겠습니다.");
		execute(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{

		System.out.println("### post 방식으로 요청을 보내어서 doPost 메소드가 응대를 하겠습니다.");
		execute(request, response);
	}

	private void execute (HttpServletRequest request, HttpServletResponse response) throws IOException{
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
		out.println("<html>" + "<head><title>개인성향 테스트 결과화면</title></head>" + "<body>" + "<h2>개인성향 테스트 결과 01(POST)</h2>"
				+ "<span style='color:green; font-weight:bold;'>" + name + "</span>님의 개인성향은 <br><br>" + "학력은 " + school
				+ "이며 " + like_color + "<br><br>" + "좋아하는 음식은 " + like_foodes + "<br><br>" + "</body>" + "</html>");

		// **** 웹브라우저에 출려갛기 끝 ****//
	}
	

}
