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
	private int cantidadVecinos;
	private int cantidadLazos;
	private Configuracion config;
	private Verificacion verificacion;

	public GestorArchivos(String path) throws IOException {
		config = leerConfiguracion(path);
	    if (config == null) {
	        throw new IOException("Error en la lectura de la configuracion");
	    }
		verificacion = new Verificacion(config);
	};

	public Configuracion leerConfiguracion(String path) {
		String directorioBase = path;
		String directorioConfig = path + "/config.txt";
		
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
			archivoEntrada = linea.split("=")[1].replace("\"", "");
			linea = br.readLine();
			archivoSalida = linea.split("=")[1].replace("\"", "");

			return new Configuracion(limiteInferiorVecinos, limiteSuperiorVecinos, limiteLazos,
					limiteInferiorFuerzaLazos, limiteSuperiorFuerzaLazos, directorioBase, archivoEntrada,
					archivoSalida);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int[] leerPrimeraLinea() {
		int[] datosPrimeraLinea = new int[4];
		int[] lideres = new int[2];
		try (BufferedReader br = new BufferedReader(new FileReader(config.archivoEntrada))) {
			String linea;

			linea = br.readLine();
			// Ignorar líneas vacías hasta encontrar una línea válida
			while (linea != null && linea.trim().isEmpty()) {
				linea = br.readLine();
			}
			if (linea == null) {
				throw new IllegalArgumentException("El archivo está vacío");
			}
			try {
				datosPrimeraLinea = Arrays.stream(linea.split(" ")).mapToInt(Integer::parseInt).toArray();
				// ---------------------------------------
				verificacion.primeraLinea(datosPrimeraLinea);
				// ---------------------------------------
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException("La primera línea no es válida: " + e.getMessage());
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException("La primera línea no es válida: " + e.getMessage());
			}
			cantidadVecinos = datosPrimeraLinea[0];
			cantidadLazos = datosPrimeraLinea[1];
			lideres[0] = datosPrimeraLinea[2];
			lideres[1] = datosPrimeraLinea[3];
		} catch (IOException e) {
			// ---------------------------------------
			// Manejar tipos de excepciones y mensajes
			// ---------------------------------------
			e.printStackTrace();
		}
		return lideres;
	}

	public void leerArchivoConGestor(GestorVecinos gestor) {
		
		try (BufferedReader br = new BufferedReader(new FileReader(config.archivoEntrada))) {
			
			String linea;
			int cont = 2;
			int contLazos = 0;
			linea = br.readLine(); // Para saltar la primera linea
			int[] datosLazo = new int[3];
			
			Set<Integer> totalVecinos = new HashSet<Integer>();
			
			while ((linea = br.readLine()) != null) {
				// Ignorar líneas vacías o que solo contengan espacios en blanco
				if (linea.trim().isEmpty()) {
					cont++;
					continue;
				}

				datosLazo = Arrays.stream(linea.split(" ")).mapToInt(Integer::parseInt).toArray();
				
				// ---------------------------------------
				verificacion.enLinea(datosLazo, cont);				
				// ---------------------------------------
				
				gestor.agregarAmistades(datosLazo[0], datosLazo[1], datosLazo[2]);
				
				totalVecinos.add(datosLazo[0]);
				totalVecinos.add(datosLazo[1]);
				
				cont++;
				contLazos++;
			}

			// ---------------------------------------
			verificacion.finalDeLectura(contLazos, cantidadLazos, totalVecinos.size(), cantidadVecinos);
			// ---------------------------------------
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void exportarResultado(int[] aliados) {
		String linea = aliados[0] + " " + aliados[1];
		try {
			Files.write(Paths.get(config.archivoSalida), Arrays.asList(linea));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
