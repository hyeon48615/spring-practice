<%@ page import="net.fullstack10.util.DateUtil" %><%--
  Created by IntelliJ IDEA.
  User: MAIN
  Date: 2025-04-14
  Time: 오후 1:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <td>${bbs.title}</td>
            </tr>
            <tr>
                <td>작성자 : </td>
                <td>${bbs.user_id}</td>
            </tr>
            <tr>
                <td>등록일 : </td>
                <td>${DateUtil.localDateTimeToString(bbs.created_at)}</td>
            </tr>
            <c:if test="${bbs.updated_at ne null}">
                <tr>
                    <td>수정일 : </td>
                    <td>${DateUtil.localDateTimeToString(bbs.updated_at)}</td>
                </tr>
            </c:if>
            <tr>
                <td>내용 : </td>
                <td>${bbs.content}</td>
            </tr>
            <tr>
                <td>
                    <input type="button" id="btnModify" value="수정"/>
                    <input type="button" id="btnDelete" value="삭제"/>
                    <input type="button" id="btnList" value="목록"/>
                </td>
            </tr>
        </table>
        <input type="hidden" name="idx" value="${bbs.idx}" />
        <input type="hidden" name="user_id" value="${bbs.user_id}" />
    </form>

    <script>
        const frm = document.getElementById("frmModify");

        document.getElementById("btnModify").addEventListener('click', (e) => {
            e.preventDefault();
            e.stopPropagation();

            frm.action = "./modify.do";
            frm.submit();
        });

        document.getElementById("btnDelete").addEventListener('click', (e) => {
            e.preventDefault();
            e.stopPropagation();

            frm.action = "./delete.do";
            frm.submit();
        });

        document.getElementById("btnList").addEventListener('click', (e) => {
           e.preventDefault();
           e.stopPropagation();

           location.href = "./list.do?${rtnParam}";
        });
    </script>
</body>
</html>
