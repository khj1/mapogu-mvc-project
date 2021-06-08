<%@page import="utils.JSFunction"%>
<%@page import="member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
if(session.getAttribute("user_id") == null){
	JSFunction.alertLocation("로그인 후 이용해주세요", "../member/login.jsp", out);
	return;
}
%>
