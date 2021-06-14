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

@WebServlet("/admin/view.do")
public class ViewController extends HttpServlet{
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
			showMemberView(req, resp);
		else if(flag.equals("request"))
			showRequestView(req,resp);
		else if(flag.equals("board"))
			showBoardView(req, resp);
	}

	private void showBoardView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String flag = req.getParameter("flag");
		String type = req.getParameter("type");
		String board_idx = req.getParameter("board_idx");
		
		CommuinityDAO cDao = new CommuinityDAO();
		CommunityDTO cDto = cDao.selectView(board_idx);
		
		req.setAttribute("flag", flag);
		req.setAttribute("type", type);
		req.setAttribute("dto", cDto);
		req.getRequestDispatcher("/admin/board/" + type + "_view.jsp").forward(req, resp);
	}

	private void showRequestView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String flag = req.getParameter("flag");
		String type = req.getParameter("type");
		String idx = req.getParameter("idx");
		
		if(type.equals("clean")) {
			CleanDAO cDao = new CleanDAO();
			CleanDTO cDto = cDao.selectView(idx);
			req.setAttribute("dto", cDto);
		}
		else if(type.equals("field")) {
			FieldDAO fDao = new FieldDAO();
			FieldDTO fDto = fDao.selectView(idx);
			req.setAttribute("dto", fDto);
		}
		
		req.setAttribute("flag", flag);
		req.setAttribute("type", type);
		req.getRequestDispatcher("/admin/request/" + type + "_view.jsp").forward(req, resp);
	}

	private void showMemberView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String id = req.getParameter("id");
		
		MemberDAO mDao = new MemberDAO();
		MemberDTO mDto = mDao.getMemberInfo(id);
		
		req.setAttribute("id", id);
		req.setAttribute("dto", mDto);
		req.getRequestDispatcher("/admin/member/member_view.jsp").forward(req, resp);
	}
}
