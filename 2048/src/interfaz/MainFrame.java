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

public class MainFrame {

	private JFrame ventana;
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
	}
	
	
	////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////METODOS AUXILIARES/////////////////////////////////////

	public void tableroDeJuego() {
		//Se crea el panel que contiene a los cuadros
		JPanel contenedorDeCuadros = new JPanel();
		contenedorDeCuadros.setBackground(new Color(187, 173, 160));
		contenedorDeCuadros.setBounds(161, 69, 290, 290);
		ventana.getContentPane().add(contenedorDeCuadros);
		contenedorDeCuadros.setLayout(null);
		
		//Se crea un nuevo tablero de valores
		tableroDeValores = new Tablero();
		
		//Inicializa el tablero y la posicin de los cuadros
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
				
				//Se recuperan los valores del tablero de negocio
				String valor=Integer.toString(tableroDeValores.getValor(i, j));
				if(!valor.equals("0")) {
					//si no son cero se los muestra
					cuadros[i][j].setBorder(null);
					cuadros[i][j].setBackground(new Color(238,228,218));
					cuadros[i][j].setForeground(new Color(119,110,101));
					cuadros[i][j].setText(valor);
				}
				
			}
		}
		
	}

	public void ventanaPrincipal() {
		ventana = new JFrame();
		ventana.getContentPane().setBackground(new Color(250, 248, 239));
		ventana.setTitle("Juego 2048");
		ventana.setBounds(100, 100, 640, 480);
		ventana.setResizable(false);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.getContentPane().setLayout(null);
		
		//Ttulo
		JLabel titulo = new JLabel("2048");
		titulo.setBounds(222, 11, 158, 50);
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setForeground(new Color(119,110,101));
		titulo.setFont(new Font("Times New Roman", Font.BOLD, 50));
		ventana.getContentPane().add(titulo);
	}
}
