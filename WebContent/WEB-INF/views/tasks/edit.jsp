<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- app.jsp の ${param.content} -->
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${task != null}"> <!-- タスクがあった場合 -->
                <h2>id : ${task.id} のタスク編集ページ</h2>

                <!-- <>内のデータをupdateへ送る -->
                <form method="POST" action="${pageContext.request.contextPath}/update">
                    <c:import url="_form.jsp" />
                </form>

                <p><a href="${pageContext.request.contextPath}/index">一覧に戻る</a></p> <!-- indexへ -->

                <p><a href="#" onclick="confirmDestroy();">このタスクを削除する</a></p> <!-- onclickのフォームへ -->
                <!-- <>内のデータをdestroyへ送る -->
                <form method="POST" action="${pageContext.request.contextPath}/destroy">
                    <input type="hidden" name="_token" value="${_token}" />
                </form>
                <script>
                function confirmDestroy() { <!-- フォームの送信 -->
                    if(confirm("本当に削除してよろしいですか？")) {
                        document.forms[1].submit();
                    }
                }
                </script>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2> <!-- タスクがなかった場合 -->
            </c:otherwise>
        </c:choose>
    </c:param>
</c:import>