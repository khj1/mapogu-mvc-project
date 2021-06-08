package member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import mail.MailSend;

@WebServlet("*.find")
public class InfoFindController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		int lastSlash = uri.lastIndexOf("/");
		String commandStr = uri.substring(lastSlash);
		if(commandStr.equals("/id.find"))
			idFind(req, resp);
		else if(commandStr.equals("/pw.find"))
			pwFind(req, resp);
	}

	private void idFind(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberDAO dao = new MemberDAO();
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		
		System.out.println(name + " " + email);
		
		String result = dao.findId(name, email);
		dao.close();
		
		if(result == null)
			result = "입력하신 정보에 해당하는 아이디가 존재하지 않습니다.";
		else {
			result = name + "님의 아이디: " + result;
		}
		
		JSONObject json = new JSONObject();
		json.put("result", result);
		
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.write(json.toString());
	}

	private void pwFind(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberDAO dao = new MemberDAO();
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		
		String result = dao.findPass(id, name, email);
		dao.close();
		
		if(result == null)
			result = "입력하신 내용과 일치하는 정보가 없습니다.";
		else {
			MailSend.sendPass(name, email, result);
			result = "회원님의 이메일로 비밀번호를 전송했습니다!";
		}
		
		JSONObject json = new JSONObject();
		json.put("result", result);
		
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.write(json.toString());
	}
}
