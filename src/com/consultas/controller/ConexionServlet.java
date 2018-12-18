package com.consultas.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.consultas.model.Receta;
import com.consultas.model.Consulta;


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
		Consulta consulta= new Consulta();
		Receta receta=new Receta();
		
		int idPaciente=Integer.parseInt(request.getParameter("id"));
		
		//Creacion del props
		Properties props=new Properties();
		String propNombreArchivo = "config.properties";
		
		InputStream input=null;
		input=getClass().getClassLoader().getResourceAsStream(propNombreArchivo);
		props.load(input);
		
		//Obtencion de las sentencias del "sql.properties"
		String propUrl=props.getProperty("miUrl");
		String propUsername=props.getProperty("user");
		String propPassword=props.getProperty("password");
		String propDriver=props.getProperty("Driver");
		
		propNombreArchivo = "sql.properties";
		input=getClass().getClassLoader().getResourceAsStream(propNombreArchivo);
		props.load(input);
		
		String propSentenciaSQL=props.getProperty("sentenciaSQL");
		
		
		//Creacion de objetos de la conexion a la base de datos
		Connection conn=null;
		PreparedStatement pstmnt=null;
		ResultSet datos=null;
		String cadena="";
		boolean isNull=true;
		System.out.println(cadena);
		
		
		try
		{
			Class.forName(propDriver).getDeclaredConstructor().newInstance();
			conn=DriverManager.getConnection(propUrl, propUsername, propPassword);
			System.out.println("conexion!");
			pstmnt=conn.prepareStatement(propSentenciaSQL);
			pstmnt.setInt(1, idPaciente);
			datos=pstmnt.executeQuery();
			
			
			while(datos.next())
			{
				isNull=false;
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

			}
			
			if(isNull)
			{
				cadena=null;
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
