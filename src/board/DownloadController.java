package board;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.BoardConfig;
import utils.BoardPage;

@WebServlet("/community/download.do") 
public class DownloadController extends HttpServlet implements BoardConfig{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		int lastSlash = uri.lastIndexOf("/");
		String commandStr = uri.substring(lastSlash);
		if(commandStr.equals("/data.list"))
			selectList(req, resp, "data", "sub01");
		else if(commandStr.equals("/comp.list"))
			selectList(req, resp, "comp", "sub02"); 
	}

	private void selectList(HttpServletRequest req, HttpServletResponse resp, String flag, String board) throws ServletException, IOException {
		BoardDAO dao = new BoardDAO();
		Map<String, Object> map = new HashMap<String, Object>(); 

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
		List<BoardDTO> boardList = dao.selectList(map);
		
		String pagingStr = BoardPage.pagingImg2(totalCount, pageNum, req.getRequestURI(), searchStr);

		req.setAttribute("boardList", boardList);
		req.setAttribute("searchStr", searchStr);
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("pagingStr", pagingStr);
		req.setAttribute("pageNum", pagingStr);
		req.setAttribute("start", start);
		
		req.getRequestDispatcher("/community/" + board + ".jsp").forward(req, resp);
	}
}
