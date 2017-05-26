package ar.edu.usal.torneos.model.dto;

import java.util.ArrayList;

public class Equipos {

	private String nombre;
	private String cuil;
	private ArrayList<Jugadores> jugadores;
	private int puntos;
	
	public Equipos(String nombre, String cuil, ArrayList<Jugadores> jugadores, int puntos) {
		super();
		this.nombre = nombre;
		this.cuil = cuil;
		this.jugadores = jugadores;
		this.setPuntos(puntos);
	}
	
	public Equipos(){}
	
	@Override
	public boolean equals(Object arg0) {
		
		boolean esIgual = false;
		
		if(arg0 instanceof Equipos){
			
			esIgual = this.nombre.equals(((Equipos) arg0).getNombre());	
		}
		
		return esIgual; 
	}

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

	public ArrayList<Jugadores> getJugadores() {
		return jugadores;
	}

	public void setJugadores(ArrayList<Jugadores> jugadores) {
		this.jugadores = jugadores;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
}
