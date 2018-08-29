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
		for(int fila=0; fila<4; fila++) {
			for(int columna=0; columna<4; columna++) {
				//Pone a todas las posiciones en cero
				setValor(fila,columna,0);
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
			sumarDerecha();
			moverDerecha();
			agregarValor();
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
	public boolean existeValor(int fila, int columna) {
		return getValor(fila,columna)!=0;
	}
	////////////////////////////////////////////////////////////////////////////////////////
	
	public void sumarDerecha() {
		System.out.println("derecha");
		
		//fila a fila
		for(int fila=0; fila<4; fila++) {
			//columna a columna de derecha a izquierda
			for(int columna=3; columna>0; columna--) {
				
				int actual=columna;
				int anterior=(columna-1);
				
				//veo si hay algo en la columna actual
				if(existeValor(fila,columna)) {
					if(existeValor(fila,anterior) && (getValor(fila,actual)==getValor(fila,anterior)) ) {
						//los sumo
						setValor(fila,columna,(getValor(fila,actual))+(getValor(fila,anterior)));
						//se quita el valor del cuadro anterior poque se sumo en el cuadro actual
						setValor(fila, anterior, 0);
					}
					//Si no hay nada en el cuadro anterior
					else if(!existeValor(fila,anterior)){
						//me fijo en la columna siguienteAnterior
						int sigteAnterior=anterior-1;
						//Me aseguro que este dentro del rango de valores posibles
						if(sigteAnterior>=0) {
							if(existeValor(fila,sigteAnterior) 
							&& (getValor(fila,actual)==getValor(fila,sigteAnterior))) {
								//los sumo
								setValor(fila,columna,(getValor(fila,actual))+(getValor(fila,sigteAnterior)));
								//se quita el valor del cuadro anterior poque se sumo en el cuadro actual
								setValor(fila, sigteAnterior, 0);
							}
							//si no existe valor en el Siguiente anterior
							else if(!existeValor(fila,sigteAnterior)){
								//me fijo en la columna SigteSigteAnterior
								int sigteSigteAnterior=sigteAnterior-1;
							
								if(sigteSigteAnterior>=0) {
									if(existeValor(fila,sigteSigteAnterior) 
									&& (getValor(fila,actual)==getValor(fila,sigteSigteAnterior))) {
										//los sumo
										setValor(fila,columna,(getValor(fila,actual))+(getValor(fila,sigteSigteAnterior)));
										//se quita el valor del cuadro anterior poque se sumo en el cuadro actual
										setValor(fila, sigteSigteAnterior, 0);
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	public void moverDerecha() {
		//fila a fila
		for(int fila=0; fila<4; fila++) {
			//columna a columna
			for(int columna=0; columna<4; columna++) {
				
				//me fijo si existe un valor en la columna actual
				int actual=columna;
				if(existeValor(fila, actual)) {
					//veo en la columna siguiente
					int siguiente=actual+1;
					//me aseguro que este dentro del rango
					if(siguiente<=3) {
						//Si no existe valor
						if(!existeValor(fila, siguiente)) {
							//lo muevo ahi
							setValor(fila,siguiente,getValor(fila,actual));
							setValor(fila, actual, 0);
							//actualizo el nuevo actual y el nuevo siguiente
							actual=siguiente;
							siguiente=siguiente+1;
							//me aseguro que este dentro del rango
							if(siguiente<=3) {
								//Si no existe valor
								if(!existeValor(fila, siguiente)) {
									//lo muevo ahi
									setValor(fila,siguiente,getValor(fila,actual));
									setValor(fila, actual, 0);
									//actualizo el nuevo actual y el nuevo siguiente
									actual=siguiente;
									siguiente=siguiente+1;
									//me aseguro que este dentro del rango
									if(siguiente<=3) {
										//Si no existe valor
										if(!existeValor(fila, siguiente)) {
											//lo muevo ahi
											setValor(fila,siguiente,getValor(fila,actual));
											setValor(fila, actual, 0);
										}
									}
								}						
							}
						}						
					}
				}
			}
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
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
