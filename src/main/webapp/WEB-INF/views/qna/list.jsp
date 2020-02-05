<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Developers</title>
</head>
<link rel="stylesheet" type="text/css" href="/css/qna/list.css" />
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic" rel="stylesheet">
<script src="/js/qna/list.js" defer="defer"></script>
<body>
	<%@ include file="../include/header.jsp"%>
	<section id="list-section">
		<div class="container">
			<div>
				<h2 class="list-name">Q&A</h2>
				<form action="/qna/list">
					<button id="searchBtn">검색</button>
					<input type="text" name="keyword" id="searchText" placeholder="검색어 입력..">
					<select name="searchType" id="searchType">
						<option value="1"
							<c:out value="${criteria.searchType eq '1' ? 'selected' : ''}"/>>제목</option>
						<option value="2"
							<c:out value="${criteria.searchType eq '2' ? 'selected' : ''}"/>>내용</option>
						<option value="3"
							<c:out value="${criteria.searchType eq '3' ? 'selected' : ''}"/>>작성자</option>
						<option value="4"
							<c:out value="${criteria.searchType eq '4' ? 'selected' : ''}"/>>전체</option>
					</select>
				</form>
			</div>
			<table id="post-list">
				<tr id="post-header">
					<th class="num"><p>번호</p></th>
					<th class="title"><p>제목</p></th>
					<th class="writer"><p>글쓴이</p></th>
					<th class="view"><p>조회</p></th>
					<th class="date"><p>날짜</p></th>
					<th class="vote"><p>추천</p></th>
				</tr>
				<c:forEach items="${list}" var="qna">
					<tr class="post">
						<td class="num center"><c:if test="${qna.seq eq 0}"><p>${qna.qno}</p></c:if></td>
						<td class="title">
						<c:choose>
							<c:when test="${qna.del eq 1}">
								<p class="delete-post">${qna.title}</p>
							</c:when>
							<c:otherwise>
								<p>
									<a href="/qna/post?qno=${qna.qno}&page=${page.cri.page}">${qna.title}</a>
									<span class="list-comment">[${qna.comment}]</span>
								</p>
							</c:otherwise>
						</c:choose>
							
						</td>
						<td class="writer center"><p>${qna.name}</p></td>
						<td class="view center"><p>${qna.view}</p></td>
						<td class="date center"><p><fmt:formatDate pattern="yyyy.MM.dd HH:mm" value="${qna.regdate}"></fmt:formatDate></p></td>
						<td class="vote center"><p>${qna.recommend}</p></td>
					</tr>
				</c:forEach>
			</table>
			<c:if test="${sessionScope.mno ne null}">
				<button id="write-btn">글쓰기</button>
			</c:if>
			<ul>
				<c:if test="${page.prev}">
					<li><a href="list?page=${page.startPage-1}&searchType=${criteria.searchType}&keyword=${criteria.keyword}"><button class="idx">&laquo;</button></a></li>
				</c:if>
				<c:forEach begin="${page.startPage}" end="${page.endPage}" var="idx">
					<li
						<c:out value="${page.cri.page == idx ? 'class =active':''}"/>>
						<a href="list?page=${idx}&searchType=${criteria.searchType}&keyword=${criteria.keyword}"><button class="idx">${idx}</button></a>		
					</li>
				</c:forEach>
				<c:if test="${page.next && page.endPage > 0}">
					<li><a href="list?page=${page.endPage + 1}&searchType=${criteria.searchType}&keyword=${criteria.keyword}"><button class="idx">&raquo;</button></a></li>
				</c:if>
			</ul>

		</div>
	</section>
	<!-- 페이지 정보 기억 -->
	<input type="hidden" name="page" value=${page.cri.page }>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>