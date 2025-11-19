package Main;

import java.io.IOException;

import TP2.GestorArchivos;
import TP2.GestorVecinos;


public class Main {

	public static void main(String[] args) {

		GestorArchivos g_archivos;
		GestorVecinos g_vecinos;
		
		try {
			// PASO 1: Lee configuración del archivo config.txt
			// GestorArchivos se encarga de leer los límites y rutas de archivos
			g_archivos = new GestorArchivos("./archivos");
			
			// PASO 2: Lee primera línea del archivo de entrada
			// Obtiene los dos líderes enfrentados y valida el formato
			int[] vecinosEnfrentados = g_archivos.leerPrimeraLinea();
			
			// PASO 3: Inicializa GestorVecinos con los dos líderes
			// Crea los objetos líder y prepara la estructura para procesar lazos
			g_vecinos = new GestorVecinos(vecinosEnfrentados[0], vecinosEnfrentados[1]);
			
			// PASO 4: Procesa todos los lazos del archivo
			// Lee línea por línea, valida cada lazo, filtra los irrelevantes
			// y agrega las amistades a los líderes correspondientes
			g_archivos.leerArchivoConGestor(g_vecinos);
			
			// PASO 5: Determina aliados de cada líder
			// Compara la fuerza de amistad de cada vecino con ambos líderes
			// Un vecino es aliado del líder con mayor fuerza de amistad
			int[] resultadosFinales = g_vecinos.determinarAliados();
			
			// PASO 6: Exporta resultados al archivo de salida
			// Escribe el archivo .OUT con la cantidad de aliados de cada líder
			g_archivos.exportarResultado(g_vecinos.determinarAliados());
			System.out.println("Se grabo correctamente el archivo de salida.");
			
			// PASO 7: Muestra resultado final en consola
			// Presenta de forma legible cuántos aliados tiene cada líder
			enumerarAliados(g_vecinos, resultadosFinales);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	private static void enumerarAliados(GestorVecinos vecinos, int[] aliados) {
		int aliadosLider1 = aliados[0];
		int aliadosLider2 = aliados[1];
		int numeroLider1 = vecinos.getNumeroLider1();
		int numeroLider2 = vecinos.getNumeroLider2();
		
		System.out.println("\n=== Aliados de los Líderes ===");
		
		// Líder 1
		System.out.print("Líder " + numeroLider1 + ": ");
		if (aliadosLider1 == 0) {
			System.out.println("No tiene aliados.");
		} else if (aliadosLider1 == 1) {
			System.out.println("Tiene 1 aliado.");
		} else {
			System.out.println("Tiene " + aliadosLider1 + " aliados.");
		}
		
		// Líder 2
		System.out.print("Líder " + numeroLider2 + ": ");
		if (aliadosLider2 == 0) {
			System.out.println("No tiene aliados.");
		} else if (aliadosLider2 == 1) {
			System.out.println("Tiene 1 aliado.");
		} else {
			System.out.println("Tiene " + aliadosLider2 + " aliados.");
		}
	}
	static String mostrarResultados(int[] vec)
	{
		String res="";
		if(vec[0]==0)
			res+="No hubo aliados para el lider 1 ";
		else if(vec[0]==1)
			res+="Hubo un aliado para el lider 1 ";
		else
			res+="Hubo "+vec[0]+" aliados para el lider 1 ";
		res+="y ";
		if(vec[1]==0)
			res+="No hubo aliados para el lider 2 ";
		else if(vec[1]==1)
			res+="Hubo un aliado para el lider 2 ";
		else
			res+="Hubo "+vec[1]+" aliados para el lider 2 ";
		return res;		
	}

}
