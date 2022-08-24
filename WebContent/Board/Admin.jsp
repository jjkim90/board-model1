<%@ page import="java.util.List"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="model1.board.BoardDAO"%>
<%@ page import="model1.board.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./IsAdmin.jsp"%>
<%
BoardDAO dao = new BoardDAO(application);

Map<String, Object> param = new HashMap<String, Object>(); 
String searchField = request.getParameter("searchField");
String searchWord = request.getParameter("searchWord");
if (searchWord != null) {
    param.put("searchField", searchField);
    param.put("searchWord", searchWord);
}

List<BoardDTO> memberLists = dao.selectMemberList(param);
dao.close();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
</head>
<body>
    <jsp:include page="../Common/Link.jsp" />
    <h2>관리자 페이지 - 회원 정보 보기</h2>
    <form method="get">  
    <table border="1" width="90%">
    <tr>
        <td align="center">
            <select name="searchField"> 
                <option value="id">아이디</option> 
                <option value="name">이름</option>
            </select>
            <input type="text" name="searchWord" />
            <input type="submit" value="검색하기" />
        </td>
    </tr>   
    </table>
    </form>
    
    <table border="1" width="90%">
        <tr>
            <th width="25%">아이디</th>
            <th width="25%">이름</th>
            <th width="25%">가입일</th>
            <th width="25%">회원탈퇴</th>
        </tr>
<%
    for (BoardDTO dto : memberLists)
    {
%>
        <tr align="center">
            <td align="center"><%= dto.getId() %></td>
            <td align="center"><%= dto.getName() %></td>
            <td align="center"><%= dto.getRegidate() %></td>
            <td align="center"><a href="DeleteMemberProcess.jsp?id=<%= dto.getId() %>">회원탈퇴</a></td>
        </tr>
<%
    }
%>
    </table>

</body>
</html>