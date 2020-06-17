<%@page import="domain.MemberVO"%>
<%@page import="persistence.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    //joinForm.jsp에서 넘긴 값 받아오기
    request.setCharacterEncoding("UTF-8");
    String userid = request.getParameter("userid");
    String password = request.getParameter("password");
    String confirm_password = request.getParameter("confirm_password");
    String name = request.getParameter("name");
    String gender = request.getParameter("gender");
    String email = request.getParameter("email");
    
    if(password.equals(confirm_password)){
    	//DB작업한 후 회원가입 성공하면
		MemberDAO dao = new MemberDAO();
    	MemberVO vo = new MemberVO();
    	vo.setUserid(userid);
    	vo.setPassword(password);
	    vo.setName(name);
    	vo.setGender(gender);
    	vo.setEmail(email);
    int result = dao.register(vo);
    //로그인 페이지로 이동
    	if(result>0){
    		out.print("<script>");
    		out.print("alert('회원가입성공');");
    		out.print("location.href='../view/loginForm.jsp';");
    		out.print("</script>");
		
    //회원가입 실패하면 회원가입 페이지로 이동
    	}else{
    		out.print("<script>");
    		out.print("alert('회원가입실패');");
    		out.print("location.href='../view/joinForm.jsp';");
    		out.print("</script>");
 	
    	}
    
    }else{
    	out.print("<script>");
		out.print("alert('비밀번호를 확인해주세요');");
		out.print("location.href='../view/joinForm.jsp'");
		out.print("</script>");
    }
    


%>
    