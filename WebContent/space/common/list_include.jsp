<%@page import="utils.BoardPage"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="common.BoardConfig"%>
<%@page import="board.BoardDTO"%>
<%@page import="java.util.List"%>
<%@page import="board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
BoardDAO dao = new BoardDAO();
Map<String, Object> map = new HashMap<String, Object>(); 

String searchField = request.getParameter("searchField");
String searchWord = request.getParameter("searchWord");
String searchStr = "";
if(searchWord != null){
	map.put("searchField", searchField);
	map.put("searchWord", searchWord);
	searchStr = "searchField=" + searchField + "&searchWord=" + searchWord;
}

int totalCount = dao.countList(flag);

int pageNum = 1;
String pageTemp = request.getParameter("pageNum");
if(pageTemp != null && !pageTemp.equals(""))
	pageNum = Integer.parseInt(pageTemp);

int pageSize = BoardConfig.PAGE_PER_SIZE;
int blockPage = BoardConfig.PAGE_PER_BLOCK;
int totalPage = (int)Math.ceil(totalCount / pageSize);
int start = (pageNum - 1) * pageSize;

map.put("searchField", searchField);
map.put("searchWord", searchWord);
map.put("flag", flag);
map.put("start", start);
map.put("pageSize", pageSize);
List<BoardDTO> boardList = dao.selectList(map);
pageContext.setAttribute("boardList", boardList);
pageContext.setAttribute("searchStr", searchStr);
pageContext.setAttribute("totalCount", totalCount);
pageContext.setAttribute("start", start);
%>