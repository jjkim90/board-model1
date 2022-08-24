<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script type="text/javascript">
function validatePass(form) {
    if (form.pass.value !== form.pass2.value) {
        alert("비밀번호가 일치하지 않습니다.");
        form.pass.focus();
        return false;
    }
}
</script>
</head>
<body>
    <form name="signUpFrm" method="post" action="SignUpProcess.jsp"
    		onsubmit="return validatePass(this);">
        <fieldset style="width: 380px;">
            <legend>회원가입</legend>
            <table>
                <tr>
                    <td><label for="id">아이디</label></td>
                    <td><input type="text" id="id" name="id" placeholder="아이디 입력" required autofocus> </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                </tr>                
                <tr>
                    <td><label for="pass">비밀번호</label></td>
                    <td><input type="password" id="pass" name="pass" placeholder="비밀번호 입력" required></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                </tr>                
                <tr>
                    <td><label for="pass2">비밀번호 확인</label></td>
                    <td><input type="password" id="pass2" name="pass2" placeholder="비밀번호 재입력" required></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td><label for="name">이름</label></td>
                    <td><input type="text" id="name" name="name" placeholder="이름 입력" required> </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                </tr>                
                <tr>
                    <td></td>
                    <td><input type="submit" value="가입신청">&nbsp;<button type="button" onclick="location.href='List.jsp';">
                    취소
                </button></td>
                </tr>
                
            </table>
        </fieldset>
    </form>
</body>
</html>