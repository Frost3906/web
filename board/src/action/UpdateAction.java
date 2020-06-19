package action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.BoardVO;
import persistence.BoardDAO;
import utils.FileUpload;

public class UpdateAction implements Action {
	private String path;
	

	public UpdateAction(String path) {
		this.path = path;
	
	}
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		FileUpload upload1 = new FileUpload();
		HashMap<String, String> uploadMap = upload1.upload(req);
		int bno = Integer.parseInt(uploadMap.get("bno"));
		String name = uploadMap.get("name");
		String title = uploadMap.get("title");
		String content = uploadMap.get("content");
		String password = uploadMap.get("password");
		String attach = uploadMap.get("attach");
		
		BoardDAO dao = new BoardDAO();
		BoardVO vo = new BoardVO();
		vo.setBno(bno);
		vo.setName(name);
		vo.setTitle(title);
		vo.setContent(content);
		vo.setPassword(password);
		
		if(uploadMap.containsKey("attach")) {			
			vo.setAttach(attach);
		}
		
		if(dao.modifyArticle(vo)==0) {
			path = "modify.do?bno="+bno;
		}else {
			path="/view.do?bno="+bno;
		}
		
		
		
		
		return new ActionForward(path, true);
	}

}
