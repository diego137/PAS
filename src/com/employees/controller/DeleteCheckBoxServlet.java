package com.employees.controller;

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

/**
 * Servlet implementation class DeleteCheckBoxServlet
 */
@MultipartConfig
@WebServlet("/DeleteCheckBoxServlet")
public class DeleteCheckBoxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCheckBoxServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html, charset=UTF-8");
		PrintWriter salida = response.getWriter();
		String cadena = request.getParameter("cajas");
		
		String array[] = cadena.split(",");
		
		Properties props = new Properties();
		InputStream input = null;
		
		input = getClass().getClassLoader().getResourceAsStream("config.properties");
		props.load(input);
		
		String url = props.getProperty("miUrl");
		String user = props.getProperty("user");
		String password = props.getProperty("password");
		String driver = props.getProperty("Driver");
		
		input = getClass().getClassLoader().getResourceAsStream("sql.properties");
		props.load(input);
		
		String sentenciaSQL = props.getProperty("deleteByIdEmployee");
		
		Connection conn = null;
		PreparedStatement pstmnt = null;
		int cantEliminados = 0;
		
		for(int i = 0; i < array.length; i++) {
			try {
				Class.forName(driver).getDeclaredConstructor().newInstance();
				conn = DriverManager.getConnection(url, user, password);
				pstmnt = conn.prepareStatement(sentenciaSQL);
				pstmnt.setInt(1, Integer.parseInt(array[i]));
				pstmnt.executeUpdate();
				cantEliminados++;
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
		}
		
		salida.println(cantEliminados);
		salida.close();
	}

}
