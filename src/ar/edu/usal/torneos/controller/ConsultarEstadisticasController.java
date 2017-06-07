package ar.edu.usal.torneos.controller;

import java.util.ArrayList;
import java.util.Iterator;

import ar.edu.usal.torneos.model.dao.TorneosDao;
import ar.edu.usal.torneos.model.dto.Equipos;
import ar.edu.usal.torneos.model.dto.Torneos;
import ar.edu.usal.torneos.view.ConsultarEstadisticasView;

public class ConsultarEstadisticasController {

	private ConsultarEstadisticasView consultarEstadisticasView;
	
	public ConsultarEstadisticasController(){
		
		this.consultarEstadisticasView = new ConsultarEstadisticasView();
	}
	
	public void consultarEstadisticas() {
		
		TorneosDao torneosDao = TorneosDao.getInstance();
		
		ArrayList<Torneos> torneos = torneosDao.getTorneosList();
		int cantidadTorneos = torneos.size();
		
		boolean hayTorneoPendiente = false;
		int totalGoles = 0;
		
		Iterator torneosIterator = torneos.iterator();
		
		while (torneosIterator.hasNext()) {
			
			Torneos torneoIt = (Torneos) torneosIterator.next();
			
			if(torneoIt.getAnioFinTorneo() == 0) hayTorneoPendiente = true;
			else{
				
				totalGoles += torneoIt.getTotalGoles();
			}
		}
		
		double promedioGoles = hayTorneoPendiente ? (totalGoles / (cantidadTorneos - 1)) : (totalGoles / cantidadTorneos);
		
		this.consultarEstadisticasView.mostrarEstadisticasTorneos(String.valueOf(cantidadTorneos),
				String.valueOf(promedioGoles), hayTorneoPendiente);
		
		
	}

	public void consultaTablaDePosiciones(int anio, int mes) {
		
		TorneosDao torneosDao = TorneosDao.getInstance();
		
		Torneos torneo = torneosDao.getTorneoByAnioMesFin(anio, mes);
		
		ArrayList<Equipos> tablaReverse = torneosDao.reverseTablaPosiciones(torneo);
		ArrayList<String> tablaPosiciones = new ArrayList<String>();
		
		Iterator tablaReverseIterator = tablaReverse.iterator();
		
		while (tablaReverseIterator.hasNext()) {
			
			Equipos equipoIterado = (Equipos)tablaReverseIterator.next();
			
			String nombre = equipoIterado.getNombre().trim();
			String puntos = String.valueOf(equipoIterado.getPuntos());
			String cantidadPartidosGanados = String.valueOf(equipoIterado.getCantidadPartidosGanados());
			String cantidadPartidosEmpatados = String.valueOf(equipoIterado.getCantidadPartidosEmpatados());
			String cantidadPartidosPerdidos = String.valueOf(equipoIterado.getCantidadPartidosPerdidos());
			String goles = String.valueOf(equipoIterado.getGoles());
			String golesEnContra = String.valueOf(equipoIterado.getGolesEnContra());
			String diferenciaGoles = String.valueOf(equipoIterado.getGoles() - equipoIterado.getGolesEnContra());

			tablaPosiciones.add(

							nombre + "\t" +
							puntos + "\t" +
							cantidadPartidosGanados + "\t" +
							cantidadPartidosEmpatados + "\t" +
							cantidadPartidosPerdidos + "\t" +
							goles + "\t" +
							golesEnContra + "\t" +
							diferenciaGoles					
					);
		}
		
		this.consultarEstadisticasView.mostrarTablaPosicionesReverse(tablaPosiciones);
	}
	
	
}
