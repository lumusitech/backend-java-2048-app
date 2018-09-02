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
	
	
	
	@Test
	public void sumarDerechaSinValoresTest() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		tablero.sumarDerecha();
		
		//verify
		assertTrue(verificarValoresEnTableroCompleto(tablero, 0));
		
	}
	
	
	
	
	@Test
	public void sumarDerechaCol0Col1Test() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 0, 2);
		setColumnaCompletaConValor(tablero, 1, 2);
		tablero.sumarDerecha();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 1, 4));
		
	}
	
	@Test
	public void sumarDerechaCol0Col2Test() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 0, 2);
		setColumnaCompletaConValor(tablero, 2, 2);
		tablero.sumarDerecha();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 2, 4));
		
	}
	
	@Test
	public void sumarDerechaCol0Col3Test() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 0, 2);
		setColumnaCompletaConValor(tablero, 3, 2);
		tablero.sumarDerecha();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 3, 4));
		
	}
	
	@Test
	public void sumarDerechaCol1Col2Test() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 1, 2);
		setColumnaCompletaConValor(tablero, 2, 2);
		tablero.sumarDerecha();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 2, 4));
		
	}
	
	@Test
	public void sumarDerechaCol1Col3Test() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 1, 2);
		setColumnaCompletaConValor(tablero, 3, 2);
		tablero.sumarDerecha();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 3, 4));
		
	}
	
	@Test
	public void sumarDerechaCol2Col3Test() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 2, 2);
		setColumnaCompletaConValor(tablero, 3, 2);
		tablero.sumarDerecha();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 3, 4));
		
	}
	
	@Test
	public void sumarDerechaCol0Col1ValoresDistintosTest() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 0, 2);
		setColumnaCompletaConValor(tablero, 1, 4);
		tablero.sumarDerecha();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 1, 4));
		
	}
	
	@Test
	public void sumarDerechaCol0Col2ValoresDistintosTest() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 0, 2);
		setColumnaCompletaConValor(tablero, 2, 4);
		tablero.sumarDerecha();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 2, 4));
		
	}
	
	@Test
	public void sumarDerechaCol0Col3ValoresDistintosTest() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 0, 2);
		setColumnaCompletaConValor(tablero, 3, 4);
		tablero.sumarDerecha();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 3, 4));
		
	}
	
	@Test
	public void sumarDerechaCol1Col2ValoresDistintosTest() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 1, 2);
		setColumnaCompletaConValor(tablero, 2, 4);
		tablero.sumarDerecha();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 2, 4));
		
	}
	
	@Test
	public void sumarDerechaCol1Col3ValoresDistintosTest() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 1, 2);
		setColumnaCompletaConValor(tablero, 3, 4);
		tablero.sumarDerecha();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 3, 4));
		
	}
	
	@Test
	public void sumarDerechaCol2Col3ValoresDistintosTest() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 2, 2);
		setColumnaCompletaConValor(tablero, 3, 4);
		tablero.sumarDerecha();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 3, 4));
		
	}
	
	
	
	@Test
	public void sumarDerechaCol3Col2SinValorAnteriorTest() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 3, 2);
		tablero.sumarDerecha();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 3, 2));
	}

	
	
	
	@Test
	public void sumarIzquierdaSinValoresTest() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		tablero.sumarIzquierda();
		
		//verify
		assertTrue(verificarValoresEnTableroCompleto(tablero, 0));
		
	}
	
	@Test
	public void sumarIzquierdaCol0Col1Test() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 0, 2);
		setColumnaCompletaConValor(tablero, 1, 2);
		tablero.sumarIzquierda();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 0, 4));
		
	}
	
	@Test
	public void sumarIzquierdaCol0Col2Test() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 0, 2);
		setColumnaCompletaConValor(tablero, 2, 2);
		tablero.sumarIzquierda();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 0, 4));
		
	}
	
	@Test
	public void sumarIzquierdaCol0Col3Test() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 0, 2);
		setColumnaCompletaConValor(tablero, 3, 2);
		tablero.sumarIzquierda();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 0, 4));
		
	}
	
	@Test
	public void sumarIzquierdaCol1Col0Test() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 1, 2);
		setColumnaCompletaConValor(tablero, 2, 2);
		tablero.sumarIzquierda();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 1, 4));
		
	}
	
	@Test
	public void sumarIzquierdaCol1Col2Test() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 1, 2);
		setColumnaCompletaConValor(tablero, 2, 2);
		tablero.sumarIzquierda();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 1, 4));
		
	}
	
	@Test
	public void sumarIzquierdaCol1Col3Test() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 1, 2);
		setColumnaCompletaConValor(tablero, 3, 2);
		tablero.sumarIzquierda();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 1, 4));
		
	}
	
	@Test
	public void sumarIzquierdaCol2Col3Test() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 2, 2);
		setColumnaCompletaConValor(tablero, 3, 2);
		tablero.sumarIzquierda();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 2, 4));
		
	}
	
	@Test
	public void sumarIzquierdaCol0Col1ValoresDistintosTest() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 0, 2);
		setColumnaCompletaConValor(tablero, 1, 4);
		tablero.sumarIzquierda();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 0, 2));
		
	}
	
	@Test
	public void sumarIzquierdaCol0Col2ValoresDistintosTest() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 0, 2);
		setColumnaCompletaConValor(tablero, 2, 4);
		tablero.sumarIzquierda();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 0, 2));
		
	}
	
	@Test
	public void sumarIzquierdaCol0Col3ValoresDistintosTest() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 0, 2);
		setColumnaCompletaConValor(tablero, 3, 4);
		tablero.sumarIzquierda();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 0, 2));
		
	}
	
	@Test
	public void sumarIzquierdaCol1Col2ValoresDistintosTest() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 1, 2);
		setColumnaCompletaConValor(tablero, 2, 4);
		tablero.sumarIzquierda();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 1, 2));
		
	}
	
	@Test
	public void sumarIzquierdaCol1Col3ValoresDistintosTest() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 1, 2);
		setColumnaCompletaConValor(tablero, 3, 4);
		tablero.sumarIzquierda();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 1, 2));
		
	}
	
	@Test
	public void sumarIzquierdaCol2Col3ValoresDistintosTest() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 2, 2);
		setColumnaCompletaConValor(tablero, 3, 4);
		tablero.sumarIzquierda();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 2, 2));
		
	}
	
	@Test
	public void sumarIzquierdaCol3Col2SinValorSiguienteTest() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 0, 2);
		tablero.sumarIzquierda();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 0, 2));
	}
	
	
	
	
	@Test
	public void sumarArribaSinValoresTest() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		tablero.sumarArriba();
		
		//verify
		assertTrue(verificarValoresEnTableroCompleto(tablero, 0));
		
	}
	
	@Test
	public void sumarArribafila0fila1Test() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 0, 2);
		setFilaCompletaConValor(tablero, 1, 2);
		tablero.sumarArriba();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 0, 4));
		
	}
	
	@Test
	public void sumarArribafila0fila2Test() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 0, 2);
		setFilaCompletaConValor(tablero, 2, 2);
		tablero.sumarArriba();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 0, 4));
		
	}
	
	@Test
	public void sumarArribafila0fila3Test() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 0, 2);
		setFilaCompletaConValor(tablero, 3, 2);
		tablero.sumarArriba();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 0, 4));
		
	}
	
	@Test
	public void sumarArribafila1fila2Test() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 1, 2);
		setFilaCompletaConValor(tablero, 2, 2);
		tablero.sumarArriba();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 1, 4));
		
	}
	
	@Test
	public void sumarArribafila1fila3Test() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 1, 2);
		setFilaCompletaConValor(tablero, 3, 2);
		tablero.sumarArriba();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 1, 4));

	}
	
	@Test
	public void sumarArribafila2fila3Test() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 2, 2);
		setFilaCompletaConValor(tablero, 3, 2);
		tablero.sumarArriba();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 2, 4));
		
	}
	
	@Test
	public void sumarArribafila0fila1ValoresDistintosTest() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 0, 2);
		setFilaCompletaConValor(tablero, 1, 4);
		tablero.sumarArriba();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 0, 2));
		
	}
	
	@Test
	public void sumarArribafila0fila2ValoresDistintosTest() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 0, 2);
		setFilaCompletaConValor(tablero, 2, 4);
		tablero.sumarArriba();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 0, 2));
		
	}
	
	@Test
	public void sumarArribafila0fila3ValoresDistintosTest() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 0, 2);
		setFilaCompletaConValor(tablero, 3, 4);
		tablero.sumarArriba();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 0, 2));
		
	}
	
	@Test
	public void sumarArribafila1fila2ValoresDistintosTest() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 1, 2);
		setFilaCompletaConValor(tablero, 2, 4);
		tablero.sumarArriba();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 1, 2));
		
	}

	@Test
	public void sumarArribafila1fila3ValoresDistintosTest() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 1, 2);
		setFilaCompletaConValor(tablero, 3, 4);
		tablero.sumarArriba();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 1, 2));
		
	}
	
	@Test
	public void sumarArribafila2fila3ValoresDistintosTest() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 2, 2);
		setFilaCompletaConValor(tablero, 3, 4);
		tablero.sumarArriba();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 2, 2));
		
	}
	
	@Test
	public void sumarArribaCol3Col2SinValorAbajoTest() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 0, 2);
		tablero.sumarArriba();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 0, 2));
	}
	
	
	
	@Test
	public void sumarAbajoSinValoresTest() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		tablero.sumarAbajo();
		
		//verify
		assertTrue(verificarValoresEnTableroCompleto(tablero, 0));
		
	}
	
	@Test
	public void sumarAbajoFila0Fila1Test() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 0, 2);
		setFilaCompletaConValor(tablero, 1, 2);
		tablero.sumarAbajo();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 1, 4));
		
	}
	
	@Test
	public void sumarAbajoFila0Fila2Test() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 0, 2);
		setFilaCompletaConValor(tablero, 2, 2);
		tablero.sumarAbajo();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 2, 4));
		
	}
	
	@Test
	public void sumarAbajoFila0Fila3Test() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 0, 2);
		setFilaCompletaConValor(tablero, 3, 2);
		tablero.sumarAbajo();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 3, 4));
		
	}
	
	@Test
	public void sumarAbajoFila1Fila2Test() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 1, 2);
		setFilaCompletaConValor(tablero, 2, 2);
		tablero.sumarAbajo();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 2, 4));

	}
	
	@Test
	public void sumarAbajoFila1Fila3Test() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 1, 2);
		setFilaCompletaConValor(tablero, 3, 2);
		tablero.sumarAbajo();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 3, 4));
		
	}
	
	@Test
	public void sumarAbajoFila2Fila3Test() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 2, 2);
		setColumnaCompletaConValor(tablero, 3, 2);
		tablero.sumarAbajo();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 3, 4));
		
	}

	
	@Test
	public void sumarAbajoFila0Fila1ValoresDistintosTest() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 0, 2);
		setFilaCompletaConValor(tablero, 1, 4);
		tablero.sumarAbajo();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 1, 4));
		
	}
	
	@Test
	public void sumarAbajoFila0Abajo2ValoresDistintosTest() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 0, 2);
		setFilaCompletaConValor(tablero, 2, 4);
		tablero.sumarAbajo();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 2, 4));

	}
	
	@Test
	public void sumarAbajoFila0Fila3ValoresDistintosTest() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 0, 2);
		setColumnaCompletaConValor(tablero, 3, 4);
		tablero.sumarIzquierda();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 3, 4));
		
	}
	
	@Test
	public void sumarAbajoFila1Fila2ValoresDistintosTest() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 1, 2);
		setFilaCompletaConValor(tablero, 2, 4);
		tablero.sumarAbajo();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 2, 4));
		
	}
	
	@Test
	public void sumarAbajoFila1Fila3ValoresDistintosTest() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 1, 2);
		setColumnaCompletaConValor(tablero, 3, 4);
		tablero.sumarIzquierda();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 3, 4));
		
	}
	
	@Test
	public void sumarAbajoFila2Fila3ValoresDistintosTest() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 2, 2);
		setColumnaCompletaConValor(tablero, 3, 4);
		tablero.sumarIzquierda();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 3, 4));
		
	}
	
	@Test
	public void sumarAbajoFila3Fila2SinValorAnteriorTest() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 3, 2);
		tablero.sumarAbajo();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 3, 2));
	}
	
	
	
	@Test
	public void moverDerechaCol0Col1OrigenTest() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 0, 2);
		setColumnaCompletaConValor(tablero, 2, 4);
		setColumnaCompletaConValor(tablero, 3, 4);
		
		tablero.moverDerecha();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 0, 0));
	}
	
	@Test
	public void moverDerechaCol0Col1LibreTest() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 0, 2);
		setColumnaCompletaConValor(tablero, 2, 4);
		setColumnaCompletaConValor(tablero, 3, 4);
		tablero.moverDerecha();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 1, 2));
	}
	
	@Test
	public void moverDerechaCol0Col2OrigenTest() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 0, 2);
		setColumnaCompletaConValor(tablero, 1, 4);
		setColumnaCompletaConValor(tablero, 3, 4);
		
		tablero.moverDerecha();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 0, 0));
	}
	
	@Test
	public void moverDerechaCol0Col2LibreTest() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 0, 2);
		setColumnaCompletaConValor(tablero, 1, 4);
		setColumnaCompletaConValor(tablero, 3, 4);
		
		tablero.moverDerecha();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 1, 2));
	}
	
	@Test
	public void moverDerechaCol0Col3OrigenTest() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 0, 2);
		setColumnaCompletaConValor(tablero, 1, 4);
		setColumnaCompletaConValor(tablero, 2, 4);
		
		tablero.moverDerecha();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 0, 0));
	}
	
	@Test
	public void moverDerechaCol0Col3LibreTest() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 0, 2);
		setColumnaCompletaConValor(tablero, 1, 4);
		setColumnaCompletaConValor(tablero, 2, 4);
		tablero.moverDerecha();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 1, 2));
	}
	
	@Test
	public void moverDerechaCol1Col2OrigenTest() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 0, 4);
		setColumnaCompletaConValor(tablero, 1, 2);
		setColumnaCompletaConValor(tablero, 3, 4);
		
		tablero.moverDerecha();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 0, 0));
	}
	
	@Test
	public void moverDerechaCol1Col2DestinoTest() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 0, 4);
		setColumnaCompletaConValor(tablero, 1, 2);
		setColumnaCompletaConValor(tablero, 3, 4);
		tablero.moverDerecha();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 2, 2));
	}
	
	@Test
	public void moverDerechaCol1Col3OrigenTest() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 0, 4);
		setColumnaCompletaConValor(tablero, 1, 2);
		setColumnaCompletaConValor(tablero, 2, 4);
		
		tablero.moverDerecha();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 0, 0));
	}
	
	@Test
	public void moverDerechaCol1Col3DestinoTest() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 0, 4);
		setColumnaCompletaConValor(tablero, 1, 2);
		setColumnaCompletaConValor(tablero, 2, 4);
		tablero.moverDerecha();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 2, 2));
	}
	
	@Test
	public void moverDerechaCol2Col3OrigenTest() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 0, 4);
		setColumnaCompletaConValor(tablero, 1, 4);
		setColumnaCompletaConValor(tablero, 2, 2);
		
		tablero.moverDerecha();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 0, 0));
	}
	
	@Test
	public void moverDerechaCol2Col3DestinoTest() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 0, 4);
		setColumnaCompletaConValor(tablero, 1, 4);
		setColumnaCompletaConValor(tablero, 2, 2);
		tablero.moverDerecha();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 3, 2));
	}
	
	@Test
	public void moverDerechaSinMovimientoTest() {
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 0, 2);
		setColumnaCompletaConValor(tablero, 1, 4);
		setColumnaCompletaConValor(tablero, 2, 8);
		setColumnaCompletaConValor(tablero, 3, 16);
		tablero.moverDerecha();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 2, 8));
	}
	
	@Test void moverDerecha2ColumnasVaciasTest() {
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 0, 2);
		setColumnaCompletaConValor(tablero, 1, 4);
		tablero.moverDerecha();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 2, 2));
	}
	
	@Test void moverDerecha3ColumnasVaciasTest() {
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 0, 2);
		tablero.moverDerecha();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 3, 2));
	}
	
	@Test void moverDerecha4ColumnasVaciasTest() {
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		tablero.moverDerecha();
		
		//verify
		assertTrue(verificarValoresEnTableroCompleto(tablero, 0));
	}
	
	
	
	@Test
	public void moverIzquierdaCol0Col1Origen() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 0, 2);
		setColumnaCompletaConValor(tablero, 2, 4);
		setColumnaCompletaConValor(tablero, 3, 2);
		
		tablero.moverIzquierda();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 3, 0));
	}
	
	@Test
	public void moverIzquierdaCol0Col1Libre() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 0, 2);
		setColumnaCompletaConValor(tablero, 2, 4);
		setColumnaCompletaConValor(tablero, 3, 2);
		
		tablero.moverIzquierda();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 1, 4));
	}
	
	@Test
	public void moverIzquierdaCol0Col2Origen() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 0, 2);
		setColumnaCompletaConValor(tablero, 1, 4);
		setColumnaCompletaConValor(tablero, 3, 2);
		
		tablero.moverIzquierda();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 3, 0));
	}
	
	@Test
	public void moverIzquierdaCol0Col2Libre() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 0, 2);
		setColumnaCompletaConValor(tablero, 1, 4);
		setColumnaCompletaConValor(tablero, 3, 2);
		
		tablero.moverIzquierda();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 3, 0));
	}
	
	@Test
	public void moverIzquierdaCol0Col3Origen() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 0, 2);
		setColumnaCompletaConValor(tablero, 1, 4);
		setColumnaCompletaConValor(tablero, 2, 2);
		
		tablero.moverIzquierda();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 3, 0));
	}

	@Test
	public void moverIzquierdaCol0Col3Libre() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 0, 2);
		setColumnaCompletaConValor(tablero, 1, 4);
		setColumnaCompletaConValor(tablero, 2, 2);
		
		tablero.moverIzquierda();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 2, 2));
	}
	
	@Test
	public void moverIzquierdaCol1Col2Origen() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 0, 4);
		setColumnaCompletaConValor(tablero, 1, 4);
		setColumnaCompletaConValor(tablero, 3, 2);
		
		tablero.moverIzquierda();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 3, 0));
	}
	
	@Test
	public void moverIzquierdaCol1Col2Libre() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 0, 4);
		setColumnaCompletaConValor(tablero, 1, 4);
		setColumnaCompletaConValor(tablero, 3, 2);
		
		tablero.moverIzquierda();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 2, 2));
	}
	
	@Test
	public void moverIzquierdaCol1Col3Origen() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 0, 4);
		setColumnaCompletaConValor(tablero, 1, 4);
		setColumnaCompletaConValor(tablero, 2, 2);
		
		tablero.moverIzquierda();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 3, 0));
	}
	
	@Test
	public void moverIzquierdaCol1Col3Libre() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 0, 4);
		setColumnaCompletaConValor(tablero, 1, 4);
		setColumnaCompletaConValor(tablero, 2, 2);
		
		tablero.moverIzquierda();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 2, 2));
	}
	
	@Test
	public void moverIzquierdaCol2Col3Origen() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 0, 4);
		setColumnaCompletaConValor(tablero, 1, 4);
		setColumnaCompletaConValor(tablero, 2, 2);
		
		tablero.moverIzquierda();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 3, 0));
	}
	
	@Test
	public void moverIzquierdaCol2Col3Libre() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 0, 4);
		setColumnaCompletaConValor(tablero, 1, 4);
		setColumnaCompletaConValor(tablero, 2, 2);
		
		tablero.moverIzquierda();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 2, 2));
	}
	
	@Test
	public void moverIzquierdaSinMovimientoTest() {
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 0, 2);
		setColumnaCompletaConValor(tablero, 1, 4);
		setColumnaCompletaConValor(tablero, 2, 8);
		setColumnaCompletaConValor(tablero, 3, 16);
		tablero.moverIzquierda();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 2, 8));
	}
	
	@Test 
	public void moverIzquierda2ColumnasVaciasTest() {
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 2, 2);
		setColumnaCompletaConValor(tablero, 3, 4);
		tablero.moverIzquierda();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 0, 2));
	}
	
	@Test 
	public void moverIzquierda3ColumnasVaciasTest() {
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setColumnaCompletaConValor(tablero, 3, 2);
		tablero.moverIzquierda();
		
		//verify
		assertTrue(verificarValoresEnColumnaCompleta(tablero, 0, 2));
	}
	
	@Test 
	public void moverIzquierda4ColumnasVaciasTest() {
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		tablero.moverIzquierda();
		
		//verify
		assertTrue(verificarValoresEnTableroCompleto(tablero, 0));
	}
	
	
	

	
	@Test
	public void moverArribaFila0Fila1Origen() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 0, 4);
		setFilaCompletaConValor(tablero, 2, 4);
		setFilaCompletaConValor(tablero, 3, 2);
		tablero.moverArriba();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 3, 0));
	}
	
	@Test
	public void moverArribaFila0Fila1Libre() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 0, 4);
		setFilaCompletaConValor(tablero, 2, 4);
		setFilaCompletaConValor(tablero, 3, 2);
		tablero.moverArriba();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 2, 2));
	}
	
	@Test
	public void moverArribaFila0Fila2Origen() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 0, 4);
		setFilaCompletaConValor(tablero, 1, 4);
		setFilaCompletaConValor(tablero, 3, 2);
		tablero.moverArriba();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 3, 0));
	}
	
	@Test
	public void moverArribaFila0Fila2Libre() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 0, 4);
		setFilaCompletaConValor(tablero, 1, 4);
		setFilaCompletaConValor(tablero, 3, 2);
		tablero.moverArriba();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 2, 2));
	}

	@Test
	public void moverArribaFila0Fila3Origen() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 0, 4);
		setFilaCompletaConValor(tablero, 1, 4);
		setFilaCompletaConValor(tablero, 2, 2);
		tablero.moverArriba();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 3, 0));
	}
	
	@Test
	public void moverArribaFila0Fila3Libre() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 0, 4);
		setFilaCompletaConValor(tablero, 1, 4);
		setFilaCompletaConValor(tablero, 2, 2);
		tablero.moverArriba();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 2, 2));
	}

	@Test
	public void moverArribaFila1Fila2Origen() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 0, 4);
		setFilaCompletaConValor(tablero, 1, 4);
		setFilaCompletaConValor(tablero, 3, 2);
		tablero.moverArriba();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 3, 0));
	}
	
	@Test
	public void moverArribaFila1Fila2Libre() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 0, 4);
		setFilaCompletaConValor(tablero, 1, 4);
		setFilaCompletaConValor(tablero, 3, 2);
		tablero.moverArriba();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 2, 2));
	}

	@Test
	public void moverArribaFila1Fila3Origen() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 0, 4);
		setFilaCompletaConValor(tablero, 1, 4);
		setFilaCompletaConValor(tablero, 2, 2);
		tablero.moverArriba();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 3, 0));
	}
	
	@Test
	public void moverArribaFila1Fila3Libre() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 0, 4);
		setFilaCompletaConValor(tablero, 1, 4);
		setFilaCompletaConValor(tablero, 2, 2);
		tablero.moverArriba();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 2, 2));
	}

	@Test
	public void moverArribaFila2Fila3Origen() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 0, 4);
		setFilaCompletaConValor(tablero, 1, 4);
		setFilaCompletaConValor(tablero, 2, 2);
		tablero.moverArriba();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 3, 0));
	}
	
	@Test
	public void moverArribaFila2Fila3Libre() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 0, 4);
		setFilaCompletaConValor(tablero, 1, 4);
		setFilaCompletaConValor(tablero, 2, 2);
		tablero.moverArriba();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 2, 2));
	}
	
	
	@Test
	public void moverArribaSinMovimientoTest() {
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 0, 2);
		setFilaCompletaConValor(tablero, 1, 4);
		setFilaCompletaConValor(tablero, 2, 8);
		setFilaCompletaConValor(tablero, 3, 16);
		tablero.moverArriba();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 2, 8));
	}
	
	@Test 
	public void moverArriba2FilasVaciasTest() {
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 2, 2);
		setFilaCompletaConValor(tablero, 3, 4);
		tablero.moverArriba();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 0, 2));
	}
	
	@Test 
	public void moverArriba3FilasVaciasTest() {
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 1, 2);
		setFilaCompletaConValor(tablero, 2, 4);
		setFilaCompletaConValor(tablero, 3, 8);
		tablero.moverArriba();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 0, 2));
	}
	
	@Test 
	public void moverArriba4FilasVaciasTest() {
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		tablero.moverArriba();
		
		//verify
		assertTrue(verificarValoresEnTableroCompleto(tablero, 0));
	}
	
	
	
	
	
	
	
	@Test
	public void moverAbajoFila0Fila1Origen() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 0, 4);
		setFilaCompletaConValor(tablero, 2, 4);
		setFilaCompletaConValor(tablero, 3, 2);
		tablero.moverAbajo();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 0, 0));
	}
	
	@Test
	public void moverAbajoFila0Fila1Libre() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 0, 4);
		setFilaCompletaConValor(tablero, 2, 4);
		setFilaCompletaConValor(tablero, 3, 2);
		tablero.moverAbajo();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 1, 4));
	}

	@Test
	public void moverAbajoFila0Fila2Origen() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 0, 4);
		setFilaCompletaConValor(tablero, 1, 2);
		setFilaCompletaConValor(tablero, 3, 2);
		tablero.moverAbajo();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 0, 0));
	}
	
	@Test
	public void moverAbajoFila0Fila2Libre() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 0, 4);
		setFilaCompletaConValor(tablero, 1, 2);
		setFilaCompletaConValor(tablero, 3, 2);
		tablero.moverAbajo();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 1, 4));
	}
	
	@Test
	public void moverAbajoFila0Fila3Origen() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 0, 4);
		setFilaCompletaConValor(tablero, 1, 2);
		setFilaCompletaConValor(tablero, 2, 2);
		tablero.moverAbajo();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 0, 0));
	}
	
	@Test
	public void moverAbajoFila0Fila3Libre() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 0, 4);
		setFilaCompletaConValor(tablero, 1, 4);
		setFilaCompletaConValor(tablero, 2, 2);
		tablero.moverAbajo();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 1, 4));
	}
	
	@Test
	public void moverAbajoFila1Fila2Origen() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 0, 4);
		setFilaCompletaConValor(tablero, 1, 2);
		setFilaCompletaConValor(tablero, 3, 2);
		tablero.moverAbajo();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 0, 0));
	}
	
	@Test
	public void moverAbajoFila1Fila2Libre() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 0, 4);
		setFilaCompletaConValor(tablero, 1, 2);
		setFilaCompletaConValor(tablero, 3, 2);
		tablero.moverAbajo();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 1, 4));
	}
	
	@Test
	public void moverAbajoFila1Fila3Origen() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 0, 4);
		setFilaCompletaConValor(tablero, 1, 2);
		setFilaCompletaConValor(tablero, 2, 2);
		tablero.moverAbajo();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 0, 0));
	}
	
	@Test
	public void moverAbajoFila1Fila3Libre() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 0, 4);
		setFilaCompletaConValor(tablero, 1, 2);
		setFilaCompletaConValor(tablero, 2, 2);
		tablero.moverAbajo();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 1, 4));
	}
	
	@Test
	public void moverAbajoFila2Fila3Origen() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 0, 4);
		setFilaCompletaConValor(tablero, 1, 2);
		setFilaCompletaConValor(tablero, 2, 2);
		tablero.moverAbajo();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 0, 0));
	}
	
	@Test
	public void moverAbajoFila2Fila3Libre() {
		
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 0, 4);
		setFilaCompletaConValor(tablero, 1, 2);
		setFilaCompletaConValor(tablero, 2, 2);
		tablero.moverAbajo();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 1, 4));
	}
	
	@Test
	public void moverAbajoSinMovimientoTest() {
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 0, 2);
		setFilaCompletaConValor(tablero, 1, 4);
		setFilaCompletaConValor(tablero, 2, 8);
		setFilaCompletaConValor(tablero, 3, 16);
		tablero.moverAbajo();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 2, 8));
	}
	
	@Test 
	public void moverAbajo2FilasVaciasTest() {
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 1, 2);
		setFilaCompletaConValor(tablero, 2, 4);
		tablero.moverAbajo();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 3, 4));
	}
	
	@Test 
	public void moverAbajo3FilasVaciasTest() {
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 0, 2);
		setFilaCompletaConValor(tablero, 1, 2);
		setFilaCompletaConValor(tablero, 2, 4);
		tablero.moverAbajo();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 3, 4));
	}
	
	@Test 
	public void moverAbajo4FilasVaciasTest() {
		//setup
		Tablero tablero = new Tablero();
		
		//excercise
		tablero.vaciarTablero();
		setFilaCompletaConValor(tablero, 0, 2);
		setFilaCompletaConValor(tablero, 1, 4);
		setFilaCompletaConValor(tablero, 2, 8);
		setFilaCompletaConValor(tablero, 3, 16);
		tablero.moverAbajo();
		
		//verify
		assertTrue(verificarValoresEnFilaCompleta(tablero, 3, 16));
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

	public void setFilaCompletaConValor(Tablero tablero, int fila, int valor) {
		
		for(int columna=0; columna<tablero.getTamanio(); columna++) {
			tablero.setValor(fila, columna, valor);
		}
	}
	
	public void setColumnaCompletaConValor(Tablero tablero, int columna, int valor) {
		
			for(int fila=0; fila<tablero.getTamanio(); fila++) {
				tablero.setValor(fila, columna, valor);
			}
	}
	
	public boolean verificarValoresEnColumnaCompleta(Tablero tablero, int columna, int valor) {
		boolean ret = false;
		for(int fila=0; fila<tablero.getTamanio(); fila++) {
			if(tablero.getValor(fila, columna)==valor) {
				ret = true;
			}
			else {
				ret = false;
			}
		}
		
		return ret;
	}
	
	public boolean verificarValoresEnFilaCompleta(Tablero tablero, int fila, int valor) {
		boolean ret = false;
		for(int columna=0; columna<tablero.getTamanio(); columna++) {
			if(tablero.getValor(fila, columna)==valor) {
				ret = true;
			}
			else {
				ret = false;
			}
		}
		
		return ret;
	}
	
	public boolean verificarValoresEnTableroCompleto(Tablero tablero, int valor) {
		boolean ret=false;
		for(int fila=0; fila<tablero.getTamanio(); fila++) {
			for(int columna=0; columna<tablero.getTamanio(); columna++) {
				if(tablero.getValor(fila, columna)==0) {
					ret=true;
				}
				else {
					ret=false;
				}
			}
		}
		
		return ret;
	}
}
