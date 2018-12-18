package com.consultas.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.consultas.model.Consulta;

/**
 * Servlet implementation class InsertConsultaServlet
 */
@WebServlet("/InsertConsultaServlet")
public class InsertConsultaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertConsultaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html, charset='UTF-8'");
		PrintWriter salida = response.getWriter();
		
		Consulta miConsulta = (Consulta)request.getAttribute("miConsulta");
		
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
		
		String sentenciaSQL = props.getProperty("InsertIntoConsultas");
		
		Connection conn = null;
		PreparedStatement pstmnt = null;
		int inserciones = 0;
		
		try {
			Class.forName(driver).getDeclaredConstructor().newInstance();
			conn = DriverManager.getConnection(url, user, password);
			pstmnt = conn.prepareStatement(sentenciaSQL);
			pstmnt.setInt(1, miConsulta.getIdPaciente());
			pstmnt.setInt(2, miConsulta.getMiReceta().getIdReceta());
			pstmnt.setString(3, miConsulta.getFecha());
			pstmnt.setString(4, miConsulta.getDiagnostico());
			pstmnt.setString(5, miConsulta.getPeso());
			pstmnt.setInt(6, miConsulta.getEdad());
			inserciones = pstmnt.executeUpdate();
			salida.println("Se insertaron: "+inserciones);
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
		request.getRequestDispatcher("Doctores.jsp").forward(request, response);
		salida.close();
	}

}
