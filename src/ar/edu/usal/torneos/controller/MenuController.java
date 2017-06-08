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
				costoInscripcionController.registrarCostoInscripcion(Integer.valueOf(args[0]));
				break;
			case 2:

				RegistrarResultadoController registrarResultadoController = new RegistrarResultadoController();
				registrarResultadoController.registrarResultado();
				break;
			case 3:

				MostrarGanadoresPremiosController mostrarGanadoresPremiosController = new MostrarGanadoresPremiosController();
				mostrarGanadoresPremiosController.mostrarGanadoresPremios();
				break;
			case 4:

				ConsultarEstadisticasController consultarEstadisticasController = new ConsultarEstadisticasController();
				consultarEstadisticasController.consultarEstadisticas();
				consultarEstadisticasController.consultaTablaDePosiciones(Integer.valueOf(args[1]), Integer.valueOf(args[2]));
				break;
			case 0:

				salir = true;
				menuView.salidaDelSistema();
				break;
			}

		}while(!salir);
	}
}
