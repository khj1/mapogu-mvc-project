package calendar;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import community.CommunityDTO;
import utils.CalendarPrint;

@WebServlet("*.cal")
public class CalendarController extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String URI = req.getRequestURI();
		int lastSlash = URI.lastIndexOf("/");
		String commandStr = URI.substring(lastSlash);
		
		if(commandStr.equals("/preview.cal")) {
			previewCalendar(req, resp);
		}
		else if(commandStr.equals("/list.cal")) {
			showCalendarList(req, resp);
		}
	}
	



	protected void previewCalendar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		try {
			//캘린더 게시판 미리보기 기능
			int year = Integer.parseInt(req.getParameter("year"));
			int month = Integer.parseInt(req.getParameter("month"));
			
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.write(CalendarPrint.printCalendar(year, month));
		}
		catch (Exception e) {
			System.out.println("캘린더 미리보기 중 예외 발생");
			e.printStackTrace();
		}
	}
	
	private void showCalendarList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String yearStr = req.getParameter("year");
		String monthStr = req.getParameter("month");
		
		int year = Integer.parseInt(yearStr);
		int month = Integer.parseInt(monthStr);
		
		CalendarDAO cDao = new CalendarDAO();
		JSONArray jsonArr = cDao.selectList(year, month);
		
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.write(jsonArr.toString());
	}
}
