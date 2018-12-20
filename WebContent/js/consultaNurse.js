window.onload = function(){
	document.getElementById("btnRegresar1").addEventListener("click",regresar);
	document.getElementById("btnRegresar2").addEventListener("click",regresar);
	document.getElementById("btnCerrar").addEventListener("click",cerrarSesion);
	
	function cerrarSesion(){
		location.href="index.jsp"
	}
	
	function regresar(){
		console.log("aqui");
		location.href="Nurses.jsp"
	}
}