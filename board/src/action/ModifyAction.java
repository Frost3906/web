package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.BoardVO;
import persistence.BoardDAO;

public class ModifyAction implements Action {
	private String path;
	
	public ModifyAction(String path) {
		this.path = path;
	}
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		int bno = Integer.parseInt(req.getParameter("bno"));
		
		BoardDAO dao = new BoardDAO();
		BoardVO vo = dao.viewArticle(bno);
		
		if(vo!=null) {
			req.setAttribute("vo", vo);
		}else {
			path="/view.do?bno="+bno;
			return new ActionForward(path, true);
		}
		
		return new ActionForward(path, false);
	}

}
