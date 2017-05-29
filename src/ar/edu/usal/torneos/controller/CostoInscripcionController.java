package ar.edu.usal.torneos.controller;

import ar.edu.usal.torneos.model.dao.TorneosDao;
import ar.edu.usal.torneos.view.CostoInscripcionView;

public class CostoInscripcionController {

	private CostoInscripcionView costoInscripcionView;

	public CostoInscripcionController(){

		this.costoInscripcionView = new CostoInscripcionView();
	}

	public void registrarCostoInscripcion() {

		double costoInscripcion = this.costoInscripcionView.ingresarCostoInscripcion();
		
		TorneosDao torneosDao = TorneosDao.getInstance();
		
		if(torneosDao.registrarCostoInscripcion(costoInscripcion))
			this.costoInscripcionView.registracionCostoSuccess();
		else this.costoInscripcionView.registracionCostoFailed();
	}
}
