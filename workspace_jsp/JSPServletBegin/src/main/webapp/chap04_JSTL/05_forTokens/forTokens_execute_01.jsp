<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<% String friend_name_1 = "정두환,김성곤,이동훈,이원모,최재혁,차은우";
    String friend_name_2 = "이유진,이혜연.김민지/이지혜,김홍비";
    
    request.setAttribute("friend_name_1", friend_name_1);
    request.setAttribute("friend_name_2", friend_name_2);
    //////////////////////////////////////////////////////

    RequestDispatcher dispatcher = request.getRequestDispatcher("forTokens_result_02.jsp");
    dispatcher.forward(request,response);
    
    %>