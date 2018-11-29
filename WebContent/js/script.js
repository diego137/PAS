window.onload = function(){
	document.getElementById("btn-Mostrar").addEventListener("click",mostrarLogin);
}

function mostrarLogin(){
	if(document.getElementById("parrafo").style.display == "block"){
		document.getElementById("parrafo").style.display = "none";
	}else{
		document.getElementById("parrafo").style.display = "block";
	}
}