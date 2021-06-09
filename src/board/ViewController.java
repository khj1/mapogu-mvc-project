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

@WebServlet("/community/view.do") 
public class ViewController extends HttpServlet implements BoardConfig{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String flag = req.getParameter("flag");
		String board = req.getParameter("board");
		String pageNum = req.getParameter("pageNum");
		String board_idx = req.getParameter("board_idx");
		String searchField = req.getParameter("searchField");
		String searchWord = req.getParameter("searchWord");
		String searchStr = "";
		if(searchWord != null)
			searchStr = "searchField=" + searchField + "&searchWord=" + searchWord;

		BoardDAO dao = new BoardDAO();
		dao.updateVisitCount(board_idx);

		BoardDTO dto = dao.selectView(board_idx);
		dto.setContent(dto.getContent().replaceAll("\r\n", "<br/>"));
		req.setAttribute("dto", dto);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("searchStr", searchStr);
		req.setAttribute("board", board);
		req.setAttribute("flag", flag);
		req.getRequestDispatcher("/community/"+ board +"_view.jsp").forward(req, resp);

		dao.close();
	}
}
