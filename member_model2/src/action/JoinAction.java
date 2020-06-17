package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.MemberVO;
import persistence.MemberDAO;

public class JoinAction implements Action{
	
	private String path;
	
	public JoinAction(String path) {
		this.path = path;
	}

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//loginform.jsp에서 넘긴 값 받아오기
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		String confirm_password = request.getParameter("confirm_password");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		
		//db처리
		MemberDAO dao = new MemberDAO();
		MemberVO vo = new MemberVO();
		
		if(password.equals(confirm_password)) {
			
			vo.setUserid(userid);
			vo.setPassword(password);
			vo.setName(name);
			vo.setGender(gender);
			vo.setEmail(email);
			
			
			
			if(dao.register(vo)>0) {
				path="/view/loginForm.jsp";
			}else {
				path="/view/joinForm.jsp";
			}
		}
		else {
			path="/view/joinForm.jsp";
		}
		//경로와 방법 설정
		return new ActionForward(path,true);
	}
	
}
