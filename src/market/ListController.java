package market;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.MemberDAO;
import member.MemberDTO;
import utils.Authority;
import utils.BoardPage;

@WebServlet("/market/list.do") 
public class ListController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String flag = req.getParameter("flag");
		if(flag.equals("basket")) {
			if(!Authority.isLogin(resp, session)) return;
			basketList(req, resp);
		}
		else if(flag.equals("order")) {
			if(!Authority.isLogin(resp, session)) return;
			orderList(req, resp);
		}
		else { 
			commonList(req,resp); 
		}
	}

	protected void commonList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProductDAO dao = new ProductDAO();
		Map<String, Object> map = new HashMap<String, Object>(); 

		String flag = req.getParameter("flag");
		String searchField = req.getParameter("searchField");
		String searchWord = req.getParameter("searchWord");
		String searchStr = "";
		if(searchWord != null){
			map.put("searchField", searchField);
			map.put("searchWord", searchWord);
			searchStr = "searchField=" + searchField + "&searchWord=" + searchWord;
		}

		map.put("flag", flag);
		int totalCount = dao.countList(map);

		int pageNum = 1;
		String pageTemp = req.getParameter("pageNum"); 
		if(pageTemp != null && !pageTemp.equals(""))
			pageNum = Integer.parseInt(pageTemp);

		int pageSize = 5;
		int blockPage = 5;
		int totalPage = (int)Math.ceil(totalCount / pageSize);
		int start = (pageNum - 1) * pageSize;

		map.put("searchField", searchField);
		map.put("searchWord", searchWord);
		map.put("start", start);
		map.put("pageSize", pageSize);
		List<ProductDTO> boardList = dao.selectList(map);
		
		String pagingStr = BoardPage.pagingImg2(totalCount, pageNum, req.getRequestURI(), searchStr);

		req.setAttribute("boardList", boardList);
		req.setAttribute("searchStr", searchStr);
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("pagingStr", pagingStr);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("flag", flag);
		req.setAttribute("start", start);
		
		req.getRequestDispatcher("/market/" + flag + ".jsp").forward(req, resp);
	}
	
	private void basketList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		HttpSession session = req.getSession();
		String user_id = session.getAttribute("user_id").toString();

		BasketDAO bDao = new BasketDAO();
		map.put("user_id", user_id);
		
		int totalCount = bDao.countList(map);
		
		bDao.allCheck(user_id);
		List<ProductDTO> basketList = bDao.showBasket(map);

		req.setAttribute("basketList", basketList);
		req.setAttribute("totalCount", totalCount);
		
		req.getRequestDispatcher("/market/basket.jsp").forward(req, resp);
		
	}
	
	private void orderList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		HttpSession session = req.getSession();
		String user_id = session.getAttribute("user_id").toString();
		String product_idx = req.getParameter("product_idx");
		
		List<ProductDTO> orderList = null;
		BasketDAO bDao = new BasketDAO();
		if(product_idx != null)
			orderList = bDao.buyNowList(user_id, product_idx);
		else
			orderList = bDao.buyBasketList(user_id);
		
		bDao.close();
		
		/* 회원정보로 주문자 정보 설정하기
		 * 
		 * MemberDAO mDao = new MemberDAO(); MemberDTO mDto =
		 * mDao.getMemberInfo(user_id); mDao.close();
		 * 
		 * req.setAttribute("mDto", mDto);
		 */
		
		req.setAttribute("orderList", orderList);
		req.getRequestDispatcher("/market/order.jsp").forward(req, resp);
	}
}
