
window.addEventListener('load', () => {
	switch(location.href.split('/')[3]){
	case "": document.querySelector('.home').style.backgroundColor = "black";
			document.querySelector('.home').style.color = "#e03131"
			break;
	case "board": document.querySelector('.freeBoard').style.backgroundColor = "black";
			document.querySelector('.freeBoard').style.color = "#e03131"
			break;
	case "qna": document.querySelector('.qna').style.backgroundColor = "black";
			document.querySelector('.qna').style.color = "#e03131"
			break;
	case "chatting": document.querySelector('.chatting').style.backgroundColor = "black";
			document.querySelector('.qna').style.color = "#e03131"
			break;
	case "notice": document.querySelector('.notice').style.backgroundColor = "black";
			document.querySelector('.notice').style.color = "#e03131"
			break;
	}
})

	
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
	
	if(document.querySelector('.modal.login')){
		let loginModal = document.querySelector('.modal.login');
		let btn = document.querySelector("#login");
		if(document.querySelector("#login") != null){
			btn.addEventListener('click', function () {
				loginModal.style.display = "block";
			})
		}
		
		loginModal.addEventListener('click', function (event) {
			if (event.target == loginModal) {
				loginModal.style.display = "none";
			}
		})
	}

let signinModal = document.querySelector('.modal.signin');
let btn2 = document.querySelector("#signin");
if(btn2 != null){
	btn2.addEventListener('click', function () {
	signinModal.style.display = "block";
	})
}

window.onclick = function (event) {
    if (event.target == signinModal) {
        signinModal.style.display = "none";
    }
}

//아이디, 네임, 이메일, 비밀번호 정규식
let idExptext = /^[A-Za-z0-9+]{4,12}$/;
let emailExptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
let pwExptext = /^.*(?=.{6,20})(?=.*[0-9])(?=.*[a-zA-Z]).*$/;

//유효하다면 true


// 로그인 모달에서 회원 가입 버튼 눌렀을 때
document.querySelector('#signinBtn').addEventListener('click', function () {
    loginModal.style.display = "none";
    signinModal.style.display = "block";
})

// 회원가입 유효성 검사
document.querySelector('#signinForm').addEventListener('keyup', (e) => {
	let idval = false;
	let pwval = false;
	let repwval = false;
	let emailval = false;
	let nameval = false;
    
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
                pwval = true;
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
                repwval = true;
            }
            break;
        default: console.log(e);

    }
    
    let signinBtn = document.querySelector('#signinForm .btn');
    if (idval == true && pwval == true && repwbal == true && emailval == true && nameval == true) {
        signinBtn.style.backgroundColor = "#228be6";
        signinBtn.disabled = false;
    } else {
        signinBtn.style.backgroundColor = "";
        signinBtn.disabled = true;
    }

    document.querySelector('#complete-btn').addEventListener('click', function () {
        location.href = location.href;
    })


})

//ajax
async function get(rest, url, obj) {
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


    document.querySelector('.btn.ajax').addEventListener('click', function(){
        get('POST', '/login', {
            id: document.querySelector('.text.id').value,
            pw: document.querySelector('.text.pw').value
        }).then(
                result => {
                   if(result == "false"){
                	   document.querySelector('#loginForm .error').innerHTML = '잘못된 계정입니다.';
                    } else{
                    	location.href = location.href;
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
    if(document.querySelector('#logout') != null){
    	document.querySelector('#logout').addEventListener('click', function(){
    		get('POST', '/login/logout', null);
    		location.href = location.href;
    	})
    }
    
    //마이페이지 눌렀을 때 - ajax로 서버에서 멤버 받아온 후 뿌려줌
    if(document.querySelector('#myPage') != null){
    	
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
    	
    	//마이페이지 유효성 검사
    	document.querySelector('#myPage-content').addEventListener('keyup', (e) => {
    		
    	    let error = document.querySelector('#mypage-modal .error');
    	    
    	    switch(e.target.id){
	    	    case "mypage-name":
		    	    if(e.target.value.length < 2 || e.target.value.length > 8){
		        		error.style.color = "red";
		                error.innerHTML = "닉네임 형식이 맞지 않습니다. (2~8 자리)";
		                console.log(emailval, pwval,repwval)
		                nameval = false;
		        	} else{
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
		        	if(document.querySelector('#mypage-pw').value == "" || pwExptext.test(document.querySelector('#mypage-pw').value)) {
		        		error.innerHTML = '&nbsp;';
		        		pwval = true;
		        		repwval = false;
		        	} else if (pwExptext.test(document.querySelector('#mypage-pw').value) == false) {
		            	error.style.color = "red";
		                error.innerHTML = "영문, 숫자 혼합 6~20자리"
		                pwval = false;
		                repwval = false;
		            } else if(pwExptext.test(document.querySelector('#mypage-pw').value) && document.querySelector('#mypage-pw').value != document.querySelector('#mypage-repw').value){
		            	error.style.color = "red";
		                error.innerHTML = "비밀번호가 일치하지 않습니다."
		                pwval = false;
		                repwval = false;
		            } else if(document.querySelector('#mypage-pw').value != document.querySelector('#mypage-repw').value) {
		            	error.style.color = "red";
		                error.innerHTML = "비밀번호가 일치하지 않습니다."
		            	pwval = false;
		                repwval = false;
		            } else if(document.querySelector('#mypage-pw').value == "" && document.querySelector('#mypage-repw').value == ""){
		            	pwval= true;
		            	repwval= true;
		            }
		            break;
		        case 'mypage-repw':
		            if (document.querySelector('#mypage-pw').value != document.querySelector('#mypage-repw').value) {
		                error.innerHTML = "비밀번호가 일치하지 않습니다.";
		                repwval = false;
		            } else if (pwExptext.test(document.querySelector('#mypage-pw').value) == false && document.querySelector('#mypage-pw').value == document.querySelector('#mypage-repw').value && document.querySelector('#mypage-pw').value != "") {
		                error.innerHTML = "영문, 숫자 혼합 6~20자리";
		                repwval = false;
		            } else if(document.querySelector('#mypage-repw').value == ""){
		                error.innerHTML = '&nbsp;';
		                repwval = true;
		            }else if(document.querySelector('#mypage-pw').value == "" && document.querySelector('#mypage-repw').value == ""){
		            	pwval= true;
		            	repwval= true;
		            } else{
		            	error.innerHTML = '&nbsp;';
		            	repwval = true;
		            }
		            break;
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
    	
    	
    	//마이페이지 확인 버튼 눌렀을 때
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

document.querySelector('#loginForm').onkeyup = function(){
	if(document.querySelector('.text.id').value != "" && document.querySelector('.text.pw').value != ""){
		document.querySelector('.btn.ajax').style.backgroundColor = "#228be6";
	} else {
		document.querySelector('.btn.ajax').style.backgroundColor = "";
		document.querySelector('.btn.ajax').addEventListener('click', null);
    }
}


//마이페이지
document.querySelector('#myPage-modal').addEventListener('click', function (event) {
	if (event.target == document.querySelector('#myPage-modal')) {
		document.querySelector('#myPage-modal').style.display = "none";
	}
})
