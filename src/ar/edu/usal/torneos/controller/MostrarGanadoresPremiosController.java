package ar.edu.usal.torneos.controller;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;

import ar.edu.usal.torneos.model.dao.TorneosDao;
import ar.edu.usal.torneos.model.dto.Equipos;
import ar.edu.usal.torneos.model.dto.Partidos;
import ar.edu.usal.torneos.model.dto.Torneos;
import ar.edu.usal.torneos.utils.Validador;
import ar.edu.usal.torneos.utils.ValueComparator;
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
		
		HashMap<String, Integer> puntosEquipo = new HashMap<String, Integer>();
		HashMap<String, Integer> cantidadPartidosGanadosEquipo = new HashMap<String, Integer>(); 
		HashMap<String, Integer> cantidadPartidosEmpatadosEquipo = new HashMap<String, Integer>();
		HashMap<String, Integer> cantidadPartidosPerdidosEquipo = new HashMap<String, Integer>();
		HashMap<String, Integer> golesEquipo = new HashMap<String, Integer>();
		HashMap<String, Integer> golesEnContraEquipo = new HashMap<String, Integer>();
		
		for (int i = 0; i < torneo.getPartidos().size(); i++) {
			
			Partidos partido = torneo.getPartidos().get(i);
			
			String equipoLocal = partido.getEquipoLocal().getNombre().trim();
			String equipoVisitante = partido.getEquipoVisitante().getNombre().trim();
			
			int golesLocal = partido.getGolesLocal();
			int golesVisitante = partido.getGolesVisitante();
			
			String equipoGanadorTmp = equipoLocal;
			String equipoPerdedorTmp = equipoVisitante;
			boolean esEmpate = true;
			
			if(golesLocal > golesVisitante) esEmpate = false;
			else if(golesLocal < golesVisitante){
			
				equipoGanadorTmp = equipoVisitante;
				equipoPerdedorTmp = equipoLocal;
				esEmpate = false;
				
			}
			
			int golesEquipoLocal = golesEquipo.get(equipoLocal)!=null ? golesEquipo.get(equipoLocal) : 0; 
			golesEquipo.put(equipoLocal, golesEquipoLocal+golesLocal);
			int golesEquipoVisitante = golesEquipo.get(equipoVisitante)!=null ? golesEquipo.get(equipoVisitante) : 0; 
			golesEquipo.put(equipoVisitante, golesEquipoVisitante+golesVisitante);
			
			int golesEnContraEquipoLocal = golesEnContraEquipo.get(equipoLocal)!=null ? golesEnContraEquipo.get(equipoLocal) : 0; 
			golesEnContraEquipo.put(equipoLocal, golesEnContraEquipoLocal+golesVisitante);
			int golesEnContraEquipoVisitante = golesEnContraEquipo.get(equipoVisitante)!=null ? golesEnContraEquipo.get(equipoVisitante) : 0; 
			golesEnContraEquipo.put(equipoVisitante, golesEnContraEquipoVisitante+golesLocal);
			
			if(!esEmpate){
				
				int puntosGanador = puntosEquipo.get(equipoGanadorTmp)!=null ? puntosEquipo.get(equipoGanadorTmp) : 0; 
				puntosEquipo.put(equipoGanadorTmp, puntosGanador+3);
			
				int cantidadPartidosGanados = cantidadPartidosGanadosEquipo.get(equipoGanadorTmp)!=null ? cantidadPartidosGanadosEquipo.get(equipoGanadorTmp) : 0;
				cantidadPartidosGanadosEquipo.put(equipoGanadorTmp, cantidadPartidosGanados+1);
				
				int cantidadPartidosPerdidos = cantidadPartidosPerdidosEquipo.get(equipoPerdedorTmp)!=null ? cantidadPartidosPerdidosEquipo.get(equipoPerdedorTmp) : 0;
				cantidadPartidosPerdidosEquipo.put(equipoPerdedorTmp, cantidadPartidosPerdidos+1);
			}else{
				
				int puntosEquipoLocal = puntosEquipo.get(equipoLocal)!=null ? puntosEquipo.get(equipoLocal) : 0;
				puntosEquipo.put(equipoLocal, puntosEquipoLocal+1);
				
				int puntosEquipoVisitante = puntosEquipo.get(equipoVisitante)!=null ? puntosEquipo.get(equipoVisitante) : 0;
				puntosEquipo.put(equipoVisitante, puntosEquipoVisitante+1);
				
				int cantidadPartidosEmpatadosEquipoLocal = cantidadPartidosEmpatadosEquipo.get(equipoLocal)!=null ? cantidadPartidosEmpatadosEquipo.get(equipoLocal) : 0;
				cantidadPartidosEmpatadosEquipo.put(equipoLocal, cantidadPartidosEmpatadosEquipoLocal+1);
				
				int cantidadPartidosEmpatadosEquipoVisitante = cantidadPartidosEmpatadosEquipo.get(equipoVisitante)!=null ? cantidadPartidosEmpatadosEquipo.get(equipoVisitante) : 0;
				cantidadPartidosEmpatadosEquipo.put(equipoVisitante, cantidadPartidosEmpatadosEquipoVisitante+1);
			}
		}
		
//		LinkedHashMap hashMapOrdenado = Validador.sortHashMapStringInteger(puntosEquipo);
		
			Comparator<String> comparator = new ComparadorMap(puntosEquipo);
			 
			TreeMap<String, Integer> mapOrdenado = new TreeMap<String, Integer>(comparator);
			mapOrdenado.putAll(puntosEquipo);
			
			
	}
	
	class ComparadorMap implements Comparator<String>{
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
	 
		public ComparadorMap(HashMap<String, Integer> map){
			
			this.map.putAll(map);
		}
	 
		@Override
		public int compare(String s1, String s2) {
			
			if(map.get(s1) >= map.get(s2)){
				
				return -1;
			}else{
				
				return 1;
			}
		}
	}
}
