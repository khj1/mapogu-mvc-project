package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.MemberDAO;
import utils.CookieManager;
import utils.JSFunction;

@WebServlet("/member/loginPage.do")
public class LoginPageController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idSaveCheck = "";
		String cookie_id = CookieManager.readCookies(req, "cookie_id");
		if(!cookie_id.equals("")) {
			idSaveCheck = "checked";
		}
		req.setAttribute("cookie_id", cookie_id);
		req.setAttribute("idSaveCheck", idSaveCheck);
		
		req.getRequestDispatcher("/member/login.jsp").forward(req, resp);
	}
}
