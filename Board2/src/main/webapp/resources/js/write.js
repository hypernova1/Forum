document.querySelector('#cancel').addEventListener('click', function(){
	location.href = "/board/list?" + document.querySelector('input[type=hidden]').value;
})

let a = function(){
	
	const btn = document.querySelector('#btn-wrapper>button');
	
	document.querySelector('form[action="./write"]').addEventListener('keyup', function(e){
		switch(e.target.placeholder){
		case "제목": content = e.path[0].value;
		}
		if(content != ""){
			btn.style.backgroundColor = "#228be6";
			btn.type = "submit";
		} else{
			btn.style.backgroundColor = "#e9ecef";
			btn.type = "button";
		}
	})
}
a();