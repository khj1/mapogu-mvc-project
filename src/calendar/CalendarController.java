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
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		String yearStr = req.getParameter("year");
		String monthStr = req.getParameter("month");
		
		if(yearStr != null && monthStr != null){
			year = Integer.parseInt(yearStr);
			month = Integer.parseInt(monthStr);
		}
		
		CalendarDAO cDao = new CalendarDAO();
		List<CommunityDTO> cList = cDao.selectList(year, month);
		
		req.setAttribute("cList", cList);
		req.setAttribute("year", year);
		req.setAttribute("month", month);
		
		req.getRequestDispatcher("/space/sub02.jsp").forward(req, resp);
	}
}
