package community;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fileupload.FileUtil;
import utils.Authority;
import utils.JSFunction;

@WebServlet("/community/delete.do")
public class DeleteController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String board_idx = req.getParameter("board_idx");
		CommuinityDAO dao = new CommuinityDAO();
		
		HttpSession session = req.getSession();
		if(!Authority.isLogin(resp, session)) return;
		if(!Authority.isWriter(resp, session, board_idx)) return;
		
		doGet(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String flag = req.getParameter("flag");
		String board_idx = req.getParameter("board_idx");
		CommuinityDAO dao = new CommuinityDAO();

		int result = dao.deletePost(board_idx);
		dao.close();

		if(result != 0){
			String saveFileName = req.getParameter("sfile");
			FileUtil.deleteFile(req, "/uploads", saveFileName);
			JSFunction.alertLocation(resp, "게시물이 삭제되었습니다.", "../community/list.do?flag=" + flag );
		}
		else
			JSFunction.alertBack(resp, "게시물을 삭제하지 못했습니다.");
	}
}
