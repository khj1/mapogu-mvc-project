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

import utils.Authority;
import utils.BoardPage;

@WebServlet("/market/list.do") 
public class ListController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String flag = req.getParameter("flag");
		if(flag.contains("basket")) {
			if(!Authority.isLogin(resp, session)) return;
			basketList(req, resp);
		}
		else { 
			doGet(req,resp); 
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		String flag = req.getParameter("flag");
		String user_id = session.getAttribute("user_id").toString();

		BasketDAO bDao = new BasketDAO();
		map.put("flag", flag);
		int totalCount = bDao.countList(map);

		int pageNum = 1;
		String pageTemp = req.getParameter("pageNum"); 
		if(pageTemp != null && !pageTemp.equals(""))
			pageNum = Integer.parseInt(pageTemp);

		int pageSize = 5;
		int blockPage = 5;
		int totalPage = (int)Math.ceil(totalCount / pageSize);
		int start = (pageNum - 1) * pageSize;

		map.put("user_id", user_id);
		map.put("start", start);
		map.put("pageSize", pageSize);
		
		List<ProductDTO> basketList = bDao.showBasket(map);
		
		String pagingStr = BoardPage.pagingImg2(totalCount, pageNum, req.getRequestURI());

		req.setAttribute("basketList", basketList);
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("pagingStr", pagingStr);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("flag", flag);
		req.setAttribute("start", start);
		
		req.getRequestDispatcher("/market/" + flag + ".jsp").forward(req, resp);
		
	}
}
