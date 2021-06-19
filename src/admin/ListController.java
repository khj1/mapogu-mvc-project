package admin;

import java.io.IOException;
import java.util.List;

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

@WebServlet("/admin/list.do")
public class ListController extends HttpServlet{
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
		
		String flag = req.getParameter("flag");
		if(flag.equals("member"))
			showMemberList(req, resp);
		else if(flag.equals("request"))
			showRequestList(req,resp);
		else if(flag.equals("board"))
			showBoardList(req, resp);
	}

	private void showBoardList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String flag = req.getParameter("flag");
		String type = req.getParameter("type");
		
		CommuinityDAO cDao = new CommuinityDAO();
		List<CommunityDTO> list = cDao.selectAllList(type);
		
		req.setAttribute("flag", flag);
		req.setAttribute("type", type);
		req.setAttribute("list", list);
		req.getRequestDispatcher("/admin/board/" + type + ".jsp").forward(req, resp);
	}

	private void showRequestList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String flag = req.getParameter("flag");
		String type = req.getParameter("type");
		
		if(type.equals("clean")) {
			CleanDAO cDao = new CleanDAO();
			List<CleanDTO> clist = cDao.getAllRequestInfo();
			req.setAttribute("list", clist);
		}
		else if(type.equals("field")) {
			FieldDAO fDao = new FieldDAO();
			List<FieldDTO> flist = fDao.getAllRequestInfo();
			req.setAttribute("list", flist);
		}
		
		req.setAttribute("flag", flag);
		req.setAttribute("type", type);
		req.getRequestDispatcher("/admin/request/" + type + ".jsp").forward(req, resp);
	}

	private void showMemberList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		MemberDAO mDao = new MemberDAO();
		List<MemberDTO> list = mDao.getAllMemberInfo();
		
		req.setAttribute("list", list);
		req.getRequestDispatcher("/admin/member/member.jsp").forward(req, resp);
	}
}
