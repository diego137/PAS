
var xhr = new XMLHttpRequest();
$(document).ready(function(){
	// Activate tooltip
	$('[data-toggle="tooltip"]').tooltip();

document.getElementById('btnAdd').addEventListener("click",CrearEmpleado);

document.getElementById('txtCurp').addEventListener("keyup",CopiarEntradas);




function CrearEmpleado()
{		  
	var id = document.getElementById('txtId').value;
	var nombre = document.getElementById('txtNombre').value;
	var apellido = document.getElementById('txtApellidos').value;
	var curp = document.getElementById('txtCurp').value;
	var usuario = document.getElementById('txtUsuario').value;
	var contrasena = document.getElementById('txtContrasena').value;
	var rol = document.getElementById('txtRol').value;

	$.ajax({ 
	type: "POST", 
	url: "CreateEmployeesServlet", 
	data: {txtId:id,txtNombre:nombre,txtApellidos:apellido,txtCurp:curp,txtUsuario:usuario,txtContrasena:contrasena,txtRol:rol}, 
			//cuando la respuesta del servlet es exitosa
			success: function(result){
				if(result=='negativeNumber')
					Swal({ title: 'Only positive numbers', confirmButtonText: 'Ok', onAfterClose: () => {
					setTimeout(() => $("#txtId").focus(), 100);}});

				if(result=='vacioId')
					Swal({ title: 'Fill Id Employee input', confirmButtonText: 'Ok', onAfterClose: () => {
					setTimeout(() => $("#txtId").focus(), 100);}});

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
				swal('Succesfull Added').then(function(){window.location.replace("AdminEmployee.jsp");}) 
				}
			},
			//Si ocurre un error en el servlet muestra mensaje
			error:function(jqXHR, textStatus, errorThrown){
				Swal({ title: 'Error, Type only numbers', confirmButtonText: 'Ok', onAfterClose: () => {
					setTimeout(() => $("#txtId").focus(), 100);}  
					});}		
	});          
	}



	function CopiarEntradas()
	{
 		txtUsuario.value = txtCurp.value;
 		txtContrasena.value = txtCurp.value;
	}

	var checkbox = $('table tbody input[type="checkbox"]');
	$("#selectAll").click(function(){
		if(this.checked){
			checkbox.each(function(){
				this.checked = true;                        
			});
		} else{
			checkbox.each(function(){
				this.checked = false;                        
			});
		} 
	});
	checkbox.click(function(){
		if(!this.checked){
			$("#selectAll").prop("checked", false);
		}
	});
	
});






