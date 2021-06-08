<%@page import="member.MemberDTO"%>
<%@page import="member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String id = session.getAttribute("user_id").toString();
MemberDAO mDao = new MemberDAO();
MemberDTO mDto = mDao.getMemberInfo(id);
pageContext.setAttribute("mDto", mDto);
mDao.close();
%>