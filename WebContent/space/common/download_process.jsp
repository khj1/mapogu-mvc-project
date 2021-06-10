<%@page import="fileupload.FileUtil"%>
<%@page import="community.CommuinityDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../include/isLogin.jsp" %>
<%
String ofile = request.getParameter("ofile");
String sfile = request.getParameter("sfile");
String board_idx = request.getParameter("board_idx");

out.clear();

// 파일 다운로드를 위한 메소드 호출
FileUtil.downloadFile(request, response, "/uploads", sfile, ofile);

// 다운로드 횟수 증가
CommuinityDAO dao = new CommuinityDAO();
dao.updateDownCount(board_idx);

dao.close();
%>