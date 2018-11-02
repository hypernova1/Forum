<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Developers</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" media="screen" href="/css/post.css" />
    <link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" rel="stylesheet">
</head>
<body>
<%@ include file="../include/header.jsp" %>
    <section>
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
            <div class="inline btn">추천</div>
            <div class="inline btn">반대</div>
        </div>
        <div id="modify-box">
            <div class="inline modify btn">수정</div>
            <div class="inline modify btn">삭제</div>
        </div>
        <div id="comment-box">
            <div id="comment-view">comments &nbsp; <span>${board.comment }</span></div>
            <ul id="comment-list">
                <li class="comment">
                    <div>
                        <div class="left">닉네임</div>
                        <div class="remove-btn left">수정</div>
                        <div class="remove-btn left">삭제</div>
                        <div class="right">2018-10-31</div>
                    </div>
                    <br>
                    <div class="comment-content">아진짜 짱낭</div>
                    
                </li>
            </ul>
            <div id="comment-write">
                <div id="comment-write-head" class="inline">댓글 작성</div>
                <div id="-comment-write-box">
                    <textarea class=""></textarea>
                <button class="write right" id="content-btn">등록</button></div>
            </div>
        </div>
        <div class="btn list">목록 보기</div>
    </section>
    <input type="hidden" value="${board.bno}" id="bno">
    <input type="hidden" value="${board.mno}" id="mno">
<%@ include file="../include/footer.jsp" %>
</body>
<script src="/js/post.js"></script>
</html>