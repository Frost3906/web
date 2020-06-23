package action;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.BoardVO;
import persistence.BoardDAO;

public class ReplyAction implements Action {

	private String path;
	
	public ReplyAction(String path) {
		this.path = path;
	}
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		
		
		String name = req.getParameter("name");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String password = req.getParameter("password");
		int bno = Integer.parseInt(req.getParameter("bno"));
		//원본글에 대한 반응
		int re_ref = Integer.parseInt(req.getParameter("re_ref"));
		int re_seq = Integer.parseInt(req.getParameter("re_seq"));
		int re_lev = Integer.parseInt(req.getParameter("re_lev"));

		
		int page = Integer.parseInt(req.getParameter("page"));
		String criteria = req.getParameter("criteria");
		String keyword = URLEncoder.encode(req.getParameter("keyword"),"utf-8");
		
		
		
		BoardDAO dao = new BoardDAO();
		BoardVO vo = new BoardVO(); 
		
		vo.setName(name);
		vo.setTitle(title);
		vo.setContent(content);
		vo.setPassword(password);
		
		vo.setRe_ref(re_ref);
		vo.setRe_seq(re_seq);
		vo.setRe_lev(re_lev);
				
		if(dao.replyArticle(vo)==0) {
			path = "replyView.do?bno="+bno+"&page="+page+"&criteria="+criteria+"&keyword="+keyword;
		}else {
			path += "?page="+page+"&criteria="+criteria+"&keyword="+keyword;
		}
		
		
		return new ActionForward(path, true);
	}

}
