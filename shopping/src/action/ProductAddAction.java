package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.ProductVO;
import persistence.ProductDAO;

public class ProductAddAction implements Action {

	private String path;
	
	public ProductAddAction(String path) {
		this.path = path;
	}
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		String category = req.getParameter("goods_category");
		String name = req.getParameter("name");
		int price = Integer.parseInt(req.getParameter("price"));
		String color = req.getParameter("color");
		int amount = Integer.parseInt(req.getParameter("amount"));
		String psize = req.getParameter("psize");
		String content = req.getParameter("content");
		
			
		ProductDAO dao = new ProductDAO();
		ProductVO vo = new ProductVO();
		vo.setCategory(category);
		vo.setName(name);
		vo.setPrice(price);
		vo.setColor(color);
		vo.setAmount(amount);
		vo.setPsize(psize);
		vo.setContent(content);
		
		if(dao.insertProduct(vo)==0) {
			path = "product_add.jsp";
		}
		
		return new ActionForward(path, true);
	}

}
