package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.BoardVO;
import domain.PageVO;
import domain.SearchVO;
import persistence.BoardDAO;

public class ListAction implements Action {

	private String path;
	
	public ListAction(String path) {
		this.path = path;
	}
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String criteria = req.getParameter("criteria");
		String keyword = req.getParameter("keyword");
		
		
		
		int page = 1;
		if(req.getParameter("page")!=null) {
			page = (Integer.parseInt(req.getParameter("page")));
		}
		int amount = 10;
		
		SearchVO search = new SearchVO(criteria, keyword, page, amount);
		BoardDAO dao = new BoardDAO();
		
		
		//전체 게시물 수
		
		int total = dao.totalRow(criteria, keyword);
		PageVO pageVO = new PageVO(total, search);
		
		List<BoardVO> list = dao.ListArticle(search);
		
		if(!list.isEmpty()) {
			req.setAttribute("list", list);
			req.setAttribute("pageVO", pageVO);
		}
		return new ActionForward(path, false);
	}

}
