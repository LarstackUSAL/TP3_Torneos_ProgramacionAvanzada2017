package ar.edu.usal.torneos.view;

import java.util.ArrayList;
import java.util.Iterator;

public class ConsultarEstadisticasView {

	public void mostrarEstadisticasTorneos(String cantidadTorneos, String promedioGoles,
			boolean hayTorneoPendiente) {
		
		System.out.println();
		System.out.println("Se han jugado " + cantidadTorneos);
		
		if(hayTorneoPendiente){
			
			System.out.println("de los cuales uno todavia no ha finalizado, por lo tanto no se tendrà en cuenta en las estadisticas de goles.\n");
		}
		
		System.out.println("Promedio goles de los torneos jugados: " + promedioGoles);
	}

	public void mostrarTablaPosicionesReverse(ArrayList<String> tablaPosiciones) {
		
		Iterator tablaPosicionesIterator = tablaPosiciones.iterator();
		
		System.out.println();
		System.out.println("TABLA POSICIONES REVERSE");
		
		System.out.println("NOMBRE\t" +
				"PTS\t" +
				"PG\t" +
				"PE\t" +
				"PP\t" +
				"GF\t" +
				"GC\t" +
				"GF-GC");
		
		while (tablaPosicionesIterator.hasNext()) {
			
			System.out.println(tablaPosicionesIterator.next());
		}
	}

}
