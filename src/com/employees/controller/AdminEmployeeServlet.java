package com.employees.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AdminEmployeeServlet")
public class AdminEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminEmployeeServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter salida = response.getWriter();
		response.setContentType("text/html");
		//paso 1 Establecer las credenciales de acceso
		String miDireccionServidor="jdbc:mysql://localhost:3306/Consultorio?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String miUsuario="root";
		String miPassword="root";
		String sentenciaSQL = "select * from Empleados";
		salida.println("si estas en el servlet ");
		ResultSet datos = null;
		
		//paso2 crear los objetos de coneccion a la base de datos
		Connection conn=null;
		Statement stmnt = null;
	
		try
		{
		//paso3 Instanciamos el objeto de la clase connector
		// Class.forName("com.mysql.jdbc.Driver").newInstance(); antigua version de conector
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			conn = DriverManager.getConnection(miDireccionServidor, miUsuario, miPassword);
			System.out.println("conexion establecida");
			
		//paso 4 creamos el objeto statement, responsable de enviarle instrucciones al servidor de base de datos
			stmnt = conn.createStatement();
			datos = stmnt.executeQuery(sentenciaSQL);
			
			while(datos.next())
			{	
				salida.println("id Empleado:"+ datos.getInt("idEmpleados"));
				salida.println("<br/>");
				salida.println("Nombre Empleado:"+datos.getString("Nombre"));
				salida.println("<br/>");
				salida.println("Apellido Empleado:"+datos.getString("Apellidos"));
				salida.println("<br/>");
				salida.println("Curp:"+datos.getString("Curp"));
				salida.println("<br/>");
				salida.println("Usuario:"+datos.getString("usuariolog"));
				salida.println("<br/>");
				salida.println("Contraseña:"+datos.getString("Contrasena"));
				salida.println("<br/>");
				salida.println("Rol:"+datos.getString("Rol"));
				salida.println("<br/>");
				salida.println("First Time:"+datos.getString("FirsTime"));
				salida.println("<br/>");
				salida.println("<br/>"); 
				
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
			
		}
		
	salida.close();
		
	}

}
