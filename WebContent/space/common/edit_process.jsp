<%@page import="member.MemberDAO"%>
<%@page import="member.MemberDTO"%>
<%@page import="utils.JSFunction"%>
<%@page import="community.CommuinityDAO"%>
<%@page import="fileupload.FileUtil"%>
<%@page import="community.CommunityDTO"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="../../include/isLogin.jsp" %>
<%
String saveDirectory = request.getServletContext().getRealPath("/uploads");
int maxPostSize = 1024 * 1000;

MultipartRequest mr = FileUtil.uploadFile(request, saveDirectory, maxPostSize);
if(mr != null) {
	String pass = mr.getParameter("pass");
	String board = mr.getParameter("board");
	String title = mr.getParameter("title");
	String content = mr.getParameter("content");
	String pageNum = mr.getParameter("pageNum");
	String board_idx = mr.getParameter("board_idx");
	String prevOfile = mr.getParameter("prevOfile");
	String prevSfile = mr.getParameter("prevSfile");
	
	CommunityDTO bDto = new CommunityDTO();
	bDto.setBoard_idx(board_idx);
	bDto.setContent(content);
	bDto.setTitle(title);
	bDto.setPass(pass);
	
	String fileName = mr.getFilesystemName("ofile");
	if(fileName != null) {
		String newFileName = FileUtil.renameFile(fileName, saveDirectory);
		
		bDto.setOfile(fileName);
		bDto.setSfile(newFileName);
		
		FileUtil.deleteFile(request, "/uploads", prevSfile);
	}
	else {
		 bDto.setOfile(prevOfile);
		 bDto.setSfile(prevSfile);
	}
		
	CommuinityDAO bDao = new CommuinityDAO();
	int result = bDao.updateEdit(bDto);
	bDao.close();
	
	if(result == 1) {
		response.sendRedirect("../" + board + "_view.jsp?board_idx=" + board_idx + "&pageNum=" + pageNum);
	}
	else {
		JSFunction.alertBack(response, "글을 수정하지 못했습니다.");
	}
}
else {
	JSFunction.alertBack(response, "글 수정 중 오류가 발생하였습니다.");
}
%>