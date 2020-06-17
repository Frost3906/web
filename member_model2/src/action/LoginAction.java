package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.MemberVO;
import persistence.MemberDAO;

public class LoginAction implements Action{
	
	private String path;
	
	public LoginAction(String path) {
		this.path = path;
	}

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//loginform.jsp에서 넘긴 값 받아오기
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		//db처리
		MemberDAO dao = new MemberDAO();
		MemberVO vo = dao.login(userid, password);
		
		if(vo!=null) {
			//세션에 값을 담고 loginForm.jsp로 이동
			HttpSession session = request.getSession();
			session.setAttribute("login", vo);
		}
		//경로와 방법 설정
		return new ActionForward(path,true);
	}
	
}
