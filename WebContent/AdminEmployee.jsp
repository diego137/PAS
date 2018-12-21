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
<title>Administrador Employee</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="css/jquery.sweet-modal.min.css">
<link rel="stylesheet" href="css/UserManagement.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="js/jquery.sweet-modal.min.js"></script>
<script src="js/sweetalert2.all.min.js"></script>
<script src="js/UserManagement.js"></script>

</head>
<body>
<section id="banner" class="banner">
    <div class="container" >
    	<div class="row">
    		<div class="col-sm-8 col-md-8 col-lg-8"></div>
    		<div class="col-sm-2 col-md-2 col-lg-2">
    			<input id="btnRegresar" type="button" class="btn btn-warning btn-block" value="Regresar"/>
    		</div>
    		<div class="col-sm-2 col-md-2 col-lg-2">
    			<input id="btnCerrar" type="button" class="btn btn-danger btn-block" value="Cerrar Session"/>
    		</div>    		
    	</div>
    	
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class=" col-sm-4 col-md-4 col-lg-6">
						<h2>Manage <b>Employees</b></h2>
					</div>
					
					<form action="SearchEmployeeServlet" method="POST">
					<div class="col-sm-4 col-md-4 col-lg-3  ">
					    <!-- <input id="txtBusqueda" type="number" class="form-control" placeholder="Search by Id" name="txtBusqueda" required>
					    <input type="button" id="btnBuscarTodos"  class="btn btn-info" value="Search All">
					    <input type="submit" id="btnBuscar" name="btnBuscar" class="btn btn-info" value="Search">-->
					    <div class="row">
					    	<div class="col-sm-12 col-md-12">
					    		<input id="txtBusqueda" type="number" class="form-control" placeholder="Search by Id" name="txtBusqueda" required>
					    	</div>
					    </div>
					    <div class="row">
					    	<div class="col-sm-6 col-md-6">
					    		<input type="button" id="btnBuscarTodos"  class="btn btn-info btn-block" value="Search All">
					    	</div>
					    	<div class="col-sm-6 col-md-6">
					    		<input type="submit" id="btnBuscar" name="btnBuscar" class="btn btn-info btn-block" value="Search">
					    	</div>
					    </div>
					</div>
					</form>
					<div class="col-sm-4 col-md-4 col-lg-3 "  >
						 <div class="row">
							 <div class="col-sm-12 col-md-12">
							 	<a href="#addEmployeeModal" id="btnAddNew" class="btn btn-success btn-block " data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add New Employee</span></a>
							 </div>
						 </div>
						 <div class="row">
							 <div class="col-sm-12 col-md-12">
							 	<a href="#deleteEmployeeModal" id="btnDeleteNew"class="btn btn-danger btn-block" data-toggle="modal"><i class="material-icons">&#xE15C;</i> <span>Delete Employee</span></a>
							 </div>
						 </div>						
					</div>
					
                </div>
            </div>
            <table class="table table-striped table-hover">
                <thead>
                    <tr>
						<th>
							<span class="custom-checkbox">
								<input type="checkbox" id="selectAll">
								<label for="selectAll"></label>
							</span>
						</th>
						<th>Id Employee</th>
                        <th>Name</th>
                        <th>Last Name</th>
						<th>CURP</th>
                        <th>User</th>
                        <th>Password</th>
                        <th>Rol</th>
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
			if(request.getAttribute("txtId")==null)
			{
			String sentenciaSQL = "select * from empleados";
			datos = stmnt.executeQuery(sentenciaSQL);
				while(datos.next())
				{%><tbody>
                <tr>
				 <td>
				  <span class="custom-checkbox">
					<input type="checkbox" id="checkbox1" name="options" class="paraEliminar" value=<%=datos.getInt("idEmpleados") %>>
					<label for="checkbox1"></label>
				  </span>
				<td><%=datos.getInt("idEmpleados") %></td>
				<td><%=datos.getString("Nombre") %></td>
				<td><%=datos.getString("Apellidos") %></td>
				<td><%=datos.getString("Curp") %></td>
				<td><%=datos.getString("usuarioLog") %></td>
				<td><%=datos.getString("Contrasena") %></td>
				<td><%=datos.getString("Rol") %></td>		
				<td>
					<%int idUsuario = datos.getInt("idEmpleados");
					  String data = datos.getInt("idEmpleados")+"@"+
					  				datos.getString("Nombre")+"@"+
					  				datos.getString("Apellidos")+"@"+
					  				datos.getString("Curp")+"@"+
					  				datos.getString("Rol");
					  data = data.replace(" ","#");%>
					<!-- Lapiz -->
					
                   <a href="#editEmployeeModal" class="edit" data-toggle="modal" data-whatever=<%= data %>>
                   <i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i>
                   </a>
                   
                   <a onclick="return confirm('Are you sure?')" href=<%="/PAS/DeleteEmployeeServlet?idEmpleados="+idUsuario%>  class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
               </td>
                    </tr>
                </tbody>		
		<% }
		}else{
		String sentenciaSQL = "select * from empleados where idEmpleados="+request.getAttribute("txtId");
		datos = stmnt.executeQuery(sentenciaSQL);
		datos.next();
		{%><tbody>
        <tr>
		 <td>
		  <span class="custom-checkbox">
			<input type="checkbox" id="checkbox1" name="options" value=<%=request.getAttribute("txtId") %>>
			<label for="checkbox1"></label>
		  </span>
		<td><%=request.getAttribute("txtId")%></td>
		<td><%=request.getAttribute("txtNombre") %></td>
		<td><%=request.getAttribute("txtApellidos") %></td>
		<td><%=request.getAttribute("txtCurp") %></td>
		<td><%=request.getAttribute("txtUsuario") %></td>
		<td><%=request.getAttribute("txtContrasena") %></td>
		<td><%=request.getAttribute("txtRol") %></td>
		
		<%String data = datos.getInt("idEmpleados")+"@"+
  				datos.getString("Nombre")+"@"+
  				datos.getString("Apellidos")+"@"+
  				datos.getString("Curp")+"@"+
  				datos.getString("Rol");
  			data = data.replace(" ","#"); %>
		
		<td>
		<a href="#editEmployeeModal" class="edit" data-toggle="modal" data-whatever=<%=data%>
		                 	>
		                   <i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i>
		                   </a>           
           <a href=<%="/PAS/DeleteEmployeeServlet?idEmpleados="+ datos.getInt("idEmpleados")%> onclick="return confirm('Are you sure?')"class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
           
       </td>
            </tr>
        </tbody>		
<% }
}
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
						<h4 class="modal-title">Add Employee</h4>
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
						<div class="form-group">
						<select id="txtRol" name="txtRol" class="form-control">
						  <option value="DOCTOR">DOCTOR</option>
						  <option value="NURSE">NURSE</option>
						</select>
						</div>							
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
						<input type="button" class="btn btn-success" id="btnAdd" name="btnAdd" value="Add">
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- Edit Modal HTML ********************************************************************************-->
	<div id="editEmployeeModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="UpdateEmployeeServlet" id="frmUpdate" method="post">
					<div class="modal-header">						
						<h4 class="modal-title">Edit Employee</h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">					
						<div class="form-group">
							<label>ID Employee</label>
							<input type="text" id="txtIdM" name="txtIdM"  class="form-control" required readonly>
						</div>
						<div class="form-group">
							<label>Name</label>
							<input type="text" id="txtNombreM" name="txtNom" class="form-control" required>
						</div>
						<div class="form-group">
							<label>Last Name</label>
							<input type="text" id="txtApellidosM" name="txtApellidos" class="form-control" required>
						</div>
						<div class="form-group">
							<label>CURP</label>
							<input type="text" id="txtCurpM" name="txtCurp" class="form-control" required onkeyup="this.value = this.value.toUpperCase();">
						</div>
						<div class="form-group">
						<select id="txtRol" name="txtRol" class="form-control">
						  <option value="DOCTOR">DOCTOR</option>
						  <option value="NURSE">NURSE</option>
						</select>
						</div>							
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
						<input type="submit" class="btn btn-info" value="Save">
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- Delete Modal HTML -->
	<div id="deleteEmployeeModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form>
					<div class="modal-header">						
						<h4 class="modal-title">Delete Employee</h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">					
						<p>Are you sure you want to delete these Records?</p>
						<p class="text-warning"><small>This action cannot be undone.</small></p>
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
						<input id="btnDeleteRecords" type="button" class="btn btn-danger" value="Delete">
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>                                		                            