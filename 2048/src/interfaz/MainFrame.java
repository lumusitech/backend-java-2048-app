package interfaz;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.SwingConstants;

import negocio.Tablero;
import javax.swing.JTextField;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;

import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.awt.Toolkit;
import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame{

	private JFrame ventana;
	
	private JPanel contenedorDeCuadros;
	private JTextField[][] cuadros;
	private int cuadroPosX;
	private int cuadroPosY;
	private int cuadrosTamanio;
	
	private Tablero tableroDeValores;
	
	private JTextField cuadroPuntaje;
	private String puntajeAMostrar;
	
	private JTextField cuadroRecord;
	private String recordAMostrar; 
	
	private JPanel contenedorDePuntajesHistoricos;
	private JTextField [] cuadrosRecordsHistoricos; 
	
	private JTextArea cuadroDeMsj;
	
	//Lanza la aplicacion
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.ventana.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Constructor: crea la aplicacion
	public MainFrame() {
		initialize();
	}

	//Inicializa los contenidos de la ventana
	private void initialize() {
		
		ventanaPrincipal();
		
		tableroDeJuego();
		
		puntaje();
		actualizarPuntaje();
		
		record();
		recordsHistoricos();
		actualizarRecord();
		
		usuarioNuevo();
		
		cuadroDeMsjAlUsuario();
		
		setMsj("Hola "+tableroDeValores.getUsuario()+" Bienvenid@ a 2048");
		
		botonJuegoNuevo();
	}

	////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////METODOS AUXILIARES/////////////////////////////////////
	
	public void ventanaPrincipal () {
		ventana = new JFrame();
		ventana.setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/interfaz/icon.png")));
		ventana.getContentPane().setBackground(new Color(250, 248, 239));
		ventana.setTitle("2048");
		ventana.setBounds(100, 100, 640, 500);
		ventana.setResizable(false);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.getContentPane().setLayout(null);
		
		//Ttulo
		JLabel titulo = new JLabel("2048");
		titulo.setBounds(120, 11, 158, 50);
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(new Font("Times New Roman", Font.BOLD, 50));
		titulo.setForeground(new Color(143,122,102));
		ventana.getContentPane().add(titulo);
		
		///////////Barra de menu//////////
		JMenuBar menuBar = new JMenuBar();
		ventana.setJMenuBar(menuBar);
		
		///////////Menu Archivo////////////////
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		//////////items de Archivo/////////////
		
		//Archivo --> Juego nuevo
		JMenuItem mntmJuegoNuevo = new JMenuItem("Juego nuevo");
		mntmJuegoNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				reinicioOnuevo("nuevo");
			}
		});
		mntmJuegoNuevo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mnArchivo.add(mntmJuegoNuevo);
		
		//Archivo --> Reiniciar
		JMenuItem mntmReiniciar = new JMenuItem("Reiniciar");
		mntmReiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcion=JOptionPane.showConfirmDialog(ventana,"Seguro que quieres reiniciar el juego?");
        		if(opcion==0) {
        			reinicioOnuevo("reinicio");
        		}
			}
		}); 
		mntmReiniciar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
		mnArchivo.add(mntmReiniciar);
				
		//Archivo --> Salir
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcion=JOptionPane.showConfirmDialog(ventana,"Seguro que quieres salir del juego?");
        		if(opcion==0) {
        			System.exit(0);
        		}
			}
		});
		mntmSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnArchivo.add(mntmSalir);
		
		
		
		//////////Menu Ayuda///////////////
		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		
		//////////////////////////Items de Ayuda//////////////////////////////////
		
		//Archivo --> Acerca De
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca De");
		mntmAcercaDe.setToolTipText("Informaci\u00F3n del juego y su desarrollo");
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				try {
				     File ruta = new File ("src/interfaz/informe.pdf");
				     Desktop.getDesktop().open(ruta);
				}catch (IOException ex) {
				     ex.printStackTrace();
				}
			}
		});
		mntmAcercaDe.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mnAyuda.add(mntmAcercaDe);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////

	public void tableroDeJuego() {
		//Se crea el panel que contiene a los cuadros
		contenedorDeCuadros = new JPanel();
		contenedorDeCuadros.setBackground(new Color(187, 173, 160));
		contenedorDeCuadros.setBounds(56, 71, 290, 290);
		ventana.getContentPane().add(contenedorDeCuadros);
		contenedorDeCuadros.setLayout(null);
		
		//Se escuchan eventos de teclado del panel
		escucharTeclado(contenedorDeCuadros, null);
		
		//Se crea un nuevo tablero de valores (negocio)
		tableroDeValores = new Tablero();
		
		int tamanio= tableroDeValores.getTamanio();
		//Inicializan variables del tablero grafico y la posicion de los cuadros
		cuadros=new JTextField[tamanio][tamanio]; 
		cuadrosTamanio=60;
		cuadroPosX=10;
		cuadroPosY=10;
		int tamanioDeCuadro=70;
		int posicionYInicial=10;
		
		
		//Se crean los cuadros y se los ordena en el panel
		for(int i=0; i<tamanio; i++) {
			for(int j=0; j<tamanio; j++) {
				
				if(j!=0 && j<tamanio) {
					cuadroPosY+=tamanioDeCuadro;
				}
				cuadros[i][j]=new JTextField();
				cuadros[i][j].setBorder(null);
				cuadros[i][j].setBackground(new Color(205,193,180));
				cuadros[i][j].setFont(new Font("Tahoma", Font.BOLD, 25));
				cuadros[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				cuadros[i][j].setEditable(false);
				cuadros[i][j].setFocusable(false);
				cuadros[i][j].setBounds(cuadroPosY, cuadroPosX, cuadrosTamanio, cuadrosTamanio);
				contenedorDeCuadros.add(cuadros[i][j]);
				cuadros[i][j].setColumns(10);
				
				if(j==tamanio-1) {
					cuadroPosX+=tamanioDeCuadro;
					cuadroPosY=posicionYInicial;
				}
			}
		}
		actualizarTableroGrafico();
		
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	
	public void puntaje() {
		cuadroPuntaje = new JTextField();
		cuadroPuntaje.setFont(new Font("Tahoma", Font.BOLD, 25));
		cuadroPuntaje.setHorizontalAlignment(SwingConstants.RIGHT);
		cuadroPuntaje.setFocusable(false);
		cuadroPuntaje.setEditable(false);
		cuadroPuntaje.setBorder(null);
		cuadroPuntaje.setBackground(new Color(187,173,160));
		cuadroPuntaje.setForeground(Color.WHITE);
		cuadroPuntaje.setBounds(400, 71, 90, 50);
		ventana.getContentPane().add(cuadroPuntaje);
		
		JLabel lblPuntaje = new JLabel("Puntaje");
		lblPuntaje.setForeground(new Color(143,122,102));
		lblPuntaje.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPuntaje.setHorizontalAlignment(SwingConstants.CENTER);
		lblPuntaje.setBounds(400, 46, 90, 23);
		ventana.getContentPane().add(lblPuntaje);
	}
	
	public void record() {
		
		JLabel lblRecord = new JLabel("Record");
		lblRecord.setForeground(new Color(143,122,102));
		lblRecord.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecord.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblRecord.setBounds(513, 46, 90, 23);
		ventana.getContentPane().add(lblRecord);
		
		cuadroRecord = new JTextField();
		cuadroRecord.setFont(new Font("Tahoma", Font.BOLD, 25));
		cuadroRecord.setHorizontalAlignment(SwingConstants.RIGHT);
		cuadroRecord.setFocusable(false);
		cuadroRecord.setEditable(false);
		cuadroRecord.setBorder(null);
		cuadroRecord.setBackground(new Color(187,173,160));
		cuadroRecord.setForeground(Color.WHITE);
		cuadroRecord.setBounds(513, 71, 90, 50);
		ventana.getContentPane().add(cuadroRecord);
	}
	
	
	private void recordsHistoricos() {
		
		contenedorDePuntajesHistoricos = new JPanel();
		contenedorDePuntajesHistoricos.setBackground(new Color(187, 173, 160));
		contenedorDePuntajesHistoricos.setBounds(400, 162, 204, 199);
		ventana.getContentPane().add(contenedorDePuntajesHistoricos);
		contenedorDePuntajesHistoricos.setLayout(null);
		
		JLabel lblRecords = new JLabel("Records hist\u00F3ricos");
		lblRecords.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecords.setForeground(new Color(143, 122, 102));
		lblRecords.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblRecords.setBounds(410, 132, 189, 25);
		ventana.getContentPane().add(lblRecords);
		
		cuadrosRecordsHistoricos = new JTextField[5];
		int posicionX = 10;
		int posicionY = 11;
		int distanciaSiguienteCuadro = 38;
		for (int i = 0; i < cuadrosRecordsHistoricos.length; i++) {
			
			cuadrosRecordsHistoricos[i]=new JTextField();
			cuadrosRecordsHistoricos[i].setBorder(null);
			cuadrosRecordsHistoricos[i].setBackground(new Color(205,193,180));
			cuadrosRecordsHistoricos[i].setForeground(Color.WHITE);
			cuadrosRecordsHistoricos[i].setFont(new Font("Tahoma", Font.BOLD, 15));
			cuadrosRecordsHistoricos[i].setHorizontalAlignment(SwingConstants.CENTER);
			cuadrosRecordsHistoricos[i].setFocusable(false);
			
			if(i==0) {
				cuadrosRecordsHistoricos[i].setBounds(posicionX, (posicionY), 184, 27);
			}
			else {
				cuadrosRecordsHistoricos[i].setBounds(posicionX, (posicionY+=distanciaSiguienteCuadro), 184, 27);
			}
			cuadrosRecordsHistoricos[i].setColumns(10);
			contenedorDePuntajesHistoricos.add(cuadrosRecordsHistoricos[i]);
		}
		
		actualizarRecordsHistoricos();
	}
	
	public void actualizarRecordsHistoricos() {
		for (int i = 0; i < cuadrosRecordsHistoricos.length; i++) {
			
			String usuarioConRecord = tableroDeValores.getUsuarioConRecord(i);
			String recordDeUsuario = Integer.toString(tableroDeValores.getRecord(i));
			String nivelDeUsuario = tableroDeValores.getNivelUsado(i);
			
			recordAMostrar = usuarioConRecord;
			
			if(Integer.parseInt(recordDeUsuario)!=0) {
				cuadrosRecordsHistoricos[i].setText(usuarioConRecord+" "+recordDeUsuario);
			}
			
			
			if(nivelDeUsuario.equals("principiante")) {
				cuadrosRecordsHistoricos[i].setBackground(new Color(100,193,100));
			}
			else if(nivelDeUsuario.equals("intermedio")) {
				cuadrosRecordsHistoricos[i].setBackground(new Color(193,193,100));
			}
			else if(nivelDeUsuario.equals("experto")) {
				cuadrosRecordsHistoricos[i].setBackground(new Color(193,100,100));
			}
			else {
				cuadrosRecordsHistoricos[i].setBackground(new Color(205,193,180));
			}
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	public void escucharTeclado(JPanel panel, JButton boton){
		Object o = new Object();
		if(panel != null) {
			o = panel;
			((Component) o).setFocusable(true);
		}
		else {
			o = boton;
			((Component) o).setFocusable(false);
		}
		
		((Component) o).addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e){
				
				//metodo que controla el movimiento de teclas
				controlDeMovimiento(e);

                if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
                	
                	String usuarioActual = tableroDeValores.getUsuario();
                	setMsj("No te rindas "+usuarioActual+" llevas solo "+puntajeAMostrar+" puntos :(");
                	
                	int opcion=JOptionPane.showConfirmDialog(ventana,"Seguro que quieres salir del juego?");
            		if(opcion==0) {
            			System.exit(0);
            		}
            		else {
            			setMsj(":) Sabía que lo reconsiderarías");
            		}
                }
                
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                	int opcion=JOptionPane.showConfirmDialog(ventana,"Seguro que quieres reiniciar el juego?");
            		if(opcion==0) {
            			reinicioOnuevo("reinicio");
            		}
                }
                
            }
		});
	}
	
	public void controlDeMovimiento(KeyEvent e) {
		String tecla="";
		int codigoTecla=0;
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			tecla="derecha";
			codigoTecla = KeyEvent.VK_RIGHT;
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			tecla="izquierda";
			codigoTecla = KeyEvent.VK_LEFT;
		}
		if(e.getKeyCode()==KeyEvent.VK_UP) {
			tecla="arriba";
			codigoTecla = KeyEvent.VK_UP;
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			tecla="abajo";
			codigoTecla = KeyEvent.VK_DOWN;
		}
		
	    if(e.getKeyCode()==codigoTecla){
	    	cuadroDeMsjAlUsuario();
	    	if(tableroDeValores.mover(tecla)) {
	    		actualizarTableroGrafico();
	    		actualizarPuntaje();
	    		
	    		//Si el puntaje supera el record ya lo empieza a mostrar como record
	    		if(tableroDeValores.getPuntaje() > tableroDeValores.getRecord(0)) {
	    			puntajeAMostrar = Integer.toString(tableroDeValores.getPuntaje());
	    			cuadroRecord.setText(puntajeAMostrar);
	    			
	    			setMsj("Excelente! Lograste superar el record de "+tableroDeValores.getRecord(0));
	    		}
	    	}
	      	else {
		  		int opcion=JOptionPane.showConfirmDialog(ventana,"Fin del juego - Desea reintentar?");
		  		if(opcion==0) {
					seleccionDeNivel();
					reinicioOnuevo("reinicio");
		  		}
		  		else if(opcion==1) {
		  			System.exit(0);
		  		}
	      	}
	    }
	}

	public void seleccionDeNivel() {
		
		Object [] niveles ={"principiante","intermedio","experto"};
		Object opcion = JOptionPane.showInputDialog(ventana,"Selecciona un Nivel", "Elegir",JOptionPane.QUESTION_MESSAGE,null,niveles, niveles[0]);
		String dificultad = (String)opcion;
		if(dificultad!=null) {
			tableroDeValores.setNivel(dificultad);
		}
	}
	
	
	////////////////////////////////////////////////////////////////////////////////////////
	public void actualizarTableroGrafico() {
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				//Se recuperan los valores del tablero de negocio
				String valor=Integer.toString(tableroDeValores.getValor(i, j));
				//Se actualiza el valor de cada cuadro y su color de fondo
				actualizarCuadros(cuadros[i][j], valor);
			}
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
		
	//Este metodo actualiza la vista grafica del tablero (cuadros), valores y colores
	public void actualizarCuadros(JTextField cuadro, String valor) {
		if(!valor.equals("0")) {
		cuadro.setText(valor);
	
			//De acuerdo al valor que posse el cuadro, le da un color de texto y de fondo
			if(cuadro.getText().equals("2")) {
				cuadro.setBackground(new Color(238,228,218));
				cuadro.setForeground(new Color(119,110,101));
			}
			else if(cuadro.getText().equals("4")){
				cuadro.setBackground(new Color(237,224,200));
				cuadro.setForeground(new Color(119,110,101));
			}
			
			//A partir de acï¿½ hay que configurar los colores, hacer cuando se halla resuelto la funcion de las teclas
			else if(cuadro.getText().equals("8")){
				cuadro.setBackground(new Color(242,177,121));
				cuadro.setForeground(new Color(249,246,242));
			}
			else if(cuadro.getText().equals("16")){
				cuadro.setBackground(new Color(245,149,99));
				cuadro.setForeground(new Color(249,246,242));
			}
			else if(cuadro.getText().equals("32")){
				cuadro.setBackground(new Color(246,124,95));
				cuadro.setForeground(new Color(249,246,242));
			}
			else if(cuadro.getText().equals("64")){
				cuadro.setBackground(new Color(246,94,59));
				cuadro.setForeground(new Color(249,246,242));
			}
			else if(cuadro.getText().equals("128")){
				cuadro.setBackground(new Color(237,207,114));
				cuadro.setForeground(new Color(249,246,242));
			}
			else if(cuadro.getText().equals("256")){
				cuadro.setBackground(new Color(237,207,114));
				cuadro.setForeground(new Color(249,246,242));
			}
			else if(cuadro.getText().equals("512")){
				cuadro.setBackground(new Color(236,200,80));
				cuadro.setForeground(new Color(249,246,242));
			}
			else if(cuadro.getText().equals("1024")){
				cuadro.setBackground(new Color(237,197,63));
				cuadro.setForeground(new Color(249,246,242));
			}
			else if(cuadro.getText().equals("2048")){
				cuadro.setBackground(new Color(238,194,46));
				cuadro.setForeground(new Color(249,246,242));
			}
			else {
				cuadro.setBackground(new Color(61,58,51));
				cuadro.setForeground(new Color(249,246,242));
			}
		}
		//Si es cero
		else {
			cuadro.setText(null);
			cuadro.setBackground(new Color(205,193,180));
		}
			
	}


	////////////////////////////////////////////////////////////////////////////////////////
	public void botonJuegoNuevo() {
		
		JButton btnJuegoNuevo = new JButton("Juego nuevo");
		
		//se estiliza el botï¿½n juegoNuevo y se lo agrega
		btnJuegoNuevo.setBorder(null);//saca los bordes que vienen por defecto
		btnJuegoNuevo.setFocusable(false);//evita que se vea un cuadro punteado alrededor del texto del boton
		btnJuegoNuevo.setBackground(new Color(143,122,102));
		btnJuegoNuevo.setForeground(new Color(249,246,242));
		btnJuegoNuevo.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnJuegoNuevo.setBounds(135, 389, 136, 35);
		ventana.getContentPane().add(btnJuegoNuevo);
		
		//El botï¿½n de juego nuevo esta a la escucha de eventos
		btnJuegoNuevo.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				reinicioOnuevo("nuevo");
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				btnJuegoNuevo.setBackground(new Color(131,110,90).darker());
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				btnJuegoNuevo.setBackground(new Color(143,122,102));
			}
			
			public void mousePressed(MouseEvent e) {
				btnJuegoNuevo.setForeground(new Color(143,122,102).darker());
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				btnJuegoNuevo.setForeground(new Color(249,246,242));
			}
			
		});
		
		//Despues de reiniciar, se debe indicar de nuevo que escuche eventos de teclado del panel
		escucharTeclado(null, btnJuegoNuevo);
	}
	
	public void usuarioNuevo() {
		String usuarioIngresado = JOptionPane.showInputDialog("Ingrese su nombre de usuario: ");
		if(usuarioIngresado!=null) {
			tableroDeValores.setUsuario(usuarioIngresado);
		}
	}

	public void cuadroDeMsjAlUsuario() {

		cuadroDeMsj = new JTextArea(3, 5);//número de filas y columnas
		cuadroDeMsj.setLineWrap(true);
		cuadroDeMsj.setWrapStyleWord(true);//para que haga salto de linea al final sin cortar palabras
		cuadroDeMsj.setBounds(400, 372, 204, 57);
		cuadroDeMsj.setBackground(new Color(187,173,160)); 
		cuadroDeMsj.setForeground(Color.WHITE);
		cuadroDeMsj.setFont(new Font("Tahoma", Font.BOLD, 15));
		cuadroDeMsj.setFocusable(false);
		cuadroDeMsj.setBorder(null);
		cuadroDeMsj.setMargin(new Insets(200,500,200,200));

		ventana.getContentPane().add(cuadroDeMsj);
	}
	
	public void reinicioOnuevo(String eleccion) {
		//Se pide al tablero de negocio que se reinicie
		tableroDeValores.reiniciar();
		
		//Se recorren los cuadros graficos
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				
				//Se borran los textos de los cuadros del juego anterior
				cuadros[i][j].setText(null);
				
				//Se vuelve a poner el color de fondo inicial
				cuadros[i][j].setBackground(new Color(205,193,180));				
			}	
		}
		
		actualizarPuntaje();
		actualizarRecord();
		actualizarRecordsHistoricos();
		
		if(eleccion.equals("nuevo")) {
			usuarioNuevo();
			seleccionDeNivel();
			
			setMsj("Hola "+tableroDeValores.getUsuario()+" Bienvenido a 2048");
		}
		
		actualizarTableroGrafico();
	}

	public void actualizarPuntaje() {
		puntajeAMostrar = Integer.toString(tableroDeValores.getPuntaje());
		cuadroPuntaje.setText(puntajeAMostrar);
	}
	public void actualizarRecord() {
		recordAMostrar = Integer.toString(tableroDeValores.getRecord(0));
		cuadroRecord.setText(recordAMostrar);
	}
	
	public void setMsj(String msj) {
		cuadroDeMsj.selectAll();//selecciona todo el texto del jTextArea
		cuadroDeMsj.replaceSelection(msj);//Reemplaza la seleccion con el texto que recibe
	}
}
