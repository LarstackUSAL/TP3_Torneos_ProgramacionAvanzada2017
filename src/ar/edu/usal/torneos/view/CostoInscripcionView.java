package ar.edu.usal.torneos.view;

import ar.edu.usal.torneos.utils.Validador;

public class CostoInscripcionView {

	public CostoInscripcionView(){}

	public double ingresarCostoInscripcion() {
		
		double costoInscripcion = Validador.insertDouble("Ingresar costo de la inscripcion: ", true);
		
		return costoInscripcion;
	}

	public void registracionCostoSuccess() {
		
		System.out.println("Se ha registrado el costo de inscripcion del nuevo torneo con exito!");
	}

	public void registracionCostoFailed() {
		
		System.out.println("Se ha verificado un error al registrar el costo de inscripcion del nuevo torneo.");
	}
}
