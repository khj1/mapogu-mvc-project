package market;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import utils.Authority;
import utils.Mail;

@WebServlet("*.req")
public class RequestController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if(!Authority.isLogin(resp, session)) return;
		
		String URI = req.getRequestURI();
		int lastSlash = URI.lastIndexOf("/");
		String commandStr = URI.substring(lastSlash);
		
		if(req.getMethod().equals("GET")) {
			if(commandStr.equals("/clean.req"))
				req.getRequestDispatcher("/market/clean.jsp").forward(req, resp);
			else if(commandStr.equals("/field.req"))
				req.getRequestDispatcher("/market/field.jsp").forward(req, resp);
		}
		else if(req.getMethod().equals("POST")) {
			try {
				if(commandStr.equals("/clean.req"))
					insertCleanRequest(req, resp);
				else if(commandStr.equals("/field.req"))
					insertFieldRequest(req, resp);
			}
			catch (AddressException e) {
				System.out.println("이메일 전송 중 예외 발생");
				e.printStackTrace();
			}
		}
	}

	private void insertCleanRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, AddressException{
		String name = req.getParameter("name");
		String addr = req.getParameter("addr");
		String tel1 = req.getParameter("tel1");
		String tel2 = req.getParameter("tel2");
		String tel3 = req.getParameter("tel3");
		String mobile1 = req.getParameter("mobile1");
		String mobile2 = req.getParameter("mobile2");
		String mobile3 = req.getParameter("mobile3");
		String email1 = req.getParameter("email1");
		String email2 = req.getParameter("email2");
		String cleanType = req.getParameter("cleanType");
		String acreage = req.getParameter("acreage");
		String reqDate = req.getParameter("reqDate");
		String receiptType = req.getParameter("receiptType");
		String etc = req.getParameter("etc");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", name);
		map.put("addr", addr);
		map.put("tel", tel1 + "-" + tel2 + "-" + tel3);
		map.put("mobile", mobile1 + "-" + mobile2 + "-" + mobile3);
		map.put("email", email1 + "@" + email2);
		map.put("cleanType", cleanType);
		map.put("acreage", acreage);
		map.put("reqDate", reqDate);
		map.put("receiptType", receiptType);
		map.put("etc", etc);
		map.put("flag", "clean");
		
		RequestDAO dao = new RequestDAO();
		int result = dao.insertCleaningRequest(map);
		
		Mail.sendRuest_clean(map);
		sendJson(req, resp, result, map);
	}

	private void insertFieldRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, AddressException{
		String name = req.getParameter("name");
		String isdis = req.getParameter("isdis");
		String disType = req.getParameter("disType");
		String useDev = req.getParameter("useDev");
		String devType = req.getParameter("typeDev");
		String tel1 = req.getParameter("tel1");
		String tel2 = req.getParameter("tel2");
		String tel3 = req.getParameter("tel3");
		String mobile1 = req.getParameter("mobile1");
		String mobile2 = req.getParameter("mobile2");
		String mobile3 = req.getParameter("mobile3");
		String email1 = req.getParameter("email1");
		String email2 = req.getParameter("email2");
		String cake = req.getParameter("cake");
		String cookie = req.getParameter("cookie");
		String reqDate = req.getParameter("reqDate");
		String receiptType = req.getParameter("receiptType");
		String etc = req.getParameter("etc");
		
		if(disType == null)
			disType = "";
		if(devType == null)
			devType = "";
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", name);
		map.put("isdis", isdis);
		map.put("disType", disType);
		map.put("useDev", useDev);
		map.put("devType", devType);
		map.put("tel", tel1 + "-" + tel2 + "-" + tel3);
		map.put("mobile", mobile1 + "-" + mobile2 + "-" + mobile3);
		map.put("email", email1 + "@" + email2);
		map.put("cake", cake);
		map.put("cookie", cookie);
		map.put("reqDate", reqDate);
		map.put("receiptType", receiptType);
		map.put("etc", etc);
		map.put("flag", "field");
		
		RequestDAO dao = new RequestDAO();
		int result = dao.insertFieldRequest(map);
		
		Mail.sendRuest_field(map);
		sendJson(req, resp, result, map);
	}

	private void sendJson(HttpServletRequest req, HttpServletResponse resp, int result, Map<String, String> map) throws ServletException, IOException{
		String msg = "";
		String url = "";
		String flag = map.get("flag").toString();
		
		if(result != 0) {
			msg = "신청이 완료되었습니다.";
			url = "../main/main.do";
		}
		else {
			msg = "신청하지 못했습니다.";
			url = "../market/" + flag + ".req";
		}
		
		JSONObject json = new JSONObject();
		json.put("msg", msg);
		json.put("url", url);
		
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.write(json.toString());
	}
}
