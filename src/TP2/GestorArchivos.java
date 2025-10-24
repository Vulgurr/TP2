package TP2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class GestorArchivos {
	GestorVecinos gestor;
	int cantidadVecinos;
	int lazos;
	
	public GestorArchivos() {};
	
	public void leerArchivoEntrada()
	{
	    try (BufferedReader br = new BufferedReader(new FileReader("ALIADOS.IN"))) {
	        String linea;
	        
	        linea = br.readLine();
	        int[] valores = Arrays.stream(linea.split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
	        
	        gestor = new GestorVecinos(valores[2], valores[3]);
	        
	        while ((linea = br.readLine()) != null) {
	        	valores = Arrays.stream(linea.split(" "))
	                    .mapToInt(Integer::parseInt)
	                    .toArray();
	        	gestor.agregarAmistades(valores[0], valores[1], valores[2]);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	public void exportarResultado()
	{
		String[] lineas = Arrays.stream(gestor.determinarAliados())
                .mapToObj(String::valueOf)
                .toArray(String[]::new);
		int[] aliados = gestor.determinarAliados();
		String linea=aliados[0] + " " + aliados[1];
        try {
            Files.write(Paths.get("ALIADOS.OUT"), Arrays.asList(linea));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
