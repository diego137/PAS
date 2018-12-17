package com.employees.controller;

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

import com.employees.model.Employees;

/**
 * Servlet implementation class LogIn
 */
@WebServlet("/LogIn")
@MultipartConfig
public class LogIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogIn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html charset='UTF-8'");
		PrintWriter salida = response.getWriter();
		
		Employees emp = new Employees();
		
		emp.setPassword(request.getParameter("txtPassword"));
		emp.setUser(request.getParameter("txtUser"));
		System.out.println("Pass servlet: "+request.getParameter("txtPassword"));
		System.out.println("User servlet: "+request.getParameter("txtUser"));
		System.out.println("Aqui es el servlet: "+emp.toString());
		
		Properties props = new Properties();
		InputStream input = null;
		
		input = getClass().getClassLoader().getResourceAsStream("config.properties");
		props.load(input);
		
		String propUrl = props.getProperty("miUrl");
		String propUser = props.getProperty("user");
		String propPass = props.getProperty("password");
		String propDriver = props.getProperty("Driver");
		
		input = getClass().getClassLoader().getResourceAsStream("sql.properties");
		props.load(input);
		
		String propSql = props.getProperty("hacerLogin");
		
		Connection conn = null;
		PreparedStatement pstmnt = null;
		ResultSet datos = null;
		
		try {
			Class.forName(propDriver).getDeclaredConstructor().newInstance();
			conn = DriverManager.getConnection(propUrl, propUser, propPass);
			pstmnt = conn.prepareStatement(propSql);
			pstmnt.setString(1,emp.getUser());
			pstmnt.setString(2, emp.getPassword());
			datos = pstmnt.executeQuery();
			boolean existLog = false;
			while(datos.next()) {
				existLog = true;
				emp.setIdEmployee(datos.getInt("idEmpleados"));
				emp.setName(datos.getString("Nombre"));
				emp.setLastName(datos.getString("Apellidos"));
				emp.setRol(datos.getString("Rol"));
				emp.setCurp(datos.getString("Curp"));
				emp.setUser(datos.getString("usuarioLog"));
				emp.setPassword(datos.getString("Contrasena"));
				emp.setFirstTime(datos.getBoolean("FirstTime"));
			}
			System.out.println(emp.toString());
			if(existLog) {
				if(emp.isFirstTime()) {
					//salida.println("Es primera vez");
					//request.setAttribute("miEmpleado", emp);
					//request.getRequestDispatcher("WEB-INF/cambiarPass.jsp").forward(request, response);
					salida.println(emp.getIdEmployee()+"@"+emp.getCurp()+"/"+emp.getRol());
					System.out.println("Salida cadena: "+emp.getIdEmployee()+"@"+emp.getCurp()+"/"+emp.getRol());
				}else {
					salida.println(emp.getRol());
				}
			}else {
				salida.println("error");
				//request.setAttribute("exist", "0");
				//request.getRequestDispatcher("index.jsp").forward(request, response);
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
