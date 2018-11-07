<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Developers</title>
</head>
<link rel="stylesheet" type="text/css" href="/css/post.css" />
<script src="/js/post.js" defer="defer"></script>  
<body>
	<%@ include file="../include/header.jsp" %>
    <section id="post-section">
        <div id="title">
            <div id="title-header">
                <h2 class="left">${board.title}</h2>
                <div id="date" class="right"><fmt:formatDate pattern="yyyy.MM.dd HH:mm" value="${board.regdate}"></fmt:formatDate></div>
            </div>
            <div id="title-footer">
                <div id="writer" class="left">${board.name}</div>
                <div class="right">추천 <span>${board.recommend}</span></div>
                <div class="right">댓글 <span>${board.comment}</span></div>
                <div class="right">조회 <span>${board.view}</span></div>
            </div>
        </div>
        <div id="content">
        	${board.content}
        </div>
        <div id="recommend" class="center">
            <div class="inline btn" id="true">추천</div>
            <div class="inline btn" id="false">반대</div>
        </div>
        <div id="modify-box">
        <c:if test="${sessionScope.mno eq board.mno}">
	        <form action="./modify" method="get" id="modifyForm">
	            <div class="inline modify btn">수정</div>
	            <div class="inline delete btn">삭제</div>
	            <input type="hidden" name="bno" value="${board.bno}">
	        </form>
        </c:if>
	    <input type="hidden" value="${criteria.page}" name="page">
	    <input type="hidden" value="${board.bno}" name="bno">
        </div>
        <div id="comment-box">
            <div id="comment-view">comments &nbsp; <span></span></div>
            <ul id="comment-list">
            </ul>
            <div id="comment-write">
                <div id="comment-write-head" class="inline">댓글 작성</div>
                <div id="-comment-write-box">
                    <textarea id="content-textarea"></textarea>
                <button class="write right" id="content-btn" disabled="disabled">등록</button></div>
            </div>
        </div>
        <div class="btn list">목록 보기</div>
        
    </section>
    <input type="hidden" value="${board.bno}" id="bno">
    <input type="hidden" value="${board.mno}" id="mno">
    <input type="hidden" value="${criteria.page}" id="page">
	<%@ include file="../include/footer.jsp" %>
</body>
</html>
