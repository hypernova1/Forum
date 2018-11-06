(function(){
	
	const btn = document.querySelector('#submit');
	window.addEventListener('load', () => {
		btn.style.backgroundColor = "#228be6";
	})
	
	document.querySelector('form[action="./modify"]').addEventListener('keyup', function(e){
		let title = document.querySelector('#title').value
		console.log(e)
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