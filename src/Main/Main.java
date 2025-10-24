package Main;

import java.io.IOException;

import TP2.GestorArchivos;


public class Main {

	public static void main(String[] args) {
//		GestorVecinos gestor=new GestorVecinos(1,5);
//		gestor.agregarAmistades(1, 2, 29);
//		gestor.agregarAmistades(2, 5, 43);
//		gestor.agregarAmistades(3, 1, 12);
//		gestor.agregarAmistades(2, 3, 9);
//		gestor.agregarAmistades(4, 5, 6);
//		gestor.agregarAmistades(1, 4, 6);
//		gestor.agregarAmistades(3, 5, 7);
//		gestor.agregarAmistades(4, 6, 78);
//		gestor.agregarAmistades(3, 7, 98);
//		gestor.agregarAmistades(6, 1, 2);
//		int[] amistades = gestor.determinarAliados();
//		System.out.println(amistades[0] + " " + amistades[1]);
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
