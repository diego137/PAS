window.onload = function(){
	
	//Botones
	document.getElementById("btn-Mostrar").addEventListener("click",funcionMostrar);
	document.getElementById("cancelarLogin").addEventListener("click",funcionOcultarLogin);
	document.getElementById("closeLogin").addEventListener("click",funcionOcultarLogin);
	
	// Get the modal
	var modal = document.getElementById('id01');

	// When the user clicks anywhere outside of the modal, close it
	window.onclick = function(event) {
	    if (event.target == modal) {
	        modal.style.display = "none";
	    }
	}
	
	
}


function funcionMostrar(){
	document.getElementById("id01").style.display = "block";
}

function funcionOcultarLogin(){
	document.getElementById("id01").style.display = "none";
}