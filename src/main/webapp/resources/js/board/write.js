document.querySelector('#cancel').addEventListener('click', function(){
	history.back();
	
})
document.querySelector('#submit').addEventListener('click', function(){
	document.querySelector('#form').method = "post";
	document.querySelector('#form').submit();	
});

(function(){
	
	const btn = document.querySelector('#submit');
	let title = '';
	document.querySelector('form[action="./write"]').addEventListener('keyup', function(e){
		switch(e.target.placeholder){
		case "제목": title = e.path[0].value;
		}
		if(title != ""){
			btn.style.backgroundColor = "#228be6";
			btn.disabled = false;
		} else{
			btn.style.backgroundColor = "#e9ecef";
			btn.disabled = true;
		}
	})
})()
