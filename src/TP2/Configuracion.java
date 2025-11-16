package TP2;

public class Configuracion {

	public final int limiteInferiorVecinos;
	public final int limiteSuperiorVecinos;
	public final int limiteLazos;
	public final int limiteInferiorFuerzaLazos;
	public final int limiteSuperiorFuerzaLazos;
	public final String archivoEntrada;
	public final String archivoSalida;

	public Configuracion(int limiteInferiorVecinos, int limiteSuperiorVecinos, int limiteLazos,
			int limiteInferiorFuerzaLazos, int limiteSuperiorFuerzaLazos, String directorioBase, String archivoEntrada,
			String archivoSalida) {
		if(limiteLazos<1)
        	throw new IllegalArgumentException("Debe haber al menos un lazo");
        if(limiteInferiorFuerzaLazos<1)
        	throw new IllegalArgumentException("La fuerza minima de un lazo debe ser 1");
        if(limiteInferiorFuerzaLazos>=limiteSuperiorFuerzaLazos)
        	throw new IllegalArgumentException("El limite inferior de fuerza de los lazos"
        			+ " no puede ser igual o mayor al superior");
        if(limiteInferiorVecinos>=limiteSuperiorVecinos)
        	throw new IllegalArgumentException("El limite inferior de fuerza de la cantidad"
        			+ " de vecinos no puede ser igual o mayor al superior");

		this.limiteInferiorVecinos = limiteInferiorVecinos;
		this.limiteSuperiorVecinos = limiteSuperiorVecinos;
		this.limiteLazos = limiteLazos;
		this.limiteInferiorFuerzaLazos = limiteInferiorFuerzaLazos;
		this.limiteSuperiorFuerzaLazos = limiteSuperiorFuerzaLazos;
		this.archivoEntrada = directorioBase + "/"+ archivoEntrada;
		this.archivoSalida = directorioBase + "/"+  archivoSalida;
		
		
	}
	
}
