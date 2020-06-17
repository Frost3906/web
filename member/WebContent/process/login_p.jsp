<%@page import="domain.MemberVO"%>
<%@page import="persistence.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//loginForm.jsp에서 넘긴 값 가져오기
	
	String userid = request.getParameter("userid");
	String password = request.getParameter("password");
	
	
	//DB확인 후 존재하는 사용자라면
	MemberDAO dao = new MemberDAO();
	MemberVO vo = dao.login(userid, password);
	
	if(vo!=null){
		
		session.setAttribute("login", vo);
		
	}
	response.sendRedirect("../view/loginForm.jsp");
	


%>