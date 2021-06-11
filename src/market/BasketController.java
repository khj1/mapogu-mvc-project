package market;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.Authority;
import utils.JSFunction;

@WebServlet("/market/basket.do")
public class BasketController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if(!Authority.isLogin(resp, session)) return;

		String check = req.getParameter("check");
		
		if(check == null)
			insertGoods(req, resp);
		else
			checkGoods(req, resp);
	}
	

	private void insertGoods(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		String user_id = session.getAttribute("user_id").toString();
		String product_idx = req.getParameter("product_idx");
		int amount = Integer.parseInt(req.getParameter("amount"));
		
		ProductDAO pDao = new ProductDAO();
		ProductDTO pDto = pDao.getProductInfo(product_idx);
		pDao.close();
		
		int total_price = pDto.getPrice() * amount;
		int total_reserves = pDto.getReserves() * amount;
		
		BasketDTO bDto = new BasketDTO();
		bDto.setProduct_idx(product_idx);
		bDto.setId(user_id);
		bDto.setAmount(amount);
		bDto.setTotal_price(total_price);
		bDto.setTotal_reserves(total_reserves);
		
		BasketDAO bDao = new BasketDAO();
		
		int result = 0;
		if(bDao.checkOverlap(product_idx, user_id)) 
			result = bDao.updateBasket(bDto);
		else 
			result = bDao.insertBasket(bDto);
		
		if(result == 0) {
			JSFunction.alertBack(resp, "장바구니에 추가하지 못했습니다.");
			return;
		}
		bDao.close();
	}
	
	
	private void checkGoods(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		String user_id = session.getAttribute("user_id").toString();
		String product_idx = req.getParameter("product_idx");
		String check = req.getParameter("check");
		
		BasketDTO bDto = new BasketDTO();
		bDto.setId(user_id);
		bDto.setProduct_idx(product_idx);
		bDto.setBasket_check(check);
		
		BasketDAO bDao = new BasketDAO();
		bDao.updateCheck(bDto);
	}
}
