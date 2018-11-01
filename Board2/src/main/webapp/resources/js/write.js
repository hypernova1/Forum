document.querySelector('#cancel').addEventListener('click', function(){
	location.href = document.referrer;
})

let a = function(){
	
	let writer = "";
	let pw = "";
	let content = "";
	const btn = document.querySelector('#btn-wrapper>button');
	
	document.querySelector('form[action="./write"]').addEventListener('keyup', function(e){
		switch(e.target.placeholder){
		case "글쓴이": writer = e.path[0].value; break;
		case "비밀번호": pw = e.path[0].value; break;
		case "제목": content = e.path[0].value;
		}
		if(writer != "" && pw != "" && content != ""){
			btn.style.backgroundColor = "#228be6";
			btn.type = "submit";
		} else{
			btn.style.backgroundColor = "#e9ecef";
			btn.type = "button";
		}
	})
}
a();