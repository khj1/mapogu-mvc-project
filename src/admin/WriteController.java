package admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import community.CommuinityDAO;
import community.CommunityDTO;
import utils.Authority;
import utils.JSFunction;

@WebServlet("/admin/write.do")
public class WriteController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		if(!Authority.isLogin_admin(resp, session)) return;
		else {
			String id = session.getAttribute("user_id").toString();
			if(!id.equals("administrator")) {
				JSFunction.alertLocation(resp, "권한이 없습니다.", "../admin/main.do");
			}
		}
		
		String flag = req.getParameter("flag");
		
		if(req.getMethod().equals("GET")) {
			if(flag.equals("board"))
				moveToWrite(req, resp);
		}
		else if(req.getMethod().equals("POST")) {
			if(flag.equals("board"))
				writePost(req, resp);
		}
	}

	private void moveToWrite(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String flag = req.getParameter("flag");
		String type = req.getParameter("type");
		
		req.setAttribute("flag", flag);
		req.setAttribute("type", type);
		req.getRequestDispatcher("/admin/" + flag + "/" + type + "_write.jsp").forward(req, resp);
		
	}

	private void writePost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String flag = req.getParameter("flag");
		String type = req.getParameter("type");
		
		CommunityDTO cDto = new CommunityDTO();
		String caldate = req.getParameter("caldate");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		cDto.setFlag(type);
		cDto.setId("administrator");
		cDto.setName("관리자");
		cDto.setCaldate(caldate);
		cDto.setTitle(title);
		cDto.setContent(content);
		
		CommuinityDAO cDao = new CommuinityDAO();
		int result = cDao.insertWrite(cDto);
		
		resp.sendRedirect("../admin/list.do?flag=" + flag + "&type=" + type);
	}
}

