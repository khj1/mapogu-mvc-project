<%@page import="fileupload.FileUtil"%>
<%@page import="utils.JSFunction"%>
<%@page import="community.CommunityDTO"%>
<%@page import="community.CommuinityDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../include/isLogin.jsp" %>
<%
String board_idx = request.getParameter("board_idx");
String board = request.getParameter("board");
CommuinityDAO dao = new CommuinityDAO();

int result = dao.deletePost(board_idx);
dao.close();

if(result != 0){
	String saveFileName = request.getParameter("sfile");
	FileUtil.deleteFile(request, "/uploads", saveFileName);
	JSFunction.alertLocation("게시물이 삭제되었습니다.", "../" + board + ".jsp", out);
}
else
	JSFunction.alertBack("게시물을 삭제하지 못했습니다.", out);
%>