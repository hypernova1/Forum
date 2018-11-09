(function(){
	
	document.querySelector('form[action="/board/modify"]').addEventListener('keyup', function(e){
		console.log(e.target)
		let title = document.querySelector('#title').value
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
	
	document.querySelector('#cancel').addEventListener('click', function(){
		history.back();
	})
})()