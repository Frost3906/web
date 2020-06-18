package action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import domain.BoardVO;
import persistence.BoardDAO;
import utils.FileUpload;

public class WriteAction implements Action {
	private String path;
	public WriteAction(String path) {
		this.path = path;
	}
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
//		String name = req.getParameter("name");
//		String title = req.getParameter("title");
//		String content = req.getParameter("content");
//		String password = req.getParameter("password");
//		String attach = req.getParameter("attach");
		
		FileUpload upload1 = new FileUpload();
		HashMap<String, String> uploadMap = upload1.upload(req);
		String name = uploadMap.get("name");
		String title = uploadMap.get("title");
		String content = uploadMap.get("content");
		String password = uploadMap.get("password");
		String attach = uploadMap.get("attach");
		
		
		BoardDAO dao = new BoardDAO();
		BoardVO vo = new BoardVO();
		vo.setName(name);
		vo.setTitle(title);
		vo.setContent(content);
		vo.setPassword(password);
		if(uploadMap.containsKey("attach")) {
			vo.setAttach(attach);
		}
		
		if(dao.insertArticle(vo)==0) {
			path = "view/qna_board_write.jsp";
		}
		return new ActionForward(path, true);
		
	}

}
