<%--
  Created by IntelliJ IDEA.
  User: MAIN
  Date: 2025-04-14
  Time: 오후 1:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>공지사항 게시판</title>
</head>
<body>
    <h1>공지사항 등록</h1>

    <form id="frmRegist" name="frmRegist" method="post">
        <table>
            <tr>
                <td>제목 : </td>
                <td><input type="text" name="title" id="title" value="" maxlength="30" /></td>
            </tr>
            <tr>
                <td>작성자 : </td>
                <td><input type="text" name="user_id" id="user_id" value="" maxlength="20" /></td>
            </tr>
            <tr>
                <td>등록일 : </td>
                <td><input type="date" name="reg_date" id="reg_date" /></td>
            </tr>
            <tr>
                <td>내용 : </td>
                <td><textarea name="content" id="content" cols="40" rows="6"></textarea></td>
            </tr>
            <tr>
                <td>
                    <input type="submit" id="btnRegist" value="등록"/>
                    <input type="reset" id="btnCancel" value="취소"/>
                    <input type="reset" id="btnList" value="목록"/>
                </td>
            </tr>
        </table>
    </form>

    <script>
        const btnRegist = document.getElementById("btnRegist");
        btnRegist.addEventListener('click', (e) => {
            e.preventDefault();
            e.stopPropagation();

            location.href = "./regist.do";
        });

        const btnList = document.getElementById("btnList");
        btnList.addEventListener('click', (e) => {
            e.preventDefault();
            e.stopPropagation();

            location.href = "./list.do";
        });
    </script>
</body>
</html>
