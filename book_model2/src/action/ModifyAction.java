package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import persistence.BookDAO;

public class ModifyAction implements Action {

	private String path;
	
	public ModifyAction(String path) {
		this.path = path;
	}
	
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//사용자가 요청한 작업 처리하기
		
		//사용자가 입력한 값 가져오기
		String code = req.getParameter("code");
		int price = Integer.parseInt(req.getParameter("price"));
		
		//db작업 후 성공하면 전체 리스트 보여주기
		BookDAO dao = new BookDAO();
		//DB입력이 실패하는 경우 path 재설정
		if(dao.modifyBook(code, price)==0){
			path = "index.jsp?tab=modify";
		}
		return new ActionForward(path,true);
	
	}

}
