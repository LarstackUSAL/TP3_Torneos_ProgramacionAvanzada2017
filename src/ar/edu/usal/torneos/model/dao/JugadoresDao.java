package ar.edu.usal.torneos.model.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

import ar.edu.usal.torneos.model.dto.Jugadores;

public class JugadoresDao {

	private static JugadoresDao jugadoresDaoInstance = null;

	private ArrayList<Jugadores> jugadores;

	private JugadoresDao(){

		this.jugadores = new ArrayList<Jugadores>();
		this.loadJugadores();
	}

	public static JugadoresDao getInstance(){

		if(jugadoresDaoInstance==null){

			jugadoresDaoInstance  = new JugadoresDao();
		}

		return jugadoresDaoInstance;
	}

	private void loadJugadores(){

		Scanner jugadoresScanner;

		File directoryArchivos = new File("./archivos/");
		File[] archivosArray = directoryArchivos.listFiles();

		for (int i = 0; i < archivosArray.length; i++) {

			File file = archivosArray[i];

			if (file.isFile()){

				String nombreFile = file.getName();
				if (nombreFile.endsWith(".txt") && nombreFile.startsWith("EQUIPO")){

					try {

						jugadoresScanner = new Scanner(file);

						archivo:
							while(jugadoresScanner.hasNextLine()){

								String linea = jugadoresScanner.nextLine().trim();

								if(!linea.startsWith("Equipo:")){

									String[] datosJugador = linea.split(";");

									int cuil = Integer.valueOf(datosJugador[0].trim());
									String apellido = datosJugador[1].trim();
									String nombre = datosJugador[2].trim();

									Jugadores jugador = new Jugadores(nombre, apellido, cuil);

									if(!this.jugadores.contains(jugador)){

										this.jugadores.add(jugador);
									}									
								}
							}

						jugadoresScanner.close();

					}catch(InputMismatchException e){

						System.out.println("Se ha encontrado un tipo de dato insesperado.");

					}catch (FileNotFoundException e) {

						System.out.println("No se ha encontrado el archivo.");
					}catch(StringIndexOutOfBoundsException e){

						System.out.println("Se ha verificado un error en el parseo del archivo de jugadores.");

					}catch(Exception e){

						System.out.println("Se ha verificado un error inesperado.");
					}
				}
			}
		}
	}

	public Jugadores getJugadorByCuil(int cuil) {

		Iterator jugadoresIterator = this.jugadores.iterator();
		
		while (jugadoresIterator.hasNext()) {
			
			Jugadores jugador = (Jugadores) jugadoresIterator.next();
			
			if(jugador.getCuil() == cuil){
				
				return jugador;
			}
		}
		
		return null;
	}
}
