package com.patients.model;

public class consulta 
{
	private int idConsulta;
	private int idPaciente;
	private String fecha;
	private String diagnostico;
	private String peso;
	private int edad;
	private Receta miReceta;
	
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
	
	public String getFecha()
	{
		return fecha;
	}
	public void setFecha(String fecha)
	{
		this.fecha=fecha;
	}
	
	public String getDiagnostico()
	{
		return diagnostico;
	}
	public void setDiagnostico(String diagnostico)
	{
		this.diagnostico=diagnostico;
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
	
	public Receta getMiReceta()
	{
		return miReceta;
	}
	public void setReceta(Receta miReceta)
	{
		this.miReceta=miReceta;
	}
	
	public String toCard()
	{
		return "<div class='row'>"
				+"<div class=\"card cuerpoCard col-lg-12 mb-4\">"
				+ "<header>"
				+ 	"<h1 class='tituloCard'>Fecha "+getFecha()+"</h1>"
				+ "</header>"
				
				+ "<div class='row'>" + 
					"<div class='col-lg-6'>" + 
						"<label for='txtEdad'>Edad</label>" + 
						"<input id='txtEdad' name='txtEdad' type='text' disabled value="+getEdad()+ " class='form-control'>" + 
					"</div>" + 
						
					"<div class='col-lg-6'>" + 
						"<label for='txtPeso'>Peso</label>" + 
						"<input id='txtPeso' name='txtPeso' type='text' disabled value="+getPeso()+" class='form-control'>" + 
					"</div>" + 
				"</div>"
				+ "<div class='row mx-1'>"
					+"<div class='col-lg-12'>"
						+"<h1>Diagnostico</h1>"
						+ "<textarea disabled class='form-control'>"+getDiagnostico()+"</textarea>"
					+ "</div>"
				+ "</div>"
				+ "<div class='row'>"
					+ "<div class='col-lg-12'>"
						+"<h1>Receta</h1>"
						+ "<textarea disabled class='form-control mb-5'>"+getMiReceta().getDescripcion()+"</textarea>"
					+ "</div>"
					+ "<div class='row'></div>"
				+ "</div>"
				+ "</div></div><div class='row separado'></div>" 
				;
	}
}
