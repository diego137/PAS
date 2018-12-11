package com.employees.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.employees.model.Employees;

@WebServlet("/UpdateEmployeeServlet")
public class UpdateEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateEmployeeServlet() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html charset=\"UTF-8\"");
		PrintWriter salida = response.getWriter();
		Employees miEmployee = new Employees();
		//obtenemos los datos
		miEmployee.setIdEmployee(Integer.parseInt(request.getParameter("txtId")));
		miEmployee.setName(request.getParameter("txtNom"));
		miEmployee.setLastName(request.getParameter("txtApellidos"));
		miEmployee.setRol(request.getParameter("txtRol"));
		miEmployee.setCurp(request.getParameter("txtCurp"));
		//salida.println("Datos:"+miEmployee.toString());
		//Generamos el props
		Properties props = new Properties();
		String propNombreArchivo = "sql.properties";
		InputStream input = null;
		input = getClass().getClassLoader().getResourceAsStream(propNombreArchivo);
		props.load(input);
		
		//props 
		String propiedadurlServidor = props.getProperty("url");
		String propiedadmiUsuario = props.getProperty("username");
		String propiedadmiPassword = props.getProperty("password");
		String propiedadmiDriver = props.getProperty("driver");
		String UpdateEmployee = props.getProperty("SQLUpdateEmployee");
		/*salida.println(" "+"Servidor"+propiedadurlServidor);
		salida.println(" "+"UserName"+propiedadmiUsuario);
		salida.println(" "+"Password"+propiedadmiPassword);
		salida.println(" "+"Driver"+propiedadmiDriver); */
		
		Connection conn = null;
		PreparedStatement pstmnt = null;
		int nRegistro=0;
		try {
			Class.forName(propiedadmiDriver).getDeclaredConstructor().newInstance();
			conn=DriverManager.getConnection(propiedadurlServidor,propiedadmiUsuario,propiedadmiPassword);
			pstmnt=conn.prepareStatement(UpdateEmployee);
			pstmnt.setString(1,miEmployee.getName());
			pstmnt.setString(2,miEmployee.getLastName());
			pstmnt.setString(3,miEmployee.getCurp());
			pstmnt.setString(4,miEmployee.getRol());
			pstmnt.setInt(5,miEmployee.getIdEmployee());
			nRegistro = pstmnt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				pstmnt.close();
				conn.close();
				
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		request.getRequestDispatcher("AdminEmployee.jsp").forward(request, response);
		salida.close();
		
	}
	
}
