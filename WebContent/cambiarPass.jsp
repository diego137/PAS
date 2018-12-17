<%@ page import="com.employees.model.Employees" %> 
<%@ page import="com.patients.model.Patient" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <link rel="stylesheet" type="text/css" href="css/font-awesome.min.css">
  <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
  <link  rel="stylesheet" type="text/css" href="css/jquery.sweet-modal.min.css">
  <link rel="stylesheet" type="text/css" href="css/style.css">
  <link rel="stylesheet" type="text/css" href="css/cambiarPass.css">
  
</head>
<body>

<div id="bg">
	<div id="bg-color">
		<div class="container">
			<div class="row">
			</div>
			<div class="row">
				<div class="col-md-12">
					<%String rol = (String)request.getAttribute("Rol");
					Employees miEmployee = new Employees();
					Patient miPatient = new Patient();
					if(rol.equals("Empleado")){
						miEmployee = (Employees)request.getAttribute("miEmployee");
					}else{
						miPatient = (Patient)request.getAttribute("miPatient");
					}%>
					<h1 class="text-center">Es la primera vez que ingresas, por favor cambia tu contraseña</h1>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-4 col-md-4">
				</div>
				<div class="col-sm-4 col-md-4">
					<form class="form-group" action="actualizarPassword" method="POST">
						<div class="row" id="dId">
							<div class="col-md-10">
								<p>
									<label for="txtId"> ID: </label>
									<%if(rol.equals("Empleado")){%>
										<input id="txtId" name="txtId" type="text" class="form-control col-md-8" disabled value=<%=miEmployee.getIdEmployee() %>>
									<%}else{ %>
										<input id="txtId" name="txtId" type="text" class="form-control col-md-8" disabled value=<%=miPatient.getIdPaciente() %>>
									<%} %>
								</p>
							</div>
						</div>
						<div class="row" id="dCurp">
							<div class="col-md-10">
								<p>
									<label for="txtCurp"> Curp: </label>
									<%if(rol.equals("Empleado")){%>
										<input id="txtCurp" name="txtCurp" type="text" class="form-control col-md-8" disabled value=<%=miEmployee.getCurp() %>>
									<%}else{ %>
										<input id="txtCurp" name="txtCurp" type="text" class="form-control col-md-8" disabled value=<%=miPatient.getCurpPaciente() %>>
									<%} %>
								</p>
							</div>
						</div>
						<div class="row" id="dRol">
							<div class="col-md-10">
								<p>
									<label for="txtRol"> Rol: </label>
									<%if(rol.equals("Empleado")){%>
										<input id="txtRol" name="txtRol" type="text" class="form-control col-md-8" disabled value=<%=miEmployee.getRol() %>>
									<%}else{ %>
										<input id="txtRol" name="txtRol" type="text" class="form-control col-md-8" disabled value="Patient">
									<%} %>
								</p>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-2 col-md-2">
							</div>
							<div class="col-sm-4 col-md-4">
								<img id="imgSecure" class="img-fluid mx-auto d-block" src="img/medical-report.png"/>
							</div>
							<div class="col-sm-6 col-md-6">
							</div>
						</div>
						
						<div class="row">
						<p>
							<div class="col-md-10 text-center" id="dP">
								
									<label for="txtPassword"> Introduce la nueva contraseña: </label>
									<input id="txtPassword" name="txtPassword" type="password" placeholder = "Nueva Contraseña" class="form-control col-md-8" required>
								
							</div>
							</p>
						</div>						
						
						<div class="row">
							<div class="col-md-12" >
								<br/>
								<div class="row">
									<div class="col-md-10 text-center" id="dPR">
										<label for="txtPasswordR"> Repite la contraseña: </label>
										<input id="txtPasswordR" name="txtPasswordR" type="password" placeholder = "Nueva Contraseña" class="form-control" required>
									</div>
									<div class="col-md-2" id="dImg">
										<img src="img/cancel.png" id="imgMal">
									</div>
								</div>
								<div class="row">
									<div class="col-md-10">
										<label id="lblLogin" for="txtPasswordR" class="label label-info">Las contraseñas no coinciden</label>
									</div>
								</div>
							</div>
						</div>
						
						<div class="row">
							<div class="col-md-12">
								<br/>
								<input type="button" id="btnComparar" class="btn btn-primary col-md-4" value="Enviar">
							</div>
						</div>
					</form>
				</div>
				<div class="col-sm-4 col-md-4">
				</div>
			</div>
		</div>
				
	</div>
</div>

<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/scriptSonIguales.js"></script>
<script src="js/jquery.sweet-modal.min.js"></script>

</body>
</html>