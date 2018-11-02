document.querySelector('#cancel').addEventListener('click', function(){
	history.back();
	
})
document.querySelector('#submit').addEventListener('click', function(){
	document.querySelector('#form').method = "post";
	document.querySelector('#form').submit();	
});

let a = function(){
	
	const btn = document.querySelector('#submit');
	
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