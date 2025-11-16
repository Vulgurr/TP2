package TP2;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class testGestorArchivos {

	@TempDir
	Path tempDir;
	
	private String directorioTest;
	
	@BeforeEach
	void setUp() throws IOException {
		directorioTest = tempDir.toString();
		crearConfigBasico();
	}
	
	private void crearConfigBasico() throws IOException {
		File configFile = new File(directorioTest, "config.txt");
		try (FileWriter writer = new FileWriter(configFile)) {
			writer.write("limiteInferiorVecinos=1\n");
			writer.write("limiteSuperiorVecinos=200\n");
			writer.write("limiteLazos=5000\n");
			writer.write("limiteInferiorFuerzaLazos=1\n");
			writer.write("limiteSuperiorFuerzaLazos=100\n");
			writer.write("archivoEntrada=\"test.in\"\n");
			writer.write("archivoSalida=\"test.out\"\n");
		}
	}
	
	private void crearArchivoEntrada(String contenido) throws IOException {
		File archivo = new File(directorioTest, "test.in");
		try (FileWriter writer = new FileWriter(archivo)) {
			writer.write(contenido);
		}
	}

	@Test
	void errorLecturaConfiguracion() throws IOException {
		Path directorioSinConfig = tempDir.resolve("directorio_sin_config");
		Files.createDirectories(directorioSinConfig);
		
		PrintStream originalErr = System.err;
		try {
			System.setErr(new PrintStream(new java.io.ByteArrayOutputStream()));
			
			IOException exception = assertThrows(IOException.class, () -> {
				new GestorArchivos(directorioSinConfig.toString());
			}, "Expected IOException, but received: no exception");
			
			String expectedMessage = "Error en la lectura de la configuracion";
			String receivedMessage = exception.getMessage();
			assertEquals(expectedMessage, receivedMessage, 
				"Expected exception message: \"" + expectedMessage + "\", but received: \"" + receivedMessage + "\"");
		} finally {
			System.setErr(originalErr);
		}
	}

	@Test
	void primeraLineaMalFormato() throws IOException {
		crearArchivoEntrada("5 3 2\n1 2 10\n");
		
		GestorArchivos gestor = new GestorArchivos(directorioTest);
		
		assertThrows(IllegalArgumentException.class, () -> {
			gestor.leerPrimeraLinea();
		});
	}

	@Test
	void lineaNMalFormato() throws IOException {
		crearArchivoEntrada("3 2 1 2\n1 2\n2 3 5\n");
		
		GestorArchivos gestor = new GestorArchivos(directorioTest);
		gestor.leerPrimeraLinea();
		GestorVecinos vecinos = new GestorVecinos(1, 2);
		
		assertThrows(IllegalArgumentException.class, () -> {
			gestor.leerArchivoConGestor(vecinos);
		});
	}

	@Test
	void cantidadDeLazosIncorrecta() throws IOException {
		crearArchivoEntrada("3 3 1 2\n1 2 10\n2 3 5\n");
		
		GestorArchivos gestor = new GestorArchivos(directorioTest);
		gestor.leerPrimeraLinea();
		GestorVecinos vecinos = new GestorVecinos(1, 2);
		
		assertThrows(IllegalArgumentException.class, () -> {
			gestor.leerArchivoConGestor(vecinos);
		});
	}

	@Test
	void cantidadDeVecinosIncorrecta() throws IOException {
		crearArchivoEntrada("4 2 1 2\n1 2 10\n2 3 5\n");
		
		GestorArchivos gestor = new GestorArchivos(directorioTest);
		gestor.leerPrimeraLinea();
		GestorVecinos vecinos = new GestorVecinos(1, 2);
		
		assertThrows(IllegalArgumentException.class, () -> {
			gestor.leerArchivoConGestor(vecinos);
		});
	}
	
	@Test
	void lazoPropioFalla() throws IOException {
		crearArchivoEntrada("2 2 1 2\n1 1 10\n1 2 5\n");
		
		GestorArchivos gestor = new GestorArchivos(directorioTest);
		gestor.leerPrimeraLinea();
		GestorVecinos vecinos = new GestorVecinos(1, 2);
		
		assertThrows(IllegalArgumentException.class, () -> {
			gestor.leerArchivoConGestor(vecinos);
		});
	}

	@Test
	void cargaDeArchivoExitoso() throws IOException {
		crearArchivoEntrada("4 3 1 2\n1 3 10\n2 3 5\n1 4 8\n");
		
		GestorArchivos gestor = new GestorArchivos(directorioTest);
		int[] lideres = gestor.leerPrimeraLinea();
		
		int expectedLider1 = 1;
		int receivedLider1 = lideres[0];
		assertEquals(expectedLider1, receivedLider1, 
			"Expected lider1: " + expectedLider1 + ", but received: " + receivedLider1);
		
		int expectedLider2 = 2;
		int receivedLider2 = lideres[1];
		assertEquals(expectedLider2, receivedLider2, 
			"Expected lider2: " + expectedLider2 + ", but received: " + receivedLider2);
		
		GestorVecinos vecinos = new GestorVecinos(lideres[0], lideres[1]);
		assertDoesNotThrow(() -> {
			gestor.leerArchivoConGestor(vecinos);
		}, "Expected: no exception, but received: exception thrown");
		
		int[] aliados = vecinos.determinarAliados();
		assertDoesNotThrow(() -> {
			gestor.exportarResultado(aliados);
		}, "Expected: no exception, but received: exception thrown");
		
		Path archivoSalida = Paths.get(directorioTest, "test.out");
		boolean expectedExiste = true;
		boolean receivedExiste = Files.exists(archivoSalida);
		assertEquals(expectedExiste, receivedExiste, 
			"Expected file exists: " + expectedExiste + ", but received: " + receivedExiste);
	}

	@Test
	void archivoVacio() throws IOException {
		crearArchivoEntrada("");
		
		GestorArchivos gestor = new GestorArchivos(directorioTest);
		
		assertThrows(Exception.class, () -> {
			gestor.leerPrimeraLinea();
		});
	}
	
	@Test
	void vecinoFueraDeRango() throws IOException {
		crearArchivoEntrada("3 2 1 2\n1 201 10\n2 3 5\n");
		
		GestorArchivos gestor = new GestorArchivos(directorioTest);
		gestor.leerPrimeraLinea();
		GestorVecinos vecinos = new GestorVecinos(1, 2);
		
		assertThrows(IllegalArgumentException.class, () -> {
			gestor.leerArchivoConGestor(vecinos);
		}, "Expected IllegalArgumentException for vecino fuera de rango, but received: no exception");
	}
	
	@Test
	void fuerzaLazoFueraDeRango() throws IOException {
		crearArchivoEntrada("3 2 1 2\n1 3 101\n2 3 5\n");
		
		GestorArchivos gestor = new GestorArchivos(directorioTest);
		gestor.leerPrimeraLinea();
		GestorVecinos vecinos = new GestorVecinos(1, 2);
		
		assertThrows(IllegalArgumentException.class, () -> {
			gestor.leerArchivoConGestor(vecinos);
		}, "Expected IllegalArgumentException for fuerza de lazo fuera de rango, but received: no exception");
	}
	
	@Test
	void primeraLineaVecinoFueraDeRango() throws IOException {
		crearArchivoEntrada("201 2 1 2\n1 3 10\n2 3 5\n");
		
		GestorArchivos gestor = new GestorArchivos(directorioTest);
		
		assertThrows(IllegalArgumentException.class, () -> {
			gestor.leerPrimeraLinea();
		}, "Expected IllegalArgumentException for vecino fuera de rango en primera línea, but received: no exception");
	}
	
	@Test
	void primeraLineaLazosExcedeLimite() throws IOException {
		crearArchivoEntrada("2 5001 1 2\n1 3 10\n");
		
		GestorArchivos gestor = new GestorArchivos(directorioTest);
		
		assertThrows(IllegalArgumentException.class, () -> {
			gestor.leerPrimeraLinea();
		}, "Expected IllegalArgumentException for lazos que exceden límite, but received: no exception");
	}
	
	@Test
	void verificarContenidoArchivoSalida() throws IOException {
		crearArchivoEntrada("4 4 1 2\n1 3 20\n2 3 10\n1 4 5\n2 4 15\n");
		
		GestorArchivos gestor = new GestorArchivos(directorioTest);
		int[] lideres = gestor.leerPrimeraLinea();
		GestorVecinos vecinos = new GestorVecinos(lideres[0], lideres[1]);
		gestor.leerArchivoConGestor(vecinos);
		
		int[] aliados = vecinos.determinarAliados();
		gestor.exportarResultado(aliados);
		
		Path archivoSalida = Paths.get(directorioTest, "test.out");
		String contenido = Files.readString(archivoSalida).trim();
		
		String expectedContenido = aliados[0] + " " + aliados[1];
		String receivedContenido = contenido;
		assertEquals(expectedContenido, receivedContenido, 
			"Expected file content: \"" + expectedContenido + "\", but received: \"" + receivedContenido + "\"");
	}
	
	@Test
	void archivoConLineasVacias() throws IOException {
		crearArchivoEntrada("3 2 1 2\n\n1 3 10\n");
		
		GestorArchivos gestor = new GestorArchivos(directorioTest);
		gestor.leerPrimeraLinea();
		GestorVecinos vecinos = new GestorVecinos(1, 2);
		
		assertThrows(IllegalArgumentException.class, () -> {
			gestor.leerArchivoConGestor(vecinos);
		}, "Expected IllegalArgumentException for línea vacía, but received: no exception");
	}

}
