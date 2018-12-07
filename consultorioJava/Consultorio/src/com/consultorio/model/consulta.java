package com.consultorio.model;

public class consulta 
{
	private int idConsulta;
	private int idPaciente;
	private int idReceta;
	private String fecha;
	private String peso;
	private int edad;
	
	public consulta()
	{
		
	}
	
	
	public int getIdConsulta()
	{
		return idConsulta;
	}
	public void setIdConsulta(int idConsulta)
	{
		this.idConsulta=idConsulta;
	}
	
	public int getIdPaciente()
	{
		return idPaciente;
	}
	public void setIdPaciente(int idPaciente)
	{
		this.idPaciente=idPaciente;
	}
	
	public int getIdReceta()
	{
		return idReceta;
	}
	public void setIdReceta(int idReceta)
	{
		this.idReceta=idReceta;
	}
	
	public String getFecha()
	{
		return fecha;
	}
	public void setFecha(String fecha)
	{
		this.fecha=fecha;
	}
	
	public String getPeso()
	{
		return peso;
	}
	public void setPeso(String peso)
	{
		this.peso=peso;
	}
	
	public int getEdad()
	{
		return edad;
	}
	public void setEdad(int edad)
	{
		this.edad=edad;
	}
}
