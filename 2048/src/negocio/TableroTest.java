package negocio;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

class TableroTest {
	
	@Test
	public void tableroTest() {
		Tablero tablero=new Tablero();
		int cont=0;
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				if(tablero.existeValor(i, j)) {
					cont++;
				}
			}
		}
		assertTrue(cont==2);
	}

}
