package ar.edu.usal.torneos.view;

import ar.edu.usal.torneos.utils.Validador;

public class MenuView {

	public int mostrarMenu() {

		String[] menu = {

				"1 - Ingresar costo inscripcion.",
				"2 - Registrar resultado.",
				"3 - Mostrar ganadores y premios.",
				"4 - Consultar estadisticas.",
				"0 - Salir del sistema."
		};

		System.out.println();
		for (int i = 0; i < menu.length; i++) {

			System.out.println(menu[i]);
		}
		System.out.println();
		int opcion = Validador.insertInt("Elegir una opcion: ", 0, menu.length-1, false);

		return opcion;
	}

	public void salidaDelSistema() {
		
		System.out.println("GRACIAS POR UTILIZAR EL SERVICIO.");
	}
}
