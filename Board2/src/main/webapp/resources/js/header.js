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

let modal = document.querySelector('.modal');

let btn = document.querySelector("#login");

let span = document.getElementsByClassName("close")[0];                                          

btn.addEventListener('click', function() {
    modal.style.display = "block";
})

span.onclick = function() {
    modal.style.display = "none";
}

window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}