<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" type="text/css" href="resources/css/board.css" />
<body>
	<%@ include file="./include/header.jsp"%>
	<section>
		<div class="container">
			<h2 class="list-name">자유게시판</h2>
			<table id="post-list">
				<tr id="post-header">
					<th class="num"><p>번호</p></th>
					<th class="title"><p>제목</p></th>
					<th class="writer"><p>글쓴이</p></th>
					<th class="view"><p>조회</p></th>
					<th class="date"><p>날짜</p></th>
					<th class="vote"><p>추천</p></th>
				</tr>
				<tr class="post">
					<td class="num center"><p>1</p></td>
					<td class="title"><p>안녕하세요</p></td>
					<td class="writer center"><p>김밥나라</p></td>
					<td class="view center"><p>1</p></td>
					<td class="date center"><p>2018-08-11</p></td>
					<td class="vote center"><p>1</p></td>
				</tr>
			</table>
		</div>
		<aside>
			<div class="aside">
				<h4>공지사항</h4>
					<div class="title">즐거운 인생입니다 dsadadsadsadasdasdad</div>
					<div class="title">ddd</div>
					<div class="title">ddd</div>
					<div class="title">ddd</div>
					<div class="title">ddd</div>
					<div class="title">ddd</div>
					<div class="title">ddd</div>
			</div>
			<div class="aside">
				<h4>
					조회수 Up
					</h4>
					<div class="title">즐거운 인생입니다 dsadadsadsadasdasdad</div>
					<div class="title">ddd</div>
					<div class="title">ddd</div>
					<div class="title">ddd</div>
					<div class="title">ddd</div>
					<div class="title">ddd</div>
					<div class="title">ddd</div>
			</div>
			<div class="aside">
				<h4>
					최근 댓글
					</h4>
					<div class="title">즐거운 인생입니다 dsadadsadsadasdasdad</div>
					<div class="title">ddd</div>
					<div class="title">ddd</div>
					<div class="title">ddd</div>
					<div class="title">ddd</div>
					<div class="title">ddd</div>
					<div class="title">ddd</div>
			</div>
		</aside>
	</section>
	<%@ include file="./include/footer.jsp"%>
</body>
</html>