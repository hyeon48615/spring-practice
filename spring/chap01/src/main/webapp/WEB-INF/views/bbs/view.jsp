<%--
  Created by IntelliJ IDEA.
  User: MAIN
  Date: 2025-04-14
  Time: 오후 1:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>공지사항 게시판</title>
</head>
<body>
    <h1>공지사항 조회</h1>
    <form id="frmView" name="frmView" method="post">
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
                    <input type="button" id="btnModify" value="수정"/>
                    <input type="button" id="btnDelete" value="삭제"/>
                    <input type="button" id="btnList" value="목록"/>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
