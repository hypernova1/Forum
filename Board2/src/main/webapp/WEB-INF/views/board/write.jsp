<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="/css/write.css" />
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>
<script src="/js/write.js" defer="defer"></script>

<script defer="defer">
    $(document).ready(function () {
        $('#summernote').summernote({
            height: 450, // set editor height
            focus: true // set focus to editable area after initializing summernote
        });
        $('.note-statusbar').hide(); 
    });
</script>

<%@ include file="../include/header.jsp" %>

    <form action="./write" method="get" id="form">
		<section class="wrapper">
            <input type="hidden" name="type" value="1">
            <div><input type="text" class="text" placeholder="제목" name="title"></div>
            <div><textarea name="content" id="summernote"></textarea></div>
            <div id="btn-wrapper">
                <button id="submit" disabled="disabled">확인</button>
                <a id="cancel">취소</a>
            </div>
    	</section>
    </form>
	<input type="hidden" value="${page.cri.page}">

<%@ include file="../include/footer.jsp" %>
