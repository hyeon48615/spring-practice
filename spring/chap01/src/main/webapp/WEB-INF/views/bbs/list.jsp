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
    <h1>공지사항 목록</h1>

    <table>
        <tr>
            <td>등록일</td>
            <td>
                <input type="date" name="search_date1" id="search_date1" value="${map.search_date1}"/> ~
                <input type="date" name="search_date2" id="search_date2" value="${map.search_date2}"/>
            </td>
        </tr>
        <tr>
            <td>구분</td>
            <td>
                <select name="search_category" id="search_category">
                    <option value="title" ${map.search_category eq 'title' ? 'selected' : ''}>제목</option>
                    <option value="content" ${map.search_category eq 'content' ? 'selected' : ''}>내용</option>
                    <option value="user_id" ${map.search_category eq 'user_id' ? 'selected' : ''}>작성자</option>
                </select>
                <input type="text" name="search_word" id="search_word" value="${map.search_word}" maxlength="20" />
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="button" name="btnSearch" id="btnSearch" value="검색" />
                <input type="button" name="btnReset" id="btnReset" value="초기화" />
            </td>
        </tr>
    </table>

    <form name="frmDelete" id="frmDelete" method="post" action="./delete.do">
        <input type="hidden" name="rtnParam" value="${rtnParam}" />
        <table>
            <thead>
            <tr>
                <th><input type="checkbox" id="btnCheckAll" /> 전체 선택</th>
                <th>번호</th>
                <th>제목</th>
                <th>조회수</th>
                <th>등록일</th>
                <th>삭제</th>
            </tr>
            </thead>
            <tbody>
                <input type="hidden" name="delete_idx" value="" />
                <c:choose>
                    <c:when test="${not empty bbsList}">
                        <c:forEach items="${bbsList}" var="bbs">
                            <tr>
                                <td><input type="checkbox" name="delete_list" value="${bbs.idx}"/></td>
                                <td>${bbs.idx}</td>
                                <td><a href="./view.do?idx=${bbs.idx}&rtnParam=${rtnParam}">${bbs.title}</a></td>
                                <td>0</td>
                                <td>${DateUtil.toString(bbs.created_at)}</td>
                                <td><input type="button" class="btnDeleteOne" value="삭제"/></td>
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
                    <td colspan="6">${paging}</td>
                </tr>
            </tbody>
        </table>
    </form>

    <table>
        <tr>
            <td colspan="6">
                <input type="button" id="btnSubmit" value="등록"/>
                <input type="button" id="btnDelete" value="선택삭제"/>
            </td>
        </tr>
    </table>

    <script>
        const search_date1 = document.getElementById("search_date1");
        const search_date2 = document.getElementById("search_date2");
        const search_category = document.getElementById("search_category");
        const search_word = document.getElementById("search_word");

        document.querySelectorAll("input[type='date']").forEach(el => {
            el.addEventListener('change', () => {
                if (search_date1.value && search_date2.value && new Date(search_date1.value) > new Date(search_date2.value)) {
                    alert("시작일은 종료일보다 빠르거나 같아야 합니다.");
                    search_date1.value = "";
                    search_date2.value = "";
                    search_date1.focus();
                }
            });
        });

        document.getElementById("btnSearch").addEventListener('click', () => {
            const params = new URLSearchParams();

            if (search_date1.value && search_date2.value) {
                params.append("search_date1", search_date1.value);
                params.append("search_date2", search_date2.value);
            }
            if (search_category.value && search_word.value) {
                params.append("search_category", search_category.value);
                params.append("search_word", encodeURIComponent(search_word.value));
            }

            if ((search_date1.value && !search_date2.value) || (!search_date1.value && search_date2.value)) {
                alert("시작일과 종료일 모두 선택해주세요.");
                return;
            }
            if (params.size === 0) {
                alert("검색 조건을 입력해주세요.");
                return;
            }

            location.href = "./list.do?" + params.toString();
        });

        document.getElementById("btnReset").addEventListener('click', () => {
            search_date1.value = "";
            search_date2.value = "";
            search_category.selectedIndex = 0;
            search_word.value = "";

            location.href = "./list.do";
        });

        document.getElementById("btnSubmit").addEventListener('click', () => {
            location.href = "./regist.do?rtnParam=${rtnParam}";
        });

        // 전체 선택 및 해지
        const btnCheckAll = document.getElementById("btnCheckAll");
        btnCheckAll.addEventListener('click', () => {
            let check_flag = btnCheckAll.checked;

            const check = document.getElementsByName("delete_list");
            check.forEach((el) => {
                el.checked = check_flag;
            });
        });

        // 개별 삭제
        document.querySelectorAll(".btnDeleteOne").forEach(btn => {
            btn.addEventListener('click', function(e) {
                e.preventDefault();
                e.stopPropagation();

                if (!confirm("해당 글을 삭제하시겠습니까?\n삭제된 글은 복구되지 않습니다.")) return;

                const idx = this.closest("tr").querySelector("input[type='checkbox']").value;

                const frm = document.getElementById("frmDelete");
                frm.delete_idx.value = idx;
                frm.submit();
            });
        });

        // 선택 삭제
        document.getElementById("btnDelete").addEventListener('click', () => {
            const items = document.querySelectorAll("input[name='delete_list']:checked");
            if (items.length === 0) {
                alert("삭제할 항목을 선택하세요.");
                return;
            }

            if (!confirm("선택하신 글을 삭제하시겠습니까?\n삭제된 글은 복구되지 않습니다.")) return;

            const frm = document.getElementById("frmDelete");
            frm.submit();
        });
    </script>
</body>
</html>
