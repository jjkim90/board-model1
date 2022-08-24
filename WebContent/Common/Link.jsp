<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String id = (String) session.getAttribute("UserId");
%>
<table border="1" width="90%"> 
    <tr>
        <td align="center">
        <% if (id == null) { %>
        	<a href="../Board/SignUp.jsp">회원가입</a>
        	&nbsp;&nbsp;&nbsp;
            <a href="../Login/LoginForm.jsp">로그인</a>
        <% } else { %>
            <a href="../Login/Logout.jsp">로그아웃</a>
        <% } %>
            &nbsp;&nbsp;&nbsp; 
            <a href="../Board/List.jsp">게시판</a>
        <% if (id != null && id.equals("admin")) { %>
        	&nbsp;&nbsp;&nbsp;
        	<a href="../Board/Admin.jsp">관리자페이지</a>
       	<% } %>
        </td>
    </tr>
</table>
