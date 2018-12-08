package com.patients.model;

public class Receta 
{
	private int idReceta;
	private String descripcion;
	
	public Receta()
	{
		
	}
	
	public Receta(int idReceta, String descripcion)
	{
		setIdReceta(idReceta);
		setDescripcion(descripcion);
	}
	
	public int getIdReceta()
	{
		return idReceta;
	}
	public void setIdReceta(int idReceta)
	{
		this.idReceta=idReceta;
	}
	
	public String getDescripcion()
	{
		return descripcion;
	}
	public void setDescripcion(String descripcion)
	{
		this.descripcion=descripcion;
	}
	
	public String toStringReceta()
	{
		return "IdReceta: "+getIdReceta()+ " Descripcion: "+getDescripcion();
	}
}
