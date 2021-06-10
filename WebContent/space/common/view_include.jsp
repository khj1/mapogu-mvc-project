<%@page import="community.CommunityDTO"%>
<%@page import="community.CommuinityDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String pageNum = request.getParameter("pageNum");
String board_idx = request.getParameter("board_idx");
String searchField = request.getParameter("searchField");
String searchWord = request.getParameter("searchWord");
String searchStr = "";
if(searchWord != null)
	searchStr = "searchField=" + searchField + "&searchWord=" + searchWord;

CommuinityDAO dao = new CommuinityDAO();
dao.updateVisitCount(board_idx);

CommunityDTO dto = dao.selectView(board_idx);
dto.setContent(dto.getContent().replaceAll("\r\n", "<br/>"));
pageContext.setAttribute("dto", dto);

dao.close();
%>