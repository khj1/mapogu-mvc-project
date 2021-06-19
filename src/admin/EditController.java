package admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.MemberDAO;
import member.MemberDTO;
import utils.Authority;
import utils.JSFunction;

@WebServlet("/admin/edit.do")
public class EditController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		if(!Authority.isLogin_admin(resp, session)) return;
		else {
			String id = session.getAttribute("user_id").toString();
			if(!id.equals("administrator")) {
				JSFunction.alertLocation(resp, "권한이 없습니다.", "../main/main.do");
			}
		}
		
		if(req.getMethod().equals("GET")) {
			doGet(req, resp);
		}
		else if(req.getMethod().equals("POST")) {
			doPost(req, resp);
		}
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String flag = req.getParameter("flag");
		String id = req.getParameter("id");
		
		MemberDAO mDao = new MemberDAO();
		MemberDTO mDto = mDao.getMemberInfo(id);
		
		req.setAttribute("flag", flag);
		req.setAttribute("id", id);
		req.setAttribute("dto", mDto);
		
		req.getRequestDispatcher("/admin/member/member_edit.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String auth = req.getParameter("auth");
		
		MemberDAO mDao = new MemberDAO();
		int result = mDao.updateAuth(id, auth);
		
		if(result != 0)
			JSFunction.alertLocation(resp, "권한이 변경되었습니다.", "../admin/list.do?flag=member");
		else
			JSFunction.alertBack(resp, "권한을 변경하지 못했습니다.");
			
	}
}
