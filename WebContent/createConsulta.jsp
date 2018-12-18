<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Create Consulta</title>
</head>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/createConsulta.css">
<body>
	<div id="bg">
		<div id="bg-color">
			<div class="container">
				<div class="row">
					<div class="col-sm-3 col-lg-3"></div>
					<div class="col-sm-6 col-lg-6">
						<form method="POST" action="InsertRecetaServlet" class="form-group">
							
							<div id="txtIdDiv" class="row miDiv">
								<div class="col-sm-12 col-lg-12">
									<label for="txtId">Id: </label>
									<input id="txtId" name="txtId" type="text" class="form-control" readonly value=<%=request.getParameter("id") %>>
								</div>
							</div>
							
							<div class="row miDiv">
								<div class="col-sm-4 col-md-4"></div>
								
								<div class="col-sm-4 col-md-4">
									<img id="imgSecure" class="img-fluid mx-auto d-block" src="img/healt.png"/>
								</div>
								
								<div class="col-sm-4 col-md-4"></div>
							</div>
							
							<div class="row miDiv">
								<div class="col-sm-4 col-lg-4">
									<label for="txtFecha">Fecha Actual: </label>
									<input id="txtFecha" name="txtFecha" type="date" class="form-control" required/>
								</div>
								<div class="col-sm-4 col-lg-4">
									<label for="txtPeso">Peso: </label>
									<input id="txtPeso" name="txtPeso" type="text" class="form-control" required/>
								</div>
								<div class="col-sm-4 col-lg-4">
									<label for="txtEdad">Edad: </label>
									<input id="txtEdad" name="txtEdad" type="text" class="form-control" required/>
								</div>
							</div>
							
							<div class="row miDiv">
								<div class="col-sm-12 col-lg-12">
									<label for="txtDiagnostico">Diagnostico: </label>
									<!--<input id="txtDiagnostico" name="txtDiagnostico" type="text" class="form-control"/>-->
									<textarea id="txtDiagnostico" name="txtDiagnostico" class="form-control" required></textarea>
								</div>
							</div>
							
							<div class="row miDiv">
								<div class="col-sm-12 col-lg-12">
									<label for="txtDescripcion">Descripcion</label>
									<!--<input id="txtDescripcion" name="txtDescripcion" type="text" class="form-control"/>-->
									<textarea id="txtDescripcion" name="txtDescripcion" class="form-control" required></textarea>
								</div>
							</div>						
							
							<div class="row mt-5 pt-5 miDiv">
								<div class="col-sm-6 col-lg-6">
									<input type="submit" value="Agregar" class="btn btn-success  btn-block"/>
								</div>
								<div class="col-sm-6 col-lg-6">
									<input id="btnCancelar" type="button" value="Cancelar" class="btn btn-danger btn-block"/>
								</div>
							</div>
							
						</form>
						
						
					</div>
					<div class="col-sm-3 col-lg-3"></div>
				</div>
			</div>
		</div>
	</div>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/createConsulta.js"></script>
</body>
</html>