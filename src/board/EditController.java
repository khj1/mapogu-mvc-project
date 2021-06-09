package board;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import common.BoardConfig;
import fileupload.FileUtil;
import utils.BoardPage;
import utils.JSFunction;

@WebServlet("/community/edit.do")  
public class EditController extends HttpServlet implements BoardConfig{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String board = req.getParameter("board");
		String pageNum = req.getParameter("pageNum");
		String board_idx = req.getParameter("board_idx");
		String searchField = req.getParameter("searchField");
		String searchWord = req.getParameter("searchWord");
		String searchStr = "";
		if(searchWord != null)
			searchStr = "searchField=" + searchField + "&searchWord" + searchWord;

		BoardDAO bDao = new BoardDAO();
		BoardDTO bDto = bDao.selectView(board_idx);
		req.setAttribute("bDto", bDto);
		req.setAttribute("pageNum", bDto);
		req.setAttribute("board", board);
		
		req.getRequestDispatcher("/community/" + board + "_edit.jsp").forward(req, resp);;
		bDao.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String saveDirectory = req.getServletContext().getRealPath("/uploads");
		int maxPostSize = 1024 * 1000;

		MultipartRequest mr = FileUtil.uploadFile(req, saveDirectory, maxPostSize);
		if(mr != null) {
			String pass = mr.getParameter("pass");
			String flag = mr.getParameter("flag");
			String board = mr.getParameter("board");
			String title = mr.getParameter("title");
			String content = mr.getParameter("content");
			String pageNum = mr.getParameter("pageNum");
			String board_idx = mr.getParameter("board_idx");
			String prevOfile = mr.getParameter("prevOfile");
			String prevSfile = mr.getParameter("prevSfile");
			
			BoardDTO bDto = new BoardDTO();
			bDto.setBoard_idx(board_idx);
			bDto.setContent(content);
			bDto.setTitle(title);
			bDto.setPass(pass);
			
			String fileName = mr.getFilesystemName("ofile");
			if(fileName != null) {
				String newFileName = FileUtil.renameFile(fileName, saveDirectory);
				
				bDto.setOfile(fileName);
				bDto.setSfile(newFileName);
				
				FileUtil.deleteFile(req, "/uploads", prevSfile);
			}
			else {
				 bDto.setOfile(prevOfile);
				 bDto.setSfile(prevSfile);
			}
				
			BoardDAO bDao = new BoardDAO();
			int result = bDao.updateEdit(bDto);
			bDao.close();
			
			if(result == 1) {
				resp.sendRedirect("../community/view.do?board=" + board + "&board_idx=" + board_idx + "&pageNum=" + pageNum);
			}
			else {
				JSFunction.alertBack(resp, "글을 수정하지 못했습니다.");
			}
		}
		else {
			JSFunction.alertBack(resp, "글 수정 중 오류가 발생하였습니다.");
		}
	}
}
