package ar.edu.usal.torneos.model.dto;

import java.util.ArrayList;

public class Equipos {

	private String nombre;
	private String cuil;
	private ArrayList<Empleados> empleados;
	private int puntos;
	
	public Equipos(String nombre, String cuil, ArrayList<Empleados> empleados, int puntos) {
		super();
		this.nombre = nombre;
		this.cuil = cuil;
		this.empleados = empleados;
		this.setPuntos(puntos);
	}
	
	public Equipos(){}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCuil() {
		return cuil;
	}

	public void setCuil(String cuil) {
		this.cuil = cuil;
	}

	public ArrayList<Empleados> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(ArrayList<Empleados> empleados) {
		this.empleados = empleados;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
}
