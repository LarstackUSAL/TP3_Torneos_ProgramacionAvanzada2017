package ar.edu.usal.torneos.controller;

import java.io.IOException;
import java.util.ArrayList;

import ar.edu.usal.torneos.model.dao.PartidosDao;
import ar.edu.usal.torneos.model.dao.TorneosDao;
import ar.edu.usal.torneos.model.dto.Partidos;
import ar.edu.usal.torneos.model.dto.Torneos;
import ar.edu.usal.torneos.view.RegistrarResultadoView;


public class RegistrarResultadoController {

	private RegistrarResultadoView registrarResultadoView;

	public RegistrarResultadoController(){
		
		this.registrarResultadoView = new RegistrarResultadoView();
	}
	
	public void registrarResultado() {
		
		TorneosDao torneosDao = TorneosDao.getInstance();
		Torneos torneo = torneosDao.getTorneoById(torneosDao.getIdTorneoActual());
		
		ArrayList<String> partidosStringList = new ArrayList<String>();
		
		for (int i = 0; i < torneo.getPartidos().size(); i++) {
			
			Partidos partido = torneo.getPartidos().get(i);
			String partidosString = (i+1) + ". " + partido.getEquipoLocal().getNombre().trim()
					+ " - " + partido.getEquipoVisitante().getNombre().trim();
			
			partidosStringList.add(partidosString);
		}
		
		int numeroPartido = registrarResultadoView.elegirPartido(partidosStringList);
		
		Partidos partido = torneo.getPartidos().get(numeroPartido-1);
		
		int golesLocal = registrarResultadoView.ingresarGolesLocal();
		int golesVisitante = registrarResultadoView.ingresarGolesVisitante();
		
		partido.setGolesLocal(golesLocal);
		partido.setGolesVisitante(golesVisitante);
		
		PartidosDao partidosDao = PartidosDao.getInstance();
		try {
			
			partidosDao.actualizarArchivoResultados();
		
		} catch (IOException e) {
			
			System.out.println("Se ha verificado un error al actualizar el archivo de resultados.");
		}
	}

	
}
