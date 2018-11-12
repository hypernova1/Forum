document.querySelector('.btn.list').addEventListener('click', () => {
    location.href = "/board/list?page=" + document.querySelector('#page').value;
});


(function () {
	
    //포스트 수정 페이지로 이동
    if(document.querySelector('.inline.modify.btn')){
		document.querySelector('.inline.modify.btn').addEventListener('click', () => {
	    	location.href = "/board/modify?bno=" + document.querySelector('#bno').value + "&page=" + document.querySelector('#page').value;
	    })
    }
    
    //포스트 삭제
    if(document.querySelector('.inline.delete.btn')){
	    document.querySelector('.inline.delete.btn').addEventListener('click', function(){
	    	document.querySelector('#modifyForm').method = "post";
	    	document.querySelector('#modifyForm').action = "./delete";
	    	document.querySelector('#modifyForm').submit();	
	    });
    }
    
    //추천
    if(document.querySelector('#true') != null){
    	document.querySelector("#true").addEventListener('click', () => {
    		location.href = "/board/recommend?bno=" 
    			+ document.querySelector('#bno').value
    			+ "&page=" + document.querySelector('#page').value
    			+ "&recom=true&mno=" + document.querySelector('#session').value
    			+ "&type=1";
    	})
    	//반대
    	document.querySelector("#false").addEventListener('click', () => {
    		location.href = "/board/recommend?bno=" 
    			+ document.querySelector('#bno').value
    			+ "&page=" + document.querySelector('#page').value
    			+ "&recom=false&mno=" + document.querySelector('#session').value
    			+ "&type=1";
    	})
    }

    //댓글 페이지에 뿌려주기
    let getCommentList = (result) => {
        let resultObj = JSON.parse(result);
        let list = resultObj.list
        
        for (let i = 0; i < list.length; i++) {
            if(document.querySelector('#session').value == list[i].mno){
                document.querySelector('#comment-list').innerHTML += `<li class="comment" id="${list[i].cno}">
                    <div class="left comment-writer">${list[i].name}</div>
                    <div class="modify-btn left ${list[list.length-1].cno}">수정</div>
                    <div class="remove-btn left ${list[list.length-1].cno}">삭제</div>
                    <div class="right">${list[i].regdate}</div>
                <br>
                <div class="comment-content">${list[i].content}</div>
            </li>`
            } else 
            {
                document.querySelector('#comment-list').innerHTML += `<li class="comment">
                <div>
                    <div class="left comment-writer">${list[i].name}</div>
                    <div class="right">${list[i].regdate}</div>
                </div><br>
                <div class="comment-content">${list[i].content}</div>
            </li>`
            }
            document.querySelector('#comment-view>span').innerText = resultObj.count;

        }
        document.querySelector('#comment-view>span').innerText = resultObj.count;
    }

    let url = '';
    let rest = '';

    let commentObj = {
        mno: "",
        bno: "",
        cno: "",
        content: "",
        type: 1
    }

    // 포스트 페이지 로딩시 댓글 출력
    window.addEventListener('load', () => {
        url = '/comment/read?type=1&bno=' + document.querySelector('#bno').value;
        rest = 'GET';

        get(rest, url, commentObj).then(
            result => {
            	console.log(result)
                getCommentList(result);
            }
        )
    })

    if(document.querySelector('#content-textarea') != null){
    	document.querySelector('#content-textarea').addEventListener('keyup', () => {
    		if(document.querySelector('#content-textarea').value == ""){
    			document.querySelector('#content-btn').disabled = true;
    		} else{
    			document.querySelector('#content-btn').disabled = false;
    		}
    	})
    }
    
    //댓글 작성
    if(document.querySelector('#content-btn') != null){
    	document.querySelector('#content-btn').addEventListener('click', function () {
    		url = '/comment/write';
    		rest = 'POST';
    		commentObj.mno = document.querySelector('#session').value;
    		commentObj.content = document.querySelector('#content-textarea').value;
    		commentObj.bno = document.querySelector('#bno').value;
    		
    		get('POST', '/comment/write', commentObj).then( result => {
    			document.querySelector('#comment-list').innerHTML = "";
    			getCommentList(result);
    			document.querySelector('#content-textarea').value = "";
    		})
    	});
    }

    
    //댓글 삭제,수정
    document.querySelector('#comment-list').addEventListener('mouseover', function(e) {
    	//삭제
    	if(e.target.className.split(' ')[0] == "remove-btn"){
    		e.target.addEventListener('click', () => {
    			get('POST', '/comment/remove', { bno: document.querySelector('#bno').value, cno: e.target.className.split(' ')[2], type: 1})
    			.then(result => {
                    document.querySelector('#comment-list').innerHTML = "";
    				getCommentList(result);
    			})
    		})
    	}
    	//수정
    	if(e.target.className.split(' ')[0] == "modify-btn"){
    		e.target.addEventListener('click', () => {
    			console.log(e.target.parentElement)
    			e.target.parentElement.children[5].innerHTML = `<div id='commentModify-box'><textarea id="modify-textarea">${e.target.parentElement.children[5].textContent}</textarea><button id='commentModify-btn'>수정</button></div>`
    		})
        }
        if(e.target.id == "commentModify-btn"){
            document.querySelector('#commentModify-btn').addEventListener('click', () => {
                get("POST", "/comment/modify", {
                    cno: e.target.parentElement.parentElement.parentElement.id,
                    bno: document.querySelector('#bno').value,
                    content: document.querySelector('#modify-textarea').value,
                    type: 1
                }).then( result => {
                	document.querySelector('#comment-list').innerHTML = "";
    				getCommentList(result);
                })
                
            })
        }
    })
    
    
    
    
    
})();

