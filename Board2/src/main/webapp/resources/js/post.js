document.querySelector('.btn.list').addEventListener('click', () => {
    history.back();
});


(function () {

    let getCommnetList = (result) => {
        let resultObj = JSON.parse(result);
        let list = resultObj.list
        for (let i = 0; i < list.length; i++) {
            document.querySelector('#comment-list').innerHTML += `<li class="comment">
                <div>
                    <div class="left">${list[i].name}</div>
                    <div class="remove-btn left">수정</div>
                    <div class="remove-btn left">삭제</div>
                    <div class="right">${list[i].regdate}</div>
                </div><br>
            <div class="comment-content">${list[i].content}</div>
        </li>`

        }
        document.querySelector('#comment-view>span').innerText = resultObj.count;
    }

    let url = '';
    let rest = '';

    let commentObj = {
        mno: "",
        bno: "",
        cno: "",
        content: ""
    }

    // 포스트 페이지 로딩시
    window.onload = () => {
        url = '/readcomment?bno=' + document.querySelector('#bno').value;
        rest = 'GET';

        get(rest, url, commentObj).then(
            result => {
                getCommnetList(result);
            }
        )
    }


    /* 코멘트 작성 */
    document.querySelector('#content-btn').addEventListener('click', function () {
        url = '/writecomment';
        rest = 'POST';
        commentObj.mno = document.querySelector('#mno').value;
        commentObj.content = document.querySelector('textarea').value;
        commentObj.bno = document.querySelector('#bno').value;

        get(rest, url, commentObj).then(
            result => {
                document.querySelector('textarea').value = ""
                let resultObj = JSON.parse(result);
                let list = resultObj.list;
                document.querySelector('#comment-list').innerHTML += `<li class="comment">
                <div>
                    <div class="left">${list[list.length-1].name}</div>
                    <div class="remove-btn left">수정</div>
                    <div class="remove-btn left">삭제</div>
                    <div class="right">${list[list.length-1].regdate}</div>
                </div><br>
            <div class="comment-content">${list[list.length-1].content}</div>
        </li>`
    	document.querySelector('#comment-view>span').innerText = resultObj.count;

        
            },
            error => document.querySelector('#loginForm .error').innerHTML = 'error.'
        )
    });
    //수정 페이지로 이동
    document.querySelector('.inline.modify.btn').addEventListener('click', () => {
    	location.href = "/board/modify?bno=" + document.querySelector('#bno').value + "&page=" + document.querySelector('#page').value;
    })
    
    //삭제
    document.querySelector('.inline.delete.btn').addEventListener('click', function(){
    	console.log(document.querySelector('#modifyForm').method);
    	document.querySelector('#modifyForm').method = "post";
    	document.querySelector('#modifyForm').action = "./delete";
    	document.querySelector('#modifyForm').submit();	
    });
    
})();

