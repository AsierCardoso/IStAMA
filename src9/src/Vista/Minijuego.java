package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.CandySopa;
import Modelo.GestorEventosTama;
import Modelo.Ladrillo;
import Modelo.Ladrillo0;
import Modelo.Tablero;
import Modelo.Tamagotchi;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

public class Minijuego extends JFrame implements Observer{

	
	
	private Controler controler = null;
	
	private JPanel contentPane;
	private JPanel Arriba;
	private JLabel NombreTama;
	private JLabel Score;
	private JButton BotónSalir;
	private JPanel Abajo;
	private JLabel TextoAbajo;
	private JPanel Muro;

	/**
	 * Launch the application.
	 */
	
	//////////////////////   NUNCA SE EJECUTA 
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tablero.getTablero().crearMuro();                     ///mover
					Minijuego frame = new Minijuego();
					Tablero.getTablero().notificarTodos();                 ///mover
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//////////////////////////////////
	
	
	/**
	 * Create the frame.
	 */
	public Minijuego() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getArriba(), BorderLayout.NORTH);
		contentPane.add(getAbajo(), BorderLayout.SOUTH);
		contentPane.add(getMuro(), BorderLayout.CENTER);
		
		
		Tablero.getTablero().addObserver(this);
		GestorEventosTama.getGestorEventosTama().addObserver(this);
	
		crearLabels();
	}
	
	
	
	@Override
	public void update(Observable o, Object arg) {
		
		int[] array = (int[]) arg;
		
		
	
		if (o instanceof GestorEventosTama && array[0] == 2) {
			
			setVisible(false);
			PantallaJuego.getPantallaJuego().setVisible(true);
			
		}
		
		if(o instanceof GestorEventosTama && array[0] == 3) {
			
			Score.setText("Score: " + array[1]);
			
		}
		
		if(o instanceof Tablero) {
			
			setVisible(false);
			PantallaJuego.getPantallaJuego().setVisible(true);
			
		}
		
		
		
		
		
		
	}

	private JPanel getArriba() {
		if (Arriba == null) {
			Arriba = new JPanel();
			FlowLayout flowLayout = (FlowLayout) Arriba.getLayout();
			flowLayout.setHgap(82);
			Arriba.add(getNombreTama());
			Arriba.add(getScore());
			Arriba.add(getBotónSalir());
		}
		return Arriba;
	}
	private JLabel getNombreTama() {
		if (NombreTama == null) {
			NombreTama = new JLabel("Kutchipatchi");
		}
		return NombreTama;
	}
	private JLabel getScore() {
		if (Score == null) {
			Score = new JLabel("Score: ");
		}
		return Score;
	}
	private JButton getBotónSalir() {
		if (BotónSalir == null) {
			BotónSalir = new JButton("Salir");
		}
		BotónSalir.addActionListener(getControler());
		return BotónSalir;
	}
	private JPanel getAbajo() {
		if (Abajo == null) {
			Abajo = new JPanel();
			Abajo.add(getTextoAbajo());
		}
		return Abajo;
	}
	private JLabel getTextoAbajo() {
		if (TextoAbajo == null) {
			TextoAbajo = new JLabel("Busca la Llave!!!");
		}
		return TextoAbajo;
	}
	private JPanel getMuro() {
		if (Muro == null) {
			Muro = new JPanel();
			Muro.setLayout(new GridLayout(8, 12, 0, 0));
		}
		
		return Muro;
	}
	
	
	
	///////////////////////////////////////
	
	///METODO CREAR LABELS
	
		private JLabelObserver matriz(int i, int j) { //parámetros de entrada
			JLabelObserver newLbl = new JLabelObserver(); //texto del botón
			newLbl.resize(getPreferredSize());
			newLbl.setBackground(Color.GREEN);
			newLbl.setOpaque(true);
			newLbl.addMouseListener(getControler());   
			newLbl.addKeyListener(getControler());
			Tablero.getTablero().getMuro()[i][j].addObserver(newLbl);   ////
			newLbl.setCoords(i,j);
																										
			return newLbl;
			}
		
		///
		
		///METODO CREAR LABELS 2
		
		private void crearLabels() {
			
			int i,j;
			for(i=0; i<8;i++) {
			for(j=0; j<12;j++) {
				
			Muro.add(matriz(i, j));
			
			}
			}
			}

		
	
	
	
	
	////////////////////////////////   CLASE LABEL OBSERVER
		
		
		public class JLabelObserver extends JLabel implements Observer{
			
			private int fila;
			private int col;
			
			@Override
			public void update(Observable o, Object arg) {
				
				setFocusable(true);
				requestFocusInWindow();
				
				int[] array = (int[]) arg;
				
				if(array[0] == 0) {
					
					
					if(array[1] == 1) {
						
						this.setBackground(Color.GREEN);
						
					}
					else if(array[2]==1){
						
						this.setBackground(Color.BLUE);
					}
					else {
						this.setBackground(Color.BLACK);
						
					}
					
					
					
				}
				else if(array[0] == 1) {
					
					this.setBackground(Color.YELLOW);
					
				}
				else if(array[0] == 2) {
					
					this.setBackground(Color.ORANGE);
					
				}
				else if(array[0] == 3) {
					
					this.setBackground(Color.RED);
					
				}
				
				
				
				
			}
			
			public void setCoords(int pFila, int pCol) {
				
				fila = pFila;
				col = pCol;
				
			}
			
			public int getFila() {
				
				return fila;
			}
			
			public int getCol() {
				
				return col;
			}
			
			
		
		}
	
	
	
////////////////CONTROLADOR	 ////////////////////////////////////////////////////////////
		
		
private Controler getControler() {
if (controler == null) {
	controler = new Controler();
}
return controler;
}

private class Controler implements ActionListener, MouseListener, KeyListener {
public void actionPerformed(ActionEvent e) {
	
	if (e.getSource().equals(BotónSalir)){
		
		System.exit(0);
	}
	
	
	
	
}

@Override
public void mouseClicked(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mousePressed(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseReleased(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseEntered(MouseEvent e) {
	
	if(e.getSource() instanceof JLabelObserver) {
		
		JLabelObserver e2 = (JLabelObserver)e.getSource();
		int fila = e2.getFila();
		int col = e2.getCol();
		Tablero.getTablero().getMuro()[fila][col].bajarDureza();
			
		
	}
	
	
}

@Override
public void mouseExited(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void keyTyped(KeyEvent e) {
	
	
}

@Override
public void keyPressed(KeyEvent e) {
	
	int tecla = e.getKeyCode();
	Tablero.getTablero().moverJugador(tecla);
	
	}
	


@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	
}


}




	
	
	
	
	
	
	
	
	
	
	
	
}
