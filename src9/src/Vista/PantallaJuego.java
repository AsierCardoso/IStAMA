package Vista;

import java.awt.EventQueue;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.CandySopa;
import Modelo.GestorEventosTama;
import Modelo.Tablero;
import Modelo.Tamagotchi;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;


public class PantallaJuego extends JFrame implements Observer {

	Tamagotchi Tama = Tamagotchi.getTamagotchi();
	CandySopa cs = CandySopa.getCandySopa();
	
	private static PantallaJuego miPantallaJuego = new PantallaJuego();
	
	private JPanel contentPane;
	private JPanel Arriba;
	private JPanel Izq;
	private JPanel Dcha;
	private JPanel Centro;
	private JLabel NombreTama;
	private JLabel Score;
	private JButton Bot�nSalir;
	private JLabel NumeroCoras;
	private JLabel NumeroSopas;
	private JButton Bot�nTama;
	private JPanel Abajo;
	private JLabel Candy;
	private JLabel Soup;
	private JButton Bot�nCandy;
	private JButton Bot�nSoup;
	private Controler controler = null;
	private JPanel CentroIzq;
	private JButton EnfermoBot�n;
	private JButton SucioBot�n;
	
	
	/////////////////////   NUNCA SE EJECUTA ///////////////////

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaJuego frame = new PantallaJuego();
					frame.setVisible(true);
					GestorEventosTama.getGestorEventosTama();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	///////////////////////////////////////////////////////////
	
	
	/**
	 * Create the frame.
	 */
	private PantallaJuego() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getArriba(), BorderLayout.NORTH);
		contentPane.add(getIzq(), BorderLayout.WEST);
		contentPane.add(getDcha(), BorderLayout.EAST);
		contentPane.add(getCentro(), BorderLayout.CENTER);
		contentPane.add(getAbajo(), BorderLayout.SOUTH);
		
		CandySopa.getCandySopa().addObserver(this);
		Tamagotchi.getTamagotchi().addObserver(this);
		GestorEventosTama.getGestorEventosTama().addObserver(this);
			
	}
	
	
	
	public static PantallaJuego getPantallaJuego() {
		return miPantallaJuego;
	}

	@Override
	public void update(Observable o, Object arg) {
		
		int[] array = (int[]) arg;
		
		if (o instanceof GestorEventosTama && array[0] == 1) {
			setVisible(false);
			Minijuego minijuego = new Minijuego();
			minijuego.setVisible(true);
		}
		
		if(o instanceof GestorEventosTama && array[0] == 3) {
			Score.setText("Score: " + array[1]);
		}

		
		if (o instanceof CandySopa) {
			
			int candy = array[0];
			int sopa = array[1];
			
			Bot�nCandy.setText(candy + "");
			Bot�nSoup.setText(sopa + "");
			
		}
	
		if(o instanceof Tamagotchi) {
			
			int vivo = array[0];
			int apetito = array[2];
			int corazones = array[1];
			int enfermo = array[3];
			int sucio = array[4];
			
			NumeroSopas.setText(apetito + "");
			NumeroCoras.setText(corazones + "");
			
			
			if(enfermo == 1) {
				EnfermoBot�n.setText("Enfermo!");
			}
			else {
				EnfermoBot�n.setText("");
			}
			if(sucio == 1) {
				SucioBot�n.setText("Sucio!");
			}
			else {
				SucioBot�n.setText("");
			}

			if(vivo == 0) {				
				setVisible(false);
				Muerte.getMuerte().setVisible(true);
			}
			
		}
		
	}
	
	
	

	private JPanel getArriba() {
		if (Arriba == null) {
			Arriba = new JPanel();
			FlowLayout flowLayout = (FlowLayout) Arriba.getLayout();
			flowLayout.setHgap(82);
			Arriba.add(getNombreTama());
			Arriba.add(getScore());
			Arriba.add(getBot�nSalir());
		}
		return Arriba;
	}
	private JPanel getIzq() {
		if (Izq == null) {
			Izq = new JPanel();
			FlowLayout flowLayout = (FlowLayout) Izq.getLayout();
			flowLayout.setVgap(82);
			Izq.add(getNumeroCoras());
		}
		return Izq;
	}
	private JPanel getDcha() {
		if (Dcha == null) {
			Dcha = new JPanel();
			FlowLayout flowLayout = (FlowLayout) Dcha.getLayout();
			flowLayout.setVgap(82);
			Dcha.add(getNumeroSopas());
		}
		return Dcha;
	}
	private JPanel getCentro() {
		if (Centro == null) {
			Centro = new JPanel();
			Centro.setLayout(new GridLayout(1, 2, 0, 0));
			Centro.add(getBot�nTama());
			Centro.add(getCentroIzq());
			
		}
		return Centro;
	}
	private JLabel getNombreTama() {
		if (NombreTama == null) {
			NombreTama = new JLabel(Tama.getNombre());
		}
		return NombreTama;
	}
	private JLabel getScore() {
		if (Score == null) {
			Score = new JLabel("Score: 0");
		}
		return Score;
	}
	private JButton getBot�nSalir() {
		if (Bot�nSalir == null) {
			Bot�nSalir = new JButton("Salir");
		}
		Bot�nSalir.addActionListener(getControler());
		return Bot�nSalir;
	}
	private JLabel getNumeroCoras() {
		if (NumeroCoras == null) {
			NumeroCoras = new JLabel("40");
		}
		return NumeroCoras;
	}
	private JLabel getNumeroSopas() {
		if (NumeroSopas == null) {
			NumeroSopas = new JLabel("40");
		}
		return NumeroSopas;
	}
	private JButton getBot�nTama() {
		if (Bot�nTama == null) {
			Bot�nTama = new JButton("");	
		}
		
		Bot�nTama.addActionListener(getControler());
		
		
		return Bot�nTama;
	}
	private JPanel getAbajo() {
		if (Abajo == null) {
			Abajo = new JPanel();
			Abajo.setLayout(new GridLayout(2, 2, 0, 0));
			Abajo.add(getLabel_1());
			Abajo.add(getLabel_2());
			Abajo.add(getBot�nCandy());
			Abajo.add(getBot�nSoup());
		}
		return Abajo;
	}
	private JLabel getLabel_1() {
		if (Candy == null) {
			Candy = new JLabel("Candy");
			Candy.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return Candy;
	}
	private JLabel getLabel_2() {
		if (Soup == null) {
			Soup = new JLabel("Soup");
			Soup.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return Soup;
	}
	private JButton getBot�nCandy() {
		if (Bot�nCandy == null) {
			Bot�nCandy = new JButton("0");
		}
		
		Bot�nCandy.addActionListener(getControler());
		return Bot�nCandy;
	}
	private JButton getBot�nSoup() {
		if (Bot�nSoup == null) {
			Bot�nSoup = new JButton("0");
		}
		Bot�nSoup.addActionListener(getControler());
		return Bot�nSoup;
	}
	
	
	private JPanel getCentroIzq() {
		if (CentroIzq == null) {
			CentroIzq = new JPanel();
			CentroIzq.setLayout(new GridLayout(2, 1, 0, 0));
			CentroIzq.add(getEnfermoBot�n());
			CentroIzq.add(getSucioBot�n());
		}
		return CentroIzq;
	}
	private JButton getEnfermoBot�n() {
		if (EnfermoBot�n == null) {
			EnfermoBot�n = new JButton("");
		}
		EnfermoBot�n.addActionListener(getControler());
		return EnfermoBot�n;
	}
	private JButton getSucioBot�n() {
		if (SucioBot�n == null) {
			SucioBot�n = new JButton("");
		}
		SucioBot�n.addActionListener(getControler());
		return SucioBot�n;
	}
	
	
	
	
	
////////////////   CONTROLADOR	 ////////////////////////////////////////////////////////////
	
	
	private Controler getControler() {
		if (controler == null) {
			controler = new Controler();
		}
		return controler;
	}
	
	private class Controler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(Bot�nCandy)){

				CandySopa.getCandySopa().darCandy();
				
				
			}
			if (e.getSource().equals(Bot�nSoup)){

				CandySopa.getCandySopa().darSopa();
				
				
			}
			if (e.getSource().equals(Bot�nTama)){

				Tama.comerYCuidar();
				
				
			}
			if (e.getSource().equals(Bot�nSalir)){
				
				System.exit(0);
			}
			if (e.getSource().equals(EnfermoBot�n)){
				
				Tama.curar();
			}
			if (e.getSource().equals(SucioBot�n)){
				
				Tama.limpiar();
			}
			
			
		}
		
		
		
		
		
		
	}
	
	
	
}
