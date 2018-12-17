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
<script src="js/UserManagement.js"></script>

</head>
<body>
<section id="banner" class="banner">
    <div class="container" >
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class=" col-sm-4 col-md-4 col-lg-6">
						<h2>See <b>Patients</b></h2>
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
				                <a href="" class="add"><i class="material-icons" data-toggle="tooltip" title="Add">add_circle</i></a>
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
</body>
</html>                                		                            