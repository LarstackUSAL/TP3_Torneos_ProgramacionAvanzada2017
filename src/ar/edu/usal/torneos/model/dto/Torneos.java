package ar.edu.usal.torneos.model.dto;

import java.util.ArrayList;
import java.util.Calendar;

import ar.edu.usal.torneos.model.interfaces.IPagoInscripcion;

public class Torneos implements IPagoInscripcion{

	private int id;
	private int anioInicioTorneo;
	private int anioFinTorneo;
	private int mesFinTorneo;
	private double costoInscripcion;
	private int cantidadEquipos;
	private int puntuacionMaxima;
	private int puntuacionMinima;
	private int totalGoles;

	private ArrayList<Equipos> equipos =  new ArrayList<Equipos>();
	private ArrayList<Partidos> partidos =  new ArrayList<Partidos>();
	
	public Torneos(){}

	public Torneos(int id, int anioInicioTorneo, double costoInscripcion,
			int cantidadEquipos, int puntuacionMaxima, int puntuacionMinima,
			int totalGoles, int anioFinTorneo, int mesFinTorneo, ArrayList<Equipos> equipos) {
		super();
		this.id = id;
		this.anioInicioTorneo = anioInicioTorneo;
		this.costoInscripcion = costoInscripcion;
		this.cantidadEquipos = cantidadEquipos;
		this.puntuacionMaxima = puntuacionMaxima;
		this.puntuacionMinima = puntuacionMinima;
		this.totalGoles = totalGoles;
		this.anioFinTorneo = anioFinTorneo;
		this.mesFinTorneo = mesFinTorneo;
		this.equipos = equipos;
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

	public void setPartidos(Equipos equipoLocal, Equipos equipoVisitante, 
			int golesLocal, int golesVisitante, Calendar fechaPartido, Integer indiceArrayPartidos) {
		
		if(indiceArrayPartidos != null){
			
			Partidos partidoTmp = this.partidos.get(indiceArrayPartidos);
			
			partidoTmp.setEquipoLocal(equipoLocal);
			partidoTmp.setEquipovisitante(equipoVisitante);
			partidoTmp.setGolesLocal(golesLocal);
			partidoTmp.setGolesVisitante(golesVisitante);
			partidoTmp.setFechaPartido(fechaPartido);
		
		}else{
			
			this.partidos.add(new Partidos(equipoLocal, equipoVisitante, golesLocal, golesVisitante, fechaPartido));
		}
		
	}

	public double getCostoInscripcion() {		
		return costoInscripcion;
	}

	public void setCostoInscripcion(double costoInscripcion) {
		this.costoInscripcion = costoInscripcion;
	}

	public int getAnioInicioTorneo() {
		return anioInicioTorneo;
	}

	public void setAnioInicioTorneo(int anioInicioTorneo) {
		this.anioInicioTorneo = anioInicioTorneo;
	}

	public int getCantidadEquipos() {
		return cantidadEquipos;
	}

	public void setCantidadEquipos(int cantidadEquipos) {
		this.cantidadEquipos = cantidadEquipos;
	}

	public int getPuntuacionMaxima() {
		return puntuacionMaxima;
	}

	public void setPuntuacionMaxima(int puntuacionMaxima) {
		this.puntuacionMaxima = puntuacionMaxima;
	}

	public int getPuntuacionMinima() {
		return puntuacionMinima;
	}

	public void setPuntuacionMinima(int puntuacionMinima) {
		this.puntuacionMinima = puntuacionMinima;
	}

	public int getTotalGoles() {
		return totalGoles;
	}

	public void setTotalGoles(int totalGoles) {
		this.totalGoles = totalGoles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAnioFinTorneo() {
		return anioFinTorneo;
	}

	public void setAnioFinTorneo(int anioFinTorneo) {
		this.anioFinTorneo = anioFinTorneo;
	}

	public int getMesFinTorneo() {
		return mesFinTorneo;
	}

	public void setMesFinTorneo(int mesFinTorneo) {
		this.mesFinTorneo = mesFinTorneo;
	}
}
