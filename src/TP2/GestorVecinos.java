package TP2;

import java.util.HashSet;
import java.util.Set;

public class GestorVecinos {
	Vecino lider1; //2 5 9
	Vecino lider2; // 3 5 8
	Set<Integer> totalVecinosAConsiderar; // 2 3 5 8 9

	public GestorVecinos(int lider1, int lider2) {
		this.lider1 = new Vecino(lider1);
		this.lider2 = new Vecino(lider2);
		totalVecinosAConsiderar = new HashSet<Integer>();
	}

	public void agregarAmistades(int numero1, int numero2, int valor) {
		int idLider1 = lider1.getNumero();
		int idLider2 = lider2.getNumero();
		if (numero1 != idLider1 && numero2 != idLider1 && numero1 != idLider2 && numero2 != idLider2) {// No me interesa
																										// una amistad
																										// si no es
																										// entre los dos
																										// vecinos
			return;
		}
		if (numero1 == idLider2)
			lider2.agregarAmistad(numero2, valor);
		else if (numero2 == idLider2)
			lider2.agregarAmistad(numero1, valor);
		else if (numero1 == idLider1)
			lider1.agregarAmistad(numero2, valor);
		else if (numero2 == idLider1)
			lider1.agregarAmistad(numero1, valor);
		totalVecinosAConsiderar.add(numero1);
		totalVecinosAConsiderar.add(numero2);
	}

	public int[] determinarAliados() {
		int[] aliados = new int[2];
		for (Integer vecino : totalVecinosAConsiderar) {
			int amistad1 = lider1.nivelDeAmistadCon(vecino);
			int amistad2 = lider2.nivelDeAmistadCon(vecino);
			if (amistad1 > amistad2)
				aliados[0]++;
			else if (amistad2 > amistad1)
				aliados[1]++;
		}
		return aliados;
	}

	public int getCantidadVecinos() {
		return totalVecinosAConsiderar.size() - 1;
	}
}
