package com.employees.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/SearchEmployeeServlet")
@MultipartConfig
public class SearchEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    //DOPOST   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html charset=\"UTF-8\"");
		PrintWriter out = response.getWriter();
		
		if(request.getParameter("txtBusqueda").equals(""))
		{
			out.println("<script src=\"js/sweetalert2.all.min.js\"></script>");
			out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js'></script>");
			out.println("<script>");
			out.println("$(document).ready(function(){");
			out.println("Swal({ title: 'Can not let input in lank', confirmButtonText: 'Ok', onAfterClose: () => { setTimeout(() => location.href = 'AdminEmployee.jsp', 100);}});});");
		   out.println("</script>");}
		int idEmpleado = Integer.parseInt(request.getParameter("txtBusqueda"));
		if(idEmpleado<0)
			{
			out.println("<script src=\"js/sweetalert2.all.min.js\"></script>");
			out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js'></script>");
			out.println("<script>");
			out.println("$(document).ready(function(){");
			out.println("Swal({ type:'error',title: 'Please type',text: 'onlye positive numbers', onAfterClose: () => { setTimeout(() => location.href = 'AdminEmployee.jsp', 100);}});});");
		   out.println("</script>");}
		
	else {
		String miDireccionServidor="jdbc:mysql://localhost:3306/Consultorio?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String miUsuario="root";
		String miPassword="root";
		String sentenciaSQL = "select * from empleados where idEmpleados="+idEmpleado ;
		ResultSet datos = null;
		Connection conn=null;
		Statement stmnt = null;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			conn = DriverManager.getConnection(miDireccionServidor, miUsuario, miPassword);
			stmnt = conn.createStatement();
			datos = stmnt.executeQuery(sentenciaSQL);
			datos.next();
			int idEmpleados = datos.getInt("idEmpleados");
			String nombre = datos.getString("Nombre");
			String apellidos = datos.getString("Apellidos");
			String curp = datos.getString("Curp");
			String usuario = datos.getString("usuarioLog");
			String contrasena = datos.getString("Contrasena");
			String rol = datos.getString("Rol");
			request.setAttribute("txtId",idEmpleados);
			request.setAttribute("txtNombre",nombre);
			request.setAttribute("txtApellidos",apellidos);
			request.setAttribute("txtCurp",curp);
			request.setAttribute("txtUsuario",usuario);
			request.setAttribute("txtContrasena",contrasena);
			request.setAttribute("txtRol",rol);
			RequestDispatcher rd = request.getRequestDispatcher("AdminEmployee.jsp");
			rd.forward(request, response);
		}	 
	catch(Exception miExcepcioncita)
		{
		out.println("<script src=\"js/sweetalert2.all.min.js\"></script>");
		out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js'></script>");
		out.println("<script>");
		out.println("$(document).ready(function(){");
		out.println("Swal({ type:'error',title: 'Oops...',text: 'ID not found!', onAfterClose: () => { setTimeout(() => location.href = 'AdminEmployee.jsp', 100);}});});");
	   out.println("</script>");
			miExcepcioncita.printStackTrace();
		} 
		 finally 
		{
			try
			{	datos.close();
				stmnt.close();
				conn.close();
			}
			catch (SQLException miExcepcioncita2)
			{
				miExcepcioncita2.printStackTrace();
			}			
		}  	 }
	}

}
