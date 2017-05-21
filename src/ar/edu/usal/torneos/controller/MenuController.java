package ar.edu.usal.torneos.controller;

import ar.edu.usal.torneos.view.MenuView;

public class MenuController {

	public MenuController(){}

	public static void main(String[] args) {

		MenuView menuView = new MenuView();
		boolean salir = false;

		do{

			int opcion = menuView.mostrarMenu();
			switch(opcion) {

			case 1:

				CostoInscripcionController costoInscripcionController = new CostoInscripcionController();
				costoInscripcionController.registrarCostoInscripcion();
				break;
			case 2:

				RegistrarResultadoController registrarResultadoController = new RegistrarResultadoController();
				registrarResultadoController.registrarResultado();
				break;
			case 3:

				MostrarGanadoresPremiosController mostrarGanadoresPremiosController = new MostrarGanadoresPremiosController();
				mostrarGanadoresPremiosController.mostrarGanadoresPremios();
			case 4:

				ConsultarEstadisticasController consultarEstadisticasController = new ConsultarEstadisticasController();
				consultarEstadisticasController.consultarEstadisticas();
				break;
			case 0:

				salir = true;
				menuView.salidaDelSistema();
				break;
			}

		}while(!salir);
	}
}
