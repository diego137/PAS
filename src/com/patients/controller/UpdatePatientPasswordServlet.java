package com.patients.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
 * Servlet implementation class UpdatePatientPasswordServlet
 */
@MultipartConfig
@WebServlet("/UpdatePatientPasswordServlet")
public class UpdatePatientPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePatientPasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html, charset=UTF-8");
		PrintWriter salida = response.getWriter();
		
		Patient miPatient = new Patient();
		
		miPatient.setIdPaciente(Integer.parseInt(request.getParameter("txtId")));
		miPatient.setPasswordPaciente(request.getParameter("txtPassword"));
		
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
		
		String sentenciaSQL = props.getProperty("UpdatePasswordPatient");
		
		Connection conn = null;
		PreparedStatement pstmnt = null;
		int cambios = 0;
		
		try {
			Class.forName(driver).getDeclaredConstructor().newInstance();
			conn = DriverManager.getConnection(url, user, password);
			pstmnt = conn.prepareStatement(sentenciaSQL);
			pstmnt.setString(1, miPatient.getPasswordPaciente());
			pstmnt.setInt(2, miPatient.getIdPaciente());
			cambios = pstmnt.executeUpdate();
			
			if(cambios>0) {
				salida.println(miPatient.getIdPaciente());
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmnt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		salida.close();
	}

}
