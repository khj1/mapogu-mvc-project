package admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import community.CommuinityDAO;
import member.MemberDAO;
import utils.Authority;
import utils.JSFunction;

@WebServlet("/admin/del.do")
public class DeleteController extends HttpServlet{
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
		if(flag.equals("member"))
			deleteMember(req, resp);
		else if(flag.equals("board"))
			deleteBoard(req, resp);
	}

	private void deleteBoard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String flag = req.getParameter("flag");
		String type = req.getParameter("type");
		String board_idx = req.getParameter("board_idx");
		
		CommuinityDAO cDao = new CommuinityDAO();
		int result = cDao.deletePost(board_idx);
		
		if(result != 0)
			JSFunction.alertLocation(resp, "삭제되었습니다.", "../admin/list.do?flag=" + flag + "&type=" + type);
		else
			JSFunction.alertBack(resp, "삭제하지 못했습니다.");
	}

	private void deleteMember(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String flag = req.getParameter("flag");
		String id = req.getParameter("id");
		
		MemberDAO mDao = new MemberDAO();
		int result = mDao.deleteMember(id);
		
		if(result != 0)
			JSFunction.alertLocation(resp, "삭제되었습니다.", "../admin/list.do?flag=" + flag);
		else
			JSFunction.alertBack(resp, "삭제하지 못했습니다.");
	}
}