<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link rel="stylesheet" type="text/css" href="/css/write.css" />
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>

<%@ include file="../include/header.jsp" %>

    <form action="/board/modify" method="post">
		<section class="wrapper">
            <div><input type="text" class="text" placeholder="제목" name="title" id="title" value="${board.title}"></div>
            <div><textarea name="content" id="summernote"></textarea></div>
            <div id="btn-wrapper">
                <button type="submit" id="submit">확인</button>
                <a id="cancel">취소</a>
            </div>
	   	</section>
		<input type="hidden" name="content" value="${board.content}" id="content">
        <input type="hidden" name="page" id="page" value="${criteria.page}">
        <input type="hidden" name="bno" id="bno" value="${board.bno}">
   	</form>

<%@ include file="../include/footer.jsp" %>

<script defer="defer">
    $(document).ready(function () {
        $('#summernote').summernote({
            height: 450,
            focus: true
        }).summernote('code', document.querySelector('#content').value);
        $('.note-statusbar').hide(); 
    });
</script>
<script src="/js/modify.js"></script>
