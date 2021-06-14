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
import member.MemberDAO;
import member.MemberDTO;
import request.CleanDAO;
import request.CleanDTO;
import request.FieldDAO;
import request.FieldDTO;
import utils.Authority;
import utils.JSFunction;

@WebServlet("/admin/write.do")
public class WriteController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		if(!Authority.isLogin(resp, session))
			JSFunction.alertLocation(resp, "로그인 후 이용해주세요.", "../admin/login.html");
		else {
			String id = session.getAttribute("user_id").toString();
			if(!id.equals("administrator")) {
				JSFunction.alertLocation(resp, "권한이 없습니다.", "../main/main.do");
			}
		}
		
		String flag = req.getParameter("flag");
		if(flag.equals("board"))
			writePost(req, resp);
	}

	private void writePost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String flag = req.getParameter("flag");
		String type = req.getParameter("type");
		
		CommunityDTO cDto = new CommunityDTO();
		String name = req.getParameter("name");
		String caldate = req.getParameter("caldate");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		cDto.setFlag(type);
		cDto.setId("administrator");
		cDto.setName(name);
		cDto.setCaldate(caldate);
		cDto.setTitle(title);
		cDto.setContent(content);
		
		CommuinityDAO cDao = new CommuinityDAO();
		int result = cDao.insertWrite(cDto);
		
		resp.sendRedirect("/admin/list.do?flag=" + flag + "&type=" + type);
	}
}

