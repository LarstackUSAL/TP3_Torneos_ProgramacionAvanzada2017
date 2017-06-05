package ar.edu.usal.torneos.exception;

public class HayTorneoEnCursoException extends Exception {
	
	public HayTorneoEnCursoException(){}
	
	@Override
	public void printStackTrace()
	{
		System.out.println("Los torneos son anuales. Ya se encuentra uno en curso.");
	}
}
