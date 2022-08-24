<%@ page import="model1.board.BoardDAO"%>
<%@ page import="model1.board.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./IsAdmin.jsp"%>
<%
String deleteId = request.getParameter("id"); 

BoardDTO dto = new BoardDTO();
BoardDAO dao = new BoardDAO(application);
dto = dao.selectMember(deleteId);

String sessionId = session.getAttribute("UserId").toString(); 

int delResult = 0;

if (sessionId.equals("admin")) { 
    dto.setId(deleteId);   
    delResult = dao.deleteMember(dto); 
    dao.close();

    if (delResult == 1) { 
        JSFunction.alertLocation("해당 회원이 탈퇴되었습니다.", "Admin.jsp", out); 
    } else {
        JSFunction.alertBack("탈퇴에 실패하였습니다. \\n게시물이 남아 있는 회원은 탈퇴시킬 수 없습니다.", out);
    } 
} 
else { 
    JSFunction.alertBack("관리자만 회원탈퇴를 할 수 있습니다.", out);

    return;
}
%>