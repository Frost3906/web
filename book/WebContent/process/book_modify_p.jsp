<%@page import="javax.websocket.SendResult"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="domain.BookVO"%>
<%@page import="persistence.BookDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	//book_modify.jsp���� �ѱ� �� ��������
	request.setCharacterEncoding("utf-8");
	String code = request.getParameter("code");
	int price = Integer.parseInt(request.getParameter("price"));
	
	//db�۾� �� �����ϸ� ��ü ����Ʈ �����ֱ�
	BookDAO dao = new BookDAO();

	
	if(dao.modifyBook(code, price)>0){
		response.sendRedirect("../book_selectAll.jsp");
	}
	
	
	//�����ϸ� book_modify.jsp�� ����������
	else{
		response.sendRedirect("../index.jsp");
	}

%>
