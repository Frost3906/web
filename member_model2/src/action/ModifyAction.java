package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.MemberVO;
import persistence.MemberDAO;

public class ModifyAction implements Action{
	
	private String path;
	
	public ModifyAction(String path) {
		this.path = path;
	}

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//modifyForm.jsp에서 넘긴 값 받아오기
		
		String current_password = request.getParameter("current_password");
		String new_password = request.getParameter("new_password");
		String confirm_password = request.getParameter("confirm_password");
		
		if(new_password.equals(confirm_password)){
		
			HttpSession session = request.getSession(false);
			MemberVO login = (MemberVO) session.getAttribute("login");
			String userid = login.getUserid();
			
			//db처리
			MemberDAO dao = new MemberDAO();
			
			
			if(dao.modifyPassword(userid, new_password, current_password)>0) {
				session.invalidate();
			}else {
				path = "view/modifyForm.jsp";
			}
		}else {
			path = "view/modifyForm.jsp";
		}
		//경로와 방법 설정
		return new ActionForward(path,true);
	}
	
}
