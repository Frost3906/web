package action;

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
		
		
		BoardDAO dao = new BoardDAO();
		if(dao.deleteArticle(bno, password)==0) {
			path = "view/qna_board_pwdCheck.jsp?bno="+bno;
		}
		
		return new ActionForward(path, true);
	}

}
