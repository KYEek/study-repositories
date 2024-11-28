<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="chap04.PersonDTO" %>

<%
	String[] arr_friend_name = {"정두환", "김성곤", "이동훈", "이원모", "최재혁", "차은우"};
	request.setAttribute("arr_friend_name", arr_friend_name);
	
	//////////////////////////////////////////////////////////////////
	PersonDTO person_1 = new PersonDTO();
	person_1.setName("이순신");
	person_1.setSchool("대졸");
	person_1.setColor("red");
	person_1.setFood("김밥,라면,짜장면".split("\\,"));
	
	PersonDTO person_2 = new PersonDTO();
	person_2.setName("엄정화");
	person_2.setSchool("대학원졸");
	person_2.setColor("blue");
	person_2.setFood("돈까스,볶음밥,냉모밀,제육볶음".split("\\,"));
	
	PersonDTO person_3 = new PersonDTO();
	person_3.setName("홍길동");
	person_3.setSchool("초대졸");
	person_3.setColor("green");
	person_3.setFood("계란말이,빵,수제비,칼국수".split("\\,"));
	
	PersonDTO person_4 = new PersonDTO();
	person_4.setName("유관순");
	person_4.setSchool("대졸");
	person_4.setColor("yellow");
	person_4.setFood("라면,공깁밥,짜장면,한식부페".split("\\,"));
	
	List<PersonDTO> person_List = new ArrayList<>();
	person_List.add(person_1);
	person_List.add(person_2);
	person_List.add(person_3);
	person_List.add(person_4);
	
	request.setAttribute("person_List", person_List);
	//////////////////////////////////////////////////////////////
	Map<String, String> paraMap_1 = new HashMap<>();
	paraMap_1.put("name", "이유진");
	paraMap_1.put("gender", "여");
	paraMap_1.put("address", "경기도 부천시");
	
	Map<String, String> paraMap_2 = new HashMap<>();
	paraMap_2.put("name", "이혜연");
	paraMap_2.put("gender", "여");
	paraMap_2.put("address", "서울시 관악구");
	
	Map<String, String> paraMap_3 = new HashMap<>();
	paraMap_3.put("name", "김민지");
	paraMap_3.put("gender", "여");
	paraMap_3.put("address", "인천시 미추홀");
	
	Map<String, String> paraMap_4 = new HashMap<>();
	paraMap_4.put("name", "이지혜");
	paraMap_4.put("gender", "여");
	paraMap_4.put("address", "충남 천안시");
	
	Map<String, String> paraMap_5 = new HashMap<>();
	paraMap_3.put("name", "김홍비");
	paraMap_3.put("gender", "여");
	paraMap_3.put("address", "경기도 구리시");
	
	List<Map<String, String>> woman_List = new ArrayList<>();
	woman_List.add(paraMap_1);
	woman_List.add(paraMap_2);
	woman_List.add(paraMap_3);
	woman_List.add(paraMap_4);
	woman_List.add(paraMap_5);
	
	request.setAttribute("woman_List",woman_List);
	
	System.out.println("테스트");
	RequestDispatcher dispatcher = request.getRequestDispatcher("02_forEach_Array_List_result_02.jsp");
	dispatcher.forward(request, response);	
%>
