<%@page import="javax.websocket.SendResult"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="domain.BookVO"%>
<%@page import="persistence.BookDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	//book_search.jsp���� �ѱ� �� ��������
	request.setCharacterEncoding("utf-8");
	String criteria = request.getParameter("criteria");
	String keyword = request.getParameter("keyword");

	
	//db�۾� �� ����� request�� ���
	BookDAO dao = new BookDAO();
	
	List<BookVO> search =  dao.searchBook(criteria, keyword);
	
	request.setAttribute("search", search);
	
	//book_searchAll.jsp �� �̵�
	pageContext.forward("../book_searchAll.jsp");


%>
