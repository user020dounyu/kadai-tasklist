<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- app.jsp の ${param.content} -->
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${task != null}"> <!-- タスクがあった場合 -->
                <h2>id : ${task.id} のタスク詳細ページ</h2>
                <table>
                    <tbody>
                        <tr>
                            <th>タイトル</th>
                            <td><c:out value="${task.title}" /></td> <!-- title出力 -->
                        </tr>
                        <tr>
                            <th>メッセージ</th>
                            <td><c:out value="${task.content}" /></td> <!-- content出力 -->
                        </tr>
                        <tr>
                            <th>作成日時</th>
                            <td><fmt:formatDate value="${task.created_at}" pattern="yyyy-MM-dd HH:mm:ss" /></td> <!-- created_at出力 -->
                        </tr>
                        <tr>
                            <th>更新日時</th>
                            <td><fmt:formatDate value="${task.updated_at}" pattern="yyyy-MM-dd HH:mm:ss" /></td> <!-- updated_at出力 -->
                        </tr>
                    </tbody>
                </table>
                <p><a href="${pageContext.request.contextPath}/index">一覧に戻る</a></p>  <!-- indexへ -->
                <p><a href="${pageContext.request.contextPath}/edit?id=${task.id}">このタスクを編集する</a></p> <!-- editへ -->
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2> <!-- タスクがなかった場合 -->
            </c:otherwise>
        </c:choose>
    </c:param>
</c:import>