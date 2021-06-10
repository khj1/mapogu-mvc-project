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

@WebServlet("/community/view.do") 

public class ViewController extends HttpServlet implements BoardConfig{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String flag = req.getParameter("flag");
		
		if(!Authority.isLogin(resp, session)) return;
		if(!Authority.checkAuth(resp, session, flag)) return;
		
		doGet(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String flag = req.getParameter("flag");
		String pageNum = req.getParameter("pageNum");
		String board_idx = req.getParameter("board_idx");
		String searchField = req.getParameter("searchField");
		String searchWord = req.getParameter("searchWord");
		String searchStr = "";
		if(searchWord != null)
			searchStr = "searchField=" + searchField + "&searchWord=" + searchWord;

		CommuinityDAO dao = new CommuinityDAO();
		dao.updateVisitCount(board_idx);

		CommunityDTO dto = dao.selectView(board_idx);
		dto.setContent(dto.getContent().replaceAll("\r\n", "<br/>"));
		req.setAttribute("dto", dto);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("searchStr", searchStr);
		req.setAttribute("flag", flag);
		req.getRequestDispatcher("/community/"+ flag +"_view.jsp").forward(req, resp);

		dao.close();
	}
}
