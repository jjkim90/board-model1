<%@page import="utils.JSFunction"%>
<%@page import="model1.board.BoardDAO"%>
<%@page import="model1.board.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
// 폼값 받기
String id = request.getParameter("id");
String pass = request.getParameter("pass");
String name = request.getParameter("name");

//폼값을 DTO 객체에 저장
BoardDTO dto = new BoardDTO();
dto.setId(id);
dto.setPass(pass);
dto.setName(name);

//DAO 객체를 통해 DB에 DTO 저장
BoardDAO dao = new BoardDAO(application);
int iResult = dao.insertMember(dto);
dao.close();

//프로세스 성공or실패 시 처리
if (iResult == 1) {
	// 로그인 처리.
	session.setAttribute("UserId", id);
	session.setAttribute("UserName", name);
	
	// 회원가입 축하 메시지 추가하면서 LoginForm.jsp 포워딩
	request.setAttribute("SignUpSuccessMsg", "회원가입을 진심으로 축하합니다!!");
	request.getRequestDispatcher("../Login/LoginForm.jsp").forward(request, response);

} else {
 JSFunction.alertBack("글쓰기에 실패하였습니다.", out);
}
%>

