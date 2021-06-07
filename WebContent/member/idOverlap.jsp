<%@page import="member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%
String id = request.getParameter("id");
System.out.println(id);
MemberDAO dao = new MemberDAO(); 
String result = "N";
if(dao.isMember(id))
	result = "Y";
dao.close();
%>
{"result" : "<%=result %>"} 
