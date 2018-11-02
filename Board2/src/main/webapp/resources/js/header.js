
document.querySelector('.menu.home').addEventListener('click', function () {
    location.href = "/";
});
document.querySelector('.menu.freeBoard').addEventListener('click', function () {
    location.href = "/board/list";
});
document.querySelector('.menu.qna').addEventListener('click', function () {
    location.href = "/qna";
});
document.querySelector('.menu.chatting').addEventListener('click', function () {
    location.href = "/chatting";
});
document.querySelector('.menu.notice').addEventListener('click', function () {
    location.href = "/notice";
});


let loginModal = document.querySelector('.modal.login');
let btn = document.querySelector("#login");
btn.addEventListener('click', function () {
    loginModal.style.display = "block";
})

loginModal.addEventListener('click', function (event) {
    if (event.target == loginModal) {
        loginModal.style.display = "none";
    }
})

let signinModal = document.querySelector('.modal.signin');
let btn2 = document.querySelector("#signin");
btn2.addEventListener('click', function () {
    signinModal.style.display = "block";
})

window.onclick = function (event) {
    if (event.target == signinModal) {
        signinModal.style.display = "none";
    }
}

// 로그인 모달에서 회원 가입 버튼 눌렀을 때
document.querySelector('#signinBtn').addEventListener('click', function () {
    loginModal.style.display = "none";
    signinModal.style.display = "block";
})

// 회원가입 유효성 검사
let idval;
let pwval;
let emailval;

document.querySelector('#signinForm').onkeyup = function (e) {
    let idExptext = /^[A-Za-z0-9+]{4,12}$/;
    let emailExptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
    let pwExptext = /^.*(?=.{6,20})(?=.*[0-9])(?=.*[a-zA-Z]).*$/;


    switch(e.target.id){
        case 'id': 
            if (idExptext.test(id.value) == false) {
                document.querySelector('#signinForm>.error').innerHTML = "아이디 형식이 맞지 않습니다. (4~12 자리)";
                idval = false;
            } else {
                document.querySelector('#signinForm>.error').innerHTML = '&nbsp;';
                idval = true;
            } break;
        case 'email':
            if (emailExptext.test(email.value) == false) {
                document.querySelector('#signinForm>.error').innerHTML = "이메일 형식이 맞지 않습니다.";
                emailval = false;
            } else {
                document.querySelector('#signinForm>.error').innerHTML = '&nbsp;';
                emailval = true;
            } break;
            case 'pw':
            if(pwExptext.test(document.querySelector('#pw').value) == false) {
                document.querySelector('#signinForm>.error').innerHTML = "영문, 숫자 혼합 6~20자리"
                pwval = false;
            } else{
                document.querySelector('#signinForm>.error').innerHTML = '&nbsp;';
            }
            break;
        case 'repw':
            if (document.querySelector('#pw').value != repw.value) {
                document.querySelector('#signinForm>.error').innerHTML = "비밀번호가 일치하지 않습니다.";
                pwval =  false;
            } else if(pwExptext.test(document.querySelector('#pw').value) == false && document.querySelector('#pw').value == repw.value) {
                document.querySelector('#signinForm>.error').innerHTML = "영문, 숫자 혼합 6~20자리";
                pwval = false;
            } else {
                document.querySelector('#signinForm>.error').innerHTML = '&nbsp;';
                pwval =  true;
            } break;

        }
        let btn = document.querySelector('#signinForm .btn');
        if(idval == true && pwval == true && emailval == true){
            btn.style.backgroundColor = "#228be6";
            btn.disabled = false;
            btn.type = "submit";
        } else{
            btn.style.backgroundColor = "";
            btn.disabled = true;
        }

    }


function get(rest, url, obj){
	return new Promise((resolve, reject) => {
		const xhr = new XMLHttpRequest();
		
		xhr.onreadystatechange = () => {
			if(xhr.readyState === XMLHttpRequest.DONE){
				if(xhr.status === 200){
					resolve(xhr.response);
				} else{
					reject('Error:'  + xhr.status)
				}
			}
		}
		xhr.open(rest, url);
		xhr.setRequestHeader('Content-type', 'application/json');
		xhr.send(JSON.stringify(obj));
	})
}

const url = '/login';
const rest = 'POST';
let obj = {
		id: null,
		pw: null
}

document.querySelector('#loginForm').addEventListener('keyup', function(){
    obj.id = document.querySelector('.text.id').value,
    obj.pw = document.querySelector('.text.pw').value
})

document.querySelector('#loginForm .ajax').addEventListener('click', function(){
    get(rest, url, obj).then(
    		result => location.href = location.href,
    		error => document.querySelector('#loginForm .error').innerHTML = '잘못된 계정입니다.'
    		)
})





