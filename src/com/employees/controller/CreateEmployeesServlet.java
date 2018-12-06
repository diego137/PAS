package com.employees.controller;

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


import com.employees.model.Employees;


@WebServlet("/CreateEmployeesServlet")
@MultipartConfig
public class CreateEmployeesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html charset=\"UTF-8\"");
		PrintWriter salida = response.getWriter();
		int nRegistros=0;
		//obtenemos valores de los textboxes
	Employees miEmpleado = new Employees();
	
	int a = Integer.parseInt(request.getParameter("txtId"));
			if(a<0)
			response.getWriter().write("negativeNumber");
		
		else if (request.getParameter("txtId").equals(""))
			response.getWriter().write("vacioId");
					
		else if (request.getParameter("txtNombre").equals(""))
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
		miEmpleado.setIdEmployee(Integer.parseInt(request.getParameter("txtId")));
		miEmpleado.setName(request.getParameter("txtNombre"));
		miEmpleado.setLastName(request.getParameter("txtApellidos" ));
		miEmpleado.setCurp(request.getParameter("txtCurp"));
		miEmpleado.setUser(request.getParameter("txtUsuario"));
		miEmpleado.setPassword(request.getParameter("txtContrasena"));
		miEmpleado.setRol(request.getParameter("txtRol")); 
		//salida.println(miEmpleado.toString());
		//salida.println("estas en el post"); 
		
		String miUrl="jdbc:mysql://localhost:3306/Consultorio?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String miUsuario="root";
		String miPassword="root";	
		
		Connection conn = null;
		Statement stmnt = null;
		
		String setencialSQL="insert into Empleados (idEmpleados,Nombre,Apellidos,Curp,usuarioLog,Contrasena,Rol) values("+miEmpleado.getIdEmployee()+",'"+miEmpleado.getName()+"','"+miEmpleado.getLastName()+ "','"+miEmpleado.getCurp()+"','"+miEmpleado.getUser()+"','"+miEmpleado.getPassword()+"','"+miEmpleado.getRol()+"')";
		
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
