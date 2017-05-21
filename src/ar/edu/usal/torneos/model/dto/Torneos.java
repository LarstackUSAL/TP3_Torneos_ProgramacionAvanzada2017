package ar.edu.usal.torneos.model.dto;

import java.util.ArrayList;
import java.util.Calendar;

import ar.edu.usal.torneos.model.interfaces.IPagoInscripcion;

public class Torneos implements IPagoInscripcion{

	private Calendar anio;
	private double costoInscripcion;
	private ArrayList<Equipos> equipos;
	private ArrayList<Partidos> partidos;
	
	public Torneos(Calendar anio, double costoInscripcion, 
			ArrayList<Equipos> equipos, ArrayList<Partidos> partidos) {
		super();
		this.anio = anio;
		this.equipos = equipos;
		this.partidos = partidos;
		this.setCostoInscripcion(costoInscripcion);
	}
	
	public Torneos(){}

	public Calendar getAnio() {
		return anio;
	}

	public void setAnio(Calendar anio) {
		this.anio = anio;
	}

	public ArrayList<Equipos> getEquipos() {
		return equipos;
	}

	public void setEquipos(ArrayList<Equipos> equipos) {
		this.equipos = equipos;
	}

	public ArrayList<Partidos> getPartidos() {
		return partidos;
	}

	public void setPartidos(ArrayList<Partidos> partidos) {
		this.partidos = partidos;
	}

	public double getCostoInscripcion() {		
		return costoInscripcion;
	}

	public void setCostoInscripcion(double costoInscripcion) {
		this.costoInscripcion = costoInscripcion;
	}
}
