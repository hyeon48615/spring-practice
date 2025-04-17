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

    <form id="frmRegist" name="frmRegist" method="post" action="./regist.do">
        <input type="hidden" name="rtnParam" value="${rtnParam}" />
        <table>
            <tr>
                <td>제목 : </td>
                <td><input type="text" name="title" id="title" value="" maxlength="20" /></td>
            </tr>
            <tr>
                <td>작성자 : </td>
                <td><input type="text" name="user_id" id="user_id" value="" maxlength="20" /></td>
            </tr>
            <tr>
                <td>등록일 : </td>
                <td><span id="created_at"></span></td>
            </tr>
            <tr>
                <td>내용 : </td>
                <td><textarea name="content" id="content" cols="40" rows="6"></textarea></td>
            </tr>
            <tr>
                <td>
                    <input type="submit" id="btnRegist" value="등록"/>
                    <input type="button" id="btnCancel" value="취소"/>
                </td>
            </tr>
        </table>
    </form>

    <script>
        // 초기화면
        const created_at = document.getElementById("created_at");
        created_at.textContent = new Date().toISOString().split("T")[0];

        // 폼 액션
        const frm = document.getElementById("frmRegist");

        const btnRegist = document.getElementById("btnRegist");
        btnRegist.addEventListener('click', (e) => {
            e.preventDefault();
            e.stopPropagation();

            // value -> 값이 없는 경우 "" 반환 -> null 체크 안함
            const title = frm.title.value.trim();
            const user_id = frm.user_id.value.trim();
            const content = frm.content.value.trim();

            if (title.length < 1 || title.length > 20) {
                alert("제목은 1자 이상 20자 이하로 입력해주세요.");
                frm.title.focus();
                return;
            }
            if (user_id.length < 1 || user_id.length > 20) {
                alert("작성자는 1자 이상 20자 이하로 입력해주세요.");
                frm.user_id.focus();
                return;
            }
            if (content.length < 1) {
                alert("내용은 1자 이상 입력해주세요.");
                frm.content.focus();
                return;
            }

            frm.submit();
        });

        const btnCancel = document.getElementById("btnCancel");
        btnCancel.addEventListener('click', (e) => {
            e.preventDefault();
            e.stopPropagation();

            history.back();
        });
    </script>
</body>
</html>
