<%@page import="javax.websocket.SendResult"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="domain.BookVO"%>
<%@page import="persistence.BookDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	//book_insert.jsp���� �ѱ� �� ��������
	request.setCharacterEncoding("utf-8");
	String code = request.getParameter("code");
	String title = request.getParameter("title");
	String writer = request.getParameter("writer");
	int price = Integer.parseInt(request.getParameter("price"));
	
	//db�۾� �� �����ϸ� ��ü ����Ʈ �����ֱ�
	BookDAO dao = new BookDAO();
	BookVO vo = new BookVO();
	vo.setCode(code);
	vo.setTitle(title);
	vo.setWriter(writer);
	vo.setPrice(price);
	
	if(dao.insertBook(vo)>0){
		response.sendRedirect("../book_selectAll.jsp");
	}
	
	
	//�����ϸ� book_insert.jsp�� ����������
	else{
		response.sendRedirect("../index.jsp");
	}

%>
