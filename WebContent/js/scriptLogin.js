var xhr = new XMLHttpRequest();

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
	
	xhr.open("POST","LogIn");
	
	xhr.onload = () => {
		
		esto = new String(xhr.responseText);
		
		if(esto.indexOf("@")>-1){
			
			var div1 = esto.split("@");
			var id = div1[0];
			var curp = div1[1].split("/")[0];
			var Rol = esto.split("/")[1];
			
			location.href="ReadIndividualEmployeeServlet?id="+id;
			
		}else if(esto.indexOf("DOCTOR")>-1){
			location.href="Doctores.jsp";
		}else if(esto.indexOf("NURSE")>-1){
			location.href="Nurses.jsp";
		}else if(esto.indexOf("ADMINISTRATOR")>-1){
			location.href="PrincipalAdmistrador.jsp";
		}else{

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
					location.href="consultasServletPatient?id="+id;
				}else{
					$.sweetModal({
						content: 'The User or Password is Incorrect',
						icon: $.sweetModal.ICON_ERROR,
						theme: $.sweetModal.THEME_MIXED
					});
				}
			}
			xhr.send(myForm);
		}
		
	}
	
	xhr.send(myForm);
}