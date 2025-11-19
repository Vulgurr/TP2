package Main;

import java.io.IOException;

import TP2.GestorArchivos;
import TP2.GestorVecinos;


public class Main {

	public static void main(String[] args) {

		GestorArchivos archivos;
		GestorVecinos vecinos;
		try {
			archivos = new GestorArchivos("./archivos");
			int[] vecinosEnfrentados = archivos.leerPrimeraLinea();
			vecinos = new GestorVecinos(vecinosEnfrentados[0], vecinosEnfrentados[1]);
			archivos.leerArchivoConGestor(vecinos);
			int[] resultadosFinales=vecinos.determinarAliados();
			archivos.exportarResultado(vecinos.determinarAliados());
			System.out.println("Fueron grabados en el archivo de salida los siguientes valores");
			//System.out.println(mostrarResultados(resultadosFinales));
			enumerarAliados(vecinos, resultadosFinales);
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
