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
let nameval;

document.querySelector('#signinForm').onkeyup = function (e) {
    let idExptext = /^[A-Za-z0-9+]{4,12}$/;
    let emailExptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
    let pwExptext = /^.*(?=.{6,20})(?=.*[0-9])(?=.*[a-zA-Z]).*$/;
    
    let error = document.querySelector('#signinForm>.error');
    switch (e.target.id) {
        case 'id':
            if (idExptext.test(id.value) == false) {
            	error.style.color = "red";
                error.innerHTML = "아이디 형식이 맞지 않습니다. (4~12 자리)";
                idval = false;
            } else if (idExptext.test(id.value) == true) {
                get('POST', '/login/idcheck', { id: id.value }).then(
                    result => {
                    	if(result == "success"){
                    		error.style.color = "blue";
                    		error.innerHTML = "사용가능한 아이디입니다.";
                            idval = true;
                    	} else{
                    		error.style.color = "red";
                            error.innerHTML = "이미 존재하는 아이디입니다.";
                            signinBtn.style.backgroundColor = "";
                            signinBtn.disabled = true;
                            idval = false;
                    	}
                    }
                )
                idval = true;
            } else {
                error.innerHTML = '&nbsp;';
                idval = true;
            }
            break;
        case 'name':
        	if(e.target.value.length < 2 || e.target.value.length > 8){
        		error.style.color = "red";
                error.innerHTML = "닉네임 형식이 맞지 않습니다. (2~8 자리)";
                nameval = false;
        	} else{
        		error.innerHTML = '&nbsp;';
        		nameval = true;
        	}
        	break;
        case 'email':
            if (emailExptext.test(email.value) == false) {
            	error.style.color = "red";
                error.innerHTML = "이메일 형식이 맞지 않습니다.";
                emailval = false;
            } else {
                error.innerHTML = '&nbsp;';
                emailval = true;
            }
            break;
        case 'pw':
            if (pwExptext.test(document.querySelector('#pw').value) == false) {
            	error.style.color = "red";
                error.innerHTML = "영문, 숫자 혼합 6~20자리"
                pwval = false;
            } else {
                error.innerHTML = '&nbsp;';
            }
            break;
        case 'repw':
            if (document.querySelector('#pw').value != repw.value) {
                error.innerHTML = "비밀번호가 일치하지 않습니다.";
                pwval = false;
            } else if (pwExptext.test(document.querySelector('#pw').value) == false && document.querySelector('#pw').value == repw.value) {
                error.innerHTML = "영문, 숫자 혼합 6~20자리";
                pwval = false;
            } else {
                error.innerHTML = '&nbsp;';
                pwval = true;
            }
            break;

    }
    let signinBtn = document.querySelector('#signinForm .btn');
    if (idval == true && pwval == true && emailval == true && nameval == true) {
        signinBtn.style.backgroundColor = "#228be6";
        signinBtn.disabled = false;
    } else {
        signinBtn.style.backgroundColor = "";
        signinBtn.disabled = true;
    }

    document.querySelector('#complete-btn').addEventListener('click', function () {
        location.href = location.href;
    })


}

//ajax
function get(rest, url, obj) {
    return new Promise((resolve, reject) => {
        const xhr = new XMLHttpRequest();

        xhr.onreadystatechange = () => {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    resolve(xhr.response);
                } else {
                    reject('Error:' + xhr.status)
                }
            }
        }
        xhr.open(rest, url);
        xhr.setRequestHeader('Content-type', 'application/json');
        xhr.send(JSON.stringify(obj));
    })
}

(function () {
    let url = '';
    let rest = '';

    let loginObj = {
        id: "",
        pw: ""
    }

    //헤더 로그인 모달 결과 출력
    document.querySelector('#loginForm').addEventListener('keyup', function () {

        url = '/login';
        rest = 'POST';

        loginObj.id = document.querySelector('.text.id').value,
            loginObj.pw = document.querySelector('.text.pw').value
    })

    //로그인 처리
/*    document.querySelector('#loginForm .ajax').addEventListener('click', function () {
        get(rest, url, loginObj).then(
            result => console.log(result),
            error => document.querySelector('#loginForm .error').innerHTML = '잘못된 계정입니다.'
        )
    })*/
	    document.querySelector('.btn.ajax').addEventListener('click', function(){
	        get('POST', '/login', {
	            id: document.querySelector('.text.id').value,
	            pw: document.querySelector('.text.pw').value
	        }).then(
	                result => {
	                	console.log(result);
                       if(result == "true"){
                    	   location.href = location.href;
                        } else{
                        	document.querySelector('#loginForm .error').innerHTML = '잘못된 계정입니다.';
                        }
                    }
	        )
	    })
    
    //회원가입 처리
    document.querySelector('#signinForm .btn').addEventListener('click', function () {
        get('POST', '/login/join', {
    		id: document.querySelector('#signinForm #id').value,
            pw: document.querySelector('#signinForm #pw').value,
    		email: document.querySelector('#signinForm #email').value,
    		name: document.querySelector('#signinForm #name').value
    		}
    	)

        document.querySelector('#complete-container').style.display = "block";
    })
})();

document.querySelector('#loginForm').onkeyup = function(){
	if(document.querySelector('.text.id').value != "" && document.querySelector('.text.pw').value != ""){
		document.querySelector('.btn.ajax').style.backgroundColor = "#228be6";
	} else {
		document.querySelector('.btn.ajax').style.backgroundColor = "";
		document.querySelector('.btn.ajax').addEventListener('click', null);
    }
}