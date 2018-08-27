package negocio;

import java.util.Random;

public class Tablero {
	private int [][] tablero;
	private int fila;
	private int columna;
	
	
	
	public Tablero() {
		
		tablero=new int[4][4];
		
		//Agrega el 1er valor al tablero (posición y valor aleatorio)
		agregarValor();
		
		//Agrega el 2do valor al tablero (posición y valor aleatorio)
		agregarValor();
		
		System.out.println();
		System.out.println();
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	public void agregarValor() {
		lugarAleatorio();//busca un lugar aleatorio disponible
		tablero[fila][columna]=dosOCuatro();//asigna a ese lugar dos o cuatro aleatoriamente
	}

	////////////////////////////////////////////////////////////////////////////////////////
	public void lugarAleatorio() {
		Random r1 = new Random();
		Random r2 = new Random();
		
		int contador=0;
		do {
			System.out.println("posición:");
			
			fila = r1.nextInt(4);
			System.out.println(fila);
			
			columna = r2.nextInt(4);
			System.out.println(columna);
			
			contador++;
		}
		while(tablero[fila][columna]!=0 && contador<10);
		
		//cuando el contador llego a 10 se pasa a recorrer la matriz completa 
		//en busca de un lugar libre
		if(contador==10) {
			if(!existeLugarDisponible()){
				System.out.println("Fin del juego - No existe lugar disponible");
			}
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	public int dosOCuatro() {
		Random r = new Random();
		if(r.nextInt(100)<50) {
			return 2;
		}
		return 4;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	public boolean existeLugarDisponible() {
		
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				if(tablero[i][j]==0) {
					//guarda la fila y columna que encontro vacía
					fila=i;
					columna=j;
					return true;
				}
			}
		}
		return false;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	public int getValor(int i, int j) {
		return tablero[i][j];
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	public void reiniciar() {
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				//Pone a todas las posiciones en cero
				tablero[i][j]=0;
			}
		}
		
		agregarValor();
		
		agregarValor();
		
		System.out.println();
		System.out.println();
		
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	
	public void mover(String direccion) {
		
	}
	
	
}
