package com.employees.model;
public class Employees {
	
	private int idEmployee;
	private String name;
	private String lastName;
	private String curp;
	private String user;
	private String password;
	private String rol;
	private boolean firstTime;
	
	public Employees ()
	{
		
	}
	
	public Employees (int idEmployee ,String name,String lastName,String curp, String user,String password,String rol,boolean firstTime)
	{
		setIdEmployee(idEmployee);
		setName(name);
		setLastName(lastName);
		setCurp(curp);
		setUser(user);
		setPassword(password);
		setRol(rol);
		setFirstTime(firstTime);
	}

	public int getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(int idEmployee) {
		this.idEmployee = idEmployee;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getCurp()
	{
		return curp;
	}
	public void setCurp(String curp)
	{
		this.curp=curp;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public boolean isFirstTime() {
		return firstTime;
	}

	public void setFirstTime(boolean firstTime) {
		this.firstTime = firstTime;
	}
	
	
	public String toString()
	{
		return "ID Employee: "+getIdEmployee()+ "Name: "+getName()+ "Last Name: "+getLastName()+ "Curp: "+getCurp()+ "Usuario: "+getUser()+"Contraseña: "+getPassword()+"Rol: "+getRol();
	}
	
	

}
