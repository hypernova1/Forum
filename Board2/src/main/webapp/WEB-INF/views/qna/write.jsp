<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Developers</title>
</head>
<script src="https://cdn.ckeditor.com/ckeditor5/11.1.1/classic/ckeditor.js"></script>
<link rel="stylesheet" type="text/css" href="/css/board/write.css" />
<script src="/js/qna/write.js" defer="defer"></script>
<style>
	.ck-editor__editable {
	    min-height: 600px;
	    min-width: 400px;
	}
</style>
<body>
	<%@ include file="../include/header.jsp" %>

    <form action="/qna/write" method="post" id="form">
		<section class="wrapper">
            <input type="hidden" name="type" value="1">
            <div><input type="text" class="text" placeholder="제목" name="title"></div>
            <div><textarea name="content" id="editor"></textarea></div>
            <div id="btn-wrapper">
            <button id="submit" disabled="disabled">확인</button>
            <button type="button" id="cancel">취소</button>
		    <input type="hidden" value="${qna.groupno}" name="groupno" id="groupno">
		    <input type="hidden" value="${qna.seq}" name="seq" id="seq">
		    <input type="hidden" value="${qna.depth}" name="depth" id="depth">
            </div>
    	</section>
    </form>
    <input type="hidden" value="${qna.qno}" name="qno" id="qno">
    <input type="hidden" value="${qna.mno}" name="mno" id="mno">
	<input type="hidden" value="${page.cri.page}">

	<%@ include file="../include/footer.jsp" %>
</body>
<script>
	ClassicEditor.create(document.querySelector( '#editor' ), {
	    ckfinder: {
	        uploadUrl: '/board/write'
	      }
	    }).then(editor => {
	    console.log(editor.setData(''));
	}).catch(error => {
	    console.error(error);
	});


</script>
</html>
