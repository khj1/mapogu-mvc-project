package admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.MemberDAO;
import utils.JSFunction;

@WebServlet("/admin/login.do")
public class LoginController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		MemberDAO dao = new MemberDAO();
		String user_id = req.getParameter("user_id");
		String user_pass = req.getParameter("user_pass");
		
		if(dao.isMember(user_id, user_pass)) {
			String auth = dao.getMemberInfo(user_id).getAuth();
			session.setAttribute("user_id", user_id);
			session.setAttribute("auth", auth);
			resp.sendRedirect("../admin/main.do");
		}
		else if(dao.isMember(user_id))
			JSFunction.alertBack(resp, "비밀번호가 틀렸습니다.");
		else 
			JSFunction.alertBack(resp, "해당 아이디가 존재하지 않습니다.");
		
		dao.close();
	}
}
