package TP2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class testConfiguracion {

	@Test
	void minimoVecinosMayorMaximoVecinos() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Configuracion(2, 1, 500, 1, 1, "", "", "");
		}, "Expected IllegalArgumentException, but received: no exception");
	}

	@Test
	void minimoLazosMayorOIgualMaximoLazos() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Configuracion(1, 100, 500, 1, 1, "", "", "");
		}, "Expected IllegalArgumentException, but received: no exception");
	}

	@Test
	void fuerzaLazosMenorA1() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Configuracion(1, 100, 500, -1, 1, "", "", "");
		}, "Expected IllegalArgumentException, but received: no exception");
	}

	@Test
	void limiteLazosMenorA1() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Configuracion(1, 100, -1, 1, 100, "", "", "");
		}, "Expected IllegalArgumentException, but received: no exception");
	}

}
