package com.consultorio.controller;

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

import com.consultorio.model.consulta;


@WebServlet("/ConexionServlet")
public class ConexionServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

    public ConexionServlet()
    {
    	super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html charset='UTF-8'");
		PrintWriter salida=response.getWriter();
		consulta consulta= new consulta();
		
		//datos de acceso
		String url="jdbc:mysql://localhost:3306/consultorio?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String username="root";
		String password="root";
		
		//Creacion de objetos de la conexion a la base de datos
		Connection conn=null;
		Statement stmnt=null;
		ResultSet datos=null;
		String sentenciaSQL="select * from pacientes as P, consultas as C, recetas as R where P.idPaciente = C.idPaciente and R.idReceta = C.idReceta";
		
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			conn=DriverManager.getConnection(url, username, password);
			System.out.println("conexion!");
			stmnt=conn.createStatement();
			datos=stmnt.executeQuery(sentenciaSQL);
			
			while(datos.next())
			{
				salida.println("<br>");
				salida.println("Id Consulta: "+datos.getInt("idConsulta"));
				salida.println("<br>");
				salida.println("Id Paciente: "+datos.getInt("idPaciente"));
				salida.println("<br>");
				salida.println("Id Receta: "+datos.getInt("idReceta"));
				salida.println("<br>");
				salida.println("Fecha: "+datos.getString("fecha"));
				salida.println("<br>");
				salida.println("Peso: "+datos.getString("peso"));
				salida.println("<br>");
				salida.println("Edad: "+datos.getInt("edad"));
				salida.println("<br>");
				salida.println("<br>");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				datos.close();
				stmnt.close();
				conn.close();
			}
			catch(Exception el)
			{
				el.printStackTrace();
			}
		}
		salida.close();
	}

}
