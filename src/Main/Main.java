package Main;

import java.io.IOException;

import TP2.GestorArchivos;


public class Main {

	public static void main(String[] args) {

		GestorArchivos archivos;
		try {
			archivos = new GestorArchivos("./archivos");
			archivos.leerArchivoEntrada();
			archivos.exportarResultado();
			System.out.println("Listo");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
