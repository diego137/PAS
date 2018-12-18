package com.patients.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.patients.model.Patients;

@WebServlet("/CreatePatientsServlet")
@MultipartConfig
public class CreatePatientsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html charset=\"UTF-8\"");
		PrintWriter salida = response.getWriter();
		int nRegistros=0;
	Patient miPaciente = new Patient();				
		if (request.getParameter("txtNombre").equals(""))
				response.getWriter().write("vacioName");
		
		else if (request.getParameter("txtApellidos").equals(""))
				response.getWriter().write("vacioApellidos");
		
		else if (request.getParameter("txtCurp").equals(""))
				response.getWriter().write("vacioCurp");
		
		else if (request.getParameter("txtUsuario").equals(""))
				response.getWriter().write("vacioUsuario");
		
		else if (request.getParameter("txtContrasena").equals(""))
				response.getWriter().write("vacioContrasena");
		
		else {
        miPaciente.setName(request.getParameter("txtNombre"));
		miPaciente.setLastName(request.getParameter("txtApellidos" ));
		miPaciente.setCurp(request.getParameter("txtCurp"));
		miPaciente.setUser(request.getParameter("txtUsuario"));
		miPaciente.setPassword(request.getParameter("txtContrasena"));
		
		
		String miUrl="jdbc:mysql://localhost:3306/Consultorio?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String miUsuario="root";
		String miPassword="root";	
		Connection conn = null;
		Statement stmnt = null;
		String setencialSQL="insert into Empleados (idEmpleados,Nombre,Apellidos,Curp,usuarioLog,Contrasena,Rol) values(default,'"+miEmpleado.getName()+"','"+miEmpleado.getLastName()+ "','"+miEmpleado.getCurp()+"','"+miEmpleado.getUser()+"','"+miEmpleado.getPassword()+"','"+miEmpleado.getRol()+"')";
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			conn = DriverManager.getConnection(miUrl, miUsuario, miPassword);
			stmnt = conn.createStatement();
			nRegistros = stmnt.executeUpdate(setencialSQL);
			if (nRegistros>0)
			{
				response.getWriter().write("succes");
			}		 
		}
		catch (Exception e)
		{ 
			response.getWriter().write("duplicate");
			e.printStackTrace();	
		}
		
		finally
		{
			try {
				stmnt.close();
				conn.close();
				salida.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
				
			}
			
		} 
		}	
	}

}