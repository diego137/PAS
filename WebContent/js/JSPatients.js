
var xhr = new XMLHttpRequest();

$(document).ready(function(){
	// Activate tooltip
	$('[data-toggle="tooltip"]').tooltip();
	
document.getElementById('btnAdd').addEventListener("click",CrearPaciente);
document.getElementById('txtCurp').addEventListener("keyup",CopiarEntradas);
document.getElementById('btnBuscarTodos').addEventListener("click",BuscarTodos);
document.getElementById('txtNombreM').addEventListener("input",hacer);
document.getElementById('txtApellidosM').addEventListener("input",hacer);
document.getElementById('btnDeleteRecords').addEventListener("click",eliminarMuchos);
document.getElementById('btnRegresar').addEventListener("click",regresar);

function regresar(){
	location.href="PrincipalAdmistrador.jsp";
}

function eliminarMuchos(){
	
	var checkArray = [];
	var checks = document.getElementsByClassName("paraEliminar");
	var j=0;
	for(var i = 0 ; i <= checks.length-1; i++){
		if(checks[i].checked){
			checkArray[j] = checks[i].value;
			j++;
		}
	}
	
	if(checkArray.length>0){
		var miFormita = new FormData();
		
		miFormita.append("cajas",checkArray);
		
		xhr.open("POST","DeleteCheckBoxPatientsServlet",true);
		
		xhr.onload=()=>{
			console.log(xhr.response);
			var respuesta = parseInt(xhr.response);
			if(respuesta==0){
				$.sweetModal({
					content: "No record was deleted",
					icon: $.sweetModal.ICON_ERROR
				});
				window.location.replace("AdminPatient.jsp");
			}else if(respuesta == 1){
				
				$("#deleteEmployeeModal").modal("hide");
				
				$.sweetModal.confirm('SUCCESS', 'A record was deleted', function() {
					window.location.replace("AdminPatient.jsp");
				}, function() {
					window.location.replace("AdminPatient.jsp");
				});
				
			}else{
				$("#deleteEmployeeModal").modal("hide");
			
				$.sweetModal.confirm('SUCCESS', respuesta+" records were deleted", function() {
					window.location.replace("AdminPatient.jsp");
				}, function() {
					window.location.replace("AdminPatient.jsp");
				});
			}
		}
		
		xhr.send(miFormita);
	}else{
		window.location.replace("AdminPatient.jsp");
	}
	/*for(var i = 0 ; i < checkArray.length; i++){
		console.log("Valores Checados: "+checkArray[i]);
	}*/
}

function BuscarTodos()
{
	window.location.replace("AdminPatient.jsp");
}

function hacer(){
	$('#editEmployeeModal').find('.modal-title').text($('#txtNombreM').val()+" "+$('#txtApellidosM').val());
}


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
				swal('Succesfull Added').then(function(){window.location.replace("AdminPatient.jsp");}) 
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
	
	$('#editEmployeeModal').on('show.bs.modal', function (event) {
		  var button = $(event.relatedTarget) // Button that triggered the modal
		  var recipient = button.data('whatever')// Extract info from data-* attributes
		  
		  //Separar campos obtenidos
		  for(var i = 0; i < recipient.length; i++){
			  if(recipient.charAt(i)=="#"){
				  recipient = recipient.replace("#"," ");
			  }
		  }
		  
		  var arreglo = recipient.split("@");
		   
		  //console.log(arreglo)//Visualizar que envia
		  
		  
		  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
		  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
		  var modal = $(this)
		  modal.find('.modal-title').text(arreglo[1]+" "+arreglo[2])
		  modal.find('.modal-body').find('input[id="txtIdM"]').val(arreglo[0]);
		  modal.find('.modal-body').find('input[id="txtNombreM"]').val(arreglo[1]);
		  modal.find('.modal-body').find('input[id="txtApellidosM"]').val(arreglo[2]);
		  modal.find('.modal-body').find('input[id="txtCurpM"]').val(arreglo[3]);
		  modal.find('.modal-body').find('select[id="txtRolM"]').val(arreglo[4]);
		})
	
});
