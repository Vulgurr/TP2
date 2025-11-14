package TP2;

public class Registro {
	private final int vecino1;
	private final int vecino2;
	private final int valor;
	public Registro(int vecino1, int vecino2, int valor) {
		this.vecino1 = vecino1;
		this.vecino2 = vecino2;
		this.valor = valor;
	}
	public int getVecino1() {
		return vecino1;
	}
	public int getVecino2() {
		return vecino2;
	}
	public int getValor() {
		return valor;
	}
	
	
}
