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

import com.employees.model.Employees;

/**
 * Servlet implementation class UpdateEmployeePasswordServlet
 */
@MultipartConfig
@WebServlet("/UpdateEmployeePasswordServlet")
public class UpdateEmployeePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateEmployeePasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html, charset='UTF-8'");
		PrintWriter salida = response.getWriter();
		salida.println("Estas en el Servlet Update Password");
		salida.println("Id: "+request.getParameter("id"));
		salida.println("Password: "+request.getParameter("pass"));
		salida.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html, charset='UTF-8'");
		PrintWriter salida = response.getWriter();
		//salida.println("Estas en el Servlet Update Password");
		//salida.println("Id: "+request.getParameter("txtId"));
		//salida.println("Password: "+request.getParameter("txtPassword"));
		
		Employees miEmp = new Employees();
		
		miEmp.setIdEmployee(Integer.parseInt(request.getParameter("txtId")));
		miEmp.setPassword(request.getParameter("txtPassword"));
		miEmp.setRol(request.getParameter("txtRol"));
		
		Properties props = new Properties();
		InputStream input = null;
		
		input = getClass().getClassLoader().getResourceAsStream("config.properties");
		props.load(input);
		
		String url = props.getProperty("miUrl");
		String driver = props.getProperty("Driver");
		String password = props.getProperty("password");
		String user = props.getProperty("user");
		
		input = getClass().getClassLoader().getResourceAsStream("sql.properties");
		props.load(input);
		
		String sentenciaSQL = props.getProperty("UpdatePasswordEmployee");
		
		Connection conn = null;
		PreparedStatement pstmnt = null;
		int cambios = 0;
		
		try {
			Class.forName(driver).getDeclaredConstructor().newInstance();
			conn = DriverManager.getConnection(url, user, password);
			pstmnt = conn.prepareStatement(sentenciaSQL);
			pstmnt.setString(1,miEmp.getPassword());
			pstmnt.setInt(2, miEmp.getIdEmployee());
			cambios = pstmnt.executeUpdate();
			if(cambios>0) {
				salida.println(miEmp.getRol());
			}else {
				salida.println("No hay cambios");
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
		
		System.out.println("Aqui");
		salida.close();
	}

}
