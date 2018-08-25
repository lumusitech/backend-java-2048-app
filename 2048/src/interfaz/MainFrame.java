package interfaz;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.Color;

public class MainFrame {

	private JFrame ventana;
	private JTextField[][] cuadros;
	private int cuadroPosX;
	private int cuadroPosY;
	private int cuadrosTamanio;


	//Lanza la aplicación
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

	//crea la aplicación
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

	public void tableroDeJuego() {
		//Se crea el panel que contiene a los cuadros
		JPanel contenedorDeCuadros = new JPanel();
		contenedorDeCuadros.setBackground(Color.DARK_GRAY);
		contenedorDeCuadros.setBounds(161, 69, 290, 290);
		ventana.getContentPane().add(contenedorDeCuadros);
		contenedorDeCuadros.setLayout(null);
		
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
				cuadros[i][j].setFont(new Font("Tahoma", Font.PLAIN, 16));
				cuadros[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				cuadros[i][j].setEditable(false);
				cuadros[i][j].setBounds(cuadroPosX, cuadroPosY, cuadrosTamanio, cuadrosTamanio);
				contenedorDeCuadros.add(cuadros[i][j]);
				cuadros[i][j].setColumns(10);
				
				if(j==3) {
					cuadroPosX+=70;
					cuadroPosY=10;
				}
			}
		}
	}

	public void ventanaPrincipal() {
		ventana = new JFrame();
		ventana.setTitle("Juego 2048");
		ventana.setBounds(100, 100, 640, 480);
		ventana.setResizable(false);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.getContentPane().setLayout(null);
		
		//Título
		JLabel titulo = new JLabel("2048");
		titulo.setBounds(222, 11, 158, 50);
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(new Font("Times New Roman", Font.BOLD, 30));
		ventana.getContentPane().add(titulo);
	}
}