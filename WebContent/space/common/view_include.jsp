<%@page import="board.BoardDTO"%>
<%@page import="board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String pageNum = request.getParameter("pageNum");
String board_idx = request.getParameter("board_idx");
String searchField = request.getParameter("searchField");
String searchWord = request.getParameter("searchWord");
String searchStr = "";
if(searchWord != null)
	searchStr = "searchField=" + searchField + "&searchWord" + searchWord;

BoardDAO dao = new BoardDAO();
dao.updateVisitCount(board_idx);

BoardDTO dto = dao.selectView(board_idx);
pageContext.setAttribute("dto", dto);
%>