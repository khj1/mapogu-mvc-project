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
import utils.JSFunction;

@WebServlet("/main/main.do") 
public class MainController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = new MemberDTO();
		HttpSession session = req.getSession();
		
		String loginStatus = "N";
		String user_id = "";
		if(session.getAttribute("user_id") != null) {
			user_id = session.getAttribute("user_id").toString();
			if(dao.isMember(user_id)) {
				loginStatus = "Y";
				dto = dao.getMemberInfo(user_id);
			}
		}
			
		req.setAttribute("dto", dto);
		req.setAttribute("loginStatus", loginStatus);
		req.getRequestDispatcher("/main/main.jsp").forward(req, resp);
	}
}
