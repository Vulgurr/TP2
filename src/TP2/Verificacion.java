package TP2;

public class Verificacion {
	Configuracion conf;
	public Verificacion(Configuracion config)
	{
		conf=config;
	}
	public boolean primeraLinea(int[] valores)
	{
		if ((valores.length != 4 || ! vecinoEnRango(valores[0]) 
			|| valores[1]>conf.limiteLazos))
		{
			throw new IllegalArgumentException("Primera linea contiene errores");
		}
		return true;
	}
	
	
	public boolean enLinea(int[] valores, int cont)
	{
		if(valores.length == 3 &&vecinoEnRango(valores[0])  && vecinoEnRango(valores[1]) 
				&& lazoEnRango(valores[2]))
		{
			return true;
		}
		throw new IllegalArgumentException("La linea "+cont +" contiene errores");
	}
	
	public boolean finalDeLectura(int contLazos, int cantidadLazos, int contVecinos, int cantidadVecinos)
	{
		
		if(contLazos != cantidadLazos || cantidadVecinos != contVecinos)
        {
        	throw new IllegalArgumentException("No se cumplieron los lazos y cantidad de vecinos establecidos");
        }
		return true;
	}
	
	private boolean vecinoEnRango(int num)
	{
		return num>=conf.limiteInferiorVecinos && num<=conf.limiteSuperiorVecinos;
	}
	private boolean lazoEnRango(int num)
	{
		return num>=conf.limiteInferiorFuerzaLazos && num<=conf.limiteSuperiorFuerzaLazos;
	}
}
