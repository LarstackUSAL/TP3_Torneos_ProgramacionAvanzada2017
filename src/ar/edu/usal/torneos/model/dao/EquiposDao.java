package ar.edu.usal.torneos.model.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

import ar.edu.usal.torneos.exception.EquipoInexistenteException;
import ar.edu.usal.torneos.model.dto.Equipos;
import ar.edu.usal.torneos.model.dto.Jugadores;
import ar.edu.usal.torneos.model.interfaces.ITorneoEmpresa;

public class EquiposDao {

	private static EquiposDao equiposDaoInstance = null;

	private ArrayList<Equipos> equipos;
	private HashMap<Integer,ArrayList<Equipos>> equiposTorneosMap;

	private EquiposDao(){

		this.equipos = new ArrayList<Equipos>();
		this.equiposTorneosMap = new HashMap<Integer,ArrayList<Equipos>>();
		this.loadEquiposTorneoActual();
		this.loadTorneosEquipos();
	}

	public static EquiposDao getInstance(){

		if(equiposDaoInstance==null){

			equiposDaoInstance  = new EquiposDao();
		}

		return equiposDaoInstance;
	}

	private void loadEquiposTorneoActual(){

		Scanner equiposScanner;

		File directoryArchivos = new File("./archivos/");
		File[] archivosArray = directoryArchivos.listFiles();

		for (int i = 0; i < archivosArray.length; i++) {

			File file = archivosArray[i];

			String nombreEquipo;

			if (file.isFile()){

				String nombreFile = file.getName();
				if (nombreFile.endsWith(".txt") && nombreFile.startsWith("EQUIPO")){

					Equipos equipo = new Equipos();
					try {

						equiposScanner = new Scanner(file);

						ArrayList<Jugadores> jugadoresEquipo = new ArrayList<Jugadores>();
						equipo.setJugadores(jugadoresEquipo);

						archivo:
							while(equiposScanner.hasNextLine()){

								String linea = equiposScanner.nextLine().trim();

								if(linea.startsWith("Equipo:")){

									String[] lineaEquipo = linea.split(":");								
									nombreEquipo = lineaEquipo[1].trim();

									equipo.setNombre(nombreEquipo);								

									if(this.equipos.contains(nombreEquipo)){

										System.out.println("El archivo es rechazado. El nombre " + nombreEquipo + " ya existe.");
										equipo = null;
										break;
									}
								}else{

									if(jugadoresEquipo.size() < ITorneoEmpresa.MAX_JUGADORES_EQUIPO){

										String[] datosJugador = linea.split(";");

										int cuil = Integer.valueOf(datosJugador[0].trim());
										String apellido = datosJugador[1].trim();
										String nombre = datosJugador[2].trim();

										Jugadores jugador = new Jugadores(nombre, apellido, cuil);

										for (int j = 0; j < this.equipos.size(); j++) {

											Equipos equipoTmp = this.equipos.get(j);

											if(equipoTmp.getJugadores().contains(jugador)){

												System.out.println("El archivo es rechazado. El jugador con cuil " + cuil + " ya juega para otro equipo.");
												equipo = null;
												break archivo;
											}
										}

										jugadoresEquipo.add(jugador);
									}
								}
							}

						if(equipo != null) this.equipos.add(equipo);

						equiposScanner.close();

					}catch(InputMismatchException e){

						System.out.println("Se ha encontrado un tipo de dato insesperado.");

					}catch (FileNotFoundException e) {

						System.out.println("No se ha encontrado el archivo.");
					}catch(StringIndexOutOfBoundsException e){

						System.out.println("Se ha verificado un error en el parseo del archivo de equipos.");

					}catch(Exception e){

						System.out.println("Se ha verificado un error inesperado.");
					}
				}
			}
		}
	}

	private void loadTorneosEquipos() {

		Scanner torneosEquiposScanner;

		File torneosEquiposFile = new File("./archivos/TorneosEquipos.txt");

		try {

			torneosEquiposScanner = new Scanner(torneosEquiposFile);

			while(torneosEquiposScanner.hasNextLine()){

				ArrayList<Equipos> equiposTorneoList = new ArrayList<Equipos>();

				String linea = torneosEquiposScanner.nextLine().trim();

				String[] lineaTorneo = linea.split(";");								
				int idTorneo = Integer.valueOf(lineaTorneo[0].trim());

				for (int j = 1; j < lineaTorneo.length; j++) {

					String nombreEquipo = lineaTorneo[j].trim();
					
					try{
					
						Equipos equipo = null;
						
						if(idTorneo == TorneosDao.getIdTorneoActual()){
							
							equipo = this.getEquipoByName(nombreEquipo);
						}else{
							
							equipo = new Equipos();
							equipo.setNombre(nombreEquipo);
						}
						
						equiposTorneoList.add(equipo);
					
					}catch(EquipoInexistenteException e){

						e.printStackTrace();

					}
				}
				
				this.equiposTorneosMap.put(idTorneo, equiposTorneoList);
			}

			torneosEquiposScanner.close();

		}catch(InputMismatchException e){

			System.out.println("Se ha encontrado un tipo de dato insesperado.");

		}catch (FileNotFoundException e) {

			System.out.println("No se ha encontrado el archivo.");
		}catch(Exception e){

			System.out.println("Se ha verificado un error inesperado.");
		}
	}

	public Equipos getEquipoByName(String nombreEquipo) throws EquipoInexistenteException {
		
		for (int i = 0; i < this.equipos.size(); i++) {
			
			if(this.equipos.get(i).getNombre().equals(nombreEquipo)){
				
				return this.equipos.get(i);
			}
		}
		
		throw new EquipoInexistenteException(nombreEquipo);
	}

	public ArrayList<Equipos> getEquiposTorneoById(int id) {

		return equiposTorneosMap.get(id);
	}

	public HashMap<Integer,ArrayList<Equipos>> getEquiposTorneosMap() {
		return equiposTorneosMap;
	}

	public void setEquiposTorneosMap(HashMap<Integer,ArrayList<Equipos>> equiposTorneosMap) {
		this.equiposTorneosMap = equiposTorneosMap;
	}

	public ArrayList<Equipos> getEquipos() {
		return equipos;
	}

	public void setEquipos(ArrayList<Equipos> equipos) {
		this.equipos = equipos;
	}

	public void addTorneoArchivoTorneosEquipos(int id, ArrayList<Equipos> equiposArray) throws IOException {
		
		FileWriter torneosEquiposFile = new FileWriter("./archivos/TorneosEquipos.txt", true);
		PrintWriter torneosEquiposOut = new PrintWriter(torneosEquiposFile);

		Iterator equiposIterator = equiposArray.iterator();
		
		String stringPrint = String.valueOf(id);
		
		while (equiposIterator.hasNext()) {
			
			Equipos equipo = (Equipos)equiposIterator.next();
			
			stringPrint = stringPrint + ";" + equipo.getNombre().trim();
		}

		torneosEquiposOut.println(stringPrint);
		torneosEquiposOut.close();
	}
}
