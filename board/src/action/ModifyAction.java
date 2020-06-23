package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.BoardVO;
import domain.SearchVO;
import persistence.BoardDAO;

public class ModifyAction implements Action {
	private String path;
	
	public ModifyAction(String path) {
		this.path = path;
	}
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		int bno = Integer.parseInt(req.getParameter("bno"));
		
		int page = Integer.parseInt(req.getParameter("page"));
		String criteria = req.getParameter("criteria");
		String keyword = req.getParameter("keyword");	
		
		BoardDAO dao = new BoardDAO();
		BoardVO vo = dao.viewArticle(bno);
		SearchVO search = new SearchVO(criteria, keyword, page, 10);
		
		if(vo!=null) {
			req.setAttribute("vo", vo);
			req.setAttribute("search", search);
		}else {
			path="/view.do?bno="+bno;
			return new ActionForward(path, true);
		}
		
		return new ActionForward(path, false);
	}

}
