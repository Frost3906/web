package action;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.BoardVO;
import domain.SearchVO;
import persistence.BoardDAO;

public class ViewAction implements Action {
	private String path;
	
	public ViewAction(String path) {
		this.path = path;
	}
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		//list.do => 제목 클릭 (view.do?bno=해당번호)
		
		//bno 가져오기
		int bno = Integer.parseInt(req.getParameter("bno"));
		
		
		int page = Integer.parseInt(req.getParameter("page"));
		String criteria = req.getParameter("criteria");
		String keyword = req.getParameter("keyword");	
				
				
		
		//bno에 해당하는 게시물 가져오기
		BoardDAO dao = new BoardDAO();
		BoardVO vo = dao.viewArticle(bno);
		SearchVO search = new SearchVO(criteria, keyword, page, 10);
		if(vo!=null) {
			req.setAttribute("vo", vo);
			req.setAttribute("search", search);
					
		}else {
			path = "/list.do"+"?page="+page+"&criteria="+criteria+"&keyword="+keyword;
			return new ActionForward(path, true);
		}
		
		//가져온 게시물 request에 담고 페이지 이동
		
		return new ActionForward(path, false);
	}

}
