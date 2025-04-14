<%--
  Created by IntelliJ IDEA.
  User: MAIN
  Date: 2025-04-14
  Time: 오전 11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>로그인 페이지</h1>

    <form name="frmLogin" id="frmLogin" method="post">
        아이디 : <input type="text" name="user_id" id="user_id" value="" /><br>
        비밀번호 : <input type="password" name="pwd" id="pwd "value="" /><br>
        <br>
        <input type="submit" id="btnLogin" value="로그인" />
        <input type="reset" value="취소" />
    </form>

    <script>
        const btnLogin = document.getElementById("btnLogin");
        btnLogin.addEventListener('click', (e) => {
            e.preventDefault();
            e.stopPropagation();

            const frm = document.getElementById("frmLogin");
            const user_id = frm.user_id.value;
            const pwd = frm.pwd.value;

            console.log("user_id: " + user_id);
            console.log("pwd: " + pwd);

            frm.submit();
        });
    </script>
</body>
</html>
