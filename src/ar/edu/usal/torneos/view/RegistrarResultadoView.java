package ar.edu.usal.torneos.view;

import java.util.ArrayList;

import ar.edu.usal.torneos.utils.Validador;

public class RegistrarResultadoView {

	public RegistrarResultadoView(){}
	
	public int elegirPartido(ArrayList<String> partidosStringList) {
		
		System.out.println("PARTIDOS DEL TORNEO:");
		
		for (int i = 0; i < partidosStringList.size(); i++) {
			
			System.out.println(partidosStringList.get(i));
		}		
		System.out.println();
		
		return Validador.insertInt("Ingresar numero partido: ", 1, partidosStringList.size(), false);
	}

	public int ingresarGolesLocal() {
		
		return Validador.insertInt("Ingresar goles del equipo local: ", 0, 99, false);
	}

	public int ingresarGolesVisitante() {
		
		return Validador.insertInt("Ingresar goles del equipo visitante: ", 0, 99, false);
	}

}
