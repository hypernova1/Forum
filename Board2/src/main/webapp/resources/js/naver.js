let naverLogin = new naver.LoginWithNaverId({
    clientId: "Bc2zvL52e8sCG5i1R9K1",
    callbackUrl: "http://localhost:8080/html/callback.html",
    isPopup: true,
});
naverLogin.init();

naverLogin.getLoginStatus(function (status) {
    //로그인이 된 경우
    if (status) {
        let email = naverLogin.user.getEmail();
        let uniqId = naverLogin.user.getId();
        console.log(email)
        document.getElementById('naverIdLogin').remove();
        document.body.innerHTML = '<button id="logout">로그아웃</button>';
        document.getElementById('logout').addEventListener('click', function(){
            naverLogin.logout();
            location.reload();
        })
    } else {
        // console.log("AccessToken이 올바르지 않습니다.");
    }
});