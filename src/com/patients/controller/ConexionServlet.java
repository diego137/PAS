package com.patients.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Properties;

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
		
		//Creacion del props
		Properties props=new Properties();
		String propNombreArchivo = "sql.properties";
		
		InputStream input=null;
		input=getClass().getClassLoader().getResourceAsStream(propNombreArchivo);
		props.load(input);
		
		//Obtencion de las sentencias del "sql.properties"
		String propUrl=props.getProperty("url");
		String propUsername=props.getProperty("username");
		String propPassword=props.getProperty("password");
		String propDriver=props.getProperty("driver");
		String propSentenciaSQL=props.getProperty("sentenciaSQL");
		
		
		//Creacion de objetos de la conexion a la base de datos
		Connection conn=null;
		PreparedStatement pstmnt=null;
		ResultSet datos=null;
		String cadena="nbwabjk";
		
		System.out.println(cadena);
		
		
		try
		{
			Class.forName(propDriver).getDeclaredConstructor().newInstance();
			conn=DriverManager.getConnection(propUrl, propUsername, propPassword);
			System.out.println("conexion!");
			pstmnt=conn.prepareStatement(propSentenciaSQL);
			datos=pstmnt.executeQuery();
			
			
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
				pstmnt.close();
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
