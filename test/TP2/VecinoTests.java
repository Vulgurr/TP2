package TP2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class VecinoTests {

	@Test
	void mismoLazoSeActualiza() {
		Vecino pepe = new Vecino(1);

		pepe.agregarAmistad(3, 10);

		int expectedAmistad = 10;
		int receivedAmistad = pepe.nivelDeAmistadCon(3);
		assertEquals(expectedAmistad, receivedAmistad,
				"Expected nivel de amistad: " + expectedAmistad + ", but received: " + receivedAmistad);

		pepe.agregarAmistad(3, 20);
		expectedAmistad = 20;
		receivedAmistad = pepe.nivelDeAmistadCon(3);
		assertEquals(expectedAmistad, receivedAmistad,
				"Expected nivel de amistad actualizado: " + expectedAmistad + ", but received: " + receivedAmistad);
	}

	@Test
	void lazoNoExisteDevuelveCero() {
		Vecino pepe = new Vecino(1);
		int expectedAmistad = 0;
		int receivedAmistad = pepe.nivelDeAmistadCon(3);
		assertEquals(expectedAmistad, receivedAmistad,
				"Expected nivel de amistad: " + expectedAmistad + ", but received: " + receivedAmistad);
	}

}
