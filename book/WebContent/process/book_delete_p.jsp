<%@page import="javax.websocket.SendResult"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="domain.BookVO"%>
<%@page import="persistence.BookDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	//book_delete.jsp���� �ѱ� �� ��������
	request.setCharacterEncoding("utf-8");
	String code = request.getParameter("code");
	
	//db�۾� �� �����ϸ� ��ü ����Ʈ �����ֱ�
	BookDAO dao = new BookDAO();
	
	if(dao.deleteBook(code)){
		response.sendRedirect("../book_selectAll.jsp");
	}

	//�����ϸ� book_insert.jsp�� ����������
	else{
		response.sendRedirect("../index.jsp");
	}

%>
