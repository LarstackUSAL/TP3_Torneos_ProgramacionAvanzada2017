package ar.edu.usal.torneos.model.dto;

public class Empleados {

	private String nombre;
	private String apellido;
	private int cuil;
	
	public Empleados(String nombre, String apellido, int cuil) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.cuil = cuil;
	}
	
	public Empleados(){}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getCuil() {
		return cuil;
	}

	public void setCuil(int cuil) {
		this.cuil = cuil;
	}
}
