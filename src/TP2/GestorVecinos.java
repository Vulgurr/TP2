package TP2;

import java.util.HashSet;
import java.util.Set;

public class GestorVecinos {
	private Vecino lider1; // 2 5 9
	private Vecino lider2; // 3 5 8
	private Set<Integer> totalVecinosAConsiderar; // 2 3 5 8 9

	public GestorVecinos(int lider1, int lider2) {
		if (lider1 == lider2)
			throw new IllegalArgumentException("Los dos lideres no pueden ser el mismo vecino");

		this.lider1 = new Vecino(lider1);
		this.lider2 = new Vecino(lider2);
		totalVecinosAConsiderar = new HashSet<Integer>();
	}

	private boolean verificarLazo(int numero1, int numero2) {
		if (numero1 != lider1.getNumero() && numero2 != lider1.getNumero() && numero1 != lider2.getNumero()
				&& numero2 != lider2.getNumero()) {
			// No me interesa una amistad si no es entre los dos vecinos enemistados
			return false;
		}
		if (numero1 == numero2)
			throw new IllegalArgumentException("Un vecino no puede ser amigo de sí mismo");
		if ((numero1 == lider1.getNumero() && numero2 == lider2.getNumero())
				|| (numero2 == lider1.getNumero() && numero1 == lider2.getNumero()))
			throw new IllegalArgumentException("Los dos vecinos enemistados no pueden ser amigos");

		return true;
	}

	public void agregarAmistades(int numero1, int numero2, int valor) {
		int idLider1 = lider1.getNumero();
		int idLider2 = lider2.getNumero();

		if (verificarLazo(numero1, numero2) == false)
			return;

		if (numero1 == idLider2)
			lider2.agregarAmistad(numero2, valor);
		else if (numero2 == idLider2)
			lider2.agregarAmistad(numero1, valor);
		else if (numero1 == idLider1)
			lider1.agregarAmistad(numero2, valor);
		else if (numero2 == idLider1)
			lider1.agregarAmistad(numero1, valor);
		
		// Solo agregar vecinos que no son líderes (los líderes no pueden ser aliados de sí mismos)
		if (numero1 != idLider1 && numero1 != idLider2)
			totalVecinosAConsiderar.add(numero1);
		if (numero2 != idLider1 && numero2 != idLider2)
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
		return totalVecinosAConsiderar.size();
	}

	public int getNumeroLider1() {
		return lider1.getNumero();
	}

	public int getNumeroLider2() {
		return lider2.getNumero();
	}

}
