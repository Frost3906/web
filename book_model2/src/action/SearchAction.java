package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.BookVO;
import persistence.BookDAO;

public class SearchAction implements Action {
	
	private String path;
	
	public SearchAction(String path) {
		this.path = path;
	
	}

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//도서목록 전체 가져오기
		BookDAO dao = new BookDAO();
		String criteria = req.getParameter("criteria");
		String keyword = req.getParameter("keyword");
		List<BookVO> search = dao.searchBook(criteria, keyword);

		if(search.isEmpty()) {
			path = "index.jsp?tab=search";
		}
		req.setAttribute("search", search);
		//리퀘스트 -> 포워드
		
		
		return new ActionForward(path, false);
	}

}
