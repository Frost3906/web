package action;

import java.net.URLEncoder;

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
		int page = Integer.parseInt(req.getParameter("page"));
		String criteria = req.getParameter("criteria");
		String keyword = URLEncoder.encode(req.getParameter("keyword"),"utf-8");
		
		BoardDAO dao = new BoardDAO();
		
		if(dao.hitUpdate(bno)==0) {
			path = "index.html";
		}else {
			path += "?bno="+bno+"&page="+page+"&criteria="+criteria+"&keyword="+keyword;
		}
		
		
		
		return new ActionForward(path, true);
	}

}
