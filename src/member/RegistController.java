package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberDAO;
import member.MemberDTO;
import utils.JSFunction;

@WebServlet("/member/regist.do")
public class RegistController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// name id pass tel mobile email open_email address
		MemberDTO dto = new MemberDTO();
		dto.setName(req.getParameter("name"));
		dto.setId(req.getParameter("id"));
		dto.setPass(req.getParameter("pass"));
		dto.setTel(req.getParameter("tel1") + "-" + req.getParameter("tel2") + "-" + req.getParameter("tel3"));
		dto.setMobile(req.getParameter("mobile1") + "-" + req.getParameter("mobile2") + "-" + req.getParameter("mobile3"));
		dto.setEmail(req.getParameter("email_1") + "@" + req.getParameter("email_2"));
		dto.setOpen_email(req.getParameter("open_email"));
		dto.setAddress(req.getParameter("zip") + " " + req.getParameter("addr1") + " " + req.getParameter("addr2"));
		
		MemberDAO dao = new MemberDAO();
		int result = dao.insertMember(dto);
		if(result == 0) 
			JSFunction.alertBack(resp, "회원가입에 실패했습니다.");
		else 
			JSFunction.alertLocation(resp, dto.getName() + "님 회원이 되신걸 축하드립니다!", "../main/main.do");
	}
}
