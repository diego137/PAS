package com.patients.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.patients.model.Patient;

/**
 * Servlet implementation class ReadIndividualPatientServlet
 */
@WebServlet("/ReadIndividualPatientServlet")
public class ReadIndividualPatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadIndividualPatientServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html charset='UTF-8'");
		PrintWriter salida = response.getWriter();
		
		Patient miPatient = new Patient();
		
		miPatient.setIdPaciente(Integer.parseInt(request.getParameter("id")));
		
		Properties props = new Properties();
		InputStream input = null;
		
		input = getClass().getClassLoader().getResourceAsStream("config.properties");
		props.load(input);
		
		String url = props.getProperty("miUrl");
		String driver = props.getProperty("Driver");
		String user = props.getProperty("user");
		String password = props.getProperty("password");
		
		input = getClass().getClassLoader().getResourceAsStream("sql.properties");
		props.load(input);
		
		String sentenciaSQL = props.getProperty("ReadIndividualPatient");
		
		Connection conn = null;
		PreparedStatement pstmnt = null;
		ResultSet datos = null;
		
		try {
			Class.forName(driver).getDeclaredConstructor().newInstance();
			conn = DriverManager.getConnection(url, user, password);
			pstmnt = conn.prepareStatement(sentenciaSQL);
			pstmnt.setInt(1, miPatient.getIdPaciente());
			datos = pstmnt.executeQuery();
			while(datos.next()) {
				miPatient.setNombrePaciente(datos.getString("nombrePaciente"));
				miPatient.setApellidosPaciente(datos.getString("apellidosPaciente"));
				miPatient.setCurpPaciente(datos.getString("curpPaciente"));
				miPatient.setUsuarioPaciente(datos.getString("usuarioPaciente"));
				miPatient.setPasswordPaciente(datos.getString("contrasenaPaciente"));
				miPatient.setIsFirstTime(datos.getBoolean("FirstTime"));
			}
			request.setAttribute("miPatient", miPatient);
			request.setAttribute("Rol", "Patient");
			request.getRequestDispatcher("cambiarPass.jsp").forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				datos.close();
				pstmnt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		salida.close();
	}

}
