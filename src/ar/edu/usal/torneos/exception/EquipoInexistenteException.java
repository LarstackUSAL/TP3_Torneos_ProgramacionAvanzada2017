package ar.edu.usal.torneos.exception;

public class EquipoInexistenteException extends Exception {

	private String nombreEquipo;
	
	public EquipoInexistenteException(String nombreEquipo){
		
		this.nombreEquipo = nombreEquipo;
	}
	
	@Override
	public void printStackTrace()
	{
		System.out.println("El equipo " + nombreEquipo + " es inexistente.");
	}
}
