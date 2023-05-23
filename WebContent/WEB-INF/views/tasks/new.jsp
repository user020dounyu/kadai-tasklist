 <!-- タスク新規作成ページ -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- app.jsp の ${param.content} -->
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <h2>タスク新規作成ページ</h2>

        <form method="POST" action="${pageContext.request.contextPath}/create"> <!-- ボタンが押されたらcreateへ -->
            <c:import url="_form.jsp" />  <!-- _formを挿入 -->
        </form>

        <p><a href="${pageContext.request.contextPath}/index">一覧に戻る</a></p>

    </c:param>
</c:import>