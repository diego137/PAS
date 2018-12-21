package com.patients.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/SearchPatientsServlet")
public class SearchPatientsServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html charset=\"UTF-8\"");
		PrintWriter out = response.getWriter();
		
		if(request.getParameter("txtBusqueda").equals(""))
		{
			out.println("<script src=\"js/sweetalert2.all.min.js\"></script>");
			out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js'></script>");
			out.println("<script>");
			out.println("$(document).ready(function(){");
			out.println("Swal({ title: 'Can not let input in lank', confirmButtonText: 'Ok', onAfterClose: () => { setTimeout(() => location.href = 'AdminPatient.jsp.jsp', 100);}});});");
		   out.println("</script>");}
		int idPaciente = Integer.parseInt(request.getParameter("txtBusqueda"));
		if(idPaciente<0)
			{
			out.println("<script src=\"js/sweetalert2.all.min.js\"></script>");
			out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js'></script>");
			out.println("<script>");
			out.println("$(document).ready(function(){");
			out.println("Swal({ type:'error',title: 'Please type',text: 'onlye positive numbers', onAfterClose: () => { setTimeout(() => location.href = 'AdminPatient.jsp', 100);}});});");
		   out.println("</script>");}
		
	else {
		String miDireccionServidor="jdbc:mysql://localhost:3306/Consultorio?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String miUsuario="root";
		String miPassword="root";
		String sentenciaSQL = "select * from Pacientes where idPaciente="+idPaciente;
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
			int idPacientes = datos.getInt("idPaciente");
			String nombreP = datos.getString("nombrePaciente");
			String apellidosP = datos.getString("apellidosPaciente");
			String curpP = datos.getString("curpPaciente");
			String usuarioP = datos.getString("usuarioPaciente");
			String contrasenaP = datos.getString("contrasenaPaciente");
			
			request.setAttribute("txtId",idPacientes);
			request.setAttribute("txtNombre",nombreP);
			request.setAttribute("txtApellidos",apellidosP);
			request.setAttribute("txtCurp",curpP);
			request.setAttribute("txtUsuario",usuarioP);
			request.setAttribute("txtContrasena",contrasenaP);
			
			RequestDispatcher rd = request.getRequestDispatcher("AdminPatient.jsp");
			rd.forward(request, response);
		}	 
	catch(Exception miExcepcioncita)
		{
		out.println("<script src=\"js/sweetalert2.all.min.js\"></script>");
		out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js'></script>");
		out.println("<script>");
		out.println("$(document).ready(function(){");
		out.println("Swal({ type:'error',title: 'Oops...',text: 'ID not found!', onAfterClose: () => { setTimeout(() => location.href = 'AdminPatient.jsp', 100);}});});");
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