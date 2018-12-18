window.onload = function(){
	document.getElementById("btnRegresar1").addEventListener("click",regresar);
	document.getElementById("btnRegresar2").addEventListener("click",regresar);
	
	function regresar(){
		location.href="Doctores.jsp"
	}
}