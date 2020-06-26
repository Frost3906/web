package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.MemberDAO;

public class CheckIdAction implements Action {

	private String path;
	
	public CheckIdAction(String path) {
		this.path = path;
	}
	
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//사용자로부터 아이디를 가져와서 중복되었는지 확인한 후
		String userid = request.getParameter("userid");
		
		
		MemberDAO dao = new MemberDAO();
		
		//그 결과를 request에 담고 페이지 이동
		if(dao.checkId(userid)) {
			request.setAttribute("dupid", "false");
		}else {
			request.setAttribute("dupid", "true");
			
		}
		
		return new ActionForward(path, false);
	}

}
