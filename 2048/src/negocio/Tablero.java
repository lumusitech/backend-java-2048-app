package negocio;

import java.util.Random;

public class Tablero {
	private int [][] tablero;
	private int tamanio;
	private int fila;
	private int columna;
	private int puntaje;
	private int record;
	

	public Tablero() {
		
		tamanio=4;
		tablero=new int[tamanio][tamanio];
		puntaje=0;
		record=0;
		
		agregarValoresIniciales();
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	public boolean agregarValorRandom() {
		if(lugarAleatorio()) {
			//busca un lugar aleatorio disponible
			setValor(fila, columna, dosOCuatro());//asigna a ese lugar dos o cuatro aleatoriamente
			System.out.println("se agrega el cuadro aleatorio: ("+fila+","+columna+")");
			return true;
		}
		System.out.println("No se puede agregar valor! Tablero completo");
		return false;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	private boolean lugarAleatorio() {
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
	private int dosOCuatro() {
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
	
	public int getPuntaje() {
		return puntaje;
	}
	
	public int getRecord() {
		return record;
	}
	
	public int getTamanio() {
		return tamanio;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	public void reiniciar() {

		vaciarTablero();
		
		controlDePuntajes();
		
		agregarValoresIniciales();
		
	}

	public void agregarValoresIniciales() {
		//Agrega el 1er valor al tablero (posición y valor aleatorio)
		agregarValorRandom();
		int filaPrimerValor=fila;
		int columnaPrimerValor=columna;
	
		//Agrega el 2do valor al tablero (posición y valor aleatorio)
		agregarValorRandom();
		int filaSegundoValor=fila;
		int columnaSegundoValor=columna;
		
		System.out.println("Primer Cuadro--->posición("+filaPrimerValor+", "+columnaPrimerValor+")");
		System.out.println("Segundo Cuadro--->posición("+filaSegundoValor+", "+columnaSegundoValor+")");
		System.out.println();
	}

	public void controlDePuntajes() {
		//guarda el ultimo puntaje si es mayor al record
		if(puntaje>record) {
			record=puntaje;
		}
		
		//resetea el puntaje
		puntaje=0;
	}

	public void vaciarTablero() {
		for(int i=0; i<tamanio; i++) {
			for(int j=0; j<tamanio; j++) {
				//Pone a todas las posiciones en cero
				setValor(i,j,0);
			}
		}
	}
	
	public void setPuntaje(int puntaje) {
		this.puntaje=puntaje;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	
	public boolean mover(String direccion) {
		if(direccion.equals("derecha")) {
	
			sumarDerecha();//funciona sin problemas
			moverDerecha();//funcion casi completa
			//si logra agregar devuelve true
			return agregarValorRandom();
		}
		
		if(direccion.equals("izquierda")) {
			
			sumarIzquierda();//funciona sin problemas
			moverIzquierda();//funcion casi completa
			//si logra agregar, devuelve true
			return agregarValorRandom();
		}
		
		if(direccion.equals("arriba")) {
			sumarArriba();
			moverArriba();
			return agregarValorRandom();
		}
		
		if(direccion.equals("abajo")) {
			sumarAbajo();
			moverAbajo();
			return agregarValorRandom();
		}
		return false;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	
	public void sumarDerecha() {
		
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
						//sumo al puntaje
						puntaje+=(getValor(i,actual))+(getValor(i,anterior));
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
								//sumo al puntaje
								puntaje+=(getValor(i,actual))+(getValor(i,anterior));
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
										//sumo al puntaje
										puntaje+=(getValor(i,actual))+(getValor(i,anterior));
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
							//sumo al puntaje
							puntaje+=(getValor(i,actual))+(getValor(i,siguiente));
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
									//sumo al puntaje
									puntaje+=(getValor(i,actual))+(getValor(i,siguiente));
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
											//sumo al puntaje
											puntaje+=(getValor(i,actual))+(getValor(i,siguiente));
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
	
	public void moverIzquierda2() {
        //fila a fila
        for(int fila=0; fila<4; fila++) {
            //columna a columna
            for(int columna=3; columna>=0; columna--) {
                
                //me fijo si existe un valor en la columna actual
                int actual=columna;
                if(existeValor(fila, actual)) {
                    //veo en la columna anterior
                    int anterior=actual-1;
                    //me aseguro que este dentro del rango
                    if(anterior>=0) {
                        //Si no existe valor
                        if(!existeValor(fila, anterior)) {
                            //lo muevo ahi
                            setValor(fila,anterior,getValor(fila,actual));
                            setValor(fila, actual, 0);
                            //actualizo el nuevo actual y el nuevo anterior
                            actual=anterior;
                            anterior=anterior-1;
                            //me aseguro que este dentro del rango
                            if(anterior>=0) {
                                //Si no existe valor
                                if(!existeValor(fila, anterior)) {
                                    //lo muevo ahi
                                    setValor(fila,anterior,getValor(fila,actual));
                                    setValor(fila, actual, 0);
                                    //actualizo el nuevo actual y el nuevo anterior
                                    actual=anterior;
                                    anterior=anterior-1;
                                    //me aseguro que este dentro del rango
                                    if(anterior>=0) {
                                        //Si no existe valor
                                        if(!existeValor(fila, anterior)) {
                                            //lo muevo ahi
                                            setValor(fila,anterior,getValor(fila,actual));
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
	public void sumarArriba() {
		//fila a fila
		for(int j=0; j<4; j++) {
			//columna a columna de derecha a izquierda
			for(int i=0; i<tamanio; i++) {
				
				int actual=i;
				int siguiente=actual+1;
				
				if(siguiente<tamanio) {
					//veo si hay algo en la columna actual
					if(existeValor(actual,j)) {
						if(existeValor(siguiente,j) && (getValor(actual,j)==getValor(siguiente,j)) ) {
							//los sumo
							setValor(actual,j,(getValor(actual,j))+(getValor(siguiente,j)));
							//se quita el valor del cuadro anterior poque se sumo en el cuadro actual
							setValor(siguiente, j, 0);
							//sumo al puntaje
							puntaje+=(getValor(actual,j))+(getValor(siguiente,j));
						}
						//Si no hay nada en el cuadro anterior
						else if(!existeValor(siguiente,j)){
							//me fijo en la columna siguiente
							siguiente=siguiente+1;
							//Me aseguro que este dentro del rango de valores posibles
							if(siguiente<tamanio) {
								if(existeValor(siguiente,j) 
								&& (getValor(actual,j)==getValor(siguiente,j))) {
									//los sumo
									setValor(actual,j,(getValor(actual,j))+(getValor(siguiente,j)));
									//se quita el valor del cuadro anterior poque se sumo en el cuadro actual
									setValor(siguiente, j, 0);
									//sumo al puntaje
									puntaje+=(getValor(actual,j))+(getValor(siguiente,j));
								}
								//si no existe valor en el Siguiente anterior
								else if(!existeValor(siguiente,j)){
									//me fijo en la columna SigteSigteAnterior
									siguiente=siguiente+1;
								
									if(siguiente<tamanio) {
										if(existeValor(siguiente,j) 
										&& (getValor(actual,j)==getValor(siguiente,j))) {
											//los sumo
											setValor(actual,j,(getValor(actual,j))+(getValor(siguiente,j)));
											//se quita el valor del cuadro anterior poque se sumo en el cuadro actual
											setValor(siguiente, j, 0);
											//sumo al puntaje
											puntaje+=(getValor(actual,j))+(getValor(siguiente,j));
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
	public void moverArriba() {
		for(int j=0; j<tamanio; j++) {
			for(int i=0; i<tamanio; i++) {
				
				int actual=i;
				int siguiente=i+1;
				int moverAFila=0;
				int moverAColumna=0;
				
				if(!existeValor(actual, j)) {
					//guardo la posicion del cuadro libre donde voy a mover
					moverAFila=actual;
					moverAColumna=j;
					
					//me fijo si esta en rango
					if(siguiente<tamanio) {
						
						if(!existeValor(siguiente, j)) {
							
							//actualizo el siguiente
							siguiente=siguiente+1;
							//verifico si esta en rango
							if(siguiente<tamanio) {
								//ahora veo si esta vacio
								if(!existeValor(siguiente, j)) {
									//actualizo el anterior
									siguiente=siguiente+1;
									//verifico si esta en rango
									if(siguiente<tamanio) {
										//ahora veo si esta vacio
										if(existeValor(siguiente, j)) {
											moverCuadro(siguiente,j,moverAFila,moverAColumna);
										}
									}
								}
								else {
									moverCuadro(siguiente,j,moverAFila,moverAColumna);
								}
							}
						}
						//si existe, lo muevo al cuadro vacio que guarde
						else {
							moverCuadro(siguiente,j,moverAFila,moverAColumna);
						}
					}
				}
			}
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	public void sumarAbajo() {
		//fila a fila
		for(int j=0; j<4; j++) {
			//columna a columna de derecha a izquierda
			for(int i=3; i>0; i--) {
				
				int actual=i;
				int anterior=(i-1);
				
				//veo si hay algo en la columna actual
				if(existeValor(actual,j)) {
					if(existeValor(anterior,j) && (getValor(actual,j)==getValor(anterior,j)) ) {
						//los sumo
						setValor(actual,j,(getValor(actual,j))+(getValor(anterior,j)));
						//se quita el valor del cuadro anterior poque se sumo en el cuadro actual
						setValor(anterior, j, 0);
						//sumo al puntaje
						puntaje+=(getValor(actual,j))+(getValor(anterior,j));
					}
					//Si no hay nada en el cuadro anterior
					else if(!existeValor(anterior,j)){
						//me fijo en la columna siguiente Anterior
						anterior=anterior-1;
						//Me aseguro que este dentro del rango de valores posibles
						if(anterior>=0) {
							if(existeValor(anterior,j) 
							&& (getValor(actual,j)==getValor(anterior,j))) {
								//los sumo
								setValor(actual,j,(getValor(actual,j))+(getValor(anterior,j)));
								//se quita el valor del cuadro anterior poque se sumo en el cuadro actual
								setValor(anterior, j, 0);
								//sumo al puntaje
								puntaje+=(getValor(actual,j))+(getValor(anterior,j));
							}
							//si no existe valor en el Siguiente anterior
							else if(!existeValor(anterior,j)){
								//me fijo en la columna Sigte Sigte Anterior
								anterior=anterior-1;
								//Me aseguro que este dentro del rango de valores posibles
								if(anterior>=0) {
									if(existeValor(anterior,j) 
									&& (getValor(actual,j)==getValor(anterior,j))) {
										//los sumo
										setValor(actual,j,(getValor(actual,j))+(getValor(anterior,j)));
										//se quita el valor del cuadro anterior poque se sumo en el cuadro actual
										setValor(anterior, j, 0);
										//sumo al puntaje
										puntaje+=(getValor(actual,j))+(getValor(anterior,j));
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
	public void moverAbajo() {
		for(int j=0; j<tamanio; j++) {
			for(int i=tamanio-1; i>0; i--) {
				
				int actual=i;
				int anterior=i-1;
				int moverAFila=0;
				int moverAColumna=0;
				
				if(!existeValor(actual, j)) {
					//guardo la posicion del cuadro libre donde voy a mover y cuento +1
					moverAFila=actual;
					moverAColumna=j;
					
					if(!existeValor(anterior, j)) {
						
						//actualizo el anterior
						anterior=anterior-1;
						//verifico si esta en rango
						if(anterior>=0) {
							//ahora veo si esta vacio
							if(!existeValor(anterior, j)) {
								//actualizo el anterior
								anterior=anterior-1;
								//verifico si esta en rango
								if(anterior>=0) {
									//ahora veo si esta vacio
									if(existeValor(anterior, j)) {
										moverCuadro(anterior,j,moverAFila,moverAColumna);
									}
								}
							}
							else {
								moverCuadro(anterior,j,moverAFila,moverAColumna);
							}
						}
					}
					//si existe, lo muevo al cuadro vacio que guarde
					else {
						moverCuadro(anterior,j,moverAFila,moverAColumna);
					}
					
				}
			}
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	public boolean existeValor(int fila, int columna) {
		return getValor(fila,columna)!=0;
	}
	

	////////////////////////////////////////////////////////////////////////////////////////
	public void moverCuadro(int OrigenFila,int OrigenColumna,int DestinoFila,int DestinoColumna) {
		setValor(DestinoFila,DestinoColumna,getValor(OrigenFila,OrigenColumna));
		setValor(OrigenFila,OrigenColumna,0);	
	}
	////////////////////////////////////////////////////////////////////////////////////////
}
