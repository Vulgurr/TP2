package TP2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class GestorArchivos {
	int cantidadVecinos;
	int cantidadLazos;
	Configuracion config;
	Verificacion verificacion;
	
	public GestorArchivos(String path) throws IOException {
		Configuracion aux;
		if((aux=leerConfiguracion(path))!=null)
		{
			config=aux;
		}
		else throw new IOException("Error en la lectura de la configuracion");
		verificacion=new Verificacion(config);
	};
	
	public Configuracion leerConfiguracion(String path)
	{
		String directorioBase=path;
		String directorioConfig=path+ "/config.txt";
		try (BufferedReader br = new BufferedReader(new FileReader(directorioConfig))) {
	        String linea;
	        int limiteInferiorVecinos;
	        int limiteSuperiorVecinos;
	        int limiteLazos;
	        int limiteInferiorFuerzaLazos;
	        int limiteSuperiorFuerzaLazos;
	        String archivoEntrada;
	        String archivoSalida;

	        linea = br.readLine();
	        limiteInferiorVecinos = Integer.parseInt((linea.split("="))[1]);
	        linea = br.readLine();
	        limiteSuperiorVecinos = Integer.parseInt((linea.split("="))[1]);
	        linea = br.readLine();
	        limiteLazos = Integer.parseInt((linea.split("="))[1]);
	        linea = br.readLine();
	        limiteInferiorFuerzaLazos = Integer.parseInt((linea.split("="))[1]);
	        linea = br.readLine();
	        limiteSuperiorFuerzaLazos = Integer.parseInt((linea.split("="))[1]);
	        linea = br.readLine();
	        archivoEntrada=linea.split("=")[1].replace("\"", "");
	        linea = br.readLine();
	        archivoSalida=linea.split("=")[1].replace("\"", "");

	        return new Configuracion(limiteInferiorVecinos, limiteSuperiorVecinos, 
	        		limiteLazos, limiteInferiorFuerzaLazos, limiteSuperiorFuerzaLazos, 
	        		directorioBase, archivoEntrada, archivoSalida);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return null;
	}
	public int[] leerPrimeraLinea()
	{
		int[] valores = new int[4];
		int[] vecinos = new int[2];
	    try (BufferedReader br = new BufferedReader(new FileReader(config.archivoEntrada))) {
	        String linea;
	        
	        linea = br.readLine();
	        valores = Arrays.stream(linea.split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
	     // ---------------------------------------
	     verificacion.primeraLinea(valores);
	     // ---------------------------------------
	        cantidadVecinos=valores[0];
	        cantidadLazos=valores[1];
	        vecinos[0]=valores[2];
	        vecinos[1]=valores[3];
	    }
	    catch (IOException e) {
	    	 // ---------------------------------------
		     // Manejar tipos de excepciones y mensajes
		     // ---------------------------------------
	        e.printStackTrace();
	    }
	    return vecinos;
	}
	public void leerArchivoConGestor(GestorVecinos gestor)
	{
		try (BufferedReader br = new BufferedReader(new FileReader(config.archivoEntrada))) {
		String linea;
	        int cont=2;
	        int contLazos=0;
	        linea = br.readLine(); //Para saltar la primera linea
	        int[] valores = new int[3];
	        Set<Integer> totalVecinos = new HashSet<Integer>();
	        while ((linea = br.readLine()) != null) {
	        	
	        	valores = Arrays.stream(linea.split(" "))
	                    .mapToInt(Integer::parseInt)
	                    .toArray();
	        	 // ---------------------------------------
		   	     verificacion.enLinea(valores, cont);
		   	     // ---------------------------------------
	        	gestor.agregarAmistades(valores[0], valores[1], valores[2]);
	        	totalVecinos.add(valores[0]);
	        	totalVecinos.add(valores[1]);
	        	cont++;
	        	contLazos++;
	        }

	        // ---------------------------------------
	        verificacion.finalDeLectura(contLazos, cantidadLazos, totalVecinos.size(), cantidadVecinos);
	        
		     // ---------------------------------------
	    } catch (IOException e) {
	    	 // ---------------------------------------
		     // Manejar tipos de excepciones y mensajes
		     // ---------------------------------------
	        e.printStackTrace();
	    }
	    
	}
	
	public void exportarResultado(int[] aliados)
	{
		String linea=aliados[0] + " " + aliados[1];
        try {
            Files.write(Paths.get(config.archivoSalida), Arrays.asList(linea));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
