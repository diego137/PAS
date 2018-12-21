<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.ResultSet"  %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Doctores</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="css/jquery.sweet-modal.min.css">
<link rel="stylesheet" href="css/doctores.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="js/jquery.sweet-modal.min.js"></script>
<script src="js/sweetalert2.all.min.js"></script>
<script src="js/CreatePatientDoc.js"></script>

</head>
<body>
<section id="banner" class="banner">
    <div class="container" >
    
    	<div class="row">
    		<div class="col-sm-10 col-md-10 col-lg-10"></div>
    		<div class="col-sm-2 col-md-2 col-lg-2">
    			<input id="btnCerrar" type="button" class="btn btn-danger btn-block" value="Cerrar Session"/>
    		</div> 	
    	</div>
    <div class="col-sm-4 col-md-4 col-lg-3 "  >
						 					
					</div>
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class=" col-sm-3 col-md-3 col-lg-3">
						<h2>See <b>Patients</b></h2>
					</div>
						<div class="col-sm-9 col-md-9">
				<a href="#addEmployeeModal" id="btnAddNew" class="btn btn-success form-inline" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add New Patient</span></a>
			</div> 	
                </div>
            </div>
            <table class="table table-striped table-hover">
                <thead>
                    <tr>
                        <th>Name</th>
						<th>Last Name</th>
                        <th>CURP</th>
                        <th>Action</th>
                    </tr>
                </thead>
       <%--CONEXION A BASE DE DATOS CON SCRIPLET PARA MOSTRARLOS EN ESTE JSP --%>         
		<% 
		
		String miDireccionServidor="jdbc:mysql://localhost:3306/consultorio?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String miUsuario="root";
		String miPassword="root";
		ResultSet datos = null;
		Connection conn=null;
		Statement stmnt = null;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			conn = DriverManager.getConnection(miDireccionServidor, miUsuario, miPassword);
			stmnt = conn.createStatement();
			
			String sentenciaSQL = "select * from pacientes ";
			datos = stmnt.executeQuery(sentenciaSQL);
				while(datos.next())
				{%><tbody>
		                <tr>
							<td><%=datos.getString("nombrePaciente") %></td>
							<td><%=datos.getString("apellidosPaciente") %></td>
							<td><%=datos.getString("curpPaciente") %></td>
							
							<td>	
				                <a href=<%="ConexionServlet?id="+datos.getInt("idPaciente")%> class="show"><i class="material-icons" data-toggle="tooltip" title="Show">visibility</i></a>
				                <a href=<%="createConsulta.jsp?id="+datos.getInt("idPaciente") %> class="add"><i class="material-icons" data-toggle="tooltip" title="Add">add_circle</i></a>
			                </td>
		                </tr>
                	</tbody>		
				<%}
			}
	catch(Exception miExcepcioncita)
		{
			miExcepcioncita.printStackTrace();
		} 
		 finally 
		{
			try
			{	datos.close();
				stmnt.close();
				conn.close();
			}
			catch (Exception miExcepcioncita2)
			{
				miExcepcioncita2.printStackTrace();
		}			
		}  	%> 
		
		   
	<%--TERMINA CONECCION A BASE DE DATOS CON SCRIPLET PARA MOSTRARLOS EN ESTE JSP --%> 
            </table>
        </div>
    </div>
   </section>
   <!-- Add Modal HTML -->
	<div id="addEmployeeModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form>
					<div class="modal-header">						
						<h4 class="modal-title">Add Patient</h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">					
						<div class="form-group">
							<label>Name</label>
							<input type="text" id="txtNombre" name="txtNombre" class="form-control" required autofocus>
						</div>
						<div class="form-group">
							<label>Last Name</label>
							<input type="text" id="txtApellidos" name="txtApellidos" class="form-control" required>
						</div>
						<div class="form-group">
							<label>CURP</label>
							<input type="text" id="txtCurp" name="txtCurp" class="form-control" required onkeyup="this.value = this.value.toUpperCase();">
						</div>
						<div class="form-group">
							<label>User</label>
							<input type="text" id="txtUsuario" name="txtUsuario" class="form-control" required onkeyup="this.value = this.value.toUpperCase();">
						</div>	
						<div class="form-group">
							<label>Password</label>
							<input type="text" id="txtContrasena" name="txtContrasena" class="form-control" required onkeyup="this.value = this.value.toUpperCase();">
						</div>
												
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
						<input type="button" class="btn btn-success" id="btnAddp" name="btnAdd" value="Add">
					</div>
				</form>
			</div>
		</div>
	</div>
   
   <script src="js/pAdmin.js"></script>
</body>
</html>                                		                            