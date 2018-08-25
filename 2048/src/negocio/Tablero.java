package negocio;

public class Tablero {
	private int [][] tablero;
	
	
	public Tablero() {
		tablero=new int[4][4];
	}
	
	public int getValor(int i, int j) {
		return tablero[i][j];
	}
	
	
}
