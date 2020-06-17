package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction implements Action{

	String path;
	public LogoutAction(String path) {
		this.path = path;
	}
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//세션 해제 후 이동
		//request.getSession(boolean); => 세션이 없는 경우  true, false값에 의해 세션 생성여부 결정
		//request.getSession(); => 세션이 없는 경우 새로운 세션을 무조건 생성
		HttpSession session = request.getSession(false);
		session.removeAttribute("login");
		
		//경로와 방법 설정
		return new ActionForward(path,true);
	}
}
