<%@page import="domain.MemberVO"%>
<%@page import="persistence.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//modifyForm.jsp 데이터 가져오기
	
	String current_password = request.getParameter("current_password");
	String new_password = request.getParameter("new_password");
	String confirm_password = request.getParameter("confirm_password");
	

	
	//new_password와 confirm_password가 같은지 확인 후
	//같이 않으면 modifyForm.jsp 돌려보내기
	if(!new_password.equals(confirm_password)){
		response.sendRedirect("../view/modifyForm.jsp");
	}
	
	else{
		MemberVO login = (MemberVO) session.getAttribute("login");
		String userid = login.getUserid();
		MemberDAO dao = new MemberDAO();
		if(dao.modifyPassword(userid, new_password, current_password)>0){
			session.invalidate();
			response.sendRedirect("../index.jsp");
		}else{
			response.sendRedirect("../view/modifyForm.jsp");
		}
	}
	//현재 비밀번호가 일치하면 
	//새로운 비밀번호로 수정 후 
	//로그인 페이지로 이동
	
	
	//현재 비밀번호가 틀린 경우
	//다시 modifyForm.jsp를 보여주기
%>