package com.patients.model;

public class Patient {
	private int idPaciente;
	private String nombrePaciente;
	private String apellidosPaciente;
	private String curpPaciente;
	private String usuarioPaciente;
	private String passwordPaciente;
	private Boolean isFirstTime;
	public final static String rol = "Paciente";
	
	public Patient(){
		
	}
 
	public Patient(int idPaciente, String nombrePaciente, String apellidosPaciente, String curpPaciente, String usuarioPaciente, String passwordPaciente) {
		setIdPaciente(idPaciente);
		setNombrePaciente(nombrePaciente);
		setApellidosPaciente(apellidosPaciente);
		setCurpPaciente(curpPaciente);
		setUsuarioPaciente(usuarioPaciente);
		setPasswordPaciente(passwordPaciente);
	}
	
	public int getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}
	public String getNombrePaciente() {
		return nombrePaciente;
	}
	public void setNombrePaciente(String nombrePaciente) {
		this.nombrePaciente = nombrePaciente;
	}
	public String getApellidosPaciente() {
		return apellidosPaciente;
	}
	public void setApellidosPaciente(String apellidosPaciente) {
		this.apellidosPaciente = apellidosPaciente;
	}
	public String getCurpPaciente() {
		return curpPaciente;
	}
	public void setCurpPaciente(String curpPaciente) {
		this.curpPaciente = curpPaciente;
	}
	public String getUsuarioPaciente() {
		return usuarioPaciente;
	}
	public void setUsuarioPaciente(String usuarioPaciente) {
		this.usuarioPaciente = usuarioPaciente;
	}
	public String getPasswordPaciente() {
		return passwordPaciente;
	}
	public void setPasswordPaciente(String passwordPaciente) {
		this.passwordPaciente = passwordPaciente;
	}

	public Boolean getIsFirstTime() {
		return isFirstTime;
	}

	public void setIsFirstTime(Boolean isFirstTime) {
		this.isFirstTime = isFirstTime;
	}	
	
	public String toString() {
		return "PACIENTE---Id: "+getIdPaciente()+
				" Nombre: "+getNombrePaciente()+
				" Apellidos: "+getApellidosPaciente()+
				" Curp: "+getCurpPaciente()+
				" User: "+getUsuarioPaciente()+
				" Password: "+getPasswordPaciente()+
				" First Time: "+getIsFirstTime()+
				" Rol: "+rol;
	}
}
