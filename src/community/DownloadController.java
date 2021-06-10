package community;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;

import common.BoardConfig;
import fileupload.FileUtil;

@WebServlet("/community/download.do") 
public class DownloadController extends HttpServlet implements BoardConfig{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String ofile = req.getParameter("ofile");
		String sfile = req.getParameter("sfile");
		String board_idx = req.getParameter("board_idx");

		FileUtil.downloadFile(req, resp, "/uploads", sfile, ofile);

		CommuinityDAO dao = new CommuinityDAO();
		dao.updateDownCount(board_idx);

		dao.close();
	}
}
