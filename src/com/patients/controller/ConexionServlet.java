package com.patients.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.patients.model.Receta;
import com.patients.model.consulta;


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
		Receta receta=new Receta();
		LinkedList<consulta> listaConsultas = new LinkedList<consulta>();
		
		//datos de acceso
		String url="jdbc:mysql://localhost:3306/consultorio?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String username="root";
		String password="root";
		
		//Creacion de objetos de la conexion a la base de datos
		Connection conn=null;
		Statement stmnt=null;
		ResultSet datos=null;
		String cadena="nbwabjk";
		String sentenciaSQL="select * from pacientes as P, consultas as C, recetas as R where P.idPaciente = C.idPaciente and R.idReceta = C.idReceta";
		
		System.out.println(cadena);
		
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			conn=DriverManager.getConnection(url, username, password);
			System.out.println("conexion!");
			stmnt=conn.createStatement();
			datos=stmnt.executeQuery(sentenciaSQL);
			
			
			while(datos.next())
			{
				/*salida.println("<br>");
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
				salida.println("<br>");*/
				consulta.setIdConsulta(datos.getInt("idConsulta"));
				consulta.setIdPaciente(datos.getInt("idPaciente"));
				consulta.setFecha(datos.getString("fecha"));
				consulta.setPeso(datos.getString("peso"));
				consulta.setEdad(datos.getInt("edad"));
				consulta.setDiagnostico(datos.getString("diagnostico"));
				receta.setIdReceta(datos.getInt("idReceta"));
				receta.setDescripcion(datos.getString("descripcion"));
				consulta.setReceta(receta);
				cadena+=consulta.toCard();
				//listaConsultas.add(consulta);
			}
			
			
			request.setAttribute("card", cadena);
			RequestDispatcher rd=request.getRequestDispatcher("consulta.jsp");  
		    rd.forward(request, response); 
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
