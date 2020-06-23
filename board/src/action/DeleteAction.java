package action;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.BoardDAO;

public class DeleteAction implements Action {

	private String path;
	
	public DeleteAction(String path) {
		this.path = path;
	}
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		int bno = Integer.parseInt(req.getParameter("bno"));
		String password = req.getParameter("password");
		
		int page = Integer.parseInt(req.getParameter("page"));
		String criteria = req.getParameter("criteria");
		String keyword = URLEncoder.encode(req.getParameter("keyword"),"utf-8");	
		
		BoardDAO dao = new BoardDAO();
		if(dao.deleteArticle(bno, password)==0) {
			path = "view/qna_board_pwdCheck.jsp?bno="+bno+"&page="+page+"&criteria="+criteria+"&keyword="+keyword;
		}else {
			path+="?page="+page+"&criteria="+criteria+"&keyword="+keyword;
		}
		
		return new ActionForward(path, true);
	}

}
