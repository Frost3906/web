package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.BookDAO;

public class DeleteAction implements Action {

	private String path;
	
	public DeleteAction(String path) {
		this.path = path;
	}
	
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//사용자가 요청한 작업 처리하기
		
		//사용자가 입력한 값 가져오기
		String code = req.getParameter("code");
		
		//db작업 후 성공하면 전체 리스트 보여주기
		BookDAO dao = new BookDAO();
		

		
		//DB입력이 실패하는 경우 path 재설정
		if(!dao.deleteBook(code)){
			path = "index.jsp?tab=delete";
		}
		return new ActionForward(path,true);
	
	}

}
