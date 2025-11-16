package TP2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class testGestorVecinos {

	@Test
	void vecinosNoRelevantes() {
		GestorVecinos gestor = new GestorVecinos(1, 2);

		gestor.agregarAmistades(1, 3, 10);
		gestor.agregarAmistades(2, 4, 5);
		gestor.agregarAmistades(5, 6, 8);

		int expectedCantidad = 2;
		int receivedCantidad = gestor.getCantidadVecinos();
		assertEquals(expectedCantidad, receivedCantidad,
				"Expected cantidad de vecinos: " + expectedCantidad + ", but received: " + receivedCantidad);
	}

	@Test
	void mismoLazoNoSeTi() {
		GestorVecinos gestor = new GestorVecinos(1, 2);

		gestor.agregarAmistades(1, 3, 10);
		gestor.agregarAmistades(1, 3, 10);

		int expectedAmistad = 10;
		int receivedAmistad = gestor.lider1.nivelDeAmistadCon(3);
		assertEquals(expectedAmistad, receivedAmistad,
				"Expected nivel de amistad: " + expectedAmistad + ", but received: " + receivedAmistad);

		gestor.agregarAmistades(1, 3, 20);
		expectedAmistad = 20;
		receivedAmistad = gestor.lider1.nivelDeAmistadCon(3);
		assertEquals(expectedAmistad, receivedAmistad,
				"Expected nivel de amistad actualizado: " + expectedAmistad + ", but received: " + receivedAmistad);
	}

	@Test
	void lideresNoPuedenSerIguales() {
		assertThrows(IllegalArgumentException.class, () -> {
			new GestorVecinos(1, 1);
		}, "Los dos líderes no pueden ser el mismo vecino");
	}

	@Test
	void lazoEntreLideresFalla() {
		GestorVecinos gestor = new GestorVecinos(1, 2);

		assertThrows(IllegalArgumentException.class, () -> {
			gestor.agregarAmistades(1, 2, 10);
		}, "Los dos vecinos enemistados no pueden ser amigos");
	}

	@Test
	void lazoPropioFalla() {
		GestorVecinos gestor = new GestorVecinos(1, 2);

		assertThrows(IllegalArgumentException.class, () -> {
			gestor.agregarAmistades(1, 1, 10);
		}, "Expected IllegalArgumentException, but received: no exception. Un vecino no puede ser amigo de sí mismo");

		assertThrows(IllegalArgumentException.class, () -> {
			gestor.agregarAmistades(2, 2, 10);
		}, "Expected IllegalArgumentException, but received: no exception. Un vecino no puede ser amigo de sí mismo");
	}

	@Test
	void determinarAliadosCorrectamente() {
		GestorVecinos gestor = new GestorVecinos(1, 2);

		gestor.agregarAmistades(1, 3, 20);
		gestor.agregarAmistades(2, 3, 10);
		gestor.agregarAmistades(1, 4, 5);
		gestor.agregarAmistades(2, 4, 15);

		int[] aliados = gestor.determinarAliados();

		int expectedAliadosLider1 = 1;
		int receivedAliadosLider1 = aliados[0];
		assertEquals(expectedAliadosLider1, receivedAliadosLider1,
				"Expected aliados del líder 1: " + expectedAliadosLider1 + ", but received: " + receivedAliadosLider1);

		int expectedAliadosLider2 = 1;
		int receivedAliadosLider2 = aliados[1];
		assertEquals(expectedAliadosLider2, receivedAliadosLider2,
				"Expected aliados del líder 2: " + expectedAliadosLider2 + ", but received: " + receivedAliadosLider2);
	}

	@Test
	void empateEnAmistadesNoCuenta() {
		GestorVecinos gestor = new GestorVecinos(1, 2);

		gestor.agregarAmistades(1, 3, 10);
		gestor.agregarAmistades(2, 3, 10);
		gestor.agregarAmistades(1, 4, 20);
		gestor.agregarAmistades(2, 4, 5);

		int[] aliados = gestor.determinarAliados();

		int expectedAliadosLider1 = 1;
		int receivedAliadosLider1 = aliados[0];
		assertEquals(expectedAliadosLider1, receivedAliadosLider1,
				"Expected aliados del líder 1: " + expectedAliadosLider1 + ", but received: " + receivedAliadosLider1);

		int expectedAliadosLider2 = 0;
		int receivedAliadosLider2 = aliados[1];
		assertEquals(expectedAliadosLider2, receivedAliadosLider2,
				"Expected aliados del líder 2: " + expectedAliadosLider2 + ", but received: " + receivedAliadosLider2);
	}

	@Test
	void vecinoSoloConUnLider() {
		GestorVecinos gestor = new GestorVecinos(1, 2);

		gestor.agregarAmistades(1, 3, 10);
		gestor.agregarAmistades(2, 4, 15);

		int[] aliados = gestor.determinarAliados();

		int expectedAliadosLider1 = 1;
		int receivedAliadosLider1 = aliados[0];
		assertEquals(expectedAliadosLider1, receivedAliadosLider1,
				"Expected aliados del líder 1: " + expectedAliadosLider1 + ", but received: " + receivedAliadosLider1);

		int expectedAliadosLider2 = 1;
		int receivedAliadosLider2 = aliados[1];
		assertEquals(expectedAliadosLider2, receivedAliadosLider2,
				"Expected aliados del líder 2: " + expectedAliadosLider2 + ", but received: " + receivedAliadosLider2);
	}

	@Test
	void lideresNoSeCuentanEnAliados() {
		GestorVecinos gestor = new GestorVecinos(1, 2);

		gestor.agregarAmistades(1, 3, 10);
		gestor.agregarAmistades(2, 4, 5);

		int[] aliados = gestor.determinarAliados();

		int expectedAliadosLider1 = 1;
		int receivedAliadosLider1 = aliados[0];
		assertEquals(expectedAliadosLider1, receivedAliadosLider1,
				"Expected aliados del líder 1: " + expectedAliadosLider1 + ", but received: " + receivedAliadosLider1);

		int expectedAliadosLider2 = 1;
		int receivedAliadosLider2 = aliados[1];
		assertEquals(expectedAliadosLider2, receivedAliadosLider2,
				"Expected aliados del líder 2: " + expectedAliadosLider2 + ", but received: " + receivedAliadosLider2);
	}

	@Test
	void vecinoConAmistadCero() {
		GestorVecinos gestor = new GestorVecinos(1, 2);

		gestor.agregarAmistades(1, 3, 10);

		int[] aliados = gestor.determinarAliados();

		int expectedAliadosLider1 = 1;
		int receivedAliadosLider1 = aliados[0];
		assertEquals(expectedAliadosLider1, receivedAliadosLider1,
				"Expected aliados del líder 1: " + expectedAliadosLider1 + ", but received: " + receivedAliadosLider1);

		int expectedAliadosLider2 = 0;
		int receivedAliadosLider2 = aliados[1];
		assertEquals(expectedAliadosLider2, receivedAliadosLider2,
				"Expected aliados del líder 2: " + expectedAliadosLider2 + ", but received: " + receivedAliadosLider2);
	}

}