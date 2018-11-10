(function(){
	let btn = document.querySelector('#submit');
	
	window.addEventListener('load', () => {
		btn.style.backgroundColor = "#228be6";
	})
	
	
	document.querySelector('form[action="./modify"]').addEventListener('keyup', function(e){
		switch(e.target.placeholder){
		case "제목":
			if(e.path[0].value != ""){
				btn.style.backgroundColor = "#228be6";
				btn.disabled = false;
			} else{
				btn.style.backgroundColor = "#e9ecef";
				btn.disabled = true;
			}
		}
	})
	
	document.querySelector('#cancel').addEventListener('click', function(){
		history.back();
	})
})()