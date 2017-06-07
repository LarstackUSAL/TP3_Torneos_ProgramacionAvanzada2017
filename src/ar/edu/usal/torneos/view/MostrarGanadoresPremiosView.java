package ar.edu.usal.torneos.view;

import java.util.HashMap;

public class MostrarGanadoresPremiosView {
	
	public MostrarGanadoresPremiosView(){}

	public void mostrarPremios(HashMap<String,String> equipoGanadorMap, HashMap<String,String> equipoSegundoMap){
		
		System.out.println();
		
		String nombreGanador = equipoGanadorMap.get("nombre");
		String montoGanador = equipoGanadorMap.get("montoEquipo");
		String montoJugadorGanador = equipoGanadorMap.get("montoJugador");
		
		String nombreSegundo = equipoSegundoMap.get("nombre");
		String montoSegundo = equipoSegundoMap.get("montoEquipo");
		String montoJugadorSegundo = equipoSegundoMap.get("montoJugador");
		
		System.out.println("GANADOR: " + nombreGanador);
		System.out.println("Premio del equipo: " + montoGanador);
		System.out.println("Premio por jugador: " + montoJugadorGanador);
		System.out.println();
		System.out.println("SEGUNDO: " + nombreSegundo);
		System.out.println("Premio del equipo: " + montoSegundo);
		System.out.println("Premio por jugador: " + montoJugadorSegundo);
	}
}
