package ar.edu.usal.torneos.controller;

import java.util.ArrayList;
import java.util.HashMap;

import ar.edu.usal.torneos.model.dao.TorneosDao;
import ar.edu.usal.torneos.model.dto.Equipos;
import ar.edu.usal.torneos.model.dto.Partidos;
import ar.edu.usal.torneos.model.dto.Torneos;
import ar.edu.usal.torneos.model.interfaces.ITorneoEmpresa;
import ar.edu.usal.torneos.view.MostrarGanadoresPremiosView;

public class MostrarGanadoresPremiosController {

	MostrarGanadoresPremiosView mostrarGanadoresPremiosView;

	public MostrarGanadoresPremiosController(){

		this.mostrarGanadoresPremiosView = new MostrarGanadoresPremiosView();
	}

	public void mostrarGanadoresPremios() {

		/* Si aun no se han regitrado los resultados de todos los partidos, 
		 * los partidos de los cuales no se ha registrado el resultado se tomaran como empatados 0-0.
		 */

		TorneosDao torneosDao = TorneosDao.getInstance();
		Torneos torneo = torneosDao.getTorneoById(torneosDao.getIdTorneoActual());

		Equipos equipoGanadorTorneo;
		Equipos equipoSegundoTorneo;

		for (int i = 0; i < torneo.getPartidos().size(); i++) {

			Partidos partido = torneo.getPartidos().get(i);

			Equipos equipoLocal = partido.getEquipoLocal();
			Equipos equipoVisitante = partido.getEquipoVisitante();

			int golesLocal = partido.getGolesLocal();
			int golesVisitante = partido.getGolesVisitante();
			equipoLocal.setGoles(golesLocal);
			equipoVisitante.setGoles(golesVisitante);

			Equipos equipoGanadorTmp = equipoLocal;
			Equipos equipoPerdedorTmp = equipoVisitante;
			boolean esEmpate = true;

			if(golesLocal > golesVisitante) esEmpate = false;
			else if(golesLocal < golesVisitante){

				equipoGanadorTmp = equipoVisitante;
				equipoPerdedorTmp = equipoLocal;
				esEmpate = false;
			}

			equipoLocal.setGoles(equipoLocal.getGoles() + golesLocal);
			equipoVisitante.setGoles(equipoVisitante.getGoles() + golesVisitante);

			//Incremento el total de goles en el torneo.
			torneo.setTotalGoles(torneo.getTotalGoles() + golesLocal + golesVisitante);
			
			equipoLocal.setGolesEnContra(equipoLocal.getGolesEnContra() + golesVisitante);
			equipoVisitante.setGolesEnContra(equipoVisitante.getGolesEnContra() + golesLocal);

			if(!esEmpate){

				equipoGanadorTmp.setPuntos(equipoGanadorTmp.getPuntos() + 3);				
				equipoGanadorTmp.setCantidadPartidosGanados(equipoGanadorTmp.getCantidadPartidosGanados() + 1);
				equipoPerdedorTmp.setCantidadPartidosPerdidos(equipoPerdedorTmp.getCantidadPartidosPerdidos() + 1);

			}else{

				equipoLocal.setPuntos(equipoLocal.getPuntos() + 1);				
				equipoVisitante.setPuntos(equipoVisitante.getPuntos() + 1);

				equipoLocal.setCantidadPartidosEmpatados(equipoLocal.getCantidadPartidosEmpatados() + 1);
				equipoVisitante.setCantidadPartidosEmpatados(equipoVisitante.getCantidadPartidosEmpatados() + 1);
			}
		}

		ArrayList<Equipos> tablaPosiciones = torneosDao.getTablaPosiciones(torneo);

		equipoGanadorTorneo = tablaPosiciones.get(0);
		equipoSegundoTorneo = tablaPosiciones.get(1);

		torneosDao.grabarTablaPosiciones(torneo);

		double montoParaRepartir = torneosDao.montoParaRepartir();

		double montoPrimero = montoParaRepartir * ITorneoEmpresa.PORCENTAJE_PRIMER_PUESTO;
		double montoPorJugadorGanador = montoPrimero / ITorneoEmpresa.MAX_JUGADORES_EQUIPO;
		double montoSegundo = montoParaRepartir - montoPrimero;
		double montoPorJugadorSegundo = montoSegundo / ITorneoEmpresa.MAX_JUGADORES_EQUIPO;
		
		HashMap<String, String> equipoGanadorMap = new HashMap<String, String>();
		HashMap<String, String> equipoSegundoMap = new HashMap<String, String>();
		
		equipoGanadorMap.put("nombre", equipoGanadorTorneo.getNombre().trim());
		equipoGanadorMap.put("montoEquipo", String.valueOf(montoPrimero));
		equipoGanadorMap.put("montoJugador", String.valueOf(montoPorJugadorGanador));
		
		equipoGanadorMap.put("nombre", equipoSegundoTorneo.getNombre().trim());
		equipoGanadorMap.put("montoEquipo", String.valueOf(montoSegundo));
		equipoGanadorMap.put("montoJugador", String.valueOf(montoPorJugadorSegundo));
		
		this.mostrarGanadoresPremiosView.mostrarPremios(equipoGanadorMap, equipoSegundoMap);
	}
}
