package ar.edu.usal.torneos.model.dto;

import java.util.Calendar;

public class Partidos {

	private Equipos equipoLocal;
	private Equipos equipoVisitante;
	private int golesLocal;
	private int golesVisitante;
	private Calendar fechaPartido;
	
	public Partidos(Equipos equipoLocal, Equipos equipoVisitante,
			int golesLocal, int golesVisitante, Calendar fechaPartido) {
		super();
		this.equipoLocal = equipoLocal;
		this.equipoVisitante = equipoVisitante;
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

	public Equipos getEquipoVisitante() {
		return equipoVisitante;
	}

	public void setEquipovisitante(Equipos equipoVisitante) {
		this.equipoVisitante = equipoVisitante;
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
