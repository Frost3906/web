<%@page import="persistence.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	//leaveForm.jsp 에서 넘긴 값 가져오기
	String userid = request.getParameter("userid");
	String password = request.getParameter("current_password");
	//DB처리 후 탈퇴 성공하면
	MemberDAO dao = new MemberDAO();
	if(dao.leave(userid, password)>0){
	//세션 제거 후 index페이지 이동
		session.removeAttribute("login");
		out.print("<script>");
		out.print("alert('회원탈퇴 성공');");		
		out.print("location.href='../index.jsp'");
		out.print("</script>");
	}
	
	
	//탈퇴 실패시 leaveForm.jsp이동
	else{
		out.print("<script>");
		out.print("alert('비밀번호를 확인해주세요');");		
		out.print("location.href='../view/leaveForm.jsp'");
		out.print("</script>");
	}

%>
