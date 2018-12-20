window.onload = function(){
	document.getElementById("btnCerrar").addEventListener("click",cerrarSesion);
	
	function cerrarSesion(){
		location.href="index.jsp"
	}
}