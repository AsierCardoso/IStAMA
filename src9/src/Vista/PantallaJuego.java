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
	private JButton BotónSalir;
	private JLabel NumeroCoras;
	private JLabel NumeroSopas;
	private JButton BotónTama;
	private JPanel Abajo;
	private JLabel Candy;
	private JLabel Soup;
	private JButton BotónCandy;
	private JButton BotónSoup;
	private Controler controler = null;
	private JPanel CentroIzq;
	private JButton EnfermoBotón;
	private JButton SucioBotón;
	
	
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
			
			BotónCandy.setText(candy + "");
			BotónSoup.setText(sopa + "");
			
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
				EnfermoBotón.setText("Enfermo!");
			}
			else {
				EnfermoBotón.setText("");
			}
			if(sucio == 1) {
				SucioBotón.setText("Sucio!");
			}
			else {
				SucioBotón.setText("");
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
			Arriba.add(getBotónSalir());
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
			Centro.add(getBotónTama());
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
	private JButton getBotónSalir() {
		if (BotónSalir == null) {
			BotónSalir = new JButton("Salir");
		}
		BotónSalir.addActionListener(getControler());
		return BotónSalir;
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
	private JButton getBotónTama() {
		if (BotónTama == null) {
			BotónTama = new JButton("");	
		}
		
		BotónTama.addActionListener(getControler());
		
		
		return BotónTama;
	}
	private JPanel getAbajo() {
		if (Abajo == null) {
			Abajo = new JPanel();
			Abajo.setLayout(new GridLayout(2, 2, 0, 0));
			Abajo.add(getLabel_1());
			Abajo.add(getLabel_2());
			Abajo.add(getBotónCandy());
			Abajo.add(getBotónSoup());
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
	private JButton getBotónCandy() {
		if (BotónCandy == null) {
			BotónCandy = new JButton("0");
		}
		
		BotónCandy.addActionListener(getControler());
		return BotónCandy;
	}
	private JButton getBotónSoup() {
		if (BotónSoup == null) {
			BotónSoup = new JButton("0");
		}
		BotónSoup.addActionListener(getControler());
		return BotónSoup;
	}
	
	
	private JPanel getCentroIzq() {
		if (CentroIzq == null) {
			CentroIzq = new JPanel();
			CentroIzq.setLayout(new GridLayout(2, 1, 0, 0));
			CentroIzq.add(getEnfermoBotón());
			CentroIzq.add(getSucioBotón());
		}
		return CentroIzq;
	}
	private JButton getEnfermoBotón() {
		if (EnfermoBotón == null) {
			EnfermoBotón = new JButton("");
		}
		EnfermoBotón.addActionListener(getControler());
		return EnfermoBotón;
	}
	private JButton getSucioBotón() {
		if (SucioBotón == null) {
			SucioBotón = new JButton("");
		}
		SucioBotón.addActionListener(getControler());
		return SucioBotón;
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
			if (e.getSource().equals(BotónCandy)){

				CandySopa.getCandySopa().darCandy();
				
				
			}
			if (e.getSource().equals(BotónSoup)){

				CandySopa.getCandySopa().darSopa();
				
				
			}
			if (e.getSource().equals(BotónTama)){

				Tama.comerYCuidar();
				
				
			}
			if (e.getSource().equals(BotónSalir)){
				
				System.exit(0);
			}
			if (e.getSource().equals(EnfermoBotón)){
				
				Tama.curar();
			}
			if (e.getSource().equals(SucioBotón)){
				
				Tama.limpiar();
			}
			
			
		}
		
		
		
		
		
		
	}
	
	
	
}
