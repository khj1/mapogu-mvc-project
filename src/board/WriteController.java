package board;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import common.BoardConfig;
import fileupload.FileUtil;
import member.MemberDAO;
import member.MemberDTO;
import utils.BoardPage;
import utils.JSFunction;

@WebServlet("/community/write.do") 
public class WriteController extends HttpServlet implements BoardConfig{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String flag = req.getParameter("flag");
		String board = req.getParameter("board");
		
		HttpSession session = req.getSession();
		if(session.getAttribute("user_id") == null) {
			JSFunction.alertLocation(resp, "로그인 후 이용해주세요.", "../member/login.jsp");
			return;
		}
		String id = session.getAttribute("user_id").toString();
		MemberDAO mDao = new MemberDAO();
		MemberDTO mDto = mDao.getMemberInfo(id);
		req.setAttribute("mDto", mDto);
		req.setAttribute("flag", flag);
		req.setAttribute("board", board);
		mDao.close();
		
		req.getRequestDispatcher("/community/" + board + "_write.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		ServletContext application = req.getServletContext();
		String saveDirectory = application.getRealPath("/uploads");
		int maxPostSize = 1024 * 1000;

		MultipartRequest mr = FileUtil.uploadFile(req, saveDirectory, maxPostSize);
		if(mr != null) {
			String id = session.getAttribute("user_id").toString();
			String board = mr.getParameter("board");
			String content = mr.getParameter("content");
			String title = mr.getParameter("title");
			String pass = mr.getParameter("pass");
			String flag = mr.getParameter("flag");
			
			BoardDTO bDto = new BoardDTO();
			bDto.setId(id);
			bDto.setContent(content);
			bDto.setTitle(title);
			bDto.setPass(pass);
			bDto.setFlag(flag);
			
			// 파일 이름 변경 처리
			String fileName = mr.getFilesystemName("ofile");
			if(fileName != null) {
				String newFileName = FileUtil.renameFile(fileName, saveDirectory);
				
				bDto.setOfile(fileName); 
				bDto.setSfile(newFileName);
			}
				
			BoardDAO bDao = new BoardDAO();
			int result = bDao.insertWrite(bDto);
			bDao.close();
			
			if(result == 1) {
				resp.sendRedirect("../community/list.do?board=" + board + "&flag=" + flag);
			}
			else {
				JSFunction.alertBack(resp, "게시물을 업로드하지 못했습니다.");
			}
		}
		else {
			JSFunction.alertBack(resp, "글 작성 중 오류가 발생했습니다.");
		}
	}
}
