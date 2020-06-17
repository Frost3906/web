package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.BookVO;
import persistence.BookDAO;

public class SelectAction implements Action {
	
	private String path;
	
	public SelectAction(String path) {
		this.path = path;
	
	}

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//도서목록 전체 가져오기
		BookDAO dao = new BookDAO();
		List<BookVO> list = dao.getList();
		
		req.setAttribute("list", list);
		//리퀘스트 -> 포워드
		
		
		return new ActionForward(path, false);
	}

}
