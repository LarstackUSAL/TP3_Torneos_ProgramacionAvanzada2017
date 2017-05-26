package ar.edu.usal.torneos.model.interfaces;

public interface ITorneoEmpresa {

	public static final double PORCENTAJE_ORGANIZACION = 0.20;
	public static final double PORCENTAJE_PRIMER_PUESTO = 0.6335;
	public static final double PORCENTAJE_SEGUNDO_PUESTO = 0.1665;
	
	public static final int PUNTOS_PARTIDO_GANADO = 3;
	public static final int PUNTOS_PARTIDO_PERDIDO = 0;
	public static final int PUNTOS_PARTIDO_EMPATADO = 1;
	
	public static final int MAX_JUGADORES_EQUIPO = 7;
	
	public double asignarPremios();
}
