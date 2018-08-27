package interfaz;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import negocio.Tablero;

import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame{

	private JFrame ventana;
	private JPanel contenedorDeCuadros;
	private Tablero tableroDeValores;
	private JTextField[][] cuadros;
	private int cuadroPosX;
	private int cuadroPosY;
	private int cuadrosTamanio;
	


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

	//crea la aplicacion
	public MainFrame() {
		initialize();
	}

	//Inicializa los contenidos de la ventana
	private void initialize() {
		
		//Se crea la ventana principal
		ventanaPrincipal();
		
		//Se crea el tablero de juego
		tableroDeJuego();
		
		//Se crea boton de juego nuevo
		botonJuegoNuevo();
	}

	
	
	
	////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////METODOS AUXILIARES/////////////////////////////////////
	
	public void ventanaPrincipal () {
		ventana = new JFrame();
		ventana.getContentPane().setBackground(new Color(250, 248, 239));
		ventana.setTitle("Juego 2048");
		ventana.setBounds(100, 100, 640, 480);
		ventana.setResizable(false);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.getContentPane().setLayout(null);
		
		
		//Ttulo
		JLabel titulo = new JLabel("2048");
		titulo.setBounds(120, 11, 158, 50);
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		//titulo.setForeground(new Color(119,110,101));
		titulo.setFont(new Font("Times New Roman", Font.BOLD, 50));
		titulo.setForeground(new Color(143,122,102));
		ventana.getContentPane().add(titulo);
		
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
		escucharPanel(contenedorDeCuadros);
		
		//Se crea un nuevo tablero de valores (negocio)
		tableroDeValores = new Tablero();
		
		//Inicializan variables del tablero grafico y la posicion de los cuadros
		cuadros=new JTextField[4][4];
		cuadrosTamanio=60;
		cuadroPosX=10;
		cuadroPosY=10;
		
		//Se crean los cuadros
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				
				if(j!=0 && j<4) {
					cuadroPosY+=70;
				}
				cuadros[i][j]=new JTextField();
				cuadros[i][j].setBorder(null);
				cuadros[i][j].setBackground(new Color(205,193,180));
				cuadros[i][j].setFont(new Font("Tahoma", Font.BOLD, 25));
				cuadros[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				cuadros[i][j].setEditable(false);
				cuadros[i][j].setBounds(cuadroPosX, cuadroPosY, cuadrosTamanio, cuadrosTamanio);
				contenedorDeCuadros.add(cuadros[i][j]);
				cuadros[i][j].setColumns(10);
				
				if(j==3) {
					cuadroPosX+=70;
					cuadroPosY=10;
				}
				
				iniciarTableroGrafico(i, j);
				
			}
		}
		
	}
	
		
	////////////////////////////////////////////////////////////////////////////////////////
	public void escucharPanel(JPanel panel){
		panel.setFocusable(true);
		panel.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e){
				
                if(e.getKeyCode()==KeyEvent.VK_RIGHT){
                    System.out.println("presiono derecha");
                }
                if(e.getKeyCode()==KeyEvent.VK_LEFT){
                    System.out.println("presiono izquierda");
                }
                if(e.getKeyCode()==KeyEvent.VK_UP){
                    System.out.println("presiono arriba");
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN){
                    System.out.println("presiono abajo");
                }
                if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
                    System.exit(0);
                }
                
            }
		});
	}
	

	////////////////////////////////////////////////////////////////////////////////////////
	public void iniciarTableroGrafico(int i, int j) {
		//Se recuperan los valores del tablero de negocio
		String valor=Integer.toString(tableroDeValores.getValor(i, j));
		//Se actualiza el valor de cada cuadro y su color de fondo
		actualizarCuadros(cuadros[i][j], valor);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	public void botonJuegoNuevo() {
		
		JButton juegoNuevo = new JButton("Juego nuevo");
		//juegoNuevo.setEnabled(true);
		juegoNuevo.setBorder(null);
		juegoNuevo.setFocusable(false);
		
		
		//El botón de juego nuevo esta a la escucha de eventos sobre el
		juegoNuevo.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				//Si se da el evento click, se crea un juego nuevo a partir del metodo nuevo()
				nuevo();
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				juegoNuevo.setBackground(new Color(131,110,90).darker());
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				juegoNuevo.setBackground(new Color(143,122,102));
			}
			
			public void mousePressed(MouseEvent e) {
				juegoNuevo.setForeground(new Color(143,122,102).darker());
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				juegoNuevo.setForeground(new Color(249,246,242));
			}
			
			
		});
		
		
		//A partir de que el foco lo tiene el botón se empieza a escuchar desde aquí al teclado
		juegoNuevo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_RIGHT){
                    System.out.println("presiono derecha");
                }
                if(e.getKeyCode()==KeyEvent.VK_LEFT){
                    System.out.println("presiono izquierda");
                }
                if(e.getKeyCode()==KeyEvent.VK_UP){
                    System.out.println("presiono arriba");
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN){
                    System.out.println("presiono abajo");
                }
                if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
                    System.exit(0);
                }
			}
		});
		
		//se estiliza el botón juegoNuevo y se lo agrega
		juegoNuevo.setBackground(new Color(143,122,102));
		juegoNuevo.setForeground(new Color(249,246,242));
		juegoNuevo.setFont(new Font("Tahoma", Font.BOLD, 15));
		juegoNuevo.setBounds(135, 389, 136, 35);
		ventana.getContentPane().add(juegoNuevo);
		
	}
	
	
	public void nuevo() {
		//Se pide al tablero de negocio que se reinicie
		tableroDeValores.reiniciar();
		
		//Se recorren los cuadros graficos
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				
				//Se borran los textos de los cuadros del juego anterior
				cuadros[i][j].setText(null);
				
				//Se vuelve a poner el color de fondo inicial
				cuadros[i][j].setBackground(new Color(205,193,180));
				
				//Se obtiene el valor de cada cuadro y se lo setea
				iniciarTableroGrafico(i, j);
				
				ventana.toFront();
				
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
			
			//A partir de acá hay que configurar los colores, hacer cuando se halla resuelto la funcion de las teclas
			else if(cuadro.getText().equals("8")){
				cuadro.setBackground(new Color(237,224,200));
				cuadro.setForeground(new Color(205,193,180));
			}
			else if(cuadro.getText().equals("16")){
				cuadro.setBackground(new Color(237,224,200));
				cuadro.setForeground(new Color(205,193,180));
			}
			else if(cuadro.getText().equals("32")){
				cuadro.setBackground(new Color(237,224,200));
				cuadro.setForeground(new Color(205,193,180));
			}
			else if(cuadro.getText().equals("64")){
				cuadro.setBackground(new Color(237,224,200));
				cuadro.setForeground(new Color(205,193,180));
			}
			else if(cuadro.getText().equals("128")){
				cuadro.setBackground(new Color(237,224,200));
				cuadro.setForeground(new Color(205,193,180));
			}
		}
		
	}

	
	////////////////////////////////////////////////////////////////////////////////////////
	
}
