document.querySelector('.menu.home').addEventListener('click', function(){
    location.href="/";
});
document.querySelector('.menu.freeBoard').addEventListener('click', function(){
    location.href="/board";
});
document.querySelector('.menu.qna').addEventListener('click', function(){
    location.href="/qna";
});
document.querySelector('.menu.chatting').addEventListener('click', function(){
    location.href="/chatting";
});
document.querySelector('.menu.notice').addEventListener('click', function(){
    location.href="/notice";
});


let loginModal = document.querySelector('.modal.login');
let btn = document.querySelector("#login");
btn.addEventListener('click', function() {
    loginModal.style.display = "block";
})

loginModal.addEventListener('click', function(event) {
    if (event.target == loginModal) {
        loginModal.style.display = "none";
    }
})

let signinModal = document.querySelector('.modal.signin');
let btn2 = document.querySelector("#signin");
btn2.addEventListener('click', function() {
    signinModal.style.display = "block";
})

window.onclick = function(event) {
    if (event.target == signinModal) {
        signinModal.style.display = "none";
    }
}

//로그인 모달에서 회원 가입 버튼 눌렀을 때
document.querySelector('#signinBtn').addEventListener('click', function(){
    loginModal.style.display = "none";
    signinModal.style.display = "block";
})
