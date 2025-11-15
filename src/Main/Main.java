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
			archivos.exportarResultado(vecinos.determinarAliados());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
