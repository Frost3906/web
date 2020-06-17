package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionFactory;
import action.ActionForward;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//어디서 온 요청인지 알아내기
		request.setCharacterEncoding("utf-8");
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String cmd = requestURI.substring(contextPath.length());
		
		
		response.setContentType("text/html;charset=utf-8");
//		PrintWriter out = response.getWriter();
//		out.print("requsetURI "+requestURI+"<br>");
//		out.print("contextPath "+contextPath+"<br>");
//		out.print("cmd "+cmd);
//		
		
		//action 생성
		ActionFactory factory = ActionFactory.getInstance();
		
		
		//cmd 분석
		Action action = factory.action(cmd);
		
		ActionForward af = null;
		
		try {
			//생성된 action 구동
			af = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		//결과 페이지 이동
		//forward / redirect 결정
		if(af.isRedirect()) {
			response.sendRedirect(af.getPath());
		}else {
			RequestDispatcher rd = request.getRequestDispatcher(af.getPath());
			rd.forward(request, response);
		}
	
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
