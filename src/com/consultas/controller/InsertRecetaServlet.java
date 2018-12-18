package com.consultas.controller;

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

import com.consultas.model.Consulta;
import com.consultas.model.Receta;

/**
 * Servlet implementation class InsertRecetaServlet
 */
@WebServlet("/InsertRecetaServlet")
public class InsertRecetaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertRecetaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html, charset=UTF-8");
		PrintWriter salida = response.getWriter();
		
		Receta miReceta = new Receta();
		Consulta miConsulta = new Consulta();
		
		miConsulta.setIdPaciente(Integer.parseInt(request.getParameter("txtId")));
		miConsulta.setFecha(request.getParameter("txtFecha"));
		miConsulta.setEdad(Integer.parseInt(request.getParameter("txtEdad")));
		miConsulta.setPeso(request.getParameter("txtPeso"));
		miConsulta.setDiagnostico(request.getParameter("txtDiagnostico"));
		
		miReceta.setDescripcion(request.getParameter("txtDescripcion"));
		
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
		
		String sentenciaSQL = props.getProperty("InsertIntoRecetas");
		
		Connection conn = null;
		PreparedStatement pstmnt = null;
		ResultSet datos = null;
		
		try {
			Class.forName(driver).getDeclaredConstructor().newInstance();
			conn = DriverManager.getConnection(url, user, password);
			pstmnt = conn.prepareStatement(sentenciaSQL, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmnt.setString(1, miReceta.getDescripcion());
			pstmnt.executeUpdate();
			datos = pstmnt.getGeneratedKeys();
			
			if(datos.next()) {
				miReceta.setIdReceta(datos.getInt(1));
			}
			
			miConsulta.setReceta(miReceta);
			
			request.setAttribute("miConsulta",miConsulta);
			
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
		
		request.getRequestDispatcher("InsertConsultaServlet").forward(request, response);
		salida.close();
	}

}
