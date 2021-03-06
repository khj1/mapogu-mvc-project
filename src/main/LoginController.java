package main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.MemberDAO;
import member.MemberDTO;
import utils.CookieManager;
import utils.JSFunction;

@WebServlet("/main/login.do")
public class LoginController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		MemberDAO dao = new MemberDAO();
		String user_id = req.getParameter("user_id");
		String user_pass = req.getParameter("user_pass");
		String idSave = req.getParameter("idSave");
		
		
		if(dao.isMember(user_id, user_pass)) {
			if(idSave != null && idSave.equals("Y"))
				CookieManager.makeCookie(resp, "cookie_id", user_id, 86400*7);
			else
				CookieManager.deleteCookie(resp, "cookie_id");
			
			String auth = dao.getMemberInfo(user_id).getAuth();
			System.out.println(auth);
			session.setAttribute("user_id", user_id);
			session.setAttribute("auth", auth);
			resp.sendRedirect("../main/main.do");
		}
		else if(dao.isMember(user_id))
			JSFunction.alertBack(resp, "비밀번호가 틀렸습니다.");
		else 
			JSFunction.alertBack(resp, "해당 아이디가 존재하지 않습니다.");
	}
}
