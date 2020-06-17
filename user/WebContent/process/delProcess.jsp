<%@page import="domain.UserVO"%>
<%@page import="persistence.UserDAO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	//사용자가 입력한 정보 가져오기
	//userDel.jsp
	request.setCharacterEncoding("utf-8");
	int no = Integer.parseInt(request.getParameter("no"));
	String userName = request.getParameter("userName");
	
	UserDAO dao = new UserDAO();
		
	//사용자에게 결과 페이지 보여주기 전체 User화면 보여주기
	if(dao.userDelete(no, userName)>0){
		response.sendRedirect("../index.jsp");		
			
	}
	

	


%>
</body>
</html>