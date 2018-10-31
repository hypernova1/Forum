<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Page Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
     <link href="https://fonts.googleapis.com/css?family=Do+Hyeon" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Merriweather" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="resources/css/header.css" />
    <script src="resources/js/header.js" defer="defer"></script>
</head>
<body>
    <header class="header-wrap">
        <div class="header-container">
            <div class="header-body none-drag">
                <h1 class="logo">Developer Comunity</h1>
                <div class="privacy-box">
                    <span id="login">로그인</span>&#5;<span id="signin">회원가입</span>
                </div>
            </div>
        </div>
    </header>
    <nav id="menu-wrap">
    	<div class="menu none-drag home">Home</div>
    	<div class="menu none-drag freeBoard">Free Board</div>
    	<div class="menu none-drag qna">Q&A</div>
    	<div class="menu none-drag chatting">Chatting</div>
        <div class="menu none-drag notice">Notice</div>
    </nav>

        <div class="modal login">
            <div class="modal-content">
                <div id="loginForm">
                    <h2 id="login-title">Login</h2>
                    <div>
                        <input type="text" class="text" placeholder="아이디">  
                    </div>
                    <div>
                        <input type="password" class="text" placeholder="비밀번호">  
                    </div>
                    <div class="btn">로그인</div>
                    <div class="btn naver">네이버 로그인</div>
                    <div class="btn google">구글 로그인</div>
                    <div class="etc">
                        <span id="signinBtn">회원가입</span> | 
                        <span id="pwBtn">비밀번호 찾기</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal signin">
            <div class="modal-content">
                <div id="signinForm">
                    <h2 id="signin-title">Sign In</h2>
                    <div>
                        <input type="text" class="text" placeholder="아이디">  
                    </div>
                    <div>
                        <input type="password" class="text" placeholder="비밀번호" id="pw">  
                    </div>
                    <div>
                        <input type="password" class="text" placeholder="비밀번호 재입력" id="repw">  
                    </div>
                    <div>
                        <input type="email" class="text" placeholder="이메일">  
                    </div>
                    <div class="sign btn">가입</div>
                    <div class="sign btn">취소</div>
                </div>
            </div>
        </div>
</body>

</html>