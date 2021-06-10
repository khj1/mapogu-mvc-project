package market;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/market/view.do")
public class ViewController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String flag = req.getParameter("flag");
		String pageNum = req.getParameter("pageNum");
		String product_idx = req.getParameter("product_idx");
		String searchField = req.getParameter("searchField");
		String searchWord = req.getParameter("searchWord");
		String searchStr = "";
		if(searchWord != null)
			searchStr = "searchField=" + searchField + "&searchWord=" + searchWord;

		ProductDAO dao = new ProductDAO();

		ProductDTO dto = dao.getProductInfo(product_idx);
		dto.setDescription(dto.getDescription().replaceAll("\r\n", "<br/>"));
		req.setAttribute("dto", dto);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("searchStr", searchStr);
		req.setAttribute("flag", flag);
		req.getRequestDispatcher("/market/"+ flag +"_view.jsp").forward(req, resp);

		dao.close();
	}
}
