<%--
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
    <h1>공지사항 목록</h1>

    <table>
        <tr>
            <td>
                <select name="search_category" id="search_category">
                    <option value="" selected>선택</option>
                    <option value="title">제목</option>
                    <option value="content">내용</option>
                    <option value="user_id">작성자</option>
                    <option value="reg_date">등록일</option>
                </select>
                &nbsp;&nbsp;
                <input type="text" name="search_word" id="search_word" value="" maxlength="20" />
                <input type="date" name="search_word" id="search_date1" value=""/>~
                <input type="date" name="search_word" id="search_date2" value=""/>
                &nbsp;&nbsp;
                <input type="button" name="btnSearch" id="btnSearch" value="검색" />
            </td>
        </tr>
    </table>
    <table>
        <thead>
            <tr>
                <td colspan="6">전체 게시물 수 : n 개</td>
            </tr>
            <tr>
                <th>전체 선택</th>
                <th>번호</th>
                <th>제목</th>
                <th>조회수</th>
                <th>등록일</th>
                <th>삭제</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${not empty bbsList}">
                    <c:forEach items="${bbsList}" var="bbs">
                        <tr>
                            <td><input type="checkbox" name="" id=""/></td>
                            <td>${bbs.idx}</td>
                            <td><a href="./view?idx=${bbs.idx}">${bbs.title}</a></td>
                            <td>0</td>
                            <td>${bbs.reg_date}</td>
                            <td><input type="button" value="삭제"/></td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td colspan="6">등록된 게시물이 존재하지 않습니다.</td>
                    </tr>
                </c:otherwise>
            </c:choose>
            <tr>
                <td colspan="6"><< < 1 2 3 4 5 > >></td>
            </tr>
        </tbody>
    </table>

    <table>
        <tr>
            <td colspan="6">
                <button id="btnSubmit">등록</button>
                <button id="btnDelete">선택삭제</button>
            </td>
        </tr>
    </table>

    <script>
        const btnSubmit = document.getElementById("btnSubmit");
        btnSubmit.addEventListener('click', (e) => {
            e.preventDefault();
            e.stopPropagation();

            location.href = "./regist.do";
        });
    </script>
</body>
</html>
