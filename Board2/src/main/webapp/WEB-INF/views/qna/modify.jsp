<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Developers</title>
</head>
<style>
    .ck-editor__editable {
        min-height: 600px;
        min-width: 400px;
    }
</style>
<script src="https://cdn.ckeditor.com/ckeditor5/11.1.1/classic/ckeditor.js"></script>
<link rel="stylesheet" type="text/css" href="/css/board/write.css" />
<script src="/js/qna/modify.js" defer="defer"></script>
<body>
	<%@ include file="../include/header.jsp" %>

    <form action="./modify" method="post">
		<section class="wrapper">
            <div><input type="text" class="text" placeholder="제목" name="title" id="title" value="${qna.title}"></div>
            <div><textarea name="content" id="editor"></textarea></div>
            <div id="btn-wrapper">
                <button type="submit" id="submit">확인</button>
                <button type="button" id="cancel">취소</button>
            </div>
	   	</section>
        <input type="hidden" name="page" id="page" value="${criteria.page}">
        <input type="hidden" name="qno" id="qno" value="${qna.qno}">
   	</form>

<%@ include file="../include/footer.jsp" %>
</body>
<script>
	ClassicEditor
	.create( document.querySelector('#editor'), {
	    })
	.then( editor => {
	    editor.setData('${qna.content}');
	}).catch( error => {
	    console.error(error);
	});
</script>
</html>
