package ar.edu.usal.torneos.model.dto;

import java.util.Calendar;

public class Partidos {

	private Equipos equipoLocal;
	private Equipos equipovisitante;
	private int golesLocal;
	private int golesVisitante;
	private Calendar fechaPartido;
	
	public Partidos(Equipos equipoLocal, Equipos equipovisitante,
			int golesLocal, int golesVisitante, Calendar fechaPartido) {
		super();
		this.equipoLocal = equipoLocal;
		this.equipovisitante = equipovisitante;
		this.golesLocal = golesLocal;
		this.golesVisitante = golesVisitante;
		this.fechaPartido = fechaPartido;
	}
	
	public Partidos(){}

	public Equipos getEquipoLocal() {
		return equipoLocal;
	}

	public void setEquipoLocal(Equipos equipoLocal) {
		this.equipoLocal = equipoLocal;
	}

	public Equipos getEquipovisitante() {
		return equipovisitante;
	}

	public void setEquipovisitante(Equipos equipovisitante) {
		this.equipovisitante = equipovisitante;
	}

	public int getGolesLocal() {
		return golesLocal;
	}

	public void setGolesLocal(int golesLocal) {
		this.golesLocal = golesLocal;
	}

	public int getGolesVisitante() {
		return golesVisitante;
	}

	public void setGolesVisitante(int golesVisitante) {
		this.golesVisitante = golesVisitante;
	}

	public Calendar getFechaPartido() {
		return fechaPartido;
	}

	public void setFechaPartido(Calendar fechaPartido) {
		this.fechaPartido = fechaPartido;
	}
}
