
var xhr = new XMLHttpRequest();

window.onload = function(){
	document.getElementById("btnComparar").addEventListener("click",SonIguales);
	document.getElementById("txtPasswordR").addEventListener("focus",Ocultar);
}

function SonIguales(){
	var txtPassword = document.getElementById("txtPassword").value;
	var txtPasswordR = document.getElementById("txtPasswordR").value;
	var txtCurp = document.getElementById("txtCurp").value;
	
	var form = new FormData;
	
	if(txtPassword=='' || txtPasswordR==''){
		
		$.sweetModal({
			content: 'Alguno de los campos esta vacio',
			icon: $.sweetModal.ICON_WARNING
		});


	}else{
		if((txtPassword == txtPasswordR) && (txtCurp.toUpperCase() != txtPassword.toUpperCase())){

			var rol = document.getElementById("txtRol").value;
			var id = document.getElementById("txtId").value;
			
			form.append('txtPassword',txtPassword);
			form.append('txtId',id);
			form.append('txtRol',rol);
			
			
			if(rol!="Patient"){

				xhr.open("POST","UpdateEmployeePasswordServlet");
				
				xhr.onload = function(){
					esto = new String(xhr.responseText);
					esto = xhr.response;
					
					if(esto.indexOf("DOCTOR")>-1){
					location.href="Doctores.jsp";
					}else if(esto.indexOf("NURSE")>-1){
						location.href="Nurses.jsp";
					}else{
						location.href="PrincipalAdmistrador.jsp";
					}
					
				}
				
				xhr.send(form)
				
			}else{
				
				xhr.open("POST","UpdatePatientPasswordServlet");
				
				xhr.onload = function(){
					esto = new String(xhr.responseText);
					esto = xhr.response;
					
					location.href="consultasServletPatient?id="+esto;
					
				}
				
				xhr.send(form)
			}
		}else if(txtCurp.toUpperCase() == txtPassword.toUpperCase()){
			$.sweetModal({
				content: 'La nueva contraseña es identica a la actual',
				icon: $.sweetModal.ICON_WARNING
			});
		}else{
			document.getElementById("imgMal").style.display="block";
			document.getElementById("lblLogin").style.display="block";
		}
	}
}

function Ocultar(){
	document.getElementById("imgMal").style.display="none";
	document.getElementById("lblLogin").style.display="none";
}