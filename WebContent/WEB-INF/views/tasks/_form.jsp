<!-- 共通レイアウト -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>
    </div>
</c:if>
<label for="title">タイトル</label><br /> <!-- titleを表示 -->
<input type="text" name="title" id="title" value="${task.title}" /> <!-- titleを格納 -->
<br /><br />

<label for="content_msg">タスク</label><br /> <!-- contentを表示 -->
<input type="text" name="content" id="content_msg" value="${task.content}" /> <!-- contentを格納 -->
<br /><br />

 <!-- 追加ボタンの作成 -->
<input type="hidden" name="_token" value="${_token}" />
<button type="submit">追加</button>
