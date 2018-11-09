window.addEventListener('load', () => {
    // 선택된 탭 색상 바꾸기
    switch (location.href.split('/')[3]) {
        case "":
            document.querySelector('.home').style.backgroundColor = "black";
            document.querySelector('.home').style.color = "#e03131"
            break;
        case "board":
            document.querySelector('.freeBoard').style.backgroundColor = "black";
            document.querySelector('.freeBoard').style.color = "#e03131"
            break;
        case "qna":
            document.querySelector('.qna').style.backgroundColor = "black";
            document.querySelector('.qna').style.color = "#e03131"
            break;
        case "chatting":
            document.querySelector('.chatting').style.backgroundColor = "black";
            document.querySelector('.qna').style.color = "#e03131"
            break;
        case "notice":
            document.querySelector('.notice').style.backgroundColor = "black";
            document.querySelector('.notice').style.color = "#e03131"
            break;
    }
    // 각 메뉴 링크
    document.querySelector('.menu.home').addEventListener('click', () => {
        location.href = "/";
    });
    document.querySelector('.menu.freeBoard').addEventListener('click', () => {
        location.href = "/board/list";
    });
    document.querySelector('.menu.qna').addEventListener('click', () => {
        location.href = "/qna/list";
    });
    document.querySelector('.menu.chatting').addEventListener('click', () => {
        location.href = "/chatting";
    });
    document.querySelector('.menu.notice').addEventListener('click', () => {
        location.href = "/notice";
    });
})


let loginModal = document.querySelector('.modal.login');
let signinModal = document.querySelector('.modal.signin');

let loginBtn = document.querySelector("#login");
let signinBtn = document.querySelector("#signin");

// 헤더에 있는 로그인 버튼 눌렀을 때
if(loginBtn != null){
	loginBtn.addEventListener('click', () => {
		loginModal.style.display = "block";
	})
}
// 헤더에 있는 회원가입 버튼 눌렀을 때
if(signinBtn != null){
	signinBtn.addEventListener('click', () => {
		signinModal.style.display = "block";
	})
}
// 로그인 모달에서 회원가입 버튼 눌렀을 때
document.querySelector('#signinBtn').addEventListener('click', () => {
    loginModal.style.display = "none";
    signinModal.style.display = "block";
})
// 로그인, 회원가입 모달 빈 영역을 눌렀을 때
window.addEventListener('click', (e) => {
    if (e.target == signinModal) signinModal.style.display = "none";
    else if (e.target == loginModal) loginModal.style.display = "none";
})

// 아이디, 네임, 이메일, 비밀번호 정규식
let idExptext = /^[A-Za-z0-9+]{4,12}$/;
let emailExptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
let pwExptext = /^.*(?=.{6,20})(?=.*[0-9])(?=.*[a-zA-Z]).*$/;

let idval;
let pwval;
let repwval;
let emailval;
let nameval;

// 회원가입 유효성 검사
document.querySelector('#signinForm').addEventListener('keyup', (e) => {
    let error = document.querySelector('#signinForm>.error');

    switch (e.target.id) {
        case 'id':
            // 정규식이 맞지 않으면
            if (idExptext.test(e.target.value) == false) {
                idval = false;
                error.style.color = "red";
                error.innerHTML = "아이디 형식이 맞지 않습니다. (4~12 자리)";
                // 정규식이 맞고
            } else {
                get('POST', '/login/idcheck', {
                    id: id.value
                }).then(
                    result => {
                        // 사용가능한 아이디면
                        if (result == "success") {
                            error.style.color = "blue";
                            error.innerHTML = "사용가능한 아이디입니다.";
                            idval = true;
                            // 이미존재하는 아이디면
                        } else {
                            error.style.color = "red";
                            error.innerHTML = "이미 존재하는 아이디입니다.";
                            signinBtn.style.backgroundColor = "";
                            signinBtn.disabled = true;
                            idval = false;
                        }
                    }
                )
            }
            break;
        case 'name':
            // 닉네임이 두자리 이하, 8자리 이상이면
            if (e.target.value.length < 2 || e.target.value.length > 8) {
                error.style.color = "red";
                error.innerHTML = "닉네임 형식이 맞지 않습니다. (2~8 자리)";
                nameval = false;
            } else {
                error.innerHTML = '&nbsp;';
                nameval = true;
            }
            break;
        case 'email':
            // 이메일 형식이 맞지 않으면
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
            // 비밀번호 형식이 맞지 않으면
            if (pwExptext.test(e.target.value) == false) {
                error.style.color = "red";
                error.innerHTML = "영문, 숫자 혼합 6~20자리"
                pwval = false;
            } else {
                error.innerHTML = '&nbsp;';
                pwval = true;
            }
            break;
        case 'repw':
            // 패스워드와 리패스워드가 일치하지 않으면
            if (document.querySelector('#pw').value != repw.value) {
                error.innerHTML = "비밀번호가 일치하지 않습니다.";
                pwval = false;
                // 패스워드가 형식이 맞지않고, 패스워드가 리패스워드와 같으면
            } else if (pwExptext.test(document.querySelector('#pw').value) == false && document.querySelector('#pw').value == repw.value) {
                error.innerHTML = "영문, 숫자 혼합 6~20자리";
                pwval = false;
            } else {
                error.innerHTML = '&nbsp;';
                pwval = true;
                repwval = true;
            }
    }
    console.log("idval:" + idval +" pwval: " +  pwval + " repwval: " + repwval + " emailval: " +  emailval + " nameval: " + nameval);
    let signinBtn = document.querySelector('#signinForm .btn');
    if (idval && pwval && repwval && emailval && nameval) {
        signinBtn.style.backgroundColor = "#228be6";
        signinBtn.disabled = false;
    } else {
        signinBtn.style.backgroundColor = "";
        signinBtn.disabled = true;
    }

})

document.querySelector('#complete-btn').addEventListener('click', () => {
	location.href = location.href;
})

// ajax 요청 함수
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
	document.querySelector('.header.modal.login').addEventListener('keyup', (e) => {
		if(!(document.querySelector('.text.id').value == "" || document.querySelector('.text.pw').value == "")){
			document.querySelector('.btn.ajax').style.backgroundColor = "#228be6";
			document.querySelector('.btn.ajax').disabled = false;
		} else{
			document.querySelector('.btn.ajax').style.backgroundColor = "";
			document.querySelector('.btn.ajax').disabled = true;
			
		}
	})
	// 로그인 버튼 클릭했을 때
	document.querySelector('.btn.ajax').addEventListener('click', () => {
		get('POST', '/login', {
			id: document.querySelector('.text.id').value,
			pw: document.querySelector('.text.pw').value
		}).then(
				result => {
					// 아이디나 비밀번호가 틀리면
					if (result == "false") {
						document.querySelector('#loginForm .error').innerHTML = '잘못된 계정입니다.';
					} else if(result = "true") {
					// 맞으면
						location.reload();
					}
				}
		)
	})
    

    // 회원가입 버튼을 눌렀을 때
    document.querySelector('#signinForm .btn').addEventListener('click', () => {
        get('POST', '/login/join', {
            id: document.querySelector('#signinForm #id').value,
            pw: document.querySelector('#signinForm #pw').value,
            email: document.querySelector('#signinForm #email').value,
            name: document.querySelector('#signinForm #name').value
        }).then(result => document.querySelector('#complete-container').style.display = "block")

    })

    // 로그인 후 로그아웃 버튼 눌렀을 때
    if (document.querySelector('#logout') != null) {
        document.querySelector('#logout').addEventListener('click', () => {
            get('POST', '/login/logout', null).then(() => location.reload());
        })
    }

    // 마이페이지 눌렀을 때
    if (document.querySelector('#myPage') != null) {

        let pwval = true;
        let repwval = true;
        let emailval = true;
        let nameval = true;

        document.querySelector('#myPage').addEventListener('click', () => {
            document.querySelector('#myPage-modal').style.display = "block";
            get('GET', '/member/profile?mno=' + document.querySelector('#session').value, null)
                .then(result => {
                    let resultObj = JSON.parse(result)
                    document.querySelector('#mypage-id').value = resultObj.id
                    document.querySelector('#mypage-name').value = resultObj.name
                    document.querySelector('#mypage-email').value = resultObj.email
                })
        })

        // 마이페이지 유효성 검사
        document.querySelector('#myPage-content').addEventListener('keyup', (e) => {

            let error = document.querySelector('#myPage-modal .error');

            switch (e.target.id) {
                case "mypage-name":
                    if (e.target.value.length < 2 || e.target.value.length > 8) {
                        error.style.color = "red";
                        error.innerHTML = "닉네임 형식이 맞지 않습니다. (2~8 자리)";
                        console.log(emailval, pwval, repwval)
                        nameval = false;
                    } else {
                        error.innerHTML = '&nbsp;';
                        nameval = true;
                    }
                    break;
                case 'mypage-email':
                    if (emailExptext.test(e.target.value) == false) {
                        error.style.color = "red";
                        error.innerHTML = "이메일 형식이 맞지 않습니다.";
                        emailval = false;
                    } else {
                        error.innerHTML = '&nbsp;';
                        emailval = true;
                    }
                    break;
                case 'mypage-pw':
                    if (document.querySelector('#mypage-pw').value == "" || pwExptext.test(document.querySelector('#mypage-pw').value)) {
                        error.innerHTML = '&nbsp;';
                        pwval = true;
                        repwval = false;
                    } else if (pwExptext.test(document.querySelector('#mypage-pw').value) == false) {
                        error.style.color = "red";
                        error.innerHTML = "영문, 숫자 혼합 6~20자리"
                        pwval = false;
                        repwval = false;
                    } else if (pwExptext.test(document.querySelector('#mypage-pw').value) && document.querySelector('#mypage-pw').value != document.querySelector('#mypage-repw').value) {
                        error.style.color = "red";
                        error.innerHTML = "비밀번호가 일치하지 않습니다."
                        pwval = false;
                        repwval = false;
                    } else if (document.querySelector('#mypage-pw').value != document.querySelector('#mypage-repw').value) {
                        error.style.color = "red";
                        error.innerHTML = "비밀번호가 일치하지 않습니다."
                        pwval = false;
                        repwval = false;
                    } else if (document.querySelector('#mypage-pw').value == "" && document.querySelector('#mypage-repw').value == "") {
                        pwval = true;
                        repwva = true;
                    }
                    break;
                case 'mypage-repw':
                    if (document.querySelector('#mypage-pw').value != document.querySelector('#mypage-repw').value) {
                        error.innerHTML = "비밀번호가 일치하지 않습니다.";
                        repwval = false;
                    } else if (pwExptext.test(document.querySelector('#mypage-pw').value) == false && document.querySelector('#mypage-pw').value == document.querySelector('#mypage-repw').value && document.querySelector('#mypage-pw').value != "") {
                        error.innerHTML = "영문, 숫자 혼합 6~20자리";
                        repwval = false;
                    } else if (document.querySelector('#mypage-repw').value == "") {
                        error.innerHTML = '&nbsp;';
                        repwval = true;
                    } else if (document.querySelector('#mypage-pw').value == "" && document.querySelector('#mypage-repw').value == "") {
                        pwval = true;
                        repwval = true;
                    } else {
                        error.innerHTML = '&nbsp;';
                        repwval = true;
                    }
                    break;
            }
            if(document.querySelector('#mypage-pw') == "" && document.querySelector('#mypage-repw') == ""){
            	pwval = true;
            	repwval = true;
            }

            let updateBtn = document.querySelector('#mypage-btn');
            if (pwval && repwval && emailval && nameval) {
                updateBtn.style.backgroundColor = "#228be6";
                updateBtn.disabled = false;
            } else {
                console.log(pwval, repwval, emailval, nameval)
                updateBtn.style.backgroundColor = "#e9ecef";
                updateBtn.disabled = true;
            }
        })


        // 마이페이지 확인 버튼 눌렀을 때
        document.querySelector('#mypage-btn').addEventListener('click', () => {
            get("POST", '/member/profile', {
                mno: document.querySelector('#session').value,
                name: document.querySelector('#mypage-name').value,
                pw: document.querySelector('#mypage-pw').value,
                email: document.querySelector('#mypage-email').value
            }).then(result => (location.href = location.href));

        })
    }
})();


// 마이페이지
document.querySelector('#myPage-modal').addEventListener('click', function (event) {
    if (event.target == document.querySelector('#myPage-modal')) {
        document.querySelector('#myPage-modal').style.display = "none";
    }
})