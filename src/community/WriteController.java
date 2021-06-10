package community;

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
import utils.Authority;
import utils.BoardPage;
import utils.JSFunction;

@WebServlet("/community/write.do") 
public class WriteController extends HttpServlet implements BoardConfig{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		if(!Authority.isLogin(resp, session)) return;
		
		if(req.getMethod().equals("GET"))
			doGet(req, resp);
		else if(req.getMethod().equals("POST"))
			doPost(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String flag = req.getParameter("flag");
		String id = session.getAttribute("user_id").toString();
		
		// 권한 인증
		if(!Authority.checkAuth(resp, session, flag)) return;
		
		MemberDAO mDao = new MemberDAO();
		MemberDTO mDto = mDao.getMemberInfo(id); 
		req.setAttribute("mDto", mDto);
		req.setAttribute("flag", flag);
		mDao.close();
		
		req.getRequestDispatcher("/community/" + flag + "_write.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext application = req.getServletContext();
		HttpSession session = req.getSession();
		String saveDirectory = application.getRealPath("/uploads");
		int maxPostSize = 1024 * 1000;

		MultipartRequest mr = FileUtil.uploadFile(req, saveDirectory, maxPostSize);
		if(mr != null) {
			String flag = mr.getParameter("flag");
			String id = session.getAttribute("user_id").toString();
			String content = mr.getParameter("content");
			String title = mr.getParameter("title");
			String pass = mr.getParameter("pass");
			
			// 권한 인증
			if(!Authority.checkAuth(resp, session, flag)) return;
			
			CommunityDTO bDto = new CommunityDTO();
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
				
			CommuinityDAO bDao = new CommuinityDAO();
			int result = bDao.insertWrite(bDto);
			bDao.close();
			
			if(result == 1) {
				resp.sendRedirect("../community/list.do?flag=" + flag);
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
