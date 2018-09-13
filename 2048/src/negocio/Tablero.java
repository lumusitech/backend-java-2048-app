package negocio;

import java.util.Random;

public class Tablero {
	private int[][] tablero;
	private int tamanio;
	private int fila;
	private int columna;
	private int puntaje;
	private int record;
	
	private boolean sumaDerechaEfectuada;
	private boolean sumaIzquierdaEfectuada;
	private boolean sumaArribaEfectuada;
	private boolean sumaAbajoEfectuada;
	private boolean movDerechaEfectuado;
	private boolean movIzquierdaEfectuado;
	private boolean movArribaEfectuado;
	private boolean movAbajoEfectuado;

	public Tablero() {

		tamanio = 4;
		tablero = new int[tamanio][tamanio];
		puntaje = 0;
		record = 0;
		
		sumaDerechaEfectuada = true;
		sumaIzquierdaEfectuada = true;
		sumaArribaEfectuada = true;
		sumaAbajoEfectuada = true;
		movDerechaEfectuado = true;
		movIzquierdaEfectuado = true;
		movArribaEfectuado = true;
		movAbajoEfectuado = true;
		

		agregarValoresIniciales();
	}

	////////////////////////////////////////////////////////////////////////////////////////
	public boolean agregarValorRandom() {
		if (lugarAleatorio()) {
			// busca un lugar aleatorio disponible
			setValor(fila, columna, dosOCuatro());// asigna a ese lugar dos o cuatro aleatoriamente
			System.out.println("se agrega el cuadro aleatorio: (" + fila + "," + columna + ")");
			return true;
		}
		System.out.println("No se puede agregar valor! Tablero completo");
		return false;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	private boolean lugarAleatorio() {
		Random r1 = new Random();
		Random r2 = new Random();

		int contador = 0;
		do {

			fila = r1.nextInt(4);
			columna = r2.nextInt(4);

			contador++;

		} while (existeValor(fila, columna) && contador < 10);

		// cuando el contador llego a 10 se pasa a recorrer la matriz completa
		// en busca de un lugar libre
		if (contador == 10) {
			if (!existeLugarDisponible()) {
				System.out.println("No existe lugar disponible");
				return false;
			}
		}
		return true;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	private int dosOCuatro() {
		Random r = new Random();
		if (r.nextInt(100) < 50) {
			return 2;
		}
		return 4;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	public boolean existeLugarDisponible() {

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (!existeValor(i, j)) {
					// guarda la fila y columna que encontro vacï¿½a
					fila = i;
					columna = j;
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
		tablero[fila][columna] = valor;
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
	
	public boolean getSumaDerechaEfectuada() {
		return sumaDerechaEfectuada;
	}
	
	public boolean getMovDerechaEfectuado() {
		return movDerechaEfectuado;
	}
	
	public boolean getSumaIzquierdaEfectuada() {
		return sumaIzquierdaEfectuada;
	}
	
	public boolean getMovIzquierdaEfectuado() {
		return movIzquierdaEfectuado;
	}
	
	public boolean getSumaArribaEfectuada() {
		return sumaArribaEfectuada;
	}
	
	public boolean getMovArribaEfectuado() {
		return movArribaEfectuado;
	}
	
	public boolean getSumaAbajoEfectuada() {
		return sumaAbajoEfectuada;
	}
	
	public boolean getMovAbajoEfectuado() {
		return movAbajoEfectuado;
	}


	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public void agregarValoresRandom(int cant) {
		for (int i = 0; i < cant; i++) {
			agregarValorRandom();
		}
	}

	public void setFilaCompletaConValor(int fila, int valor) {

		for (int columna = 0; columna < getTamanio(); columna++) {
			setValor(fila, columna, valor);
		}
	}

	public void setColumnaCompletaConValor(int columna, int valor) {

		for (int fila = 0; fila < getTamanio(); fila++) {
			setValor(fila, columna, valor);
		}
	}

	public boolean verificarValoresEnColumnaCompleta(int columna, int valor) {
		boolean ret = false;
		for (int fila = 0; fila < getTamanio(); fila++) {
			if (getValor(fila, columna) == valor) {
				ret = true;
			} else {
				return false;
			}
		}

		return ret;
	}

	public boolean verificarValoresEnFilaCompleta(int fila, int valor) {
		boolean ret = false;
		for (int columna = 0; columna < getTamanio(); columna++) {
			if (getValor(fila, columna) == valor) {
				ret = true;
			} else {
				return false;
			}
		}

		return ret;
	}

	public boolean verificarValoresEnTableroCompleto(int valor) {
		boolean ret = false;
		for (int fila = 0; fila < getTamanio(); fila++) {
			for (int columna = 0; columna < getTamanio(); columna++) {
				if (getValor(fila, columna) == 0) {
					ret = true;
				} else {
					return false;
				}
			}
		}

		return ret;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	public void reiniciar() {

		vaciarTablero();

		controlDePuntajes();
		
		resetearSumasYMov();

		agregarValoresIniciales();

	}
	
	////////////////////////////////////////////////////////////////////////////////////////

	public void agregarValoresIniciales() {
		// Agrega el 1er valor al tablero (posicion y valor aleatorio)
		agregarValorRandom();
		int filaPrimerValor = fila;
		int columnaPrimerValor = columna;

		// Agrega el 2do valor al tablero (posiciï¿½n y valor aleatorio)
		agregarValorRandom();
		int filaSegundoValor = fila;
		int columnaSegundoValor = columna;

		System.out.println("Primer Cuadro--->posiciï¿½n(" + filaPrimerValor + ", " + columnaPrimerValor + ")");
		System.out.println("Segundo Cuadro--->posiciï¿½n(" + filaSegundoValor + ", " + columnaSegundoValor + ")");
		System.out.println();
	}
	
	////////////////////////////////////////////////////////////////////////////////////////

	public void controlDePuntajes() {
		// guarda el ultimo puntaje si es mayor al record
		if (getPuntaje() > getRecord()) {
			setRecord(getPuntaje());
			// resetea el puntaje
		    setPuntaje(0);
		}
		else {			
			// resetea el puntaje
		    setPuntaje(0);
		}		
	}
	
	////////////////////////////////////////////////////////////////////////////////////////

	public void setRecord(int puntaje) {
		record=puntaje;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////

	public void vaciarTablero() {
		for (int i = 0; i < tamanio; i++) {
			for (int j = 0; j < tamanio; j++) {
				// Pone a todas las posiciones en cero
				setValor(i, j, 0);
			}
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////	
	
	public boolean mover(String direccion) {
		
		if (direccion.equals("derecha")) {
			
			sumaDerechaEfectuada = sumarDerecha();
			movDerechaEfectuado = moverDerecha();

			if(movDerechaEfectuado || sumaDerechaEfectuada) {
				agregarValorRandom();
				resetearSumasYMov();
			}
		}

		if (direccion.equals("izquierda")) {
			
			sumaIzquierdaEfectuada = sumarIzquierda();
			movIzquierdaEfectuado = moverIzquierda();

			if(movIzquierdaEfectuado || sumaIzquierdaEfectuada) {
				agregarValorRandom();
				resetearSumasYMov();
			}
		}

		if (direccion.equals("arriba")) {
			
			sumaArribaEfectuada = sumarArriba();
			movArribaEfectuado = moverArriba();

			if(movArribaEfectuado || sumaArribaEfectuada) {
				agregarValorRandom();
				resetearSumasYMov();
			}
		}

		if (direccion.equals("abajo")) {
			
			sumaAbajoEfectuada = sumarAbajo();
			movAbajoEfectuado = moverAbajo();

			if(movAbajoEfectuado || sumaAbajoEfectuada) {
				agregarValorRandom();
				resetearSumasYMov();
			}
		}
		
		return juegoActivo();
	}
	
	////////////////////////////////////////////////////////////////////////////////////////

	public boolean juegoActivo() {
		
		//si no hay sumas ni movmientos posibles el juego ya no estará activo, termina
		if( (!getSumaDerechaEfectuada()    &&   !getMovDerechaEfectuado()) 
		&&  (!getSumaIzquierdaEfectuada()  &&   !getMovIzquierdaEfectuado())
		&&  (!getSumaArribaEfectuada()     &&   !getMovArribaEfectuado())
		&&  (!getSumaAbajoEfectuada()      &&   !getMovAbajoEfectuado()) ) {
			return false;
		}
		
		return true;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	
	private void resetearSumasYMov() {
		
		sumaDerechaEfectuada = true;
		sumaIzquierdaEfectuada = true;
		sumaArribaEfectuada = true;
		sumaAbajoEfectuada = true;
		
		movDerechaEfectuado = true;
		movIzquierdaEfectuado = true;
		movArribaEfectuado = true;
		movAbajoEfectuado = true;
		
	}

	////////////////////////////////////////////////////////////////////////////////////////
	
	public boolean sumarDerecha() {
		boolean ret = false;
		for (int fila = 0; fila < tamanio; fila++) {
			for (int columna = tamanio-1; columna > 0; columna--) {
				ret = sumarDerecha(fila, columna, columna-1);
			}
		}
		return ret;
	}
	
	private boolean sumarDerecha(int fila, int actual, int anterior) {
		if(anterior >= 0 && existeValor(fila, actual)) {
			if(!existeValor(fila, anterior)) {
				return sumarDerecha(fila, actual, anterior-1);
			}
			else if ((getValor(fila, actual) == getValor(fila, anterior))){
				sumarCuadrosDeFila(fila, actual, anterior);
				return true;
			}
		}
		return false;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	
	public boolean moverDerecha() {
		boolean ret = false;
		for (int i = 0; i < tamanio; i++) {
			for (int j = tamanio - 1; j > 0; j--) {

				int actual = j;
				int anterior = j - 1;
				int moverAFila = 0;
				int moverAColumna = 0;

				if (!existeValor(i, actual)) {
					// guardo la posicion del cuadro libre donde voy a mover y cuento +1
					moverAFila = i;
					moverAColumna = actual;

					if (!existeValor(i, anterior)) {

						// actualizo el anterior
						anterior = anterior - 1;
						// verifico si esta en rango
						if (anterior >= 0) {
							// ahora veo si esta vacio
							if (!existeValor(i, anterior)) {
								// actualizo el anterior
								anterior = anterior - 1;
								// verifico si esta en rango
								if (anterior >= 0) {
									// ahora veo si esta vacio
									if (existeValor(i, anterior)) {
										moverCuadro(i, anterior, moverAFila, moverAColumna);
										//avisa que se pudo mover
										ret =  true;
									}
								}
							} else {
								moverCuadro(i, anterior, moverAFila, moverAColumna);
								//avisa que se pudo mover
								ret =  true;
							}
						}
					}
					// si existe, lo muevo al cuadro vacio que guarde
					else {
						moverCuadro(i, anterior, moverAFila, moverAColumna);
						//avisa que se pudo mover
						ret =  true;
					}

				}
			}
		}
		
		//avisa que se NO pudo mover
		return ret;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	
	public boolean sumarIzquierda() {
		boolean ret = false;
		for (int fila = 0; fila < tamanio; fila++) {
			for (int columna = 0; columna < tamanio; columna++) {
				ret = sumarIzquierda(fila, columna, columna+1);
			}
		}
		return ret;
	}
	
	private boolean sumarIzquierda(int fila, int actual, int siguiente) {
		if(siguiente < tamanio-1 && existeValor(fila, actual)) {
			if(!existeValor(fila, siguiente)) {
				return sumarIzquierda(fila, actual, siguiente+1);
			}
			else if ( getValor(fila, actual) == getValor(fila, siguiente) ){
				sumarCuadrosDeFila(fila, actual, siguiente);
				return true;
			}
		}
		return false;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////

	public boolean moverIzquierda() {
		boolean ret = false;
		for (int i = 0; i < tamanio; i++) {
			for (int j = 0; j < tamanio; j++) {

				int actual = j;
				int siguiente = j + 1;
				int moverAFila = 0;
				int moverAColumna = 0;

				if (!existeValor(i, actual)) {
					// guardo la posicion del cuadro libre donde voy a mover
					moverAFila = i;
					moverAColumna = actual;

					// me fijo si esta en rango
					if (siguiente < tamanio) {

						if (!existeValor(i, siguiente)) {

							// actualizo el siguiente
							siguiente = siguiente + 1;
							// verifico si esta en rango
							if (siguiente < tamanio) {
								// ahora veo si esta vacio
								if (!existeValor(i, siguiente)) {
									// actualizo el anterior
									siguiente = siguiente + 1;
									// verifico si esta en rango
									if (siguiente < tamanio) {
										// ahora veo si esta vacio
										if (existeValor(i, siguiente)) {
											moverCuadro(i, siguiente, moverAFila, moverAColumna);
											//avisa que se pudo mover
											ret =  true;
										}
									}
								} else {
									moverCuadro(i, siguiente, moverAFila, moverAColumna);
									//avisa que se pudo mover
									ret =  true;
								}
							}
						}
						// si existe, lo muevo al cuadro vacio que guarde
						else {
							moverCuadro(i, siguiente, moverAFila, moverAColumna);
							//avisa que se pudo mover
							ret =  true;
						}
					}
				}
			}
		}
		//avisa que se NO pudo mover
		return ret;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	
	public boolean sumarArriba() {
		boolean ret = false;
		for (int columna = 0; columna < tamanio; columna++) {
			for (int fila = 0; fila < tamanio; fila++) {
				ret = sumarArriba(columna, fila, fila+1);
			}
		}
		return ret;
	}
	
	private boolean sumarArriba(int columna, int actual, int siguiente) {
		if(siguiente < tamanio && existeValor(actual, columna)) {
			if(!existeValor(siguiente, columna)) {
				return sumarArriba(columna, actual, siguiente+1);
			}
			else if ( getValor(actual, columna) == getValor(siguiente, columna) ){
				sumarCuadrosDeColumna(columna, actual, siguiente);
				return true;
			}
		}
		return false;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	public boolean moverArriba() {
		boolean ret = false;
		for (int j = 0; j < tamanio; j++) {
			for (int i = 0; i < tamanio; i++) {

				int actual = i;
				int siguiente = i + 1;
				int moverAFila = 0;
				int moverAColumna = 0;

				if (!existeValor(actual, j)) {
					// guardo la posicion del cuadro libre donde voy a mover
					moverAFila = actual;
					moverAColumna = j;

					// me fijo si esta en rango
					if (siguiente < tamanio) {

						if (!existeValor(siguiente, j)) {

							// actualizo el siguiente
							siguiente = siguiente + 1;
							// verifico si esta en rango
							if (siguiente < tamanio) {
								// ahora veo si esta vacio
								if (!existeValor(siguiente, j)) {
									// actualizo el anterior
									siguiente = siguiente + 1;
									// verifico si esta en rango
									if (siguiente < tamanio) {
										// ahora veo si esta vacio
										if (existeValor(siguiente, j)) {
											moverCuadro(siguiente, j, moverAFila, moverAColumna);
											//avisa que se pudo mover
											ret =  true;
										}
									}
								} else {
									moverCuadro(siguiente, j, moverAFila, moverAColumna);
									//avisa que se pudo mover
									ret =  true;
								}
							}
						}
						// si existe, lo muevo al cuadro vacio que guarde
						else {
							moverCuadro(siguiente, j, moverAFila, moverAColumna);
							//avisa que se pudo mover
							ret =  true;
						}
					}
				}
			}
		}
		
		//avisa que NO se pudo mover
		return ret;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	
	public boolean sumarAbajo() {
		boolean ret = false;
		for (int columna = 0; columna < tamanio; columna++) {
			for (int fila = 3; fila > 0; fila--) {
				ret = sumarAbajo(columna, fila, fila-1);
			}
		}
		return ret;
	}
	
	private boolean sumarAbajo(int columna, int actual, int anterior) {
		if(anterior >= 0 && existeValor(actual, columna)) {
			if(!existeValor(anterior, columna)) {
				return sumarAbajo(columna, actual, anterior-1);
			}
			else if ((getValor(actual, columna) == getValor(anterior, columna))){
				sumarCuadrosDeColumna(columna, actual, anterior);
				return true;
			}
		}
		return false;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	public boolean moverAbajo() {
		boolean ret = false;
		for (int j = 0; j < tamanio; j++) {
			for (int i = tamanio - 1; i > 0; i--) {

				int actual = i;
				int anterior = i - 1;
				int moverAFila = 0;
				int moverAColumna = 0;

				if (!existeValor(actual, j)) {// 3
					// guardo la posicion del cuadro libre donde voy a mover y cuento +1
					moverAFila = actual;
					moverAColumna = j;

					if (!existeValor(anterior, j)) {// 2

						// actualizo el anterior
						anterior = anterior - 1;
						// verifico si esta en rango
						if (anterior >= 0) {
							// ahora veo si esta vacio
							if (!existeValor(anterior, j)) {// 1
								// actualizo el anterior
								anterior = anterior - 1;
								// verifico si esta en rango
								if (anterior >= 0) {
									// ahora veo si esta vacio
									if (existeValor(anterior, j)) {// 0
										moverCuadro(anterior, j, moverAFila, moverAColumna);
										//avisa que se pudo mover
										ret =  true;
									}
								}
							} else {// 1
								moverCuadro(anterior, j, moverAFila, moverAColumna);
								//avisa que se pudo mover
								ret =  true;
							}
						}
					}
					// si existe, lo muevo al cuadro vacio que guarde
					else {// 2
						moverCuadro(anterior, j, moverAFila, moverAColumna);
						//avisa que se pudo mover
						ret =  true;
					}

				}
			}
		}
		
		//avisa que NO se pudo mover
		return ret;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	public boolean existeValor(int fila, int columna) {
		return getValor(fila, columna) != 0;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	
	public void sumarCuadrosDeFila(int fila, int actual, int cuadroASumar) {
		// los sumo
		int suma = (getValor(fila, actual)) + (getValor(fila, cuadroASumar));
		setValor(fila, actual, suma);
		// se quita el valor del cuadro a sumar poque el resultado de la suma queda solo en el actual
		setValor(fila, cuadroASumar, 0);
		// sumo al puntaje
		puntaje += suma;
	}
	
	public void sumarCuadrosDeColumna(int columna, int actual, int cuadroASumar) {
		// los sumo
		int suma = (getValor(actual, columna)) + (getValor(cuadroASumar, columna));
		setValor(actual, columna, suma);
		// se quita el valor del cuadro a sumar poque el resultado de la suma queda solo en el actual
		setValor(cuadroASumar, columna, 0);
		// sumo al puntaje
		puntaje += suma;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	
	public void moverCuadro(int OrigenFila, int OrigenColumna, int DestinoFila, int DestinoColumna) {
		setValor(DestinoFila, DestinoColumna, getValor(OrigenFila, OrigenColumna));
		setValor(OrigenFila, OrigenColumna, 0);
	}
	////////////////////////////////////////////////////////////////////////////////////////
}
