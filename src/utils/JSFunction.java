package utils;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;

public class JSFunction {
	public static void alertLocation(String msg, String url, JspWriter out) {
		try {
			String str = ""
					+ "<script>"
					+ "	  alert('"+msg+"');	"
					+ "	  location.href='"+url+"';	"
					+ "</script>";
			out.println(str);
		}
		catch(Exception e) {}
	}
	
	public static void alertLocation(HttpServletResponse resp ,String msg, String url) {
		String str = "";
		try {
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = resp.getWriter();
			str = "<script>"
				+ "	  alert('"+msg+"');	"
				+ "	  location.href='"+url+"';	"
				+ "</script>";
			writer.println(str);
		}
		catch(Exception e) {}
	}
	
	public static void alertBack(String msg, JspWriter out) {
		try {
			String str = ""
					+ "<script>"
					+ "	  alert('"+msg+"');	"
					+ "	  history.back();	"
//					      history.go(-1);
					+ "</script>";
			out.println(str);
		}
		catch(Exception e) {}
	}
	
	public static void alertBack(HttpServletResponse resp, String msg) {
		try {
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = resp.getWriter();
			String str = ""
					+ "<script>"
					+ "	  alert('"+msg+"');	"
					+ "	  history.back();	"
					+ "</script>";
			writer.println(str);
		}
		catch(Exception e) {}
	}
	
	// location.reload() : JS 에서 F5(새로고침)을 누른것 처럼 페이지에 대한 새로고침을 해준다.
	public static void alertOpenerReloadClose(HttpServletResponse resp, String msg) {
		String str = "";
		try {
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = resp.getWriter();
			str = ""
				+ "<script>"
				+ "	  alert('"+msg+"');	"
				+ "	  opener.location.reload();	"
				+ "	  self.close()"
				+ "</script>";
			writer.println(str);
		}
		catch(Exception e) {}
		
	}
}
