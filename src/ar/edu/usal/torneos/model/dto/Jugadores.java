package ar.edu.usal.torneos.model.dto;

public class Jugadores {

	private String nombre;
	private String apellido;
	private int cuil;
	
	public Jugadores(String nombre, String apellido, int cuil) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.cuil = cuil;
	}
	
	public Jugadores(){}

	@Override
	public boolean equals(Object arg0) {
	
		boolean esIgual = false;
		
		if(arg0 instanceof Jugadores){
			
			esIgual = ((Jugadores) arg0).getCuil() == this.cuil;
		}
		
		return esIgual;
	}
	
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
