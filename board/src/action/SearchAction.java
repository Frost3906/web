package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.BoardVO;
import domain.SearchVO;
import persistence.BoardDAO;

public class SearchAction implements Action {

	private String path;
	
	public SearchAction(String path) {
		this.path = path;
	}
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		String criteria = req.getParameter("criteria");
		String keyword = req.getParameter("keyword");
		
		
		
		BoardDAO dao = new BoardDAO();
		
		List<BoardVO> list = dao.SearchArticle(criteria, keyword);
		
		//req.setAttribute("list", list);
		//req.setAttribute("search", new SearchVO(criteria, keyword));
		
		
		return new ActionForward(path, false);
	}

}
