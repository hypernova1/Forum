<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Developer Comunity</title>
</head>
<link href="https://fonts.googleapis.com/css?family=Poor+Story" rel="stylesheet">
<style>
	body{
		background-image: url("http://www.sdjgjx.com/up/pc/background%20hd.jpg");	
	}
    #notice{
        text-align: center;
        font-size: 2em;
        margin: 100px;
        color: white;
        font-family: 'Poor Story', cursive;
        -ms-user-select: none;
        -moz-user-select: -moz-none;
        -webkit-user-select: none;
        -khtml-user-select: none;
        user-select:none;
    }

</style>
<body>
<%@ include file="./include/header.jsp" %>

<section>
            <pre id="notice">
                    
                개발자들이 모여 지식 및 노하우를 공유하는 공간입니다.

                개발과 관련되지 않은 글이어도 좋습니다.

                누구나 편하게 이용하고 재밌게 즐길 수 있는 것을 모토로 합니다.
            </pre>

</section>

<%@ include file="./include/footer.jsp" %>
</body>
</html>