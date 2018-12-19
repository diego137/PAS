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
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.patients.model.Patient;

/**
 * Servlet implementation class LogInPatientServlet
 */
@WebServlet("/LogInPatientServlet")
@MultipartConfig 
public class LogInPatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogInPatientServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html charset='UTF-8'");
		PrintWriter salida = response.getWriter();
		Patient myPatient = new Patient();

		myPatient.setUsuarioPaciente(request.getParameter("txtUser"));
		myPatient.setPasswordPaciente(request.getParameter("txtPassword"));
		
		System.out.println(myPatient.toString());
		
		Properties props = new Properties();
		InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");
		props.load(input);
		
		String url = props.getProperty("miUrl");
		String driver = props.getProperty("Driver");
		String user = props.getProperty("user");
		String pass = props.getProperty("password");
		
		input = getClass().getClassLoader().getResourceAsStream("sql.properties");
		props.load(input);
		
		String sentenciaSQL = props.getProperty("loginPatients");
		
		Connection conn = null;
		PreparedStatement pstmnt = null;
		ResultSet datos = null;
		
		try {
			Class.forName(driver).getDeclaredConstructor().newInstance();
			conn = DriverManager.getConnection(url, user, pass);
			pstmnt = conn.prepareStatement(sentenciaSQL);
			pstmnt.setString(1, myPatient.getUsuarioPaciente());
			pstmnt.setString(2, myPatient.getPasswordPaciente());
			datos = pstmnt.executeQuery();
			boolean isExistLog = false;
			while(datos.next()) {
				isExistLog = true;
				myPatient.setIdPaciente(datos.getInt("idPaciente"));
				myPatient.setNombrePaciente(datos.getString("nombrePaciente"));
				myPatient.setApellidosPaciente(datos.getString("apellidosPaciente"));
				myPatient.setCurpPaciente(datos.getString("curpPaciente"));
				myPatient.setUsuarioPaciente(datos.getString("usuarioPaciente"));
				myPatient.setPasswordPaciente(datos.getString("contrasenaPaciente"));
				myPatient.setIsFirstTime(datos.getBoolean("FirstTime"));
			}
			
			if(isExistLog) {
				if(myPatient.getIsFirstTime()) {
					salida.println(myPatient.getIdPaciente()+"@"+myPatient.getCurpPaciente()+"@"+myPatient.rol);
					System.out.println(myPatient.getIdPaciente()+"@"+myPatient.getCurpPaciente()+"@"+myPatient.rol);
				}else {
					salida.println(myPatient.getIdPaciente()+"-");
				}
			}else {
				salida.println("Error");
				System.out.println("Aqui");
			}
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
