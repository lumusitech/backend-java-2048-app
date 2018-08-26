package negocio;

import java.util.Random;

public class Tablero {
	private int [][] tablero;
	private int valorInicial1;
	private int valorInicial2;
	private int fila;
	private int columna;
	private int DosOCuatro;
	
	
	public Tablero() {
		
		tablero=new int[4][4];
		
		valorInicial1=0;
		valorInicial2=0;
		
		valorAleatorio();
		valorInicial1=DosOCuatro;
		tablero[fila][columna]=valorInicial1;
		
		valorAleatorio();
		valorInicial2=DosOCuatro;
		tablero[fila][columna]=valorInicial2;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	public void valorAleatorio() {
		Random r1 = new Random();
		Random r2 = new Random();
		Random r3 = new Random();
		
		do {
			System.out.println("posición:");
			
			fila = r1.nextInt(4);
			System.out.println(fila);
			
			columna = r2.nextInt(4);
			System.out.println(columna);
		}
		while(tablero[fila][columna]!=0);
		
		int valorAleatorio = r3.nextInt(100);
		if(valorAleatorio<50) {
			DosOCuatro=2;
		}
		else {
			DosOCuatro=4;
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	public int getValor(int i, int j) {
		return tablero[i][j];
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	public void reiniciar() {
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				tablero[i][j]=0;
			}
		}
		
		valorInicial1=0;
		valorInicial2=0;
		
		valorAleatorio();
		valorInicial1=DosOCuatro;
		tablero[fila][columna]=valorInicial1;
		
		valorAleatorio();
		valorInicial2=DosOCuatro;
		tablero[fila][columna]=valorInicial2;
		
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	
	
}
