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
	public boolean agregarValor() {
		if(lugarAleatorio()) {
			//busca un lugar aleatorio disponible
			tablero[fila][columna]=dosOCuatro();//asigna a ese lugar dos o cuatro aleatoriamente
			return true;
		}
		return false;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	public boolean lugarAleatorio() {
		Random r1 = new Random();
		Random r2 = new Random();
		
		int contador=0;
		do {
			System.out.println("posición:");
			
			fila = r1.nextInt(4);
			System.out.println(fila);
			
			columna = r2.nextInt(4);
			System.out.println(columna);
			System.out.println("----------------------------");
			contador++;
		}
		while(tablero[fila][columna]!=0 && contador<10);
		
		//cuando el contador llego a 10 se pasa a recorrer la matriz completa 
		//en busca de un lugar libre
		if(contador==10) {
			if(!existeLugarDisponible()){
				System.out.println("Fin del juego - No existe lugar disponible");
				return false;
			}
		}
		return true;
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
				if(getValor(i,j)==0) {
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
	public int getValor(int fila, int columna) {
		return tablero[fila][columna];
	}
	
	public void setValor(int fila, int columna, int valor) {
		tablero[fila][columna]=valor;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	public void reiniciar() {
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				//Pone a todas las posiciones en cero
				setValor(i,j,0);
			}
		}
		
		agregarValor();
		
		agregarValor();
		
		System.out.println();
		System.out.println();
		
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	
	public boolean mover(String direccion) {
		if(direccion.equals("derecha")) {
			moverDerecha();
			return true;
		}
		
		if(direccion.equals("izquierda")) {
			moverIzquierda();
			return true;
		}
		
		if(direccion.equals("arriba")) {
			moverArriba();
			return true;
		}
		
		if(direccion.equals("abajo")) {
			moverAbajo();
			return true;
		}
		return false;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	
	public void moverDerecha() {
		System.out.println("derecha");
		
		//fila a fila
		for(int i=0; i<4; i++) {
			//columna a columna de derecha a izquierda
			for(int j=3; j>0; j--) {
				
				//veo si hay algo en ese
				if(getValor(i,j)!=0) {
					
					
				}
			}
		}
		agregarValor();
	}
	
	public void moverIzquierda() {
		System.out.println("izquierda");
		//agregarValor();
	}
	
	public void moverArriba() {
		System.out.println("arriba");
		//agregarValor();
	}
	
	public void moverAbajo() {
		System.out.println("abajo");
		//agregarValor();
	}

	////////////////////////////////////////////////////////////////////////////////////////
	
}
