<%@ page import="utils.JSFunction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String id = (String) session.getAttribute("UserId");
if (id == null || !id.equals("admin")) {
    JSFunction.alertLocation("관리자 아이디로 로그인 하세요.",
                             "../Login/LoginForm.jsp", out);
    return;
}
%>