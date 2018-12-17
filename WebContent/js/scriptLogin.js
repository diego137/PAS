var xhr = new XMLHttpRequest();

/*$(document).ready(function(){
	
	//Buttons
	$("#btn-Mostrar").on("click",funcionMostrar);
	$("#cancelarLogin").on("click",funcionOcultarLogin);
	$("#closeLogin").on("click",funcionOcultarLogin);
	$("#btnEntrar").on("click",funcionLogear);
	
	//Get the modal
	var modal = $("#id01");
	
	function funcionMostrar(){
		$("#id01").show();
	}
	
	function funcionOcultarLogin(){
		$("#id01").hide();
	}
	
	$(body).click(function(){
		modal.hide();
	});
	
});*/

window.onload = function(){
	
	//Botones
	document.getElementById("btn-Mostrar").addEventListener("click",funcionMostrar);
	document.getElementById("cancelarLogin").addEventListener("click",funcionOcultarLogin);
	document.getElementById("closeLogin").addEventListener("click",funcionOcultarLogin);
	document.getElementById("btnEntrar").addEventListener("click",funcionLogear);
	
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

function funcionLogear(){
	var password = document.getElementById("txtPassword").value;
	var user = document.getElementById("txtUser").value;
	
	var myForm = new FormData();
	
	myForm.append("txtUser",user);
	myForm.append("txtPassword",password);
	
	console.log("Password de js: "+password);
	console.log("User de js: "+user);
	
	xhr.open("POST","LogIn");
	
	xhr.onload = () => {
		
		esto = new String(xhr.responseText);
		
		if(esto.indexOf("@")>-1){
			console.log(xhr.response);
			//location.href="cambiarPass.jsp?idEmpleado="+xhr.response;
			var div1 = esto.split("@");
			var id = div1[0];
			var curp = div1[1].split("/")[0];
			var Rol = esto.split("/")[1];
			
			location.href="ReadIndividualEmployeeServlet?id="+id;
			
		}else if(esto.indexOf("DOCTOR")>-1){
			location.href="Doctores.jsp";
		}else if(esto.indexOf("NURSE")>-1){
			location.href="PrincipalNurse.jsp";
		}else if(esto.indexOf("ADMINISTRATOR")>-1){
			location.href="AdminEmployee.jsp";
		}else{
			console.log("Entra otro AJAX");

			xhr.open("POST","LogInPatientServlet");
			xhr.onload = () => {
				texto = new String(xhr.responseText);
				if(texto.indexOf("@")>-1){
					var div2 = texto.split("@");
					var id = div2[0];
					var curp = div2[1];
					var rol = div2[2];
					
					location.href="ReadIndividualPatientServlet?id="+id;
				}else if(texto.indexOf("-")>-1){
					var array = texto.split("-");
					var id = array[0];
					location.href="ConexionServlet?id="+id;
				}else{
					$.sweetModal({
						content: 'The User or Password is Incorrect',
						icon: $.sweetModal.ICON_ERROR,
						theme: $.sweetModal.THEME_MIXED
					});
				}
				//console.log(texto);
			}
			xhr.send(myForm);
			/*$.sweetModal({
				content: 'The User or Password is Incorrect',
				icon: $.sweetModal.ICON_ERROR,
				theme: $.sweetModal.THEME_MIXED
			});*/
		}
		
		/*if(esto.indexOf("DOCTOR")>-1){
			location.href="PrincipalDoctor.jsp";
		}
		
		if(esto.indexOf("NURSE")>-1){
			location.href="PrincipalNurse.jsp";
		}
		
		if(esto.indexOf("Error")>-1){
			$.sweetModal({
				content: 'The User or Password is Incorrect',
				icon: $.sweetModal.ICON_ERROR,
				theme: $.sweetModal.THEME_MIXED
			});
		}*/
		
	}
	
	xhr.send(myForm);
}