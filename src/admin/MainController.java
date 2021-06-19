package admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.Authority;
import utils.JSFunction;

@WebServlet("/admin/main.do")
public class MainController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		if(!Authority.isLogin_admin(resp, session)) return;
		else {
			String id = session.getAttribute("user_id").toString();  
			if(!id.equals("administrator")) {
				JSFunction.alertLocation(resp, "권한이 없습니다.", "../main/main.do");
			}
			else {
				req.getRequestDispatcher("/admin/main.jsp").forward(req, resp);
			}
		}
	}
}
