<%@page import="javax.websocket.SendResult"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="domain.BookVO"%>
<%@page import="persistence.BookDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	//book_delete.jsp에서 넘긴 값 가져오기
	request.setCharacterEncoding("utf-8");
	String code = request.getParameter("code");
	
	//db작업 후 성공하면 전체 리스트 보여주기
	BookDAO dao = new BookDAO();
	
	if(dao.deleteBook(code)){
		response.sendRedirect("../book_selectAll.jsp");
	}

	//실패하면 book_insert.jsp로 돌려보내기
	else{
		response.sendRedirect("../index.jsp");
	}

%>
