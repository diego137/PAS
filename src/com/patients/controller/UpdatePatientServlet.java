package com.patients.controller;

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
import com.patients.model.Patient;

/**
 * Servlet implementation class UpdatePatientServlet
 */
@WebServlet("/UpdatePatientServlet")
public class UpdatePatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public UpdatePatientServlet() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html charset=\"UTF-8\"");
		PrintWriter salida = response.getWriter();
		Patient miPaciente = new Patient();
		//obtenemos los datos
		miPaciente.setIdPaciente(Integer.parseInt(request.getParameter("txtIdM")));
		miPaciente.setNombrePaciente(request.getParameter("txtNom"));
		miPaciente.setApellidosPaciente(request.getParameter("txtApellidos"));
		miPaciente.setCurpPaciente(request.getParameter("txtCurp"));

		//salida.println("Datos:"+miEmployee.toString());
		//Generamos el props
		Properties props = new Properties();
		String propNombreArchivo = "config.properties";
		InputStream input = null;
		input = getClass().getClassLoader().getResourceAsStream(propNombreArchivo);
		props.load(input);
		
		//props 
		String propiedadurlServidor = props.getProperty("miUrl");
		String propiedadmiUsuario = props.getProperty("user");
		String propiedadmiPassword = props.getProperty("password");
		String propiedadmiDriver = props.getProperty("Driver");
		
		propNombreArchivo = "sql.properties";
		input = getClass().getClassLoader().getResourceAsStream(propNombreArchivo);
		props.load(input);
		
		String UpdatePatient = props.getProperty("SQLUpdatePatient");
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
			pstmnt=conn.prepareStatement(UpdatePatient);
			pstmnt.setString(1,miPaciente.getNombrePaciente());
			pstmnt.setString(2,miPaciente.getApellidosPaciente());
			pstmnt.setString(3,miPaciente.getCurpPaciente());
			pstmnt.setInt(4,miPaciente.getIdPaciente());
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
		request.getRequestDispatcher("AdminPatient.jsp").forward(request, response);
		salida.close();
		
	}    

}
