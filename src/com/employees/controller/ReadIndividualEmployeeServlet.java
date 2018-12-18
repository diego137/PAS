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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.employees.model.Employees;

/**
 * Servlet implementation class ReadIndividualEmployeeServlet
 */
@WebServlet("/ReadIndividualEmployeeServlet")
public class ReadIndividualEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadIndividualEmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html charset='UTF-8'");
		PrintWriter salida = response.getWriter();
		
		Employees miEmployee = new Employees();
		miEmployee.setIdEmployee(Integer.parseInt(request.getParameter("id")));
		
		InputStream input = null;
		Properties props = new Properties();
		
		input = getClass().getClassLoader().getResourceAsStream("config.properties");
		props.load(input);
		
		String url = props.getProperty("miUrl");
		String Driver = props.getProperty("Driver");
		String user = props.getProperty("user");
		String pass = props.getProperty("password");
		
		input = getClass().getClassLoader().getResourceAsStream("sql.properties");
		props.load(input);
		
		String sentenciaSQL = props.getProperty("ReadIndividualEmployee");
		
		Connection conn = null;
		PreparedStatement pstmnt = null;
		ResultSet datos = null;
		
		try {
			Class.forName(Driver).getDeclaredConstructor().newInstance();
			conn = DriverManager.getConnection(url, user, pass);
			pstmnt = conn.prepareStatement(sentenciaSQL);
			pstmnt.setInt(1, miEmployee.getIdEmployee());
			datos = pstmnt.executeQuery();
			while(datos.next()) {
				miEmployee.setName(datos.getString("Nombre"));
				miEmployee.setLastName(datos.getString("Apellidos"));
				miEmployee.setCurp(datos.getString("Curp"));
				miEmployee.setUser(datos.getString("usuarioLog"));
				miEmployee.setPassword(datos.getString("Contrasena"));
				miEmployee.setRol(datos.getString("Rol"));
				miEmployee.setFirstTime(datos.getBoolean("FirstTime"));
			}
			request.setAttribute("miEmployee", miEmployee);
			request.setAttribute("Rol", "Empleado");
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
