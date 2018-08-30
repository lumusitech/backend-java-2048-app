package negocio;

import java.util.Random;

public class Tablero {
	private int [][] tablero;
	private int tamanio;
	private int fila;
	private int columna;
	

	public Tablero() {
		
		tamanio=4;
		tablero=new int[tamanio][tamanio];
		
		//Agrega el 1er valor al tablero (posici�n y valor aleatorio)
		agregarValor();
		int filaPrimerValor=fila;
		int columnaPrimerValor=columna;
	
		//Agrega el 2do valor al tablero (posici�n y valor aleatorio)
		agregarValor();
		int filaSegundoValor=fila;
		int columnaSegundoValor=columna;
		
		//codigo de control
		System.out.println("Primer Cuadro--->posici�n("+filaPrimerValor+", "+columnaPrimerValor+")");
		System.out.println("Segundo Cuadro--->posici�n("+filaSegundoValor+", "+columnaSegundoValor+")");
		System.out.println();
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	public boolean agregarValor() {
		if(lugarAleatorio()) {
			//busca un lugar aleatorio disponible
			setValor(fila, columna, dosOCuatro());//asigna a ese lugar dos o cuatro aleatoriamente
			System.out.println("se agrega el cuadro aleatorio: ("+fila+","+columna+")");
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
			
			fila = r1.nextInt(4);
			columna = r2.nextInt(4);
			
			contador++;

		}
		while(existeValor(fila, columna) && contador<10);
		
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
				if(!existeValor(i, j)) {
					//guarda la fila y columna que encontro vac�a
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

		for(int i=0; i<tamanio; i++) {
			for(int j=0; j<tamanio; j++) {
				//Pone a todas las posiciones en cero
				setValor(i,j,0);
			}
		}
		
		//Agrega el 1er valor al tablero (posici�n y valor aleatorio)
		agregarValor();
		int filaPrimerValor=fila;
		int columnaPrimerValor=columna;
	
		//Agrega el 2do valor al tablero (posici�n y valor aleatorio)
		agregarValor();
		int filaSegundoValor=fila;
		int columnaSegundoValor=columna;
		
		System.out.println("Primer Cuadro--->posici�n("+filaPrimerValor+", "+columnaPrimerValor+")");
		System.out.println("Segundo Cuadro--->posici�n("+filaSegundoValor+", "+columnaSegundoValor+")");
		System.out.println();
		
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	
	public boolean mover(String direccion) {
		if(direccion.equals("derecha")) {
	
			sumarDerecha();//funciona sin problemas
			moverDerecha();//funcion casi completa
		
			//si logra agregar devuelve true
			return agregarValor();
		}
		
		if(direccion.equals("izquierda")) {
			
			sumarIzquierda();//funciona sin problemas
			moverIzquierda();//funcion casi completa
			//si logra agregar devuelve true
			return agregarValor();
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
		for(int i=0; i<4; i++) {
			//columna a columna de derecha a izquierda
			for(int j=3; j>0; j--) {
				
				int actual=j;
				int anterior=(j-1);
				
				//veo si hay algo en la columna actual
				if(existeValor(i,j)) {
					if(existeValor(i,anterior) && (getValor(i,actual)==getValor(i,anterior)) ) {
						//los sumo
						setValor(i,j,(getValor(i,actual))+(getValor(i,anterior)));
						//se quita el valor del cuadro anterior poque se sumo en el cuadro actual
						setValor(i, anterior, 0);
					}
					//Si no hay nada en el cuadro anterior
					else if(!existeValor(i,anterior)){
						//me fijo en la columna siguienteAnterior
						int sigteAnterior=anterior-1;
						//Me aseguro que este dentro del rango de valores posibles
						if(sigteAnterior>=0) {
							if(existeValor(i,sigteAnterior) 
							&& (getValor(i,actual)==getValor(i,sigteAnterior))) {
								//los sumo
								setValor(i,j,(getValor(i,actual))+(getValor(i,sigteAnterior)));
								//se quita el valor del cuadro anterior poque se sumo en el cuadro actual
								setValor(i, sigteAnterior, 0);
							}
							//si no existe valor en el Siguiente anterior
							else if(!existeValor(i,sigteAnterior)){
								//me fijo en la columna SigteSigteAnterior
								int sigteSigteAnterior=sigteAnterior-1;
							
								if(sigteSigteAnterior>=0) {
									if(existeValor(i,sigteSigteAnterior) 
									&& (getValor(i,actual)==getValor(i,sigteSigteAnterior))) {
										//los sumo
										setValor(i,j,(getValor(i,actual))+(getValor(i,sigteSigteAnterior)));
										//se quita el valor del cuadro anterior poque se sumo en el cuadro actual
										setValor(i, sigteSigteAnterior, 0);
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
		for(int i=0; i<tamanio; i++) {
			for(int j=tamanio-1; j>0; j--) {
				
				int actual=j;
				int anterior=j-1;
				int moverAFila=0;
				int moverAColumna=0;
				
				if(!existeValor(i, actual)) {
					//guardo la posicion del cuadro libre donde voy a mover y cuento +1
					moverAFila=i;
					moverAColumna=actual;
					
					if(!existeValor(i, anterior)) {
						
						//actualizo el anterior
						anterior=anterior-1;
						//verifico si esta en rango
						if(anterior>=0) {
							//ahora veo si esta vacio
							if(!existeValor(i, anterior)) {
								//actualizo el anterior
								anterior=anterior-1;
								//verifico si esta en rango
								if(anterior>=0) {
									//ahora veo si esta vacio
									if(existeValor(i, anterior)) {
										moverCuadro(i,anterior,moverAFila,moverAColumna);
									}
								}
							}
							else {
								moverCuadro(i,anterior,moverAFila,moverAColumna);
							}
						}
					}
					//si existe, lo muevo al cuadro vacio que guarde
					else {
						moverCuadro(i,anterior,moverAFila,moverAColumna);
					}
					
				}
			}
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	public void sumarIzquierda() {
		System.out.println("derecha");
		
		//fila a fila
		for(int i=0; i<4; i++) {
			//columna a columna de derecha a izquierda
			for(int j=0; j<tamanio; j++) {
				
				int actual=j;
				int siguiente=actual+1;
				
				if(siguiente<tamanio) {
					//veo si hay algo en la columna actual
					if(existeValor(i,actual)) {
						if(existeValor(i,siguiente) && (getValor(i,actual)==getValor(i,siguiente)) ) {
							//los sumo
							setValor(i,actual,(getValor(i,actual))+(getValor(i,siguiente)));
							//se quita el valor del cuadro anterior poque se sumo en el cuadro actual
							setValor(i, siguiente, 0);
						}
						//Si no hay nada en el cuadro anterior
						else if(!existeValor(i,siguiente)){
							//me fijo en la columna siguiente
							siguiente=siguiente+1;
							//Me aseguro que este dentro del rango de valores posibles
							if(siguiente<tamanio) {
								if(existeValor(i,siguiente) 
								&& (getValor(i,actual)==getValor(i,siguiente))) {
									//los sumo
									setValor(i,actual,(getValor(i,actual))+(getValor(i,siguiente)));
									//se quita el valor del cuadro anterior poque se sumo en el cuadro actual
									setValor(i, siguiente, 0);
								}
								//si no existe valor en el Siguiente anterior
								else if(!existeValor(i,siguiente)){
									//me fijo en la columna SigteSigteAnterior
									siguiente=siguiente+1;
								
									if(siguiente<tamanio) {
										if(existeValor(i,siguiente) 
										&& (getValor(i,actual)==getValor(i,siguiente))) {
											//los sumo
											setValor(i,actual,(getValor(i,actual))+(getValor(i,siguiente)));
											//se quita el valor del cuadro anterior poque se sumo en el cuadro actual
											setValor(i, siguiente, 0);
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
		for(int i=0; i<tamanio; i++) {
			for(int j=0; j<tamanio; j++) {
				
				int actual=j;
				int siguiente=j+1;
				int moverAFila=0;
				int moverAColumna=0;
				
				if(!existeValor(i, actual)) {
					//guardo la posicion del cuadro libre donde voy a mover
					moverAFila=i;
					moverAColumna=actual;
					
					//me fijo si esta en rango
					if(siguiente<tamanio) {
						
						if(!existeValor(i, siguiente)) {
							
							//actualizo el siguiente
							siguiente=siguiente+1;
							//verifico si esta en rango
							if(siguiente<tamanio) {
								//ahora veo si esta vacio
								if(!existeValor(i, siguiente)) {
									//actualizo el anterior
									siguiente=siguiente+1;
									//verifico si esta en rango
									if(siguiente<tamanio) {
										//ahora veo si esta vacio
										if(existeValor(i, siguiente)) {
											moverCuadro(i,siguiente,moverAFila,moverAColumna);
										}
									}
								}
								else {
									moverCuadro(i,siguiente,moverAFila,moverAColumna);
								}
							}
						}
						//si existe, lo muevo al cuadro vacio que guarde
						else {
							moverCuadro(i,siguiente,moverAFila,moverAColumna);
						}
					}
				}
			}
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	public void moverArriba() {
		System.out.println("arriba");
		//agregarValor();
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	public void moverAbajo() {
		System.out.println("abajo");
		//agregarValor();
	}

	////////////////////////////////////////////////////////////////////////////////////////
	public void moverCuadro(int OrigenFila,int OrigenColumna,int DestinoFila,int DestinoColumna) {
		setValor(DestinoFila,DestinoColumna,getValor(OrigenFila,OrigenColumna));
		setValor(OrigenFila,OrigenColumna,0);	
	}
	////////////////////////////////////////////////////////////////////////////////////////
}
