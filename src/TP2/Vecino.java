package TP2;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Vecino {
	private int numero;
	private Map<Integer, Integer> amistades= new HashMap<Integer, Integer>();
	public Vecino(int numero)
	{
		this.numero=numero;
	}
	public void agregarAmistad(int numero, int amistad)
	{
		amistades.put(numero, amistad);
	}
	public int nivelDeAmistadCon(Integer numero)
	{
		return amistades.getOrDefault(numero, 0);
	}
	@Override
	public int hashCode() {
		return Objects.hash(numero);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vecino other = (Vecino) obj;
		return numero == other.numero;
	}
	
	public int getNumero()
	{
		return this.numero;
	}
	
}
