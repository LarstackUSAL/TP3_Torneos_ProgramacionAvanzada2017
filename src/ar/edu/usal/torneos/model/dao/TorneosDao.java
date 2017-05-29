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
import java.util.Scanner;

import ar.edu.usal.torneos.model.dto.Equipos;
import ar.edu.usal.torneos.model.dto.Torneos;
import ar.edu.usal.torneos.model.interfaces.ITorneoEmpresa;

public class TorneosDao implements ITorneoEmpresa{

	private static int nextIdTorneo = 1;
	
	private static TorneosDao torneosDaoInstance = null;
	private ArrayList<Torneos> torneosList; 
	
	public TorneosDao(){
		
		this.torneosList = new ArrayList<Torneos>();
		this.loadTorneos();
	}
	
	public static TorneosDao getInstance() {
		
		if(torneosDaoInstance == null){
			
			torneosDaoInstance = new TorneosDao();
		}
		
		return torneosDaoInstance;
	}
	
	private void loadTorneos() {

		File torneosFile = new File("./archivos/Torneos.txt");
		Scanner torneosScanner;

		try {

			try {
				torneosFile.createNewFile();

			} catch (IOException e) {

				System.out.println("Se ha verificado un error al cargar el archivo de torneos.");
			}

			torneosScanner = new Scanner(torneosFile);

			HashMap<Integer,Double> valoresMap = this.loadCostoInscripcion();

			EquiposDao equiposDao = EquiposDao.getInstance();
			PartidosDao partidosDao = PartidosDao.getInstance();
			
			while(torneosScanner.hasNextLine()){

				int id = torneosScanner.nextInt();
				int anioInicioTorneo = torneosScanner.nextInt();
				int cantidadEquipos = torneosScanner.nextInt();
				int puntuacionMaxima = torneosScanner.nextInt();
				int puntuacionMinima = torneosScanner.nextInt();
				int totalGoles = torneosScanner.nextInt();
				
				Torneos torneo = new Torneos();

				torneo.setId(id);
				torneo.setAnioInicioTorneo(anioInicioTorneo);
				torneo.setCantidadEquipos(cantidadEquipos);
				torneo.setPuntuacionMaxima(puntuacionMaxima);
				torneo.setPuntuacionMinima(puntuacionMinima);
				torneo.setTotalGoles(totalGoles);
				torneo.setCostoInscripcion(valoresMap.get(anioInicioTorneo));
				
				torneo.setEquipos(equiposDao.getEquiposTorneoById(id));
								
				for (int i = 0; i < partidosDao.getResultadosPartidosTorneos().size(); i++) {
					
					HashMap<String, Object> datosTmp = partidosDao.getResultadosPartidosTorneos().get(i);
					
					if((int)datosTmp.get("idTorneo") == id){
						
						torneo.setPartidos((Equipos)datosTmp.get("equipoLocal"),
								(Equipos)datosTmp.get("equipoVisitante"), 
								(int)datosTmp.get("golesLocal"),
								(int)datosTmp.get("golesVisitante"),
								(Calendar)datosTmp.get("fechaPartido"), null);
					}
				}
				
				this.torneosList.add(torneo);
			}
			
			torneosScanner.close();

		}catch(InputMismatchException e){

			System.out.println("Se ha encontrado un tipo de dato insesperado.");

		}catch(FileNotFoundException e) {

			System.out.println("No se ha encontrado el archivo.");

		}catch(Exception e){

			e.printStackTrace();
		}
	}
	
	private HashMap<Integer,Double> loadCostoInscripcion() {
		
		File costoInscripcionesFile = new File("./archivos/InscripcionesValorAnual.txt");
		Scanner costoInscripcionesScanner;
		
		HashMap valoresMap = new HashMap<Integer,Double>();
		
		try {
			
			try {
				costoInscripcionesFile.createNewFile();
			
			} catch (IOException e) {

				System.out.println("Se ha verificado un error al cargar el archivo de costo de inscripciones.");
			}
			
			costoInscripcionesScanner = new Scanner(costoInscripcionesFile);
			
			while(costoInscripcionesScanner.hasNextLine()){
				
				String linea = costoInscripcionesScanner.nextLine();
				String[] costoInscripcionesArray = linea.split(";");
				
				int anioTorneo = Integer.valueOf(costoInscripcionesArray[0].trim());
				double costoTxt = Double.parseDouble(costoInscripcionesArray[1]);
				
				valoresMap.put(anioTorneo, costoTxt);
			}
			
			costoInscripcionesScanner.close();
			
		}catch(InputMismatchException e){
			
			System.out.println("Se ha encontrado un tipo de dato insesperado.");
			
		}catch (FileNotFoundException e) {
			
			System.out.println("No se ha encontrado el archivo.");
		
		}catch(StringIndexOutOfBoundsException e){
			
			System.out.println("Se ha verificado un error en el parseo del archivo de costo de inscripciones.");
				
		}catch(Exception e){
			
			System.out.println("Se ha verificado un error inesperado.");
		}
		
		return valoresMap;
	}
	
	public void actualizarArchivo() throws IOException {

		FileWriter torneosFile = new FileWriter("./archivos/Torneos.txt");
		PrintWriter torneosOut = new PrintWriter(torneosFile);

		for(int i=0; i < this.torneosList.size(); i++)
		{
			Torneos torneo = this.torneosList.get(i);

			torneosOut.println(
					torneo.getId() + " " +
							torneo.getAnioInicioTorneo() + " " +
							torneo.getCantidadEquipos() + " " +
							torneo.getPuntuacionMaxima() + " " +
							torneo.getPuntuacionMinima()+ " " +
							torneo.getTotalGoles());
		}

		torneosOut.close();
		torneosFile.close();
		
		//Se actualiza el archivo de id.
		FileWriter idtorneoFile = new FileWriter("./archivos/ID_TORNEOS.txt");
		PrintWriter idtorneoOut = new PrintWriter(idtorneoFile);
		
		idtorneoOut.println(nextIdTorneo);
		idtorneoOut.close();
		idtorneoFile.close();
	}
	
	// Al registrar un nuevo costo de inscripcion, se inicializa un nuevo torneo.
	public boolean registrarCostoInscripcion(double costoInscripcion) {

		// Se valida que no haya un torneo en curso, ya que, por definicion del enunciado, los torneos son anuales(LR)
		if(this.hayTorneoEnCurso()){
			
			throw new HayTorneoEnCursoException();
		}else{
			
			Torneos torneo = new Torneos();

			torneo.setId(id);
			torneo.setAnioInicioTorneo(anioInicioTorneo);
			torneo.setCantidadEquipos(cantidadEquipos);
			torneo.setPuntuacionMaxima(puntuacionMaxima);
			torneo.setPuntuacionMinima(puntuacionMinima);
			torneo.setTotalGoles(totalGoles);
			torneo.setCostoInscripcion(valoresMap.get(anioInicioTorneo));
			
			torneo.setEquipos(equiposDao.getEquiposTorneoById(id));
			setPartidos
		}
	}
	
	private boolean hayTorneoEnCurso() {
		
		Scanner tablaPosicionesScanner;

		File directoryArchivos = new File("./archivos/");
		File[] archivosArray = directoryArchivos.listFiles();

		int contadorTablaPosiciones = 0;
		
		for (int i = 0; i < archivosArray.length; i++) {

			File file = archivosArray[i];

			if (file.isFile()){

				String nombreFile = file.getName();
				if (nombreFile.endsWith(".txt") && nombreFile.startsWith("TablaPosiciones")){
					
					contadorTablaPosiciones++;
				}
			}
		}
		
		return this.torneosList.size() > contadorTablaPosiciones;
	}

	private static void loadNextId() {
		
		File idTorneosFile = new File("./archivos/ID_TORNEOS.txt");
		Scanner torneosScanner;

		try {

			try {
				idTorneosFile.createNewFile();

			} catch (IOException e) {

				System.out.println("Se ha verificado un error al cargar el archivo de id.");
			}

			torneosScanner = new Scanner(idTorneosFile);

			nextIdTorneo = torneosScanner.nextInt();
			
			torneosScanner.close();

		}catch(InputMismatchException e){

			System.out.println("Se ha encontrado un tipo de dato insesperado.");

		}catch(FileNotFoundException e) {

			System.out.println("No se ha encontrado el archivo.");

		}catch(Exception e){

			e.printStackTrace();
		}

	}
	
	public static int getNextIdTorneo() {

		int idReturn = nextIdTorneo;
		nextIdTorneo++;

		return idReturn;
	}
	
	public static int getIdTorneoActual(){
		
		return nextIdTorneo;
	}
}
