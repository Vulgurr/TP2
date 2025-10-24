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

		this.limiteInferiorVecinos = limiteInferiorVecinos;
		this.limiteSuperiorVecinos = limiteSuperiorVecinos;
		this.limiteLazos = limiteLazos;
		this.limiteInferiorFuerzaLazos = limiteInferiorFuerzaLazos;
		this.limiteSuperiorFuerzaLazos = limiteSuperiorFuerzaLazos;
		this.archivoEntrada = directorioBase + "/"+ archivoEntrada;
		this.archivoSalida = directorioBase + "/"+  archivoSalida;
	}
	
	public boolean verificarValores(int vecino1, int vecino2, int valor)
	{
		return (vecinoEnRango(vecino1) && vecinoEnRango(vecino2) && lazoEnRango(valor));
	}
	private boolean vecinoEnRango(int num)
	{
		return num>=limiteInferiorVecinos && num<=limiteSuperiorVecinos;
	}
	private boolean lazoEnRango(int num)
	{
		return num>=limiteInferiorFuerzaLazos && num<=limiteSuperiorFuerzaLazos;
	}
	
}
