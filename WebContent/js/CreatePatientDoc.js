var xhr = new XMLHttpRequest();

$(document).ready(function(){
	// Activate tooltip
	$('[data-toggle="tooltip"]').tooltip();
	
document.getElementById('btnAddp').addEventListener("click",CrearPaciente);
document.getElementById('txtCurp').addEventListener("keyup",CopiarEntradas);


function CrearPaciente()
{		  
	//var id = document.getElementById('txtId').value;
	var  nombrePaciente = document.getElementById('txtNombre').value;
	var apellidosPaciente = document.getElementById('txtApellidos').value;
	var curpPaciente = document.getElementById('txtCurp').value;
	var usuarioPaciente = document.getElementById('txtUsuario').value;
	var contrasenaPaciente = document.getElementById('txtContrasena').value;
	

	

	$.ajax({ 
	type: "POST", 
	url: "CreatePatientsServlet", 
	data: {txtNombre:nombrePaciente,txtApellidos:apellidosPaciente,txtCurp:curpPaciente,txtUsuario:usuarioPaciente,txtContrasena:contrasenaPaciente}, 
			//cuando la respuesta del servlet es exitosa
			success: function(result){
			
				if(result=='vacioName')
					Swal({ title: 'Fill Name input', confirmButtonText: 'Ok', onAfterClose: () => {
					setTimeout(() => $("#txtNombre").focus(), 100);}});

				if(result=='vacioApellidos')
					Swal({ title: 'Fill Last Name input', confirmButtonText: 'Ok', onAfterClose: () => {
					setTimeout(() => $("#txtApellidos").focus(), 100);}});
					
				if(result=='vacioCurp')
					Swal({ title: 'Fill Curp input', confirmButtonText: 'Ok', onAfterClose: () => {
					setTimeout(() => $("#txtCurp").focus(), 100);}});
					
				if(result=='vacioUsuario')
					Swal({ title: 'Fill User input', confirmButtonText: 'Ok', onAfterClose: () => {
					setTimeout(() => $("#txtUsuario").focus(), 100);}});
					
				if(result=='vacioContrasena')
					Swal({ title: 'Fill Password input', confirmButtonText: 'Ok', onAfterClose: () => {
					setTimeout(() => $("#txtContrasena").focus(), 100);}});
		
				if(result=='duplicate')
				Swal({ title: 'Id already exist', confirmButtonText: 'Ok', onAfterClose: () => {
					setTimeout(() => $("#txtId").focus(), 100);}}); 	
			//  en el servlet si nRegistro > 0 --> recarga la pagina principal donde se muestra la tabla
				if(result=='succes'){ 
				swal('Succesfull Added').then(function(){window.location.replace("Doctores.jsp");}) 
				}
			},
			//Si ocurre un error en el servlet muestra mensaje
			error:function(jqXHR, textStatus, errorThrown){
				Swal({ title: 'Server error', confirmButtonText: 'Ok', onAfterClose: () => {
					setTimeout(() => $("#txtId").focus(), 100);}  
					});}		
	});          
	}



	function CopiarEntradas()
	{
 		txtUsuario.value = txtCurp.value;
 		txtContrasena.value = txtCurp.value;
	}
	
});
