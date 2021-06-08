<%@page import="member.MemberDAO"%>
<%@page import="member.MemberDTO"%>
<%@page import="utils.JSFunction"%>
<%@page import="board.BoardDAO"%>
<%@page import="fileupload.FileUtil"%>
<%@page import="board.BoardDTO"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%
String saveDirectory = application.getRealPath("/uploads");
int maxPostSize = 1024 * 1000;

MultipartRequest mr = FileUtil.uploadFile(request, saveDirectory, maxPostSize);
if(mr != null) {
	String id = session.getAttribute("user_id").toString();
	String board = mr.getParameter("board");
	String content = mr.getParameter("content");
	String title = mr.getParameter("title");
	String pass = mr.getParameter("pass");
	String flag = mr.getParameter("flag");
	
	BoardDTO bDto = new BoardDTO();
	bDto.setId(id);
	bDto.setContent(content);
	bDto.setTitle(title);
	bDto.setPass(pass);
	bDto.setFlag(flag);
	
	// 파일 이름 변경 처리
	String fileName = mr.getFilesystemName("ofile");
	if(fileName != null) {
		String newFileName = FileUtil.renameFile(fileName, saveDirectory);
		
		bDto.setOfile(fileName); 
		bDto.setSfile(newFileName);
	}
		
	BoardDAO bDao = new BoardDAO();
	int result = bDao.insertWrite(bDto);
	bDao.close();
	
	if(result == 1) {
		response.sendRedirect("../" + board + ".jsp");
	}
	else {
		response.sendRedirect("../" + board + "_write.jsp");
	}
}
else {
	JSFunction.alertBack("글 작성 중 오류가 발생했습니다.", out);
}
%>