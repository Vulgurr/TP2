package Main;

import java.io.IOException;

import TP2.GestorArchivos;
import TP2.GestorVecinos;
import TP2.Registro;


public class Main {

	public static void main(String[] args) {
		//Acordarse de separar incumbencias 
		//matriz int devuelve el archivo de entrada cargado en memoria
		/*
		 * se le manda un vector con la cantidad de aliados al gestor de vecinos
		 * el gestor lo procesa y despues en el main le pedis el determinarAliados, 
		 * el gestor de archivos lo exporta*/
		GestorArchivos archivos;
		GestorVecinos vecinos;
		try {
			archivos = new GestorArchivos("./archivos");
			int[] vecinosEnfrentados=archivos.leerPrimeraLinea();
			boolean lePasoGestor=true;
			vecinos=new GestorVecinos(vecinosEnfrentados[0], vecinosEnfrentados[1]);
			//Sin pasar el gestor como parámetro
			if(!lePasoGestor)
			{			
				System.out.println("Hola");
				Registro[] registrosLazosDeAmistad=archivos.leerArchivoSinGestor();
				vecinos.agregarAmistades(registrosLazosDeAmistad);
			}
			else
				//Le paso el gestor como parámetro
			{
				archivos.leerArchivoConGestor(vecinos);				
			}
			archivos.exportarResultado(vecinos.determinarAliados());
			System.out.println("Listo");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
