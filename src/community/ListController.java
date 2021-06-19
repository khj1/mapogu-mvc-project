package community;

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

import common.BoardConfig;
import utils.Authority;
import utils.BoardPage;
import utils.JSFunction;

@WebServlet("/community/list.do") 
public class ListController extends HttpServlet implements BoardConfig{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if(!Authority.isLogin(resp, session)) return;
		
		System.out.println(req.getRequestURI());
		doGet(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CommuinityDAO dao = new CommuinityDAO();
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

		int pageSize = BoardConfig.PAGE_PER_SIZE;
		int blockPage = BoardConfig.PAGE_PER_BLOCK;
		int totalPage = (int)Math.ceil(totalCount / pageSize);
		int start = (pageNum - 1) * pageSize;

		map.put("searchField", searchField);
		map.put("searchWord", searchWord);
		map.put("flag", flag);
		map.put("start", start);
		map.put("pageSize", pageSize);
		List<CommunityDTO> boardList = dao.selectList(map);
		
		String pagingStr = BoardPage.pagingImg2(totalCount, pageNum, req.getRequestURI(), searchStr, flag);

		req.setAttribute("boardList", boardList);
		req.setAttribute("searchStr", searchStr);
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("pagingStr", pagingStr);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("flag", flag);
		req.setAttribute("start", start);
		
		req.getRequestDispatcher("/community/" + flag + ".jsp").forward(req, resp);
	}
}
