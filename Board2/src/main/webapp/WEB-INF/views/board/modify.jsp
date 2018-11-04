<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link rel="stylesheet" type="text/css" href="/css/modify.css" />
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>

<%@ include file="../include/header.jsp" %>

    <form action="./modify" method="post" id="form">

		<section class="wrapper">
            <div><input type="text" class="text" placeholder="제목" name="title" value="${board.title}"></div>
            <div><textarea name="content" id="summernote"></textarea></div>
            <div id="btn-wrapper">
                <button type="submit" id="submit">확인</button>
                <a id="cancel">취소</a>
            </div>
    	</section>
    </form>
	<input type="hidden" value="${board.content}" id="content">

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
