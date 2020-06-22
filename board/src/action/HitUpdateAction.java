package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.BoardDAO;

public class HitUpdateAction implements Action {

	private String path;
	
	public HitUpdateAction(String path) {
		this.path = path;
	}
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		int bno = Integer.parseInt(req.getParameter("bno"));
		
		BoardDAO dao = new BoardDAO();
		
		if(dao.hitUpdate(bno)==0) {
			path = "index.html";
		}else {
			path += "?bno="+bno;
		}
		
		
		
		return new ActionForward(path, true);
	}

}
