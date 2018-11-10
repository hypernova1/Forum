<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Developers</title>
</head>
<link rel="stylesheet" type="text/css" href="/css/qna/post.css" />
<script src="/js/qna/post.js" defer="defer"></script>  
<body>
	<%@ include file="../include/header.jsp" %>
    <section id="post-section">
        <div id="title">
            <div id="title-header">
                <h2 class="left">${qna.title}</h2>
                <div id="date" class="right"><fmt:formatDate pattern="yyyy.MM.dd HH:mm" value="${qna.regdate}"></fmt:formatDate></div>
            </div>
            <div id="title-footer">
                <div id="writer" class="left">${qna.name}</div>
                <div class="right">추천 <span>${qna.recommend}</span></div>
                <div class="right">댓글 <span>${qna.comment}</span></div>
                <div class="right">조회 <span>${qna.view}</span></div>
            </div>
        </div>
        <div id="content">
        	${qna.content}
        </div>
        <c:if test="${sessionScope.mno != null}">
	        <div id="recommend" class="center">
	            <div class="inline btn" id="true">추천</div>
	            <div class="inline btn" id="false">반대</div>
	        </div>
        </c:if>
        <c:if test="${sessionScope.mno != null and sessionScope.mno ne qna.mno}">
        <form action="./write">
			<input type="hidden" value="${qna.qno}" name="qno" id="qno">
		    <input type="hidden" value="${qna.mno}" name="mno"id="mno">
		    <input type="hidden" value="${criteria.page}" name="page" id="page">
		    <input type="hidden" value="${qna.groupno}" name="groupno" id="groupno">
		    <input type="hidden" value="${qna.seq}" name="seq" id="seq">
		    <input type="hidden" value="${qna.depth}" name="depth" id="depth">
        	<button id="reply">답글 달기</button>
        </form>
        </c:if>
        <div id="modify-box">
        
        <c:if test="${sessionScope.mno eq qna.mno}">
	        <form action="./modify" method="get" id="modifyForm">
	            <button class="inline modify btn">수정</button>
	            <button type="button" class="inline delete btn">삭제</button>
	            <input type="hidden" name="qno" value="${qna.qno}">
	        </form>
        </c:if>
        
        </div>
        
        <div id="comment-box">
            <div id="comment-view">comments &nbsp; <span></span></div>
            <ul id="comment-list">
            </ul>
	        <c:if test="${sessionScope.mno != null}">
    	        <div id="comment-write">
        	        <div id="comment-write-head" class="inline">댓글 작성</div>
            	    <div id="-comment-write-box">
                    <textarea id="content-textarea"></textarea>
               		<button class="write right" id="content-btn" disabled="disabled">등록</button></div>
            	</div>
       		</c:if>
        </div>
        <div class="btn list">목록 보기</div>
        
    </section>
    <input type="hidden" value="${qna.qno}" name="qno" id="qno">
    <input type="hidden" value="${qna.mno}" name="mno"id="mno">
    <input type="hidden" value="${criteria.page}" name="page" id="page">
	<%@ include file="../include/footer.jsp" %>
</body>
</html>
