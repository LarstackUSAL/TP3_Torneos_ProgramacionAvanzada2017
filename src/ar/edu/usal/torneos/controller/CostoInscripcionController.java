package ar.edu.usal.torneos.controller;

import java.io.IOException;

import ar.edu.usal.torneos.exception.HayTorneoEnCursoException;
import ar.edu.usal.torneos.model.dao.TorneosDao;
import ar.edu.usal.torneos.view.CostoInscripcionView;

public class CostoInscripcionController {

	private CostoInscripcionView costoInscripcionView;

	public CostoInscripcionController(){

		this.costoInscripcionView = new CostoInscripcionView();
	}

	public void registrarCostoInscripcion(int nDiasEntrePartidos) {

		double costoInscripcion = this.costoInscripcionView.ingresarCostoInscripcion();
		
		TorneosDao torneosDao = TorneosDao.getInstance();
			
			try {
				torneosDao.registrarCostoInscripcion(costoInscripcion, nDiasEntrePartidos);

			} catch (HayTorneoEnCursoException | IOException e) {

				e.printStackTrace();
			}
			
	}
}
