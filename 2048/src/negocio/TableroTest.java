package negocio;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;

class TableroTest {
	
	@Test
	public void tableroTest() {
		Tablero tablero=new Tablero();
		int cont = contarValoresDelTablero(tablero);
		assertTrue(cont==2);
	}
	
	@Test
	public void agregarValorRandomTest() {
		
		//setup
		Tablero tablero=new Tablero();
		
		//excercise
		agregarValoresRandom(tablero,14);
		int cont = contarValoresDelTablero(tablero);
		
		//verify
		assertTrue(cont==16);//2 valores iniciales + 14 agregados (se completa el tablero 4x4)
	}
	
	@Test
	public void agregarValorRandomTableroCompletoTest() {

		
		//setup
		Tablero tablero=new Tablero();
		
		//excercise
		agregarValoresRandom(tablero,14);
		
		//verify
		assertFalse(tablero.agregarValorRandom());
		//2 valores iniciales + 14 agregados + 1 que no se agrega por estar completo (tablero 4x4)
	}

	@Test
	public void reiniciarTest() {
		
		//setup
		Tablero tablero=new Tablero();
		
		//excercise
		agregarValoresRandom(tablero,10);
		tablero.reiniciar();
		int cont = contarValoresDelTablero(tablero);
		
		//verify
		assertTrue(cont==2);
	}
	
	@Test
	public void controlDePuntajesTest() {
		
		//setup
		Tablero tablero=new Tablero();
		
		//excercise
		tablero.setPuntaje(2000);
		tablero.reiniciar();
		
		//verify
		assertTrue(tablero.getPuntaje()<tablero.getRecord());
	}
	
	@Test
	public void moverCuadroPosiciónOrigenTest() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		tablero.setValor(0, 0, 2);
		tablero.moverCuadro(0, 0, 0, 3);
		
		//verify
		assertFalse(tablero.existeValor(0, 0));
	}
	
	@Test
	public void moverCuadroPosiciónDestinoTest() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		tablero.setValor(0, 0, 2);
		tablero.moverCuadro(0, 0, 0, 3);
		
		//verify
		assertTrue(tablero.existeValor(0, 3));
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	
	public int contarValoresDelTablero(Tablero tablero) {
		int cont=0;
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				if(tablero.existeValor(i, j)) {
					cont++;
				}
			}
		}
		return cont;
	}
	
	public void agregarValoresRandom(Tablero tablero, int cant) {
		for(int i=0; i<cant; i++) {
			tablero.agregarValorRandom();
		}
	}

}
