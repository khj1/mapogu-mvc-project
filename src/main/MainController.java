package main;

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
import utils.CookieManager;

@WebServlet("/main/main.do") 
public class MainController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberDAO mDao = new MemberDAO();
		MemberDTO mDto = new MemberDTO();
		
		// 로그인 여부 확인
		HttpSession session = req.getSession();
		if(session.getAttribute("user_id") != null) {
			String user_id = session.getAttribute("user_id").toString();
			mDto = mDao.getMemberInfo(user_id);
		}
		mDao.close();
		req.setAttribute("dto", mDto);
		
		
		// 쿠키 정보 확인
		String idSaveCheck = "";
		String cookie_id = CookieManager.readCookies(req, "cookie_id");
		if(!cookie_id.equals("")) {
			idSaveCheck = "checked";
		}
		req.setAttribute("cookie_id", cookie_id);
		req.setAttribute("idSaveCheck", idSaveCheck);
		
		
		// 게시물 미리보기 기능
		CommuinityDAO bDao = new CommuinityDAO();
		List<CommunityDTO> noticeList = bDao.previewList("notice");
		List<CommunityDTO> freeList = bDao.previewList("free");
		List<CommunityDTO> photoList = bDao.previewList("photo");
		
		req.setAttribute("noticeList", noticeList);
		req.setAttribute("freeList", freeList);
		req.setAttribute("photoList", photoList);
		
		req.getRequestDispatcher("/main/main.jsp").forward(req, resp);
	}
}
