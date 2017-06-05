package ar.edu.usal.torneos.model.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

import ar.edu.usal.torneos.model.dto.Equipos;
import ar.edu.usal.torneos.model.dto.Partidos;
import ar.edu.usal.torneos.model.dto.Torneos;
import ar.edu.usal.torneos.utils.Validador;

public class PartidosDao {

	private static PartidosDao partidosDaoInstance = null;

	private HashMap<Integer, ArrayList> resultadosPartidosTorneos;

	private PartidosDao(){

		this.resultadosPartidosTorneos = new HashMap<Integer, ArrayList>();
		this.loadFixture();
	}

	public static PartidosDao getInstance(){

		if(partidosDaoInstance == null){

			partidosDaoInstance  = new PartidosDao();
		}

		return partidosDaoInstance;
	}

	private void loadFixture(){

		EquiposDao equiposDao = EquiposDao.getInstance();

		HashMap<Integer, ArrayList> resultadosMap = this.loadResultados();

		File fixtureFile = new File("./archivos/Fixture.txt");
		Scanner fixtureScanner;
		Scanner resultadosScanner;

		try {

			try {
				fixtureFile.createNewFile();

			} catch (IOException e) {

				System.out.println("Se ha verificado un error al cargar el archivo de fixture.");
			}

			fixtureScanner = new Scanner(fixtureFile);

			while(fixtureScanner.hasNextLine()){

				HashMap<String, Object> datosPartidoMap = new HashMap<String, Object>();

				Calendar fechaPartido = Validador.stringToCalendar(fixtureScanner.next().trim(), "dd/MM/yyyy");
				Equipos equipoLocal = equiposDao.getEquipoByName(fixtureScanner.next().trim());  
				Equipos equipoVisitante = equiposDao.getEquipoByName(fixtureScanner.next().trim());
				int idTorneo = 0; 

				Iterator<Integer> resultadosKeySetIterator = resultadosMap.keySet().iterator();

				iteradorResultados:
					while (resultadosKeySetIterator.hasNext()) {

						int idTorneoTmp = resultadosKeySetIterator.next();
						ArrayList<HashMap> resultadosList = resultadosMap.get(idTorneoTmp);

						for (int i = 0; i < resultadosList.size(); i++) {

							HashMap partidoMap = resultadosList.get(i);

							if(partidoMap.get("fechaPartido").equals(fechaPartido)
									&& equipoLocal == partidoMap.get("equipoLocal")
									&& equipoVisitante == partidoMap.get("equipoVisitante")){

								datosPartidoMap.put("equipoLocal", partidoMap.get("equipoLocal"));
								datosPartidoMap.put("equipoVisitante", partidoMap.get("equipoVisitante"));
								datosPartidoMap.put("golesLocal", partidoMap.get("golesLocal"));
								datosPartidoMap.put("golesVisitante", partidoMap.get("golesVisitante"));
								datosPartidoMap.put("fechaPartido", fechaPartido);

								idTorneo = idTorneoTmp;

								break iteradorResultados;
							}
						}
					}

				if(resultadosPartidosTorneos.get(idTorneo) == null){

					resultadosPartidosTorneos.put(idTorneo, new ArrayList<HashMap>());
				}

				resultadosPartidosTorneos.get(idTorneo).add(datosPartidoMap);

			}

			fixtureScanner.close();

		}catch(InputMismatchException e){

			System.out.println("Se ha encontrado un tipo de dato insesperado.");

		}catch (FileNotFoundException e) {

			System.out.println("No se ha encontrado el archivo.");
		}catch(Exception e){

			System.out.println("Se ha verificado un error inesperado.");
		}		
	}

	private HashMap<Integer, ArrayList> loadResultados() {

		EquiposDao equiposDao = EquiposDao.getInstance();

		File resultadosFile = new File("./archivos/Resultados.txt");
		Scanner resultadosScanner;

		HashMap<Integer, ArrayList> resultadosMap = new HashMap<Integer, ArrayList>();

		try {

			try {
				resultadosFile.createNewFile();

			} catch (IOException e) {

				System.out.println("Se ha verificado un error al cargar el archivo de resultados.");
			}

			resultadosScanner = new Scanner(resultadosFile); 

			while(resultadosScanner.hasNextLine()){

				HashMap<String, Object> datosPartidoMap = new HashMap<String, Object>();

				String resultadoTxt = resultadosScanner.nextLine();

				Calendar fechaPartido = Validador.stringToCalendar(resultadoTxt.substring(0, 10).trim(), "dd/MM/yyyy");
				Equipos equipoLocal = equiposDao.getEquipoByName(resultadoTxt.substring(10, 55).trim());  
				Equipos equipoVisitante = equiposDao.getEquipoByName(resultadoTxt.substring(55, 100).trim());
				int golesLocal = Integer.valueOf(resultadoTxt.substring(100, 102).trim());
				int golesVisitante = Integer.valueOf(resultadoTxt.substring(102, 104).trim());

				int idTorneo = Integer.valueOf(resultadoTxt.substring(104, 108).trim());

				datosPartidoMap.put("fechaPartido", fechaPartido);
				datosPartidoMap.put("equipoLocal", equipoLocal);
				datosPartidoMap.put("equipoVisitante", equipoVisitante);
				datosPartidoMap.put("golesLocal", golesLocal);
				datosPartidoMap.put("golesVisitante", golesVisitante);

				if(resultadosMap.get(idTorneo) == null){

					resultadosMap.put(idTorneo, new ArrayList<HashMap>());
				}

				resultadosMap.get(idTorneo).add(datosPartidoMap);
			}

			resultadosScanner.close();

		}catch(InputMismatchException e){

			System.out.println("Se ha encontrado un tipo de dato insesperado.");

		}catch (FileNotFoundException e) {

			System.out.println("No se ha encontrado el archivo.");
		}catch(Exception e){

			System.out.println("Se ha verificado un error inesperado.");
		}

		return resultadosMap;
	}

	//Asumimos que existe ida y vuelta en el torneo.
	public ArrayList<HashMap> generarFixture(Torneos torneo, int nDiasEntrePartidos){

		ArrayList<HashMap> fixtureList = new ArrayList<HashMap>();

		Calendar fechaPartido = Calendar.getInstance();

		for (int i = 0; i < torneo.getEquipos().size(); i++) {

			Equipos equipoLocal = torneo.getEquipos().get(i);

			for (int j = 0; j < torneo.getEquipos().size(); j++) {

				if(i == j) continue;

				Equipos equipoVisitante = torneo.getEquipos().get(j);
				HashMap<String, Object> partidoMap = new HashMap<String, Object>();

				partidoMap.put("equipoLocal", equipoLocal);
				partidoMap.put("equipoVisitante", equipoVisitante);

				Calendar fechaPartidoTmp = Calendar.getInstance();
				fechaPartidoTmp.setTime(fechaPartido.getTime());
				fechaPartidoTmp.add(Calendar.DAY_OF_YEAR, nDiasEntrePartidos);
				partidoMap.put("fechaCalendar", fechaPartidoTmp);

				fechaPartido.setTime(fechaPartidoTmp.getTime());

				fixtureList.add(partidoMap);
			}
		}

		return fixtureList;
	}

	public void actualizarFixture() throws IOException {

		FileWriter  fixtureFile = new FileWriter("./archivos/Fixture.txt");
		PrintWriter fixtureOut = new PrintWriter(fixtureFile);
		
		TorneosDao torneosDao = TorneosDao.getInstance();
		ArrayList<Torneos> torneosList = torneosDao.getTorneosList();
		
		for(int i=0; i < torneosList.size(); i++)
		{
			Torneos torneo = torneosList.get(i);

			for (int j = 0; j < torneo.getPartidos().size(); j++) {
				
				Partidos partido = torneo.getPartidos().get(j);
				
				String fechaPartido = Validador.calendarToString(partido.getFechaPartido(), "dd/MM/yyyy");
				String equipoLocal = partido.getEquipoLocal().getNombre().trim();  
				String equipoVisitante = partido.getEquipoVisitante().getNombre().trim();
				String idTorneo = String.valueOf(torneo.getId());
				
				fixtureOut.println(fechaPartido + "\t"
						+ equipoLocal + "\t"
						+ equipoVisitante + "\t"
						+ idTorneo
						);
			}
			
		}

		fixtureOut.close();
		fixtureFile.close();
	}
	
	public void actualizarArchivoResultados() throws IOException {

		FileWriter  resultadosFile = new FileWriter("./archivos/Resultados.txt");
		PrintWriter resultadosOut = new PrintWriter(resultadosFile);
		
		TorneosDao torneosDao = TorneosDao.getInstance();
		ArrayList<Torneos> torneosList = torneosDao.getTorneosList();
		
		for(int i=0; i < torneosList.size(); i++)
		{
			Torneos torneo = torneosList.get(i);

			for (int j = 0; j < torneo.getPartidos().size(); j++) {
				
				Partidos partido = torneo.getPartidos().get(j);
				
				String fechaPartido = Validador.calendarToString(partido.getFechaPartido(), "dd/MM/yyyy"); //10
				String equipoLocal = Validador.fillString(partido.getEquipoLocal().getNombre().trim(), 45, " ", false);  //45
				String equipoVisitante = Validador.fillString(partido.getEquipoVisitante().getNombre().trim(), 45, " ", false); //45
				String golesLocal = Validador.fillString(String.valueOf(partido.getGolesLocal()), 2, "0", true); //2
				String golesVisitante = Validador.fillString(String.valueOf(partido.getGolesVisitante()), 2, "0", true); //2
				String idTorneo = Validador.fillString(String.valueOf(torneo.getId()), 4, "0", true); //4
				
				resultadosOut.println(fechaPartido + equipoLocal + equipoVisitante + idTorneo);
			}
			
		}

		resultadosOut.close();
		resultadosFile.close();
	}
	
	public HashMap<Integer, ArrayList> getResultadosPartidosTorneos() {
		return resultadosPartidosTorneos;
	}

	public void setResultadosPartidosTorneos(
			HashMap<Integer, ArrayList> resultadosPartidosTorneos) {
		this.resultadosPartidosTorneos = resultadosPartidosTorneos;
	}
}
