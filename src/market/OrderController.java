package market;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.Authority;
import utils.JSFunction;

@WebServlet("/market/order.do")
public class OrderController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if(!Authority.isLogin(resp, session)) return;
		
		saveOrderInfo(req, resp);
	}
	
	protected void saveOrderInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String user_id = session.getAttribute("user_id").toString();
		String order_name = req.getParameter("order_name");
		String order_zipcode = req.getParameter("order_zipcode");
		String order_addr1 = req.getParameter("order_addr1");
		String order_addr2 = req.getParameter("order_addr2");
		String order_mobile1 = req.getParameter("order_mobile1");
		String order_mobile2 = req.getParameter("order_mobile2");
		String order_mobile3 = req.getParameter("order_mobile3");
		String order_email1 = req.getParameter("order_email1");
		String order_email2 = req.getParameter("order_email2");
		String receipt_name = req.getParameter("receipt_name");
		String receipt_zipcode = req.getParameter("receipt_zipcode");
		String receipt_addr1 = req.getParameter("receipt_addr1");
		String receipt_addr2 = req.getParameter("receipt_addr2");
		String receipt_mobile1 = req.getParameter("receipt_mobile1");
		String receipt_mobile2 = req.getParameter("receipt_mobile2");
		String receipt_mobile3 = req.getParameter("receipt_mobile3");
		String receipt_email1 = req.getParameter("receipt_email1");
		String receipt_email2 = req.getParameter("receipt_email2");
		String receipt_msg = req.getParameter("receipt_msg");
		String payment_type = req.getParameter("payment_type");
		int total_price = Integer.parseInt(req.getParameter("hidden_total_price")); 
		
		OrderDTO oDto = new OrderDTO();
		oDto.setId(user_id);
		oDto.setOrder_name(order_name); 
		oDto.setOrder_addr(order_zipcode + " " + order_addr1 + " " + order_addr2);
		oDto.setOrder_mobile(order_mobile1 + "-" + order_mobile2 + "-" + order_mobile3);
		oDto.setOrder_email(order_email1 + "@" + order_email2);
		oDto.setReceipt_name(receipt_name);
		oDto.setReceipt_addr(receipt_zipcode + " " + receipt_addr1 + " " + receipt_addr2);
		oDto.setReceipt_mobile(receipt_mobile1 + "-" + receipt_mobile2 + "-" + receipt_mobile3);
		oDto.setReceipt_email(receipt_email1 + "@" + receipt_email2);
		oDto.setReceipt_msg(receipt_msg);
		oDto.setPayment_type(payment_type);
		oDto.setTotal_price(total_price);
		
		OrderDAO oDao = new OrderDAO();
		int result = oDao.insertOrderInfo(oDto);
		oDao.close();
		
		if(result == 0) {
			JSFunction.alertLocation(resp, "결제에 실패했습니다.", "../market/list.do?flag=basket");
		}
		else {
			resp.sendRedirect("../market/list.do?flag=order_complete");
		}
	}
}
