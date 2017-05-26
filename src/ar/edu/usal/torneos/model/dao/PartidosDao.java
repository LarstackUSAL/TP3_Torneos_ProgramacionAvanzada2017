package ar.edu.usal.torneos.model.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import ar.edu.usal.torneos.model.dto.Equipos;
import ar.edu.usal.torneos.utils.Validador;

public class PartidosDao {

	private static PartidosDao partidosDaoInstance = null;

	private ArrayList<HashMap<String, Object>> datosPartidosTorneos;

	private PartidosDao(){

		this.datosPartidosTorneos = new ArrayList<HashMap<String, Object>>();
		this.loadPartidos();
	}

	public static PartidosDao getInstance(){

		if(partidosDaoInstance == null){

			partidosDaoInstance  = new PartidosDao();
		}

		return partidosDaoInstance;
	}
	
	private void loadPartidos() {
		
		EquiposDao equiposDao = EquiposDao.getInstance();
		
		File resultadosFile = new File("./archivos/Resultados.txt");
		Scanner resultadosScanner;
		
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
				datosPartidoMap.put("idTorneo", idTorneo);
				
				this.datosPartidosTorneos.add(datosPartidoMap);
			}

			resultadosScanner.close();
			
		}catch(InputMismatchException e){
			
			System.out.println("Se ha encontrado un tipo de dato insesperado.");
			
		}catch (FileNotFoundException e) {
			
			System.out.println("No se ha encontrado el archivo.");
		}catch(Exception e){
			
			System.out.println("Se ha verificado un error inesperado.");
		}		
	}
	
	public ArrayList<HashMap<String, Object>> getDatosPartidosTorneos() {
		return datosPartidosTorneos;
	}

	public void setDatosPartidosTorneos(
			ArrayList<HashMap<String, Object>> datosPartidosTorneos) {
		this.datosPartidosTorneos = datosPartidosTorneos;
	}
}
