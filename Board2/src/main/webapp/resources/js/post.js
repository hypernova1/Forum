document.querySelector('.btn.list').addEventListener('click', () => {
	history.back();
})

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

(function(){
    let url;
    let rest;
    let obj = {
        mno: "",
		bno: "",
		cno: "",
		content: ""
    }
    /* 게시글 작성 */
    document.querySelector('#content-btn').addEventListener('click', function(){
        url = '/writecomment';
        rest = 'POST';
        obj.mno = document.querySelector('#mno').value;
        obj.content = document.querySelector('textarea').value;
        obj.bno = document.querySelector('#bno').value;
        
        get(rest, url, obj).then(
            result => document.querySelector('textarea').value = "",
    		error => document.querySelector('#loginForm .error').innerHTML = 'error.'
    		)
    });
})()
        