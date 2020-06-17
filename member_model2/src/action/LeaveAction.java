package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import persistence.MemberDAO;

public class LeaveAction implements Action{
	
	private String path;
	
	public LeaveAction(String path) {
		this.path = path;
	}

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//modifyForm.jsp에서 넘긴 값 받아오기
		
		String userid = request.getParameter("userid");
		String password = request.getParameter("current_password");

		
		
			
		//db처리
		//세션 해제
		MemberDAO dao = new MemberDAO();
			
			
		if(dao.leave(userid, password)>0) {
			HttpSession session = request.getSession(false);
			session.removeAttribute("login");
		}else {
			path = "view/leaveForm.jsp";
		}
		
		//경로와 방법 설정
		return new ActionForward(path,true);
	}
	
}
