package utils;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import community.CommuinityDAO;

public class Authority {
	public static boolean isLogin(HttpServletResponse resp, HttpSession session) {
		if(session.getAttribute("user_id") == null) {
			JSFunction.alertLocation(resp, "로그인 후 이용해주세요.", "../member/login.jsp");
			return false;
		}
		return true;
	}
	
	public static boolean checkAuth (HttpServletResponse resp, HttpSession session, String flag) {
		String auth = session.getAttribute("auth").toString();
		if(flag.equals("data") && auth.equals("com")) {
			JSFunction.alertBack(resp, "권한이 없습니다.");
			return false;
		}
		return true;
	}
	
	public static boolean isWriter(HttpServletResponse resp, HttpSession session, String board_idx) {
		CommuinityDAO dao = new CommuinityDAO();
		String user_id = session.getAttribute("user_id").toString();
		if(!dao.isWriter(board_idx, user_id)) {
			JSFunction.alertBack(resp, "권한이 없습니다.");
			return false;
		}
		return true;
	}
}
